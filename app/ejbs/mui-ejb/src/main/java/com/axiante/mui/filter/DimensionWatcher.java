package com.axiante.mui.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.http.cookie.Cookie;

import com.axiante.Tm1Tunnel;
import com.axiante.connection.ConnectionData;
import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.common.utility.HttpUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.utils.ApplicationConfiguration;
import com.axiante.tm1.TunnelConstants;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class DimensionWatcher implements Serializable {
	private static final long serialVersionUID = 5918731331166614060L;

	@Getter
	Map<String, DimensionWatch> watches = Collections.synchronizedMap(new HashMap<String, DimensionWatch>());

	@Inject
	ApplicationConfiguration applicationConfiguration;

	public boolean check(final String dimension, final Cookie cookie, final ConnectionProfile profile) {
		boolean result = false;
		final DimensionWatch watch = query(dimension, cookie, profile);
		if ( watch != null ) {
			final DimensionWatch previous = watches.get(watch.getDimension());
			if ( previous != null && previous.equals(watch)) {
				log.debug("dimension " + dimension + " not changed");
				result = true;
			} else {
				// new watch
				log.debug("dimension " + dimension + " updated");
				watches.put(watch.getDimension(), watch);
			}
		}

		return result;
	}


	public boolean checkVersion(final Cookie cookie, final ConnectionProfile profile) {
		boolean result = false;
		final DimensionWatch watch = queryVersion(cookie, profile);
		if ( watch != null ) {
			final DimensionWatch previous = watches.get(watch.getDimension());
			if ( previous != null && previous.equals(watch)) {
				log.debug("endpoint " + profile.getName()+ " not changed");
				result = true;
			} else {
				// new watch
				log.debug("endpoint " + profile.getName() + " updated");
				watches.put(watch.getDimension(), watch);
			}
		}

		return result;
	}

	DimensionWatch queryVersion(final Cookie cookie, final ConnectionProfile profile) {
		final StringBuffer json = new StringBuffer();
		try(Tm1Tunnel tunnel = new Tm1Tunnel(profile) ){
			final String query = TunnelConstants.ExecuteMDX + "$expand=Cells";
			final String mdx = "select {[Log - Processi].[_MASTER_LOAD_ALL]} on rows , {[Log - Misura].[DataInizio]} on columns from [Log - Reporting]";
			final ConnectionData data = tunnel.executeRequestAsConnectionData("POST", query, mdx,  null, applicationConfiguration.getConnectionTimeout() , cookie);


			if ( data != null ) {
				if ( new HttpUtils().checkResponse(data.getResponse())) {
					final BufferedReader rd = new BufferedReader(new InputStreamReader(data.getResponse().getEntity().getContent()));
					String line;
					json.delete(0, json.length());
					while ((line = rd.readLine()) != null) {
						json.append(line).append("\n");
					}
				}
				data.close();
			}
		} catch (final URISyntaxException e) {
			log.error("error querying dimension status " , e);
		} catch (final IOException e) {
			log.error("error querying dimension status " , e);
		} catch (final Exception e) {
			log.error("error querying dimension status " , e);
		}

		// read the response
		return parseJsonCube(json, profile.getName());
	}

	DimensionWatch parseJsonCube(final StringBuffer json, @NonNull final String profileName) {
		String dataUpdate = null;
		String schemaUpdate = null;
		DimensionWatch result = null;
		try {
			if (json.length() > 0 ) {
				final JsonNode root = JsonUtils.getMapper().readTree(json.toString());
				final JsonNode cells = root != null ?
						root.get("Cells") != null ? root.get("Cells").get(0) : null
								: null;
				if ( cells != null) {
					JsonNode node = cells.get("Value");
					if ( node != null ) {
						dataUpdate = node.textValue();
					}
					node = cells.get("FormattedValue");
					if ( node != null ) {
						schemaUpdate = node.textValue();
					}
					node = null;
					result = new DimensionWatch(profileName, schemaUpdate, dataUpdate);
				} else {
					log.error("Error parsing version from json response " + json);
				}
			}
		} catch (final IOException e) {
			log.error("Error parsing version from json response " + json, e);
		}
		return result;
	}


	DimensionWatch query(final String dimension, final Cookie cookie, final ConnectionProfile profile) {
		final StringBuffer json = new StringBuffer();
		try(Tm1Tunnel tunnel = new Tm1Tunnel(profile) ){
			final String query = "Cubes(\'%7DElementAttributes_"+URLEncoder.encode(dimension,"UTF-8")+"\')";
			final ConnectionData data = tunnel.executeRequestAsConnectionData("GET", query, null, null, applicationConfiguration.getConnectionTimeout() , cookie);

			if ( data != null ) {
				if ( new HttpUtils().checkResponse(data.getResponse())) {
					final BufferedReader rd = new BufferedReader(new InputStreamReader(data.getResponse().getEntity().getContent()));
					String line;
					json.delete(0, json.length());
					while ((line = rd.readLine()) != null) {
						json.append(line).append("\n");
					}
				}
				data.close();
			}
		} catch (final URISyntaxException e) {
			log.error("error querying dimension status " , e);
		} catch (final IOException e) {
			log.error("error querying dimension status " , e);
		} catch (final Exception e) {
			log.error("error querying dimension status " , e);
		}
		return parseJsonDimension(json, dimension);
	}
	DimensionWatch parseJsonDimension(final StringBuffer json,  final String dimension) {
		// read the response
		String dataUpdate = null;
		String schemaUpdate = null;
		DimensionWatch result  = null;
		try {
			if (json.length() > 0 ) {
				JsonNode root;
				root = JsonUtils.getMapper().readTree(json.toString());
				JsonNode node = root.get(DimensionWatch.LastDataUpdate);
				if ( node != null ) {
					dataUpdate = node.textValue();
				}
				node = root.get(DimensionWatch.LastSchemaUpdate);
				if ( node != null ) {
					schemaUpdate = node.textValue();
				}
				node = null;
				result = new DimensionWatch(dimension, schemaUpdate, dataUpdate);
			}
		} catch (final IOException e) {
			log.error("error parsing response for dimension status ", e);
		}
		return result;

	}
}

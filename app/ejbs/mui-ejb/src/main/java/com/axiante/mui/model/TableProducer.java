package com.axiante.mui.model;

import java.io.Serializable;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.backing.MuiToken;
import com.axiante.mui.common.interfaces.MuiConnectionListener;
import com.axiante.tm1.json.objects.Table;
import com.axiante.tm1.mdx.objects.Query;
import com.axiante.utility.configuration.Configuration;

public interface TableProducer extends Serializable, MuiConnectionListener{
    List<String> producePicklist(Configuration configuration, Query filteredQuery, Integer cellOrdinal,Double connectionTimeout, Integer socketTimeout,Integer connectionRequestTimeout, Cookie cookie) ;
    Table produceTable(Configuration configuration,Query filteredQuery,  String cellSetId, boolean attributesData, Cookie cookie, Double timeout);
    MuiToken generateCellset(Configuration configuration, Query query, Cookie cookie);
    Table produceTableSkelton(MuiToken muiToken, Query filteredQuery, String token, String cellSetId);
    Table produceTableHeaders(MuiToken muiToken, Query filteredQuery, String cellSetId) ;
    CloseableHttpResponse produceTableContentReader(Configuration configuration,String cellSetId, Cookie cookie) ;
    boolean forceCloseSession(Cookie cookie, ConnectionProfile profile);
}

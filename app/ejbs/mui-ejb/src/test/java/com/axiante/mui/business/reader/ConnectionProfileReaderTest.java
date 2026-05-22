package com.axiante.mui.business.reader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.axiante.connection.ConnectionProfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionProfileReaderTest {

	private static final String json = "[{\n" + 
			"	\"pluto\": {\n" + 
			"		\"username\": \"rambaldo.melandri\",\n" + 
			"		\"password\": \"?PassworD!posterdati\",\n" + 
			"		\"domain\": \"necchi\",\n" + 
			"		\"host\": \"http://sblinda.com\",\n" + 
			"		\"port\": \":14041\",\n" + 
			"		\"path\": \"/api/v1/\"\n" + 
			"	},\n" + 
			"	\"pippo\": {\n" + 
			"		\"username\": \"lello.mascetti\",\n" + 
			"		\"password\": \"?PassworD!antanty\",\n" + 
			"		\"domain\": \"sassaroli\",\n" + 
			"		\"host\": \"http://sbiriguda.com\",\n" + 
			"		\"port\": \":14041\",\n" + 
			"		\"path\": \"/api/v1/\"\n" + 
			"	}\n" + 
			"}]";
	
	
	@Test
	public void testParseJsonSuccess() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(json);
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		
		assertNotNull(reader);
	}
	
	@Test
	public void testReadJsonReturnsObjects() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(json);
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		
		assertThat(reader.getConfigurations().keySet().size() , CoreMatchers.equalTo(2));
	}
	
	@Test
	public void testReadJsonContainsCorrectKeys() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(json);
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		
		assertTrue(reader.getConfigurations().keySet().contains("pippo"));
		assertTrue(reader.getConfigurations().keySet().contains("pluto"));
	}
	
	@Test
	public void testReadJsonContainsCorrectObject() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(json);
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		ConnectionProfile profile = reader.getConfigurations().get("pluto");
		assertNotNull(profile);

		ConnectionProfile test = new ConnectionProfile();
		test.setUsername("rambaldo.melandri");
		test.setPassword("?PassworD!posterdati"); 
		test.setDomain  ("necchi"); 
		test.setHost    ("http://sblinda.com"); 
		test.setPort    (":14041"); 
		test.setPath    ("/api/v1/");
		test.setName    ("pluto");
		assertThat(profile, CoreMatchers.equalTo(test));
	}
	
	@Test
	public void testReadJsonContainsWrongObject() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(json);
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		ConnectionProfile profile = reader.getConfigurations().get("pippo");
		assertNotNull(profile);

		ConnectionProfile test = new ConnectionProfile();
		test.setUsername("rambaldo.melandri");
		test.setPassword("?PassworD!posterdati"); 
		test.setDomain  ("necchi"); 
		test.setHost    ("http://sblinda.com"); 
		test.setPort    (":14041"); 
		test.setPath    ("/api/v1/");
		test.setName    ("pippo");
		assertThat(profile, CoreMatchers.not(CoreMatchers.equalTo(test)));	
	}

	
	@Test
	public void testReadJsonStreamReturnsObjects() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(new ByteArrayInputStream(json.getBytes()));
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		
		assertThat(reader.getConfigurations().keySet().size() , CoreMatchers.equalTo(2));
	}
	
	@Test
	public void testReadJsonStreamContainsCorrectKeys() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(new ByteArrayInputStream(json.getBytes()));
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		
		assertTrue(reader.getConfigurations().keySet().contains("pippo"));
		assertTrue(reader.getConfigurations().keySet().contains("pluto"));
	}
	
	@Test
	public void testReadJsonStreamContainsCorrectObject() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(new ByteArrayInputStream(json.getBytes()));
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		ConnectionProfile profile = reader.getConfigurations().get("pluto");
		assertNotNull(profile);

		ConnectionProfile test = new ConnectionProfile();
		test.setUsername("rambaldo.melandri");
		test.setPassword("?PassworD!posterdati"); 
		test.setDomain  ("necchi"); 
		test.setHost    ("http://sblinda.com"); 
		test.setPort    (":14041"); 
		test.setPath    ("/api/v1/");
		test.setName    ("pluto");
		assertThat(profile, CoreMatchers.equalTo(test));
	}
	
	@Test
	public void testReadJsonStreamContainsWrongObject() {
		ConnectionProfileReader reader = null;
		try {
			reader = new ConnectionProfileReader().read(new ByteArrayInputStream(json.getBytes()));
		} catch (IOException e) {
			log.error("error reading json", e);
			assertTrue(false);
		}
		ConnectionProfile profile = reader.getConfigurations().get("pippo");
		assertNotNull(profile);

		ConnectionProfile test = new ConnectionProfile();
		test.setUsername("rambaldo.melandri");
		test.setPassword("?PassworD!posterdati"); 
		test.setDomain  ("necchi"); 
		test.setHost    ("http://sblinda.com"); 
		test.setPort    (":14041"); 
		test.setPath    ("/api/v1/");
		test.setName    ("pippo");
		assertThat(profile, CoreMatchers.not(CoreMatchers.equalTo(test)));	
	}

	@Test(expected = NullPointerException.class)
	public void testReadNullStringDataThrowsExceptio() throws IOException {
		String nullString = null;
		new ConnectionProfileReader().read(nullString);
	}
	
	@Test(expected = NullPointerException.class)
	public void testReadNullStreamDataThrowsExceptio() throws IOException {
		InputStream nullStream = null;
		new ConnectionProfileReader().read(nullStream);
	}
	
}

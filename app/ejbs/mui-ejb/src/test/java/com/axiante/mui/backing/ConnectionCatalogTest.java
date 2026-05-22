package com.axiante.mui.backing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.axiante.connection.ConnectionProfile;
import com.axiante.mui.persistence.entity.ConnectionSetupEntity;
import com.axiante.mui.persistence.service.MuiService;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class ConnectionCatalogTest {
	@Mock
	MuiService myMuiService;

	@InjectMocks
	@Spy
	ConnectionCatalog connectionCatalog;
	@Rule
	public ExpectedException ex = ExpectedException.none();

	List<ConnectionSetupEntity> connectionSetupEntityList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		log.info("setting up test");
		connectionSetupEntityList = new ArrayList<>();
		connectionSetupEntityList.add(createConnectionSetupEntity("promo", null, null, null, "https://TMONETEST", 8882,
				"/api/v1/", false, "PASSPORT"));
		connectionSetupEntityList.add(createConnectionSetupEntity("reporting", null, null, null, "https://TMONETEST",
				8883, "/api/v1/", false, "PASSPORT"));
		when(myMuiService.readConnectionSetups()).thenReturn(connectionSetupEntityList);
		connectionCatalog.update();
	}

	private ConnectionSetupEntity createConnectionSetupEntity(String name, String username, String password,
			String domain, String host, Integer port, String path, Boolean validateSsl, String authType) {
		ConnectionSetupEntity connectionSetupEntity = new ConnectionSetupEntity();
		connectionSetupEntity.setConnectionName(name);
		connectionSetupEntity.setUsername(username);
		connectionSetupEntity.setPassword(password);
		connectionSetupEntity.setDomain(domain);
		connectionSetupEntity.setHost(host);
		connectionSetupEntity.setPort(port);
		connectionSetupEntity.setPath(path);
		connectionSetupEntity.setValidateSsl(validateSsl);
		connectionSetupEntity.setAuthType(authType);
		return connectionSetupEntity;
	}

	@Test
	public void init() {
		connectionCatalog.init();
	}

	@Test
	public void update() {
		assertTrue(connectionCatalog.update());
	}

	@Test
	public void testCatalogIsNotEmpty() {
		assertTrue(connectionCatalog.getSize() > 0);
	}

	@Test
	public void testCatalogContainsKey() {
		assertNotNull(connectionCatalog.getProfile("promo"));
	}

	@Test
	public void getSize() {
		if (connectionSetupEntityList.size() != connectionCatalog.getSize()) {
			StringBuffer buffer = new StringBuffer("connectionSetupEntityList:\n");
			for (ConnectionSetupEntity c : connectionSetupEntityList) {
				buffer.append(c.getConnectionName()).append("\n");
			}
			buffer.append("connectionCatalog:\n");
			for (ConnectionProfile c : connectionCatalog.availableConfigurationList()) {
				buffer.append(c.getName()).append("\n");
			}
			Assert.fail(buffer.toString());
		} else {
			assertEquals(connectionSetupEntityList.size(), connectionCatalog.getSize());
		}
	}

	@Test
	public void avaliableConfigurations() {
		connectionCatalog.avaliableConfigurations();
	}

	@Test
	public void getMuiService() {
		assertNotNull(connectionCatalog.getMuiService());
	}

	@Test
	public void testCatalogNotContainsKey() {
		assertNull(connectionCatalog.getProfile("notInList"));
	}

	@Test
	public void testMultiThread() throws InterruptedException, ExecutionException {
		CountDownLatch latch = new CountDownLatch(1);
		AtomicBoolean running = new AtomicBoolean();
		AtomicInteger overlaps = new AtomicInteger();
		int threads = 100;
		ExecutorService service = Executors.newFixedThreadPool(threads);
		Collection<Future<Boolean>> futures = new ArrayList<>(threads);

		for (int t = 0; t < threads; ++t) { // generate a list of threads
			futures.add(service.submit(() -> {
				latch.await(); // hold the execution of the thread
				if (running.get()) {// check if there is a concurrent call
					overlaps.incrementAndGet();
				}
				running.set(true); // mark the operation
				boolean id = connectionCatalog.update(); // do the operation
				running.set(false); // mark the end of the operation
				return id; // return the result of the operation
			}));
		}
		latch.countDown(); // start all threads ... they were waiting for the object to finish

		// check the results... aka return the value of the future task
		List<Boolean> ids = new ArrayList<>();
		for (Future<Boolean> f : futures) {
			ids.add(f.get());
		}

		if (!(overlaps.get() > 0)) {
			// we couldn't generate concurrencies ... log a warn !
			log.warn("could not generate concurrent hits: test results may be false positives !");
		}
//		assertTrue(overlaps.get() > 0); // check that we generated at least one concurrency
		assertThat(futures.size(), CoreMatchers.equalTo(threads)); // check the number of results matches the number of
																	// threads
		assertTrue(ids.stream().allMatch(Boolean::valueOf)); // check that all updates are successful
	}

	@Test
	public void testGetProfileThrowsNPE() {
		ex.expect(NullPointerException.class);
		connectionCatalog.getProfile(null);
	}

	@Test
	public void testUpdateReturnsEmptyConfiguration() throws Exception {
		when(myMuiService.readConnectionSetups()).thenReturn(new ArrayList<ConnectionSetupEntity>());
		assertTrue(connectionCatalog.update());
		assertThat(connectionCatalog.avaliableConfigurations(), CoreMatchers.equalTo("empty"));
	}

	@Test
	public void testAvailableConfigurationList() {
		List<ConnectionProfile> list = connectionCatalog.availableConfigurationList();
		assertNotNull(list);
	}

	@Test
	public void testUpdateReturnsFalse() throws Exception {
		doThrow(Exception.class).when(myMuiService).readConnectionSetups();
		assertFalse(connectionCatalog.update());
	}
}

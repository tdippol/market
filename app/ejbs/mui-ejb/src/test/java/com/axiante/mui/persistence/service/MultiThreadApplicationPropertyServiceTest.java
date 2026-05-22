package com.axiante.mui.persistence.service;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.impl.DaoTest;
import com.axiante.mui.persistence.dao.impl.JpaApplicationPropertiesDAO;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.service.impl.ApplicationPropertiesServiceImpl;

public class MultiThreadApplicationPropertyServiceTest extends DaoTest {

	@Inject
	ApplicationPropertiesService realService ;
	@Rule
	public WeldInitiator weld = WeldInitiator.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class,
			JpaApplicationPropertiesDAO.class, ApplicationPropertiesServiceImpl.class).activate(RequestScoped.class)
			.inject(this).build();

	@Before
	public void initialize() throws Exception {
		List<ApplicationPropertiesEntity> list = realService
				.findAnyById(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE);
		if (list.size() == 0) {
			ApplicationPropertiesEntity e = new ApplicationPropertiesEntity();
			e.setKey(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE);
			e.setValue(ApplicationProperties.DEFAULT_ADMIN_MODE_SCHEDULER_ACTIVE.toString());

			getEm().getTransaction().begin();
			e = realService.saveProperty(e);
			getEm().getTransaction().commit();

			if (!(e.getIdApplicationProperties() > 0)) {
				fail("could not initalize test");
			}
		}
		assertTrue(realService.findAnyById(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE).size() == 1);
	}

	@Test
	public void testWhenMultipleConcurrentGetOrSetThenAlwaysOneProperty() throws Exception {
		// simulate multiple users/daemons accessing MANUAL_ADMIN_MODE
		// verify that there is only one property
		CountDownLatch latch = new CountDownLatch(1);
		AtomicBoolean running = new AtomicBoolean();
		AtomicInteger overlaps = new AtomicInteger();
		int threads = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(threads);
		Collection<Future<Boolean>> futures = new ArrayList<>(threads);

		getEm().getTransaction().begin();
		for (int t = 0; t < threads; ++t) { // generate a list of threads
			futures.add(executorService.submit(() -> {
				latch.await(); // hold the execution of the thread
				if (running.get()) {// check if there is a concurrent call
					overlaps.incrementAndGet();
				}
				running.set(true); // mark the operation
				realService.getOrCreateProperty(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE,
						ApplicationProperties.DEFAULT_ADMIN_MODE_SCHEDULER_ACTIVE.toString());// do the operation
				running.set(false); // mark the end of the operation
				return true; // return the result of the operation
			}));
		}
		getEm().getTransaction().commit();

		latch.countDown(); // start all threads ... they were waiting for the object to finish

		// check the results... aka return the value of the future task
		List<Boolean> ids = new ArrayList<>();
		for (Future<Boolean> f : futures) {
			ids.add(f.get());
		}
//		assertTrue(overlaps.get() > 0); // check that we generated at least one concurrency
		assertThat(futures.size(), CoreMatchers.equalTo(threads)); // check the number of results matches the number of
																	// threads
		assertTrue(ids.stream().allMatch(Boolean::valueOf)); // check that all updates are successful

		assertTrue(realService.findAnyById(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE).size() == 1);

	}
}

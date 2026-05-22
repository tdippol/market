package com.axiante.mui.persistence.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hamcrest.CoreMatchers;
import org.hamcrest.number.OrderingComparison;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.axiante.mui.common.promo.DBPromoAuditLogEnum;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.AuditLogDAO;
import com.axiante.mui.persistence.entity.AuditLogEntity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuditLogDAOTest extends DaoTest {
	@Inject
	AuditLogDAO dao;

	@Spy
	@InjectMocks
	JpaAuditLogDAOImpl mockedDao;

	@Rule
	public WeldInitiator weld = WeldInitiator
	.from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaAuditLogDAOImpl.class)
	.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void testReadAll() {
		assertNotNull(dao.readAll());
	}

	@Test
	public void testPersist() throws Exception {
		AuditLogEntity entity = createEntry();
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			int before = dao.readAll().size();
			dao.persist(entity);
			int after = dao.readAll().size();
			em.getTransaction().commit();
			assertThat(after, CoreMatchers.equalTo(before + 1));
		}
	}

	@Test
	public void testPersistThrowsExceptionWhenAddedTwice() throws Exception {
		ex.expect(Exception.class);
		ex.expectMessage(contains("già presente"));
		mockedDao.persist(mockEntry());
	}

	@Test
	public void testRemove() throws Exception {
		AuditLogEntity e = createEntry();
		dao.remove(e);
	}

	@Test
	public void testRemoveThrowsExceptionObjectInUse() throws Exception {
		AuditLogEntity e = mockEntry();
		ex.expect(Exception.class);
		ex.expectMessage(contains("oggetto in uso"));
		Mockito.doThrow(new PersistenceException()).when(mockedEm).remove(ArgumentMatchers.any(AuditLogEntity.class));
		mockedDao.remove(e);
	}

	@Test
	public void testDeleteAll() throws Exception {
		when(mockedDao.getEm()).thenReturn(mockedEm);
		when(mockedEm.createQuery(ArgumentMatchers.anyString())).thenReturn(mock(Query.class));
		mockedDao.deleteAll();
		Mockito.verify(mockedEm).createQuery(JpaAuditLogDAOImpl.DELETE_ALL);
	}

	@Test
	public void testDeleteAllFilteredBefore() throws Exception {
		when(mockedDao.getEm()).thenReturn(mockedEm);
		Query q = mock(Query.class);
		when(q.setParameter(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(q);
		when(mockedEm.createQuery(ArgumentMatchers.anyString())).thenReturn(q);

		mockedDao.deleteAllFiltered(mock(Date.class), Boolean.TRUE);
		Mockito.verify(mockedEm).createQuery("DELETE FROM AuditLogEntity a WHERE a.logDate < :limitDay");
	}

	@Test
	public void testDeleteAllFilteredAfter() throws Exception {
		when(mockedDao.getEm()).thenReturn(mockedEm);
		Query q = mock(Query.class);
		when(q.setParameter(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(q);
		when(mockedEm.createQuery(ArgumentMatchers.anyString())).thenReturn(q);

		mockedDao.deleteAllFiltered(mock(Date.class), Boolean.FALSE);
		Mockito.verify(mockedEm).createQuery("DELETE FROM AuditLogEntity a WHERE a.logDate >= :limitDay");
	}

	@Test
	public void testFindAllPaginationSortedLogsReturnsCorrectData() {
		setup();
		int pageSize = 30;
		Map<String, Object> filters = new HashMap<>();
		filters.put(DBPromoAuditLogEnum.USERNAME.toString(), "mockito");
		filters.put(DBPromoAuditLogEnum.ACTION.toString(), AuditLogEntity.MENU_CLICK);
		List<AuditLogEntity> list = dao.findAllPaginationFilteredLogs(0, pageSize, null, null, filters);
		// questo e' sbagliato: il numero di record deve essere <= 30, non 30 ... se non ho almeno 30 record non torna
		assertThat(list.size(), OrderingComparison.lessThanOrEqualTo(pageSize));

	}

	@Test
	public void testFindAllPaginationSortedLogsWithoutPageReturnsAllData() throws Exception {
		int pageSize = 30;
		Map<String, String> sort = new HashMap<>();
		sort.put(DBPromoAuditLogEnum.USERNAME.toString(), "ASC");
		sort.put(DBPromoAuditLogEnum.ACTION.toString(), "DESC");

		Map<String, Object> filters = new HashMap<>();
		filters.put(DBPromoAuditLogEnum.USERNAME.toString(), "foo");
		filters.put(DBPromoAuditLogEnum.ACTION.toString(), AuditLogEntity.MENU_CLICK);

		final Date date = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
		final List<AuditLogEntity> entities = createEntities(60, AuditLogEntity.MENU_CLICK, date, "foo path", "foo");
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			for (AuditLogEntity entity : entities) {
				dao.persist(entity);
			}
			em.getTransaction().commit();
		}

		List<AuditLogEntity> list = dao.findAllPaginationSortedLogs(0, pageSize, sort, filters, false);

		assertThat(list.size(), CoreMatchers.equalTo(60));
	}

	@Test
	public void testFindAllPaginationSortedLogsWithPageReturnPage() {
		int pageSize = 30;
		Map<String, String> sort = new HashMap<>();
		sort.put(DBPromoAuditLogEnum.USERNAME.toString(), "DESC");
		sort.put(DBPromoAuditLogEnum.ACTION.toString(), "DESC");
		sort.put(DBPromoAuditLogEnum.LOGDATE.toString(), "ASC");
		sort.put(DBPromoAuditLogEnum.PATH.toString(), "DESC");

		Map<String, Object> filters = new HashMap<>();
		filters.put(DBPromoAuditLogEnum.USERNAME.toString(), "mockito");
		filters.put(DBPromoAuditLogEnum.ACTION.toString(), AuditLogEntity.MENU_CLICK);

		List<AuditLogEntity> list = dao.findAllPaginationSortedLogs(0, pageSize, sort, filters, true);

		assertThat(list.size(), OrderingComparison.lessThanOrEqualTo(pageSize));
	}

	@Test
	public void findAllPaginationSortedLogs_givenFilterByDateAndPath_shouldReturnPage() throws Exception {
		// Arrange
		int pageSize = 30;
		Map<String, String> sort = new HashMap<>();
		sort.put(DBPromoAuditLogEnum.LOGDATE.toString(), "DESC");
		sort.put(DBPromoAuditLogEnum.PATH.toString(), "ASC");
		Map<String, Object> filters = new HashMap<>();
		filters.put(DBPromoAuditLogEnum.LOGDATE.toString(), "2021");
		filters.put(DBPromoAuditLogEnum.PATH.toString(), "baz");
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final Date date2 = new GregorianCalendar(2020, Calendar.MAY, 1).getTime();
		final List<AuditLogEntity> entities = createEntities(10, "foo action", date2, "foo path", "junit");
		entities.addAll(createEntities(5, "bar action", date, "bar path", "junit"));
		entities.addAll(createEntities(5, "baz action", date, "baz", "foo"));
		persistEntities(entities);
		// Act
		List<AuditLogEntity> list = dao.findAllPaginationSortedLogs(0, pageSize, sort, filters, true);
		// Assert
		assertThat(list.size(), CoreMatchers.equalTo(5));
	}

	@Test
	public void findAllPaginationFilteredLogs_givenFilterDate_shouldReturnPage() throws Exception {
		// Arrange
		Map<String, Object> filters = new HashMap<>();
		filters.put(DBPromoAuditLogEnum.LOGDATE.toString(), "2021");
		filters.put(DBPromoAuditLogEnum.PATH.toString(), "baz");
		final Date date = new GregorianCalendar(2021, Calendar.JUNE, 1).getTime();
		final Date date2 = new GregorianCalendar(2020, Calendar.MAY, 1).getTime();
		final List<AuditLogEntity> entities = createEntities(10, "foo action", date2, "foo path", "junit");
		entities.addAll(createEntities(5, "bar action", date, "bar path", "junit"));
		entities.addAll(createEntities(5, "baz action", date, "baz", "foo"));
		persistEntities(entities);
		final Date startDate = new GregorianCalendar(2021, Calendar.MAY, 1).getTime();
		final Date endDate = new GregorianCalendar(2021, Calendar.JUNE, 3).getTime();
		// Act
		final List<AuditLogEntity> list = dao.findAllPaginationFilteredLogs(0, 30, startDate, null, filters);
		assertThat(list.size(), CoreMatchers.equalTo(5));
		final List<AuditLogEntity> list2 = dao.findAllPaginationFilteredLogs(0, 30, startDate, endDate, filters);
		assertThat(list2.size(), CoreMatchers.equalTo(3));
	}

	@Test
	public void setup() {
		try {
			getEm().getTransaction().begin();
			for (int i = 0; i < 100; ++i) {
				dao.persist(createEntry());
			}
			getEm().getTransaction().commit();
		} catch (Exception e) {
			getEm().getTransaction().rollback();
			e.printStackTrace();

		}
	}

	AuditLogEntity mockEntry() {
		AuditLogEntity e = mock(AuditLogEntity.class);
		Mockito.lenient().when(e.getAction()).thenReturn(AuditLogEntity.MENU_CLICK);
		Mockito.lenient().when(e.getPath()).thenReturn("mockito test");
		Mockito.lenient().when(e.getUserName()).thenReturn("mockito");
		Mockito.lenient().when(e.getLogDate()).thenReturn(new Date(System.currentTimeMillis()));
		return e;
	}

	private void persistEntities(List<AuditLogEntity> entities) throws Exception {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			for (AuditLogEntity entity : entities) {
				dao.persist(entity);
			}
			em.getTransaction().commit();
		}
	}

	private List<AuditLogEntity> createEntities(int size, String action, Date date, String path, String username) {
		final List<AuditLogEntity> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			final AuditLogEntity entity = new AuditLogEntity();
			entity.setLogDate(addDays(date, i));
			entity.setAction(action);
			entity.setPath(path);
			entity.setUserName(username);
			list.add(entity);
		}
		return list;
	}

	private Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	static AuditLogEntity createEntry() {
		LocalDateTime ld = LocalDateTime.now();
		AuditLogEntity e = new AuditLogEntity();
		e.setAction(AuditLogEntity.MENU_CLICK);
		e.setLogDate(java.sql.Date.from(ld.atZone(ZoneId.systemDefault()).toInstant()));
		e.setPath("mockito test" + ld.getMinute() + ":" + ld.getSecond());
		e.setUserName("mockito");
		return e;
	}
}

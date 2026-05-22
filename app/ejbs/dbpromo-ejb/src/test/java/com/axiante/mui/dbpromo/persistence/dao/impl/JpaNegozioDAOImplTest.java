package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.NegozioDAO;
import com.axiante.mui.dbpromo.persistence.dto.ZonaDto;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaNegozioDAOImplTest extends AbstractDaoTest {

	@Inject
	private NegozioDAO dao;
	
	private TipoNegozioEntity tipoNegozioEntity = new TipoNegozioEntity();

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaNegozioDAOImpl.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		tipoNegozioEntity.setId((long) 1);
		tipoNegozioEntity.setCodiceTipoNegozio("ng1");
		final NegozioEntity negozio1 = createNegozioEntity(1L, "TipoConsegna1", 100L,
				"c", "N01", "11", "B", "Zona 11", "A");
		final NegozioEntity negozio2 = createNegozioEntity(2L, "TipoConsegna2", 100L,
				"c", "N02", "22", "A", "Zona 22", "B");
		final NegozioEntity negozio3 = createNegozioEntity(3L, "TipoConsegna3", 100L,
				"c", "N03", "22", "A", "Zona 22", "A");
		persist(negozio1, negozio2, negozio3);
	}

	@Test
	public void testFindAllTipoConsegna() {
		final List<String> listTipoConsegna = dao.findAllTipoConsegna();
		assertNotNull(listTipoConsegna);
		assertEquals(3, listTipoConsegna.size());
	}

	@Test
	public void findDistinctZone_shouldReturnOnlyDistinctEntities() {
		final List<ZonaDto> zone = dao.findAllDistinctZone();
		assertEquals(2, zone.size());
		assertEquals("A", zone.get(0).getSocieta());
		assertEquals("22", zone.get(0).getCodiceZona());
		assertEquals("Zona 22", zone.get(0).getDescrizioneZona());
		assertEquals("B", zone.get(1).getSocieta());
		assertEquals("11", zone.get(1).getCodiceZona());
		assertEquals("Zona 11", zone.get(1).getDescrizioneZona());
	}

	@Test
	public void findAllDistinctCedi() {
		final List<String> result = dao.findAllDistinctCedi();
		assertEquals(2, result.size());
		assertTrue(result.contains("A"));
		assertTrue(result.contains("B"));
	}

	private NegozioEntity createNegozioEntity(Long id, String tipoConsegna, Long codiceCluster, String codiceFormato,
											  String codiceNegozio, String codiceZona, String codiceSocieta,
											  String descrizioneZona, String cedi) {
		NegozioEntity entity = new NegozioEntity();
		entity.setId(id);
		entity.setTipoConsegna(tipoConsegna);
		entity.setCodiceCluster(codiceCluster);
		entity.setCodiceFormato(codiceFormato);
		entity.setCodiceNegozio(codiceNegozio);
		entity.setCodiceZona(codiceZona);
		entity.setSocieta(codiceSocieta);
		entity.setDescrizioneZona(descrizioneZona);
		entity.setTipoNegozioEntity(tipoNegozioEntity);
		entity.setCedi(cedi);
		return entity;
	}
}

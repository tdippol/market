package com.axiante.mui.dbpromo.persistence;

import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import java.lang.reflect.Field;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import lombok.NonNull;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

public class MetadataExtractorTest extends AbstractDaoTest {
	@Inject
	@DbPromo
	protected EntityManager em;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, DbPromo.class)
			.activate(RequestScoped.class).inject(this).build();

	@Test
	public void testMetadataExtractor() throws NoSuchFieldException, SecurityException {
		em.getMetamodel().entity(PromozionePianificazioneEntity.class).getAttributes().forEach(a -> {
			if (a.getPersistentAttributeType().equals(PersistentAttributeType.BASIC)) {
				System.out.println("Field " + a.getName() + " maps " + getDeclaringColumn(a.getName()));
			} else {
				// complex type is out of scope
				System.out.println("\t complex type " + a.getName());
			}
		});

	}

	private String getDeclaringColumn(String fieldName) {
		try {
			final Field field = PromozionePianificazioneEntity.class.getDeclaredField(fieldName);
			if (field != null) {
				final Column col = field.getAnnotation(Column.class);
				if (col != null) {
					return col.name();
				}
			}
		} catch (final Exception e) {
			System.err.println("something's wrong ! ");
			e.printStackTrace();
		}
		return camelToSnake(fieldName);
	}

	private static final String camelToSnake(@NonNull final String string) {
		final String ret = string.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");
		return ret.toUpperCase();
	}

}

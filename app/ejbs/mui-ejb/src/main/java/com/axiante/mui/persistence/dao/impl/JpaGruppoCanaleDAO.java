package com.axiante.mui.persistence.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoCanaleDAO;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCanaleEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaGruppoCanaleDAO implements GruppoCanaleDAO {

	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public GruppoCanaleEntity findByCodiceGruppoAndCodiceCanale(@NonNull String gruppo, @NonNull Long canale) {
		Long l = getEm().createNamedQuery("GruppoCanaleEntity.countByCodiceGruppoAndCodiceCanale",Long.class).setParameter("gruppo", gruppo).setParameter("canale", canale).getSingleResult();
		if ( l ==1 ) {
			return getEm().createNamedQuery("GruppoCanaleEntity.findByCodiceGruppoAndCodiceCanale", GruppoCanaleEntity.class).setParameter("gruppo", gruppo).setParameter("canale", canale).getSingleResult();
		} else {
			if (l == 0 ) {
				throw new NoResultException(String.format("Nessun valore trovato per gruppo = %s e canale = %s", gruppo, canale));
			} else {
				throw new NonUniqueResultException (String.format("Trovati %d valori per gruppo = %s e canale = %s",l.intValue(), gruppo, canale));
			}
		}
	}

	@Override
	public GruppoCanaleEntity findByGruppoAndCanale(@NonNull GroupEntity gruppo, @NonNull CanaleEntity canale) {
		Long l = getEm().createNamedQuery("GruppoCanaleEntity.countByGruppoAndCanale",Long.class).setParameter("gruppo", gruppo).setParameter("canale", canale).getSingleResult();
		if ( l ==1 ) {
			return getEm().createNamedQuery("GruppoCanaleEntity.findByGruppoAndCanale", GruppoCanaleEntity.class).setParameter("gruppo", gruppo).setParameter("canale", canale).getSingleResult();
		} else {
			if (l == 0 ) {
				throw new NoResultException(String.format("Nessun valore trovato per gruppo = %s e canale = %s", gruppo.getCodiceGruppo(), canale.getCodiceCanale()));
			} else {
				throw new NonUniqueResultException (String.format("Trovati %d valori per gruppo = %s e canale = %s",l.intValue(), gruppo.getCodiceGruppo(), canale.getCodiceCanale()));
			}
		}
	}

	@Override
	public List<GruppoCanaleEntity> findAllByGruppo(GroupEntity gruppo) {
		return getEm().createNamedQuery("GruppoCanaleEntity.findAllByGruppo", GruppoCanaleEntity.class)
				.setParameter("gruppo", gruppo)
				.getResultList();
	}

	@Override
	public List<CanaleEntity> findCreatableCanaliByGruppo(@NonNull List<GroupEntity> gruppi) {
		return getEm().createNamedQuery("GruppoCanaleEntity.findCreatableCanaliByGruppo", CanaleEntity.class)
				.setParameter("gruppi", gruppi).getResultList();
	}

	@Override
	public List<CanaleEntity> findOwnedCanaliByGruppi(@NonNull List<GroupEntity> gruppi) {
		if (gruppi.isEmpty()) {
			throw new IllegalArgumentException("gruppi cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoCanaleEntity.findOwnedCanaliByGruppi", CanaleEntity.class)
				.setParameter("gruppi", gruppi).getResultList();
	}

	@Override
	public List<Long> findOwnershipCodiciCanaleByGruppi(@NonNull Collection<GroupEntity> gruppi) {
		if (gruppi != null && !gruppi.isEmpty()) {
			return getEm().createNamedQuery("GruppoCanaleEntity.findOwnershipCodiciCanaleByGruppi", Long.class)
					.setParameter("gruppi", gruppi)
					.setParameter("tipoAccesso", PianificazioneSecurityEnum.WRITE)
					.getResultList();
		} else {
			throw new IllegalArgumentException("gruppi cannot be null or empty");
		}
	}

    @Override
    public List<Long> findOwnershipCodiciCanaleByGruppiAndCompratori(@NonNull Collection<String> gruppi,
																	 @NonNull Collection<String> compratori) {
		if (gruppi != null && !gruppi.isEmpty()) {
			if (compratori.isEmpty()) {
				return getEm().createNamedQuery("GruppoCanaleEntity.findOwnershipCodiciCanaleByGruppiAndFlagOwner", Long.class)
						.setParameter("gruppi", gruppi)
						.getResultList();
			}
			return getEm().createNamedQuery("GruppoCanaleEntity.findOwnershipCodiciCanaleByGruppiAndCompratori", Long.class)
					.setParameter("gruppi", gruppi)
					.setParameter("compratori", compratori)
					.setParameter("tipoAccesso", PianificazioneSecurityEnum.WRITE)
					.getResultList();
		} else {
			throw new IllegalArgumentException("gruppi cannot be null or empty");
		}
    }

    @Override
	public GruppoCanaleEntity save(GruppoCanaleEntity entity) {
		try {
			entity = getEm().merge(entity);
			getEm().flush();
		} catch (Exception ex) {
			log.error(String.format("Error saving gruppo canale; codiceGruppo: %s; codiceCanale: %d",
					entity.getGruppo().getCodiceGruppo(), entity.getCanale().getCodiceCanale()), ex);
		}
		return entity;
	}

	@Override
	public boolean toggleFlagCreate(GruppoCanaleEntity entity) {
		entity.setCreate(!entity.getCreate());
		try {
			entity = getEm().merge(entity);
			getEm().flush();
			return true;
		} catch (Exception ex) {
			log.error(String.format("Error saving gruppo canale; codiceGruppo: %s; codiceCanale: %d",
					entity.getGruppo().getCodiceGruppo(), entity.getCanale().getCodiceCanale()), ex);
		}
		return false;
	}
	@Override
	public List<GruppoCanaleEntity> findAll(){
		return getEm().createNamedQuery("GruppoCanaleEntity.findAll", GruppoCanaleEntity.class).getResultList();
	}
}

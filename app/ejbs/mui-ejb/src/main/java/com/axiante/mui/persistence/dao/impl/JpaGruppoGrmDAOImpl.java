package com.axiante.mui.persistence.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoGrmDAO;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoGrmEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaGruppoGrmDAOImpl implements GruppoGrmDAO {

	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;


	@Override
	public List<GruppoGrmEntity> findAll(){
		return getEm().createNamedQuery("GruppoGrmEntity.findAll",GruppoGrmEntity.class).getResultList();
	}

	@Override
	public List<GruppoGrmEntity> findByGroup(@NonNull GroupEntity group){
		return getEm().createNamedQuery("GruppoGrmEntity.findByGroup", GruppoGrmEntity.class).setParameter("gruppo", group).getResultList();
	}

	@Override
	public GruppoGrmEntity findByGroupAndGrm(@NonNull GroupEntity group, @NonNull GrmEntity grm) {
		Long count = getEm().createNamedQuery("GruppoGrmEntity.countByGroupAndGrm", Long.class).setParameter("gruppo", group).setParameter("grm", grm).getSingleResult();
		if ( count == 1) {
			return getEm().createNamedQuery("GruppoGrmEntity.findByGroupAndGrm", GruppoGrmEntity.class).setParameter("gruppo", group).setParameter("grm", grm).getSingleResult();
		} else {
			if ( count == 0 ) {
				log.error(String.format("Nessun valore trovato per gruppo %s e grm %s", group.getCodiceGruppo(), grm.getCodice()));
			} else {
				log.error(String.format("Trovati %d valori trovato per gruppo %s e grm %s", count, group.getCodiceGruppo(), grm.getCodice()));
			}
		}
		return null;
	}

	@Override
	public GruppoGrmEntity save( @NonNull GruppoGrmEntity gruppoGrm) {
		gruppoGrm = getEm().merge(gruppoGrm);
		getEm().flush();
		return gruppoGrm;
	}

    @Override
	@Transactional
    public Collection<GruppoGrmEntity> save(@NonNull Collection<GruppoGrmEntity> entities) {
		entities.stream().filter(Objects::nonNull).forEach(e -> getEm().merge(e));
		getEm().flush();
        return entities;
    }

    @Override
    public GruppoGrmEntity remove(@NonNull GruppoGrmEntity entity) {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
		} catch (Exception ex) {
			log.error(String.format("Error removing entity with gruppo %s and grm %s",
					entity.getGruppo().getCodiceGruppo(), entity.getGrm().getCodice()), ex);
			throw ex;
		}
		return entity;
    }

	@Override
	@Transactional
	public void removeAllByGruppo(GroupEntity group) {
		try {
			findByGroup(group).forEach(e -> {
				e = getEm().merge(e);
				getEm().remove(e);
			});
			getEm().flush();
		} catch (Exception ex) {
			log.error(String.format("Error removing entities with gruppo %s", group.getCodiceGruppo()), ex);
			throw ex;
		}
	}

	@Override
	public Long countByGrmIdAndCodiciGruppo(@NonNull Integer idGrm, @NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoGrmEntity.countByGrmIdAndCodiciGruppo", Long.class)
				.setParameter("idGrm", idGrm)
				.setParameter("codiciGruppo", codiciGruppo)
				.getSingleResult();
	}

	@Override
	public Long countWritableByGrmIdAndCodiciGruppo(@NonNull Integer idGrm, @NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoGrmEntity.countWritableByGrmIdAndCodiciGruppo", Long.class)
				.setParameter("idGrm", idGrm)
				.setParameter("tipoAccesso", PianificazioneSecurityEnum.WRITE)
				.setParameter("codiciGruppo", codiciGruppo)
				.getSingleResult();
	}

    @Override
    public List<String> findAllByCodiciGruppoAndTipoAccesso(@NonNull List<String> codiciGruppo,
															@NonNull PianificazioneSecurityEnum tipoAccesso) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoGrmEntity.findAllByCodiciGruppoAndTipoAccesso", String.class)
				.setParameter("codiciGruppo", codiciGruppo)
				.setParameter("tipoAccesso", tipoAccesso)
				.getResultList();
    }
}

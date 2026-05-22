package com.axiante.mui.persistence.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoRepartoDAO;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoRepartoEntity;
import com.axiante.mui.persistence.entity.RepartoEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class JpaGruppoRepartoDAO implements GruppoRepartoDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public GruppoRepartoEntity findByGruppoAndReparto(GroupEntity gruppo, RepartoEntity reparto) {
		Long count = getEm().createNamedQuery("GruppoRepartoEntity.countByGruppoAndReparto", Long.class)
				.setParameter("gruppo", gruppo).setParameter("reparto", reparto).getSingleResult();
		if ( count == 1 ) {
			return getEm().createNamedQuery("GruppoRepartoEntity.findByGruppoAndReparto", GruppoRepartoEntity.class)
					.setParameter("gruppo", gruppo).setParameter("reparto", reparto).getSingleResult();
		} else {
			if (count ==  0) {
				throw new NoResultException(String.format("Nessun gruppo/reparto trovato per la coppia gruppo = %s e reparto = %s",
						gruppo.getCodiceGruppo(), reparto.getCodiceReparto()));
			} else {
				throw new NonUniqueResultException(String.format("%d gruppo/reparto trovati per la coppia gruppo = %s e reparto = %s",
						count, gruppo.getCodiceGruppo(), reparto.getCodiceReparto()));
			}
		}
	}

	@Override
	public GruppoRepartoEntity save(GruppoRepartoEntity entity) {
		entity = getEm().merge(entity);
		getEm().persist(entity);
		getEm().flush();
		return entity;
	}

    @Override
	@Transactional
    public Collection<GruppoRepartoEntity> save(@NonNull Collection<GruppoRepartoEntity> entities) {
		entities.stream().filter(Objects::nonNull).forEach(e -> getEm().merge(e));
		getEm().flush();
		return entities;
    }

    @Override
	public List<GruppoRepartoEntity> findAllByGruppo(GroupEntity gruppo) {
		return getEm().createNamedQuery("GruppoRepartoEntity.findByGruppo", GruppoRepartoEntity.class).setParameter("gruppo", gruppo).getResultList();
	}

	@Override
	public GruppoRepartoEntity remove(@NonNull GruppoRepartoEntity entity) {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
		} catch (Exception ex) {
			log.error(String.format("Error removing entity with gruppo %s and reparto %s",
					entity.getGruppo().getCodiceGruppo(), entity.getReparto().getCodiceReparto()));
			throw ex;
		}
		return entity;
	}

    @Override
	@Transactional
    public void removeAllByGruppo(GroupEntity group) {
		try {
			findAllByGruppo(group).forEach(gr -> {
				gr = getEm().merge(gr);
				getEm().remove(gr);
			});
			getEm().flush();
		} catch (Exception ex) {
        	log.error(String.format("Error removing entities with gruppo %s", group.getCodiceGruppo()));
        	throw ex;
		}
    }

	@Override
	public Long countByRepartoIdAndCodiciGruppo(@NonNull Integer idReparto, @NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoRepartoEntity.countByRepartoIdAndCodiciGruppo", Long.class)
				.setParameter("idReparto", idReparto)
				.setParameter("codiciGruppo", codiciGruppo)
				.getSingleResult();
	}

    @Override
    public Long countWriteableByRepartoIdAndCodiciGruppo(@NonNull Integer idReparto, @NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
        return getEm().createNamedQuery("GruppoRepartoEntity.countWriteableByRepartoIdAndCodiciGruppo", Long.class)
				.setParameter("idReparto", idReparto)
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
        return getEm().createNamedQuery("GruppoRepartoEntity.findAllByCodiciGruppoAndTipoAccesso", String.class)
				.setParameter("codiciGruppo", codiciGruppo)
				.setParameter("tipoAccesso", tipoAccesso)
				.getResultList();
    }
}

package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GruppoCompratoreDAO;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Stateless
@Slf4j
public class JpaGruppoCompratoreDAOImpl implements GruppoCompratoreDAO {

	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public List<GruppoCompratoreEntity> findAll() {
		return getEm().createNamedQuery("GruppoCompratoreEntity.findAll",GruppoCompratoreEntity.class)
				.getResultList();
	}

	@Override
	public List<GruppoCompratoreEntity> findAllByGroup(@NonNull GroupEntity group) {
		return getEm().createNamedQuery("GruppoCompratoreEntity.findAllByGruppo", GruppoCompratoreEntity.class)
				.setParameter("gruppo", group)
				.getResultList();
	}

	@Override
	public GruppoCompratoreEntity findByGroupAndCompratore(GroupEntity group, CompratoreEntity compratore) {
		Long count = getEm().createNamedQuery("GruppoCompratoreEntity.countByGruppoAndCompratore",Long.class)
				.setParameter("compratore", compratore).setParameter("gruppo", group).getSingleResult();
		if ( count == 1) {
			return getEm().createNamedQuery("GruppoCompratoreEntity.findByGruppoAndCompratore",GruppoCompratoreEntity.class)
					.setParameter("compratore", compratore).setParameter("gruppo", group).getSingleResult();
		}else {
			if (count == 0 ) {
				log.error(String.format("nessun valore ritornato per gruppo %s e compratore %s",
						group.getCodiceGruppo(), compratore.getDescrizione()));
			} else {
				log.error(String.format("%d valori ritornato per gruppo %s e compratore %s", count,
						group.getCodiceGruppo(), compratore.getDescrizione()));
			}
		}
		return null;
	}

	@Override
	public GruppoCompratoreEntity save(GruppoCompratoreEntity gruppoCompratore) {
		gruppoCompratore = getEm().merge(gruppoCompratore);
		getEm().flush();
		return gruppoCompratore;
	}

	@Override
	@Transactional
	public Collection<GruppoCompratoreEntity> save(@NonNull Collection<GruppoCompratoreEntity> entities) {
		entities.stream().filter(Objects::nonNull).forEach(e -> getEm().merge(e));
		getEm().flush();
		return entities;
	}

	@Override
	public GruppoCompratoreEntity remove(@NonNull GruppoCompratoreEntity entity) {
		try {
			entity = getEm().merge(entity);
			getEm().remove(entity);
			getEm().flush();
		} catch (Exception ex) {
			log.error(String.format("Error removing entity with gruppo %s and compratore %s",
					entity.getGruppo().getCodiceGruppo(), entity.getCompratore().getCodiceCompratore()), ex);
			throw ex;
		}
		return entity;
	}

	@Override
	@Transactional
	public void removeAllByGruppo(GroupEntity group) {
		try {
			findAllByGroup(group).forEach(gr -> {
				gr = getEm().merge(gr);
				getEm().remove(gr);
			});
			getEm().flush();
		} catch (Exception ex) {
			log.error(String.format("Error removing entities with gruppo %s", group.getCodiceGruppo()), ex);
			throw ex;
		}
	}

	@Override
	public List<String> findAllCodiciCompratoreByGroupAndTipoAccesso(List<GroupEntity> groups,
																	 @NonNull PianificazioneSecurityEnum security) {
		if (groups == null || groups.isEmpty()) {
			throw new IllegalArgumentException("groups cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoCompratoreEntity.findAllCodiciCompratoreByGroupAndTipoAccesso", String.class)
				.setParameter("gruppo", groups)
				.setParameter("tipoAccesso", security)
				.getResultList();
	}

	@Override
	public List<String> findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(List<String> groups,
																			@NonNull PianificazioneSecurityEnum security) {
		if (groups == null || groups.isEmpty()) {
			throw new IllegalArgumentException("groups cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoCompratoreEntity.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso", String.class)
				.setParameter("gruppo", groups)
				.setParameter("tipoAccesso", security)
				.getResultList();
	}

    @Override
    public List<String> findAllCodiciCompratoreByCodiciGruppo(List<String> codiciGruppo) {
		if (codiciGruppo == null || codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GruppoCompratoreEntity.findAllCodiciCompratoreByCodiciGruppo", String.class)
				.setParameter("codiciGruppo", codiciGruppo)
				.getResultList();
    }
}

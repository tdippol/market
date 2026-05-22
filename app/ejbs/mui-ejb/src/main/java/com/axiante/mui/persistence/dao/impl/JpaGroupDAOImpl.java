package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.dao.GroupDAO;
import com.axiante.mui.persistence.dto.GruppoUtenteDto;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
@Slf4j
public class JpaGroupDAOImpl implements GroupDAO {
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

	@Override
	public GroupEntity persist(GroupEntity group) {
		try {
			group = getEm().merge(group);
			getEm().flush();
		} catch (Exception e) {
			log.error("Error saving group " + group.getDescrizione(), e);
		}
		return group;
	}
	@Override
	public List<GroupEntity> persist(List<GroupEntity> groups) {
		if ( groups != null ) {
			try {
				for ( GroupEntity g : groups ) {
					if ( g != null ) {
						g = getEm().merge(g);
					}
				}
				getEm().flush();
			} catch (Exception e) {
				log.error(String.format("Error saving groups [%s] ", groups.stream().filter(Objects::nonNull).map(GroupEntity::getDescrizione).collect(Collectors.joining(","))), e);
			}
		}
		return groups;
	}

	@Override
	public List<GroupEntity> findAll() {
		return getEm().createNamedQuery("GroupEntity.findAll", GroupEntity.class).getResultList();
	}

	@Override
	public GroupEntity remove(GroupEntity group) {
		try {
			group = getEm().merge(group);
			getEm().remove(group);
		} catch (Exception e) {
			log.error("Error removing group " + group.getDescrizione(), e);
			throw (e);
		}
		return group;
	}

	@Override
	public GroupEntity findById(Integer id) {
		return getEm().find(GroupEntity.class, id);
	}

	@Override
	public List<GroupEntity> findAllByUser(UsersEntity user){
		return getEm().createNamedQuery("GroupEntity.findAllByUser", GroupEntity.class).setParameter("user", user).getResultList();
	}

    @Override
    public List<GruppoUtenteDto> findAllWithUsers() {
        return getEm().createNamedQuery("GroupEntity.findAllWithUsers", GruppoUtenteDto.class).getResultList();
    }

	@Override
	public Long countAccessTotaleByCodiciGruppoAndTipoNotNull(@NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GroupEntity.countAccessTotaleByCodiciGruppoAndTipoNotNull", Long.class)
				.setParameter("codiciGruppo", codiciGruppo)
				.getSingleResult();
	}

	@Override
	public Long countWriteableAccessTotaleByCodiciGruppo(@NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GroupEntity.countWriteableAccessTotaleByCodiciGruppo", Long.class)
				.setParameter("codiciGruppo", codiciGruppo)
				.setParameter("accessoTotale", PianificazioneSecurityEnum.WRITE)
				.getSingleResult();
	}

	@Override
	public Long countAccessAttributoByCodiciGruppoAndTipoNotNull(@NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GroupEntity.countAccessAttributoByCodiciGruppoAndTipoNotNull", Long.class)
				.setParameter("codiciGruppo", codiciGruppo)
				.getSingleResult();
	}

	@Override
	public Long countWriteableAccessAttributoByCodiciGruppo(@NonNull List<String> codiciGruppo) {
		if (codiciGruppo.isEmpty()) {
			throw new IllegalArgumentException("codiciGruppo cannot be null or empty");
		}
		return getEm().createNamedQuery("GroupEntity.countWriteableAccessAttributoByCodiciGruppo", Long.class)
				.setParameter("codiciGruppo", codiciGruppo)
				.setParameter("accessoAttributo", PianificazioneSecurityEnum.WRITE)
				.getSingleResult();
	}
}

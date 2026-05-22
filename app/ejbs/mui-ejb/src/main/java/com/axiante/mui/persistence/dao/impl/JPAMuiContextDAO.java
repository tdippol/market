package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.dao.MuiContextDAO;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.MuiContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
@Slf4j
public class JPAMuiContextDAO implements MuiContextDAO{
	@Inject
	@Getter(value = AccessLevel.PROTECTED)
	private EntityManager em;

    @Override
    public List<MuiContext> findAll() {
        return getEm().createNamedQuery("MuiContext.findAll", MuiContext.class).getResultList();
    }

    @Override
	public MuiContext findById(@NonNull Long id) {
		return getEm().createNamedQuery("MuiContext.findById", MuiContext.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public MuiContext findByCode(@NonNull String code) {
		Long count = getEm().createNamedQuery("MuiContext.countByCode", Long.class).setParameter("code", code).getSingleResult();
		if ( (count != null) && (count == 1) ) {
			return getEm().createNamedQuery("MuiContext.findByCode", MuiContext.class).setParameter("code", code).getSingleResult();
		} else {
			if ( (count == null) || (count == 0) ) {
				log.error(String.format("No results found for code %s", code));
			} if ( count > 1 ) {
				log.error(String.format("Found %d results for code %s", count, code));
			}
		}
		return null;
	}

	@Override
	public MuiContext persist(@NonNull MuiContext muiContext) throws Exception{
		try {
			muiContext = getEm().merge(muiContext);
			getEm().flush();
		} catch (EntityExistsException e) {
			throw new Exception("MuiContext gia' presente", e);
		}
		return muiContext;

	}

	@Override
	public List<GroupEntity> findGroupsByContextCode(@NonNull String code) {
		return getEm().createNamedQuery("MuiContext.findGroupsByContextCode", GroupEntity.class)
				.setParameter("code", code)
				.getResultList();
	}
}

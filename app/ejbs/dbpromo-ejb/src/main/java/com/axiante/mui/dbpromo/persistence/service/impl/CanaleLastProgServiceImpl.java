package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CanaleLastProgEntityDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanaleLastProgService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class CanaleLastProgServiceImpl extends AbstractDbPromoService<CanaleLastProgEntity>
implements CanaleLastProgService {
	private static final long serialVersionUID = 2957839778319316404L;
	@Inject
	@Getter
	CanaleLastProgEntityDAO dao;

	@Override
	public List<CanaleLastProgEntity> findByCanale(@NonNull CanalePromozioneEntity canale) {
		return dao.findByChannel(canale);
	}

}

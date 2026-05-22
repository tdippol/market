package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import java.util.List;

public interface MuiFontStileService {

	List<MuiFontStileEntity> findAll();

	MuiFontStileEntity findById(String id);
}

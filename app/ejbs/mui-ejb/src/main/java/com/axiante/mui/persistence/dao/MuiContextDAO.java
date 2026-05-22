package com.axiante.mui.persistence.dao;

import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.MuiContext;

import java.util.List;

public interface MuiContextDAO {

	List<MuiContext> findAll();
	MuiContext findById(Long id);
	MuiContext findByCode(String code);
	MuiContext persist(MuiContext muiContext) throws Exception;
	List<GroupEntity> findGroupsByContextCode(String code);
}

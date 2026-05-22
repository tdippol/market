package com.axiante.mui.webapp.business;

import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.data.UserDTO;
import java.io.Serializable;

public interface UserService extends Serializable {
	UserDTO asDto(UsersEntity user);
	UserDTO asDto(UsersEntity user, String context);
}

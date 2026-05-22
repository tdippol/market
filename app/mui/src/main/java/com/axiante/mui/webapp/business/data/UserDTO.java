package com.axiante.mui.webapp.business.data;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	UsersEntity user;
	List<CanalePromozioneEntity> canali;
	List<Long> canaliCreatePromo;
	List<Long> canaliOwner;
	List<String> gruppi;

	public boolean isAdmin() {
		return user.isAdmin();
	}

	public String getUsermame() {
		return user.getName();
	}
}

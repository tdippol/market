package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.persistence.entity.UsersEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class RuoliUtentiBackingBean {

    @Getter
    @Setter
    private List<UsersEntity> utenti;

    @Getter
    @Setter
    private UsersEntity selectedUser;

    @Getter
    @Setter
    private List<UsersEntity> availableUsers;

    @Getter
    @Setter
    private UsersEntity[] selectedUsers;
}

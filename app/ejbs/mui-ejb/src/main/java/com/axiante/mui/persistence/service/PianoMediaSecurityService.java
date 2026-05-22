package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.entity.UsersEntity;

import java.io.Serializable;
import java.util.List;

public interface PianoMediaSecurityService extends Serializable {
    List<PianoMediaSecurityEntity> readAll();
    PianoMediaSecurityEntity persist(PianoMediaSecurityEntity entity) throws Exception;
    void remove(PianoMediaSecurityEntity entity) throws Exception;
    PianoMediaSecurityEntity findById(Integer id);
    List<PianoMediaSecurityEntity> findByUser(UsersEntity user) throws Exception;
}

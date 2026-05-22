package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;

import java.io.Serializable;
import java.util.List;

public interface RepartoService extends Serializable {
    boolean hasAssociationWithIdRepartoAndCodiciGruppo(Integer idReparto, List<String> codiciGruppo,
                                                       PianificazioneSecurityEnum security);
    List<String> findAllWritableCodiciRepartoByCodiciGruppo(List<String> codiciGruppo);
}

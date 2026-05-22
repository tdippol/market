package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;

import java.io.Serializable;
import java.util.List;

public interface GrmService extends Serializable {
    boolean hasAssociationWithIdGrmAndCodiciGruppo(Integer idGrm, List<String> codiciGruppo,
                                                   PianificazioneSecurityEnum security);
    List<String> findAllWritableCodiciGrmByCodiciGruppo(List<String> codiciGruppo);
}

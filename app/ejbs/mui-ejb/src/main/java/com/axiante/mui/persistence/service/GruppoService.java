package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;

import java.io.Serializable;
import java.util.List;

public interface GruppoService extends Serializable {
    boolean hasAccessTotaleByCodiciGruppo(List<String> codiciGruppo, PianificazioneSecurityEnum security);

    boolean hasAccessAttributoByCodiciGruppo(List<String> codiciGruppo, PianificazioneSecurityEnum security);
}

package com.axiante.mui.persistence.service;

import com.axiante.mui.common.PianificazioneSecurityEnum;

import java.io.Serializable;
import java.util.List;

public interface ArticoloService extends Serializable {
    boolean hasAssociationWithIdArticoloAndCodiciGruppo(Long idArticolo, List<String> codiciGruppo,
                                                        PianificazioneSecurityEnum security);
    List<String> findCompratoriByIdArticoliAndCodiciGruppo(List<Long>idArticoli, List<String> codiciGruppo,
                                                           PianificazioneSecurityEnum security);
}

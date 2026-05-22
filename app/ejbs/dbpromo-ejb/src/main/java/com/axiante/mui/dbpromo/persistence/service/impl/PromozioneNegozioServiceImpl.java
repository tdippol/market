package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromozioneNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneNegozioService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Slf4j
@Dependent
@Transactional
public class PromozioneNegozioServiceImpl extends AbstractDbPromoService<PromozioneNegozioEntity>
        implements PromozioneNegozioService {
    private static final long serialVersionUID = -2742829640887178254L;

    @Inject
    @Getter
    PromozioneNegozioDAO dao;

    @Override
    public boolean importaDaPromo(@NonNull Long idPromozioneCorrente, String codicePromozioneSorgente,
                                  String codiceCategoriaPlano,
                                  String tipologiaPlano,
                                  String dimensionePlano,
                                  @NonNull String username) {
        try {
            getDao().impostaNegozi(idPromozioneCorrente, codicePromozioneSorgente,
                    codiceCategoriaPlano,
                    tipologiaPlano,
                    dimensionePlano,
                    username);
            return true;
        } catch (Exception e) {
            log.error("Errore durante il lancio della stored procedure P_MUI_IMPOSTA_NEGOZI", e);
        }
        return false;
    }

    @Override
    public boolean eliminaNegozi(@NonNull Long idPromozione, String codiceCategoriaPlano, String tipologiaPlano,
                                 String dimensionePlano) {
        try {
            getDao().eliminaNegozi(idPromozione, codiceCategoriaPlano, tipologiaPlano, dimensionePlano);
            return true;
        } catch (Exception ex) {
            log.error("Errore durante il lancio della stored procedure P_MUI_ELIMINA_NEGOZI", ex);
        }
        return false;
    }
}

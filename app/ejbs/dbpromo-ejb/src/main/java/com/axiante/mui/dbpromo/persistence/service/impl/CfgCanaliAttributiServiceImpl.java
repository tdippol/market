package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgCanaliAttributiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaliAttributiEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaliAttributiService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Dependent
@Transactional
public class CfgCanaliAttributiServiceImpl extends AbstractDbPromoService<CfgCanaliAttributiEntity>
        implements CfgCanaliAttributiService {
    private static final long serialVersionUID = 1905618483846866886L;

    @Inject
    @Getter
    CfgCanaliAttributiDAO dao;

    @Override
    public String getListaByCanaleAndAttributo(@NonNull Long idCanale, @NonNull Long idAttributo) {
        try {
            return getDao().getListaByCanaleAndAttributo(idCanale, idAttributo);
        } catch (Exception e) {
            log.error(String.format("Errore durante il recupero della lista di valori per l'attributo con id %d sul canale con id %d",
                    idAttributo, idCanale));
            return null;
        }
    }

    @Override
    public List<CfgCanaliAttributiEntity> getAllByCanale(@NonNull Long idCanale) {
        return getDao().getAllByCanale(idCanale);
    }
}

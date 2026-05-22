package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.dao.TipoElementoDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.service.TipoElementoService;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Getter
@Dependent
@Transactional
public class TipoElementoServiceImpl extends AbstractDbPromoService<CfgTipoElementoEntity>
        implements TipoElementoService {
    private static final long serialVersionUID = 4460925940519718555L;

    @Inject
    private TipoElementoDAO dao;

    @Override
    public List<String> findTipoElemento(Long meccanicaId, Long setPianificazioneId) {
        final CfgTipoElementoEntity entity = dao
                .findByMeccanicaIdAndSetPianificazioneId(meccanicaId, setPianificazioneId);
        List<String> list = new ArrayList<>();
        if (entity != null) {
            if (entity.getArticolo() == 1) {
                list.add(ElementType.ARTICOLO.name());
            }
            if (entity.getAttributo() == 1) {
                list.add(ElementType.ATTRIBUTO.name());
            }
            if (entity.getGrm() == 1) {
                list.add(ElementType.GRM.name());
            }
            if (entity.getReparto() == 1) {
                list.add(ElementType.REPARTO.name());
            }
            if (entity.getTotale() == 1) {
                list.add(ElementType.TOTALE.name());
            }
        }
        return list;
    }
}

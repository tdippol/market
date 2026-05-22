package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianificazioneAnagraficaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import java.util.List;

@DbPromoJpaDao
public class JpaPianificazioneAnagraficaPianoMediaDAOImpl extends JpaDbPromoDAO<PianificazioneAnagraficaPianoMediaEntity>
        implements PianificazioneAnagraficaPianoMediaDAO {
    private static final long serialVersionUID = 8429197938510291157L;

    @Override
    public PianificazioneAnagraficaPianoMediaEntity findByPianificazioneDettaglioAndPianificazioneMedia(PianoMediaPianificazioneDettaglioEntity pianificazioneDettaglio, PianificazionePianoMediaEntity pianificazioneMedia) {
        List<PianificazioneAnagraficaPianoMediaEntity> list = getEm().createNamedQuery("PianificazioneAnagraficaPianoMediaEntity.findByPianificazioneDettaglioAndPianificazioneMedia", PianificazioneAnagraficaPianoMediaEntity.class)
                .setParameter("pianificazioneDettaglio", pianificazioneDettaglio)
                .setParameter("pianificazioneMedia", pianificazioneMedia)
                .getResultList();
        if (list.size() > 1) {
            throw new IllegalStateException("PianificazioneAnagraficaPianoMediaEntity.findByPianificazioneDettaglioAndPianificazioneMedia: more than one result");
        }
        if (list.isEmpty()) {
            throw new IllegalStateException("PianificazioneAnagraficaPianoMediaEntity.findByPianificazioneDettaglioAndPianificazioneMedia: no result");
        }
        return list.get(0);
    }
}

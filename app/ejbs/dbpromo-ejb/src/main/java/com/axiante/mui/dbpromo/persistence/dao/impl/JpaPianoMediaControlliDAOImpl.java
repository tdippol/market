package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianoMediaControlliDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import java.util.List;
import lombok.NonNull;

public class JpaPianoMediaControlliDAOImpl extends JpaDbPromoDAO<PianoMediaControlliEntity>
        implements PianoMediaControlliDAO {
    private static final long serialVersionUID = 3375539200148655863L;

    @Override
    public List<PianoMediaControlliEntity> findByPianificazioneMedia(@NonNull PianificazionePianoMediaEntity pianificazioneMedia) {
        return getEm()
                .createNamedQuery("PianoMediaControlliEntity.findByPianificazioneMedia", PianoMediaControlliEntity.class)
                .setParameter("pianificazioneMedia", pianificazioneMedia)
                .getResultList();
    }

    @Override
    public List<PianoMediaControlliEntity> findByIdPianificazioniMedia(@NonNull List<Long> idPianificazioniMedia) {
        if (idPianificazioniMedia.isEmpty()) {
            throw new IllegalArgumentException("idPianificazioniMedia cannot be null or empty");
        }
        return getEm()
                .createNamedQuery("PianoMediaControlliEntity.findByIdPianificazioniMedia", PianoMediaControlliEntity.class)
                .setParameter("idPianificazioniMedia", idPianificazioniMedia)
                .getResultList();
    }

    @Override
    public List<PianoMediaControlliEntity> findByPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        return getEm().createNamedQuery("PianoMediaControlliEntity.findByPianoMedia", PianoMediaControlliEntity.class)
                .setParameter("pianoMedia", pianoMedia)
                .getResultList();
    }


    @Override
    public List<PianoMediaControlliEntity> findByCodiceSupportoConfigurato(String codiceControllo){
        return getEm().createNamedQuery("PianoMediaControlliEntity.findByCodiceSupportoConfigurato", PianoMediaControlliEntity.class)
                .setParameter("codiceControllo", codiceControllo)
                .getResultList();
    }
    @Override
    public long countByCodiceSupportoConfigurato(String codiceControllo) {
        return getEm().createNamedQuery("PianoMediaControlliEntity.countByCodiceSupportoConfigurato", Long.class)
                .setParameter("codiceControllo", codiceControllo)
                .getSingleResult();
    }

}

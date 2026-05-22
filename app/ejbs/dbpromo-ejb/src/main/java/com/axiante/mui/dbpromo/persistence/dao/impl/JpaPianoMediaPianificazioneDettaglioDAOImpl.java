package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaPianificazioneDettaglioDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaPianoMediaPianificazioneDettaglioDAOImpl extends JpaDbPromoDAO<PianoMediaPianificazioneDettaglioEntity>
        implements PianoMediaPianificazioneDettaglioDAO {
    private static final long serialVersionUID = 8931811095710615272L;

    @Override
    public List<PianoMediaPianificazioneDettaglioEntity> findByPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        return getEm().createNamedQuery("PianoMediaPianificazioneDettaglioEntity.findByPianoMedia", PianoMediaPianificazioneDettaglioEntity.class)
                .setParameter("pianoMedia", pianoMedia)
                .getResultList();
    }
}

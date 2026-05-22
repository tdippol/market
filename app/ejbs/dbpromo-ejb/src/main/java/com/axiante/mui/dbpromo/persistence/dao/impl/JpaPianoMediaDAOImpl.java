package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import java.util.Date;
import java.util.List;
import lombok.NonNull;

@DbPromoJpaDao
public class JpaPianoMediaDAOImpl extends JpaDbPromoDAO<PianoMediaEntity> implements PianoMediaDAO {
    private static final long serialVersionUID = -1783206187918381227L;

    @Override
    public List<PianoMediaEntity> findByDataInizioAndDataFine(@NonNull Date dataInizio, @NonNull Date dataFine) {
        return getEm().createNamedQuery("PianoMediaEntity.findByDataInizioAndDataFine", PianoMediaEntity.class)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public List<PianoMediaEntity> findNonPubblicatiByDataInizioAndDataFine(@NonNull Date dataInizio, @NonNull Date dataFine) {
        return getEm().createNamedQuery("PianoMediaEntity.findNonPubblicatiByDataInizioAndDataFine", PianoMediaEntity.class)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public List<PianoMediaEntity> findOpenPianiMedia() {
        return getEm().createNamedQuery("PianoMediaEntity.findOpenPianiMedia", PianoMediaEntity.class)
                .getResultList();
    }
    @Override
    public List<PianoMediaEntity> findNonPubblicatiPianiMedia() {
        return getEm().createNamedQuery("PianoMediaEntity.findNonPubblicatiPianiMedia", PianoMediaEntity.class)
                .getResultList();
    }

    @Override
    public List<PianoMediaEntity> findNotCancelled() {
        return getEm().createNamedQuery("PianoMediaEntity.findNotCancelled", PianoMediaEntity.class)
                .getResultList();
    }

    @Override
    public List<Integer> findOpenAvailableYears(){
        return getEm().createNamedQuery("PianoMediaEntity.findOpenAvailableYears", Integer.class)
                .getResultList();
    }

    @Override
    public List<PianoMediaEntity> findPubblicatiByDataInizio(@NonNull Date dataInizio){
            return getEm().createNamedQuery("PianoMediaEntity.findPubblicatiByDataInizio", PianoMediaEntity.class)
                .setParameter("dataInizio", dataInizio)
                .getResultList(); }

    @Override
    public List<PianoMediaEntity> findOpenByDataFine(@NonNull Date dataFine){
            return getEm().createNamedQuery("PianoMediaEntity.findOpenByDataFine", PianoMediaEntity.class)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    @Override
    public List<PianoMediaEntity> findPianiMediaAccessibiliInPianificazione(){
        return getEm().createNamedQuery("PianoMediaEntity.findPianiMediaAccessibiliInPianificazione", PianoMediaEntity.class)
                .getResultList();
    }
}

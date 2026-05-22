package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.PianificazionePianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.NonNull;

public class JpaPianificazionePianoMediaDAOImpl extends JpaDbPromoDAO<PianificazionePianoMediaEntity>
        implements PianificazionePianoMediaDAO {
    private static final long serialVersionUID = 7825771643003694567L;

    @Override
    public void remove(@NonNull Set<PianificazionePianoMediaEntity> set, int batchSize) {
        List<PianificazionePianoMediaEntity> entities = new ArrayList<>(set);
        int pendingFlush = 0;
        for (int i = 0; i < entities.size(); ++i) {
            if ((batchSize > 0) && ((i % batchSize) == 0)) {
                getEm().flush();
                getEm().clear();
                pendingFlush = 0;
            }
            if (entities.get(i) != null) {
                entities.set(i, getEm().merge(entities.get(i)));
                getEm().remove(entities.get(i));
                ++pendingFlush;
            }
        }
        if (pendingFlush > 0) {
            getEm().flush();
        }
    }

    @Override
    public List<PianificazionePianoMediaEntity> findByPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        return getEm().createNamedQuery("PianificazionePianoMediaEntity.findByPianoMedia",
                        PianificazionePianoMediaEntity.class)
                .setParameter("pianoMedia", pianoMedia)
                .getResultList();
    }

    @Override
    public List<PianificazionePianoMediaEntity> findAttiviByPianoMedia(PianoMediaEntity pianoMedia){
        return getEm().createNamedQuery("PianificazionePianoMediaEntity.findAttiviByPianoMedia",
                        PianificazionePianoMediaEntity.class)
                .setParameter("pianoMedia", pianoMedia)
                .getResultList();
    }
}

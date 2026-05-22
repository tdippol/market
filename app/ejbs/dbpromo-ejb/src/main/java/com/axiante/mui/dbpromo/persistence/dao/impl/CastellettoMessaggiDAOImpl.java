package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CastellettoMessaggiDAOImpl extends JpaDbPromoDAO<CastellettoMessaggiEntity>
        implements CastellettoMessaggiDAO {
    private static final long serialVersionUID = 63716647756385167L;

    @Override
    public List<CastellettoMessaggiEntity> findByIdPianificazione(Long idPianificazione) {
        return getEm()
                .createNamedQuery(
                        "CastellettoMessaggiEntity.findByIdPianificazione", CastellettoMessaggiEntity.class)
                .setParameter("idPianificazione", idPianificazione)
                .getResultList();
    }

    @Override
    public List<CastellettoMessaggiEntity> findMessaggiByIdPianificazione(Long idPianificazione) {
        return getEm()
                .createNamedQuery(
                        "CastellettoMessaggiEntity.findMessaggiByIdPianificazione",
                        CastellettoMessaggiEntity.class)
                .setParameter("idPianificazione", idPianificazione)
                .getResultList();
    }

    @Override
    public List<CastellettoMessaggiEntity> findMessaggiByIdPianificazioneAndCodiceDispositivo(
            Long idPianificazione, String codiceDispositivo) {
        return getEm()
                .createNamedQuery(
                        "CastellettoMessaggiEntity.findMessaggiByIdPianificazioneAndCodiceDispositivo",
                        CastellettoMessaggiEntity.class)
                .setParameter("idPianificazione", idPianificazione)
                .setParameter("codiceCanaleDispositivo", codiceDispositivo)
                .getResultList();
    }

    @Override
    public List<CastellettoMessaggiEntity> findCastellettiByIdPianificazione(Long idPianificazione) {
        return getEm()
                .createNamedQuery(
                        "CastellettoMessaggiEntity.findCastellettiByIdPianificazione",
                        CastellettoMessaggiEntity.class)
                .setParameter("idPianificazione", idPianificazione)
                .getResultList();
    }

    @Override
    public void remove(List<Long> idCastellettoMessaggiList) {
        if (idCastellettoMessaggiList == null || idCastellettoMessaggiList.isEmpty()) {
            return;
        }
        getEm()
                .createNamedQuery("CastellettoMessaggiEntity.removeByIds")
                .setParameter("idCastelletti", idCastellettoMessaggiList)
                .executeUpdate();
    }

    @Override
    public List<CastellettoMessaggiEntity> findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan(Long idPianificazione, String codiceDispositivo, Integer seqStampa) {
        return getEm()
                .createNamedQuery(
                        "CastellettoMessaggiEntity.findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan",
                        CastellettoMessaggiEntity.class)
                .setParameter("idPianificazione", idPianificazione)
                .setParameter("codiceCanaleDispositivo", codiceDispositivo)
                .setParameter("seqStampa", seqStampa)
                .getResultList();
    }

    @Override
    public String getHtmlFromDb(Long idPianificazione, String codiceDispositivo, String imgPath) {
        String ret = "";
        try {
            java.sql.Connection conn = getEm().unwrap(java.sql.Connection.class);
            try (java.sql.CallableStatement cs =
                         conn.prepareCall("{ CALL PKG_PREVIEW_MESSAGGI.GENERA_REPORT(?, ?, ?, ?) }")) {
                cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                if (idPianificazione != null) {
                    cs.setObject(1, idPianificazione); // numeric
                } else {
                    cs.setNull(1, java.sql.Types.NUMERIC);
                }
                cs.setString(2, codiceDispositivo);
                if (imgPath != null) {
                    cs.setString(3, imgPath);
                } else {
                    cs.setNull(3, java.sql.Types.VARCHAR);
                }
                cs.setNull(4, java.sql.Types.VARCHAR); // server per resettare...
                cs.execute();
                String out = cs.getString(4);
                ret = out == null ? "" : out;
            }
        } catch (Exception e) {
            log.error(String.format("Retrieved HTML message from DB for pianificazione %s and dispositivo %s",
                    idPianificazione, codiceDispositivo), e);
            ret = "";
        }
        return ret;
    }

}

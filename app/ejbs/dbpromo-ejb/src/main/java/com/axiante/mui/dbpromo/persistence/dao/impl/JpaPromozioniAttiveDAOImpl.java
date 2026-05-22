package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.PromozioniAttiveDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttiva;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaPromozioniAttiveDAOImpl implements Serializable, PromozioniAttiveDAO {
    private static final long serialVersionUID = -1638415966862489572L;

    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<PromozioneAttiva> findAll() {
        return getEm().createNamedQuery("PromozioneAttiva.findAll", PromozioneAttiva.class)
                .getResultList();
    }

    @Override
    public boolean runDeletePromozione(String codicePromozione, String codiceUtente) {
        boolean result = true;
        try {
            Connection conn = getEm().unwrap(Connection.class);
            CallableStatement cs = conn.prepareCall("{ CALL PKG_UTILITY.P_CANCELLA_PROMO(?, ?)}");
            cs.setString(1, codicePromozione);
            cs.setString(2, codiceUtente);
            cs.execute();
        } catch (Exception ex) {
            log.error("Error calling procedure P_CANCELLA_PROMO", ex);
            result = false;
        }
        return result;
    }
}

package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoDAO;
import com.axiante.mui.dbpromo.persistence.dao.FornitoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpParametroDAO;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpParametroEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpParametroService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpPianificazioneService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Dependent
@Transactional
public class MuiSpParametroServiceImpl extends AbstractDbPromoService<MuiSpParametroEntity> implements MuiSpParametroService {

    private static final long serialVersionUID = 1L;

    @Inject
    private transient MuiSpPianificazioneService muiSpPianificazioneService;

    @Inject
    private transient MuiSpParametroDAO muiSpParametroDAO;

    @Inject
    private transient CompratoreDAO compratoreDAO;

    @Inject
    private transient FornitoreDAO fornitoreDAO;

    @Override
    public DbPromoDAO<MuiSpParametroEntity> getDao() {
        return muiSpParametroDAO;
    }

    @Override
    public List<MuiSpParametroEntity> findByPromozioneId(Long idPromozione) {
        List<MuiSpParametroEntity> parametri = muiSpParametroDAO.findByPromozioneId(idPromozione);

        if (parametri != null) {
            for (MuiSpParametroEntity p : parametri) {
                CompratoreEntity compratore = compratoreDAO.findById(p.getIdCompratore());
                FornitoreEntity fornitore = fornitoreDAO.findById(p.getIdFornitore());

                p.setDescrizioneCompratore(compratore != null ? compratore.getFullDescription() : "");
                p.setCodiceCompratore(compratore != null ? compratore.getCodiceCompratore() : "");
                p.setDescrizioneFornitore(fornitore != null ? fornitore.getFullDescription() : "");
                p.setCodiceFornitore(fornitore != null ? fornitore.getCodiceFornitore() : "");
            }
        }

        return parametri;
    }

    @Override
    public MuiSpParametroEntity findByPromozioneIdAndCompratoreAndFornitore(Long idPromozione,
                                                                            Long idCompratore,
                                                                            Long idFornitore) {
        return muiSpParametroDAO.findByPromozioneIdAndCompratoreAndFornitore(
                idPromozione,
                idCompratore,
                idFornitore
        );
    }

    @Override
    public void saveOrUpdateParametro(MuiSpParametroEntity parametro, String username) {
        if (parametro == null
                || parametro.getIdPromozione() == null
                || parametro.getIdCompratore() == null
                || parametro.getIdFornitore() == null) {
            throw new IllegalArgumentException("Parametro Special Promotion incompleto");
        }

        MuiSpParametroEntity existing = muiSpParametroDAO.findByPromozioneIdAndCompratoreAndFornitore(
                parametro.getIdPromozione(),
                parametro.getIdCompratore(),
                parametro.getIdFornitore()
        );

        if (existing == null) {

            parametro.setDataInserimento(new Date());
            parametro.setCodiceUtenteInserimento(username);
            parametro.setUuid(AxUUID.randomUUID().toString());

            muiSpParametroDAO.persist(parametro);
            muiSpParametroDAO.flush();

        } else {
            existing.setTipoPremio(parametro.getTipoPremio());
            existing.setPremioCf(parametro.getPremioCf());
            existing.setPremioPerc(parametro.getPremioPerc());
            existing.setTipoBaseImp(parametro.getTipoBaseImp());
            existing.setSogliaMax(parametro.getSogliaMax());
            existing.setModLiq(parametro.getModLiq());
            existing.setCheckValue(parametro.getCheckValue());

            existing.setDataAggiornamento(new Date());
            existing.setCodiceUtenteAggiornamento(username);

            muiSpParametroDAO.flush();
        }

        muiSpPianificazioneService.refreshPremiFinaliParametro(
                parametro.getIdPromozione(),
                parametro.getIdCompratore(),
                parametro.getIdFornitore(),
                username
        );
    }
}
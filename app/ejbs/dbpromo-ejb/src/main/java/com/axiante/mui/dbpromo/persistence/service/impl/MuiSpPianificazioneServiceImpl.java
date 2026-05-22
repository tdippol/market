package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoDAO;
import com.axiante.mui.dbpromo.persistence.dao.FornitoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpParametroDAO;
import com.axiante.mui.dbpromo.persistence.dao.MuiSpPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpPianificazioneService;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.math.RoundingMode;


import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpParametroEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Dependent
@Transactional
public class MuiSpPianificazioneServiceImpl
        extends AbstractDbPromoService<MuiSpPianificazioneEntity>
        implements MuiSpPianificazioneService {

    private static final long serialVersionUID = 1L;

    @Inject
    private transient ItemService itemService;

    @Inject
    private transient FornitoreDAO fornitoreDAO;

    @Inject
    private transient MuiSpParametroDAO muiSpParametroDAO;

    @Inject
    private transient MuiSpPianificazioneDAO dao;

    @Override
    public DbPromoDAO<MuiSpPianificazioneEntity> getDao() {
        return dao;
    }

    @Override
    public List<MuiSpPianificazioneEntity> findByPromozioneAndTipo(Long idPromozione, String tipo) {
        List<MuiSpPianificazioneEntity> righe = dao.findByPromozioneAndTipo(idPromozione, tipo);

        enrichPianificazioni(righe);

        for (MuiSpPianificazioneEntity riga : righe) {

            BigDecimal nuovoPremioFinale = calculatePremioFinale(riga);

            if (riga.getPremioFinale() == null
                    || riga.getPremioFinale().compareTo(nuovoPremioFinale) != 0) {

                dao.updatePremioFinale(riga.getId(), nuovoPremioFinale);
                riga.setPremioFinale(nuovoPremioFinale);
            }
        }

        return righe;
    }

    private void enrichPianificazioni(List<MuiSpPianificazioneEntity> righe) {
        if (righe == null || righe.isEmpty()) {
            return;
        }

        List<Long> idsItem = new ArrayList<Long>();

        for (MuiSpPianificazioneEntity riga : righe) {
            if (riga.getIdItem() != null && !idsItem.contains(riga.getIdItem())) {
                idsItem.add(riga.getIdItem());
            }
        }

        if (idsItem.isEmpty()) {
            return;
        }


        Map<Long, ItemEntity> itemById = new HashMap<Long, ItemEntity>();
        List<ItemEntity> items = itemService.findByIds(idsItem);
        if (items != null) {
            for (ItemEntity item : items) {
                itemById.put(item.getId(), item);
            }
        }

        for (MuiSpPianificazioneEntity riga : righe) {
            enrichPianificazione(riga, itemById);
        }
    }

    private void enrichPianificazione(MuiSpPianificazioneEntity riga,
                                      Map<Long, ItemEntity> itemById) {
        if (riga == null) {
            return;
        }

        ItemEntity item = riga.getIdItem() == null ? null : itemById.get(riga.getIdItem());

        if (item != null) {
            riga.setDescrizioneItem(item.getLabel());

            if (item.getCompratoreEntity() != null) {
                CompratoreEntity compratore = item.getCompratoreEntity();

                riga.setIdCompratore(compratore.getId());
                riga.setCodiceCompratore(compratore.getCodiceCompratore());
                riga.setDescrizioneCompratore(buildCompratoreLabel(compratore));
            }
        }

        if (riga.getIdFornitore() != null) {
            FornitoreEntity fornitore = fornitoreDAO.findById(riga.getIdFornitore());
            riga.setDescrizioneFornitore(fornitore != null ? buildFornitoreLabel(fornitore) : "");
        }

        if (riga.getIdPromozione() != null
                && riga.getIdCompratore() != null
                && riga.getIdFornitore() != null) {

            MuiSpParametroEntity parametro =
                    muiSpParametroDAO.findByPromozioneIdAndCompratoreAndFornitore(
                            riga.getIdPromozione(),
                            riga.getIdCompratore(),
                            riga.getIdFornitore()
                    );

            if (parametro != null) {
                riga.setPremioCf(parametro.getPremioCf());
                riga.setPremioPerc(parametro.getPremioPerc());
                riga.setTipoBaseImp(parametro.getTipoBaseImp());
                riga.setSogliaMax(parametro.getSogliaMax());
                riga.setModLiq(parametro.getModLiq());
            }

            //riga.setPremioFinale(calculatePremioFinale(riga));

        }
    }

    @Override
    public void saveOrUpdate(MuiSpPianificazioneEntity entity, String username) {

        MuiSpPianificazioneEntity existing = dao.findByUniqueKey(
                entity.getIdPromozione(),
                entity.getIdItem(),
                entity.getIdFornitore(),
                entity.getTipo()
        );

        if (existing == null) {

            entity.setDataInserimento(new Date());
            entity.setCodiceUtenteInserimento(username);
            entity.setUuid(AxUUID.randomUUID().toString());

            BigDecimal premioFinale = calculatePremioFinale(entity);
            entity.setPremioFinale(premioFinale);

            dao.persist(entity);

        } else {

            existing.setEsc(entity.getEsc());
            existing.setEscPezziKg(entity.getEscPezziKg());
            existing.setPremioPercU(entity.getPremioPercU());

            Map<Long, ItemEntity> itemById = new HashMap<Long, ItemEntity>();

            if (existing.getIdItem() != null) {

                ItemEntity item = itemService.findById(existing.getIdItem());

                if (item != null) {
                    itemById.put(item.getId(), item);
                }
            }

            enrichPianificazione(existing, itemById);

            BigDecimal premioFinale = calculatePremioFinale(existing);

            dao.updatePremioFinale(existing.getId(), premioFinale);
            existing.setPremioFinale(premioFinale);
            updatePremioFinaleParametro(existing, premioFinale, username);

            existing.setDataAggiornamento(new Date());
            existing.setCodiceUtenteAggiornamento(username);

            dao.flush();
        }
    }

    private String buildFornitoreLabel(FornitoreEntity fornitore) {
        if (fornitore == null) {
            return "";
        }

        String codice = fornitore.getCodiceFornitore() == null ? "" : fornitore.getCodiceFornitore();
        String descrizione = fornitore.getDescrizione() == null ? "" : fornitore.getDescrizione();

        if (codice.isEmpty()) {
            return descrizione;
        }

        if (descrizione.isEmpty()) {
            return codice;
        }

        return codice + " - " + descrizione;
    }

    private String buildCompratoreLabel(CompratoreEntity compratore) {
        if (compratore == null) {
            return "";
        }

        String codice = compratore.getCodiceCompratore() == null ? "" : compratore.getCodiceCompratore();
        String descrizione = compratore.getDescrizione() == null ? "" : compratore.getDescrizione();

        if (codice.isEmpty()) {
            return descrizione;
        }

        if (descrizione.isEmpty()) {
            return codice;
        }

        return codice + " - " + descrizione;
    }

    //calcolo p finale
    private BigDecimal calculatePremioFinale(MuiSpPianificazioneEntity riga) {
        if (riga == null) {
            return BigDecimal.ZERO;
        }

        if (riga.getEsc() != null && riga.getEsc().intValue() == 1) {
            return BigDecimal.ZERO;
        }

        BigDecimal percentuale = riga.getPremioPercU() != null
                && riga.getPremioPercU().compareTo(BigDecimal.ZERO) != 0
                ? riga.getPremioPercU()
                : riga.getPremioPerc();

        if (percentuale == null || percentuale.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal base = resolveBaseImponibile(riga);

        if (base == null || base.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return base.multiply(percentuale)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal resolveBaseImponibile(MuiSpPianificazioneEntity riga) {
        String tipoBaseImp = riga.getTipoBaseImp();

        if (tipoBaseImp == null) {
            return BigDecimal.ZERO;
        }

        boolean escludiPromo = riga.getEscPezziKg() != null
                && riga.getEscPezziKg().intValue() == 1;

        if ("VENDUTO".equals(tipoBaseImp)) {
            return escludiPromo ? riga.getVendNopromo() : riga.getVendTot();
        }

        if ("COSTO ASSEGNATO ALLA VENDITA".equals(tipoBaseImp)) {
            return escludiPromo ? riga.getVendCostNopromo() : riga.getVendCostTot();
        }

        if ("QUOTA CONSEGNATO DA FORNITORE".equals(tipoBaseImp)) {
            return riga.getVendTot();
        }

        return BigDecimal.ZERO;
    }

    private void updatePremioFinaleParametro(MuiSpPianificazioneEntity riga,
                                             BigDecimal premioFinale,
                                             String username) {
        if (riga == null
                || riga.getIdPromozione() == null
                || riga.getIdCompratore() == null
                || riga.getIdFornitore() == null
                || riga.getTipo() == null) {
            return;
        }

        MuiSpParametroEntity parametro =
                muiSpParametroDAO.findByPromozioneIdAndCompratoreAndFornitore(
                        riga.getIdPromozione(),
                        riga.getIdCompratore(),
                        riga.getIdFornitore()
                );

        if (parametro == null) {
            return;
        }

        List<MuiSpPianificazioneEntity> righe =
                dao.findByPromozioneAndTipo(
                        riga.getIdPromozione(),
                        riga.getTipo()
                );

        enrichPianificazioni(righe);

        BigDecimal totale = BigDecimal.ZERO;
        BigDecimal soglia = null;

        if (righe != null) {
            for (MuiSpPianificazioneEntity pian : righe) {

                if (pian.getIdCompratore() == null
                        || pian.getIdFornitore() == null) {
                    continue;
                }

                if (!pian.getIdCompratore().equals(riga.getIdCompratore())) {
                    continue;
                }

                if (!pian.getIdFornitore().equals(riga.getIdFornitore())) {
                    continue;
                }

                if (soglia == null && pian.getSogliaMax() != null) {
                    soglia = pian.getSogliaMax();
                }

                BigDecimal premioRiga = calculatePremioFinale(pian);

                if (premioRiga != null) {
                    totale = totale.add(premioRiga);
                }
            }
        }

        if (soglia != null
                && soglia.compareTo(BigDecimal.ZERO) != 0
                && totale.compareTo(BigDecimal.ZERO) != 0
                && totale.compareTo(soglia) > 0) {
            totale = soglia;
        }

        if ("V".equalsIgnoreCase(riga.getTipo())) {
            parametro.setPremioFinaleVend(totale);
        }

        if ("C".equalsIgnoreCase(riga.getTipo())) {
            parametro.setPremioFinaleCons(totale);
        }

        parametro.setDataAggiornamento(new Date());
        parametro.setCodiceUtenteAggiornamento(username);

        muiSpParametroDAO.flush();
    }

    @Override
    public void refreshPremiFinaliParametro(Long idPromozione,
                                            Long idCompratore,
                                            Long idFornitore,
                                            String username) {
        refreshPremioFinaleParametroByTipo(idPromozione, idCompratore, idFornitore, "V", username);
        refreshPremioFinaleParametroByTipo(idPromozione, idCompratore, idFornitore, "C", username);
    }

    private void refreshPremioFinaleParametroByTipo(Long idPromozione,
                                                    Long idCompratore,
                                                    Long idFornitore,
                                                    String tipo,
                                                    String username) {
        MuiSpPianificazioneEntity riga = new MuiSpPianificazioneEntity();

        riga.setIdPromozione(idPromozione);
        riga.setIdCompratore(idCompratore);
        riga.setIdFornitore(idFornitore);
        riga.setTipo(tipo);

        updatePremioFinaleParametro(riga, null, username);
    }

}
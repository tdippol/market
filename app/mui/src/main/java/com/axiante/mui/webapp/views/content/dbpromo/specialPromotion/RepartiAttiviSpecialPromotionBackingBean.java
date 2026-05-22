package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpRepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.VMuiSpRepartoCompratoreEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpRepartoService;
import com.axiante.mui.dbpromo.persistence.service.VMuiSpRepartoCompratoreService;
import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Dependent
@Slf4j
public class RepartiAttiviSpecialPromotionBackingBean implements Serializable, FacesContextAware {

    private static final long serialVersionUID = 1L;

    @Inject
    private VMuiSpRepartoCompratoreService vMuiSpRepartoCompratoreService;

    @Inject
    private MuiSpRepartoService muiSpRepartoService;

    @Getter
    private List<RepartoAttivoModel> repartiAttivi = new ArrayList<RepartoAttivoModel>();

    @Getter
    private Long idPromozioneCorrente;

    public void loadData(Long idPromozione) {
        this.idPromozioneCorrente = idPromozione;
        this.repartiAttivi = new ArrayList<RepartoAttivoModel>();

        if (idPromozione == null) {
            return;
        }

        try {
            List<VMuiSpRepartoCompratoreEntity> associazioni =
                    vMuiSpRepartoCompratoreService.findAllOrdered();

            List<MuiSpRepartoEntity> repartiSpecialPromotion =
                    muiSpRepartoService.findByPromozioneId(idPromozione);

            Map<Long, MuiSpRepartoEntity> mappaRepartiPromo =
                    new LinkedHashMap<Long, MuiSpRepartoEntity>();
            for (MuiSpRepartoEntity entity : repartiSpecialPromotion) {
                mappaRepartiPromo.put(entity.getIdReparto(), entity);
            }

            Map<Long, RepartoAttivoModel> mappaReparti =
                    new LinkedHashMap<Long, RepartoAttivoModel>();

            for (VMuiSpRepartoCompratoreEntity associazione : associazioni) {
                if (associazione.getRepartoEntity() == null) {
                    continue;
                }

                Long idReparto = associazione.getRepartoEntity().getId();

                RepartoAttivoModel reparto = mappaReparti.get(idReparto);
                if (reparto == null) {
                    reparto = new RepartoAttivoModel();
                    reparto.setIdReparto(idReparto);
                    reparto.setCodiceReparto(associazione.getRepartoEntity().getCodiceReparto());
                    reparto.setDescrizioneReparto(associazione.getRepartoEntity().getDescrizione());

                    MuiSpRepartoEntity repartoPromo = mappaRepartiPromo.get(idReparto);
                    reparto.setAttivo(repartoPromo != null
                            && repartoPromo.getAttivo() != null
                            && repartoPromo.getAttivo().intValue() == 1);

                    reparto.setEspanso(false);
                    reparto.setCompratori(new ArrayList<CompratoreRepartoAttivoModel>());
                    mappaReparti.put(idReparto, reparto);
                }

                if (associazione.getCompratoreEntity() != null) {
                    CompratoreRepartoAttivoModel compratore = new CompratoreRepartoAttivoModel();
                    compratore.setIdCompratore(associazione.getCompratoreEntity().getId());
                    compratore.setCodiceCompratore(associazione.getCompratoreEntity().getCodiceCompratore());
                    compratore.setDescrizioneCompratore(associazione.getCompratoreEntity().getDescrizione());
                    reparto.getCompratori().add(compratore);
                }
            }

            for (MuiSpRepartoEntity entity : repartiSpecialPromotion) {
                Long idReparto = entity.getIdReparto();

                if (!mappaReparti.containsKey(idReparto) && entity.getRepartoEntity() != null) {
                    RepartoAttivoModel reparto = new RepartoAttivoModel();
                    reparto.setIdReparto(idReparto);
                    reparto.setCodiceReparto(entity.getRepartoEntity().getCodiceReparto());
                    reparto.setDescrizioneReparto(entity.getRepartoEntity().getDescrizione());
                    reparto.setAttivo(entity.getAttivo() != null && entity.getAttivo().intValue() == 1);
                    reparto.setEspanso(false);
                    reparto.setCompratori(new ArrayList<CompratoreRepartoAttivoModel>());
                    mappaReparti.put(idReparto, reparto);
                }
            }

            this.repartiAttivi = new ArrayList<RepartoAttivoModel>(mappaReparti.values());

        } catch (Exception e) {
            log.error("Errore caricamento tab Reparti Attivi", e);
            addErrorMessage("Errore", "Impossibile caricare i reparti attivi");
        }
    }

    public void onToggleReparto(RepartoAttivoModel reparto) {
        if (reparto == null || reparto.getIdReparto() == null || idPromozioneCorrente == null) {
            addErrorMessage("Errore", "Dati mancanti");
            return;
        }

        try {
            MuiSpRepartoEntity entity =
                    muiSpRepartoService.findByPromozioneIdAndRepartoId(idPromozioneCorrente, reparto.getIdReparto());

            if (entity == null) {
                addErrorMessage("Errore", "Associazione reparto non trovata");
                loadData(idPromozioneCorrente);
                return;
            }

            Integer attivoCorrente = entity.getAttivo();
            Integer nuovoAttivo = (attivoCorrente != null && attivoCorrente.intValue() == 1) ? 0 : 1;

            entity.setAttivo(nuovoAttivo);
            muiSpRepartoService.update(entity);
            muiSpRepartoService.flush();

            reparto.setAttivo(nuovoAttivo.intValue() == 1);

        } catch (Exception e) {
            log.error("Errore update reparto", e);
            addErrorMessage("Errore", "Impossibile aggiornare il reparto");
            loadData(idPromozioneCorrente);
        }
    }

    public void toggleEspanso(RepartoAttivoModel reparto) {
        if (reparto != null) {
            reparto.setEspanso(!reparto.isEspanso());
        }
    }

    @Getter
    @Setter
    public static class RepartoAttivoModel implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long idReparto;
        private String codiceReparto;
        private String descrizioneReparto;
        private boolean attivo;
        private boolean espanso;
        private List<CompratoreRepartoAttivoModel> compratori = new ArrayList<CompratoreRepartoAttivoModel>();
    }

    @Getter
    @Setter
    public static class CompratoreRepartoAttivoModel implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long idCompratore;
        private String codiceCompratore;
        private String descrizioneCompratore;
    }
}
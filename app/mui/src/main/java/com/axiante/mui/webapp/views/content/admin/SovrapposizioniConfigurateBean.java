package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSovrapposizioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgSovrapposizioneMeccanicheService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurazioneMeccanicheCanaleService;
import com.axiante.mui.dbpromo.persistence.service.MeccanicaService;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class SovrapposizioniConfigurateBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = -990633307353918303L;

    @Inject
    private MeccanicaService meccanicaService;

    @Inject
    private CanalePromozioneService canaleService;

    @Inject
    private ConfigurazioneMeccanicheCanaleService meccanicaCanaleService;

    @Inject
    private CfgSovrapposizioneMeccanicheService sovrapposizioniService;

    @Getter
    private List<MeccanicheEntity> meccaniche;

    @Getter
    private MeccanicheEntity meccanicaSelezionata = null;

    @Getter
    private List<SovrapposizioneDTO> sovrapposizioni;

    @Getter
    @Setter
    private Integer idCopyChannel;

    @Getter
    @Setter
    private List<CanalePromozioneEntity> channelsForCopy;

    @Getter
    @Setter
    private Long idMeccanicaCopy;

    private CanalePromozioneEntity canale;
    private CfgAbilitaMeccCanaleEntity meccanicaCanaleAbilitata = null;
    private List<String> meccanichePresenti;

    @PostConstruct
    public void init() {
        readSovrapposizioni();
    }

    public void setCanale(CanalePromozioneEntity canale) {
        this.canale = canale;
        readMeccanicheAssociate(canale);
    }

    public void setMeccanicaSelezionata(MeccanicheEntity meccanica) {
        this.meccanicaSelezionata = meccanica;
        if (meccanica != null) {
            meccanicaCanaleAbilitata = forceReload(meccanica);
            readSovrapposizioniPresenti();
            clearSelectedSovrapposizioni();
            updateSelectedSovrapposizioni();
        }
    }

    public void updateSelected(SovrapposizioneDTO sovrapposizione) {
        try {
            if (sovrapposizione.isSelected()) {
                if (addSovrapposizione(sovrapposizione.getCodice())) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                            String.format("Sovrapposizione meccanica '%s' associata", sovrapposizione.getCodice())));
                } else {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "Errore durante il salvataggio dei dati"));
                }
            } else {
                if (removeSovrapposizione(sovrapposizione.getCodice())) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                            String.format("Sovrapposizione meccanica '%s' disassociata", sovrapposizione.getCodice())));
                } else {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "Errore durante il salvataggio dei dati"));
                }
            }
        } catch (Exception ex) {
            log.error(String.format("Errore salvataggio sovrapposizione meccanica '%s'", sovrapposizione.getCodice()), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Errore durante il salvataggio dei dati"));
        }
    }

    public void updateCompatibilita(SovrapposizioneDTO sovrapposizione) {
        try {
            final CfgSovrapposizioneMeccanicheEntity cfg = getCfgSovrapposizione(sovrapposizione.getMeccanica());
            if (cfg != null) {
                cfg.setCompatibilita(sovrapposizione.getCompatibilita());
                meccanicaCanaleAbilitata.addSovrapposizione(cfg);
                sovrapposizioniService.persist(cfg);
                sovrapposizione.setSovrapposizione(cfg);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    String.format("Compatibilità sovrapposizione meccanica '%s' aggiornata", sovrapposizione.getCodice())));
            } else {
                log.error(String.format("Sovrapposizione con meccanica '%s' non presente per la meccanica '%s' ed il canale '%d'",
                        sovrapposizione.getCodice(), meccanicaSelezionata.getCodiceMeccanica(), canale.getCodiceCanale()));
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "Errore durante il salvataggio dei dati"));
            }
        } catch (Exception ex) {
            log.error(String.format("Errore salvataggio sovrapposizione meccanica '%s'", sovrapposizione.getCodice()), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Errore durante il salvataggio dei dati"));
        }
    }

    public void openCopyFromChannelDialog() {
        resetCopyFromChannelDialog();
        channelsForCopy = new ArrayList<>();
        if (this.canale != null) {
            final Comparator<CanalePromozioneEntity> byGruppo = Comparator
                    .comparing(c -> c.getGruppoPromozioneEntity().getDescrizione());
            final Comparator<CanalePromozioneEntity> byCanale = Comparator
                    .comparing(CanalePromozioneEntity::getDescrizione);
            channelsForCopy.addAll(
                    canaleService.findAll().stream()//tutti i canali disponibili
                            .filter(c -> !c.getId().equals(this.canale.getId())) //rimuovo il canale corrente
                            .sorted(byGruppo.thenComparing(byCanale)) //ordino
                            .collect(Collectors.toList())
            );
        }
    }

    public void confirmCopyFromChannel() {
        if (idCopyChannel != null) {
            try {
                final CanalePromozioneEntity canaleSelezionato = canaleService.findById(idCopyChannel.longValue());
                if (canaleSelezionato == null) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
                            "Errore di copia delle sovrapposizioni"));
                    log.error(String.format("Canale con id '%d' non presente a db", idCopyChannel));
                    return;
                }
                List<MeccanicheEntity> meccanicheDisponibili = canaleSelezionato.getMuiCfgAbilitaMeccCanales().stream()
                        .map(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity)
                        .filter(m -> meccaniche.contains(m))
                        .collect(Collectors.toList());
                if (meccanicheDisponibili.isEmpty()) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nessuna meccanica disponibile",
                                    String.format("Nessuna meccanica associata al canale '%s' e' associata al canale '%s'",
                                            canaleSelezionato.getCodiceCanale(), canale.getCodiceCanale())));
                    return;
                }
                meccanicheDisponibili.forEach(m -> {
                    final CfgAbilitaMeccCanaleEntity meccanicaCanale = getCfgMeccanicaCanale(m);
                    if (meccanicaCanale != null) {
                        sovrapposizioniService.findByCanaleMeccanica(meccanicaCanale)
                                .forEach(meccanicaCanale::removeSovrapposizione);
                        meccanicaCanaleService.persist(meccanicaCanale);
                        copiaSovrapposizioniDaMeccanica(canaleSelezionato, m)
                                .forEach(meccanicaCanale::addSovrapposizione);
                        meccanicaCanaleService.persist(meccanicaCanale);
                    }
                });
                clearSelectedSovrapposizioni();
                readMeccanicheAssociate(canale);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                        "Sovrapposizioni copiate"));
            } catch (Exception ex) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
                        "Errore durante il salvataggio delle sovrapposizioni"));
                log.error("Errore durante la copia da canale", ex);
            }
        }
    }

    public List<MeccanicheEntity> getMeccanicheForCopy(){
        return meccaniche.stream().filter(m -> !m.equals(meccanicaSelezionata)).collect(Collectors.toList());
    }

    public void resetCopyFromChannelDialog() {
        setIdCopyChannel(null);
    }

    public void resetCopyFromMeccanicaDialog() {
        setIdMeccanicaCopy(null);
    }

    public void confirmCopyFromMeccanica() {
        if (idMeccanicaCopy != null) {
            MeccanicheEntity meccanica = meccaniche.stream().filter(m -> m.getId().equals(idMeccanicaCopy))
                    .findFirst().orElse(null);
            if (meccanica == null) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
                        "Errore di copia delle sovrapposizioni"));
                log.error(String.format("Meccanica con id '%d' non presente a db per il canale '%d'", idMeccanicaCopy,
                        canale.getCodiceCanale()));
                return;
            }
            try {
                final CfgAbilitaMeccCanaleEntity meccanicaCanale = getCfgMeccanicaCanale(meccanicaSelezionata);
                final List<CfgSovrapposizioneMeccanicheEntity> sovrapposizioniDaCopiare =
                        copiaSovrapposizioniDaMeccanica(canale, meccanica);
                if (sovrapposizioniDaCopiare.isEmpty()) {
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nessuna sovrapposizione",
                            "Non ci sono sovrapposizioni compatibili"));
                    return;
                }
                if (meccanicaCanale != null) {
                    sovrapposizioniService.findByCanaleMeccanica(meccanicaCanale)
                            .forEach(meccanicaCanale::removeSovrapposizione);
                    meccanicaCanaleService.persist(meccanicaCanale);
                    sovrapposizioniDaCopiare.forEach(meccanicaCanale::addSovrapposizione);
                    meccanicaCanaleService.persist(meccanicaCanale);
                }
                clearSelectedSovrapposizioni();
                readMeccanicheAssociate(canale);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                        "Sovrapposizioni copiate"));
            } catch (Exception ex) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!",
                        "Errore durante il salvataggio delle sovrapposizioni"));
                log.error("Errore durante la copia da meccanica", ex);
            }
        }
    }

    private List<CfgSovrapposizioneMeccanicheEntity> copiaSovrapposizioniDaMeccanica(CanalePromozioneEntity canale,
                                                                                     MeccanicheEntity meccanica) {
        return canale.getMuiCfgAbilitaMeccCanales().stream()
                .filter(a -> a.getMeccanicheEntity().getId().equals(meccanica.getId()))
                .flatMap(a -> a.getSovrapposizioniConfigurate().stream())
                .map(s -> {
                    CfgSovrapposizioneMeccanicheEntity entity = new CfgSovrapposizioneMeccanicheEntity();
                    entity.setCompatibilita(s.getCompatibilita());
                    entity.setMeccanicaSovrapposta(s.getMeccanicaSovrapposta());
                    return entity;
                })
                .collect(Collectors.toList());
    }

    private boolean addSovrapposizione(String codiceMeccanica) {
        final SovrapposizioneDTO sovrapposizione = getSovrapposizione(codiceMeccanica);
        if (sovrapposizione != null) {
            CfgSovrapposizioneMeccanicheEntity cfg = getCfgSovrapposizione(sovrapposizione.getMeccanica());
            if (cfg == null) {
                // Aggiungo relazione
                cfg = new CfgSovrapposizioneMeccanicheEntity();
                cfg.setMeccanicaSovrapposta(sovrapposizione.getMeccanica());
                meccanicaCanaleAbilitata.addSovrapposizione(cfg);
                meccanicaCanaleAbilitata = meccanicaCanaleService.persist(meccanicaCanaleAbilitata);
                sovrapposizione.setSovrapposizione(cfg);
                return true;
            } else {
                log.error(String.format("Sovrapposizione con meccanica '%s' gia' presente per la meccanica '%s' ed il canale '%d'",
                        codiceMeccanica, meccanicaSelezionata.getCodiceMeccanica(), canale.getCodiceCanale()));
            }
        } else {
            log.error(String.format("Codice meccanica sovrapposta '%s' non trovato", codiceMeccanica));
        }
        return false;
    }

    private boolean removeSovrapposizione(String codiceMeccanica) {
        final SovrapposizioneDTO sovrapposizione = getSovrapposizione(codiceMeccanica);
        if (sovrapposizione != null) {
            CfgSovrapposizioneMeccanicheEntity cfg = getCfgSovrapposizione(sovrapposizione.getMeccanica());
            if (cfg != null) {
                // Rimuovo relazione
                meccanicaCanaleAbilitata.removeSovrapposizione(cfg);
                meccanicaCanaleAbilitata = meccanicaCanaleService.persist(meccanicaCanaleAbilitata);
                sovrapposizione.reset();
                return true;
            } else {
                log.error(String.format("Sovrapposizione con meccanica '%s' non presente per la meccanica '%s' ed il canale '%d'",
                        codiceMeccanica, meccanicaSelezionata.getCodiceMeccanica(), canale.getCodiceCanale()));
            }
        } else {
            log.error(String.format("Codice meccanica sovrapposta '%s' non trovato", codiceMeccanica));
        }
        return false;
    }

    private void readSovrapposizioni() {
        sovrapposizioni = meccanicaService.findAll().stream()
                .sorted(Comparator.comparing(MeccanicheEntity::getCodiceMeccanica))
                .map(SovrapposizioneDTO::new)
                .collect(Collectors.toList());
    }

    private CfgAbilitaMeccCanaleEntity getCfgMeccanicaCanale(@NonNull MeccanicheEntity meccanica) {
        if (canale == null) {
            return null;
        }
        return canale.getMuiCfgAbilitaMeccCanales().stream()
                .filter(c -> meccanica.getId().equals(c.getMeccanicheEntity().getId()))
                .findAny().orElse(null);
    }

    private CfgAbilitaMeccCanaleEntity forceReload(@NonNull MeccanicheEntity meccanica){
        return meccanicaCanaleService.findByMeccanicaAndCanale(meccanica.getId(), canale.getId());

    }
    private void readMeccanicheAssociate(CanalePromozioneEntity canale) {
        meccaniche = canale == null
                ? new ArrayList<>()
                : canale.getMuiCfgAbilitaMeccCanales().stream()
                    .map(CfgAbilitaMeccCanaleEntity::getMeccanicheEntity)
                    .distinct()
                    .sorted(Comparator.comparing(MeccanicheEntity::getCodiceMeccanica))
                    .collect(Collectors.toList());
        if (meccaniche.isEmpty()) {
            clearSelectedSovrapposizioni();
        } else {
            if (meccanicaSelezionata == null || meccaniche.stream()
                    .noneMatch(m -> meccanicaSelezionata.getCodiceMeccanica().equals(m.getCodiceMeccanica()))) {
                setMeccanicaSelezionata(meccaniche.get(0));
            } else {
                setMeccanicaSelezionata(meccanicaSelezionata);
            }
        }
    }

    private void readSovrapposizioniPresenti() {
        meccanichePresenti = canale.getMuiCfgAbilitaMeccCanales().stream()
                .filter(a -> meccanicaSelezionata.getCodiceMeccanica().equals(a.getMeccanicheEntity().getCodiceMeccanica()))
                .map(CfgAbilitaMeccCanaleEntity::getSovrapposizioniConfigurate)
                .flatMap(List::stream)
                .map(s -> s.getMeccanicaSovrapposta().getCodiceMeccanica())
                .distinct()
                .collect(Collectors.toList());
    }

    private void clearSelectedSovrapposizioni() {
        sovrapposizioni.forEach(SovrapposizioneDTO::reset);
    }

    private void updateSelectedSovrapposizioni() {
        sovrapposizioni.stream().filter(s -> meccanichePresenti.contains(s.getCodice()))
                .forEach(s -> {
                    CfgSovrapposizioneMeccanicheEntity cfg = getCfgSovrapposizione(s.getMeccanica());
                    if (cfg != null) {
                        s.setSovrapposizione(cfg);
                        s.setSelected(true);
                        s.setCompatibilita(cfg.getCompatibilita());
                    }
                });
    }

    private CfgSovrapposizioneMeccanicheEntity getCfgSovrapposizione(MeccanicheEntity meccanica) {
        return meccanica == null
                ? null
                : meccanicaCanaleAbilitata.getSovrapposizioniConfigurate().stream()
                    .filter(s -> meccanica.getCodiceMeccanica().equals(s.getMeccanicaSovrapposta().getCodiceMeccanica()))
                    .findAny().orElse(null);
    }

    private SovrapposizioneDTO getSovrapposizione(String codiceMeccanica) {
        return codiceMeccanica == null
                ? null
                : sovrapposizioni.stream().filter(s -> codiceMeccanica.equals(s.getCodice())).findAny().orElse(null);
    }

    public class SovrapposizioneDTO {
        @Getter
        private CfgSovrapposizioneMeccanicheEntity cfg;

        @Getter
        private MeccanicheEntity meccanica;

        @Getter
        private MeccanicheEntity meccanicaBase;

        @Getter
        @Setter
        private boolean selected = false;

        @Getter
        @Setter
        private Boolean compatibilita = false;

        public SovrapposizioneDTO(@NonNull MeccanicheEntity meccanica) {
            this.meccanicaBase = meccanica;
            this.meccanica = meccanica;
        }

        public void setSovrapposizione(CfgSovrapposizioneMeccanicheEntity cfg) {
            this.cfg = cfg;
            if (cfg == null) {
                this.meccanica = meccanicaBase;
                this.selected = false;
                this.compatibilita = false;
            } else {
                this.meccanica = cfg.getMeccanicaSovrapposta();
                this.selected = true;
                this.compatibilita = cfg.getCompatibilita();
            }
        }

        public void reset() {
            setSovrapposizione(null);
        }

        public Long getId() {
            return getMeccanica().getId();
        }

        public String getCodice() {
            return getMeccanica().getCodiceMeccanica();
        }

        public String getDescrizione() {
            return getMeccanica().getDescrizione();
        }
    }
}

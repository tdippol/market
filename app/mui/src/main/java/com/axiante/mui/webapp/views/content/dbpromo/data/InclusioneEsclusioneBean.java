package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.TipoInclusioneEsclusione;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiInclusioneEsclusioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgInclusioniEsclusioniService;
import com.axiante.mui.dbpromo.persistence.service.GrmService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MeccanicaService;
import com.axiante.mui.dbpromo.persistence.service.MuiInclusioneEsclusioneService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.persistence.PersistenceException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class InclusioneEsclusioneBean implements FacesContextAware {
    private static final String NOMATCH = "no match";
    private static final String DIALOG_HEADER_ADD_INCLUSIONE = "Aggiungi Inclusione";
    private static final String DIALOG_HEADER_ADD_ESCLUSIONE = "Aggiungi Esclusione";
    private static final String DELETE_MESSAGE_TEMPLATE = "Sei sicuro di voler eliminare l'%s per %s dalla meccanica \"%s?\"";
    private MuiInclusioneEsclusioneService inclusioneEsclusioneService;
    private CfgInclusioniEsclusioniService cfgInclusioniEsclusioniService;
    private ItemService itemService;
    private RepartoService repartoService;
    private GrmService grmService;
    private MeccanicaService meccanicaService;
    private UserDTO userDTO;
    private PromozioneTestataEntity promozioneCorrente;

    @Getter
    private String deleteMessage;

    @Getter
    @Setter
    Long idPromozioneCorrente;

    @Getter
    private boolean disabledBtnScarico = true;

    @Getter
    private boolean disabledBtnAddInclusione = true;

    @Getter
    private boolean disabledBtnAddEsclusione = true;

    @Getter
    private String dialogHeader = DIALOG_HEADER_ADD_INCLUSIONE;

    @Getter
    private AddInclusioneBackingBean bean;

    private Map<MeccanicheEntity, List<Configuration>> configurations;
    private MuiInclusioneEsclusioneEntity entityToDelete = null;

    public InclusioneEsclusioneBean(MuiInclusioneEsclusioneService inclusioneEsclusioneService,
                                    CfgInclusioniEsclusioniService cfgInclusioniEsclusioniService,
                                    ItemService itemService,
                                    RepartoService repartoService,
                                    GrmService grmService,
                                    MeccanicaService meccanicaService,
                                    UserDTO userDto) {
        this.inclusioneEsclusioneService = inclusioneEsclusioneService;
        this.cfgInclusioniEsclusioniService = cfgInclusioniEsclusioniService;
        this.itemService = itemService;
        this.repartoService = repartoService;
        this.grmService = grmService;
        this.meccanicaService = meccanicaService;
        this.userDTO = userDto;
    }

    public void confirmScarico() {
        if (promozioneCorrente != null) {
            try {
                inclusioneEsclusioneService.runFunctionExportInclusioniEsclusioni(promozioneCorrente.getId(),
                        userDTO.getUsermame());
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusioni / Esclusioni pubblicate",
                        "Inclusioni / Esclusioni pubblicate correttamente"));
            } catch (Exception ex) {
                log.error(String.format("Stored procedure %s terminata con errore", Constants.P_MUI_EXPORT_INCL_ESCL), ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore pubblicazione Inclusioni / Esclusioni"));
            }
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile pubblicazione Inclusioni / Esclusioni; contattare l'assistenza"));
        }
    }

    public void addInclusione() {
        bean = new AddInclusioneBackingBean(promozioneCorrente.getDescrizioneEstesa(), TipoInclusioneEsclusione.INCLUSIONE);
        dialogHeader = DIALOG_HEADER_ADD_INCLUSIONE;
        calculateMeccanicheDisponibili();
    }

    public void addEsclusione() {
        bean = new AddInclusioneBackingBean(promozioneCorrente.getDescrizioneEstesa(), TipoInclusioneEsclusione.ESCLUSIONE);
        dialogHeader = DIALOG_HEADER_ADD_ESCLUSIONE;
        calculateMeccanicheDisponibili();
    }

    public void setPromozioneCorrente(PromozioneTestataEntity promozioneCorrente) {
        this.promozioneCorrente = promozioneCorrente;
        if (promozioneCorrente != null) {
            idPromozioneCorrente = promozioneCorrente.getId();
            readConfiguration();
        }
        disabledBtnScarico = idPromozioneCorrente == null;
    }

    public void lookupElemento() {
        String result = null;
        if (bean == null || bean.getTipiElemento() == null || bean.getElemento() == null) {
            log.debug("bean should not trigger lookupElemento when null");
        } else {
            switch (bean.getTipoElemento()) {
                case GRM:
                    if (bean.getElemento().length() >= 3) {
                        result = lookupGrmDescription(bean.getElemento());
                    }
                    break;
                case REPARTO:
                    if (bean.getElemento().length() >= 2) {
                        result = lookupRepartoDescription(bean.getElemento());
                    }
                    break;
                case ARTICOLO:
                    if (bean.getElemento().length() >= 6) {
                        result = lookupArticoloDescription(bean.getElemento());
                    }
                    break;
                default:
                    log.warn("Lookup non implementato per tipo elemento {}", bean.getTipoElemento());
                    break;
            }
        }
        if (NOMATCH.equals(result)) {
            log.debug("lookupElemento: nessun match per tipoElemento {} e codiceElemento {}", bean.getTipoElemento(), bean.getElemento());
            result = null;
        }
        bean.setDescrizione(result);
        bean.okDisabled = (result == null);
    }

    public void resetDialog() {
        bean = null;
        dialogHeader = DIALOG_HEADER_ADD_INCLUSIONE;
    }

    public void saveBean() {
        if (isValid()) {
            MeccanicheEntity meccanica = meccanicaService.findById(bean.getMeccanica());
            if (meccanica == null) {
                String msg = String.format("Errore nel salvataggio della nuova inclusione/esclusione: meccanica non valida %s",
                        bean.getMeccanica());
                log.error(msg);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
            } else {
                try {
                    saveInclusioneEsclusione(meccanica);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                            "Inclusione/Esclusione aggiunta con successo."));
                    readConfiguration();
                } catch (SQLDataException ex) {
                    String msg = String.format("Errore nel salvataggio della nuova inclusione/esclusione: tipo elemento non valido %s",
                            bean.getTipoElemento());
                    log.error(msg, ex);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
                } catch (PersistenceException ex) {
                    log.error("Errore nel salvataggio della nuova inclusione/esclusione", ex);
                    String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                    if (message != null && message.contains("duplicate key value violates unique constraint")) {
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Errore",
                                "Impossibile aggiungere l'inclusione/esclusione: elemento già presente per la promozione selezionata."));
                    } else {
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                                "Errore dati, contattare l'assistenza"));
                    }
                } catch (Exception ex) {
                    log.error("Errore nel salvataggio della nuova inclusione/esclusione", ex);
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                            "Errore generico durante il salvataggio, contattare l'assistenza"));
                }
            }
        }
    }

    public void delete() {
        String idRequested = getRequestParameterMap().get("id");
        try {
            Long id = Long.parseLong(idRequested);
            entityToDelete = inclusioneEsclusioneService.findById(id);
            if (entityToDelete == null) {
                log.error(String.format("No InclusioneEsclusioneEntity found with id: %s", id));
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        String.format("Codice inclusione/esclusione non valido: %s", idRequested)));
            } else {
                String tipo = entityToDelete.getTipo().equals(TipoInclusioneEsclusione.INCLUSIONE) ? "inclusione" : "esclusione";
                String tipoElemento = getTipoElemento();
                deleteMessage = String.format(DELETE_MESSAGE_TEMPLATE, tipo, tipoElemento,
                        entityToDelete.getMeccanicaEntity().getDescrizione());
            }
        } catch (Exception ex) {
            String msg = String.format("Codice inclusione/esclusione non valido: %s", idRequested);
            log.error(msg, ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
        }
    }

    public void performDelete() {
        if (entityToDelete != null) {
            String tipo = entityToDelete.getTipo().equals(TipoInclusioneEsclusione.INCLUSIONE) ? "Inclusione" : "Esclusione";
            inclusioneEsclusioneService.remove(entityToDelete);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                    String.format("%s eliminata con successo", tipo)));
        }
        resetDelete();
        readConfiguration();
    }

    public void resetDelete() {
        this.entityToDelete = null;
        this.deleteMessage = null;
    }

    private String getTipoElemento() {
        String tipoElemento = "l'articolo";
        switch (entityToDelete.getTipoElemento()) {
            case GRM:
                tipoElemento = "il GRM";
                break;
            case REPARTO:
                tipoElemento = "il reparto";
                break;
            case TOTALE:
                tipoElemento = "il totale";
                break;
            case ARTICOLO:
                tipoElemento = "l'articolo";
                break;
        }
        tipoElemento += " \"" + entityToDelete.getCodiceElemento() + " - " + entityToDelete.getElemento() + "\"";
        return tipoElemento;
    }

    protected void readConfiguration() {
        configurations = new HashMap<>();
        if (promozioneCorrente != null) {
            List<MeccanicheEntity> meccaniche = promozioneCorrente.getPromozionePianificazioneEntities().stream()
                    .map(PromozionePianificazioneEntity::getMeccanicaEntity).distinct().collect(Collectors.toList());
            // #5707: Se ho la M800 o la M801, allora aggiungo anche la M810
            List<String> codiciMeccaniche = meccaniche.stream().map(MeccanicheEntity::getCodiceMeccanica)
                    .collect(Collectors.toList());
            if (codiciMeccaniche.contains("M800") || codiciMeccaniche.contains("M801")) {
                MeccanicheEntity meccanicaM810 = meccanicaService.findByCodice("M810");
                if (meccanicaM810 != null) {
                    meccaniche.add(meccanicaM810);
                }
            }
            meccaniche.forEach(m -> configurations.put(m,
                    cfgInclusioniEsclusioniService.findByMeccanica(m.getId()).stream()
                            .map(c -> new Configuration(c.getTipoElemento(), c.getInclusioni(), c.getEsclusioni()
                            )).collect(Collectors.toList())
            ));
        }
        // a questo punto filtro le meccaniche che hanno raggiunto il limite
        configurations.entrySet().removeIf(entry -> {
            MeccanicheEntity meccanica = entry.getKey();
            List<Configuration> configs = entry.getValue();
            boolean removeMeccanica = true;
            for (Configuration config : configs) {
                if (config.inclusioniMassime != null && config.inclusioniMassime > 0) {
                    long countInclusioni = inclusioneEsclusioneService.countByPromozioneAndMeccanicaAndTipoAndTipoElemento(
                            promozioneCorrente.getId(),
                            meccanica.getId(),
                            TipoInclusioneEsclusione.INCLUSIONE,
                            config.tipo);
                    config.canAddInclusione = config.inclusioniMassime > countInclusioni;
                } else {
                    config.canAddInclusione = false;
                }
                if (config.esclusioniMassime != null && config.esclusioniMassime > 0) {
                    long countEsclsusioni = inclusioneEsclusioneService.countByPromozioneAndMeccanicaAndTipoAndTipoElemento(
                            promozioneCorrente.getId(),
                            meccanica.getId(),
                            TipoInclusioneEsclusione.ESCLUSIONE,
                            config.tipo
                    );
                    config.canAddEsclusione = config.esclusioniMassime > countEsclsusioni;
                } else {
                    config.canAddEsclusione = false;
                }
                // devo elminare tutta la meccanica se per tutti i tipi elemento non posso aggiungere nè inclusioni nè esclusioni
                if (config.canAddInclusione || config.canAddEsclusione) {
                    removeMeccanica = false;
                }
            }
            return removeMeccanica;
        });
        if (configurations.isEmpty()) {
            disabledBtnAddEsclusione = true;
            disabledBtnAddInclusione = true;
        } else {
            disabledBtnAddEsclusione = configurations.values().stream().flatMap(List::stream).noneMatch(c -> c.canAddEsclusione);
            disabledBtnAddInclusione = configurations.values().stream().flatMap(List::stream).noneMatch(c -> c.canAddInclusione);
        }
    }

    protected void calculateMeccanicheDisponibili() {
        if (bean != null) {
            // filtro le meccaniche che hanno ancora spazio per l'inclusione o l'esclusione
            ArrayList<MeccanicheEntity> meccanicheDisponibili = new ArrayList<>();
            if (promozioneCorrente != null) {
                configurations.keySet().forEach(k -> {
                    boolean keep = configurations.get(k).stream().anyMatch(c -> {
                        if (bean.tipo.equals(TipoInclusioneEsclusione.INCLUSIONE) && c.canAddInclusione) {
                            return true;
                        }
                        return bean.tipo.equals(TipoInclusioneEsclusione.ESCLUSIONE) && c.canAddEsclusione;
                    });
                    if (keep) {
                        meccanicheDisponibili.add(k);
                    }
                });
                bean.meccaniche = meccanicheDisponibili;
            } else {
                bean.meccaniche = new ArrayList<>();
            }
        }
    }

    private void saveInclusioneEsclusione(MeccanicheEntity meccanica) throws Exception {
        MuiInclusioneEsclusioneEntity entity = new MuiInclusioneEsclusioneEntity();
        entity.setPromozione(promozioneCorrente);
        entity.setMeccanicaEntity(meccanica);
        entity.setTipoElemento(bean.getTipoElemento());
        entity.setCodiceElemento(bean.getElemento());
        entity.setElemento(bean.getDescrizione());
        entity.setTipo(bean.getTipo());
        inclusioneEsclusioneService.persistWithAuditLog(entity, userDTO.getUsermame());
    }

    private boolean isValid() {
        return bean != null && promozioneCorrente != null && bean.getElemento() != null && bean.getMeccanica() != null
                && bean.getTipoElemento() != null && bean.getDescrizione() != null;
    }

    private String lookupArticoloDescription(String codiceArticolo) {
        if (codiceArticolo == null || codiceArticolo.isEmpty()) {
            return "";
        }
        ItemEntity e = itemService.findActiveByCode(codiceArticolo);
        return e != null ? e.getDescrizione() : NOMATCH;
    }

    private String lookupRepartoDescription(String codiceReparto) {
        if (codiceReparto == null || codiceReparto.isEmpty()) {
            return "";
        }
        RepartoEntity e = repartoService.findByCode(codiceReparto);
        return e != null ? e.getDescrizione() : NOMATCH;
    }

    private String lookupGrmDescription(String codiceGrm) {
        if (codiceGrm == null || codiceGrm.isEmpty()) {
            return "";
        }
        GrmEntity e = grmService.findByCode(codiceGrm);
        return e != null ? e.getDescrizione() : NOMATCH;
    }

    public class AddInclusioneBackingBean {
        @Getter
        String promozione;
        @Getter
        List<MeccanicheEntity> meccaniche;
        @Getter
        @Setter
        Long meccanica;
        @Getter
        ElementType tipoElemento;
        @Getter
        @Setter
        String elemento;
        @Getter
        @Setter
        String descrizione;
        final List<ElementType> tipiElemento;
        @Getter(value = AccessLevel.PROTECTED)
        private TipoInclusioneEsclusione tipo;

        boolean okDisabled = true;

        protected AddInclusioneBackingBean(String promozione, TipoInclusioneEsclusione tipo) {
            tipiElemento = new ArrayList<>();
            tipiElemento.add(ElementType.ARTICOLO);
            tipiElemento.add(ElementType.REPARTO);
            tipiElemento.add(ElementType.GRM);

            this.promozione = promozione;
            this.tipo = tipo;
        }

        public boolean isOkDisabled() {
            if (meccanica == null || tipoElemento == null || elemento == null || elemento.isEmpty()) {
                return true;
            }
            return okDisabled;
        }

        public List<ElementType> getTipiElemento() {
            // filtro i tipi elemento in base al tipo di inclusione/esclusione
            // e alla mecanica e alla configurazione
            if (meccanica == null || promozioneCorrente == null) {
                return Collections.emptyList();
            }
            MeccanicheEntity meccanicheEntity = meccaniche.stream().filter(m -> m.getId().equals(meccanica)).findFirst().orElse(null);
            if (meccanicheEntity == null) {
                return Collections.emptyList();
            }
            List<Configuration> configs = configurations.get(meccanicheEntity);
            if (configs == null) {
                return Collections.emptyList();
            }
            // adesso filtro la configurazione in base al tipo della configurazione e al tipo del bean
            return configs.stream().filter(c -> {
                boolean keep = false;
                if (tipo.equals(TipoInclusioneEsclusione.INCLUSIONE) && c.canAddInclusione) {
                    keep = true;
                }
                if (tipo.equals(TipoInclusioneEsclusione.ESCLUSIONE) && c.canAddEsclusione) {
                    keep = true;
                }
                return keep;
            }).map(c -> c.tipo).collect(Collectors.toList());
        }

        public void setTipoElemento(String tipoElemento) {
            if (tipoElemento == null || tipoElemento.isEmpty()) {
                this.tipoElemento = null;
                return;
            }
            this.tipoElemento = ElementType.fromDescription(tipoElemento);
        }

        public void setTipoElemento(ElementType tipoElemento) {
            this.tipoElemento = tipoElemento;
        }
    }

    class Configuration {
        protected ElementType tipo;
        protected Long inclusioniMassime;
        protected Long esclusioniMassime;
        boolean canAddInclusione;
        boolean canAddEsclusione;

        protected Configuration(ElementType tipo, Long inclusioniMassime, Long esclusioniMassime) {
            this.tipo = tipo;
            this.inclusioniMassime = inclusioniMassime;
            this.esclusioniMassime = esclusioniMassime;
        }
    }
}

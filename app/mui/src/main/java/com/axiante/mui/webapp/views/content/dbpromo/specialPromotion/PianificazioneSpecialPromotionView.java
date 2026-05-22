package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpContribCompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpParametroEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiSpContribCompratoreService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpParametroService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpTestataService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.enumeration.TabPianificazioneSpecialPromotionEnum;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@MuiViewModel("PianificazioneSpecialPromotionView")
@Dependent
@Slf4j
public class PianificazioneSpecialPromotionView extends AbstractDBPromoNavigation {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<Long> idCompratoriDellaPromozione = new ArrayList<>();

    @Inject
    private MuiSpContribCompratoreService muiSpContribCompratoreService;

    @Inject
    private MuiSpParametroService muiSpParametroService;

    @Getter
    private List<String> reportRiassuntivoRows = new ArrayList<String>();

    @Inject
    private PromoService promoService;

    @Inject
    private MuiService muiService;

    @Inject
    private MuiSpTestataService muiSpTestataService;

    @Inject
    @Getter
    private InserimentoParametriBackingBean inserimentoParametriBackingBean;

    @Getter
    private List<CompratoreEntity> compratori = new ArrayList<>();

    @Getter
    private List<MuiSpTestataEntity> specialPromotions = new ArrayList<>();

    @Getter
    @Setter
    private MuiSpTestataEntity specialPromotionSelected;

    @Getter
    private MuiSpTestataEntity specialPromotionCorrente;

    @Getter
    private MuiSpStatoEntity statoCorrente;

    @Getter
    private Object idSpecialPromotionCorrente;

    @Getter
    private Long idPromozioneCorrente;

    @Getter
    @Setter
    private Long idCompratoreSelezionato;

    @Getter
    @Setter
    private Long idReportCompratoreSelezionato;

    @Getter
    @Setter
    private Long idReportFornitoreSelezionato;

    private Map<Long, String> reportCompratori = new LinkedHashMap<Long, String>();

    private Map<Long, String> reportFornitori = new LinkedHashMap<Long, String>();

    @Getter
    @Setter
    private Integer activeTab = 0;

    @Getter
    private final List<TabPianificazioneSpecialPromotionEnum> renderedTabs = new ArrayList<>();

    private List<TabItem> tabs;

    @Getter
    private List<TabItem> renderedTabItems;

    private Long currentIdMenu = 0L;

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        retrieveCurrentIdMenu(node.getParams());
        init();
    }

    private void retrieveCurrentIdMenu(Map<String, List<String>> params) {
        String idMenu = params.get("idMenu") != null ? params.get("idMenu").get(0) : null;
        if (idMenu != null) {
            currentIdMenu = Long.valueOf(idMenu);
        } else {
            log.error("Cannot retrieve idMenu from params: " + params);
        }
    }

    public void init() {
        createTabs();
        activeTab = 0;

        loadSpecialPromotions();
        loadSpecialPromotionAttiva();
        loadCompratori();

        applyTabsPosition();
        updateInserimentoParametriBean();
    }

    private void loadSpecialPromotions() {
        specialPromotions = muiSpTestataService.findAllForVisualizza();
    }

    private void loadSpecialPromotionAttiva() {
        MuiSpTestataEntity testataAttiva = muiSpTestataService.findActive();

        if (testataAttiva == null) {
            setSpecialPromotionCorrente(null);
            this.statoCorrente = null;
            return;
        }

        MuiSpTestataEntity testataPerHeader = muiSpTestataService.findByIdForHeader(testataAttiva.getId());
        MuiSpTestataEntity testataFinale = testataPerHeader != null ? testataPerHeader : testataAttiva;

        setSpecialPromotionCorrente(testataFinale);
        loadHeaderState(testataFinale);
    }

    public void setIdSpecialPromotionCorrente(Object idSpecialPromotionCorrente) {
        this.idSpecialPromotionCorrente = idSpecialPromotionCorrente;

        if (idSpecialPromotionCorrente != null) {
            try {
                Long id = Long.valueOf(idSpecialPromotionCorrente.toString());

                MuiSpTestataEntity testata = muiSpTestataService.findById(id);
                MuiSpTestataEntity testataHeader = muiSpTestataService.findByIdForHeader(id);

                setSpecialPromotionCorrente(testataHeader != null ? testataHeader : testata);

            } catch (Exception e) {
                log.error("Errore nel recupero della Special Promotion con id " + idSpecialPromotionCorrente, e);
                setSpecialPromotionCorrente(null);
            }
        } else {
            setSpecialPromotionCorrente(null);
        }
    }

    private void setSpecialPromotionCorrente(MuiSpTestataEntity testata) {
        this.specialPromotionSelected = testata;
        this.specialPromotionCorrente = testata;
        this.idSpecialPromotionCorrente = testata != null ? testata.getId() : null;
        this.idPromozioneCorrente = testata != null ? testata.getId() : null;

        loadHeaderState(testata);
        updateInserimentoParametriBean();
        loadCompratoriDellaPromozione(testata);
        loadReportCompratori();
    }

    private void loadHeaderState(MuiSpTestataEntity testata) {
        if (testata != null && testata.getSpStatoEntities() != null) {
            this.statoCorrente = testata.getSpStatoEntities().stream()
                    .filter(st -> st.getDataFineStato() == null)
                    .findFirst()
                    .orElse(null);
        } else {
            this.statoCorrente = null;
        }
    }

    private void loadCompratori() {
        compratori = new ArrayList<>();

        try {
            if (getUserDto().isAdmin()) {
                compratori = promoService.findAllBuyers();
            } else {
                List<String> codiciGruppo = getUserDto().getGruppi();

                if (codiciGruppo != null && !codiciGruppo.isEmpty()) {
                    List<String> codiciCompratore =
                            muiService.findAllCodiciCompratoreByCodiciGruppo(codiciGruppo);

                    if (codiciCompratore != null && !codiciCompratore.isEmpty()) {
                        compratori = promoService.findAllBuyersByCodes(
                                codiciCompratore.stream().distinct().collect(Collectors.toList())
                        );
                    }
                }
            }

            if (compratori == null || compratori.isEmpty()) {
                log.warn("Nessun compratore trovato per l'utente " + getUserDto().getUsermame());
                idCompratoreSelezionato = null;
            } else if (idCompratoreSelezionato == null) {
                idCompratoreSelezionato = compratori.get(0).getId();
            }

        } catch (Exception e) {
            log.error("Errore nel caricamento dei compratori", e);
            compratori = new ArrayList<>();
            idCompratoreSelezionato = null;
        }
    }

    public void onCompratoreChange() {
        updateInserimentoParametriBean();
    }

    private void updateInserimentoParametriBean() {
        if (inserimentoParametriBackingBean != null) {
            inserimentoParametriBackingBean.setIdCompratoreSelezionato(idCompratoreSelezionato);
        }
    }

    public void createTabs() {
        tabs = new ArrayList<>();

        tabs.add(new TabItem(
                "Inserimento Parametri",
                "tabs/inserimentoParametri.xhtml",
                TabPianificazioneSpecialPromotionEnum.TAB_INSERIMENTO_PARAMETRI
        ));

        tabs.add(new TabItem(
                "Validazione Articolo Venduto",
                "tabs/validazioneArticoloVenduto.xhtml",
                TabPianificazioneSpecialPromotionEnum.TAB_VALIDAZIONE_ARTICOLO_VENDUTO
        ));

        tabs.add(new TabItem(
                "Validazione Articolo Consegnato",
                "tabs/validazioneArticoloConsegnato.xhtml",
                TabPianificazioneSpecialPromotionEnum.TAB_VALIDAZIONE_ARTICOLO_CONSEGNATO
        ));

        tabs.add(new TabItem(
                "Cifra Fissa",
                "tabs/contributiFornitoriCifraFissa.xhtml",
                TabPianificazioneSpecialPromotionEnum.TAB_CONTRIBUTI_FORNITORI_CIFRA_FISSA
        ));

        tabs.add(new TabItem(
                "Report Riassuntivo",
                "tabs/reportRiassuntivo.xhtml",
                TabPianificazioneSpecialPromotionEnum.TAB_REPORT_RIASSUNTIVO
        ));

        tabs.add(new TabItem(
                "Pubblicazione",
                "tabs/pubblicazione.xhtml",
                TabPianificazioneSpecialPromotionEnum.TAB_PUBBLICAZIONE
        ));
    }

    private void applyTabsPosition() {
        renderedTabs.clear();
        renderedTabs.add(TabPianificazioneSpecialPromotionEnum.TAB_INSERIMENTO_PARAMETRI);
        renderedTabs.add(TabPianificazioneSpecialPromotionEnum.TAB_VALIDAZIONE_ARTICOLO_VENDUTO);
        renderedTabs.add(TabPianificazioneSpecialPromotionEnum.TAB_VALIDAZIONE_ARTICOLO_CONSEGNATO);
        renderedTabs.add(TabPianificazioneSpecialPromotionEnum.TAB_CONTRIBUTI_FORNITORI_CIFRA_FISSA);
        renderedTabs.add(TabPianificazioneSpecialPromotionEnum.TAB_REPORT_RIASSUNTIVO);
        renderedTabs.add(TabPianificazioneSpecialPromotionEnum.TAB_PUBBLICAZIONE);

        renderedTabItems = renderedTabs.stream()
                .map(this::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private TabItem findByName(TabPianificazioneSpecialPromotionEnum name) {
        return tabs.stream()
                .filter(t -> t.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTab = tv.getChildren().indexOf(event.getTab());
    }

    @Override
    public void setIdPromozioneCorrente(Object idPromozioneCorrente) {
        if (idPromozioneCorrente != null) {
            this.idPromozioneCorrente = Long.valueOf(idPromozioneCorrente.toString());
            setIdSpecialPromotionCorrente(idPromozioneCorrente);
        } else {
            this.idPromozioneCorrente = null;
            setIdSpecialPromotionCorrente(null);
        }
    }

    @Override
    public void applyRules() {
    }

    @Override
    public void applyDefaultRules() {
    }

    @Override
    public void updateView() {
    }

    private void loadCompratoriDellaPromozione(MuiSpTestataEntity testata) {
        idCompratoriDellaPromozione = new ArrayList<>();

        if (testata == null) {
            return;
        }

        try {
            List<MuiSpContribCompratoreEntity> contrib =
                    muiSpContribCompratoreService.findByPromozioneId(testata.getId());

            if (contrib != null && !contrib.isEmpty()) {
                idCompratoriDellaPromozione = contrib.stream()
                        .map(c -> c.getCompratoreEntity().getId())
                        .distinct()
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            log.error("Errore nel caricamento compratori per promozione " + testata.getId(), e);
            idCompratoriDellaPromozione = new ArrayList<>();
        }
    }

    private void loadReportCompratori() {
        reportCompratori = new LinkedHashMap<Long, String>();
        reportFornitori = new LinkedHashMap<Long, String>();

        idReportCompratoreSelezionato = null;
        idReportFornitoreSelezionato = null;

        if (idPromozioneCorrente == null) {
            return;
        }

        try {
            List<MuiSpParametroEntity> parametri =
                    muiSpParametroService.findByPromozioneId(idPromozioneCorrente);

            if (parametri != null) {
                for (MuiSpParametroEntity parametro : parametri) {
                    if (parametro.getIdCompratore() != null
                            && !reportCompratori.containsKey(parametro.getIdCompratore())) {

                        reportCompratori.put(
                                parametro.getIdCompratore(),
                                buildCompratoreLabel(parametro)
                        );
                    }
                }
            }

            if (!reportCompratori.isEmpty()) {
                idReportCompratoreSelezionato = reportCompratori.keySet().iterator().next();
                loadReportFornitori();
            }

        } catch (Exception e) {
            log.error("Errore caricamento compratori report", e);
            reportCompratori = new LinkedHashMap<Long, String>();
            reportFornitori = new LinkedHashMap<Long, String>();
        }
    }

    public void onReportCompratoreChange() {
        idReportFornitoreSelezionato = null;
        loadReportFornitori();
    }

    private void loadReportFornitori() {
        reportFornitori = new LinkedHashMap<Long, String>();

        if (idPromozioneCorrente == null || idReportCompratoreSelezionato == null) {
            return;
        }

        try {
            List<MuiSpParametroEntity> parametri =
                    muiSpParametroService.findByPromozioneId(idPromozioneCorrente);

            if (parametri != null) {
                for (MuiSpParametroEntity parametro : parametri) {
                    if (idReportCompratoreSelezionato.equals(parametro.getIdCompratore())
                            && parametro.getIdFornitore() != null
                            && !reportFornitori.containsKey(parametro.getIdFornitore())) {

                        reportFornitori.put(
                                parametro.getIdFornitore(),
                                buildFornitoreLabel(parametro)
                        );
                    }
                }
            }

        } catch (Exception e) {
            log.error("Errore caricamento fornitori report", e);
            reportFornitori = new LinkedHashMap<Long, String>();
        }
    }

    private String buildCompratoreLabel(MuiSpParametroEntity parametro) {
        String codice = parametro.getCodiceCompratore() != null ? parametro.getCodiceCompratore().trim() : "";
        String descrizione = parametro.getDescrizioneCompratore() != null ? parametro.getDescrizioneCompratore().trim() : "";

        descrizione = cleanReportLabel(descrizione, codice);

        if (!codice.isEmpty() && !descrizione.isEmpty()) {
            return codice + " - " + descrizione;
        }

        if (!descrizione.isEmpty()) {
            return descrizione;
        }

        return codice;
    }
    private String buildFornitoreLabel(MuiSpParametroEntity parametro) {

        String codice = parametro.getCodiceFornitore() != null
                ? parametro.getCodiceFornitore().trim()
                : "";

        String descrizione = parametro.getDescrizioneFornitore() != null
                ? parametro.getDescrizioneFornitore().trim()
                : "";

        descrizione = cleanReportLabel(descrizione, codice);

        if (!codice.isEmpty() && !descrizione.isEmpty()) {
            return codice + " - " + descrizione;
        }

        if (!descrizione.isEmpty()) {
            return descrizione;
        }

        return codice;
    }

    private String cleanReportLabel(String value, String codice) {
        if (value == null) {
            return "";
        }

        String cleaned = value.trim();

        cleaned = cleaned.replaceAll("\\s*\\[[^\\]]*\\]\\s*", "").trim();

        if (codice != null && !codice.trim().isEmpty()) {
            String codiceClean = codice.trim();

            if (cleaned.startsWith(codiceClean + " - ")) {
                cleaned = cleaned.substring((codiceClean + " - ").length()).trim();
            }

            if (cleaned.startsWith(codiceClean)) {
                cleaned = cleaned.substring(codiceClean.length()).trim();
            }
        }

        return cleaned;
    }

    public List<SelectItem> getReportCompratoriSelectItems() {
        List<SelectItem> items = new ArrayList<SelectItem>();

        for (Map.Entry<Long, String> entry : reportCompratori.entrySet()) {
            items.add(new SelectItem(entry.getKey(), entry.getValue()));
        }

        return items;
    }

    public List<SelectItem> getReportFornitoriSelectItems() {
        List<SelectItem> items = new ArrayList<SelectItem>();

        for (Map.Entry<Long, String> entry : reportFornitori.entrySet()) {
            items.add(new SelectItem(entry.getKey(), entry.getValue()));
        }

        return items;
    }


    public class TabItem {

        @Getter
        String title;

        @Getter
        String content;

        @Getter
        TabPianificazioneSpecialPromotionEnum name;

        public TabItem(@NonNull String title,
                       @NonNull String content,
                       @NonNull TabPianificazioneSpecialPromotionEnum name) {
            this.title = title;
            this.content = content;
            this.name = name;
        }
    }
}
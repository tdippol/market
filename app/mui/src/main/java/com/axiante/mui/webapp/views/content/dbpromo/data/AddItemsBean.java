package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.content.dbpromo.data.pojo.FilterSelectionPojo;
import com.axiante.mui.webapp.views.content.dbpromo.data.pojo.SingleValueFilterSelectionPojo;
import com.axiante.mui.webapp.views.content.dbpromo.dtos.ItemDto;
import com.axiante.mui.webapp.webservice.dto.PianoMediaDettaglioArticoliDto;
import com.axiante.mui.webapp.webservice.util.PianoMediaPianificazioneUtil;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class AddItemsBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = -7035384231654608293L;

    @Setter
    private UserDTO user;

    @Setter
    private PianoMediaEntity pianoMedia;

    @Inject
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Inject
    private Instance<PianoMediaPianificazioneUtil> pianoMediaPianificazioneUtilInstance;

    @Getter
    private String currentPromoRif;

    @Getter
    @Setter
    private boolean addItemsBtnDisabled = true;

    @Getter
    @Setter
    private List<PianoMediaPromoDbpromoEntity> promoAvailable;

    @Getter
    private Set<ItemDto> selectedItems;

    @Getter
    @Setter
    private List<FilterSelectionPojo> subGrmAvailable;

    @Getter
    @Setter
    private String subGrm;

    @Getter
    @Setter
    private List<FilterSelectionPojo> grmAvailable;

    @Getter
    @Setter
    private String grm;

    @Getter
    @Setter
    private List<FilterSelectionPojo> categorieAvailable;

    @Getter
    @Setter
    private String categoria;

    @Getter
    @Setter
    private List<FilterSelectionPojo> repartiAvailable;

    @Getter
    @Setter
    private String reparto;

    @Getter
    @Setter
    private List<FilterSelectionPojo> compratoriAvailable;

    @Getter
    @Setter
    private String buyer;

    @Getter
    @Setter
    private Date dataInizio;

    @Getter
    @Setter
    private Date dataFine;

    @Getter
    @Setter
    private String volantino;


    @Getter
    @Setter
    private String zona;
    @Getter
    @Setter
    private List<FilterSelectionPojo> zoneAvailable;


    private PianoMediaPromoDbpromoEntity currentPromo;
    private List<PianoMediaDettaglioDTO> availableItems;

    public void loadPromoAvailable() {
        if (pianoMedia != null) {
            try {
                List<String> promoRif = new ArrayList<>();
                promoRif.add(pianoMedia.getPromoMaster());
                if (pianoMedia.getPromoSecondaryA() != null) {
                    promoRif.add(pianoMedia.getPromoSecondaryA());
                }
                if (pianoMedia.getPromoSecondaryB() != null) {
                    promoRif.add(pianoMedia.getPromoSecondaryB());
                }
                if (pianoMedia.getPromoSecondaryC() != null) {
                    promoRif.add(pianoMedia.getPromoSecondaryC());
                }
                promoAvailable = pianoMediaPromoServiceInstance.get().findAllByCodiciPromo(promoRif);
                autoselectPromoMaster(pianoMedia.getPromoMaster());
            } catch (Exception ex) {
                log.error("Error loading available promo riferimento", ex);
                promoAvailable = new ArrayList<>();
            }
        } else {
            if (promoAvailable == null) {
                promoAvailable = new ArrayList<>();
            } else {
                promoAvailable.clear();
            }
        }
    }

    public void loadAllFilterOptions() {
        if (pianoMedia != null) {
            loadAvailableItems(pianoMedia);
            loadSubGrmAvailable(pianoMedia);
            loadGrmAvailable(pianoMedia);
            loadCategorieAvailable(pianoMedia);
            loadRepartiAvailable(pianoMedia);
            loadCompratoriAvailable(pianoMedia);
            loadZoneAvailable(pianoMedia);
        }
    }

    public void loadSubGrmAvailable(PianoMediaEntity pianoMedia) {
        try {
            // If not already loaded, try again
            if (availableItems == null || availableItems.isEmpty()) {
                loadAvailableItems(pianoMedia);
            }
            subGrmAvailable = availableItems.stream()
                    .filter(distinctByKey(PianoMediaDettaglioDTO::getCodiceSubGrm))
                    .map(d -> new FilterSelectionPojo(d.getCodiceSubGrm(), d.getDescSubGrm()))
                    .sorted(Comparator.comparing(FilterSelectionPojo::getKey))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error loading filter for 'SubGrm'", ex);
        }
    }

    public void loadGrmAvailable(PianoMediaEntity pianoMedia) {
        try {
            // If not already loaded, try again
            if (availableItems == null || availableItems.isEmpty()) {
                loadAvailableItems(pianoMedia);
            }
            grmAvailable = availableItems.stream()
                    .filter(distinctByKey(PianoMediaDettaglioDTO::getCodiceGrm))
                    .map(d -> new FilterSelectionPojo(d.getCodiceGrm(), d.getDescGrm()))
                    .sorted(Comparator.comparing(FilterSelectionPojo::getKey))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error loading filter for 'Grm'", ex);
        }
    }

    public void loadCategorieAvailable(PianoMediaEntity pianoMedia) {
        try {
            // If not already loaded, try again
            if (availableItems == null || availableItems.isEmpty()) {
                loadAvailableItems(pianoMedia);
            }
            categorieAvailable = availableItems.stream()
                    .filter(distinctByKey(PianoMediaDettaglioDTO::getCodiceCategoria))
                    .map(d -> new FilterSelectionPojo(d.getCodiceCategoria(), d.getDescCategoria()))
                    .sorted(Comparator.comparing(FilterSelectionPojo::getKey))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error loading filter for 'Categoria'", ex);
        }
    }

    public void loadRepartiAvailable(PianoMediaEntity pianoMedia) {
        try {
            // If not already loaded, try again
            if (availableItems == null || availableItems.isEmpty()) {
                loadAvailableItems(pianoMedia);
            }
            repartiAvailable = availableItems.stream()
                    .filter(distinctByKey(PianoMediaDettaglioDTO::getCodiceReparto))
                    .map(d -> new FilterSelectionPojo(d.getCodiceReparto(), d.getDescReparto()))
                    .sorted(Comparator.comparing(FilterSelectionPojo::getKey))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error loading filter for 'Reparto'", ex);
        }
    }

    public void loadCompratoriAvailable(PianoMediaEntity pianoMedia) {
        try {
            // If not already loaded, try again
            if (availableItems == null || availableItems.isEmpty()) {
                loadAvailableItems(pianoMedia);
            }
            compratoriAvailable = availableItems.stream()
                    .filter(distinctByKey(PianoMediaDettaglioDTO::getCodiceCompratore))
                    .map(d -> new FilterSelectionPojo(d.getCodiceCompratore(), d.getDescCompratore()))
                    .sorted(Comparator.comparing(FilterSelectionPojo::getKey))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error loading filter for 'Compratore'", ex);
        }
    }

    public void loadZoneAvailable(PianoMediaEntity pianoMedia) {
        try {
            // If not already loaded, try again
            if (availableItems == null || availableItems.isEmpty()) {
                loadAvailableItems(pianoMedia);
            }
            zoneAvailable = availableItems.stream()
                    .map(PianoMediaDettaglioDTO::getZone)
                    .filter(Objects::nonNull)
                    .filter(zone -> !zone.isEmpty())
                    .flatMap(Collection::stream)
                    .distinct()
                    .map(SingleValueFilterSelectionPojo::new)
                    .sorted(Comparator.comparing(FilterSelectionPojo::getKey))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error loading filter for 'Zone'", ex);
        }
    }

    private void loadAvailableItems(@NonNull PianoMediaEntity pianoMedia) {
        if (currentPromoRif != null && user != null) {
            if (availableItems == null) {
                availableItems = new ArrayList<>();
            } else {
                availableItems.clear();
            }
            final PianoMediaDettaglioArticoliDto itemsDto = pianoMediaPianificazioneUtilInstance.get()
                    .getAvailableItems(pianoMedia, currentPromoRif, user);
            if (!itemsDto.isError()) {
                availableItems.addAll(itemsDto.getDettagli());
            } else {
                log.error("Error getting available items");
            }
        }
    }

    public void selectedItems() {
        final String selectionJsonArray = getRequestParameterMap().get("itemSelected");
        if (StringUtils.isBlank(selectionJsonArray)) {
            log.error("Missing request params 'itemSelected'");
            return;
        }
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(selectionJsonArray);
            if (jsonNode != null) {
                clearSelectedItems();
                jsonNode.forEach(this::selectSingleItem);
                setAddItemsBtnDisabled(selectedItems.isEmpty());
            } else {
                setAddItemsBtnDisabled(true);
            }
        } catch (Exception ex) {
            log.error("Error reading JSON items selected", ex);
            setAddItemsBtnDisabled(true);
        }
    }

    public void clearSelectedItems() {
        if (selectedItems == null) {
            selectedItems = new HashSet<>();
        } else {
            selectedItems.clear();
        }
    }

    public void updateItems() {
        final List<String> filters = new ArrayList<>();
        if (!StringUtils.isBlank(subGrm)) {
            filters.add(String.format("subGrm=%s", subGrm.trim()));
        }
        if (!StringUtils.isBlank(grm)) {
            filters.add(String.format("grm=%s", grm.trim()));
        }
        if (!StringUtils.isBlank(categoria)) {
            filters.add(String.format("categoria=%s", categoria.trim()));
        }
        if (!StringUtils.isBlank(reparto)) {
            filters.add(String.format("reparto=%s", reparto.trim()));
        }
        if (!StringUtils.isBlank(buyer)) {
            filters.add(String.format("buyer=%s", buyer.trim()));
        }
        if (dataInizio != null) {
            filters.add(String.format("dataInizio=%s", parseDate(dataInizio)));
        }
        if (dataFine != null) {
            filters.add(String.format("dataFine=%s", parseDate(dataFine)));
        }
        if (!StringUtils.isBlank(volantino)) {
            filters.add(String.format("volantino=%s", volantino));
        }
        if ( !StringUtils.isBlank(zona)){
            filters.add(String.format("zona=%s", zona));
        }
        executeScript(String.format("loadGridFiltered('%s')", String.join("&", filters)));
    }

    private String parseDate(@NonNull Date date) {
        return new SimpleDateFormat(DateTimeUtils.ITALIAN_DATE_PATTERN)
                .format(new DateTimeUtils().getDateWithoutTime(date));
    }

    private void selectSingleItem(JsonNode jsonNode) {
        final String codiceItem = jsonNode.get("codiceItem") != null
                ? jsonNode.get("codiceItem").asText()
                : null;
        final String codicePromo = jsonNode.get("codicePromo") != null
                ? jsonNode.get("codicePromo").asText()
                : null;
        final String tipoItem = jsonNode.get("tipoItem") != null
                ? jsonNode.get("tipoItem").asText()
                : null;
        if (!StringUtils.isBlank(codiceItem) && !StringUtils.isBlank(codicePromo) && !StringUtils.isBlank(tipoItem)) {
            selectedItems.add(new ItemDto(codiceItem, codicePromo, tipoItem));
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Errore", "Errore selezione articoli, contattare l'assistenza"));
        }
    }

    public void resetDialog() {
        clearSelectedItems();
        setAddItemsBtnDisabled(true);
        resetFilters();
    }

    public void setCurrentPromoRif(String currentPromoRif) {
        this.currentPromoRif = currentPromoRif;
        setAddItemsBtnDisabled(true);
        resetFilters();
        if (this.currentPromoRif != null) {
            this.currentPromo = promoAvailable.stream()
                    .filter(p -> currentPromoRif.equals(p.getCodicePromozione()))
                    .findFirst()
                    .orElse(null);
        } else {
            this.currentPromo = null;
        }
        if (this.currentPromo != null) {
            loadAllFilterOptions();
        }
    }

    private void autoselectPromoMaster(@NonNull String promoMaster) {
        this.currentPromo = promoAvailable.stream()
                .filter(p -> promoMaster.equals(p.getCodicePromozione()))
                .findFirst()
                .orElse(null);
        setCurrentPromoRif(this.currentPromo != null ? this.currentPromo.getCodicePromozione() : null);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private void resetFilters() {
        subGrm = null;
        grm = null;
        categoria = null;
        reparto = null;
        buyer = null;
        dataInizio = null;
        dataFine = null;
        volantino = null;
    }

    public Date getMinDate() {
        if (currentPromo != null) {
            return currentPromo.getDataInizio();
        }
        return null;
    }

    public Date getMaxDate() {
        if (currentPromo != null) {
            return currentPromo.getDataFine();
        }
        return null;
    }
}

package com.axiante.mui.webapp.utils;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneDefaultEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneDefaultService;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.content.dbpromo.RifatturazioneInitialLoad;
import com.axiante.mui.webapp.views.content.dbpromo.dtos.CompratoreFornitoreTipologiaDTO;
import com.axiante.mui.webapp.webservice.util.AbstractRowDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Dependent
@Slf4j
public class RifatturazioneUtil extends AbstractRowDataUtil {
    public static final String FLAG_NON_RIFATTURABILE = "NON RIFATTURABILE";

    @Inject
    private Instance<MuiService> muiServiceInstance;

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<PromoFatturazioneService> promoFatturazioneServiceInstance;

    @Inject
    private Instance<PromoFatturazioneDefaultService> promoFattDefaultServiceInstance;

    @Inject
    private Instance<ApplicationProperties> applicationPropertiesInstance;

    @Inject
    private Instance<CanalePromozioneService> canalePromozioneServiceInstance;

    public boolean isRightChannelForNumeroContatti(@NonNull Long idPromozioneSelected) {
        PromozioneTestataEntity promozione = promoServiceInstance.get().findById(idPromozioneSelected);
        if (promozione == null) {
            log.error(String.format("Promozione not found for id '%d'", idPromozioneSelected));
            return false;
        }
        return canalePromozioneServiceInstance.get()
                .countByIdWithTipologiaInitialLoad(promozione.getCanalePromozioneEntity().getId()) == 0;
    }

    public List<CompratoreEntity> compratoriWritableByGruppi(List<String> codiciGruppi) {
        List<String> codiciCompratori = muiServiceInstance.get()
                .findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(codiciGruppi, PianificazioneSecurityEnum.WRITE);
        if (codiciCompratori.isEmpty()) {
            return Collections.emptyList();
        }
        return promoServiceInstance.get().findAllBuyersByCodes(codiciCompratori);
    }

    public List<PromoFatturazioneEntity> fatturazioniWithWritableCompratoriByCodiciGruppo(List<String> codiciGruppi) {
        List<Long> compratoriIds = compratoriWritableByGruppi(codiciGruppi).stream()
                .map(CompratoreEntity::getId).collect(Collectors.toList());
        return promoFatturazioneServiceInstance.get().findAllByIdsCompratori(compratoriIds);
    }

    public List<FornitoreEntity> fornitoriByCodiceCompratore(String codiceCompratore) {
        return promoServiceInstance.get()
                .findAllFornitoriAttiviByCodiceCompratore(codiceCompratore).stream()
                .sorted(Comparator.comparing(FornitoreEntity::getCodiceFornitore))
                .collect(Collectors.toList());
    }

    public Map<Long, List<FornitoreEntity>> fornitoriGroupedByIdCompratore(List<CompratoreEntity> compratori) {
        return compratori.stream()
                .filter(c -> c != null && c.getId() != null)
                .collect(Collectors.toMap(
                        CompratoreEntity::getId,
                        c -> {
                            String codice = c.getCodiceCompratore();
                            if (codice == null) {
                                return Collections.emptyList();
                            }
                            List<FornitoreEntity> fornitori = fornitoriByCodiceCompratore(codice);
                            return fornitori != null ? fornitori : Collections.emptyList();
                        })
                );
    }

    public List<CompratoreFornitoreTipologiaDTO> createMatrixForDefaults(List<CompratoreEntity> compratori,
                                                                         Map<Long, List<FornitoreEntity>> fornitoriByCompratoreId,
                                                                         List<TipoPromoRifatturazioneEntity> tipologie) {
        return compratori.stream()
                .filter(c -> c != null && c.getId() != null)
                .flatMap(c -> fornitoriByCompratoreId.getOrDefault(c.getId(), Collections.emptyList()).stream()
                        .flatMap(f -> tipologie.stream()
                                .map(t -> new CompratoreFornitoreTipologiaDTO(c, f, t))))
                .collect(Collectors.toList());
    }

    public List<CompratoreFornitoreTipologiaDTO> createMatrixForMissingDefaults(List<String> codiciGruppi,
                                                                                List<TipoPromoRifatturazioneEntity> tipologie) {
        List<CompratoreEntity> compratori = compratoriWritableByGruppi(codiciGruppi);
        List<Long> idsCompratori = compratori.stream().map(CompratoreEntity::getId).collect(Collectors.toList());
        Map<Long, List<FornitoreEntity>> fornitoriByCompratoreId = fornitoriGroupedByIdCompratore(compratori);
        List<PromoFatturazioneDefaultEntity> defaults = promoFattDefaultServiceInstance.get()
                .findAllByIdsCompratori(idsCompratori);
        List<CompratoreFornitoreTipologiaDTO> matrix = createMatrixForDefaults(compratori, fornitoriByCompratoreId, tipologie);
        updateMatrix(matrix, defaults);
        return matrix.stream().filter(m -> !m.isDone()).collect(Collectors.toList());
    }

    public void updateMatrix(List<CompratoreFornitoreTipologiaDTO> matrix, List<PromoFatturazioneDefaultEntity> defaults) {
        defaults.forEach(pfd -> updateMatrixRow(matrix, pfd, true));
    }

    public void updateMatrixRow(List<CompratoreFornitoreTipologiaDTO> matrix, PromoFatturazioneDefaultEntity pfd, boolean done) {
        matrix.stream()
                .filter(filterByDto(pfd))
                .findFirst()
                .ifPresent(row -> row.setDone(done));
    }

    private Predicate<CompratoreFornitoreTipologiaDTO> filterByDto(PromoFatturazioneDefaultEntity pfd) {
        return c -> c.getCompratore().getId().equals(pfd.getCompratore().getId())
                && c.getFornitore().getId().equals(pfd.getFornitore().getId())
                && c.getTipologia().getCodice().equals(pfd.getTipoPromozione().getCodice());
    }

    public String createFatturazioniDefaultsRowData(@NonNull List<PromoFatturazioneDefaultEntity> fatturazioniDefaults) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            fatturazioniDefaults.stream()
                    .sorted(Comparator.comparing(f -> f.getCompratore().getCodiceCompratore()))
                    .forEach(fatturazione -> arrayNode.add(createFatturazioneDefaultRowNode(fatturazione)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'PromoFatturazioneDefaultEntity'", ex);
            return "";
        }
    }

    public String createFatturazioniDefaultsMatrixRowData(@NonNull List<CompratoreFornitoreTipologiaDTO> matrix) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            matrix.stream()
                    .sorted(Comparator
                            .comparing((CompratoreFornitoreTipologiaDTO dto) -> dto.getCompratore().getCodiceCompratore())
                            .thenComparing(dto -> dto.getFornitore().getCodiceFornitore())
                            .thenComparing(dto -> dto.getTipologia().getDescrizione()))
                    .forEach(m -> arrayNode.add(createFatturazioneDefaultMatrixRowNode(m)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'CompratoreFornitoreTipologiaDTO'", ex);
            return "";
        }
    }

    public String createFatturazioniRowData(@NonNull List<PromoFatturazioneEntity> fatturazioni, @NonNull Long idCanale,
                                            @NonNull Date dataFinePromo, String flagRifatturazioneAttivita) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            Integer gg = applicationPropertiesInstance.get().getProperty(ApplicationProperties.GIORNI_CUTOFF_FATT_SINGOLA_ATTIVITA,
                    ApplicationProperties.DEFAULT_GIORNI_CUTOFF_FATT_SINGOLA_ATTIVITA);
            DateTimeUtils dtUtils = new DateTimeUtils();
            boolean editable = !FLAG_NON_RIFATTURABILE.equalsIgnoreCase(flagRifatturazioneAttivita) &&
                    dtUtils.isBefore(new Date(), DateTimeUtils.addDaysToDate(dataFinePromo, gg), true);
            fatturazioni.stream()
                    .sorted(Comparator.comparing(f -> f.getFornitore().getCodiceFornitore()))
                    .forEach(fatturazione -> arrayNode.add(createFatturazioneRowNode(fatturazione, editable, idCanale)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (Exception ex) {
            log.error("Error creating JSON rowData for available 'PromoFatturazioneEntity'", ex);
            return "";
        }
    }

    private ObjectNode createFatturazioneDefaultRowNode(PromoFatturazioneDefaultEntity fatturazione) {
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellTypeString)
                    .value(String.valueOf(fatturazione.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellTypeString)
                    .value(fatturazione.getCompratore().getFullDescription()).build();
            map.put("compratore", cell);

            cell = DBPromoAgCell.builder().name("Fornitore").editable(false).type(cellTypeString)
                    .value(fatturazione.getFornitore().getFullDescription()).build();
            map.put("fornitore", cell);

            cell = DBPromoAgCell.builder().name("Tipo Promozione").editable(false).type(cellTypeString)
                    .value(fatturazione.getTipoPromozione().getDescrizione()).build();
            map.put("tipoPromozione", cell);

            cell = DBPromoAgCell.builder().name("Rifatturazione").editable(false).type(cellTypeString)
                    .value(fatturazione.getRifattura()).build();
            map.put("rifatturazione", cell);

            cell = DBPromoAgCell.builder().name("Variabile Fisso").editable(false).type(cellTypeString)
                    .value(nullableValue(fatturazione.getVarFiss()))
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .build();
            map.put("variabileFisso", cell);

            cell = DBPromoAgCell.builder().name("Variabile Pct").editable(false).type(cellTypeString)
                    .value(nullableValue(fatturazione.getVarPerc())).build();
            map.put("variabilePct", cell);

            cell = DBPromoAgCell.builder().name("Abbattimento").editable(false).type(cellTypeString)
                    .value(nullableValue(fatturazione.getAbbattimento())).build();
            map.put("abbattimento", cell);

            cell = DBPromoAgCell.builder().name("NC / ND").editable(false).type(cellTypeString)
                    .value(nullableValue(fatturazione.getNcNd())).build();
            map.put("ncNd", cell);

            cell = DBPromoAgCell.builder().name("Cap Min").editable(false).type(cellTypeString)
                    .value(nullableValue(fatturazione.getCapMin()))
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .build();
            map.put("capMin", cell);

            cell = DBPromoAgCell.builder().name("Cap Max").editable(false).type(cellTypeString)
                    .value(nullableValue(fatturazione.getCapMax()))
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .build();
            map.put("capMax", cell);

            cell = DBPromoAgCell.builder().name("Sovrapposizioni").editable(false).type(cellTypeString)
                    .value(nullableValue(fatturazione.getSovrapposizioni())).build();
            map.put("sovrapposizioni", cell);

            cell = DBPromoAgCell.builder().name("EditEnabled").editable(false).type(cellTypeString)
                    .value(String.valueOf(true)).build();
            map.put("editEnabled", cell);

            cell = DBPromoAgCell.builder().name("DeleteEnabled").editable(false).type(cellTypeString)
                    .value(String.valueOf(true)).build();
            map.put("deleteEnabled", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating JSON rowData for fatturazione with id '%d'",
                    fatturazione.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createFatturazioneDefaultMatrixRowNode(CompratoreFornitoreTipologiaDTO dto) {
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Compratore").editable(false).type(cellTypeString)
                    .value(dto.getCompratore().getFullDescription()).build();
            map.put("compratore", cell);

            cell = DBPromoAgCell.builder().name("Fornitore").editable(false).type(cellTypeString)
                    .value(dto.getFornitore().getFullDescription()).build();
            map.put("fornitore", cell);

            cell = DBPromoAgCell.builder().name("Tipo Promozione").editable(false).type(cellTypeString)
                    .value(dto.getTipologia().getDescrizione()).build();
            map.put("tipoPromozione", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating JSON rowData for DTO with compratore '%s', fornitore '%s' and tipologia '%s'",
                    dto.getCompratore().getCodiceCompratore(), dto.getFornitore().getCodiceFornitore(), dto.getTipologia().getDescrizione()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private ObjectNode createFatturazioneRowNode(PromoFatturazioneEntity fatturazione, boolean editable, Long idCanale) {
        boolean withoutInitialLoad = canalePromozioneServiceInstance.get()
                .countByIdWithTipologiaInitialLoad(idCanale) == 0;
        final String cellTypeString = DBPromoCellTypeEnum.STRING.getType();
        final String cellTypePicklist = DBPromoCellTypeEnum.PICKLIST.getType();
        final Map<String, DBPromoAgCell> map = new HashMap<>();
        final String[] yesNoChoices = Arrays.stream(RifatturazioneInitialLoad.YesNoChoice.values())
                .map(RifatturazioneInitialLoad.YesNoChoice::getLabel).toArray(String[]::new);
        final String[] ncNdChoices = Arrays.stream(RifatturazioneInitialLoad.NcNdChoice.values())
                .map(RifatturazioneInitialLoad.NcNdChoice::getLabel).toArray(String[]::new);
        final String[] variabilePctChoices = Stream.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
                .map(String::valueOf).toArray(String[]::new);
        // when RIFATTURA=='NO', all other fields are not editable
        boolean rifatturaNo = RifatturazioneInitialLoad.YesNoChoice.NO.getLabel().equalsIgnoreCase(fatturazione.getRifattura());
        boolean editableVariabileFisso = editable && !rifatturaNo;
        boolean editableVariabilePct = editable && !rifatturaNo;
        boolean editableAbbattimento = editable && !rifatturaNo;
        boolean editableNcNd = editable && !rifatturaNo;
        boolean editableCapMin = editable && !rifatturaNo;
        boolean editableCapMax = editable && !rifatturaNo;
        boolean editableSovrapposizioni = editable && !rifatturaNo;
        boolean editableValoreContatto = editable && withoutInitialLoad && !rifatturaNo;
        boolean editableFeeIngresso = editable && withoutInitialLoad && !rifatturaNo;
        boolean editableDataLiquidazione = editable && withoutInitialLoad && !rifatturaNo;
        // when VARIABILE_FISO != null, CAP_MIN, CAP_MAX are not editable; ABBATTIMENTO only if NC_ND == null
        if (fatturazione.getVarFiss() != null) {
            editableCapMin = false;
            editableCapMax = false;
            editableAbbattimento = fatturazione.getNcNd() != null;
        }

        try {
            DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(cellTypeString)
                    .value(String.valueOf(fatturazione.getId())).build();
            map.put("id", cell);

            cell = DBPromoAgCell.builder().name("Fornitore").editable(false).type(cellTypeString)
                    .value(fatturazione.getFornitore().getFullDescription()).build();
            map.put("fornitore", cell);

            cell = DBPromoAgCell.builder().name("Rifattura").editable(editable).type(cellTypePicklist)
                    .value(fatturazione.getRifattura()).picklistValues(yesNoChoices).build();
            map.put("rifattura", cell);

            if (withoutInitialLoad) {
                cell = DBPromoAgCell.builder().name("Valore Contatto").editable(editableValoreContatto).type(cellTypeString)
                        .value(nullableValue(fatturazione.getValoreContatto()))
                        .dataTypeParams(DataTypeParams.builder().precision(0).build())
                        .build();
                map.put("valoreContatto", cell);

                cell = DBPromoAgCell.builder().name("Costo Contatti").editable(false).type(cellTypeString)
                        .value(nullableValue(fatturazione.getImportoTotale()))
                        .dataTypeParams(DataTypeParams.builder().precision(0).build())
                        .build();
                map.put("costoContatti", cell);

                cell = DBPromoAgCell.builder().name("Fee Ingresso").editable(editableFeeIngresso).type(cellTypeString)
                        .value(nullableValue(fatturazione.getFeeIngresso()))
                        .dataTypeParams(DataTypeParams.builder().precision(0).build())
                        .build();
                map.put("feeIngresso", cell);

                cell = DBPromoAgCell.builder().name("Data Liquidazione").editable(editableDataLiquidazione).type(cellTypePicklist)
                        .value(nullableDate(fatturazione.getDataLiquidazione()))
                        .picklistValues(prepareDateLiquidazioneValues(fatturazione.getPromozione()))
                        .build();
                map.put("dataLiquidazione", cell);
            }

            cell = DBPromoAgCell.builder().name("Variabile Fisso").editable(editableVariabileFisso).type(cellTypeString)
                    .value(nullableValue(fatturazione.getVarFiss()))
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .build();
            map.put("variabileFisso", cell);

            cell = DBPromoAgCell.builder().name("Variabile %").editable(editableVariabilePct).type(cellTypePicklist)
                    .value(nullableValue(fatturazione.getVarPerc())).picklistValues(variabilePctChoices).build();
            map.put("variabilePct", cell);

            cell = DBPromoAgCell.builder().name("Abbattimento").editable(editableAbbattimento).type(cellTypePicklist)
                    .value(nullableValue(fatturazione.getAbbattimento())).picklistValues(yesNoChoices).build();
            map.put("abbattimento", cell);

            cell = DBPromoAgCell.builder().name("NC / ND").editable(editableNcNd).type(cellTypePicklist)
                    .value(nullableValue(fatturazione.getNcNd())).picklistValues(ncNdChoices).build();
            map.put("ncNd", cell);

            cell = DBPromoAgCell.builder().name("Cap MIN").editable(editableCapMin).type(cellTypeString)
                    .value(nullableValue(fatturazione.getCapMin()))
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .build();
            map.put("capMin", cell);

            cell = DBPromoAgCell.builder().name("Cap MAX").editable(editableCapMax).type(cellTypeString)
                    .value(nullableValue(fatturazione.getCapMax()))
                    .dataTypeParams(DataTypeParams.builder().precision(0).build())
                    .build();
            map.put("capMax", cell);

            cell = DBPromoAgCell.builder().name("Sovrapposizioni").editable(editableSovrapposizioni).type(cellTypePicklist)
                    .value(nullableValue(fatturazione.getSovrapposizioni())).picklistValues(yesNoChoices).build();
            map.put("sovrapposizioni", cell);
        } catch (Exception ex) {
            log.error(String.format("Error creating JSON rowData for fatturazione with id '%d'",
                    fatturazione.getId()), ex);
        }
        return JsonUtils.getMapper().valueToTree(map);
    }

    private String nullableDate(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtils.ESSELUNGA_PATTERN, DateTimeUtils.ESSELUNGA_LOCALE);
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
    }

    private String[] prepareDateLiquidazioneValues(@NonNull PromozioneTestataEntity promozione) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtils.ESSELUNGA_PATTERN, DateTimeUtils.ESSELUNGA_LOCALE);
        if (promozione.getDataInizio() == null || promozione.getDataFine() == null) {
            log.error(String.format("Start date and end date not setted for promozione with id '%d'", promozione.getId()));
            return new String[0];
        }
        List<String> wednesdays = new ArrayList<>();
        LocalDate dataInizio = promozione.getDataInizio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFine = promozione.getDataFine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate current = dataInizio.with(DayOfWeek.WEDNESDAY).plusWeeks(1);
        while (!current.isAfter(dataFine)) {
            wednesdays.add(current.format(formatter));
            current = current.plusWeeks(1);
        }
        return wednesdays.toArray(new String[0]);
    }
}

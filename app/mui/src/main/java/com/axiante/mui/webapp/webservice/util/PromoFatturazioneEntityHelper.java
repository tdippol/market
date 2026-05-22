package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneCostiContattoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneCostiContattoService;
import com.axiante.mui.webapp.views.content.dbpromo.RifatturazioneInitialLoad;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

@Dependent
@Slf4j
public class PromoFatturazioneEntityHelper {
    @Inject
    private Instance<PromozioneCostiContattoService> promoCostiContattoServiceInstance;

    @Inject
    private Instance<CanalePromozioneService> canalePromozioneServiceInstance;

    // Managed field
    public static final String RIFATTURA = "RIFATTURA";
    public static final String VARIABILE_FISSO = "VARIABILEFISSO";
    public static final String VARIABILE_PCT = "VARIABILEPCT";
    public static final String ABBATTIMENTO = "ABBATTIMENTO";
    public static final String NC_ND = "NCND";
    public static final String CAP_MIN = "CAPMIN";
    public static final String CAP_MAX = "CAPMAX";
    public static final String SOVRAPPOSIZIONI = "SOVRAPPOSIZIONI";
    public static final String VALORE_CONTATTO = "VALORECONTATTO";
    public static final String FEE_INGRESSO = "FEEINGRESSO";
    public static final String DATA_LIQUIDAZIONE = "DATALIQUIDAZIONE";
    private static final String[] yesNoChoices = Arrays.stream(RifatturazioneInitialLoad.YesNoChoice.values())
            .map(RifatturazioneInitialLoad.YesNoChoice::getLabel).toArray(String[]::new);
    private static final String[] ncNdChoices = Arrays.stream(RifatturazioneInitialLoad.NcNdChoice.values())
            .map(RifatturazioneInitialLoad.NcNdChoice::getLabel).toArray(String[]::new);

    public void invokeSetter(@NonNull PromoFatturazioneEntity entity, @NonNull JsonNode jsonNode) {
        final String fieldName = jsonNode.get("field").asText();
        if (fieldName == null || fieldName.trim().isEmpty() || fieldName.trim().equalsIgnoreCase("null")) {
            throw new IllegalArgumentException("Field is null");
        }
        String value = jsonNode.get("value").asText();
        if (value != null && (value.trim().isEmpty() || value.trim().equalsIgnoreCase("null"))) {
            value = null;
        }
        invokeSetter(entity, fieldName, value);
    }

    public void invokeSetter(@NonNull PromoFatturazioneEntity entity, @NonNull String fieldName, Object value) {
        try {
            switch (fieldName.toUpperCase()) {
                case RIFATTURA:
                    if (value == null) {
                        log.error(String.format("Field '%s' cannot be null", fieldName));
                    } else if (!isValid(value.toString(), yesNoChoices)) {
                        log.error(String.format("Field '%s' not valid: '%s'", fieldName, value));
                    } else {
                        entity.setRifattura(value.toString());
                        if (RifatturazioneInitialLoad.YesNoChoice.NO.getLabel().equalsIgnoreCase(value.toString())) {
                            entity.setVarFiss(null);
                            entity.setVarPerc(null);
                            entity.setAbbattimento(null);
                            entity.setNcNd(null);
                            entity.setCapMin(null);
                            entity.setCapMax(null);
                            entity.setSovrapposizioni(null);
                            entity.setValoreContatto(null);
                            entity.setImportoTotale(null);
                            entity.setFeeIngresso(null);
                        }
                    }
                    break;
                case VARIABILE_FISSO:
                    entity.setVarFiss(nullableBigDecimal(value));
                    if (entity.getVarFiss() != null) {
                        entity.setVarPerc(null);
                        entity.setCapMin(null);
                        entity.setCapMax(null);
                        if (entity.getNcNd() == null) {
                            entity.setAbbattimento(null);
                        }
                    }
                    break;
                case VARIABILE_PCT:
                    entity.setVarPerc(nullableInteger(value));
                    if (entity.getVarPerc() != null) {
                        entity.setVarFiss(null);
                    }
                    break;
                case ABBATTIMENTO:
                    if (value != null && !isValid(value.toString(), yesNoChoices)) {
                        log.error(String.format("Field '%s' not valid: '%s'", fieldName, value));
                    } else {
                        entity.setAbbattimento(nullableString(value));
                    }
                    break;
                case NC_ND:
                    if (value != null && !isValid(value.toString(), ncNdChoices)) {
                        log.error(String.format("Field '%s' not valid: '%s'", fieldName, value));
                    } else {
                        entity.setNcNd(nullableString(value));
                    }
                    break;
                case CAP_MIN:
                    entity.setCapMin(nullableBigDecimal(value));
                    break;
                case CAP_MAX:
                    entity.setCapMax(nullableBigDecimal(value));
                    break;
                case SOVRAPPOSIZIONI:
                    if (value != null && !isValid(value.toString(), yesNoChoices)) {
                        log.error(String.format("Field '%s' not valid: '%s'", fieldName, value));
                    } else {
                        entity.setSovrapposizioni(nullableString(value));
                    }
                    break;
                case VALORE_CONTATTO:
                    entity.setValoreContatto(nullableBigDecimal(value));
                    if (entity.getValoreContatto() != null) {
                        entity.setImportoTotale(calculateImportoTotale(entity.getValoreContatto(), entity.getPromozione()));
                    } else {
                        entity.setImportoTotale(null);
                    }
                    break;
                case FEE_INGRESSO:
                    entity.setFeeIngresso(nullableBigDecimal(value));
                    break;
                case DATA_LIQUIDAZIONE:
                    entity.setDataLiquidazione(nullableDate(value));
                    break;
                default:
                    log.error(String.format("Field '%s' not supported", fieldName));
                    break;
            }
        } catch (Exception ex) {
            log.error(String.format("Error invoking setter for field '%s' with value '%s'", fieldName, value), ex);
        }
    }

    private BigDecimal calculateImportoTotale(@NonNull BigDecimal valoreContatto, PromozioneTestataEntity promozione) {
        // Recupero numeroContatti dalla testata
        if (promozione != null && canalePromozioneServiceInstance.get().countByIdWithTipologiaInitialLoad(promozione.getCanalePromozioneEntity().getId()) > 0) {
            PromozioneCostiContattoEntity promoCostiContatto = promoCostiContattoServiceInstance.get()
                    .findByIdPromozione(promozione.getId());
            if (promoCostiContatto == null || promoCostiContatto.getNumeroContatti() == null) {
                log.error(String.format("Numero contatti not setted for promozione with id '%d'", promozione.getId()));
                return null;
            }
            return valoreContatto.multiply(BigDecimal.valueOf(promoCostiContatto.getNumeroContatti()));
        }
        return null;
    }

    private boolean isValid(String value, String[] values) {
        return Arrays.asList(values).contains(value);
    }

    private BigDecimal nullableBigDecimal(Object value) {
        return value == null ? null : new BigDecimal(value.toString());
    }

    private Integer nullableInteger(Object value) {
        return value == null ? null : Integer.parseInt(value.toString());
    }

    private String nullableString(Object value) {
        return value == null ? null : value.toString();
    }

    private Date nullableDate(Object value) {
        if (value == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtils.ESSELUNGA_PATTERN, DateTimeUtils.ESSELUNGA_LOCALE);
        LocalDate date = LocalDate.parse(value.toString(), formatter);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

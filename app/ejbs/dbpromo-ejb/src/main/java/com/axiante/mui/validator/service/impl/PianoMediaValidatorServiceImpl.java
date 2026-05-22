package com.axiante.mui.validator.service.impl;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.service.CreaPianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.validator.service.PianoMediaValidatorService;
import com.fasterxml.jackson.databind.JsonNode;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class PianoMediaValidatorServiceImpl implements PianoMediaValidatorService {

    @Inject
    private Instance<CreaPianoMediaService> creaPianoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    private final DateTimeUtils dateTimeUtils = new DateTimeUtils();

    private final String[] CAMPI = {"anno", "promoRif", "descrizione", "dataInizio", "dataFine",
            "promoSecA", "promoSecB", "promoSecC", "slotId", "userId"};

    @Override
    public CreaPianoMediaEntity validateCreaPianoMedia(@NonNull String payload) {
        try {
            final JsonNode node = JsonUtils.getMapper().readTree(payload);
            return validateCreaPianoMedia(node);
        } catch (Exception ex) {
            log.error(String.format("Error validating CreaPianoMedia with payload\n%s", payload), ex);
        }
        return null;
    }

    @Override
    public CreaPianoMediaEntity validateCreaPianoMedia(@NonNull JsonNode node) {
        try {
            final String slotId = node.get("slotId") != null ? DbPromoAgCellUtils.getValue(node.get("slotId")) : null;
            final String userId = node.get("userId") != null ? DbPromoAgCellUtils.getValue(node.get("userId")) : null;
            if (StringUtils.isBlank(slotId) || StringUtils.isBlank(userId)) {
                return null;
            }
            CreaPianoMediaEntity entity = creaPianoMediaServiceInstance.get()
                    .findByUserIdAndSlotId(userId, slotId);
            if (entity == null) {
                entity = (CreaPianoMediaEntity) AuditLogFiller.fillAuditLogFields(new CreaPianoMediaEntity(), userId);
                entity.setUserId(userId);
                entity.setSlotId(slotId);
            }
            buildFromNode(entity, node);
            return entity;
        } catch (Exception ex) {
            log.error(String.format("Error validating CreaPianoMedia from node\n%s", node.toString()), ex);
        }
        return null;
    }

    private void buildFromNode(CreaPianoMediaEntity entity, JsonNode node) {
        boolean promoMasterChanged = false;
        for (final String campo : CAMPI) {
            final JsonNode nodo = node.get(campo);
            if (nodo != null) {
                final DBPromoAgCell cell = DbPromoAgCellUtils.decode(nodo);
                String value;
                if (cell != null) {
                    value = DbPromoAgCellUtils.getValue(cell);
                    if (StringUtils.isBlank(value)) {
                        value = null;
                    }
                    switch (campo) {
                        case "userId":
                            entity.setUserId(value);
                            break;
                        case "slotId":
                            entity.setSlotId(value);
                            break;
                        case "anno":
                            if (value != null) {
                                try {
                                    entity.setAnno(Integer.parseInt(value));
                                } catch (NumberFormatException ex) {
                                    log.error(String.format("Error parsing 'anno %s' as a valid integer", value), ex);
                                }
                            } else {
                                entity.setAnno(null);
                            }
                            break;
                        case "promoRif":
                            // Se cambio il valore della promozione master
                            // devo rivalorizzare nella entity CreaPianoMedia descrizione, anno, dataInizio e dataFine
                            // prendendole dalla promozione master
                            // inoltre devo resettare le promo secondarie (A, B, C)
                            // Se ho eliminato la promoMaster, devo resettare promo secondarie, descrizione, date ed anno
                            if ((value != null && !value.equalsIgnoreCase(entity.getPromoMaster()))
                                    || (value == null && entity.getPromoMaster() != null)) {
                                promoMasterChanged = true;
                                if (value != null) {
                                    final PianoMediaPromoDbpromoEntity promoMaster = pianoMediaPromoServiceInstance.get()
                                            .findByCodicePromo(value);
                                    if (promoMaster != null) {
                                        int index = promoMaster.getDescrizioneEstesa().indexOf('+');
                                        entity.setDescrizione(index != -1
                                                ? promoMaster.getDescrizioneEstesa().substring(0, index)
                                                : promoMaster.getDescrizioneEstesa());
                                        entity.setDataInizio(promoMaster.getDataInizio());
                                        entity.setDataFine(promoMaster.getDataFine());
                                        entity.setAnno(promoMaster.getAnno());
                                        entity.setPromoSecondaryA(null);
                                        entity.setPromoSecondaryB(null);
                                        entity.setPromoSecondaryC(null);
                                    }
                                } else {
                                    entity.setDescrizione(null);
                                    entity.setAnno(null);
                                    entity.setDataInizio(null);
                                    entity.setDataFine(null);
                                    entity.setPromoSecondaryA(null);
                                    entity.setPromoSecondaryB(null);
                                    entity.setPromoSecondaryC(null);
                                }
                            }
                            entity.setPromoMaster(value);
                            break;
                        case "descrizione":
                            if (!promoMasterChanged) {
                                entity.setDescrizione(value);
                            }
                            break;
                        case "dataInizio":
                            if (!promoMasterChanged) {
                                entity.setDataInizio(value != null ? dateTimeUtils.excelToDate(value) : null);
                            }
                            break;
                        case "dataFine":
                            if (!promoMasterChanged) {
                                entity.setDataFine(value != null ? dateTimeUtils.excelToDate(value) : null);
                            }
                            break;
                        case "promoSecA":
                            if (!promoMasterChanged) {
                                entity.setPromoSecondaryA(value);
                            }
                            break;
                        case "promoSecB":
                            if (!promoMasterChanged) {
                                entity.setPromoSecondaryB(value);
                            }
                            break;
                        case "promoSecC":
                            if (!promoMasterChanged) {
                                entity.setPromoSecondaryC(value);
                            }
                            break;
                        default:
                            log.warn(String.format("Campo '%s' sconosciuto", campo));
                            break;
                    }
                }
            }
        }
    }
}

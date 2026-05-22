package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiFlagDominioService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneFlagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class PromozioneFlagUtil {

    @Inject
    private Instance<ComboBoxFactory> comboBoxFactoryInstance;

    @Inject
    private Instance<MuiFlagDominioService> flagDominioServiceInstance;

    @Inject
    private Instance<PromozioneFlagService> promoFlagServiceInstance;

    /**
     * Crea rowData flags promozione
     *
     * @param flags    flags promozione
     * @param editable true se le singole righe sono editabili, false altrimenti
     * @return rowData flags promozione come stringa JSON
     */
    public String createFlagRowData(Set<MuiPromozioneFlagEntity> flags, boolean editable) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            flags.stream().sorted(Comparator.comparing(f -> f.getFlag().getDescrizione()))
                    .forEach(f -> arrayNode.add(createFlagRowNode(f, editable)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error getting rowData for 'SchedaPromo - Flag'", ex);
            return "";
        }
    }

    /**
     * Crea singolo rowData flag promozione originale
     *
     * @param flag     flag promozione
     * @param message  messaggio da visualizzare
     * @param editable true se la riga e' editabili, false altrimenti
     * @return singolo rowData flag promozione come stringa JSON
     */
    public String createOriginalRow(MuiPromozioneFlagEntity flag, String message, boolean editable) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        try {
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set("originalRow", createFlagRowNode(flag, editable));
            objectNode.put("message", message);
            return mapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error getting rowData for 'SchedaPromo - Flag'", ex);
            return "";
        }
    }

    /**
     * Crea singolo rowData flag promozione aggiornato
     *
     * @param flag     flag promozione
     * @param editable true se la riga e' editabili, false altrimenti
     * @return singolo rowData flag promozione aggiornato come stringa JSON
     */
    public String createUpdatedRow(MuiPromozioneFlagEntity flag, boolean editable) {
        try {
            return JsonUtils.getMapper().writeValueAsString(createFlagRowNode(flag, editable));
        } catch (JsonProcessingException ex) {
            log.error("Error creating updated json node", ex);
            return "";
        }
    }

    /**
     * Aggiorna il flag della promozione
     *
     * @param promozione    promozione
     * @param promoFlag     flag da aggiornare
     * @param idFlagDominio id del flag di dominio impostato
     * @param user          utente che effettua l'aggiornamento
     * @return true in caso di aggiornamento con successo, false altrimenti
     * @throws Exception se ci sono errore nell'aggiornamento
     */
    public boolean update(PromozioneTestataEntity promozione, MuiPromozioneFlagEntity promoFlag, Long idFlagDominio,
                          String user) throws Exception {
        // Cerco il flag di dominio dal quale prendere il valore
        final MuiFlagDominioEntity flagDominio = flagDominioServiceInstance.get().findById(idFlagDominio);
        if (flagDominio == null) {
            log.error(String.format("Flag dominio con id %d non esiste", idFlagDominio));
            return false;
        }
        if (Boolean.FALSE.equals(flagDominio.getAttivo())) {
            log.error(String.format("Flag dominio con id %d [%s] non e' attivo", idFlagDominio, flagDominio.getDescrizione()));
            return false;
        }
        if (!flagDominio.getFlag().getId().equals(promoFlag.getFlag().getId())) {
            log.error(String.format("Flag dominio %d [%s] non permesso su promozione %d [%s]",
                    idFlagDominio, flagDominio.getDescrizione(), promozione.getId(), promozione.getCodicePromozione()));
            return false;
        }
        // Il flag di dominio esiste a DB, e' attivo, e' permesso, quindi setto il suo valore in PromozioneFlag
        promoFlag.setValore(flagDominio.getValore());
        try {
            promoFlagServiceInstance.get().persistWithAuditLog(promoFlag, user);
            return true;
        } catch (Exception ex) {
            log.error(String.format("Error updating flag on promozione %d", promozione.getId()));
            return false;
        }
    }

    /**
     * Crea singolo nodo JSON
     *
     * @param flag     promozione flag
     * @param editable flag se riga e' editabile
     * @return nodo JSON che mappa l'entity
     */
    private ObjectNode createFlagRowNode(MuiPromozioneFlagEntity flag, boolean editable) {
        List<MuiFlagDominioEntity> activeflags = flagDominioServiceInstance.get().findAllAttiviByFlag(flag.getFlag().getId());
        List<ComboBoxValues> availableFlags = comboBoxFactoryInstance.get().from(activeflags, false, false);
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().name("Id").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.valueOf(flag.getFlag().getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().name("Flag").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(flag.getFlag().getDescrizione()).build();
        map.put("flag", cell);

        String value = flag.getValore() != null
                ? availableFlags.stream().filter(f -> f.getLabel().equals(flag.getValore()))
                .findFirst().map(ComboBoxValues::getKey).orElse("")
                : "";
        cell = DBPromoAgCell.builder().editable(editable).type(DBPromoCellTypeEnum.COMBOBOX.getType())
                .value(value).comboBoxValues(availableFlags).build();
        map.put("valore", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }
}

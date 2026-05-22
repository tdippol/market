package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttributiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaliAttributiService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneAttributiService;
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
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Dependent
public class PromozioneAttributiUtil {

    @Inject
    private Instance<CfgCanaliAttributiService> cfgCanaliAttributiServiceInstance;

    @Inject
    private Instance<PromozioneAttributiService> promozioneAttributiServiceInstance;

    /**
     * Crea rowData attributi promozione
     *
     * @param attributiPromo promozione attributi
     * @param idCanale       id canale
     * @param editable       true se le singole righe sono editabili, false altrimenti
     * @return rowData attributi promozione come stringa JSON
     */
    public String createAttributiRowData(Set<PromozioneAttributiEntity> attributiPromo, Long idCanale, boolean editable) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        try {
            attributiPromo.stream().sorted(Comparator.comparing(a -> a.getAttributo().getDescrizioneParametro()))
                    .forEach(a -> arrayNode.add(createAttributoRowNode(a, idCanale, editable)));
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return mapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error getting rowData for 'SchedaPromo - Attributi'", ex);
            return "";
        }
    }

    /**
     * Aggiorna attributo promozione
     *
     * @param promozione      promozione
     * @param promoAttributo  promozione attributo da aggiornare
     * @param valoreAttributo valore attributo impostato
     * @param user            utente che effettua l'aggiornamento
     * @return true in caso di aggiornamento con successo, false altrimenti
     */
    public boolean update(PromozioneTestataEntity promozione, PromozioneAttributiEntity promoAttributo,
                          String valoreAttributo, String user) {
        // Controllo se promoAttributo e' associato alla promozione
        if (!promozione.getPromozioneAttributiEntity().contains(promoAttributo)) {
            log.error(String.format("Attributo %d [%s] non associato a promozione %d [%s]",
                    promoAttributo.getAttributo().getId(), promoAttributo.getAttributo().getDescrizioneParametro(),
                    promozione.getId(), promozione.getCodicePromozione()));
            return false;
        }
        // Controllo se valoreAttributo e' presente nella lista individuata da ID_CANALE e ID_ATTRIBUTO sulla tabella MUI_CFG_CANALE_ATTRIBUTI
        final String lista = cfgCanaliAttributiServiceInstance.get()
                .getListaByCanaleAndAttributo(promozione.getMuiCanalePromozione().getId(), promoAttributo.getAttributo().getId());
        if (!StringUtils.isBlank(lista)) {
            final List<String> values = new PicklistUtils().defineListaCellEditor(lista);
            if (!values.contains(valoreAttributo)) {
                log.error(String.format("Valore %s non permesso per attributo %d [%s]", valoreAttributo,
                        promoAttributo.getAttributo().getId(), promoAttributo.getAttributo().getDescrizioneParametro()));
                return false;
            }
        }
        // Superato validazioni, setto il nuovo valore sulla entity e persisto a DB
        promoAttributo.setValore(valoreAttributo);
        try {
            promozioneAttributiServiceInstance.get().persistWithAuditLog(promoAttributo, user);
            return true;
        } catch (Exception ex) {
            log.error(String.format("Errore aggiornamento PromozioneAttributoEntity %d su promozione %d [%s]",
                    promoAttributo.getId(), promozione.getId(), promozione.getCodicePromozione()));
            return false;
        }
    }

    /**
     * Crea singolo rowData attributo promozione aggiornato
     *
     * @param promoAttributo promozione attributo
     * @param idCanale       id canale
     * @param editable       true se la riga e' editabile, false altrimenti
     * @return singolo rowData attributo promozione aggiornato come stringa JSON
     */
    public String createUpdatedRow(PromozioneAttributiEntity promoAttributo, Long idCanale, boolean editable) {
        try {
            return JsonUtils.getMapper().writeValueAsString(createAttributoRowNode(promoAttributo, idCanale, editable));
        } catch (JsonProcessingException ex) {
            log.error("Error creating updated json node", ex);
            return "";
        }
    }

    /**
     * Crea singolo rowData attributo promozione originale
     *
     * @param promoAttributo promozione attributo
     * @param message        messaggio da visualizzare
     * @param idCanale       id canale
     * @param editable       true se la riga e' editabile, false altrimenti
     * @return singolo rowData attributo promozione come stringa JSON
     */
    public String createOriginalRow(PromozioneAttributiEntity promoAttributo, String message, Long idCanale, boolean editable) {
        final ObjectMapper mapper = JsonUtils.getMapper();
        try {
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set("originalRow", createAttributoRowNode(promoAttributo, idCanale, editable));
            objectNode.put("message", message);
            return mapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException ex) {
            log.error("Error getting rowData for 'SchedaPromo - Attributi'", ex);
            return "";
        }
    }

    /**
     * Crea singolo nodo JSON
     *
     * @param attributoPromo promozione attributo
     * @param idCanale       id canale
     * @param editable       flag se riga editabile
     * @return nodo JSON che mappa l'entity
     */
    private ObjectNode createAttributoRowNode(PromozioneAttributiEntity attributoPromo, Long idCanale, boolean editable) {
        final Map<String, DBPromoAgCell> map = new HashMap<>();

        DBPromoAgCell cell = DBPromoAgCell.builder().editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(String.valueOf(attributoPromo.getAttributo().getId())).build();
        map.put("id", cell);

        cell = DBPromoAgCell.builder().editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(attributoPromo.getAttributo().getDescrizioneParametro()).build();
        map.put("attributo", cell);

        cell = editable ? buildEditableCell(attributoPromo, idCanale) : buildReadOnlyCell(attributoPromo);
        map.put("valore", cell);

        return JsonUtils.getMapper().valueToTree(map);
    }

    /**
     * Crea singola cella editabile
     *
     * @param attributoPromo promozione attributo
     * @param idCanale       id canale
     * @return cella AgGrid
     */
    private DBPromoAgCell buildEditableCell(PromozioneAttributiEntity attributoPromo, Long idCanale) {
        final DBPromoAgCell.DBPromoAgCellBuilder builder = DBPromoAgCell.builder();
        builder.editable(true).value(attributoPromo.getValore());
        final String lista = cfgCanaliAttributiServiceInstance.get()
                .getListaByCanaleAndAttributo(idCanale, attributoPromo.getAttributo().getId());
        if (StringUtils.isBlank(lista)) {
            builder.type(DBPromoCellTypeEnum.STRING.getType());
        } else {
            final List<String> values = new PicklistUtils().defineListaCellEditor(lista);
            builder.type(DBPromoCellTypeEnum.PICKLIST.getType()).picklistValues(values.toArray(new String[0]));
        }
        return builder.build();
    }

    /**
     * Crea singola cella non editabile
     *
     * @param attributoPromo promozione attributo
     * @return cella AgGrid
     */
    private DBPromoAgCell buildReadOnlyCell(PromozioneAttributiEntity attributoPromo) {
        return DBPromoAgCell.builder().editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(attributoPromo.getValore()).build();
    }
}

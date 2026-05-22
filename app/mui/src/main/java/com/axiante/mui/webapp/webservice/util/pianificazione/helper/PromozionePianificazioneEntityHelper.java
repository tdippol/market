package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.TipoRigaService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Dependent
@Slf4j
public class PromozionePianificazioneEntityHelper {

    @Inject
    private TipoRigaService tipoRigaService;

    @Inject
    private CfgPianificazioneService cfgPianificazioneService;

    public void invokeSetter(@NonNull PromozionePianificazioneEntity entity,
                             @NonNull Map<String, Field> fields,
                             @NonNull Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            final Field field = fields.get(entry.getKey());
            try {
                final Object value = valueAsObject(field, entry.getValue());
                if (value != null) {
                    field.setAccessible(true);
                    field.set(entity, value);
                }
            } catch (Exception ex) {
                log.warn(String.format("Unable to set value '%s' for field '%s'", entry.getValue(), entry.getKey()), ex);
            }
        }
    }

    // TODO: Refactor in un metodo unico (questo ed il seguente)
    public boolean invokeSetterEntity(PromozionePianificazioneEntity pianificazione, String columnName,
                                      Object variableValue) {
        boolean isInvokeSetterCorrect = true;
        /*
         * I variableValue corrispondono a MUI_CFG_PIANIFICAZIONE.DEF_VALUE e sono tutti
         * di tipo String esclusi i REFERENCE value che sono Object
         */
        if (columnName != null) {
            try {
                switch (columnName) {
                    case "BUDGET_PEZZI":
                        pianificazione.setBudgetPezzi(getValueAsInteger(variableValue));
                        break;
                    case "BUONO_SCONTO_PROGRESSIVO":
                        pianificazione.setBuonoScontoProgressivo(getValueAsInteger(variableValue));
                        break;
                    case "BUONO_SCONTO_RADICE":
                        pianificazione.setBuonoScontoRadice(getValueAsInteger(variableValue));
                        break;
                    case "CARTA_ORO":
                        pianificazione.setCartaOro(getValueAsBigDecimal(variableValue));
                        break;
                    case "CARTA_ORO_PRIVILEGIATE":
                        pianificazione.setCartaOroPrivilegiate(getValueAsBigDecimal(variableValue));
                        break;
                    case "CARTA_VERDE":
                        pianificazione.setCartaVerde(getValueAsBigDecimal(variableValue));
                        break;
                    case "CARTA_VERDE_PRIVILEGIATE":
                        pianificazione.setCartaVerdePrivilegiate(getValueAsBigDecimal(variableValue));
                        break;
                    case "CLASSE_DEFAULT":
                        pianificazione.setClasseDefault(getValueAsBigDecimal(variableValue));
                        break;
                    case "CODICE_ELEMENTO":
                        pianificazione.setCodiceElemento(getValueAsString(variableValue));
                        break;
                    case "DATA_FINE":
                        // La modifica della data fine va a cascata
                        adjustDates(pianificazione, null, pianificazione.getDataFine(), false, true, null, getValueAsDate(variableValue), null);
                        break;
                    case "DATA_FINE_BARCODE":
                        pianificazione.setDataFineBarcode(getValueAsDate(variableValue));
                        break;
                    case "DATA_INIZIO":
                        // La modifica della data inizio va a cascata
                        adjustDates(pianificazione, pianificazione.getDataInizio(), null, true, false, getValueAsDate(variableValue), null, null);
                        break;
                    case "DESCRIZIONE_SCONTO":
                        pianificazione.setDescrizioneSconto(getValueAsString(variableValue));
                        break;
                    case "ELEMENTO":
                        pianificazione.setElemento(getValueAsString(variableValue));
                        break;
                    case "ELENCO_CLUSTER":
                        pianificazione.setElencoCluster(getValueAsString(variableValue));
                        break;
                    case "ESCLUSIONE":
                        pianificazione.setEsclusione(getValueAsString(variableValue));
                        break;
                    case "FASCIA_ORARIA":
                        pianificazione.setFasciaOraria(getValueAsString(variableValue));
                        break;
                    case "GENERA_BUONO_WEB":
                        pianificazione.setGeneraBuonoWeb(getValueAsString(variableValue));
                        break;
                    case "GIORNO_SETTIMANA":
                        pianificazione.setGiornoSettimana(getValueAsString(variableValue));
                        break;
                    case "LETTERA_CODICE_ECOMMERCE":
                        pianificazione.setLetteraCodiceEcommerce(getValueAsString(variableValue));
                        break;
                    case "MAX_GIORNO":
                        pianificazione.setMaxGiorno(getValueAsInteger(variableValue));
                        break;
                    case "MAX_PERIODO":
                        pianificazione.setMaxPeriodo(getValueAsInteger(variableValue));
                        break;
                    case "MAX_SCONTRINO":
                        pianificazione.setMaxScontrino(getValueAsInteger(variableValue));
                        break;
                    case "MOLTEPLICITA":
                        pianificazione.setMolteplicita(getValueAsInteger(variableValue));
                        break;
                    case "NOTE_TIMING":
                        pianificazione.setNoteTiming(getValueAsString(variableValue));
                        break;
                    case "NUM_RAGGRUPPAMENTO":
                        pianificazione.setNumRaggruppamento(getValueAsString(variableValue));
                        break;
                    case "NUM_SET":
                        pianificazione.setNumSet(getValueAsString(variableValue));
                        break;
                    case "SCALA":
                        pianificazione.setScala(getValueAsString(variableValue));
                        break;
                    case "STAMPA_OFFERTA":
                        pianificazione.setStampaOfferta(getValueAsString(variableValue));
                        break;
                    case "TARGET":
                        pianificazione.setTarget(getValueAsString(variableValue));
                        break;
                    case "TIPO_ELEMENTO":
                        pianificazione.setTipoElemento(getValueAsString(variableValue));
                        break;
                    case "TIPO_RIGA":
                        pianificazione.setTipoRiga(variableValue == null
                                ? null
                                : tipoRigaService.findByDescription((String) variableValue));
                        break;
                    case "TIPO_SOGLIA":
                        pianificazione.setTipoSoglia(getValueAsString(variableValue));
                        break;
                    case "TIPO_TAGLIO":
                        pianificazione.setTipoTaglio(getValueAsString(variableValue));
                        break;
                    case "TIPO_TETTO":
                        pianificazione.setTipoTetto(getValueAsString(variableValue));
                        break;
                    case "VALORE":
                        pianificazione.setValore(getValueAsBigDecimal(variableValue));
                        break;
                    case "VALORE_SCALA":
                        pianificazione.setValoreScala(getValueAsInteger(variableValue));
                        break;
                    case "VALORE_SOGLIA":
                        pianificazione.setValoreSoglia(getValueAsBigDecimal(variableValue));
                        break;
                    case "VALORE_TAGLIO":
                        pianificazione.setValoreTaglio(getValueAsBigDecimal(variableValue));
                        break;
                    case "VALORE_TETTO":
                        pianificazione.setValoreTetto(getValueAsBigDecimal(variableValue));
                        break;
                    case "NOTE":
                        pianificazione.setNote(getValueAsString(variableValue));
                        break;
                    case "NUM_UTILIZZI":
                        pianificazione.setNumUtilizzi(getValueAsString(variableValue));
                        break;
                    case "CODICE_UTENTE_AGGIORNAMENTO":
                        pianificazione.setCodiceUtenteAggiornamento(getValueAsString(variableValue));
                        break;
                    case "CODICE_UTENTE_INSERIMENTO":
                        pianificazione.setCodiceUtenteInserimento(getValueAsString(variableValue));
                        break;
                    case "DATA_AGGIORNAMENTO":
                        pianificazione.setDataAggiornamento(getValueAsDate(variableValue));
                        break;
                    case "DATA_INSERIMENTO":
                        pianificazione.setDataInserimento(getValueAsDate(variableValue));
                        break;
                    case "CANALE_DISPOSITIVO":
                        pianificazione.setCanaleDispositivo(getValueAsString(variableValue));
                        break;
                    case "CODICE_ON_LINE":
                        pianificazione.setCodiceOnline(getValueAsString(variableValue));
                        break;
                    case "VALIDITA_PERIODO":
                        pianificazione.setValiditaPeriodo(getValueAsInteger(variableValue));
                        break;
                    case "NUMERO_STAMPE":
                        pianificazione.setNumeroStampe(getValueAsInteger(variableValue));
                        break;
                    case "MAGGIOR_VANTAGGIO":
                        pianificazione.setMaggiorVantaggio(getValueAsString(variableValue));
                        break;
                    case "INCLUDI_ESSELUNGA":
                        pianificazione.setIncludiEsselunga(getValueAsString(variableValue));
                        break;
                    case "INCLUDI_ESSERBELLA":
                        pianificazione.setIncludiEsserbella(getValueAsString(variableValue));
                        break;
                    case "INCLUDI_ATLANTIC":
                        pianificazione.setIncludiAtlantic(getValueAsString(variableValue));
                        break;
                    case "INCLUDI_LAESSE":
                        pianificazione.setIncludiLaesse(getValueAsString(variableValue));
                        break;
                    case "SCONTO_RIFATTURABILE":
                        pianificazione.setScontoRifatturabile(getValueAsInteger(variableValue));
                        break;
                    case "LINK":
                        pianificazione.setLink(getValueAsString(variableValue));
                        break;
                    case "SCONTO_CASSA":
                        pianificazione.setScontoCassa(getValueAsString(variableValue));
                        break;
                    case "PORTI_VIA":
                        pianificazione.setPortiVia(getValueAsInteger(variableValue));
                        break;
                    case "PAGHI":
                        pianificazione.setPaghi(getValueAsInteger(variableValue));
                        break;
                    case "TIPO_SCONTO":
                        pianificazione.setTipoSconto(getValueAsString(variableValue));
                        break;
                    case "ORA_INIZIO":
                        pianificazione.setOraInizio(variableValue == null
                                ? null
                                : DateTimeUtils.toTime(variableValue.toString(), false));
                        break;
                    case "ORA_FINE":
                        pianificazione.setOraFine(variableValue == null
                                ? null
                                : DateTimeUtils.toTime(variableValue.toString(), false));
                        break;
                    case "STAMPA_ETICHETTA":
                        pianificazione.setStampaEtichetta(getValueAsString(variableValue));
                        break;
                    case "MECCANICA_REDENZIONE":
                        pianificazione.setMeccanicaRedenzione(getValueAsString(variableValue));
                        break;
                    case "BRUCIABILITA":
                        pianificazione.setBruciabilita(getValueAsString(variableValue));
                        break;
                    case "CUMULABILITA":
                        pianificazione.setCumulabilita(getValueAsString(variableValue));
                        break;
                    case "VISUALIZZA_MECCANICA":
                        pianificazione.setVisualizzaMeccanica(getValueAsString(variableValue));
                        break;
                    case "CLASSE":
                        pianificazione.setClasse(getValueAsString(variableValue));
                        break;
                    case "SOTTOCLASSE":
                        pianificazione.setSottoclasse(getValueAsString(variableValue));
                        break;
                    case "PREZZO_BUDGET":
                        pianificazione.setPrezzoBudget(getValueAsBigDecimal(variableValue));
                        break;
                    case "TAGLIO_PUNTI":
                        pianificazione.setTaglioPunti(getValueAsInteger(variableValue));
                        break;
                    case "MULTITRANSAZIONE":
                        pianificazione.setMultiTransazione(getValueAsString(variableValue));
                        break;
                    case "CLUSTER_CLIENTE":
                        pianificazione.setClusterCliente(getValueAsString(variableValue));
                        break;
                    case "TIPO_SMALTIMENTO":
                        pianificazione.setTipoSmaltimento(getValueAsString(variableValue));
                        break;
                    case "ID_PROMO_EXT":
                        pianificazione.setIdPromoExt(getValueAsString(variableValue));
                        break;
                    case "CODICE_GRUPPO":
                        pianificazione.setCodiceGruppo(getValueAsString(variableValue));
                        break;
                    case "CONV_BOLLINI":
                        pianificazione.setConvBollini(getValueAsString(variableValue));
                        break;
                    case "VALORE_2":
                        pianificazione.setValore2(getValueAsBigDecimal(variableValue));
                        break;
                    case "FORMA_PAGAMENTO":
                        pianificazione.setFormaPagamento(getValueAsString(variableValue));
                        break;
                    case "SCONTO_IN_APP":
                        pianificazione.setScontoInApp(getValueAsString(variableValue));
                        break;
                    case "CODICE_INIZIATIVA":
                        pianificazione.setCodiceIniziativa(getValueAsString(variableValue));
                        break;
                    case "PREZZO":
                        pianificazione.setPrezzo(getValueAsBigDecimal(variableValue));
                        break;
                    default:
                        log.warn(String.format("Column %s is not defined in the entity PromozionePianificazioneEntityHelper",
                                columnName));
                        isInvokeSetterCorrect = false;
                }

            } catch (ClassCastException | NumberFormatException e) {
                log.error(
                        String.format("The format of value %s for column %s is not correct", variableValue, columnName),
                        e);
                isInvokeSetterCorrect = false;
            }
        }
        return isInvokeSetterCorrect;
    }

    public List<PromozionePianificazioneEntity> invokeSetterEntity(List<PromozionePianificazioneEntity> pianificazioni,
                                                                   String columnName,
                                                                   Object variableValue) {
        return invokeSetterEntity(pianificazioni, columnName, variableValue, null);
    }

    public List<PromozionePianificazioneEntity> invokeSetterEntity(List<PromozionePianificazioneEntity> pianificazioni,
                                                                   String columnName,
                                                                   Object variableValue, CfgPianificazioneEntity configurazione) {
        List<PromozionePianificazioneEntity> result = new ArrayList<>();
        for (PromozionePianificazioneEntity pianificazione : pianificazioni) {
            if (columnName != null) {
                try {
                    switch (columnName) {
                        case "BUDGET_PEZZI":
                            pianificazione.setBudgetPezzi(getValueAsInteger(variableValue));
                            break;
                        case "BUONO_SCONTO_PROGRESSIVO":
                            pianificazione.setBuonoScontoProgressivo(getValueAsInteger(variableValue));
                            break;
                        case "BUONO_SCONTO_RADICE":
                            pianificazione.setBuonoScontoRadice(getValueAsInteger(variableValue));
                            break;
                        case "CARTA_ORO":
                            pianificazione.setCartaOro(getValueAsBigDecimal(variableValue));
                            break;
                        case "CARTA_ORO_PRIVILEGIATE":
                            pianificazione.setCartaOroPrivilegiate(getValueAsBigDecimal(variableValue));
                            break;
                        case "CARTA_VERDE":
                            pianificazione.setCartaVerde(getValueAsBigDecimal(variableValue));
                            break;
                        case "CARTA_VERDE_PRIVILEGIATE":
                            pianificazione.setCartaVerdePrivilegiate(getValueAsBigDecimal(variableValue));
                            break;
                        case "CLASSE_DEFAULT":
                            pianificazione.setClasseDefault(getValueAsBigDecimal(variableValue));
                            break;
                        case "CODICE_ELEMENTO":
                            pianificazione.setCodiceElemento(getValueAsString(variableValue));
                            break;
                        case "DATA_FINE":
                            // La modifica della data fine va a cascata
                            adjustDates(pianificazione, null, pianificazione.getDataFine(), false, true, null, getValueAsDate(variableValue), result);
                            break;
                        case "DATA_FINE_BARCODE":
                            pianificazione.setDataFineBarcode(getValueAsDate(variableValue));
                            break;
                        case "DATA_INIZIO":
                            // La modifica della data inizio va a cascata
                            adjustDates(pianificazione, pianificazione.getDataInizio(), null, true, false, getValueAsDate(variableValue), null, result);
                            break;
                        case "DESCRIZIONE_SCONTO":
                            pianificazione.setDescrizioneSconto(getValueAsString(variableValue));
                            break;
                        case "ELEMENTO":
                            pianificazione.setElemento(getValueAsString(variableValue));
                            break;
                        case "ELENCO_CLUSTER":
                            pianificazione.setElencoCluster(getValueAsString(variableValue));
                            break;
                        case "ESCLUSIONE":
                            pianificazione.setEsclusione(getValueAsString(variableValue));
                            break;
                        case "FASCIA_ORARIA":
                            pianificazione.setFasciaOraria(getValueAsString(variableValue));
                            break;
                        case "GENERA_BUONO_WEB":
                            pianificazione.setGeneraBuonoWeb(getValueAsString(variableValue));
                            break;
                        case "GIORNO_SETTIMANA":
                            pianificazione.setGiornoSettimana(getValueAsString(variableValue));
                            break;
                        case "LETTERA_CODICE_ECOMMERCE":
                            pianificazione.setLetteraCodiceEcommerce(getValueAsString(variableValue));
                            break;
                        case "MAX_GIORNO":
                            pianificazione.setMaxGiorno(getValueAsInteger(variableValue));
                            break;
                        case "MAX_PERIODO":
                            pianificazione.setMaxPeriodo(getValueAsInteger(variableValue));
                            break;
                        case "MAX_SCONTRINO":
                            pianificazione.setMaxScontrino(getValueAsInteger(variableValue));
                            break;
                        case "MOLTEPLICITA":
                            pianificazione.setMolteplicita(getValueAsInteger(variableValue));
                            break;
                        case "NOTE_TIMING":
                            pianificazione.setNoteTiming(getValueAsString(variableValue));
                            break;
                        case "NUM_RAGGRUPPAMENTO":
                            pianificazione.setNumRaggruppamento(getValueAsString(variableValue));
                            break;
                        case "NUM_SET":
                            pianificazione.setNumSet(getValueAsString(variableValue));
                            break;
                        case "SCALA":
                            pianificazione.setScala(getValueAsString(variableValue));
                            break;
                        case "STAMPA_OFFERTA":
                            pianificazione.setStampaOfferta(getValueAsString(variableValue));
                            break;
                        case "TARGET":
                            pianificazione.setTarget(getValueAsString(variableValue));
                            break;
                        case "TIPO_ELEMENTO":
                            pianificazione.setTipoElemento(getValueAsString(variableValue));
                            break;
                        case "TIPO_RIGA":
                            pianificazione.setTipoRiga(variableValue == null
                                    ? null
                                    : tipoRigaService.findByDescription((String) variableValue));
                            break;
                        case "TIPO_SOGLIA":
                            pianificazione.setTipoSoglia(getValueAsString(variableValue));
                            break;
                        case "TIPO_TAGLIO":
                            pianificazione.setTipoTaglio(getValueAsString(variableValue));
                            break;
                        case "TIPO_TETTO":
                            pianificazione.setTipoTetto(getValueAsString(variableValue));
                            break;
                        case "VALORE":
                            pianificazione.setValore(getValueAsBigDecimal(variableValue));
                            break;
                        case "VALORE_SCALA":
                            pianificazione.setValoreScala(getValueAsInteger(variableValue));
                            break;
                        case "VALORE_SOGLIA":
                            pianificazione.setValoreSoglia(getValueAsBigDecimal(variableValue));
                            break;
                        case "VALORE_TAGLIO":
                            pianificazione.setValoreTaglio(getValueAsBigDecimal(variableValue));
                            break;
                        case "VALORE_TETTO":
                            pianificazione.setValoreTetto(getValueAsBigDecimal(variableValue));
                            break;
                        case "NOTE":
                            pianificazione.setNote(getValueAsString(variableValue));
                            break;
                        case "NUM_UTILIZZI":
                            pianificazione.setNumUtilizzi(getValueAsString(variableValue));
                            break;
                        case "CODICE_UTENTE_AGGIORNAMENTO":
                            pianificazione.setCodiceUtenteAggiornamento(getValueAsString(variableValue));
                            break;
                        case "CODICE_UTENTE_INSERIMENTO":
                            pianificazione.setCodiceUtenteInserimento(getValueAsString(variableValue));
                            break;
                        case "DATA_AGGIORNAMENTO":
                            pianificazione.setDataAggiornamento(getValueAsDate(variableValue));
                            break;
                        case "DATA_INSERIMENTO":
                            pianificazione.setDataInserimento(getValueAsDate(variableValue));
                            break;
                        case "CANALE_DISPOSITIVO":
                            pianificazione.setCanaleDispositivo(getValueAsString(variableValue));
                            break;
                        case "CODICE_ON_LINE":
                            pianificazione.setCodiceOnline(getValueAsString(variableValue));
                            break;
                        case "VALIDITA_PERIODO":
                            pianificazione.setValiditaPeriodo(getValueAsInteger(variableValue));
                            break;
                        case "NUMERO_STAMPE":
                            pianificazione.setNumeroStampe(getValueAsInteger(variableValue));
                            break;
                        case "MAGGIOR_VANTAGGIO":
                            pianificazione.setMaggiorVantaggio(getValueAsString(variableValue));
                            break;
                        case "INCLUDI_ESSELUNGA":
                            pianificazione.setIncludiEsselunga(getValueAsString(variableValue));
                            break;
                        case "INCLUDI_ESSERBELLA":
                            pianificazione.setIncludiEsserbella(getValueAsString(variableValue));
                            break;
                        case "INCLUDI_ATLANTIC":
                            pianificazione.setIncludiAtlantic(getValueAsString(variableValue));
                            break;
                        case "INCLUDI_LAESSE":
                            pianificazione.setIncludiLaesse(getValueAsString(variableValue));
                            break;
                        case "SCONTO_RIFATTURABILE":
                            pianificazione.setScontoRifatturabile(getValueAsInteger(variableValue));
                            break;
                        case "LINK":
                            pianificazione.setLink(getValueAsString(variableValue));
                            break;
                        case "SCONTO_CASSA":
                            pianificazione.setScontoCassa(getValueAsString(variableValue));
                            break;
                        case "PORTI_VIA":
                            pianificazione.setPortiVia(getValueAsInteger(variableValue));
                            break;
                        case "PAGHI":
                            pianificazione.setPaghi(getValueAsInteger(variableValue));
                            break;
                        case "TIPO_SCONTO":
                            pianificazione.setTipoSconto(getValueAsString(variableValue));
                            break;
                        case "ORA_INIZIO":
                            pianificazione.setOraInizio(variableValue == null
                                    ? null
                                    : DateTimeUtils.toTime(variableValue.toString(), false));
                            break;
                        case "ORA_FINE":
                            pianificazione.setOraFine(variableValue == null
                                    ? null
                                    : DateTimeUtils.toTime(variableValue.toString(), false));
                            break;
                        case "STAMPA_ETICHETTA":
                            pianificazione.setStampaEtichetta(getValueAsString(variableValue));
                            break;
                        case "MECCANICA_REDENZIONE":
                            pianificazione.setMeccanicaRedenzione(getValueAsString(variableValue));
                            break;
                        case "BRUCIABILITA":
                            pianificazione.setBruciabilita(getValueAsString(variableValue));
                            break;
                        case "CUMULABILITA":
                            pianificazione.setCumulabilita(getValueAsString(variableValue));
                            break;
                        case "VISUALIZZA_MECCANICA":
                            pianificazione.setVisualizzaMeccanica(getValueAsString(variableValue));
                            break;
                        case "CLASSE":
                            pianificazione.setClasse(getValueAsString(variableValue));
                            break;
                        case "SOTTOCLASSE":
                            pianificazione.setSottoclasse(getValueAsString(variableValue));
                            break;
                        case "PREZZO_BUDGET":
                            pianificazione.setPrezzoBudget(getValueAsBigDecimal(variableValue));
                            break;
                        case "TAGLIO_PUNTI":
                            pianificazione.setTaglioPunti(getValueAsInteger(variableValue));
                            break;
                        case "MULTITRANSAZIONE":
                            pianificazione.setMultiTransazione(getValueAsString(variableValue));
                            break;
                        case "CLUSTER_CLIENTE":
                            pianificazione.setClusterCliente(getValueAsString(variableValue));
                            break;
                        case "TIPO_SMALTIMENTO":
                            pianificazione.setTipoSmaltimento(getValueAsString(variableValue));
                            break;
                        case "ID_PROMO_EXT":
                            pianificazione.setIdPromoExt(getValueAsString(variableValue));
                            break;
                        case "CODICE_GRUPPO":
                            pianificazione.setCodiceGruppo(getValueAsString(variableValue));
                            break;
                        case "CONV_BOLLINI":
                            pianificazione.setConvBollini(getValueAsString(variableValue));
                            break;
                        case "VALORE_2":
                            pianificazione.setValore2(getValueAsBigDecimal(variableValue));
                            break;
                        case "FORMA_PAGAMENTO":
                            pianificazione.setFormaPagamento(getValueAsString(variableValue));
                            break;
                        case "SCONTO_IN_APP":
                            pianificazione.setScontoInApp(getValueAsString(variableValue));
                            break;
                        case "CODICE_INIZIATIVA":
                            pianificazione.setCodiceIniziativa(getValueAsString(variableValue));
                            break;
                        case "PREZZO":
                            pianificazione.setPrezzo(getValueAsBigDecimal(variableValue));
                            break;
                        default:
                            log.warn(String.format("Column %s is not defined in the entity PromozionePianificazioneEntityHelper",
                                    columnName));
                    }
                } catch (ClassCastException | NumberFormatException e) {
                    log.error(
                            String.format("The format of value %s for column %s is not correct", variableValue, columnName),
                            e);
                }
                if ( configurazione == null ){
                    configurazione = findConfigurazione(pianificazione, columnName);
                }
                if (canPropagate(configurazione, columnName)){
                    if ( pianificazione.getChildren() != null && !pianificazione.getChildren().isEmpty()){
                        Set<PromozionePianificazioneEntity> children = new HashSet<>();
                        children.addAll(invokeSetterEntity(new ArrayList<>(pianificazione.getChildren()), columnName, variableValue, null));
                        pianificazione.setChildren(children);
                    }
                }
            }
            result.add(pianificazione);
        }
        return result;
    }

    CfgPianificazioneEntity findConfigurazione(PromozionePianificazioneEntity pianificazione, String codiceCampo){
        return cfgPianificazioneService
                .findPianificazioneByCanaleMeccanicaTipoRigaField(pianificazione.getPromozioneTestataEntity().getMuiCanalePromozione(),
                        pianificazione.getMeccanicaEntity(),
                        pianificazione.getTipoRiga(),
                        codiceCampo,
                        pianificazione.getPromozioneTestataEntity());
    }

    boolean canPropagate(CfgPianificazioneEntity configurazione, String codiceCampo){
        if ( configurazione == null ) return false;
        if ( codiceCampo == null ) return configurazione.getPropaga();
        switch (codiceCampo.toUpperCase()) {
            case "DATA_FINE":
            case "DATA_INIZIO":
            case "ID_MECCANICA":
                return false;
        }
        return configurazione.getPropaga();
    }

    /**
     * La funzione aggiorna la data inizio/fine della pianificazione e dei suoi figli
     * solo se la data inizio/fine della pianificazione (o di un figlio) e' uguale alla
     * vecchia data. In caso contrario non aggiorna
     *
     * @param pianificazione
     * @param vecchioInizio
     * @param vecchiaFine
     * @param aggiornaInizio
     * @param aggiornaFine
     * @param dataInizio
     * @param dataFine
     * @param updatedPromo
     * @return
     */
    public void adjustDates(@NonNull PromozionePianificazioneEntity pianificazione, Date vecchioInizio, Date vecchiaFine, boolean aggiornaInizio, boolean aggiornaFine, Date dataInizio, Date dataFine, final List<PromozionePianificazioneEntity> updatedPromo) {
        final DateTimeUtils dateTimeUtils = new DateTimeUtils();
        boolean isUpdated = false;
        // prima modifico i figli ... se le date sono compatibili
        if (pianificazione.getChildren() != null) {
            pianificazione.getChildren().stream()
                    .filter(Objects::nonNull)
                    .forEach(child -> adjustDates(child, vecchioInizio, vecchiaFine,
                            aggiornaInizio, aggiornaFine, dataInizio, dataFine, updatedPromo));
        }
        if (
                aggiornaInizio // devo aggiornare la data inizio
                        && Objects.nonNull(pianificazione.getDataInizio()) // la pianificazione ha data inizio
                        && dateTimeUtils.equalsNoTime(pianificazione.getDataInizio(), vecchioInizio) // le date erano uguali
        ) {
            pianificazione.setDataInizio(dataInizio);
            isUpdated = true;
        }
        if (
                aggiornaFine // devo aggiornare la data fine
                        && Objects.nonNull(pianificazione.getDataFine()) // la pianificazione ha data fine
                        && dateTimeUtils.equalsNoTime(pianificazione.getDataFine(), vecchiaFine) // le date erano uguali
        ) {
            pianificazione.setDataFine(dataFine);
            isUpdated = true;
        }

        if (isUpdated && updatedPromo != null) {
            updatedPromo.add(pianificazione);
        }
    }


    private Integer getValueAsInteger(Object value) {
        return value == null ? null : Integer.parseInt((String) value);
    }
    private Long getValueAsLong(Object value) {
        return value == null ? null : Long.parseLong((String) value);
    }

    private String getValueAsString(Object value) {
        return value == null ? null : (String) value;
    }
    private Date getValueAsDate(Object value) {
        return value == null ? null : (Date) value;
    }

    private BigDecimal getValueAsBigDecimal(Object value) {
        if (value instanceof BigDecimal) return (BigDecimal) value;
        return value == null ? null : new BigDecimal(value.toString());
    }

    private Object valueAsObject(@NonNull final Field field, @NonNull final String value) {
        final String fieldType = field.getType().getSimpleName();
        if ( value.isEmpty() ) return null;
        switch (fieldType) {
            case "String":
                return value;
            case "Integer":
                return Integer.parseInt(value);
            case "BigDecimal":
                return new BigDecimal(value);
            case "Date":
                // First try to parse value as date; if fails, then try parsing as time
                Date date = new DateTimeUtils().toDate(value, false);
                if (date == null) {
                    date = DateTimeUtils.toTime(value, false);
                }
                if (date == null) {
                    log.warn(String.format("Failed to parse '%s' as a valid date / time", value));
                    return null;
                }
                return date;
            default:
                log.error(String.format("Field type '%s' not managed", fieldType));
                return null;
        }
    }
}

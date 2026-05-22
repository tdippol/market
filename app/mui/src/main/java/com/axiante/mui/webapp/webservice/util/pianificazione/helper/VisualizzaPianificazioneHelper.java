package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;

@Dependent
@Slf4j
public class VisualizzaPianificazioneHelper {

    private DateTimeUtils dateTimeUtils = new DateTimeUtils();

    public Object invokeGetterEntity(String header, PromozionePianificazioneEntity promozionePianificazione) {

        Object fieldValue = null;

        if (header != null) {
            switch (header) {
                case "BUDGET_PEZZI":
                    fieldValue = promozionePianificazione.getBudgetPezzi();
                    break;
                case "BUONO_SCONTO_PROGRESSIVO":
                    fieldValue = promozionePianificazione.getBuonoScontoProgressivo();
                    break;
                case "BUONO_SCONTO_RADICE":
                    fieldValue = promozionePianificazione.getBuonoScontoRadice();
                    break;
                case "CARTA_ORO":
                    fieldValue = promozionePianificazione.getCartaOro();
                    break;
                case "CARTA_ORO_PRIVILEGIATE":
                    fieldValue = promozionePianificazione.getCartaOroPrivilegiate();
                    break;
                case "CARTA_VERDE":
                    fieldValue = promozionePianificazione.getCartaVerde();
                    break;
                case "CARTA_VERDE_PRIVILEGIATE":
                    fieldValue = promozionePianificazione.getCartaVerdePrivilegiate();
                    break;
                case "CLASSE_DEFAULT":
                    fieldValue = promozionePianificazione.getClasseDefault();
                    break;
                case "CODICE_ELEMENTO":
                    fieldValue = promozionePianificazione.getCodiceElemento();
                    break;
                case "DATA_FINE":
                    fieldValue = promozionePianificazione.getDataFine() != null
                            ? dateTimeUtils.toExcelDate(promozionePianificazione.getDataFine())
                            : "";
                    break;
                case "DATA_FINE_BARCODE":
                    fieldValue = promozionePianificazione.getDataFineBarcode();
                    break;
                case "DATA_INIZIO":
                    fieldValue = promozionePianificazione.getDataInizio() != null
                            ? dateTimeUtils.toExcelDate(promozionePianificazione.getDataInizio())
                            : "";
                    break;
                case "DESCRIZIONE_SCONTO":
                    fieldValue = promozionePianificazione.getDescrizioneSconto();
                    break;
                case "ELEMENTO":
                    fieldValue = promozionePianificazione.getElemento();
                    break;
                case "ELENCO_CLUSTER":
                    fieldValue = promozionePianificazione.getElencoCluster();
                    break;
                case "ESCLUSIONE":
                    fieldValue = promozionePianificazione.getEsclusione();
                    break;
                case "FASCIA_ORARIA":
                    fieldValue = promozionePianificazione.getFasciaOraria();
                    break;
                case "GENERA_BUONO_WEB":
                    fieldValue = promozionePianificazione.getGeneraBuonoWeb();
                    break;
                case "GIORNO_SETTIMANA":
                    fieldValue = promozionePianificazione.getGiornoSettimana();
                    break;
                case "LETTERA_CODICE_ECOMMERCE":
                    fieldValue = promozionePianificazione.getLetteraCodiceEcommerce();
                    break;
                case "MAX_GIORNO":
                    fieldValue = promozionePianificazione.getMaxGiorno();
                    break;
                case "MAX_PERIODO":
                    fieldValue = promozionePianificazione.getMaxPeriodo();
                    break;
                case "MAX_SCONTRINO":
                    fieldValue = promozionePianificazione.getMaxScontrino();
                    break;
                case "MOLTEPLICITA":
                    fieldValue = promozionePianificazione.getMolteplicita();
                    break;
                case "NOTE_TIMING":
                    fieldValue = promozionePianificazione.getNoteTiming();
                    break;
                case "NUM_RAGGRUPPAMENTO":
                    fieldValue = promozionePianificazione.getNumRaggruppamento();
                    break;
                case "NUM_SET":
                    fieldValue = promozionePianificazione.getNumSet();
                    break;
                case "SCALA":
                    fieldValue = promozionePianificazione.getScala();
                    break;
                case "STAMPA_OFFERTA":
                    fieldValue = promozionePianificazione.getStampaOfferta();
                    break;
                case "TARGET":
                    fieldValue = promozionePianificazione.getTarget();
                    break;
                case "TIPO_ELEMENTO":
                    fieldValue = promozionePianificazione.getTipoElemento();
                    break;
                case "TIPO_RIGA":
                    fieldValue = promozionePianificazione.getTipoRiga().getDescrizione();
                    break;
                case "TIPO_SOGLIA":
                    fieldValue = promozionePianificazione.getTipoSoglia();
                    break;
                case "TIPO_TAGLIO":
                    fieldValue = promozionePianificazione.getTipoTaglio();
                    break;
                case "TIPO_TETTO":
                    fieldValue = promozionePianificazione.getTipoTetto();
                    break;
                case "VALORE":
                    fieldValue = promozionePianificazione.getValore();
                    break;
                case "VALORE_SCALA":
                    fieldValue = promozionePianificazione.getValoreScala();
                    break;
                case "VALORE_SOGLIA":
                    fieldValue = promozionePianificazione.getValoreSoglia();
                    break;
                case "VALORE_TAGLIO":
                    fieldValue = promozionePianificazione.getValoreTaglio();
                    break;
                case "VALORE_TETTO":
                    fieldValue = promozionePianificazione.getValoreTetto();
                    break;
                case "NOTE":
                    fieldValue = promozionePianificazione.getNote();
                    break;
                case "NUM_UTILIZZI":
                    fieldValue = promozionePianificazione.getNumUtilizzi();
                    break;
                case "CODICE_UTENTE_AGGIORNAMENTO":
                    fieldValue = promozionePianificazione.getCodiceUtenteAggiornamento();
                    break;
                case "CODICE_UTENTE_INSERIMENTO":
                    fieldValue = promozionePianificazione.getCodiceUtenteInserimento();
                    break;
                case "DATA_AGGIORNAMENTO":
                    fieldValue = promozionePianificazione.getDataAggiornamento();
                    break;
                case "DATA_INSERIMENTO":
                    fieldValue = promozionePianificazione.getDataInserimento();
                    break;
                case "CANALE_DISPOSITIVO":
                    fieldValue = promozionePianificazione.getCanaleDispositivo();
                    break;
                case "CODICE_ON_LINE":
                    fieldValue = promozionePianificazione.getCodiceOnline();
                    break;
                case "VALIDITA_PERIODO":
                    fieldValue = promozionePianificazione.getValiditaPeriodo();
                    break;
                case "MAGGIOR_VANTAGGIO":
                    fieldValue = promozionePianificazione.getMaggiorVantaggio();
                    break;
                case "NUMERO_STAMPE":
                    fieldValue = promozionePianificazione.getNumeroStampe();
                    break;
                case "INCLUDI_ESSELUNGA":
                    fieldValue = promozionePianificazione.getIncludiEsselunga();
                    break;
                case "INCLUDI_ESSERBELLA":
                    fieldValue = promozionePianificazione.getIncludiEsserbella();
                    break;
                case "INCLUDI_ATLANTIC":
                    fieldValue = promozionePianificazione.getIncludiAtlantic();
                    break;
                case "INCLUDI_LAESSE":
                    fieldValue = promozionePianificazione.getIncludiLaesse();
                    break;
                case "SCONTO_RIFATTURABILE":
                    fieldValue = promozionePianificazione.getScontoRifatturabile();
                    break;
                case "LINK":
                    fieldValue = promozionePianificazione.getLink();
                    break;
                case "SCONTO_CASSA":
                    fieldValue = promozionePianificazione.getScontoCassa();
                    break;
                case "PORTI_VIA":
                    fieldValue = promozionePianificazione.getPortiVia();
                    break;
                case "PAGHI":
                    fieldValue = promozionePianificazione.getPaghi();
                    break;
                case "TIPO_SCONTO":
                    fieldValue = promozionePianificazione.getTipoSconto();
                    break;
                case "ORA_INIZIO":
                    fieldValue = promozionePianificazione.getOraInizio();
                    break;
                case "ORA_FINE":
                    fieldValue = promozionePianificazione.getOraFine();
                    break;
                case "STAMPA_ETICHETTA":
                    fieldValue = promozionePianificazione.getStampaEtichetta();
                    break;
                case "FORMULA":
                    log.debug("Formula field found");
                    break;
                case "MECCANICA_REDENZIONE":
                    fieldValue = promozionePianificazione.getMeccanicaRedenzione();
                    break;
                case "BRUCIABILITA":
                    fieldValue = promozionePianificazione.getBruciabilita();
                    break;
                case "CUMULABILITA":
                    fieldValue = promozionePianificazione.getCumulabilita();
                    break;
                case "VISUALIZZA_MECCANICA":
                    fieldValue = promozionePianificazione.getVisualizzaMeccanica();
                    break;
                case "CLASSE":
                    fieldValue = promozionePianificazione.getClasse();
                    break;
                case "SOTTOCLASSE":
                    fieldValue = promozionePianificazione.getSottoclasse();
                    break;
                case "PREZZO_BUDGET":
                    fieldValue = promozionePianificazione.getPrezzoBudget();
                    break;
                case "TAGLIO_PUNTI":
                    fieldValue = promozionePianificazione.getTaglioPunti();
                    break;
                case "MULTITRANSAZIONE":
                    fieldValue = promozionePianificazione.getMultiTransazione();
                    break;
                case "CLUSTER_CLIENTE":
                    fieldValue = promozionePianificazione.getClusterCliente();
                    break;
                case "CODICE_INIZIATIVA":
                    fieldValue = promozionePianificazione.getCodiceIniziativa();
                    break;
                case "TIPO_SMALTIMENTO":
                    fieldValue = promozionePianificazione.getTipoSmaltimento();
                    break;
                case "ID_PROMO_EXT":
                    fieldValue = promozionePianificazione.getIdPromoExt();
                    break;
                case "CODICE_GRUPPO":
                    fieldValue = promozionePianificazione.getCodiceGruppo();
                    break;
                case "CONV_BOLLINI":
                    fieldValue = promozionePianificazione.getConvBollini();
                    break;
                case "VALORE_2":
                    fieldValue = promozionePianificazione.getValore2();
                    break;
                case "FORMA_PAGAMENTO":
                    fieldValue = promozionePianificazione.getFormaPagamento();
                    break;
                case "SCONTO_IN_APP":
                    fieldValue = promozionePianificazione.getScontoInApp();
                    break;
                case "PREZZO":
                    fieldValue = promozionePianificazione.getPrezzo();
                    break;
                default:
                    log.debug(String.format("Column %s is not defined in the entity PromozionePianificazioneEntity", header));
            }
        }
        return fieldValue;
    }
}

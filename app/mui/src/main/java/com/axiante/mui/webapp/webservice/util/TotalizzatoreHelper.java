package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.TemplateTotalizzatoriService;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.webapp.views.util.PianificazioneTotalizzatoriFactory;
import com.axiante.mui.webapp.webservice.dto.RAFRequest;
import com.axiante.mui.webapp.webservice.dto.RAFResponse;
import com.axiante.mui.webapp.webservice.dto.RafContent;
import com.axiante.tm1.http.Tm1HttpClientFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Dependent
@Slf4j
public class TotalizzatoreHelper {
    @Inject
    Instance<TemplateTotalizzatoriService> templateServiceInstance;

    @Inject
    ApplicationProperties properties;

    @Inject
    Instance<AuditLogService> auditLogServiceInstance;
    public RAFResponse parse(@NonNull String response) throws IOException {
        return JsonUtils.getMapper().readValue(response, RAFResponse.class);
    }

    /**
     * Data una iniziativa genera l'oggetto request per l'update. </br>
     * Se l'iniziativa non ha un totalizzatori allora ritorna una request nuova basata sui template
     * attuali
     *
     * @param iniziativa
     * @return
     */
    public RAFRequest generate(@NonNull IniziativaEntity iniziativa) {
        RAFRequest request = new RAFRequest();
        request.setIdIniziativa(iniziativa.getId());
        request.setDataFine(iniziativa.getDataFine());
        request.setDataInizio(iniziativa.getDataInizio());
        request.setDecimali(iniziativa.getFlagDecimale() ? 1 : 0);
        request.setParent(new RafContent());
        request.getParent().setDescrizione(iniziativa.getDescrizione());
        request.getParent().setSegno(1);

        if (iniziativa.getTotalizzatori() == null || iniziativa.getTotalizzatori().isEmpty()) {
            // crea nuova
            request.setFigli(templateServiceInstance.get().findAll().stream().map(RafContent::fromTemplateTotalizzatoriEntity).toArray(RafContent[]::new));
        } else {
            // crea da esistente
            request.setMethod("PUT");
            RafContent[] figli = new RafContent[iniziativa.getTotalizzatori().size() - 1];
            final AtomicInteger i = new AtomicInteger(0);
            iniziativa.getTotalizzatori().forEach(t -> {
                if (t.getIdParent() == null) {
                    request.getParent().setIdTotalizzatore(t.getId());
                } else {
                    figli[i.getAndIncrement()] = RafContent.fromTotalizzatoriEntity(t);
                }
            });
            request.setFigli(figli);
        }
        return request;
    }

    /**
     * Genera una RAF request a partire da una pianificazione. </br>
     * Un totalizzatore per una pianificazione non puo' essere aggiornato quindi
     * non viene controllato se la pianificazione ha dei figli
     * @param pianificazioni
     * @return
     */
    public RAFRequest generate(@NonNull List<PromozionePianificazioneEntity> pianificazioni) {
        final PromozioneTestataEntity t = pianificazioni.get(0).getPromozioneTestataEntity();
        final RAFRequest request = new RAFRequest();
        final Long idIniziativa = calculateIdFromTestata(t);
        final RafContent padre = new RafContent ();
        // MG 5248: la descrizione massima e' 30 caratteri
        //          scoperta dopo trial & error (aka 'a caso') perche' il cliente non ha fornito alcuna indicazione
        padre.setDescrizione(t.getDescrizione().length() > 30
                ? t.getDescrizione().substring(0, 30)
                : t.getDescrizione());
        padre.setSegno(1);
        padre.setIdTotalizzatore(null);

        request.setIdIniziativa(idIniziativa);
        request.setDataFine(t.getDataFine());
        request.setDataInizio(t.getDataInizio());
        request.setDecimali(2);
        request.setParent(padre);
        final List<RafContent> figli = pianificazioni.stream().map(RafContent::fromPianificazioneEntity).collect(Collectors.toList());

        // #5198: Hard-code figli
        final RafContent accreditiManuali = new RafContent();
        accreditiManuali.setIdTotalizzatore(null);
        accreditiManuali.setDescrizione("ACCREDITI MANUALI OGGETTI");
        accreditiManuali.setSegno(1);
        accreditiManuali.setActionType(0);
        accreditiManuali.setExportVs(0);
        figli.add(accreditiManuali);
        final RafContent storniManuali = new RafContent();
        storniManuali.setIdTotalizzatore(null);
        storniManuali.setDescrizione("STORNI MANUALI OGGETTI");
        storniManuali.setSegno(-1);
        storniManuali.setActionType(0);
        storniManuali.setExportVs(0);
        figli.add(storniManuali);

        request.setFigli(figli.toArray(new RafContent[0]));

        return request;
    }

    private Long calculateIdFromTestata(PromozioneTestataEntity testata){
        String codice = testata.getCodicePromozione().replaceAll("_", "").replaceAll(testata.getAnno(), "");
        return Long.valueOf(testata.getAnno().substring(2, 4) + codice);
    }
    public CloseableHttpResponse queryRaf(RAFRequest request) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("Sending request body : \n");
                log.debug("-----------------------------------");
                try {
                    log.debug("Sending request body : \n" + JsonUtils.getMapper().writeValueAsString(request));
                } catch (JsonProcessingException ex) {
                    log.error("Error parsing request body", ex);
                } finally {
                    log.debug("-----------------------------------");
                }
            }
            CloseableHttpClient client = getClient();
            HttpEntityEnclosingRequestBase requestBase = null;
            if (request.getMethod().equals("POST")) {
                requestBase = new HttpPost(getUri());
            } else {
                requestBase = new HttpPut(getUri());
            }
            requestBase.setHeader("Accept", "application/json");
            requestBase.setHeader("Content-type", "application/json");
            requestBase.setEntity(new StringEntity(JsonUtils.getMapper().writeValueAsString(request)));
            return client.execute(requestBase);
        } catch (Exception e ){
            log.error("Error querying RAF server", e);
        }
        return null;
    }
    public RAFResponse parseAndClose ( @NonNull CloseableHttpResponse response ){
        try {
            return parse(response, true);
        } catch ( Exception e){
            log.error("Error parsing response", e);
        }
        return null;
    }

    public RAFResponse parse ( @NonNull CloseableHttpResponse response, boolean close ) throws IOException {
        RAFResponse raf = null;
        if ( response == null ){
            raf = new RAFResponse();
            raf.setStato(-100);
            raf.setStatusLine("Non e' stato possibile contattare il ws totalizzatori");
            return raf;
        }
        if (response.getStatusLine().getStatusCode() == 200) {
            StringBuilder body = readResponse(response);
            if (log.isDebugEnabled()) {
                log.debug("response received : \n");
                log.debug("-----------------------------------");
                log.debug(body.toString());
                log.debug("-----------------------------------");
            }
            raf = parse(body.toString());
        } else {
            raf = new RAFResponse();
            raf.setStato(response.getStatusLine().getStatusCode());
            raf.setStatusLine("Non e' stata ricevuta una risposta valida dal ws totalizzatori:\n" +
                    EnglishReasonPhraseCatalog.INSTANCE.getReason(response.getStatusLine().getStatusCode(), null)
            );
        }
        try {
            if (close) {
                EntityUtils.consumeQuietly(response.getEntity());
                response.close();
            }
        } catch (Exception warn) {
            log.warn("error closing connection to server", warn);
        }
        return raf;
    }

    public IniziativaEntity update(@NonNull IniziativaEntity iniziativa, @NonNull String response) throws IOException {
        return update(iniziativa, parse(response));
    }

    public IniziativaEntity update(@NonNull IniziativaEntity iniziativa, @NonNull RAFResponse response) {
        if (response.isEmpty()) {
            log.error("RAF response vuota");
            return iniziativa;
        }
        if (response.getStato() > 200) {
            log.error("RAF response has error status");
            return iniziativa;
        }
        String message = null;
        if (iniziativa.getTotalizzatori() != null && "PUT".equalsIgnoreCase(response.getMethod())) {
            // update: controllo che i figli che ho mandato siano quelli che mi ha risposto
            if (response.getFigli().length != iniziativa.getTotalizzatori().size() - 1) {
                //errore
                message = String.format("Numero figli ritornati %d, attesi %d", response.getFigli().length, iniziativa.getTotalizzatori().size() - 1);
            } else {
                Set<RafContent> set = iniziativa.getTotalizzatori().stream().map(RafContent::fromTotalizzatoriEntity).collect(Collectors.toSet());
                for (RafContent t : response.getFigli()) {
                    if ( !set.contains(t)){
                        message = "Totalizzatori ritornati diversi da totalizzatori attesi";
                        break;
                    }
                }
            }
        }
        if (iniziativa.getTotalizzatori() == null || iniziativa.getTotalizzatori().isEmpty()) {
            iniziativa.addTotalizzatore(response.getParent().toTotalizzatoriEntity());
            for (RafContent t : response.getFigli()) {
                iniziativa.addTotalizzatore(t.toTotalizzatoriEntity());
            }
        }
        if (message == null) {
            iniziativa.setTotalizzatoreAllineato(Boolean.TRUE);
        } else {
            log.error(message);
        }
        return iniziativa;
    }

    CloseableHttpClient getClient() {
        Tm1HttpClientFactory factory = new Tm1HttpClientFactory().withBasicAuth(properties.getProperty(ApplicationProperties.AUTH_TOTALIZZATORI, ApplicationProperties.DEFAULT_AUTH_TOTALIZZATORI));
        return factory.withSslVerify(false)
                .withConnectionRequestTimeout(properties.getProperty(ApplicationProperties.CONNECTION_MANAGER_TIMEOUT,
                        ApplicationProperties.DEFAULT_CONNECTION_MANAGER_TIMEOUT))
                .withTimeout(properties.getProperty(ApplicationProperties.CONNECTION_TIMEOUT,
                        ApplicationProperties.DEFAULT_CONNECTION_TIMEOUT))
                .withSocketTimeout(properties.getProperty(ApplicationProperties.SOCKET_TIMEOUT,
                        ApplicationProperties.DEFAULT_SOCKET_TIMEOUT))
                .create();
    }

    String getUri() {
        return properties.getProperty(ApplicationProperties.HOST_TOTALIZZATORI, ApplicationProperties.DEFAULT_HOST_TOTALIZZATORI) +
                properties.getProperty(ApplicationProperties.WEBSERVICE_TOTALIZZATORI, ApplicationProperties.DEFAULT_WEBSERVICE_TOTALIZZATORI);
    }

    public String getAuditPath(RAFRequest request){
        String pathTemplate = "@%s-%s/%s-%d";

        return String.format(pathTemplate,
                request.getMethod(), //ACTION
                properties.getProperty(ApplicationProperties.HOST_TOTALIZZATORI, ApplicationProperties.DEFAULT_HOST_TOTALIZZATORI), // SERVER
                properties.getProperty(ApplicationProperties.WEBSERVICE_TOTALIZZATORI, ApplicationProperties.DEFAULT_WEBSERVICE_TOTALIZZATORI), //PATH
                request.getIdIniziativa() // RESOURCE_ID
                );
    }
    private StringBuilder readResponse(CloseableHttpResponse response) throws IOException {
        final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        result.delete(0, result.length());
        while ((line = rd.readLine()) != null) {
            result.append(line).append("\n");
        }
        return result;
    }

    public PromozioneTestataEntity handleRafRequest(PromozioneTestataEntity testata, String user){
        /*
        M.F.
        Super forzatura. La MUI ragiona a canale, in questo caso stiamo forzando una configurazione
        particolare di un canale e ci aspettiamo che funzioni sempre.
        Inoltre, l'autorizzatore Fidaty non permette di passare campi in piu' per poter
        discriminare i totalizzatori per pianificazione. Risultato: non e' possibile associare SEMPRE E COMUNQUE
        un totalizzatore a una riga di pianificazione.
         */
        List<PromozionePianificazioneEntity> pianificazioni =
                testata.getPromozionePianificazioneEntities().stream()
                        .filter(PromozionePianificazioneEntity::isRafSeed)
                        .filter(p->p.getTotalizzatori() == null || p.getTotalizzatori().isEmpty())
                        .collect(Collectors.toList());
        if ( pianificazioni.size() > 2 ){
            log.error(String.format("Impossibile generare una chiamata al totalizzatore con %s pianificazioni", pianificazioni.size()));
            return null;
        }
        RAFRequest request = generate(pianificazioni);
        if ( request == null ){
            log.error("Errore nella generazione della RAF request");
            return null;
        }
        CloseableHttpResponse response = queryRaf(request);
        auditLogServiceInstance.get().logAction(AuditLogEntity.EXTERNAL_RESOURCE, getAuditPath(request),
                user != null ? user : "AUTOMATIC");
        if (response == null) {
            log.error("Errore nell'ottenimento della response dal servizio Fidaty");
            return null;
        }
        RAFResponse raf = parseAndClose(response);
        if (raf != null && raf.getStato() == 1 && !raf.isEmpty()) {
            Set<PianificazioneTotalizzatoriEntity> set = PianificazioneTotalizzatoriFactory.createPianificazioneTotalizzatoriEntitySet(raf);
            // tutti i totalizzatori sono associati alla testata promozionale
            set.forEach(testata::addTotalizzatore);
            //todo: associare se possibile i totalizzatori alle pianificazioni
            if (set.size() <= 5) {
                // posso associare alle pianificazioni solo se ho al massimo due figli
                String filter= "1";
                for (PianificazioneTotalizzatoriEntity totalizzatore : set) {
                    if (totalizzatore.getIdParent() != null) {
                        //i figli vengono associati alla pianificazione se e' possibile identificarli univocamente
                        //il parent solo alla promozione
                        PromozionePianificazioneEntity pianificazione = null;
                        if (totalizzatore.getSegno() == 1) {
                            filter = "1";
                        } else if (totalizzatore.getSegno() == -1) {
                            filter = "2";
                        } else {
                            //qualcosa e' andato molto male
                            log.error("Errore nella generazione della RAF response: segno non riconosciuto");
                        }
                        String finalFilter = filter;
                        pianificazione = pianificazioni.stream()
                                .filter(p-> finalFilter.equals(p.getNumRaggruppamento())).findFirst().orElse(null);
                        if (pianificazione != null) {
                            pianificazione.addTotalizzatore(totalizzatore);
                        }
                    }
                }
            }
        } else {
            return null;
        }
        return testata;
    }
}

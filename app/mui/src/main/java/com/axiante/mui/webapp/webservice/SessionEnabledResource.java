package com.axiante.mui.webapp.webservice;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.security.PromoSecurity;
import com.axiante.mui.webapp.webservice.dto.PromozioneCheckDto;
import com.axiante.mui.webapp.webservice.util.ColumnDefUtils;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SessionEnabledResource {
    @Inject
    HttpSession session;

    @Inject
    UserService userService;

    @Inject
    PromoService promoService;

    @Inject
    PromoSecurity security;

    UsersEntity getCurrentUser() {
        if (session.getAttribute(UsersEntity.USER_ATTRIBUTE) != null) {
            return (UsersEntity) session.getAttribute(UsersEntity.USER_ATTRIBUTE);
        }
        return null;
    }

    /**
     * Controlla se l'utente corrente (in sessione) e' admin o meno
     * 
     * @return true se l'utente ha il ruolo admin, false altrimenti
     */
    public boolean isUserAdmin() {
        final UsersEntity userEntity = getCurrentUser();
        if (userEntity == null) {
            log.warn("Unable to retrieve current user from session");
            return false;
        }
        return userEntity.isAdmin();
    }
    /**
     * Deprecated: use {@link SessionEnabledResource#getApplicationUser(String)}
     *
     * @return
     */
    @Deprecated
    UserDTO getApplicationUser() {
        return getApplicationUser(null);
    }

    UserDTO getApplicationUser(String context) {
        UsersEntity entity = getCurrentUser();
        if (entity != null) {
            return userService.asDto(entity, context);
        }
        return null;
    }


    PromozioneTestataEntity getPromozioneTestataEntity(Long idPromozione) {
        if (idPromozione == null) {
            return null;
        }
        try {
            return promoService.findById(idPromozione);
        } catch (Exception e) {
            log.error("PromozioneTestataEntity with id " + idPromozione + " not found ", e);
            return null;
        }
    }

    /**
     * Carica le columnDefs da un file JSON di configurazione, applicando le
     * hiddenColumns per l'utente corrente
     * 
     * @param jsonFile file JSON contenente la configurazione
     * @param gridName nome griglia che definisce le hiddenColumns
     * @return JSON che definisce le columnDefs per una determinata griglia
     */
    protected Response loadColumnDefFromFile(final String jsonFile, final String gridName) {
        return loadColumnDefFromFile(jsonFile, gridName, null);
    }

    /**
     * Carica le columnDefs da un file JSON di configurazione in base al contesto,
     * applicando le hiddenColumns per l'utente corrente
     * 
     * @param jsonFile file JSON contenente la configurazione
     * @param gridName nome griglia che definisce le hiddenColumns
     * @param contesto contesto (directory) da dove caricare il file JSON, se null
     *                 usa la root resources
     * @return JSON che definisce le columnDefs per una determinata griglia
     */
    protected Response loadColumnDefFromFile(final String jsonFile, final String gridName, final String contesto) {
        return loadColumnDefFromFile(jsonFile, gridName, contesto, false);
    }

    /**
     * Carica le columnDefs da un file JSON di configurazione in base al contesto,
     * applicando le hiddenColumns per l'utente corrente. Se il parametro contesto
     * e' diverso da null e non e' presente il file JSON viene caricato il file JSON
     * di default (nella root resources); se il parametro contestoRequired e' true
     * viene loggato un errore nel caso non il file JSON di contesto non esiste
     * 
     * @param jsonFile         file JSON contenente la configurazione
     * @param gridName         nome griglia che definisce le hiddenColumns
     * @param contesto         contesto (directory) da dove caricare il file JSON,
     *                         se null usa la root resources
     * @param contestoRequired true se la presenza del contesto e' da considerarsi
     *                         obbligatoria
     * @return JSON che definisce le columnDefs per una determinata griglia
     */
    protected Response loadColumnDefFromFile(final String jsonFile, final String gridName, final String contesto,
            final boolean contestoRequired) {
        String columnDefJson;
        try {
            if (!StringUtils.isBlank(contesto)) {
                // Contesto presente, tenta di caricare il file specifico del contesto
                columnDefJson = loadColumnDef(String.format("%s%s%s", contesto, "/", jsonFile), gridName,
                        contestoRequired);
                if (columnDefJson == null) {
                    if (contestoRequired) {
                        log.error(String.format(
                                "Unable to load columnDef JSON file '%s' from contesto '%s'; using default", jsonFile,
                                contesto));
                    }
                    columnDefJson = loadColumnDef(jsonFile, gridName, contestoRequired);
                    if (StringUtils.isBlank(columnDefJson)) {
                        log.error(String.format("Unable to load default columnDef JSON file '%s'", jsonFile));
                    }
                }
            } else {
                // Contesto non presente, carica il file di default
                columnDefJson = loadColumnDef(jsonFile, gridName, contestoRequired);
                if (StringUtils.isBlank(columnDefJson)) {
                    log.error(String.format("Unable to load default columnDef JSON file '%s'", jsonFile));
                }
            }
            return StringUtils.isBlank(columnDefJson) ? Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
                    : Response.ok(columnDefJson).build();
        } catch (Exception ex) {
            log.error(String.format("Error loading columnDef JSON file '%s' from contesto '%s'", jsonFile, contesto),
                    ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String loadColumnDef(String jsonFile, String gridName, boolean contestoRequired) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFile);
        if (inputStream == null) {
            if (contestoRequired) {
                log.error(String.format("Cannot load columnDef JSON file '%s'", jsonFile));
            }
            return null;
        }
        JsonNode columnDef = new ColumnDefUtils().applyHiddenColumns(inputStream, getCurrentUser().getHiddenCols(),
                gridName);
        if (columnDef == null) {
            log.warn(String.format("Error applying hidden columns for grid '%s'", gridName));
            // Reload stream
            inputStream = getClass().getClassLoader().getResourceAsStream(jsonFile);
            if (inputStream == null) {
                if (contestoRequired) {
                    log.error(String.format("Cannot load columnDef JSON file '%s'", jsonFile));
                }
                return null;
            }
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        }
        return columnDef.toString();
    }

    protected PromozioneCheckDto validatePromozioneFromRequest(final String promozioneId, final String contesto) {
        return validatePromozioneFromRequest(promozioneId, contesto, null);
    }
    protected PromozioneCheckDto validatePromozioneFromRequest(final String promozioneId, final String contesto, String codiceGruppoSelezionato) {
        Long idPromozione;
        try {
            idPromozione = Long.parseLong(promozioneId);
        } catch (final Exception ex) {
            log.error(String.format("Error converting promozione id %s to long", promozioneId), ex);
            return PromozioneCheckDto.error(Response.Status.PRECONDITION_FAILED, "Promozione inesistente");
        }
        final PromozioneTestataEntity testata = promoService.findById(idPromozione);
        if (testata == null) {
            return PromozioneCheckDto.error(Response.Status.PRECONDITION_FAILED,
                    String.format("Promozione con id %s non esiste", idPromozione));
        }
        final UserDTO userDto = getApplicationUser(contesto);
        if (!security.isAccessible(testata, userDto)) {
            return PromozioneCheckDto.error(Response.Status.PRECONDITION_FAILED,
                    String.format("Promozione con id %s non accessibile", promozioneId));
        }

        List<String> codiciGruppo = null;
        if ( !userDto.isAdmin() ) {
            if (codiceGruppoSelezionato == null) {
                codiciGruppo = userDto.getGruppi();
            } else if (userDto.getGruppi().contains(codiceGruppoSelezionato)) {
                codiciGruppo = Collections.singletonList(codiceGruppoSelezionato);
            } else {
                return PromozioneCheckDto.error(Response.Status.PRECONDITION_FAILED,
                        String.format("L'utente %s non ha accesso al gruppo %s", userDto.getUser().getName(), codiceGruppoSelezionato));
            }
        } else {
            codiciGruppo = userDto.getGruppi();
        }
        if ((codiciGruppo == null) || codiciGruppo.isEmpty()) {
            log.error(String.format("Error getting Group for user %s", getApplicationUser(null).getUser().getName()));
            return PromozioneCheckDto.error(Response.Status.PRECONDITION_FAILED,
                    String.format("Nessun gruppo associato all'utente %s", userDto.getUser().getName()));
        }
        return new PromozioneCheckDto(testata, codiciGruppo);
    }

    protected PromozioneCheckDto validatePromozioneFromRequest(final String promozioneId, final String meccanicaId,
            final String contesto, String codiceGruppoSelezionato) {
        final PromozioneCheckDto promozioneCheckDto = validatePromozioneFromRequest(promozioneId, contesto, codiceGruppoSelezionato);
        Long idMeccanica;
        try {
            idMeccanica = Long.parseLong(meccanicaId);
        } catch (final Exception ex) {
            log.error(String.format("Error converting meccanica id %s to long", meccanicaId), ex);
            return PromozioneCheckDto.error(Response.Status.PRECONDITION_FAILED, "Meccanica inesistente");
        }
        promozioneCheckDto.setIdMeccanica(idMeccanica);
        return promozioneCheckDto;
    }
}

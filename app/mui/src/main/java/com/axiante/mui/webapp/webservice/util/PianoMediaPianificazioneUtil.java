package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoArticoliDbPromoService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.PianoMediaSecurityUtils;
import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.business.service.PianoMediaDettaglioFactory;
import com.axiante.mui.webapp.webservice.dto.PianoMediaDettaglioArticoliDto;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class PianoMediaPianificazioneUtil {
    @Inject
    private Instance<PianoMediaSecurityUtils> securityUtilsInstance;

    @Inject
    private Instance<PianoMediaPromoArticoliDbPromoService> pianoMediaPromoArticoliServiceInstance;

    @Inject
    private Instance<PianoMediaDettaglioFactory> pianoMediaDettaglioFactoryInstance;

    @Inject
    private Instance<MuiService> muiServiceInstance;

    public List<PianoMediaDettaglioDTO> appyFilters(@NonNull List<PianoMediaDettaglioDTO> dettagli,
                                                    String subGrm, String grm, String categoria, String reparto,
                                                    String buyer, Date dataInizio, Date dataFine, String volantino,
                                                    String zona) {
        Stream<PianoMediaDettaglioDTO> stream = dettagli.stream();
        if (!StringUtils.isBlank(subGrm)) {
            stream = stream.filter(d -> subGrm.equals(d.getCodiceSubGrm()));
        }
        if (!StringUtils.isBlank(grm)) {
            stream = stream.filter(d -> grm.equals(d.getCodiceGrm()));
        }
        if (!StringUtils.isBlank(categoria)) {
            stream = stream.filter(d -> categoria.equals(d.getCodiceCategoria()));
        }
        if (!StringUtils.isBlank(reparto)) {
            stream = stream.filter(d -> reparto.equals(d.getCodiceReparto()));
        }
        if (!StringUtils.isBlank(buyer)) {
            stream = stream.filter(d -> buyer.equals(d.getCodiceCompratore()));
        }
        if (dataInizio != null) {
            stream = stream.filter(d -> new DateTimeUtils().isAfter(d.getDataInizio(), dataInizio, true));
        }
        if (dataFine != null) {
            stream = stream.filter(d -> new DateTimeUtils().isBefore(d.getDataFine(), dataFine, true));
        }
        if (!StringUtils.isBlank(volantino) && !"VOLANTINO_TUTTI".equalsIgnoreCase(volantino)) {
            if ("VOLANTINO_SI".equalsIgnoreCase(volantino)) {
                stream = stream.filter(PianoMediaDettaglioDTO::isFlVolantino);
            } else if ("VOLANTINO_NO".equalsIgnoreCase(volantino)) {
                stream = stream.filter(d -> !d.isFlVolantino());
            } else {
                log.error(String.format("Flag volantino '%s' non gestito", volantino));
            }
        }
        if ( !StringUtils.isBlank(zona)){
            stream = stream.filter(d->d.getZone().contains(zona));
        }
        return stream.collect(Collectors.toList());
    }

    public PianoMediaDettaglioArticoliDto getAvailableItems(@NonNull PianoMediaEntity pianoMedia,
                                                            @NonNull String promoRif,
                                                            @NonNull UserDTO user) {
        final boolean canAdd = user.isAdmin() || securityUtilsInstance.get().canAddItems(user.getUser());
        if (!canAdd) {
            log.error(String.format("Error; user '%s' cannot add items", user.getUsermame()));
            return PianoMediaDettaglioArticoliDto
                    .error(String.format("Errore sicurezza accessi aggiunta articoli per utente '%s'", user.getUsermame()));
        }
        // Prendo gli articoli gia' in pianificazione e li tolgo dalla lista che devo mostrare
        final List<String> usedItems = pianoMedia.getDettagliPianificazione().stream()
                .map(PianoMediaPianificazioneDettaglioEntity::getCodiceItem)
                .collect(Collectors.toList());
        Stream<PianoMediaPromoArticoliDbPromoEntity> stream = pianoMediaPromoArticoliServiceInstance.get()
                .findByCodicePromo(promoRif).stream()
                .filter(i -> !usedItems.contains(i.getCodiceItem()));
        // Controllo visibilita' articoli da inserire
        final boolean onlyOwnItems = !securityUtilsInstance.get().canAddAllItems(user.getUser())
                && securityUtilsInstance.get().canAddOwnItemsOnly(user.getUser());
        if (!user.isAdmin() && onlyOwnItems) {
            final List<String> gruppi = user.getGruppi();
            if (gruppi == null || gruppi.isEmpty()) {
                final String msg = String.format("Nessun gruppo associato all'utente '%s'; impossibile determinare compratori e di conseguenza articoli",
                        user.getUsermame());
                log.error(msg);
                return PianoMediaDettaglioArticoliDto.error(msg);
            }
            final List<String> buyers = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppo(gruppi);
            // Tengo solo gli articoli dei compratori associati ai gruppi dell'utente
            stream = stream.filter(i -> buyers.contains(i.getCodiceCompratore()));
        }
        final List<PianoMediaDettaglioDTO> dettagli = pianoMediaDettaglioFactoryInstance.get()
                .createPianoMediaDettaglioDTO(stream.collect(Collectors.toList()));
        return new PianoMediaDettaglioArticoliDto(dettagli);
    }
}

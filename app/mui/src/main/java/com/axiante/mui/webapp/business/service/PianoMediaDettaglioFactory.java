package com.axiante.mui.webapp.business.service;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoArticoliDbPromoService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoArticoliDettaglioDbPromoService;
import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianoMediaDettaglioFactory {

    @Inject
    Instance<PianoMediaPromoArticoliDbPromoService> pianoMediaPromoArticoliDbPromoService;

    @Inject
    Instance<PianoMediaPromoArticoliDettaglioDbPromoService> pianoMediaPromoArticoliDettaglioDbPromoService;

    /**
     * Helper: ritorna tutte le righe di pianificazione di dettaglio del piano media come (@see PianoMediaDettaglioDTO)
     *
     * @param testata
     * @return
     */
    public List<PianoMediaDettaglioDTO> createPianoMediaDettaglioDTO(PianoMediaEntity testata) {
        List<PianoMediaDettaglioDTO> result = new ArrayList<>();
        Set<PianoMediaPianificazioneDettaglioEntity> dettagliPianificazione = testata.getDettagliPianificazione();
        if (dettagliPianificazione != null) {
            for (PianoMediaPianificazioneDettaglioEntity pianificazione : dettagliPianificazione) {
                result.addAll(createPianoMediaDettaglioDTO(testata, pianificazione));
            }
        }
        return result;
    }

    /**
     * Helper: a partire da una testata di piano media e una lista di dettagli di pianificazione crea una lista di dto
     *
     * @param testata
     * @param dettagliPianificazione
     * @return
     */
    public List<PianoMediaDettaglioDTO> createPianoMediaDettaglioDTO(PianoMediaEntity testata,
                                                                     List<PianoMediaPromoArticoliDbPromoEntity> dettagliPianificazione) {
        List<PianoMediaDettaglioDTO> result = new ArrayList<>();
        dettagliPianificazione.forEach(dettaglio -> result.add(createPianoMediaDettaglioDTO(testata, dettaglio)));
        return result;
    }

    /**
     * Helper: a partire da una lista di dettagli di pianificazione crea una lista di dto
     *
     * @param dettagliPianificazione
     * @return
     */
    public List<PianoMediaDettaglioDTO> createPianoMediaDettaglioDTO(List<PianoMediaPromoArticoliDbPromoEntity> dettagliPianificazione) {
        List<PianoMediaDettaglioDTO> result = new ArrayList<>();
        dettagliPianificazione.forEach(dettaglio -> result.add(createPianoMediaDettaglioDTO(dettaglio)));
        return result;
    }


    /**
     * @param articolo il dettaglio articolo
     * @return
     * @(see PianoMediaDettaglioFactory#createPianoMediaDettaglioDTO(PianoMediaEntity, PianoMediaPianificazioneDettaglioEntity)
     */
    public PianoMediaDettaglioDTO createPianoMediaDettaglioDTO(@NonNull PianoMediaPromoArticoliDbPromoEntity articolo) {
        return createPianoMediaDettaglioDTO(null, articolo);
    }

    /**
     * crea un PianoMediaDettaglioDTO a partire da un dettaglio articolo e da una testata media
     *
     * @param testata
     * @param articolo l'articolo di cui creare dto
     * @return
     */
    public PianoMediaDettaglioDTO createPianoMediaDettaglioDTO(PianoMediaEntity testata, @NonNull PianoMediaPromoArticoliDbPromoEntity articolo) {
        return createPianoMediaDettaglioDTO(testata, null, articolo, getDifferenziazionePerZona(articolo));
    }

    /**
     * A partire da una testata e un dettaglio di pianificazione crea una lista di PianoMediaDettaglioDTO andando a verificare
     * la presenza dei dettagli articoli e della loro differenziazione per zona
     *
     * @param testata
     * @param dettaglioPianificazione
     * @return
     */
    public List<PianoMediaDettaglioDTO> createPianoMediaDettaglioDTO(PianoMediaEntity testata,
                                                                     @NonNull PianoMediaPianificazioneDettaglioEntity dettaglioPianificazione) {
        List<PianoMediaDettaglioDTO> result = new ArrayList<>();
        final PianoMediaPromoArticoliDbPromoEntity articolo = pianoMediaPromoArticoliDbPromoService.get()
                .findByCodiceItemAndCodicePromoAndTipoItem(dettaglioPianificazione.getCodiceItem(),
                        dettaglioPianificazione.getCodicePromozione(), dettaglioPianificazione.getTipoItem());
        result.add(createPianoMediaDettaglioDTO(testata, dettaglioPianificazione, articolo));
        return result;
    }

    protected PianoMediaDettaglioDTO createPianoMediaDettaglioDTO(PianoMediaEntity testata,
                                                                  PianoMediaPianificazioneDettaglioEntity dettaglioPianificazione,
                                                                  PianoMediaPromoArticoliDbPromoEntity articolo) {
        return createPianoMediaDettaglioDTO(testata, dettaglioPianificazione, articolo, getDifferenziazionePerZona(articolo));
    }

    public PianoMediaDettaglioDTO createPianoMediaDettaglioDTO(PianoMediaEntity testata,
                                                               PianoMediaPianificazioneDettaglioEntity dettaglioPianificazione,
                                                               PianoMediaPromoArticoliDbPromoEntity articolo,
                                                               List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglioArticolo
    ) {
        PianoMediaDettaglioDTO dto = new PianoMediaDettaglioDTO(dettaglioPianificazione, articolo, dettaglioArticolo);
        if (testata != null) {
            dto.setPianoMedia(testata);
        }
        return dto;
    }

    protected List<PianoMediaPromoArticoliDettaglioDbPromoEntity> getDifferenziazionePerZona(PianoMediaPromoArticoliDbPromoEntity articolo) {
        if (articolo != null)
            return pianoMediaPromoArticoliDettaglioDbPromoService.get().findByCodicePromoAndItemAndTipoItem(
                    articolo.getCodicePromozione(),
                    articolo.getCodiceItem(),
                    articolo.getTipoItem()
            );
        return null;
    }
}

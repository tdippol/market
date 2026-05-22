package com.axiante.mui.webapp.business.data;

import com.axiante.mui.dbpromo.persistence.entities.PianificazioneAnagraficaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPianificazioneDettaglioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import com.axiante.mui.webapp.webservice.util.serializers.CommaSeparatedStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * La classe e' utilizzata per la gestione della pianificazione di un piano media e rappresenta l'unione di una entry di
 * dettaglio di pianificazione di piano media ed i relativi lookup sulle viste degli articoli e dei dettagli degli
 * articoli.
 *
 * Sono presenti dei metodi di utility per recuperare direttamente i valori dei campi di dettaglio pianificazione e le
 * informazioni principali sulla differenziazione per zona del dettaglio articolo.
 *
 * In caso di valorizzazione di uno o piu' valori del dettaglio di pianificazione vienw create una entry di tipo
 * <pre>PianoMediaPianificazioneDettaglioEntity.class</pre> .
 *
 * Sono anche presenti due metodi di utility che permettono di sapere se l'entry di pianificazione e' nuova e se e'
 * eventualmente completa e pronta per la persistenza.
 *
 *
 *
 */
@Slf4j
public class PianoMediaDettaglioDTO {

    protected List<PianoMediaPromoArticoliDettaglioDbPromoDTO> dettaglioArticoloLookup;

    @Setter
    @Getter(AccessLevel.PROTECTED)
    protected PianoMediaPromoArticoliDbPromoEntity articoloLookup;

    @Getter
    protected PianoMediaPianificazioneDettaglioEntity dettaglioPianificazione;

    protected HashMap<Long, PianificazioneAnagraficaPianoMediaEntity> anagraficheMedia;

    /**
     * Campo calcolato al set del dettaglio articolo
     */
    @Getter
    private boolean differenziato = false;

    /**
     * Campo calcolato al set del dettaglio articolo
     */
    @Getter
    private boolean flVolantino = false;

    /**
     * Campo calcolato al set del dettaglio articolo
     */
    @Getter
    private Date dataInizio = null;

    /**
     * Campo calcolato al set del dettaglio articolo
     */
    @Getter
    private Date dataFine = null;

    @Getter
    @JsonSerialize(using = CommaSeparatedStringSerializer.class)
    private List<String> zone = new ArrayList<>();


    @Getter
    private boolean nuovaPianificazione = false;
    //TODO: manca la lista del dettaglio dei media


    public PianoMediaDettaglioDTO(
            PianoMediaPianificazioneDettaglioEntity dettaglio,
            PianoMediaPromoArticoliDbPromoEntity articolo,
            List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglioArticolo
    ) {
        dettaglioPianificazione = dettaglio;
        articoloLookup = articolo;
        setDettaglioArticoloLookup(dettaglioArticolo);
    }

    public PianoMediaDettaglioDTO() {
        dettaglioPianificazione = null;
        articoloLookup = null;
        dettaglioArticoloLookup = null;
    }


    public void setDettaglioArticoloLookup(List<PianoMediaPromoArticoliDettaglioDbPromoEntity> dettaglioArticoloLookup) {
        this.dettaglioArticoloLookup = from(dettaglioArticoloLookup);
    }


    public void setCodicePromozione(@NonNull String codicePromozione) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
        }
        getDettaglioPianificazione().setCodicePromozione(codicePromozione);
    }

    public String getCodicePromozione() {
        if (getArticoloLookup() != null) {
            return getArticoloLookup().getCodicePromozione();
        } else if (getDettaglioPianificazione() != null) {
            return getDettaglioPianificazione().getCodicePromozione();
        }
        return null;
    }

    public void setCodiceItem(@NonNull String codiceItem) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
        }
        dettaglioPianificazione.setCodiceItem(codiceItem);
    }

    public String getCodiceItem() {
        if (getArticoloLookup() != null) {
            return getArticoloLookup().getCodiceItem();
        } else if (getDettaglioPianificazione() != null) {
            return getDettaglioPianificazione().getCodiceItem();
        }
        return null;
    }


    public void setTipoItem(@NonNull String tipoItem) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
        }
        dettaglioPianificazione.setTipoItem(tipoItem);
    }

    public String getTipoItem() {
        if (getArticoloLookup() != null) {
            return getArticoloLookup().getTipoItem();
        } else if (getDettaglioPianificazione() != null) {
            return getDettaglioPianificazione().getTipoItem();
        }
        return null;
    }

    public PianoMediaEntity getPianoMedia() {
        if (getDettaglioPianificazione() == null) {
            return null;
        }
        return dettaglioPianificazione.getPianoMedia();
    }

    public void setPianoMedia(@NonNull PianoMediaEntity pianoMedia) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
            dettaglioPianificazione.setPianoMedia(pianoMedia);
        } else {
            if (dettaglioPianificazione.getPianoMedia() == null) {
                dettaglioPianificazione.setPianoMedia(pianoMedia);
            } else {
                if (!dettaglioPianificazione.getPianoMedia().equals(pianoMedia)) {
                    throw new IllegalArgumentException("Piano media gia' valorizzato");
                }
            }
        }
    }

    public Long getId() {
        return dettaglioPianificazione != null ? dettaglioPianificazione.getId() : null;
    }

    public String getCodiceArticolo() {
        if (articoloLookup == null) {
            return null;
        }
        return articoloLookup.getCodiceArticolo();
    }

    public String getDescrizioneArticolo() {
        if (articoloLookup == null) {
            return null;
        }
        return articoloLookup.getDescrizione();
    }

    public String getArticolo() {
        if (articoloLookup == null) return null;
        return articoloLookup.getDescrizione() == null
                ? articoloLookup.getCodiceArticolo()
                : String.format("%s - %s", articoloLookup.getCodiceArticolo(), articoloLookup.getDescrizione());
    }

    public String getGrm() {
        if (articoloLookup == null) return null;
        return articoloLookup.getDescrizioneGrm() == null
                ? articoloLookup.getCodiceGrm()
                : String.format("%s - %s", articoloLookup.getCodiceGrm(), articoloLookup.getDescrizioneGrm());
    }

    public String getCodiceGrm() {
        return articoloLookup != null ? articoloLookup.getCodiceGrm() : null;
    }

    public String getDescGrm() {
        return articoloLookup != null ? articoloLookup.getDescrizioneGrm() : null;
    }

    public String getSubGrm() {
        if (articoloLookup == null) return null;
        return articoloLookup.getDescrizioneSubGrm() == null
                ? articoloLookup.getCodiceSubGrm()
                : String.format("%s - %s", articoloLookup.getCodiceSubGrm(), articoloLookup.getDescrizioneSubGrm());
    }

    public String getCodiceSubGrm() {
        return articoloLookup != null ? articoloLookup.getCodiceSubGrm() : null;
    }

    public String getDescSubGrm() {
        return articoloLookup != null ? articoloLookup.getDescrizioneSubGrm() : null;
    }

    public String getCategoria() {
        if (articoloLookup == null) return null;
        return articoloLookup.getDescrizioneCategoria() == null
                ? articoloLookup.getCodiceCategoria()
                : String.format("%s - %s", articoloLookup.getCodiceCategoria(), articoloLookup.getDescrizioneCategoria());
    }

    public String getCodiceCategoria() {
        return articoloLookup != null ? articoloLookup.getCodiceCategoria() : null;
    }

    public String getDescCategoria() {
        return articoloLookup != null ? articoloLookup.getDescrizioneCategoria() : null;
    }

    public String getReparto() {
        if (articoloLookup == null) return null;
        return articoloLookup.getDescrizioneReparto() == null
                ? articoloLookup.getCodiceReparto()
                : String.format("%s - %s", articoloLookup.getCodiceReparto(), articoloLookup.getDescrizioneReparto());
    }

    public String getCodiceReparto() {
        return articoloLookup != null ? articoloLookup.getCodiceReparto() : null;
    }

    public String getDescReparto() {
        return articoloLookup != null ? articoloLookup.getDescrizioneReparto() : null;
    }

    public String getCompratore() {
        if (articoloLookup == null) return null;
        return articoloLookup.getDescrizioneCompratore() == null
                ? articoloLookup.getCodiceCompratore()
                : String.format("%s - %s", articoloLookup.getCodiceCompratore(), articoloLookup.getDescrizioneCompratore());
    }

    public String getCodiceCompratore() {
        return articoloLookup != null ? articoloLookup.getCodiceCompratore() : null;
    }

    public String getDescCompratore() {
        return articoloLookup != null ? articoloLookup.getDescrizioneCompratore() : null;
    }

    public Integer getPezzi() {
        return articoloLookup == null ? null : articoloLookup.getPezzi();
    }

    public Double getFatturato() {
        return articoloLookup == null ? null : articoloLookup.getFatturato();
    }

    /**
     * Campo calcolato al set del dettaglio articolo
     * Considerare solo per non differenziato
     */
    public String getCodiceMeccanica() {
        return isDifferenziato()
                ? null
                : dettaglioArticoloLookup != null && !dettaglioArticoloLookup.isEmpty()
                ? dettaglioArticoloLookup.get(0).getCodiceMeccanica()
                : null;
    }

    /**
     * Campo calcolato al set del dettaglio articolo
     * Considerare solo per non differenziato
     */
    public String getCodiceCondizione() {
        return isDifferenziato()
                ? null
                : dettaglioArticoloLookup != null && !dettaglioArticoloLookup.isEmpty()
                ? dettaglioArticoloLookup.get(0).getCodiceCondizione()
                : null;
    }

    /**
     * Campo calcolato al set del dettaglio articolo
     * Considerare solo per non differenziato
     */
    public String getValoreOfferta() {
        return isDifferenziato()
                ? null
                : dettaglioArticoloLookup != null && !dettaglioArticoloLookup.isEmpty()
                ? dettaglioArticoloLookup.get(0).getValore()
                : null;
    }

    /**
     * Ritorna se il DTO non contiene una entry di lookup
     * per l'articolo.
     *
     * @return
     */
    public boolean articoloRemoved() {
        return articoloLookup == null;
    }

    public boolean validateDettaglio() {
        return dettaglioPianificazione != null &&
                dettaglioPianificazione.getCodiceItem() != null &&
                dettaglioPianificazione.getCodicePromozione() != null &&
                dettaglioPianificazione.getTipoItem() != null &&
                dettaglioPianificazione.getPianoMedia() != null;
    }


    public boolean isCivetta() {
        if (getDettaglioPianificazione() == null) {
            return false;
        }
        return dettaglioPianificazione.getCivetta();
    }

    public void setCivetta(boolean civetta) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
        }
        dettaglioPianificazione.setCivetta(civetta);
    }

    public Integer getPezzoRank() {
        if (getDettaglioPianificazione() == null) {
            return null;
        }
        return dettaglioPianificazione.getPezzoRank();
    }

    public void setPezzoRank(Integer pezzoRank) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
        }
        dettaglioPianificazione.setPezzoRank(pezzoRank);
    }

    public Integer getFatturatoRank() {
        if (getDettaglioPianificazione() == null) {
            return null;
        }
        return dettaglioPianificazione.getFatturatoRank();
    }

    public void setFatturatoRank(Integer fatturatoRank) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
        }
        dettaglioPianificazione.setFatturatoRank(fatturatoRank);
    }

    public String getNoteCompratore() {
        if (getDettaglioPianificazione() == null) {
            return null;
        }
        return dettaglioPianificazione.getNoteCompratore();
    }

    public void setNoteCompratore(String noteCompratore) {
        if (getDettaglioPianificazione() == null) {
            dettaglioPianificazione = new PianoMediaPianificazioneDettaglioEntity();
            nuovaPianificazione = true;
        }
        dettaglioPianificazione.setNoteCompratore(noteCompratore);
    }

    public List<PianoMediaPromoArticoliDettaglioDbPromoDTO> getDettaglioArticolo() {
        return dettaglioArticoloLookup;
    }

    // helper methods

    /**
     * Trasforma la lista di dettaglio articolo in una lista di DTO e calcola se il dettaglio e' differenziato. Se il
     * dettaglio e' differenziato allora valorizza flVolantino, dataInizio e dataFine con i valori : <ul>
     * <li>flVolantino = true se almeno un dettaglio ha flVolantino = true</li>
     * <li>dataInizio = min(dataInizio) dei dettagli</li>
     * <li>dataFine = max(dataFine) dei dettagli</li>
     * </ul>
     * Se il dettaglio non e' differenziato allora valorizza flVolantino, dataInizio e dataFine con i valori del primo
     * valore disponibile.
     * Se la lista e' vuota allora valorizza flVolantino a folse, dataInizio e dataFine a null e differenziato a false.
     *
     * @param entities
     * @return
     */
    protected List<PianoMediaPromoArticoliDettaglioDbPromoDTO> from(List<PianoMediaPromoArticoliDettaglioDbPromoEntity> entities) {
        List<PianoMediaPromoArticoliDettaglioDbPromoDTO> ret = new ArrayList<>();
        if (entities != null && !entities.isEmpty()) {
            for (PianoMediaPromoArticoliDettaglioDbPromoEntity entity : entities) {
                PianoMediaPromoArticoliDettaglioDbPromoDTO dto = new PianoMediaPromoArticoliDettaglioDbPromoDTO(entity);
                if (ret.contains(dto)) {
                    ret.get(ret.indexOf(dto)).merge(dto);
                } else {
                    ret.add(dto);
                }
            }
            // adesso che ho fatto i conti controllo la differenziazione
            if (ret.size() > 1) {
                for (int i = 0; i < ret.size() - 1; i++) {
                    if (ret.get(i).isDifferenziato(ret.get(i + 1))) {
                        differenziato = true;
                        break;
                    }
                }
            }
        } else {
            differenziato = false;
        }

        if (!differenziato) {
            if (ret.size() > 0) {
                flVolantino = ret.get(0).getFlVolantino();
                dataInizio = ret.get(0).getDataInizio();
                dataFine = ret.get(0).getDataFine();
            } else {
                flVolantino = false;
                dataInizio = null;
                dataFine = null;
            }
        } else {
            flVolantino = ret.stream().map(PianoMediaPromoArticoliDettaglioDbPromoDTO::getFlVolantino)
                    .reduce((a, b) -> a || b).orElse(false);
            dataFine = ret.stream().map(PianoMediaPromoArticoliDettaglioDbPromoDTO::getDataFine)
                    .max(Date::compareTo).orElse(null);
            dataInizio = ret.stream().map(PianoMediaPromoArticoliDettaglioDbPromoDTO::getDataInizio)
                    .min(Date::compareTo).orElse(null);
            zone = ret.stream().map(PianoMediaPromoArticoliDettaglioDbPromoDTO::getZona).collect(Collectors.toList());
        }
        return ret;
    }


    public void setDettaglioPianificazione(PianoMediaPianificazioneDettaglioEntity dettaglioPianificazione) {
        this.dettaglioPianificazione = dettaglioPianificazione;
        mapAnagrafiche();
    }

    /**
     * Mappa le anagrafiche media pianificate per id media in
     * modo da facilitare l'accesso in modifica dalla pagina di
     * pianificazione di dettaglio
     */
    protected void mapAnagrafiche() {
        if (this.dettaglioPianificazione == null || dettaglioPianificazione.getPianificazioniAnagrafiche() == null) {
            this.anagraficheMedia = null;
        } else {
            this.anagraficheMedia = new HashMap<>();
            dettaglioPianificazione.getPianificazioniAnagrafiche()
                    .stream()
                    .filter(anagrafica->anagrafica.getPianificazioneMedia() != null)
                    .forEach(anagrafica -> anagraficheMedia.put(anagrafica.getPianificazioneMedia().getId(), anagrafica));
        }
    }

    /**
     * Set del valore attivo <pre>true/false</pre> di una anagrafica media pianificata
     * in pianificazione di dettaglio per l'articolo.
     * @param id
     * @param attiva
     */
    public void attivaAnagrafica(@NonNull Long id, boolean attiva) {
        if ( anagraficheMedia != null ) {
            PianificazioneAnagraficaPianoMediaEntity anagrafica = anagraficheMedia.get(id);
            if (anagrafica == null) {
                log.error(String.format("Anagrafica %s non presente in pianificazione ", id));
            } else {
                anagrafica.setAttivo(attiva);
            }
        } else {
            log.error("Nessuna anagrafica media configurata");
        }
    }

    /**
     * temporaneo: al momento ritorna la lista dei nomi colonna
     * da aggiungere nel columndef dinamico per la visualizzazione
     * della pianificazione di dettaglio
     * @return
     */
    public List<String> getColonneDinamiche(){
        List<String> list = new ArrayList<>();
        if ( anagraficheMedia != null ){
            anagraficheMedia.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)// prendo le anagrafiche pianificate
                    .collect(Collectors.groupingBy(e -> e.getPianificazioneMedia().getSupportoMedia().getId()))// le raggruppo per anagrafica
                    .forEach((key, valueList) -> { // per ogni anagrafica (se ci sono + blocchetti nel gantt)
                // Ordino lista
                valueList.sort(Comparator.comparing(pianificazioneAnagrafica->pianificazioneAnagrafica.getPianificazioneMedia().getDataInizio()));
                // Itero lista per costruire 'text' e aggiungere a nome colonna
                for (int i = 0; i < valueList.size(); i++) {
                    list.add(
                            String.format("%s - %d",valueList.get(i).getPianificazioneMedia().getSupportoMedia().getDescrizione(),i)
                    );
                }
            });
        }
        return list;
    }
}

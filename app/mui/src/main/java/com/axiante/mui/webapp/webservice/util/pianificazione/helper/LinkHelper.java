package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class LinkHelper {

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    /**
     * Ritorna i valori disponibili presenti nella lista di partenza, escludendo quelli gia' utilizzati nelle pianificazioni
     * con stesso anno e canale della testata, stessa meccanica e date in sovrapposizione rispetto alla pianificazione
     * di riferimento
     *
     * @param lista          lista di partenza dalla quale estrarre i valori
     * @param pianificazione pianificazione di riferimento
     * @return valori disponibili
     */
    public List<String> getAvailableValues(String lista, PromozionePianificazioneEntity pianificazione) {
        final PicklistUtils picklistUtils = new PicklistUtils();
        List<Integer> values = picklistUtils.defineListaCellEditorAsInts(lista);
        if (values == null || values.isEmpty()) {
            log.error(String.format("Errore durante la definizione della lista %s", lista));
            return Collections.emptyList();
        }
        final PromozioneTestataEntity promozione = pianificazione.getPromozioneTestataEntity();
        if (promozione == null) {
            log.error(String.format("Nessuna promozione associata alla pianificazione con id %d", pianificazione.getId()));
            return Collections.emptyList();
        }
        final List<PromozionePianificazioneEntity> overlapping = pianificazioneServiceInstance.get()
                .findOverlappingByAnnoCanaleMeccanica(pianificazione, promozione.getAnno(),
                        promozione.getMuiCanalePromozione(), pianificazione.getMeccanicaEntity());
        // recupero i valori del campo 'LINK' gia' utilizzati
        final List<Integer> usedValues = overlapping.stream().map(PromozionePianificazioneEntity::getLink)
                .filter(Objects::nonNull)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        // e li rimuovo dalla lista iniziale
        values.removeAll(usedValues);
        Collections.sort(values);
        return values.stream().map(value -> String.format("%04d", value)).collect(Collectors.toList());
    }
}

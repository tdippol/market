package com.axiante.mui.webapp.business;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.persistence.entity.PianoMediaSecurityEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.PianoMediaSecurityService;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianoMediaSecurityUtils {

    @Inject
    private Instance<PianoMediaSecurityService> pianoMediaSecurityServiceInstance;

    /**
     * Controllo abilitazione alla pubblicazione di un piano media
     * Se almeno uno dei gruppi associati all'utente ha il flag AccessoPianificazione=W, flag FiltroArticoli=false
     * e flag PianificazioneCompratore=false ritorna true, altrimenti false
     *
     * @param user utente da controllare
     * @return true se almeno uno dei gruppi associati all'utente ha il flag AccessoPianificazione=W
     * , flag FiltroArticoli=false e flag PianificazioneCompratore false, false altrimenti
     */
    public boolean canPublishPianoMedia(@NonNull UsersEntity user) {
        if ( user.isAdmin() ) return true;
        try {
            final List<PianoMediaSecurityEntity> securities = pianoMediaSecurityServiceInstance.get().findByUser(user);
            return securities.stream().anyMatch(s -> s.getAccessoPianificazione().isEditable())
                    && securities.stream().noneMatch(s -> s.getPianificazioneCompratore() && s.getFiltroArticoli());
        } catch (Exception ex) {
            log.error("Error getting write access to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo abilitazione approvazione o non approvazione articoli in pianificazione
     *
     * @param user               utente da controllare
     * @param modalitaCompratore true modalita compratore attiva, false disattiva
     * @return true se utente ha flag AccessoPianificazione=W e flag PianificazioneCompratore=true, false altrimenti
     */
    public boolean canApproveItems(@NonNull UsersEntity user, boolean modalitaCompratore) {
        if (!modalitaCompratore) {
            return false;
        }
        try {
            if ( user.isAdmin() ) return true;
            return pianoMediaSecurityServiceInstance.get().findByUser(user).stream()
                    .anyMatch(s -> s.getAccessoPianificazione().isEditable() && s.getPianificazioneCompratore());
        } catch (Exception ex) {
            log.error("Error getting approve access to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo accesso in scrittura alla 'Scheda Piano Media'
     * Se almeno uno dei gruppi associati all'utente ha il flag AccessoScheda=W ritorna true, altrimenti ritorna false
     *
     * @param user utente da controllare
     * @return true se almeno uno dei gruppi associati ha flag AccessoScheda=W, false altrimenti
     */
    public boolean canWriteScheda(@NonNull UsersEntity user) {
        try {
            return pianoMediaSecurityServiceInstance.get().findByUser(user).stream()
                    .anyMatch(s -> s.getAccessoScheda().isEditable());
        } catch (Exception ex) {
            log.error("Error getting write access to 'Scheda Piano Media'", ex);
            return false;
        }
    }

    /**
     * Controllo accesso scrittura / modifica alla 'Pianificazione Media'
     * Se almeno uno dei gruppi associati all'utente ha il flag AccessoPianificazione=W ritorna true, altrimenti false
     *
     * @param user utente da controllare
     * @return true se almeno uno dei gruppi associati all'utente ha il flag AccessoPianificazione=W, false altrimenti
     */
    public boolean canWritePianificazione(@NonNull UsersEntity user) {
        try {
            return pianoMediaSecurityServiceInstance.get().findByUser(user).stream()
                    .anyMatch(s -> s.getAccessoPianificazione().isEditable());
        } catch (Exception ex) {
            log.error("Error getting write access to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo accesso scrittura / modifica al compo 'Note' della 'Pianificazione Media'
     * Se tutti i gruppi associati all'utente hanno il flag AccessoPianificazione=R
     * e almeno uno ha flag PianificazioneCompratore=true e modalita' compratore=ON ritorna true, altrimenti ritorna false
     *
     * @param user               utente da controllare
     * @param modalitaCompratore true modalita compratore attiva, false disattiva
     * @return  false se modalita' compratore false oppure, se modalita' compratore true, se ogni gruppo in scrittura ha
     * il flag pianificazione compratore true.
     */
    public boolean canWritePianificazioneNoteOnly(@NonNull UsersEntity user, boolean modalitaCompratore) {
        try {
            if ( modalitaCompratore ) {
                final List<PianoMediaSecurityEntity> securities = pianoMediaSecurityServiceInstance.get().findByUser(user);
                // se tutti i gruppi che hanno accesso in scrittura
                final List<PianoMediaSecurityEntity> accessiScrittura = securities.stream().filter(s->s.getAccessoPianificazione().isEditable()).collect(Collectors.toList());
                if ( accessiScrittura.isEmpty()){
                    // non ha accesso in scrittura
                    return false;
                }
                // se almeno un accesso in scrittura non ha il flag pianificazione compratore true allora scrive tutto
                final boolean scriveTutto = accessiScrittura.stream().anyMatch(s->!s.getPianificazioneCompratore());
                return !scriveTutto;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.error("Error getting write access on 'Note' to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo livello scrittura pianificazione
     * Con modalita' compratore attiva e flag AccessoPianificazione=W
     * - utente e' compratore può scrivere solo le note
     * - utente non e' compratore può scrivere tutto
     * Con modalita' compratore non attiva e flag AccessoPianificazione=W
     * - utente e' compratore non può scrivere
     * - utente non e' compratore può scrivere tutto
     *
     * @param user               utente da controllare
     * @param modalitaCompratore true modalita' compratore attiva, false no
     * @return livello scrittura pianificazione: NOTES scrive solo le note, ALL scrive tutto, NONE non scrive (solo lettura)
     */
    public PianificazioneMediaWriteLevelEnum getPianificazioneMediaWriteLevel(@NonNull UsersEntity user, boolean modalitaCompratore) {
        if ( user.isAdmin() ) return PianificazioneMediaWriteLevelEnum.ALL;
        if (modalitaCompratore) {
            if (isBuyer(user)) {
                return PianificazioneMediaWriteLevelEnum.NOTES;
            }
        } else {
            if (isBuyer(user)) {
                return PianificazioneMediaWriteLevelEnum.NONE;
            }
        }
        return canWritePianificazione(user)
                ? PianificazioneMediaWriteLevelEnum.ALL
                : PianificazioneMediaWriteLevelEnum.NONE;
    }

    /**
     * Controllo accesso visualizzazione TUTTI articoli in 'Pianificazione Media'
     * Se almeno uno dei gruppi associati all'utente ha il flag FiltroArticoli=false ritorna true, altrimenti ritorna false
     *
     * @param user utente da controllare
     * @return true se almeno uno dei gruppi associati ha il flag FiltroArticoli=false, false altrimenti
     */
    public boolean canViewAllItems(@NonNull UsersEntity user) {
        try {
            return pianoMediaSecurityServiceInstance.get().findByUser(user).stream()
                    .anyMatch(s -> Boolean.FALSE.equals(s.getFiltroArticoli()));
        } catch (Exception ex) {
            log.error("Error getting view access on all items to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo accesso visualizzazione PROPRI articoli in 'Pianificazione Media'
     * Se tutti i gruppi associati all'utente hanno il flag FiltroArticoli=true ritorna true, altrimenti ritorna false
     *
     * @param user utente da controllare
     * @return true se tutti i gruppi associati hanno il flag FiltroArticoli=true, false altrimenti
     */
    public boolean canViewOwnItemsOnly(@NonNull UsersEntity user) {
        try {
            return pianoMediaSecurityServiceInstance.get().findByUser(user).stream()
                    .allMatch(s -> Boolean.TRUE.equals(s.getFiltroArticoli()));
        } catch (Exception ex) {
            log.error("Error getting view access on own items to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo accesso inserimento TUTTI articoli in 'Pianificazione Media'
     * Se almeno un gruppo associato all'utente ha il flag AccessoPianificazione=W e flag FiltroArticoli=false ritorna true,
     * altrimenti ritorna false
     *
     * @param user utente da controllare
     * @return true se almeno un gruppo associato all'utente ha il flag AccessoPianificazione=W e flag FiltroArticoli=false,
     * altrimenti false
     */
    public boolean canAddAllItems(@NonNull UsersEntity user) {
        try {
            return pianoMediaSecurityServiceInstance.get().findByUser(user).stream()
                    .anyMatch(s -> s.getAccessoPianificazione().isEditable() && Boolean.FALSE.equals(s.getFiltroArticoli()));
        } catch (Exception ex) {
            log.error("Error getting access on add all items to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo accesso inserimento PROPRI articoli in 'Pianificazione Media'
     * Se tutti i gruppi associati all'utente con flag AccessoPianificazione=W hanno flag FiltroArticoli=true ritorna true,
     * altrimenti ritorna false
     *
     * @param user utente da controllare
     * @return true se tutti i gruppi associati all'utente con flag AccessoPianificazione=W hanno flag FiltroArticoli=true,
     * altrimenti false
     */
    public boolean canAddOwnItemsOnly(@NonNull UsersEntity user) {
        try {
            final List<PianoMediaSecurityEntity> allWriteSec = pianoMediaSecurityServiceInstance.get().findByUser(user).stream()
                    .filter(s -> s.getAccessoPianificazione().isEditable())
                    .collect(Collectors.toList());
            return !allWriteSec.isEmpty() && allWriteSec.stream().allMatch(s -> Boolean.TRUE.equals(s.getFiltroArticoli()));
        } catch (Exception ex) {
            log.error("Error getting access on own all items to 'Pianificazione Media'", ex);
            return false;
        }
    }

    /**
     * Controllo accesso inserimento articoli in 'Pianificazione Media', sia PROPRI che TUTTI
     * Un metodo generico che non da importanza agli articoli propri o tutti
     *
     * @param user utente da controllare
     * @return true se l'utente puo' inserire articoli (senza distinguire tra PROPRI e TUTTI), altrimenti false
     */
    public boolean canAddItems(@NonNull UsersEntity user) {
        return canAddOwnItemsOnly(user) || canAddAllItems(user);
    }

    /**
     * Controllo editabilita' azioni Gannt
     *
     * @param pianoMedia piano media da controllare
     * @return true se le azioni di editing sono possibili, false altrimenti
     */
    public boolean isGanntEditable(@NonNull PianoMediaEntity pianoMedia) {
        PianoMediaStatoEntity stato = pianoMedia.getPianoMediaStati().stream()
                .filter(p -> p.getDataFineStato() == null).findFirst().orElse(null);
        switch (stato.getStato().getCodiceStato()) {
            case "10":
            case "30":
            case "300":
            case "410":
                return true;

        }
        return false;
    }

    /**
     * Controllo accesso al tab 'Controllo Compratori' in 'Pianificazione'
     * L'accesso e' possibile se la modalita' compratore e' attiva e  l'utente ha almeno un gruppo associato
     * con flag AccessoPianificazione=W e non e' un compratore
     *
     * @param user               utente da controllare
     * @param modalitaCompratore
     * @return true se l'utente accede al tab, false altrimenti
     */
    public boolean canAccessCheckBuyers(@NonNull UsersEntity user, boolean modalitaCompratore) {
        return modalitaCompratore && canWritePianificazione(user) && !isBuyer(user);
    }

    /**
     * Controllo se utente e' un 'Compratore'
     * Un utente e' compratore se in sicurezza ha almeno un groppo con AccessoPianificazione=W e PianificazioneCompratore=true
     * e nessun gruppo con AccessoPianificazione=W e PianificazioneCompratore=false
     *
     * @param user utente da controllare
     * @return true se utente e' 'Compratore', false altrimenti
     */
    public boolean isBuyer(@NonNull UsersEntity user) {
        try {
            final List<PianoMediaSecurityEntity> securities = pianoMediaSecurityServiceInstance.get().findByUser(user);
            return securities.stream()
                    .noneMatch(s -> s.getAccessoPianificazione().isEditable() && !s.getPianificazioneCompratore())
                    && securities.stream()
                    .anyMatch(s -> s.getAccessoPianificazione().isEditable() && s.getPianificazioneCompratore());
        } catch (Exception ex) {
            log.error("Error checking if user i a 'Buyer'", ex);
            return false;
        }
    }

    public PianificazioneMediaWriteLevelEnum calculateWriteLevel(
            StatoPromozioneEntity statoCorrente, final boolean modalitaCompratore,@NonNull UsersEntity user
    ){
        PianificazioneMediaWriteLevelEnum result = PianificazioneMediaWriteLevelEnum.NONE;
        if ( statoCorrente != null ){
            /*
             * default level è NOTES se l'utente è un buyer e modalita' compratore è on
             * default level è NONE se l'utente è un buyer e modalita' compratore è off
             * altrimenti mi dà il livello dato dalla sicurezza
             */
            final PianificazioneMediaWriteLevelEnum defaultLevel = this.getPianificazioneMediaWriteLevel(user, modalitaCompratore);
            if ( !PianificazioneMediaWriteLevelEnum.NONE.equals(defaultLevel) ){
                // se ho qualche tipo di accesso, questo deve essere comparato con lo stato della pianificazione
                switch (statoCorrente.getCodiceStato()){
                    case "30":
                    case "410":
                        if ( PianificazioneMediaWriteLevelEnum.ALL.equals(defaultLevel) ){
                            result =  PianificazioneMediaWriteLevelEnum.ALL;
                        }
                        break;
                    case "300":
                    case "310":
                    case "320":
                        if (modalitaCompratore) {
                            if ( PianificazioneMediaWriteLevelEnum.NOTES.equals(defaultLevel) || user.isAdmin() ){
                                result =  PianificazioneMediaWriteLevelEnum.NOTES;
                            }
                        }
                        break;
                }
            }
        }

        return result;
    }
}

package com.axiante.mui.webapp.business.service;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaCfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaControlliService;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianoMediaHelperService {

    private static final String ERROR_LEVEL = "Errore";
    private static final String WARNING_LEVEL = "Warning";

    @Inject
    private Instance<PianoMediaControlliFactory> controlliFactoryInstance;

    @Inject
    Instance<PianoMediaControlliService> controlliServiceInstance;

    public int executeChecks(@NonNull PianificazionePianoMediaEntity pianificazioneMedia, @NonNull String username) {
        int failed = 0;
        // Pulisco i controlli per la PianificazionePianoMedia con dato id
        removeExistingChecks(pianificazioneMedia);
        final PianoMediaControlliFactory controlliFactory = controlliFactoryInstance.get();
        // Recupero controlli configurati per il supporto
        for (SupportoMediaCfgCheckEntity check : pianificazioneMedia.getSupportoMedia().getSupportoMediaChecks()) {
            final Boolean passed = controlliFactory.getControlli(check.getCodiceControllo()).apply(pianificazioneMedia);
            if (!passed) {
                // Controllo fallisce, devo inserire a DB
                persist(pianificazioneMedia, check, username);
                ++failed;
            }
        }
        return failed;
    }

    public void removeExistingChecks(@NonNull PianificazionePianoMediaEntity pianificazioneMedia) {
        final List<PianoMediaControlliEntity> checks = controlliServiceInstance.get()
                .findByPianificazioneMedia(pianificazioneMedia);
        removeChecks(checks);
    }

    public void removeExistingChecks(@NonNull PianoMediaEntity pianoMedia) {
        final List<PianoMediaControlliEntity> checks = controlliServiceInstance.get().findByPianoMedia(pianoMedia);
        removeChecks(checks);
    }

    private void removeChecks(List<PianoMediaControlliEntity> checks) {
        for (PianoMediaControlliEntity check : checks) {
            try {
                controlliServiceInstance.get().remove(check);
            } catch (Exception ex) {
                log.error(String.format("Error delete PianoMediaControlliEntity with id %d", check.getId()), ex);
            }
        }
    }

    private void persist(PianificazionePianoMediaEntity pianificazioneMedia, SupportoMediaCfgCheckEntity check,
                         String username) {
        try {
            PianoMediaControlliEntity e = new PianoMediaControlliEntity();
            e.setPianificazioneMedia(pianificazioneMedia);
            e.setSupportoMediaCfgCheck(check);
            e.setDataInserimento(new Date());
            e.setCodiceUtenteInserimento(username);
            controlliServiceInstance.get().persist(e);
        } catch (Exception ex) {
            log.error(String.format("Error saving PianoMediaControlli for PianificazionePianoMedia with id %d",
                    pianificazioneMedia.getId()), ex);
        }
    }
}

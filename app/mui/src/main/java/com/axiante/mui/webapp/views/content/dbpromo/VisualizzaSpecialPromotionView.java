package com.axiante.mui.webapp.views.content.dbpromo;

import org.primefaces.PrimeFaces;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpTestataService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.specialPromotion.SpecialPromotionDialog;
import com.axiante.mui.webapp.webservice.util.ViewSpecialPromotionUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Calendar;

@Slf4j
@MuiViewModel("visualizzaSpecialPromotionView")
@Dependent
public class VisualizzaSpecialPromotionView extends AbstractDBPromoNavigation {

    private static final long serialVersionUID = 1L;

    private static final String INITIAL_STATUS_CODE = "10";
    private static final Long CANALE_SPECIAL_PROMO_ID = 40L;

    @Inject
    @Getter
    private SpecialPromotionDialog specialPromotionDialog;

    @Inject
    private ViewSpecialPromotionUtil viewSpecialPromotionUtil;

    @Inject
    private MuiSpTestataService muiSpTestataService;

    @Inject
    private CanalePromozioneService canalePromozioneService;

    @Inject
    private StatoPromoService statoPromoService;

    public void openCreateDialog() {
        specialPromotionDialog.initForCreate();
    }

    public void saveSpecialPromotion() {
        if (!specialPromotionDialog.validateForSave()) {
            return;
        }
        String userName = getUserDto() != null ? getUserDto().getUsermame() : "SYSTEM";
        try {
            MuiSpTestataEntity testata = specialPromotionDialog.getBean();

            CanalePromozioneEntity canale = canalePromozioneService.findById(CANALE_SPECIAL_PROMO_ID);
            if (canale == null) {
                throw new IllegalStateException("Canale Special Promotion non trovato: " + CANALE_SPECIAL_PROMO_ID);
            }

            StatoPromozioneEntity statoIniziale = statoPromoService.findByCode(INITIAL_STATUS_CODE);
            if (statoIniziale == null) {
                throw new IllegalStateException("Stato iniziale non trovato: " + INITIAL_STATUS_CODE);
            }

            String anno = resolveAnno(testata);

            String codicePromo = muiSpTestataService.calculatePromoCode(anno, canale);

            viewSpecialPromotionUtil.createSpecialPromotion(
                    testata,
                    codicePromo,
                    canale,
                    statoIniziale,
                    userName
            );
            PrimeFaces.current().ajax().addCallbackParam("createdPromoId", testata.getId());
            PrimeFaces.current().ajax().addCallbackParam("landingPage", 1272);  //reindirizza su scheda special promo
            log.error("SP create -> createdPromoId=" + testata.getId() + ", landingPage=" + getLandingPage());


            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Operazione completata",
                            "Special Promotion creata correttamente"
                    )
            );

            //openCreateDialog();

        } catch (Exception e) {
            log.error("Errore durante la creazione della Special Promotion", e);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Errore applicativo",
                            "Impossibile creare la Special Promotion"
                    )
            );
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    private String resolveAnno(MuiSpTestataEntity testata) {
        if (testata.getAnno() != null && !testata.getAnno().trim().isEmpty()) {
            return testata.getAnno();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(testata.getDataInizio());
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Override
    public void applyRules() {
    }

    @Override
    public void applyDefaultRules() {
    }
}
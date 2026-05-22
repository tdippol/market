package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoFatturazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneCostiContattoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoFatturazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneCostiContattoService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.RifatturazioneUtil;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@MuiViewModel("fatturazioneSingolaAttivita")
@Dependent
@Slf4j
public class FatturazioneSingolaAttivita extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = -515268469553145399L;

    @Inject
    private Instance<RifatturazioneUtil> rifatturazioneUtilInstance;

    @Inject
    private Instance<PromoFatturazioneService> promoFatturazioneServiceInstance;

    @Inject
    private Instance<PromozioneCostiContattoService> promoCostiContattoServiceInstance;

    @Getter
    private List<CompratoreEntity> compratori;

    @Getter
    private Long idCompratoreSelected;

    @Getter
    private List<PromozioneTestataEntity> promozioni;

    @Getter
    private Long idPromozioneSelected;

    @Getter
    private String flagRifatturazioneAttivita;

    @Getter
    private Integer numeroContatti;

    @Getter
    private boolean numeroContattiRendered = false;

    private UserDTO userDTO;
    private List<PromoFatturazioneEntity> fatturazioni;

    public void init() {
        userDTO = getUserDto();
        loadRifatturazioni();
        loadCompratori();
        if (compratori.size() == 1) {
            setIdCompratoreSelected(compratori.get(0).getId());
        }
    }

    public void setIdCompratoreSelected(Long idCompratoreSelected) {
        this.idCompratoreSelected = idCompratoreSelected;
        if (idCompratoreSelected != null) {
            loadPromozioni(idCompratoreSelected);
            if (promozioni.size() == 1) {
                setIdPromozioneSelected(promozioni.get(0).getId());
            } else {
                flagRifatturazioneAttivita = null;
                numeroContatti = null;
                numeroContattiRendered = false;
            }
        }
    }

    public void setIdPromozioneSelected(Long idPromozioneSelected) {
        this.idPromozioneSelected = idPromozioneSelected;
        if (idPromozioneSelected != null && idCompratoreSelected != null) {
            loadFlagRifatturazioneAttivita(idPromozioneSelected);
            loadNumeroContatti(idPromozioneSelected);
            executeScript(String.format("loadRifatturazioni(%d, %d)", idCompratoreSelected, idPromozioneSelected));
        } else {
            flagRifatturazioneAttivita = null;
            numeroContatti = null;
            numeroContattiRendered = false;
        }
    }

    private void loadRifatturazioni() {
        fatturazioni = rifatturazioneUtilInstance.get()
                .fatturazioniWithWritableCompratoriByCodiciGruppo(userDTO.getGruppi());
    }

    private void loadCompratori() {
        if (fatturazioni == null || fatturazioni.isEmpty()) {
            compratori = new ArrayList<>();
        } else {
            compratori = fatturazioni.stream()
                    .map(PromoFatturazioneEntity::getCompratore)
                    .distinct()
                    .sorted(Comparator.comparing(CompratoreEntity::getCodiceCompratore))
                    .collect(Collectors.toList());
        }
    }

    private void loadPromozioni(@NonNull Long idCompratore) {
        promozioni = fatturazioni.stream()
                .filter(f -> idCompratore.equals(f.getCompratore().getId()))
                .map(PromoFatturazioneEntity::getPromozione)
                .distinct()
                .collect(Collectors.toList());
    }

    private void loadFlagRifatturazioneAttivita(@NonNull Long idPromozioneSelected) {
        flagRifatturazioneAttivita = promozioni.stream()
                .filter(p -> idPromozioneSelected.equals(p.getId()))
                .flatMap(p -> p.getPromozioneFlags().stream())
                .filter(f -> DbPromoConstants.FLAG_SCONTO_RIFATTURABILE.equalsIgnoreCase(f.getFlag().getCodice()))
                .map(MuiPromozioneFlagEntity::getValore)
                .findFirst()
                .orElse(null);
    }

    private void loadNumeroContatti(@NonNull Long idPromozioneSelected) {
        if (rifatturazioneUtilInstance.get().isRightChannelForNumeroContatti(idPromozioneSelected)) {
            numeroContattiRendered = true;
            PromozioneCostiContattoEntity promoCostiContatto = promoCostiContattoServiceInstance.get()
                    .findByIdPromozione(idPromozioneSelected);
            if (promoCostiContatto == null) {
                log.error(String.format("Costi contatto not found for promozione with id '%d'", idPromozioneSelected));
                numeroContatti = null;
            } else {
                numeroContatti = promoCostiContatto.getNumeroContatti();
            }
        } else {
            numeroContattiRendered = false;
            numeroContatti = null;
        }
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        init();
    }

    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }
}

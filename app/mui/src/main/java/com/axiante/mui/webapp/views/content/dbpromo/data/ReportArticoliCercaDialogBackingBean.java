package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.MarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.views.FacesContextAware;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class ReportArticoliCercaDialogBackingBean implements FacesContextAware {
    @Getter
    private Map<String, String> compratori;

    @Getter
    private Map<String, String> fornitori;

    @Getter
    private Map<String, String> reparti;

    @Getter
    private Map<String, String> categorie;

    @Getter
    private Map<String, String> grm;

    @Getter
    private Map<String, String> marchioPrivato;

    @Getter
    @Setter
    private String idCompratoreCorrente;

    @Getter
    @Setter
    private String idFornitoreCorrente;

    @Getter
    @Setter
    private String idRepartoCorrente;

    @Getter
    @Setter
    private String idCategoriaCorrente;

    @Getter
    @Setter
    private String idGrmCorrente;

    @Getter
    @Setter
    private String idMarchioPrivCorrente;

    @Inject
    private Instance<UserService> userServiceInstance;

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<MuiService> muiServiceInstance;

    @Inject
    private Instance<PianificazioneService> pianificazioneServiceInstance;

    @Inject
    private Instance<MarchioPrivatoService> marchioPrivatoServiceInstance;

    @Inject
    private Instance<ItemService> itemServiceInstance;

    private UserDTO userDTO;

    public void updateSelezioneGrid() {
        executeScript(prepareJsCall());
    }

    public void prepare() {
        userDTO = userServiceInstance.get().asDto(getCurrentUser());
        resetSelections();
        loadComparatori();
        loadFornitori();
        loadReparti();
        loadCategorie();
        loadGrm();
        loadMarchiPrivati();
    }

    public void resetSelections() {
        idCompratoreCorrente = null;
        idFornitoreCorrente = null;
        idRepartoCorrente = null;
        idCategoriaCorrente = null;
        idGrmCorrente = null;
        idMarchioPrivCorrente = null;
    }

    private void loadComparatori() {
        if (compratori == null) {
            compratori = new LinkedHashMap<>();
        } else {
            compratori.clear();
        }
        List<CompratoreEntity> buyers = isUserAdmin()
                ? promoServiceInstance.get().findAllBuyers()
                : promoServiceInstance.get().findAllBuyersByCodes(getBuyerCodes());
        buyers.stream().sorted(Comparator.comparing(CompratoreEntity::getCodiceCompratore))
                .forEach(c -> compratori.put(String.valueOf(c.getId()), String.format("[%s] %s",
                        c.getCodiceCompratore(), c.getDescrizione() != null ? c.getDescrizione() : "")));
    }

    private void loadFornitori() {
        if (fornitori == null) {
            fornitori = new LinkedHashMap<>();
        } else {
            fornitori.clear();
        }
        List<FornitoreEntity> suppliers = isUserAdmin()
                ? promoServiceInstance.get().findAllFornitori()
                : promoServiceInstance.get().findAllFornitoriByCodiciCompratore(getBuyerCodes());
        suppliers.stream().sorted(Comparator.comparing(FornitoreEntity::getCodiceFornitore))
                .forEach(f -> fornitori.put(String.valueOf(f.getId()), String.format("[%s] %s",
                        f.getCodiceFornitore(), f.getDescrizione() != null ? f.getDescrizione().toUpperCase() : "")));
    }

    private void loadReparti() {
        if (reparti == null) {
            reparti = new LinkedHashMap<>();
        } else {
            reparti.clear();
        }
        List<RepartoEntity> departments = isUserAdmin()
                ? pianificazioneServiceInstance.get().getAllReparti()
                : pianificazioneServiceInstance.get().findAllRepartiByCodiciCompratore(getBuyerCodes());
        departments.stream().sorted(Comparator.comparing(RepartoEntity::getCodiceReparto))
                .forEach(r -> reparti.put(String.valueOf(r.getId()), String.format("[%s] %s",
                        r.getCodiceReparto(), r.getDescrizione() != null ? r.getDescrizione().toUpperCase() : "")));
    }

    private void loadCategorie() {
        if (categorie == null) {
            categorie = new LinkedHashMap<>();
        } else {
            categorie.clear();
        }
        List<CategoriaEntity> categories = isUserAdmin()
                ? promoServiceInstance.get().findAllCategorie()
                : promoServiceInstance.get().findAllCategorieByCodiciCompratore(getBuyerCodes());
        categories.stream().sorted(Comparator.comparing(CategoriaEntity::getCodiceCategoria))
                .forEach(c -> categorie.put(String.valueOf(c.getId()), String.format("[%s] %s",
                        c.getCodiceCategoria(), c.getDescrizione() != null ? c.getDescrizione().toUpperCase() : "")));
    }

    private void loadGrm() {
        if (grm == null) {
            grm = new LinkedHashMap<>();
        } else {
            grm.clear();
        }
        List<GrmEntity> grms = isUserAdmin()
                ? pianificazioneServiceInstance.get().getAllGrmEntity()
                : pianificazioneServiceInstance.get().findAllGrmByCodiciCompratore(getBuyerCodes());
        grms.stream().sorted(Comparator.comparing(GrmEntity::getCodiceGrm))
                .forEach(g -> grm.put(String.valueOf(g.getId()), String.format("[GRM_%s] %s",
                        g.getCodiceGrm(), g.getDescrizione() != null ? g.getDescrizione().toUpperCase() : "")));
    }

    private void loadMarchiPrivati() {
        if (marchioPrivato == null) {
            marchioPrivato = new LinkedHashMap<>();
        } else {
            marchioPrivato.clear();
        }
        List<MarchioPrivatoEntity> marchiPrivati = isUserAdmin()
                ? marchioPrivatoServiceInstance.get().findAll()
                : marchioPrivatoServiceInstance.get().findByCodici(getCodiciMarchioPrivato());
        marchiPrivati.stream().sorted(Comparator.comparing(MarchioPrivatoEntity::getKey))
                .forEach(m -> marchioPrivato.put(m.getKey(), String.format("[%s] %s",
                        m.getKey(), m.getLabel())));
    }

    private List<String> getCodiciMarchioPrivato() {
        return itemServiceInstance.get().findCodiceMarchioPrivatoByCompratori(getBuyerCodes());
    }

    private List<String> getBuyerCodes() {
        return muiServiceInstance.get()
                .findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(userDTO.getGruppi(), PianificazioneSecurityEnum.READ);
    }

    private String prepareJsCall() {
        return String.format("updateSelezioneGrid('%s', '%s', '%s', '%s', '%s', '%s')",
                idCompratoreCorrente, idFornitoreCorrente, idRepartoCorrente, idCategoriaCorrente,
                idGrmCorrente, idMarchioPrivCorrente);
    }
}

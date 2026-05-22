package com.axiante.mui.webapp.utils;

import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.FatturazioneService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.webservice.util.pianificazione.PianificazioneEntityPredicates;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class FatturazioneUtil {

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<ItemService> itemServiceInstance;

    @Inject
    private Instance<FatturazioneService> fatturazioneServiceInstance;

    @Inject
    private Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Inject
    private Instance<PianificazioneEntityPredicates> predicatesInstance;

    @Inject
    private Instance<OwnershipService> ownershipServiceInstance;

    public List<ContributiPromoEntity> readAvailableFatturazioni(@NonNull UserDTO userDTO) throws Exception {
        final List<PromozioneTestataEntity> promozioni = readPromozioni(userDTO);
        return fatturazioneServiceInstance.get().findAllByPromozioni(promozioni);
    }

    public List<PromozioneTestataEntity> readPromozioni(@NonNull UserDTO userDTO) throws Exception {
        UserFilterUtils userFilterUtils = new UserFilterUtils();
        final Map<String, String> filters = userFilterUtils.createUserFiltersMapToQueryString(
                userDTO.getUser().getDbFilters());
        final List<PromozioneTestataEntity> testate = promoServiceInstance.get()
                .findAllNotCancelled(filters, userDTO.getCanali());
        if (!userDTO.isAdmin()) {
            final PianificazioneSecurityUtil securityUtil = securityUtilInstance.get();
            testate.removeIf(t -> securityUtil.getReadableCompratori(t, userDTO.getGruppi()).isEmpty());
        }
        return testate;
    }

    public List<PromozioneTestataEntity> readPromozioniWithWritableItems(@NonNull UserDTO userDTO) throws Exception {
        UserFilterUtils userFilterUtils = new UserFilterUtils();
        final Map<String, String> filters = userFilterUtils.createUserFiltersMapToQueryString(
                userDTO.getUser().getDbFilters());
        final List<PromozioneTestataEntity> testate = promoServiceInstance.get()
                .findAllNotCancelled(filters, userDTO.getCanali());
        if (userDTO.isAdmin()) {
            return testate;
        }
        final PianificazioneSecurityUtil securityUtil = securityUtilInstance.get();
        return testate.stream().filter(t -> t.getPromozionePianificazioneEntities().stream()
                .anyMatch(p -> securityUtil.isWriteable(p, userDTO.getGruppi()))).collect(Collectors.toList());
    }

    public List<CompratoreEntity> readCompratoriWithWritableItems(@NonNull PromozioneTestataEntity promozione,
                                                                  @NonNull UserDTO userDTO) {
        final List<Long> idArticoli = getPlannedItemsId(promozione);
        if (userDTO.isAdmin()) {
            return promoServiceInstance.get().findAllBuyersByIdItems(idArticoli);
        }
        final List<String> codiciBuyers = securityUtilInstance.get()
                .getCompratoriWithPlannedItems(promozione, idArticoli, userDTO.getGruppi());
        return codiciBuyers == null || codiciBuyers.isEmpty()
                ? new ArrayList<>()
                : promoServiceInstance.get().findAllBuyersByCodes(codiciBuyers);
    }

    public List<FornitoreEntity> readFornitoriWithPlannedItems(@NonNull PromozioneTestataEntity promozione,
                                                               @NonNull String codiceCompratore) {
        final List<Long> idArticoli = getPlannedItemsId(promozione);
        return itemServiceInstance.get().findByIds(idArticoli).stream()
                .filter(i -> codiceCompratore.equals(i.getCompratoreEntity().getCodiceCompratore()))
                .flatMap(i -> i.getMuiAssortimentoFornitores().stream())
                .map(AssortimentoFornitoreEntity::getFornitoreEntity)
                .distinct()
                .sorted(Comparator.comparing(FornitoreEntity::getCodiceFornitore))
                .collect(Collectors.toList());
    }

    public boolean maxProgressivoReached(@NonNull PromozioneTestataEntity promozione,
                                         @NonNull CompratoreEntity compratore,
                                         @NonNull FornitoreEntity fornitore) {
        final Long maxProgressivo = promozione.getCanalePromozioneEntity().getMaxProgressivo();
        if (maxProgressivo == null || maxProgressivo.equals(0L)) {
            return true;
        }
        final Long count = fatturazioneServiceInstance.get()
                .countByPromozioneAndCompratoreAndFornitore(promozione, compratore, fornitore);
        return count >= maxProgressivo;
    }

    public List<ItemEntity> getBillableItems(@NonNull PromozioneTestataEntity promozione,
                                             @NonNull String codiceCompratore, @NonNull String codiceFornitore) {
        final List<Long> idArticoli = getPlannedItemsId(promozione);
        return itemServiceInstance.get()
                .findByIdsAndCompratoreAndFornitore(idArticoli, codiceCompratore, codiceFornitore);
    }

    public List<ItemEntity> getBillableItems(@NonNull PromozioneTestataEntity promozione,
                                             @NonNull List<String> codiciCompratori, @NonNull String codiceFornitore) {
        final List<Long> idArticoli = getPlannedItemsId(promozione);
        return itemServiceInstance.get()
                .findByIdsAndCompratoriAndFornitore(idArticoli, codiciCompratori, codiceFornitore);
    }

    public boolean isEditable(@NonNull ContributiPromoEntity contributo, @NonNull List<String> codiciGruppi) {
        final List<String> codiciCompratori = ownershipServiceInstance.get().hasOwnership(contributo.getPromozione(), codiciGruppi)
                ? securityUtilInstance.get().getReadableCompratori(contributo.getPromozione(), codiciGruppi)
                : securityUtilInstance.get().getWritableCompratori(contributo.getPromozione(), codiciGruppi);
        return codiciCompratori.contains(contributo.getCompratore().getCodiceCompratore());
    }

    private List<Long> getPlannedItemsId(@NonNull PromozioneTestataEntity promozione) {
        return promozione.getPromozionePianificazioneEntities().stream()
                .filter(predicatesInstance.get().byTipoRiga(PianificazioneRowTypeEnum.ELEMENTO))
                .map(p -> Long.parseLong(p.getCodiceElemento()))
                .collect(Collectors.toList());
    }

    public Integer nextProgressivo(@NonNull PromozioneTestataEntity promozione, @NonNull CompratoreEntity compratore,
                                   @NonNull FornitoreEntity fornitore) {
        return fatturazioneServiceInstance.get()
                .nextProgressivoByPromozioneAndCompratoreAndFornitore(promozione, compratore, fornitore);
    }
}

package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaliAttributiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFlagDominioEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromozioneFlagEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttributiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMarchioPrivatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaliAttributiService;
import com.axiante.mui.dbpromo.persistence.service.MarchioPrivatoService;
import com.axiante.mui.dbpromo.persistence.service.MuiFlagDominioService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromozionePianificazioneEntityHelper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Dependent
@Slf4j
public class PromozioneTestataHelper {

    @Inject
    private Instance<PromozionePianificazioneEntityHelper> entityHelperInstance;

    @Inject
    private Instance<StatoPromoService> statoPromoServiceInstance;

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Inject
    private Instance<MuiFlagDominioService> flagDominioService;

    @Inject
    private Instance<CfgCanaliAttributiService> cfgCanaliAttributiService;

	@Inject
	private Instance<MarchioPrivatoService> marchioPrivatoService;

    @Inject
    private Instance<RepartoService> repartoService;

    public void updateEntity(@NonNull PromozioneTestataEntity testata, String description, String notes, Date startDate,
                             Date endDate, Date startHour, Date endHour, BigDecimal valorePunto) {
        if (!StringUtils.isBlank(description)) {
            testata.setDescrizione(description);
        }
        if (testata.getNoteMarketing() == null || !testata.getNoteMarketing().equals(notes)) {
            testata.setNoteMarketing(notes);
        }
        if (testata.getDataInizio() != null && startDate != null && !testata.getDataInizio().equals(startDate)) {
            testata.setDataInizio(startDate);
        }
        if (testata.getDataFine() != null && endDate != null && !testata.getDataFine().equals(endDate)) {
            testata.setDataFine(endDate);
        }
        if ((testata.getOraInizio() != null && !testata.getOraInizio().equals(startHour))
                || (startHour != null && !startHour.equals(testata.getOraInizio()))) {
            testata.setOraInizio(startHour);
        }
        if ((testata.getOraFine() != null && !testata.getOraFine().equals(endHour))
                || (endHour != null && !endHour.equals(testata.getOraFine()))) {
            testata.setOraFine(endHour);
        }
        if (testata.getDataInizio() != null && testata.getDataFine() != null
                && !StringUtils.isBlank(testata.getDescrizioneEstesa())) {
            final DateTimeUtils dateTimeUtils = new DateTimeUtils();
            testata.setDescrizioneEstesa(dateTimeUtils.calculateExtendedDescription(testata.getCodicePromozione(),
                    testata.getDataInizio(), testata.getDataFine(), testata.getDescrizione()));
        }
        if (testata.getValorePunto() == null || !testata.getValorePunto().equals(valorePunto)) {
            testata.setValorePunto(valorePunto);
        }
    }

    public void adjustDates(@NonNull PromozioneTestataEntity testata, Date newStartDate, Date newEndDate) {
        final boolean updateStartDate = newStartDate != null && testata.getDataInizio() != null
                && !testata.getDataInizio().equals(newStartDate);
        final boolean updateEndDate = newEndDate != null && testata.getDataFine() != null
                && !testata.getDataFine().equals(newEndDate);
        if (updateStartDate || updateEndDate) {
            adjustShopsDates(testata, updateStartDate, updateEndDate, newStartDate, newEndDate);
            adjustPlannigDates(testata, updateStartDate, updateEndDate, newStartDate, newEndDate);
        }
    }

    public void adjustShopsDates(@NonNull PromozioneTestataEntity testata, boolean updateStartDate,
                                 boolean updateEndDate, Date newStartDate, Date newEndDate) {
        testata.getPromozioneNegozioEntities().forEach(negozio -> {
            if (updateStartDate && negozio.getDataInizio().equals(testata.getDataInizio())) {
                negozio.setDataInizio(newStartDate != null ? newStartDate : testata.getDataInizio());
            }
            if (updateEndDate && negozio.getDataFine().equals(testata.getDataFine())) {
                negozio.setDataFine(newEndDate != null ? newEndDate : testata.getDataFine());
            }
        });
    }

    public void adjustPlannigDates(@NonNull PromozioneTestataEntity testata, boolean updateStartDate,
                                   boolean updateEndDate, Date newStartDate, Date newEndDate) {
        final PromozionePianificazioneEntityHelper entityHelper = entityHelperInstance.get();
        // Per ogni pianificazione di "livello alto"
        testata.getPromozionePianificazioneEntities().stream().filter(p -> p.getParent() == null).forEach(p ->
                entityHelper.adjustDates(p, testata.getDataInizio(), testata.getDataFine(), updateStartDate, updateEndDate, newStartDate, newEndDate, null)
        );
    }

    public boolean delete(@NonNull PromozioneTestataEntity testata, @NonNull String username) {
        try {
            final PromozioneStatoEntity lastStatus = testata.getPromozioneStatoEntities().stream()
                    .filter(st -> st.getDataFineStato() == null).findFirst().orElse(null);
            if (lastStatus == null) {
                log.error("Nessun lastStatus per la promozione con id " + testata.getId());
                return false;
            }
            if (!PromoAcl.isDeletable(lastStatus.getStatoPromozioneEntity())
                    || PromoAcl.isCancelled(lastStatus.getStatoPromozioneEntity())) {
                log.error(String.format("Promozione con id %d in stato %s non e' cancellabile", testata.getId(),
                        lastStatus.getStatoPromozioneEntity().getCodiceStato()));
                return false;
            }
            final Date now = new Date();
            lastStatus.setDataFineStato(now);
            final PromozioneStatoEntity statusCancelled = (PromozioneStatoEntity) AuditLogFiller
                    .fillAuditLogFields(new PromozioneStatoEntity(), username);
            statusCancelled.setDataInizioStato(now);
            final StatoPromozioneEntity byCode = statoPromoServiceInstance.get()
                    .findByCode(PromoStatusEnum.CANCELLATA_00.getKey());
            if (byCode == null) {
                log.error(String.format("Errore recupero stato promozione codice %s", PromoStatusEnum.CANCELLATA_00.getKey()));
                return false;
            }
            statusCancelled.setStatoPromozioneEntity(byCode);
            testata.addPromozioneStatoEntity(statusCancelled);
            promoServiceInstance.get().persist(testata, username);
            return true;
        } catch (Exception ex) {
            log.error(String.format("Errore impostatzione stato 'CANCELLATA' su promozione con id %d", testata.getId()), ex);
            return false;
        }
    }

    public void addPromozioneFlags(PromozioneTestataEntity promozione, String user) throws Exception {
        if (promozione.getPromozioneFlags() != null && !promozione.getPromozioneFlags().isEmpty()) {
            throw new IllegalArgumentException("La promozione " + promozione.getDescrizione() + " contiene gia' dei flag");
        }
        Set<CfgCanaleFlagEntity> canaleFlags = promozione.getMuiCanalePromozione().getMuiCfgCanaleFlagEntities();
        if (canaleFlags != null) {
            canaleFlags.stream().filter(cf -> cf.getFlag().getAttivo()).map(CfgCanaleFlagEntity::getFlag).forEach(flag -> {
                MuiPromozioneFlagEntity promoFlag = (MuiPromozioneFlagEntity) AuditLogFiller.fillAuditLogFields(new MuiPromozioneFlagEntity(), user);
                promoFlag.setFlag(flag);
                List<MuiFlagDominioEntity> l = flagDominioService.get().findAllAttiviAndDefaultByFlag(flag.getId());
                if (l != null && !l.isEmpty()) {
                    promoFlag.setValore(l.get(0).getValore());
                } else {
                    promoFlag.setValore(null);
                }
                promozione.addMuiPromozioneFlagEntity(promoFlag);
            });
        } else {
            log.warn("Nessun flag configurato per il canale " + promozione.getMuiCanalePromozione().getCodiceCanale());
        }
    }

    public void addPromozioneAttributi(PromozioneTestataEntity promozione, String user) throws Exception {
        if (promozione.getPromozioneAttributiEntity() != null && !promozione.getPromozioneAttributiEntity().isEmpty()) {
            throw new IllegalArgumentException("La promozione " + promozione.getDescrizione() + " contiene gia' degli attributi di testata");
        }
        List<CfgCanaliAttributiEntity> attributiConfigurati = cfgCanaliAttributiService.get().getAllByCanale(promozione.getCanalePromozioneEntity().getId());
        if (attributiConfigurati != null && !attributiConfigurati.isEmpty()) {
            attributiConfigurati.stream().forEach(att -> {
                PromozioneAttributiEntity attributo = (PromozioneAttributiEntity) AuditLogFiller.fillAuditLogFields(new PromozioneAttributiEntity(), user);
                attributo.setValore(att.getValoreDefault());
                attributo.setAttributo(att.getAttributo());
                promozione.addPromozioneAttributo(attributo);
            });
        } else {
            log.warn("Nessun attributo configurato per il canale " + promozione.getMuiCanalePromozione().getCodiceCanale());
        }
    }

    public void addPromozioneMarchiPrivati(PromozioneTestataEntity promozioneTestataEntity, String user) throws Exception {
		if ( promozioneTestataEntity.getCanalePromozioneEntity().getFlMarchioPrivato()) {
			if (promozioneTestataEntity.getMarchiPrivati() != null && !promozioneTestataEntity.getMarchiPrivati().isEmpty()) {
				throw new IllegalArgumentException("La promozione " + promozioneTestataEntity.getDescrizione() + " contiene gia' dei marchi privati");
			}
			marchioPrivatoService.get().findAll().forEach(marchio -> {
                PromozioneMarchioPrivatoEntity item = (PromozioneMarchioPrivatoEntity)AuditLogFiller.fillAuditLogFields(new PromozioneMarchioPrivatoEntity(), user);
                item.setAttivo(false);
                item.setMarchioPrivato(marchio);
				promozioneTestataEntity.addMarchioPrivato(item);
			});
		} else {
			log.debug("Il canale " + promozioneTestataEntity.getCanalePromozioneEntity().getCodiceCanale() + " non supporta i marchi privati");
		}
    }

    public void addPromozioneRepartiMarchiPrivati(PromozioneTestataEntity promozioneTestataEntity, String user) throws Exception {
        if ( promozioneTestataEntity.getCanalePromozioneEntity().getFlMarchioPrivato()) {
            if (promozioneTestataEntity.getRepartiMarchiPrivati() != null && !promozioneTestataEntity.getRepartiMarchiPrivati().isEmpty()) {
                throw new IllegalArgumentException("La promozione " + promozioneTestataEntity.getDescrizione() + " contiene gia' dei reparti per i marchi privati");
            }
            repartoService.get().findAll().forEach(reparto->{
                PromoRepartoMarchioPrivato item = (PromoRepartoMarchioPrivato)AuditLogFiller.fillAuditLogFields(new PromoRepartoMarchioPrivato(), user);
                item.setAttivo(false);
                item.setReparto(reparto);
                promozioneTestataEntity.addRepartoMarchioPrivato(item);
            });
        } else {
            log.debug("Il canale " + promozioneTestataEntity.getCanalePromozioneEntity().getCodiceCanale() + " non supporta i marchi privati");
        }
    }
}

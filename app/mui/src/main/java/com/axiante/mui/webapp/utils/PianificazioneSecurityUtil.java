package com.axiante.mui.webapp.utils;

import com.axiante.mui.common.CanaleSecurityEnum;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleSecService;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.RepartoEntity;
import com.axiante.mui.persistence.service.ArticoloService;
import com.axiante.mui.persistence.service.GrmService;
import com.axiante.mui.persistence.service.GruppoService;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.persistence.service.RepartoService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class PianificazioneSecurityUtil {

    @Inject
    private Instance<OwnershipService> ownershipServiceInstance;

    @Inject
    private Instance<CfgCanaleSecService> canaleSecServiceInstance;

    @Inject
    private Instance<ArticoloService> articoloServiceInstance;

    @Inject
    private Instance<RepartoService> repartoServiceInstance;

    @Inject
    private Instance<GrmService> grmServiceInstance;

    @Inject
    private Instance<GruppoService> gruppoServiceInstance;

    @Inject
    private Instance<MuiService> muiServiceInstance;

    public boolean isReadable(@NonNull PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo) {
        try {
            return applySecurity(pianificazione, codiciGruppo, PianificazioneSecurityEnum.READ);
        } catch (Exception ex) {
            log.error(String.format("Error getting readability for pianificazione id %d", pianificazione.getId()), ex);
        }
        return false;
    }

    public boolean isWriteable(@NonNull PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo) {
        try {
            final PianificazioneRowTypeEnum rowType = PianificazioneRowTypeEnum
                    .fromCode(pianificazione.getTipoRiga().getCodiceTipo());
            if (PianificazioneRowTypeEnum.SET.equals(rowType)
                    || PianificazioneRowTypeEnum.RAGGRUPPAMENTO.equals(rowType)) {
                return true;
            }
            return applySecurity(pianificazione, codiciGruppo, PianificazioneSecurityEnum.WRITE);
        } catch (Exception ex) {
            log.error(String.format("Error getting writability for pianificazione id %d", pianificazione.getId()), ex);
        }
        return false;
    }

    public List<Long> keepAddableItems(List<Long> idItems, PromozioneTestataEntity testata, List<String> codiciGruppo) {
        final CanaleSecurityEnum sec = getSecurityFromChannelAndMode(testata, codiciGruppo,
                PianificazioneSecurityEnum.WRITE.isEditable());
        switch (sec) {
            case ALL:
                // Keep all items
                return idItems;
            case SECURED:
                // Check for security, relazione GRUPPI / COMPRATORI+ARTICOLI
                final ArticoloService articoloService = articoloServiceInstance.get();
                return idItems.stream().filter(id -> articoloService
                        .hasAssociationWithIdArticoloAndCodiciGruppo(id, codiciGruppo, PianificazioneSecurityEnum.WRITE))
                        .collect(Collectors.toList());
            case NONE:
                // Discard all items
                return Collections.emptyList();
            default:
                log.error(String.format("Security '%s' not managed; discard all items", sec.toString()));
                return Collections.emptyList();
        }
    }

    public List<String> getWritableCompratori(@NonNull PromozioneTestataEntity testata, List<String> codiciGruppo) {
        final CanaleSecurityEnum sec = getSecurityFromChannelAndMode(testata, codiciGruppo,
                PianificazioneSecurityEnum.WRITE.isEditable());
        switch (sec) {
            case ALL:
                // All buyers
                return muiServiceInstance.get().findAllCompratori().stream()
                        .map(CompratoreEntity::getCodiceCompratore)
                        .collect(Collectors.toList());
            case SECURED:
                // Check for security, relazione GRUPPI / COMPRATORI
                return muiServiceInstance.get()
                        .findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(codiciGruppo, PianificazioneSecurityEnum.WRITE);
            case NONE:
                // No buyers
                return Collections.emptyList();
            default:
                log.error(String.format("Security '%s' not managed; no buyer", sec.toString()));
                return Collections.emptyList();
        }
    }

    public List<String> getReadableCompratori(@NonNull PromozioneTestataEntity testata, List<String> codiciGruppo) {
        final CanaleSecurityEnum sec = getSecurityFromChannelAndMode(testata, codiciGruppo, false);
        switch (sec) {
            case ALL:
                // All buyers
                return muiServiceInstance.get().findAllCompratori().stream()
                        .map(CompratoreEntity::getCodiceCompratore)
                        .collect(Collectors.toList());
            case SECURED:
                // Check for security, relazione GRUPPI / COMPRATORI
                return muiServiceInstance.get()
                        .findAllCodiciCompratoreByCodiciGruppo(codiciGruppo);
            case NONE:
                // No buyers
                return Collections.emptyList();
            default:
                log.error(String.format("Security '%s' not managed; no buyer", sec.toString()));
                return Collections.emptyList();
        }
    }

    public List<String> getWritableGrm(@NonNull PromozioneTestataEntity testata, List<String> codiciGruppo) {
        final CanaleSecurityEnum sec = getSecurityFromChannelAndMode(testata, codiciGruppo,
                PianificazioneSecurityEnum.WRITE.isEditable());
        switch (sec) {
            case ALL:
                // All grm
                return muiServiceInstance.get().findAllGrm().stream()
                        .map(GrmEntity::getCodice)
                        .collect(Collectors.toList());
            case SECURED:
                // Check for security, relazione GRUPPI / GRM
                return grmServiceInstance.get().findAllWritableCodiciGrmByCodiciGruppo(codiciGruppo);
            case NONE:
                // No grm
                return Collections.emptyList();
            default:
                log.error(String.format("Security '%s' not managed; no grm", sec.toString()));
                return Collections.emptyList();
        }
    }

    public List<String> getWritableReparti(@NonNull PromozioneTestataEntity testata, List<String> codiciGruppo) {
        final CanaleSecurityEnum sec = getSecurityFromChannelAndMode(testata, codiciGruppo,
                PianificazioneSecurityEnum.WRITE.isEditable());
        switch (sec) {
            case ALL:
                // All reparti
                return muiServiceInstance.get().findAllReparti().stream()
                        .map(RepartoEntity::getCodiceReparto)
                        .collect(Collectors.toList());
            case SECURED:
                // Check for security, relazione GRUPPI / REPARTI
                return repartoServiceInstance.get().findAllWritableCodiciRepartoByCodiciGruppo(codiciGruppo);
            case NONE:
                // No reparti
                return Collections.emptyList();
            default:
                log.error(String.format("Security '%s' not managed; no reparti", sec.toString()));
                return Collections.emptyList();
        }
    }

    public boolean isDeletable(PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo) {
        try {
            final String codiceTipoRiga = pianificazione.getTipoRiga().getCodiceTipo();
            final PianificazioneRowTypeEnum tipoRiga = PianificazioneRowTypeEnum.fromCode(codiceTipoRiga);
            if (tipoRiga == null) {
                log.error(String.format("Cannot identify tipoRiga '%s' for pianificazione id %d",
                        codiceTipoRiga, pianificazione.getId()));
                return false;
            }
            switch (tipoRiga) {
                case SET:
                    return isDeletableSet(pianificazione, codiciGruppo);
                case RAGGRUPPAMENTO:
                    return isDeletableRaggruppamento(pianificazione, codiciGruppo);
                case ELEMENTO:
                    return isWriteable(pianificazione, codiciGruppo);
                default:
                    log.error(String.format("TipoRiga '%s' not managed for pianificazione id %d",
                            codiceTipoRiga, pianificazione.getId()));
                    return false;
            }
        } catch (Exception ex) {
            log.error(String.format("Error getting deletable for pianificazione id %d", pianificazione.getId()), ex);
        }
        return false;
    }

    public List<String> getCompratoriWithPlannedItems(PromozioneTestataEntity testata, List<Long> idItems,
                                                      List<String> codiciGruppo) {
        final CanaleSecurityEnum sec = getSecurityFromChannelAndMode(testata, codiciGruppo, true);
        switch (sec) {
            case ALL:
                return articoloServiceInstance.get().findCompratoriByIdArticoliAndCodiciGruppo(idItems, codiciGruppo,
                        PianificazioneSecurityEnum.READ);
            case SECURED:
                return articoloServiceInstance.get().findCompratoriByIdArticoliAndCodiciGruppo(idItems, codiciGruppo,
                        PianificazioneSecurityEnum.WRITE);
            case NONE:
                return Collections.emptyList();
            default:
                log.error(String.format("Security '%s' not managed", sec.toString()));
                return Collections.emptyList();
        }
    }

    private boolean isDeletableSet(@NonNull PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo) {
        try {
            return pianificazione.getChildren().stream().allMatch(p -> isDeletableRaggruppamento(p, codiciGruppo));
        } catch (Exception ex) {
            log.error(String.format("Error getting deletable for pianificazione set id %d", pianificazione.getId()), ex);
        }
        return false;
    }

    private boolean isDeletableRaggruppamento(@NonNull PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo) {
        try {
            return pianificazione.getChildren().stream().allMatch(p -> isWriteable(p, codiciGruppo));
        } catch (Exception ex) {
            log.error(String.format("Error getting deletable for pianificazione raggruppamento id %d", pianificazione.getId()), ex);
        }
        return false;
    }

    private boolean applySecurity(PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo,
                                  PianificazioneSecurityEnum security) {
        final CanaleSecurityEnum sec = getSecurityFromChannelAndMode(pianificazione.getPromozioneTestataEntity(),
                codiciGruppo, security.isEditable());
        switch (sec) {
            case ALL:
                return true;
            case SECURED:
                final PromoPianificazioneEnum tipoElemento = PromoPianificazioneEnum.valueOf(pianificazione.getTipoElemento());
                if (PromoPianificazioneEnum.TOTALE.equals(tipoElemento)) {
                    return gruppoServiceInstance.get().hasAccessTotaleByCodiciGruppo(codiciGruppo, security);
                }
                if (PromoPianificazioneEnum.ATTRIBUTO.equals(tipoElemento)) {
                    return gruppoServiceInstance.get().hasAccessAttributoByCodiciGruppo(codiciGruppo, security);
                }
                final Long id = Long.parseLong(pianificazione.getCodiceElemento());
                switch (tipoElemento) {
                    case ARTICOLO:
                        // Controllo relazione GRUPPI / COMPRATORI+ARTICOLI
                        return articoloServiceInstance.get()
                                .hasAssociationWithIdArticoloAndCodiciGruppo(id, codiciGruppo, security);
                    case REPARTO:
                        // Controllo relazione GRUPPI / REPARTO
                        return repartoServiceInstance.get()
                                .hasAssociationWithIdRepartoAndCodiciGruppo(id.intValue(), codiciGruppo, security);
                    case GRM:
                        // Controllo relazione GRUPPI / GRM
                        return grmServiceInstance.get()
                                .hasAssociationWithIdGrmAndCodiciGruppo(id.intValue(), codiciGruppo, security);
                    default:
                        log.error(String.format("Tipo elemento '%s' not managed", tipoElemento.getTipoElemento()));
                        return false;
                }
            case NONE:
                return false;
            default:
                log.error(String.format("Security '%s' not managed", sec.toString()));
                return false;
        }
    }

    private CanaleSecurityEnum getSecurityFromChannelAndMode(PromozioneTestataEntity testata, List<String> codiciGruppo,
                                                             boolean isWriteable) {
        final boolean hasOwnership = ownershipServiceInstance.get().hasOwnership(testata, codiciGruppo);
        final CfgCanaleSecEntity cfgCanaleSec = canaleSecServiceInstance.get()
                .findByCanale(testata.getMuiCanalePromozione());
        if (isWriteable) {
            return hasOwnership ? cfgCanaleSec.getOwnerWrite() : cfgCanaleSec.getOtherWrite();
        }
        return hasOwnership ? cfgCanaleSec.getOwnerRead() : cfgCanaleSec.getOtherRead();
    }
}

package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.SupportoMediaService;
import com.axiante.mui.dbpromo.persistence.service.impl.PianoMediaPromoDbpromoServiceImpl;
import com.axiante.mui.webapp.webservice.dto.CreatePianificazioneDto;
import com.axiante.mui.webapp.webservice.dto.EnabledDisabledFlag;
import com.axiante.mui.webapp.webservice.dto.PianificazioniPianoDto;
import com.axiante.mui.webapp.webservice.pojo.PianificazionePianoMedia;
import com.axiante.mui.webapp.webservice.pojo.SupportoMedia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
public class PianificazionePianoFactory {
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    @Inject
    private Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Inject
    private Instance<SupportoMediaService> supportoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaPromoDbpromoServiceImpl> pianoMediaPromoServiceInstance;

    public PianificazioniPianoDto build(List<PianoMediaEntity> piani, Date dataInizio, Date dataFine, boolean editable) {
        final PianificazioniPianoDto dto = new PianificazioniPianoDto();
        try {
            final List<SupportoMedia> supportiMedia = new ArrayList<>();
            // Riga tecnica "Campagna"
            supportiMedia.add(new SupportoMedia(-1L, "CAMPAGNA"));
            supportiMedia.addAll(supportoMediaServiceInstance.get().findAllAttivi().stream()
                    .sorted(Comparator.comparing(SupportoMediaEntity::getDescrizione))
                    .map(e -> new SupportoMedia(e.getId(), e.getDescrizione()))
                    .collect(Collectors.toList()));
            dto.setSupportiMedia(supportiMedia);

            LocalDateTime ldStart = dataInizio.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            LocalDateTime ldEnd = dataFine.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            dto.setStartDate(ldStart.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            dto.setDays(Duration.between(ldStart, ldEnd).toDays());
            List<PianificazionePianoMedia> configurazioni = new ArrayList<>();

            // Aggiungo la durata delle promo master per ogni piano media
            final List<String> codiciPromo = piani.stream().map(PianoMediaEntity::getPromoMaster).distinct()
                    .collect(Collectors.toList());
            if (!codiciPromo.isEmpty()) {
                final List<PianoMediaPromoDbpromoEntity> promoRiferimento = pianoMediaPromoServiceInstance.get()
                        .findAllByCodiciPromo(codiciPromo);
                for (int i = 0; i < promoRiferimento.size(); i++) {
                    configurazioni.add(mapPromoMasterPianoMedia((long) (i + 1), -1L, promoRiferimento.get(i)));
                }
            }

            // Aggiungo le pianificazioni alla propria riga del supporto
            for (PianoMediaEntity p : piani) {
                final Map<Long, List<PianificazionePianoMediaEntity>> grouped = p.getConfigurazioniPianoMedia().stream()
                        .filter(e -> e.getSupportoMedia().getAttivo())
                        .collect(Collectors.groupingBy(e -> e.getSupportoMedia().getId()));
                grouped.forEach((k, v) -> {
                    // Ordino lista
                    v.sort(Comparator.comparing(PianificazionePianoMediaEntity::getDataInizio));
                    // Itero lista per costruire 'text' e aggiungere a 'pianificazionePiani'
                    for (int i = 0; i < v.size(); i++) {
                        configurazioni.add(mapPianificazionePiano(p, v.get(i), i, editable));
                    }
                });

            }

            dto.setPianificazioniPiano(configurazioni);
            dto.setTimeRangeSelectedHandling(editable ? EnabledDisabledFlag.ENABLED : EnabledDisabledFlag.DISABLED);

        } catch (Exception ex) {
            log.error("Error building PianificazionePiano", ex);
        }
        return dto;
    }

    public PianificazioniPianoDto build(@NonNull PianoMediaEntity pianoMedia, boolean editable) {
        final PianificazioniPianoDto dto = new PianificazioniPianoDto();
        try {
            // Recupero tutti i Supporti Media censiti
            final List<SupportoMedia> supportiMedia = supportoMediaServiceInstance.get().findAllAttivi().stream()
                    .sorted(Comparator.comparing(SupportoMediaEntity::getDescrizione))
                    .map(e -> new SupportoMedia(e.getId(), e.getDescrizione()))
                    .collect(Collectors.toList());
            dto.setSupportiMedia(supportiMedia);
            dto.setTimeRangeSelectedHandling(editable ? EnabledDisabledFlag.ENABLED : EnabledDisabledFlag.DISABLED);
            // Recupero tutte le Pianificazioni relative al dato Piano Media
            final List<PianificazionePianoMediaEntity> entities = pianificazionePianoMediaServiceInstance.get()
                    .findAttiviByPianoMedia(pianoMedia);
            LocalDateTime ldStart = getStartDate(entities.stream().map(PianificazionePianoMediaEntity::getDataInizio)
                            .min(Comparator.comparing(d -> d)).orElse(null),
                    pianoMedia.getDataInizio());
            LocalDateTime ldEnd = getEndDate(entities.stream().map(PianificazionePianoMediaEntity::getDataFine)
                            .max(Comparator.comparing(d -> d)).orElse(null),
                    pianoMedia.getDataFine());
            dto.setStartDate(ldStart.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            dto.setDays(Duration.between(ldStart, ldEnd).toDays());
            final Map<Long, List<PianificazionePianoMediaEntity>> grouped = entities.stream()
                    .collect(Collectors.groupingBy(e -> e.getSupportoMedia().getId()));
            List<PianificazionePianoMedia> pianificazionePiani = new ArrayList<>();
            grouped.forEach((k, v) -> {
                // Ordino lista
                v.sort(Comparator.comparing(PianificazionePianoMediaEntity::getDataInizio));
                // Itero lista per costruire 'text' e aggiungere a 'pianificazionePiani'
                for (int i = 0; i < v.size(); i++) {
                    pianificazionePiani.add(mapPianificazionePiano(v.get(i), i, editable));
                }
            });
            dto.setPianificazioniPiano(pianificazionePiani);
        } catch (Exception ex) {
            log.error("Error building PianificazionePiano", ex);
        }
        return dto;
    }

    public PianificazionePianoMediaEntity buildPianificazionePianoMedia(@NonNull PianoMediaEntity pianoMedia,
                                                                        @NonNull CreatePianificazioneDto payload,
                                                                        @NonNull String username) {
        if (payload.getStart() == null || payload.getEnd() == null || payload.getResource() == null) {
            return null;
        }
        final SupportoMediaEntity supportoMedia = supportoMediaServiceInstance.get().findById(payload.getResource());
        if (supportoMedia == null || !supportoMedia.getAttivo()) {
            return null;
        }
        // Validate dates
        Date dataInizio = parseDate(payload.getStart());
        Date dataFine = parseDate(payload.getEnd());
        if (!validDates(dataInizio, dataFine)) {
            log.error(String.format("Dates are not valid; dataInizio=%s; dataFine=%s", payload.getStart(), payload.getEnd()));
            return null;
        }
        // OK, ho il supporto, ho validato payload --> creo entity
        final PianificazionePianoMediaEntity entity = (PianificazionePianoMediaEntity) AuditLogFiller
                .fillAuditLogFields(new PianificazionePianoMediaEntity(), username);
        entity.setPianoMedia(pianoMedia);
        entity.setSupportoMedia(supportoMedia);
        entity.setDataInizio(dataInizio);
        entity.setDataFine(dataFine);
        return entity;
    }

    public boolean update(@NonNull PianificazionePianoMediaEntity pianificazionePiano,
                          @NonNull CreatePianificazioneDto createPianificazioneDto) {
        if (createPianificazioneDto.getResource() == null
                || createPianificazioneDto.getStart() == null
                || createPianificazioneDto.getEnd() == null) {
            return false;
        }
        final Date startDate = parseDate(createPianificazioneDto.getStart());
        final Date endDate = parseDate(createPianificazioneDto.getEnd());
        if (!validDates(startDate, endDate)) {
            return false;
        }
        if (!createPianificazioneDto.getResource().equals(pianificazionePiano.getSupportoMedia().getId())) {
            return false;
        }
        pianificazionePiano.setDataInizio(startDate);
        pianificazionePiano.setDataFine(endDate);
        return true;
    }

    private LocalDateTime getStartDate(Date minDataInizio, Date dataInizioPianoMedia) {
        if (minDataInizio == null) {
            final DateTimeUtils dtUtils = new DateTimeUtils();
            final Date today = dtUtils.getDateWithoutTime(new Date());
            final Date d = dtUtils.isBefore(today, dataInizioPianoMedia, false)
                    ? today
                    : dataInizioPianoMedia;
            return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay().minusDays(3);
        }
        return minDataInizio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay().minusDays(3);
    }

    private LocalDateTime getEndDate(Date maxDataFine, Date dataFinePianoMedia) {
        if (maxDataFine == null) {
            final DateTimeUtils dtUtils = new DateTimeUtils();
            final Date today = dtUtils.getDateWithoutTime(new Date());
            final Date d = dtUtils.isAfter(dataFinePianoMedia, today, false)
                    ? dataFinePianoMedia
                    : today;
            return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay().plusDays(3);
        }
        return maxDataFine.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay().plusDays(3);
    }

    private Date parseDate(@NonNull String dateString) {
        try {
            return sdf.parse(dateString);
        } catch (ParseException ex) {
            log.error(String.format("Error parsing date %s as valid date", dateString), ex);
        }
        return null;
    }

    private boolean validDates(Date dataInizio, Date dataFine) {
        return new DateTimeUtils().isBefore(dataInizio, dataFine, true);
    }

    private PianificazionePianoMedia mapPianificazionePiano(PianificazionePianoMediaEntity e, int i, boolean editable) {
        final PianificazionePianoMedia ppm = new PianificazionePianoMedia();
        ppm.setId(e.getId());
        ppm.setResource(e.getSupportoMedia().getId());
        ppm.setStart(sdf.format(e.getDataInizio()));
        ppm.setEnd(sdf.format(e.getDataFine()));
        ppm.setText(String.format("%s #%d", e.getSupportoMedia().getDescrizione(), i + 1));
        ppm.setBarColor(String.format("#%s", getColor(e.getSupportoMedia())));
        if (!editable) {
            ppm.setDeleteDisabled(Boolean.TRUE);
            ppm.setMoveDisabled(Boolean.TRUE);
            ppm.setResizeDisabled(Boolean.TRUE);
        }
        return ppm;
    }

    private PianificazionePianoMedia mapPianificazionePiano(@NonNull PianoMediaEntity piano,
                                                            PianificazionePianoMediaEntity e,
                                                            int i,
                                                            boolean editable) {
        final PianificazionePianoMedia ppm = mapPianificazionePiano(e, i, editable);
        ppm.setText(piano.getDescrizione());
        ppm.setDeleteDisabled(true);
        ppm.setMoveDisabled(true);
        ppm.setResizeDisabled(true);
        ppm.setBarColor(String.format("#%s", getColor(e.getSupportoMedia())));
        return ppm;
    }

    private PianificazionePianoMedia mapPromoMasterPianoMedia(@NonNull Long id, @NonNull Long resourceId,
                                                              @NonNull PianoMediaPromoDbpromoEntity promoMaster) {
        final PianificazionePianoMedia ppm = new PianificazionePianoMedia();
        ppm.setId(id * -1);
        ppm.setResource(resourceId);
        ppm.setStart(sdf.format(promoMaster.getDataInizio()));
        ppm.setEnd(sdf.format(promoMaster.getDataFine()));
        ppm.setText(promoMaster.getDescrizioneEstesa());
        ppm.setBarColor(String.format("#%s", DbPromoConstants.PIANO_MEDIA_BLACK_COLOR));
        ppm.setDeleteDisabled(Boolean.TRUE);
        ppm.setMoveDisabled(Boolean.TRUE);
        ppm.setResizeDisabled(Boolean.TRUE);
        return ppm;
    }

    private String getColor(@NonNull SupportoMediaEntity supporto) {
        return supporto.getColore() != null ? supporto.getColore() : DbPromoConstants.PIANO_MEDIA_DEFAULT_COLOR;
    }
}

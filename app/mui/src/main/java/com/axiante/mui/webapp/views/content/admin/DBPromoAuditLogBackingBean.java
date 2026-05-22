package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.promo.DBPromoAuditLogDeleteRadioEnum;
import com.axiante.mui.common.utility.AuditLogUtils;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.util.CSVUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.StreamedContent;

@Slf4j
public class DBPromoAuditLogBackingBean implements FacesContextAware {

    private static final String DATA = "Data";
    private static final String UTENTE = "Utente";
    private static final String TIPO_AZIONE = "Tipo azione";
    private static final String PAGINA_BOTTONE = "Pagina / bottone";

    private AuditLogService auditLogService;

    @Getter
    private String labelData = DBPromoAuditLogBackingBean.DATA;
    @Getter
    private String labelUtente = DBPromoAuditLogBackingBean.UTENTE;
    @Getter
    private String labelTipoAzione = DBPromoAuditLogBackingBean.TIPO_AZIONE;
    @Getter
    private String labelPaginaBottone = DBPromoAuditLogBackingBean.PAGINA_BOTTONE;

    @Getter
    @Setter
    private int months;
    @Getter
    @Setter
    private int weeks;
    @Getter
    @Setter
    private int days;
    @Getter
    @Setter
    private String limit;
    @Getter
    @Setter
    private String from;
    @Getter
    @Setter
    private String to;

    @Getter
    @Setter
    private int dataSize;

    @Getter
    @Setter
    private String defaultDelete;
    @Getter
    @Setter
    private String defaultExport;

    @Getter
    @Setter
    private Map<String, Object> filters;

    @Setter
    private StreamedContent exportExcel;

    public DBPromoAuditLogBackingBean(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
        this.dataSize = auditLogService.countAllLogs().intValue();
        this.defaultDelete = DBPromoAuditLogDeleteRadioEnum.ALL.name();
        this.defaultExport = DBPromoAuditLogDeleteRadioEnum.ALL.name();
    }

    public void deleteAuditLog() {

        try {
            DBPromoAuditLogDeleteRadioEnum radioChecked = checkDBPromoAuditLogDeleteRadioEnum(defaultDelete);

            if (radioChecked != null) {
                Calendar limitDay = Calendar.getInstance();
                limitDay.set(Calendar.HOUR_OF_DAY, 0);
                limitDay.set(Calendar.MINUTE, 0);
                limitDay.set(Calendar.SECOND, 0);
                switch (radioChecked) {
                    case ALL:
                        //Cancella Tutto
                        auditLogService.deleteAllLogs();
                        break;
                    case MONTHS:
                        //Cancella Tutto gli ultimi n mesi
                        limitDay.add(Calendar.MONTH, -months);
                        auditLogService.deleteAllLogsFiltered(limitDay.getTime(), false);
                        break;
                    case WEEKS:
                        //Cancella Tutto gli ultime n settimane
                        limitDay.add(Calendar.WEEK_OF_YEAR, -weeks);
                        auditLogService.deleteAllLogsFiltered(limitDay.getTime(), false);
                        break;
                    case DAYS:
                        //Cancella Tutto gli ultimi n giorni
                        limitDay.add(Calendar.DAY_OF_YEAR, -days);
                        auditLogService.deleteAllLogsFiltered(limitDay.getTime(), false);
                        break;
                    case LIMIT:
                        //Cancella Tutto prima della data
                        auditLogService.deleteAllLogsFiltered(new AuditLogUtils().composeLogDateQueryCondition(limit, true), true);
                        break;
                }
            } else {
                log.debug(String.format("RadioButton definition for %s not exist", radioChecked));
            }
            resetDeleteFilter();

            this.dataSize = auditLogService.countAllLogs().intValue();
        } catch (Exception e) {
            log.error("Error try delete audit log ", e);
        }
    }

    private void resetDeleteFilter() {
        this.months = 0;
        this.weeks = 0;
        this.days = 0;
        this.limit = null;
        this.defaultDelete = DBPromoAuditLogDeleteRadioEnum.ALL.name();
    }

    private void resetExportFilter() {
        this.months = 0;
        this.weeks = 0;
        this.days = 0;
        this.from = null;
        this.to = null;
        this.defaultExport = DBPromoAuditLogDeleteRadioEnum.ALL.name();
    }

    private DBPromoAuditLogDeleteRadioEnum checkDBPromoAuditLogDeleteRadioEnum(String field) {
        Optional<DBPromoAuditLogDeleteRadioEnum> radioEnum = Arrays.stream(DBPromoAuditLogDeleteRadioEnum.values())
            .filter(Objects::nonNull).filter(radio -> radio.name().equalsIgnoreCase(field)).findFirst();
        return radioEnum.isPresent() ? radioEnum.get() : null;
    }

    public void prepareDeleteButtonDialog() {
        resetDeleteFilter();
    }

    public void prepareExportButtonDialog() {
        resetExportFilter();
    }

    public StreamedContent getExportExcel() {

        DBPromoAuditLogDeleteRadioEnum radioChecked = checkDBPromoAuditLogDeleteRadioEnum(defaultExport);
        List<AuditLogEntity> auditLogEntities;
        final AtomicReference<String> csvData = new AtomicReference<>("");
        final CSVUtils csvUtils = new CSVUtils();

        if (radioChecked != null) {

            Calendar limitDay = Calendar.getInstance();
            limitDay.set(Calendar.HOUR_OF_DAY, 0);
            limitDay.set(Calendar.MINUTE, 0);
            limitDay.set(Calendar.SECOND, 0);

            boolean isEmptyResults = false;
            int countResults = 0;

            final List<String[]> dataLines = new ArrayList<>();
            dataLines.add(new String[] {DBPromoAuditLogBackingBean.DATA, DBPromoAuditLogBackingBean.UTENTE, DBPromoAuditLogBackingBean.TIPO_AZIONE, DBPromoAuditLogBackingBean.PAGINA_BOTTONE});

            while(!isEmptyResults && countResults <= CSVUtils.RESULTS_MAX_LIMIT) {

                final int currentPage = countResults == 0 ? countResults : (int) Math.ceil((double) countResults / CSVUtils.PAGESIZE);

                switch (radioChecked) {
                    case ALL:
                        //Esporta Tutto
                        auditLogEntities = auditLogService.findAllPaginationFilteredLogs(currentPage, CSVUtils.PAGESIZE, null, null, filters);
                        countResults += CSVUtils.PAGESIZE;
                        isEmptyResults = auditLogEntities == null || auditLogEntities.isEmpty();
                        csvUtils.composeCsvDataRows(auditLogEntities, dataLines, csvData);
                        dataLines.clear();
                        break;
                    case MONTHS:
                        //Esporta Tutto gli ultimi n mesi
                        if(countResults == 0) {
                            limitDay.add(Calendar.MONTH, -months);
                        }
                        auditLogEntities = auditLogService.findAllPaginationFilteredLogs(currentPage, CSVUtils.PAGESIZE, limitDay.getTime(), null, filters);
                        countResults += CSVUtils.PAGESIZE;
                        isEmptyResults = auditLogEntities == null || auditLogEntities.isEmpty();
                        csvUtils.composeCsvDataRows(auditLogEntities, dataLines, csvData);
                        dataLines.clear();
                        break;
                    case WEEKS:
                        //Esporta Tutto gli ultime n settimane
                        if(countResults == 0) {
                            limitDay.add(Calendar.WEEK_OF_YEAR, -weeks);
                        }
                        auditLogEntities = auditLogService.findAllPaginationFilteredLogs(currentPage, CSVUtils.PAGESIZE, limitDay.getTime(), null, filters);
                        countResults += CSVUtils.PAGESIZE;
                        isEmptyResults = auditLogEntities == null || auditLogEntities.isEmpty();
                        csvUtils.composeCsvDataRows(auditLogEntities, dataLines, csvData);
                        dataLines.clear();
                        break;
                    case DAYS:
                        //Esporta Tutto gli ultimi n giorni
                        if(countResults == 0) {
                            limitDay.add(Calendar.DAY_OF_YEAR, -days);
                        }
                        auditLogEntities = auditLogService.findAllPaginationFilteredLogs(currentPage, CSVUtils.PAGESIZE, limitDay.getTime(), null, filters);
                        countResults += CSVUtils.PAGESIZE;
                        isEmptyResults = auditLogEntities == null || auditLogEntities.isEmpty();
                        csvUtils.composeCsvDataRows(auditLogEntities, dataLines, csvData);
                        dataLines.clear();
                        break;
                    case LIMIT:
                        //Esporta Tutto da data a data
                        auditLogEntities = auditLogService.findAllPaginationFilteredLogs(currentPage, CSVUtils.PAGESIZE,
                            new AuditLogUtils().composeLogDateQueryCondition(from, true),
                            new AuditLogUtils().composeLogDateQueryCondition(to, false), filters);
                        countResults += CSVUtils.PAGESIZE;
                        isEmptyResults = auditLogEntities == null || auditLogEntities.isEmpty();
                        csvUtils.composeCsvDataRows(auditLogEntities, dataLines, csvData);
                        dataLines.clear();
                        break;
                }
            }
        } else {
            log.debug(String.format("RadioButton definition for %s not exist", radioChecked));
        }

        this.setExportExcel(csvUtils.createExportCsv(csvData));
        resetExportFilter();

        this.dataSize = auditLogService.countAllLogs().intValue();

        return this.exportExcel;
    }

    public boolean getDeleteDisabled() {
        return DBPromoAuditLogDeleteRadioEnum.LIMIT.name().equals(defaultExport) &&
            (this.limit == null || this.limit.length() < 10); //campo data limite non è completo
    }

    public boolean getExportDisabled() {
        return DBPromoAuditLogDeleteRadioEnum.LIMIT.name().equals(defaultExport) &&
            (this.from == null || this.from.length() < 10 || // campo data inizio range di ricerca non è completo
                this.to == null || this.to.length() < 10);   // campo campo data fine range di ricerca non è completo
    }

}

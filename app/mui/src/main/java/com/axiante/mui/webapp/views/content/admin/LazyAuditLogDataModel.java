package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Data
@NoArgsConstructor
public class LazyAuditLogDataModel extends LazyDataModel<AuditLogEntity> {

	private static final long serialVersionUID = -7581158295323544456L;
	private AuditLogService auditLogService;
    private DBPromoAuditLogBackingBean backingBean;

    @Getter
    @Setter
    private Map<String, String> currentSortFilters;

    public LazyAuditLogDataModel(AuditLogService auditLogService, DBPromoAuditLogBackingBean backingBean) {
        this.auditLogService = auditLogService;
        this.currentSortFilters = new LinkedHashMap<>();
        this.backingBean = backingBean;
    }

    @Override
    public List<AuditLogEntity> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        List<AuditLogEntity> data;

        final int currentPage = first == 0 ? first : (int) Math.ceil((double) first / pageSize);

        if(multiSortMeta != null) {
            //Riordinare i filtri di ordinamento in modo che siano applicati correttamente nell'ordinamento
            Map<String, String> tmpCurrentSortFilters = this.currentSortFilters;
            this.currentSortFilters.clear();

            //I filtri di ordinamento  modificati per ultimi (parametro multiSortMeta in input) devono essere inseriti per primi nella mappa dei filtri di ordinamento
            multiSortMeta.stream()
                .filter(sortMeta -> getSortedSQL(sortMeta.getSortOrder().name()) != null)
                .forEach(sortMeta -> getCurrentSortFilters().put(sortMeta.getSortField(), getSortedSQL(sortMeta.getSortOrder().name())));

            //I filtri di ordinamento precendentemente definiti, ad esclusione degli ultimi(parametro multiSortMeta in input)
            //devono essere reinseriti nella mappa dei filtri di ordinamento mantenendo l'ordinamento precedente
            tmpCurrentSortFilters.entrySet().stream()
                .filter(sortFilter -> !getCurrentSortFilters().keySet().contains(sortFilter.getKey()))
                .forEach(filter -> getCurrentSortFilters().put(filter.getKey(), filter.getValue()));
        }

        data = this.auditLogService.findAllPaginationSortedLogs(currentPage, pageSize, currentSortFilters, filters, true);
        int allDataSize = this.auditLogService.countAllLogs().intValue();
        if ( filters == null || filters.isEmpty()){
            this.setRowCount(allDataSize);
        } else {
            this.setRowCount(this.auditLogService.countFilteredLogs(filters).intValue());
        }

        backingBean.setFilters(filters);
        backingBean.setDataSize(allDataSize);

        return data;
    }

    private String getSortedSQL(String sortOrder) {
        if(SortOrder.ASCENDING.name().equals(sortOrder)) {
            return DbPromoConstants.ASCENDING_SQL;
        } else if(SortOrder.DESCENDING.name().equals(sortOrder)) {
            return DbPromoConstants.DESCENDING_SQL;
        } else {
            return null;
        }
    }

}

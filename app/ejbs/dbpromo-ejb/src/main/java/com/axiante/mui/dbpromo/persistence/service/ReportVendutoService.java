package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import java.util.List;

public interface ReportVendutoService {
    List<ReportVendutoEntity> findAllByIdPromozione(Long idPromozione);
}

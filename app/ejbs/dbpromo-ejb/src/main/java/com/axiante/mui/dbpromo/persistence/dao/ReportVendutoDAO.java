package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.ReportVendutoEntity;
import java.util.List;

public interface ReportVendutoDAO {
    List<ReportVendutoEntity> findAllByIdPromozione(Long idPromozione);
}

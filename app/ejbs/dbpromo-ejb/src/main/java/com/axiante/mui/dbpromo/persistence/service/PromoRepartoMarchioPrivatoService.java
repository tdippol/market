package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import java.io.Serializable;
import java.util.List;

public interface PromoRepartoMarchioPrivatoService extends DbPromoService<PromoRepartoMarchioPrivato>, Serializable {
    List<PromoRepartoMarchioPrivato> findByIdPromozione(Long idPromozione);

}

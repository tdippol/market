package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.PromoRepartoMarchioPrivatoDAO;
import com.axiante.mui.dbpromo.persistence.entities.PromoRepartoMarchioPrivato;
import com.axiante.mui.dbpromo.persistence.service.PromoRepartoMarchioPrivatoService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class PromoRepartoMarchioPrivatoServiceImpl extends AbstractDbPromoService<PromoRepartoMarchioPrivato>
        implements PromoRepartoMarchioPrivatoService {
    private static final long serialVersionUID = 1435460619401716935L;

    @Inject
    @Getter
    private PromoRepartoMarchioPrivatoDAO dao;

    public List<PromoRepartoMarchioPrivato> findByIdPromozione(Long idPromozione){
        return getDao().findByIdPromozione(idPromozione);
    }
}

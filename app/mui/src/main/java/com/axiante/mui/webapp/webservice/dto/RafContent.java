package com.axiante.mui.webapp.webservice.dto;


import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TemplateTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class RafContent {
    @Getter
    @Setter
    private Long idTotalizzatore;
    @Getter
    @Setter
    private String descrizione;
    @Getter
    @Setter
    private Integer actionType;
    @Getter
    @Setter
    private Integer segno;
    @Getter
    @Setter
    private Integer exportVs;
    @Getter
    @Setter
    private Long idParent;

    public static RafContent fromTotalizzatoriEntity(TotalizzatoriEntity totalizzatoriEntity) {
        RafContent rafContent = new RafContent();
        rafContent.setIdTotalizzatore(totalizzatoriEntity.getId());
        rafContent.setDescrizione(totalizzatoriEntity.getDescrizione());
        rafContent.setActionType(totalizzatoriEntity.getActionType());
        rafContent.setSegno(totalizzatoriEntity.getSegno());
        rafContent.setExportVs(totalizzatoriEntity.getExportVs());
        rafContent.setIdParent(totalizzatoriEntity.getIdParent());
        return rafContent;
    }

    public static RafContent fromPianificazioneTotalizzatoreEntity(PianificazioneTotalizzatoriEntity pianificazioneTotalizzatoriEntity) {
        RafContent rafContent = new RafContent();
        rafContent.setIdTotalizzatore(pianificazioneTotalizzatoriEntity.getId());
        rafContent.setDescrizione(pianificazioneTotalizzatoriEntity.getDescrizione());
        rafContent.setActionType(pianificazioneTotalizzatoriEntity.getActionType());
        rafContent.setSegno(pianificazioneTotalizzatoriEntity.getSegno());
        rafContent.setExportVs(pianificazioneTotalizzatoriEntity.getExportVs());
        rafContent.setIdParent(pianificazioneTotalizzatoriEntity.getIdParent());
        return rafContent;
    }

    public static RafContent fromPianificazioneEntity(PromozionePianificazioneEntity pianificazione) {
        RafContent rafContent = new RafContent();
        rafContent.setIdTotalizzatore(pianificazione.getId());
        if("1".equals(pianificazione.getNumRaggruppamento())){
            rafContent.setDescrizione("ACCUMULO MULTITRANSAZIONE");
            rafContent.setSegno(1);
            rafContent.setActionType(55);
        } else {
            rafContent.setDescrizione("REDENZIONE MULTITRANSAZIONE");
            rafContent.setSegno(-1);
            rafContent.setActionType(56);
        }
        rafContent.setExportVs(1);
        return rafContent;
    }

    public static RafContent fromTemplateTotalizzatoriEntity(TemplateTotalizzatoriEntity template) {
        RafContent rafContent = new RafContent();
//        rafContent.setIdTotalizzatore(template.getId());
        rafContent.setDescrizione(template.getDescrizione());
        rafContent.setActionType(template.getActionType());
        rafContent.setSegno(template.getSegno());
        rafContent.setExportVs(template.getExportVs());
        return rafContent;
    }
    public TotalizzatoriEntity toTotalizzatoriEntity() {
        TotalizzatoriEntity totalizzatoriEntity = new TotalizzatoriEntity();
        totalizzatoriEntity.setId(getIdTotalizzatore());
        totalizzatoriEntity.setDescrizione(getDescrizione());
        totalizzatoriEntity.setActionType(getActionType());
        totalizzatoriEntity.setSegno(getSegno());
        totalizzatoriEntity.setExportVs(getExportVs());
        totalizzatoriEntity.setIdParent(getIdParent());
        return totalizzatoriEntity;
    }

    public PianificazioneTotalizzatoriEntity toPianificazioneTotalizzatoriEntity() {
        PianificazioneTotalizzatoriEntity pianificazioneTotalizzatoriEntity = new PianificazioneTotalizzatoriEntity();
        pianificazioneTotalizzatoriEntity.setId(getIdTotalizzatore());
        pianificazioneTotalizzatoriEntity.setDescrizione(getDescrizione());
        pianificazioneTotalizzatoriEntity.setActionType(getActionType());
        pianificazioneTotalizzatoriEntity.setSegno(getSegno());
        pianificazioneTotalizzatoriEntity.setExportVs(getExportVs());
        pianificazioneTotalizzatoriEntity.setIdParent(getIdParent());
        return pianificazioneTotalizzatoriEntity;
    }
}

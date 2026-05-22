package com.axiante.mui.webapp.webservice.pojo;

import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import lombok.Getter;

@Getter
public class ItemHierarchy {
    private String subGrm;
    private String grm;
    private String categoria;
    private String reparto;

    private ItemHierarchy(String subGrm, String grm, String categoria, String reparto) {
        this.subGrm = subGrm;
        this.grm = grm;
        this.categoria = categoria;
        this.reparto = reparto;
    }

    public static ItemHierarchy build(ItemEntity item) {
        String subGrm = "";
        if (item.getSubGrmEntity() != null) {
            subGrm = String.format("[SGRM_%s] %s", item.getSubGrmEntity().getCodiceSubgrm(),
                    item.getSubGrmEntity().getDescrizione() != null
                            ? item.getSubGrmEntity().getDescrizione().toUpperCase()
                            : "");
        }

        String grm = "";
        if (!subGrm.isEmpty() && item.getSubGrmEntity().getGrmEntity() != null) {
            grm = String.format("[GRM_%s] %s", item.getSubGrmEntity().getGrmEntity().getCodiceGrm(),
                    item.getSubGrmEntity().getGrmEntity().getDescrizione() != null
                            ? item.getSubGrmEntity().getGrmEntity().getDescrizione().toUpperCase()
                            : "");
        }

        String categoria = "";
        if (!grm.isEmpty() && item.getSubGrmEntity().getGrmEntity().getMuiCategoria() != null) {
            categoria = String.format("[%s] %s",
                    item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getCodiceCategoria(),
                    item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getDescrizione() != null
                            ? item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getDescrizione()
                            .toUpperCase()
                            : "");
        }

        String reparto = "";
        if (!categoria.isEmpty()
                && item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getRepartoEntity() != null) {
            reparto = String.format("[%s] %s",
                    item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getRepartoEntity().getCodiceReparto(),
                    item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getRepartoEntity()
                            .getDescrizione() != null
                            ? item.getSubGrmEntity().getGrmEntity().getMuiCategoria().getRepartoEntity()
                            .getDescrizione().toUpperCase()
                            : "");
        }
        return new ItemHierarchy(subGrm, grm, categoria, reparto);
    }
}

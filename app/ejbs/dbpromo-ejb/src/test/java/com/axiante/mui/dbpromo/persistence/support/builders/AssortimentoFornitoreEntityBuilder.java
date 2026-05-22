package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.persistence.entities.AssortimentoFornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;

public class AssortimentoFornitoreEntityBuilder {

    private Long id;
    private Boolean fornitorePrincipale;
    private FornitoreEntity fornitore;
    private ItemEntity item;

    public AssortimentoFornitoreEntityBuilder() {}

    public AssortimentoFornitoreEntityBuilder(Long id) {
        this.id = id;
    }

    public AssortimentoFornitoreEntityBuilder withFornitorePrincipale(Boolean fornitorePrincipale) {
        this.fornitorePrincipale = fornitorePrincipale != null && fornitorePrincipale;
        return this;
    }

    public AssortimentoFornitoreEntityBuilder withFornitore(FornitoreEntity fornitore) {
        this.fornitore = fornitore;
        return this;
    }

    public AssortimentoFornitoreEntityBuilder withItem(ItemEntity item) {
        this.item = item;
        return this;
    }

    public AssortimentoFornitoreEntity build() {
        final AssortimentoFornitoreEntity entity = new AssortimentoFornitoreEntity();
        entity.setId(id);
        entity.setFornitorePrincipale(fornitorePrincipale == null || !fornitorePrincipale ? 0L : 1L);
        entity.setFornitoreEntity(fornitore);
        entity.setItemEntity(item);
        return entity;
    }
}

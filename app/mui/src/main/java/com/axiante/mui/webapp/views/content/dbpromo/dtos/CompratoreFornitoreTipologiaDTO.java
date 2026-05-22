package com.axiante.mui.webapp.views.content.dbpromo.dtos;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoPromoRifatturazioneEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CompratoreFornitoreTipologiaDTO {
    private final CompratoreEntity compratore;
    private final FornitoreEntity fornitore;
    private final TipoPromoRifatturazioneEntity tipologia;
    @Setter
    private boolean done = false;

    public CompratoreFornitoreTipologiaDTO(CompratoreEntity compratore, FornitoreEntity fornitore,
                                           TipoPromoRifatturazioneEntity tipologia) {
        this.compratore = compratore;
        this.fornitore = fornitore;
        this.tipologia = tipologia;
    }
}

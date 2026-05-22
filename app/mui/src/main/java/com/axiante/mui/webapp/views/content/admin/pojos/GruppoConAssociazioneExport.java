package com.axiante.mui.webapp.views.content.admin.pojos;

import lombok.Getter;

public class GruppoConAssociazioneExport {
    @Getter
    private String codiceGruppo;

    @Getter
    private String descrizioneGruppo;

    @Getter
    private String codiceAssociazione;

    @Getter
    private String descrizioneAssociazione;

    @Getter
    private String tipoAccesso;

    public GruppoConAssociazioneExport(String codiceGruppo, String descrizioneGruppo) {
        this.codiceGruppo = codiceGruppo;
        this.descrizioneGruppo = descrizioneGruppo;
    }

    public GruppoConAssociazioneExport(String codiceGruppo, String descrizioneGruppo,
                                       String codiceAssociazione, String descrizioneAssociazione, String tipoAccesso) {
        this.codiceGruppo = codiceGruppo;
        this.descrizioneGruppo = descrizioneGruppo;
        this.codiceAssociazione = codiceAssociazione;
        this.descrizioneAssociazione = descrizioneAssociazione;
        this.tipoAccesso = tipoAccesso;
    }
}

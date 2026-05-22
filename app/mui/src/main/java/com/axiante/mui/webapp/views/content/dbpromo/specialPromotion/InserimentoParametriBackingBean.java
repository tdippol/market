package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.math.BigDecimal;

@Dependent
@Getter
@Setter
@Slf4j
public class InserimentoParametriBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCompratoreSelezionato;

    public void clear() {
        this.idCompratoreSelezionato = null;
    }

    public void applyBusinessRules(RigaInserimentoParametri riga) {
        if (riga == null) {
            return;
        }

        if (!riga.isAbilitatoInserimento()) {
            riga.setEditableTipoPremio(false);
            riga.setEditablePremioCf(false);
            riga.setEditablePremioPerc(false);
            riga.setEditableSogliaMax(false);
            riga.setEditableTipoBaseImponibile(false);
            riga.setEditableModalitaLiquidazione(false);

            riga.setPremioCf(BigDecimal.ZERO);
            riga.setPremioPerc(BigDecimal.ZERO);
            riga.setSogliaMax(null);
            riga.setTipoBaseImponibile(null);
            riga.setModalitaLiquidazione("IM");
            riga.setCheck("Compratore non abilitato sul fornitore");
            return;
        }

        if (riga.getTipoPremio() == null || riga.getTipoPremio().trim().isEmpty()) {
            riga.setEditableTipoPremio(true);
            riga.setEditablePremioCf(false);
            riga.setEditablePremioPerc(false);
            riga.setEditableSogliaMax(false);
            riga.setEditableTipoBaseImponibile(false);
            riga.setEditableModalitaLiquidazione(false);

            riga.setPremioCf(BigDecimal.ZERO);
            riga.setPremioPerc(BigDecimal.ZERO);
            riga.setSogliaMax(null);
            riga.setTipoBaseImponibile(null);
            riga.setModalitaLiquidazione("IM");
            riga.setCheck("Nessun parametro inserito");
            return;
        }

        if ("Cifra fissa".equals(riga.getTipoPremio())) {
            riga.setEditableTipoPremio(true);
            riga.setEditablePremioCf(true);
            riga.setEditablePremioPerc(false);
            riga.setEditableSogliaMax(false);
            riga.setEditableTipoBaseImponibile(false);
            riga.setEditableModalitaLiquidazione(false);

            riga.setPremioPerc(BigDecimal.ZERO);
            riga.setSogliaMax(null);
            riga.setTipoBaseImponibile(null);
            riga.setModalitaLiquidazione("IM");

            if (riga.getPremioCf() == null) {
                riga.setPremioCf(BigDecimal.ZERO);
            }

            if (riga.getPremioCf().compareTo(BigDecimal.ZERO) == 0 || !"IM".equals(riga.getModalitaLiquidazione())) {
                riga.setCheck("Uno o più campi richiesti non sono compilati correttamente!");
            } else {
                riga.setCheck("OK");
            }
            return;
        }

        if ("Premio %".equals(riga.getTipoPremio())) {
            riga.setEditableTipoPremio(true);
            riga.setEditablePremioCf(false);
            riga.setEditablePremioPerc(true);
            riga.setEditableSogliaMax(true);
            riga.setEditableTipoBaseImponibile(true);
            riga.setEditableModalitaLiquidazione(true);

            riga.setPremioCf(BigDecimal.ZERO);

            if (riga.getPremioPerc() == null) {
                riga.setPremioPerc(BigDecimal.ZERO);
            }

            if (isPercentualeNonValida(riga.getPremioPerc())) {
                riga.setCheck("Il % supera il limite di 100%");
                return;
            }

            boolean premioPercNonValido = riga.getPremioPerc().compareTo(BigDecimal.ZERO) == 0;
            boolean baseImpVuota = riga.getTipoBaseImponibile() == null || riga.getTipoBaseImponibile().trim().isEmpty();
            boolean modLiqVuota = riga.getModalitaLiquidazione() == null || riga.getModalitaLiquidazione().trim().isEmpty();

            if (premioPercNonValido || baseImpVuota || modLiqVuota) {
                riga.setCheck("Uno o più campi richiesti non sono compilati correttamente!");
            } else {
                riga.setCheck("OK");
            }
        }
    }

    private boolean isPercentualeNonValida(BigDecimal percentuale) {
        if (percentuale == null) {
            return true;
        }

        if (percentuale.compareTo(BigDecimal.ZERO) < 0 || percentuale.compareTo(new BigDecimal("100")) > 0) {
            return true;
        }

        return percentuale.scale() > 2;
    }

    @Getter
    @Setter
    public static class RigaInserimentoParametri implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private Long idPromozione;
        private Long idCompratore;
        private Long idFornitore;

        private String compratore;
        private String fornitore;

        private boolean abilitatoInserimento;

        private String tipoPremio;
        private BigDecimal premioCf;
        private BigDecimal premioPerc;
        private BigDecimal sogliaMax;
        private String tipoBaseImponibile;
        private String modalitaLiquidazione;
        private String check;
        private BigDecimal premioFinale;

        private boolean editableTipoPremio;
        private boolean editablePremioCf;
        private boolean editablePremioPerc;
        private boolean editableSogliaMax;
        private boolean editableTipoBaseImponibile;
        private boolean editableModalitaLiquidazione;
    }
}
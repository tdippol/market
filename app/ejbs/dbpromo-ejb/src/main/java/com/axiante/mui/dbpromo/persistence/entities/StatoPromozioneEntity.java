package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_STATO_PROMOZIONE database table.
 */
@Entity
@Getter
@Setter
@Table(name = "MUI_STATO_PROMOZIONE", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "StatoPromozioneEntity.findAll",
                query = "SELECT m FROM StatoPromozioneEntity m"),
        @NamedQuery(name = "StatoPromozioneEntity.findAllSorted",
                query = "SELECT e FROM StatoPromozioneEntity e ORDER BY e.codiceStato"),
        @NamedQuery(name = "StatoPromozioneEntity.findByCode",
                query = "SELECT m FROM StatoPromozioneEntity m WHERE m.codiceStato = :code")
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatoPromozioneEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_STATO_PROMOZIONE_ID_GENERATOR", sequenceName = "MUI_STATO_PROMOZIONE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_STATO_PROMOZIONE_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "CODICE_STATO", nullable = false, length = 4)
    private String codiceStato;

    @Column(nullable = false, length = 200)
    private String descrizione;

    @Column(name = "LABEL", length = 200)
    private String label;

    // bi-directional many-to-one association to MuiCfgStatiCanaleConsent
    @OneToMany(mappedBy = "statoPromozioneEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<CfgStatiCanaleConsentEntity> muiCfgStatiCanaleConsents;

    // bi-directional many-to-one association to CfgStatiTransizioniEntity
    @OneToMany(mappedBy = "statoTransizione", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<CfgStatiTransizioniEntity> muiCfgStatiTransizionis1;

    // bi-directional many-to-one association to CfgStatiTransizioniEntity
    @OneToMany(mappedBy = "statoPromozione", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<CfgStatiTransizioniEntity> muiCfgStatiTransizionis2;

    // bi-directional many-to-one association to PromozioneStatoEntity
    @OneToMany(mappedBy = "statoPromozioneEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PromozioneStatoEntity> promozioneStatoEntities;


    // bi-directional many-to-one association to PromoStatiConsentitiEntity
    @OneToMany(mappedBy = "statoPromozioneEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PromoStatiConsentitiEntity> promoStatiConsentitiEntities;

    // bi-directional many-to-one association to PromoStatiTransizioneEntity
    @OneToMany(mappedBy = "statoTransizione", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PromoStatiTransizioneEntity> promoStatiTransizioneEntities1;

    // bi-directional many-to-one association to PromoStatiTransizioneEntity
    @OneToMany(mappedBy = "statoPromozione", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PromoStatiTransizioneEntity> promoStatiTransizioneEntities2;

    // bi-directional many-to-one association to PromoStatiTransizioneEntity
    @OneToMany(mappedBy = "statoPromozioneEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities;

    // bi-directional many-to-many association to Canali... comanda il Canale !!!
    @ManyToMany(mappedBy = "statiBlocco")
    private Set<CanalePromozioneEntity> canali;

    // bi-directional many-to-one association to PromoStatiConsentitiEntity
    @OneToMany(mappedBy = "statoPromozioneEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PianoMediaStatiConsentitiEntity> pianoMediaStatiConsentitiEntities;

    // bi-directional many-to-one association to PromoStatiTransizioneEntity
    @OneToMany(mappedBy = "statoTransizione", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PianoMediaStatiTransizioneEntity> pianoMediaStatiTransizioneEntities;

    @OneToMany(mappedBy = "stato", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PianoMediaStatoEntity> pianoMediaStatoEntities;

    @OneToMany(mappedBy = "stato", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<PianoMediaPianificazioneDettaglioEntity> pianoMediaPianificazioneDettaglioEntities;

    public PianoMediaPianificazioneDettaglioEntity addPianoMediaDettaglio(PianoMediaPianificazioneDettaglioEntity e) {
        if (getPianoMediaPianificazioneDettaglioEntities() == null) {
            setPianoMediaPianificazioneDettaglioEntities(new HashSet<>());
        }
        getPianoMediaPianificazioneDettaglioEntities().add(e);
        e.setStato(this);
        return e;
    }

    public PianoMediaPianificazioneDettaglioEntity removePianoMediaDettaglio(PianoMediaPianificazioneDettaglioEntity e) {
        if (getPianoMediaPianificazioneDettaglioEntities() != null && e.getStato() != null && e.getStato().equals(this) && getPianoMediaPianificazioneDettaglioEntities().remove(e)) {
            e.setStato(null);
        }
        return e;
    }

    public CfgStatiCanaleConsentEntity addMuiCfgStatiCanaleConsent(
            CfgStatiCanaleConsentEntity muiCfgStatiCanaleConsent) {
        getMuiCfgStatiCanaleConsents().add(muiCfgStatiCanaleConsent);
        muiCfgStatiCanaleConsent.setStatoPromozioneEntity(this);

        return muiCfgStatiCanaleConsent;
    }

    public CfgStatiCanaleConsentEntity removeMuiCfgStatiCanaleConsent(
            CfgStatiCanaleConsentEntity muiCfgStatiCanaleConsent) {
        getMuiCfgStatiCanaleConsents().remove(muiCfgStatiCanaleConsent);
        muiCfgStatiCanaleConsent.setStatoPromozioneEntity(null);

        return muiCfgStatiCanaleConsent;
    }

    public CfgStatiTransizioniEntity addMuiCfgStatiTransizionis1(CfgStatiTransizioniEntity muiCfgStatiTransizionis1) {
        getMuiCfgStatiTransizionis1().add(muiCfgStatiTransizionis1);
        muiCfgStatiTransizionis1.setStatoTransizione(this);

        return muiCfgStatiTransizionis1;
    }

    public CfgStatiTransizioniEntity removeMuiCfgStatiTransizionis1(
            CfgStatiTransizioniEntity muiCfgStatiTransizionis1) {
        getMuiCfgStatiTransizionis1().remove(muiCfgStatiTransizionis1);
        muiCfgStatiTransizionis1.setStatoTransizione(null);

        return muiCfgStatiTransizionis1;
    }

    public CfgStatiTransizioniEntity addMuiCfgStatiTransizionis2(CfgStatiTransizioniEntity muiCfgStatiTransizionis2) {
        getMuiCfgStatiTransizionis2().add(muiCfgStatiTransizionis2);
        muiCfgStatiTransizionis2.setStatoPromozione(this);

        return muiCfgStatiTransizionis2;
    }

    public CfgStatiTransizioniEntity removeMuiCfgStatiTransizionis2(
            CfgStatiTransizioniEntity muiCfgStatiTransizionis2) {
        getMuiCfgStatiTransizionis2().remove(muiCfgStatiTransizionis2);
        muiCfgStatiTransizionis2.setStatoPromozione(null);

        return muiCfgStatiTransizionis2;
    }

    public PromozioneStatoEntity addPromozioneStatoEntity(PromozioneStatoEntity promozioneStatoEntity) {
        getPromozioneStatoEntities().add(promozioneStatoEntity);
        promozioneStatoEntity.setStatoPromozioneEntity(this);

        return promozioneStatoEntity;
    }

    public PromozioneStatoEntity removePromozioneStatoEntity(PromozioneStatoEntity promozioneStatoEntity) {
        getPromozioneStatoEntities().remove(promozioneStatoEntity);
        promozioneStatoEntity.setStatoPromozioneEntity(null);

        return promozioneStatoEntity;
    }


    public PromoStatiConsentitiEntity addPromoStatiConsentitiEntity(
            PromoStatiConsentitiEntity promoStatiConsentitiEntity) {
        getPromoStatiConsentitiEntities().add(promoStatiConsentitiEntity);
        promoStatiConsentitiEntity.setStatoPromozioneEntity(this);

        return promoStatiConsentitiEntity;
    }

    public PromoStatiConsentitiEntity removePromoStatiConsentitiEntity(
            PromoStatiConsentitiEntity promoStatiConsentitiEntity) {
        getPromoStatiConsentitiEntities().remove(promoStatiConsentitiEntity);
        promoStatiConsentitiEntity.setStatoPromozioneEntity(null);

        return promoStatiConsentitiEntity;
    }

    public PromoStatiTransizioneEntity addPromoStatiTransizioneEntities1(
            PromoStatiTransizioneEntity promoStatiTransizioneEntities1) {
        getPromoStatiTransizioneEntities1().add(promoStatiTransizioneEntities1);
        promoStatiTransizioneEntities1.setStatoTransizione(this);

        return promoStatiTransizioneEntities1;
    }

    public PromoStatiTransizioneEntity removePromoStatiTransizioneEntities1(
            PromoStatiTransizioneEntity promoStatiTransizioneEntities1) {
        getPromoStatiTransizioneEntities1().remove(promoStatiTransizioneEntities1);
        promoStatiTransizioneEntities1.setStatoTransizione(null);

        return promoStatiTransizioneEntities1;
    }

    public PromoStatiTransizioneEntity addPromoStatiTransizioneEntities2(
            PromoStatiTransizioneEntity promoStatiTransizioneEntities2) {
        getPromoStatiTransizioneEntities2().add(promoStatiTransizioneEntities2);
        promoStatiTransizioneEntities2.setStatoPromozione(this);

        return promoStatiTransizioneEntities2;
    }

    public PromoStatiTransizioneEntity removePromoStatiTransizioneEntities2(
            PromoStatiTransizioneEntity promoStatiTransizioneEntities2) {
        getPromoStatiTransizioneEntities2().remove(promoStatiTransizioneEntities2);
        promoStatiTransizioneEntities2.setStatoPromozione(null);

        return promoStatiTransizioneEntities2;
    }

    public PromoPubblicazioneTestataEntity addPromoPubblicazioneTestataEntity(
            PromoPubblicazioneTestataEntity promoPubblicazioneTestataEntity) {
        getPromoPubblicazioneTestataEntities().add(promoPubblicazioneTestataEntity);
        promoPubblicazioneTestataEntity.setStatoPromozioneEntity(this);

        return promoPubblicazioneTestataEntity;
    }

    public PromoPubblicazioneTestataEntity removePromoPubblicazioneTestataEntity(
            PromoPubblicazioneTestataEntity promoPubblicazioneTestataEntity) {
        getPromoPubblicazioneTestataEntities().remove(promoPubblicazioneTestataEntity);
        promoPubblicazioneTestataEntity.setStatoPromozioneEntity(null);

        return promoPubblicazioneTestataEntity;
    }

    public CanalePromozioneEntity addCanale(final CanalePromozioneEntity canale) {
        if (getCanali() == null) {
            setCanali(new HashSet<>());
        }
        getCanali().add(canale);
        return canale;
    }

    public CanalePromozioneEntity removeCanale(final CanalePromozioneEntity canale) {
        if (getCanali() != null) {
            getCanali().remove(canale);
        }
        return canale;
    }

    @Transient
    public String fullDescription() {
        return String.format("%s - %s", codiceStato, descrizione);
    }
}
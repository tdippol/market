package com.axiante.mui.persistence.entity;

import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "V_ITEM", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "ArticoloEntity.countByArticoloIdAndCodiciGruppo",
                query = "SELECT COUNT(a) FROM ArticoloEntity a JOIN FETCH a.compratore c JOIN FETCH c.gruppi g WHERE a.id = :idArticolo AND g.codiceGruppo IN :codiciGruppo"),
        @NamedQuery(name = "ArticoloEntity.countWritableByArticoloIdAndCodiciGruppo",
                query = "SELECT COUNT(gc) FROM GruppoCompratoreEntity gc JOIN FETCH gc.compratore.articoli a WHERE a.id = :idArticolo AND gc.tipoAccesso = :tipoAccesso AND gc.gruppo.codiceGruppo IN :codiciGruppo"),
        @NamedQuery(name = "ArticoloEntity.findWritableCompratoriByIdArticoliAndCodiciGruppo",
                query = "SELECT gc.compratore.codiceCompratore FROM GruppoCompratoreEntity gc JOIN FETCH gc.compratore.articoli a WHERE a.id IN :idArticoli AND gc.tipoAccesso = :tipoAccesso AND gc.gruppo.codiceGruppo IN :codiciGruppo"),
        @NamedQuery(name = "ArticoloEntity.findCompratoriByIdArticoliAndCodiciGruppo",
                query = "SELECT a.compratore.codiceCompratore FROM ArticoloEntity a JOIN FETCH a.compratore c JOIN FETCH c.gruppi g WHERE a.id IN :idArticoli AND g.codiceGruppo IN :codiciGruppo")
})
public class ArticoloEntity {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPRATORE", nullable = false)
    private CompratoreEntity compratore;
}

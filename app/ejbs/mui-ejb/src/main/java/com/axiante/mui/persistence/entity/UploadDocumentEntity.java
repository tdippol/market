package com.axiante.mui.persistence.entity;

import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UPLOAD_DOCUMENT", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UploadDocumentEntity implements Serializable {

    private static final long serialVersionUID = -5910623768681970290L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "UPLOAD_DOCUMENT_ID_SEQ", allocationSize = 1, name = "uploadDocSeq")
    @GeneratedValue(generator = "uploadDocSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "NAME", length = 4000, nullable =  false)
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
    private String codiceUtenteInserimento;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO", length = 50)
    private String codiceUtenteAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @ToString.Exclude
    @ManyToMany(mappedBy = "documents")
    private Set<RolesEntity> roles;

    public RolesEntity addRole(final RolesEntity role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
        role.addDocument(this);
        return role;
    }

    public RolesEntity removeRole(final RolesEntity role) {
        if (roles != null) {
            roles.remove(role);
        }
        role.removeDocument(this);
        return role;
    }
}

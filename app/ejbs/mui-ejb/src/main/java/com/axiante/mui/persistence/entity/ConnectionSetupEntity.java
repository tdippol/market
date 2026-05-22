package com.axiante.mui.persistence.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axiante.mui.persistence.Metadata;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CONNECTION_SETUP", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class ConnectionSetupEntity implements Serializable {
    private static final long serialVersionUID = 1032092661813357755L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, sequenceName="CONNECTION_SETUP_SEQ",allocationSize=1,name="connectionSetupSeq")
    @GeneratedValue(generator="connectionSetupSeq",strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    @Column(name = "ID_CONNECTION_SETUP")
    private Integer idConnectionSetup;

    @Basic
    @Column(name = "CONNECTION_NAME")
    private String connectionName;

    @Basic
    @Column(name = "USERNAME")
    private String username;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "DOMAIN")
    private String domain;

    @Basic
    @Column(name = "HOST")
    private String host;

    @Basic
    @Column(name = "PORT")
    private Integer port;

    @Basic
    @Column(name = "PATH")
    private String path;

    @Basic
    @Column(name = "VALIDATE_SSL")
    private Integer validateSsl;


    @Basic
    @Column(name = "AUTH_TYPE")
    private String authType;

    public Boolean getValidateSsl() {
        return (validateSsl !=null) && (validateSsl == 1);
    }

    public void setValidateSsl(final Boolean validateSsl) {
        this.validateSsl = validateSsl?1:0;
    }
}

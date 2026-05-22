package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Base64;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_CANALE_DISPOSITIVO", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "CfgCanaleDispositivoEntity.findAll", query = "SELECT a FROM CfgCanaleDispositivoEntity a"),
        @NamedQuery(name = "CfgCanaleDispositivoEntity.findByCodice", query = "SELECT a FROM CfgCanaleDispositivoEntity a where a.codice = :codice"),
        @NamedQuery(name = "CfgCanaleDispositivoEntity.countByCodice", query = "SELECT count(a) FROM CfgCanaleDispositivoEntity a where a.codice = :codice")
})
public class CfgCanaleDispositivoEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 6313058897714813416L;
    public static final String TIPO_1 = "tipo1";
    public static final String CODICE_F = "F";

    @Id
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name="CODICE", length = 50)
    String codice;

    @Column(name="DESCRIZIONE")
    String descrizione;

    @Column(name="UPLOAD", length = 400)
    String formatter;

    @Column(name="PATH", length = 400)
    String directory;

    @Column(name = "LIMITA_CARATTERI", length = 20)
    String limitaCaratteri;

    @Column(name = "NUMERO_CARATTERI", nullable = false)
    private Integer numeroCaratteri = 4000;

    @Column(name="abilita_ftp", nullable = false )
    @Convert(converter = BooleanConverter.class)
    Boolean abilitaFtp = Boolean.FALSE;

    @Column(name = "ftp_server", length = 255)
    String ftpServer;
    @Column(name = "ftp_port", length = 5)
    String ftpPort;
    @Column(name = "ftp_path", length = 1000)
    String ftpPath;
    @Column(name = "ftp_user", length = 255)
    String ftpUser;
    @Column(name = "ftp_password", length = 255)
    String ftpPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CfgCanaleDispositivoEntity)) {
            return false;
        }
        return (id != null) && id.equals(((CfgCanaleDispositivoEntity) o).getId());
    }

    @Transient
    public void setFtpUser(String ftpUser) {
        if (ftpUser != null) {
            ftpUser = Base64.getEncoder().encodeToString(ftpUser.trim().getBytes());
        }
        this.ftpUser = ftpUser;
    }

    @Transient
    public String getFtpUser() {
        if (ftpUser != null) {
            return new String(Base64.getDecoder().decode(ftpUser));
        }
        return null;
    }

    @Transient
    public void setFtpPassword(String ftpPassword) {
        if (ftpPassword != null) {
            ftpPassword = Base64.getEncoder().encodeToString(ftpPassword.trim().getBytes());
        }
        this.ftpPassword = ftpPassword;
    }

    @Transient
    public String getFtpPassword() {
        if (ftpPassword != null) {
            return new String(Base64.getDecoder().decode(ftpPassword));
        }
        return null;
    }
}

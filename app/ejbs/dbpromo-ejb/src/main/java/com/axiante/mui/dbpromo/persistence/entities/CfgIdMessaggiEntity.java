package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_ID_MESSAGGI", schema = Metadata.SCHEMA)
@NamedQueries({
  @NamedQuery(
      name =
          "CfgIdMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivoAndIdMessaggio",
      query =
          "SELECT c FROM CfgIdMessaggiEntity c WHERE c.codiceCanale = :codiceCanale AND c.codiceMeccanica = :codiceMeccanica AND c.codiceDispositivo = :codiceDispositivo AND c.idMessaggio = :idMessaggio"),
  @NamedQuery(
      name = "CfgIdMessaggiEntity.findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo",
      query =
          "SELECT c FROM CfgIdMessaggiEntity c WHERE c.codiceCanale = :codiceCanale AND c.codiceMeccanica = :codiceMeccanica AND c.codiceDispositivo = :codiceDispositivo")
})
public class CfgIdMessaggiEntity implements Serializable, DbPromoEntityInterface {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mui_cfg_id_messaggi_id_seq")
  @SequenceGenerator(
      name = "mui_cfg_id_messaggi_id_seq",
      sequenceName = "mui_cfg_id_messaggi_id_seq",
      allocationSize = 1,
      schema = "dbpromo")
  @Column(name = "ID", nullable = false, precision = 16)
  private Long id;

  @Column(name = "CODICE_CANALE", precision = 3)
  private Long codiceCanale;

  @Column(name = "CODICE_MECCANICA", length = 20)
  private String codiceMeccanica;

  @Column(name = "CODICE_DISPOSITIVO", length = 50)
  private String codiceDispositivo;

  @Column(name = "ID_MESSAGGIO", precision = 5)
  private Integer idMessaggio;

  @Column(name = "DESCRIZIONE", length = 255)
  private String descrizione;
}

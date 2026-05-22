package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the MUI_UPLOAD_FIDATY database table.
 *
 */
@Entity
@Table(name = "MUI_UPLOAD_FILE_FIDATY", schema = Metadata.SCHEMA)
@NamedQueries({
	@NamedQuery(name = "UploadFidayEntity.findByPromozione",
			query = "SELECT e FROM UploadFidayEntity e WHERE e.promozionePianificazioneEntity.promozioneTestataEntity.id = :idPromozione"),
	@NamedQuery(name = "UploadFidayEntity.findValidByPromozione",
	query = "SELECT e FROM UploadFidayEntity e WHERE e.promozionePianificazioneEntity.promozioneTestataEntity.id = :idPromozione AND e.dataSovrascrittura IS NULL"),
	@NamedQuery(name = "UploadFidayEntity.findByPianificazione",
	query = "SELECT e FROM UploadFidayEntity e WHERE e.promozionePianificazioneEntity.id = :idPianificazione"),
	@NamedQuery(name = "UploadFidayEntity.findByNomeDestinazioneAndPianificazione",
	query = "SELECT e FROM UploadFidayEntity e WHERE e.promozionePianificazioneEntity.id = :idPianificazione AND e.uploadedFileName = :nomeFile AND e.dataSovrascrittura IS NULL")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class UploadFidayEntity implements Serializable, DbPromoEntityInterface, UUIDEnabledEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_UPLOAD_FILE_FIDATY_ID_GENERATOR", sequenceName = "MUI_UPLOAD_FILE_FIDATY_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_UPLOAD_FILE_FIDATY_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;

	// bi-directional many-to-one association to FornitoreEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROMOZIONE_PIANIFICAZIONE", nullable = false)
	private PromozionePianificazioneEntity promozionePianificazioneEntity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_PUBBLICAZIONE")
	private Date dataPubblicazione;

	@Basic
	@Column(name = "USER_UPLOAD", nullable = false, length = 100)
	private String userUpload;

	@Basic
	@Column(name = "NOME_FILE_ORIGINALE", nullable = false, length = 100)
	private String originalFileName;

	@Basic
	@Column(name = "NOME_FILE_SERVER", nullable = false, length = 100)
	private String uploadedFileName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_SOVRASCRITTURA")
	private Date dataSovrascrittura;

	@Column(name = "UUID", length = 32)
	@EqualsAndHashCode.Include
	private String uuid;

	@Column(name="DATA_INIZIO_PUBBLICAZIONE")
	private Date dataInizioPublicazione;

	@Column(name="DATA_FINE_PUBBLICAZIONE")
	private Date dataFinePublicazione;

	@Column(name="DESCRIZIONE")
	private String description;

	@Column(name = "CHECKSUM_FILE", length = 100)
	private String checksumFile;

	@Override
	@PreUpdate
	@PrePersist
	public String getUuid() {
		if (this.uuid == null) {
			this.uuid = AxUUID.randomUUID().toString();
		}
		return uuid;
	}

}
package com.axiante.mui.persistence.entity;

import com.axiante.mui.business.SecurityEnumConverter;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GRUPPO", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(value = {
		@NamedQuery(name = "GroupEntity.findAllByUser", query = "Select g from GroupEntity g where :user member of g.users"),
		@NamedQuery(name = "GroupEntity.findAll", query = "Select g from GroupEntity g"),
		@NamedQuery(name = "GroupEntity.findAllWithUsers", query = "SELECT new com.axiante.mui.persistence.dto.GruppoUtenteDto(g.codiceGruppo, g.descrizione, u.name, u.description) FROM GroupEntity g LEFT OUTER JOIN g.users u ORDER BY g.codiceGruppo, u.name"),
		@NamedQuery(name = "GroupEntity.countAccessTotaleByCodiciGruppoAndTipoNotNull",
				query = "SELECT COUNT(e) FROM GroupEntity e WHERE e.codiceGruppo IN :codiciGruppo AND e.accessoTotale IS NOT NULL"),
		@NamedQuery(name = "GroupEntity.countWriteableAccessTotaleByCodiciGruppo",
				query = "SELECT COUNT(e) FROM GroupEntity e WHERE e.codiceGruppo IN :codiciGruppo AND e.accessoTotale = :accessoTotale"),
		@NamedQuery(name = "GroupEntity.countAccessAttributoByCodiciGruppoAndTipoNotNull",
				query = "SELECT COUNT(e) FROM GroupEntity e WHERE e.codiceGruppo IN :codiciGruppo AND e.accessoAttributo IS NOT NULL"),
		@NamedQuery(name = "GroupEntity.countWriteableAccessAttributoByCodiciGruppo",
				query = "SELECT COUNT(e) FROM GroupEntity e WHERE e.codiceGruppo IN :codiciGruppo AND e.accessoAttributo = :accessoAttributo")
})
@Slf4j
public class GroupEntity {

	public static final int CODE_LENGTH = 10;
	public static final int DESCRIZIONE_LENGTH = 30;

	@Id
	@SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "GRUPPO_ID_SEQ", allocationSize = 1, name = "gruppoSeq")
	@GeneratedValue(generator = "gruppoSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer id;

	@EqualsAndHashCode.Include
	@Column(name = "CODICE_GRUPPO", length = CODE_LENGTH, nullable = false)
	private String codiceGruppo;

	@Column(name = "DESCRIZIONE", length = DESCRIZIONE_LENGTH)
	private String descrizione;

	@Column(name = "TIPO_ACCESSO_TOTALE")
	@Convert(converter = SecurityEnumConverter.class)
	PianificazioneSecurityEnum accessoTotale;

	@Column(name = "TIPO_ACCESSO_ATTRIBUTO")
	@Convert(converter = SecurityEnumConverter.class)
	PianificazioneSecurityEnum accessoAttributo;

	@ManyToMany(mappedBy = "gruppi", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<UsersEntity> users;

	// bi-directional many-to-many association to Canali... comanda il Canale !!!
	@ManyToMany(mappedBy = "groups")
	private Set<CanaleEntity> canali;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "GRUPPO_REPARTO", schema = Metadata.SCHEMA, joinColumns = {
			@JoinColumn(name = "ID_GRUPPO", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ID_REPARTO", referencedColumnName = "ID") })
	Set<RepartoEntity> reparti;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "GRUPPO_COMPRATORE", schema = Metadata.SCHEMA, joinColumns = {
			@JoinColumn(name = "ID_GRUPPO", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ID_COMPRATORE", referencedColumnName = "ID") })
	Set<CompratoreEntity> compratori;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "GRUPPO_GRM", schema = Metadata.SCHEMA, joinColumns = {
			@JoinColumn(name = "ID_GRUPPO", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ID_GRM", referencedColumnName = "ID") })
	Set<GrmEntity> grm;

	@OneToMany(mappedBy = "group", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PianoMediaSecurityEntity> pianoMediaSecurity;

	public PianoMediaSecurityEntity addPianiMediaSecurity(PianoMediaSecurityEntity pianoMediaSecurity){
		if (getPianoMediaSecurity() == null ){
			setPianoMediaSecurity(new HashSet<>());
		}
		getPianoMediaSecurity().add(pianoMediaSecurity);
		pianoMediaSecurity.setGroup(this);
		return pianoMediaSecurity;
	}

	public PianoMediaSecurityEntity removePianiMediaSecurity(PianoMediaSecurityEntity pianoMediaSecurity){
		if (getPianoMediaSecurity() != null ){
			if ( getPianoMediaSecurity().remove(pianoMediaSecurity) )
				pianoMediaSecurity.setGroup(null);
		}
		return pianoMediaSecurity;
	}

	public GrmEntity addGrm(GrmEntity grm) {
		if (getGrm() == null) {
			setGrm(new HashSet<GrmEntity>());
		}
		getGrm().add(grm);
		if (grm.getGruppi() == null) {
			grm.setGruppi(new HashSet<GroupEntity>());
		}
		grm.getGruppi().add(this);
		return grm;
	}

	public GrmEntity removeGrm(GrmEntity grm) {
		if (getGrm() != null) {
			getGrm().remove(grm);
		}
		if (grm.getGruppi() != null) {
			grm.getGruppi().remove(this);
		}
		return grm;
	}

	public CompratoreEntity addCompratore(final CompratoreEntity compratore) {
		if (getCompratori() == null) {
			setCompratori(new HashSet<CompratoreEntity>());
		}
		getCompratori().add(compratore);
		if (compratore.getGruppi() == null) {
			compratore.setGruppi(new HashSet<GroupEntity>());
		}
		compratore.getGruppi().add(this);
		return compratore;
	}

	public CompratoreEntity removeCompratore(CompratoreEntity compratore) {
		if (getCompratori() != null) {
			getCompratori().remove(compratore);
		}
		if (compratore.getGruppi() != null) {
			compratore.getGruppi().remove(this);
		}
		return compratore;
	}

	public CanaleEntity addCanale(final CanaleEntity canale) {
		if (getCanali() == null) {
			setCanali(new HashSet<>());
		}
		getCanali().add(canale);
		canale.addGroup(this);
		return canale;
	}

	public CanaleEntity removeCanale(final CanaleEntity canale) {
		if (getCanali() != null) {
			getCanali().remove(canale);
		}
		canale.removeGroup(this);
		return canale;
	}

	public void addReparto(RepartoEntity reparto) {
		if (getReparti() == null) {
			setReparti(new HashSet<RepartoEntity>());
		}
		getReparti().add(reparto);
	}

	public RepartoEntity removeReparto(RepartoEntity reparto) {
		if (getReparti() != null) {
			getReparti().remove(reparto);
		}
		return reparto;
	}

	public void addUser(UsersEntity user) {
		if (getUsers() == null) {
			setUsers(new HashSet<UsersEntity>());
		}
		getUsers().add(user);
		user.addGruppo(this);
	}

	public boolean removeUser(UsersEntity user) {
		return (getUsers() != null) && getUsers().remove(user) && user.removeGruppo(this);
	}

	@Transient
	public void setAccessoTotaleAsString(String value) {
		if (value == null) {
			this.accessoTotale = null;
		} else {
			switch (value.trim().toUpperCase()) {
				case "":
					this.accessoTotale = null;
					break;
				case "READ":
					this.accessoTotale = PianificazioneSecurityEnum.READ;
					break;
				case "WRITE":
					this.accessoTotale = PianificazioneSecurityEnum.WRITE;
					break;
				default:
					log.error(String.format("Valore non riconosciuto per accesso totale %s", value));
			}
		}
	}

	@Transient
	public String getAccessoTotaleAsString() {
		return this.accessoTotale == null ? "NONE"
				: this.accessoAttributo == PianificazioneSecurityEnum.WRITE
				? "WRITE"
				: "READ";
	}

	@Transient
	public void setAccessoAttributoAsString(String value) {
		if (value == null) {
			this.accessoAttributo = null;
		} else {
			switch (value.trim().toUpperCase()) {
				case "":
					this.accessoAttributo = null;
					break;
				case "READ":
					this.accessoAttributo = PianificazioneSecurityEnum.READ;
					break;
				case "WRITE":
					this.accessoAttributo = PianificazioneSecurityEnum.WRITE;
					break;
				default:
					log.error(String.format("Valore non riconosciuto per accesso attributo %s", value));
			}
		}
	}

	@Transient
	public String getAccessoAttributoAsString() {
		return this.accessoAttributo == null ? "NONE"
				: this.accessoAttributo == PianificazioneSecurityEnum.WRITE
				? "WRITE"
				: "READ";
	}
}

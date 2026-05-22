package com.axiante.mui.persistence.entity;

import com.axiante.mui.persistence.Metadata;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "AUDIT_LOG", schema = Metadata.SCHEMA)
@Data
@NamedQueries({
        @NamedQuery(name = "AuditLogEntity.countAll",
                query = "SELECT count(e) FROM AuditLogEntity e"),
})

public class AuditLogEntity {

	@Id
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "LOG_DATE", nullable = false)
	private Date logDate;

	@Column(name = "USERNAME", nullable = false, length = 50)
	private String userName;

	@Column(name = "ACTION", nullable = false, length = 50)
	private String action;

	@Column(name = "PATH", length = 1000)
	private String path;

	@Transient
	public static final String MENU_CLICK = "NAVIGATION";

	@Transient
	public static final String BUTTON_CLICK = "ACTION";

	@Transient
	public static final String DOWNLOAD_CLICK = "DOWNLOAD";
	@Transient
	public static final String EXTERNAL_RESOURCE = "EXTERNAL_RESOURCE";

	@Transient
	public static final String PIANO_MEDIA = "PIANO_MEDIA";
}

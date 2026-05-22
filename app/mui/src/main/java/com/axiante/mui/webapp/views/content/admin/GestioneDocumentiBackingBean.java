package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.views.content.admin.pojos.UploadDocumentPojo;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

public class GestioneDocumentiBackingBean implements FacesContextAware {

    @Getter
    @Setter
    private List<UploadDocumentPojo> documents;

    @Getter
    @Setter
    private RolesEntity selectedRole;

    @Getter
    @Setter
    private UploadDocumentPojo[] selectedDocs;

    @Getter
    @Setter
    private UploadDocumentEntity selectedDoc;

    @Getter
    @Setter
    private boolean btnSelectAllDocsDisabled = true;

    @Getter
    @Setter
    private boolean btnDeselectAllDocsDisabled = true;

    @Getter
    @Setter
    private boolean selectManyDisabled = true;

    @Getter
    @Setter
    private UploadedFile uploadedFile;

    @Getter
    @Setter
    private String tmpUploadedFilename;
}

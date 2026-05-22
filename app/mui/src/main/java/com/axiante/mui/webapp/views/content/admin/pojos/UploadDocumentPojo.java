package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UploadDocumentPojo {

    @Getter
    @Setter
    private UploadDocumentEntity entity;

    @Getter
    @Setter
    private boolean associated;
}

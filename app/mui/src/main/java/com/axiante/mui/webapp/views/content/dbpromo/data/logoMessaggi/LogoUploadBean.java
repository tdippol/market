package com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;
import java.io.File;
import java.io.Serializable;

@Getter
@Setter
public class LogoUploadBean implements Serializable {
    private UploadedFile uploadedLogo;
    private File tempLogoFile;

    public void preloadDefaults() {
        uploadedLogo = null;
        if (tempLogoFile != null && tempLogoFile.exists()) {
            tempLogoFile.delete();
            tempLogoFile = null;
        }
    }

    public void messaggioChanged() {
        if (tempLogoFile != null && tempLogoFile.exists()) {
            tempLogoFile.delete();
            tempLogoFile = null;
        }
    }

    public void removeLogo() {
        setUploadedLogo(null);
        if (tempLogoFile != null && tempLogoFile.exists()) {
            tempLogoFile.delete();
        }
    }
}
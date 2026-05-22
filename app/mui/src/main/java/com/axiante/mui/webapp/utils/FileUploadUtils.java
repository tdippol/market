package com.axiante.mui.webapp.utils;

import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;

import static com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity.CODICE_F;

public class FileUploadUtils {

    public static synchronized String createFileName(String logoFileName, String codicePromo, Integer idMessaggio,
                                                     Integer seqStampa, CfgCanaleDispositivoEntity dispositivo) {
        String[] fileParts = logoFileName.split("\\.");
        String fileName = fileParts[0];
        String fileExtension = "";
        if (fileParts.length > 1) {
            fileExtension = fileParts[1];
        }
        if (CODICE_F.equalsIgnoreCase(dispositivo.getCodice())) {
            return String.format("%s_%s.%s", fileName, codicePromo, fileExtension);
        }
        return String.format("%s_%s_%s_%s_%s.%s",
                fileName, codicePromo, idMessaggio, seqStampa, dispositivo.getCodice(), fileExtension);
    }
}

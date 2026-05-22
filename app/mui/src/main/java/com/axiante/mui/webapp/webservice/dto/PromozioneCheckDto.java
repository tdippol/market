package com.axiante.mui.webapp.webservice.dto;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import javax.ws.rs.core.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PromozioneCheckDto {

    @Getter
    private PromozioneTestataEntity testata;

    @Getter
    private List<String> codiciGruppo;

    @Getter
    @Setter
    private Long idMeccanica;

    @Getter
    private Response.Status responseStatus;

    @Getter
    private String errorMessage;

    public PromozioneCheckDto(PromozioneTestataEntity testata, List<String> codiciGruppo) {
        this.testata = testata;
        this.codiciGruppo = codiciGruppo;
    }

    public boolean isError() {
        return errorMessage != null && !errorMessage.trim().isEmpty();
    }

    public static PromozioneCheckDto error(Response.Status status, String errorMessage) {
        final PromozioneCheckDto dto = new PromozioneCheckDto();
        dto.responseStatus = status;
        dto.errorMessage = errorMessage;
        return dto;
    }

    public void setError(String errorMessage, Response.Status status) {
        this.errorMessage = errorMessage;
        this.responseStatus = status;
    }
}

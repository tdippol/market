package com.axiante.mui.webapp.webservice.dto;

import com.axiante.mui.webapp.business.data.PianoMediaDettaglioDTO;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PianoMediaDettaglioArticoliDto {
    @Getter
    @Setter
    private List<PianoMediaDettaglioDTO> dettagli;

    @Getter
    @Setter
    private String validationMessage;

    public PianoMediaDettaglioArticoliDto(List<PianoMediaDettaglioDTO> dettagli) {
        this.dettagli = dettagli;
    }

    public static PianoMediaDettaglioArticoliDto error(String message) {
        final PianoMediaDettaglioArticoliDto dto = new PianoMediaDettaglioArticoliDto();
        dto.setValidationMessage(message);
        return dto;
    }

    public boolean isError() {
        return validationMessage != null && !validationMessage.isEmpty();
    }
}

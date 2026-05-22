package com.axiante.mui.webapp.webservice.dto;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.webapp.webservice.pojo.PianificazionePianoMedia;
import com.axiante.mui.webapp.webservice.pojo.SupportoMedia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PianificazioniPianoDto {
    private String startDate;
    private Long days;
    private EnabledDisabledFlag timeRangeSelectedHandling = EnabledDisabledFlag.DISABLED;
    private List<SupportoMedia> supportiMedia;
    private List<PianificazionePianoMedia> pianificazioniPiano;
    private String message;

    public String getTimeRangeSelectedHandling() {
        return timeRangeSelectedHandling.getValue();
    }

    @JsonIgnore
    public String asJson() {
        return JsonUtils.asJson(this);
    }
}

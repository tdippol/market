package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@MuiViewModel("overivewPianoMedia")
@Dependent
@Slf4j
public class OveriviewPianoMediaView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 3521326746894856145L;

    @Getter
    Integer selectedYear;

    @Getter
    List<Integer> years;

    @Getter
    private final Scale[] validScales = {
            new Scale("Giornaliera", "Day"),
            new Scale("Settimanale", "Week"),
            new Scale("Mensile", "Month")
    };

    @Getter
    private String scale = "Day";

    @PostConstruct
    public void init() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        selectedYear = calendar.get(Calendar.YEAR);
        years = pianoMediaServiceInstance.get().findOpenAvailableYears().stream().sorted().collect(Collectors.toList());
        if (years.isEmpty()) {
            years.add(selectedYear);
        }
    }

    @Inject
    Instance<PianoMediaService> pianoMediaServiceInstance;

    @Inject
    Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }

    public void setSelectedYear(Integer selectedYear) {
        this.selectedYear = selectedYear;
        if (this.selectedYear != null) {
            executeScript("setSelectedYear(" + selectedYear + ")");
        }
    }

    public void setScale(String scale){
        this.scale = scale;
        executeScript(String.format("changeScale('%s')", scale));
    }

    public class Scale {
        @Getter
        String name;

        @Getter
        String value;

        public Scale(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}

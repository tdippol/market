package com.axiante.mui.webapp.webservice.validator;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import lombok.NonNull;

@Dependent
public class PromozioneTestataValidator {

    private static final String SUFFIX_SHOP = "i negozi";
    private static final String SUFFIX_PLANNING = "la pianificazione";

    public String validateDates(@NonNull PromozioneTestataEntity testata, Date startDate, Date endDate) {
        final DateTimeUtils dateTimeUtils = new DateTimeUtils();
        List<String> messages = new ArrayList<>();
        final List<PromozioneNegozioEntity> invalidShops = new ArrayList<>();
        final List<PromozionePianificazioneEntity> invalidPlanning = new ArrayList<>();
        if (startDate != null) {
            invalidShops.addAll(testata.getPromozioneNegozioEntities().stream()
                    .filter(n -> !dateTimeUtils.equalsNoTime(n.getDataInizio(), testata.getDataInizio()))
                    .filter(n -> !dateTimeUtils.compatibleDates(n.getDataInizio(), startDate, DateTimeUtils.INIZIO))
                    .collect(Collectors.toList()));
            invalidPlanning.addAll(testata.getPromozionePianificazioneEntities().stream()
                    .filter(p -> p.getParent() == null)
                    .filter(p -> Objects.nonNull(p.getDataInizio()))
                    .filter(p -> !dateTimeUtils.equalsNoTime(p.getDataInizio(), testata.getDataInizio()))
                    .filter(p -> !dateTimeUtils.compatibleDates(p.getDataInizio(), startDate, DateTimeUtils.INIZIO))
                    .collect(Collectors.toList()));
        }
        if (endDate != null) {
            invalidShops.addAll(testata.getPromozioneNegozioEntities().stream()
                    .filter(n -> !dateTimeUtils.equalsNoTime(n.getDataFine(), testata.getDataFine()))
                    .filter(n -> !dateTimeUtils.compatibleDates(n.getDataFine(), endDate, DateTimeUtils.FINE))
                    .collect(Collectors.toList()));
            invalidPlanning.addAll(testata.getPromozionePianificazioneEntities().stream()
                    .filter(p -> p.getParent() == null)
                    .filter(p -> Objects.nonNull(p.getDataFine()))
                    .filter(p -> !dateTimeUtils.equalsNoTime(p.getDataFine(), testata.getDataFine()))
                    .filter(p -> !dateTimeUtils.compatibleDates(p.getDataFine(), endDate, DateTimeUtils.FINE))
                    .collect(Collectors.toList()));
        }
        if (!invalidShops.isEmpty()) {
            messages.add(createMessage(testata, startDate, endDate, SUFFIX_SHOP));
        }
        if (!invalidPlanning.isEmpty()) {
            messages.add(createMessage(testata, startDate, endDate, SUFFIX_PLANNING));
        }

        return messages.isEmpty() ? null : String.join("; ", messages);
    }

    private String createMessage(@NonNull PromozioneTestataEntity testata, Date startDate, Date endDate,
                                 @NonNull final String suffix) {
        final StringBuilder msg = new StringBuilder("Data ");
        final boolean inizio = startDate != null && !startDate.equals(testata.getDataInizio());
        if (inizio) {
            msg.append("inizio ");
        }
        if (endDate != null && !endDate.equals(testata.getDataFine())) {
            if (inizio) {
                msg.append("e ");
            }
            msg.append("fine ");
        }
        msg.append("non coerente con ").append(suffix);
        return msg.toString();
    }
}

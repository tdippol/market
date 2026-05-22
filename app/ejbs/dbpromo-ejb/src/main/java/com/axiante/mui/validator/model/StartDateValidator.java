package com.axiante.mui.validator.model;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.util.PromotionValidatorModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PromotionValidatorModel("startDateValidator")
public class StartDateValidator implements PromotionValidator {
    @Override
    public String validate(Promotion promotion) {

        List<String> messages = new ArrayList<>();
        final DateTimeUtils dateTimeUtils = new DateTimeUtils();
        LocalDateTime currentDate = dateTimeUtils.getDBPromoMinDate();

        Date dataInizio = promotion.getDataInizio();
        if (dataInizio == null) {
            return null;
        }

        LocalDate startDate = dataInizio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if ((!startDate.isEqual(currentDate.toLocalDate())
                && startDate.isBefore(currentDate.toLocalDate()))) {
            messages.add(MessageConsts.START_DATE_AFTER_CURRENT_DATE);
        }
        return messages.isEmpty() ? null : String.join(";", messages);
    }
}
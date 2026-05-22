package com.axiante.mui.validator.model;

import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.util.PromotionValidatorModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PromotionValidatorModel("hoursValidator")
public class HoursValidator implements PromotionValidator {

    @Override
    public String validate(Promotion promotion) {
        List<String> messages = new ArrayList<>();
        if (promotion.getOraInizio() != null && promotion.getOraFine() == null) {
            messages.add(MessageConsts.START_HOUR_WITHOUT_END);
        }
        if (promotion.getOraFine() != null && promotion.getOraInizio() == null) {
            messages.add(MessageConsts.END_HOUR_WITHOUT_START);
        }
        if (promotion.getOraInizio() != null && promotion.getOraFine() != null) {
            final Date oraInizio = promotion.getOraInizio();
            final Date oraFine = promotion.getOraFine();
            if (oraFine.before(oraInizio) || oraFine.equals(oraInizio)) {
                messages.add(MessageConsts.END_HOUR_BEFORE_START);
            }
        }
        return messages.isEmpty() ? null : String.join(";", messages);
    }
}

package com.axiante.mui.validator.model;

import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.util.PromotionValidatorModel;
import java.util.ArrayList;
import java.util.List;

@PromotionValidatorModel("endDateValidator")
public class EndDateValidator implements PromotionValidator {
    @Override
    public String validate(Promotion promotion) {
        List<String> messages = new ArrayList<>();
        if (promotion.getDataInizio() == null || promotion.getDataFine() == null) {
            return null;
        }

        if (!promotion.getDataFine().equals(promotion.getDataInizio())
                && promotion.getDataFine().before(promotion.getDataInizio())) {
            messages.add(MessageConsts.END_DATE_BEFORE_START_DATE);
        }
        return messages.isEmpty() ? null : String.join(";", messages);
    }
}
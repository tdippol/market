package com.axiante.mui.validator.model;

import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.util.PromotionValidatorModel;
import java.util.ArrayList;
import java.util.List;

@PromotionValidatorModel("descriptionValidator")
public class DescriptionValidator implements PromotionValidator {
    @Override
    public String validate(Promotion promotion) {
        return validate(promotion, 100);
    }

    public String validate(Promotion promotion, int maxLength) {
        List<String> messages = new ArrayList<>();
        if (promotion.getDescrizione() == null) {
            return null;
        }

        if (promotion.getDescrizione().length() > maxLength) {
            messages.add(String.format(MessageConsts.DESCRIPTION_TOO_LONG, maxLength));
        }
        if (!promotion.getDescrizione().equals(promotion.getDescrizione().toUpperCase())) {
            messages.add(MessageConsts.DESCRIPTION_NOT_UPPERCASE);
        }
        return messages.isEmpty() ? null : String.join(";", messages);
    }
}
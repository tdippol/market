package com.axiante.mui.validator.model;

import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.util.PromotionValidatorModel;
import java.util.ArrayList;
import java.util.List;

@PromotionValidatorModel("noteMarketingValidator")
public class NoteMarketingValidator implements PromotionValidator {

    @Override
    public String validate(Promotion promotion) {
        List<String> messages = new ArrayList<>();
        String noteMarketing = promotion.getNoteMarketing();
        if (noteMarketing == null)
            return null;

        if (noteMarketing.length() > 100) {
            messages.add(MessageConsts.NOTE_MARKETING_TOO_LONG);
        }
        return messages.isEmpty() ? null : String.join(";", messages);
    }
}

package com.axiante.mui.validator.model;

import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.util.PromotionValidatorModel;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Setter
@PromotionValidatorModel("yearValidator")
public class YearValidator implements PromotionValidator {
    private boolean isEdit = false;

    @Override
    public String validate(Promotion promotion) {
        List<String> messages = new ArrayList<>();
        if(promotion.getAnno() == null) {
            return null;
        }

        if (promotion.getDataFine() != null && promotion.getDataInizio() != null) {
            LocalDate startDate = promotion.getDataInizio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = promotion.getDataFine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (!(promotion.getAnno().equals(String.valueOf(startDate.getYear()))
                    || promotion.getAnno().equals(String.valueOf(endDate.getYear())))) {
                messages.add(MessageConsts.START_END_DATES_YEAR_NOT_VALID);
            }
        }
        if (promotion.getAnno() != null && !isEdit) {
            if (!(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).equals(promotion.getAnno())
                    || (String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1)).equals(promotion.getAnno()))) {
                messages.add(MessageConsts.YEAR_NOT_CURRENT_OR_NEXT);
            }
        }
        return messages.isEmpty() ? null : String.join(";", messages);
    }
}

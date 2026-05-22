package com.axiante.mui.validator.factory;

import com.axiante.mui.validator.model.PromotionValidator;
import com.axiante.mui.validator.model.PromotionValidatorEnum;
import com.axiante.mui.validator.util.PromotionValidatorLiteral;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
public class PromotionValidatorFactory implements Serializable{

    @Inject
    @Any
    transient Instance<PromotionValidator> promotionValidators;

    public PromotionValidator getPromotionValidator(String column) {
        Optional<PromotionValidatorEnum> validatorEnum =  Arrays.stream(PromotionValidatorEnum.values())
                .filter(Objects::nonNull).filter(c -> c.getColumn().equalsIgnoreCase(column))
                .findFirst();
        return validatorEnum.isPresent() ? promotionValidators.select(new PromotionValidatorLiteral(validatorEnum.get().getValidatorModel())).get() : null;
    }
}

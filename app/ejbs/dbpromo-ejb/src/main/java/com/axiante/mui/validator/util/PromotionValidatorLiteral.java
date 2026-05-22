package com.axiante.mui.validator.util;

import javax.enterprise.util.AnnotationLiteral;

public class PromotionValidatorLiteral extends AnnotationLiteral<PromotionValidatorModel> implements PromotionValidatorModel {
	private static final long serialVersionUID = -5908808229223038215L;
		private String type;
        @Override
        public String value() {
            return this.type;
        }

        public PromotionValidatorLiteral(String type) {
            this.type = type;
        }

}

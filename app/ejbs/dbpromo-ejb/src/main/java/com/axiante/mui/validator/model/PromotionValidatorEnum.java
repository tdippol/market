package com.axiante.mui.validator.model;

public enum PromotionValidatorEnum {
    YEAR("anno") {
        @Override
        public PromotionValidator getValidator() {
            return new YearValidator();
        }

        @Override
        public String getValidatorModel() {
            return "yearValidator";
        }

    },
    DESCRIPTION("descrizione") {
        @Override
        public PromotionValidator getValidator() {
            return new DescriptionValidator();
        }
        @Override
        public String getValidatorModel() {
            return "descriptionValidator";
        }
    },
    NOTE_MARKETING("noteMarketing") {
        @Override
        public PromotionValidator getValidator() {
            return new NoteMarketingValidator();
        }
        @Override
        public String getValidatorModel() {
            return "noteMarketingValidator";
        }
    },
    CHANNEL("canalePromozioneEntity") {
        @Override
        public PromotionValidator getValidator() {
            return new ChannelValidator();
        }
        @Override
        public String getValidatorModel() {
            return "channelValidator";
        }
    },
    START_DATE("dataInizio") {
        @Override
        public PromotionValidator getValidator() {
            return new StartDateValidator();
        }
        @Override
        public String getValidatorModel() {
            return "startDateValidator";
        }
    },
    END_DATE("dataFine") {
        @Override
        public PromotionValidator getValidator() {
            return new EndDateValidator();
        }
        @Override
        public String getValidatorModel() {
            return "endDateValidator";
        }
    };

    PromotionValidatorEnum(String column)
    {
        this.column = column;
    }

    private String column;

    public String getColumn()
    {
        return column;
    }

    public abstract PromotionValidator getValidator();
    public abstract String getValidatorModel();
}

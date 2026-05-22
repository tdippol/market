package com.axiante.mui.validator.service.impl;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.factory.PromotionValidatorFactory;
import com.axiante.mui.validator.model.DescriptionValidator;
import com.axiante.mui.validator.model.EndDateValidator;
import com.axiante.mui.validator.model.HoursValidator;
import com.axiante.mui.validator.model.NoteMarketingValidator;
import com.axiante.mui.validator.model.Promotion;
import com.axiante.mui.validator.model.PromotionValidator;
import com.axiante.mui.validator.model.StartDateValidator;
import com.axiante.mui.validator.model.YearValidator;
import com.axiante.mui.validator.service.PromotionValidatorService;
import com.axiante.mui.validator.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Dependent
@Transactional
@Slf4j
public class PromotionValidatorServiceImpl implements PromotionValidatorService {
    @Inject
    private transient PromotionValidatorFactory factory;

    @Override
    public List<String> validateNewPromotion(Promotion entity, List<CanalePromozioneEntity> listaCanaliAbilitati,
                                             GruppoPromozioneEntity gruppoPromozione) {
        if (entity != null) {
            List<String> messagesList = parameters(entity).keySet()
                    .stream()
                    .map(o -> {
                        PromotionValidator validator = factory.getPromotionValidator(o);
                        return validator != null
                                ? validator.validate(entity)
                                : null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            if(listaCanaliAbilitati != null && entity.getCanalePromozioneEntity() != null) {
            	if(listaCanaliAbilitati.stream()
            			.map(CanalePromozioneEntity::getCodiceCanale)
            			.noneMatch(c -> c.equals(entity.getCanalePromozioneEntity().getCodiceCanale()))) {
            	    entity.setCanalePromozioneEntity(null);
            		messagesList.add("Canale promozione non accessibile");
            	}

                if( gruppoPromozione != null && gruppoPromozione.getCodiceGruppo() !=null) {
                    List<CanalePromozioneEntity> listaCanaliAbilitatiFiltrati = new ArrayList<>();
                    listaCanaliAbilitati.forEach(canalePromozione -> {
                        if(canalePromozione.getGruppoPromozioneEntity()!=null) {
                            if(canalePromozione.getGruppoPromozioneEntity().getCodiceGruppo().equals(gruppoPromozione.getCodiceGruppo())) {
                                listaCanaliAbilitatiFiltrati.add(canalePromozione);
                            }
                        }
                    });

                    if (!listaCanaliAbilitatiFiltrati.isEmpty() && listaCanaliAbilitatiFiltrati.stream()
                            .map(c -> c.getGruppoPromozioneEntity().getCodiceGruppo()).distinct()
                            .noneMatch(c -> c.equals(gruppoPromozione.getCodiceGruppo()))) {
                        messagesList.add("Gruppo promozione non accessibile");
                    }
                }
            }
            return !messagesList.isEmpty() ? messagesList : null;
        } else {
            return null;
        }
    }

    @Override
    public Set<String> validateEditPromotion(Promotion promotion, PromozioneTestataEntity entity) {
        if (promotion == null || entity == null) {
            return null;
        }
        Set<String> messagesList = new HashSet<>();
        PromotionValidator promotionValidator;

        // start date
        if (promotion.getDataInizio() != null && !promotion.getDataInizio().equals(entity.getDataInizio())) {
            promotionValidator = new StartDateValidator();
            String validate = promotionValidator.validate(promotion);
            if (validate != null) {
                messagesList.add(validate);
            }
            //I controlli sulle date sono collegate all'anno
            promotionValidator = new YearValidator();
            ((YearValidator) promotionValidator).setEdit(true);
            validate = promotionValidator.validate(promotion);
            if (validate != null) {
                messagesList.add(validate);
            }
        }

        if (promotion.getDataFine() != null) {
            promotionValidator = new EndDateValidator();
            String validate = promotionValidator.validate(promotion);
            if (validate != null) {
                messagesList.add(validate);
            }
            com.axiante.mui.common.utility.DateTimeUtils utils = new com.axiante.mui.common.utility.DateTimeUtils();
            if ( !utils.getDateWithoutTime(entity.getDataFine()).equals(utils.getDateWithoutTime(promotion.getDataFine()))){
                //devo controllare che la data fine sia maggiore di oggi
                if (LocalDate.now().isAfter(promotion.getDataFine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) ) {
                    messagesList.add(MessageConsts.END_DATE_BEFORE_TODAY);
                }
            }
            //I controlli sulle date sono collegate all'anno
            promotionValidator = new YearValidator();
            ((YearValidator) promotionValidator).setEdit(true);
            validate = promotionValidator.validate(promotion);
            if (validate != null) {
                messagesList.add(validate);
            }
        }

        if (promotion.getDescrizione() != null && !promotion.getDescrizione().equals(entity.getDescrizione())) {
            String validate = new DescriptionValidator().validate(promotion, 40);
            if (validate != null) {
                messagesList.add(validate);
            }
        }

        if (promotion.getNoteMarketing() != null && !promotion.getNoteMarketing().equals(entity.getNoteMarketing())) {
            promotionValidator = new NoteMarketingValidator();
            String validate = promotionValidator.validate(promotion);
            if (validate != null) {
                messagesList.add(validate);
            }
        }

        // #3444: Check ORA_INIZIO e ORA_FINE
        if (promotion.getOraInizio() != null || promotion.getOraFine() != null) {
            promotionValidator = new HoursValidator();
            final String validate = promotionValidator.validate(promotion);
            if (validate != null) {
                messagesList.add(validate);
            }
        }

        return messagesList;
    }

    private Map<String, Object> parameters(Object obj) {
        ArrayList<Method> methods = new ReflectionUtil().findGetters(obj.getClass());
        Map<String, Object> map = new HashMap<>();
        for (Method method : methods) {
            try {
                String field = method.getName().substring(3);
                map.put(Character.toLowerCase(field.charAt(0)) + field.substring(1), method.invoke(obj));
            } catch (Exception e) {
               log.error("Error invoking getter during validation ", e);
            }
        }
        return map;
    }

}


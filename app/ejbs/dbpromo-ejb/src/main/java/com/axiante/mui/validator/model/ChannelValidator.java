package com.axiante.mui.validator.model;

import com.axiante.mui.dbpromo.persistence.dao.CanalePromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.validator.constants.MessageConsts;
import com.axiante.mui.validator.util.PromotionValidatorModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@PromotionValidatorModel("channelValidator")
public class ChannelValidator implements PromotionValidator {

    @Inject
    private CanalePromozioneDAO canalePromozioneDAO;

    @Override
    public String validate(Promotion promotion) {
        List<String> messages = new ArrayList<>();

        CanalePromozioneEntity canalePromozioneEntity = promotion.getCanalePromozioneEntity();

        CanalePromozioneEntity singleChannel = findSingleChannel(canalePromozioneEntity);
        if (singleChannel != null) {
            if (!(canalePromozioneEntity.getDescrizione().equals(singleChannel.getDescrizione()))) {
                messages.add(MessageConsts.DEFAULT_CHANNEL_NOT_SELECTED);
            }
        }
        if (canalePromozioneEntity != null
                && (canalePromozioneEntity.getGruppoPromozioneEntity() == null || canalePromozioneEntity.getGruppoPromozioneEntity().getDescrizione() == null)
                && canalePromozioneEntity.getDescrizione() != null) {
            messages.add(MessageConsts.NOT_EMPTY_CHANNEL_NO_GROUP);
        }
        return messages.isEmpty() ? null : String.join(";", messages);
    }

    private CanalePromozioneEntity findSingleChannel(CanalePromozioneEntity canalePromozioneEntity) {
        if (canalePromozioneEntity == null)
            return null;

        List<CanalePromozioneEntity> canalePromozioneEntities = canalePromozioneDAO.findAllByGroup(canalePromozioneEntity.getGruppoPromozioneEntity());
        if (canalePromozioneEntities != null) {
            if (canalePromozioneEntities.size() == 1) {
                return canalePromozioneEntities.get(0);
            }
        }
        return null;
    }
}
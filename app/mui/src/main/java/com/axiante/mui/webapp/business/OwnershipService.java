package com.axiante.mui.webapp.business;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface OwnershipService {
    boolean hasOwnership(PromozioneTestataEntity testata, List<String> groups);
}

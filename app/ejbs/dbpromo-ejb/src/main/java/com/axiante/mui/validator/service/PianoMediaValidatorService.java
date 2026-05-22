package com.axiante.mui.validator.service;

import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.fasterxml.jackson.databind.JsonNode;

public interface PianoMediaValidatorService {
    CreaPianoMediaEntity validateCreaPianoMedia(String payload);

    CreaPianoMediaEntity validateCreaPianoMedia(JsonNode node);
}

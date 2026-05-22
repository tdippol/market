package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.MuiCfgDefaultCastellettoMessaggiService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
@Slf4j
public class DefaultMessaggiHelper {

  @Inject
  @Getter(AccessLevel.PROTECTED)
  CastellettoMessaggiService castellettoMessaggiService;

  @Inject
  @Getter(AccessLevel.PROTECTED)
  Instance<CfgConfHeaderService> cfgConfHeaderService;

  @Inject
  @Getter(AccessLevel.PROTECTED)
  Instance<MuiCfgDefaultCastellettoMessaggiService> muiCfgDefaultCastellettoMessaggiService;

  /**
   * Adds default messages to the given list of promotion planning entities.
   *
   * @param pianificazioni the list of promotion planning entities to which default messages will be
   *     added
   * @param utente the user performing the operation
   */
  public void addDefaultMessageRows(
      List<PromozionePianificazioneEntity> pianificazioni, String utente) {
    log.debug("addDefaultMessaggi for {} pianificazioni", pianificazioni.size());
    pianificazioni.stream()
        .filter(this::isCanaleAbilitato)
        .filter(this::isMeccanicaAbilitata)
        .filter(this::hasNoPreviousMessages)
        .forEach(p -> addDefaultMessageRows(p, utente));
  }

  /**
   * Adds default messages to the given promotion planning entity if the channel and mechanics are
   * enabled and there are no existing messages.
   *
   * @param pianificazione the promotion planning entity to which default messages will be added
   * @param utente the user performing the operation
   */
  public void addDefaultMessageRows(PromozionePianificazioneEntity pianificazione, String utente) {
    if (isCanaleAbilitato(pianificazione) && isMeccanicaAbilitata(pianificazione)) {

      // devo inserire il messaggio nella pianificazione root... la cerco
      PromozionePianificazioneEntity segnapostoMessaggio = pianificazione;
      while (segnapostoMessaggio.getParent() != null) {
        segnapostoMessaggio = segnapostoMessaggio.getParent();
      }
      // se non ci sono gia' dei messaggi ...
      if (hasNoPreviousMessages(segnapostoMessaggio)) {
        // inserisco i messaggi di default
        addMessageRows(segnapostoMessaggio, utente);
      }
    }
  }

  /**
   * Adds default messages to the given promotion planning entity.
   *
   * @param pianificazione the promotion planning entity to which default messages will be added
   * @param utente the user performing the operation
   */
  protected void addMessageRows(PromozionePianificazioneEntity pianificazione, String utente) {
    // leggo i messaggi di default per canale, meccanica e dispositivo
    List<CastellettoMessaggiEntity> messaggiDaInserire =
        getDefaultMessages(pianificazione).stream()
            .map(m -> mapToMessageRow(m, pianificazione, utente))
            .collect(Collectors.toList());
    // e li scrivo
    try {
      getCastellettoMessaggiService().persist(messaggiDaInserire, messaggiDaInserire.size());
      log.info(
          "Inserted {} default message rows for planning {}",
          messaggiDaInserire.size(),
          pianificazione.getId());
    } catch (Exception e) {
      log.error("Error saving default messages", e);
    }
  }

  // mapper

  /**
   * Creates a new CastellettoMessaggiEntity from the given default message, promotion planning
   * entity, and user.
   *
   * @param messaggio the default message configuration entity
   * @param pianificazione the promotion planning entity
   * @param utente the user performing the operation
   * @return the created CastellettoMessaggiEntity
   */
  public CastellettoMessaggiEntity mapToMessageRow(MuiCfgDefaultCastellettoMessaggiEntity messaggio,
                                                   PromozionePianificazioneEntity pianificazione,
                                                   String utente) {
    CastellettoMessaggiEntity e = new CastellettoMessaggiEntity();
    e.setPianificazione(pianificazione);
    e.setSeqStampa(Integer.valueOf(messaggio.getSeqStampa()));
    e.setSezione(messaggio.getSezione());
    e.setTesto(messaggio.getTesto());
    e.setTaglioCarta(messaggio.getTaglioCarta());
    e.setBarcode(messaggio.getBarcode());
    e.setFont(messaggio.getFont());
    e.setAllineamento(messaggio.getAllineamento());
    e.setBold(messaggio.getBold());
    e.setLogo(messaggio.getLogo());
    e.setDataInserimento(new Date());
    e.setCodiceUtenteInserimento(utente);
    e.setCodiceCanaleDispositivo(messaggio.getCodiceDispositivo());
    e.setIdMessaggio(messaggio.getIdMessaggio());
    e.setFontStile(messaggio.getFontStile());
    e.setBottone(messaggio.getBottone());
    e.setCodice(messaggio.getCodice());
    e.setRegolamento(messaggio.getRegolamento());
    e.setBarra(messaggio.getBarra());
    e.setFontEntity(messaggio.getFontEntity() != null ? messaggio.getFontEntity().getId() : null);
    log.debug("Default message created: {}", e);
    return e;
  }

  // extractor

  /**
   * Retrieves the default messages for the given promotion planning entity based on the channel,
   * mechanics, and device code.
   *
   * @param pianificazione the promotion planning entity for which to retrieve default messages
   * @return the list of default message configuration entities
   */
  List<MuiCfgDefaultCastellettoMessaggiEntity> getDefaultMessages(
      PromozionePianificazioneEntity pianificazione) {
    // al momento c'e' un problema di processo e il codice dispositivo potrebbe non
    // essere presente in pianificazione
    if (pianificazione.getCanaleDispositivo() != null) {
      return getMuiCfgDefaultCastellettoMessaggiService()
          .get()
          .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
              pianificazione
                  .getPromozioneTestataEntity()
                  .getCanalePromozioneEntity()
                  .getCodiceCanale(),
              pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
              pianificazione.getCanaleDispositivo());
    } else {
      // se non ho un dispositivo allora metto tutti i messaggi
      // configurati per default sulla meccanica/canale
      return getMuiCfgDefaultCastellettoMessaggiService()
          .get()
          .findByCodiceCanaleAndCodiceMeccanica(
              pianificazione
                  .getPromozioneTestataEntity()
                  .getCanalePromozioneEntity()
                  .getCodiceCanale(),
              pianificazione.getMeccanicaEntity().getCodiceMeccanica());
    }
  }

  // filters

  /**
   * Checks if the given promotion planning entity has no existing messages. This method is used to
   * determine if default messages need to be added to the entity.
   *
   * @param p the promotion planning entity to check for existing messages
   * @return true if the entity has no existing messages, false otherwise
   */
  private boolean hasNoPreviousMessages(PromozionePianificazioneEntity p) {
    return getCastellettoMessaggiService().findMessaggiByIdPianificazione(p.getId()).isEmpty();
  }

  /**
   * Checks if the channel of the given promotion planning entity is enabled for messages. This
   * method is used to determine if default messages can be added to the entity.
   *
   * @param p the promotion planning entity to check for channel enablement
   * @return true if the channel is enabled, false otherwise
   */
  private boolean isCanaleAbilitato(PromozionePianificazioneEntity p) {
    return p.getPromozioneTestataEntity().getCanalePromozioneEntity().getFlLogoMessaggio();
  }

  /**
   * Checks if the mechanics of the given promotion planning entity is enabled for messages. This
   * method is used to determine if default messages can be added to the entity.
   *
   * @param p the promotion planning entity to check for mechanics enablement
   * @return true if the mechanics is enabled, false otherwise
   */
  private boolean isMeccanicaAbilitata(PromozionePianificazioneEntity p) {
    return getCfgConfHeaderService()
        .get()
        .findByMeccanicaIdAndSetPianificazioneId(
            p.getMeccanicaEntity().getId(),
            p.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getId())
        .getLogoMessaggi();
  }
}

package com.axiante.mui.webapp.business.service;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.webapp.business.supportimedia.SupportoMediaCheckEnum;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianoMediaControlliFactory {

    private PianoMediaControlliFactory(){

    }

    public PianoMediaControlliFactory get(){
        return new PianoMediaControlliFactory();
    }

    @Inject
    Instance<PianoMediaPromoDbpromoService> pianoMediaPromoDbpromoServiceInstance;

    Function<PianificazionePianoMediaEntity, Boolean> dummy = (ppm) -> {
        log.error("dummy interface should never be called");
        return false;
    };

    /**
     * Controlla che DataInizio di PianificazionePianoMedia coincida con DataInizio della Campagna
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk1 = (ppm) -> {
        PianoMediaPromoDbpromoEntity pianoMediaPromo =
                pianoMediaPromoDbpromoServiceInstance.get().findByCodicePromo(ppm.getPianoMedia().getPromoMaster());
        if (pianoMediaPromo == null) {
            return false;
        }
        return ppm.getDataInizio().equals(pianoMediaPromo.getDataInizio());
    };

    /**
     * Controlla che la durata di PianificazionePianoMedia (differenza tra DataFine e DataInizio) sia minore di 3 giorni
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk2 = (ppm) -> {
        final Date dataInizio = ppm.getDataInizio();
        final Date dataFine = ppm.getDataFine();
        if (dataInizio == null || dataFine == null) {
            return false;
        }
        final long diffMillies = dataFine.getTime() - dataInizio.getTime();
        if (diffMillies < 0) {
            return false;
        }
        return TimeUnit.DAYS.convert(diffMillies, TimeUnit.MILLISECONDS) < 3;
    };

    /**
     * Controlla che la durata di PianificazionePianoMedia (differenza tra DataFine e DataInizio) sia maggiore di 5 giorni
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk3 = (ppm) -> {
        final Date dataInizio = ppm.getDataInizio();
        final Date dataFine = ppm.getDataFine();
        if (dataInizio == null || dataFine == null) {
            return false;
        }
        final long diffMillies = dataFine.getTime() - dataInizio.getTime();
        if (diffMillies < 0) {
            return false;
        }
        return TimeUnit.DAYS.convert(diffMillies, TimeUnit.MILLISECONDS) > 5;
    };

    /**
     * Controlla che la dataFine di PianificazionePianoMedia sia 1 giorno prima della DataFine della Testata
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk4 = (ppm) -> {
        PianoMediaPromoDbpromoEntity pianoMediaPromo =
                pianoMediaPromoDbpromoServiceInstance.get().findByCodicePromo(ppm.getPianoMedia().getPromoMaster());
        if (pianoMediaPromo == null) {
            return false;
        }
        final Date dataFinePianoMedia = ppm.getDataFine();
        final Date dataFineCampagna = pianoMediaPromo.getDataFine();
        if (dataFinePianoMedia == null || dataFineCampagna == null) {
            return false;
        }
        final long diffMillies = dataFineCampagna.getTime() - dataFinePianoMedia.getTime();
        if (diffMillies < 0) {
            return false;
        }
        return TimeUnit.DAYS.convert(diffMillies, TimeUnit.MILLISECONDS) == 1;
    };

    /**
     * Controlla che la durata di PianificazionePianoMedia (differenza tra DataFine e DataInizio) sia di 1 giorno
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk5 = (ppm) -> {
        final Date dataInizio = ppm.getDataInizio();
        final Date dataFine = ppm.getDataFine();
        if (dataInizio == null || dataFine == null) {
            return false;
        }
        return diffDatesEqualTo(dataFine, dataInizio, 1);
    };

    /**
     * Controlla che la durata di PianificazionePianoMedia (differenza tra DataFine e DataInizio) sia di 4 giorni
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk6 = (ppm) -> {
        final Date dataInizio = ppm.getDataInizio();
        final Date dataFine = ppm.getDataFine();
        if (dataInizio == null || dataFine == null) {
            return false;
        }
        return diffDatesEqualTo(dataFine, dataInizio, 4);
    };

    /**
     * Controlla che la durata di PianificazionePianoMedia (differenza tra DataFine e DataInizio) sia di 5 giorni
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk7 = (ppm) -> {
        final Date dataInizio = ppm.getDataInizio();
        final Date dataFine = ppm.getDataFine();
        if (dataInizio == null || dataFine == null) {
            return false;
        }
        return diffDatesEqualTo(dataFine, dataInizio, 5);
    };
    /**
     * Controlla che la data inizio di PianificazionePianoMedia (differenza tra data inizio e data inizio promo master) sia di 3 giorni
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk8 = (ppm) -> {

        PianoMediaPromoDbpromoEntity pianoMediaPromo =
                pianoMediaPromoDbpromoServiceInstance.get().findByCodicePromo(ppm.getPianoMedia().getPromoMaster());
        if (pianoMediaPromo == null)
            return false;

        final Date dataInizio = ppm.getDataInizio();
        final Date dataFine = pianoMediaPromo.getDataInizio();
        if (dataInizio == null || dataFine == null) {
            return false;
        }
        return diffDatesEqualTo(dataFine, dataInizio, 3);
    };
    /**
     *  il controllo deve ritornare false se c'e' un errore quindi se la data fine della campagna e' minore o uguale della data fine del gantt
     *  e' tutto ok, altrimenti ritorna false
     */
    Function<PianificazionePianoMediaEntity, Boolean> chk9 = (ganttBlock) -> {
        PianoMediaPromoDbpromoEntity campagna =
                pianoMediaPromoDbpromoServiceInstance.get().findByCodicePromo(ganttBlock.getPianoMedia().getPromoMaster());
        final DateTimeUtils utils = new DateTimeUtils();
        boolean result = false;
        try {
            result = utils.isBefore(
                    utils.getDateWithoutTime(ganttBlock.getDataFine()), // data fine gantt
                    utils.getDateWithoutTime(campagna.getDataFine()), // data fine campagna
                    true, // se sono uguali va bene
                    false // non accetta valori null
            );
        } catch (NullPointerException e){

        } catch (Exception e){
            log.error("unexpected error", e);
        } finally {
            return result;
        }
    };
    boolean diffDatesEqualTo(Date date1, Date date2, int days) {
        final long diffMillies = date1.getTime() - date2.getTime();
        if (diffMillies < 0) {
            return false;
        }
        return TimeUnit.DAYS.convert(diffMillies, TimeUnit.MILLISECONDS) == (days -1);
    }

    public Function<PianificazionePianoMediaEntity, Boolean> getControlli(@NonNull SupportoMediaCheckEnum checkEnum) {
        switch (checkEnum) {
            case DATA_INIZIO_COINCIDE_DATA_INIZIO_CAMPAGNA:
                return chk1;
            case DURATA_MINORE_3GG:
                return chk2;
            case DURATA_MAGGIORE_5GG:
                return chk3;
            case CHIUSURA_1GG_PRIMA_DATA_FINE:
                return chk4;
            case DURATA_1GG:
                return chk5;
            case DURATA_4GG:
                return chk6;
            case DURATA_5GG:
                return chk7;
            case DATA_INIZIO_3_GIORNI_PRIMA_DATA_INIZIO_CAMPAGNA:
                return chk8;
            case DATA_FINE_COINCIDE_CON_DATA_FINE_CAMPAGNA:
                return chk9;
            default:
                log.error(String.format("unknown check enum %s",checkEnum.getCodice()));
                return dummy;
        }
    }

    public Function<PianificazionePianoMediaEntity, Boolean> getControlli(@NonNull String checkEnum) {
        try {
            return getControlli(SupportoMediaCheckEnum.fromCodice(checkEnum));
        } catch (NullPointerException ex) {
            log.error(String.format("unknown check enum %s", checkEnum), ex);
            return dummy;
        }
    }


}

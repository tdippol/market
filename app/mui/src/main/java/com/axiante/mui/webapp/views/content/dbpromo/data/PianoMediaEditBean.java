package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PianoMediaEditBean implements Serializable {
    private static final long serialVersionUID = -2860629679963343528L;

    private PianoMediaEntity pianoMedia;

    String descrizione;

    @Getter
    @Setter
    Date dataInizio;

    @Getter
    @Setter
    Date dataFine;

    @Getter
    String promoMaster;

    @Getter
    @Setter
    String promoSecondaryA;

    @Getter
    @Setter
    String promoSecondaryB;

    @Getter
    @Setter
    String promoSecondaryC;

    private PianoMediaPromoDbpromoService pianoMediaPromoService;

    private List<PianoMediaPromoDbpromoEntity> promoList;

    private static final int MASTER = -1;
    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 2;

    public PianoMediaEditBean(PianoMediaPromoDbpromoService pianoMediaPromoService) {
        this.pianoMediaPromoService = pianoMediaPromoService;
    }

    public void setPianoMedia(PianoMediaEntity pianoMedia) {
        this.pianoMedia = pianoMedia;
        setDescrizione(pianoMedia.getDescrizione());
        setDataFine(pianoMedia.getDataFine());
        setDataInizio(pianoMedia.getDataInizio());
        setPromoMaster(pianoMedia.getPromoMaster());
        setPromoSecondaryA(pianoMedia.getPromoSecondaryA());
        setPromoSecondaryB(pianoMedia.getPromoSecondaryB());
        setPromoSecondaryC(pianoMedia.getPromoSecondaryC());
    }

    public void reset() {
        this.pianoMedia = null;
        setDescrizione(null);
        setDataFine(null);
        setDataInizio(null);
        setPromoMaster(null);
        setPromoSecondaryA(null);
        setPromoSecondaryB(null);
        setPromoSecondaryC(null);
    }

    public void setPromoList(List<PianoMediaPromoDbpromoEntity> promoList) {
        this.promoList = promoList;
        // Aggiungo le promo secondarie se non già presenti
        final List<String> codiciPromo = promoList.stream()
                .map(PianoMediaPromoDbpromoEntity::getCodicePromozione)
                .collect(Collectors.toList());
        if (getPromoMaster() != null && !codiciPromo.contains(getPromoMaster())) {
            final PianoMediaPromoDbpromoEntity promoRif = getPromoRifByCodice(getPromoMaster());
            if (promoRif != null) {
                this.promoList.add(promoRif);
            }
        }
        if (getPromoSecondaryA() != null && !codiciPromo.contains(getPromoSecondaryA())) {
            final PianoMediaPromoDbpromoEntity promoRif = getPromoRifByCodice(getPromoSecondaryA());
            if (promoRif != null) {
                this.promoList.add(promoRif);
            }
        }
        if (getPromoSecondaryB() != null && !codiciPromo.contains(getPromoSecondaryB())) {
            final PianoMediaPromoDbpromoEntity promoRif = getPromoRifByCodice(getPromoSecondaryB());
            if (promoRif != null) {
                this.promoList.add(promoRif);
            }
        }
        if (getPromoSecondaryC() != null && !codiciPromo.contains(getPromoSecondaryC())) {
            final PianoMediaPromoDbpromoEntity promoRif = getPromoRifByCodice(getPromoSecondaryC());
            if (promoRif != null) {
                this.promoList.add(promoRif);
            }
        }
    }

    private PianoMediaPromoDbpromoEntity getPromoRifByCodice(@NonNull String codicePromo) {
        return pianoMediaPromoService.findByCodicePromo(codicePromo);
    }

    public PianoMediaEntity getPianoMedia() {
        this.pianoMedia.setDataFine(getDataFine());
        this.pianoMedia.setDataInizio(getDataInizio());
        this.pianoMedia.setDescrizione(getDescrizione());
        this.pianoMedia.setPromoMaster(getPromoMaster());
        this.pianoMedia.setPromoSecondaryA(getPromoSecondaryA());
        this.pianoMedia.setPromoSecondaryB(getPromoSecondaryB());
        this.pianoMedia.setPromoSecondaryC(getPromoSecondaryC());
        return this.pianoMedia;
    }

    public List<PianoMediaPromoDbpromoEntity> getPromoMasterList(){
        return promoList;
    }

    public List<PianoMediaPromoDbpromoEntity> getPromoSecondaryListA() {
        return calculatePromoList(A);
    }
    public List<PianoMediaPromoDbpromoEntity> getPromoSecondaryListB() {
        return calculatePromoList(B);
    }
    public List<PianoMediaPromoDbpromoEntity> getPromoSecondaryListC() {
        return calculatePromoList(C);
    }

    private List<PianoMediaPromoDbpromoEntity> calculatePromoList(int listNumber) {
        switch (listNumber){
            case 0:
                return promoList.stream()
                        .filter(p->!(p.getCodicePromozione().equals(getPromoMaster()) || p.getCodicePromozione().equals(getPromoSecondaryB()) || p.getCodicePromozione().equals(getPromoSecondaryC())))
                        .collect(Collectors.toList());
            case 1:
                return promoList.stream()
                        .filter(p -> !(p.getCodicePromozione().equals(getPromoMaster()) || p.getCodicePromozione().equals(getPromoSecondaryA()) || p.getCodicePromozione().equals(getPromoSecondaryC())))
                        .collect(Collectors.toList());
            case 2:
                return promoList.stream()
                        .filter(p -> !(p.getCodicePromozione().equals(getPromoMaster()) || p.getCodicePromozione().equals(getPromoSecondaryB()) || p.getCodicePromozione().equals(getPromoSecondaryA())))
                        .collect(Collectors.toList());

            default:
                return promoList;
        }
    }


    public  void setPromoMaster(String promoMaster){
        if ( this.promoMaster != null && this.promoMaster.equals(promoMaster)){
            return;
        } else {
            log.debug(String.format("modifica promo master a %s reset delle promo secondarie", promoMaster));

            if ( this.promoSecondaryA != null && this.promoSecondaryA.equals(promoMaster)) {
                this.promoSecondaryA = null;
            }
            if ( this.promoSecondaryB != null && this.promoSecondaryB.equals(promoMaster)) {
                this.promoSecondaryB = null;
            }
            if ( this.promoSecondaryC != null && this.promoSecondaryC.equals(promoMaster)) {
                this.promoSecondaryC = null;
            }
            this.promoMaster = promoMaster;
        }
    }


    public void setDescrizione(String descrizione){
        if ( descrizione != null ){
            this.descrizione = descrizione.toUpperCase();
        } else {
            this.descrizione = null;
        }
    }

    public String getDescrizione(){
        if ( this.descrizione != null )
            return this.descrizione.toUpperCase();
        return null;
    }
}

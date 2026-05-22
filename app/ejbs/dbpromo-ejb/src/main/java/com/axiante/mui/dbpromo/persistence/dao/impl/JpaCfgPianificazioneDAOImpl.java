package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@DbPromoJpaDao
@Slf4j
public class JpaCfgPianificazioneDAOImpl extends JpaDbPromoDAO<CfgPianificazioneEntity>
		implements CfgPianificazioneDAO {
	private static final long serialVersionUID = 5151260322102408183L;

	@Override
	public List<CfgPianificazioneEntity> findAllByMuiCfgSetPianificazione(final PromozioneTestataEntity testata) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.findAllByMuiCfgSetPianificazione", CfgPianificazioneEntity.class)
				.setParameter("idSet", testata.getMuiCfgSetPianificazione().getId())
				.getResultList();
	}

	@Override
	public List<String> findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(Long promoId, Long meccanicaId) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento", String.class)
				.setParameter("promoId", promoId)
				.setParameter("meccanicaId", meccanicaId)
				.getResultList();
	}

	@Override
	public List<CfgPianificazioneEntity> findAllByPromoAndMeccanicaId(PromozioneTestataEntity promo, Long meccanicaId) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.findAllByPromoAndMeccanicaId", CfgPianificazioneEntity.class)
				.setParameter("promo", promo)
				.setParameter("meccanicaId", meccanicaId)
				.getResultList();
	}

	@Override
	public List<CfgPianificazioneEntity> findAllDistinctByCanaleAndMeccanica(CanalePromozioneEntity canale,
																			 MeccanicheEntity meccanica) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.findAllDistinctByCanaleAndMeccanica", CfgPianificazioneEntity.class)
				.setParameter("canale", canale)
				.setParameter("meccanica", meccanica)
				.getResultList();
	}

	@Override
	public List<CfgPianificazioneEntity> findAllByCanaleAndMeccanicaAndField(CanalePromozioneEntity canale,
																			 MeccanicheEntity meccanica,
																			 String field) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.findAllByCanaleAndMeccanicaAndField", CfgPianificazioneEntity.class)
				.setParameter("canale", canale)
				.setParameter("meccanica", meccanica)
				.setParameter("campo", field)
				.getResultList();
	}

	@Override
	public CfgPianificazioneEntity findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo(
			CanalePromozioneEntity canale, MeccanicheEntity meccanica,
			CfgPianificazTipoRigaEntity tipoRiga, String field, PromozioneTestataEntity testata) {
		try {
			return getEm().createNamedQuery("CfgPianificazioneEntity.findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo", CfgPianificazioneEntity.class)
					.setParameter("canale", canale).setParameter("meccanica", meccanica)
					.setParameter("tipoRiga", tipoRiga).setParameter("campo", field)
					.setParameter("idPromozione", testata.getId())
					.getSingleResult();
		} catch (NoResultException nre) {
			log.warn(String.format("nessuna configurazione per meccanica = %s canale = %s tipo riga = %s campo = %s",
					meccanica.getDescrizione(), canale.getDescrizione(),
					tipoRiga.getDescrizione(), field), nre);
		} catch (NonUniqueResultException nue) {
			log.error(String.format("campo %s configurato piu volte per meccanica = %s canale = %s tipo riga = %s ",
					field, meccanica.getDescrizione(), canale.getDescrizione(),
					tipoRiga.getDescrizione()), nue);
		} catch (Exception e) {
			log.error(String.format(
					"impossibile recuperare la configurazione per il campo %s con meccanica = %s canale = %s tipo riga = %s ",
					field, meccanica.getDescrizione(), canale.getDescrizione(),
					tipoRiga.getDescrizione()), e);
		}
		return null;
	}

	@Override
	public List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampo(
			CfgSetPianificazioneEntity setPianificazioneEntity, MeccanicheEntity meccanicaEntity, String campo) {
		return getEm()
				.createNamedQuery("CfgPianificazioneEntity.findBySetAndMeccanicaAndCampo",
						CfgPianificazioneEntity.class)
				.setParameter("setPianificazione", setPianificazioneEntity).setParameter("meccanica", meccanicaEntity)
				.setParameter("campo", campo).getResultList();
	}

    @Override
    public List<CfgPianificazioneEntity> findBySetAndMeccanicaAndCampoAndTipoRiga(
    		@NonNull CfgSetPianificazioneEntity setPianificazione, @NonNull MeccanicheEntity meccanica,
			@NonNull String field, @NonNull PianificazioneRowTypeEnum rowType) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.findBySetAndMeccanicaAndCampoAndTipoRiga",
					CfgPianificazioneEntity.class)
				.setParameter("setPianificazione", setPianificazione)
				.setParameter("meccanica", meccanica)
				.setParameter("campo", field)
				.setParameter("tipoRiga", rowType.getTypeCode())
				.getResultList();
    }

    @Override
	public Map<String, String> getDefaultValues(Long testataId, Long meccanicaId, String codeTipoRiga) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.getDefaultValues", CfgPianificazioneEntity.class)
				.setParameter("testataId", testataId)
				.setParameter("meccanicaId", meccanicaId)
				.setParameter("codeTipoRiga", codeTipoRiga)
				.getResultList()
				.stream()
				.filter(p -> p.getMuiCfgPianificazioneCampi() != null)
				.filter(p -> !p.getDefValue().trim().isEmpty())
				.collect(Collectors.toMap(e -> e.getMuiCfgPianificazioneCampi().getCampo(), CfgPianificazioneEntity::getDefValue));
	}

	@Override
	public List<String> findTipiRigaByHeaderAndCampo(@NonNull Long headerId, @NonNull String field) {
		return getEm().createNamedQuery("CfgPianificazioneEntity.findTipiRigaByHeaderAndCampo", String.class)
				.setParameter("campo", field).setParameter("idHeader", headerId).getResultList();
	}
}

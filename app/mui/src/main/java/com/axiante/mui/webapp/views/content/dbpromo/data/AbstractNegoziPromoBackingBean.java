package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.promo.ShopUpdateTypeEnum;
import com.axiante.mui.dbpromo.persistence.dto.ZonaDto;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.views.content.dbpromo.NegoziPromoBackinInterface;
import com.axiante.mui.webapp.webservice.util.PromoShopsUtil;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

@Slf4j
public abstract class AbstractNegoziPromoBackingBean implements NegoziPromoBackinInterface {
	PromoShopsUtil promoShopsUtil;

	@Getter
	@Setter
	private MenuModel menuModelB;

	@Setter
	@Getter
	private MenuModel menuModelF;

	@Getter
	@Setter
	private MenuModel menuModelCB;
	
	@Getter
	@Setter
	private MenuModel menuModelCF;

	@Getter
	@Setter
	private MenuModel menuModelZonaYes;

	@Getter
	@Setter
	private MenuModel menuModelCediYes;

	@Getter
	@Setter
	private MenuModel menuModelCediNo;

	@Getter
	@Setter
	private MenuModel menuModelZonaNo;

	@Getter
	@Setter
	private String visualizzaStatus;

	@Getter
	@Setter
	private boolean renderTabUploadShops;

	@Getter
	public String dlgConfirmMessage;

	@Getter
	public String dlgConfirmMessageAll;

	@Getter
	public Boolean flagForUpdate;

	@Getter
	public Boolean flagForUpdateAll;

	@Getter
	public ShopUpdateTypeEnum typeForUpdate;

	@Getter
	public Long idTipoNegozioForUpdate;

	@Getter
	public String tipoConsegnaForUpdate;

	@Getter
	public String zonaSocietaForUpdate;

	@Getter
	public String zonaCodiceForUpdate;

	public String codiceCediForUpdate;
	@Getter(value = AccessLevel.PROTECTED)
	private String user;

	@Getter
	private UserDTO userDTO;

	public AbstractNegoziPromoBackingBean(@NonNull PromoShopsUtil promoShopsUtil, @NonNull final String user,
										  @NonNull final Boolean renderTabUploadShops, @NonNull final UserDTO userDTO) {
		this.promoShopsUtil = promoShopsUtil;
		this.user = user;
		this.renderTabUploadShops = renderTabUploadShops;
		this.userDTO = userDTO;
	}

	@Override
	public void resetDefault() {
		executeScript("PF('shopPromoConfirmDialog').hide();");
		try {
			final UserDTO userDTO = getUserDTO();
			promoShopsUtil.resetDefaultRowData(getIdPromo(), getUser(), userDTO.getCanali(), userDTO.getGruppi(), isUserAdmin());
			addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", "Valori di default ripristinati"));
		} catch (Exception e) {
			log.error("Error map default PromozioneTestataEntity into JSON object", e);
			addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Errore ripristino valori di default"));
		}
		executeScript("refreshGridFilterByRadioCheck(" + getIdPromo() + ");");
	}

	public void prepareDialog(ActionEvent event) {
		try {
			final MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
			dlgConfirmMessage = menuItem.getParams().get("msg").get(0);
			final String type = menuItem.getParams().get("type").get(0);
			typeForUpdate = ShopUpdateTypeEnum.valueOf(type);
			switch (typeForUpdate) {
				case NEGOZIO:
					idTipoNegozioForUpdate = Long.parseLong(menuItem.getParams().get("idTipoNegozio").get(0));
					tipoConsegnaForUpdate = null;
					zonaSocietaForUpdate = null;
					zonaCodiceForUpdate = null;

					break;
				case CONSEGNA:
					idTipoNegozioForUpdate = null;
					tipoConsegnaForUpdate = menuItem.getParams().get("tipoConsegna").get(0);
					zonaSocietaForUpdate = null;
					zonaCodiceForUpdate = null;
					break;
				case ZONA:
					idTipoNegozioForUpdate = null;
					tipoConsegnaForUpdate = null;
					zonaSocietaForUpdate = menuItem.getParams().get("zonaSocieta").get(0);
					zonaCodiceForUpdate = menuItem.getParams().get("zonaCodice").get(0);
					break;
				case CEDI:
					idTipoNegozioForUpdate = null;
					codiceCediForUpdate = menuItem.getParams().get("codiceCedi").get(0);
					zonaSocietaForUpdate = null;
					zonaCodiceForUpdate = null;
					break;
				default:
					log.warn(String.format("UpdateType %s not managed", type));
			}
			flagForUpdate = Boolean.valueOf(menuItem.getParams().get("flag").get(0));
			executeScript("PF('dlgBulkUpdate').show()");
		} catch (Exception ex) {
			log.warn("Error getting parameters from MenuActionEvent", ex);
		}
	}

	public void prepareDialogAll(boolean flag) {
		dlgConfirmMessageAll = String.format("Sei sicuro di voler impostare a %s tutti i negozi?", flag ? "SI" : "NO");
		flagForUpdateAll = flag;
		executeScript("PF('dlgBulkUpdateAll').show()");
	}

	public void callBulkUpdateWs() {
		// Build JS script based on typeForUpdate
		final StringBuilder sb = new StringBuilder("updateShops(");
		sb.append("'").append(typeForUpdate != null ? typeForUpdate.toString() : "null").append("', ")
				.append(flagForUpdate != null ? flagForUpdate.toString() : "null").append(", ");
		if ( typeForUpdate != null ){
			switch (typeForUpdate) {
				case NEGOZIO:
					sb.append(idTipoNegozioForUpdate);
					break;
				case CONSEGNA:
					sb.append("'").append(tipoConsegnaForUpdate).append("'");
					break;
				case ZONA:
					sb.append("'").append(zonaSocietaForUpdate).append("', '").append(zonaCodiceForUpdate).append("'");
					break;
				case CEDI:
					sb.append("'").append(codiceCediForUpdate).append("'");
					break;

				default:
					sb.append("'null'");
			}
		} else {
			sb.append("'null'");
		}
			sb.append(")");
		executeScript(sb.toString());
	}

	public void callBulkUpdateAllWs() {
		executeScript(String.format("updateShops('ALL', %s, null)",
				flagForUpdateAll != null ? flagForUpdateAll.toString() : "null"));
	}

	public void callResetAllWs() {
		executeScript("updateShops('RESET', null, null)");
	}

	protected List<MenuItem> createModel() {
		List<TipoNegozioEntity> list = promoShopsUtil.getShopTypes();

		final String msgNoFormat = "Sei sicuro di voler impostare a NO tutti i Tipo Negozio '%s'?";
		List<MenuItem> itemList = list.stream().map(tipoNegozioEntity -> {
			final String desc = tipoNegozioEntity.getDescrizione() != null
					? tipoNegozioEntity.getDescrizione()
					: tipoNegozioEntity.getCodiceTipoNegozio();
			final DefaultMenuItem item = new DefaultMenuItem(desc);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton splitF");
			item.setParam("source", "F");
			item.setParam("tipoNegozio", tipoNegozioEntity.getId());
			item.setParam("itemDescription", desc);
			item.setId("menuF_" + tipoNegozioEntity.getId());
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgNoFormat, desc));
			item.setParam("flag", false);
			item.setParam("type", ShopUpdateTypeEnum.NEGOZIO);
			item.setParam("idTipoNegozio", tipoNegozioEntity.getId());
			return item;
		}).collect(Collectors.toList());

		final String msgYesFormat = "Sei sicuro di voler impostare a SI tutti i Tipo Negozio '%s'?";
		itemList.addAll(list.stream().map(tipoNegozioEntity -> {
			final String desc = tipoNegozioEntity.getDescrizione() != null
					? tipoNegozioEntity.getDescrizione()
					: tipoNegozioEntity.getCodiceTipoNegozio();
			final DefaultMenuItem item = new DefaultMenuItem(desc);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton splitB");
			item.setParam("source", "B");
			item.setParam("tipoNegozio", tipoNegozioEntity.getId());
			item.setParam("itemDescription", desc);
			item.setId("menuB_" + tipoNegozioEntity.getId());
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgYesFormat, desc));
			item.setParam("flag", true);
			item.setParam("type", ShopUpdateTypeEnum.NEGOZIO);
			item.setParam("idTipoNegozio", tipoNegozioEntity.getId());
			return item;
		}).collect(Collectors.toList()));

		return itemList;
	}

	protected List<MenuItem> createModelConsegna() {
		List<String> listaTipiConsegna = promoShopsUtil.getTipiConsegna();

		final String msgNoFormat = "Sei sicuro di voler impostare a NO tutti i Canale Consegna '%s'?";
		List<MenuItem> itemList = listaTipiConsegna.stream().filter(Objects::nonNull).map(tipoConsegna -> {
			final DefaultMenuItem item = new DefaultMenuItem(tipoConsegna);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton splitCF");
			item.setParam("source", "CF");
			item.setParam("tipoCanale", tipoConsegna);
			item.setParam("itemDescription", tipoConsegna);
			item.setId("menuCF_" + tipoConsegna);
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgNoFormat, tipoConsegna));
			item.setParam("flag", false);
			item.setParam("type", ShopUpdateTypeEnum.CONSEGNA);
			item.setParam("tipoConsegna", tipoConsegna);
			return item;
		}).collect(Collectors.toList());

		final String msgYesFormat = "Sei sicuro di voler impostare a SI tutti i Canale Consegna '%s'?";
		itemList.addAll(listaTipiConsegna.stream().filter(Objects::nonNull).map(tipoConsegna -> {
			final DefaultMenuItem item = new DefaultMenuItem(tipoConsegna);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton splitCB");
			item.setParam("source", "CB");
			item.setParam("tipoCanale", tipoConsegna);
			item.setParam("itemDescription", tipoConsegna);
			item.setId("menuCB_" + tipoConsegna);
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgYesFormat, tipoConsegna));
			item.setParam("flag", true);
			item.setParam("type", ShopUpdateTypeEnum.CONSEGNA);
			item.setParam("tipoConsegna", tipoConsegna);
			return item;
		}).collect(Collectors.toList()));

		return itemList;
	}

	protected List<MenuItem> createModelZona() {
		List<ZonaDto> list = promoShopsUtil.getDistinctZone();

		final String msgNoFormat = "Sei sicuro di voler impostare a NO tutte le Zone '%s'?";
		final List<MenuItem> itemsList = list.stream().map(z -> {
			final String desc = String.format("[%s_%s] %s", z.getSocieta(), z.getCodiceZona(), z.getDescrizioneZona());
			final DefaultMenuItem item = new DefaultMenuItem(desc);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton");
			item.setParam("source", "zonaNo");
			item.setId(String.format("menuZonaNo_%s_%s", z.getSocieta(), z.getCodiceZona()));
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgNoFormat, desc));
			item.setParam("flag", false);
			item.setParam("type", ShopUpdateTypeEnum.ZONA);
			item.setParam("zonaSocieta", z.getSocieta());
			item.setParam("zonaCodice", z.getCodiceZona());
			return item;
		}).collect(Collectors.toList());

		final String msgYesFormat = "Sei sicuro di voler impostare a SI tutte le Zone '%s'?";
		itemsList.addAll(list.stream().map(z -> {
			final String desc = String.format("[%s_%s] %s", z.getSocieta(), z.getCodiceZona(), z.getDescrizioneZona());
			final DefaultMenuItem item = new DefaultMenuItem(desc);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton");
			item.setParam("source", "zonaYes");
			item.setId(String.format("menuZonaYes_%s_%s", z.getSocieta(), z.getCodiceZona()));
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgYesFormat, desc));
			item.setParam("flag", true);
			item.setParam("type", ShopUpdateTypeEnum.ZONA);
			item.setParam("zonaSocieta", z.getSocieta());
			item.setParam("zonaCodice", z.getCodiceZona());
			return item;
		}).collect(Collectors.toList()));

		return itemsList;
	}


	protected List<MenuItem> createModelCedi() {
		List<String> listaTipiConsegna = promoShopsUtil.getDistinctCedi();

		final String msgNoFormat = "Sei sicuro di voler impostare a NO tutti i Cedi '%s'?";
		List<MenuItem> itemList = listaTipiConsegna.stream().filter(Objects::nonNull).map(cedi -> {
			final DefaultMenuItem item = new DefaultMenuItem(cedi);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton splitCF");
			item.setParam("source", "cediNo");
			item.setParam("codiceCedi", cedi);
			item.setId("menuCN_" + cedi);
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgNoFormat, cedi));
			item.setParam("flag", false);
			item.setParam("type", ShopUpdateTypeEnum.CEDI);
			return item;
		}).collect(Collectors.toList());

		final String msgYesFormat = "Sei sicuro di voler impostare a SI tutti i Cedi '%s'?";
		itemList.addAll(listaTipiConsegna.stream().filter(Objects::nonNull).map(cedi -> {
			final DefaultMenuItem item = new DefaultMenuItem(cedi);
			item.setUpdate("@(.dlgBulkUpdate)");
			item.setStyleClass("ItemStyle refreshSplitButton splitCB");
			item.setParam("source", "cediSi");
			item.setId("menuCS_" + cedi);
			item.setCommand("#{templateView.currentView.negoziPromoBean.prepareDialog}");
			item.setParam("msg", String.format(msgYesFormat, cedi));
			item.setParam("flag", true);
			item.setParam("type", ShopUpdateTypeEnum.CEDI);
			item.setParam("codiceCedi", cedi);
			return item;
		}).collect(Collectors.toList()));

		return itemList;
	}
}

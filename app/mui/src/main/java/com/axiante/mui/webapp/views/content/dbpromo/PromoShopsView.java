package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.NegoziPromoBackingBean;
import com.axiante.mui.webapp.views.content.enumeration.TabEnum;
import com.axiante.mui.webapp.webservice.util.PromoShopsUtil;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@MuiViewModel("promoShops")
@Named
@Dependent
@Slf4j
public class PromoShopsView extends BasePromoView {

	private static final long serialVersionUID = -4985183703092027769L;
	@Getter
	NegoziPromoBackinInterface negoziPromoBean;

	@Inject
	private PromoShopsUtil promoShopsUtil;

	@Getter
	PromozioneTestataEntity testata = null;

	@Getter
	@Setter
	private boolean tabEditRendered = true;

	@Getter
	private boolean textBoxWarningNegozi = false;

	@Getter
	private boolean uploadNegoziRendered = false;

	@Getter
	private boolean negoziCediRendered = false;

	@Getter
	private boolean negoziZonaRendered = false;

	@PostConstruct
	public void init() {
		log.debug("constructing promoshopview");
		final UsersEntity user = ((UsersEntity) this.getSessionMap().get(UsersEntity.USER_ATTRIBUTE));
		negoziPromoBean = new NegoziPromoBackingBean(promoShopsUtil, user.getName(), getUserDto(), false);
	}

	@Override
	public void updateView() {
		executeScript("refreshGridFilterByRadioCheck();");
	}

	@Override
	public void applyRules() {
		// noop
	}

	@Override
	public void applyDefaultRules() {
		// noop
	}

	public Object getIdPromozioneCorrente() {
		return "null";
	}

	public int indexOf(TabEnum tab) {
		return 0;
	}
}

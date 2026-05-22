package com.axiante.mui.webapp.views.content.admin.menu;

import com.axiante.mui.persistence.entity.MenuEntity;
import java.nio.CharBuffer;
import java.util.LinkedHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.TreeNode;

@Slf4j
public class AddMenuBean {
	@Getter
	private MenuEntity menu;

	private boolean leafNode;
	private boolean dynamic = true; // per default creo pagine tm1
	private boolean external;
	private String label;
	private Integer order;
	private Integer idParent;
	private String bean;
	private String url;

	private boolean dynamicDisabled;
	private boolean externalDisabled;
	private boolean beanDisabled;
	private boolean pathVisible;
	private boolean layoutDisabled;

	@Getter
	@Setter
	private LinkedHashMap<DropDownKey, Integer> dropdownMenu = new LinkedHashMap<>();

	public static final String ROOT_NODE_KEY = "Root";
	public static final Integer ROOT_NODE_VALUE = -1;
	public static final String DEFAULT_BEAN_NAME = "agGrid";
	public static final String EXTERNAL_BEAN_NAME = "external_page";

	public AddMenuBean(@NonNull final MenuEntity entity) {
		log.debug("instantiating backing bean for edit purposes");
		menu = entity;
		setup();
	}

	private void setup() {
		this.external = menu.getExternalLink();
		this.bean = menu.getBean();
		this.label = menu.getLabel();
		this.leafNode = menu.getBean() != null;
		this.url = menu.getUrl();
		this.dynamic = menu.getTemplate();
		this.order = menu.getOrderId();
		if (external) {
			bean = null;
			dynamic = false;
		} else {
			if(menu.getIdMenu()!=null && menu.getBean()==null && getLabel()!=null) {
				this.bean=getLabel();
			}
			else if (menu.getIdMenu()==null && bean == null) {
				throw new RuntimeException("Corrupted entry id " + menu.getIdMenu());
			}
		}
	}

	public AddMenuBean(final TreeNode mainMenu) {
		log.debug("instantiating backing bean");
		populateMenuItemsMap(dropdownMenu, 1, mainMenu);
		menu = new MenuEntity();
		menu.setParent(new MenuEntity());
		menu.getParent().setIdMenu(ROOT_NODE_VALUE);
		menu.setBean(DEFAULT_BEAN_NAME);
		setup();
	}

//	private void populateMenuItemsMap(final LinkedHashMap<String, Integer> map, final String level,
//			final TreeNode node) {
//		if (map.size() == 0) {
//			map.put(ROOT_NODE_KEY, ROOT_NODE_VALUE);
//		}
//		if (node != null) {
//			for (final TreeNode child : node.getChildren()) {
//				final MenuEntity menu = (MenuEntity) child.getData();
//				map.put(level + " " + menu.getLabel(), menu.getIdMenu());
//				populateMenuItemsMap(map, level + "-", child);
//			}
//		}
//	}
	private void populateMenuItemsMap(final LinkedHashMap<DropDownKey, Integer> map, final int level,
									  final TreeNode node) {
		if (map.size() == 0) {
			map.put(new DropDownKey(0, ROOT_NODE_KEY, 0), ROOT_NODE_VALUE);
		}
		if (node != null) {
			for (final TreeNode child : node.getChildren()) {
				final MenuEntity menu = (MenuEntity) child.getData();
				map.put(new DropDownKey(level , menu.getLabel(), map.size()), menu.getIdMenu());
				populateMenuItemsMap(map, level + 1 , child);
			}
		}
	}

	public boolean isExternal() {
		log.debug("external value is " + external);
		return external;
	}

	public void setExternal(boolean external) {
		log.debug("change external from " + this.external + "to " + external);
		this.external = external;
	}

	public boolean isLeafNode() {
		log.debug("leafnode value is " + leafNode);
		return leafNode;
	}

	public void setLeafNode(boolean leafNode) {
		log.debug("change leafNode from " + this.leafNode + "to " + leafNode);
		this.leafNode = leafNode;
	}

	public boolean isDynamic() {
		log.debug("dynamic value is " + external);
		return dynamic;
	}

	public void setDynamic(boolean dynamic) {
		log.debug("change dynamic from " + this.dynamic + "to " + dynamic);
		this.dynamic = dynamic;
	}

	public String getLabel() {
		log.debug("label value is " + external);
		return label;
	}

	public void setLabel(String label) {
		log.debug("change label from " + this.label + "to " + label);
		this.label = label;
	}

	public Integer getOrder() {
		log.debug("order value is " + external);
		return order;
	}

	public void setOrder(Integer order) {
		log.debug("change order from " + this.order + "to " + order);
		this.order = order;
	}

	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}

	public String getBean() {
		log.debug("bean value is " + external);
		return bean;
	}

	public void setBean(String bean) {
		log.debug("change bean from " + this.bean + "to " + bean);
		this.bean = bean;
	}

	public Integer getIdParent() {
		log.debug("idParent value is " + external);
		return idParent;
	}

	public void setIdParent(Integer idParent) {
		log.debug("change idParent from " + this.idParent + "to " + idParent);
		this.idParent = idParent;
	}

	public String getUrl() {
		log.debug("url value is " + external);
		return url;
	}

	public void setUrl(String url) {
		log.debug("change url from " + this.url + "to " + url);
		this.url = url;
	}

	public MenuEntity getUpdatedMenu() {
		this.menu.setLabel(getLabel());
		this.menu.setOrderId(getOrder());
		this.menu.setTemplate(isDynamic());
		if (isLeafNode()) {
			this.menu.setExternalLink(isExternal());
			this.menu.setBean(getBean());
			this.menu.setUrl(url);
			if (isDynamic()) {
				this.menu.setBean(DEFAULT_BEAN_NAME);
			} else {
				if (isExternal()) {
					this.menu.setBean(EXTERNAL_BEAN_NAME);
				} else {
					this.menu.setBean(getBean());
				}
			}
		} else {
			this.menu.setBean(null);
			this.menu.setExternalLink(false);
			this.menu.setUrl(null);
		}

		return getMenu();
	}

	void checkDynamicDisabled() {
		setDynamicDisabled(!isLeafNode());
	}

	void checkExternalDisabled() {
		setExternalDisabled(!isLeafNode() || (isLeafNode() && isDynamic()));
	}

	void checkBeanDisabled() {
		setBeanDisabled(isExternalDisabled() || (!isExternalDisabled() && isExternal()));
	}

	void checkPathVisible() {
		setPathVisible(isLeafNode() && !isDynamic() && !isExternal());
	}

	void checkLayoutDisabled() {
		setLayoutDisabled(isDynamicDisabled() || !isDynamic());
	}

	public boolean isDynamicDisabled() {
		checkDynamicDisabled();
		return dynamicDisabled;
	}

	public void setDynamicDisabled(boolean dynamicDisabled) {
		this.dynamicDisabled = dynamicDisabled;
	}

	public boolean isExternalDisabled() {
		checkExternalDisabled();
		return externalDisabled;
	}

	public void setExternalDisabled(boolean externalDisabled) {
		this.externalDisabled = externalDisabled;
	}

	public boolean isBeanDisabled() {
		checkBeanDisabled();
		return beanDisabled;
	}

	public void setBeanDisabled(boolean beanDisabled) {
		this.beanDisabled = beanDisabled;
	}

	public boolean isPathVisible() {
		checkPathVisible();
		return pathVisible;
	}

	public void setPathVisible(boolean pathVisible) {
		this.pathVisible = pathVisible;
	}

	public boolean isLayoutDisabled() {
		checkLayoutDisabled();
		return layoutDisabled;
	}

	public void setLayoutDisabled(boolean layoutDisabled) {
		this.layoutDisabled = layoutDisabled;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	public class DropDownKey{
		int level = 1;
		String label;
		// questo mi serve per differenziare. Banalmente, mi basta mettere
		// map.size qui e ho tutti gli elementi diversi
		int id = 0;
		public String toString(){
			StringBuffer buf = new StringBuffer(spaces(level));
			if ( label != null ){
				buf.append(label);
			}
			return buf.toString();
		}

		private String spaces( int spaces ) {
			return CharBuffer.allocate( spaces ).toString().replace( '\0', '-' );
		}
	}
}

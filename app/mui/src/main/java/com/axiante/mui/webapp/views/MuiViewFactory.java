package com.axiante.mui.webapp.views;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

@Dependent
@Slf4j
public class MuiViewFactory implements Serializable {

	private static final long serialVersionUID = 2104552619053204487L;


	@Inject
	@Any
	transient Instance<MuiViewInterface> views;

	@PostConstruct
	public void post() {
		log.debug("MuiViewFactory initialized");
	}

	public MuiViewInterface getView(@NonNull final MenuItem node) {
		try{
			String selectedBean = node.getParams().get("bean").get(0);
			DinamicMuiViewLiteral literal = new DinamicMuiViewLiteral(selectedBean);
			Instance<MuiViewInterface> muiInstance = views.select(literal);
			MuiViewInterface mui = muiInstance.get();
			mui.setNode(node);
			return  mui;
		}catch (NullPointerException e) {
			log.error("the node " + node.getValue() + " is not properly configured as it is missing the bean declaration", e);
		} catch (NoClassDefFoundError e){
			log.error("the node {} points to a class that cannot be loaded ", node.getValue(), e);
		}
		return null;
	}


}

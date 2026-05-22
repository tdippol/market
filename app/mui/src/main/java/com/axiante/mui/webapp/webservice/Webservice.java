package com.axiante.mui.webapp.webservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("resources")
public class Webservice extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> webservices = new HashSet<>();
		webservices.add(GridServiceResource.class);
		webservices.add(GridPianificazioneServiceResource.class);
		webservices.add(ConfigurationResource.class);
		webservices.add(BarcodeServiceResource.class);
		webservices.add(FatturazioneServiceResource.class);
		webservices.add(IniziativeServiceResource.class);
		webservices.add(CumulabilitaServiceResource.class);
		webservices.add(ReportVendutoServiceResource.class);
		webservices.add(PianoMediaResource.class);
		webservices.add(PianificazionePianoMediaResource.class);
		webservices.add(PianoMediaPianificazioniResource.class);
		webservices.add(PianificazioneMessaggiServiceResource.class);
		webservices.add(CheckSovrapposizioniResource.class);
		webservices.add(TotalizzatoriPromoResource.class);
		webservices.add(ReportArticoloInPromoResource.class);
		webservices.add(ReportBuonoInPromoResource.class);
		webservices.add(PrefissoBsServiceResource.class);
		webservices.add(CustomerJourneyResource.class);
        webservices.add(InclusioneEsclusioneServiceResource.class);
		webservices.add(ObiettiviResource.class);
		webservices.add(SottoscrizioniResource.class);
        webservices.add(MacrospaziMediaServiceResource.class);
        webservices.add(EventiMediaServiceResource.class);
		webservices.add(GridServiceSPResource.class);
		return webservices;
	}
}

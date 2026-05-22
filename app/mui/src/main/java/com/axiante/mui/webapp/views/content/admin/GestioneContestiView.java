package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.MuiContext;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.admin.pojos.CanaleWrapper;
import com.axiante.mui.webapp.views.content.admin.pojos.ContestoCanali;
import com.axiante.tm1.mdx.objects.Query;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MuiViewModel("gestioneContesti")
@Named("gestioneContesti")
@SessionScoped
public class GestioneContestiView extends AbstractAdminView {
    private static final long serialVersionUID = -4598151190589347534L;
    private static final int MAX_CODE_LENGTH = 15;
    private static final int MAX_DESC_LENGTH = 255;

    @Inject
    @Getter
    transient private ApplicationFilterCatalogProducer catalogProducer;

    @Inject
    @Getter
    transient private CatalogReducer catalogReducer;

    @Inject
    transient private Instance<MuiService> muiServiceInstance;

    @Getter
    private ContestoCanali selectedContext;

    @Getter
    private Long idSelectedContext;

    @Getter
    private List<ContestoCanali> contexts;

    @Getter
    private List<CanaleEntity> channels;

    @Getter
    @Setter
    private String contextCode;

    @Getter
    @Setter
    private String contextDesc;

    @PostConstruct
    public void init() {
        loadContexts();
        if (contexts != null && !contexts.isEmpty()) {
            setIdSelectedContext(contexts.get(0).getContext().getId());
        }
    }

    public void setIdSelectedContext(Long idSelectedContext) {
        this.idSelectedContext = idSelectedContext;
        if (idSelectedContext != null) {
            selectedContext = contexts.stream()
                    .filter(c -> idSelectedContext.equals(c.getContext().getId()))
                    .findFirst().orElse(null);
        }
    }

    public void toggleChannelAvailable(CanaleWrapper channel) {
        if (selectedContext != null) {
            final MuiContext context = selectedContext.getContext();
            try {
                if (channel.isAvailable()) {
                    // Aggiunta canale a contesto
                    context.addCanale(channel.getCh());
                } else {
                    // Rimozione canale da contesto
                    context.removeCanale(channel.getCh());
                }
                muiServiceInstance.get().saveContext(context);
                final String msg = String.format("Canale '%s - %s' %s contesto '%s'",
                        channel.getCh().getCodiceCanale(), channel.getCh().getDescrizione(),
                        channel.isAvailable() ? "aggiunto a" : "rimosso da", context.getCode());
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", msg));
            } catch (Exception ex) {
                final String msg = String.format("Errore %s canale '%s - %s' %s contesto '%s'",
                        channel.isAvailable() ? "aggiunta" : "rimozione",
                        channel.getCh().getCodiceCanale(), channel.getCh().getDescrizione(),
                        channel.isAvailable() ? "a" : "da", context.getCode());
                log.error(msg, ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", msg));
            }
        }
        loadContexts();
    }

    public void openAddContextDialog() {
        contextCode = null;
        contextDesc = null;
    }

    public void addContext() {
        try {
            if (validate()) {
                MuiContext context = new MuiContext();
                context.setCode(contextCode.trim());
                context.setDescription(contextDesc.trim());
                context = muiServiceInstance.get().saveContext(context);
                loadContexts();
                setIdSelectedContext(context.getId());
                executeScript("PF('dlgAddContext').hide()");
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                        String.format("Aggiunto contesto con codice '%s'", contextCode)));
            }
        } catch (Exception ex) {
            log.error("Error adding context", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore!", ex.getMessage()));
        }
    }

    private boolean validate() throws Exception {
        if (contextCode == null || contextCode.trim().isEmpty()) {
            throw new Exception("Campo 'Codice' obbligatorio");
        }
        if (contextCode.trim().length() > MAX_CODE_LENGTH) {
            throw new Exception(String.format("Campo 'Codice' supera i %d caratteri", MAX_CODE_LENGTH));
        }
        if (contextDesc == null || contextDesc.trim().isEmpty()) {
            throw new Exception("Campo 'Descrizione' obbligatorio");
        }
        if (contextDesc.trim().length() > MAX_DESC_LENGTH) {
            throw new Exception(String.format("Campo 'Descrizione' supera i %d caratteri", MAX_DESC_LENGTH));
        }
        return true;
    }

    private void loadContexts() {
        if (contexts == null) {
            contexts = new ArrayList<>();
        }
        contexts.clear();
        try {
            channels = muiServiceInstance.get().readCanali();
            muiServiceInstance.get().findAllContexts().forEach(c -> {
                final ContestoCanali cc = new ContestoCanali(c);
                channels.forEach(ch -> cc.addCanale(ch, c.getCanali().contains(ch)));
                contexts.add(cc);
            });
        } catch (Exception ex) {
            log.error("Error getting contexts", ex);
        }
    }

    public int getMaxCodeLength() {
        return MAX_CODE_LENGTH;
    }

    public int getMaxDescLength() {
        return MAX_DESC_LENGTH;
    }

    @Override
    public void updateView() {
        // Do nothing
    }

    @Override
    public void updateView(String grid) {
        updateView();
    }

    @Override
    public Query prepareFilteredQuery(String grid) {
        return null;
    }
}

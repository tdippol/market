package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.business.utils.pojo.PromoShopUpdateDto;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.webservice.util.PromoShopsUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;

@Slf4j
public class NegoziPromoBackingBean extends AbstractNegoziPromoBackingBean {

    static final String TUTTO = "ALL";
    byte[] fileContent;

    @Getter
    @Setter
    Long idPromo;

    @Getter
    @Setter
    String selectedRows;

    @Getter
    String sourcePromoSelected = null;

    public NegoziPromoBackingBean(PromoShopsUtil promoShopsUtil, @NonNull final String user,
                                  @NonNull final UserDTO userDTO, boolean renderTabUploadShops) {
        super(promoShopsUtil, user, renderTabUploadShops, userDTO);
        init();
    }

    public void init() {
        setVisualizzaStatus(TUTTO);

        setMenuModelB(new DefaultMenuModel());
        setMenuModelF(new DefaultMenuModel());
        setMenuModelCB(new DefaultMenuModel());
        setMenuModelCF(new DefaultMenuModel());
        setMenuModelZonaNo(new DefaultMenuModel());
        setMenuModelZonaYes(new DefaultMenuModel());
        setMenuModelCediNo(new DefaultMenuModel());
        setMenuModelCediYes(new DefaultMenuModel());

        createModel().forEach(m -> {
            if ((m.getParams().get("source") != null) && (m.getParams().get("source").size() > 0)) {
                if (m.getParams().get("source").get(0).equals("B")) {
                    getMenuModelB().addElement(m);
                } else if (m.getParams().get("source").get(0).equals("F")) {
                    getMenuModelF().addElement(m);
                } else {
                    log.warn("unsupported menu model type " + m.getParams().get("source"));
                }
            } else {
                log.warn("Empty menu model");
            }
        });

        createModelConsegna().forEach(m -> {
            if ((m.getParams().get("source") != null) && (m.getParams().get("source").size() > 0)) {
                if (m.getParams().get("source").get(0).equals("CB")) {
                    getMenuModelCB().addElement(m);
                } else if (m.getParams().get("source").get(0).equals("CF")) {
                    getMenuModelCF().addElement(m);
                } else {
                    log.warn("unsupported menu model type " + m.getParams().get("source"));
                }
            } else {
                log.warn("Empty menu model");
            }
        });

        createModelZona().forEach(m -> {
            if ((m.getParams().get("source") != null) && (m.getParams().get("source").size() > 0)) {
                if (m.getParams().get("source").get(0).equals("zonaNo")) {
                    getMenuModelZonaNo().addElement(m);
                } else if (m.getParams().get("source").get(0).equals("zonaYes")) {
                    getMenuModelZonaYes().addElement(m);
                } else {
                    log.warn("unsupported menu model type " + m.getParams().get("source").get(0));
                }
            } else {
                log.warn("Empty menu model");
            }
        });


        createModelCedi().forEach(m -> {
            if ((m.getParams().get("source") != null) && (m.getParams().get("source").size() > 0)) {
                if (m.getParams().get("source").get(0).equals("cediNo")) {
                    getMenuModelCediNo().addElement(m);
                } else if (m.getParams().get("source").get(0).equals("cediSi")) {
                    getMenuModelCediYes().addElement(m);
                } else {
                    log.warn("unsupported menu model type " + m.getParams().get("source").get(0));
                }
            } else {
                log.warn("Empty menu model");
            }
        });

        getMenuModelB().generateUniqueIds();
        getMenuModelF().generateUniqueIds();
        getMenuModelCB().generateUniqueIds();
        getMenuModelCF().generateUniqueIds();
        getMenuModelZonaNo().generateUniqueIds();
        getMenuModelZonaYes().generateUniqueIds();
        getMenuModelCediYes().generateUniqueIds();
        getMenuModelCediNo().generateUniqueIds();
    }

    @Override
    public void menuItemClicked(final ActionEvent event) {
        final MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
        log.debug(
                "clicked on menuitem " + menuItem.getValue() + " on splitButton " + menuItem.getParams().get("source"));

        boolean radioButtonSelected = menuItem.getParams().get("source").get(0).equals("F");
        Long tipoNegozioSelected = new Long(menuItem.getParams().get("tipoNegozio").get(0));
        String descriptionShopSelected = menuItem.getParams().get("itemDescription").get(0);

        try {
            final UserDTO userDTO = getUserDTO();
            promoShopsUtil.updatePromoNegozioSelectedFlag(tipoNegozioSelected, radioButtonSelected ? "0" : "1",
                    getIdPromo(), getUser(), userDTO.getCanali(), userDTO.getGruppi(), isUserAdmin());
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                    "Promozioni modificate per tipo negozio " + descriptionShopSelected));
        } catch (Exception e) {
            log.error("Error map PromozioneTestataEntity into JSON object", e);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore modifica promozioni per tipo negozio " + descriptionShopSelected));
        }

        executeScript("refreshGridFilterByRadioCheck(" + getIdPromo() + ");");
    }

    @Override
    public void handleFileUpload(FileUploadEvent event) {
        if ((event != null) && (event.getFile() != null)) {
            File file = writeByte(event.getFile().getContents(), event.getFile().getFileName());
            fileContent = event.getFile().getContents();
            final PromoShopUpdateDto result = promoShopsUtil.readAndUploadFileUntilEmptyRow(file, getIdPromo(),
                    getCurrentUser().getName());
            FacesMessage.Severity severity;
            switch (result.getLevel()) {
                case INFO:
                    severity = FacesMessage.SEVERITY_INFO;
                    break;
                case WARNING:
                    severity = FacesMessage.SEVERITY_WARN;
                    break;
                case ERROR:
                    severity = FacesMessage.SEVERITY_ERROR;
                    break;
                default:
                    severity = FacesMessage.SEVERITY_FATAL;
                    break;
            }
            addMessage(null, new FacesMessage(severity, result.getLevel().getValue(), result.getMessage()));
            executeScript("refreshGridFilterByRadioCheck(" + getIdPromo() + ");");
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di caricamento",
                    "Non e' stato possibile caricare il file"));
        }
    }

    public StreamedContent getDownloadTemplate() {
        try {
            final InputStream stream = this.getClass().getClassLoader()
                    .getResourceAsStream("TEMPLATE/caricamento_negozi_template.xltx");
            if (stream == null) {
                log.error("Error getting template caricamento_negozi_template.xltx");
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Non e' stato possibile scaricare il template; contattare l'assistenza tecnica"));
                return null;
            }
            return new DefaultStreamedContent(stream,
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.template",
                    "caricamento_negozi_template.xltx");
        } catch (Exception ex) {
            log.error("Error getting template caricamento_negozi_template.xltx", ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Non e' stato possibile scaricare il template; contattare l'assistenza tecnica"));
            return null;
        }
    }

    private File writeByte(byte[] bytes, String filename) {
        try {
            // Initialize a pointer
            // in file using OutputStream
            File file = new File(filename);
            OutputStream os = new FileOutputStream(file);

            // Starts writing the bytes in it
            os.write(bytes);
            os.flush();

            // Close the file
            os.close();
            return file;
        } catch (Exception e) {
            throw new RuntimeException("write file error", e);
        }
    }
}

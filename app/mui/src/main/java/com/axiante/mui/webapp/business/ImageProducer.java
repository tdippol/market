package com.axiante.mui.webapp.business;

import com.axiante.mui.backing.ApplicationProperties;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ApplicationScoped
@Slf4j
@Named(value = "imageProducer")
public class ImageProducer {
    @Inject
    ApplicationProperties applicationProperties;

    public StreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
            String filename = context.getExternalContext().getRequestParameterMap().get("mui_image_name");
            log.info(String.format("trying to serve image %s", filename));
            if ( filename != null ){
                File file = getFile(filename);
                if ( file != null && file.exists() && file.canRead() ){
                    try {
                        return new DefaultStreamedContent(Files.newInputStream(Paths.get(file.toURI())), Files.probeContentType(file.toPath()));
                    } catch (Exception e) {
                        log.error(String.format("Error getting content of image at %s", file.getAbsolutePath()));
                    }
                }
                log.error(String.format("File %s does not exist or is not readable", filename));
            } else {
                log.error("missing name parameter from context");
            }
        }
        return new DefaultStreamedContent();
    }

    public File getFile(@NonNull String fileName){
        final String path = getImagePath();

        if ( StringUtils.isEmpty(path)){
            log.error(String.format("Invalid configuration for Image Path %s", path));
            return null;
        }
        StringBuilder filePath = new StringBuilder(path);
        if ( !path.endsWith(File.separator) ){
            filePath.append(File.separator);
        }
        filePath.append(fileName);
        filePath = new StringBuilder(filePath.toString().replace("\\", "/"));

        if ( isDefaultPath(path) ){
            try {
                log.info("trying to retrieve resource at "+ filePath.toString());
                URL url = FacesContext.getCurrentInstance().getExternalContext().getResource(filePath.toString());
                if ( url == null ){
                    log.error("Error resolving " + filePath.toString());
                    return null;
                }
                return Paths.get(url.toURI()).toFile();
            } catch (MalformedURLException | URISyntaxException e) {
                log.error(String.format("Error getting actual file %s", fileName), e);
            }
        } else {
            return Paths.get(filePath.toString()).toFile();
        }
        return null;
    }

    private String getImagePath(){
        return applicationProperties.getProperty(ApplicationProperties.LOGOS_DATA_PATH, ApplicationProperties.DEFAULT_LOGOS_DATA_PATH);
    }
    private boolean isDefaultPath(@NonNull String path){
        return ApplicationProperties.DEFAULT_LOGOS_DATA_PATH.equals(path);
    }
}

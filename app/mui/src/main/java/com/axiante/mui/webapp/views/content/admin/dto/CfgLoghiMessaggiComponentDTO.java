package com.axiante.mui.webapp.views.content.admin.dto;

import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CfgLoghiMessaggiComponentDTO implements Serializable {
  private static final long serialVersionUID = -4159685593813663720L;
  MessaggiComponentsEnum name;
  @Setter Boolean attivo;
  @Setter Boolean unicaInRiga;
  @Setter String componentText;

  public String getTesto() {
    if (componentText == null) {
      return name.getValue();
    }
    return componentText;
  }

  public void setTesto(String testo) {
    if (StringUtils.isEmpty(testo)) {
      this.componentText = null;
    } else {

      this.componentText = testo;
    }
  }

  public static CfgLoghiMessaggiComponentDTO getDefaultComponent(MessaggiComponentsEnum name) {
    return new CfgLoghiMessaggiComponentDTO(name, false, false, null);
  }
}

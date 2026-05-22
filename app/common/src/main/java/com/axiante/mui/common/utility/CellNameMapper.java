package com.axiante.mui.common.utility;

import java.io.Serializable;
import java.util.regex.Pattern;
import lombok.NonNull;

public class CellNameMapper implements Serializable{
    public static synchronized String map2agGrid(@NonNull final String name) {
        return name 
                .replaceAll(Pattern.quote(" "), "")
                .replaceAll(Pattern.quote("+"), "_plus_")
                .replaceAll(Pattern.quote("{"), "_obrace_")
                .replaceAll(Pattern.quote("}"), "_cbrace_")
                .replaceAll(Pattern.quote("."), "_dot_")
                .replaceAll(Pattern.quote("%"), "_perc_")
                .replaceAll(Pattern.quote("/"), "_slash_")
                .replaceAll(Pattern.quote("à"), "_agrave_")
                .replaceAll(Pattern.quote("á"), "_aacute_")
                .replaceAll(Pattern.quote("è"), "_egrave_")
                .replaceAll(Pattern.quote("é"), "_eacute_")
                .replaceAll(Pattern.quote("ì"), "_igrave_")
                .replaceAll(Pattern.quote("í"), "_iacute_")
                .replaceAll(Pattern.quote("ò"), "_ograve_")
                .replaceAll(Pattern.quote("ó"), "_oacute_")
                .replaceAll(Pattern.quote("ù"), "_ugrave_")
                .replaceAll(Pattern.quote("ú"), "_uacute_")
                .replaceAll(Pattern.quote("-"), "_minus_")
                .replaceAll(Pattern.quote("("), "_ocurve_")
                .replaceAll(Pattern.quote(")"), "_ccurve_")
                .replaceAll(Pattern.quote("["), "_osquare_")
                .replaceAll(Pattern.quote("]"), "_csquare_")
                .replaceAll(Pattern.quote("€"), "_eur_")
                .replaceAll(Pattern.quote("Δ"), "_delta_")
                ;

    }

    public static synchronized String agGrid2map(@NonNull final String name) {
        return name
                .replaceAll(Pattern.quote("_plus_")  ,"+")
                .replaceAll(Pattern.quote("_obrace_"),"{")
                .replaceAll(Pattern.quote("_cbrace_"),"}")
                .replaceAll(Pattern.quote("_dot_")   ,".")
                .replaceAll(Pattern.quote("_perc_")  ,"%")
                .replaceAll(Pattern.quote("_slash_") ,"/")
                .replaceAll(Pattern.quote("_agrave_") ,"à")
                .replaceAll(Pattern.quote("_aacute_") ,"á")
                .replaceAll(Pattern.quote("_egrave_") ,"è")
                .replaceAll(Pattern.quote("_eacute_") ,"é")
                .replaceAll(Pattern.quote("_igrave_") ,"ì")
                .replaceAll(Pattern.quote("_iacute_") ,"í")
                .replaceAll(Pattern.quote("_ograve_") ,"ò")
                .replaceAll(Pattern.quote("_oacute_") ,"ó")
                .replaceAll(Pattern.quote("_ugrave_") ,"ù")
                .replaceAll(Pattern.quote("_uacute_") ,"ú")
                .replaceAll(Pattern.quote("_minus_") ,"-")
                .replaceAll(Pattern.quote("_ocurve_") ,"(")
                .replaceAll(Pattern.quote("_ccurve_") ,")")
                .replaceAll(Pattern.quote("_osquare_") ,"[")
                .replaceAll(Pattern.quote("_csquare_") ,"]")
                .replaceAll(Pattern.quote("_eur_") ,"€")
                .replaceAll(Pattern.quote("_delta_") ,"Δ")
                ;
    }
}

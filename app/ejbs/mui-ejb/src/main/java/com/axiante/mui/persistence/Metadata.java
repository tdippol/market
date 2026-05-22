package com.axiante.mui.persistence;

/**
 * la bruttezza fatta classe: non posso passare a runtime lo schema alla JPA
 * ma ho bisogno di deployare su schemi db diversi a seconda dello stadio di 
 * sviluppo.
 * 
 * @author cslmf14
 *
 */
public class Metadata {
    public static final String SCHEMA="MUIPROMO";
}

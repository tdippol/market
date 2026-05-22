UPDATE MUIPROMO.CONFIGURATION SET PATH = '/Reporting/Copia_in_Pianificazione' WHERE PATH = '/Reporting/Copia_in_pianificazione';
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('114', '/Reporting/Analisi_Budget-Venduto/Categoria', '{
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_compratore":["descrizione"],
    "rep_categoria":["descrizione"],
    "rep_misuraTimone":["descrizione"],
    "rep_scenario":["descrizione"]
}
', 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('115', '/Reporting/Analisi_Budget-Venduto/Categoria', TO_CLOB('{
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Categoria",
			"logMemory": true,
			"logTime": true,
			"skip": true,
            "maxCells":1000000 ,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ]}",
						"REP - Misura Timone": "{[REP - Misura Timone].[Misura Timone - Timone]}"
					}
				},
				"FROM": "[Timone Reporting]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Categoria.Descrizione",
				"headerName": "Categoria",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
                {
					"headerName": "Totale Compratore",
					"field": "REP_minus_Compratore.MUI_TOTS",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Caregory",
					"field": "REP_minus_Compratore.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},

              {
					"headerName": "Totale Categoria",
					"field": "REP_minus_Categoria.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Direzione",
					"field": "REP_minus_Categoria.MUI_DIR",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Reparto",
					"field": "REP_minus_Categoria.MUI_REP",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
				{
					"headerName": "Categoria",
					"field": "REP_minus_Categoria.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('116', '/Reporting/Analisi_Budget-Venduto/Articolo', '{
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
    "rep_fornitore":["descrizione"],
    "rep_zonaPromo":["descrizione"],
    "rep_sezioneTematica":["descrizione"],
    "rep_meccanicaSemplice":["descrizione"],
    "rep_avolantino":["descrizione"],
    "rep_MisuraReportingArticoloZona":["descrizione"]
}
', 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('117', '/Reporting/Analisi_Budget-Venduto/Articolo', TO_CLOB('{
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Articolo",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Articolo.DescrizioneArticolo",
				"headerName": "Articolo",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
			    {
					"headerName": "Totale Articolo",
					"field": "REP_minus_Articolo.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"headerName": "Reparto",
					"field": "REP_minus_Articolo.RepartoDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				 } ,
                 {
					"headerName": "Categoria",
					"field": "REP_minus_Articolo.CategoriDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				  } ,
                 {
					"headerName": "GRM",
					"field": "REP_minus_Articolo.GRMDesc",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				  } ,
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.SubGrmDesc",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Compratori",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Zona Promo",
					"field": "REP_minus_ZonaPromo.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Meccanica",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}
'), 'GRID');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('101', '25', 'Target (CAT)', 'timone/fotoSpeciali/targetCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('102', '25', 'Target (ACQ)', 'timone/fotoSpeciali/targetAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('103', '28', 'Target Reparto (CAT)', 'timone/spaziCampagna/targetRepartoCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('104', '28', 'Target Reparto (ACQ)', 'timone/spaziCampagna/targetRepartoAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('105', '28', 'Target Categoria (CAT)', 'timone/spaziCampagna/targetCategoriaCat.xhtml', 'agGrid', '2', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('106', '28', 'Target Categoria (ACQ)', 'timone/spaziCampagna/targetCategoriaAcq.xhtml', 'agGrid', '2', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('107', '22', 'Promo (CAT)', 'timone/targetCategoria/promoCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('108', '22', 'Promo (ACQ)', 'timone/targetCategoria/promoAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('109', '22', 'Data (CAT)', 'timone/targetCategoria/dataCat.xhtml', 'agGrid', '2', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('110', '22', 'Data (ACQ)', 'timone/targetCategoria/dataAcq.xhtml', 'agGrid', '2', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('111', '19', 'Promo (CAT)', 'timone/targetReparto/promoCat.xhtml', 'agGrid', '1', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('112', '19', 'Promo (ACQ)', 'timone/targetReparto/promoAcq.xhtml', 'agGrid', '1', '0');

INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('113', '19', 'Data (CAT)', 'timone/targetReparto/dataCat.xhtml', 'agGrid', '2', '0');
INSERT INTO MUIPROMO.MENU (ID_MENU, PARENT_ID, LABEL, URL, BEAN, ORDER_ID, EXTERNAL_LINK) VALUES ('114', '19', 'Data (ACQ)', 'timone/targetReparto/dataAcq.xhtml', 'agGrid', '2', '0');


INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('118', '/Timone/Foto_Speciali/Target_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Foto_Speciali/Target' AND TYPE = 'GRID') , 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('119', '/Timone/Foto_Speciali/Target_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Foto_Speciali/Target' AND TYPE = 'FILTER'), 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('120', '/Timone/Foto_Speciali/Target_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Foto_Speciali/Target' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('121', '/Timone/Foto_Speciali/Target_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Foto_Speciali/Target' AND TYPE = 'FILTER'), 'FILTER');

INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('122', '/Timone/Spazi_Campagna/Target_Reparto_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Reparto' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('123', '/Timone/Spazi_Campagna/Target_Reparto_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Reparto' AND TYPE = 'FILTER'), 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('124', '/Timone/Spazi_Campagna/Target_Reparto_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Reparto' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('125', '/Timone/Spazi_Campagna/Target_Reparto_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Reparto' AND TYPE = 'FILTER'), 'FILTER');

INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('126', '/Timone/Spazi_Campagna/Target_Categoria_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Categoria' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('127', '/Timone/Spazi_Campagna/Target_Categoria_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Categoria' AND TYPE = 'FILTER'), 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('128', '/Timone/Spazi_Campagna/Target_Categoria_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Categoria' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('129', '/Timone/Spazi_Campagna/Target_Categoria_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Spazi_Campagna/Target_Categoria' AND TYPE = 'FILTER'), 'FILTER');

INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('130', '/Timone/Target_Categoria/Promo_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Promo' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('131', '/Timone/Target_Categoria/Promo_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Promo' AND TYPE = 'FILTER'), 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('132', '/Timone/Target_Categoria/Promo_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Promo' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('133', '/Timone/Target_Categoria/Promo_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Promo' AND TYPE = 'FILTER'), 'FILTER');

INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('134', '/Timone/Target_Categoria/Data_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Data' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('135', '/Timone/Target_Categoria/Data_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Data' AND TYPE = 'FILTER'), 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('136', '/Timone/Target_Categoria/Data_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Data' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('137', '/Timone/Target_Categoria/Data_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Categoria/Data' AND TYPE = 'FILTER'), 'FILTER');

INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('138', '/Timone/Target_Reparto/Data_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Data' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('139', '/Timone/Target_Reparto/Data_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Data' AND TYPE = 'FILTER'), 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('140', '/Timone/Target_Reparto/Data_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Data' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('141', '/Timone/Target_Reparto/Data_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Data' AND TYPE = 'FILTER'), 'FILTER');

INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('142', '/Timone/Target_Reparto/Promo_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Promo' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('143', '/Timone/Target_Reparto/Promo_(CAT)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Promo' AND TYPE = 'FILTER'), 'FILTER');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('144', '/Timone/Target_Reparto/Promo_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Promo' AND TYPE = 'GRID'), 'GRID');
INSERT INTO MUIPROMO.CONFIGURATION (ID_CONFIGURATION, PATH, JSON, TYPE) VALUES ('145', '/Timone/Target_Reparto/Promo_(ACQ)', (SELECT JSON FROM MUIPROMO.CONFIGURATION WHERE PATH LIKE '/Timone/Target_Reparto/Promo' AND TYPE = 'FILTER'), 'FILTER');
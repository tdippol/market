INSERT INTO MUIPROMO.APPLICATION_PROPERTIES (ID_APPLICATION_PROPERTIES, AP_KEY, AP_VALUE) VALUES (8, 'GRID_HEIGHT', '65');
UPDATE MUIPROMO.MENU SET ORDER_ID = 999 WHERE LABEL = 'Admin';

UPDATE MUIPROMO.MENU SET TEMPLATE = 1 WHERE PARENT_ID <> 31 AND ID_MENU <> 31;
UPDATE MUIPROMO.MENU SET TEMPLATE = 1 WHERE PARENT_ID IS NULL AND ID_MENU <> 31;

DELETE FROM MUIPROMO.CONFIGURATION;

SET DEFINE OFF;
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('95','/Piano_Annuale/Spazi/Spazi_Condivisi',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "spazio":["descrizione","compratore","macroSpazio", "reparto","macroSpazioDescrizione"],
    "compratore":["descrizione" , "repartodcodesc" , "categorymanager" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('102','/Reporting/Timone_Reparto',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_misuraTimone":["descrizione"],
	"rep_scenario":["descrizione"],
	"rep_reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('105','/Timone/Foto_Speciali/Riepilogo',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('106','/Timone/Foto_Speciali/Target',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('107','/Timone/Spazi_Campagna/Target_Categoria',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('108','/Timone/Spazi_Campagna/Target_Reparto',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('109','/Timone/Target_Categoria/Data',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('110','/Timone/Target_Categoria/Promo',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('111','/Timone/Target_Reparto/Data',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('112','/Timone/Target_Reparto/Promo','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('113','/Timone/Associazione_Promo','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "promozioneriferimento":["anno","rif_canale","semestre","gruppo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('87','/Piano_Annuale/Visualizza_Piano','{
    "promozione":["anno", "canale","tipo","descrizione","riferimento","semestre","proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('58','/welcome',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","proximity"],
    "rep_promozione":["anno","canale","riferimento","semestre", "descrizione","canaleanno"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('89','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "pubblicita":["descrizione"]
} ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('91','/Piano_Annuale/Spazi/Macrospazi_Listino_Promo',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "macrospazio":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('78','/Piano_Annuale/Crea_Promozione',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('79','/Piano_Annuale/Foto',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "raggruppamentoFoto":["mui_descrizione","tot", "tots","mui_compratore" , "mui_reparto"],
    "compratore":["descrizione" , "repartodcodesc" , "categorymanager"  ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('80','/Piano_Annuale/Gabbia',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('81','/Piano_Annuale/Gestione_Contributi',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "tipoPromozione":["descrizione","gruppo"],
     "contratto":["descrizione"],
       "prestazione":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('83','/Piano_Annuale/Meccaniche',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('84','/Piano_Annuale/Modifica_Promozione',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('85','/Piano_Annuale/Negozi_Promo',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "negozio":["descrizione","zonaPromo" ],
    "canale":["descrizione"],
    "misuraCanale":["descrizione"],
    "sezioneTematica":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('88','/Piano_Annuale/Zone_Promo',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "zonaPromo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('67','/Fatturazione/Associazioni_Articoli',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "spazioProgressivo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('70','/Pianificazione/Articoli_Fittizi',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore": ["descrizione"],
	"articoloFittizio":["descrizione","compratore"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('71','/Pianificazione/Differenziazione_Zone',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"],
	"zonaPromo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('4','/Admin/Gestione_Configurazioni','  {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('5','/Admin/Gestione_Menu',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('7','/Admin/Gestione_UI','  {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('74','/Pianificazione/Selezione_Articoli_Contributi',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"categoria": ["descrizione"],
	"fornitore": ["descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('75','/Pianificazione/Selezione_Famiglie_Contributi',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"categoria": ["descrizione"],
	"fornitore": ["descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('96','/Reporting/Copia_in_Pianificazione',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_scenario":["descrizione"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticolo":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('97','/Reporting/Dettaglio_Campagna',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_scenario":["descrizione"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('114','/Reporting/Analisi_Budget-Venduto/Categoria','{
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
    "rep_compratore":["descrizione"],
    "rep_categoria":["descrizione"],
    "rep_misuraTimone":["descrizione"],
    "rep_scenario":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('115','/Reporting/Analisi_Budget-Venduto/Categoria',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Categoria",
			"logMemory": true,
			"logTime": true,
			"skip": true,
            "maxCells":2000000 ,
          "title": "Analisi Budget-Venduto - Categoria",
"height": 60,

          "REP - Misura Timone": "{[REP - Misura Timone].[MUI_SUB_Analisi_Budget_Venduto_Categoria]}",
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM')
    || TO_CLOB('1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ],[REP - Scenario].[BDG],[REP - Scenario].[CONS]}",
	')
    || TO_CLOB('					"REP - Misura Timone": " {{[REP - Misura Timone].[N_ART_PROMO]},{[REP - Misura Timone].[TOT_FOTO]},{[REP - Misura Timone].[N_FOTO]},{[REP - Misura Timone].[N_FOTO_SCAFFALE]},{[REP - Misura Timone].[N_FOTO_SPEC]},{[REP - Misura Timone].[N_FOTO_SCAFFALE_SPEC]},{[REP - Misura Timone].[N_FOTO_ULT]},{[REP - Misura Timone].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [REP - Misura Timone] )}, [REP - Misura Timone].[Tipo] = ''Spazi'')}   }"
					}
				},
				"FROM": "[Timone Reporting]",
				"WHERE": {
')
    || TO_CLOB('					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/MUI_DescrizioneData",
                    "Attributes/Descrizione",
                  	"Attributes/MUI_TOTS",
                  "Attributes/MUI_TOT",
                  "Attributes/MUI_DIR",
                  "Attributes/MUI_REP",
				  "UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			')
    || TO_CLOB('},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "open')
    || TO_CLOB('ByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ')
    || TO_CLOB(',

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
')
    || TO_CLOB('
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
						"TM1Elemen')
    || TO_CLOB('t"
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
		')
    || TO_CLOB('			"headerName": "Categoria",
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
			"rowSuppressionEnabled":')
    || TO_CLOB(' false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('116','/Reporting/Analisi_Budget-Venduto/Articolo','{
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
    "rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
    "rep_fornitore":["descrizione"],
    "rep_zonaPromo":["descrizione"],
    "rep_sezioneTematica":["descrizione"],
    "rep_meccanicaSemplice":["descrizione"],
    "rep_avolantino":["descrizione"],
    "rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('117','/Reporting/Analisi_Budget-Venduto/Articolo',TO_CLOB('{
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_AnalisiBudgetVenduto_Articolo",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":2000000 ,
          "title": "Analisi Budget-Venduto - Articolo",
"height": 60,

             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[RE')
    || TO_CLOB('P - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIO')
    || TO_CLOB('NS": {
          						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)')
    || TO_CLOB('}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozio')
    || TO_CLOB('ne": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
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
					"Name"')
    || TO_CLOB(',
					"Attributes/Descrizione",
                    "Attributes/DescrizioneArticolo",
                    "Attributes/MUI_TOT",
                    "Attributes/RepartoDesc",
                    "Attributes/CategoriDesc",
                    "Attributes/GRMDesc",
                    "Attributes/SubGrmDesc",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},

			"DynamicColumns" : true ,')
    || TO_CLOB('
			"DynamicColumnsSettings" :{
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_R')
    || TO_CLOB('EP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererPa')
    || TO_CLOB('rams": {
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
			')
    || TO_CLOB('		"headerName": "Reparto",
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
					"headerNam')
    || TO_CLOB('e": "GRM",
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
					"field": "REP_mi')
    || TO_CLOB('nus_Articolo.DescrizioneArticolo",
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
					"field": "REP_minus_Comp')
    || TO_CLOB('ratore.Descrizione",
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
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTem')
    || TO_CLOB('atica.Descrizione",
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
					"field": "REP_minus_AVolantino')
    || TO_CLOB('.Descrizione",
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
			"colSuppressionEnabled": false,
           "preSelections": [
        {
          "dimension": "REP - Compratore",
          "dimCode": "rep_compratore",
          "dimColumnName": "REP - Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          ')
    || TO_CLOB('"attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_AnalisiBudgetVenduto_Articolo"]
        }
      ]



		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('8','/Admin/Gestione_Utenti','  {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_GestioneUtenti",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1','/welcome',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_Welcome",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}
','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('72','/Pianificazione/Meccaniche_Set',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('73','/Pianificazione/Proiezioni',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"categoria": ["descrizione"],
	"fornitore": ["descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('2','/Admin/Gestione_ACL',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('3','/Admin/Gestione_Applicazione',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('6','/Admin/Gestione_Ruoli',' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gridContainer",
			"logMemory": true,
			"logTime": true,
			"skip": true
		}
	]
}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('9','/Fatturazione/Associazione_Articoli_Promozione',TO_CLOB('  {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_AssociazioneArticoliPromo_top",
			"logMemory": true,
			"logTime": true,
			"skip": true,
"title":"Contribuzione Campagna",
          "height": 60,

			"Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1')
    || TO_CLOB('SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
						"Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) },')
    || TO_CLOB(' { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"


					}
				},
				"FROM": "[Fatturazione]",
				"WHERE": {
					"Scenario": "[BDG]",
					"Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/MUI_Descrizione",
                    "Attributes/Canale",
          ')
    || TO_CLOB('          "Attributes/Anno",
                    "Attributes/MUI_Semestre",
                    "Attributes/Descrizione",
                    "Attributes/Riferimento",
                    "Attributes/MUI_TOT",
                    "Attributes/MUI_TOTS",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},
			"DynamicColumns": true,
			"DynamicColumnsSettings": {
				"headerconf": [
					"De')
    || TO_CLOB('scrizione"
				],
				"headerdefaults": {
					"marryChildren": true
				},
				"headerCustomTypes": {
					"RIF_MKT_DT": {
						"openByDefault": true
					},
					"TGT_ACQ": {
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT": {
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP": {
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults": {
					"width": 100,
	')
    || TO_CLOB('				"hide": false,
					"rowGroup": false,
					"aggFunc": "sum",
					"columnGroupShow": "always",
					"editable": true,
					"type": [
						"TM1DataColumnText",
						"numericColumn"
					]
				},
				"childrenCustomTypes": {
					"LISTINO": {
						"type": [
							"TM1DataColumnInteger",
							"numericColumn"
						]
					},
					"APPLICATO": {
						"type": [
							"TM1DataColumnInteger",
							"numericColumn"
						]
					},
					"PRESTAZIONE": {
						"width":')
    || TO_CLOB(' 300,
						"type": [
							"TM1DataColumnText"
						]
					},
					"CAUS": {
						"width": 300,
						"type": [
							"TM1DataColumnText"
						]
					},
					"DESC_FATT": {
						"width": 300,
						"type": [
							"TM1DataColumnText"
						]
					},
					"OK": {
						"width": 50,
						"type": [
							"TM1DataColumnText"
						]
					}
				}
			},
			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "Rata.Des')
    || TO_CLOB('crizione",
				"headerName": "Rata",
				"width": 400,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Descrizione + Data",
					"field": "Promozione.MUI_Descrizione",
					"width": 400,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},{
					"headerName": "Canale",
					"field": "Promozione.Canale",
					"width": 100,
					"hide": true,
					"row')
    || TO_CLOB('Group": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1E')
    || TO_CLOB('lement"
					]
				},
				{

					"headerName": "Descrizione",
					"field": "Promozione.Descrizione",
					"width": 400,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferimento",
					"field": "Promozione.Riferimento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName"')
    || TO_CLOB(': "Totale Compratore",
					"field": "Compratore.MUI_TOT",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Compratore",
					"field": "Compratore.Descrizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Fornitore",
					"field": "Fornitore.MUI')
    || TO_CLOB('_TOTS",
					"width": 100,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitore",
					"field": "Fornitore.Descrizione",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Rata",
					"field": "Rata.MUI_TOTS",
					"width": 70,
					"hide": true,
					"rowGroup"')
    || TO_CLOB(': true,
					"editable": false,

					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Rata",
					"field": "Rata.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}
			]
		},
		{
			"name": "gc_AssociazioneArticoliPromo_bottom",
			"logMemory": true,
			"logTime": true,
			"skip": true,
          "title": "Associazione Articoli",
"height": 60,

			"MDX": ')
    || TO_CLOB('{
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
						"Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitor')
    || TO_CLOB('e] )}, 0)}, DESC)}}",
						"Rata": "{ [Rata].[(I) Fatturazione] }",
						"ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
						"Spazio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Fatturazione": "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
					}
				},
				"FROM": "[Fattura')
    || TO_CLOB('zione Articolo]",
				"WHERE": {
					"Scenario": "[BDG]",
					"Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/MUI_Descrizione",
                    "Attributes/Canale",
                    "Attributes/Anno",
                    "Attributes/MUI_Semestre",
                    "Attributes/Descrizione",
                    "Attributes/Riferimento",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updat')
    || TO_CLOB('eable",
					"Consolidated",
					"HasPicklist"
				]
			},
			"DynamicColumns": true,
			"DynamicColumnsSettings": {
				"headerconf": [
					"Descrizione"
				],
				"headerdefaults": {
					"marryChildren": true
				},
				"headerCustomTypes": {
					"RIF_MKT_DT": {
						"openByDefault": true
					},
					"TGT_ACQ": {
						"headerClass": "headerAcq",
						"openByDefault": true
					},
					"TGT_MKT": {
						"headerClass": "headerMkt",
						"openByDefault": true
	')
    || TO_CLOB('				},
					"TGT_REP": {
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults": {
					"width": 200,
					"hide": false,
					"rowGroup": false,
					"aggFunc": "sum",
					"columnGroupShow": "always",
					"editable": true,
					"type": [
						"TM1DataColumnText"
					]
				},
				"childrenCustomTypes": {

				}
			},
			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true

				},
				"field": "Art')
    || TO_CLOB('icoloNoSec.Descrizione",
				"headerName": "Articolo",
				"width": 400,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "MUI_Descrizione",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},{
					"headerName": "Compratore",
					"field": "Compratore.Descrizione",
					"width": 200,
')
    || TO_CLOB('
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Canale",
					"field": "Promozione.Canale",
					"width": 100,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					')
    || TO_CLOB('"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.Descrizione",
					"width": 400,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
')
    || TO_CLOB('
					"headerName": "Riferimento",
					"field": "Promozione.Riferimento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitore",
					"field": "Fornitore.Descrizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Rata",

					"field": "Rata.Desc')
    || TO_CLOB('rizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "ArticoloNoSec.Descrizione",
					"width": 300,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Spazio",
					"field": "SpazioProgressivo.Descrizione",
					"width": 200,
					"hide": false')
    || TO_CLOB(',
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}
			]
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('10','/Fatturazione/Associazioni_Articoli',TO_CLOB('  {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_AssociazioneArticoliPromo_top",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      			"title":"Contribuzione Campagna",
          "height": 60,

      "Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{{TM1SOR')
    || TO_CLOB('T( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER')
    || TO_CLOB('( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
          }
        },
        "FROM": "[Fatturazione]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "Execu')
    || TO_CLOB('teMDX": {
        "Members": [
          "Name",
          			"Attributes/MUI_DescrizioneData",
                    "Attributes/Descrizione",
                    "Attributes/MUI_TOT",
                    "Attributes/MUI_TOTS",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
   ')
    || TO_CLOB('     "headerconf": [
          "MUI_DescrizioneData",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          }')
    || TO_CLOB(',
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "LISTINO"')
    || TO_CLOB(': {
            "type": [
              "TM1DataColumnInteger",
              "numericColumn"
            ]
          },
          "APPLICATO": {
            "type": [
              "TM1DataColumnInteger",
              "numericColumn"
            ]
          },
          "PRESTAZIONE": {
            "width": 300,
            "type": [
              "TM1DataColumnText"
            ]
          },
          "CAUS": {
            "width": 300,
            "type": [
            ')
    || TO_CLOB('  "TM1DataColumnText"
            ]
          },
          "DESC_FATT": {
            "width": 300,
            "type": [
              "TM1DataColumnText"
            ]
          },
          "OK": {
            "width": 50,
            "type": [
              "TM1DataColumnText"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Rata.Descrizione",
        "')
    || TO_CLOB('headerName": "Rata",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Totale Compratore",
          "field": "Compratore.MUI_TOT",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "')
    || TO_CLOB('field": "Compratore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Totale Fornitore",
          "field": "Fornitore.MUI_TOTS",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
    ')
    || TO_CLOB('    {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Totale Rata",
          "field": "Rata.MUI_TOTS",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            ')
    || TO_CLOB('"TM1Element"
          ]
        },
        {
          "headerName": "Rata",
          "field": "Rata.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ]
    },
    {
      "name": "gc_AssociazioneArticoliPromo_bottom",
      "logMemory": true,
      "logTime": true,
      "skip": true,
                "title": "Associazione Articoli"')
    || TO_CLOB(',
"height": 60,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ [Rata].[(I) Fatturazione] }",
            "ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
            "Sp')
    || TO_CLOB('azio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Misura Fattu')
    || TO_CLOB('razione": "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
          }
        },
        "FROM": "[Fatturazione Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_DescrizioneData",
          "Attributes/Descrizione",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
        ')
    || TO_CLOB('  "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "MUI_DescrizioneData",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
')
    || TO_CLOB('            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
  ')
    || TO_CLOB('        "type": [
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {}
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "ArticoloNoSec.Descrizione",
        "headerName": "Articolo",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Compratore",
')
    || TO_CLOB('          "field": "Compratore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },')
    || TO_CLOB('
        {
          "headerName": "Rata",
          "field": "Rata.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "ArticoloNoSec.Descrizione",
          "width": 300,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
     ')
    || TO_CLOB('       "TM1Element"
          ]
        },
        {
          "headerName": "Spazio",
          "field": "SpazioProgressivo.Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('13','/Pianificazione/Articoli_Fittizi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_articoliFittizi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000 ,
      "title" : "Articoli Fittizi",
      "height": 58,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Articolo Fittizio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}"
          }
        },
        ')
    || TO_CLOB('"COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Articolo Fittizio": "{[Misura Articolo Fittizio].[(I) Aggiornamento Articoli Fittizi]}"
          }
        },
        "FROM": "[Articoli Fittizi Aggiornamento]"
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
                    "Attributes/Descrizione",
                    "Attributes/MUI_TOT",
                    "Attributes/Compratore",
          "UniqueName"
        ],
')
    || TO_CLOB('
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "cellClass": "cellClass-left-text",
          "width": 150,
')
    || TO_CLOB('
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [


            "TM1DataColumnText",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {

          "VALORE": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
           "PEZZATURA": {
            "type": ["TM1Da')
    || TO_CLOB('taColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
           "IVA": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "DataInizio": {
            "cellClass": "dateFormat",
            "type": ["TM1DataColumnDate"]
          },
          "DataFine": {
            "cellClass": "dateFormat",
            "type": ["TM1DataColumnDate"]
          },
          "PRZ_ATT": {
            "typ')
    || TO_CLOB('e": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "ArticoloFittizio.Descrizione",
        "headerName": "Articolo Fittizio",
        "cellClass": "cellClass-left-text",
        "w')
    || TO_CLOB('idth": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Totale Fittizio",
          "field": "ArticoloFittizio.MUI_TOT",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "ArticoloFittizio.Com')
    || TO_CLOB('pratore",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": ')
    || TO_CLOB('"MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_articoliFittizi"]
        }
      ],
      "styleRules": {},
      "actions": [
        {
          "componentId": "pnf_aft_crea",
          "componentLabel": "Crea Articolo Fittizio in Promo",
          "process": "MUI_DUMMY_DIM.Articolo Fittizio.Aggiornamento New iN Promo",
          "timeout": -1,
          "refresh": [
            "gc_articoliFittizi"
          ],
      ')
    || TO_CLOB('    "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "pnf_aft_esegui",
          "componentLabel": "Esegui Azione Su Articoli Fittizzi",
          "process": "MUI_DUMMY_CUB.Articolo Fittizio.SpostaSuArtEff_new",
          "timeout": -1,
  ')
    || TO_CLOB('        "refresh": [
            "gc_articoliFittizi"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('14','/Pianificazione/Differenziazione_Zone',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_differenziazionePromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 750000,
      "title" : "Pianificazione promozione per zona",
      "height": 58,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione')
    || TO_CLOB('].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}",
            "Zona Promo": "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIE')
    || TO_CLOB('TA_1],[Zona Promo].[SOCIETA_2] })}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Promozione Pianificazione": "{[Misura Promozione Pianificazione Zone].[(I) Pianif promozione (zone)]}"
          }
        },
        "FROM": "[Promozione Pianificazione - Zone]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
        ')
    || TO_CLOB('  "Name",
                    "Attributes/MUI_Descrizione",
                    "Attributes/Descrizione",
          			"Attributes/Fornitore",
                    "Attributes/RepartoDesc",
          			"Attributes/CategoriaDesc",
          			"Attributes/GRMDesc",
          			"Attributes/SubGrmDesc",
                    "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
   ')
    || TO_CLOB('     ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1Da')
    || TO_CLOB('taColumnText"
          ]
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_MIN": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_MAX": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_ATT_USR"')
    || TO_CLOB(': {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "%_SC": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "VAL_SC": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_PROMO": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
   ')
    || TO_CLOB('       },
          "CST_AN": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_USR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CONTR_%_IN_FATT": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_C_IVA": {
            "type": ["TM1DataColumnNumber"],
           ')
    || TO_CLOB(' "cellClass": "cellClass-right-text"
          },
          "CST_PROMO_C_IVA": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "N_PEZZI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellClass-right-text"
          },
          "COLLI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellClass-right-text"
          },
          "TOT_dot_VEND": {
          ')
    || TO_CLOB('  "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "F_FATT": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "IVA": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
          "RIFATT_BS": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
 ')
    || TO_CLOB('         "LIM_UTIL": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "ZonaPromo.Descrizione",
        "headerName": "Zona Promo",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
')
    || TO_CLOB('
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable')
    || TO_CLOB('": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Articolo.Fornitore",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",
          "width": 100,
          "hide": true,
  ')
    || TO_CLOB('        "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "width": 100,
          "hide": true,

          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
 ')
    || TO_CLOB('         "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Sub Grm",
       ')
    || TO_CLOB('   "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
       "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizio')
    || TO_CLOB('ne",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_differenziazionePromo"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["')
    || TO_CLOB('gc_differenziazionePromo"]
        }
      ],
      "styleRules": {},
      "actions": [
        {
          "componentId": "pnf_dif_inizializza",
          "componentLabel": "Inizializza",
          "process": "MUI_DUMMY_CUB.Promozione Pianificazione Zone (Inizializza)",
          "timeout": -1,
          "refresh": [
            "gc_differenziazionePromo"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descri')
    || TO_CLOB('zione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('15','/Pianificazione/Meccaniche_Set',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_meccanicheSet_creazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 750000,
      "title" : "Creazione e modifica set",
      "height": 58,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data')
    || TO_CLOB('_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "ID Set": "{[ID Set].[(I) Lista set]}",
            "ID RaggrSet": "{[ID RaggrSet].[(I) Configura set]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozio')
    || TO_CLOB('ne - Set": "{[Misura Configurazione Promozione - Set].[(I) Definizione set]}"
          }
        },
        "FROM": "[Configurazione Promozione - Set Parametri]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
                    "Attributes/Descrizione",
                    "Attributes/MUI_Descrizione",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
')
    || TO_CLOB('          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": ')
    || TO_CLOB('true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "headerCustomTypes": {},
        "childrenCustomTypes": {
          "VAL_SC": {
           "type": [
              "TM1DataColumnNumber"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "IDRaggrSet.Descrizione",
        "headerName": "ID Raggr Set",
        "cellClass": "c')
    || TO_CLOB('ellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          ')
    || TO_CLOB('"field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "ID Set",
          "field": "IDSet.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
    ')
    || TO_CLOB('      "headerName": "ID RaggrSet",
          "field": "IDRaggrSet.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione"')
    || TO_CLOB(',
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_meccanicheSet_creazione","gc_meccanicheSet_associazione"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",')
    || TO_CLOB('
          "process": "",
          "paramName": "",
          "refresh": ["gc_meccanicheSet_creazione","gc_meccanicheSet_associazione"]
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_meccanicheSet_associazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "title" : "Associazione articoli a set",
      "height": 58,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          ')
    || TO_CLOB('"DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Compratore": "{{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000] }) }}",
            "ID Set": "{[ID Set].[(I) Lista set]}",
            "Articolo": "{{TM1SORT( {TM1FI')
    || TO_CLOB('LTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "ID RaggrSet": "{[ID RaggrSet].[(I) Configura set articoli]}",
            "Misura Configurazione Promozione - Set": "{[Misura Configurazione Promozione - Set].[(I) Set Articoli]}"

          }
        },
        "FROM": "[Configurazione Promozione - Set]",
        "WHERE": {
          "Versione": "[UFF]"
        }
   ')
    || TO_CLOB('   },
      "ExecuteMDX": {
        "Members": [
          "Name",
                    "Attributes/Descrizione",
                    "Attributes/MUI_Descrizione",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descriz')
    || TO_CLOB('ione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {
          "DataInizio": {
            "cellClass": "')
    || TO_CLOB('dateFormat",
            "type": [
              "TM1DataColumnDate",
              "numericColumn"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "DataFine": {
            "cellClass": "dateFormat",

            "type": [
              "TM1DataColumnDate",
              "numericColumn"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          }
        }
      },
      "autoGroupColum')
    || TO_CLOB('nDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "headerName": "Articolo",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "width": 100,
          "hide": true,
   ')
    || TO_CLOB('       "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "ID Set",
          "field": "IDSet.Descrizione",
        ')
    || TO_CLOB('  "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
       ')
    || TO_CLOB('   "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_meccanicheSet_creazione","gc_meccanicheSet_associazione"]
        },
         {
          "dimension": "Compratore",
          "dim')
    || TO_CLOB('Code": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_meccanicheSet_creazione","gc_meccanicheSet_associazione"]
        }
      ],
      "styleRules": {}
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('16','/Pianificazione/Proiezioni',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_proiezioni_1",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 750000,
      "title" : "",
      "height": 29,

      "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT')
    || TO_CLOB(']},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }" ,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[P')
    || TO_CLOB('roximity] <> ''Y'')} ",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misur')
    || TO_CLOB('a Timone.":"{[Misura Timone.].[MUI_Proiezione_Timone_Categoria]}"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
                    "Attributes/Descrizione",
                    "Attributes/MUI_Descrizione",
          			"Attributes/MUI_RepartoDesc",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          ')
    || TO_CLOB('"Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },

      "DynamicColumns": true,
      "Dyna')
    || TO_CLOB('micColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault"')
    || TO_CLOB(': true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },

          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": false,
          "type": [
         ')
    || TO_CLOB('   "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true"
          },
          "N_FOTO_ULT": {
            "aggFunc": ""
          },
          "N_FOTO_SCAFFALE": {
            "hide": "true"
          },
          "N_FOTO_SCAFFALE_SPEC": {
            "hide": "true"
          },
          "CONTR": ')
    || TO_CLOB('{
            "hide": "true"
          },
          "EXTRA_CONTR": {
            "hide": "true"
          },
          "D_ART_slash_TGT": {
            "hide": "true"
          },
          "D_FOTO_slash_TGT": {
            "hide": "true"
          },
		 "MARGINE_LORDO_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
		 },
		 "D_VEN')
    || TO_CLOB('D/RIF_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": ""
		 }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName')
    || TO_CLOB('": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.MUI_RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
    ')
    || TO_CLOB('      ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        }')
    || TO_CLOB(',
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        }
      ],
      "styleRules": {},
       "groupRowAggNodes": {
			"nodeGroupTrue": "true",
			"nodeGro')
    || TO_CLOB('upFalse": "true"
		}
    },
    {
      "name": "gc_proiezioni_2",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "title" : "",
      "height": 29,

       "Misura Promozione Pianificazione 2": "{([Misura Promozione Pianificazione].[Configurazione Subset Pianificazione 1])}",
      "Misura Promozione Pianificazione": "({FILTER(TM1SUBSETALL([Misura Promozione Pianificazione]),[}ElementAttributes_Misura Promozione Pianificazi')
    || TO_CLOB('one].([}ElementAttributes_Misura Promozione Pianificazione].[Ordinamento])>0 )})",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Promozione Pianificazione": "{([Misura Promozione Pianificazione].[MUI_Proiezioni])}"
          }
        },
        "FROM": "[Promozione Pianificazione]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Version')
    || TO_CLOB('e": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Descrizione",
          "Attributes/DescrizioneCODICE",
          "Attributes/Fornitore",
          "Attributes/RepartoDesc",
          "Attributes/CategoriaDesc",
          "Attributes/GRMDesc",
          "Attributes/SubGrmDesc",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
   ')
    || TO_CLOB('       "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": tr')
    || TO_CLOB('ue,
          "type": ["TM1DataColumnText"],
          "cellClass": "cellClass-left-text"
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_MIN": {

            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_MAX": {
            "type": ["TM1DataColumnNumber"],
            "cellCl')
    || TO_CLOB('ass": "cellClass-right-text"
          },
          "PRZ_ATT_USR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "_perc__SC": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"

          },
          "VAL_SC": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_PROMO": {
            "type": ["TM')
    || TO_CLOB('1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_AN": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_USR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CONTR__perc__IN_FATT": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
')
    || TO_CLOB('          "CST_C_IVA": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_PROMO_C_IVA": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "N_PEZZI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellClass-right-text"
          },
          "COLLI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellCl')
    || TO_CLOB('ass": "cellClass-right-text"
          },
          "TOT_dot_VEND": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "F_FATT": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "IVA": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
          "RIFATT_BS": {
            "type": ["TM1D')
    || TO_CLOB('ataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "LIM_UTIL": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "%_SC": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CONTR_%_IN_FATT": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "P')
    || TO_CLOB('EZZ": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "VEND_PROMO_S_IVA": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "CST_PROMO": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "ML_I_UNI": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellC')
    || TO_CLOB('lass-right-text"
          },
          "ML": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "%_ML": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
          "CONTR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "EXTRA_CONTR": {
            "type": ["TM1DataColumnNumber"],
 ')
    || TO_CLOB('           "cellClass": "cellClass-right-text"
          },
          "NumeroNegozi": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "PUNTI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellClass-right-text"
          }


        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descri')
    || TO_CLOB('zione",
        "headerName": "Articolo",
        "cellClass": "cellClass-left-text",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": ')
    || TO_CLOB('[
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo(Codice)",
          "field": "Articolo.DescrizioneCODICE",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "cel')
    || TO_CLOB('lClass": "cellClass-left-text",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Articolo.Fornitore",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM')
    || TO_CLOB('1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "cellClass": "cellClass-left-text",
 ')
    || TO_CLOB('         "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
     ')
    || TO_CLOB('   {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": ')
    || TO_CLOB('"Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",')
    || TO_CLOB('
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_proiezioni_3",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "title" : "",
      "height": 29,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSION')
    || TO_CLOB('S": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Cat')
    || TO_CLOB('egoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(II) TCS I]}",
            "Sezione Tematica": "{{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, { [Sezione Tematica].[ST_0000] }) }, { [Sezione Tematica].[ST_001] }) }, { [Sezione Tematica].[ST_042] }) }}",
            "Misura Timone.": "{[Misura Timone.].[(')
    || TO_CLOB('II)Timone Categoria Sez]}"
          }
        },
        "FROM": "[Timone Categoria Sezione I]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/RepartoDesc",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated"')
    || TO_CLOB(',
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "D')
    || TO_CLOB('escrizione",
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TG')
    || TO_CLOB('T_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": false,
          "type": ["TM1DataColumnInteger"],
          "cellClass":')
    || TO_CLOB(' "cellClass-right-text"
        },
        "childrenCustomTypes": {
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "w')
    || TO_CLOB('idth": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {},
      "preSelections": [
')
    || TO_CLOB('
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        },
         {
          "dimension": "Compratore",
  ')
    || TO_CLOB('        "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        }
      ],
      "actions": [
        {
          "componentId": "pnf_pro_sposta",
          "componentLabel": "Sposta Articoli",
          "process": "MUI_D')
    || TO_CLOB('UMMY_COPY.Promozione.Articolo+del",
          "timeout": -1,
          "refresh": [],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "toPromo",
            ')
    || TO_CLOB('  "label": "Descrizione Promozione",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "pnf_pro_copia",
          "componentLabel": "Copia Articoli",
          "process": "MUI_DUMMY_COPY.Promozione.Articolo",
          "timeout": -1,
          "refresh": [],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
       ')
    || TO_CLOB('       "label": "Compratore",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "toPromo",
              "label": "Descrizione Promozione",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "pnf_pro_clear",
          "componentLabel": "Clear",
          "process": "MUI_DUMMY_CUB.Promozione Pianificazi')
    || TO_CLOB('one Pulizia Articoli",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_2"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "pCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "pnf_pro_abilita",
          "componentLabel": "Abilita Famiglia"')
    || TO_CLOB(',
          "process": "MUI_DUMMY_CUB.Famiglia.Programmazione Fornitore-Articolo.Caller",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_2"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId')
    || TO_CLOB('": "pnf_pro_inizializza",
          "componentLabel": "Inizializza",
          "process": "MUI_DUMMY_CUB.Pianificazione Panieri.SPF.Caller",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_2"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
      ')
    || TO_CLOB('    ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('17','/Pianificazione/Selezione_Articoli_Contributi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_selezioneArticoliContributi_targetCategoria",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 750000,
      "title" : "Target per categoria",
      "height": 29,

      "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N')
    || TO_CLOB('_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Prom')
    || TO_CLOB('ozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scena')
    || TO_CLOB('rio Timone Pianificazione]}",
            "Misura Timone.": "[Misura Timone.].[MUI_SUB_Selezione_Articoli_Contributi_Timone]"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_RepartoDesc",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Va')
    || TO_CLOB('lue",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": ["TM1Element"],
        "cellClass": "cellClass-left-text"
      },
      "DynamicColumns": ')
    || TO_CLOB('true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
         ')
    || TO_CLOB('   "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 80,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": false,
          "type')
    || TO_CLOB('": ["TM1DataColumnInteger"],
          "cellClass": "cellClass-right-text"
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": ["TM1DataColumnText"],
            "cellClass": "cellClass-left-text",
            "hide": "true"
          },
          "N_FOTO_ULT": {
            "hide": "true"
          },
          "N_FOTO_SCAFFALE": {
            "hide": "true"
          },
          "SPZ_CAMP": {
            "hide": "true"
          },
    ')
    || TO_CLOB('      "MARGINE_LORDO_%": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
		 "D_VEND/RIF_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": ""
		 }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "row')
    || TO_CLOB('Group": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Reparto",
          "field')
    || TO_CLOB('": "Categoria.MUI_RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        }
      ],
       "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName"')
    || TO_CLOB(': "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_selezioneArticoliContributi_targetCategoria","gc_selezioneArticoliContributi_promozione"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizion')
    || TO_CLOB('e",
          "process": "",
          "paramName": "",
          "refresh": ["gc_selezioneArticoliContributi_targetCategoria","gc_selezioneArticoliContributi_promozione"]
        }
      ],
      "styleRules": {},
		"groupRowAggNodes": {
			"nodeGroupTrue": true,
			"nodeGroupFalse": true
		}
    },
    {
      "name": "gc_selezioneArticoliContributi_promozione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "title" : "Pro')
    || TO_CLOB('grammazione Articoli in promozione",
      "height": 29,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [')
    || TO_CLOB('Compratore] )}, 0)}, ASC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Programmazione Fornitore": "{[Misura Programmazione Fornitore].[(I) Programmazione Fornitore Articolo (Dinamico)]}"
          }
        },
    ')
    || TO_CLOB('    "FROM": "[Programmazione Fornitore - Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
          "Attributes/DescrizioneCODICE",
          "Attributes/RepartoDesc",
          "Attributes/CategoriaDesc",
          "Attributes/GRMDesc",
          "Attributes/SubGrmDesc",
          "U')
    || TO_CLOB('niqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
      ')
    || TO_CLOB('    "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "c')
    || TO_CLOB('olumnGroupShow": "always",
          "editable": true,
          "type": ["TM1DataColumnText"],
          "cellClass": "cellClass-left-text"
        },
        "childrenCustomTypes": {
          "D_Promo1": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "D_Promo2": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-righ')
    || TO_CLOB('t-text"
          },
          "CONTR_%_IN_FATT": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "CONTR": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "EXTRA_CONTR": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "CONTR_SP": {
         ')
    || TO_CLOB('   "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "EXTRA_CONTR_SP": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "FF_EC_NC_slash_ND_NO_Abbatt(%)": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "FF_EC_NC_slash_ND_NO_Abbatt(€/pz)": {
            "type": ["TM1DataColumnInteger"],
    ')
    || TO_CLOB('        "cellClass": "cellClass-right-text"
          },
          "FF_C_%": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "EC_%_ABB_FATT": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "E_PZ_SELL_IN": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },')
    || TO_CLOB('
          "FF_EC_%": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "E_PZ_SELL_OUT": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "headerName": ')
    || TO_CLOB('"Articolo",
        "cellClass": "cellClass-left-text",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
   ')
    || TO_CLOB('     {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
')
    || TO_CLOB('
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Articolo(Codice)",
          "field": "Articolo.DescrizioneCODICE",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
     ')
    || TO_CLOB('     "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
    ')
    || TO_CLOB('    {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type"')
    || TO_CLOB(': ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        }
      ],
       "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
       ')
    || TO_CLOB('   "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_selezioneArticoliContributi_targetCategoria","gc_selezioneArticoliContributi_promozione"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumn')
    || TO_CLOB('Name": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_selezioneArticoliContributi_targetCategoria","gc_selezioneArticoliContributi_promozione"]
        }
      ],
      "styleRules": {},
		"groupRowAggNodes": {
			"nodeGroupTrue": true,
			"nodeGroupFalse": true
		}
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('18','/Pianificazione/Selezione_Famiglie_Contributi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_selezioneFamiglieContributi_targetCategoria",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 750000,
      "title": "Target per categoria",
      "height": 29,
      "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCA')
    || TO_CLOB('FFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }" ,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].')
    || TO_CLOB('[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timo')
    || TO_CLOB('ne Pianificazione]}",
            "Misura Timone.": "{[Misura Timone.].[MUI_SUB_Selezione_Articoli_Contributi_Timone]}"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_RepartoDesc",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
')
    || TO_CLOB('
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsS')
    || TO_CLOB('ettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
   ')
    || TO_CLOB('       },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 80,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": false,
          "type": ["TM1DataColumnInteger"],
')
    || TO_CLOB('
          "cellClass": "cellClass-right-text"
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": ["TM1DataColumnText"],
            "cellClass": "cellClass-left-text",
            "hide": "true"
          },
          "N_FOTO_ULT": {
            "hide": "true"
          },
          "N_FOTO_SCAFFALE": {
            "hide": "true"
          },
          "N_FOTO_SCAFFALE_SPEC": {
            "hide": "true"
          },
          "CONTR": {
')
    || TO_CLOB('
            "hide": "true"
          },
          "EXTRA_CONTR": {
            "hide": "true"
          },
          "D_ART_slash_TGT": {
            "hide": "true"
          },
          "D_FOTO_slash_TGT": {
            "hide": "true"
          },
          "MARGINE_LORDO_%": {
            "type": ["TM1DataColumnPercentage"],
            "aggFunc": "weightedAvg",
            "aggFuncParam": "$VENDUTO_PROMO_NETTO"
          },
		 "D_VEND/RIF_%": {
			"type": ["TM1DataColumnPer')
    || TO_CLOB('centage"],
			"columnGroupShow": "always",
           	"aggFunc": ""
		 },
          "SEL": {
            "type": ["TM1DataColumnText"],
            "cellClass": "cellClass-left-text"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
')
    || TO_CLOB('          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.MUI_RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": fa')
    || TO_CLOB('lse,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_sele')
    || TO_CLOB('zioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_selezioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContri')
    || TO_CLOB('buti_promozione"]
        }
      ],
      "styleRules": {},
       "groupRowAggNodes": {
			"nodeGroupTrue": "true",
			"nodeGroupFalse": "true"
		}
    },
    {
      "name": "gc_selezioneFamiglieContributi_promozione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "title": "Programmazione Famiglie in promozione - definizione contributi",
      "height": 29,

      "MDX": {
        "ROWS": {
          "NON_EMPTY":')
    || TO_CLOB(' true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, A')
    || TO_CLOB('SC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Programmazione Fornitore": "{[Misura Programmazione Fornitore].[(I) Programmazione Fornitore Articolo (Dinamico)]}"
          }
        },
        "FROM": "[Programmazione Fornitore - Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Version')
    || TO_CLOB('e": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Famiglia",
          "Attributes/MUI_Descrizione",
          "Attributes/DescrizioneCODICE",
          "Attributes/RepartoDesc",
          "Attributes/CategoriaDesc",
          "Attributes/GRMDesc",
          "Attributes/SubGrmDesc",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
')
    || TO_CLOB('          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "col')
    || TO_CLOB('umnGroupShow": "always",
          "editable": true,
          "type": ["TM1DataColumnText"],
          "cellClass": "cellClass-left-text"
        },
        "childrenCustomTypes": {
          "D_Promo1": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "D_Promo2": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellCl')
    || TO_CLOB('ass-right-text"
          },
          "CONTR_%_IN_FATT": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "CONTR": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "EXTRA_CONTR": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "CONTR_SP": {
 ')
    || TO_CLOB('           "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "EXTRA_CONTR_SP": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "FF_EC_NC_slash_ND_NO_Abbatt(%)": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "FF_EC_NC_slash_ND_NO_Abbatt(€/pz)": {
            "type": ["TM1DataColumnInteger"')
    || TO_CLOB('],
            "cellClass": "cellClass-right-text"
          },
          "FF_C_%": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "EC_%_ABB_FATT": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "E_PZ_SELL_IN": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
    ')
    || TO_CLOB('      },
          "FF_EC_%": {
            "type": ["TM1DataColumnInteger"],
            "aggFunc": "avg",
            "cellClass": "cellClass-right-text"
          },
          "E_PZ_SELL_OUT": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "heade')
    || TO_CLOB('rName": "Articolo",
        "width": 400,
        "pinned": "left",
         "type": ["TM1Element"],
         "cellClass": "cellClass-left-text"
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
           "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
   ')
    || TO_CLOB('       "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "t')
    || TO_CLOB('ype": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Famiglia",
          "field": "Articolo.MUI_Famiglia",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Articolo (Codice)",
          "field": "Articolo.DescrizioneCODICE",
          "width"')
    || TO_CLOB(': 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
       ')
    || TO_CLOB('   "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1El')
    || TO_CLOB('ement"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",
          "width": 100,
          "hide": tru')
    || TO_CLOB('e,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        }
      ],
       "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPia')
    || TO_CLOB('nificazione",
          "paramName": "inPromo",
          "refresh": ["gc_selezioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh')
    || TO_CLOB('": ["gc_selezioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"]
        }
      ],
      "styleRules": {},
		"groupRowAggNodes": {
			"nodeGroupTrue": true,
			"nodeGroupFalse": true
		}
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('19','/Pianificazione/Selezione_Promo',TO_CLOB('  {
  "connection" : "promo" ,
  "configurations": [
    {
      "name": "gc_listinoContributi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 750000,
	  "title" : "Listino Contributi",
      "height": 29,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Tipo Promozione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
            "Promozione": "{')
    || TO_CLOB('FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Prestazione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
            "Misura Prestazioni": "{[Misura Prestazi')
    || TO_CLOB('oni].[listino] }",
            "Contratto": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Promozione Pianificazione Listino(I)]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consoli')
    || TO_CLOB('dated" ,
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Prestazione.Descrizione"
      , "headerName": "Prestazione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Descrizione","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},')
    || TO_CLOB('
        {"headerName":"Promozione","field":"Promozione.MUI_Descrizione","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},

        {"headerName":"Listino",
          "marryChildren":true,
          "children" :
          [
            {"headerName":"Extra Contratto","field":"MisuraPrestazioni$listino$$Contratto$ExtraContratto","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger"],"cellClass": "cellClass-right-text')
    || TO_CLOB('"} ,
            {"headerName":"Contratto","field":"MisuraPrestazioni$listino$$Contratto$Contratto_1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger"],"cellClass": "cellClass-right-text"}
          ]
        }


      ]
    ,"preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
')
    || TO_CLOB('
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_listinoContributi","gc_timing"]
        }
      ],
      "styleRules":{		},
		"groupRowAggNodes": {
			"nodeGroupTrue": true,
			"nodeGroupFalse": true
		}
    },

    {
      "name": "gc_timing",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
"title": "Ti')
    || TO_CLOB('ming",
      "height": 29,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Versione": "{[Versione].[UFF]}" ,
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
          }
        },
        "COLS": {
          "')
    || TO_CLOB('NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà": "{[Misura Promozione Proprietà].[Misura 1]}"
          }
        },
        "FROM": "[Configurazione Promozione - Proprietà]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/Riferimento",
          "UniqueN')
    || TO_CLOB('ame"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione"
      , "cellClass": "cellClass-left-text"
      , "width":400
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "colum')
    || TO_CLOB('nDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellCl')
    || TO_CLOB('ass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":150,"hide":false,"rowGroup": false , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_agrave_$Data_I')
    || TO_CLOB('nizio_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":100,"hide":false,"rowGroup": false ')
    || TO_CLOB(', "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Freschi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFor')
    || TO_CLOB('mat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
     ')
    || TO_CLOB('   {"headerName":"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":100,"hide":false,"rowGroup": false , "editable": false,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},

        {"headerName":"Data Scadenza Conferma Prezzi","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"he')
    || TO_CLOB('aderName":"Pianificazione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Volantino/Piano Media","field":"MisuraPromozionePropriet_agrave_$resp_mktg2","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","width":100,"hide":false,"rowGrou')
    || TO_CLOB('p": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", ')
    || TO_CLOB('"type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"hea')
    || TO_CLOB('derName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}

      ]
    ,"preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": ')
    || TO_CLOB('"MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_listinoContributi","gc_timing"]
        }
      ],
      "styleRules":{		},
		"groupRowAggNodes": {
			"nodeGroupTrue": true,
			"nodeGroupFalse": true
		}
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('20','/Piano_Annuale/Controllo_Negozi',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_ControlloNegozi",
			"logMemory": true,
			"logTime": true,
			"skip": true,
      "MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
						"Reparto": "{{TM1SORT( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)}, ASC)}}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
				')
    || TO_CLOB('		"Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
						"Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
					}
				},
				"FROM": "[Timone Reparto]",
				"WHERE": {
					"Versione": "[Ufficiale]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/Descrizione",
                    "Attributes/Canale",
                    "Attributes/Anno')
    || TO_CLOB('",
                    "Attributes/MUI_Semestre",
                    "Attributes/MUI_Descrizione",
                    "Attributes/Riferimento",
                    "Attributes/TipoElemento",
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
			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "Reparto.Descrizione"')
    || TO_CLOB(',
				"headerName": "Reparto",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Canale",
					"field": "Promozione.Canale",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"edita')
    || TO_CLOB('ble": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.Descrizione",
					"width": 400,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
				')
    || TO_CLOB('	]
				},
				{
					"headerName": "Descrizione + Data",
					"field": "Promozione.MUI_Descrizione",
					"width": 400,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Reparto",
					"field": "Reparto.Descrizione",
					"width": 120,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferime')
    || TO_CLOB('nto",
					"field": "Promozione.Riferimento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Tipo Elemento",
					"field": "Reparto.TipoElemento",
					"width": 70,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferimento Data",
					"openByDefault": true,
					"child')
    || TO_CLOB('ren": [
						{
							"headerName": "N. Art",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
							"width"')
    || TO_CLOB(': 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable":')
    || TO_CLOB(' true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Banco",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"he')
    || TO_CLOB('aderName": "N. Foto Speciali",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"w')
    || TO_CLOB('idth": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Ultima",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
				')
    || TO_CLOB('			"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Venduto Promo (s/iva)",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
			')
    || TO_CLOB('			},
						{
							"headerName": "ML %",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnPercentage",
								"numericColumn"
							]
						}
					]
				},
				{
					"headerName": "Target MKT",
					"headerClass": "headerMkt",
					"openByDefault": true,')
    || TO_CLOB('
					"children": [
						{
							"headerName": "N. Art",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
							')
    || TO_CLOB('"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editab')
    || TO_CLOB('le": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Banco",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"h')
    || TO_CLOB('eaderName": "N. Foto Speciali",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width"')
    || TO_CLOB(': 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Ultima",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"edit')
    || TO_CLOB('able": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						}
					]
				},
				{
					"headerName": "Target REP",
					"headerClass": "headerRep",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N. Art",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"')
    || TO_CLOB('editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerN')
    || TO_CLOB('ame": "N. Foto",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Banco",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
	')
    || TO_CLOB('						"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Speciali",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type":')
    || TO_CLOB(' [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N.')
    || TO_CLOB(' Foto Ultima",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto/tgt Mkt",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
							"width": 80,
							"hide": f')
    || TO_CLOB('alse,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto Banco/tgt Mkt",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"edita')
    || TO_CLOB('ble": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						}
					]
				},
				{
					"headerName": "Target Acq.",
					"headerClass": "headerAcq",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N. Art",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"e')
    || TO_CLOB('ditable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerNam')
    || TO_CLOB('e": "N. Foto",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Banco",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": true,
					')
    || TO_CLOB('		"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Speciali",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
	')
    || TO_CLOB('							"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Spec. Banco",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto ')
    || TO_CLOB('Ultima",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto/tgt Rep",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
							"width": 80,
							"hide": true,
	')
    || TO_CLOB('						"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto Banco/tgt Rep",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
							"width": 80,
							"hide": true,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": tru')
    || TO_CLOB('e,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						}
					]
				}
			]
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('21','/Piano_Annuale/Crea_Promozione',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_CreaPromozione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
          "title":"Crea Nuove Promozioni",
          "height": 60,
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "}Clients": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, ASC)}}",
                      "Anno": "{{TM1SORT( {TM1FILTERBYL')
    || TO_CLOB('EVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
                      "ID": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"
                  }
              },
              "COLS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "MisuraPianoOperativoCommerciale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
                  }
              },
              "FROM"')
    || TO_CLOB(': "[Promozione Creazione]"

          },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
                        "Attributes/Name",
                        "Attributes/MUI_Client",
                        "UniqueName"
                              ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
           ')
    || TO_CLOB('             "HasPicklist",
                        "PicklistValues"
                      ]
                    },

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "ID.Name"
                                 , "headerName": "ID"
                                 , "width":200
                                 , "pinned": "left"
                                 , "type":["TM1El')
    || TO_CLOB('ement"]
                          } ,
        "columnDefs": [
              {"headerName":"Anno","field":"Anno.Name","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
              {"headerName":"Client","field":"_cbrace_Clients.MUI_Client","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"ID","field":"ID.Name","width":80,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]')
    || TO_CLOB('},
              {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Desc","field":"MisuraPianoOperativoCommerciale$Desc","width":500,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Gruppo","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":120,"hide":false,"row')
    || TO_CLOB('Group": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Sottogruppo","field":"MisuraPianoOperativoCommerciale$Canale","width":120,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
        ')
    || TO_CLOB('      {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
              {"headerName')
    || TO_CLOB('":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
              ],
          "actions": [{
            "componentId": "pia_cp_crea",
     ')
    || TO_CLOB('       "componentLabel": "Crea Promozione",
            "process": "MUI_DUMMY_DIM.Promozione.NewPromo",
            "timeout":-1,
            "refresh": ["gc_CreaPromozione"],
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            }]
          }]
      }
     ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('22','/Piano_Annuale/Foto',TO_CLOB('{
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Foto_foto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
      "maxCells": 2000000,
      "title": "Foto",
      "height": 22,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Raggruppamento Foto": "{{TM1SORTBYINDEX')
    || TO_CLOB('({{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "}ElementAttributes_Raggruppamento Foto": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Raggruppamento Foto] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[}ElementAttributes_Raggruppamento Foto]",
        "WHERE": {
        }
      },
      "ExecuteMDX": {
     ')
    || TO_CLOB('   "Members": [
          "Name",
          "UniqueName",
          "Attributes/MUI_TOTS",
          "Attributes/MUI_Descrizione",
          "Attributes/MUI_Compratore",
          "Attributes/MUI_Reparto"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {},
      "columnDefs": [
        {
          "headerName": "Reparto",
          "fi')
    || TO_CLOB('eld": "RaggruppamentoFoto.MUI_Reparto",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "RaggruppamentoFoto.MUI_Compratore",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]')
    || TO_CLOB('
        },
        {
          "headerName": "Raggruppamento Foto",
          "field": "RaggruppamentoFoto.MUI_TOTS",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Foto",
          "field": "RaggruppamentoFoto.MUI_Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          ')
    || TO_CLOB('"editable": false,
          "type": [
            "TM1Element"
          ]
        }

      ],
      "selections": [
        {
          "source": {
            "table": "gc_Foto_foto",
            "dimension": "RaggruppamentoFoto",
            "attribute": "MUI_Descrizione"
          },
          "destination": [
            {
              "table": "gc_Foto_foto",
              "dimension": "RaggruppamentoFoto",
              "attribute": "MUI_Descrizione"
            }
  ')
    || TO_CLOB('        ]
        }
      ]
    },
    {
      "name": "gc_Foto_compratore",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
      "maxCells": 2000000,
      "title": "Compratore",
      "height": 22,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{ORDER ( {EXCEPT ( { TM1F')
    || TO_CLOB('ILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , { {[Compratore].[S000] }, {[Compratore].[NA] }})} , [Compratore].[MUI_Sort] , BASC )}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "}ElementAttributes_Compratore": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Compratore] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[}ElementAttributes_Compratore]",
        "WHERE": {
        ')
    || TO_CLOB('}
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/CategoryManager",
          "Attributes/MUI_Reparto",
          "Attributes/MUI_Descrizione",
          "Attributes/Descrizione",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {},
      "columnDefs": [
        {
    ')
    || TO_CLOB('      "headerName": "Category Manager",
          "field": "Compratore.CategoryManager",
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Compratore.MUI_Reparto",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
 ')
    || TO_CLOB('           "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "selections": [
        {
          "source": {
            "table": "gc_Foto_compratore",
            "dimension": "Compratore",
            "attribut')
    || TO_CLOB('e": "Descrizione"
          },
          "destination": [
            {
              "table": "gc_Foto_compratore",
              "dimension": "Compratore",
              "attribute": "Descrizione"
            }
          ]
        }
      ]
    },
    {
      "name": "gc_Foto_associazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000,
      "maxCellsMessage": "Aggiungere il filtro sulla Promozione.",
      "rowSuppressionEnab')
    || TO_CLOB('led": false,
      "colSuppressionEnabled": false,
      "title": "Condivisione foto",
      "height": 28,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Raggruppamento Foto": "{{TM1SORTBYINDEX({FILTER( {{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}}, [Raggruppamento Foto].[MUI_NRFOTO] <= 30.000000)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
       ')
    || TO_CLOB('     "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
            "Compratore": "{{ [Compratore].[TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
          }
        },
   ')
    || TO_CLOB('     "FROM": "[Configurazione RaggrFoto]",
        "WHERE": {
          "Misura Config RaggrFoto": "[OK]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_DescrizioneData",
          "Attributes/MUI_TOT",
          "Attributes/MUI_TOTS",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updatea')
    || TO_CLOB('ble",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "MUI_DescrizioneData",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
           ')
    || TO_CLOB(' "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "ty')
    || TO_CLOB('pe": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "cellClass": "cellClass-left-text",
        "field": "RaggruppamentoFoto.Descrizione",
        "headerName": "Raggruppamento Foto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
 ')
    || TO_CLOB('       ]
      },
      "columnDefs": [
        {
          "headerName": "Tot Foto",
          "field": "RaggruppamentoFoto.MUI_TOT",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tot Foto by",
          "field": "RaggruppamentoFoto.MUI_TOTS",
          "width": 70,
          "hide": true,
          "rowGroup": true,
')
    || TO_CLOB('
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_Foto_associazione"]
        }')
    || TO_CLOB('
      ],
      "actions": [
        {
          "componentId": "pia_fo_condividi",
          "componentLabel": "Condividi",
          "process": "MUI_DUMMY_CUB.ConfigurazioneRaggrFoto",
          "timeout": -1,
          "refresh": [
            "gc_Foto_associazione"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
  ')
    || TO_CLOB('            "hasPicklist": false,
              "disabled": true,
              "visible": true
            },
            {
              "dimension": "RaggruppamentoFoto",
              "attribute": "MUI_Descrizione",
              "paramName": "inFoto",
              "label": "Foto",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            },
            {
              "dimension": "Compratore",
              "attribute": "D')
    || TO_CLOB('escrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            }
          ]
        }
      ]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('23','/Piano_Annuale/Gabbia',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Gabbia",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title": "Gabbia",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozion')
    || TO_CLOB('e].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} "
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Piano Operativo Commerciale": "{[Misura Piano Operativo Commerciale].[Costo_punto_Fragola]}"
          }
        },
        "FROM": "[Promozione Aggiornamento]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Canale"')
    || TO_CLOB(',
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione"
      , "width":300
')
    || TO_CLOB('
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": fa')
    || TO_CLOB('lse,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Valore Punto Fragola","field":"MisuraPianoOperativoCommerciale$Costo_punto_Fragola","width":150,"hide":false,"rowGroup": fals')
    || TO_CLOB('e , "editable": true,"type":[ "TM1DataColumnDecimal3" ,"numericColumn"]}
      ]
    },
    {
      "name": "gc_GabbiaPunti",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title": "Gabbia Punti",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "ID": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY')
    || TO_CLOB('": false,
          "DIMENSIONS": {
            "Misura Gabbia Punti Fragola": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Gabbia Punti Fragola] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Gabbia Punti Fragola]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Name",
		  "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
      ')
    || TO_CLOB('    "HasPicklist"
        ]
      },



      "autoGroupColumnDef" :  {

      } ,
      "columnDefs": [
        {"headerName":"ID","field":"ID.Name","width":100,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Prezzo Minimo","field":"MisuraGabbiaPuntiFragola$PrezzoMinimo","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Prezzo Massimo","field":"Misu')
    || TO_CLOB('raGabbiaPuntiFragola$PrezzoMassimo","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Punti Fragola","field":"MisuraGabbiaPuntiFragola$PuntiFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]}
      ],
      "actions": [{
            "componentId": "pia_ga_modifica",
            "componentLabel": "Modifica Valore Punti Fragola",
         ')
    || TO_CLOB('   "process": "MUI_DUMMY_DIM.Cambio valore Costo Punti Fragola",
            "timeout":-1,
            "refresh": ["gc_Gabbia","gc_GabbiaPunti"],
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramNam')
    || TO_CLOB('e": "inPromo",
              "label": "Descrizione",
              "hasPicklist": true
            }]
          }]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('24','/Piano_Annuale/Gestione_Contributi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_GestioneContributi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title": "Listino Contributi",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Ca')
    || TO_CLOB('nale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')} ",
            "Tipo Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Contratto": "{[Contratto].[Extra Contratto],[Contratto].[Contratto_1] }",
            "Prestazione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
')
    || TO_CLOB('
            "Misura Prestazioni": "[Misura Prestazioni].[Listino]"
          }
        },
        "FROM": "[Promozione Modifica - Listino]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Caption_Default",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
   ')
    || TO_CLOB('       "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione" ,"Descrizione" ,"Descrizione"   ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "hea')
    || TO_CLOB('derAcq",
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
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "avg", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "ch')
    || TO_CLOB('ildrenCustomTypes"  : {
          "DataInizio":{"cellClass": "dateFormat","type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
          "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione')
    || TO_CLOB('"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Caption_Default","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":')
    || TO_CLOB('true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":400,"hide":true,"rowGroup": false , "')
    || TO_CLOB('editable": false,"type":["TM1Element"]}
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue" : "false",
        "nodeGroupFalse" : "false"
      }
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('25','/Piano_Annuale/Gestione_Iniziative',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_GestioneIniziative",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title": "Configurazione Iniziative",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozi')
    || TO_CLOB('one].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Iniziativa": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Iniziative": "{[Misura Configurazione Iniziative].[OK] }"
          }
        },
        "FROM": "[Configurazione Promozione - Iniziative]",
        ')
    || TO_CLOB('"WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
        ')
    || TO_CLOB('  "HasPicklist"
        ]
      },



      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Iniziativa.Descrizione"
      , "headerName": "Iniziativa"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","fi')
    || TO_CLOB('eld":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","wid')
    || TO_CLOB('th":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"OK","field":"MisuraConfigurazioneIniziative$OK","width":70,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnInteger" ,"numericColumn"]}
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue" : "false",
        "nodeGroupFalse" : "false"
      }

    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('26','/Piano_Annuale/Meccaniche',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Meccaniche",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title": "Configurazione Meccaniche Promozionali",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione": "{[Misura Configurazione Promozione].[SEL]}" ,
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FI')
    || TO_CLOB('LTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Meccanica Complesse": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Meccanica Complesse] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Meccanic')
    || TO_CLOB('he]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "autoGroupColumnDef" :  {
       ')
    || TO_CLOB(' "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text", "type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":60,"hide":t')
    || TO_CLOB('rue,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"heade')
    || TO_CLOB('rName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},

        {
        "headerName": "Meccaniche Semplici",
        "openByDefault": true,
        "children": [

        {"headerName":"Sconto %","field":"MeccanicaComplesse$M002","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},')
    || TO_CLOB('
        {"headerName":"Prezzo Corto","field":"MeccanicaComplesse$M003","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"P. Fragola","field":"MeccanicaComplesse$M004","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc % Fidaty","field":"MeccanicaComplesse$M005","width":110,"hide":false')
    || TO_CLOB(',"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Prezzo Corto & P.Fragola","field":"MeccanicaComplesse$M006","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc% Fidaty  &  P. Fragola","field":"MeccanicaComplesse$M007","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center')
    || TO_CLOB('-text","type":["TM1DataColumnText"]},
        {"headerName":"3x2","field":"MeccanicaComplesse$M008","width":50,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"1 + 1","field":"MeccanicaComplesse$M009","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Il 2 costa la metà ","field":"MeccanicaComplesse$')
    || TO_CLOB('M010","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto % + Punti Fragola","field":"MeccanicaComplesse$M012","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc + Facile val","field":"MeccanicaComplesse$M014","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass')
    || TO_CLOB('": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Oggetti articolo","field":"MeccanicaComplesse$M015","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono sc.Fidaty (F11)","field":"MeccanicaComplesse$M018","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":')
    || TO_CLOB('"Sc Val Fidaty","field":"MeccanicaComplesse$M023","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto fidaty per classe cliente (%)","field":"MeccanicaComplesse$M024","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"PC + Triplica Punti","field":"MeccanicaComplesse$M025","width":110,"')
    || TO_CLOB('hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Punti Fragola su insieme","field":"MeccanicaComplesse$M104","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Triplica i punti","field":"MeccanicaComplesse$M111","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-cent')
    || TO_CLOB('er-text","type":["TM1DataColumnText"]},
        {"headerName":"Punti Fragola Set per Triplica Punti","field":"MeccanicaComplesse$M164","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sconto a valore","field":"MeccanicaComplesse$M031","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
 ')
    || TO_CLOB('       {"headerName":"Buono Sconto Categoria - punti fragola","field":"MeccanicaComplesse$M034","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sconto percentuale","field":"MeccanicaComplesse$M035","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Ca')
    || TO_CLOB('tegoria - sc val - Set semplice","field":"MeccanicaComplesse$M131","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - PF - Set semplice","field":"MeccanicaComplesse$M134","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sc % - Set semplice"')
    || TO_CLOB(',"field":"MeccanicaComplesse$M135","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Semplice per Triplica Oggetti","field":"MeccanicaComplesse$M165","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]} ,
        {"headerName":"PAGINA PUBBLICITARIA","field":"MeccanicaComplesse$M000","width":110,"hide":false,"row')
    || TO_CLOB('Group": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Meccanica NO Promo con Spazio","field":"MeccanicaComplesse$M090","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}

        ]} ,

        {
        "headerName": "Meccaniche Complesse",
        "openByDefault": true,
        "children": [

        {"headerName":"SSc + Facile Set","fie')
    || TO_CLOB('ld":"MeccanicaComplesse$M114","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Oggetti Set","field":"MeccanicaComplesse$M115","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto SET a valore","field":"MeccanicaComplesse$M201","width":110,"hide":false,"rowGroup": false , "editable": t')
    || TO_CLOB('rue, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto SET %","field":"MeccanicaComplesse$M205","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Set Esteso Valore","field":"MeccanicaComplesse$M301","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        ')
    || TO_CLOB('{"headerName":"Set Esteso - prezzo corto","field":"MeccanicaComplesse$M303","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Esteso Punti Fragola","field":"MeccanicaComplesse$M304","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Percentuale Set Esteso","field":"MeccanicaCompl')
    || TO_CLOB('esse$M305","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}

         ]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('27','/Piano_Annuale/Modifica_Promozione',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_ModificaPromozione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
          "title":"Modifica Promozione",
          "height": 60,
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": true,
                  "DIMENSIONS": {
                    "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC')
    || TO_CLOB(')}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
                  }
              },
              "COLS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "MisuraPianoOperativoCommerciale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
                  }
              },
              "FROM": "[Promozione Aggiornamento]"
  ')
    || TO_CLOB('        },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
                        "Attributes/MUI_Descrizione",
                       "Attributes/Anno",
                       "Attributes/Canale",
                       "Attributes/Descrizione",
                       "Attributes/Riferimento",
                       "Attributes/MUI_Semestre",
                        "UniqueName"
                              ] ,
                      "Cells')
    || TO_CLOB('": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist"
                      ]
                    } ,
        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Promozione.MUI_Descrizione"
                                 , "headerName": "Promozione"
')
    || TO_CLOB('
                                 , "width":300
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
              {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
              {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1E')
    || TO_CLOB('lement"]} ,
              {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Descrizione","field":"Promozione.Descrizione","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Elem')
    || TO_CLOB('ent"]},
              {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":85,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":90,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Descrizione","field":"MisuraPianoOperativoCommerciale$Descrizione","width":300,"hide":false,"rowGroup": false , ')
    || TO_CLOB('"editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Descrizione.","field":"MisuraPianoOperativoCommerciale$Desc_Agg","width":300,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"header')
    || TO_CLOB('Name":"Data Inizio.","field":"MisuraPianoOperativoCommerciale$DataInizio_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine.","field":"M')
    || TO_CLOB('isuraPianoOperativoCommerciale$DataFine_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist","field":"MisuraPian')
    || TO_CLOB('oOperativoCommerciale$DataInizioIst_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist","field":"MisuraPianoOperativo')
    || TO_CLOB('Commerciale$DataFineIst_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Abbatt Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":200,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Abbatt Prezzi_","field":"MisuraPianoOperativoCommerci')
    || TO_CLOB('ale$DATA_ABB_PRZ_AGG","width":200,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Ordine Timone.","field":"MisuraPianoOperativoCommerciale$Ordinamento_Agg","width":200,"hide":false,"rowGroup": fals')
    || TO_CLOB('e , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa %_","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc__AGG","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa Eti')
    || TO_CLOB('c","field":"MisuraPianoOperativoCommerciale$NO_STMP_ETICH","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"CanalePromozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"StatoPromozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":150,"hide":false,"rowGroup": false , "editab')
    || TO_CLOB('le": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"TipoPromozione","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note","field":"MisuraPianoOperativoCommerciale$Note","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommer')
    || TO_CLOB('ciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}

            ],
            "actions": [{
            "componentId": "pia_mp_aggiorna",
            "componentLabel": "Aggiorna Promozione",
            "process": "MUI_DUMMY_DIM.Promozione.UpdatePromo (I)",
            "timeout":-1,
            "refresh": ["gc_ModificaPromozione"],
            "params":[{
              "dimension": "Promozione",
              "attribute"')
    || TO_CLOB(': "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            }]
          }]
        }
     ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('28','/Piano_Annuale/Negozi_Promo',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_NegoziPromo",
			"logMemory": true,
			"logTime": true,
			"skip": true,
          "title": "Definizione Negozi Promozione",
          "height": 60,
			 "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "Negozio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
                  }
              },
              "')
    || TO_CLOB('COLS": {
                  "NON_EMPTY": true,
                  "DIMENSIONS": {
                    "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
                    "Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}" ,
                ')
    || TO_CLOB('    "Canale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Canale] )}, 0)}, ASC)}}",
                    "Misura Canale": "{ [Misura Canale].[Tipo_Neg_Calc], [Misura Canale].[Tipo_Neg_Input], [Misura Canale].[Canale], [Misura Canale].[DataInizio], [Misura Canale].[DataFine],[Misura Canale].[Delta] }"



                  }
              },
              "FROM": "[Canale Neg SezTematica]"

          },
        "ExecuteMDX": {
                     "Members": [
                       ')
    || TO_CLOB(' "Name",
                        "Attributes/Descrizione",
                        "Attributes/Canale",
                        "Attributes/Anno",
                        "Attributes/MUI_Semestre",
                        "Attributes/MUI_Descrizione",
                        "Attributes/MUI_ZonaPromo",
                        "Attributes/MUI_DescrizioneData",
                        "UniqueName"
                              ] ,
                      "Cells": [
')
    || TO_CLOB('                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist"
                      ]
                    },

          "DynamicColumns" : true ,
          "DynamicColumnsSettings" :{
              "headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" , "Descrizione"]  ,
              "headerdefaults":  {"marryChildren" : true}  ,
            "he')
    || TO_CLOB('aderCustomTypes":{
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
       ')
    || TO_CLOB('     },
              "childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnText"] },
              "childrenCustomTypes"  : {
                                   "DataInizio":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
                                   "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"')
    || TO_CLOB(' ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"}
                               }
              } ,

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Negozio.Descrizione"
                                 , "headerName": "Negozio"
                                 , "width":300
                                 , "pinned": "left"
                                 , "')
    || TO_CLOB('type":["TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":80,"hide":true,"rowGroup": true , "editable": false,"typ')
    || TO_CLOB('e":["TM1Element"]},
          {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":200,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
          {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Negozio","field":"Negozio.Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}



 ')
    || TO_CLOB('             ]
            ,
			"rowSuppressionEnabled": true,
			"colSuppressionEnabled": false
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('29','/Piano_Annuale/Timing',TO_CLOB('{
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Timing",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title":"Timing",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Versione": "{[Versione].[UFF]}" ,
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDES')
    || TO_CLOB('C)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà ": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Promozione Proprietà] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Proprietà]"

      },
      "ExecuteMDX": {
        "Members": [
         ')
    || TO_CLOB(' "Name",
          "Attributes/Canale",
          "Attributes/MUI_Descrizione",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/Riferimento",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

        "autoGroupColumnDef" :  {
                                   "')
    || TO_CLOB('cellRendererParams":{ "suppressCount": true }
                                 , "field": "Promozione.MUI_Descrizione"
                                 , "headerName": "Promozione"
                                 , "width":500
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true ')
    || TO_CLOB(', "editable": false,"type":["TM1Element"]},
          {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Riferimento","field":"Promozione.Riferimento","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
')
    || TO_CLOB('          {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Las","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":110')
    || TO_CLOB(',"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Freschi","width":110,"hide":false,"rowGroup": false , "editabl')
    || TO_CLOB('e": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFor')
    || TO_CLOB('mat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":150,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnD')
    || TO_CLOB('ate"]},
          {"headerName":"Data Scadenza Conferma Prezzi","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":150,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Pianificazione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Volantino/Piano Me')
    || TO_CLOB('dia","field":"MisuraPromozionePropriet_agrave_$resp_mktg2","width":120,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":110,"hide":false,"rowGroup": false , ')
    || TO_CLOB('"editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass"')
    || TO_CLOB(': "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}

      ]
    }
  ]')
    || TO_CLOB('
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('30','/Piano_Annuale/Visualizza_Piano',TO_CLOB(' {
  "connection": "promo",

  "configurations": [
    {
      "name": "gc_VisualizzaPiano",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "pagination": false ,
      "title" : "Riepilogo Promozioni",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist')
    || TO_CLOB('.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}   "
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPianoOperativoCommerciale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Promozione Aggiornamento]"
      },
      "ExecuteMDX": {
        "Members": [
 ')
    || TO_CLOB('         "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Anno",
          "Attributes/Canale",
          "Attributes/Descrizione",
          "Attributes/Riferimento",
          "Attributes/MUI_Semestre",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      } ,
      "autoGroupColumnDef" :  {
        "cellRendererParam')
    || TO_CLOB('s":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.Anno","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Elem')
    || TO_CLOB('ent"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"he')
    || TO_CLOB('aderName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , ')
    || TO_CLOB('"editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true')
    || TO_CLOB(',  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"')
    || TO_CLOB(']},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoCommerciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide"')
    || TO_CLOB(':false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('31','/Piano_Annuale/Zone_Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_ZonePromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title":"Abilitazione delle zone promozionali",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione": "{[Misura Configurazione Promozione].[SEL]}" ,
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTER')
    || TO_CLOB('BYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Meccanica Complesse": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Zone]"

      },
 ')
    || TO_CLOB('     "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
          "Attributes/Anno",
          "Attributes/Canale",
          "Attributes/MUI_Semestre",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParam')
    || TO_CLOB('s":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Ele')
    || TO_CLOB('ment"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]} ,
        {"head')
    || TO_CLOB('erName":"Zona BDG","field":"ZonaPromo$ZONAPROMO_ALL","width":90,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO","field":"ZonaPromo$ZONAPROMO_1_01","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_02] EMILIA","field":"ZonaPromo$ZONAPROMO_1_02","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"s')
    || TO_CLOB('um", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"ZonaPromo$ZONAPROMO_1_03","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_04] VERONA","field":"ZonaPromo$ZONAPROMO_1_04","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"ZonaPromo$ZONAPROMO_2_01","width":12')
    || TO_CLOB('5,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_02] LAZIO","field":"ZonaPromo$ZONAPROMO_2_02","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]}

      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('32','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche',TO_CLOB('{
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SezioniTematiche_SezioniTematiche",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title": "Associazione sezione tematica e campagna",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Iniz')
    || TO_CLOB('io_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Pubblicità": "{[Pubblicità].[(I) Volantino]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Zona Promo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Zona Promo] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Sezione Tematica]"

 ')
    || TO_CLOB('     },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/MUI_Descrizione",
          "Attributes/Descrizione",

          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellR')
    || TO_CLOB('endererParams":{ "suppressCount": true }
      , "field": "Pubblicit_agrave_.Descrizione"
      , "headerName": ""
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":50,"hide":true,"rowGroup": true , "editable": false,"type":["TM')
    || TO_CLOB('1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":100,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"head')
    || TO_CLOB('erName":"Descrizione Sezione Tematica","field":"MisuraZonaPromo$Descrizione","width":170,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Canale","field":"MisuraZonaPromo$Canale","width":70,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Agg_canale","field":"MisuraZonaPromo$Agg_canale","width":90,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
       ')
    || TO_CLOB(' {"headerName":"DataInizio","field":"MisuraZonaPromo$DataInizio","width":80,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"DataFine","field":"MisuraZonaPromo$DataFine","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Zona BDG","field":"MisuraZonaPromo$ZONAPROMO_ALL","width":70,"hide":false,"rowGroup": false , "editabl')
    || TO_CLOB('e": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO","field":"MisuraZonaPromo$ZONAPROMO_1_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_02] EMILIA","field":"MisuraZonaPromo$ZONAPROMO_1_02","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"Misura')
    || TO_CLOB('ZonaPromo$ZONAPROMO_1_03","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_04] VERONA","field":"MisuraZonaPromo$ZONAPROMO_1_04","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"MisuraZonaPromo$ZONAPROMO_2_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM')
    || TO_CLOB('1DataColumnInteger"]},
        {"headerName":"[2_02] LAZIO","field":"MisuraZonaPromo$ZONAPROMO_2_02","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"Check","field":"MisuraZonaPromo$Check","width":70,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]}

      ]
    }
  ]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('33','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_SezioniTematiche_CreaSezioniTematiche",
			"logMemory": true,
			"logTime": true,
			"skip": true,
          "title": "Definizione Sezioni Tematiche",
          "height": 60,
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "ID Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}"
  ')
    || TO_CLOB('                }
              },
              "COLS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "Misura Configurazione Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Configurazione Sezione Tematica] )}, 0)}, ASC)}}"
                  }
              },
              "FROM": "[Configurazione Sezione Tematica]"

          },
        "ExecuteMDX": {
                     "Members": [
                     ')
    || TO_CLOB('   "Name",
                        "Attributes/Name",
						"UniqueName"
                              ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist"
                      ]
                    },

        "autoGroupColumnDef" :  {   } ,
        "columnDefs": [
          {"headerName":"ID Sezione Tematica","f')
    || TO_CLOB('ield":"IDSezioneTematica.Name","width":100,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione","field":"MisuraConfigurazioneSezioneTematica$Descrizione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Tipo","field":"MisuraConfigurazioneSezioneTematica$TIPO","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"hea')
    || TO_CLOB('derName":"Note","field":"MisuraConfigurazioneSezioneTematica$Note","width":170,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Operazione","field":"MisuraConfigurazioneSezioneTematica$Operazione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]}



              ],
          "actions": [{
            "componentId": "pia_st_aggiorna",
            "componentLabel": "Aggiorna Anagrafica Sezio')
    || TO_CLOB('ni Tematiche",
            "process": "MUI_DUMMY_DIM.Sezione Tematica",
            "timeout":-1,
            "refresh": ["gc_SezioniTematiche_CreaSezioniTematiche"],
            "params":[{
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": false
            }]
          }]
        }
     ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('34','/Piano_Annuale/Spazi/Macrospazi_Listino_Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacrospaziListinoPromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "title": "Configurazione Macrospazi Listino Promo",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}"
    ')
    || TO_CLOB('      }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Misura_Configurazione_Macrospazi_Listino": "{[Misura_Configurazione_Macrospazi_Listino].[CC],[Misura_Configurazione_Macr')
    || TO_CLOB('ospazi_Listino].[EC]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Listino - Promo]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_DescrizioneData",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]')
    || TO_CLOB('
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["MUI_DescrizioneData" , "Descrizione" ]  ,
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
           ')
    || TO_CLOB(' "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":260,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"closed",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"] ,"aggFunc": "","columnGroupShow":"always"}
        }
')
    || TO_CLOB('      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName":  "Promozione"
      , "width":400
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.MUI_Descrizione","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('35','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacrospaziNegoziPromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "title": "Configurazione Macrospazi Negozi Promo",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {

            "Negozio": "{EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SU')
    || TO_CLOB('BSETALL( [Negozio] )}, 0)}, ASC)}},{[Negozio].[NA] })}"
          }
        },
        "COLS": {
          "NON_EMPTY": true ,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}   ",
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}" ,
   ')
    || TO_CLOB('         "Misura_Configurazione_Macrospazi_Neg_Promo": "{[Misura_Configurazione_Macrospazi_Neg_Promo].[DEFAULT],[Misura_Configurazione_Macrospazi_Neg_Promo].[SPAZIO]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Negozio - Promo]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_DescrizioneData",
          "Attributes/Descrizione",
          "Attributes/MUI_ZonaPromo",
          "UniqueName"
        ] ,
      ')
    || TO_CLOB('  "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["MUI_DescrizioneData","Descrizione" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
       ')
    || TO_CLOB('     "headerClass": "headerAcq",
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
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numeric')
    || TO_CLOB('Column"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Negozio.Descrizione"
      , "headerName": "Negozio"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [

        {"headerName":"Zona Promo","field":"Negozio.M')
    || TO_CLOB('UI_ZonaPromo","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Negozio","field":"Negozio.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('36','/Piano_Annuale/Spazi/Macrospazi_Negozi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacrospaziNegozi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title": "Configurazione Macrospazi Negozio",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Negozio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EM')
    || TO_CLOB('PTY": false ,
          "DIMENSIONS": {
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}" ,
            "Misura_Configurazione_Macrospazi_Neg": "{[Misura_Configurazione_Macrospazi_Neg].[(I) Configurazione Macro Neg]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Negozio]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_TOT",
          "Attributes/MUI_ZonaPromo')
    || TO_CLOB('",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          }')
    || TO_CLOB(',
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
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"closed",  "editable": true, "type"')
    || TO_CLOB(':[ "TM1DataColumnText"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"columnGroupShow":"always"},
          "DEFAULT":{"type":[ "TM1DataColumnInteger" ,"numericColumn"]}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Negozio.Descrizione"
      , "headerName": "Negozio"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"h')
    || TO_CLOB('eaderName":"Tot. Negozio","field":"Negozio.MUI_TOT","width":200,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":200,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Negozio","field":"Negozio.Descrizione","width":200,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('37','/Piano_Annuale/Spazi/Macrospazi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_MacroSpazi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title":"Anagrafica Macrospazi",
      "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MacroSpazio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false')
    || TO_CLOB(',
          "DIMENSIONS": {
            "Misura_Macrospazi": "{[Misura_Macrospazi].[(I) Macro Spazi]}"
          }
        },
        "FROM": "[Configurazione Macrospazio]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasP')
    || TO_CLOB('icklist"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
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
            "open')
    || TO_CLOB('ByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "NOTE":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GG_COM_DEFAULT":{"type":[ "TM1DataColumnTe')
    || TO_CLOB('xt"] ,"columnGroupShow":"always"},
          "GRP_SPZ":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.MUI_Descrizione","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}

      ]

    },
    {
      "name": "gc_Spazi_MacroSpazi_Listino",
      "logMemory": true,
      "logTime": true,
      ')
    || TO_CLOB('"skip": true,
        "title": "Aggiorna Macrospazi",
          "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MacroSpazio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura_Configurazione_Macrospazi_Listino": "{[Misura_Configurazione_Macrospazi_Listino].[(I) Configurazion')
    || TO_CLOB('e Macro Listino]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Listino]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
      },

      "DynamicColumns" : true ,
      "Dy')
    || TO_CLOB('namicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
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
            "headerC')
    || TO_CLOB('lass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "TIPO_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_EXTRA_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "DATA_CAMBIO":{')
    || TO_CLOB('"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_EXTRA_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.MUI_Descrizione","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]')
    || TO_CLOB('}
      ]

    },
    {
      "name": "gc_Spazi_MacroSpazi_Aggiorna",
      "logMemory": true,
      "logTime": true,
      "skip": true,
        "title": "Listino Macrospazi",
          "height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MacroSpazio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIME')
    || TO_CLOB('NSIONS": {
            "Misura_Macrospazi": "{[Misura_Macrospazi].[(I) Macro Spazi Agg.]}"
          }
        },
        "FROM": "[Configurazione Macrospazio]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_Descrizione",
"UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist"
        ]
 ')
    || TO_CLOB('     },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
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
    ')
    || TO_CLOB('      },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "GG_COM_NEW":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "OPERAZIONE":{"type":[ "TM1DataColumnText"] ,"columnGroupSh')
    || TO_CLOB('ow":"always"},
          "GRP_SPZ":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.MUI_Descrizione","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}

      ],
      "actions": [{
            "componentId": "pia_ms_crea",
            "componentLabel": "Creazione Macrospazio",
            "proce')
    || TO_CLOB('ss": "MUI_DUMMY_DIM.MacroSpazioInsertElement",
            "timeout":-1,
            "refresh": ["gc_Spazi_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
            "params":[{
              "paramName": "inCodice",
              "label": "Codice Macrospazi",
              "hasPicklist": false
            },
            {
              "paramName": "inDescrizione",
              "label": "Descrizione Macrospazio",
              "hasPicklist": false
      ')
    || TO_CLOB('      },
            {
              "paramName": "inDescTimone",
              "label": "Desrizione Timone",
              "hasPicklist": false
            },
            {
              "paramName": "inGruppo",
              "label": "Gruppo Macrospazi",
              "defaultValue": "Spazi Campagna",
              "hasPicklist": false
            }]
          },
          {
            "componentId": "pia_ms_aggiorna",
            "componentLabel": "Aggiorna Macrospazio",
    ')
    || TO_CLOB('        "process": "MUI_DUMMY_CUB.ConfigurazioneMacrospazi_Aggiornamento",
            "timeout":-1,
            "refresh": ["gc_Spazi_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
            "params":[{
              "label": "Aggiorna:",
              "defaultValue": "Sei Sicuro Di Voler Aggiornare?",
              "hasPicklist": false,
              "disabled": true
            }]
          }]
    }

  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('38','/Piano_Annuale/Spazi/Spazi_Condivisi',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_Spazi_SpaziCondivisi_spazio",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "maxCells" : 2000000,
    "title" : "Spazio",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Spazio" : "{{ORDER( {TM1FILTERBYLEVEL( ')
    || TO_CLOB('{TM1SUBSETALL( [Spazio] )}, 0)},     [Spazio].[MUI_Sort]  ,  ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "}ElementAttributes_Spazio" : "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Spazio] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[}ElementAttributes_Spazio]",
      "WHERE" : { }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "UniqueName", "Attributes/MacroSpazioDescrizione",')
    || TO_CLOB(' "Attributes/Descrizione", "Attributes/MUI_Compratore", "Attributes/MUI_Reparto" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Reparto",
      "field" : "Spazio.MUI_Reparto",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" :')
    || TO_CLOB(' "Spazio.MUI_Compratore",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Macro Spazio",
      "field" : "Spazio.MacroSpazioDescrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Spazio",
      "field" : "Spazio.Descrizione",
      "width" : 200,
      "h')
    || TO_CLOB('ide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
        "table" : "gc_Spazi_SpaziCondivisi_spazio",
        "dimension" : "Spazio",
        "attribute" : "Descrizione"
      },
      "destination" : [ {
        "table" : "gc_Spazi_SpaziCondivisi_spazio",
        "dimension" : "Spazio",
        "attribute" : "Descrizione"
      } ]
    } ]
  }, {
    "name" : "gc_Spazi_SpaziCondi')
    || TO_CLOB('visi_compratore",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "suppressRowClickSelection" : false,
    "maxCells" : 2000000,
    "title" : "Compratore",
    "height" : 22,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , { {[Compratore].[S000]')
    || TO_CLOB(' }, {[Compratore].[NA] }})} , [Compratore].[MUI_Sort] , BASC )}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "}ElementAttributes_Compratore" : "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Compratore] )}, 0)}, ASC)}}"
        }
      },
      "FROM" : "[}ElementAttributes_Compratore]",
      "WHERE" : { }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "UniqueName", "Attributes/CategoryManager"')
    || TO_CLOB(', "Attributes/MUI_Reparto", "Attributes/MUI_Descrizione" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : { },
    "columnDefs" : [ {
      "headerName" : "Category Manager",
      "field" : "Compratore.CategoryManager",
      "width" : 150,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Comprato')
    || TO_CLOB('re.MUI_Reparto",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 200,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "selections" : [ {
      "source" : {
        "table" : "gc_Spazi_SpaziCondivisi_compratore",
        "dimen')
    || TO_CLOB('sion" : "Compratore",
        "attribute" : "MUI_Descrizione"
      },
      "destination" : [ {
        "table" : "gc_Spazi_SpaziCondivisi_compratore",
        "dimension" : "Compratore",
        "attribute" : "MUI_Descrizione"
      } ]
    } ]
  }, {
    "name" : "gc_Spazi_SpaziCondivisi_associazione",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "maxCells" : 2000000,
    "title"')
    || TO_CLOB(' : "Condivisione Spazi",
    "height" : 28,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Spazio" : "{{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 0)},     [Spazio].[MUI_Sort]  ,  ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BD')
    || TO_CLOB('ESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{ [Compratore].[TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
        }
      },
      "FROM" : "[Configurazione RaggrSpazi]",
      "WHERE" : {
        "Misura Config RaggrFoto" : "[OK]"
      }
    },
    "ExecuteMDX" : {
      ')
    || TO_CLOB('"Members" : [ "Name", "UniqueName", "Attributes/Canale", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/Descrizione", "Attributes/Compratore", "Attributes/MacroSpazioDescrizione", "Attributes/MUI_TOT", "Attributes/MUI_DescrizioneData" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
 ')
    || TO_CLOB('       "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefau')
    || TO_CLOB('lts" : {
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : { }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "cellClass" : "cellClass-left-text",
      "field" : "Spazio.Descrizione",
      "headerNa')
    || TO_CLOB('me" : "Spazio",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Totale",
      "field" : "Spazio.MUI_TOT",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Spazio.Compratore",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : ')
    || TO_CLOB('false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Macro Spazio",
      "field" : "Spazio.MacroSpazioDescrizione",
      "width" : 200,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColum')
    || TO_CLOB('nName" : "MUI_Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_Spazi_SpaziCondivisi_associazione" ]
    } ],
    "actions" : [ {
      "componentId" : "pia_sc_condividi",
      "componentLabel" : "Condividi",
      "process" : "MUI_DUMMY_CUB.ConfigurazioneRaggrSpazi",
      "timeout" : -1,
      "refresh" : [ "gc_Spazi_SpaziCondivisi_associazione" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
')
    || TO_CLOB('        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      }, {
        "dimension" : "Spazio",
        "attribute" : "Descrizione",
        "paramName" : "inSpazio",
        "label" : "Spazio",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      }, {
        "dimension" : "Compratore",
        "attribute" : "MUI_Descrizione",
        "paramName" : "')
    || TO_CLOB('inCompratore",
        "label" : "Compratore",
        "hasPicklist" : false,
        "disabled" : true,
        "visible" : true
      } ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('39','/Reporting/Copia_in_Pianificazione',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_CopiaInPianificazione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
           "maxCells": 750000,
          "title": "Copia in Pianificazione",
"height": 60,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona ')
    || TO_CLOB('Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - S')
    || TO_CLOB('cenario": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}",
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                       	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SU')
    || TO_CLOB('BSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_')
    || TO_CLOB('EMPTY": false,
					"DIMENSIONS": {
						"REP - Misura Reporting Articolo": "{[REP - Misura Reporting Articolo].[Misura Reporting Articolo - Reporting]}"
					}
				},
				"FROM": "[Reporting Articolo]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/Descrizione",
                    "Attributes/MUI_Descrizione",
                    "Attributes/DescrizioneArticolo",

"UniqueName"
			')
    || TO_CLOB('	],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
 ')
    || TO_CLOB('         "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColu')
    || TO_CLOB('mnInteger" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {},
			"columnDefs": [
                 {
					"headerName": "Scenario",
					"field": "REP_minus_Scenario.Descrizione",
					"width": 70,
					"hide": false,
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
					"hide": fa')
    || TO_CLOB('lse,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.MUI_Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup"')
    || TO_CLOB(': false,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
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
					"editable')
    || TO_CLOB('": true,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
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
					"editable": true,
					"type":')
    || TO_CLOB(' [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"actions": [{
				"componentId": "rep_cpi_copia",
				"componentLabel": "Copia pezzi e colli",
				"process": "MUI_DUMMY_EXP.CUB.Reporting Articolo - FlagCopia",
				"timeout":-1,
				"refresh": [],
				"params":[
                {
                    "dimension": "REP - Promozione",
					"attribute": "MUI_Descrizione",
					"paramName": "pPromozione",
					"labe')
    || TO_CLOB('l": "Promozione",
					"hasPicklist": true,
					"disabled": false,
					"visible": true
                },
                {
                    "dimension": "REP - Compratore",
					"attribute": "Descrizione",
					"paramName": "pCompratore",
					"label": "Compratore",
					"hasPicklist": true,
					"disabled": false,
					"visible": true
                }
                ]
			},
				{
					"componentId": "rep_cpi_copest",
					"componentLabel": "Copia estesa",')
    || TO_CLOB('
					"process": "MUI_DUMMY_EXP.CUB.Reporting Articolo - FlagCopiaEstesa",
					"timeout":-1,
					"refresh": [],
					"params":[
                      {
                    "dimension": "REP - Promozione",
					"attribute": "MUI_Descrizione",
					"paramName": "pPromozione",
					"label": "Promozione",
					"hasPicklist": true,
					"disabled": false,
					"visible": true
                },
                {
                    "dimension": "REP - Compratore",
					"attri')
    || TO_CLOB('bute": "Descrizione",
					"paramName": "pCompratore",
					"label": "Compratore",
					"hasPicklist": true,
					"disabled": false,
					"visible": true
                }
                ]
				}],
           "preSelections": [
        {
          "dimension": "REP - Compratore",
          "dimCode": "rep_compratore",
          "dimColumnName": "REP - Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descri')
    || TO_CLOB('zione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_CopiaInPianificazione"]
        },
         {
          "dimension": "REP - Scenario",
          "dimCode": "rep_scenario",
          "dimColumnName": "REP - Scenario",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_CopiaInPianificazione"]
        }
')
    || TO_CLOB('
      ]
		}

	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('40','/Reporting/Dettaglio_Campagna',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_DettaglioCampagna",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
          "title": "Dettaglio Campagna",
"height": 60,

             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Prom')
    || TO_CLOB('o -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compr')
    || TO_CLOB('atore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": ')
    || TO_CLOB('"{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                         "REP - Scenario": "{ [REP - Scenario].[Scenario -')
    || TO_CLOB('Storico]}",
						"REP - Misura Reporting Articolo": "{[REP - Misura Reporting Articolo].[Reporting ACQ]}"
					}
				},
				"FROM": "[Reporting Articolo]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/MUI_DescrizioneData",
                    "Attributes/Descrizione",
                    "Attributes/MUI_TOT",
                    "Attributes/RepartoDesc",
                    "Attributes/CategoriDesc",
')
    || TO_CLOB('
                    "Attributes/GRMDesc",
                    "Attributes/DescrizioneArticolo",
                    "Attributes/SubGrmDesc",
                    "UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "MUI_DescrizioneData" , "Descrizione" ,  "Descrizione" ]  ,
				"headerdefaults":  {"marry')
    || TO_CLOB('Children" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "heade')
    || TO_CLOB('rClass": "headerBdg",
            "openByDefault": true
          }
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef":{
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Compratore.Descrizione",
				"headerName": "Compratore",
				"widt')
    || TO_CLOB('h": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
              {
					"headerName": "Category",
					"field": "REP_minus_Compratore.MUI_TOT",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
              {
					"headerName": "Compratori",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": false')
    || TO_CLOB(',
					"editable": false,
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
					"editable": ')
    || TO_CLOB('true ,
					"type": [
						"TM1Element"
					]
				  } ,
               {
					"headerName": "GRM",
					"field": "REP_minus_Articolo.GRMDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
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
					"rowGroup": true,
					"editable": true,
					"type"')
    || TO_CLOB(': [
						"TM1Element"
					]
				} ,
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
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.SubGrmDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,')
    || TO_CLOB('

                {
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
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
			')
    || TO_CLOB('	} ,
               {
					"headerName": "Meccanica Semplice",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
               {
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
			')
    || TO_CLOB('		]
				}





			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('41','/Reporting/Dettaglio_Zona',TO_CLOB('{
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_DettaglioZona",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
          "title": "Dettaglio Zona",
"height": 60,

             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVen')
    || TO_CLOB('d]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          			    "REP - For')
    || TO_CLOB('nitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
             ')
    || TO_CLOB('           "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                       "REP - Scenario": "{[REP - Scenari')
    || TO_CLOB('o].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
						"REP - Zona Promo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
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
					"Attributes')
    || TO_CLOB('/MUI_DescrizioneData",
                    "Attributes/Descrizione",
                    "Attributes/MUI_TOT",
                    "Attributes/RepartoDesc",
                    "Attributes/CategoriDesc",
                    "Attributes/GRMDesc",
                    "Attributes/DescrizioneArticolo",
                    "Attributes/SubGrmDesc",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},')
    || TO_CLOB('

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "MUI_DescrizioneData" ,"Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
         ')
    || TO_CLOB('   "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
		')
    || TO_CLOB('	} ,

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
					"type"')
    || TO_CLOB(': [
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
						"TM1')
    || TO_CLOB('Element"
					]
				  } ,
                 {
					"headerName": "GRM",
					"field": "REP_minus_Articolo.GRMDesc",
					"width": 70,
					"hide": true,
					"rowGroup": true,
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
					"rowGroup": true,
					"editable": true ,
					"type": [
						"TM1Element"
					]
				} ,
        ')
    || TO_CLOB('      {
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				} ,
				{
					"headerName": "Compratori",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
                {
					"he')
    || TO_CLOB('aderName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
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
				} ,
               {
					"h')
    || TO_CLOB('eaderName": "Meccanica Semplice",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				} ,
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
			"rowSu')
    || TO_CLOB('ppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('42','/Reporting/Sintesi_Campagna',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_SintesiCampagna",
			"logMemory": true,
			"logTime": true,
			"skip": true,
          "title": "Sintesi Campagna",
"height": 60,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , AS')
    || TO_CLOB('C)}"
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
					"REP - Versione"')
    || TO_CLOB(': "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/MUI_DescrizioneData",
                    "Attributes/Descrizione",
                    "Attributes/MUI_TOTS",
                    "Attributes/MUI_TOT",
                    "Attributes/MUI_DIR",
                    "Attributes/MUI_REP",
                    "UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				')
    || TO_CLOB(']
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
           ')
    || TO_CLOB(' "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
')
    || TO_CLOB('			} ,

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
						')
    || TO_CLOB('"TM1Element"
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
					"rowGroup": false,
					"editable": true,
					"type": [
						"TM1Element"
				')
    || TO_CLOB('	]
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
					"head')
    || TO_CLOB('erName": "Categoria",
					"field": "REP_minus_Categoria.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": true,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,

')
    || TO_CLOB('			"colSuppressionEnabled": false
		}
	]
}


'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('43','/Reporting/Storico_articolo_per_zona_(ACQ)',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_StoricoArticoloPerZonaAcq",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
          "title": "Storico articolo per zona (ACQ)",
"height": 60,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zon')
    || TO_CLOB('a Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
')
    || TO_CLOB('
                        "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
    ')
    || TO_CLOB('                    "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{TM1S')
    || TO_CLOB('ORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -BDGVend]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Version')
    || TO_CLOB('e": "[UFF]"
				}
			},
			"ExecuteMDX": {

				"Members": [
					"Name",
					"Attributes/MUI_Descrizione",
          			"Attributes/Descrizione",
          			"Attributes/DescrizioneArticolo",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerd')
    || TO_CLOB('efaults":  {"marryChildren" : true}  ,
				"headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG')
    || TO_CLOB('": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {},
			"columnDefs": [

               {
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descrizione",
	')
    || TO_CLOB('				"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.MUI_Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
	')
    || TO_CLOB('				"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
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
		')
    || TO_CLOB('			"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
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
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false')
    || TO_CLOB(',
					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
            "preSelections": [
        {
          "dimension": "REP - Compratore",
          "dimCode": "rep_compratore",
          "dimColumnName": "REP - Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramN')
    || TO_CLOB('ame": "",
          "refresh": ["gc_StoricoArticoloPerZonaAcq"]
        },
         {
          "dimension": "REP - Scenario",
          "dimCode": "rep_scenario",
          "dimColumnName": "REP - Scenario",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_StoricoArticoloPerZonaAcq"]
        }
      ]
        }
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('44','/Reporting/Storico_articolo_per_zona_(MKT)',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_StoricoArticoloPerZonaAcqMkt",
			"logMemory": true,
			"logTime": true,
			"skip": true,
            "maxCells":1000000 ,
                    "title": "Storico articolo per zona (MKT)",
"height": 60,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": ')
    || TO_CLOB('"{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIM')
    || TO_CLOB('ENSIONS": {
                        "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , A')
    || TO_CLOB('SC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scena')
    || TO_CLOB('rio": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -Storico]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"R')
    || TO_CLOB('EP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/MUI_Descrizione",
          			"Attributes/Descrizione",
          			"Attributes/DescrizioneArticolo",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
		')
    || TO_CLOB('		"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
    ')
    || TO_CLOB('      "BDG": {
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {},
			"columnDefs": [

               {
					"headerName": "Compratore",
					"field": "REP_minus_Compratore.Descri')
    || TO_CLOB('zione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
               {
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.MUI_Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"widt')
    || TO_CLOB('h": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
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
					"hide": ')
    || TO_CLOB('false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
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
					"headerName": "A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
					"width": 70,
					"hide": false,
					"rowG')
    || TO_CLOB('roup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}

			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
          "preSelections": [
        {
          "dimension": "REP - Compratore",
          "dimCode": "rep_compratore",
          "dimColumnName": "REP - Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
       ')
    || TO_CLOB('   "paramName": "",
          "refresh": ["gc_StoricoArticoloPerZonaAcqMkt"]
        },
         {
          "dimension": "REP - Scenario",
          "dimCode": "rep_scenario",
          "dimColumnName": "REP - Scenario",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_StoricoArticoloPerZonaAcqMkt"]
        }
      ]
  	}
	]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('45','/Reporting/Timone_Reparto',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_TimoneReparto",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
"title": "Timone Reparto",
"height": 60,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Reparto": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto')
    || TO_CLOB('] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ]}",
						"REP - Misura Timone": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Timone] )}, 0)} , ASC)}"
					}
				},
				"FROM": "[Timone Reparto]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
')
    || TO_CLOB('
				"Members": [
					"Name",
					"Attributes/Anno",
          			"Attributes/Descrizione",
          			"Attributes/Canale",
"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "Descrizione" , "Descrizione"]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
')
    || TO_CLOB('						"openByDefault": true
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
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn')
    || TO_CLOB('"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "REP_minus_Reparto.Descrizione",
				"headerName": "Reparto",
				"width": 300,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
              {
					"headerName": "Anno",
					"field": "REP_minus_Promozione.Anno",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editab')
    || TO_CLOB('le": false,
					"type": [
						"TM1Element"
					]
				},
              {
					"headerName": "Canale",
					"field": "REP_minus_Promozione.Canale",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.Descrizione",
					"width": 70,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						')
    || TO_CLOB('"TM1Element"
					]
				},
				{
					"headerName": "Reparto",
					"field": "REP_minus_Reparto.Descrizione",
					"width": 70,
					"hide": true,
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
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('48','/Timone/Foto_Speciali/Riepilogo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Riepilogo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Riepilogo",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Categoria" : "{{[Categoria].[CAT_0000]},{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{ {TM1FI')
    || TO_CLOB('LTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}}} , {[Categoria].[CAT_0000]}) }}",
          "Compratore" : "{{[Compratore].[S000]},{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]} )}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} ,')
    || TO_CLOB(' [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica" : "{TM1SUBSETALL( [Sezione Tematica] )}",
          "Misura Timone." : "{{[Misura Timone.].[N_FOTO_TOT]},{[Misura Timone.].[N_FOTO_SCAFFALE_TOT]}}"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
  ')
    || TO_CLOB('  "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_TOT", "Attributes/MUI_RepartoDesc", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.Descrizione",
  ')
    || TO_CLOB('    "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" ')
    || TO_CLOB(': "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        ')
    || TO_CLOB('"type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnNumberK", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO__perc_" : {
          "type" : [ "TM1DataColumnNumber", "numericColumn" ],
          "columnGroupShow" : "always"
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "Totale Categoria",
      "field" : "')
    || TO_CLOB('Categoria.MUI_TOT",
      "width" : 90,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Categoria.MUI_RepartoDesc",
      "width" : 90,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Categoria",
      "field" : "Categoria.Descrizi')
    || TO_CLOB('one",
      "width" : 90,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizio')
    || TO_CLOB('ne",
      "width" : 70,
      "hide" : false,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
  ')
    || TO_CLOB('    "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000')
    || TO_CLOB(''')  || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].')
    || TO_CLOB('includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',')
    || TO_CLOB('''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-1{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('49','/Timone/Foto_Speciali/Target',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Target",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Target",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( ')
    || TO_CLOB('{EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{{[Categoria].[CAT_0000]}, {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria]')
    || TO_CLOB('.[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica" : "{{TM1S')
    || TO_CLOB('UBSETALL( [Sezione Tematica] )}}",
          "Misura Timone." : "{[Misura Timone.].[(II) Timone Categoria Sez]}"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells"')
    || TO_CLOB(' : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "MUI_Descrizione", "D')
    || TO_CLOB('escrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : tr')
    || TO_CLOB('ue
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "ASSORT_P" : {
          "hide" : true,
          "type" : [ "TM1DataColumnText" ]
        },
        "ST_0000" : {
          "hide" : true
        }
    ')
    || TO_CLOB('  }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
     ')
    || TO_CLOB(' "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" :')
    || TO_CLOB(' [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name ==')
    || TO_CLOB('= ''CAT_0000'')  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "r')
    || TO_CLOB('ow_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name')
    || TO_CLOB('.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
     "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('50','/Timone/Spazi_Campagna/Target_Categoria',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetCategoria",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCE')
    || TO_CLOB('PT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}  }",
          "Categoria" : "{{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].')
    || TO_CLOB('[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Mi')
    || TO_CLOB('sura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Misura Timone.].[ASSORT_P]}  }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attribu')
    || TO_CLOB('tes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "')
    || TO_CLOB('TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
')
    || TO_CLOB('        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : ')
    || TO_CLOB('{
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "h')
    || TO_CLOB('eaderName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName"')
    || TO_CLOB(' : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(d')
    || TO_CLOB('ata.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(')
    || TO_CLOB('data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data')
    || TO_CLOB('.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
  ')
    || TO_CLOB('  "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('51','/Timone/Spazi_Campagna/Target_Reparto',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SpaziCampagna_TargetReparto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
                "title":"Target Reparto",
    "height": 60,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUB')
    || TO_CLOB('SETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Reparto": "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
     ')
    || TO_CLOB('   "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[SPZ_CAMP]},{FILT')
    || TO_CLOB('ER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        		"Attributes/Canale",

         	   "Attributes/Riferimento",
        		"Attributes/TipoElemento",
"UniqueName"
     ')
    || TO_CLOB('   ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
    ')
    || TO_CLOB('  "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
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
            "openByDefau')
    || TO_CLOB('lt": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 110,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "open",
          "editable": true,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": ')
    || TO_CLOB('{
          "N_ART_PROMO":  {
            "columnGroupShow":"always"
          },
          "TOT_FOTO":  {
            "columnGroupShow":"always"
          },
          "SPZ_CAMP":  {
            "columnGroupShow":"always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type')
    || TO_CLOB('": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.MUI_Descrizione",
          "width": 400,
          "hide": true,
      ')
    || TO_CLOB('    "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento",
      ')
    || TO_CLOB('    "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": {
        "row')
    || TO_CLOB('_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Comprat')
    || TO_CLOB('ore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [{
            "componentId": "tim_scr_inizializza",
            "componentLabel": "Inizializza Spazio",
            "process": "MUI_DUMMY_CUB.Timone Reparto Inizializza Spazi",
            "timeout":-1,
            "refresh": ["gc_SpaziCampagna_TargetReparto"],
            ')
    || TO_CLOB('"params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            }]
          }]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('52','/Timone/Target_Categoria/Data',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Categoria (data)",
    "height" : 60,
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
')
    || TO_CLOB('
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{    {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )},')
    || TO_CLOB(' 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " ')
    || TO_CLOB('{{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{')
    || TO_CLOB('[Misura Timone.].[ASSORT_P]} }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoG')
    || TO_CLOB('roupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomType')
    || TO_CLOB('s" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
     ')
    || TO_CLOB('   "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow"')
    || TO_CLOB(' : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Comprator')
    || TO_CLOB('e",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",')
    || TO_CLOB('
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ')
    || TO_CLOB('''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04''')
    || TO_CLOB(',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03''')
    || TO_CLOB(',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('54','/Timone/Target_Categoria/Promo',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 500000,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
     ')
    || TO_CLOB('   "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{  {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETAL')
    || TO_CLOB('L( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
       ')
    || TO_CLOB('   "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO')
    || TO_CLOB('_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
 ')
    || TO_CLOB('   },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      ')
    || TO_CLOB('"headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide')
    || TO_CLOB('" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          ')
    || TO_CLOB('"columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerN')
    || TO_CLOB('ame" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Li')
    || TO_CLOB('vello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Co')
    || TO_CLOB('mpratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_')
    || TO_CLOB('01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',')
    || TO_CLOB('''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('55','/Timone/Target_Reparto/Data',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Data",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "title":"Target Reparto (data)",
      "height": 60,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione')
    || TO_CLOB('].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Reparto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Ti')
    || TO_CLOB('mone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
        		"Attributes/Descrizione",
                "Attributes/Anno",
        		"Attributes/MUI_Semestre",
         	    "Attributes/DataInizioIst",
        		"Attributes/MUI_Descrizione",
                "')
    || TO_CLOB('Attributes/Riferimento",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
')
    || TO_CLOB('        ]
      },
      "columnDefs": [
        {
          "headerName": "Anno",
          "field": "Promozione.Anno",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {

          "headerName": "Semestre",
          "field": "Promozione.MUI_Semestre",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editabl')
    || TO_CLOB('e": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Data",
          "field": "Promozione.DataInizioIst",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": tru')
    || TO_CLOB('e,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.MUI_Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.')
    || TO_CLOB('Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "hea')
    || TO_CLOB('derName": "Riferimento Data",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
         ')
    || TO_CLOB('       "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
            ')
    || TO_CLOB('  ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
    ')
    || TO_CLOB('          "headerName": "N. Foto Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",

              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName":')
    || TO_CLOB(' "N. Foto Speciali",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
 ')
    || TO_CLOB('             "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field')
    || TO_CLOB('": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_D')
    || TO_CLOB('T$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_L')
    || TO_CLOB('ORDO__perc_",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
              "editable": true,

              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF C",
              "field": "Scenario$')
    || TO_CLOB('RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF EC",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",')
    || TO_CLOB('
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "headerMkt",
          "openByDefault": true,
          "children')
    || TO_CLOB('": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "')
    || TO_CLOB('headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              ')
    || TO_CLOB('"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_MKT$$MisuraTim')
    || TO_CLOB('one_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
 ')
    || TO_CLOB('             "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "wid')
    || TO_CLOB('th": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": ')
    || TO_CLOB('false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N.')
    || TO_CLOB(' Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scen')
    || TO_CLOB('ario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO')
    || TO_CLOB('",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 8')
    || TO_CLOB('0,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": fal')
    || TO_CLOB('se,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
            ')
    || TO_CLOB('  "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
          ')
    || TO_CLOB('    "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
')
    || TO_CLOB('
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
         ')
    || TO_CLOB('     "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
')
    || TO_CLOB('              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
           ')
    || TO_CLOB('   "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": true,
              "rowGroup"')
    || TO_CLOB(': false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggF')
    || TO_CLOB('unc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "c')
    || TO_CLOB('olumnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "')
    || TO_CLOB('open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable":')
    || TO_CLOB(' true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "')
    || TO_CLOB('type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
 ')
    || TO_CLOB('               "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trd_inizializza",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY_CUB.Timone.Inizializza Reference",
          "timeout": -1')
    || TO_CLOB(',
          "refresh": [
            "gc_TargetReparto_Data"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
    ')
    || TO_CLOB('          "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('56','/Timone/Target_Reparto/Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Promo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000,
            "title":"Target Reparto ",
      "height": 60,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_')
    || TO_CLOB('Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Reparto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEV')
    || TO_CLOB('EL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
        		"Attributes/Descrizione",
                "Attributes/Canale",
        		"Attributes/MUI_Descrizione",
                "Attributes/Riferimento",
                "Attributes/TipoElemento",
        ')
    || TO_CLOB('  "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "column')
    || TO_CLOB('Defs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type')
    || TO_CLOB('": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.MUI_Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "row')
    || TO_CLOB('Group": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento",
          "field": "Promozione.Riferimento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
       ')
    || TO_CLOB('   "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGr')
    || TO_CLOB('oup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc')
    || TO_CLOB('": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupS')
    || TO_CLOB('how": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
          ')
    || TO_CLOB('    "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
   ')
    || TO_CLOB('           "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "ty')
    || TO_CLOB('pe": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1D')
    || TO_CLOB('ataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInte')
    || TO_CLOB('ger",
                "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
')
    || TO_CLOB('                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target MKT",
          "headerClass": "headerMkt",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": fals')
    || TO_CLOB('e,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
  ')
    || TO_CLOB('            "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
 ')
    || TO_CLOB('             "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": tru')
    || TO_CLOB('e,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
')
    || TO_CLOB('                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "')
    || TO_CLOB('TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
        ')
    || TO_CLOB('        "numericColumn"
              ]
            }
          ]
        },
        {
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "')
    || TO_CLOB('columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
          ')
    || TO_CLOB('    "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type"')
    || TO_CLOB(': [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1Dat')
    || TO_CLOB('aColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
           ')
    || TO_CLOB('     "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColu')
    || TO_CLOB('mn"
              ]
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
          ')
    || TO_CLOB('  },
            {
              "headerName": "D Foto/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
 ')
    || TO_CLOB('             "headerName": "D Foto Banco/tgt Mkt",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        },
  ')
    || TO_CLOB('      {
          "headerName": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": ')
    || TO_CLOB('[
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": true,
              "type": [
                "TM1DataColumnInteger')
    || TO_CLOB('",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
       ')
    || TO_CLOB('       ]
            },
            {
              "headerName": "N. Foto Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
   ')
    || TO_CLOB('         {
              "headerName": "N. Foto Speciali",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "hea')
    || TO_CLOB('derName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "N. Foto')
    || TO_CLOB(' Ultima",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field":')
    || TO_CLOB(' "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_AC')
    || TO_CLOB('Q$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",

              "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupF')
    || TO_CLOB('alse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [
        {
          "componentId": "tim_trp_inizializza",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY_CUB.Timone.Inizializza Reference",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Promo"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": ')
    || TO_CLOB('"inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Gruppo",
              "paramName": "inGruppo",
              "label": "Gruppo",
     ')
    || TO_CLOB('         "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('57','/Timone/Associazione_Promo',TO_CLOB(' {
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_AssociazionePromo_Promozioni",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"suppressRowClickSelection": false,
          "title":"Promozioni",
          "height": 22,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL(')
    || TO_CLOB(' [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"}ElementAttributes_Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione] )}, 0)}, ASC)}"
					}
				},
				"FROM": "[}ElementAttributes_Promozione]"
			},
			"ExecuteMDX": {
				"Members": [
					"Nam')
    || TO_CLOB('e",
					"Attributes/Name",
                    "Attributes/MUI_Descrizione",
                    "Attributes/Riferimento",
                    "Attributes/TipoPromozione",
                  	"Attributes/Canale",
                    "Attributes/Anno",
                    "Attributes/MUI_Semestre",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]


			},
			"autoGroupColumnDef": {

			},
')
    || TO_CLOB('			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.Name",
					"width": 70,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.MUI_Descrizione",
					"width": 400,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferi')
    || TO_CLOB('mento",
					"field": "Promozione.Riferimento",
					"width": 400,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Gruppo",
					"field": "Promozione.TipoPromozione",
					"width": 100,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Canale",
					"field": "Promozione.Canale",
					"widt')
    || TO_CLOB('h": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
					"field": "Promozione.Anno",
					"width": 50,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "Promozione.MUI_Semestre",
					"width": 100,
					"hide": false,
					"editable": false,
					"rowG')
    || TO_CLOB('roup": false,
					"type": [
						"TM1Element"
					]
				}
			],
			"selections": [
				{
					"source": {
						"table": "gc_AssociazionePromo_Promozioni",
						"dimension": "Promozione",
						"attribute": "MUI_Descrizione"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Associazioni",
							"dimension": "Promozione",
							"attribute": "MUI_Descrizione"
						},
						{
							"table": "gc_AssociazionePromo_SezioniTematiche",
							"dimen')
    || TO_CLOB('sion": "Promozione",
							"attribute": "MUI_Descrizione"
						}
					]
				},
				{
					"source": {
						"table": "gc_AssociazionePromo_Promozioni",
						"dimension": "Promozione",
						"attribute": "Anno"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Promozioni",
							"dimension": "Promozione",
							"attribute": "Anno"
						}
					]
				}
			]
		},
		{
			"name": "gc_AssociazionePromo_Riferimento",
			"logMemory": true,
			"logTi')
    || TO_CLOB('me": true,
			"skip": true,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"suppressRowClickSelection": false,
                    "title":"Riferimento",
          "height": 22,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione Riferimento": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, [Promozione Riferimento].[Data_Inizio_Ist.],DESC )}"
					}
				},
				"COLS": {
					"NON_EMPTY": ')
    || TO_CLOB('false,
					"DIMENSIONS": {
						"}ElementAttributes_Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione Riferimento] )}, 0)}, ASC)}"
					}
				},
				"FROM": "[}ElementAttributes_Promozione Riferimento]"
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/Name",
                    "Attributes/MUI_Descrizione",
                    "Attributes/Descrizione",
                    "Attributes/MUI_Canale",
 					"Attributes/Anno')
    || TO_CLOB('",
                    "Attributes/Gruppo",
                    "Attributes/Semestre",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},
			"autoGroupColumnDef": {

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "PromozioneRiferimento.Name",
					"width": 100,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"T')
    || TO_CLOB('M1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "PromozioneRiferimento.MUI_Descrizione",
					"width": 300,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Codice Descrizione",
					"field": "PromozioneRiferimento.Descrizione",
					"width": 300,
					"hide": true,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
				')
    || TO_CLOB('	]
				},
				{
					"headerName": "Anno",
					"field": "PromozioneRiferimento.Anno",
					"width": 50,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Canale",
					"field": "PromozioneRiferimento.MUI_Canale",
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Gruppo",
')
    || TO_CLOB('
					"field": "PromozioneRiferimento.Gruppo",
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Semestre",
					"field": "PromozioneRiferimento.Semestre",
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				}
			],
			"selections": [
				{
					"source": {
						"table": "gc_Associaz')
    || TO_CLOB('ionePromo_Riferimento",
						"dimension": "PromozioneRiferimento",
						"attribute": "Descrizione"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Riferimento",
							"dimension": "PromozioneRiferimento",
							"attribute": "Descrizione"
						}
					]
				}
			]
		},
		{
			"name": "gc_AssociazionePromo_Associazioni",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false')
    || TO_CLOB(',
			"alertNoDataFound": false,
                    "title":"Associazioni",
          "height": 18,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
						"Promozione Riferimento": "{TM1SORT( {TM1FILTERBYLE')
    || TO_CLOB('VEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Configurazione Riferimento": "{[Misura Configurazione Riferimento].[Selezione]}"
					}
				},
				"FROM": "[Configurazione Riferimento Associazione]"
			},
			"ElementSelector": {
				"promozione": {
					"lable": "Promozione",
					"Dimension": "Promozione",
					"Show": "Descrizione",
					"mdx": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM')
    || TO_CLOB('1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
					"enabled": false
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
                    "Attributes/Descrizione",
                    "Attributes/MUI_Descrizione",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},
			"autoGroupColumnDef": {

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Pro')
    || TO_CLOB('mozione.MUI_Descrizione",
					"width": 200,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Riferimento",
					"field": "PromozioneRiferimento.Descrizione",
					"width": 300,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				}
			],
			"actions": [
              {
					"componentId": "tim_ap_aggiorna",
					"compone')
    || TO_CLOB('ntLabel": "Aggiorna Descrizione Riferimento",
					"process": "MUI_DUMMY_DIM.Promozione.ConfRiferimento",

					"timeout": -1,
					"refresh": [
						"gc_AssociazionePromo_Promozioni","gc_AssociazionePromo_Riferimento","gc_AssociazionePromo_Associazioni","gc_AssociazionePromo_SezioniTematiche"
					],
					"params": [
						{
							"dimension": "Promozione",
							"attribute": "Anno",
							"paramName": "inAnno",
							"label": "Anno",
							"hasPicklist": false,
							"disa')
    || TO_CLOB('bled": false,
							"visible": true
						}
					]
				},
              {
					"componentId": "tim_ap_associa",
					"componentLabel": "Associa",
					"process": "MUI_DUMMY_CUB.Associa Promozione",

					"timeout": -1,
					"refresh": [
						"gc_AssociazionePromo_Associazioni","gc_AssociazionePromo_SezioniTematiche"
					],
					"params": [
						{
							"dimension": "Promozione",
							"attribute": "MUI_Descrizione",
							"paramName": "inPromo",
							"label": "Promozi')
    || TO_CLOB('one",
							"hasPicklist": false,
							"disabled": true,
							"visible": true
						},
						{
							"dimension": "Promozione",
							"attribute": "Anno",
							"paramName": "inAnno",
							"label": "Anno",
							"hasPicklist": false,
							"disabled": true,
							"visible": false
						},
						{
							"dimension": "PromozioneRiferimento",
							"attribute": "Descrizione",
							"paramName": "inRiferimento",
							"label": "Riferimento",
							"hasPicklist": ')
    || TO_CLOB('false,
							"disabled": true,
							"visible": true
						}
					]
				}
			]
		},
		{
			"name": "gc_AssociazionePromo_SezioniTematiche",
			"logMemory": true,
			"logTime": true,
			"skip": true,

			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"alertNoDataFound": false,
                    "title":"Sezioni Tematiche",
          "height": 35,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{FILTER(')
    || TO_CLOB(' { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}  ",
						"Promozione con Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione con Sezione Tematica] )}, 0)}, ASC)}} "
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Configurazione Riferimento": "{[')
    || TO_CLOB('Misura Configurazione Riferimento].[Selezione] , [Misura Configurazione Riferimento].[DataInizio.],[Misura Configurazione Riferimento].[DataFine.],[Misura Configurazione Riferimento].[DataInizio],[Misura Configurazione Riferimento].[DataFine],[Misura Configurazione Riferimento].[COUNT_GIORNI],[Misura Configurazione Riferimento].[VOL],[Misura Configurazione Riferimento].[ESCLUSIONE_CLUSTER],[Misura Configurazione Riferimento].[ESCLUSIONE_COUNTING],[Misura Configurazione Riferimento].[Escludi_Vend')
    || TO_CLOB('uto],[Misura Configurazione Riferimento].[DESCRIZIONE_RIF]}"
					}
				},
				"FROM": "[Configurazione Riferimento]"
			},
			"ElementSelector": {
				"promozione": {
					"lable": "Promozione",
					"Dimension": "Promozione",
					"Show": "Descrizione",
					"mdx": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
					"enabled": false
				}
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
                    "Attributes/Descrizione",
       ')
    || TO_CLOB('             "Attributes/MUI_Descrizione",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist"
				]
			},
			"autoGroupColumnDef": {

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.MUI_Descrizione",
					"width": 300,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"heade')
    || TO_CLOB('rName": "Descrizione Rif.",
					"field": "MisuraConfigurazioneRiferimento$DESCRIZIONE_RIF",
					"width": 400,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnText"
					]
				},
				{
					"headerName": "Sezione Tematica",
					"field": "PromozioneconSezioneTematica.Descrizione",
					"width": 300,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
')
    || TO_CLOB('					"headerName": "Selezione",
					"field": "MisuraConfigurazioneRiferimento$Selezione",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnCheckbox"
					]
				},
				{
					"headerName": "DataInizio Rif",
					"field": "MisuraConfigurazioneRiferimento$DataInizio_dot_",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
					]
		')
    || TO_CLOB('		},
				{
					"headerName": "DataFine Rif",
					"field": "MisuraConfigurazioneRiferimento$DataFine_dot_",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
					]
				},
				{
					"headerName": "Data Inizio",
					"field": "MisuraConfigurazioneRiferimento$DataInizio",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
		')
    || TO_CLOB('			]
				},
				{
					"headerName": "Data Fine",
					"field": "MisuraConfigurazioneRiferimento$DataFine",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnDate"
					]
				},
				{
					"headerName": "N. Giorni",
					"field": "MisuraConfigurazioneRiferimento$COUNT_GIORNI",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnText"
		')
    || TO_CLOB('			]
				},
				{
					"headerName": "Gestione Volantino",
					"field": "MisuraConfigurazioneRiferimento$VOL",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnText"
					]
				},
				{
					"headerName": "Escludi Cluster Formato",
					"field": "MisuraConfigurazioneRiferimento$ESCLUSIONE_CLUSTER",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
					')
    || TO_CLOB('	"TM1DataColumnText"
					]
				},
				{
					"headerName": "Escludi Conteggio",
					"field": "MisuraConfigurazioneRiferimento$ESCLUSIONE_COUNTING",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGroup": false,
					"type": [
						"TM1DataColumnCheckbox"
					]
				},
				{
					"headerName": "Escludi Venduto",

					"field": "MisuraConfigurazioneRiferimento$Escludi_Venduto",
					"width": 100,
					"hide": false,
					"editable": true,
					"rowGro')
    || TO_CLOB('up": false,
					"type": [
						"TM1DataColumnCheckbox"
					]
				}
			]
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('0','config_filter.json',TO_CLOB('[ {
  "DIM_code" : "idsezioneTematica",
  "DIM_columnName" : "ID Sezione Tematica",
  "DIM_description" : "ID Sezione Tematica",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "Caption_Default",
    "ATTR_columnName" : "Caption_Default",
    "ATTR_desc" : "Caption_Default"
  } ]
}, {
  "DIM_code" : "promozione",
  "DIM_columnName" : "Promozione",
  "D')
    || TO_CLOB('IM_description" : "Promozione",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
  "list_ATTR" : [ {
    "ATTR_code" : "anno",
    "ATTR_columnName" : "Anno",
    "ATTR_desc" : "Anno"
  }, {
    "ATTR_code" : "canale",
    "ATTR_columnName" : "Canale",
    "ATTR_desc" : "Canale"
  }, {
')
    || TO_CLOB('    "ATTR_code" : "tipo",
    "ATTR_columnName" : "MUI_Tipo Promozione Desc",
    "ATTR_desc" : "Gruppo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "MUI_Descrizione",
    "ATTR_desc" : "Promozione"
  }, {
    "ATTR_code" : "riferimento",
    "ATTR_columnName" : "Riferimento",
    "ATTR_desc" : "Riferimento"
  }, {
    "ATTR_code" : "semestre",
    "ATTR_columnName" : "MUI_Semestre",
    "ATTR_desc" : "Semestre"
  }, {
    "ATTR_code" : "datainizioist",
    "')
    || TO_CLOB('ATTR_columnName" : "DataInizioIst",
    "ATTR_desc" : "Data Inizio Ist."
  }, {
    "ATTR_code" : "datafineist",
    "ATTR_columnName" : "DataFineIst",
    "ATTR_desc" : "Data Fine Ist."
  }, {
    "ATTR_code" : "tipopromozione",
    "ATTR_columnName" : "Tipo Promozione",
    "ATTR_desc" : "Tipo Promozione"
  }, {
    "ATTR_code" : "statodesc",
    "ATTR_columnName" : "StatoDesc",
    "ATTR_desc" : "Stato Desc"
  }, {
    "ATTR_code" : "canaleanno",
    "ATTR_columnName" : "Canal')
    || TO_CLOB('e_Anno",
    "ATTR_desc" : "Canale Anno"
  }, {
    "ATTR_code" : "proximity",
    "ATTR_columnName" : "Proximity",
    "ATTR_desc" : "Proximity"
  } ]
}, {
  "DIM_code" : "promozioneriferimento",
  "DIM_columnName" : "Promozione Riferimento",
  "DIM_description" : "Promozione Riferimento",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)},[Promozione Riferimento].[Data_Inizio_Ist.], BD')
    || TO_CLOB('ESC)}} , [Promozione Riferimento].[Canale], BASC),[Promozione Riferimento].[Gruppo],BASC)}",
  "list_ATTR" : [ {
    "ATTR_code" : "anno",
    "ATTR_columnName" : "Anno",
    "ATTR_desc" : "Anno"
  }, {
    "ATTR_code" : "rif_canale",
    "ATTR_columnName" : "MUI_Canale",
    "ATTR_desc" : "Canale"
  }, {
    "ATTR_code" : "gruppo",
    "ATTR_columnName" : "MUI_Tipo Promozione Desc",
    "ATTR_desc" : "Gruppo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descri')
    || TO_CLOB('zione + Riferimento",
    "ATTR_desc" : "Promozione"
  }, {
    "ATTR_code" : "riferimento",
    "ATTR_columnName" : "Descrizione + Data",
    "ATTR_desc" : "Promozione"
  }, {
    "ATTR_code" : "semestre",
    "ATTR_columnName" : "Semestre",
    "ATTR_desc" : "Semestre"
  } ]
}, {
  "DIM_code" : "reparto",
  "DIM_columnName" : "Reparto",
  "DIM_description" : "Reparto",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}')
    || TO_CLOB(', 0)},[Reparto].[MUI_Sort],  BASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "compratore",
    "ATTR_columnName" : "Compratore",
    "ATTR_desc" : "Compratore"
  }, {
    "ATTR_code" : "sigla",
    "ATTR_columnName" : "Sigla",
    "ATTR_desc" : "Sigla"
  }, {
    "ATTR_code" : "tipelemento",
    "ATTR_columnName" : "TipoElemento",
    "ATTR_desc" : "Tipo Elemento"
  }, ')
    || TO_CLOB('{
    "ATTR_code" : "codpadre",
    "ATTR_columnName" : "cod_padre",
    "ATTR_desc" : "Cod. Padre"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "Reparto",
    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "categorymanager",
    "ATTR_columnName" : "CategoryManager",
    "ATTR_desc" : "Category Manager"
  } ]
}, {
  "DIM_code" : "categoria",
  "DIM_columnName" : "Categoria",
  "DIM_description" : "Categoria",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSourc')
    || TO_CLOB('e" : "{{Except ( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_000],[Categoria].[CAT_0000] }  ) } ,{[Categoria].[CAT_0000]}}",
  "list_ATTR" : [ {
    "ATTR_code" : "direzionedesc",
    "ATTR_columnName" : "DirezioneDesc",
    "ATTR_desc" : "Direzione"
  }, {
    "ATTR_code" : "direzione",
    "ATTR_columnName" : "Direzione",
    "ATTR_desc" : "Cod Direzione"
  }, {
    "ATTR_code" : "repartodesc",
    "ATTR_columnName" : "RepartoDesc",
')
    || TO_CLOB('    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "Reparto",
    "ATTR_desc" : "Cod Reparto"
  }, {
    "ATTR_code" : "estensionecl",
    "ATTR_columnName" : "EstensioneCL",
    "ATTR_desc" : "Estensione CL"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "raggruppamentoFoto",
  "DIM_columnName" : "Raggruppamento Foto",
  "DIM_description" : "Raggruppame')
    || TO_CLOB('nto Foto",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "tot",
    "ATTR_columnName" : "MUI_TOT",
    "ATTR_desc" : "Totali"
  }, {
    "ATTR_code" : "tots",
    "ATTR_columnName" : "MUI_TOTS",
    "ATTR_desc" : "Sub Totali"
  }, {
    "ATTR_code" : "mui_descrizione",
    "ATTR_columnName" : "MUI_Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {')
    || TO_CLOB('
    "ATTR_code" : "mui_compratore",
    "ATTR_columnName" : "MUI_Compratore",
    "ATTR_desc" : "Compratore"
  }, {
    "ATTR_code" : "mui_reparto",
    "ATTR_columnName" : "MUI_Reparto",
    "ATTR_desc" : "Reparto"
  } ]
}, {
  "DIM_code" : "spazio",
  "DIM_columnName" : "Spazio",
  "DIM_description" : "Spazio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 1)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "')
    || TO_CLOB('compratore",
    "ATTR_columnName" : "MUI_Compratore",
    "ATTR_desc" : "Compratore"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "MUI_Reparto",
    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "macroSpazioDescrizione",
    "ATTR_columnName" : "MacroSpazioDescrizione",
    "ATTR_desc" : "Macro Spazio"
  } ]
}, {
  "DIM_code" : "compratore",
  "DIM_columnName" : "Compratore",
  "DIM_description" : "Compratore",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSou')
    || TO_CLOB('rce" : "{ EXCEPT( {TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}, { [Compratore].[NA] ,  [Compratore].[S000]  }) } , { [Compratore].[S000]}",
  "list_ATTR" : [ {
    "ATTR_code" : "categorymanager",
    "ATTR_columnName" : "CategoryManager",
    "ATTR_desc" : "Category Manager"
  }, {
    "ATTR_code" : "repartodcodesc",
    "ATTR_columnName" : "MUI_Reparto",
    "ATTR_desc" : "RepartoCodDesc"
  }, {
    "ATTR_code" : "repartodesc",
    "ATTR_columnName" : "Rep')
    || TO_CLOB('artoDesc",
    "ATTR_desc" : "Reparto"
  }, {
    "ATTR_code" : "reparto",
    "ATTR_columnName" : "Reparto",
    "ATTR_desc" : "Cod Reparto"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "anno",
  "DIM_columnName" : "Anno",
  "DIM_description" : "Anno",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
  "list_A')
    || TO_CLOB('TTR" : [ {
    "ATTR_code" : "Name",
    "ATTR_columnName" : "Name",
    "ATTR_desc" : "Anno"
  } ]
}, {
  "DIM_code" : "clients",
  "DIM_columnName" : "}Clients",
  "DIM_description" : "Clients",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, DESC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "TM1_DefaultDisplayValue",
    "ATTR_columnName" : "}TM1_DefaultDisplayValue",
    "ATTR_desc" : "Client"
  } ]
}, {
')
    || TO_CLOB('
  "DIM_code" : "zonaPromo",
  "DIM_columnName" : "Zona Promo",
  "DIM_description" : "Zona Promo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SOCIETA_2] })}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "pubblicita",
')
    || TO_CLOB('  "DIM_columnName" : "Pubblicità",
  "DIM_description" : "Pubblicità",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{ EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Pubblicità] )}, 0)}, ASC)}}, { [Pubblicità].[RADIO] }) }",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "canale",
  "DIM_columnName" : "Canale",
  "DIM_description" : "Canale",
  "ENDPOINT_serverName')
    || TO_CLOB('" : "promo",
  "MDX_jsonSource" : "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Canale] )}, 0)}, [Canale].[Descrizione],ASC )}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "misuraCanale",
  "DIM_columnName" : "Misura Canale",
  "DIM_description" : "Misura Canale",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Canale] ')
    || TO_CLOB(')}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "negozio",
  "DIM_columnName" : "Negozio",
  "DIM_description" : "Negozio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "zonaPromo",
    "ATTR_columnName" : "MUI_ZonaPromo",
    "ATTR_desc" : "')
    || TO_CLOB('Zona Promo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "tipoPuntoVendita",
    "ATTR_columnName" : "Tipo Punto Vendita",
    "ATTR_desc" : "Tipo Punto Vendita"
  }, {
    "ATTR_code" : "repartoProfumeria",
    "ATTR_columnName" : "Reparto Profumeria",
    "ATTR_desc" : "Reparto Profumeria"
  } ]
}, {
  "DIM_code" : "tipoPromozione",
  "DIM_columnName" : "Tipo Promozione",
  "DIM_descript')
    || TO_CLOB('ion" : "Tipo Promozione",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "gruppo",
    "ATTR_columnName" : "GRUPPO",
    "ATTR_desc" : "Gruppo"
  } ]
}, {
  "DIM_code" : "macrospazio",
  "DIM_columnName" : "MacroSpazio",
  "DIM_description" : "M')
    || TO_CLOB('acro Spazio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "prestazioniEC",
    "ATTR_columnName" : "Prestazioni EC",
    "ATTR_desc" : "Prestazioni EC"
  }, {
    "ATTR_code" : "prestazioniCNT",
    "ATTR_columnName" : "Prestazioni CNT",
    "ATTR')
    || TO_CLOB('_desc" : "Prestazioni CNT"
  } ]
}, {
  "DIM_code" : "misuraConfigurazioneMacrospaziNeg",
  "DIM_columnName" : "Misura_Configurazione_Macrospazi_Neg",
  "DIM_description" : "Configurazione Macrospazio Neg",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"')
    || TO_CLOB('
  } ]
}, {
  "DIM_code" : "misuraConfigurazioneMacrospaziNegPromo",
  "DIM_columnName" : "Misura_Configurazione_Macrospazi_Neg_Promo",
  "DIM_description" : "Configurazione Macrospazio Neg Promo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg_Promo] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
 ')
    || TO_CLOB(' } ]
}, {
  "DIM_code" : "misuraConfigurazioneMacrospaziListino",
  "DIM_columnName" : "Misura_Configurazione_Macrospazi_Listino",
  "DIM_description" : "Misura Configurazione Macrospazi Listino",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Listino] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]')
    || TO_CLOB('
}, {
  "DIM_code" : "rep_promozione",
  "DIM_columnName" : "REP - Promozione",
  "DIM_description" : "Rep Promozione",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)}, [REP - Promozione].[Data_Inizio_Ist],DESC )} ",
  "list_ATTR" : [ {
    "ATTR_code" : "anno",
    "ATTR_columnName" : "Anno",
    "ATTR_desc" : "Anno"
  }, {
    "ATTR_code" : "canale",
    "ATTR_columnName" : "Canale",
    "ATTR_desc"')
    || TO_CLOB(' : "Canale"
  }, {
    "ATTR_code" : "tipo",
    "ATTR_columnName" : "Tipo Promozione",
    "ATTR_desc" : "Gruppo"
  }, {
    "ATTR_code" : "riferimento",
    "ATTR_columnName" : "Riferimento",
    "ATTR_desc" : "Riferimento"
  }, {
    "ATTR_code" : "semestre",
    "ATTR_columnName" : "MUI_Semestre",
    "ATTR_desc" : "Semestre"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione + Data",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "datain')
    || TO_CLOB('izioist",
    "ATTR_columnName" : "DataInizioIst",
    "ATTR_desc" : "Data Inizio Ist."
  }, {
    "ATTR_code" : "datafineist",
    "ATTR_columnName" : "DataFineIst",
    "ATTR_desc" : "Data Fine Ist."
  }, {
    "ATTR_code" : "tipopromozione",
    "ATTR_columnName" : "Tipo Promozione",
    "ATTR_desc" : "Tipo Promozione"
  }, {
    "ATTR_code" : "statodesc",
    "ATTR_columnName" : "StatoDesc",
    "ATTR_desc" : "Stato Desc"
  }, {
    "ATTR_code" : "canaleanno",
    "ATTR_colu')
    || TO_CLOB('mnName" : "Canale_Anno",
    "ATTR_desc" : "Canale Anno"
  } ]
}, {
  "DIM_code" : "rep_compratore",
  "DIM_columnName" : "REP - Compratore",
  "DIM_description" : "Rep Compratore",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_categ')
    || TO_CLOB('oria",
  "DIM_columnName" : "REP - Categoria",
  "DIM_description" : "Rep Categoria",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_misuraTimone",
  "DIM_columnName" : "REP - Misura Timone",
  "DIM_description" : "Rep Misura Timone",
 ')
    || TO_CLOB(' "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Timone] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_scenario",
  "DIM_columnName" : "REP - Scenario",
  "DIM_description" : "Rep Scenario",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSE')
    || TO_CLOB('TALL( [REP - Scenario] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "categoriadesc",
    "ATTR_columnName" : "Categoria Desc",
    "ATTR_desc" : "Categoria Desc"
  }, {
    "ATTR_code" : "grmdesc",
    "ATTR_columnName" : "GRM Desc",
    "ATTR_desc" : "GRM Desc"
  }, {
    "ATTR_code" : "subgrmdesc",
    "ATTR_columnName" : "SubGrm Desc",
    "ATTR_desc" : "Su')
    || TO_CLOB('bGrm Desc"
  } ]
}, {
  "DIM_code" : "rep_articolo",
  "DIM_columnName" : "REP - Articolo",
  "DIM_description" : "Rep Articolo",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 1)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "categoriadesc",
    "ATTR_columnName" : "Categoria Desc",
    "ATTR_desc" : "Categoria Desc"
  }, {
    "ATTR_code" : "grmdesc",
    "ATTR_columnName" : "GRM Desc",
    "ATTR_')
    || TO_CLOB('desc" : "GRM Desc"
  }, {
    "ATTR_code" : "subgrmdesc",
    "ATTR_columnName" : "SubGrm Desc",
    "ATTR_desc" : "SubGrm Desc"
  } ]
}, {
  "DIM_code" : "rep_fornitore",
  "DIM_columnName" : "REP - Fornitore",
  "DIM_description" : "Rep Fornitore",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
')
    || TO_CLOB('    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_zonaPromo",
  "DIM_columnName" : "REP - Zona Promo",
  "DIM_description" : "Rep Zona Promo",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_sezioneTematica",
  "DIM_column')
    || TO_CLOB('Name" : "REP - Sezione Tematica",
  "DIM_description" : "Rep Sezione Tematica",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_meccanicaSemplice",
  "DIM_columnName" : "REP - Meccanica Semplice",
  "DIM_description" : "Rep Meccanic')
    || TO_CLOB('a Semplice",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_avolantino",
  "DIM_columnName" : "REP - AVolantino",
  "DIM_description" : "Rep AVolantino",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {T')
    || TO_CLOB('M1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_MisuraReportingArticoloZona",
  "DIM_columnName" : "REP - Misura Reporting Articolo Zona",
  "DIM_description" : "Rep Articolo Zona",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting A')
    || TO_CLOB('rticolo Zona] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_MisuraReportingArticolo",
  "DIM_columnName" : "REP - Misura Reporting Articolo",
  "DIM_description" : "Rep Articolo",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
  ')
    || TO_CLOB('  "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "rep_reparto",
  "DIM_columnName" : "REP - Reparto",
  "DIM_description" : "Rep Reparto",
  "ENDPOINT_serverName" : "reporting",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ')
    || TO_CLOB(']
}, {
  "DIM_code" : "fornitore",
  "DIM_columnName" : "Fornitore",
  "DIM_description" : "Fornitore",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "articoloFittizio",
  "DIM_columnName" : "Articolo Fittizio",
  "DIM_description" : "Articolo Fitti')
    || TO_CLOB('zio",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }, {
    "ATTR_code" : "compratore",
    "ATTR_columnName" : "Compratore",
    "ATTR_desc" : "Compratore"
  } ]
}, {
  "DIM_code" : "sezioneTematica",
  "DIM_columnName" : "Sezione Tematica",
  "DIM_description" :')
    || TO_CLOB(' "Sezione Tematica",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "scenario",
  "DIM_columnName" : "Scenario",
  "DIM_description" : "Scenario",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETA')
    || TO_CLOB('LL( [Scenario] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "misuraTimone",
  "DIM_columnName" : "Misura Timone.",
  "DIM_description" : "Misura",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "D')
    || TO_CLOB('escrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "contratto",
  "DIM_columnName" : "Contratto",
  "DIM_description" : "Contratto",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "prestazione",
  "DIM_columnName" : "Prestazione"')
    || TO_CLOB(',
  "DIM_description" : "Prestazione",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "iniziativa",
  "DIM_columnName" : "Iniziativa",
  "DIM_description" : "Iniziativa",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTER')
    || TO_CLOB('BYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "Descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  } ]
}, {
  "DIM_code" : "articolo",
  "DIM_columnName" : "Articolo",
  "DIM_description" : "Articolo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 1)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "categoriadesc",
    "ATTR_column')
    || TO_CLOB('Name" : "Categoria Desc",
    "ATTR_desc" : "Categoria Desc"
  }, {
    "ATTR_code" : "grmdesc",
    "ATTR_columnName" : "GRM Desc",
    "ATTR_desc" : "GRM Desc"
  }, {
    "ATTR_code" : "subgrmdesc",
    "ATTR_columnName" : "SubGrm Desc",
    "ATTR_desc" : "SubGrm Desc"
  }, {
    "ATTR_code" : "attivo",
    "ATTR_columnName" : "Attivo",
    "ATTR_desc" : "Attivo"
  }, {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "Descrizione",
    "ATTR_desc" : "Descrizione"
  }')
    || TO_CLOB(' ]
}, {
  "DIM_code" : "misuraProgrammazioneFornitore",
  "DIM_columnName" : "Misura Programmazione Fornitore",
  "DIM_description" : "Misura Programmazione Fornitore",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Programmazione Fornitore] )}, 0)}, ASC)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "visualizzazione",
    "ATTR_columnName" : "Visualizzazione",
    "ATTR_desc" : "Visualizzazione"
  } ]
}, {
  "DIM_code" : "s')
    || TO_CLOB('pazioProgressivo",
  "DIM_columnName" : "SpazioProgressivo",
  "DIM_description" : "Spazio Progressivo",
  "ENDPOINT_serverName" : "promo",
  "MDX_jsonSource" : "{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [SpazioProgressivo] )}, 0)}}",
  "list_ATTR" : [ {
    "ATTR_code" : "descrizione",
    "ATTR_columnName" : "MUI_Descrizione",
    "ATTR_desc" : "Spazio"
  } ]
} ]'),'GLOBAL');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('59','/Admin/Gestione_ACL',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('60','/Admin/Gestione_Applicazione',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('61','/Admin/Gestione_Configurazioni',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('62','/Admin/Gestione_Menu',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('63','/Admin/Gestione_Ruoli',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('64','/Admin/Gestione_UI',' ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('65','/Admin/Gestione_Utenti',' {
    "clients":["TM1_DefaultDisplayValue"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('66','/Fatturazione/Associazione_Articoli_Promozione',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "spazioProgressivo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('76','/Pianificazione/Selezione_Promo',' {
	"promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"tipoPromozione":["descrizione"],
	"prestazione":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('77','/Piano_Annuale/Controllo_Negozi',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('82','/Piano_Annuale/Gestione_Iniziative',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
     "iniziativa":["Descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('92','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "macrospazio":["descrizione"],
    "negozio":["zonaPromo","descrizione"],
    "misuraConfigurazioneMacrospaziNegPromo":["descrizione"]
}
 ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('98','/Reporting/Dettaglio_Zona',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_zonaPromo":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('99','/Reporting/Sintesi_Campagna',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_categoria":["descrizione"],
	"rep_misuraTimone":["descrizione"],
	"rep_scenario":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('100','/Reporting/Storico_articolo_per_zona_(ACQ)',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_zonaPromo":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('101','/Reporting/Storico_articolo_per_zona_(MKT)',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_zonaPromo":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('103','/Analisi_Budget-Venduto/Articolo',' {
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
    "rep_fornitore":["descrizione"],
    "rep_zonaPromo":["descrizione"],
    "rep_sezioneTematica":["descrizione"],
    "rep_meccanicaSemplice":["descrizione"],
    "rep_avolantino":["descrizione"],
    "rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('104','/Analisi_Budget-Venduto/Categoria',' {
    "rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "rep_compratore":["descrizione"],
    "rep_categoria":["descrizione"],
    "rep_misuraTimone":["descrizione"],
    "rep_scenario":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('93','/Piano_Annuale/Spazi/Macrospazi_Negozi',' {
    "macrospazio":["descrizione" ],
    "negozio":["zonaPromo","descrizione" ],
    "misuraConfigurazioneMacrospaziNeg":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('94','/Piano_Annuale/Spazi/Macrospazi',' {
    "macrospazio":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('86','/Piano_Annuale/Timing',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('90','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',' {
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('150','/Proximity/Timing','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('151','/Proximity/Negozi_Promo','{
    "promozione":["anno", "canale","tipo","descrizione","riferimento","semestre","proximity"],
    "negozio":["descrizione","zonaPromo" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('152','/Proximity/Proiezioni','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('153','/Proximity/Articoli_per_Negozio','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
	"negozio":["descrizione","zonaPromo" ],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('154','/Proximity/Set_per_Negozio','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('155','/Proximity/Timing',TO_CLOB('{
    "connection": "promo",
    "configurations": [
      {
        "name": "gc_Timing",
        "logMemory": true,
        "logTime": true,
        "skip": true,
        "pagination": false ,
        "title": "Timing",
"height": 60,

         "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Versione": "{[Versione].[UFF]}" ,
            "Promozione": " {FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( ')
    || TO_CLOB('[Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] = ''Y'')}   "
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Promozione Proprietà] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Proprietà]"
      }')
    || TO_CLOB(',
        "ExecuteMDX": {
          "Members": [
              "Name",
			  "Level",
        	  "Attributes",
			  "UniqueName"
          ] ,
          "Cells": [
            "Ordinal",
            "Value",
            "Updateable",
            "Consolidated" ,
            "HasPicklist",
            "PicklistValues"
          ]
        } ,
         "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                      ')
    || TO_CLOB('           , "field": "Promozione.MUI_Descrizione"
                                 , "headerName": "Promozione"
                                 , "width":500
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":')
    || TO_CLOB('"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Riferimento","field":"Promozione.Riferimento","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione + Data","field":"Promozione.MUI_')
    || TO_CLOB('Descrizione","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ ')
    || TO_CLOB('"TM1DataColumnDate"]},
          {"headerName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}
]
      }
    ]
  }
  '),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('156','/Proximity/Negozi_Promo',TO_CLOB('{
	"connection": "promo",
	"configurations": [
		{
			"name": "gc_NegoziPromo",
			"logMemory": true,
			"logTime": true,
			"skip": true,
"title": "Negozi Promo",
"height": 60,
			 "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "Negozio": "{[Negozio].[Proximity]}"
                  }
              },
              "COLS": {
                  "NON_EMPTY": true,
                  "DIMENSIO')
    || TO_CLOB('NS": {
                    "Promozione": "{[Promozione].[(I) Promozione Proximity]}"
                  }
              },
              "FROM": "[PROX Configurazione Negozio Promozione]"

          },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
                        "Attributes/Descrizione",
                        "Attributes/MUI_DescrizioneData",
                        "UniqueName"
                           ')
    || TO_CLOB('   ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    },

          "DynamicColumns" : true ,
          "DynamicColumnsSettings" :{
              "headerconf" : ["MUI_DescrizioneData"]  ,
              "headerdefaults":  {"marryC')
    || TO_CLOB('hildren" : true}  ,
              "childrendefaults":  {"width":300,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnText"] },
              "childrenCustomTypes"  : {
                               }
              } ,

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Negozio.Descrizione"
                  ')
    || TO_CLOB('               , "headerName": "Negozio"
                                 , "width":300
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
            {"headerName":"Negozio","field":"Negozio.Descrizione","width":250,"hide":true,"rowGroup": true , "editable": true,"type":["TM1DataColumnText"]}

              ]
            ,
            "actions": [{
                "componen')
    || TO_CLOB('tId": "refresh_assort_negozi_promo_prox",
                "componentLabel": "Creazione Macrospazio",
                "process": "MUI_DUMMY_Esegui Prox Cube Programmazione Fornitori Articoli Gestione Main",
                "timeout":-1,
                "refresh": ["gc_NegoziPromo"],
                "params":[{
                  "paramName": "pCompratore",
                  "label": "Inserire il Compratore",
                  "defaultValue":"TOT_COMP",
                  "hasPicklist": fal')
    || TO_CLOB('se
                }]
            }],

			"rowSuppressionEnabled": true,
            "colSuppressionEnabled": false,
            "preSelections": [
               {
                  "dimension": "Promozione",
                  "dimCode": "promozione",
                  "dimColumnName": "Promozione",
                  "attribute": "Descrizione",
                  "attrCode": "descrizione",
                  "attrColumnName": "MUI_Descrizione",
                  "process": "MUI_DUMM')
    || TO_CLOB('Y_ConfigurazioneSubsetProximity",
                  "paramName": "inPromo",
                  "refresh": ["gc_NegoziPromo"]
                }
              ]
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('157','/Proximity/Proiezioni',TO_CLOB('{
    "connection": "promo",
    "configurations": [
      {
      "name": "gc_Proiezioni",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
"title": "Proiezioni",
"height": 60,

        "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione Proximity]}",
            "Articolo": "{[Articolo].[(I) Articoli lev.0]}",
            "Compratore":')
    || TO_CLOB(' "{[Compratore].[(I) Compratori lev.0]}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "MisuraPromozionePianificazione": "{TM1SORT( {FILTER( {TM1SUBSETALL( [Misura Promozione Pianificazione] )}, [Misura Promozione Pianificazione].[Proximity Subset] = \"1\")}, ASC)}"
          }
        },
        "FROM": "[Promozione Pianificazione]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        ')
    || TO_CLOB('}
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
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
        ],
        "headerdefaults": {
          "marry')
    || TO_CLOB('Children": true
        },
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": true,
          "type": ["TM1DataColumnText"],
          "cellClass": "cellClass-left-text"
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
       ')
    || TO_CLOB('   },
          "PUNTI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellClass-right-text"
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "headerName": "Articolo",
        "cellClass": "cellClass-left-text",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
     ')
    || TO_CLOB(' },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.MUI_Descrizione",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo(Codice)",
          "field": "Articolo.DescrizioneCODICE",
          "cellClass": "cellClass-left')
    || TO_CLOB('-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
        ')
    || TO_CLOB('  ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetProximity",
          "paramName": "inPromo",
          "refresh": ["gc_Proiezioni"]
        },
         {
          "dimension": "Comprato')
    || TO_CLOB('re",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_Proiezioni"]
        }
      ],
      "styleRules": {}
      }
    ]
  }
  '),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('158','/Proximity/Articoli_per_Negozio',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_ArticoliPerNegozio",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "pagination": false ,
"title": "Articoli per Negozio",
"height": 60,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Negozio": "{[Negozio].[Proximity]}",
            "Articolo": "{[Articolo].[(I) Articoli lev.0]}",
            "Compratore": "{[Compratore]')
    || TO_CLOB('.[(I) Compratori lev.0]}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione Proximity]}",
              "PROX Report Promozione Assortimento Negozio": "{[PROX Report Promozione Assortimento Negozio].[Lev 0]}"
          }
        },
        "FROM": "[PROX Report Promozione Assortimento Negozio]"
      },
      "ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"At')
    || TO_CLOB('tributes/Descrizione",
              	"Attributes/DescrizioneArticolo",
                "Attributes/MUI_DescrizioneData",
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
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Articolo.DescrizioneArticolo",
			"headerName": "Articolo",
			"width": 600,
			"pinned": "left",
			"typ')
    || TO_CLOB('e": ["TM1Element"]
		},
      	"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
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
					"headerClass')
    || TO_CLOB('": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 200,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",

				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnNumber", "numericColumn"]
			},
			"childrenCustomTypes": {

			}
		},


      "columnDefs": [
        {"headerName":"Negozio","field":"Negozio.Descrizione","width":250,"hide":true,"rowGroup": true , "editable": false,"t')
    || TO_CLOB('ype":["TM1Element"]}
      ]
      ,"preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetProximity",
          "paramName": "inPromo",
          "refresh": ["gc_ArticoliPerNegozio"]
        },
         {
          "dimensi')
    || TO_CLOB('on": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_ArticoliPerNegozio"]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('159','/Proximity/Set_per_Negozio',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SetPerNegozio",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "pagination" : false,
    "title" : "Set Per Negozio",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{TM1SORT( {FILTER( {TM1SUBSETALL( [Promozione] )}, [Promozione].[Proximity] = ''Y'')}, ASC)}",
          "Compratore" : "{ EXCEPT( {TM1SORT( {TM1FIL')
    || TO_CLOB('TERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}, { [Compratore].[NA], [Compratore].[S000] }) }",
          "Negozio" : "{[Negozio].[Tutto]}",
          "FamigliaSet" : "[Famiglia Set].[Lev0]",
          "Articolo" : "{[Articolo].[Tutto]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "PROX Report Articoli non in SET" : "{[PROX Report Articoli non in SET].[In Assortimento]}"
        }
      },
      "FROM" : "[PROX Report Art')
    || TO_CLOB('icoli non in SET]"
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/DescrizioneArticolo", "Attributes/MUI_DescrizioneData", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist", "PicklistValues" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.DescrizioneArticolo",
      "headerName" : "Articolo",
    ')
    || TO_CLOB('  "width" : 600,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
     ')
    || TO_CLOB('   "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 200,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnNumber", "numericColumn" ]
      },
      ')
    || TO_CLOB('"childrenCustomTypes" : { }
    },
    "columnDefs" : [ {
      "headerName" : "Negozio",
      "field" : "Negozio.Descrizione",
      "width" : 250,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName')
    || TO_CLOB('" : "MUI_Descrizione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetProximity",
      "paramName" : "inPromo",
      "refresh" : [ "gc_SetPerNegozio" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_SetPerNegozio" ]
    } ]
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1001','/Pianificazione/Informazioni_Aggiuntive_(MKT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_informazioniAggiuntiveMKT",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Aggiungi Articoli",
    "height" : 58,
    "maxCells" : 750000,
    "Misura Promozione Pianificazione 2" : "{([Misura Promozione Pianificazione].[Configurazione Subset Pianificazione 1])}",
    "Misura Promozione Pianificazione" : "({FILTER(TM1SUBSETALL([Misura Promozione Pianificazione]),[}ElementAttributes_M')
    || TO_CLOB('isura Promozione Pianificazione].([}ElementAttributes_Misura Promozione Pianificazione].[Ordinamento])>0 )})",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Compratore" : "{{TM1SO')
    || TO_CLOB('RT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
          "Articolo" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Misura Promozione Pianificazione" : "{([Misura Promozione Pianificazione].[I - Misura Promozione Pianificazione Media (MKTG)])}"
        }
      },
      "FROM" : "[Promozione Pianificazione]",
      "WHERE" : {
        "')
    || TO_CLOB('Scenario" : "[BDG]",
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/MUI_Descrizione", "Attributes/Descrizione", "Attributes/DescrizioneCODICE", "Attributes/Fornitore", "Attributes/RepartoDesc", "Attributes/CategoriaDesc", "Attributes/GRMDesc", "Attributes/SubGrmDesc", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings"')
    || TO_CLOB(' : {
      "headerconf" : [ "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "childrendefaults" : {
        "width" : 100,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnText" ],
        "cellClass" : "cellClass-left-text"
      },
      "childrenCustomTypes" : {
        "DataInizio" : {
          "type" : [ "TM1D')
    || TO_CLOB('ataColumnDate" ],
          "cellClass" : "cellClass-right-text"
        },
        "DataFine" : {
          "type" : [ "TM1DataColumnDate" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_ATT_ANN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_MIN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_MAX" : {
     ')
    || TO_CLOB('     "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "PRZ_ATT_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "_perc__SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "VAL_SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
   ')
    || TO_CLOB('     "PRZ_PROMO" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_AN" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_USR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR__perc__IN_FATT" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "')
    || TO_CLOB('cellClass-right-text"
        },
        "CST_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_PROMO_C_IVA" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "N_PEZZI" : {
          "type" : [ "TM1DataColumnDecimal3" ],
          "cellClass" : "cellClass-right-text"
        },
        "COLLI" : {
          "type" : [ "TM1DataColumnDecimal')
    || TO_CLOB('3" ],
          "cellClass" : "cellClass-right-text"
        },
        "TOT_dot_VEND" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "F_FATT" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "IVA" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "RIFATT_BS" : {
          "ty')
    || TO_CLOB('pe" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "LIM_UTIL" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_SC" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR_%_IN_FATT" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "P')
    || TO_CLOB('EZZ" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "VEND_PROMO_S_IVA" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "CST_PROMO" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "ML_I_UNI" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-rig')
    || TO_CLOB('ht-text"
        },
        "ML" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "%_ML" : {
          "type" : [ "TM1DataColumnPercentage" ],
          "cellClass" : "cellClass-right-text"
        },
        "CONTR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass" : "cellClass-right-text"
        },
        "EXTRA_CONTR" : {
          "type" : [ "TM1DataColumnNumber" ],
          "cellClass')
    || TO_CLOB('" : "cellClass-right-text"
        },
        "NumeroNegozi" : {
          "type" : [ "TM1DataColumnInteger" ],
          "cellClass" : "cellClass-right-text"
        },
        "PUNTI" : {
          "type" : [ "TM1DataColumnDecimal3" ],
          "cellClass" : "cellClass-right-text"
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Articolo.Descrizione",
      "headerName" : "Articolo",
')
    || TO_CLOB('
      "cellClass" : "cellClass-left-text",
      "width" : 400,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Promozione",
      "field" : "Promozione.MUI_Descrizione",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Articolo(Codice)",
      "field" : "Articolo.Descriz')
    || TO_CLOB('ioneCODICE",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.Descrizione",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Fornitore",
 ')
    || TO_CLOB('     "field" : "Articolo.Fornitore",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Articolo.RepartoDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerNam')
    || TO_CLOB('e" : "Categoria",
      "field" : "Articolo.CategoriaDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Grm",
      "field" : "Articolo.GRMDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
')
    || TO_CLOB('      "headerName" : "Sub Grm",
      "field" : "Articolo.SubGrmDesc",
      "cellClass" : "cellClass-left-text",
      "width" : 100,
      "hide" : false,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "preSelections" : [ {
      "dimension" : "Promozione",
      "dimCode" : "promozione",
      "dimColumnName" : "Promozione",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "MUI_Descri')
    || TO_CLOB('zione",
      "process" : "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
      "paramName" : "inPromo",
      "refresh" : [ "gc_informazioniAggiuntiveMKT" ]
    }, {
      "dimension" : "Compratore",
      "dimCode" : "compratore",
      "dimColumnName" : "Compratore",
      "attribute" : "Descrizione",
      "attrCode" : "descrizione",
      "attrColumnName" : "Descrizione",
      "process" : "",
      "paramName" : "",
      "refresh" : [ "gc_informazioniAggiuntiveMKT" ]
    } ]')
    || TO_CLOB(',
    "styleRules" : { }
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('1002','/Pianificazione/Informazioni_Aggiuntive_(MKT)','{
  "promozione" : [ "anno", "canale", "tipo", "descrizione", "riferimento", "semestre", "proximity" ],
  "compratore" : [ "categorymanager", "repartodcodesc", "repartodesc", "reparto", "descrizione" ],
  "categoria" : [ "descrizione" ],
  "fornitore" : [ "descrizione" ],
  "articolo" : [ "categoriadesc", "grmdesc", "subgrmdesc", "attivo" ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('118','/Timone/Foto_Speciali/Target_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Target",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Target",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{[Compratore].[MUI_Timone_FotoSpec_Target_CAT]}",
         ')
    || TO_CLOB(' "Categoria" : "{[Categoria].[MUI_Timone_FotoSpec_Target_CAT]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}')
    || TO_CLOB('}",
          "Sezione Tematica" : "{{TM1SUBSETALL( [Sezione Tematica] )}}",
          "Misura Timone." : "{EXCEPT( {[Misura Timone.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]} ) }"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attributes/Cat')
    || TO_CLOB('egoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings"')
    || TO_CLOB(' : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "MUI_Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
     ')
    || TO_CLOB('   "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "ASSORT_P" : {
          "type" : [ "TM1DataColumnText" ]
        },
 ')
    || TO_CLOB('       "ST_0000" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : tru')
    || TO_CLOB('e,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
     ')
    || TO_CLOB(' "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0) ')
    || TO_CLOB(' || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore')
    || TO_CLOB('.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name))')
    || TO_CLOB(' || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('119','/Timone/Foto_Speciali/Target_(CAT)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('120','/Timone/Foto_Speciali/Target_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_FotoSpeciali_Target",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Foto Speciali Target",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{ [Compratore].[MUI_Timone_FotoSpec_Target_ACQ] }",
       ')
    || TO_CLOB('   "Categoria" : "{ [Categoria].[MUI_Timone_FotoSpec_Target_ACQ]  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_')
    || TO_CLOB('MKT]}}",
          "Sezione Tematica" : "{ EXCEPT({{TM1SUBSETALL( [Sezione Tematica] )}}  ,{[Sezione Tematica].[ST_0000]}  )} ",
          "Misura Timone." : "{ EXCEPT({[Misura Timone.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]}  ) }"
        }
      },
      "FROM" : "[Timone Categoria Sezione]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizion')
    || TO_CLOB('eData", "Attributes/MUI_Descrizione", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "')
    || TO_CLOB('DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "MUI_Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",')
    || TO_CLOB('
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "ASSORT_P" : {
    ')
    || TO_CLOB('      "type" : [ "TM1DataColumnText" ]
        },
        "ST_000" : {
          "hide" : false
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width')
    || TO_CLOB('" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
 ')
    || TO_CLOB('     "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore')
    || TO_CLOB('.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Cate')
    || TO_CLOB('goria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12')
    || TO_CLOB(''',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('121','/Timone/Foto_Speciali/Target_(ACQ)',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('122','/Timone/Spazi_Campagna/Target_Reparto_(CAT)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SpaziCampagna_TargetReparto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
                "title":"Target Reparto",
    "height": 60,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBS')
    || TO_CLOB('ETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Reparto": "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
      ')
    || TO_CLOB('  "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NET')
    || TO_CLOB('TO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        		"Attributes/Canale",
         	   "Attributes/Riferimento",
        		"Attribut')
    || TO_CLOB('es/TipoElemento",
"UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      ')
    || TO_CLOB('},
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
            "headerClass":')
    || TO_CLOB(' "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 110,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "open",
          "editable": true,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
    ')
    || TO_CLOB('    },
        "childrenCustomTypes": {
          "N_ART_PROMO":  {
            "columnGroupShow":"always"
          },
          "TOT_FOTO":  {
            "columnGroupShow":"always"
          },
          "SPZ_CAMP":  {
            "columnGroupShow":"always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
      ')
    || TO_CLOB('    "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione + Data",
          "field": "Promozione.MUI_Descrizione",
          "width"')
    || TO_CLOB(': 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "')
    || TO_CLOB('field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": {
        "row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
        "row_style_2": "data.Compratore.Name==''S00')
    || TO_CLOB('0'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [{
            "com')
    || TO_CLOB('ponentId": "tim_scr_inizializza",
            "componentLabel": "Inizializza Spazio",
            "process": "MUI_DUMMY_CUB.Timone Reparto Inizializza Spazi",
            "timeout":-1,
            "refresh": ["gc_SpaziCampagna_TargetReparto"],
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
            ')
    || TO_CLOB('  "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            }]
          }]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('123','/Timone/Spazi_Campagna/Target_Reparto_(CAT)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('124','/Timone/Spazi_Campagna/Target_Reparto_(ACQ)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SpaziCampagna_TargetReparto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
                "title":"Target Reparto",
    "height": 60,

      "rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
       "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_S')
    || TO_CLOB('PEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
       "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
      "MDX": {
        "ROWS": {
          "')
    || TO_CLOB('NON_EMPTY": false   ,
          "DIMENSIONS": {
            "Promozione": "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
            "Reparto": "{[Reparto].[MUI_Timone_Spazi_Rep_ACQ]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false   ,
          "DIMENSIONS": {
            ')
    || TO_CLOB('"Scenario": " {[Scenario].[RIF_MKT_DT],[Scenario].[TGT_REP]}",
             "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''TOT_FOTO'')}, ALL, RECURSIVE )},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''SPZ_CAMP'')}, ALL, RECURSIVE )}}"
          }
        },
        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[')
    || TO_CLOB('Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        		"Attributes/Canale",
				"Attributes/Name",
         	   "Attributes/Riferimento",
        		"Attributes/TipoElemento",
"UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },')
    || TO_CLOB('
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryC')
    || TO_CLOB('hildren": true
        },
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
        "childre')
    || TO_CLOB('ndefaults": {
          "width": 110,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "open",
          "editable": true,
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_ART_PROMO":  {
            "columnGroupShow":"always"
          },
          "TOT_FOTO":  {
            "columnGroupShow":"always"
          },
')
    || TO_CLOB('
          "SPZ_CAMP":  {
            "columnGroupShow":"always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promozione.Canale",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
         {
          "headerName": "Descrizione",
          "field": "Promozione.Riferimento",
          ')
    || TO_CLOB('"width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field": "Promozione.Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione +')
    || TO_CLOB(' Data",
          "field": "Promozione.MUI_Descrizione",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Reparto.Descrizione",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
')
    || TO_CLOB('        },
        {
          "headerName": "Cod Reparto",
          "field": "Reparto.Name",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Tipo Elemento",
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "ty')
    || TO_CLOB('pe": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": {
        "row_style_1": "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Reparto.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' ')
    || TO_CLOB('&& data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": []
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('125','/Timone/Spazi_Campagna/Target_Reparto_(ACQ)',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('126','/Timone/Spazi_Campagna/Target_Categoria_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetCategoria",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : true,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{[Compratore].[MUI_Timone_Spazi_Cat_CAT]}",
          "Categoria" : "{[Categoria]')
    || TO_CLOB('.[MUI_Timone_Spazi_Cat_CAT]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : ')
    || TO_CLOB('"{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Misura Timone.].[ASSORT_P]}  }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" ')
    || TO_CLOB(': {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descr')
    || TO_CLOB('izione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDe')
    || TO_CLOB('fault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
   ')
    || TO_CLOB('     "SPZ_CAMP" : {
          "columnGroupShow" : "always"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "fiel')
    || TO_CLOB('d" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "fiel')
    || TO_CLOB('d" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Cate')
    || TO_CLOB('goria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_')
    || TO_CLOB('REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Comprato')
    || TO_CLOB('re.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' ||')
    || TO_CLOB(' data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('127','/Timone/Spazi_Campagna/Target_Categoria_(CAT)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('128','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_SpaziCampagna_TargetCategoria",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "rowSuppressionEnabled" : false,
    "colSuppressionEnabled" : false,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Compratore" : "{[Compratore].[MUI_Timone_Spazi_Cat_ACQ]}",
          "Categoria" : "{[Categoria')
    || TO_CLOB('].[MUI_Timone_Spazi_Cat_ACQ]}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
     ')
    || TO_CLOB('     "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}   }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    }')
    || TO_CLOB(',
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/MUI_Descrizione", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults"')
    || TO_CLOB(' : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childr')
    || TO_CLOB('endefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "N_ART_PROMO" : {
          "columnGroupShow" : "always"
        },
        "TOT_FOTO" : {
          "columnGroupShow" : "always"
        },
        "SPZ_CAMP" : {
          "columnGroupShow" ')
    || TO_CLOB(': "always"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width"')
    || TO_CLOB(' : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
   ')
    || TO_CLOB('   "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" :')
    || TO_CLOB(' true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)"')
    || TO_CLOB(',
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Comprator')
    || TO_CLOB('e.Name != ''S000''",
      "nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Comp')
    || TO_CLOB('ratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('129','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('130','/Timone/Target_Categoria/Promo_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX_Comment" : "Ã¿ stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
    ')
    || TO_CLOB('      "Compratore" : "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categor')
    || TO_CLOB('ia] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
        ')
    || TO_CLOB('  "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
        }
      },
      "FROM" : "[Timone Categori')
    || TO_CLOB('a]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
  ')
    || TO_CLOB('    },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
      ')
    || TO_CLOB('  "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open"')
    || TO_CLOB(',
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDU')
    || TO_CLOB('TO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" ')
    || TO_CLOB(': true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
 ')
    || TO_CLOB('     "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' &&')
    || TO_CLOB(' data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level =')
    || TO_CLOB('= 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data')
    || TO_CLOB('.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('131','/Timone/Target_Categoria/Promo_(CAT)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('132','/Timone/Target_Categoria/Promo_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 500000,
    "title" : "Target Categoria",
    "height" : 60,
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
     ')
    || TO_CLOB('   "DIMENSIONS" : {
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{  {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETAL')
    || TO_CLOB('L( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
       ')
    || TO_CLOB('   "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO')
    || TO_CLOB('_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
 ')
    || TO_CLOB('   },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      ')
    || TO_CLOB('"headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide')
    || TO_CLOB('" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          ')
    || TO_CLOB('"columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerN')
    || TO_CLOB('ame" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Li')
    || TO_CLOB('vello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Co')
    || TO_CLOB('mpratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  ",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_')
    || TO_CLOB('01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',')
    || TO_CLOB('''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('133','/Timone/Target_Categoria/Promo_(ACQ)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('134','/Timone/Target_Categoria/Data_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Categoria (data)",
    "height" : 60,
    "MDX_Comment" : "Ã¿ stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {')
    || TO_CLOB('
          "Compratore" : "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [C')
    || TO_CLOB('ategoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
  ')
    || TO_CLOB('        "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
        }
      },
      "FROM" : "[Timone C')
    || TO_CLOB('ategoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : t')
    || TO_CLOB('rue
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomTypes" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
')
    || TO_CLOB('
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" :')
    || TO_CLOB(' "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow" : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : ')
    || TO_CLOB('"$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Compratore",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      ')
    || TO_CLOB('"hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : t')
    || TO_CLOB('rue,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_C')
    || TO_CLOB('OMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_')
    || TO_CLOB('Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' ')
    || TO_CLOB('&& data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('143','/Timone/Target_Reparto/Promo_(CAT)','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('144','/Timone/Target_Reparto/Promo_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Pro')
    || TO_CLOB('mozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura ')
    || TO_CLOB('Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/DataInizioIst", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
   ')
    || TO_CLOB(' "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "h')
    || TO_CLOB('eaderName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data",
      "field" : "Promozione.DataInizioIst",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descrizion')
    || TO_CLOB('e",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
      ')
    || TO_CLOB('"rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$')
    || TO_CLOB('N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : ')
    || TO_CLOB('"always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
')
    || TO_CLOB('
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowG')
    || TO_CLOB('roup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [')
    || TO_CLOB(' "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Venduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_D')
    || TO_CLOB('T$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "')
    || TO_CLOB('weightedAvg",
        "aggFuncParam" : "$VENDUTO_PROMO_NETTO",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
    ')
    || TO_CLOB('    "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColum')
    || TO_CLOB('nInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
')
    || TO_CLOB('
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
')
    || TO_CLOB('
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. ')
    || TO_CLOB('Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
  ')
    || TO_CLOB('      "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
      ')
    || TO_CLOB('  "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
     ')
    || TO_CLOB(' }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80')
    || TO_CLOB(',
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        ')
    || TO_CLOB('"editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "')
    || TO_CLOB('field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
     ')
    || TO_CLOB('   "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" ')
    || TO_CLOB(': false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "num')
    || TO_CLOB('ericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width')
    || TO_CLOB('" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "edita')
    || TO_CLOB('ble" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
')
    || TO_CLOB('        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup"')
    || TO_CLOB(' : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ ')
    || TO_CLOB('"TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Reparto.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_R')
    || TO_CLOB('EP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_trd_inizializza",
  ')
    || TO_CLOB('    "componentLabel" : "Inizializza Target",
      "process" : "MUI_DUMMY_CUB.TimoneRIF.TGT4",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Promo" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozi')
    || TO_CLOB('one",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      }, {
        "dimension" : "Reparto",
        "attribute" : "Descrizione",
        "paramName" : "inReparto",
        "label" : "Reparto",
        "hasPicklist" : true
      } ]
    } ],
     "css" : ".ag-row-group.ag-row-level-1{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('145','/Timone/Target_Reparto/Promo_(ACQ)','{
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('147','/Reporting/Storico_Articolo_Zona_Promo',' {
    "rep_promozione":["anno","canale","tipo","descrizione","canaleanno"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"],
	"rep_zonaPromo":["descrizione"],
	"rep_sezioneTematica":["descrizione"],
	"rep_meccanicaSemplice":["descrizione"],
	"rep_avolantino":["descrizione"],
	"rep_MisuraReportingArticoloZona":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('146','/Reporting/Storico_Articolo_Zona_Promo',TO_CLOB('{
  "connection": "reporting",
  "configurations": [
    {
      "name": "gc_StoricoArticoloZonaPromo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells":1000000 ,
      "title": "Storico articolo zona promo",
"height": 60,
      "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
      "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
      "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo')
    || TO_CLOB(' -BDGVend]}",
      "REP - Sezione Tematica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
      "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
      "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
      "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "REP - Promozi')
    || TO_CLOB('one": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
            "REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
            "REP - Meccanica Semplice": "{TM1SORT( ')
    || TO_CLOB(' {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
            "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
')
    || TO_CLOB('
          "DIMENSIONS": {
            "REP - Scenario": "{{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)} , ASC)}}",
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
		  "')
    || TO_CLOB('Attributes/MUI_Descrizione",
          "Attributes/Descrizione",
          "Attributes/DescrizioneArticolo",

          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated",
          "HasPicklist"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : tru')
    || TO_CLOB('e}  ,
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_ACQ": {
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP": {
            "headerClass": "headerRep",
            "openByDefault": true
          },
          "BDG": {
            "headerClas')
    || TO_CLOB('s": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {

          "MARGINE_LORDO_%_P": {
            "type": ["TM1DataColumnPercentage", "numericColumn"],
            "columnGroupShow": "always",
            "aggFunc": "weightedAvg",
')
    || TO_CLOB('
            "aggFuncParam": "$VENDUTO_PROMO_NETTO_P"
          }
        }
      } ,

      "autoGroupColumnDef": {},
      "columnDefs": [

        {
          "headerName": "Compratore",
          "field": "REP_minus_Compratore.Descrizione",
          "width": 70,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Promozione",
          "f')
    || TO_CLOB('ield": "REP_minus_Promozione.MUI_Descrizione",
          "width": 70,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitori",
          "field": "REP_minus_Fornitore.Descrizione",
          "width": 70,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
         ')
    || TO_CLOB(' ]
        },
        {
          "headerName": "Articolo",
          "field": "REP_minus_Articolo.DescrizioneArticolo",
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
')
    || TO_CLOB('          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
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
          "headerName": "A Volantino",
          "field": "REP_minus_AVolantino.Descriz')
    || TO_CLOB('ione",
          "width": 70,
          "hide": false,
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
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ] ,
      "rowSup')
    || TO_CLOB('pressionEnabled": false,
      "colSuppressionEnabled": false,
      "preSelections": [
        {
          "dimension": "REP - Compratore",
          "dimCode": "rep_compratore",
          "dimColumnName": "REP - Compratore",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_StoricoArticoloZonaPromo"]
        },
         {
          "di')
    || TO_CLOB('mension": "REP - Scenario",
          "dimCode": "rep_scenario",
          "dimColumnName": "REP - Scenario",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_StoricoArticoloZonaPromo"]
        }
      ]
   }
  ]
}

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('135','/Timone/Target_Categoria/Data_(CAT)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('136','/Timone/Target_Categoria/Data_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetCategoria_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Categoria (data)",
    "height" : 60,
    "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) ",
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
')
    || TO_CLOB('
          "Compratore" : "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
          "Categoria" : "{    {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )},')
    || TO_CLOB(' 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
        }
      },
      "COLS" : {
        "NON_EMPTY" : true,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Scenario" : " ')
    || TO_CLOB('{{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
          "Misura Timone." : "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{')
    || TO_CLOB('[Misura Timone.].[ASSORT_P]} }"
        }
      },
      "FROM" : "[Timone Categoria]",
      "WHERE" : {
        "Versione" : "[UFF]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Level", "Attributes/Descrizione", "Attributes/MUI_Descrizione", "Attributes/MUI_DescrizioneData", "Attributes/CategoryManager", "Attributes/Name", "Attributes/MUI_Level", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoG')
    || TO_CLOB('roupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Categoria.MUI_Descrizione",
      "headerName" : "Categoria",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "DynamicColumns" : true,
    "DynamicColumnsSettings" : {
      "headerconf" : [ "MUI_DescrizioneData", "Descrizione", "Descrizione" ],
      "headerdefaults" : {
        "marryChildren" : true
      },
      "headerCustomType')
    || TO_CLOB('s" : {
        "RIF_MKT_DT" : {
          "openByDefault" : true
        },
        "TGT_ACQ" : {
          "headerClass" : "headerAcq",
          "openByDefault" : true
        },
        "TGT_MKT" : {
          "headerClass" : "headerMkt",
          "openByDefault" : true
        },
        "TGT_REP" : {
          "headerClass" : "headerRep",
          "openByDefault" : true
        }
      },
      "childrendefaults" : {
        "width" : 110,
        "hide" : false,
     ')
    || TO_CLOB('   "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },
      "childrenCustomTypes" : {
        "VENDUTO_PROMO_NETTO" : {
          "type" : [ "TM1DataColumnInteger", "numericColumn" ],
          "columnGroupShow" : "always"
        },
        "MARGINE_LORDO_%" : {
          "type" : [ "TM1DataColumnPercentage", "numericColumn" ],
          "columnGroupShow"')
    || TO_CLOB(' : "always",
          "aggFunc" : "weightedAvg",
          "aggFuncParam" : "$VENDUTO_PROMO_NETTO"
        },
        "ASSORT_P" : {
          "hide" : true
        }
      }
    },
    "columnDefs" : [ {
      "headerName" : "CategoryManager",
      "field" : "Compratore.CategoryManager",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Comprator')
    || TO_CLOB('e",
      "field" : "Compratore.MUI_Descrizione",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Compratore",
      "field" : "Compratore.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Livello Categoria",')
    || TO_CLOB('
      "field" : "Categoria.MUI_Level",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "pinned" : "left",
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Cod Categoria",
      "field" : "Categoria.Name",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ')
    || TO_CLOB('''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04''')
    || TO_CLOB(',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse" : "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03''')
    || TO_CLOB(',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    },
    "css" : ".ag-row-group.ag-row-level-0{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('137','/Timone/Target_Categoria/Data_(ACQ)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('138','/Timone/Target_Reparto/Data_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto (data)",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC')
    || TO_CLOB('),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[M')
    || TO_CLOB('isura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/DataInizioIst", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    }')
    || TO_CLOB(',
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
  ')
    || TO_CLOB('    "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data",
      "field" : "Promozione.DataInizioIst",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descr')
    || TO_CLOB('izione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
 ')
    || TO_CLOB('     "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_')
    || TO_CLOB('dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSho')
    || TO_CLOB('w" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ban')
    || TO_CLOB('co",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        ')
    || TO_CLOB('"rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type')
    || TO_CLOB('" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Venduto Promo (s/iva)",
        "field" : "Scenario$RIF_')
    || TO_CLOB('MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc')
    || TO_CLOB('" : "weightedAvg",
        "aggFuncParam" : "$VENDUTO_PROMO_NETTO",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      }, {
        "headerName" : "FF C",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ ')
    || TO_CLOB('"TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "FF EC",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByD')
    || TO_CLOB('efault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
       ')
    || TO_CLOB(' "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable"')
    || TO_CLOB(' : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "fiel')
    || TO_CLOB('d" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : fals')
    || TO_CLOB('e,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "nume')
    || TO_CLOB('ricColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
')
    || TO_CLOB('
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" :')
    || TO_CLOB(' false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "t')
    || TO_CLOB('ype" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT')
    || TO_CLOB('_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : ')
    || TO_CLOB('"sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
')
    || TO_CLOB('      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,')
    || TO_CLOB('
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
')
    || TO_CLOB('
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        ')
    || TO_CLOB('"type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_')
    || TO_CLOB('ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc"')
    || TO_CLOB(' : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      },')
    || TO_CLOB(' {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
')
    || TO_CLOB('
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_trd_inizializza",
      "componentLabel" : "Inizializza Reference",')
    || TO_CLOB('
      "process" : "MUI_DUMMY_CUB.Timone.Inizializza Reference",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Data" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Promozione",
        "hasPicklist" : ')
    || TO_CLOB('true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      } ]
    } ],
      "css" : ".ag-row-group.ag-row-level-2{background: #28aeff !important;}"
  } ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('139','/Timone/Target_Reparto/Data_(CAT)',' {
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('140','/Timone/Target_Reparto/Data_(ACQ)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Data",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "title" : "Target Reparto (data)",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC')
    || TO_CLOB('),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[M')
    || TO_CLOB('isura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Anno", "Attributes/MUI_Semestre", "Attributes/DataInizioIst", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    }')
    || TO_CLOB(',
    "autoGroupColumnDef" : {
      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Anno",
      "field" : "Promozione.Anno",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
  ')
    || TO_CLOB('    "headerName" : "Semestre",
      "field" : "Promozione.MUI_Semestre",
      "width" : 70,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Data",
      "field" : "Promozione.DataInizioIst",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione",
      "field" : "Promozione.Descr')
    || TO_CLOB('izione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
      "width" : 120,
      "hide" : true,
 ')
    || TO_CLOB('     "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_')
    || TO_CLOB('dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupSho')
    || TO_CLOB('w" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ban')
    || TO_CLOB('co",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        ')
    || TO_CLOB('"rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type')
    || TO_CLOB('" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Venduto Promo (s/iva)",
        "field" : "Scenario$RIF_')
    || TO_CLOB('MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc')
    || TO_CLOB('" : "weightedAvg",
        "aggFuncParam" : "$VENDUTO_PROMO_NETTO",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
')
    || TO_CLOB('
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1Data')
    || TO_CLOB('ColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFA')
    || TO_CLOB('LE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "op')
    || TO_CLOB('en",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" :')
    || TO_CLOB(' "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art"')
    || TO_CLOB(',
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
 ')
    || TO_CLOB('       "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
')
    || TO_CLOB('      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width"')
    || TO_CLOB(' : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
   ')
    || TO_CLOB('     "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
    ')
    || TO_CLOB('    "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
')
    || TO_CLOB('        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGr')
    || TO_CLOB('oup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger",')
    || TO_CLOB(' "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "')
    || TO_CLOB('width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "')
    || TO_CLOB('editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultim')
    || TO_CLOB('a",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowG')
    || TO_CLOB('roup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type"')
    || TO_CLOB(' : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "rowClassRules" : {
      "row_style_hidden" : "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1" : "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Reparto.Name)",
      "row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01')
    || TO_CLOB(''',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_trd_inizializza"')
    || TO_CLOB(',
      "componentLabel" : "Inizializza Target",
      "process" : "MUI_DUMMY_CUB.TimoneRIF.TGT4",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Data" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "inPromo",
        "label" : "Pro')
    || TO_CLOB('mozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      }, {
        "dimension" : "Reparto",
        "attribute" : "Descrizione",
        "paramName" : "inReparto",
        "label" : "Reparto",
        "hasPicklist" : true
      } ]
    } ],
    "css" : ".ag-row-group.ag-row-level-2{background: #28aeff !important;}"
  } ]')
    || TO_CLOB('
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('141','/Timone/Target_Reparto/Data_(ACQ)',' {
     "promozione":["anno","canale","tipo","descrizione","riferimento","semestre", "proximity"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('142','/Timone/Target_Reparto/Promo_(CAT)',TO_CLOB('{
  "connection" : "promo",
  "configurations" : [ {
    "name" : "gc_TargetReparto_Promo",
    "logMemory" : true,
    "logTime" : true,
    "skip" : true,
    "maxCells" : 1000000,
    "title" : "Target Reparto",
    "height" : 60,
    "MDX" : {
      "ROWS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Promozione" : "{FILTER( { { ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promo')
    || TO_CLOB('zione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}}, [Promozione].[Proximity] <> ''Y'')}",
          "Reparto" : "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
        }
      },
      "COLS" : {
        "NON_EMPTY" : false,
        "DIMENSIONS" : {
          "Scenario" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
          "Misura Timone." : "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.')
    || TO_CLOB('] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
        }
      },
      "FROM" : "[Timone Reparto]",
      "WHERE" : {
        "Versione" : "[Ufficiale]"
      }
    },
    "ExecuteMDX" : {
      "Members" : [ "Name", "Attributes/Descrizione", "Attributes/Canale", "Attributes/TipoElemento", "Attributes/MUI_Descrizione", "Attributes/Riferimento", "UniqueName" ],
      "Cells" : [ "Ordinal", "Value", "Updateable", "Consolidated", "HasPicklist" ]
    },
    "autoGroupColumnDef" : {
')
    || TO_CLOB('      "cellRendererParams" : {
        "suppressCount" : true
      },
      "field" : "Reparto.Descrizione",
      "headerName" : "Reparto",
      "width" : 300,
      "pinned" : "left",
      "type" : [ "TM1Element" ]
    },
    "columnDefs" : [ {
      "headerName" : "Canale",
      "field" : "Promozione.Canale",
      "width" : 100,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizi')
    || TO_CLOB('one",
      "field" : "Promozione.Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Descrizione + Data",
      "field" : "Promozione.MUI_Descrizione",
      "width" : 400,
      "hide" : true,
      "rowGroup" : true,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Reparto",
      "field" : "Reparto.Descrizione",
     ')
    || TO_CLOB(' "width" : 120,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento",
      "field" : "Promozione.Riferimento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : false,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Tipo Elemento",
      "field" : "Reparto.TipoElemento",
      "width" : 70,
      "hide" : true,
      "rowGroup" : fa')
    || TO_CLOB('lse,
      "editable" : false,
      "type" : [ "TM1Element" ]
    }, {
      "headerName" : "Riferimento Data",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "num')
    || TO_CLOB('ericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
        "width" : 8')
    || TO_CLOB('0,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editab')
    || TO_CLOB('le" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
     ')
    || TO_CLOB('   "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup')
    || TO_CLOB('" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Venduto Promo (s/iva)",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "T')
    || TO_CLOB('M1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "ML %",
        "field" : "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "weightedAvg",
        "aggFuncParam" : "$VENDUTO_PROMO_NETTO",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnPercentage", "numericColumn" ]
      } ]
    }, {
      "headerName" ')
    || TO_CLOB(': "Target MKT",
      "headerClass" : "headerMkt",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
       ')
    || TO_CLOB(' "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc')
    || TO_CLOB('" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
  ')
    || TO_CLOB('    }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
      ')
    || TO_CLOB('  "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "')
    || TO_CLOB('editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target REP",
      "headerClass" : "headerRep",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : t')
    || TO_CLOB('rue,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_REP$$')
    || TO_CLOB('MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        ')
    || TO_CLOB('"columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "he')
    || TO_CLOB('aderName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
       ')
    || TO_CLOB(' "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : tr')
    || TO_CLOB('ue,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Mkt",
        "field" : "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
        "width" : 80,
        "hide" : false,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    }, {
      "headerName" : "Target Acq.",')
    || TO_CLOB('
      "headerClass" : "headerAcq",
      "openByDefault" : true,
      "children" : [ {
        "headerName" : "N. Art",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
        "width" : 150,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "Totale",
        "field" : "Scen')
    || TO_CLOB('ario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
        "width" : 150,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "always",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
      ')
    || TO_CLOB('  "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "')
    || TO_CLOB('headerName" : "N. Foto Speciali",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Spec. Banco",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
        "width" : 80,
   ')
    || TO_CLOB('     "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "N. Foto Ultima",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
   ')
    || TO_CLOB('     "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto/tgt Rep",
        "field" : "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      }, {
        "headerName" : "D Foto Banco/tgt Rep",
        "field" :')
    || TO_CLOB(' "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
        "width" : 80,
        "hide" : true,
        "rowGroup" : false,
        "aggFunc" : "sum",
        "columnGroupShow" : "open",
        "editable" : true,
        "type" : [ "TM1DataColumnInteger", "numericColumn" ]
      } ]
    } ],
    "groupRowAggNodes" : {
      "nodeGroupTrue" : "false",
      "nodeGroupFalse" : "data.Reparto.TipoElemento == ''R''"
    },
    "actions" : [ {
      "componentId" : "tim_t')
    || TO_CLOB('rp_inizializza",
      "componentLabel" : "Inizializza Reference",
      "process" : "MUI_DUMMY_CUB.Timone.Inizializza Reference",
      "timeout" : -1,
      "refresh" : [ "gc_TargetReparto_Promo" ],
      "params" : [ {
        "dimension" : "Promozione",
        "attribute" : "Anno",
        "paramName" : "inAnno",
        "label" : "Anno",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "MUI_Descrizione",
        "paramName" : "')
    || TO_CLOB('inPromo",
        "label" : "Promozione",
        "hasPicklist" : true
      }, {
        "dimension" : "Promozione",
        "attribute" : "Gruppo",
        "paramName" : "inGruppo",
        "label" : "Gruppo",
        "hasPicklist" : true
      } ]
    } ],
      "css" : ".ag-row-group.ag-row-level-1{background: #28aeff !important;}"
  } ]
}'),'GRID');

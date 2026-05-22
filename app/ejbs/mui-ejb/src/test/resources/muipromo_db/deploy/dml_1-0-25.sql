UPDATE MUIPROMO.APPLICATION_PROPERTIES SET AP_VALUE ='http://tmonetest.mil.esselunga.net/ibmcognos/cgi-bin/cognosisapi.dll' WHERE AP_KEY = 'AUTH_SERVER';

DELETE FROM MUIPROMO.CONFIGURATION;
SET DEFINE OFF;
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('95','/Piano_Annuale/Spazi/Spazi_Condivisi',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
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
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('106','/Timone/Foto_Speciali/Target',' {
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('107','/Timone/Spazi_Campagna/Target_Categoria',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('108','/Timone/Spazi_Campagna/Target_Reparto',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('109','/Timone/Target_Categoria/Data',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('110','/Timone/Target_Categoria/Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('111','/Timone/Target_Reparto/Data',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('112','/Timone/Target_Reparto/Promo','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('113','/Timone/Associazione_Promo','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "promozioneriferimento":["anno","rif_canale","semestre","gruppo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('87','/Piano_Annuale/Visualizza_Piano','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('58','/welcome',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "rep_promozione":["anno","canale","riferimento","semestre", "descrizione","canaleanno"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('89','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche','{
     "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "pubblicita":["descrizione"]
} ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('91','/Piano_Annuale/Spazi/Macrospazi_Listino_Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "macrospazio":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('78','/Piano_Annuale/Crea_Promozione',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('79','/Piano_Annuale/Foto',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "raggruppamentoFoto":["mui_descrizione","tot", "tots","mui_compratore" , "mui_reparto"],
    "compratore":["descrizione" , "repartodcodesc" , "categorymanager"  ]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('80','/Piano_Annuale/Gabbia',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('81','/Piano_Annuale/Gestione_Contributi',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "tipoPromozione":["descrizione","gruppo"],
     "contratto":["descrizione"],
       "prestazione":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('83','/Piano_Annuale/Meccaniche',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('84','/Piano_Annuale/Modifica_Promozione',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('85','/Piano_Annuale/Negozi_Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "negozio":["descrizione","zonaPromo" ],
    "canale":["descrizione"],
    "misuraCanale":["descrizione"],
    "sezioneTematica":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('88','/Piano_Annuale/Zone_Promo',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "zonaPromo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('67','/Fatturazione/Associazioni_Articoli',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "spazioProgressivo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('68','/Fatturazione/Contribuzione_Campagna',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('69','/Fatturazione/Contribuzione_Promozioni',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('70','/Pianificazione/Articoli_Fittizi',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
    "compratore": ["descrizione"],
	"articoloFittizio":["descrizione","compratore"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('71','/Pianificazione/Differenziazione_Zone',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
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
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"categoria": ["descrizione"],
	"fornitore": ["descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('75','/Pianificazione/Selezione_Famiglie_Contributi',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
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
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
		')
    || TO_CLOB('			}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT_DT],[REP - Scenario].[TGT_ACQ],[REP - Scenario].[BDG],[REP - Scenario].[CONS]}",
						"REP - Misura Timone": "{[REP - Misura Timone].[MUI_SUB_Analisi_Budget_Venduto_Categoria]}"
					}
				},
				"FROM": "[Timone Reporting]",
				"WHERE": {
					"REP - Ve')
    || TO_CLOB('rsione": "[UFF]"
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
					"HasPicklist",
					"PicklistValues"
	')
    || TO_CLOB('			]
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
    || TO_CLOB('    "openByDefault": true
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
			')
    || TO_CLOB('	}
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
					"')
    || TO_CLOB('type": [
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
						"')
    || TO_CLOB('TM1Element"
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
')
    || TO_CLOB('				{
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
			"rowSuppression')
    || TO_CLOB('Enabled": false,
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
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Temat')
    || TO_CLOB('ica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( ')
    || TO_CLOB('{TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1F')
    || TO_CLOB('ILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, ')
    || TO_CLOB('0)} , ASC)}",
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
					"Attributes/Descrizione",
                    "Attributes/Descriz')
    || TO_CLOB('ioneArticolo",
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
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" ')
    || TO_CLOB(': ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
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
          "TGT_REP": {
            "headerClass": "headerRep",
  ')
    || TO_CLOB('          "openByDefault": true
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
				"cellRendererParams": {
					"suppressCount": true
				},
				"')
    || TO_CLOB('field": "REP_minus_Articolo.DescrizioneArticolo",
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
					"field": "REP_minu')
    || TO_CLOB('s_Articolo.RepartoDesc",
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
					"field": "REP_minus_Articolo.GRMDe')
    || TO_CLOB('sc",
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
					"width": 7')
    || TO_CLOB('0,
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
					"hide')
    || TO_CLOB('": true,
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
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide"')
    || TO_CLOB(': false,
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
					"hide": fal')
    || TO_CLOB('se,
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
          "attrColumnName": "Descrizione",
          "proces')
    || TO_CLOB('s": "",
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
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"articolo": ["categoriadesc","grmdesc","subgrmdesc","attivo"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('73','/Pianificazione/Proiezioni',' {
	"promozione": ["anno","canale","tipo","riferimento","semestre","descrizione"],
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
			"Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, DESC)}}",
						"Compratore": "{{TM1SORT( {TM1FILTERB')
    || TO_CLOB('YLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
')
    || TO_CLOB('


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
                    "Attributes/Anno",
                    "Attributes/MUI_Semestre",
                    "Attributes/Descrizione",
                    "Attributes/Riferimento",
                    "Attributes/MUI_TOT",
          ')
    || TO_CLOB('          "Attributes/MUI_TOTS",
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
					"marryChildren": true
				},
				"headerCustomTypes": {
					"RIF_MKT_DT": {
						"openByDefault": true
					},
					"TGT_ACQ": {
						"headerC')
    || TO_CLOB('lass": "headerAcq",
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
					"hide": false,
					"rowGroup": false,
					"aggFunc": "sum",
					"columnGroupShow": "always",
					"editable": true,
					"type": [
						"TM1DataColumnText",
						"numericColumn"
					]
')
    || TO_CLOB('
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
						"width": 300,
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
						"widt')
    || TO_CLOB('h": 300,
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
				')
    || TO_CLOB('	"field": "Promozione.MUI_Descrizione",
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
	')
    || TO_CLOB('				"rowGroup": false,
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
		')
    || TO_CLOB('			"type": [
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
')
    || TO_CLOB('				{
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
					"field": "Fornitore.MUI_TOTS",
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
					"field')
    || TO_CLOB('": "Fornitore.Descrizione",
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
						"TM1Element"
					]
				},
				{
					"headerName": "Rata",
					"field": "Rata.Descrizione",
					"width": 70,
					"hide": true,
					')
    || TO_CLOB('"rowGroup": false,
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
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, DESC)}}",
						"Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
	')
    || TO_CLOB('					"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ [Rata].[(I) Fatturazione] }",
						"ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
						"Spazio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Fatturazione": "{[Misura Fatturazione].[ASS_')
    || TO_CLOB('RATA],[Misura Fatturazione].[OK]}"
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
					"Attributes/MUI_Descrizione",
                    "Attributes/Canale",
                    "Attributes/Anno",
                    "Attributes/MUI_Semestre",
                    "Attributes/Descrizione",
                    "Attributes/Riferimento",
					"UniqueN')
    || TO_CLOB('ame"
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
					"marryChildren": true
				},
				"headerCustomTypes": {
					"RIF_MKT_DT": {
						"openByDefault": true
					},
					"TGT_ACQ": {
						"headerClass": "headerAcq",
						"openByDefault": tru')
    || TO_CLOB('e
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
					"type": [
						"TM1DataColumnText"
					]
				},
				"childrenCustomTypes": {

				}
			},
			"autoGroupCol')
    || TO_CLOB('umnDef": {
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
					"headerName": "MUI_Descrizione",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},{')
    || TO_CLOB('
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
					"field": "Promozione.Anno",')
    || TO_CLOB('
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
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.Descrizione",
					"width": 400,
					"hide": true,
					"rowGro')
    || TO_CLOB('up": false,
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
					"headerName": "Fornitore",
					"field": "Fornitore.Descrizione",
					"width": 200,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
	')
    || TO_CLOB('					"TM1Element"
					]
				},
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
						"TM1Element"
					]
				},
				{
					"headerName": "')
    || TO_CLOB('Spazio",
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
      "Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
 ')
    || TO_CLOB('           "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)')
    || TO_CLOB('},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
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
          			"Attributes/MUI_DescrizioneData",
                  ')
    || TO_CLOB('  "Attributes/Descrizione",
                    "Attributes/MUI_TOT",
                    "Attributes/MUI_TOTS",
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
          "MUI_DescrizioneData",
          "Descrizione"
        ]')
    || TO_CLOB(',
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
            "openByDef')
    || TO_CLOB('ault": true
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
          "LISTINO": {
            "type": [
              "TM1DataColumnInteger",
              "numericC')
    || TO_CLOB('olumn"
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
              "TM1DataColumnText"
            ]
          },
          "DESC_FATT": {
            ')
    || TO_CLOB('"width": 300,
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
        "headerName": "Rata",
        "width": 400,
        "pinned": "left",
        "type": [
')
    || TO_CLOB('
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
          "field": "Compratore.Descrizione",
          "width": 200,
          "hide": true,
     ')
    || TO_CLOB('     "rowGroup": true,
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
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
')
    || TO_CLOB('          "width": 100,
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
            "TM1Element"
          ]
        },
        {
          "headerName": "Rata",
       ')
    || TO_CLOB('   "field": "Rata.Descrizione",
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{{TM1SORT( {TM1FILT')
    || TO_CLOB('ERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ [Rata].[(I) Fatturazione] }",
            "ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
            "Spazio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
          }
        },
        "COLS": {
         ')
    || TO_CLOB(' "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Misura Fatturazione": "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
          }
        },
        "FROM": "[Fatturazione Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
         ')
    || TO_CLOB(' "Versione": "[UFF]"
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
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf"')
    || TO_CLOB(': [
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
          },
          "TGT')
    || TO_CLOB('_REP": {
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
        "childrenCustomTypes": {}
      },
      "autoGroupColumnDef": {
        "cellRendererPar')
    || TO_CLOB('ams": {
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
          "field": "Compratore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
 ')
    || TO_CLOB('           "TM1Element"
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
          "field": "Rata.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "e')
    || TO_CLOB('ditable": false,
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
       ')
    || TO_CLOB('   "hide": false,
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('11','/Fatturazione/Contribuzione_Campagna',TO_CLOB(' {
	"connection": "promo",

	"configurations": [
		{
			"name": "gc_ContribuzioneCampagna",
			"logMemory": true,
			"logTime": true,
			"skip": true,
			"Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }"  ,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, DESC)}}",
						"Misura Fatturazione": "{[Misura Fatturazione].[I-Fatturazione Contributi]}"
				')
    || TO_CLOB('	}
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
					"HasPicklis')
    || TO_CLOB('t",
					"PicklistValues"
				]
			},


			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "MUI_DescrizioneData" , "Descrizione"]  ,
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
					"TG')
    || TO_CLOB('T_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":100,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnText"] },
				"childrenCustomTypes"  : { "LISTINO":{"type":[ "TM1DataColumnInteger" ,"numericColumn"]} ,
					"APPLICATO":{"type":[ "TM1DataColumnInteger" ,"numericColumn"]},
					"PRESTAZIONE":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"')
    || TO_CLOB('CAUS":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"DESC_FATT":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"OK":{"width":50 , "type":[ "TM1DataColumnText" ]}
				}
			} ,


			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
				},
				"field": "Rata.Descrizione",
				"headerName": "Rata",
				"width": 400,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Totale Compr')
    || TO_CLOB('atore",
					"field": "Compratore.MUI_TOT",
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
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Totale Fornitore",
					"field": "Fornitore.MUI_TOTS",
					"')
    || TO_CLOB('width": 100,
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
					"rowGroup": true,
					"')
    || TO_CLOB('editable": false,
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
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('12','/Fatturazione/Contribuzione_Promozioni',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_ContribuzionePromozioni",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "Rata": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata].[I-Rate Fatturazione Contr])}, 0)}, DESC)}}   }",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promo')
    || TO_CLOB('zione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] ')
    || TO_CLOB('}) }"
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
                    "Attributes/Descrizione",
                    "Attri')
    || TO_CLOB('butes/MUI_Descrizione",
                    "Attributes/MUI_TOTS",
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
          "marryChildren": true
        },
    ')
    || TO_CLOB('    "headerCustomTypes": {
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
          "w')
    || TO_CLOB('idth": 110,
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
          "LISTINO": {
            "type": [
              "TM1DataColumnInteger",
              "numericColumn"
            ]
          },
          "APPLICATO": {
            "type"')
    || TO_CLOB(': [
              "TM1DataColumnInteger",
              "numericColumn"
            ]
          }
        }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Rata.Descrizione",
        "headerName": "Rata",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "')
    || TO_CLOB('field": "Promozione.MUI_Descrizione",
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
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
   ')
    || TO_CLOB('     {
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
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
')
    || TO_CLOB('
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
            "TM1Element"
          ]
        },
        {
          "headerName": "Rata",
          "field": "Rata.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editab')
    || TO_CLOB('le": false,
          "type": [
            "TM1Element"
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('13','/Pianificazione/Articoli_Fittizi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_articoliFittizi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000 ,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Articolo Fittizio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSI')
    || TO_CLOB('ONS": {
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
        "Cells": [
          "Ordinal",
          "Value"')
    || TO_CLOB(',
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
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "cellClass": "cellClass-left-text",
          "width": 150,
          "hide": false,
     ')
    || TO_CLOB('     "rowGroup": false,
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
            "type": ["TM1DataColumnInteger"],
            ')
    || TO_CLOB('"cellClass": "cellClass-right-text"
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
            "type": ["TM1DataColumnNumber"],
  ')
    || TO_CLOB('          "cellClass": "cellClass-right-text"
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
        "width": 300,
        "pinned": "')
    || TO_CLOB('left",
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
          "field": "ArticoloFittizio.Compratore",
          "width": 10')
    || TO_CLOB('0,
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
          "process": "MUI_DUMMY_ConfigurazioneSubsetP')
    || TO_CLOB('ianificazione",
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
          "params": [
            {
')
    || TO_CLOB('              "dimension": "Compratore",
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
          "refresh": [
          ')
    || TO_CLOB('  "gc_articoliFittizi"
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozio')
    || TO_CLOB('ne],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}",
            "Zona Promo": "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SOCIETA_2] })}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DI')
    || TO_CLOB('MENSIONS": {
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
          "Name",
                    "Attributes/MUI_Descrizione",
                    "Attributes/Descrizione",
          			"At')
    || TO_CLOB('tributes/Fornitore",
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
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "head')
    || TO_CLOB('erconf": [
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
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN"')
    || TO_CLOB(': {
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
          "PRZ_ATT_USR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text')
    || TO_CLOB('"
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
          },
          "CST_AN": {
            "type": ["TM1DataColumnNumber"],
            "cell')
    || TO_CLOB('Class": "cellClass-right-text"
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
            "cellClass": "cellClass-right-text"
          },
          "CST_PROMO_C_IVA": {
            "')
    || TO_CLOB('type": ["TM1DataColumnNumber"],
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
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },')
    || TO_CLOB('
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
          "LIM_UTIL": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "c')
    || TO_CLOB('ellClass-right-text"
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
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Pro')
    || TO_CLOB('mozione.MUI_Descrizione",
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
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
   ')
    || TO_CLOB('       "headerName": "Fornitore",
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
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Ele')
    || TO_CLOB('ment"
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
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editabl')
    || TO_CLOB('e": false,
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
          "field": "Articolo.SubGrmDesc",
          "width": 100,
          "hide": true,
          "')
    || TO_CLOB('rowGroup": false,
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
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName"')
    || TO_CLOB(': "inPromo",
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
          "refresh": ["gc_differenziazionePromo"]
        }
      ],
      "styleRules": {},
      "actions": [
   ')
    || TO_CLOB('     {
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
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
      ')
    || TO_CLOB('        "hasPicklist": true
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promo')
    || TO_CLOB('zione],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "ID Set": "{[ID Set].[(I) Lista set]}",
            "ID RaggrSet": "{[ID RaggrSet].[(I) Configura set]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione - Set": "{[Misura Configurazione Promozione - Set].[(I) Definizione set]}"
          }
        },
        "FR')
    || TO_CLOB('OM": "[Configurazione Promozione - Set Parametri]",
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
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]')
    || TO_CLOB('
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
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]
        },
')
    || TO_CLOB('        "headerCustomTypes": {},
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
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type"')
    || TO_CLOB(': [
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
          "field": "Compratore.Descrizione",
          "width": 100,
          "hide": true,
')
    || TO_CLOB('          "rowGroup": true,
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
          "headerName": "ID RaggrSet",
          "field": "IDRaggrSet.Descrizione",
    ')
    || TO_CLOB('      "width": 100,
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
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_Con')
    || TO_CLOB('figurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_meccanicheSet_creazione","gc_meccanicheSet_associazione"]
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
          "refresh": ["gc_mecc')
    || TO_CLOB('anicheSet_creazione","gc_meccanicheSet_associazione"]
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)')
    || TO_CLOB('}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000] }) }}",
            "ID Set": "{[ID Set].[(I) Lista set]}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "ID RaggrSet": "{[ID RaggrSet].[(I) Con')
    || TO_CLOB('figura set articoli]}",
            "Misura Configurazione Promozione - Set": "{[Misura Configurazione Promozione - Set].[(I) Set Articoli]}"

          }
        },
        "FROM": "[Configurazione Promozione - Set]",
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
     ')
    || TO_CLOB('   "Cells": [
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
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {},
        "childrendefaults": {
          "width": 100,')
    || TO_CLOB('
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
            "cellClass": "dateFormat",
            "type": [
              "TM1DataColumnDate",
              "numericColumn"
            ],
            "aggFunc": "",
            "columnGroupShow": "a')
    || TO_CLOB('lways"
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
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Articolo.Descrizione",
        "headerName": "Articolo",
        "cellCla')
    || TO_CLOB('ss": "cellClass-left-text",
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
    || TO_CLOB('       "field": "Compratore.Descrizione",
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
        ')
    || TO_CLOB('{
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
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizion')
    || TO_CLOB('e",
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
          "attrColumnName": "Descrizione')
    || TO_CLOB('",
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
      "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misu')
    || TO_CLOB('ra Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }" ,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}')
    || TO_CLOB(', 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.":"{[Misura Timone.].[MUI_Proiezione_Timone_Categoria]}"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
        "Members": [
      ')
    || TO_CLOB('    "Name",
                    "Attributes/Descrizione",
                    "Attributes/MUI_Descrizione",
          			"Attributes/MUI_RepartoDesc",
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
        "fi')
    || TO_CLOB('eld": "Categoria.Descrizione",
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
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "')
    || TO_CLOB('RIF_MKT_DT": {
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
            "headerClass": "headerBdg",
            "openByDefault": t')
    || TO_CLOB('rue
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
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": [
              "TM1DataColumnText"
            ],
           ')
    || TO_CLOB(' "hide": "true"
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
          "CONTR": {
            "hide": "true"
          },
          "EXTRA_CONTR": {
            "hide": "true"
          },
          "D_ART_slash_TGT": {
            "hide": "true"
          },
          "D_FOTO_slash_TGT": {
       ')
    || TO_CLOB('     "hide": "true"
          },
		 "MARGINE_LORDO_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
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
          "headerName": "Pr')
    || TO_CLOB('omozione",
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
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
         ')
    || TO_CLOB(' ]
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
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
       ')
    || TO_CLOB('   "attrCode": "descrizione",
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
          "attrCode": "descrizione",
          "attrColumn')
    || TO_CLOB('Name": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        }
      ],
      "styleRules": {},
       "groupRowAggNodes": {
			"nodeGroupTrue": "true",
			"nodeGroupFalse": "true"
		}
    },
    {
      "name": "gc_proiezioni_2",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
       "Misura Promozione Pianificazione 2": "{([Misura Pr')
    || TO_CLOB('omozione Pianificazione].[Configurazione Subset Pianificazione 1])}",
      "Misura Promozione Pianificazione": "({FILTER(TM1SUBSETALL([Misura Promozione Pianificazione]),[}ElementAttributes_Misura Promozione Pianificazione].([}ElementAttributes_Misura Promozione Pianificazione].[Ordinamento])>0 )})",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, ')
    || TO_CLOB('0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Promozione Pianificazione": "{([Misura Promozione Pianifi')
    || TO_CLOB('cazione].[MUI_Proiezioni])}"
          }
        },
        "FROM": "[Promozione Pianificazione]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
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
          "Attributes/CategoriaDesc"')
    || TO_CLOB(',
          "Attributes/GRMDesc",
          "Attributes/SubGrmDesc",
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
          "marryChildren": true
        },
 ')
    || TO_CLOB('       "childrendefaults": {
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
          },
          "PRZ_MIN": {
')
    || TO_CLOB('
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_MAX": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_ATT_USR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "_perc__SC": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
')
    || TO_CLOB('
          },
          "VAL_SC": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PRZ_PROMO": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_AN": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_USR": {
            "type": ["TM1DataColumnNumber"],
            "cel')
    || TO_CLOB('lClass": "cellClass-right-text"
          },
          "CONTR__perc__IN_FATT": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
          "CST_C_IVA": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "CST_PROMO_C_IVA": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "N_PEZZI": {
   ')
    || TO_CLOB('         "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellClass-right-text"
          },
          "COLLI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellClass-right-text"
          },
          "TOT_dot_VEND": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "F_FATT": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
  ')
    || TO_CLOB('        },
          "IVA": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
          "RIFATT_BS": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "LIM_UTIL": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "%_SC": {
            "type": ["TM1DataColumnNumber"],
            "cellCl')
    || TO_CLOB('ass": "cellClass-right-text"
          },
          "CONTR_%_IN_FATT": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "PEZZ": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "VEND_PROMO_S_IVA": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "CST_PROMO": {
            "ty')
    || TO_CLOB('pe": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "ML_I_UNI": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "ML": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "%_ML": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
         ')
    || TO_CLOB(' "CONTR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "EXTRA_CONTR": {
            "type": ["TM1DataColumnNumber"],
            "cellClass": "cellClass-right-text"
          },
          "NumeroNegozi": {
            "type": ["TM1DataColumnInteger"],
            "cellClass": "cellClass-right-text"
          },
          "PUNTI": {
            "type": ["TM1DataColumnDecimal3"],
            "cellClass": "cellCl')
    || TO_CLOB('ass-right-text"
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
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
       ')
    || TO_CLOB('   "field": "Promozione.MUI_Descrizione",
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
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": fal')
    || TO_CLOB('se,
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
          ]
        },
        {
          "headerName": "Fornitore",
          "field": ')
    || TO_CLOB('"Articolo.Fornitore",
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
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,')
    || TO_CLOB('
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
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
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "cellCla')
    || TO_CLOB('ss": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Sub Grm",
          "field": "Articolo.SubGrmDesc",
          "cellClass": "cellClass-left-text",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Ele')
    || TO_CLOB('ment"
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
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]')
    || TO_CLOB('
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
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_proiezioni_3",
      "logM')
    || TO_CLOB('emory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0')
    || TO_CLOB(')}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(II) TCS I]}",
            "Sezione Tematica": "{{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, { [Sezione Tematica].[ST_0000] }) }, {')
    || TO_CLOB(' [Sezione Tematica].[ST_001] }) }, { [Sezione Tematica].[ST_042] }) }}",
            "Misura Timone.": "{[Misura Timone.].[(II)Timone Categoria Sez]}"
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
          "UniqueName')
    || TO_CLOB('"
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
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type')
    || TO_CLOB('": [
          "TM1Element"
        ]
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
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
            "openByDef')
    || TO_CLOB('ault": true
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
            "headerClass": "headerBdg",
            "openByDefault": true
          }
        },
        "childrendefaults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "a')
    || TO_CLOB('ggFunc": "sum",
          "columnGroupShow": "always",
          "editable": false,
          "type": ["TM1DataColumnInteger"],
          "cellClass": "cellClass-right-text"
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
     ')
    || TO_CLOB('       "TM1Element"
          ]
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
          "field": "Categoria.RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
       ')
    || TO_CLOB('   "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {},
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": ')
    || TO_CLOB('"inPromo",
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
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
          "refresh": ["gc_proiezioni_1","gc_proiezioni_2","gc_proiezioni_3"]
        }
      ')
    || TO_CLOB('],
      "actions": [
        {
          "componentId": "pnf_pro_sposta",
          "componentLabel": "Sposta Articoli",
          "process": "MUI_DUMMY_COPY.Promozione.Articolo+del",
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
 ')
    || TO_CLOB('           {
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
              "paramName": "toPromo",
              "label": "Descrizione Promozione",
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
          "params": ')
    || TO_CLOB('[
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
              "label": "Descrizione Promozione",
              "hasPicklist": true
            }
          ]
        ')
    || TO_CLOB('},
        {
          "componentId": "pnf_pro_clear",
          "componentLabel": "Clear",
          "process": "MUI_DUMMY_CUB.Promozione Pianificazione Pulizia Articoli",
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
              "hasPickl')
    || TO_CLOB('ist": true
            }
          ]
        },
        {
          "componentId": "pnf_pro_abilita",
          "componentLabel": "Abilita Famiglia",
          "process": "MUI_DUMMY_CUB.Famiglia.Programmazione Fornitore-Articolo.Caller",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_2"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompra')
    || TO_CLOB('tore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "pnf_pro_inizializza",
          "componentLabel": "Inizializza",
          "process": "MUI_DUMMY_CUB.Pianificazione Panieri.SPF.Caller",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_2"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": ')
    || TO_CLOB('"Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            }
          ]
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
      "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.')
    || TO_CLOB('].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL(')
    || TO_CLOB(' {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "[Misura Timone.].[MUI_SUB_Selezione_Articoli_Contributi_Timone]"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "Ex')
    || TO_CLOB('ecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_RepartoDesc",
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
        "field": "Categoria.Descr')
    || TO_CLOB('izione",
        "headerName": "Categoria",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": ["TM1Element"],
        "cellClass": "cellClass-left-text"
      },
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "R')
    || TO_CLOB('IF_MKT_DT": {
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
            "headerClass": "headerBdg",
            "openByDefault": true')
    || TO_CLOB('
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
          "cellClass": "cellClass-right-text"
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": ["TM1DataColumnText"],
            "cellClass": "cellClass-left-text",
    ')
    || TO_CLOB('        "hide": "true"
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
          "MARGINE_LORDO_%": {
            "type": ["TM1DataColumnPercentage"],
            "cellClass": "cellClass-right-text"
          },
		 "D_VEND/RIF_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "')
    || TO_CLOB('always",
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
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Compratore",
          "field": "Compratore.Descrizione",
          "wi')
    || TO_CLOB('dth": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.MUI_RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": ["TM1Element"],
          "cellClass": "cellClass-left-text"
        }
      ],
     ')
    || TO_CLOB('  "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_selezioneArticoliContributi_targetCategoria","gc_selezioneArticoliContributi_promozione"]
    ')
    || TO_CLOB('    },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
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
	')
    || TO_CLOB('		"nodeGroupTrue": true,
			"nodeGroupFalse": true
		}
    },
    {
      "name": "gc_selezioneArticoliContributi_promozione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
          ')
    || TO_CLOB('  "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Programmazione Fornitore": "{[Misura Programmazione Fornitore].[(I) Programmazione Fornitore Articolo (Dinamico)]}"
          }
        },
        "FROM": "[Programmazione Fornitore - ')
    || TO_CLOB('Articolo]",
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
          "UniqueName"
        ],
        "Cells": ')
    || TO_CLOB('[
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
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true
          },
          "TGT_A')
    || TO_CLOB('CQ": {
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
          "columnGroup')
    || TO_CLOB('Show": "always",
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
            "cellClass": "cellClass-right-text"
 ')
    || TO_CLOB('         },
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
            "type":')
    || TO_CLOB(' ["TM1DataColumnInteger"],
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
            "c')
    || TO_CLOB('ellClass": "cellClass-right-text"
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
          },
        ')
    || TO_CLOB('  "FF_EC_%": {
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
        "headerName": "Articolo"')
    || TO_CLOB(',
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
        {
  ')
    || TO_CLOB('        "headerName": "Compratore",
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
    || TO_CLOB(' "type": ["TM1Element"],
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
          "widt')
    || TO_CLOB('h": 100,
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
        {
   ')
    || TO_CLOB('       "headerName": "Grm",
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
          "type": ["TM1Ele')
    || TO_CLOB('ment"],
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
          "dimCol')
    || TO_CLOB('umnName": "Promozione",
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
          "dimColumnName": "Co')
    || TO_CLOB('mpratore",
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
      "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.')
    || TO_CLOB('].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }" ,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "{[Misura Timone.].[MUI_SUB_Selezione_Articoli_Contributi_Timone]}"
          }
        },
        "FROM": "[Timone Categoria II]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      ')
    || TO_CLOB('"ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/MUI_RepartoDesc",
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
        "field": "Categoria.De')
    || TO_CLOB('scrizione",
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
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
     ')
    || TO_CLOB('       "openByDefault": true
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
            "headerClass": "headerBdg",
            "openByDefault": true
          }
     ')
    || TO_CLOB('   },
        "childrendefaults": {
          "width": 80,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": false,
          "type": ["TM1DataColumnInteger"],
          "cellClass": "cellClass-right-text"
        },
        "childrenCustomTypes": {
          "N_FOTO_SPEC": {
            "type": ["TM1DataColumnText"],
            "cellClass": "cellClass-left-text",
            "hide": "tru')
    || TO_CLOB('e"
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
            "hide": "true"
          },
          "EXTRA_CONTR": {
            "hide": "true"
          },
          "D_ART_slash_TGT": {
            "hide": "true"
          },
          "D_FOTO_slash_TGT": {
            "hide":')
    || TO_CLOB(' "true"
          },
          "MARGINE_LORDO_%": {
            "type": ["TM1DataColumnPercentage"],
            "aggFunc": "weightedAvg",
            "aggFuncParam": "$VENDUTO_PROMO_NETTO"
          },
		 "D_VEND/RIF_%": {
			"type": ["TM1DataColumnPercentage"],
			"columnGroupShow": "always",
           	"aggFunc": ""
		 },
          "SEL": {
            "type": ["TM1DataColumnText"],
            "cellClass": "cellClass-left-text"
          }
        }
      },
      "columnD')
    || TO_CLOB('efs": [
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
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
         ')
    || TO_CLOB(' "type": [
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
          ]
        }
      ],
      "preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
')
    || TO_CLOB('
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_selezioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"]
        },
         {
          "dimension": "Compratore",
          "dimCode": "compratore",
          "dimColumnName": "Compratore",
          "a')
    || TO_CLOB('ttribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "Descrizione",
          "process": "",
          "paramName": "",
          "refresh": ["gc_selezioneFamiglieContributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"]
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
      ')
    || TO_CLOB('"logMemory": true,
      "logTime": true,
      "skip": true,
       "maxCells": 750000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{[Promozione].[(I) Promozione]}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERB')
    || TO_CLOB('YLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
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
          "Versione": "[UFF]"
        }
      },
      "ExecuteMDX": {
')
    || TO_CLOB('
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
          "Updateable",
          "Consolidated",
    ')
    || TO_CLOB('      "HasPicklist",
          "PicklistValues"
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
          "columnGroupShow": "always",
  ')
    || TO_CLOB('        "editable": true,
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
            "cellClass": "cellClass-right-text"
          }')
    || TO_CLOB(',
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
            "type": ["TM1Data')
    || TO_CLOB('ColumnInteger"],
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
            "cellClass":')
    || TO_CLOB(' "cellClass-right-text"
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
          },
          "FF_EC_%')
    || TO_CLOB('": {
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
        "headerName": "Articolo",
       ')
    || TO_CLOB(' "width": 400,
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
          "headerName": "Compra')
    || TO_CLOB('tore",
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
          "type": ["TM1Element"],
     ')
    || TO_CLOB('     "cellClass": "cellClass-left-text"
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
          "width": 100,
          "hide": fa')
    || TO_CLOB('lse,
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
          "headerName": "Categoria"')
    || TO_CLOB(',
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
          "type": ["TM1Element"],
          "cellCla')
    || TO_CLOB('ss": "cellClass-left-text"
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
          "hide": true,
          "rowGroup": fa')
    || TO_CLOB('lse,
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
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "pa')
    || TO_CLOB('ramName": "inPromo",
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
          "refresh": ["gc_selezioneFamiglieCon')
    || TO_CLOB('tributi_targetCategoria","gc_selezioneFamiglieContributi_promozione"]
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Tipo Promozione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promoz')
    || TO_CLOB('ione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Prestazione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
            "Misura Prestazioni": "{[Misura Prestazioni].[listino] }",
            "Contratto": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ')
    || TO_CLOB('ASC)}}"
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
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef" ')
    || TO_CLOB(':  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Prestazione.Descrizione"
      , "headerName": "Prestazione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Descrizione","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Promozione","field":"Promozione.MUI_Descrizione","width":10')
    || TO_CLOB('0,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},

        {"headerName":"Listino",
          "marryChildren":true,
          "children" :
          [
            {"headerName":"Extra Contratto","field":"MisuraPrestazioni$listino$$Contratto$ExtraContratto","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger"],"cellClass": "cellClass-right-text"} ,
            {"headerName":"Contratto","field":"MisuraPrestazioni$listino$$Cont')
    || TO_CLOB('ratto$Contratto_1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger"],"cellClass": "cellClass-right-text"}
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
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_Con')
    || TO_CLOB('figurazioneSubsetPianificazione",
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
   ')
    || TO_CLOB('         "Versione": "{[Versione].[UFF]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà": "{[Misura Promozione Proprietà].[Misura 1]}"
          }
        },
        "FROM": "[Con')
    || TO_CLOB('figurazione Promozione - Proprietà]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/Riferimento",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistV')
    || TO_CLOB('alues"
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
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellCla')
    || TO_CLOB('ss-left-text","type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":150,"hide":false,"rowG')
    || TO_CLOB('roup": false , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerNam')
    || TO_CLOB('e":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropri')
    || TO_CLOB('et_agrave_$Data_Fine_Freschi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","wid')
    || TO_CLOB('th":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":100,"hide":false,"rowGroup": f')
    || TO_CLOB('alse , "editable": false,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},

        {"headerName":"Data Scadenza Conferma Prezzi","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Pianificazione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"')
    || TO_CLOB('type":["TM1DataColumnText"]},
        {"headerName":"Volantino/Piano Media","field":"MisuraPromozionePropriet_agrave_$resp_mktg2","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine","field":"MisuraPromozioneProp')
    || TO_CLOB('riet_agrave_$DataFine","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":1')
    || TO_CLOB('00,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": t')
    || TO_CLOB('rue,"type":["TM1DataColumnDecimal3"]}

      ]
    ,"preSelections": [
        {
          "dimension": "Promozione",
          "dimCode": "promozione",
          "dimColumnName": "Promozione",
          "attribute": "Descrizione",
          "attrCode": "descrizione",
          "attrColumnName": "MUI_Descrizione",
          "process": "MUI_DUMMY_ConfigurazioneSubsetPianificazione",
          "paramName": "inPromo",
          "refresh": ["gc_listinoContributi","gc_timing"]
        }')
    || TO_CLOB('
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
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "}Clients": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, ASC)}}",
                      "Anno": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
                     ')
    || TO_CLOB(' "ID": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"
                  }
              },
              "COLS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "MisuraPianoOperativoCommerciale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Piano Operativo Commerciale] )}, 0)}, ASC)}}"
                  }
              },
              "FROM": "[Promozione Creazione]"

          },
        "ExecuteMDX": {
')
    || TO_CLOB('                     "Members": [
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
                        "HasPicklist",
                        "PicklistValues"')
    || TO_CLOB('
                      ]
                    },

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "ID.Name"
                                 , "headerName": "ID"
                                 , "width":200
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
    ')
    || TO_CLOB('          {"headerName":"Anno","field":"Anno.Name","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
              {"headerName":"Client","field":"_cbrace_Clients.MUI_Client","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"ID","field":"ID.Name","width":80,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Ordine Timone","field":"MisuraPianoO')
    || TO_CLOB('perativoCommerciale$Ordinamento","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Desc","field":"MisuraPianoOperativoCommerciale$Desc","width":500,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Gruppo","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":120,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
 ')
    || TO_CLOB('             {"headerName":"Sottogruppo","field":"MisuraPianoOperativoCommerciale$Canale","width":120,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
              {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerci')
    || TO_CLOB('ale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
              {"headerName":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine')
    || TO_CLOB('_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
              ],
          "actions": [{
            "componentId": "pia_cp_crea",
            "componentLabel": "Crea Promozione",
            "process": "')
    || TO_CLOB('MUI_DUMMY_DIM.Promozione.NewPromo",
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Raggruppamento Foto": "{{TM1SORTBYINDEX({{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggrupp')
    || TO_CLOB('amento Foto] )}, 0)}}, ASC)}}"
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
        "Members": [
          "Name",
         ')
    || TO_CLOB(' "UniqueName",
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
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {},
      "columnDefs": [
        {
          "headerName": "Reparto",
          "field": "Raggruppa')
    || TO_CLOB('mentoFoto.MUI_Reparto",
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
          ]
        },
  ')
    || TO_CLOB('      {
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
          "editable": fals')
    || TO_CLOB('e,
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
          ]
     ')
    || TO_CLOB('   }
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , { {[Compr')
    || TO_CLOB('atore].[S000] }, {[Compratore].[NA] }})} , [Compratore].[MUI_Sort] , BASC )}"
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
        }
      },
      "ExecuteMDX": {
        "Members": [
         ')
    || TO_CLOB(' "Name",
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
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "autoGroupColumnDef": {},
      "columnDefs": [
        {
          "headerName": "Category Manager"')
    || TO_CLOB(',
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
            "TM1Element"
          ]
')
    || TO_CLOB('        },
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
            "attribute": "Descrizione"
          },
     ')
    || TO_CLOB('     "destination": [
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
      "rowSuppressionEnabled": false,
      "colSuppressionEna')
    || TO_CLOB('bled": false,
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
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, ')
    || TO_CLOB('0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{ [Compratore].[TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
          }
        },
        "FROM": "[Configurazione RaggrFoto]",
        "WHERE": {
          "Misura Config RaggrFoto": "[OK]"
        }
      },
      "Execute')
    || TO_CLOB('MDX": {
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
          "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
        ]
      },
      "DynamicColumns": true,
    ')
    || TO_CLOB('  "DynamicColumnsSettings": {
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
            "openByDefault": true
          },
          "TGT_MKT": {
            "headerClass": "headerMkt",
            ')
    || TO_CLOB('"openByDefault": true
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
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childre')
    || TO_CLOB('nCustomTypes": {
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
        ]
      },
      "columnDefs": [
        {
          "headerName": "Tot Foto",
          "field": "Ragg')
    || TO_CLOB('ruppamentoFoto.MUI_TOT",
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
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
   ')
    || TO_CLOB('   "preSelections": [
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
        }
      ],
      "actions": [
        {
          "componentId": "pia_fo_condividi",
          "componentLabel"')
    || TO_CLOB(': "Condividi",
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
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            },
')
    || TO_CLOB('            {
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
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasP')
    || TO_CLOB('icklist": false,
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
   ')
    || TO_CLOB('     "COLS": {
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
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre",
          "Attributes/De')
    || TO_CLOB('scrizione",
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
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ')
    || TO_CLOB(',
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","')
    || TO_CLOB('field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Valore Punto Fragola","field":"MisuraPianoOperativoCommerciale$Costo_punto_Fragola","width":150,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnDecimal3" ,"numericCol')
    || TO_CLOB('umn"]}
      ]
    },
    {
      "name": "gc_GabbiaPunti",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "ID": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Gabbia Punti Fragola": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUB')
    || TO_CLOB('SETALL( [Misura Gabbia Punti Fragola] )}, 0)}, ASC)}}"
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
          "HasPicklist",
          "PicklistValues"
        ]
      },



      "autoGroupColumnDef" :  {

      } ,')
    || TO_CLOB('
      "columnDefs": [
        {"headerName":"ID","field":"ID.Name","width":100,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Prezzo Minimo","field":"MisuraGabbiaPuntiFragola$PrezzoMinimo","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Prezzo Massimo","field":"MisuraGabbiaPuntiFragola$PrezzoMassimo","width":100,"hide":false,"rowGroup": false , "editable')
    || TO_CLOB('": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Punti Fragola","field":"MisuraGabbiaPuntiFragola$PuntiFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]}
      ],
      "actions": [{
            "componentId": "pia_ga_modifica",
            "componentLabel": "Modifica Valore Punti Fragola",
            "process": "MUI_DUMMY_DIM.Cambio valore Costo Punti Fragola",
            "timeout":-1')
    || TO_CLOB(',
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
              "paramName": "inPromo",
              "label": "Descrizione",
              "hasPicklist": true
')
    || TO_CLOB('            }]
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Tip')
    || TO_CLOB('o Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Contratto": "{[Contratto].[Extra Contratto],[Contratto].[Contratto_1] }",
            "Prestazione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
            "Misura Prestazioni": "[Misura Prestazioni].[Listino]"
          }
        },
        "FROM')
    || TO_CLOB('": "[Promozione Modifica - Listino]"

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
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasP')
    || TO_CLOB('icklist",
          "PicklistValues"
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
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT"')
    || TO_CLOB(':{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "avg", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "DataInizio":{"cellClass": "dateFormat","ty')
    || TO_CLOB('pe":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
          "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"')
    || TO_CLOB(']
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Caption_Default","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"h')
    || TO_CLOB('eaderName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ],
      "groupRowAggNodes": {')
    || TO_CLOB('
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "In')
    || TO_CLOB('iziativa": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Iniziative": "{[Misura Configurazione Iniziative].[OK] }"
          }
        },
        "FROM": "[Configurazione Promozione - Iniziative]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
        }

      },
      "ExecuteM')
    || TO_CLOB('DX": {
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
          "HasPicklist",
          "PicklistValues"
        ]
      },



      "autoGroupColumnDef" :  {
       ')
    || TO_CLOB(' "cellRendererParams":{ "suppressCount": true }
      , "field": "Iniziativa.Descrizione"
      , "headerName": "Iniziativa"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"')
    || TO_CLOB('type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
  ')
    || TO_CLOB('      {"headerName":"OK","field":"MisuraConfigurazioneIniziative$OK","width":70,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnInteger" ,"numericColumn"]}
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione": "{[Misura Configurazione Promozione].[SEL]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}}')
    || TO_CLOB(' , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Meccanica Complesse": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Meccanica Complesse] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Meccaniche]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/MUI_Descrizione",
   ')
    || TO_CLOB('       "Attributes/Canale",
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
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
 ')
    || TO_CLOB('     , "headerName": "Promozione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text", "type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":60,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element')
    || TO_CLOB('"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":150,"hide":true,"rowGrou')
    || TO_CLOB('p": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},

        {
        "headerName": "Meccaniche Semplici",
        "openByDefault": true,
        "children": [

        {"headerName":"Sconto %","field":"MeccanicaComplesse$M002","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Prezzo Corto","field":"MeccanicaComplesse$M003","width":110,"hide":false')
    || TO_CLOB(',"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"P. Fragola","field":"MeccanicaComplesse$M004","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc % Fidaty","field":"MeccanicaComplesse$M005","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColum')
    || TO_CLOB('nText"]},
        {"headerName":"Prezzo Corto & P.Fragola","field":"MeccanicaComplesse$M006","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc% Fidaty  &  P. Fragola","field":"MeccanicaComplesse$M007","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"3x2","field":"MeccanicaComplesse$M0')
    || TO_CLOB('08","width":50,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"1 + 1","field":"MeccanicaComplesse$M009","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Il 2 costa la metà ","field":"MeccanicaComplesse$M010","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-cent')
    || TO_CLOB('er-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto % + Punti Fragola","field":"MeccanicaComplesse$M012","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc + Facile val","field":"MeccanicaComplesse$M014","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Oggetti articol')
    || TO_CLOB('o","field":"MeccanicaComplesse$M015","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono sc.Fidaty (F11)","field":"MeccanicaComplesse$M018","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc Val Fidaty","field":"MeccanicaComplesse$M023","width":110,"hide":false,"rowGroup": false , "e')
    || TO_CLOB('ditable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto fidaty per classe cliente (%)","field":"MeccanicaComplesse$M024","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"PC + Triplica Punti","field":"MeccanicaComplesse$M025","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["T')
    || TO_CLOB('M1DataColumnText"]},
        {"headerName":"Punti Fragola su insieme","field":"MeccanicaComplesse$M104","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Triplica i punti","field":"MeccanicaComplesse$M111","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Punti Fragola Set per Triplica Pu')
    || TO_CLOB('nti","field":"MeccanicaComplesse$M164","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sconto a valore","field":"MeccanicaComplesse$M031","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - punti fragola","field":"MeccanicaComplesse$M034","')
    || TO_CLOB('width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sconto percentuale","field":"MeccanicaComplesse$M035","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sc val - Set semplice","field":"MeccanicaComplesse$M131","width":170,"hide":false,"rowG')
    || TO_CLOB('roup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - PF - Set semplice","field":"MeccanicaComplesse$M134","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sc % - Set semplice","field":"MeccanicaComplesse$M135","width":170,"hide":false,"rowGroup": false , "editable": true,')
    || TO_CLOB(' "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Semplice per Triplica Oggetti","field":"MeccanicaComplesse$M165","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]} ,
        {"headerName":"PAGINA PUBBLICITARIA","field":"MeccanicaComplesse$M000","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText')
    || TO_CLOB('"]},
        {"headerName":"Meccanica NO Promo con Spazio","field":"MeccanicaComplesse$M090","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}

        ]} ,

        {
        "headerName": "Meccaniche Complesse",
        "openByDefault": true,
        "children": [

        {"headerName":"SSc + Facile Set","field":"MeccanicaComplesse$M114","width":110,"hide":false,"rowGroup": false , "editable": true, "cel')
    || TO_CLOB('lClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Oggetti Set","field":"MeccanicaComplesse$M115","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto SET a valore","field":"MeccanicaComplesse$M201","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"')
    || TO_CLOB('Sconto SET %","field":"MeccanicaComplesse$M205","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Set Esteso Valore","field":"MeccanicaComplesse$M301","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Esteso - prezzo corto","field":"MeccanicaComplesse$M303","width":110,"hide":fa')
    || TO_CLOB('lse,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Esteso Punti Fragola","field":"MeccanicaComplesse$M304","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Percentuale Set Esteso","field":"MeccanicaComplesse$M305","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass')
    || TO_CLOB('-center-text","type":["TM1DataColumnText"]}

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
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": true,
                  "DIMENSIONS": {
                    "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
   ')
    || TO_CLOB('               }
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
                        "Name",
         ')
    || TO_CLOB('               "Attributes/MUI_Descrizione",
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
')
    || TO_CLOB('
                        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    } ,
        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Promozione.MUI_Descrizione"
                                 , "headerName": "Promozione"
                                 , "width":300
                      ')
    || TO_CLOB('           , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
              {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
              {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
              {"headerName":"Descrizione + Data","field":"')
    || TO_CLOB('Promozione.MUI_Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Descrizione","field":"Promozione.Descrizione","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
              {"headerName":"Semestre","field":"Promozione.MUI')
    || TO_CLOB('_Semestre","width":85,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":90,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Descrizione","field":"MisuraPianoOperativoCommerciale$Descrizione","width":300,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"hea')
    || TO_CLOB('derName":"Descrizione.","field":"MisuraPianoOperativoCommerciale$Desc_Agg","width":300,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio.","field":"MisuraPianoOperativoCommerciale$DataInizi')
    || TO_CLOB('o_Agg","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine.","field":"MisuraPianoOperativoCommerciale$DataFine_Agg","width":110,"hide":false,"')
    || TO_CLOB('rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$DataInizioIst_Agg","width":110,"hide":false,"rowG')
    || TO_CLOB('roup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$DataFineIst_Agg","width":110,"hide":false,"rowGroup": false')
    || TO_CLOB(' , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Abbatt Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":200,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Abbatt Prezzi_","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ_AGG","width":200,"hide":false,"rowGroup": false , "edi')
    || TO_CLOB('table": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Ordine Timone.","field":"MisuraPianoOperativoCommerciale$Ordinamento_Agg","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"')
    || TO_CLOB('headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa %_","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc__AGG","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoCommerciale$NO_STMP_ETICH","width":100,')
    || TO_CLOB('"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"CanalePromozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"StatoPromozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName"')
    || TO_CLOB(':"TipoPromozione","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note","field":"MisuraPianoOperativoCommerciale$Note","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "edit')
    || TO_CLOB('able": true, "type":[ "TM1DataColumnText"]}

            ],
            "actions": [{
            "componentId": "pia_mp_aggiorna",
            "componentLabel": "Aggiorna Promozione",
            "process": "MUI_DUMMY_DIM.Promozione.UpdatePromo (I)",
            "timeout":-1,
            "refresh": ["gc_ModificaPromozione"],
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label":')
    || TO_CLOB(' "Anno",
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
			 "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "Negozio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
                  }
              },
              "COLS": {
                  "NON_EMPTY": true,
                  "DIMENSIONS"')
    || TO_CLOB(': {
                    "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
                    "Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}" ,
                    "Canale": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Canale] )}, 0)}, ASC)}}",
                    "Misura Canale": ')
    || TO_CLOB('"{ [Misura Canale].[Tipo_Neg_Calc], [Misura Canale].[Tipo_Neg_Input], [Misura Canale].[Canale], [Misura Canale].[DataInizio], [Misura Canale].[DataFine],[Misura Canale].[Delta] }"



                  }
              },
              "FROM": "[Canale Neg SezTematica]"

          },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
                        "Attributes/Descrizione",
                        "Attributes/Canale",
                  ')
    || TO_CLOB('      "Attributes/Anno",
                        "Attributes/MUI_Semestre",
                        "Attributes/MUI_Descrizione",
                        "Attributes/MUI_ZonaPromo",
                        "Attributes/MUI_DescrizioneData",
                        "UniqueName"
                              ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                ')
    || TO_CLOB('        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    },

          "DynamicColumns" : true ,
          "DynamicColumnsSettings" :{
              "headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" , "Descrizione"]  ,
              "headerdefaults":  {"marryChildren" : true}  ,
            "headerCustomTypes":{
              "RIF_MKT_DT":{
                "openByDefault": ')
    || TO_CLOB('true
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
              "childrendefaults":  {"width":110,"hide":false,"rowGroup": f')
    || TO_CLOB('alse ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnText"] },
              "childrenCustomTypes"  : {
                                   "DataInizio":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
                                   "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"}
                    ')
    || TO_CLOB('           }
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
     ')
    || TO_CLOB('     {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione + Data","field":"Promozio')
    || TO_CLOB('ne.MUI_Descrizione","width":200,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
          {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Negozio","field":"Negozio.Descrizione","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}



              ]
            ,
			"rowSuppressionEnabled": true,
			"colSuppressio')
    || TO_CLOB('nEnabled": false
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Versione": "{[Versione].[UFF]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Pr')
    || TO_CLOB('omozione],BASC)}"
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
          "Name",
          "Attributes/Canale",
          "Attributes/MUI_Descrizione",
          ')
    || TO_CLOB('"Attributes/Anno",
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
          "HasPicklist",
          "PicklistValues"
        ]
      },

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                 ')
    || TO_CLOB('                , "field": "Promozione.MUI_Descrizione"
                                 , "headerName": "Promozione"
                                 , "width":500
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerN')
    || TO_CLOB('ame":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Riferimento","field":"Promozione.Riferimento","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione + Data","field":"Promozione')
    || TO_CLOB('.MUI_Descrizione","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Las","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": ')
    || TO_CLOB('"dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Freschi","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"')
    || TO_CLOB(']},
          {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"')
    || TO_CLOB('Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":150,"hide":false,"rowGroup": false , "editable": false,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Scadenza Conferma Prezzi')
    || TO_CLOB('","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":150,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Pianificazione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Volantino/Piano Media","field":"MisuraPromozionePropriet_agrave_$resp_mktg2","wid')
    || TO_CLOB('th":120,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataCo')
    || TO_CLOB('lumnDate"]},
          {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"he')
    || TO_CLOB('aderName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}

      ]
    }
  ]
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione')
    || TO_CLOB('],BASC)}"
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
          "Name",
          "Attributes/MUI_Descrizione",
          "Attributes/Anno",
          "Attributes/C')
    || TO_CLOB('anale",
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
          "HasPicklist",
          "PicklistValues"
        ]
      } ,
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      ')
    || TO_CLOB(', "headerName": "Promozione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.Anno","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descri')
    || TO_CLOB('zione","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowG')
    || TO_CLOB('roup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numeri')
    || TO_CLOB('cColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
   ')
    || TO_CLOB('     {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$E')
    || TO_CLOB('TICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoCommerciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
     ')
    || TO_CLOB('   {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Configurazione Promozione": "{[Misura Configurazione Promozione].[SEL]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} ')
    || TO_CLOB(', [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}"
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
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Descrizione",
          "Attributes/M')
    || TO_CLOB('UI_Descrizione",
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
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.MUI_Descrizione"
      , "headerNa')
    || TO_CLOB('me": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide"')
    || TO_CLOB(':true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Zona BDG","field":"ZonaPromo$ZONAPROMO_ALL","width":90,"hide":false,"rowGroup": false')
    || TO_CLOB(' , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO","field":"ZonaPromo$ZONAPROMO_1_01","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_02] EMILIA","field":"ZonaPromo$ZONAPROMO_1_02","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"ZonaP')
    || TO_CLOB('romo$ZONAPROMO_1_03","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_04] VERONA","field":"ZonaPromo$ZONAPROMO_1_04","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"ZonaPromo$ZONAPROMO_2_01","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInt')
    || TO_CLOB('eger"]},
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
 ')
    || TO_CLOB('           "Pubblicità": "{[Pubblicità].[(I) Volantino]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Zona Promo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Zona Promo] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Sezione Tematica]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes/Canale",
          "Attributes/A')
    || TO_CLOB('nno",
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
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Pubblicit_agrave_.Descrizione"
      ,')
    || TO_CLOB(' "headerName": ""
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":50,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":')
    || TO_CLOB('true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.MUI_Descrizione","width":100,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione Sezione Tematica","field":"MisuraZonaPromo$Descrizione","width":170,"hide":fal')
    || TO_CLOB('se,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Canale","field":"MisuraZonaPromo$Canale","width":70,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Agg_canale","field":"MisuraZonaPromo$Agg_canale","width":90,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"DataInizio","field":"MisuraZonaPromo$DataInizio","width":80,"hide":false,"rowGroup"')
    || TO_CLOB(': false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"DataFine","field":"MisuraZonaPromo$DataFine","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Zona BDG","field":"MisuraZonaPromo$ZONAPROMO_ALL","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO",')
    || TO_CLOB('"field":"MisuraZonaPromo$ZONAPROMO_1_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_02] EMILIA","field":"MisuraZonaPromo$ZONAPROMO_1_02","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"MisuraZonaPromo$ZONAPROMO_1_03","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"s')
    || TO_CLOB('um", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_04] VERONA","field":"MisuraZonaPromo$ZONAPROMO_1_04","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"MisuraZonaPromo$ZONAPROMO_2_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_02] LAZIO","field":"MisuraZonaPromo$ZONAPROMO_2_0')
    || TO_CLOB('2","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
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
      "MDX": {
              "ROWS": {
                  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "ID Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}"
                  }
              },
              "COLS": {
                ')
    || TO_CLOB('  "NON_EMPTY": false,
                  "DIMENSIONS": {
                      "Misura Configurazione Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Configurazione Sezione Tematica] )}, 0)}, ASC)}}"
                  }
              },
              "FROM": "[Configurazione Sezione Tematica]"

          },
        "ExecuteMDX": {
                     "Members": [
                        "Name",
                        "Attributes/Name",
						"UniqueName"
  ')
    || TO_CLOB('                            ] ,
                      "Cells": [
                        "Ordinal",
                        "Value",
                        "Updateable",
                        "Consolidated" ,
                        "HasPicklist",
                        "PicklistValues"
                      ]
                    },

        "autoGroupColumnDef" :  {   } ,
        "columnDefs": [
          {"headerName":"ID Sezione Tematica","field":"IDSezioneTematica.Name","wid')
    || TO_CLOB('th":100,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione","field":"MisuraConfigurazioneSezioneTematica$Descrizione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Tipo","field":"MisuraConfigurazioneSezioneTematica$TIPO","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Note","field":"MisuraConf')
    || TO_CLOB('igurazioneSezioneTematica$Note","width":170,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Operazione","field":"MisuraConfigurazioneSezioneTematica$Operazione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]}



              ],
          "actions": [{
            "componentId": "pia_st_aggiorna",
            "componentLabel": "Aggiorna Anagrafica Sezioni Tematiche",
            "proces')
    || TO_CLOB('s": "MUI_DUMMY_DIM.Sezione Tematica",
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
        ')
    || TO_CLOB('  "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Misura_Configurazione_Macrospazi_Listino": "{[Misura_Configurazione_Macrospazi_Listino].[CC],[Misura_Configurazione_Macrospazi_Listino].[EC]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Listino - Promo]"

      },
')
    || TO_CLOB('      "ExecuteMDX": {
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
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerc')
    || TO_CLOB('onf" : ["MUI_DescrizioneData" , "Descrizione" ]  ,
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
            "headerClass": "headerRep')
    || TO_CLOB('",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":260,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"closed",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true')
    || TO_CLOB(' }
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {

            "Negozio": "{EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}},{[Negozio].[NA] })}"
          }
        }')
    || TO_CLOB(',
        "COLS": {
          "NON_EMPTY": true ,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}" ,
            "Misura_Configurazione_Macrospazi_Neg_Promo": "{[Misura_Configurazione_Macrospazi_Neg_Promo].[DEFAULT],[Misura_Configura')
    || TO_CLOB('zione_Macrospazi_Neg_Promo].[SPAZIO]}"
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
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPickli')
    || TO_CLOB('st",
          "PicklistValues"
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
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT')
    || TO_CLOB('":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"')
    || TO_CLOB('] ,"aggFunc": "","columnGroupShow":"always"}
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

        {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
 ')
    || TO_CLOB('       {"headerName":"Negozio","field":"Negozio.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Negozio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
            "MacroSpazio": "{[Ma')
    || TO_CLOB('croSpazio].[MacroSpazio_liv0]}" ,
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
          "Attributes/MUI_ZonaPromo",
          "UniqueName"
        ] ,
        "Cells": [
    ')
    || TO_CLOB('      "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "header')
    || TO_CLOB('Class": "headerAcq",
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
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"closed",  "editable": true, "type":[ "TM1DataColumnText"] },
        "children')
    || TO_CLOB('CustomTypes"  : {
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
        {"headerName":"Tot. Negozio","field":"Negozio.MU')
    || TO_CLOB('I_TOT","width":200,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
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
            "Misura_Macrospazi"')
    || TO_CLOB(': "{[Misura_Macrospazi].[(I) Macro Spazi]}"
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
          "HasPicklist",
          "PicklistValues"
        ]
      },

')
    || TO_CLOB('
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
          },
')
    || TO_CLOB('          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "NOTE":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GG_COM_DEFAULT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"')
    || TO_CLOB('},
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
      "skip": true,
      "MDX": {
 ')
    || TO_CLOB('       "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MacroSpazio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura_Configurazione_Macrospazi_Listino": "{[Misura_Configurazione_Macrospazi_Listino].[(I) Configurazione Macro Listino]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Listin')
    || TO_CLOB('o]"

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
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
 ')
    || TO_CLOB('       "headerdefaults":  {"marryChildren" : true}  ,
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
 ')
    || TO_CLOB('       },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "TIPO_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_EXTRA_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "DATA_CAMBIO":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
         ')
    || TO_CLOB(' "TIPO_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_EXTRA_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.MUI_Descrizione","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]

    },
    {
      "name": "gc_Spazi_MacroSpazi_Aggior')
    || TO_CLOB('na",
      "logMemory": true,
      "logTime": true,
      "skip": true,
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
            "Misura_Macrospazi": "{[Misura_Macrospazi].[(I) Macro Spazi Agg.]}"
          }
        },
        "FROM": "')
    || TO_CLOB('[Configurazione Macrospazio]"

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
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Des')
    || TO_CLOB('crizione"]  ,
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
            "openByDefault": true')
    || TO_CLOB('
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "GG_COM_NEW":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "OPERAZIONE":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GRP_SPZ":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
')
    || TO_CLOB('
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.MUI_Descrizione","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}

      ],
      "actions": [{
            "componentId": "pia_ms_crea",
            "componentLabel": "Creazione Macrospazio",
            "process": "MUI_DUMMY_DIM.MacroSpazioInsertElement",
            "timeout":-1,
            "refresh": ["gc_Spaz')
    || TO_CLOB('i_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
            "params":[{
              "paramName": "inCodice",
              "label": "Codice Macrospazi",
              "hasPicklist": false
            },
            {
              "paramName": "inDescrizione",
              "label": "Descrizione Macrospazio",
              "hasPicklist": false
            },
            {
              "paramName": "inDescTimone",
              "label": "Desrizione Tim')
    || TO_CLOB('one",
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
            "process": "MUI_DUMMY_CUB.ConfigurazioneMacrospazi_Aggiornamento",
            "timeout":-1,
    ')
    || TO_CLOB('        "refresh": ["gc_Spazi_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
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
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_SpaziCondivisi_spazio",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
      "maxCells": 2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Spazio": "{{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 0')
    || TO_CLOB(')},     [Spazio].[MUI_Sort]  ,  ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "}ElementAttributes_Spazio": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Spazio] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[}ElementAttributes_Spazio]",
        "WHERE": {
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "UniqueName",
          "Attr')
    || TO_CLOB('ibutes/MacroSpazioDescrizione",
          "Attributes/Descrizione",
          "Attributes/MUI_Compratore",
          "Attributes/MUI_Reparto"
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
      "autoGroupColumnDef": {},
      "columnDefs": [
         {
          "headerName": "Reparto",
          "field": "Spazio.MUI_Reparto",
       ')
    || TO_CLOB('   "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Spazio.MUI_Compratore",
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Macro Spaz')
    || TO_CLOB('io",
          "field": "Spazio.MacroSpazioDescrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Spazio",
          "field": "Spazio.Descrizione",
          "width": 200,
          "hide": false,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
')
    || TO_CLOB('

        }
      ],
      "selections": [
        {
          "source": {
            "table": "gc_Spazi_SpaziCondivisi_spazio",
            "dimension": "Spazio",
            "attribute": "Descrizione"
          },
          "destination": [
            {
              "table": "gc_Spazi_SpaziCondivisi_spazio",
              "dimension": "Spazio",
              "attribute": "Descrizione"
            }
          ]
        }
      ]
    },
    {
      "name": "gc_Spazi_S')
    || TO_CLOB('paziCondivisi_compratore",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "suppressRowClickSelection": false,
      "maxCells": 2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , { {[Compratore].[S000] }, {[Compratore].[NA] }})} ')
    || TO_CLOB(', [Compratore].[MUI_Sort] , BASC )}"
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
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "UniqueName",
      ')
    || TO_CLOB('    "Attributes/CategoryManager",
          "Attributes/MUI_Reparto",
          "Attributes/MUI_Descrizione"
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
      "autoGroupColumnDef": {},
      "columnDefs": [
        {
          "headerName": "Category Manager",
          "field": "Compratore.CategoryManager",
          "width": 150,
')
    || TO_CLOB('          "hide": false,
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
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field')
    || TO_CLOB('": "Compratore.MUI_Descrizione",
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
            "table": "gc_Spazi_SpaziCondivisi_compratore",
            "dimension": "Compratore",
            "attribute": "Descrizione"
          },
          "destination": [
            {
              "table')
    || TO_CLOB('": "gc_Spazi_SpaziCondivisi_compratore",
              "dimension": "Compratore",
              "attribute": "Descrizione"
            }
          ]
        }
      ]
    },
    {
      "name": "gc_Spazi_SpaziCondivisi_associazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": false,
      "colSuppressionEnabled": false,
      "maxCells": 2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIM')
    || TO_CLOB('ENSIONS": {
            "Spazio": "{{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 0)},     [Spazio].[MUI_Sort]  ,  ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{ [Compratore].[')
    || TO_CLOB('TOT_COMP], [Compratore].[S22]},{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000], [Compratore].[TOT_COMP], [Compratore].[S22]}) }}"
          }
        },
        "FROM": "[Configurazione RaggrSpazi]",
        "WHERE": {
          "Misura Config RaggrFoto": "[OK]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "UniqueName",
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MU')
    || TO_CLOB('I_Semestre",
          "Attributes/Descrizione",
          "Attributes/Compratore",
          "Attributes/MacroSpazioDescrizione",
          "Attributes/MUI_TOT",
          "Attributes/MUI_DescrizioneData"
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
        "headerconf":')
    || TO_CLOB(' [
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
          },
          "TGT_')
    || TO_CLOB('REP": {
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
          "type": [
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
        }
      },
      "autoGr')
    || TO_CLOB('oupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "cellClass": "cellClass-left-text",
        "field": "Spazio.Descrizione",
        "headerName": "Spazio",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Totale",
          "field": "Spazio.MUI_TOT",
          "width": 200,
          "hide": true,
          "rowG')
    || TO_CLOB('roup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Compratore",
          "field": "Spazio.Compratore",
          "width": 200,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Macro Spazio",
          "field": "Spazio.MacroSpazioDescrizione",
       ')
    || TO_CLOB('   "width": 200,
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
          "process": "",
          "pa')
    || TO_CLOB('ramName": "",
          "refresh": ["gc_Spazi_SpaziCondivisi_associazione"]
        }
      ],
      "actions": [
        {
          "componentId": "pia_sc_condividi",
          "componentLabel": "Condividi",
          "process": "MUI_DUMMY_CUB.ConfigurazioneRaggrSpazi",
          "timeout": -1,
          "refresh": [
            "gc_Spazi_SpaziCondivisi_associazione"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribu')
    || TO_CLOB('te": "MUI_Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
            },
            {
              "dimension": "Spazio",
              "attribute": "Descrizione",
              "paramName": "inSpazio",
              "label": "Spazio",
              "hasPicklist": false,
              "disabled": true,
              "visible": true
          ')
    || TO_CLOB('  },
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
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
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('39','/Reporting/Copia_in_Pianificazione',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_CopiaInPianificazione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
           "maxCells": 750000,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[R')
    || TO_CLOB('EP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP -')
    || TO_CLOB(' Scenario] )}, 0)} , ASC)}",
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                       	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
                ')
    || TO_CLOB('        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"REP - Misura Repo')
    || TO_CLOB('rting Articolo": "{[REP - Misura Reporting Articolo].[Misura Reporting Articolo - Reporting]}"
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
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Upd')
    || TO_CLOB('ateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
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
          "TGT_MKT": {
            "hea')
    || TO_CLOB('derClass": "headerMkt",
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
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
				"c')
    || TO_CLOB('hildrenCustomTypes"  : {
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
					"hide": false,
					"rowGroup": false,
					"ed')
    || TO_CLOB('itable": true,
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
					"rowGroup": false,
					"editable": true,
					')
    || TO_CLOB('"type": [
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
					"editable": true,
					"type": [
						"TM1Ele')
    || TO_CLOB('ment"
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
					"type": [
						"TM1Element"
					]
				}
')
    || TO_CLOB('
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
					"label": "Promozione",
					"hasPicklist": ')
    || TO_CLOB('true,
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
					"componentLabel": "Copia estesa",
					"process": "MUI_DUMMY_EXP.CUB.Re')
    || TO_CLOB('porting Articolo - FlagCopiaEstesa",
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
					"attribute": "Descrizione",
					"paramName"')
    || TO_CLOB(': "pCompratore",
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
          "attrColumnName": "Descrizione",
          "process": "",
    ')
    || TO_CLOB('      "paramName": "",
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
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[RE')
    || TO_CLOB('P - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP ')
    || TO_CLOB('- Compratore] )}, 0)} , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccan')
    || TO_CLOB('ica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                         "REP - Scenario": "{ [REP - Scenario].[Scenario -Storico]}",
						"REP - Misura Reporting Articolo": "{[RE')
    || TO_CLOB('P - Misura Reporting Articolo].[Reporting ACQ]}"
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
                    "Attributes/GRMDesc",
               ')
    || TO_CLOB('     "Attributes/DescrizioneArticolo",
                    "Attributes/SubGrmDesc",
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
				"headerconf" : [ "MUI_DescrizioneData" , "Descrizione" ,  "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCus')
    || TO_CLOB('tomTypes":{
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
            "headerClass": "headerBdg",
            "')
    || TO_CLOB('openByDefault": true
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
				"width": 300,
				"pinned": "left",
			')
    || TO_CLOB('	"type": [
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
					"rowGroup": false,
					"editable": false,
					"ty')
    || TO_CLOB('pe": [
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
						"TM1El')
    || TO_CLOB('ement"
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
					"type": [
						"TM1Element"
					]
			')
    || TO_CLOB('	} ,
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
				} ,

                {
					"headerN')
    || TO_CLOB('ame": "Fornitori",
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
					"header')
    || TO_CLOB('Name": "Meccanica Semplice",
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
			"ro')
    || TO_CLOB('wSuppressionEnabled": false,
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
             "maxCells":10000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": "{[REP - Sez')
    || TO_CLOB('ione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          			    "REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [R')
    || TO_CLOB('EP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLE')
    || TO_CLOB('VEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                       "REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF')
    || TO_CLOB('_MKT_DT]}",
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
					"Attributes/MUI_DescrizioneData",
                    "Attributes/D')
    || TO_CLOB('escrizione",
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
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
')
    || TO_CLOB('			"DynamicColumnsSettings" :{
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
            "openByDefault": true
        ')
    || TO_CLOB('  },
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
			} ,

			"autoGroupColumnDef": {')
    || TO_CLOB('
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
	')
    || TO_CLOB('			} ,
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
     ')
    || TO_CLOB('            {
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
              {
					"headerName": "Artic')
    || TO_CLOB('olo",
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
					"headerName": "Fornitori",
					"fie')
    || TO_CLOB('ld": "REP_minus_Fornitore.Descrizione",
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
					"headerName": "Meccanica Semplice",
')
    || TO_CLOB('
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
			"rowSuppressionEnabled": false,
			"col')
    || TO_CLOB('SuppressionEnabled": false
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
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}" ,
						"REP - Categoria": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": tr')
    || TO_CLOB('ue,
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
				"Members')
    || TO_CLOB('": [
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
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : ')
    || TO_CLOB('true ,
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
            "openByDefault": true
          ')
    || TO_CLOB('},
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
			} ,

			"autoGroupColumnDef":')
    || TO_CLOB(' {
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
    ')
    || TO_CLOB('           {
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
					]
				} ,
              {
				')
    || TO_CLOB('	"headerName": "Direzione",
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
					"field')
    || TO_CLOB('": "REP_minus_Categoria.Descrizione",
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

			"colSuppressionEnabled": false
')
    || TO_CLOB('
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
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica')
    || TO_CLOB('": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Promozione": "{TM1SORT(  {TM1FILTERBYL')
    || TO_CLOB('EVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTER')
    || TO_CLOB('BYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MK')
    || TO_CLOB('T_DT]}",
                        "REP - Zona Promo": "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
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
')
    || TO_CLOB('
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
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
			')
    || TO_CLOB('	"headerCustomTypes": {
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
            "headerClass": "headerBdg')
    || TO_CLOB('",
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
					"width": 70,
					"hide": false,
				')
    || TO_CLOB('	"rowGroup": false,
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
					"hide": false,
					"rowGroup": false,')
    || TO_CLOB('
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
					"rowGroup": false,
					"editable": fal')
    || TO_CLOB('se,
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
					"rowGroup": false,
					"editable": false,
					"type": [
')
    || TO_CLOB('
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
          "paramName": "",
          "refresh": ["gc_Storic')
    || TO_CLOB('oArticoloPerZonaAcq"]
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
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Temati')
    || TO_CLOB('ca": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Promozione": "{TM1SORT(  {TM1FILTERB')
    || TO_CLOB('YLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILT')
    || TO_CLOB('ERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_')
    || TO_CLOB('MKT_DT]}",
                        "REP - Zona Promo": "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -Storico]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": {
				"Members": [
')
    || TO_CLOB('
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
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
			')
    || TO_CLOB('	"headerCustomTypes": {
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
            "headerClass": "headerBdg')
    || TO_CLOB('",
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
					"width": 70,
					"hide": false,
				')
    || TO_CLOB('	"rowGroup": false,
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
					"hide": false,
					"rowGroup": false,')
    || TO_CLOB('
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
					"rowGroup": false,
					"editable": fal')
    || TO_CLOB('se,
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
					"rowGroup": false,
					"editable": false,
					"type": ')
    || TO_CLOB('[
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
          "paramName": "",
          "refresh": ["gc_Storic')
    || TO_CLOB('oArticoloPerZonaAcqMkt"]
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

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Reparto": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)} , ASC)}"

					}
				},
			')
    || TO_CLOB('	"COLS": {
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
				"Members": [
					"Name",
					"At')
    || TO_CLOB('tributes/Anno",
          			"Attributes/Descrizione",
          			"Attributes/Canale",
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
				"headerconf" : [ "Descrizione" , "Descrizione"]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefau')
    || TO_CLOB('lt": true
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
				"childr')
    || TO_CLOB('enCustomTypes"  : {
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
					"editable": false,
					')
    || TO_CLOB('"type": [
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
						"TM1Element"
				')
    || TO_CLOB('	]
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('48','/Timone/Foto_Speciali/Riepilogo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [{
    "name": "gc_FotoSpeciali_Riepilogo",
    "logMemory": true,
    "logTime": true,
    "skip": true,
    "rowSuppressionEnabled": false,
    "colSuppressionEnabled": false,
    "MDX": {
      "ROWS": {
        "NON_EMPTY": true,
        "DIMENSIONS": {
          "Categoria": "{{[Categoria].[CAT_0000]},{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{ {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}}} , {[Categoria].[CAT_0000]')
    || TO_CLOB('}) }}",
          "Compratore": "{{[Compratore].[S000]},{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]} )}}"
        }
      },
      "COLS": {
        "NON_EMPTY": true,
        "DIMENSIONS": {
          "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
          "Scenario"')
    || TO_CLOB(': " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
          "Sezione Tematica": "{TM1SUBSETALL( [Sezione Tematica] )}",
          "Misura Timone.": "{{[Misura Timone.].[N_FOTO_TOT]},{[Misura Timone.].[N_FOTO_SCAFFALE_TOT]}}"
        }
      },
      "FROM": "[Timone Categoria Sezione]",
      "WHERE": {
        "Versione": "[UFF]"
      }
    },
    "ExecuteMDX": {
      "Members": [
        "Name",
        "Level",
        "Attributes/Descrizione",
        "Attributes/MUI_')
    || TO_CLOB('DescrizioneData",
        "Attributes/MUI_TOT",
        "Attributes/MUI_RepartoDesc",
        "Attributes/CategoryManager",
        "Attributes/Name",
        "Attributes/MUI_Level",

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
      }')
    || TO_CLOB(',
      "field": "Categoria.Descrizione",
      "headerName": "Categoria",
      "width": 300,
      "pinned": "left",
      "type": ["TM1Element"]
    },
    "DynamicColumns": true,
    "DynamicColumnsSettings": {
      "headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione","Descrizione"],
      "headerdefaults": {
        "marryChildren": true
      },
      "headerCustomTypes":{
        "RIF_MKT_DT":{
          "openByDefault": true
        },
        "TGT_ACQ":{
 ')
    || TO_CLOB('         "headerClass": "headerAcq",
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
      "childrendefaults": {
        "width": 110,
        "hide": false,
        "rowGroup": false,
        "aggFunc": "sum",
        "columnGroupShow": "always",
        "editable": true,
  ')
    || TO_CLOB('      "type": ["TM1DataColumnInteger", "numericColumn"]
      },
      "childrenCustomTypes": {
        "VENDUTO_PROMO_NETTO": {
          "type": ["TM1DataColumnNumberK", "numericColumn"],
          "columnGroupShow": "always"
        },
        "MARGINE_LORDO__perc_": {
          "type": ["TM1DataColumnNumber", "numericColumn"],
          "columnGroupShow": "always"
        }
      }
    },






    "columnDefs": [
      {
        "headerName": "Totale Categoria",
      ')
    || TO_CLOB('  "field": "Categoria.MUI_TOT",
        "width": 90,
        "hide": true,
        "rowGroup": true,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Reparto",
        "field": "Categoria.MUI_RepartoDesc",
        "width": 90,
        "hide": true,
        "rowGroup": true,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Categor')
    || TO_CLOB('ia",
        "field": "Categoria.Descrizione",
        "width": 90,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },{
        "headerName": "CategoryManager",
        "field": "Compratore.CategoryManager",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "he')
    || TO_CLOB('aderName": "Compratore",
        "field": "Compratore.Descrizione",
        "width": 70,
        "hide": false,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Cod Compratore",
        "field": "Compratore.Name",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
  ')
    || TO_CLOB('    {
        "headerName": "Livello Categoria",
        "field": "Categoria.MUI_Level",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },

      {
        "headerName": "Cod Categoria",
        "field": "Categoria.Name",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "editable": false,
        "type": ["TM1Element"]
      }

    ],
')
    || TO_CLOB('
    "rowClassRules": {
      "row_style_hidden": "(data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2": "data.C')
    || TO_CLOB('ompratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
    },
    "groupRowAggNodes": {
      "nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroup')
    || TO_CLOB('False": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
    }
  }]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('49','/Timone/Foto_Speciali/Target',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_FotoSpeciali_Target",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"maxCells": 1000000 ,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Comp')
    || TO_CLOB('ratore].[MUI_Sort] , BASC )}  }",
					"Categoria": "{{[Categoria].[CAT_0000]}, {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"

				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozion')
    || TO_CLOB('e": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
					"Sezione Tematica": "{{TM1SUBSETALL( [Sezione Tematica] )}}",
					"Misura Timone.": "{[Misura Timone.].[(II) Timone Categoria Sez]}"
				}
			},
			"FROM": "[Timone Categoria Sezione]",
			"WHERE": {
				"Versione": ')
    || TO_CLOB('"[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
        		"Attributes/MUI_DescrizioneData",
        		"Attributes/MUI_Descrizione",
        		"Attributes/CategoryManager",
        		"Attributes/Name",
       			"Attributes/MUI_Level",
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
		"autoGroupColumnDef":')
    || TO_CLOB(' {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","MUI_Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
	')
    || TO_CLOB('			"TGT_ACQ":{
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
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"c')
    || TO_CLOB('hildrenCustomTypes": {
				"ASSORT_P": {
                  "hide": true ,
					"type": ["TM1DataColumnText"]
				},
				"ST_0000": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Comprator')
    || TO_CLOB('e.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,')
    || TO_CLOB('
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},

			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  ')
    || TO_CLOB('|| (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_C')
    || TO_CLOB('OMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''')
    || TO_CLOB('TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('50','/Timone/Spazi_Campagna/Target_Categoria',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_SpaziCampagna_TargetCategoria",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "maxCells": 1000000,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}  }" ,
')
    || TO_CLOB('					"Categoria": "{{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
					"Scenario": " {{[Scenario].[RIF_MK')
    || TO_CLOB('T_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Mis')
    || TO_CLOB('ura Timone.].[ASSORT_P]}  }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {

			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
        		"Attributes/MUI_DescrizioneData",
                "Attributes/MUI_Descrizione",
        		"Attributes/CategoryManager",
        		"Attributes/Name",
       			"Attributes/MUI_Level",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				')
    || TO_CLOB('"Updateable",
				"Consolidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": [
				"MUI_DescrizioneData",
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
					"headerCl')
    || TO_CLOB('ass": "headerMkt",
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
			},
			"childrenCustomTypes": {
				"N_ART_PROMO":  {
					"columnGroupShow":"always"
				},
				"TOT_F')
    || TO_CLOB('OTO":  {
					"columnGroupShow":"always"
				},
				"SPZ_CAMP":  {
					"columnGroupShow":"always"
				}
              ,
				"ASSORT_P":  {
					"hide": true
				}
			}
		},

		"autoGroupColumnDef" :  {  "cellRendererParams":{ "suppressCount": true }
		, "field": "Categoria.MUI_Descrizione"
		, "headerName": "Categoria"
		, "width":300
		, "pinned": "left"
		, "type":["TM1Element"]} ,

		"columnDefs": [
			{"headerName":"CategoryManager","field":"Compratore.CategoryManager",')
    || TO_CLOB('"width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Compratore","field":"Compratore.MUI_Descrizione","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Cod Compratore","field":"Compratore.Name","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Livello Categoria","field":"Categoria.MUI_Level"')
    || TO_CLOB(',"width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},{"headerName":"Cod Categoria","field":"Categoria.Name","width":70,"hide":true,"rowGroup": false,  "editable": false,"type":["TM1Element"]}
		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01')
    || TO_CLOB(''',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"')
    || TO_CLOB('groupRowAggNodes": {
			"nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Cate')
    || TO_CLOB('goria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [P')
    || TO_CLOB('romozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Reparto": "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].')
    || TO_CLOB('[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }"
          }
        },
     ')
    || TO_CLOB('   "FROM": "[Timone Reparto]",
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
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consol')
    || TO_CLOB('idated",
          "HasPicklist",
          "PicklistValues"
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
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione",
')
    || TO_CLOB('          "Descrizione"
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
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "header')
    || TO_CLOB('Rep",
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
        "childrenCustomTypes": {
          "N_ART_PROMO":  {
            "columnGroupShow":"always"
          ')
    || TO_CLOB('},
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
          "type": [
            "TM1Element"
          ]
        },
        {
          "hea')
    || TO_CLOB('derName": "Descrizione",
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
          "rowGroup": true,
          "editable": false,
          "type": [
        ')
    || TO_CLOB('    "TM1Element"
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
          "width": 70,
          "hide": true,
          "rowGroup": false,
         ')
    || TO_CLOB(' "editable": false,
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
        "row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_0')
    || TO_CLOB('4'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowA')
    || TO_CLOB('ggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [{
            "componentId": "tim_scr_inizializza",
            "componentLabel": "Inizializza Spazio",
            "process": "MUI_DUMMY_CUB.Timone Reparto Inizializza Spazi",
            "timeout":-1,
            "refresh": ["gc_SpaziCampagna_TargetReparto"],
            "params":[{
              "dimension": "Promozione",
              "attribute": ')
    || TO_CLOB('"Anno",
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('52','/Timone/Target_Categoria/Data',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Data",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ')
    || TO_CLOB('( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{    {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Cate')
    || TO_CLOB('goria].[CAT_0000]}}) }  }"
				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N')
    || TO_CLOB('_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        	')
    || TO_CLOB('	"Attributes/MUI_DescrizioneData",
         	   "Attributes/CategoryManager",
        		"Attributes/Name",
              "Attributes/MUI_Level",
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
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned"')
    || TO_CLOB(': "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
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
				"TGT_REP"')
    || TO_CLOB(':{
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
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO_%": {
					"t')
    || TO_CLOB('ype": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
				},
				"ASSORT_P": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Elem')
    || TO_CLOB('ent"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria')
    || TO_CLOB('",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,

				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000')
    || TO_CLOB(''') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(')
    || TO_CLOB('data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',')
    || TO_CLOB('''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('54','/Timone/Target_Categoria/Promo',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Promo",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "maxCells": 500000,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[')
    || TO_CLOB('TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{  {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Cat')
    || TO_CLOB('egoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"

				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Mis')
    || TO_CLOB('ura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
         ')
    || TO_CLOB('       "Attributes/MUI_Descrizione",
        		"Attributes/MUI_DescrizioneData",
         	    "Attributes/CategoryManager",
        		"Attributes/Name",
                "Attributes/MUI_Level",
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
			"field": "Categoria.MUI_Descrizione",
			"header')
    || TO_CLOB('Name": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
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
')
    || TO_CLOB('
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
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow":')
    || TO_CLOB(' "always"
				},
				"MARGINE_LORDO_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
				},
				"ASSORT_P": {
					"hide":true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "le')
    || TO_CLOB('ft",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]')
    || TO_CLOB('
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.N')
    || TO_CLOB('ame == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04')
    || TO_CLOB(''',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05''')
    || TO_CLOB(',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('55','/Timone/Target_Reparto/Data',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Data",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Re')
    || TO_CLOB('parto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
 ')
    || TO_CLOB('       "FROM": "[Timone Reparto]",
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
                "Attributes/Riferimento",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
      ')
    || TO_CLOB('    "Value",
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
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "An')
    || TO_CLOB('no",
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
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },')
    || TO_CLOB('
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
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": ')
    || TO_CLOB('[
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
          "rowGro')
    || TO_CLOB('up": false,
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
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "childr')
    || TO_CLOB('en": [
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
                "numericColumn"
              ]
            },
            {
          ')
    || TO_CLOB('    "headerName": "Totale",
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
              ]
            },
            {
              "headerName": "N. Foto",
      ')
    || TO_CLOB('        "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
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
              "field": "Scenario$RIF_MKT')
    || TO_CLOB('_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_do')
    || TO_CLOB('t_$N_FOTO_SPEC",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC')
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
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80')
    || TO_CLOB(',
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
          ')
    || TO_CLOB('    "hide": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
         ')
    || TO_CLOB('     "rowGroup": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "')
    || TO_CLOB('hide": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
              "width": 80,
              "hide": false,
              "rowGrou')
    || TO_CLOB('p": false,
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
          "children": [
            {
              "headerName": "N. Art",
              "field":')
    || TO_CLOB(' "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot')
    || TO_CLOB('_$TOT_FOTO",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,')
    || TO_CLOB('
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": fals')
    || TO_CLOB('e,
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
              "width": 80,
              "hide": false,
              "rowGroup":')
    || TO_CLOB(' false,
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
              "width": 80,
              "hide": false,
              "rowGroup": false,
        ')
    || TO_CLOB('      "aggFunc": "sum",
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
       ')
    || TO_CLOB('       "columnGroupShow": "open",
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
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
')
    || TO_CLOB('
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
         ')
    || TO_CLOB('     "hide": false,
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGr')
    || TO_CLOB('oup": false,
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
              "rowGroup": false,
              ')
    || TO_CLOB('"aggFunc": "sum",
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
          ')
    || TO_CLOB('    "columnGroupShow": "open",
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
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "ed')
    || TO_CLOB('itable": true,
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
              "columnGroupShow": "open",
              "editable": true,
      ')
    || TO_CLOB('        "type": [
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
              "editable": true,
              "t')
    || TO_CLOB('ype": [
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
              "width": 150,
              "hide": true,
              "rowGroup"')
    || TO_CLOB(': false,
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
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum"')
    || TO_CLOB(',
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open"')
    || TO_CLOB(',
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
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": ')
    || TO_CLOB('true,
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
              "type": [')
    || TO_CLOB('
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
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                ')
    || TO_CLOB('"TM1DataColumnInteger",
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
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
        ')
    || TO_CLOB('        "numericColumn"
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
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"')
    || TO_CLOB('
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
                "TM1DataColumnInteger",
                "numericColumn"
         ')
    || TO_CLOB('     ]
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
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Data"
          ],
    ')
    || TO_CLOB('      "params": [
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
              "hasPicklist": true
            },
            {
              "dimen')
    || TO_CLOB('sion": "Promozione",
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozio')
    || TO_CLOB('ne],BASC)}",
            "Reparto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
          }
        },
')
    || TO_CLOB('        "FROM": "[Timone Reparto]",
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
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateabl')
    || TO_CLOB('e",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
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
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "Promoz')
    || TO_CLOB('ione.Canale",
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
          "type": [
            "TM1Element"
          ]
        },
        {
          "hea')
    || TO_CLOB('derName": "Descrizione + Data",
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
            "TM')
    || TO_CLOB('1Element"
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
          "width": 70,
          "hide": true,
          "rowGroup": false,
          ')
    || TO_CLOB('"editable": false,
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "a')
    || TO_CLOB('lways",
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
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editable": t')
    || TO_CLOB('rue,
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
              "editable": true,
              "type": [
         ')
    || TO_CLOB('       "TM1DataColumnInteger",
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
                "TM1DataColumnInte')
    || TO_CLOB('ger",
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
              "type": [
                "TM1DataColumnInteger",
                "nu')
    || TO_CLOB('mericColumn"
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
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
')
    || TO_CLOB('              ]
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            ')
    || TO_CLOB('},
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
            },
         ')
    || TO_CLOB('   {
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
                "TM1DataColumnPercentage",
                "numericColumn"
     ')
    || TO_CLOB('         ]
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always",
 ')
    || TO_CLOB('             "editable": true,
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
              "columnGroupShow": "always",
              "editable": true,
       ')
    || TO_CLOB('       "type": [
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
              "editable": true,
              "type": [
                "TM1DataC')
    || TO_CLOB('olumnInteger",
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
            ')
    || TO_CLOB('    "numericColumn"
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
                "TM1DataColumnInteger",
                "numericColumn"
        ')
    || TO_CLOB('      ]
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
        ')
    || TO_CLOB('    },
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
                "numericColumn"
              ]
            }
          ]
        },
')
    || TO_CLOB('        {
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
              "columnGroupShow": "always",
              "editable": true,
              "type"')
    || TO_CLOB(': [
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
              "editable": true,
              "type": [
                "TM1DataColumnInte')
    || TO_CLOB('ger",
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
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
   ')
    || TO_CLOB('           ]
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },')
    || TO_CLOB('
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
                "numericColumn"
              ]
            },
            {
            ')
    || TO_CLOB('  "headerName": "N. Foto Spec. Banco",
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
              "headerName": "')
    || TO_CLOB('N. Foto Ultima",
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
              ')
    || TO_CLOB('"field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
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
              "headerName": "D Foto Banco/tgt Mkt",
              "field": "Scenar')
    || TO_CLOB('io$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
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
          "headerName": "Target Acq.",
          "headerClass": "headerA')
    || TO_CLOB('cq",
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
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
     ')
    || TO_CLOB('         ]
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            ')
    || TO_CLOB('{
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
              ]
            },
            {
              "headerName": "N. Foto Banc')
    || TO_CLOB('o",
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
            {
              "headerName": "N. Foto Speciali",
              "field"')
    || TO_CLOB(': "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
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
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_ACQ$$Misura')
    || TO_CLOB('Timone_dot_$N_FOTO_SCAFFALE_SPEC",
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
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT"')
    || TO_CLOB(',
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
              "width')
    || TO_CLOB('": 80,
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
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
              "width": 80,
   ')
    || TO_CLOB('           "hide": true,
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
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [
        {')
    || TO_CLOB('
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
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
    ')
    || TO_CLOB('        },
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
              "hasPicklist": true
            }
          ]
        }
      ]
    ')
    || TO_CLOB('}
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
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, [Promozione].[Data_Inizio_Ist.],DESC )}"
					}
				},
				"')
    || TO_CLOB('COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"}ElementAttributes_Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione] )}, 0)}, ASC)}"
					}
				},
				"FROM": "[}ElementAttributes_Promozione]"
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/Name",
                    "Attributes/MUI_Descrizione",
                    "Attributes/Riferimento",
                    "Attributes/TipoPromozione",
              ')
    || TO_CLOB('    	"Attributes/Canale",
                    "Attributes/Anno",
                    "Attributes/MUI_Semestre",
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

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.Name",
					"width": 70,
					"hide": false,
					"editable": false,
					"row')
    || TO_CLOB('Group": false,
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
					"headerName": "Riferimento",
					"field": "Promozione.Riferimento",
					"width": 400,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1El')
    || TO_CLOB('ement"
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
					"width": 100,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Anno",
	')
    || TO_CLOB('				"field": "Promozione.Anno",
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
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				}
			],
			"selections": [
				{
					"source": {
						"table": "gc_AssociazionePromo_Promozioni",
')
    || TO_CLOB('						"dimension": "Promozione",
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
							"dimension": "Promozione",
							"attribute": "MUI_Descrizione"
						}
					]
				},
				{
					"source": {
						"table": "gc_AssociazionePromo_Promozioni",
						"')
    || TO_CLOB('dimension": "Promozione",
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
			"logTime": true,
			"skip": true,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"suppressRowClickSelection": false,
			"MDX": {
				"ROWS": {
')
    || TO_CLOB('
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione Riferimento": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, [Promozione Riferimento].[Data_Inizio_Ist.],DESC )}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"}ElementAttributes_Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione Riferimento] )}, 0)}, ASC)}"
					}
				},
				"FROM": "[}ElementAttributes_Promozione Rifer')
    || TO_CLOB('imento]"
			},
			"ExecuteMDX": {
				"Members": [
					"Name",
					"Attributes/Name",
                    "Attributes/MUI_Descrizione",
                    "Attributes/Descrizione",
                    "Attributes/MUI_Canale",
 					"Attributes/Anno",
                    "Attributes/Gruppo",
                    "Attributes/Semestre",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"Pickli')
    || TO_CLOB('stValues"
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
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "PromozioneRiferimento.MUI_Descrizione",
					"width": 300,
					"hide": false,
					"editable": false,
					"rowGroup": false,
')
    || TO_CLOB('					"type": [
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
					]
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
')
    || TO_CLOB('					]
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
					"headerName": ')
    || TO_CLOB('"Semestre",
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
						"table": "gc_AssociazionePromo_Riferimento",
						"dimension": "PromozioneRiferimento",
						"attribute": "Descrizione"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Riferimento",
							"dimension":')
    || TO_CLOB(' "PromozioneRiferimento",
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
			"colSuppressionEnabled": false,
			"alertNoDataFound": false,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}",
						"Promo')
    || TO_CLOB('zione Riferimento": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, ASC)}"
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
	')
    || TO_CLOB('				"mdx": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
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
					"HasPicklist",
					"PicklistValues"
				]
			},
			"autoGroupColumnDef": {

			},
			"columnD')
    || TO_CLOB('efs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.MUI_Descrizione",
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
  ')
    || TO_CLOB('            {
					"componentId": "tim_ap_aggiorna",
					"componentLabel": "Aggiorna Descrizione Riferimento",
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
')
    || TO_CLOB('
							"label": "Anno",
							"hasPicklist": false,
							"disabled": false,
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
							"attribute": "MUI_Des')
    || TO_CLOB('crizione",
							"paramName": "inPromo",
							"label": "Promozione",
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
							"paramName": "inR')
    || TO_CLOB('iferimento",
							"label": "Riferimento",
							"hasPicklist": false,
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
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FI')
    || TO_CLOB('LTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
						"Promozione con Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione con Sezione Tematica] )}, 0)}, ASC)}} "
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Configurazione Riferimento": "{[Misura Configurazione Riferimento].[Selezione] , [Misura Configurazione Riferimento].[DataInizio.],[Misura Configurazione Riferimento].[DataFine.],[Misura Configurazione Rife')
    || TO_CLOB('rimento].[DataInizio],[Misura Configurazione Riferimento].[DataFine],[Misura Configurazione Riferimento].[COUNT_GIORNI],[Misura Configurazione Riferimento].[VOL],[Misura Configurazione Riferimento].[ESCLUSIONE_CLUSTER],[Misura Configurazione Riferimento].[ESCLUSIONE_COUNTING],[Misura Configurazione Riferimento].[Escludi_Venduto],[Misura Configurazione Riferimento].[DESCRIZIONE_RIF]}"
					}
				},
				"FROM": "[Configurazione Riferimento]"
			},
			"ElementSelector": {
				"promozione": {
')
    || TO_CLOB('
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
                    "Attributes/MUI_Descrizione",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPick')
    || TO_CLOB('list",
					"PicklistValues"
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
					"headerName": "Descrizione Rif.",
					"field": "MisuraConfigurazioneRiferimento$DESCRIZIONE_RIF",
					"width": 400,
					"hide": false,
					"editable"')
    || TO_CLOB(': true,
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
					"headerName": "Selezione",
					"field": "MisuraConfigurazioneRiferimento$Selezione",
					"width": 100,
					"hide": false,
					"editable": ')
    || TO_CLOB('true,
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
				},
				{
					"headerName": "DataFine Rif",
					"field": "MisuraConfigurazioneRiferimento$DataFine_dot_",
					"width": 100,
					"hide": false')
    || TO_CLOB(',
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
					]
				},
				{
					"headerName": "Data Fine",
					"field": "MisuraConfigurazioneRiferimento$DataFine",
					"width": 100,
					"hide": false')
    || TO_CLOB(',
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
					]
				},
				{
					"headerName": "Gestione Volantino",
					"field": "MisuraConfigurazioneRiferimento$VOL",
					"width": 100,
					"hide": f')
    || TO_CLOB('alse,
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
						"TM1DataColumnText"
					]
				},
				{
					"headerName": "Escludi Conteggio",
					"field": "MisuraConfigurazioneRiferimento$ESCLUSIONE_COUNTIN')
    || TO_CLOB('G",
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
					"rowGroup": false,
					"type": [
						"TM1DataColumnCheckbox"
					]
				}
			]
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('0','config_filter.json',TO_CLOB('[
  		{
          "DIM_code": "idsezioneTematica",
          "DIM_columnName": "ID Sezione Tematica",
          "DIM_description": "ID Sezione Tematica",
          "ENDPOINT_serverName": "promo",
          "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID Sezione Tematica] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
                  "ATTR_code": "Caption_Default",
                  "ATTR_columnName": "Caption_Default",
                  "ATTR_desc": "')
    || TO_CLOB('Caption_Default"
              }
          ]
      },
      {
		"DIM_code": "promozione",
		"DIM_columnName": "Promozione",
		"DIM_description": "Promozione",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATTR_columnName": "Anno",
	')
    || TO_CLOB('			"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "canale",
				"ATTR_columnName": "Canale",
				"ATTR_desc": "Canale"
			},
			{
				"ATTR_code": "tipo",
				"ATTR_columnName": "MUI_Tipo Promozione Desc",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "MUI_Descrizione",
				"ATTR_desc": "Promozione"
			},
			{
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Riferimento",
				"ATTR_desc": "Riferimento"
			},
			{
				"A')
    || TO_CLOB('TTR_code": "semestre",
				"ATTR_columnName": "MUI_Semestre",
				"ATTR_desc": "Semestre"
			},
			{
				"ATTR_code": "datainizioist",
				"ATTR_columnName": "DataInizioIst",
				"ATTR_desc": "Data Inizio Ist."
			},
			{
				"ATTR_code": "datafineist",
				"ATTR_columnName": "DataFineIst",
				"ATTR_desc": "Data Fine Ist."
			},
			{
				"ATTR_code": "tipopromozione",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Tipo Promozione"
			},
			{
				"ATTR_code": "st')
    || TO_CLOB('atodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato Desc"
			},
			{
				"ATTR_code": "canaleanno",
				"ATTR_columnName": "Canale_Anno",
				"ATTR_desc": "Canale Anno"
			}
		]
	},
	{
		"DIM_code": "promozioneriferimento",
		"DIM_columnName": "Promozione Riferimento",
		"DIM_description": "Promozione Riferimento",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)},[P')
    || TO_CLOB('romozione Riferimento].[Data_Inizio_Ist.], BDESC)}} , [Promozione Riferimento].[Canale], BASC),[Promozione Riferimento].[Gruppo],BASC)}",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATTR_columnName": "Anno",
				"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "rif_canale",
				"ATTR_columnName": "MUI_Canale",
				"ATTR_desc": "Canale"
			},
          	{
				"ATTR_code": "gruppo",
				"ATTR_columnName": "MUI_Tipo Promozione Desc",
				"ATTR_desc": "Gruppo"
			},
			{
			')
    || TO_CLOB('	"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Riferimento",
				"ATTR_desc": "Promozione"
			},
			{
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Descrizione + Data",
				"ATTR_desc": "Promozione"
			},

			{
				"ATTR_code": "semestre",
				"ATTR_columnName": "Semestre",
				"ATTR_desc": "Semestre"
			}
		]
	},
	{
		"DIM_code": "reparto",
		"DIM_columnName": "Reparto",
		"DIM_description": "Reparto",
		"ENDPOINT_serverName": "promo",
		"MDX_')
    || TO_CLOB('jsonSource": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "compratore",
				"ATTR_columnName": "Compratore",
				"ATTR_desc": "Compratore"
			},
			{
				"ATTR_code": "sigla",
				"ATTR_columnName": "Sigla",
				"ATTR_desc": "Sigla"
			},
			{
				"ATTR_code": "tipelemento",
				"A')
    || TO_CLOB('TTR_columnName": "TipoElemento",
				"ATTR_desc": "Tipo Elemento"
			},
			{
				"ATTR_code": "codpadre",
				"ATTR_columnName": "cod_padre",
				"ATTR_desc": "Cod. Padre"
			},
			{
				"ATTR_code": "reparto",
				"ATTR_columnName": "Reparto",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "categorymanager",
				"ATTR_columnName": "CategoryManager",
				"ATTR_desc": "Category Manager"
			}
		]
	},
	{
		"DIM_code": "categoria",
		"DIM_columnName": "Categoria",
		"D')
    || TO_CLOB('IM_description": "Categoria",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{Except ( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_000],[Categoria].[CAT_0000] }  ) }",
		"list_ATTR": [
			{
				"ATTR_code": "direzionedesc",
				"ATTR_columnName": "DirezioneDesc",
				"ATTR_desc": "Direzione"
			},
			{
				"ATTR_code": "direzione",
				"ATTR_columnName": "Direzione",
				"ATTR_desc": "Cod Direzione"
			},
			{
				"ATTR_code')
    || TO_CLOB('": "repartodesc",
				"ATTR_columnName": "RepartoDesc",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "reparto",
				"ATTR_columnName": "Reparto",
				"ATTR_desc": "Cod Reparto"
			},
			{
				"ATTR_code": "estensionecl",
				"ATTR_columnName": "EstensioneCL",
				"ATTR_desc": "Estensione CL"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
  	{
		"DIM_code": "raggruppamentoFoto",
		"DIM_c')
    || TO_CLOB('olumnName": "Raggruppamento Foto",
		"DIM_description": "Raggruppamento Foto",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "tot",
				"ATTR_columnName": "MUI_TOT",
				"ATTR_desc": "Totali"
			},
			{
				"ATTR_code": "tots",
				"ATTR_columnName": "MUI_TOTS",
				"ATTR_desc": "Sub Totali"
			},
            {
				"ATTR_code": "mui_descrizione",
	')
    || TO_CLOB('			"ATTR_columnName": "MUI_Descrizione",
				"ATTR_desc": "Descrizione"
			},
            {
				"ATTR_code": "mui_compratore",
				"ATTR_columnName": "MUI_Compratore",
				"ATTR_desc": "Compratore"
			},
          {
				"ATTR_code": "mui_reparto",
				"ATTR_columnName": "MUI_Reparto",
				"ATTR_desc": "Reparto"
			}
		]
	},
  {
		"DIM_code": "spazio",
		"DIM_columnName": "Spazio",
		"DIM_description": "Spazio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT(')
    || TO_CLOB(' {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "compratore",
				"ATTR_columnName": "MUI_Compratore",
				"ATTR_desc": "Compratore"
			},
            {
				"ATTR_code": "reparto",
				"ATTR_columnName": "MUI_Reparto",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "macroSpazioDescrizione",
				"ATTR_columnName": "MacroSpazioDescrizione",
				"ATTR_desc": "Macro Spazio"
			}
		]
	},
	{
		"DIM_code": "compratore",
	')
    || TO_CLOB('	"DIM_columnName": "Compratore",
		"DIM_description": "Compratore",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ EXCEPT( {TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}, { [Compratore].[NA], [Compratore].[S000] }) }",
		"list_ATTR": [
			{
				"ATTR_code": "categorymanager",
				"ATTR_columnName": "CategoryManager",
				"ATTR_desc": "Category Manager"
			},
			{
				"ATTR_code": "repartodcodesc",
				"ATTR_columnName": "MUI_Reparto",
				"ATTR_des')
    || TO_CLOB('c": "RepartoCodDesc"
			},
			{
				"ATTR_code": "repartodesc",
				"ATTR_columnName": "RepartoDesc",
				"ATTR_desc": "Reparto"
			},
			{
				"ATTR_code": "reparto",
				"ATTR_columnName": "Reparto",
				"ATTR_desc": "Cod Reparto"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "anno",
		"DIM_columnName": "Anno",
		"DIM_description": "Anno",
		"ENDPOINT_serverName": "promo",
		')
    || TO_CLOB('"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Anno] )}, 0)}, DESC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "Name",
				"ATTR_columnName": "Name",
				"ATTR_desc": "Anno"
			}
		]
	},
	{
		"DIM_code": "clients",
		"DIM_columnName": "}Clients",
		"DIM_description": "Clients",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}Clients] )}, 0)}, DESC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "TM1_DefaultDispl')
    || TO_CLOB('ayValue",
				"ATTR_columnName": "}TM1_DefaultDisplayValue",
				"ATTR_desc": "Client"
			}
		]
	},
	{
		"DIM_code": "zonaPromo",
		"DIM_columnName": "Zona Promo",
		"DIM_description": "Zona Promo",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SOCIETA_2] })}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_')
    || TO_CLOB('columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "pubblicita",
		"DIM_columnName": "Pubblicità",
		"DIM_description": "Pubblicità",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Pubblicità] )}, 0)}, ASC)}}, { [Pubblicità].[RADIO] }) }",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
')
    || TO_CLOB('		"DIM_code": "canale",
		"DIM_columnName": "Canale",
		"DIM_description": "Canale",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Canale] )}, 0)}, [Canale].[Descrizione],ASC )}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraCanale",
		"DIM_columnName": "Misura Canale",
		"DIM_description": "Misura Canale",
		"ENDP')
    || TO_CLOB('OINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Canale] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "negozio",
		"DIM_columnName": "Negozio",
		"DIM_description": "Negozio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}}",
		')
    || TO_CLOB('"list_ATTR": [
			{
				"ATTR_code": "zonaPromo",
				"ATTR_columnName": "MUI_ZonaPromo",
				"ATTR_desc": "Zona Promo"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "tipoPuntoVendita",
				"ATTR_columnName": "Tipo Punto Vendita",
				"ATTR_desc": "Tipo Punto Vendita"
			},
			{
				"ATTR_code": "repartoProfumeria",
				"ATTR_columnName": "Reparto Profumeria",
				"ATTR_desc": "Repart')
    || TO_CLOB('o Profumeria"
			}
		]
	},
	{
		"DIM_code": "tipoPromozione",
		"DIM_columnName": "Tipo Promozione",
		"DIM_description": "Tipo Promozione",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "gruppo",
				"ATTR_columnName": "GRUPPO",
				"ATTR_')
    || TO_CLOB('desc": "Gruppo"
			}
		]
	},
	{
		"DIM_code": "macrospazio",
		"DIM_columnName": "MacroSpazio",
		"DIM_description": "Macro Spazio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [MacroSpazio] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "prestazioniEC",
				"ATTR_columnName": "Prestazioni EC",
				"AT')
    || TO_CLOB('TR_desc": "Prestazioni EC"
			},
			{
				"ATTR_code": "prestazioniCNT",
				"ATTR_columnName": "Prestazioni CNT",
				"ATTR_desc": "Prestazioni CNT"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNeg",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg",
		"DIM_description": "Configurazione Macrospazio Neg",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg] )}, 0)}, ASC)}}')
    || TO_CLOB('",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNegPromo",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg_Promo",
		"DIM_description": "Configurazione Macrospazio Neg Promo",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg_Promo] )}, 0)}, ASC)}}"')
    || TO_CLOB(',
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziListino",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Listino",
		"DIM_description": "Misura Configurazione Macrospazi Listino",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Listino] )}, 0)}, ASC)}}",
')
    || TO_CLOB('
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_promozione",
		"DIM_columnName": "REP - Promozione",
		"DIM_description": "Rep Promozione",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)}, [REP - Promozione].[Data_Inizio_Ist],DESC )} ",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATT')
    || TO_CLOB('R_columnName": "Anno",
				"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "canale",
				"ATTR_columnName": "Canale",
				"ATTR_desc": "Canale"
			},
			{
				"ATTR_code": "tipo",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Riferimento",
				"ATTR_desc": "Riferimento"
			},
			{
				"ATTR_code": "semestre",
				"ATTR_columnName": "MUI_Semestre",
				"ATTR_desc": "Semestre"
			},
			{')
    || TO_CLOB('

				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Data",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "datainizioist",
				"ATTR_columnName": "DataInizioIst",
				"ATTR_desc": "Data Inizio Ist."
			},
			{
				"ATTR_code": "datafineist",
				"ATTR_columnName": "DataFineIst",
				"ATTR_desc": "Data Fine Ist."
			},
			{
				"ATTR_code": "tipopromozione",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Tipo Promozione"
			},
			{')
    || TO_CLOB('
				"ATTR_code": "statodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato Desc"
			},
			{
				"ATTR_code": "canaleanno",
				"ATTR_columnName": "Canale_Anno",
				"ATTR_desc": "Canale Anno"
			}
		]
	},
	{
		"DIM_code": "rep_compratore",
		"DIM_columnName": "REP - Compratore",
		"DIM_description": "Rep Compratore",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)}, ASC)}}",
		"lis')
    || TO_CLOB('t_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_categoria",
		"DIM_columnName": "REP - Categoria",
		"DIM_description": "Rep Categoria",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Categoria] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR')
    || TO_CLOB('_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_misuraTimone",
		"DIM_columnName": "REP - Misura Timone",
		"DIM_description": "Rep Misura Timone",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Timone] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_scenario",
		"DIM_col')
    || TO_CLOB('umnName": "REP - Scenario",
		"DIM_description": "Rep Scenario",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
            {
				"ATTR_code": "categoriadesc",
				"ATTR_columnName": "Categoria Desc",
				"ATTR_desc": "Categoria Desc"
			},
			{
				"ATTR_code": ')
    || TO_CLOB('"grmdesc",
				"ATTR_columnName": "GRM Desc",
				"ATTR_desc": "GRM Desc"
			},
			{
				"ATTR_code": "subgrmdesc",
				"ATTR_columnName": "SubGrm Desc",
				"ATTR_desc": "SubGrm Desc"
			}
		]
	},
	{
		"DIM_code": "rep_articolo",
		"DIM_columnName": "REP - Articolo",
		"DIM_description": "Rep Articolo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code')
    || TO_CLOB('": "categoriadesc",
				"ATTR_columnName": "Categoria Desc",
				"ATTR_desc": "Categoria Desc"
			},
			{
				"ATTR_code": "grmdesc",
				"ATTR_columnName": "GRM Desc",
				"ATTR_desc": "GRM Desc"
			},
			{
				"ATTR_code": "subgrmdesc",
				"ATTR_columnName": "SubGrm Desc",
				"ATTR_desc": "SubGrm Desc"
			}
		]
	},
	{
		"DIM_code": "rep_fornitore",
		"DIM_columnName": "REP - Fornitore",
		"DIM_description": "Rep Fornitore",
		"ENDPOINT_serverName": "reporting",
		"MDX_js')
    || TO_CLOB('onSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_zonaPromo",
		"DIM_columnName": "REP - Zona Promo",
		"DIM_description": "Rep Zona Promo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)}, ASC)}}",
		')
    || TO_CLOB('"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_sezioneTematica",
		"DIM_columnName": "REP - Sezione Tematica",
		"DIM_description": "Rep Sezione Tematica",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnN')
    || TO_CLOB('ame": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_meccanicaSemplice",
		"DIM_columnName": "REP - Meccanica Semplice",
		"DIM_description": "Rep Meccanica Semplice",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
')
    || TO_CLOB('
	},
	{
		"DIM_code": "rep_avolantino",
		"DIM_columnName": "REP - AVolantino",
		"DIM_description": "Rep AVolantino",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_MisuraReportingArticoloZona",
		"DIM_columnName": "REP - Misura R')
    || TO_CLOB('eporting Articolo Zona",
		"DIM_description": "Rep Articolo Zona",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo Zona] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_MisuraReportingArticolo",
		"DIM_columnName": "REP - Misura Reporting Articolo",
		"DIM_description')
    || TO_CLOB('": "Rep Articolo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Misura Reporting Articolo] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_reparto",
		"DIM_columnName": "REP - Reparto",
		"DIM_description": "Rep Reparto",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT')
    || TO_CLOB('( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "fornitore",
		"DIM_columnName": "Fornitore",
		"DIM_description": "Fornitore",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizion')
    || TO_CLOB('e",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "articoloFittizio",
		"DIM_columnName": "Articolo Fittizio",
		"DIM_description": "Articolo Fittizio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
			')
    || TO_CLOB('	"ATTR_code": "compratore",
				"ATTR_columnName": "Compratore",
				"ATTR_desc": "Compratore"
			}
		]
	},
	{
		"DIM_code": "sezioneTematica",
		"DIM_columnName": "Sezione Tematica",
		"DIM_description": "Sezione Tematica",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"')
    || TO_CLOB('
			}
		]
	},
	{
		"DIM_code": "scenario",
		"DIM_columnName": "Scenario",
		"DIM_description": "Scenario",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraTimone",
		"DIM_columnName": "Misura Timone.",
		"DIM_description": "Misura",
		"')
    || TO_CLOB('ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
     {
          "DIM_code": "contratto",
          "DIM_columnName": "Contratto",
          "DIM_description": "Contratto",
          "ENDPOINT_serverName": "promo",
          "MDX_jsonSource": "{{TM1SORT( {TM1FILTER')
    || TO_CLOB('BYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
                  "ATTR_code": "descrizione",
                  "ATTR_columnName": "Descrizione",
                  "ATTR_desc": "Descrizione"
              }
          ]
      },
      {
              "DIM_code": "prestazione",
              "DIM_columnName": "Prestazione",
              "DIM_description": "Prestazione",
              "ENDPOINT_serverName": "promo",
              "MDX_js')
    || TO_CLOB('onSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
              "list_ATTR": [
                  {
                      "ATTR_code": "descrizione",
                      "ATTR_columnName": "Descrizione",
                      "ATTR_desc": "Descrizione"
                  }
              ]
      },
      {
              "DIM_code": "iniziativa",
              "DIM_columnName": "Iniziativa",
              "DIM_description": "Iniziativa",
        ')
    || TO_CLOB('      "ENDPOINT_serverName": "promo",
              "MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Iniziativa] )}, 0)}, ASC)}}",
              "list_ATTR": [
                  {
                      "ATTR_code": "Descrizione",
                      "ATTR_columnName": "Descrizione",
                      "ATTR_desc": "Descrizione"
                  }
              ]
      },
	{
		"DIM_code": "articolo",
		"DIM_columnName": "Articolo",
		"DIM_description": "Articolo')
    || TO_CLOB('",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "categoriadesc",
				"ATTR_columnName": "Categoria Desc",
				"ATTR_desc": "Categoria Desc"
			},
			{
				"ATTR_code": "grmdesc",
				"ATTR_columnName": "GRM Desc",
				"ATTR_desc": "GRM Desc"
			},
			{
				"ATTR_code": "subgrmdesc",
				"ATTR_columnName": "SubGrm Desc",
				"ATTR_desc": "SubGrm Desc"
			},
')
    || TO_CLOB('
			{
				"ATTR_code": "attivo",
				"ATTR_columnName": "Attivo",
				"ATTR_desc": "Attivo"
			}
		]
	},
	{
		"DIM_code": "misuraProgrammazioneFornitore",
		"DIM_columnName": "Misura Programmazione Fornitore",
		"DIM_description": "Misura Programmazione Fornitore",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Programmazione Fornitore] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "visualizzazione",
				"ATT')
    || TO_CLOB('R_columnName": "Visualizzazione",
				"ATTR_desc": "Visualizzazione"
			}
		]
	},
  {
		"DIM_code": "spazioProgressivo",
		"DIM_columnName": "SpazioProgressivo",
		"DIM_description": "Spazio Progressivo",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [SpazioProgressivo] )}, 0)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "MUI_Descrizione",
				"ATTR_desc": "Spazio"
			}
		]
	}
]
'),'GLOBAL');
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
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"fornitore":["descrizione"],
	"compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "spazioProgressivo":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('76','/Pianificazione/Selezione_Promo',' {
	"promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
	"compratore": ["categorymanager","repartodcodesc","repartodesc","reparto","descrizione"],
	"tipoPromozione":["descrizione"],
	"prestazione":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('77','/Piano_Annuale/Controllo_Negozi',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('82','/Piano_Annuale/Gestione_Iniziative',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
     "iniziativa":["Descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('92','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
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
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('90','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',' {
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('118','/Timone/Foto_Speciali/Target_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_FotoSpeciali_Target",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"maxCells": 1000000 ,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{[Compratore].[MUI_Timone_FotoSpec_Target_CAT]}",
					"Categoria": "{[Categoria].[MUI_Timone_FotoSpec_Target_CAT]}"

				}
			},
			"COLS": {
				"NON_EMPTY": ')
    || TO_CLOB('true,
				"DIMENSIONS": {
					"Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
					"Sezione Tematica": "{{TM1SUBSETALL( [Sezione Tematica] )}}",
					"Misura Timone.": "{EXCEPT( {[Misura Timone.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]} ) }"
')
    || TO_CLOB('
				}
			},
			"FROM": "[Timone Categoria Sezione]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
        		"Attributes/MUI_DescrizioneData",
        		"Attributes/MUI_Descrizione",
        		"Attributes/CategoryManager",
        		"Attributes/Name",
       			"Attributes/MUI_Level",
				"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Conso')
    || TO_CLOB('lidated",
				"HasPicklist",
				"PicklistValues"
			]
		},
		"autoGroupColumnDef": {
			"cellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","MUI_Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
		')
    || TO_CLOB('	},
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
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
		')
    || TO_CLOB('		"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"ASSORT_P": {
					"type": ["TM1DataColumnText"]
				},
				"ST_0000": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
		')
    || TO_CLOB('		"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,

				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Ca')
    || TO_CLOB('tegoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},

			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Comprato')
    || TO_CLOB('re.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.')
    || TO_CLOB('Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].incl')
    || TO_CLOB('udes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('119','/Timone/Foto_Speciali/Target_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "sezioneTematica":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('120','/Timone/Foto_Speciali/Target_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_FotoSpeciali_Target",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"maxCells": 1000000 ,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{ [Compratore].[MUI_Timone_FotoSpec_Target_ACQ] }",
					"Categoria": "{ [Categoria].[MUI_Timone_FotoSpec_Target_ACQ]  }"

				}
			},
			"COLS')
    || TO_CLOB('": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]}}",
					"Sezione Tematica": "{ EXCEPT({{TM1SUBSETALL( [Sezione Tematica] )}}  ,{[Sezione Tematica].[ST_0000]}  )} ",
					"Misura Timone.": "{ EXCEPT({[Misura Timon')
    || TO_CLOB('e.].[(II) Timone Categoria Sez]} , {[Misura Timone.].[ASSORT_P]}  ) }"
				}
			},
			"FROM": "[Timone Categoria Sezione]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
        		"Attributes/MUI_DescrizioneData",
        		"Attributes/MUI_Descrizione",
        		"Attributes/CategoryManager",
        		"Attributes/Name",
       			"Attributes/MUI_Level",
				"UniqueName"
			],
			')
    || TO_CLOB('"Cells": [
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
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","MUI_Descrizione"')
    || TO_CLOB(',"Descrizione"],
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
					"headerClass": "headerRep",
					"openByDefault": true
				}
			},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGr')
    || TO_CLOB('oup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"ASSORT_P": {
					"type": ["TM1DataColumnText"]
				},
				"ST_000": {
					"hide": false
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left')
    || TO_CLOB('",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
')
    || TO_CLOB('			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},

			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
          "row_style_hidden": "(data.Compr')
    || TO_CLOB('atore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  || (data.Compratore.Name === ''S000'' && data.Categoria.Name === ''CAT_0000'')  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''')
    || TO_CLOB('_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04''')
    || TO_CLOB(',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('121','/Timone/Foto_Speciali/Target_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre","descrizione","datainizioist"],
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Pr')
    || TO_CLOB('omozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Reparto": "{{ORDER( {[Reparto].[REP_01] , [Reparto].[REP_01_01], [Reparto].[REP_01_02], [Reparto].[REP_01_03], [Reparto].[REP_01_04], [Reparto].[REP_09], [Reparto].[REP_12], [Reparto].[REP_12_01], [Reparto].[REP_12_02], [Reparto].[REP_12_04]  },[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].[')
    || TO_CLOB('RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spaz')
    || TO_CLOB('i'')}  }"
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
        ],
        "Cells": [
          "Ordinal",
          "Value",
    ')
    || TO_CLOB('      "Updateable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
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
      "DynamicColumnsSettings": {
        "head')
    || TO_CLOB('erconf": [
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
            "openByDefault": true
          },
          "TGT_REP"')
    || TO_CLOB(':{
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
        "childrenCustomTypes": {
          "N_ART_PROMO":  {
            ')
    || TO_CLOB('"columnGroupShow":"always"
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
          "type": [
            "TM1Element"
          ]
')
    || TO_CLOB('
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
          "rowGroup": true,
          "editable":')
    || TO_CLOB(' false,
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
          "field": "Reparto.TipoElemento",
          "width": 70,
          "hide": true,
 ')
    || TO_CLOB('         "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": {
        "row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02')
    || TO_CLOB(''',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [{
            "componentId": "tim_scr_inizializza",
            "componentLabel": "Inizializza Spazi')
    || TO_CLOB('o",
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
              "dimension": "Promozione",
              "attribute": "MUI_Descrizione",
      ')
    || TO_CLOB('        "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            }]
          }]
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('123','/Timone/Spazi_Campagna/Target_Reparto_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
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
      "rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
       "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.')
    || TO_CLOB('].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}  }",
       "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false   ,
          "DIMENSIONS": {
            "P')
    || TO_CLOB('romozione": "{[Promozione].[MUI_Promozione]}",
            "Reparto": "{[Reparto].[MUI_Timone_Spazi_Rep_ACQ]}"
          }
        },
        "COLS": {
          "NON_EMPTY": false   ,
          "DIMENSIONS": {
            "Scenario": " {[Scenario].[RIF_MKT_DT],[Scenario].[TGT_REP]}",
             "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''TOT_FOTO'')}, ALL, RECURSIVE )},{[Misura Timone.].[VENDUTO_PRO')
    || TO_CLOB('MO_NETTO]},{TM1DRILLDOWNMEMBER( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Misura Timone.] )}, ''SPZ_CAMP'')}, ALL, RECURSIVE )}}"
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
				"Attributes/Name",
         	   "Attributes/Riferim')
    || TO_CLOB('ento",
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
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
     ')
    || TO_CLOB('   "type": [
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
          "marryChildren": true
        },
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openByDefault": true
        ')
    || TO_CLOB('  },
          "TGT_MKT":{
            "headerClass": "headerMkt",
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
            "TM1DataColumnIn')
    || TO_CLOB('teger",
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
     ')
    || TO_CLOB('     "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
         {
          "headerName": "Descrizione",
          "field": "Promozione.Riferimento",
          "width": 400,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Descrizione",
          "field":')
    || TO_CLOB(' "Promozione.Descrizione",
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
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
 ')
    || TO_CLOB('       {
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
          "headerName": "Cod Reparto",
          "field": "Reparto.Name",
          "width": 120,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
           ')
    || TO_CLOB(' "TM1Element"
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
        "row_style_1": "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(da')
    || TO_CLOB('ta.Reparto.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto')
    || TO_CLOB('.TipoElemento == ''R''"
      },
      "actions": []
    }
  ]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('125','/Timone/Spazi_Campagna/Target_Reparto_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('126','/Timone/Spazi_Campagna/Target_Categoria_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_SpaziCampagna_TargetCategoria",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{[Compratore].[MUI_Timone_Spazi_Cat_CAT]}" ,
					"Categoria": "{[Categoria].[MUI_Timone_Spazi_Cat_CAT]}"
				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
	')
    || TO_CLOB('				"Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SC')
    || TO_CLOB('AFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')} , { [Misura Timone.].[ASSORT_P]}  }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
        		"Attributes/MUI_DescrizioneData",
                "At')
    || TO_CLOB('tributes/MUI_Descrizione",
        		"Attributes/CategoryManager",
        		"Attributes/Name",
       			"Attributes/MUI_Level",
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
				"MUI_DescrizioneData",
				"Descrizione",
				"Descrizione"
			],
			"headerdefaults": {
				"marryChildren": true
			')
    || TO_CLOB('},
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
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
			')
    || TO_CLOB('	"editable": true,
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
				"SPZ_CAMP":  {
					"columnGroupShow":"always"
				}
              ,
				"ASSORT_P":  {
					"hide": true
				}
			}
		},

		"autoGroupColumnDef" :  {  "cellRendererParams":{ "suppressCount": true }
		, "field": "Categoria.MUI_Des')
    || TO_CLOB('crizione"
		, "headerName": "Categoria"
		, "width":300
		, "pinned": "left"
		, "type":["TM1Element"]} ,

		"columnDefs": [
			{"headerName":"CategoryManager","field":"Compratore.CategoryManager","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Compratore","field":"Compratore.MUI_Descrizione","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Cod Com')
    || TO_CLOB('pratore","field":"Compratore.Name","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Livello Categoria","field":"Categoria.MUI_Level","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},{"headerName":"Cod Categoria","field":"Categoria.Name","width":70,"hide":true,"rowGroup": false,  "editable": false,"type":["TM1Element"]}
		],
		"rowClassRules": {
			"row_style_hidden": "(d')
    || TO_CLOB('ata.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.C')
    || TO_CLOB('ategoria.Name)",
			"row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_R')
    || TO_CLOB('EP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('127','/Timone/Spazi_Campagna/Target_Categoria_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('128','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_SpaziCampagna_TargetCategoria",
		"logMemory": true,
		"logTime": true,
		"skip": true,
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": false,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{[Compratore].[MUI_Timone_Spazi_Cat_ACQ]}" ,
					"Categoria": "{[Categoria].[MUI_Timone_Spazi_Cat_ACQ]}"
				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
')
    || TO_CLOB('					"Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Mi')
    || TO_CLOB('sura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[SPZ_CAMP]},{FILTER( {TM1SUBSETALL( [Misura Timone.] )}, [Misura Timone.].[Tipo] = ''Spazi'')}   }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
        		"Attributes/MUI_DescrizioneData",
                "Attributes/MUI_Descrizione",
        		"Attributes')
    || TO_CLOB('/CategoryManager",
        		"Attributes/Name",
       			"Attributes/MUI_Level",
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
				"MUI_DescrizioneData",
				"Descrizione",
				"Descrizione"
			],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
')
    || TO_CLOB('
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
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": [
					"TM1DataC')
    || TO_CLOB('olumnInteger",
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
				"SPZ_CAMP":  {
					"columnGroupShow":"always"
				}
              ,
				"ASSORT_P":  {
					"hide": true
				}
			}
		},

		"autoGroupColumnDef" :  {  "cellRendererParams":{ "suppressCount": true }
		, "field": "Categoria.MUI_Descrizione"
		, "headerName": "Categoria"
		, "wi')
    || TO_CLOB('dth":300
		, "pinned": "left"
		, "type":["TM1Element"]} ,

		"columnDefs": [
			{"headerName":"CategoryManager","field":"Compratore.CategoryManager","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Compratore","field":"Compratore.MUI_Descrizione","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Cod Compratore","field":"Compratore.Name","width":70,"hi')
    || TO_CLOB('de":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Livello Categoria","field":"Categoria.MUI_Level","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},{"headerName":"Cod Categoria","field":"Categoria.Name","width":70,"hide":true,"rowGroup": false,  "editable": false,"type":["TM1Element"]}
		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categor')
    || TO_CLOB('ia.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1" : "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3" : "data.Compra')
    || TO_CLOB('tore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || da')
    || TO_CLOB('ta.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Categoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('129','/Timone/Spazi_Campagna/Target_Categoria_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
     "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('130','/Timone/Target_Categoria/Promo_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Promo",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "Ã¿ stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM')
    || TO_CLOB('1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"

				}
			},
			"COLS": {')
    || TO_CLOB('
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VEND')
    || TO_CLOB('UTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        		"Attributes/MUI_DescrizioneData",
         	    "Attributes/CategoryManager"')
    || TO_CLOB(',
        		"Attributes/Name",
                "Attributes/MUI_Level",
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
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"D')
    || TO_CLOB('ynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
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
					"headerClass": "headerRep",
					"openByDefault": true
				}
		')
    || TO_CLOB('	},
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",

				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupSho')
    || TO_CLOB('w": "always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
				},
				"ASSORT_P": {
					"hide":true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Comprat')
    || TO_CLOB('ore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": tru')
    || TO_CLOB('e,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",')
    || TO_CLOB('
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Na')
    || TO_CLOB('me==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Na')
    || TO_CLOB('me != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('131','/Timone/Target_Categoria/Promo_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('132','/Timone/Target_Categoria/Promo_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Promo",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
        "Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {')
    || TO_CLOB('[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
        "Categoria": "{  {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }",
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
		')
    || TO_CLOB('		"DIMENSIONS": {
					"Compratore": "[Compratore].[MUI_TargetCategoriaPromo_ACQ]",
                    "Categoria": "[Categoria].[MUI_TargetCategoriaPromo_ACQ]"

				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART')
    || TO_CLOB('_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]}}"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
')
    || TO_CLOB('		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        		"Attributes/MUI_DescrizioneData",
         	    "Attributes/CategoryManager",
        		"Attributes/Name",
                "Attributes/MUI_Level",
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
			"c')
    || TO_CLOB('ellRendererParams": {
				"suppressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"heade')
    || TO_CLOB('rClass": "headerAcq",
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
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",

				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
	')
    || TO_CLOB('			"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
				},
				"ASSORT_P": {
					"hide":true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field":')
    || TO_CLOB(' "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hi')
    || TO_CLOB('de": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false')
    || TO_CLOB(',
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''S000'' && data.Categoria.Name == ''CAT_0000'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)  ",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Na')
    || TO_CLOB('me)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''"')
    || TO_CLOB(',
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('133','/Timone/Target_Categoria/Promo_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('134','/Timone/Target_Categoria/Data_(CAT)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Data",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "Ã¿ stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1')
    || TO_CLOB('SUBSETALL( [Compratore] ) }   , 0 ) } , {[Compratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{{[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }"
				}
			},
			"COLS": {
	')
    || TO_CLOB('			"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO')
    || TO_CLOB('_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members": [
				"Name",
				"Level",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        		"Attributes/MUI_DescrizioneData",
         	    "Attributes/CategoryManager",
')
    || TO_CLOB('
        		"Attributes/Name",
                "Attributes/MUI_Level",
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
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"Dyn')
    || TO_CLOB('amicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
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
					"headerClass": "headerRep",
					"openByDefault": true
				}
			}')
    || TO_CLOB(',
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
					"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": ')
    || TO_CLOB('"always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
				},
				"ASSORT_P": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore')
    || TO_CLOB('.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
')
    || TO_CLOB('
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,

				"type": ["TM1Element"]
			}

		],

		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)"')
    || TO_CLOB(',
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.N')
    || TO_CLOB('ame==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.N')
    || TO_CLOB('ame != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('143','/Timone/Target_Reparto/Promo_(CAT)','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('144','/Timone/Target_Reparto/Promo_(ACQ)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Promo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Re')
    || TO_CLOB('parto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
')
    || TO_CLOB('        "FROM": "[Timone Reparto]",
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
          	    "Attributes/Riferimento",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",')
    || TO_CLOB('
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
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerNa')
    || TO_CLOB('me": "Anno",
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
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
 ')
    || TO_CLOB('       },
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
          "hide": true,
          "rowGroup": false,
          "editable": false,
         ')
    || TO_CLOB(' "type": [
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
        ')
    || TO_CLOB('  "rowGroup": false,
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
          "headerName": "Riferimento Data",
          "openByDefault": true,
        ')
    || TO_CLOB('  "children": [
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
                "numericColumn"
              ]
            },
            {
 ')
    || TO_CLOB('             "headerName": "Totale",
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
              ]
            },
            {
              "headerName": "N. Foto"')
    || TO_CLOB(',
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
              "headerName": "N. Foto Banco",
              "field": "Scenari')
    || TO_CLOB('o$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTi')
    || TO_CLOB('mone_dot_$N_FOTO_SPEC",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFA')
    || TO_CLOB('LE_SPEC",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
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
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
   ')
    || TO_CLOB('           "hide": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
  ')
    || TO_CLOB('            "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "$VENDUTO_PROMO_NETTO",
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
          "openByDefault": true,
          "ch')
    || TO_CLOB('ildren": [
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
         ')
    || TO_CLOB('     "headerName": "Totale",
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
    || TO_CLOB('      "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
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
              "field": "Scenario$TGT_MKT$$Mis')
    || TO_CLOB('uraTimone_dot_$N_FOTO_SCAFFALE",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_S')
    || TO_CLOB('PEC",
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
          ')
    || TO_CLOB('    "width": 80,
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
              ')
    || TO_CLOB('"hide": false,
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
              "headerNa')
    || TO_CLOB('me": "N. Art",
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
              "fie')
    || TO_CLOB('ld": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
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
              "field": "Scenario$TGT_REP$$MisuraTimone_d')
    || TO_CLOB('ot_$N_FOTO",
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
              ')
    || TO_CLOB('"width": 80,
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
              "')
    || TO_CLOB('hide": false,
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
    || TO_CLOB('            "rowGroup": false,
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
    || TO_CLOB('              "aggFunc": "sum",
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
              "aggFunc')
    || TO_CLOB('": "sum",
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
    || TO_CLOB('
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
          "headerName": "Target Acq.",
          "headerClass": "headerAcq",
          "openByDefault": true,
          "children": [
            {
              "headerName": "N. Art",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART')
    || TO_CLOB('_PROMO",
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
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
')
    || TO_CLOB('              "hide": false,
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
              "hide": false,
           ')
    || TO_CLOB('   "rowGroup": false,
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
              "hide": false,
              "rowGroup": false,
     ')
    || TO_CLOB('         "aggFunc": "sum",
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
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
 ')
    || TO_CLOB('             "columnGroupShow": "open",
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
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "col')
    || TO_CLOB('umnGroupShow": "open",
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
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
        ')
    || TO_CLOB('      "editable": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true')
    || TO_CLOB(',
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
       ')
    || TO_CLOB('       "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            }
          ]
        }
      ],
	  "rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Repar')
    || TO_CLOB('to.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
    ')
    || TO_CLOB('  },
      "actions": [
        {
          "componentId": "tim_trd_inizializza",
          "componentLabel": "Inizializza Target",
          "process": "MUI_DUMMY_CUB.TimoneRIF.TGT4",
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Promo"
          ],
          "params": [
            {
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPic')
    || TO_CLOB('klist": true
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
              "hasPicklist": true
            },
            {
   ')
    || TO_CLOB('           "dimension": "Reparto",
              "attribute": "Descrizione",
              "paramName": "inReparto",
              "label": "Reparto",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('145','/Timone/Target_Reparto/Promo_(ACQ)','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
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
      "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
      "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
      "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
      "REP - Sezione Tematica": "{[REP - Sezion')
    || TO_CLOB('e Tematica].[Zona Promo -BDGVend]}",
      "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
      "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
      "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Pro')
    || TO_CLOB('mozione] )}, 0)} , ASC)}",
            "REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
            "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] ')
    || TO_CLOB(')}, 0)} , ASC)}",
            "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "REP - Scenario": "{[R')
    || TO_CLOB('EP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[RIF_MKT_DT]}",
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
		  "Attributes/MUI_Descrizione",
          "Attributes/Descrizi')
    || TO_CLOB('one",
          "Attributes/DescrizioneArticolo",

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
        "headerconf" : ["Descrizione" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomType')
    || TO_CLOB('s": {
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
            "headerClass": "headerBdg",
            "o')
    || TO_CLOB('penByDefault": true
          }
        },
        "childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {

          "MARGINE_LORDO_%_P": {
            "type": ["TM1DataColumnPercentage", "numericColumn"],
            "columnGroupShow": "always",
            "aggFunc": "weightedAvg",
            "aggFuncParam": "$V')
    || TO_CLOB('ENDUTO_PROMO_NETTO_P"
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
          "field": "REP_minus_Promozione.MUI')
    || TO_CLOB('_Descrizione",
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
          ]
        },
        {
     ')
    || TO_CLOB('     "headerName": "Articolo",
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
          "editable": false,
  ')
    || TO_CLOB('        "type": [
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
')
    || TO_CLOB('          "hide": false,
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
      "rowSuppressionEnabled": false,
      ')
    || TO_CLOB('"colSuppressionEnabled": false,
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
          "dimension": "REP - Scenario",
   ')
    || TO_CLOB('       "dimCode": "rep_scenario",
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('148','/Pianificazione/Aggiungi_Articoli','{}','GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('149','/Pianificazione/Aggiungi_Articoli','{}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('135','/Timone/Target_Categoria/Data_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('136','/Timone/Target_Categoria/Data_(ACQ)',TO_CLOB(' {
	"connection": "promo",
	"configurations": [{
		"name": "gc_TargetCategoria_Data",
		"logMemory": true,
		"logTime": true,
		"skip": true,
        "MDX_Comment" : "è stata utilizzata la funzione TM1FILTERBYPATTERN per recuperare i Reparti inseriti come elementi tecnici nella dimensione categoria ( i reparti iniziano per _) " ,
      "Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},  {ORDER ( {EXCEPT ( { TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] ) }   , 0 ) } , {[Co')
    || TO_CLOB('mpratore].[S000] })} , [Compratore].[MUI_Sort] , BASC )}  }",
                    "Categoria": "{ {[Categoria].[CAT_0000]} , {[Categoria].[MUI_TargetCategoria]} ,  {ORDER ( {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  [Categoria].[MUI_Sort] , BASC )} ,  {EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {  {TM1FILTERBYPATTERN( {TM1SUBSETALL( [Categoria] )}, ''_*'')} ,  {[Categoria].[CAT_0000]}}) }  }",
		"MDX": {
			"ROWS": {
				"NON_EMPTY": t')
    || TO_CLOB('rue,
				"DIMENSIONS": {
					"Compratore": "{[Compratore].[MUI_TargetCategoriaData_ACQ]}",
                    "Categoria": "{ [Categoria].[MUI_TargetCategoriaData_ACQ]}"
				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_')
    || TO_CLOB('FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO_%]},{[Misura Timone.].[D_FOTO/TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF/TGT_REP]},{[Misura Timone.].[ASSORT_P]} }"
				}
			},
			"FROM": "[Timone Categoria]",
			"WHERE": {
				"Versione": "[UFF]"
			}
		},
		"ExecuteMDX": {
			"Members"')
    || TO_CLOB(': [
				"Name",
				"Level",
        		"Attributes/Descrizione",
                "Attributes/MUI_Descrizione",
        		"Attributes/MUI_DescrizioneData",
         	    "Attributes/CategoryManager",
        		"Attributes/Name",
                "Attributes/MUI_Level",
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
				"sup')
    || TO_CLOB('pressCount": true
			},
			"field": "Categoria.MUI_Descrizione",
			"headerName": "Categoria",
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"op')
    || TO_CLOB('enByDefault": true
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
			"childrendefaults": {
				"width": 110,
				"hide": false,
				"rowGroup": false,
				"aggFunc": "sum",
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
			},
			"childrenCustomTypes": {
				"VENDUTO_PROMO_NETTO": {
				')
    || TO_CLOB('	"type": ["TM1DataColumnInteger", "numericColumn"],
					"columnGroupShow": "always"
				},
				"MARGINE_LORDO_%": {
					"type": ["TM1DataColumnPercentage", "numericColumn"],
					"columnGroupShow": "always",
                 	"aggFunc": "weightedAvg",
             		"aggFuncParam": "$VENDUTO_PROMO_NETTO"
				},
				"ASSORT_P": {
					"hide": true
				}
			}
		},






		"columnDefs": [
			{
				"headerName": "CategoryManager",
				"field": "Compratore.CategoryManager",
')
    || TO_CLOB('				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.MUI_Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Compratore",
				"field": "Compratore.Name",
				"width": 70,
				"hide": true,
				"rowGroup": fals')
    || TO_CLOB('e,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Livello Categoria",
				"field": "Categoria.MUI_Level",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Cod Categoria",
				"field": "Categoria.Name",
				"width": 70,
				"hide": true,
				"rowGroup": false,
				"editable": false,

				"type": ["TM1Element"]
')
    || TO_CLOB('
			}

		],
		"rowClassRules": {
			"row_style_hidden": " (data.Categoria.Name == ''CAT_0000'' )  || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_RE')
    || TO_CLOB('P_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_R')
    || TO_CLOB('EP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
		}
	}]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('137','/Timone/Target_Categoria/Data_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "compratore":["categorymanager","repartodesc","reparto","descrizione"],
    "categoria":["direzionedesc","repartodesc","descrizione"],
    "scenario":["descrizione"],
    "misuraTimone":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('138','/Timone/Target_Reparto/Data_(CAT)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Data",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Rep')
    || TO_CLOB('arto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
  ')
    || TO_CLOB('      "FROM": "[Timone Reparto]",
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
                "Attributes/Riferimento",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
       ')
    || TO_CLOB('   "Value",
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
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Ann')
    || TO_CLOB('o",
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
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
')
    || TO_CLOB('
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
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [')
    || TO_CLOB('
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
          "rowGrou')
    || TO_CLOB('p": false,
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
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "childre')
    || TO_CLOB('n": [
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
                "numericColumn"
              ]
            },
            {
           ')
    || TO_CLOB('   "headerName": "Totale",
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
              ]
            },
            {
              "headerName": "N. Foto",
       ')
    || TO_CLOB('       "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
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
              "field": "Scenario$RIF_MKT_')
    || TO_CLOB('DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot')
    || TO_CLOB('_$N_FOTO_SPEC",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC"')
    || TO_CLOB(',
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,')
    || TO_CLOB('
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
           ')
    || TO_CLOB('   "hide": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
          ')
    || TO_CLOB('    "rowGroup": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "h')
    || TO_CLOB('ide": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
              "width": 80,
              "hide": false,
              "rowGroup')
    || TO_CLOB('": false,
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
          "children": [
            {
              "headerName": "N. Art",
              "field": ')
    || TO_CLOB('"Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_')
    || TO_CLOB('$TOT_FOTO",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
')
    || TO_CLOB('
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
              "width": 80,
              "hide": false')
    || TO_CLOB(',
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
              "width": 80,
              "hide": false,
              "rowGroup": ')
    || TO_CLOB('false,
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
              "width": 80,
              "hide": false,
              "rowGroup": false,
         ')
    || TO_CLOB('     "aggFunc": "sum",
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
        ')
    || TO_CLOB('      "columnGroupShow": "open",
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
              "headerName": "N. Art",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
')
    || TO_CLOB('              "width": 150,
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
          ')
    || TO_CLOB('    "hide": false,
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              "hide": false,
              "rowGro')
    || TO_CLOB('up": false,
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
              "rowGroup": false,
              "')
    || TO_CLOB('aggFunc": "sum",
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
           ')
    || TO_CLOB('   "columnGroupShow": "open",
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupSh')
    || TO_CLOB('ow": "open",
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
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "edi')
    || TO_CLOB('table": true,
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
              "columnGroupShow": "open",
              "editable": true,
       ')
    || TO_CLOB('       "type": [
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
              "editable": true,
              "ty')
    || TO_CLOB('pe": [
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
              "width": 150,
              "hide": true,
              "rowGroup":')
    || TO_CLOB(' false,
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
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",')
    || TO_CLOB('
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",')
    || TO_CLOB('
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
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": t')
    || TO_CLOB('rue,
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
    || TO_CLOB('
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
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInteger",
         ')
    || TO_CLOB('       "numericColumn"
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
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
')
    || TO_CLOB('
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
                "TM1DataColumnInteger",
                "numericColumn"
          ')
    || TO_CLOB('    ]
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
          "timeout": -1,
          "refresh": [
            "gc_TargetReparto_Data"
          ],
     ')
    || TO_CLOB('     "params": [
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
              "hasPicklist": true
            },
            {
              "dimens')
    || TO_CLOB('ion": "Promozione",
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('139','/Timone/Target_Reparto/Data_(CAT)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('140','/Timone/Target_Reparto/Data_(ACQ)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Data",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Rep')
    || TO_CLOB('arto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[F_FATT]}}"
          }
        },
  ')
    || TO_CLOB('      "FROM": "[Timone Reparto]",
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
                "Attributes/Riferimento",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
       ')
    || TO_CLOB('   "Value",
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
        "field": "Reparto.Descrizione",
        "headerName": "Reparto",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Ann')
    || TO_CLOB('o",
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
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
')
    || TO_CLOB('
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
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [')
    || TO_CLOB('
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
          "rowGrou')
    || TO_CLOB('p": false,
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
          "headerName": "Riferimento Data",
          "openByDefault": true,
          "childre')
    || TO_CLOB('n": [
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
                "numericColumn"
              ]
            },
            {
           ')
    || TO_CLOB('   "headerName": "Totale",
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
              ]
            },
            {
              "headerName": "N. Foto",
       ')
    || TO_CLOB('       "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
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
              "field": "Scenario$RIF_MKT_')
    || TO_CLOB('DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$')
    || TO_CLOB('N_FOTO_SPEC",
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
')
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
            },
            {
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
')
    || TO_CLOB('              "hide": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
             ')
    || TO_CLOB(' "hide": false,
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
            ')
    || TO_CLOB('  "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "$VENDUTO_PROMO_NETTO",
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
          "openByDefault": true,
          "children": [')
    || TO_CLOB('
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
              "head')
    || TO_CLOB('erName": "Totale",
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
              "fie')
    || TO_CLOB('ld": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_')
    || TO_CLOB('dot_$N_FOTO_SCAFFALE",
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
    || TO_CLOB('         "width": 80,
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
              "width":')
    || TO_CLOB(' 80,
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
              "hide": fals')
    || TO_CLOB('e,
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
              "headerName": "N. Art')
    || TO_CLOB('",
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
              "field": "Scenario')
    || TO_CLOB('$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
')
    || TO_CLOB('              "width": 80,
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
')
    || TO_CLOB('              "hide": false,
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
              "hide": false,
')
    || TO_CLOB('
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
              "r')
    || TO_CLOB('owGroup": false,
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
    || TO_CLOB('"aggFunc": "sum",
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
    || TO_CLOB('           "columnGroupShow": "open",
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
    || TO_CLOB(' "columnGroupShow": "open",
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
    || TO_CLOB('          "width": 150,
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
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
              "width": 150,
              ')
    || TO_CLOB('"hide": false,
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
              "hide": false,
              "rowGroup":')
    || TO_CLOB(' false,
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
              "hide": false,
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
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "')
    || TO_CLOB('columnGroupShow": "open",
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
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow":')
    || TO_CLOB(' "open",
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
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editabl')
    || TO_CLOB('e": true,
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
           ')
    || TO_CLOB('   "type": [
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "D Foto Banco/tgt Rep",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
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
            }
          ]
        }
      ],
	  "rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "[''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Reparto.Name)",
		')
    || TO_CLOB('	"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "a')
    || TO_CLOB('ctions": [
        {
          "componentId": "tim_trd_inizializza",
          "componentLabel": "Inizializza Target",
          "process": "MUI_DUMMY_CUB.TimoneRIF.TGT4",
          "timeout": -1,
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
 ')
    || TO_CLOB('           },
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
              "hasPicklist": true
            },
            {
              "dim')
    || TO_CLOB('ension": "Reparto",
              "attribute": "Descrizione",
              "paramName": "inReparto",
              "label": "Reparto",
              "hasPicklist": true
            }
          ]
        }
      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('141','/Timone/Target_Reparto/Data_(ACQ)',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
    "reparto":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('142','/Timone/Target_Reparto/Promo_(CAT)',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_TargetReparto_Promo",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozio')
    || TO_CLOB('ne],BASC)}",
            "Reparto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Scenario] )}, 0)}, ASC)}}",
            "Misura Timone.": "{{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Timone.] )}, 0)}, ASC)}},{[Misura Timone.].[TOT_FOTO]}}"
          }
        },
')
    || TO_CLOB('        "FROM": "[Timone Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
        }
      },
      "ExecuteMDX": {
        "Members": [
          "Name",
        		"Attributes/Descrizione",
                "Attributes/Canale",
                "Attributes/TipoElemento",
                "Attributes/MUI_Descrizione",
          	    "Attributes/Riferimento",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Upda')
    || TO_CLOB('teable",
          "Consolidated",
          "HasPicklist",
          "PicklistValues"
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
      "columnDefs": [
        {
          "headerName": "Canale",
          "field": "P')
    || TO_CLOB('romozione.Canale",
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
          "type": [
            "TM1Element"
          ]
        },
        {
         ')
    || TO_CLOB(' "headerName": "Descrizione + Data",
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
          ')
    || TO_CLOB('  "TM1Element"
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
          "width": 70,
          "hide": true,
          "rowGroup": false,
     ')
    || TO_CLOB('     "editable": false,
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow')
    || TO_CLOB('": "always",
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
              "aggFunc": "sum",
              "columnGroupShow": "always",
              "editabl')
    || TO_CLOB('e": true,
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
              "editable": true,
              "type": [
    ')
    || TO_CLOB('            "TM1DataColumnInteger",
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
                "TM1DataColum')
    || TO_CLOB('nInteger",
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
              "type": [
                "TM1DataColumnInteger",
              ')
    || TO_CLOB('  "numericColumn"
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
              "type": [
                "TM1DataColumnInteger",
                "numericColu')
    || TO_CLOB('mn"
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
       ')
    || TO_CLOB('     },
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
            },
    ')
    || TO_CLOB('        {
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
                "TM1DataColumnPercentage",
                "numericColumn"
')
    || TO_CLOB('              ]
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "always')
    || TO_CLOB('",
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
              "columnGroupShow": "always",
              "editable": true,
  ')
    || TO_CLOB('            "type": [
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
              "editable": true,
              "type": [
                "TM1')
    || TO_CLOB('DataColumnInteger",
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
       ')
    || TO_CLOB('         "numericColumn"
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
                "TM1DataColumnInteger",
                "numericColumn"
   ')
    || TO_CLOB('           ]
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
   ')
    || TO_CLOB('         },
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
                "numericColumn"
              ]
            }
          ]
       ')
    || TO_CLOB(' },
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
              "columnGroupShow": "always",
              "editable": true,
              "')
    || TO_CLOB('type": [
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
              "editable": true,
              "type": [
                "TM1DataColum')
    || TO_CLOB('nInteger",
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
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"')
    || TO_CLOB('
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
         ')
    || TO_CLOB('   },
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
                "numericColumn"
              ]
            },
            {
       ')
    || TO_CLOB('       "headerName": "N. Foto Spec. Banco",
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
              "headerNam')
    || TO_CLOB('e": "N. Foto Ultima",
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
         ')
    || TO_CLOB('     "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_MKT",
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
              "headerName": "D Foto Banco/tgt Mkt",
              "field": "S')
    || TO_CLOB('cenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
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
          "headerName": "Target Acq.",
          "headerClass": "he')
    || TO_CLOB('aderAcq",
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
              "type": [
                "TM1DataColumnInteger",
                "numericColumn"
')
    || TO_CLOB('              ]
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
       ')
    || TO_CLOB('     {
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
              ]
            },
            {
              "headerName": "N. Foto')
    || TO_CLOB(' Banco",
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
            {
              "headerName": "N. Foto Speciali",
              "f')
    || TO_CLOB('ield": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
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
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_ACQ$$M')
    || TO_CLOB('isuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
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
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO')
    || TO_CLOB('_ULT",
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
              "')
    || TO_CLOB('width": 80,
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
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
              "width": 80,')
    || TO_CLOB('
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
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [
    ')
    || TO_CLOB('    {
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
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
')
    || TO_CLOB('
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
              "hasPicklist": true
            }
          ]
        }
      ]
')
    || TO_CLOB('
    }
  ]
}
'),'GRID');

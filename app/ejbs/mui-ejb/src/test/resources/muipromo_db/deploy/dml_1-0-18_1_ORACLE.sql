SET DEFINE OFF;
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
				"headerde')
    || TO_CLOB('faults": {
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
					"hide": false,
					"rowGrou')
    || TO_CLOB('p": false,
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
							"TM1DataColumnNumber",
							"numericColumn"
						]
					},
					"APPLICATO": {
						"type": [
							"TM1DataColumnNumber",
							"numericColumn"
						]
					},
					"PRESTAZIONE": {
						"width": 300,
						"type": [
							"TM1')
    || TO_CLOB('DataColumnText"
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
				"field": "Rata.Descrizione",
				"headerName": "Rata')
    || TO_CLOB('",
				"width": 400,
				"pinned": "left",
				"type": [
					"TM1Element"
				]
			},
			"columnDefs": [
				{
					"headerName": "Descrizione + Data",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": false,
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
					"rowGroup": true,
					"editabl')
    || TO_CLOB('e": false,
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
				{
')
    || TO_CLOB('
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
					"headerName": "Totale Compratore",
					"fie')
    || TO_CLOB('ld": "Compratore.MUI_TOT",
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
					"field": "Fornitore.MUI_TOTS",
					"width": 100,
				')
    || TO_CLOB('	"hide": true,
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
					"editable": false,
')
    || TO_CLOB('
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
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( ')
    || TO_CLOB('{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, DESC)}}",
						"Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
						"Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
						"Rata": "{ [Rata].[(I) Fatturazione] }",
						"ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
						"Spazio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Pr')
    || TO_CLOB('ogressivo] )}, 0)}, DESC)}}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
						"Misura Fatturazione": "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
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
					"Attributes",
					"UniqueName"
				],
				"Cells": [
					"Ordinal",
					"Value",
					"Updat')
    || TO_CLOB('eable",
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
						"openByDefault": true
					},
					"TGT_MKT": {
						"headerClass": "headerMkt",
						"')
    || TO_CLOB('openByDefault": true
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
			"autoGroupColumnDef": {
				"cellRendererParams": {
					"suppressCount": true
')
    || TO_CLOB('
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
					"headerName": "Descrizione + Data",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": false,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},{
					"headerName": "Compratore",
					"field": "Compratore.Desc')
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
		')
    || TO_CLOB('			"editable": false,
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
						"TM1Eleme')
    || TO_CLOB('nt"
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
						"TM1Element"
					]
				},
				{
					"headerName": "Rata",
')
    || TO_CLOB('
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
					"headerName": "Spazio",
					"field": "SpazioProgressivo.Descrizione",
					"width":')
    || TO_CLOB(' 200,
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
      "name": "gc_AssociazioneArticoli_top",
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
    || TO_CLOB('      "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
            "Rata": "{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Rata] )}, 0)}, { [Rata].[RATA_UNICA] }) }, { [Rata].[PROGR_CONTR] }) }, { [Rata].[PROGR_EX_CONTR] }) }"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Pr')
    || TO_CLOB('omozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
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
          "Attributes",
          "UniqueName"
        ],
        "C')
    || TO_CLOB('ells": [
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
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault')
    || TO_CLOB('": true
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
          "hide": false,
          "rowGroup": false,
          "a')
    || TO_CLOB('ggFunc": "sum",
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
              "TM1DataColumnNumber",
              "numericColumn"
            ]
          },
          "APPLICATO": {
            "type": [
              "TM1DataColumnNumber",
              "numericColumn"
        ')
    || TO_CLOB('    ]
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
            "width": 300,
            "type": [
              "TM1DataColumnText"
            ]
          },
          "OK": {
            "width": 50,
            "type')
    || TO_CLOB('": [
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
          "headerName": "Totale Compratore",
          "field": "Compratore.MUI_TOT')
    || TO_CLOB('",
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
          "headerName": "To')
    || TO_CLOB('tale Fornitore",
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
            "TM1Element"
        ')
    || TO_CLOB('  ]
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
          "editable": false,
          "type": [
 ')
    || TO_CLOB('           "TM1Element"
          ]
        }
      ]
    },
    {
      "name": "gc_AssociazioneArticoli_bottom",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, DESC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, DESC)}}",
       ')
    || TO_CLOB('     "Rata": "{ [Rata].[(I) Fatturazione] }",
            "ArticoloNoSec": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ArticoloNoSec] )}, 0)}, DESC)}}",
            "Spazio Progressivo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio Progressivo] )}, 0)}, DESC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].')
    || TO_CLOB('[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Misura Fatturazione": "{[Misura Fatturazione].[ASS_RATA],[Misura Fatturazione].[OK]}"
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
          "Attributes",
          "UniqueName"
        ],
 ')
    || TO_CLOB('       "Cells": [
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
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "open')
    || TO_CLOB('ByDefault": true
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
          "width": 200,
          "hide": false,
          "rowGroup": false,
   ')
    || TO_CLOB('       "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
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
          "TM1Element')
    || TO_CLOB('"
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
            "TM1Element"
          ]
        },
        {
          "headerName": "Fornitore",
          "field": "Fornitore.Descrizione",
          "width": 200,
          "hide": true,
          "rowGroup": true,
   ')
    || TO_CLOB('       "editable": false,
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
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Articolo",
          "field": "ArticoloNoSec.Descrizione",
          "width": 300,
          "hid')
    || TO_CLOB('e": true,
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
				"headerconf" : [ "MUI_DescrizioneData" , "Descrizione"]  ')
    || TO_CLOB(',
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
				"childrendefaults":  {"width":100,"hide":false,"rowGroup": false ,"aggFunc"')
    || TO_CLOB(': "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnText"] },
				"childrenCustomTypes"  : { "LISTINO":{"type":[ "TM1DataColumnNumber" ,"numericColumn"]} ,
					"APPLICATO":{"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
					"PRESTAZIONE":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"CAUS":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"DESC_FATT":{"width":300 , "type":[ "TM1DataColumnText" ]},
					"OK":{"width":50 , "type":[ "TM1DataColumnTex')
    || TO_CLOB('t" ]}
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
					"headerName": "Totale Compratore",
					"field": "Compratore.MUI_TOT",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
')
    || TO_CLOB('					]
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
					"width": 100,
					"hide": true,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Fornitor')
    || TO_CLOB('e",
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
						"TM1Element"
					]
				},
				{
					"headerName": "Rata",
					"field": "Rata.Descrizione",
					"width": 70,
					"hide"')
    || TO_CLOB(': true,
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
          "Attributes",
          "UniqueName"
        ],
        "Cel')
    || TO_CLOB('ls": [
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
          "TG')
    || TO_CLOB('T_ACQ": {
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
          "width": 110,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGr')
    || TO_CLOB('oupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "LISTINO": {
            "type": [
              "TM1DataColumnNumber",
              "numericColumn"
            ]
          },
          "APPLICATO": {
            "type": [
              "TM1DataColumnNumber",
              "numericColumn"
            ]
          }
        }
     ')
    || TO_CLOB(' },
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
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": tr')
    || TO_CLOB('ue,
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
          "width":')
    || TO_CLOB(' 100,
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
          ')
    || TO_CLOB('"field": "Rata.MUI_TOTS",
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('13','/Pianificazione/Articoli_Fittizi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_articoliFittizi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Articolo Fittizio": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo Fittizio] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura ')
    || TO_CLOB('Articolo Fittizio": "{[Misura Articolo Fittizio].[(I) Aggiornamento Articoli Fittizi]}"
          }
        },
        "FROM": "[Articoli Fittizi Aggiornamento]"
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
      "Dyn')
    || TO_CLOB('amicColumns": true,
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
          "rowGroup": false,
          "aggFunc": "",
          "columnGroupShow": "always",
          "editable": true,
          "type": [
')
    || TO_CLOB('
            "TM1DataColumnText",
            "numericColumn"
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
            "columnGroupShow": "always"
          },
          "DataFine": {
            "cellClass": "dateFormat",
            "type": [
              "TM1DataColumnDate"')
    || TO_CLOB(',
              "numericColumn"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "PRZ_ATT": {
            "type": [
              "TM1DataColumnNumber"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "CST": {
            "type": [
              "TM1DataColumnNumber"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          }
      ')
    || TO_CLOB('  }
      },
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "ArticoloFittizio.Descrizione",
        "headerName": "Articolo Fittizio",
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Totale Fittizio",
          "field": "ArticoloFittizio.MUI_TOT"')
    || TO_CLOB(',
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
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {},
 ')
    || TO_CLOB('     "actions": [
        {
          "componentId": "btnCreaArticoloFittizioId",
          "componentLabel": "Crea Articolo Fittizio in Promo",
          "process": "MUI_DUMMY_DIM.Articolo Fittizio.Aggiornamento New iN Promo",
          "timeout": -1,
          "refresh": [
            "gc_articoliFittizi"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
  ')
    || TO_CLOB('            "label": "Compratore",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "btnEseguiAzioneArticoliFittiziId",
          "componentLabel": "Esegui Azione Su Articoli Fittizzi",
          "process": ')
    || TO_CLOB('"MUI_DUMMY_CUB.Articolo Fittizio.SpostaSuArtEff_new",
          "timeout": -1,
          "refresh": [
            "gc_articoliFittizi"
          ],
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
              "attribute": "Descri')
    || TO_CLOB('zione",
              "paramName": "inPromo",
              "label": "Promozione",
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "')
    || TO_CLOB('Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}",
            "Zona Promo": "{ EXCEPT({{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Zona Promo] )}, 0)}, ASC)}}, { [Zona Promo].[NA],[Zona Promo].[SOCIETA_1],[Zona Promo].[SOCIETA_2] })}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "')
    || TO_CLOB('Misura Promozione Pianificazione": "{[Misura Promozione Pianificazione Zone].[(I) Pianif promozione (zone)]}"
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
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updatea')
    || TO_CLOB('ble",
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
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
          "column')
    || TO_CLOB('GroupShow": "always",
          "editable": true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MIN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MAX": {
            "type": [
              "TM1DataColumnNumber"
            ]
      ')
    || TO_CLOB('    },
          "PRZ_ATT_USR": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "_perc__SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "VAL_SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_PROMO": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_AN": {
          ')
    || TO_CLOB('  "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_USR": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CONTR__perc__IN_FATT": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "CST_C_IVA": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_PROMO_C_IVA": {
            "type": [
        ')
    || TO_CLOB('      "TM1DataColumnNumber"
            ]
          },
          "N_PEZZI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "COLLI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "TOT_dot_VEND": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "F_FATT": {
            "type": [
              "TM1DataColumnNumber"
            ]')
    || TO_CLOB('
          },
          "IVA": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "RIFATT_BS": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "LIM_UTIL": {
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
        "fiel')
    || TO_CLOB('d": "ZonaPromo.Descrizione",
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
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM')
    || TO_CLOB('1Element"
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
          "headerName": "Fornitore",
          "field": "Articolo.Fornitore",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editab')
    || TO_CLOB('le": false,
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
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "width": 100,
          "hide": true,
 ')
    || TO_CLOB('         "rowGroup": false,
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
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
         ')
    || TO_CLOB(' "width": 100,
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
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {},
      "actions": [
 ')
    || TO_CLOB('       {
          "componentId": "btnInizializzaDifferenziazionePromoId",
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
              "label": "')
    || TO_CLOB('Compratore",
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
          ')
    || TO_CLOB('  "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
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
        "FROM": "[Configurazione Promo')
    || TO_CLOB('zione - Set Parametri]",
        "WHERE": {
          "Versione": "[UFF]"
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
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": ')
    || TO_CLOB('[
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
        "headerCustomTypes": {},
        "childrenCustomTypes": {
          "VAL_SC": {
         ')
    || TO_CLOB('  "type": [
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
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "heade')
    || TO_CLOB('rName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
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
            "T')
    || TO_CLOB('M1Element"
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
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editabl')
    || TO_CLOB('e": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_meccanicheSet_associazione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promo')
    || TO_CLOB('zione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{ EXCEPT( {TM1SUBSETALL( [Compratore] )}, { [Compratore].[NA], [Compratore].[S000] }) }}",
            "ID Set": "{[ID Set].[(I) Lista set]}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "ID RaggrSet": "{[ID RaggrSet].[(I) Configura set ')
    || TO_CLOB('articoli]}",
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
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
    ')
    || TO_CLOB('      "Consolidated",
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
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "",
  ')
    || TO_CLOB('        "columnGroupShow": "always",
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
            "columnGroupShow": "always"
          },
          "DataFine": {
            "cellClass": "dateFormat",
')
    || TO_CLOB('
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
        "cellClass": "cellClass-left-text",
        "width": 300,
        "pinned": "left",
        "')
    || TO_CLOB('type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
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
          "hid')
    || TO_CLOB('e": true,
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
          "headerName": "Articolo",
          "field": "Articolo.Descrizione",')
    || TO_CLOB('
          "width": 100,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Comprator')
    || TO_CLOB('e": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "{[Misura Timone.].[(I) Misura Tim')
    || TO_CLOB('one Pianificazione]}"
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
      "autoGroupColumnDe')
    || TO_CLOB('f": {
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
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults')
    || TO_CLOB('": {
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
          },
')
    || TO_CLOB('
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
            "TM1DataColumnInteger",
            "numericColumn"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SPE')
    || TO_CLOB('C": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_ULT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE": {
            "type": [
              "TM1DataColumnInteger"')
    || TO_CLOB('
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE_SPEC": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "agg')
    || TO_CLOB('Func": "",
            "columnGroupShow": "always"
          },
          "EXTRA_CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "D_ART_slash_TGT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
         ')
    || TO_CLOB(' },
          "D_FOTO_slash_TGT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": true,
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
          "rowGroup": true,
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
    || TO_CLOB('  "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_proiezioni_2",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozion')
    || TO_CLOB('e].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Promozione Pianificazione": "({FILTER(TM1SUBSETALL([Misura Promozione Pianificazione]),[}ElementAttributes_Misura P')
    || TO_CLOB('romozione Pianificazione].([}ElementAttributes_Misura Promozione Pianificazione].[Ordinamento])>0 )})"
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
          "Attributes",
          "UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
       ')
    || TO_CLOB('   "Consolidated",
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
        "childrendefaults": {
          "width": 100,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": ')
    || TO_CLOB('true,
          "type": [
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {
          "PRZ_ATT_ANN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MIN": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_MAX": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_ATT_USR": {
         ')
    || TO_CLOB('   "type": [
              "TM1DataColumnNumber"
            ]
          },
          "_perc__SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "VAL_SC": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "PRZ_PROMO": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_AN": {
            "type": [
              "TM1DataColumnNumb')
    || TO_CLOB('er"
            ]
          },
          "CST_USR": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CONTR__perc__IN_FATT": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "CST_C_IVA": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "CST_PROMO_C_IVA": {
            "type": [
              "TM1DataColumnNumber"
            ]
 ')
    || TO_CLOB('         },
          "N_PEZZI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "COLLI": {
            "type": [
              "TM1DataColumnDecimal3"
            ]
          },
          "TOT_dot_VEND": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "F_FATT": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "IVA": {
         ')
    || TO_CLOB('   "type": [
              "TM1DataColumnPercentage"
            ]
          },
          "RIFATT_BS": {
            "type": [
              "TM1DataColumnNumber"
            ]
          },
          "LIM_UTIL": {
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
        "field": "Articolo.DescrizioneCODICE",
        "h')
    || TO_CLOB('eaderName": "Articolo",
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
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
      ')
    || TO_CLOB('  {
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
          "headerName": "Fornitore",
          "field": "Articolo.Fornitore",
          "width": 100,
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
         ')
    || TO_CLOB(' "editable": false,
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
  ')
    || TO_CLOB('        "rowGroup": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {}
    },
    {
      "name": "gc_proiezioni_3",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data')
    || TO_CLOB('_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": ')
    || TO_CLOB('"{[Scenario].[(II) TCS I]}",
            "Sezione Tematica": "{{ EXCEPT( { EXCEPT( { EXCEPT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Sezione Tematica] )}, 0)}, { [Sezione Tematica].[ST_0000] }) }, { [Sezione Tematica].[ST_001] }) }, { [Sezione Tematica].[ST_042] }) }}",
            "Misura Timone.": "{[Misura Timone.].[(II)Timone Categoria Sez]}"
          }
        },
        "FROM": "[Timone Categoria Sezione I]",
        "WHERE": {
          "Versione": "[UFF]"
        }
      },
      "')
    || TO_CLOB('ExecuteMDX": {
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
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": true
        },
        "field": "Categoria.Descrizione",
        "headerName": "Categoria",
     ')
    || TO_CLOB('   "cellClass": "cellClass-left-text",
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
          "Descrizione",
          "Descrizione"
        ],
        "headerdefaults": {
          "marryChildren": true
        },
        "headerCustomTypes": {
          "RIF_MKT_DT": {
            "openByDefault": true')
    || TO_CLOB('
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
        },
        "childrendefa')
    || TO_CLOB('ults": {
          "width": 150,
          "hide": false,
          "rowGroup": false,
          "aggFunc": "sum",
          "columnGroupShow": "always",
          "editable": false,
          "type": [
            "TM1DataColumnInteger"
          ]
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
     ')
    || TO_CLOB('     "rowGroup": true,
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
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
          "field": "Categoria.RepartoDesc",
      ')
    || TO_CLOB('    "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {},
      "actions": [
        {
          "componentId": "btnSpostaArticoliId",
          "componentLabel": "Sposta Articoli",
          "process": "MUI_DUMMY_COPY.Promozione.Articolo+del",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_1"
          ],
     ')
    || TO_CLOB('     "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "toPromo",
              "label": "Promozione",
              "hasPicklist": true
            }
          ]
        }')
    || TO_CLOB(',
        {
          "componentId": "btnCopiaArticoliId",
          "componentLabel": "Copia Articoli",
          "process": "MUI_DUMMY_COPY.Promozione.Articolo",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_1"
          ],
          "params": [
            {
              "dimension": "Compratore",
              "attribute": "Descrizione",
              "paramName": "inCompratore",
              "label": "Compratore",
              "hasPicklist": tr')
    || TO_CLOB('ue
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "toPromo",
              "label": "Promozione",
              "hasPicklist": true
            }
          ]
        },
        {
          "componentId": "btnClearId",
          "componentLabel": "Clear",
          "process": "MUI_DUMMY_CUB.Promozione Pianificazione Pulizia Articoli",
          "timeout": -1,
          "refresh": [
       ')
    || TO_CLOB('     "gc_proiezioni_1"
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
          "componentId": "btnAbilitaFamigliaId",
          "componentLabel": "Abilita Famiglia",
          "process": "MUI_DUMMY_CUB.Famiglia.Programmazione Fornitore-Art')
    || TO_CLOB('icolo.Caller",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni"
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
          "componentId": "btnInizializzaId",
          "componentLabel": "Inizializza",
          ')
    || TO_CLOB('"process": "MUI_DUMMY_CUB.Pianificazione Panieri.SPF.Caller",
          "timeout": -1,
          "refresh": [
            "gc_proiezioni_1"
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],B')
    || TO_CLOB('ASC)}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "{[')
    || TO_CLOB('Misura Timone.].[(I) Misura Timone Pianificazione]}"
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
   ')
    || TO_CLOB('   },
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
          "Descrizione",
          "Descrizione"
    ')
    || TO_CLOB('    ],
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
            "open')
    || TO_CLOB('ByDefault": true
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
          "type": [
            "TM1DataColumnInteger"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SP')
    || TO_CLOB('EC": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_ULT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE": {
            "type": [
              "TM1DataColumnInteger')
    || TO_CLOB('"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "SPZ_CAMP": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "MARGINE_LORDO__perc_": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          }
        }
      },
     ')
    || TO_CLOB(' "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
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
          "field": "Compratore.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
  ')
    || TO_CLOB('        "type": [
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
      "styleRules": {}
    },
    {
      "name": "gc_selezioneArticoliContributi_promozione",
      "logMemory": true,
      "logTime": tru')
    || TO_CLOB('e,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {T')
    || TO_CLOB('M1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
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
          "Scen')
    || TO_CLOB('ario": "[BDG]",
          "Versione": "[UFF]"
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
      "DynamicColumns": true,
      "DynamicColumnsSettings": {
        "headerconf": [
          "Descrizione"
 ')
    || TO_CLOB('       ],
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
            "o')
    || TO_CLOB('penByDefault": true
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
            "TM1DataColumnText"
          ]
        },
        "childrenCustomTypes": {
          "Selezione": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
   ')
    || TO_CLOB('         "columnGroupShow": "always"
          },
          "Famiglia": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "IF (%)": {
            "type": [
              "TM1DataColumnNumber"
            ],
            "aggFunc": "avg",
            "columnGroupShow": "always"
          },
          "Tech_Contributi_A_M": {
            "type": [
              "TM1Data')
    || TO_CLOB('ColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Misura CICS": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Memo Compratore": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "NOT')
    || TO_CLOB('E 1": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "NOTE 2": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "NOTE 3": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow"')
    || TO_CLOB(': "always"
          },
          "Raggr. No Foto": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Raggr. FF EC": {
            "type": [
              "TM1DataColumnText"
            ],
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "Raggr. Foto": {
            "type": [
              "TM1DataColumnText"
            ],')
    || TO_CLOB('
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          ".T": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
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
    ')
    || TO_CLOB('    "cellClass": "cellClass-left-text",
        "width": 400,
        "pinned": "left",
        "type": [
          "TM1Element"
        ]
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": ')
    || TO_CLOB('"Compratore",
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
          "headerName": "Reparto",
          "field": "Articolo.RepartoDesc",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]')
    || TO_CLOB('
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
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": true,
          "rowGroup": true,
          "editable": false,
       ')
    || TO_CLOB('   "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Grm",
          "field": "Articolo.GRMDesc",
          "width": 100,
          "hide": true,
          "rowGroup": true,
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
          "rowGroup": true,
  ')
    || TO_CLOB('        "editable": false,
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
      "styleRules": {}
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],B')
    || TO_CLOB('ASC)}",
            "Compratore": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Categoria].[S000]}) }",
            "Categoria": "{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }"
          }
        },
        "COLS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Scenario": "{[Scenario].[(I) Scenario Timone Pianificazione]}",
            "Misura Timone.": "{[')
    || TO_CLOB('Misura Timone.].[(I) Misura Timone Pianificazione]}"
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
   ')
    || TO_CLOB('   },
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
          "Descrizione",
          "Descrizione"
    ')
    || TO_CLOB('    ],
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
            "open')
    || TO_CLOB('ByDefault": true
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
          "type": [
            "TM1DataColumnInteger"
          ]
        },
        "childrenCustomTypes": {
          "N_FOTO_SP')
    || TO_CLOB('EC": {
            "type": [
              "TM1DataColumnText"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_ULT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE": {
            "type": [
              "TM1DataColumnInteger')
    || TO_CLOB('"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "N_FOTO_SCAFFALE_SPEC": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "ag')
    || TO_CLOB('gFunc": "",
            "columnGroupShow": "always"
          },
          "EXTRA_CONTR": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "D_ART_slash_TGT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
        ')
    || TO_CLOB('  },
          "D_FOTO_slash_TGT": {
            "type": [
              "TM1DataColumnInteger"
            ],
            "hide": "true",
            "aggFunc": "",
            "columnGroupShow": "always"
          },
          "MARGINE_LORDO__perc_": {
            "type": [
              "TM1DataColumnPercentage"
            ]
          }
        }
      },
      "columnDefs": [
        {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione",
   ')
    || TO_CLOB('       "width": 70,
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
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Reparto",
')
    || TO_CLOB('          "field": "Categoria.RepartoDesc",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Categoria",
          "field": "Categoria.Descrizione",
          "width": 70,
          "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
  ')
    || TO_CLOB('    ],
      "styleRules": {}
    },
    {
      "name": "gc_selezioneFamiglieContributi_promozione",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
         ')
    || TO_CLOB('   "Compratore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}}",
            "Fornitore": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)}, ASC)}}",
            "Articolo": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Programmazione Fornitore": "{[Misura Programmazione Fornitore].[(I) Programmazione')
    || TO_CLOB(' Fornitore Articolo (Dinamico)]}"
          }
        },
        "FROM": "[Programmazione Fornitore - Articolo]",
        "WHERE": {
          "Scenario": "[BDG]",
          "Versione": "[UFF]"
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
          "Pi')
    || TO_CLOB('cklistValues"
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
          "editable": true,
        ')
    || TO_CLOB('  "type": [
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
      ')
    || TO_CLOB('  {
          "headerName": "Promozione",
          "field": "Promozione.Descrizione_plus_Data",
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
          "typ')
    || TO_CLOB('e": [
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
          "headerName": "Categoria",
          "field": "Articolo.CategoriaDesc",
          "width": 100,
          "hide": true,
          "rowGroup": ')
    || TO_CLOB('true,
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
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        },
        {
          "headerName": "Famiglia",
          "field": "Articolo.MUI_Famiglia",
          "width": 100,
         ')
    || TO_CLOB(' "hide": true,
          "rowGroup": true,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "styleRules": {}
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
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Tipo Promozione" : "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Tipo Promozione] )}, 0)}, ASC)}}",
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].')
    || TO_CLOB('[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Prestazione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false ,
          "DIMENSIONS": {
            "Misura Prestazioni": "{[Misura Prestazioni].[listino] }",
            "Contratto": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Contratto] )}, 0)}, ASC)}}"
          }
     ')
    || TO_CLOB('   },
        "FROM": "[Promozione Pianificazione Listino(I)]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
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
      },
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "')
    || TO_CLOB('Prestazione.Descrizione"
      , "headerName": "Prestazione"
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Descrizione","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Promozione","field":"Promozione.Descrizione_plus_Data","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},')
    || TO_CLOB('

        {"headerName":"Listino",
          "marryChildren":true,
          "children" :
          [
            {"headerName":"Extra Contratto","field":"MisuraPrestazioni$listino$$Contratto$ExtraContratto","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger"]} ,
            {"headerName":"Contratto","field":"MisuraPrestazioni$listino$$Contratto$Contratto_1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnInteger')
    || TO_CLOB('"]}
          ]
        }


      ]
    ,
      "styleRules":{		}
    },

    {
      "name": "gc_timing",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Versione": "{[Versione].[UFF]}" ,
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Cana')
    || TO_CLOB('le], BASC),[Promozione].[Tipo Promozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà": "{[Misura Promozione Proprietà].[Misura 1]}"
          }
        },
        "FROM": "[Configurazione Promozione - Proprietà]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal')
    || TO_CLOB('",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "cellClass": "cellClass-left-text"
      , "width":400
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"he')
    || TO_CLOB('aderName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-left-t')
    || TO_CLOB('ext","type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":150,"hide":false,"rowGroup": false , "editable": false,"cellClass": "cellClass-left-text","type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Las","width')
    || TO_CLOB('":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":100,"hide":false,"rowGroup": false , "editable": tru')
    || TO_CLOB('e,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Freschi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "T')
    || TO_CLOB('M1DataColumnDate"]},
        {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":')
    || TO_CLOB('"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":100,"hide":false,"rowGroup": false , "editable": false,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Scadenza Conferma Prezzi","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Pianific')
    || TO_CLOB('azione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Volantino/Piano Media","field":"MisuraPromozionePropriet_agrave_$resp_mktg2","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","width":100,"hide":false,"rowGroup": false , "editab')
    || TO_CLOB('le": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataCo')
    || TO_CLOB('lumnDate"]},
        {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Valore Pu')
    || TO_CLOB('nto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}

      ]
    ,
      "styleRules":{		}
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
					"Attributes",
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
					"field": "Promozione.Canale",
					"width": 100,
					"hide": true,
					"rowGroup": true,
					"ed')
    || TO_CLOB('itable": false,
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
    || TO_CLOB('			{
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
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": true,
					"rowGroup": true,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Reparto"')
    || TO_CLOB(',
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
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Tipo Elemento",
					"field": "Reparto.TipoElemento",
					"widt')
    || TO_CLOB('h": 70,
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
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable": true,
	')
    || TO_CLOB('						"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$TOT_FOTO",
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
							"headerName": "N. Foto",')
    || TO_CLOB('
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
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
							"row')
    || TO_CLOB('Group": false,
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
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
			')
    || TO_CLOB('					"TM1DataColumnInteger",
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
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Fot')
    || TO_CLOB('o Ultima",
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
						},
						{
							"headerName": "Venduto Promo (s/iva)",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
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
							"headerName": "ML %",
							"field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,')
    || TO_CLOB('
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
					"children": [
						{
							"headerName": "N. Art",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_ART_PROMO",
							"width": 70,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "always",
							"editable"')
    || TO_CLOB(': true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "Totale",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$TOT_FOTO",
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
							"headerName": "N.')
    || TO_CLOB(' Foto",
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
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
							"field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
							"width": 80,
							"hide": false,
							"ro')
    || TO_CLOB('wGroup": false,
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
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
					')
    || TO_CLOB('			"TM1DataColumnInteger",
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
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Ult')
    || TO_CLOB('ima",
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
				{
					"headerName": "Target REP",
					"headerClass": "headerRep",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N.')
    || TO_CLOB(' Art",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_ART_PROMO",
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
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": false,
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
							"headerName": "N. Foto",
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
							"width": 80,
							"hide": false,
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnInt')
    || TO_CLOB('eger",
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
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Speciali",
							"field": "Scena')
    || TO_CLOB('rio$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
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
							"field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width": 80,
							"hide": false,
							"rowGroup": false,')
    || TO_CLOB('
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
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnI')
    || TO_CLOB('nteger",
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
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto Banco/tgt Mkt",
							"fi')
    || TO_CLOB('eld": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
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
					"headerClass": "headerAcq",
					"openByDefault": true,
					"children": [
						{
							"headerName": "N.')
    || TO_CLOB(' Art",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
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
							"headerName": "Totale",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
							"width": 70,
							"hide": true,
							"rowGroup": f')
    || TO_CLOB('alse,
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
							"rowGroup": false,
							"aggFunc": "sum",
							"columnGroupShow": "open",
							"editable": true,
							"type": [
								"TM1DataColumnIntege')
    || TO_CLOB('r",
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
							"editable": true,
							"type": [
								"TM1DataColumnInteger",
								"numericColumn"
							]
						},
						{
							"headerName": "N. Foto Speciali",
							"field": "Scenario$')
    || TO_CLOB('TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SPEC",
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
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
							"width": 80,
							"hide": true,
							"rowGroup": false,
				')
    || TO_CLOB('			"aggFunc": "sum",
							"columnGroupShow": "open",
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
							"editable": true,
							"type": [
								"TM1DataColumnInteger"')
    || TO_CLOB(',
								"numericColumn"
							]
						},
						{
							"headerName": "D Foto/tgt Rep",
							"field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_slash_TGT_REP",
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
							"field": "S')
    || TO_CLOB('cenario$TGT_ACQ$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
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
                    },

   ')
    || TO_CLOB('     "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "ID.Name"
                                 , "headerName": "ID"
                                 , "width":300
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
              {"headerName":"Anno","field":"Anno.Name","widt')
    || TO_CLOB('h":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
              {"headerName":"Client","field":"_cbrace_Clients._cbrace_TM1_DefaultDisplayValue","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"ID","field":"ID.Name","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","w')
    || TO_CLOB('idth":160,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Desc","field":"MisuraPianoOperativoCommerciale$Desc","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Gruppo","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Sottogr')
    || TO_CLOB('uppo","field":"MisuraPianoOperativoCommerciale$Canale","width":120,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]} ,
              {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
              {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":fa')
    || TO_CLOB('lse,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]} ,
              {"headerName":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false')
    || TO_CLOB(',"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":120,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
              ],
          "actions": [{
            "componentId": "btnCreaPromoId",
            "componentLabel": "Crea Promozione",
            "process": "MUI_DUMMY_DIM.Promozione.NewProm')
    || TO_CLOB('o",
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('22','/Piano_Annuale/Foto',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Foto",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "maxCells": 1000000 ,
      "maxCellsMessage": "Aggiungere il filtro sulla Promozione." ,
      "rowSuppressionEnabled": true,
      "colSuppressionEnabled": false,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Raggruppamento Foto": "{{TM1SORTBYINDEX({FILTER( {{TM1FILTERBY')
    || TO_CLOB('LEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}}, [Raggruppamento Foto].[MUI_NRFOTO] <= 30.000000)}, ASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{ [Compratore].[(I) Compratori (tot)')
    || TO_CLOB('] }"
          }
        },
        "FROM": "[Configurazione RaggrFoto]" ,
        "WHERE": {
          "Misura Config RaggrFoto": "[OK]"
        }

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
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
      },

      "DynamicC')
    || TO_CLOB('olumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["MUI_DescrizioneData" ,"Descrizione"]  ,
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
    || TO_CLOB('   },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
        }
      } ,
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Raggruppame')
    || TO_CLOB('ntoFoto.Descrizione"
      , "headerName": "Raggruppamento Foto"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tot Foto","field":"RaggruppamentoFoto.MUI_TOT","width":70,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]}  ,
        {"headerName":"Tot Foto by","field":"RaggruppamentoFoto.MUI_TOTS","width":70,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]}
      ]
')
    || TO_CLOB('
    }
  ]
}
'),'GRID');
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Conso')
    || TO_CLOB('lidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"')
    || TO_CLOB('type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descr')
    || TO_CLOB('izione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Valore Punto Fragola","field":"MisuraPianoOperativoCommerciale$Costo_punto_Fragola","width":150,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnDecimal3" ,"numericColumn"]}
      ]
    },
    {
      "name": "gc_GabbiaPunti",
      "logMemory": true,
      "logTime": true,
      "skip": true,
     ')
    || TO_CLOB(' "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "ID": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [ID] )}, 0)}, ASC)}}"

          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Misura Gabbia Punti Fragola": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Gabbia Punti Fragola] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Gabbia Punti Fragola]"

      },
      ')
    || TO_CLOB('"ExecuteMDX": {
        "Members": [
          "Name",
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
      },



      "autoGroupColumnDef" :  {

      } ,
      "columnDefs": [
        {"headerName":"ID","field":"ID.Name","width":100,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Eleme')
    || TO_CLOB('nt"]},
        {"headerName":"Prezzo Minimo","field":"MisuraGabbiaPuntiFragola$PrezzoMinimo","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Prezzo Massimo","field":"MisuraGabbiaPuntiFragola$PrezzoMassimo","width":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]},
        {"headerName":"Punti Fragola","field":"MisuraGabbiaPuntiFragola$PuntiFragola","wid')
    || TO_CLOB('th":100,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1DataColumnNumber" ,"numericColumn"]}
      ],
      "actions": [{
            "componentId": "btnModificaValorePuntiFragolaId",
            "componentLabel": "Modifica Valore Punti Fragola",
            "process": "MUI_DUMMY_DIM.Cambio valore Costo Punti Fragola",
            "timeout":-1,
            "refresh": ["gc_Gabbia","gc_GabbiaPunti"],
            "params":[{
              "dimension": "Promozione",
        ')
    || TO_CLOB('      "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
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
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione" ,"Descrizione" ,"Descrizio')
    || TO_CLOB('ne"   ]  ,
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
 ')
    || TO_CLOB('         }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "avg", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "DataInizio":{"cellClass": "dateFormat","type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
          "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc')
    || TO_CLOB('": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Tipo Promozione","field":"TipoPromozione.Caption_Default","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["')
    || TO_CLOB('TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Pr')
    || TO_CLOB('omozione.Descrizione","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}
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
      },



      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Iniziativa.Descrizione"
      , "headerName": "Iniziativa"
      , "width":300
    ')
    || TO_CLOB('  , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"t')
    || TO_CLOB('ype":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"OK","field":"MisuraConfigurazioneIniziative$OK","width":70,"hide":false,"rowGroup": false , "editable": true,"type":[ "TM1Data')
    || TO_CLOB('ColumnInteger" ,"numericColumn"]}
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
          "Attributes",
          "UniqueNa')
    || TO_CLOB('me"
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
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
')
    || TO_CLOB('        {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text", "type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":60,"hide":true,"rowGroup": true , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"cellClass": "')
    || TO_CLOB('cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":100,"hide":true,"rowGroup": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": false , "editable": false,"cellClass": "cellClass-center-text","type":["TM1Element"]},

        {
        "headerName": "Meccaniche Se')
    || TO_CLOB('mplici",
        "openByDefault": true,
        "children": [

        {"headerName":"Sconto %","field":"MeccanicaComplesse$M002","width":110,"hide":false,"rowGroup": false , "editable": true,"cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Prezzo Corto","field":"MeccanicaComplesse$M003","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"P')
    || TO_CLOB('. Fragola","field":"MeccanicaComplesse$M004","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc % Fidaty","field":"MeccanicaComplesse$M005","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Prezzo Corto & P.Fragola","field":"MeccanicaComplesse$M006","width":110,"hide":false,"rowGroup": f')
    || TO_CLOB('alse , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc% Fidaty  &  P. Fragola","field":"MeccanicaComplesse$M007","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"3x2","field":"MeccanicaComplesse$M008","width":50,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}')
    || TO_CLOB(',
        {"headerName":"1 + 1","field":"MeccanicaComplesse$M009","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Il 2 costa la metà ","field":"MeccanicaComplesse$M010","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto % + Punti Fragola","field":"MeccanicaComplesse$M012","width":')
    || TO_CLOB('110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc + Facile val","field":"MeccanicaComplesse$M014","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Oggetti articolo","field":"MeccanicaComplesse$M015","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-t')
    || TO_CLOB('ext","type":["TM1DataColumnText"]},
        {"headerName":"Buono sc.Fidaty (F11)","field":"MeccanicaComplesse$M018","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sc Val Fidaty","field":"MeccanicaComplesse$M023","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto fidaty per classe')
    || TO_CLOB(' cliente (%)","field":"MeccanicaComplesse$M024","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"PC + Triplica Punti","field":"MeccanicaComplesse$M025","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Punti Fragola su insieme","field":"MeccanicaComplesse$M104","width":110,"hide":false,"r')
    || TO_CLOB('owGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Triplica i punti","field":"MeccanicaComplesse$M111","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Punti Fragola Set per Triplica Punti","field":"MeccanicaComplesse$M164","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center')
    || TO_CLOB('-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sconto a valore","field":"MeccanicaComplesse$M031","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - punti fragola","field":"MeccanicaComplesse$M034","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
 ')
    || TO_CLOB('       {"headerName":"Buono Sconto Categoria - sconto percentuale","field":"MeccanicaComplesse$M035","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sc val - Set semplice","field":"MeccanicaComplesse$M131","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono S')
    || TO_CLOB('conto Categoria - PF - Set semplice","field":"MeccanicaComplesse$M134","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Buono Sconto Categoria - sc % - Set semplice","field":"MeccanicaComplesse$M135","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Semplice per Triplica Oggetti","fie')
    || TO_CLOB('ld":"MeccanicaComplesse$M165","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}

        ]} ,

        {
        "headerName": "Meccaniche Complesse",
        "openByDefault": true,
        "children": [

        {"headerName":"SSc + Facile Set","field":"MeccanicaComplesse$M114","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":')
    || TO_CLOB('["TM1DataColumnText"]},
        {"headerName":"Oggetti Set","field":"MeccanicaComplesse$M115","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto SET a valore","field":"MeccanicaComplesse$M201","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto SET %","field":"MeccanicaCompless')
    || TO_CLOB('e$M205","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"PAGINA PUBBLICITARIA","field":"MeccanicaComplesse$M000","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Meccanica NO Promo con Spazio","field":"MeccanicaComplesse$M090","width":170,"hide":false,"rowGroup": false , "editable": true')
    || TO_CLOB(', "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Set Esteso Valore","field":"MeccanicaComplesse$M301","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Set Esteso - prezzo corto","field":"MeccanicaComplesse$M303","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},')
    || TO_CLOB('
        {"headerName":"Set Esteso Punti Fragola","field":"MeccanicaComplesse$M304","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]},
        {"headerName":"Sconto Percentuale Set Esteso","field":"MeccanicaComplesse$M305","width":170,"hide":false,"rowGroup": false , "editable": true, "cellClass": "cellClass-center-text","type":["TM1DataColumnText"]}

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
    || TO_CLOB('               "Attributes",
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
                                   "cellRende')
    || TO_CLOB('rerParams":{ "suppressCount": true }
                                 , "field": "Promozione.Descrizione_plus_Data"
                                 , "headerName": "Promozione"
                                 , "width":300
                                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
              {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "')
    || TO_CLOB('editable": false,"type":["TM1Element"]},
              {"headerName":"Canale","field":"Promozione.Canale","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
              {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":150,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Descrizione","field":"Promozione.Descrizione","width":70,"hide":true,"rowGroup": false , "editable"')
    || TO_CLOB(': false,"type":["TM1Element"]},
              {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
              {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":85,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
              {"headerName":"Operazione","field":"MisuraPianoOperativoCommerciale$Operazione","width":90,"hide":false,"rowGroup": false , "editab')
    || TO_CLOB('le": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Descrizione","field":"MisuraPianoOperativoCommerciale$Descrizione","width":85,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Descrizione.","field":"MisuraPianoOperativoCommerciale$Desc_Agg","width":90,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText" ]},
              {"headerName":"Data Inizio","field":"MisuraPianoOperativoCo')
    || TO_CLOB('mmerciale$DataInizio","width":85,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio.","field":"MisuraPianoOperativoCommerciale$DataInizio_Agg","width":85,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":8')
    || TO_CLOB('5,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine.","field":"MisuraPianoOperativoCommerciale$DataFine_Agg","width":85,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist.","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":100,"hide":false,')
    || TO_CLOB('"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$DataInizioIst_Agg","width":100,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist.","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":100,"hide":false,"rowGrou')
    || TO_CLOB('p": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$DataFineIst_Agg","width":100,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":150,"hide":false,"rowGroup": false , "e')
    || TO_CLOB('ditable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Dt Abbatt.Prezzi_","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ_AGG","width":150,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
              {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":100,"hide":false,"rowGroup": false , "editable": true,')
    || TO_CLOB(' "type":[ "TM1DataColumnText"]},
              {"headerName":"Ordine Timone.","field":"MisuraPianoOperativoCommerciale$Ordinamento_Agg","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa %_","field":"MisuraPianoOpera')
    || TO_CLOB('tivoCommerciale$ETICH_SENZA__perc__AGG","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoCommerciale$NO_STMP_ETICH","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"CanalePromozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type"')
    || TO_CLOB(':[ "TM1DataColumnText"]},
              {"headerName":"StatoPromozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"TipoPromozione","field":"MisuraPianoOperativoCommerciale$TipoPromozione","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note","field":"MisuraPianoOperativoCommerciale$Note')
    || TO_CLOB('","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
              {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":100,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}

            ],
            "actions": [{
            "componentId": "btnAggiornaPromoId",
            "componentLabel": "Aggiorna Promozione",
            "process": "MUI_DUMMY_DIM.Promozione.UpdatePro')
    || TO_CLOB('mo (I)",
            "timeout":-1,
            "refresh": ["gc_ModificaPromozione"],
            "params":[{
              "dimension": "Promozione",
              "attribute": "Anno",
              "paramName": "inAnno",
              "label": "Anno",
              "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
              "label": "Promozione",
           ')
    || TO_CLOB('   "hasPicklist": true
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
                        "Attributes",
                        "UniqueName"
                              ] ,
   ')
    || TO_CLOB('                   "Cells": [
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
              "headerconf" : ["MUI_DescrizioneData" , "Descrizione" , "Descrizione" , "Descrizione"]  ,
    ')
    || TO_CLOB('          "headerdefaults":  {"marryChildren" : true}  ,
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
                "headerClass": "hea')
    || TO_CLOB('derRep",
                "openByDefault": true
              }
            },
              "childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnText"] },
              "childrenCustomTypes"  : {
                                   "DataInizio":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"},
                             ')
    || TO_CLOB('      "DataFine":{"cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"] ,"aggFunc": "","columnGroupShow":"always"}
                               }
              } ,

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Negozio.Descrizione"
                                 , "headerName": "Negozio"
                                 , "width":300
                ')
    || TO_CLOB('                 , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_S')
    || TO_CLOB('emestre","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":200,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
          {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":150,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Negozio","field":"Negozio.Descrizione","width":150,"h')
    || TO_CLOB('ide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]}



              ]
            ,
			"rowSuppressionEnabled": true,
			"colSuppressionEnabled": false
		}
	]
}'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('29','/Piano_Annuale/Timing',TO_CLOB(' {
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
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo P')
    || TO_CLOB('romozione],BASC)}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "MisuraPromozioneProprietà": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Promozione Proprietà] )}, 0)}, ASC)}}"
          }
        },
        "FROM": "[Configurazione Promozione - Proprietà]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
 ')
    || TO_CLOB('         "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

        "autoGroupColumnDef" :  {
                                   "cellRendererParams":{ "suppressCount": true }
                                 , "field": "Promozione.Descrizione_plus_Data"
                                 , "headerName": "Promozione"
                                 , "width":300
                    ')
    || TO_CLOB('             , "pinned": "left"
                                 , "type":["TM1Element"]
                          } ,
        "columnDefs": [
          {"headerName":"Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Semestre","field":"Promozione.MUI_Semest')
    || TO_CLOB('re","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
          {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":100,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Data Inizio Las","field":"MisuraPromozionePropriet_ag')
    || TO_CLOB('rave_$Data_Inizio_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Las","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Las","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_Freschi","width":100,"hide":false,"r')
    || TO_CLOB('owGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Freschi","field":"MisuraPromozionePropriet_agrave_$Data_Fine_Freschi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_inizio_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"')
    || TO_CLOB('cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine DRO/GEM","field":"MisuraPromozionePropriet_agrave_$Data_fine_DRO_slash_GEM","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Raccolta Proiezioni","field":"MisuraPromozionePropriet_agrave_$1_pubblicazione","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1D')
    || TO_CLOB('ataColumnDate"]},
          {"headerName":"Data Pre-Presentazione CS","field":"MisuraPromozionePropriet_agrave_$data_riunione_commerciale","width":150,"hide":false,"rowGroup": false , "editable": false,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Scadenza Conferma Prezzi","field":"MisuraPromozionePropriet_agrave_$data_scadenza_conferma_prezzi","width":150,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataCo')
    || TO_CLOB('lumnDate"]},
          {"headerName":"Pianificazione TM1/GPR","field":"MisuraPromozionePropriet_agrave_$resp_mktg1","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Volantino/Piano Media","field":"MisuraPromozionePropriet_agrave_$resp_mktg2","width":120,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Data Inizio","field":"MisuraPromozionePropriet_agrave_$DataInizio","')
    || TO_CLOB('width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine","field":"MisuraPromozionePropriet_agrave_$DataFine","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Inizio Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Inizio_ist_dot_","width":150,"hide":false,"rowGroup": false , "editable"')
    || TO_CLOB(': true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data Fine Istituzionale","field":"MisuraPromozionePropriet_agrave_$Data_Fine_ist_dot_","width":150,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
          {"headerName":"Data abb. prezzi","field":"MisuraPromozionePropriet_agrave_$Data_abb_prezzi","width":100,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[')
    || TO_CLOB(' "TM1DataColumnDate"]},
          {"headerName":"Valore Punto Fragola","field":"MisuraPromozionePropriet_agrave_$ValorePuntoFragola","width":150,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnDecimal3"]}

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
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozio')
    || TO_CLOB('ne],BASC)}"
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          ')
    || TO_CLOB('"Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      } ,
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.')
    || TO_CLOB('Anno","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true')
    || TO_CLOB(',"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":100,"hide":false,"rowGroup": f')
    || TO_CLOB('alse , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":100,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true,  "cell')
    || TO_CLOB('Class": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":110,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":110,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", ')
    || TO_CLOB('"type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":90,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoCo')
    || TO_CLOB('mmerciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":140,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
')
    || TO_CLOB('        {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
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
          "Attributes",
          "UniqueName"
        ] ,')
    || TO_CLOB('
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
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName": "Promozione"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"header')
    || TO_CLOB('Name":"Canale","field":"Promozione.Canale","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":40')
    || TO_CLOB('0,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":400,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Zona BDG","field":"ZonaPromo$ZONAPROMO_ALL","width":90,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO","field":"ZonaPromo$ZONAPROMO_1_')
    || TO_CLOB('01","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_02] EMILIA","field":"ZonaPromo$ZONAPROMO_1_02","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"ZonaPromo$ZONAPROMO_1_03","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
      ')
    || TO_CLOB('  {"headerName":"[1_04] VERONA","field":"ZonaPromo$ZONAPROMO_1_04","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"ZonaPromo$ZONAPROMO_2_01","width":125,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_02] LAZIO","field":"ZonaPromo$ZONAPROMO_2_02","width":125,"hide":false,"rowGroup": false , "editable": tr')
    || TO_CLOB('ue,"aggFunc":"sum", "type":["TM1DataColumnInteger"]}

      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('32','/Piano_Annuale/Sezioni_Tematiche/Sezioni_Tematiche',TO_CLOB(' {
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
    || TO_CLOB('            "Pubblicità": "{[Pubblicità].[(I) Volantino]}"
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
          "Attributes",
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

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Pubblicit_agrave_.Descrizione"
      , "headerName": ""
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"')
    || TO_CLOB('Canale","field":"Promozione.Canale","width":60,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Anno","field":"Promozione.Anno","width":50,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","')
    || TO_CLOB('width":100,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione Sezione Tematica","field":"MisuraZonaPromo$Descrizione","width":170,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Canale","field":"MisuraZonaPromo$Canale","width":70,"')
    || TO_CLOB('hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"Agg_canale","field":"MisuraZonaPromo$Agg_canale","width":90,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
        {"headerName":"DataInizio","field":"MisuraZonaPromo$DataInizio","width":80,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"DataFine","field":"MisuraZonaPromo$Dat')
    || TO_CLOB('aFine","width":70,"hide":false,"rowGroup": false , "editable": true,"cellClass": "dateFormat", "type":[ "TM1DataColumnDate"]},
        {"headerName":"Zona BDG","field":"MisuraZonaPromo$ZONAPROMO_ALL","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_01] MILANO","field":"MisuraZonaPromo$ZONAPROMO_1_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger')
    || TO_CLOB('"]},
        {"headerName":"[1_02] EMILIA","field":"MisuraZonaPromo$ZONAPROMO_1_02","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_03] PIEMONTE","field":"MisuraZonaPromo$ZONAPROMO_1_03","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[1_04] VERONA","field":"MisuraZonaPromo$ZONAPROMO_1_04","width":70,"hide":false,"rowG')
    || TO_CLOB('roup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_01] FIRENZE","field":"MisuraZonaPromo$ZONAPROMO_2_01","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"[2_02] LAZIO","field":"MisuraZonaPromo$ZONAPROMO_2_02","width":70,"hide":false,"rowGroup": false , "editable": true,"aggFunc":"sum", "type":["TM1DataColumnInteger"]},
        {"headerName":"Check","f')
    || TO_CLOB('ield":"MisuraZonaPromo$Check","width":70,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]}

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
                        "Attributes",
						"UniqueName"
       ')
    || TO_CLOB('                       ] ,
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
          {"headerName":"ID Sezione Tematica","field":"IDSezioneTematica.Name","width":1')
    || TO_CLOB('00,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]},
          {"headerName":"Descrizione","field":"MisuraConfigurazioneSezioneTematica$Descrizione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Tipo","field":"MisuraConfigurazioneSezioneTematica$TIPO","width":100,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Note","field":"MisuraConfigura')
    || TO_CLOB('zioneSezioneTematica$Note","width":170,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]},
          {"headerName":"Operazione","field":"MisuraConfigurazioneSezioneTematica$Operazione","width":400,"hide":false,"rowGroup": false , "editable": true,"type":["TM1DataColumnText"]}



              ],
          "actions": [{
            "componentId": "btnAggiornaSezioniTematicheId",
            "componentLabel": "Aggiorna Anagrafica Sezioni Tematiche",
          ')
    || TO_CLOB('  "process": "MUI_DUMMY_DIM.Sezione Tematica",
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
      "rowSuppressionEnabled": true,
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
    || TO_CLOB(' "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Misura_Configurazione_Macrospazi_Listino": "{[Misura_Configurazione_Macrospazi_Listino].[CC],[Misura_Configurazione_Macrospazi_Listino].[EC]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Listino - Promo]"

      },
 ')
    || TO_CLOB('     "ExecuteMDX": {
        "Members": [
          "Name",
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
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["MUI_DescrizioneData" , "Descrizione" ]  ,
        "headerdefaults":  {"marryChildren" : t')
    || TO_CLOB('rue}  ,
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
        "childrendefaults":  {"widt')
    || TO_CLOB('h":260,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"closed",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Promozione.Descrizione_plus_Data"
      , "headerName":  "Promozione"
     ')
    || TO_CLOB(' , "width":400
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Descrizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}
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
      "colSuppressionEnabled": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {

            "Negozio": "{EXCEPT({{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Negozio] )}, 0)}, ASC)}},{[Negozio].[NA] })}"
          }
        },')
    || TO_CLOB('
        "COLS": {
          "NON_EMPTY": true ,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "MacroSpazio": "{[MacroSpazio].[MacroSpazio_liv0]}" ,
            "Misura_Configurazione_Macrospazi_Neg_Promo": "{[Misura_Configurazione_Macrospazi_Neg_Promo].[DEFAULT],[Misura_Configuraz')
    || TO_CLOB('ione_Macrospazi_Neg_Promo].[SPAZIO]}"
          }
        },
        "FROM": "[Configurazione Macrospazio - Negozio - Promo]"

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
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
      },

      "DynamicColumns" : true ,
      "')
    || TO_CLOB('DynamicColumnsSettings" :{
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
          "TGT_MKT":{
            "headerClass": "headerMkt",
            "openByDefault": true
          },
  ')
    || TO_CLOB('        "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"type":[ "TM1DataColumnText"] ,"aggFunc": "","columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" ')
    || TO_CLOB(':  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Negozio.Descrizione"
      , "headerName": "Negozio"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [

        {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":100,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Negozio","field":"Negozio.Descrizione","width":100,"hide":true,"rowGroup": ')
    || TO_CLOB('false , "editable": false,"type":["TM1Element"]}
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
          "Consolidated" ,
  ')
    || TO_CLOB('        "HasPicklist",
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
            "headerClass": "headerAcq",
            "openByDefault": true
          },
          "TGT_MKT":{
')
    || TO_CLOB('
            "headerClass": "headerMkt",
            "openByDefault": true
          },
          "TGT_REP":{
            "headerClass": "headerRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"closed",  "editable": true, "type":[ "TM1DataColumnText"] },
        "childrenCustomTypes"  : {
          "VALIDO_DAL":{"columnGroupShow":"always"},
          "DEFAULT":{"')
    || TO_CLOB('type":[ "TM1DataColumnInteger" ,"numericColumn"]}
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
        {"headerName":"Tot. Negozio","field":"Negozio.MUI_TOT","width":200,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
  ')
    || TO_CLOB('      {"headerName":"Zona Promo","field":"Negozio.MUI_ZonaPromo","width":200,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
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
      },

      "DynamicColumns" : true ,
      "DynamicColum')
    || TO_CLOB('nsSettings" :{
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
            "headerClass": "he')
    || TO_CLOB('aderRep",
            "openByDefault": true
          }
        },
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "NOTE":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GG_COM_DEFAULT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GRP_SPZ":{"type":[ "TM1DataColumnText"')
    || TO_CLOB('] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Descrizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}

      ]

    },
    {
      "name": "gc_Spazi_MacroSpazi_Listino",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": fals')
    || TO_CLOB('e,
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
        "FROM": "[Configurazione Macrospazio - Listino]"

      },
      "ExecuteMDX": {
     ')
    || TO_CLOB('   "Members": [
          "Name",
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
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "R')
    || TO_CLOB('IF_MKT_DT":{
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
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroup')
    || TO_CLOB('Show":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "TIPO_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_EXTRA_CONTR_SP":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "DATA_CAMBIO":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "TIPO')
    || TO_CLOB('_EXTRA_CONTR_SP_FUT":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Descrizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}
      ]

    },
    {
      "name": "gc_Spazi_MacroSpazi_Aggiorna",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "MDX": ')
    || TO_CLOB('{
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
        "FROM": "[Configurazione Macrospazio]"

      },
      "ExecuteMDX": {
        "Members": [
  ')
    || TO_CLOB('        "Name",
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
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["Descrizione"]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "op')
    || TO_CLOB('enByDefault": true
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
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false , "columnGroupShow":"always",  "editable": ')
    || TO_CLOB('true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
          "GG_COM_NEW":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "OPERAZIONE":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"},
          "GRP_SPZ":{"type":[ "TM1DataColumnText"] ,"columnGroupShow":"always"}
        }
      } ,

      "autoGroupColumnDef" :  {
      } ,
      "columnDefs": [
        {"headerName":"Macro Spazio","field":"MacroSpazio.Desc')
    || TO_CLOB('rizione_plus_Codice","width":200,"hide":false,"rowGroup": false , "editable": false,"type":["TM1Element"]}

      ],
      "actions": [{
            "componentId": "btnCreaMacrospazioId",
            "componentLabel": "Creazione Macrospazio",
            "process": "MUI_DUMMY_DIM.MacroSpazioInsertElement",
            "timeout":-1,
            "refresh": ["gc_Spazi_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
            "params":[{
              "paramNam')
    || TO_CLOB('e": "inCodice",
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
              "label": "Desrizione Timone",
              "hasPicklist": false
            },
            {
              "paramName": "inGruppo",
            ')
    || TO_CLOB('  "label": "Gruppo Macrospazi",
              "defaultValue": "Spazi Campagna",
              "hasPicklist": false
            }]
          },
          {
            "componentId": "btnAggiornaMacrospazioId",
            "componentLabel": "Aggiorna Macrospazio",
            "process": "MUI_DUMMY_CUB.ConfigurazioneMacrospazi_Aggiornamento",
            "timeout":-1,
            "refresh": ["gc_Spazi_MacroSpazi","gc_Spazi_MacroSpazi_Listino","gc_Spazi_MacroSpazi_Aggiorna"],
           ')
    || TO_CLOB(' "params":[{
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('38','/Piano_Annuale/Spazi/Spazi_Condivisi',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Spazi_SpaziCondivisi",
      "logMemory": true,
      "logTime": true,
      "skip": true,
      "rowSuppressionEnabled": true,
      "colSuppressionEnabled": false,
      "maxCells":2000000,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Spazio": "{{TM1SORTBYINDEX( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 0)}, ASC)}}"
          }
        },
   ')
    || TO_CLOB('     "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Compratore": "{ [Compratore].[(I) Compratori (tot)] }"
          }
        },
        "FROM": "[Configurazione RaggrSpazi]" ,
        "WHERE": {
          "Misura Config RaggrFoto": "[OK]')
    || TO_CLOB('"
        }

      },
      "ExecuteMDX": {
        "Members": [
          "Name",
          "UniqueName" ,
          "Attributes/Canale",
          "Attributes/Anno",
          "Attributes/MUI_Semestre" ,
          "Attributes/Descrizione" ,
          "Attributes/Compratore" ,
          "Attributes/MacroSpazioDescrizione" ,
          "Attributes/MUI_TOT",
          "Attributes/MUI_DescrizioneData"
        ] ,
        "Cells": [
          "Ordinal",
          "Value",
       ')
    || TO_CLOB('   "Updateable",
          "Consolidated" ,
          "HasPicklist",
          "PicklistValues"
        ]
      },

      "DynamicColumns" : true ,
      "DynamicColumnsSettings" :{
        "headerconf" : ["MUI_DescrizioneData","Descrizione"]  ,
        "headerdefaults":  {"marryChildren" : true}  ,
        "headerCustomTypes":{
          "RIF_MKT_DT":{
            "openByDefault": true
          },
          "TGT_ACQ":{
            "headerClass": "headerAcq",
            "openB')
    || TO_CLOB('yDefault": true
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
        "childrendefaults":  {"width":150,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
        "childrenCustomTypes"  : {
   ')
    || TO_CLOB('     }
      } ,
      "autoGroupColumnDef" :  {
        "cellRendererParams":{ "suppressCount": true }
      , "field": "Spazio.Descrizione"
      , "headerName": "Spazio"
      , "width":300
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Totale","field":"Spazio.MUI_TOT","width":200,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]}  ,
        {"headerName":"Compratore","field":"Spazio.Compratore","wi')
    || TO_CLOB('dth":200,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]}  ,
        {"headerName":"Macro Spazio","field":"Spazio.MacroSpazioDescrizione","width":200,"hide":true,"rowGroup": true, "editable": false,"type":["TM1Element"]}

      ]
    }
  ]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('39','/Reporting/Copia_in_pianificazione',TO_CLOB(' {
	"connection": "reporting",
	"configurations": [
		{
			"name": "gc_CopiaInPianificazione",
			"logMemory": true,
			"logTime": true,
			"skip": true,
             "maxCells":1000000 ,
             "REP - Fornitore": "{[REP - Fornitore].[Fornitori]}" ,
            "REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
            "REP - Zona Promo": "{[REP - Zona Promo].[Zona Promo -BDGVend]}",
            "REP - Sezione Tematica": ')
    || TO_CLOB('"{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [R')
    || TO_CLOB('EP - Scenario] )}, 0)} , ASC)}",
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                       	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
           ')
    || TO_CLOB('             "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS')
    || TO_CLOB('": {
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

			"DynamicColu')
    || TO_CLOB('mns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "Descrizione" ]  ,
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
		')
    || TO_CLOB('		},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericColumn"] },
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
')
    || TO_CLOB('						"TM1Element"
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
				},
               {
					"headerName": "Promozione",
					"field": "REP_minus_Promozione.Descrizione_plus_Data",
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
					"headerName": "Fornitori",
					"field": "REP_minus_Fornitore.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
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
		')
    || TO_CLOB('			"headerName": "Meccanica",
					"field": "REP_minus_MeccanicaSemplice.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": true,
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
					"headerName": ')
    || TO_CLOB('"A Volantino",
					"field": "REP_minus_AVolantino.Descrizione",
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
			"colSuppressionEnabled": false,
			"actions": [{
				"componentId": "btnCopiaPezziColliId",
				"componentLabel": "Copia pezzi e colli",
				"process": "MUI_DUMMY",
				"timeout":-1,
				"refresh": ["gc_CopiaInPianificazione"],
				"para')
    || TO_CLOB('ms":[{
					"label": "Confermi esecuzione?",
					"hasPicklist": false,
					"disabled": true
				}]
			},
				{
					"componentId": "btnCopiaEstesaId",
					"componentLabel": "Copia estesa",
					"process": "MUI_DUMMY",
					"timeout":-1,
					"refresh": ["gc_CopiaInPianificazione"],
					"params":[{
						"label": "Confermi esecuzione?",
						"hasPicklist": false,
						"disabled": true
					}]
				}]
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
            "REP - Sezione Tematica": "{[R')
    || TO_CLOB('EP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP')
    || TO_CLOB(' - Compratore] )}, 0)} , ASC)}",
						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}" ,
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Mecc')
    || TO_CLOB('anica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}"

					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      	"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                         "REP - Scenario": "{ [REP - Scenario].[Scenario -Storico]}",
						"REP - Misura')
    || TO_CLOB(' Reporting Articolo": "{[REP - Misura Reporting Articolo].[Reporting ACQ]}"
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
		')
    || TO_CLOB('		"headerconf" : [ "MUI_DescrizioneData" , "Descrizione" ,  "Descrizione" ]  ,
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
		')
    || TO_CLOB('		"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
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
				"type": [
					"TM1Element"
				]
			},
')
    || TO_CLOB('			"columnDefs": [
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
					"type": [
						"TM1Element"
					]
				} ,
')
    || TO_CLOB('              {
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
               {')
    || TO_CLOB('
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
				} ,
				{
					"headerName": "Totale Ar')
    || TO_CLOB('ticolo",
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
					"headerName": "Fornitori",
					"')
    || TO_CLOB('field": "REP_minus_Fornitore.Descrizione",
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
					"headerName": "Meccanica Semplice')
    || TO_CLOB('",
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




')
    || TO_CLOB('
			] ,
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false
		}
	]
}
'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('41','/Reporting/Dettaglio_Zona',TO_CLOB( '{
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
            "REP - Sezione Tematica": "{[REP - S')
|| TO_CLOB('ezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          			    "REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( ')
|| TO_CLOB('[REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBY')
|| TO_CLOB('LEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                      "REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
                       "REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP - Scenario].[')
|| TO_CLOB('RIF_MKT_DT]}",
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
					"Attributes",
"UniqueName"
				],
				"Cells": [
					"Ordina')
|| TO_CLOB('l",
					"Value",
					"Updateable",
					"Consolidated",
					"HasPicklist",
					"PicklistValues"
				]
			},

			"DynamicColumns" : true ,
			"DynamicColumnsSettings" :{
				"headerconf" : [ "MUI_DescrizioneData" ,"Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerClass": "headerAcq",
						"openByDefault": true
')
|| TO_CLOB('					},
					"TGT_MKT":{
						"headerClass": "headerMkt",
						"openByDefault": true
					},
					"TGT_REP":{
						"headerClass": "headerRep",
						"openByDefault": true
					}
				},
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
			} ,

			"autoGroupColumnDef": {
				"cellRendererParams": {
					"su')
|| TO_CLOB('ppressCount": true
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
					"heade')
|| TO_CLOB('rName": "Reparto",
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
					"headerName": "G')
|| TO_CLOB('RM",
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
					"headerName": "Articolo",
					"field": "REP_minus_Ar')
|| TO_CLOB('ticolo.DescrizioneArticolo",
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
					"field": "REP_minus_Fornitore.Descrizi')
|| TO_CLOB('one",
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
					"field": "REP_minus_Meccanic')
|| TO_CLOB('aSemplice.Descrizione",
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
			"colSuppressionEnabled": false
		}
')
|| TO_CLOB('	]
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
					"TG')
    || TO_CLOB('T_ACQ":{
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
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
				}
	')
    || TO_CLOB('		} ,

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
						"')
    || TO_CLOB('TM1Element"
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
    || TO_CLOB('				]
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
					"h')
    || TO_CLOB('eaderName": "Categoria",
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
    || TO_CLOB('
			"colSuppressionEnabled": false
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
            "REP - Sezione Tematic')
    || TO_CLOB('a": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Promozione": "{TM1SORT(  {TM1FILTERBY')
    || TO_CLOB('LEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{T')
    || TO_CLOB('M1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[REP')
    || TO_CLOB(' - Scenario].[RIF_MKT_DT]}",
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
')
    || TO_CLOB('
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
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
			')
    || TO_CLOB('		"TGT_ACQ":{
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
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
			')
    || TO_CLOB('	}
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
					"field": "REP_minus_Promozione.Descrizione_plus_Data",
					"width": 70,
					"hide": false,
					"rowGroup": false')
    || TO_CLOB(',
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
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
		')
    || TO_CLOB('			"type": [
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
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
	')
    || TO_CLOB('					"TM1Element"
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
            "REP - Sezione Temat')
    || TO_CLOB('ica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
                        "REP - Promozione": "{TM1SORT(  {TM1FILTER')
    || TO_CLOB('BYLEVEL( {TM1SUBSETALL( [REP - Promozione] )}, 0)} , ASC)}",
          				"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "')
    || TO_CLOB('{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Scenario": "{[REP - Scenario].[RIF_MKT],[REP - Scenario].[BDG],[R')
    || TO_CLOB('EP - Scenario].[RIF_MKT_DT]}",
                        "REP - Zona Promo": "{{[REP - Zona Promo].[TOT_ZONA_PROMO],[REP - Zona Promo].[TOT_ZONE] },{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}}",
						"REP - Misura Reporting Articolo Zona": "{[REP - Misura Reporting Articolo Zona].[Misura Reporting Articolo Zona -Storico]}"
					}
				},
				"FROM": "[Reporting Articolo Zona]",
				"WHERE": {
					"REP - Versione": "[UFF]"
				}
			},
			"ExecuteMDX": ')
    || TO_CLOB('{
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
				"headerconf" : ["Descrizione" , "Descrizione" , "Descrizione" ]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
	')
    || TO_CLOB('				"TGT_ACQ":{
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
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnNumber" ,"numericColumn"] },
				"childrenCustomTypes"  : {
	')
    || TO_CLOB('			}
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
					"field": "REP_minus_Promozione.Descrizione_plus_Data",
					"width": 70,
					"hide": false,
					"rowGroup": fal')
    || TO_CLOB('se,
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
					"headerName": "Articolo",
					"field": "REP_minus_Articolo.DescrizioneArticolo",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
')
    || TO_CLOB('					"type": [
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
					"headerName": "Sezione Tematica",
					"field": "REP_minus_SezioneTematica.Descrizione",
					"width": 70,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
')
    || TO_CLOB('
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
                   ')
    || TO_CLOB('
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
				"Members":')
    || TO_CLOB(' [
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
				"headerconf" : [ "Descrizione" , "Descrizione"]  ,
				"headerdefaults":  {"marryChildren" : true}  ,
				"headerCustomTypes":{
					"RIF_MKT_DT":{
						"openByDefault": true
					},
					"TGT_ACQ":{
						"headerCla')
    || TO_CLOB('ss": "headerAcq",
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

			"autoGroupCol')
    || TO_CLOB('umnDef": {
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
					"type": [
						"TM1Element"
					]
				},
       ')
    || TO_CLOB('       {
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
					]
				},
				{
					"headerName": "Reparto",
				')
    || TO_CLOB('	"field": "REP_minus_Reparto.Descrizione",
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('46','/Analisi_Budget-Venduto/Articolo',TO_CLOB(' {
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
            "REP - Sezione Tem')
    || TO_CLOB('atica": "{[REP - Sezione Tematica].[Zona Promo -BDGVend]}",
            "REP - Meccanica Semplice": "{[REP - Meccanica Semplice].[Meccanica Semplice -BDGVend]}",
            "REP - AVolantino": "{[REP - AVolantino].[AVolantino -BDGVend]}" ,
            "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"   ,

			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
          						"REP - Fornitore": "{TM1SORT(  {TM1FILTERBYLEVEL')
    || TO_CLOB('( {TM1SUBSETALL( [REP - Fornitore] )}, 0)} , ASC)}" ,
						"REP - Compratore": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Compratore] )}, 0)} , ASC)}",
                        "REP - Zona Promo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)} , ASC)}",
                        "REP - Sezione Tematica": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)} , ASC)}",
                        "REP - Meccanica Semplice": "{TM1SORT(  {TM')
    || TO_CLOB('1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Meccanica Semplice] )}, 0)} , ASC)}",
                        "REP - AVolantino": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)} , ASC)}" ,
                        "REP - Articolo": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 0)} , ASC)}"
					}
				},
				"COLS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"REP - Promozione": "{TM1SORT(  {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Promozione] )')
    || TO_CLOB('}, 0)} , ASC)}",
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
					"Ordi')
    || TO_CLOB('nal",
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
					')
    || TO_CLOB('"TGT_MKT":{
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
					"suppressCount":')
    || TO_CLOB(' true
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
		')
    || TO_CLOB('			"field": "REP_minus_Articolo.RepartoDesc",
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
					"field": "R')
    || TO_CLOB('EP_minus_Articolo.GRMDesc",
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
					"field": "REP_minus_Articolo.DescrizioneAr')
    || TO_CLOB('ticolo",
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
				')
    || TO_CLOB('	"width": 70,
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
					"w')
    || TO_CLOB('idth": 70,
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
					"width"')
    || TO_CLOB(': 70,
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

'),'GRID');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('47','/Analisi_Budget-Venduto/Categoria',TO_CLOB(' {
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
	')
    || TO_CLOB('				}
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
					"REP - Versione": "[UFF')
    || TO_CLOB(']"
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
')
    || TO_CLOB('
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
				"childrendefaults":  {"width":110,"hide":false,"rowGroup": false ,"aggFunc": "sum", "columnGroupShow":"always",  "editable": true, "type":[ "TM1DataColumnInteger" ,"numericCo')
    || TO_CLOB('lumn"] },
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
					"row')
    || TO_CLOB('Group": false,
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
')
    || TO_CLOB('
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
					')
    || TO_CLOB('"type": [
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
						"TM1Eleme')
    || TO_CLOB('nt"
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
    "rowSuppressionEnabled": true,
    "colSuppressionEnabled": true,
    "MDX": {
      "ROWS": {
        "NON_EMPTY": true,
        "DIMENSIONS": {
          "Categoria": "{{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{ {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}}} , {[Categoria].[CAT_0000]}) }}",
          "Comprat')
    || TO_CLOB('ore": "{{[Compratore].[S000]},{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]} )}}"
        }
      },
      "COLS": {
        "NON_EMPTY": true,
        "DIMENSIONS": {
          "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Promozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
          "Scenario": " {{[Scenario].[RIF_MKT_D')
    || TO_CLOB('T]},{[Scenario].[TGT_ACQ]},{[Scenario].[TGT_MKT]}}",
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
        "Attributes",
        "UniqueName"
      ],
      "C')
    || TO_CLOB('ells": [
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
      "width": 300,
      "pinned": "left",
      "type": ["TM1Element"]
    },
    "DynamicColumns": true,
    "DynamicColumnsSettings": {
      "headerco')
    || TO_CLOB('nf": ["MUI_DescrizioneData","Descrizione","Descrizione","Descrizione"],
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
          "headerClass": "head')
    || TO_CLOB('erRep",
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
        "type": ["TM1DataColumnInteger", "numericColumn"]
      },
      "childrenCustomTypes": {
        "VENDUTO_PROMO_NETTO": {
          "type": ["TM1DataColumnNumberK", "numericColumn"],
          "columnGroupShow": "always"
     ')
    || TO_CLOB('   },
        "MARGINE_LORDO__perc_": {
          "type": ["TM1DataColumnNumber", "numericColumn"],
          "columnGroupShow": "always"
        }
      }
    },






    "columnDefs": [
      {
        "headerName": "Totale Categoria",
        "field": "Categoria.MUI_TOT",
        "width": 90,
        "hide": true,
        "rowGroup": true,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Repar')
    || TO_CLOB('to",
        "field": "Categoria.MUI_RepartoDesc",
        "width": 90,
        "hide": true,
        "rowGroup": true,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Categoria",
        "field": "Categoria.Descrizione",
        "width": 90,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },{
        "headerName')
    || TO_CLOB('": "CategoryManager",
        "field": "Compratore.CategoryManager",
        "width": 70,
        "hide": true,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },
      {
        "headerName": "Compratore",
        "field": "Compratore.Descrizione",
        "width": 70,
        "hide": false,
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["TM1Element"]
      },')
    || TO_CLOB('
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
        "rowGroup": false,
        "pinned": "left",
        "editable": false,
        "type": ["T')
    || TO_CLOB('M1Element"]
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
      "row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
      "row_style_1": "data.Compratore.Name')
    || TO_CLOB('==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
      "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
      "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
      "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria')
    || TO_CLOB('.MUI_Level == 2"
    },
    "groupRowAggNodes": {
      "nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
      "nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && da')
    || TO_CLOB('ta.Compratore.Name != ''S000'' ))"
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
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}}",
					"Categoria": "{')
    || TO_CLOB('{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_ACQ]},{[Scenario].[TGT_MKT]}}",
					"Sezione Tematica": "{{TM1SUBSETALL( [Sezione Tematica] )}}",
					"Misura ')
    || TO_CLOB('Timone.": "{[Misura Timone.].[(II) Timone Categoria Sez]}"
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
			"headerconf": ["MUI_DescrizioneData","Descrizione","MUI_Descrizione","Descrizione"],
			"headerdefaults": {
				"marryChildren": true
			},
			"headerCustomTypes":{
				"RIF_MKT_DT":{
					"openByDefault": true
				},
				"TGT_ACQ":{
					"headerClass": "headerAcq",
					"openByDefault')
    || TO_CLOB('": true
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
				"ASSORT_P": {
					"type": ["TM1DataColu')
    || TO_CLOB('mnText"]
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
				"field": "Compratore.Descrizione",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
			')
    || TO_CLOB('	"editable": false,
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
				"rowGroup": false,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
')
    || TO_CLOB('
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
			"row_style_hidden": "(data.Compratore.Name == ''TOT_C')
    || TO_CLOB('OMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style')
    || TO_CLOB('_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Na')
    || TO_CLOB('me)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
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
		"rowSuppressionEnabled": false,
		"colSuppressionEnabled": true,
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{{TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}}} , {[Compratore].[S000]} )}  }" ,
					"Categoria": "{{[Categoria')
    || TO_CLOB('].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura T')
    || TO_CLOB('imone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[SPZ_CAMP]},{[Misura Timone.].[PGN_PIC]},{[Misura Timone.].[PGN_MED_A]},{[Misura Timone.].[PGN_MED_B]},{[Misura Timone.].[PGN_MGR]},{[Misura Timone.].[PGN_GRA]},{[Misura Timone.].[TST_PIC]},{[Misura Timone.]')
    || TO_CLOB('.[TST_MED]},{[Misura Timone.].[VSC_PIC]},{[Misura Timone.].[VSC_MED]},{[Misura Timone.].[VSC_GRA]},{[Misura Timone.].[CLN]},{[Misura Timone.].[TVL]},{[Misura Timone.].[N_ART_PROMO]}}"
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
				"Attributes",
"UniqueName"
			],
			"Cells": [
				"Ordinal",
				"Value",
				"Updateable",
				"Consolidated",
				"HasPicklist",
				"Pi')
    || TO_CLOB('cklistValues"
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
					"headerClass": "headerMkt",
					"openByDefault": true
				},
				"TG')
    || TO_CLOB('T_REP":{
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
				"TOT_FOTO":  {
					"columnGroupShow":"always"
				},
				"SPZ_CAM')
    || TO_CLOB('P":  {
					"columnGroupShow":"always"
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
			{"headerName":"CategoryManager","field":"Compratore.CategoryManager","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Comprato')
    || TO_CLOB('re","field":"Compratore.MUI_Descrizione","width":70,"hide":true,"rowGroup": true, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Cod Compratore","field":"Compratore.Name","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},
			{"headerName":"Livello Categoria","field":"Categoria.MUI_Level","width":70,"hide":true,"rowGroup": false, "pinned": "left", "editable": false,"type":["TM1Element"]},
			')
    || TO_CLOB('{"headerName":"Cod Categoria","field":"Categoria.Name","width":70,"hide":true,"rowGroup": false,  "editable": false,"type":["TM1Element"]}
		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1" : "dat')
    || TO_CLOB('a.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2" : "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3" : "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4" : "data.Compratore.Name==''TOT_COMP'' && dat')
    || TO_CLOB('a.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue" : "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse" : "(data.Compratore.Name == ''S000'' && (data.Categoria.Name  == ''_REP_01'' || data.Categoria.Name  == ''_REP_02'' || data.Categoria.Name  == ''_REP_03'' || data.Categoria.Name  == ''_REP_04'' || data.Categoria.Name  == ''_REP_05'' || data.Categoria.Name  == ''_REP_06'' || data.Categoria.Name  == ''_REP_09'' || data.Cat')
    || TO_CLOB('egoria.Name  == ''_REP_11'' || data.Categoria.Name  == ''_REP_12'' || data.Categoria.Name  == ''_REP_14'' || data.Categoria.Name  == ''_REP_50'') || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
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
		"colSuppressionEnabled": true,
      "MDX": {
        "ROWS": {
          "NON_EMPTY": true,
          "DIMENSIONS": {
            "Promozione": "{ ORDER( ORDER( {{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)},[Promozione].[Data_Inizio_Ist.], BDESC)}} , [Pro')
    || TO_CLOB('mozione].[Canale], BASC),[Promozione].[Tipo Promozione],BASC)}",
            "Reparto": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}"
          }
        },
        "COLS": {
          "NON_EMPTY": false,
          "DIMENSIONS": {
            "Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_REP]},{[Scenario].[TGT_ACQ]}}",
            "Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura T')
    || TO_CLOB('imone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Misura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[SPZ_CAMP]},{[Misura Timone.].[PGN_PIC]},{[Misura Timone.].[PGN_MED_A]},{[Misura Timone.].[PGN_MED_B]},{[Misura Timone.].[PGN_MGR]},{[Misura Timone.].[PGN_GRA]},{[Misura Timone.].[TST_PIC]},{[Misura Timone.].[TST_MED]},{[Misura Timone.].')
    || TO_CLOB('[VSC_PIC]},{[Misura Timone.].[VSC_MED]},{[Misura Timone.].[VSC_GRA]},{[Misura Timone.].[CLN]},{[Misura Timone.].[TVL]},{[Misura Timone.].[N_ART_PROMO]}}"
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
          "Attributes",
"UniqueName"
        ],
        "Cells": [
          "Ordinal",
          "Value",
          "Updateable",
   ')
    || TO_CLOB('       "Consolidated",
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
          "D')
    || TO_CLOB('escrizione",
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
          "TGT_REP":{
            "headerC')
    || TO_CLOB('lass": "headerRep",
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
            "columnGroupShow":"alway')
    || TO_CLOB('s"
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
        },
        {
')
    || TO_CLOB('          "headerName": "Descrizione",
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
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
         ')
    || TO_CLOB(' "type": [
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
          "rowGroup')
    || TO_CLOB('": false,
          "editable": false,
          "type": [
            "TM1Element"
          ]
        }
      ],
      "rowClassRules": {
        "row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
        "row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].in')
    || TO_CLOB('cludes(data.Categoria.Name)",
        "row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
        "row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
      },
      "groupRowAggNodes": {
        "nodeGroupTrue": "false",
        "nodeGroupFalse": "data.Reparto.TipoElemento == ''R''"
      },
      "actions": [{
            "componentId": "btnInizializzaSpazioId",
            "componentLabel": "Inizializza Spazio",
          ')
    || TO_CLOB('  "process": "MUI_DUMMY_CUB.Timone Reparto Inizializza Spazi",
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
              "attribute": "Descrizione",
              "paramName"')
    || TO_CLOB(': "inPromo",
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
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]} )}}",
					"Categoria": "{{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( ')
    || TO_CLOB('{TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[Mi')
    || TO_CLOB('sura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]}}"
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
				"Attributes",
"UniqueName"
			],
			"Cells": [
			')
    || TO_CLOB('	"Ordinal",
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
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults"')
    || TO_CLOB(': {
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
				"rowGroup": false,
				"aggFunc": "sum",
')
    || TO_CLOB('				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
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
				"headerName": "CategoryManager",
				"field')
    || TO_CLOB('": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.Descrizione",
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
				"hide')
    || TO_CLOB('": true,
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
				"editable": false,
')
    || TO_CLOB('
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_REP')
    || TO_CLOB('_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_0')
    || TO_CLOB('2'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
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
		"MDX": {
			"ROWS": {
				"NON_EMPTY": true,
				"DIMENSIONS": {
					"Compratore": "{{[Compratore].[S000]},{[Compratore].[TOT_COMP]},{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Compratore] )}, 0)}, ASC)}} , {[Compratore].[S000]} )}}",
					"Categoria": "{{[Categoria].[MUI_TargetCategoria]}  ,{EXCEPT( {{TM1SORT( {TM1FILTERBYLEVEL(')
    || TO_CLOB(' {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}} , {[Categoria].[CAT_0000]}) }}"
				}
			},
			"COLS": {
				"NON_EMPTY": false,
				"DIMENSIONS": {
					"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}}",
					"Scenario": " {{[Scenario].[RIF_MKT_DT]},{[Scenario].[TGT_MKT]},{[Scenario].[TGT_ACQ]}}",
					"Misura Timone.": "{{[Misura Timone.].[N_ART_PROMO]},{[Misura Timone.].[TOT_FOTO]},{[Misura Timone.].[N_FOTO]},{[Misura Timone.].[N_FOTO_SCAFFALE]},{[M')
    || TO_CLOB('isura Timone.].[N_FOTO_SPEC]},{[Misura Timone.].[N_FOTO_SCAFFALE_SPEC]},{[Misura Timone.].[N_FOTO_ULT]},{[Misura Timone.].[VENDUTO_PROMO_NETTO]},{[Misura Timone.].[MARGINE_LORDO__perc_]},{[Misura Timone.].[D_FOTO_slash_TGT_REP]},{[Misura Timone.].[D_FOTO_SCAFF_slash_TGT_REP]}}"
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
				"Attributes",
"UniqueName"
			],
			"Cells": [
		')
    || TO_CLOB('		"Ordinal",
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
			"width": 300,
			"pinned": "left",
			"type": ["TM1Element"]
		},
		"DynamicColumns": true,
		"DynamicColumnsSettings": {
			"headerconf": ["MUI_DescrizioneData","Descrizione","Descrizione"],
			"headerdefaults')
    || TO_CLOB('": {
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
				"rowGroup": false,
				"aggFunc": "sum",
')
    || TO_CLOB('
				"columnGroupShow": "open",
				"editable": true,
				"type": ["TM1DataColumnInteger", "numericColumn"]
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
				"headerName": "CategoryManager",
				"fiel')
    || TO_CLOB('d": "Compratore.CategoryManager",
				"width": 70,
				"hide": true,
				"rowGroup": true,
				"pinned": "left",
				"editable": false,
				"type": ["TM1Element"]
			},
			{
				"headerName": "Compratore",
				"field": "Compratore.Descrizione",
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
				"hid')
    || TO_CLOB('e": true,
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
				"editable": false,')
    || TO_CLOB('
				"type": ["TM1Element"]
			}

		],
		"rowClassRules": {
			"row_style_hidden": "(data.Compratore.Name == ''TOT_COMP'' && data.Categoria.MUI_Level == 0) || (data.Compratore.Name != ''TOT_COMP'' && data.Categoria.MUI_Level > 0)",
			"row_style_1": "data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)",
			"row_style_2": "data.Compratore.Name==''S000'' && [''_REP_01_01'',''_RE')
    || TO_CLOB('P_01_02'',''_REP_01_03'',''_REP_01_04'',''_REP_12_01'',''_REP_12_02'',''_REP_12_04''].includes(data.Categoria.Name)",
			"row_style_3": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 1",
			"row_style_4": "data.Compratore.Name==''TOT_COMP'' && data.Categoria.MUI_Level == 2"
		},
		"groupRowAggNodes": {
			"nodeGroupTrue": "leafChild.data.Compratore.Name != ''TOT_COMP'' && leafChild.data.Compratore.Name != ''S000''",
			"nodeGroupFalse": "((data.Compratore.Name==''S000'' && [''_REP_01'',''_REP_')
    || TO_CLOB('02'',''_REP_03'',''_REP_04'',''_REP_05'',''_REP_06'',''_REP_09'',''_REP_11'',''_REP_12'',''_REP_14'',''_REP_50''].includes(data.Categoria.Name)) || (data.Compratore.Name == ''TOT_COMP'' && data.Categoria.Name.indexOf(''DIR_'') > -1 ) || (data.Compratore.Name != ''TOT_COMP'' && data.Compratore.Name != ''S000'' ))"
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
        "FROM": "[Timon')
    || TO_CLOB('e Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
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
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": tru')
    || TO_CLOB('e
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
')
    || TO_CLOB('
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
          "headerName": "Data",
          "field": "Promozione.DataInizioIst",
          "width": 70,
          "hide": true,
          "rowGroup": false,
          "editable": false,
          "type": [
            "')
    || TO_CLOB('TM1Element"
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
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup')
    || TO_CLOB('": true,
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
          "width": ')
    || TO_CLOB('70,
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
              "rowGroup": false,')
    || TO_CLOB('
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
              "aggFunc": "sum",
 ')
    || TO_CLOB('             "columnGroupShow": "always",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
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
              "headerName": "N. Foto Speciali",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SPEC",
              "width": 80,
              "hide": false,
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
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
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
              "headerName": "N. Foto Ultima",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
                "TM1DataColumnInt')
    || TO_CLOB('eger",
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
     ')
    || TO_CLOB('           "numericColumn"
              ]
            },
            {
              "headerName": "ML %",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$MARGINE_LORDO__perc_",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "columnGroupShow": "open",
              "editable": true,
')
    || TO_CLOB('
              "type": [
                "TM1DataColumnPercentage",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF C",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$CONTR",
              "width": 80,
              "hide": false,
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
              "editable": true,
              "type": [
               ')
    || TO_CLOB(' "TM1DataColumnInteger",
                "numericColumn"
              ]
            },
            {
              "headerName": "FF EC",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$EXTRA_CONTR",
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
              "col')
    || TO_CLOB('umnGroupShow": "always",
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
             ')
    || TO_CLOB(' "editable": true,
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
              "editable": true,
              "type": [')
    || TO_CLOB('
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
                "TM1DataCo')
    || TO_CLOB('lumnInteger",
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
                "TM1DataColumnInteger",
              ')
    || TO_CLOB('  "numericColumn"
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
                "TM1DataColumnInteger",
                "numericColumn"')
    || TO_CLOB('
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
                "numericColumn"
              ]
            }')
    || TO_CLOB('
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
              "columnGroupShow": "always",
              "editable": ')
    || TO_CLOB('true,
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
              "editable": true,
              "type": [
       ')
    || TO_CLOB('         "TM1DataColumnInteger",
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
         ')
    || TO_CLOB('       "numericColumn"
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
    ')
    || TO_CLOB('          ]
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
                "numericColumn"
              ]
            },
')
    || TO_CLOB('            {
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
  ')
    || TO_CLOB('            "headerName": "N. Foto Ultima",
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
              "headerName": "D Fot')
    || TO_CLOB('o/tgt Mkt",
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
              "headerName": "D Foto Banco/tgt Mkt",
   ')
    || TO_CLOB('           "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_MKT",
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
      ')
    || TO_CLOB('    "headerClass": "headerAcq",
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
           ')
    || TO_CLOB('     "numericColumn"
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
                "TM1DataColumnInteger",
                "numericColumn"
              ]
 ')
    || TO_CLOB('           },
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
              ]
            },
            {
              ')
    || TO_CLOB('"headerName": "N. Foto Banco",
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
              "headerName": "N. Foto Specia')
    || TO_CLOB('li",
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
              "headerName": "N. Foto Spec. Banco",
              "field"')
    || TO_CLOB(': "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
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
              "field": "Scenario$TGT_ACQ$$Mi')
    || TO_CLOB('suraTimone_dot_$N_FOTO_ULT",
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_slash_TGT_')
    || TO_CLOB('REP",
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$D_FOTO_SCAFF_slash_TGT_REP",
    ')
    || TO_CLOB('          "width": 80,
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
    ')
    || TO_CLOB('  "actions": [
        {
          "componentId": "btnInizializzaReferenceId",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY_CUB.Timone.Inizializza Reference",
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
         ')
    || TO_CLOB('     "hasPicklist": true
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
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
          ]')
    || TO_CLOB('
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
        "FROM": "[Tim')
    || TO_CLOB('one Reparto]",
        "WHERE": {
          "Versione": "[Ufficiale]"
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
      "autoGroupColumnDef": {
        "cellRendererParams": {
          "suppressCount": t')
    || TO_CLOB('rue
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
          "field": "Promozione.Canale",
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
          "field": "Promozione.Descrizione_plus_Data",
          "width": 400,
          "hide": true,
          "rowGroup": true,
          "editable": false,
 ')
    || TO_CLOB('         "type": [
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
          "width": 70,
          "hide": true,
          "')
    || TO_CLOB('rowGroup": false,
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
          "')
    || TO_CLOB('children": [
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
    || TO_CLOB('          "headerName": "Totale",
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
    || TO_CLOB('              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO",
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
              "field": "Scenario$R')
    || TO_CLOB('IF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimon')
    || TO_CLOB('e_dot_$N_FOTO_SPEC",
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
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$N_FOTO_SCAFFALE_')
    || TO_CLOB('SPEC",
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
              "headerName": "Venduto Promo (s/iva)",
              "field": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
              "width": 80,
      ')
    || TO_CLOB('        "hide": false,
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
    || TO_CLOB('         "rowGroup": false,
              "aggFunc": "weightedAvg",
              "aggFuncParam": "Scenario$RIF_MKT_DT$$MisuraTimone_dot_$VENDUTO_PROMO_NETTO",
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
          "ope')
    || TO_CLOB('nByDefault": true,
          "children": [
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
       ')
    || TO_CLOB('     },
            {
              "headerName": "Totale",
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
              "')
    || TO_CLOB('headerName": "N. Foto",
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO",
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
            ')
    || TO_CLOB('  "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_SCAFFALE",
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
              "field": "Scenario$TGT_')
    || TO_CLOB('MKT$$MisuraTimone_dot_$N_FOTO_SPEC",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_F')
    || TO_CLOB('OTO_SCAFFALE_SPEC",
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
              "field": "Scenario$TGT_MKT$$MisuraTimone_dot_$N_FOTO_ULT",
           ')
    || TO_CLOB('   "width": 80,
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
          "headerName": "Target REP",
          "headerClass": "headerRep",
          "openByDefault": true,
          "children": [
       ')
    || TO_CLOB('     {
              "headerName": "N. Art",
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
              "headerName": ')
    || TO_CLOB('"Totale",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$TOT_FOTO",
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
              "field": "Sce')
    || TO_CLOB('nario$TGT_REP$$MisuraTimone_dot_$N_FOTO",
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FO')
    || TO_CLOB('TO_SCAFFALE",
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
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SPEC",
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
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
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
              "headerName": "N. Foto Ultima",
              "field": "Scenario$TGT_REP$$MisuraTimone_dot_$N_FOTO_ULT",
              "width": 80,
              "hide": false,
     ')
    || TO_CLOB('         "rowGroup": false,
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
              "hide": false,
              "rowGroup": f')
    || TO_CLOB('alse,
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
   ')
    || TO_CLOB('           "aggFunc": "sum",
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
              "field": "Scenario$TGT')
    || TO_CLOB('_ACQ$$MisuraTimone_dot_$N_ART_PROMO",
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
            {
              "headerName": "Totale",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$TOT_FOTO",
 ')
    || TO_CLOB('             "width": 150,
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
              "headerName": "N. Foto",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO",
              "width": 80,
              ')
    || TO_CLOB('"hide": true,
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
    || TO_CLOB('"rowGroup": false,
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
              "headerName": "N. Foto Spec. Banco",
              "field": "Scenario$TGT_ACQ$$MisuraTimone_dot_$N_FOTO_SCAFFALE_SPEC",
              "width": 80,
              "hide": true,
              "rowGroup": false,
              "aggFunc": "su')
    || TO_CLOB('m",
              "columnGroupShow": "open",
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
              "columnGroupSh')
    || TO_CLOB('ow": "open",
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
              "rowGroup": false,
              "aggFunc": "sum",
              "columnGroupShow": "open",
         ')
    || TO_CLOB('     "editable": true,
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
              "edita')
    || TO_CLOB('ble": true,
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
        {
          "componentId": "btnInizializzaReferenceId",
          "componentLabel": "Inizializza Reference",
          "process": "MUI_DUMMY_CUB.Timone.Ini')
    || TO_CLOB('zializza Reference",
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
            },
            {
              "dimension": "Promozione",
              "attribute": "Descrizione",
              "paramName": "inPromo",
')
    || TO_CLOB('              "label": "Promozione",
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
')
    || TO_CLOB('
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
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName": "Descrizione",
					"field": "Promozione.Descrizione_plus_Data",
					"width": 400,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"')
    || TO_CLOB('
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
					"headerName": "Canal')
    || TO_CLOB('e",
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
					')
    || TO_CLOB('"hide": false,
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
						"dimension": "Promozione",
						"attribute": "Descrizione"
					},
					"destination": [
						{
							"table": "gc_AssociazionePromo_Associazioni",
							"dimension": "Promozione",
							"attribute": "Descrizione"
						},
						{
							"table": "gc_Associaz')
    || TO_CLOB('ionePromo_SezioniTematiche",
							"dimension": "Promozione",
							"attribute": "Descrizione"
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
			"name": "gc_AssociazionePromo_Riferime')
    || TO_CLOB('nto",
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
						"Promozione Riferimento": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, [Promozione Riferimento].[Data_Inizio_Ist.],DESC )}"
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
		')
    || TO_CLOB('				"}ElementAttributes_Promozione": "{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [}ElementAttributes_Promozione Riferimento] )}, 0)}, ASC)}"
					}
				},
				"FROM": "[}ElementAttributes_Promozione Riferimento]"
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
			"autoGroupColumnDef": {
')
    || TO_CLOB('
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
					"field": "PromozioneRiferimento.Descrizione_plus_Data",
					"width": 300,
					"hide": false,
					"editable": false,
					"rowGroup": false,
					"type": [
						"TM1Element"
					]
			')
    || TO_CLOB('	},
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
					]
				},
				{
					"headerName": "Cana')
    || TO_CLOB('le",
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
					"headerName": "Semestre",
					"field": "PromozioneRiferimen')
    || TO_CLOB('to.Semestre",
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
							"dimension": "PromozioneRiferimento",
							"attribute": ')
    || TO_CLOB('"Descrizione"
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
						"Promozione Riferimento": "{TM1SORT( {TM1FILTERBYLEVE')
    || TO_CLOB('L( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, ASC)}"
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
					"mdx": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1S')
    || TO_CLOB('UBSETALL( [Promozione] )}, 0)}, ASC)}} ",
					"enabled": false
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
			"autoGroupColumnDef": {

			},
			"columnDefs": [
				{
					"headerName": "Promozione",
					"field": "Promozione.Descrizione",
					"width": 200,
					"hide": f')
    || TO_CLOB('alse,
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
					"componentId": "btnAssociaId",
					"componentLabel": "Associa",
					"process": "MUI_DUMMY_CUB.Associa Promozione",
')
    || TO_CLOB('
					"timeout": -1,
					"refresh": [
						"gc_AssociazionePromo_Associazioni","gc_AssociazionePromo_SezioniTematiche"
					],
					"params": [
						{
							"dimension": "Promozione",
							"attribute": "Descrizione",
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
							"labe')
    || TO_CLOB('l": "Anno",
							"hasPicklist": false,
							"disabled": true,
							"visible": false
						},
						{
							"dimension": "PromozioneRiferimento",
							"attribute": "Descrizione",
							"paramName": "inRiferimento",
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
')
    || TO_CLOB('
			"rowSuppressionEnabled": false,
			"colSuppressionEnabled": false,
			"alertNoDataFound": false,
			"MDX": {
				"ROWS": {
					"NON_EMPTY": true,
					"DIMENSIONS": {
						"Promozione": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione] )}, 0)}, ASC)}} ",
						"Promozione con Sezione Tematica": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione con Sezione Tematica] )}, 0)}, ASC)}} "
					}
				},
				"COLS": {
					"NON_EMPTY": false,
					"DIMENSIONS": {
			')
    || TO_CLOB('			"Misura Configurazione Riferimento": "{[Misura Configurazione Riferimento].[Selezione] , [Misura Configurazione Riferimento].[DataInizio.],[Misura Configurazione Riferimento].[DataFine.],[Misura Configurazione Riferimento].[DataInizio],[Misura Configurazione Riferimento].[DataFine],[Misura Configurazione Riferimento].[COUNT_GIORNI],[Misura Configurazione Riferimento].[VOL],[Misura Configurazione Riferimento].[ESCLUSIONE_CLUSTER],[Misura Configurazione Riferimento].[ESCLUSIONE_COUNTING],[Misur')
    || TO_CLOB('a Configurazione Riferimento].[Escludi_Venduto],[Misura Configurazione Riferimento].[DESCRIZIONE_RIF]}"
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
					"Attri')
    || TO_CLOB('butes",
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
					"field": "Promozione.Descrizione_plus_Data",
					"width": 300,
					"hide": false,
					"rowGroup": false,
					"editable": false,
					"type": [
						"TM1Element"
					]
				},
				{
					"headerName"')
    || TO_CLOB(': "Descrizione Rif.",
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
					"')
    || TO_CLOB('headerName": "Selezione",
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
				},
')
    || TO_CLOB('				{
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
					]
')
    || TO_CLOB('				},
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
					]
')
    || TO_CLOB('				},
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
						"TM1D')
    || TO_CLOB('ataColumnText"
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
					"rowGroup": fal')
    || TO_CLOB('se,
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
                  "ATTR_desc": "Caption_De')
    || TO_CLOB('fault"
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
				"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "canale",
				"ATTR_columnName": "Canale",
				"ATTR_desc')
    || TO_CLOB('": "Canale"
			},
			{
				"ATTR_code": "tipo",
				"ATTR_columnName": "Tipo Promozione",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Data",
				"ATTR_desc": "Promozione"
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
			{
				"ATTR_code": "datainizioist",
		')
    || TO_CLOB('		"ATTR_columnName": "DataInizioIst",
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
				"ATTR_code": "statodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato Desc"
			},
			{
				"ATTR_code": "canaleanno",
				"ATTR_columnName": "Canale_Anno",
	')
    || TO_CLOB('			"ATTR_desc": "Canale Anno"
			}
		]
	},
	{
		"DIM_code": "promozioneriferimento",
		"DIM_columnName": "Promozione Riferimento",
		"DIM_description": "Promozione Riferimento",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{ORDER( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Promozione Riferimento] )}, 0)}, [Promozione Riferimento].[Data_Inizio_Ist.],DESC )}",
		"list_ATTR": [
			{
				"ATTR_code": "anno",
				"ATTR_columnName": "Anno",
				"ATTR_desc": "Anno"
			},
			{
				"ATTR_code": "rif_ca')
    || TO_CLOB('nale",
				"ATTR_columnName": "MUI_Canale",
				"ATTR_desc": "Canale"
			},
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione + Riferimento",
				"ATTR_desc": "Promozione"
			},
			{
				"ATTR_code": "riferimento",
				"ATTR_columnName": "Descrizione + Data",
				"ATTR_desc": "Riferimento"
			},
			{
				"ATTR_code": "gruppo",
				"ATTR_columnName": "Gruppo",
				"ATTR_desc": "Gruppo"
			},
			{
				"ATTR_code": "semestre",
				"ATTR_columnName": "Semestre",
				"ATTR_desc')
    || TO_CLOB('": "Semestre"
			}
		]
	},
	{
		"DIM_code": "reparto",
		"DIM_columnName": "Reparto",
		"DIM_description": "Reparto",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{ORDER( {TM1FILTERBYLEVEL( {[Reparto].[(II)Reparto]}, 0)},[Reparto].[MUI_Sort],  BASC)}}",
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
				"ATTR_c')
    || TO_CLOB('ode": "sigla",
				"ATTR_columnName": "Sigla",
				"ATTR_desc": "Sigla"
			},
			{
				"ATTR_code": "tipelemento",
				"ATTR_columnName": "TipoElemento",
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
				"ATTR_desc": "')
    || TO_CLOB('Category Manager"
			}
		]
	},
	{
		"DIM_code": "categoria",
		"DIM_columnName": "Categoria",
		"DIM_description": "Categoria",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Categoria] )}, 0)}, ASC)}}",
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
	')
    || TO_CLOB('		{
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
		"DIM_code": "compratore",
		"DIM_columnName": "Co')
    || TO_CLOB('mpratore",
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
				"ATTR_desc": "RepartoCodDesc"
			},
			{
			')
    || TO_CLOB('	"ATTR_code": "repartodesc",
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
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSET')
    || TO_CLOB('ALL( [Anno] )}, 0)}, DESC)}}",
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
				"ATTR_code": "TM1_DefaultDisplayValue",
				"ATTR_columnName": "}TM1_DefaultDisplayValue",
				"ATTR_desc"')
    || TO_CLOB(': "Client"
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
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "p')
    || TO_CLOB('ubblicita",
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
		"DIM_code": "canale",
		"DIM_columnName": "Canale",
		"DIM_description": "Canale",
		"ENDPOINT_serverName": "')
    || TO_CLOB('promo",
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
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura Canale] )}, 0)}, ASC)}}",
	')
    || TO_CLOB('	"list_ATTR": [
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
		"list_ATTR": [
			{
				"ATTR_code": "zonaPromo",
				"ATTR_columnName": "MUI_ZonaPromo",
				"ATTR_desc": "Zona Promo"
			},
			{
				"ATTR_cod')
    || TO_CLOB('e": "descrizione",
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
				"ATTR_desc": "Reparto Profumeria"
			}
		]
	},
	{
		"DIM_code": "raggruppamentoFoto",
		"DIM_columnName": "Raggruppamento Foto",
		"DIM_description": "Raggruppamento Foto",
		"ENDPOIN')
    || TO_CLOB('T_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Raggruppamento Foto] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "mui_descrizione",
				"ATTR_columnName": "MUI_Descrizione",
				"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "tot",
				"ATTR_columnName": "MUI_TOT",
				"ATTR_desc": "Totali"
			},
			{
				"ATTR_code": "tots",
				"ATTR_columnName": "MUI_TOTS",
				"ATTR_desc": "Sub Totali"
			}
		]
	},
	{
		"DIM_code": "tipoPromozio')
    || TO_CLOB('ne",
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
				"ATTR_desc": "Gruppo"
			}
		]
	},
	{
		"DIM_code": "macrospazio",
		"DIM_columnNa')
    || TO_CLOB('me": "MacroSpazio",
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
				"ATTR_desc": "Prestazioni EC"
			},
			{
				"ATTR_code": "prestazioniCNT",
				"ATTR_columnName":')
    || TO_CLOB(' "Prestazioni CNT",
				"ATTR_desc": "Prestazioni CNT"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNeg",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg",
		"DIM_description": "Configurazione Macrospazio Neg",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc')
    || TO_CLOB('": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziNegPromo",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Neg_Promo",
		"DIM_description": "Configurazione Macrospazio Neg Promo",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Neg_Promo] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione')
    || TO_CLOB('"
			}
		]
	},
	{
		"DIM_code": "misuraConfigurazioneMacrospaziListino",
		"DIM_columnName": "Misura_Configurazione_Macrospazi_Listino",
		"DIM_description": "Misura Configurazione Macrospazi Listino",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Misura_Configurazione_Macrospazi_Listino] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descrizione"
			}
		]
	},
	')
    || TO_CLOB('{
		"DIM_code": "spazio",
		"DIM_columnName": "Spazio",
		"DIM_description": "Spazio",
		"ENDPOINT_serverName": "promo",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Spazio] )}, 1)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "compratore",
				"ATTR_columnName": "Compratore",
				"ATTR_desc": "Compratore"
			},
			{
				"ATTR_code": "macroSpazio",
				"ATTR_columnName": "MacroSpazio",
				"ATTR_desc": "Macro Spazio"
			},
			{
				"ATTR_code": "macroSpazioDescrizione",')
    || TO_CLOB('
				"ATTR_columnName": "MacroSpazioDescrizione",
				"ATTR_desc": "Macro Spazio Descrizione"
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
				"ATTR_columnName": "Anno",
				"ATTR_desc": "Anno"
')
    || TO_CLOB('			},
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
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione +')
    || TO_CLOB(' Data",
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
			{
				"ATTR_code": "statodesc",
				"ATTR_columnName": "StatoDesc",
				"ATTR_desc": "Stato D')
    || TO_CLOB('esc"
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
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descri')
    || TO_CLOB('zione"
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
				"ATTR_desc": "Descrizione"
			}
		]
	},
	{
		"DIM_code": "rep_misuraTimone",
		"DIM_columnName": "REP - Misura Timone",
		"DIM_descri')
    || TO_CLOB('ption": "Rep Misura Timone",
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
		"DIM_columnName": "REP - Scenario",
		"DIM_description": "Rep Scenario",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYL')
    || TO_CLOB('EVEL( {TM1SUBSETALL( [REP - Scenario] )}, 0)}, ASC)}}",
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
			}
		]
	},
	{
		"DIM_code": "rep_articolo",
		"DIM_columnName": "REP - Articolo",
		"DIM_descripti')
    || TO_CLOB('on": "Rep Articolo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Articolo] )}, 1)}, ASC)}}",
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
				"ATTR_desc": "SubGrm De')
    || TO_CLOB('sc"
			}
		]
	},
	{
		"DIM_code": "rep_fornitore",
		"DIM_columnName": "REP - Fornitore",
		"DIM_description": "Rep Fornitore",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Fornitore] )}, 0)}, ASC)}}",
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
		"DIM_description": "')
    || TO_CLOB('Rep Zona Promo",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Zona Promo] )}, 0)}, ASC)}}",
		"list_ATTR": [
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
		"MDX_jsonSource": "{{TM1SORT( {TM1F')
    || TO_CLOB('ILTERBYLEVEL( {TM1SUBSETALL( [REP - Sezione Tematica] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
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
		"DIM_code": "rep_avolantino",
		"DIM_columnName": "REP - AVolantino",
		"DIM_description": "Rep AVolantino",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - AVolantino] )}, 0)}, ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
				"ATTR_columnName": "Descrizione",
				"ATTR_desc": "Descr')
    || TO_CLOB('izione"
			}
		]
	},
	{
		"DIM_code": "rep_MisuraReportingArticoloZona",
		"DIM_columnName": "REP - Misura Reporting Articolo Zona",
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
		"DIM_code": "rep_Misura')
    || TO_CLOB('ReportingArticolo",
		"DIM_columnName": "REP - Misura Reporting Articolo",
		"DIM_description": "Rep Articolo",
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
		"DIM_description": "Rep R')
    || TO_CLOB('eparto",
		"ENDPOINT_serverName": "reporting",
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [REP - Reparto] )}, 0)}, ASC)}}",
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
		"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Fornitore] )}, 0)},')
    || TO_CLOB(' ASC)}}",
		"list_ATTR": [
			{
				"ATTR_code": "descrizione",
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
				')
    || TO_CLOB('"ATTR_desc": "Descrizione"
			},
			{
				"ATTR_code": "compratore",
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
				"')
    || TO_CLOB('ATTR_desc": "Descrizione"
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
')
    || TO_CLOB('		"ENDPOINT_serverName": "promo",
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
          "DIM_code": "storeSalesPlan",
          "DIM_columnName": "Store Sales Plan",
          "DIM_description": "Store Sales Plan",
          "ENDPOINT_serverName": "local",
          "MDX_jsonSource": "{{TM1SORT(')
    || TO_CLOB(' {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Store Sales Plan] )}, 0)}, ASC)}}",
          "list_ATTR": [
              {
                  "ATTR_code": "Account",
                  "ATTR_columnName": "Account",
                  "ATTR_desc": "Account"
              }
          ]
      } ,
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
              "MDX_jsonSource": "{')
    || TO_CLOB('{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Prestazione] )}, 0)}, ASC)}}",
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
              "ENDPOINT_serverNam')
    || TO_CLOB('e": "promo",
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
		"DIM_description": "Articolo",
		"ENDPOINT_serverName": "promo",
	')
    || TO_CLOB('	"MDX_jsonSource": "{{TM1SORT( {TM1FILTERBYLEVEL( {TM1SUBSETALL( [Articolo] )}, 1)}, ASC)}}",
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
			{
				"ATTR_code": "attivo",
				"ATTR_columnName": "')
    || TO_CLOB('Attivo",
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
				"ATTR_columnName": "Visualizzazione",
				"ATTR_desc": "Visualizzazione"
			}')
    || TO_CLOB('
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('66','/Fatturazione/Associazione_Articoli_Promozione',' ','FILTER');
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('86','/Piano_Annuale/Timing',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('90','/Piano_Annuale/Sezioni_Tematiche/Crea_Sezioni_Tematiche',' {
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('92','/Piano_Annuale/Spazi/Macrospazi_Negozi_Promo','{
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "macrospazio":["descrizione"],
    "negozio":["zonaPromo","descrizione"],
    "misuraConfigurazioneMacrospaziNegPromo":["descrizione"]
}
 ','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('98','/Reporting/Dettaglio_Zona',' {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('99','/Reporting/Sintesi_Campagna',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_categoria":["descrizione"],
	"rep_misuraTimone":["descrizione"],
	"rep_scenario":["descrizione"]
}
','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('100','/Reporting/Storico_articolo_per_zona_(ACQ)',' {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('101','/Reporting/Storico_articolo_per_zona_(MKT)',' {
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('95','/Piano_Annuale/Spazi/Spazi_Condivisi',' {
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"],
    "spazio":["descrizione","compratore","macroSpazio","macroSpazioDescrizione"],
    "compratore":["descrizione"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('102','/Reporting/Timone_Reparto',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
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
    "promozione":["anno","canale","tipo","riferimento","semestre", "descrizione"]
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
    "raggruppamentoFoto":["mui_descrizione","tot", "tots"],
    "compratore":["descrizione"]
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
	"compratore":["categorymanager","repartodesc","reparto","descrizione"]
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
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('96','/Reporting/Copia_in_pianificazione',' {
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
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
	"rep_promozione":["anno","canale","tipo","riferimento","semestre", "descrizione","datainizioist","datafineist","tipopromozione","statodesc","canaleanno"],
	"rep_compratore":["descrizione"],
	"rep_scenario":["descrizione"],
	"rep_articolo":["categoriadesc","grmdesc","subgrmdesc"],
	"rep_fornitore":["descrizione"]
}
','FILTER');

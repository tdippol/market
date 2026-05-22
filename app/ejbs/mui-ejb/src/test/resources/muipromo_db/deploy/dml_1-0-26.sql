UPDATE MUIPROMO.MENU SET LABEL = 'Proximity' , URL = '', BEAN = 'agGrid', ORDER_ID = 6, EXTERNAL_LINK = 0 WHERE ID_MENU = 63;
UPDATE MUIPROMO.MENU SET LABEL = 'Timing' , URL = 'proximity/timing.xhtml', BEAN = 'agGrid', PARENT_ID = 63, ORDER_ID = 2, EXTERNAL_LINK = 0 WHERE ID_MENU = 64;
UPDATE MUIPROMO.MENU SET LABEL = 'Negozi Promo' , URL = 'proximity/negoziPromo.xhtml', BEAN = 'agGrid', PARENT_ID = 63, ORDER_ID = 3, EXTERNAL_LINK = 0 WHERE ID_MENU = 65;
UPDATE MUIPROMO.MENU SET LABEL = 'Proiezioni' , URL = 'proximity/proiezioni.xhtml', BEAN = 'agGrid', PARENT_ID = 63, ORDER_ID = 4, EXTERNAL_LINK = 0 WHERE ID_MENU = 66;
UPDATE MUIPROMO.MENU SET LABEL = 'Articoli per Negozio' , URL = 'proximity/articoliperNegozio.xhtml', BEAN = 'agGrid', PARENT_ID = 63, ORDER_ID = 5, EXTERNAL_LINK = 0 WHERE ID_MENU = 67;
UPDATE MUIPROMO.MENU SET LABEL = 'Set per Negozio' , URL = 'proximity/setperNegozio.xhtml', BEAN = 'agGrid', PARENT_ID = 63, ORDER_ID = 6, EXTERNAL_LINK = 0 WHERE ID_MENU = 68;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 69;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 70;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 71;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 72;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 73;
DELETE FROM MUIPROMO.MENU WHERE ID_MENU = 69;


Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('150','/Proximity/Timing','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('151','/Proximity/Negozi_Promo','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('152','/Proximity/Proiezioni','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('153','/Proximity/Articoli_per_Negozio','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre"]
}','FILTER');
Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('154','/Proximity/Set_per_Negozio','{
    "promozione":["anno","canale","tipo","descrizione","riferimento","semestre"]
}','FILTER');

Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('155','/Proximity/Timing',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Timing",
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "O')
    || TO_CLOB('rdinal",
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
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.An')
    || TO_CLOB('no","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"')
    || TO_CLOB('rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": fal')
    || TO_CLOB('se , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellCl')
    || TO_CLOB('ass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "t')
    || TO_CLOB('ype":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoComm')
    || TO_CLOB('erciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
  ')
    || TO_CLOB('      {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
      ]
    }
  ]
}
'),'GRID');

Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('156','/Proximity/Negozi_Promo',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_NegoziPromo",
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "O')
    || TO_CLOB('rdinal",
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
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.An')
    || TO_CLOB('no","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"')
    || TO_CLOB('rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": fal')
    || TO_CLOB('se , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellCl')
    || TO_CLOB('ass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "t')
    || TO_CLOB('ype":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoComm')
    || TO_CLOB('erciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
  ')
    || TO_CLOB('      {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
      ]
    }
  ]
}
'),'GRID');

Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('157','/Proximity/Proiezioni',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_Proiezioni",
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "O')
    || TO_CLOB('rdinal",
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
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.An')
    || TO_CLOB('no","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"')
    || TO_CLOB('rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": fal')
    || TO_CLOB('se , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellCl')
    || TO_CLOB('ass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "t')
    || TO_CLOB('ype":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoComm')
    || TO_CLOB('erciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
  ')
    || TO_CLOB('      {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
      ]
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "O')
    || TO_CLOB('rdinal",
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
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.An')
    || TO_CLOB('no","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"')
    || TO_CLOB('rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": fal')
    || TO_CLOB('se , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellCl')
    || TO_CLOB('ass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "t')
    || TO_CLOB('ype":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoComm')
    || TO_CLOB('erciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
  ')
    || TO_CLOB('      {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
      ]
    }
  ]
}
'),'GRID');

Insert into MUIPROMO.CONFIGURATION (ID_CONFIGURATION,PATH,JSON,TYPE) values ('159','/Proximity/Set_per_Negozio',TO_CLOB(' {
  "connection": "promo",
  "configurations": [
    {
      "name": "gc_SetPerNegozio",
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
          "Attributes",
          "UniqueName"
        ] ,
        "Cells": [
          "O')
    || TO_CLOB('rdinal",
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
      , "width":500
      , "pinned": "left"
      , "type":["TM1Element"]
      } ,
      "columnDefs": [
        {"headerName":"Anno","field":"Promozione.An')
    || TO_CLOB('no","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]},
        {"headerName":"Canale","field":"Promozione.Canale","width":80,"hide":true,"rowGroup": true , "editable": false,"type":["TM1Element"]} ,
        {"headerName":"Descrizione + Data","field":"Promozione.Descrizione_plus_Data","width":110,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Descrizione","field":"Promozione.Descrizione","width":90,"hide":true,"')
    || TO_CLOB('rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Riferimento","field":"Promozione.Riferimento","width":90,"hide":false,"rowGroup": false,  "editable": false,"type":["TM1Element"]},
        {"headerName":"Semestre","field":"Promozione.MUI_Semestre","width":70,"hide":true,"rowGroup": false , "editable": false,"type":["TM1Element"]},
        {"headerName":"Data Inizio","field":"MisuraPianoOperativoCommerciale$DataInizio","width":120,"hide":false,"rowGroup": fal')
    || TO_CLOB('se , "editable": true, "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine","field":"MisuraPianoOperativoCommerciale$DataFine","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Inizio Ist","field":"MisuraPianoOperativoCommerciale$Data_Inizio_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellCl')
    || TO_CLOB('ass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Fine Ist","field":"MisuraPianoOperativoCommerciale$Data_Fine_ist_dot_","width":120,"hide":false,"rowGroup": false , "editable": true,  "cellClass": "dateFormat", "type":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Data Abbatt.Prezzi","field":"MisuraPianoOperativoCommerciale$DATA_ABB_PRZ","width":120,"hide":false,"rowGroup": false , "editable": true, "cellClass": "dateFormat", "t')
    || TO_CLOB('ype":[ "TM1DataColumnDate" ,"numericColumn"]},
        {"headerName":"Ordine Timone","field":"MisuraPianoOperativoCommerciale$Ordinamento","width":80,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa %","field":"MisuraPianoOperativoCommerciale$ETICH_SENZA__perc_","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"No Stampa Etic","field":"MisuraPianoOperativoComm')
    || TO_CLOB('erciale$NO_STMP_ETICH","width":110,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Canale Promozionale","field":"MisuraPianoOperativoCommerciale$Canale","width":150,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
        {"headerName":"Stato Promozione","field":"MisuraPianoOperativoCommerciale$StatoPromo","width":170,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]},
  ')
    || TO_CLOB('      {"headerName":"Note Marketing","field":"MisuraPianoOperativoCommerciale$NoteMarketing","width":200,"hide":false,"rowGroup": false , "editable": true, "type":[ "TM1DataColumnText"]}
      ]
    }
  ]
}
'),'GRID');
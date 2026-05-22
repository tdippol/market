/**
 * Ritorna una lista di classi CSS custom in base ai flag editable, mandatory e warning del singolo campi
 * @param params parametri ag-grid
 * @returns {*[]} lista di classi CSS
 */
function axCellClass(params) {
    let classes = [];
    let field = params.colDef?.field;
    if (params.data && field) {
        let data = params.data[field];
        if (data) {
            if (data.editable) {
                classes.push("ax-editable");
            }
            if (data.mandatory) {
                classes.push("ax-mandatory-bg");
            }
            if (data.warning) {
                classes.push("ax-warning-bg");
            }
            if (data.txtMandatory) {
                classes.push("ax-mandatory-fg");
            }
            if (data.txtWarning) {
                classes.push("ax-warning-fg");
            }
            let tipoRiga = params.data.TIPO_RIGA?.value;
            if (data.invisible && tipoRiga) {
                switch (tipoRiga) {
                    case "SET":
                        classes.push("ax-invisible-set");
                        break;
                    case "RAGGRUPPAMENTO":
                        classes.push("ax-invisible-raggr");
                        break;
                    case "ELEMENTO":
                        classes.push("ax-invisible-el");
                        break;
                }
            }
            if (data.extraClasses) {
                classes.push(...data.extraClasses);
            }
        }
    }
    return classes;
}


var dbPromoLogSettings = getDebugLogSettings();


var dbPromoAppSettings = {
    stringUtils: {
        joinDelimiter: '||'			//dbPromoAppSettings.stringUtils.joinDelimiter
    },
}


var columnDefs = [];
var rowData = []
var defaultGridOptions = null;
var gridOptions = null;


var gridMap = new Map();

var gridFilterMap = new Map();

var localeText = AG_GRID_LOCALE_IT;

defaultGridOptions = {
    defaultColDef: {
        filter: true,
        resizable: true,
        sortable: true,
        menuTabs: ["filterMenuTab", "columnsMenuTab"]
    },
    suppressScrollOnNewData: true ,
    overlayLoadingTemplate:
        '<span class="ag-overlay-loading-center"> <i class="fa fa-spinner fa-spin"></i> Please wait</span>',
    //stopEditingWhenGridLosesFocus: true,
    //enableCellChangeFlash: true,
    animateRows: false,
    rowSelection: "single",
    suppressRowClickSelection: false,
    suppressCopyRowsToClipboard: true,
    columnDefs: columnDefs,
    groupDefaultExpanded: 999,
    enableRangeSelection: true,
    columnTypes: AxColumnTypes,
    getContextMenuItems: dbPromoContextMenu,
    onFilterChanged: onFilterChanged,
    onRowDataChanged: onRowDataChanged,
    localeText: localeText,
    components: {

        AxUneditableCellEditor: UneditableCellEditor,
        AxTextCellEditor: AxTextCellEditor,
        AxCheckboxCellRenderer: AxCheckboxCellRenderer,
        AxDateCellEditor: DateCellEditor,
        AxTimeCellEditor: AxTimeCellEditor,
        AxNumericCellEditor: AxNumericCellEditor,
        //AxPicklistCellEditor	: 'agRichSelectCellEditor', // AxPicklistCellEditor,
        AxPicklistCellEditor: AxPicklistCellEditor,
        //AxComboBoxCellEditor	: AxComboBoxCellEditor, // AxPicklistCellEditor,
        AxComboBoxCellEditor: AxPicklistCellEditor, // AxPicklistCellEditor,
        datePickerRenderer: DateCellRenderer,

        Pianificazione_BtnCellRenderer: Pianificazione_BtnCellRenderer,

        CreaPromo_AddPromoRenderer: CreaPromo_AddPromoRenderer,

        ModifcaPromo_DeletePromoRenderer: ModifcaPromo_DeletePromoRenderer,

        SchedaPromoMeccaniche_DeleteMeccanicheRenderer: SchedaPromoMeccaniche_DeleteMeccanicheRenderer,

        SchedaPromoPlanogram_DeletePlanogramRenderer: SchedaPromoPlanogram_DeletePlanogramRenderer,

        SchedaPromoReparti_DeleteRepartiRenderer: SchedaPromoReparti_DeleteRepartiRenderer,

        SchedaPromoMeccaniche_DeleteTipoCassaRenderer: SchedaPromoMeccaniche_DeleteTipoCassaRenderer,

        SchedaPromoCompratori_DeleteCompratoriRenderer: SchedaPromoCompratori_DeleteCompratoriRenderer,

        Complementari_downloadFileScarti: Complementari_downloadFileScarti,


        ConfigSet_DeleteRow: ConfigSet_DeleteRow,

        PianConfig_Header_BtnCellRenderer: PianConfig_Header_BtnCellRenderer,
        PianConfig_TipoElemento_BtnCellRenderer: PianConfig_TipoElemento_BtnCellRenderer,
        PianConfig_Regole_BtnCellRenderer: PianConfig_Regole_BtnCellRenderer,

        Cumulabilita_BtnCellRenderer: Cumulabilita_BtnCellRenderer,
        Buoni_BtnCellRenderer: Buoni_BtnCellRenderer,

        CreaPianoMedia_BtnRenderer: CreaPianoMedia_BtnRenderer,


        AxPicklistRenderer: AxPicklistRenderer,
        AxFilterRederer: AxFilterRederer,
        AxOpenDialogRenderer: AxOpenDialogRenderer,
        AxPickcolorRenderer: AxPickcolorRenderer,
        AxPickcolorEditor: AxPickcolorEditor,
        PianificazioneMedia_DeleteBtnRenderer: PianificazioneMedia_DeleteBtnRenderer,
        MessaggiPianificazione_ButtonsRenderer: MessaggiPianificazione_ButtonsRenderer,
        CastellettoPianificazione_DeleteBtnRenderer: CastellettoPianificazione_DeleteBtnRenderer,
        InclusioneEsclusione_DeleteBtnRenderer: InclusioneEsclusione_DeleteBtnRenderer,
        TotalizzatoriObiettivo_DeleteObiettivoRenderer: TotalizzatoriObiettivo_DeleteObiettivoRenderer,
        Sottoscrizioni_DeleteSottoscrizioneRenderer: Sottoscrizioni_DeleteSottoscrizioneRenderer,
        Default_EditBtnRenderer: Default_EditBtnRenderer,
        EventiRetail_BtnRenderer: EventiRetail_BtnRenderer,
        MacrospaziRetail_BtnRenderer: MacrospaziRetail_BtnRenderer,
        SchedaPromoSottoscrizioni_DeleteSottoscrizioneRenderer: SchedaPromoSottoscrizioni_DeleteSottoscrizioneRenderer
    },
    onBodyScroll: function (params) {
        if(params.left%10==0){
            autosizeGridHeaders(params);
        }
    },
    onColumnResized: function (params) {
        if (params.finished) {
            autosizeGridHeaders(params);
        }
    },
    onColumnGroupOpened: function () {
        var params = {finished: true, api: this.api};
        autosizeGridHeaders(params);
    },
    onColumnVisible: onColumnVisible,
    sideBar: undefined,
    processCellForClipboard: processCellForClipboard,
    //excelStyles: [{id: "dateFormat", numberFormat: {format: "ddd dd/mm/yyyy"}}]
};


// #4189: save custom sorting
function onSortChanged(event, url) {
    var sortState = event.columnApi.getColumnState().filter(function(s) {
        return s.sort != null;
    }).map(function(s) {
        return { colId: s.colId, sort: s.sort, sortIndex: s.sortIndex };
    });
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        data: JSON.stringify(sortState),
        success: function (data, textStatus, jqXHR) {
            // noop
        },
        error: function(jqXHR, exception, thrownError) {
            addErrorGrowl([
                { name: 'title', value: 'Attenzione' },
                { name: 'message', value: 'Errore salvataggio ordinamento personalizzato' }
            ]);
        }
    });
}


function onColumnVisible(event) {

    let gridCode = event.api.gridOptionsWrapper.gridOptions.gridCode
    let gridDiv = document.querySelector('[gridid=' + gridCode + ']');

    var cols = "[";
    $.each(event.columns, function (index, col) {
        cols += '{ "colId" : "' + col.colId + '", "visible" : "' + col.visible + '" },';
    });
    if (cols.endsWith(",")) {
        cols = cols.substring(0, cols.length - 1);
    }
    cols += "]";

    persistHiddenColumns([{name: "grid", value: gridCode}, {name: "cols", value: cols}]);

    let gridOptions = getGridOptionFromGridMap(gridCode)

    if (gridOptions.columnApi.columnModel.getAllDisplayedColumns().length > 0) {
        gridOptions.api.setSideBarVisible(false)
        gridOptions.api.setSideBar(false)
        //updateRemoteView([{name: "grid", value: gridCode}]);
    }
    if (gridOptions.columnApi.columnModel.getAllDisplayedColumns().length === 0) {
        gridOptions.api.setSideBarVisible(true)
        gridOptions.api.setSideBar({
            toolPanels: [{
                id: "columns",
                labelDefault: "Columns",
                labelKey: "columns",
                iconKey: "columns",
                toolPanel: "agColumnsToolPanel",
                toolPanelParams: {
                    suppressRowGroups: true,
                    suppressValues: true,
                    suppressPivots: true,
                    suppressPivotMode: true,
                    suppressSideButtons: true,
                    suppressColumnFilter: true,
                    suppressColumnSelectAll: true,
                    suppressColumnExpandAll: true
                }
            }], defaultToolPanel: "columns"
        })
        //updateRemoteView([{name: "grid", value: gridCode}]);
    }
}


function dbPromoContextMenu(params) {

    let cellValue = (params.value != undefined) ? params.value : ''
    var result = [{
        name: 'Valore Cella ' + ((cellValue != '') ? ('"' + cellValue + '"') : '(vuoto)'), action: function () {
            window.alert('Valore Cella "' + params.value + '"');
        }, disabled: false, tooltip: "alert the cell value"
    }, "separator", "separator", "expandAll", "contractAll", "autoSizeAll"];
    return result;
}


function processCellForClipboard(params){
    //LT("processCellForClipboard",evalCellColumnTypes(params,'value'))
    return evalCellColumnTypes(params,'value')
}


function onFilterTextBoxChangedpromoGrid(gridCode, value) {
    //document.getElementById('some_grid_id_from_backend_quickfilter').value
    getGridOptionFromGridMap(gridCode).api.setQuickFilter(value);

}

function setLoadingStatus(button) {
    $(button).prop("disabled", true);
    $(button).css({ "min-width": $(button).width(), });
    $(button).html("");
    $(button).append("<i class='buttonSpinner'/>");
}


function expandGrid(id) {

    var container = $("#expandable_" + id);
    container.toggleClass("expandableDiv");
    container.toggleClass("fullscreen");
    $("." + id).toggleClass("gridFullscreen");

    if (container[0] === undefined) {
        var container = $(".expandable_" + id);
        container.toggleClass("expandableDiv");
        container.toggleClass("fullscreen");
        $("." + id).toggleClass("gridFullscreen");

        if(container.hasClass('fullscreen')){
            let t=getGridDiv(id)
            $(t).data('original-height',$(t).css('height'));
            $(t).css('height','90vh')
        }else{
            let t=getGridDiv(id)
            $(t).css('height',$(t).data('original-height'))
        }

    }

}


function evalCellColumnTypes(params, property) {
    try {
        //LT("params.node.data." + params.colDef.field + "." + property,params);
        return eval("params.node.data." + params.column.colDef.field + "." + property);
    } catch (e) {
        return "";
    }
}


function onBtExport(gridCode, fileName, grouped) {

    skipRowGroups = true

    if (skipRowGroups !== undefined) {
        skipRowGroups = !grouped
    }


    //var gridOptions = document.querySelector("." + gridContainerClass).gridOptions;
    var params = {
        skipHeader: false,
        columnGroups: true,
        skipFooters: false,
        skipRowGroups: skipRowGroups,
        skipPinnedTop: false,
        skipPinnedBottom: false,
        allColumns: false,
        onlySelected: false,
        fileName: fileName,
        sheetName: fileName,
        exportMode: "xlsx",
        processCellCallback: function (params) {

            let value = evalCellColumnTypes(params, 'value')
            let type = evalCellColumnTypes(params, 'type')

            if (!value) {
                //return '';
            }


            if (type == 'date') {
                let dataTypeParams = evalCellColumnTypes(params, 'dataTypeParams');
                let dateFormat = dataTypeParams.dateFormat;
                if (dateFormat === 'DDD dd/mm/yyyy HH:MM.ss') {
                    value = formatDate(value, true);
                } else {
                    value = formatDate(value);
                }
                return value;
            }

            if (type === "picklist") {
                return value
            } else if (type === "comboBox") {
                let listOfValues = evalCellColumnTypes(params, "comboBoxValues");
                let resultItem = listOfValues.filter(item => {
                    return item.key === value
                })

                if (resultItem.length === 1) {
                    return resultItem[0].label
                }
            }

            //LT(params)

            return params.value;

        }
    };
    getGridOptionFromGridMap(gridCode).api.exportDataAsExcel(params);
}


function handleTabChange(i) {
    //i = Index of the new tab
    /*LT("ACTIVE INDEXX",PF('schedaPromo_tabs').getActiveIndex());

    alert(i);*/
    //LT("OK");
}


function getCurrentTab(gridTabGroup) {
    if (PF(gridTabGroup) !== undefined) {
        return PF(gridTabGroup).getActiveIndex();
    } else {
        return 0;
    }
}


function getGridOptionFromGridMap(gridCode) {

    return gridMap.get(gridCode);

}


function creaGriglia(gridParams) {

    CL();

    //controllo univocità griglia
    if(document.querySelector('[gridid=' + gridParams.gridCode + ']').children.length>0){
        return false
    }

    let gridOptions = { ...defaultGridOptions, ...gridParams.gridOptions };

    //overrideDefaultColDef => modifiche al default column defs
    $.each(gridParams.overrideDefaultColDef, function (index, overrideDefaultColDef) {
        gridOptions.defaultColDef[overrideDefaultColDef.property]=overrideDefaultColDef.value
    });


    //#####################
    gridMap.set(gridParams.gridCode, gridOptions);

    let GO = getGridOptionFromGridMap(gridParams.gridCode);


    //LT("'"+gridParams.gridTitle+"' gridOptions [gridCode: "+gridParams.gridCode+"] => ",GO);

    // setup the grid after the page has finished loading

    var gridDiv = document.querySelector('[gridid=' + gridParams.gridCode + ']');
    new agGrid.Grid(gridDiv, GO);


    //CLEAR FILTERS
    clearFilters(gridParams.gridCode)

    //ADDITION COMPONENTS TO gridOptions
    let componentsList = gridOptions.components
    $.each(gridParams.additionComponents, function (index, additionalComponent) {
        let componentName = additionalComponent.componentName
        componentsList.componentName = additionalComponent.componentValue
    });
    gridOptions.components = componentsList;
    gridOptions.gridCode = gridParams.gridCode

    // #4190: apply custom sorting
    if (gridParams.url_customSort != undefined) {
        agGrid.fetchRemote({ url: gridParams.url_customSort}).then(function (data) {
            LT("'" + gridParams.gridTitle + "' customSorting => ", data);
            if (gridParams.customizeColumnDefs == undefined) {
                gridParams.customizeColumnDefs = [];
            }
            data.forEach(s => {
                gridParams.customizeColumnDefs.push(
                    { columnDefId: s.colId, property: 'sort', value: s.sort }
                );
                gridParams.customizeColumnDefs.push(
                    { columnDefId: s.colId, property: 'sortIndex', value: s.sortIndex }
                );
            });
        }).catch(function (err) {
            addErrorGrowl([
                { name: 'title', value: 'Attenzione' },
                { name: 'message', value: 'Errore caricamento ordinamento personalizzato' }
            ]);
        })
    }

    //COLUMN DEFS
    if (gridParams.url_columnDefs != undefined && gridParams.url_columnDefs != null) {


        agGrid.fetchRemote({url: gridParams.url_columnDefs})
            .then(function (data) {

                $.each(gridParams.additionColumnDefs, function (index, additionalColumn) {

                    let columnPosition = 'append'
                    if (additionalColumn.position !== undefined) {
                        columnPosition = additionalColumn.position
                    }

                    if (columnPosition === 'append') {
                        data.columnDef.push(additionalColumn);
                    } else if (columnPosition === 'prepend') {
                        data.columnDef.unshift(additionalColumn);
                    }


                });


                $.each(gridParams.sortColumnDefs, function (index, sortColumnDef) {
                    var columnDefMatchID = data.columnDef.findIndex(function (objColDef) {
                        return objColDef.field === sortColumnDef.columnDefId;
                    });
                    let column = data.columnDef[columnDefMatchID];

                    data.columnDef.splice(columnDefMatchID, 1)

                    data.columnDef.splice(sortColumnDef.newPosition, 0, column);

                });


                //LOOP ALL COLUMNS
                $.each(data.columnDef, function (index, column) {
                    //Cell style - Editable
                    if (column.type === null) {
                        LT("ColumnDef " + column.field + " campo type non impostato.", "default type=string")
                        column.type = 'string'
                    }
                    if (column.editable === false) {
                        column.editable = true
                    }

                    delete column.cellStyle

                    //Set filter list custom format for all types
                    /*column.filter= 'agSetColumnFilter'
                column.filterParams= {
                    cellRenderer: 'AxFilterRederer'
                }*/


                    //DATE
                    if (column.type === 'date') {
                        column.filter = 'agSetColumnFilter'
                        //column.filterValueGetter = function (params){ return params.value;}
                        column.filterParams = {
                            //cellRenderer: function (params) { return excelNumToCellDate(params.value); }
                            //cellRenderer: function (params) { return (params.value); },
                            //valueFormatter: function (params) { return "test"; },
                        }
                    }


                    //Rimozione della proprietà rowgroup se è impostato il parametro treeData
                    if (gridParams.gridOptions.treeData) {
                        delete column.rowGroup
                    }

                    // Aggiungo la property cellClass con la function per gestire una classe custom a livello di cella
                    column.cellClass = axCellClass;
                });


                $.each(gridParams.customizeColumnDefs, function (index, customizeColumn) {
                    var columnDefMatchID = data.columnDef.findIndex(function (objColDef) {
                        return objColDef.field === customizeColumn.columnDefId;
                    });

                    if (columnDefMatchID > 0) {
                        let column = data.columnDef[columnDefMatchID];
                        column[customizeColumn.property] = customizeColumn.value;
                        data.columnDef[columnDefMatchID] = column;
                    }
                });


                LT("'" + gridParams.gridTitle + "' columnDefs => ", data.columnDef);
                GO.api.setColumnDefs(data.columnDef);

                autosizeGridHeaders({finished: true, api: GO.api});


                $.each(gridParams.moveColumnsByIndex, function (index, moveColumn) {
                    var columnDefMatchID = data.columnDef.findIndex(function (objColDef) {
                        return objColDef.field === moveColumn.columnDefId;
                    });
                    let column = data.columnDef[columnDefMatchID];

                    gridOptions.columnApi.moveColumnByIndex(columnDefMatchID, moveColumn.indexDestination);

                });


                //Sidebar view settings
                if (gridOptions.columnApi.columnModel.getAllDisplayedColumns().length > 0) {
                    gridOptions.api.setSideBarVisible(false)
                }
                if (gridOptions.columnApi.columnModel.getAllDisplayedColumns().length === 0) {
                    gridOptions.api.setSideBarVisible(true)
                }


            }).catch(function (err_obj) {
            displayErrorFetchRemote(err_obj)
        });

    } else {
        LT("'" + gridParams.gridTitle + "' columnDefs => ", GO.columnDefs);
        GO.api.setColumnDefs(GO.columnDefs);
    }

    //ROW DATA
    if (gridParams.url_rowData != undefined && gridParams.url_rowData != null) {

        if(gridParams.subordinate_rowData!=undefined && gridParams.subordinate_rowData===true){
            //subordinate_rowData => nel caso la griglia non carica i dati finche non viene
            //selezionato un record di un'altra griglia: es. Config delle pianificazioni
            GO.api.showNoRowsOverlay();
        }else{
            agGrid.fetchRemote({ url: gridParams.url_rowData }).then(function (data){

                let rowsData = [];
                if (gridParams.rowDataMainNode !== undefined) {
                    rowsData = data[gridParams.rowDataMainNode];
                } else {
                    rowsData = data.rowData;
                }


                //override data => Es. valori di default per il copia incolla
                if (gridParams.overrideRowData !== undefined) {
                    $.each(gridParams.overrideRowData, function (index, overrideRowData) {
                        let field = overrideRowData.field
                        let newValue = overrideRowData.newValue
                        $.each(rowsData, function (j) {
                            rowsData[j][field].value = newValue
                        })
                    });
                }

                /*
                    !!!!!!  TEST OVERRIDE DATA  rowsData[numero_riga]["nomecampo"].proprietà della cella = nuovo valore
                */

                LT("'" + gridParams.gridTitle + "' rowsData => ", rowsData);
                GO.api.setRowData(rowsData);

            }).catch(function (err_obj) {
                hideAgGridLoadingOverlay(gridParams.gridCode)
                displayErrorFetchRemote(err_obj)
                GO.api.showNoRowsOverlay();

            });
        }
    } else {
        LT("'" + gridParams.gridTitle + "' rowsData => ", GO.rowData);
        GO.api.setRowData(GO.rowData);
    }
}


function DBG() {
    if (dbPromoLogSettings.debugger_enabled === true) {
        debugger;
    }
}
function LT(name, obj) {

    if (dbPromoLogSettings.log_enabled === true) {
        if (name && obj) {

            //tronca il log se è un array e se il numero di record è superiore a 250
            if (Array.isArray(obj) === true) {
                log_max_array_preview = dbPromoLogSettings.max_array_preview
                if (log_max_array_preview > 0 && obj.length > log_max_array_preview) {
                    console.log('%c ======> Large amount of data, log preview sliced to ' + log_max_array_preview + ' elements. [Original data size: ' + obj.length + ' elements]', 'background: #222; color: white');
                    obj = obj.slice(0, log_max_array_preview)
                }
            }

            console.log(name, obj);
        } else {
            console.log(name);
        }

    }

}


function AT(s) {

    if (dbPromoLogSettings.log_enabled === true) {
        alert(s);
    }

}

function CL() {
    if (dbPromoLogSettings.clear_console === true) {
        console.clear();
    }
}

function CM(type, message) {
    if (dbPromoLogSettings.log_enabled === true && dbPromoLogSettings.display_custom_messages === true) {
        let style = ""
        if (type == "success") {
            style = 'color: #155724;background-color: #d4edda;border:3px solid #c3e6cb;'
        }
        if (type == "info") {
            style = 'color: #004085;background-color: #cce5ff;border:3px solid #b8daff;'
        }
        if (type == "warning") {
            style = 'color: #856404;background-color: #fff3cd;border:3px solid #ffeeba;'
        }
        if (type == "error") {
            style = 'color: #721c24;background-color: #f8d7da;border:3px solid #f5c6cb;'
        }
        console.log('%c CM LOG ==> ' + message + ' ', style);
    }
}

function getRowData(gridCode) {
    let rowData = [];
    getGridOptionFromGridMap(gridCode).api.forEachNode(node => rowData.push(node.data));
    return rowData;

}

function getLeafRowData(gridCode) {
    let rowData = [];
    getGridOptionFromGridMap(gridCode).api.forEachLeafNode(node => rowData.push(node.data));
    return rowData;

}

function getLeafRowDataAfterFilter(gridCode) {
    let rowData = [];
    getGridOptionFromGridMap(gridCode).api.forEachNodeAfterFilter(function (node, index) {
        if (node !== undefined && node.data !== undefined) {
            rowData.push(node.data)
        }
    });
    return rowData;
}

function log_getRowColumn(gridCode, field, fieldUnique) {
    if (dbPromoLogSettings.log_enabled === true) {
        let uniqueField = '';
        getRowData(gridCode).filter((r, index) => {

            if (fieldUnique !== undefined) {
                uniqueField = " - UNIQUE KEY FIELD '" + fieldUnique + ":" + r[fieldUnique].value + "'"
            }
            LT("ROW '" + index + "' - FIELD '" + field + "'" + uniqueField, sortKeys(r[field]))
        })
    }
}
function sortKeys(obj_1) {
    var key = Object.keys(obj_1)
        .sort(function order(key1, key2) {
            if (key1 < key2) return -1;
            else if (key1 > key2) return +1;
            else return 0;
        });

    // Taking the object in 'temp' object
    // and deleting the original object.
    var temp = {};

    for (var i = 0; i < key.length; i++) {
        temp[key[i]] = obj_1[key[i]];
        delete obj_1[key[i]];
    }

    // Copying the object from 'temp' to
    // 'original object'.
    for (var i = 0; i < key.length; i++) {
        obj_1[key[i]] = temp[key[i]];
    }
    return obj_1;
}


function showAgGridLoadingOverlay(gridCode) {
    getGridOptionFromGridMap(gridCode).api.showLoadingOverlay();
}

function hideAgGridLoadingOverlay(gridCode){
    getGridOptionFromGridMap(gridCode).api.hideOverlay();
}

function getRowNodeId(event) {
    try {
        return event.api.rowModel.getRow(event.rowIndex).id
    } catch (e) {
        LT("agGridDefault->getNodeId() error")
    }
}


function deleteCellsRange(params) {
    //showAgGridLoadingOverlay(getGridCodeFromParams(params))
    var ranges = params.api.getCellRanges();
    var api = params.api;
    let updateCounter = 0;
    let updateCounterNullable = 0;
    ranges.forEach(function (range, index) {
        var startRow = Math.min(range.startRow.rowIndex, range.endRow.rowIndex);
        var endRow = Math.max(range.startRow.rowIndex, range.endRow.rowIndex);

        for (var rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
            range.columns.forEach(function (column) {
                var rowModel = api.getModel();
                var rowNode = rowModel.getRow(rowIndex);

                if (rowNode.data != null && rowNode.data[column.colId].editable && rowNode.data[column.colId].value !== '') {
                    if (rowNode.data[column.colId].nullable === true) {
                        if (params.value.type === 'popupdialog') {
                            let cell = rowNode.data[column.colId];
                            cell.value = '';
                            rowNode.setDataValue(column.colId, cell);
                        } else {
                            rowNode.setDataValue(column.colId, '');
                        }
                        updateCounter++;
                    } else {
                        updateCounterNullable++;
                    }

                }


            });
        }
    });

    params.api.gridOptionsWrapper.gridOptions.api.stopEditing()
    params.api.gridOptionsWrapper.gridOptions.api.setFocusedCell();
    if (updateCounter > 0) {
        addInfoGrowl([{ name: 'title', value: 'Aggiornamento completato' }, {
            name: 'message',
            value: 'I valori selezionati sono stati cancellati'
        }]);
    }

    if (updateCounterNullable > 0) {
        addWarningGrowl([{ name: 'title', value: 'Attenzione' }, {
            name: 'message',
            value: 'I valori selezionati sono obbligatori'
        }]);
    }

    //hideAgGridLoadingOverlay(getGridCodeFromParams(params))

}


function onFilterChanged(params) {

    //LT("Start saving filter: ", params)


    let quickFilter = params.api.filterManager.quickFilter;
    //LT(quickFilter)

    let columnFilters = params.api.getFilterModel();
    //LT(columnFilters)

    //save to js map
    let gridCode=params.api.gridOptionsWrapper.gridOptions.gridCode
    let store_filter_obj={
        quickFilter:quickFilter,
        columnFilters:columnFilters
    }
    //LT("FILTER  CHANGED",store_filter_obj)
    gridFilterMap.set(gridCode, store_filter_obj);

}


function clearFilters(gridCode){
    $('.filterInputText').val('')
    getGridOptionFromGridMap(gridCode).api.setQuickFilter('');
    gridFilterMap.delete(gridCode)
}


function onRowDataChanged(params){

    let gridCode=params.api.gridOptionsWrapper.gridOptions.gridCode
    //Applica filtri memorizzati per la griglia
    let filterObj=gridFilterMap.get(gridCode);


    if(filterObj!=undefined){

        //LT("APPLICA FILTRI:",filterObj)

        if(filterObj.quickFilter!=undefined && filterObj.quickFilter!='' && filterObj.quickFilter!=null){
            getGridOptionFromGridMap(gridCode).api.setQuickFilter(filterObj.quickFilter)
        }
        if(filterObj.columnFilters!=undefined && filterObj.columnFilters!={}){
            getGridOptionFromGridMap(gridCode).api.setFilterModel(filterObj.columnFilters)
        }
    }


}


function autosizeGridHeaders(params){
    //LT("AUTOSIZE",params)

    if(params.direction!=undefined && params.direction=='vertical'){
        return false;
    }

    let gridCode=params.api.gridOptionsWrapper.gridOptions.gridCode
    let gridDiv = document.querySelector('[gridid=' + gridCode + ']');

    let minHeaderHeight=16
    let headerCells = gridDiv.querySelectorAll(".ag-header-row .ag-header-cell-label");
    getGridOptionFromGridMap(gridCode).api.setHeaderHeight(minHeaderHeight);
    $.each(headerCells, function (index, cell) {

        minHeaderHeight = Math.max(minHeaderHeight, cell.scrollHeight);
    });
    getGridOptionFromGridMap(gridCode).api.setHeaderHeight(minHeaderHeight + 10);


    let minGroupHeaderHeight=16
    let groupHeaderCells = gridDiv.querySelectorAll(".ag-header-row .ag-header-group-cell-label");
    getGridOptionFromGridMap(gridCode).api.setGroupHeaderHeight(minGroupHeaderHeight);
    $.each(groupHeaderCells, function (index, cell) {
        minGroupHeaderHeight = Math.max(minGroupHeaderHeight, cell.scrollHeight);
    });
    getGridOptionFromGridMap(gridCode).api.setGroupHeaderHeight(minGroupHeaderHeight + 10);


}


function getGridCodeFromParams(params){
    if(params.api.gridOptionsWrapper.gridOptions.gridCode!=undefined){
        return params.api.gridOptionsWrapper.gridOptions.gridCode
    }else{
        return '';
    }

}

function getGridDiv(gridCode){
    return document.querySelector('[gridid=' + gridCode + ']');
}
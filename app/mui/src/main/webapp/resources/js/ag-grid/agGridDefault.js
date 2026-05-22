var isRollback = false;
var updatedCellsList = null;
var nodeGroupTrue = "";
var nodeGroupFalse = "";
var result_master = {};
var selectedSelectionsMap = {};
var map_grid_columns=[];
var map_grid_groupRowAggNodes_true=[];
var map_grid_groupRowAggNodes_false=[];

var localeText = AG_GRID_LOCALE_IT;

function getContextMenuItems(params) {
    let cellValue = (params.value!=undefined) ? params.value:''
    var result = [{
        name: 'Valore Cella ' + ((cellValue!='')?('"'+cellValue+'"'):'(vuoto)'), action: function () {
            window.alert('Valore Cella "' + params.value+'"');
        }, disabled: false, tooltip: "alert the cell value"
    }, "separator", "separator", "expandAll", "contractAll", "autoSizeAll"];
    return result;
}

defaultGridOptions = {
    autoGroupColumnDef: {},
    groupDefaultExpanded: -1,
    //rememberGroupStateWhenNewData: true,
    suppressAggFuncInHeader: true,
    enableCellChangeFlash: true,
    animateRows: true,
    suppressRowClickSelection: false,
	suppressCopyRowsToClipboard: true,
    defaultColDef: {
        filter:true,
        sortable:true,
        resizable:true,
        enableValue: true, 
        enableRowGroup: true, 
        menuTabs: ["filterMenuTab", "columnsMenuTab"]
    },
    columnDefs: [],
    rowClassRules: null,
    rowData: null,
    enableRangeSelection: true,
    paginationAutoPageSize: true,
    pagination: false,
	localeText: localeText,
    getContextMenuItems: getContextMenuItems,
    allowContextMenuWithControlKey: true,
    onCellValueChanged: cellPut,
    onPasteStart: prepareMultipleCellPut,
    onPasteEnd: executeMultipleCellPut,
    columnTypes: AxAgColumnTypes,
    components: {
        numericCellEditor: NumericCellEditor,
        checkboxCellEditor: CheckboxCellEditor,
        checkboxCellRenderer: CheckboxCellRenderer,
        dateCellEditor: DateCellEditor,
        dateCellRenderer: DateCellRenderer,
        numericCellEditorK: NumericCellEditorK,
        numericCellRendererK: NumericCellRendererK,
        numericCellEditorInt: NumericCellEditorInt,
        numericCellRendererInt: NumericCellRendererInt,
        picklistCellEditor: PicklistCellEditor,
        uneditableCellEditor: UneditableCellEditor
    },
    excelStyles: [{id: "dateFormat", numberFormat: {format: "ddd dd/mm/yyyy"}}],
    onBodyScroll: function (params) {
        if(params.left%10==0){
            autosizeHeaders(params);
		}
    },
    onColumnResized: function (params) {
        autosizeHeaders(params);
    },
    onColumnVisible: onColumnVisible,
    onColumnGroupOpened: function () {
        var params = {finished: true, api: this.api};
        autosizeHeaders(params);
    },
    sideBar: {
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
    },
    aggFuncs: {weightedAvg: weightedAvg, customAvg: customAvg, customRatio: customRatio},
    processCellForClipboard: function (params) {
        if (params.column.colDef.type.includes("TM1DataColumnDate")) {
            return standardFormatDate(params.value);
        }
        return params.value;
    },
};

function asynchGridLoad(xhr, status, args) {
    if (typeof (args) == "undefined") {
        //console.log("WARN: args is undefined");
        return;
    }
    if (args.columnDefs === undefined) {
        //console.log("WARN: args.columnDefs is undefined");
        return;
    }
    var columnDefs = $.parseJSON(args.columnDefs);
    var axremote = args.axremote;
    var gridId = args.grid;
    var eGridDiv = $("div[gridid='" + gridId + "']")[0];
    var reload = false;
    if (args.reload === undefined) {
    } else {
        reload = args.reload;
    }
    var gridOptions = eGridDiv.gridOptions;
    if (gridOptions === undefined) {
        gridOptions = Object.assign({}, defaultGridOptions);
    }
    var autoGroupColumnDef = $.parseJSON(args.autoGroupColumnDef);
    var ctx = args.contextPath;
    var jsonFilter = args.jsonFilter;
	
	
    gridOptions.columnDefs = columnDefs;
    gridOptions.autoGroupColumnDef = autoGroupColumnDef;
	
	
	
	
	
	
	
	
    if (args.pagination != null && args.pagination !== "") {
        gridOptions.pagination = args.pagination;
    }
    if (args.suppressRowClickSelection != null && args.suppressRowClickSelection !== "") {
        gridOptions.suppressRowClickSelection = args.suppressRowClickSelection;
    }
    if (args.rowClassRules != null && args.rowClassRules !== "") {
        try {
            gridOptions.rowClassRules = $.parseJSON(args.rowClassRules);
        } catch (e) {
            console.log("error parsing rowClassRules");
            console.log(args.rowClassRules);
            console.log(e);
        }
    }
    if (args.selections != null && args.selections !== "") {
        try {
            gridOptions.rowSelection = "single";
            selectedSelectionsMap[gridId] = $.parseJSON(args.selections);
            gridOptions.onSelectionChanged = gridOnSelectionChanged;
        } catch (e) {
            console.log("error parsing selections");
            console.log(args.selections);
            console.log(e);
        }
    }
    $("." + gridId).show();
    $("#loading_" + gridId).hide();
    refreshAggridComponent(eGridDiv, gridOptions);

    if (gridOptions.sideBar !== undefined && gridOptions.columnApi.columnModel.getAllDisplayedColumns().length > 0) {
        gridOptions.sideBar = undefined;
    }
    if (gridOptions.sideBar === undefined && gridOptions.columnApi.columnModel.getAllDisplayedColumns().length === 0) {
        gridOptions.sideBar = defaultGridOptions.sideBar;
    }
    refreshAggridComponent(eGridDiv, gridOptions);
    gridOptions.api.setRowData([]);
    gridOptions.api.showLoadingOverlay();
    adjustLineBreaks(gridId);
    $.ajax({
        type: "POST",
        url: ctx + "/agGridDataStream",
        contentType: "application/x-www-form-urlencoded;charset=ISO-8859-15",
        data: {axremote: axremote, jsonFilter: jsonFilter, gridId: gridId, reload: reload},
        success: function (data) {
            if (data.error) {
                gridOptions.api.setRowData($.parseJSON("[]"));
                gridOptions.api.hideOverlay();
                gridOptions.api.setColumnDefs([]);
                gridOptions.api.showNoRowsOverlay();
                addInfoGrowl([{name: "title", value: data.error}, {
                    name: "message",
                    value: "Si prega di aggiungere filtri alla selezione corrente."
                }]);
            } else {
                if (data.maxrows) {
                    gridOptions.api.setColumnDefs([]);
                    gridOptions.api.showNoRowsOverlay();
                    addInfoGrowl([{name: "title", value: "Numero massimo di righe raggiunto"}, {
                        name: "message",
                        value: "Si prega di aggiungere filtri alla selezione corrente."
                    }]);
                } else {
                    if (data.data.length > 0) {
                        result_master = {};
                        nodeGroupTrue = "";
                        nodeGroupFalse = "";
                        if (args.groupRowAggNodes != null && args.groupRowAggNodes !== "") {
                            try {
                                var groupRowAggNodesParams = $.parseJSON(args.groupRowAggNodes);
                                gridOptions.columnApi.getAllColumns().forEach(function (currentValue, index, arr) {
                                    var field = "";
                                    var twopass = currentValue.colDef.field.indexOf(".");
                                    if (twopass < 0) {
                                        field = currentValue.colDef.field;
                                        result_master[field] = null;
                                    }
                                });


                                nodeGroupTrue = groupRowAggNodesParams.nodeGroupTrue;
                                nodeGroupFalse = groupRowAggNodesParams.nodeGroupFalse;
                                gridOptions.groupRowAggNodes = groupRowAggNodes;
                            } catch (e) {
                                result_master = {};
                                nodeGroupTrue = "";
                                nodeGroupFalse = "";
                                console.log("error in function groupRowAggNodes");
                                console.log(args.groupRowAggNodes);
                                console.log(e);
                            }
                        }

                        //Map grid - Global override
                        map_grid_columns[gridId]=result_master;
                        map_grid_groupRowAggNodes_true[gridId] = nodeGroupTrue;
                        map_grid_groupRowAggNodes_false[gridId] = nodeGroupFalse;


                        gridOptions.api.setRowData(data.data);
                        gridOptions.api.hideOverlay();
                        removeHiddenRows(gridOptions);
                        removeSuppressedCols(gridOptions, data.suppressedCols);
                        $("#" + gridId + "_quickfilter").trigger("keyup");
                    } else {
                        gridOptions.api.setColumnDefs([]);
                        gridOptions.api.showNoRowsOverlay();
                    }
                }
            }
        },
        done: null,
        fail: null,
        always: null,
        error: function (jqXHR, exception) {
            var msg = "";
            if (jqXHR.status === 0) {
                msg = "Not connected.\n Verify Network.";
            } else {
                if (jqXHR.status == 404) {
                    msg = "Requested page not found. [404]";
                } else {
                    if (jqXHR.status == 429) {
                        msg = "Richiesta gia inviata";
                    } else {
                        if (jqXHR.status == 500) {
                            if (jqXHR.responseText === undefined) {
                                msg = "Internal Server Error [500].";
                            } else {
                                msg = jqXHR.responseText;
                            }
                        } else {
                            if (jqXHR.status == 503) {
                                if (jqXHR.responseText === undefined) {
                                    msg = "Timeout waiting for response[503].";
                                } else {
                                    msg = jqXHR.responseText;
                                }
                                addWarningGrowl([{name: "title", value: "Timeout"}, {name: "message", value: msg}]);
                                gridOptions.api.setRowData($.parseJSON("[]"));
                                gridOptions.api.hideOverlay();
                            } else {
                                if (exception === "parsererror") {
                                    msg = "Requested JSON parse failed.";
                                } else {
                                    if (exception === "timeout") {
                                        msg = "Time out error.";
                                    } else {
                                        if (exception === "abort") {
                                            msg = "Ajax request aborted.";
                                        } else {
                                            msg = "Uncaught Error.\n" + jqXHR.responseText;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            console.log(msg);
            console.log(jqXHR.responseText);
        }
    });
    var params = {finished: true, api: gridOptions.api};
    autosizeHeaders(params);
}

function clearGridData(grid) {
    var eGridDiv = $("div[gridid='" + grid + "']")[0];
    if (eGridDiv != null && eGridDiv.gridOptions != null) {
        eGridDiv.gridOptions.api.setRowData([]);
        eGridDiv.gridOptions.api.setColumnDefs([]);
        eGridDiv.gridOptions.api.showNoRowsOverlay();
    }
}

function weightedAvg() {
    console.log("Tipo aggregazione 'weightedAvg' disponibile solo con aggregazione custom.");
    return "";
}

function customAvg() {
    console.log("Tipo aggregazione 'customAvg' disponibile solo con aggregazione custom.");
    return "";
}

function customRatio() {
    console.log("Tipo aggregazione 'customRatio' disponibile solo con aggregazione custom.");
    return "";
}

function refreshAggridComponent(eGridDiv, gridOptions) {
    eGridDiv.gridOptions = gridOptions;
    if (eGridDiv.children.length > 0) {
        eGridDiv.removeChild(eGridDiv.children[0]);
    }
    new agGrid.Grid(eGridDiv, gridOptions);
}

function onColumnVisible(event) {
    var gridId = event.api.gridOptionsWrapper.environment.eGridDiv.getAttribute("gridid");
    var cols = "[";
    $.each(event.columns, function (index, col) {
        cols += '{ "colId" : "' + col.colId + '", "visible" : "' + col.visible + '" },';
    });
    if (cols.endsWith(",")) {
        cols = cols.substring(0, cols.length - 1);
    }
    cols += "]";
    persistHiddenColumns([{name: "grid", value: gridId}, {name: "cols", value: cols}]);
    var eGridDiv = $("div[gridid='" + gridId + "']")[0];
    var gridOptions = Object.assign({}, eGridDiv.gridOptions);
    if (gridOptions.sideBar !== undefined && gridOptions.columnApi.columnModel.getAllDisplayedColumns().length > 0) {
        updateRemoteView([{name: "grid", value: gridId}]);
    }
    if (gridOptions.sideBar === undefined && gridOptions.columnApi.columnModel.getAllDisplayedColumns().length === 0) {
        updateRemoteView([{name: "grid", value: gridId}]);
    }
}

function removeSuppressedCols(gridOptions, suppressedCols) {
    if (suppressedCols != null && suppressedCols.length > 0) {
        var columnDefs = addNotSuppressedChild(gridOptions.columnDefs, suppressedCols);
        gridOptions.api.setColumnDefs(columnDefs);
    }
}

function addNotSuppressedChild(columnDefs, suppressedCols) {
    var cols = [];
    columnDefs.forEach(function (columnDef) {
        if (columnDef.children !== undefined) {
            columnDef.children = addNotSuppressedChild(columnDef.children, suppressedCols);
            cols.push(columnDef);
        } else {
            if (!suppressedCols.includes(columnDef.field)) {
                cols.push(columnDef);
            }
        }
    });
    return cols;
}

function removeHiddenRows(gridOptions) {
    if (gridOptions.rowClassRules != null && gridOptions.rowClassRules.row_style_hidden != null) {
        var removeRows = [];
        gridOptions.api.forEachNode(function (node) {
            var data = node.data;
            if ("data" in node && typeof node.data != "undefined" && eval(gridOptions.rowClassRules.row_style_hidden)) {
                removeRows.push(data);
            }
        });
        gridOptions.api.updateRowData({remove: removeRows});
    }
}

function groupRowAggNodes(nodes) {
    var result = Object.assign({}, result_master);
    var weightedAvgMap = {};
    var customAvgMap = {};
    var avgMap = {};
    var customRatioParam1Map = {};
    var customRatioParam2Map = {};
    var customRatioParam3Map = {};
    nodes.forEach(function (node) {
        var data = node.group ? node.aggData : node.data;
        if (node.group) {
            if (nodeGroupTrue !== "false") {
                if ((node.level - 1) === 0) {
                    node.allLeafChildren.forEach(function (leafChild) {
                        for (var property in result) {
                            if (result.hasOwnProperty(property) && node.beans.columnApi.getColumn(property)) {
                                var aggFunc =node.beans.columnApi.getColumn(property).getAggFunc();
                                var path = property.split("$");
                                var field = property.replace("$" + path[path.length - 1], node.beans.columnApi.getColumn(property).getColDef().aggFuncParam);
                                if (eval(nodeGroupTrue)) {
                                    if (typeof leafChild.data[property] != "undefined") {
                                        if (aggFunc === "sum") {
                                            var value = Number(result[property]) + Number(leafChild.data[property].Value !== "null" ? leafChild.data[property].Value : null);
                                            result[property] = value === 0 ? "" : value;
                                        } else if (aggFunc === "weightedAvg") {
                                            if (field == null || field === "" || leafChild.data[field] == null) {
                                                console.log("weightedAvg: Campo aggFuncParam non valorizzato o errato in configurazione: " + field);
                                                return null;
                                            }
                                            weightedAvgMap[property] = field;
                                            var value = Number(result[property]) + Number(leafChild.data[property].Value !== "null" ? leafChild.data[property].Value * leafChild.data[field].Value : null);
                                            result[property] = value === 0 ? "" : value;
                                        } else if (aggFunc === "customAvg") {
                                            if (field == null || field === "" || leafChild.data[field] == null) {
                                                console.log("customAvg: Campo aggFuncParam non valorizzato o errato in configurazione: " + field);
                                                return null;
                                            }
                                            customAvgMap[property] = field;
                                            var value = leafChild.data[field].Value;
                                            result[property] = value === 0 ? "" : value;
                                        } else if (aggFunc === "avg") {
                                            var count = avgMap[property];
                                            if (count === undefined) {
                                                count = 0;
                                            }
                                            value = Number(result[property]);
                                            var newValue = Number(leafChild.data[property].Value !== "null" ? leafChild.data[property].Value : null);
                                            if (newValue !== 0) {
                                                avgMap[property] = count + 1;
                                                value += newValue;
                                            }
                                            result[property] = value === 0 ? "" : value;
                                        } else if (aggFunc === "customRatio") {
                                            var field2 = property.replace("$" + path[path.length - 1], node.beans.columnApi.getColumn(property).getColDef().aggFuncParam2);
                                            if (field == null || field === "" || leafChild.data[field] == null ||
                                                field2 == null || field2 === "" || leafChild.data[field2] == null) {
                                                console.log("customRatio: Campi aggFuncParam e aggFuncParam2 non valorizzati o errati in configurazione: " + field + " , " + field2);
                                                return null;
                                            }
                                            customRatioParam1Map[property] = field;
                                            customRatioParam2Map[property] = field2;
                                            if(node.beans.columnApi.getColumn(property).getColDef().aggFuncParam3 != null){
                                                var field3 =node.beans.columnApi.getColumn(property).getColDef().aggFuncParam3;
                                                customRatioParam3Map[property] = field3;
                                            }
                                            var value = leafChild.data[field].Value;
                                            result[property] = value === 0 ? "" : value;
                                        }
                                    }
                                }
                            }
                        }
                    });
                } else {
                    node.allLeafChildren.forEach(function (leafChild) {
                        for (var property in result) {
                            if (result.hasOwnProperty(property) && node.beans.columnApi.getColumn(property)) {
                                var aggFunc = node.beans.columnApi.getColumn(property).getAggFunc();
                                var path = property.split("$");
                                var field = property.replace("$" + path[path.length - 1], node.beans.columnApi.getColumn(property).getColDef().aggFuncParam);
                                if (typeof leafChild.data[property] != "undefined") {
                                    if (aggFunc === "sum") {
                                        var value = Number(result[property]) + Number(leafChild.data[property].Value !== "null" ? leafChild.data[property].Value : null);
                                        result[property] = value === 0 ? "" : value;
                                    } else if (aggFunc === "weightedAvg") {
                                        if (field == null || field === "" || leafChild.data[field] == null) {
                                            console.log("weightedAvg: Campo aggFuncParam non valorizzato o errato in configurazione: " + field);
                                            return null;
                                        }
                                        weightedAvgMap[property] = field;
                                        var value = Number(result[property]) + Number(leafChild.data[property].Value !== "null" ? leafChild.data[property].Value * leafChild.data[field].Value : null);
                                        result[property] = value === 0 ? "" : value;
                                    } else if (aggFunc === "customAvg") {
                                        if (field == null || field === "" || leafChild.data[field] == null) {
                                            console.log("customAvg: Campo aggFuncParam non valorizzato o errato in configurazione: " + field);
                                            return null;
                                        }
                                        customAvgMap[property] = field;
                                        var value = leafChild.data[field].Value;
                                        result[property] = value === 0 ? "" : value;
                                    } else if (aggFunc === "avg") {
                                        var count = avgMap[property];
                                        if (count === undefined) {
                                            count = 0;
                                        }
                                        value = Number(result[property]);
                                        var newValue = Number(leafChild.data[property].Value !== "null" ? leafChild.data[property].Value : null);
                                        if (newValue !== 0) {
                                            avgMap[property] = count + 1;
                                            value += newValue;
                                        }
                                        result[property] = value === 0 ? "" : value;
                                    } else if (aggFunc === "customRatio") {
                                        var field2 = property.replace("$" + path[path.length - 1], node.beans.columnApi.getColumn(property).getColDef().aggFuncParam2);
                                        if (field == null || field === "" || leafChild.data[field] == null ||
                                            field2 == null || field2 === "" || leafChild.data[field2] == null) {
                                            console.log("customRatio: Campi aggFuncParam e aggFuncParam2 non valorizzati o errati in configurazione: " + field + " , " + field2);
                                            return null;
                                        }
                                        customRatioParam1Map[property] = field;
                                        customRatioParam2Map[property] = field2;
                                        if(node.beans.columnApi.getColumn(property).getColDef().aggFuncParam3 != null){
                                            var field3 = node.beans.columnApi.getColumn(property).getColDef().aggFuncParam3;
                                            customRatioParam3Map[property] = field3;
                                        }
                                        var value = leafChild.data[field].Value;
                                        result[property] = value === 0 ? "" : value;
                                    }
                                }
                            }
                        }
                    });
                }
            }
        } else {
            for (var property in result) {
                if (result.hasOwnProperty(property) && node.beans.columnApi.getColumn(property)) {
                    var aggFunc = node.beans.columnApi.getColumn(property).getAggFunc();
                    var path = property.split("$");
                    var field = property.replace("$" + path[path.length - 1], node.beans.columnApi.getColumn(property).getColDef().aggFuncParam);
                    if (eval(nodeGroupFalse)) {
                        if (typeof data[property] != "undefined") {
                            if (aggFunc === "sum") {
                                var value = Number(result[property]) + Number(data[property].Value !== "null" ? data[property].Value : null);
                                result[property] = value === 0 ? "" : value;
                            } else if (aggFunc === "weightedAvg") {
                                if (field == null || field === "" || data[field] == null) {
                                    console.log("weightedAvg: Campo aggFuncParam non valorizzato o errato in configurazione: " + field);
                                    return null;
                                }
                                weightedAvgMap[property] = field;
                                var value = Number(result[property]) + Number(data[property].Value !== "null" ? data[property].Value * data[field].Value : null);
                                result[property] = value === 0 ? "" : value;
                            } else if (aggFunc === "customAvg") {
                                if (field == null || field === "" || data[field] == null) {
                                    console.log("customAvg: Campo aggFuncParam non valorizzato o errato in configurazione: " + field);
                                    return null;
                                }
                                customAvgMap[property] = field;
                                var value = data[field].Value;
                                result[property] = value === 0 ? "" : value;
                            } else if (aggFunc === "avg") {
                                var count = avgMap[property];
                                if (count === undefined) {
                                    count = 0;
                                }
                                value = Number(result[property]);
                                var newValue = Number(data[property].Value !== "null" ? data[property].Value : null);
                                if (newValue !== 0) {
                                    avgMap[property] = count + 1;
                                    value += newValue;
                                }
                                result[property] = value === 0 ? "" : value;
                            } else if (aggFunc === "customRatio") {
                                var field2 = property.replace("$" + path[path.length - 1], node.beans.columnApi.getColumn(property).getColDef().aggFuncParam2);
                                if (field == null || field === "" || data[field] == null ||
                                    field2 == null || field2 === "" || data[field2] == null) {
                                    console.log("customRatio: Campi aggFuncParam e aggFuncParam2 non valorizzati o errati in configurazione: " + field + " , " + field2);
                                    return null;
                                }
                                customRatioParam1Map[property] = field;
                                customRatioParam2Map[property] = field2;
                                if(node.beans.columnApi.getColumn(property).getColDef().aggFuncParam3 != null){
                                    var field3 = node.beans.columnApi.getColumn(property).getColDef().aggFuncParam3;
                                    customRatioParam3Map[property] = field3;
                                }
                                var value = data[field].Value;
                                result[property] = value === 0 ? "" : value;
                            }
                        }
                    }
                }
            }
        }
    });
    if (Object.keys(weightedAvgMap).length > 0) {
        for (var property in result) {
            if (result.hasOwnProperty(property)) {
                var field = weightedAvgMap[property];
                if (field !== undefined) {
                    var value = Number(Number(result[property]) / Number(result[field]));
                    result[property] = value === 0 ? "" : value;
                }
            }
        }
    }else if (Object.keys(customAvgMap).length > 0) {
        for (var property in result) {
            if (result.hasOwnProperty(property)) {
                var field = customAvgMap[property];
                if (field !== undefined) {
                    var value = Number(result[property]);
                    result[property] = value === 0 ? "" : value;
                }
            }
        }
    }else if (Object.keys(avgMap).length > 0) {
        for (var property in result) {
            if (result.hasOwnProperty(property)) {
                var count = avgMap[property];
                if (count !== undefined) {
                    var value = Number(Number(result[property]) / count);
                    result[property] = value === 0 ? "" : value;
                }
            }
        }
    }else if (Object.keys(customRatioParam1Map).length > 0 && Object.keys(customRatioParam2Map).length > 0 ) {
        for (var property in result) {
            if (result.hasOwnProperty(property)) {
                var field = customRatioParam1Map[property];
                var field2 = customRatioParam2Map[property];
                var field3 = customRatioParam3Map[property];
                if (field !== undefined && field2 !== undefined) {
                    var value = Number(result[field] / result[field2] * (!isNaN(Number(field3)) ? Number(field3) : 1 ));
                    result[property] = value === 0 ? "" : value;
                }
            }
        }
    }
    return result;
}




function gridOnSelectionChanged() {
    var gridId = this.api.gridOptionsWrapper.environment.eGridDiv.getAttribute("gridid");
    var gridOptions = this.api.gridOptionsWrapper.gridOptions;
    var selectedRowsPromo = gridOptions.api.getSelectedRows();
    var gridIdMap = {};
    var temporaryFilters = "[";
    $.each(selectedRowsPromo, function (index, selectedRow) {
        $.each(selectedSelectionsMap[gridId], function (index, selection) {
            if (gridId === selection.source.table) {
                var sourceValues = eval("selectedRow." + selection.source.dimension + "." + selection.source.attribute);
                var sourceTable = selection.source.table;
                var visible = selection.visible;
                $.each(selection.destination, function (index, destination) {
                    var destinationTable = destination.table;
                    if (sourceTable === destinationTable) {
                        destinationTable = "none";
                    }
                    temporaryFilters += '{ "grid" : "' + destinationTable + '", "visible" : ' + visible + ', "values" : ["' + sourceValues + '"], "dimension" : "' + destination.dimension + '", "attribute" : "' + destination.attribute + '" },';
                    gridIdMap[destinationTable] = "grid";
                });
            }
        });
    });
    if (temporaryFilters.endsWith(",")) {
        temporaryFilters = temporaryFilters.substring(0, temporaryFilters.length - 1);
    }
    temporaryFilters += "]";
    for (var key in gridIdMap) {
        if (gridIdMap.hasOwnProperty(key)) {
            updateRemoteView([{name: "grid", value: key}, {name: "temporaryFilters", value: temporaryFilters}]);
        }
    }
}

function onFilterTextBoxChangedpromoGrid(gridContainerClass, value) {
    setTimeout(function () {
        document.querySelector("." + gridContainerClass).gridOptions.api.setQuickFilter(value);
    }, 200);
}

function onBtExport(gridContainerClass, fileName) {
    var gridOptions = document.querySelector("." + gridContainerClass).gridOptions;
    var params = {
        skipHeader: false,
        columnGroups: true,
        skipFooters: false,
        skipRowGroups: true,
        skipPinnedTop: false,
        skipPinnedBottom: false,
        allColumns: false,
        onlySelected: false,
        fileName: fileName,
        sheetName: fileName,
        exportMode: "xlsx"
    };
    gridOptions.api.exportDataAsExcel(params);
}

function onBtExportGrouped(gridContainerClass, fileName) {
    var gridOptions = document.querySelector("." + gridContainerClass).gridOptions;
    var params = {
        skipHeader: false,
        columnGroups: true,
        skipFooters: false,
        skipRowGroups: false,
        skipPinnedTop: false,
        skipPinnedBottom: false,
        allColumns: false,
        onlySelected: false,
        fileName: fileName+"_raggr",
        sheetName: fileName,
        exportMode: "xlsx"
    };
    gridOptions.api.exportDataAsExcel(params);
}

function asynchUpdateGrid(xhr, status, args) {
    var rowUpdated = args.updateResult;
    var eGridDiv = $("div[gridid='" + args.grid + "']")[0];
    if (args.nodeId === null || args.nodeId === undefined) {
        PF("globalSpinner").hide();
        return;
    }

    result_master = map_grid_columns[args.grid];
    
    nodeGroupTrue = map_grid_groupRowAggNodes_true[args.grid];
    nodeGroupFalse = map_grid_groupRowAggNodes_false[args.grid];
    
    try {
        let checkNodeId=JSON.parse(args.nodeId)
    } catch (error) {
        PF("globalSpinner").hide();
        console.error("AsynchUpdateGrid => Parse Error: >> "+ args.nodeId+" << to json obj")
        return;
    }

    var nodes = JSON.parse(args.nodeId);
    isRollback = true;
    var count = 0;
    $.each(nodes, function (index, node) {
        var rowNode = eGridDiv.gridOptions.api.getRowNode(node);
        if (typeof rowNode !== "undefined") {
            if (!rowUpdated && nodes.length === 1) {
                rowNode.setDataValue(args.columns, args.oldValue);
                addWarningGrowl([{name: "title", value: "Impossibile eseguire update"}, {
                    name: "message",
                    value: "Alcune celle potrebbero non essere state aggiornate a causa di regole impostate nel sistema."
                }]);
                rowUpdated = true;
            } else {
                if (typeof $.parseJSON(args.data).data[count] !== "undefined") {
                    rowNode.setData($.parseJSON(args.data).data[count]);
                    eGridDiv.gridOptions.api.redrawRows([node]);
                }
            }
        }
        count++;
    });
    eGridDiv.gridOptions.api.refreshClientSideRowModel("aggregate");
    if (!rowUpdated) {
        addWarningGrowl([{name: "title", value: "Impossibile incollare tutte le celle."}, {
            name: "message",
            value: "Alcune celle potrebbero non essere state aggiornate a causa di regole impostate nel sistema."
        }]);
    }
    isRollback = false;
    PF("globalSpinner").hide();
}

function cellPut(params) {
    if (params.data[params.column.colId].Updateable) {
        var data = {};
        data.rows = params.data.RowKeys;
        data.columns = params.column.colId;
        data.oldValue = params.oldValue;
        data.newValue = params.newValue;
        data.url = params.data.WriteBackUrl;

        data.grid = params.node.beans.gridOptionsWrapper.environment.eGridDiv.getAttribute('gridid');
        data.nodeId = params.node.id;
        var jsonData = JSON.stringify(data);
        if (updatedCellsList === null) {
            if (!isRollback) {
                updatedCellsList = [];
                updatedCellsList.push(jsonData);
                updateCell([{name: "data", value: "[" + updatedCellsList + "]"}]);
                updatedCellsList = null;
            } else {
                isRollback = false;
            }
        } else {
            updatedCellsList.push(jsonData);
        }
    }
}

function prepareMultipleCellPut(params) {
    updatedCellsList = [];
}

function executeMultipleCellPut(params) {
    if (updatedCellsList.length > 0) {
        PF("globalSpinner").show();
        updateCell([{name: "data", value: "[" + updatedCellsList + "]"}]);
    }
    updatedCellsList = null;
}

function multipleDeleteCells(params) {
    var ranges = params.api.getCellRanges();
    var api = params.api;
    ranges.forEach(function (range, index) {
        var startRow = Math.min(range.startRow.rowIndex, range.endRow.rowIndex);
        var endRow = Math.max(range.startRow.rowIndex, range.endRow.rowIndex);
        for (var rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
            range.columns.forEach(function (column) {
                var rowModel = api.getModel();
                var rowNode = rowModel.getRow(rowIndex);
                if (rowNode.data != null && rowNode.data[column.colId].Updateable) {
                    var data = {};
                    data.rows = rowNode.data.RowKeys;
                    data.columns = column.colId;
                    data.oldValue = api.getValue(column, rowNode);
                    data.newValue = "";
                    data.url = "";
                    data.grid = params.node.beans.gridOptionsWrapper.environment.eGridDiv.getAttribute('gridid');
                    data.nodeId = Number(rowNode.id);
                    var jsonData = JSON.stringify(data);
                    updatedCellsList.push(jsonData);
                }
            });
        }
    });
}

function autosizeHeaders(event) {
    
    if(event.direction!=undefined && event.direction=='vertical'){
		return false;
    }
    
    
        $(".gridPicklist").hide().remove();
        event.api.setHeaderHeight(16);
        event.api.setGroupHeaderHeight(16);
        var gridId = event.api.gridOptionsWrapper.environment.eGridDiv.getAttribute("gridid");
        adjustLineBreaks(gridId);
        var minHeaderHeight = 16;
        var headerCells = document.querySelectorAll("." + gridId + " .ag-header-row .ag-header-cell-label");
        $.each(headerCells, function (index, cell) {
            minHeaderHeight = Math.max(minHeaderHeight, cell.scrollHeight);
        });
        event.api.setHeaderHeight(minHeaderHeight + 10);
        var minGroupHeaderHeight = 16;
        var groupHeaderCells = document.querySelectorAll("." + gridId + " .ag-header-row .ag-header-group-cell-label");
        $.each(groupHeaderCells, function (index, cell) {
            minGroupHeaderHeight = Math.max(minGroupHeaderHeight, cell.scrollHeight);
        });
        event.api.setGroupHeaderHeight(minGroupHeaderHeight + 10);
    
}

function expandGrid(id) {
    var container = $(".expandable_" + id);
    container.toggleClass("expandableDiv");
    container.toggleClass("fullscreen");
    $("." + id).toggleClass("gridFullscreen");
}

function suppressStatus(gridId, type) {
    var className = type === "columns" ? ".settingsRadioCols" : ".settingsRadioRows";
    var selector = $(".expandable_" + gridId).find(className);
    var checked = !selector.hasClass("selected");
    toggleNonEmpty([{name: "grid", value: gridId}, {name: "toggle", value: checked}, {name: "type", value: type}]);
}

function setSuppressStatus(gridId, type, value) {
    var className = type === "columns" ? ".settingsRadioCols" : ".settingsRadioRows";
    var selector = $("#expandable_" + gridId).find(className);
    if (eval(value)) {
        selector.toggleClass("selected");
    }
}

function setLoadingStatus(button) {
    $(button).prop("disabled", true);
    $(button).css({"min-width": $(button).width(),});
    $(button).html("");
    $(button).append("<i class='buttonSpinner'/>");
}

function showSpinner(gridId) {
    $("#loading_" + gridId).show();
    $("." + gridId).hide();
}

function adjustLineBreaks(gridId) {
    $.each($("#expandable_" + gridId).find(".ag-header-cell-text"), function (key, value) {
        var html = value.innerHTML;
        value.innerHTML = html.replace("\\n", "<br/>");
    });
}
var AxColumnTypes = {
    string: {
        valueGetter: AX_ValueGetter,
        valueFormatter: AX_ValueFormatter,
        cellEditorSelector: AX_CellEditorSelector,
        valueSetter: AX_CellValueSetter,
        valueParser: AX_CellValueParser,
        cellStyle: AX_CellStyle,
        cellRendererSelector: AX_CellRendererSelector,
    },
    numeric: {
        valueGetter: AX_ValueGetter,
        valueFormatter: AX_ValueFormatter,
        cellEditorSelector: AX_CellEditorSelector,
        valueSetter: AX_CellValueSetter,
        valueParser: AX_CellValueParser,
        cellStyle: AX_CellStyle,
        cellRendererSelector: AX_CellRendererSelector,
    },
    date: {
        valueGetter: AX_ValueGetter,
        valueFormatter: AX_ValueFormatter,
        cellEditorSelector: AX_CellEditorSelector,
        valueSetter: AX_CellValueSetter,
        valueParser: AX_CellValueParser,
        cellStyle: AX_CellStyle,
        filterValueGetter: AX_filterValueGetter,
    },
    time: {
        valueGetter: AX_ValueGetter,
        valueFormatter: AX_ValueFormatter,
        cellEditorSelector: AX_CellEditorSelector,
        valueSetter: AX_CellValueSetter,
        valueParser: AX_CellValueParser,
        cellStyle: AX_CellStyle,
        filterValueGetter: AX_filterValueGetter,
    },
    checkbox: {
        cellStyle: AX_CellStyle
    },
    popupdialog: {
        cellStyle: AX_CellStyle,
        cellRendererSelector: AX_CellRendererSelector,
        cellEditorSelector: AX_CellEditorSelector
    },
    pickcolor: {
        cellStyle: AX_CellStyle,
        cellRendererSelector: AX_CellRendererSelector,
        cellEditorSelector: AX_CellEditorSelector
    }
}

function fillDefaultCellConfig(params) {
    var field = params.colDef.field
    var cell = eval("params.node.data." + field)

    try {
        var type = cell.type;
    } catch (e) {
        LT("columnTypes definizione Cella non corretta ====> ", field)
        return ""
    }

    var defCellConfig = {
        common: {
            value: "",
            editable: false,
            nullable: true,
        },
        string: {
            dataTypeParams: {
                length: 255
            }
        },
        checkbox: {},
        numeric: {
            dataTypeParams: {
                precision: 2
            }
        },
        picklist: {
            picklistValues: [],
            dataTypeParams: {}
        },
        comboBox: {
            comboBoxValues: [],
            dataTypeParams: {}
        },
        date: {
            dataTypeParams: {
                dateFormat: 'DDD dd/mm/yyyy'
            }
        },
        time: {
            dataTypeParams: {}
        },
        popupdialog: {}
    };

    // Create default cell obj if cell is not an obj
    if (typeof cell !== 'object') {
        type = 'string'
        eval("params.node.data." + field + "={}")
        eval("params.node.data." + field + ".value='" + cell + "'")
        eval("params.node.data." + field + ".type='string'")
        cell = eval("params.node.data." + field)
    }

    // Default if type is not set
    if (type === undefined) {
        type = 'string'
        eval("params.node.data." + field + ".type='string'")
    }

    // Presenza dataTypeParams
    if (cell.dataTypeParams === undefined) {
        let defaultDatatype = defCellConfig[type].dataTypeParams
        eval("params.node.data." + field + '.dataTypeParams=defaultDatatype')
    }

    // Default value
    if (cell.value === undefined) {
        eval("params.node.data." + field + ".value=defCellConfig['common'].value")
    }

    // Default editable
    if (cell.editable === undefined) {
        eval("params.node.data." + field + ".editable=defCellConfig['common'].editable")
    }

    // Default nullable
    if (cell.nullable === undefined) {
        eval("params.node.data." + field + ".nullable=defCellConfig['common'].nullable")
    }

    // *String* default
    if (type === 'string') {
        if (cell.dataTypeParams.length === undefined) {
            eval("params.node.data." + field + '.dataTypeParams.length=defCellConfig[type].dataTypeParams.length')
        }
    }

    // *Numeric* default
    if (type === 'numeric') {
        if (cell.dataTypeParams.precision === undefined) {
            eval("params.node.data." + field + '.dataTypeParams.precision=defCellConfig[type].dataTypeParams.precision')
        }
    }

    // *Picklist* default
    if (type === 'picklist') {
        if (cell.picklistValues === undefined) {
            eval("params.node.data." + field + '.picklistValues=defCellConfig[type].picklistValues')
        }
    }

    // *Combobox* default
    if (type === 'comboBox') {
        if (cell.comboBoxValues === undefined) {
            eval("params.node.data." + field + '.comboBoxValues=defCellConfig[type].comboBoxValues')
        }
    }
}

function evalCell(params, property) {
    try {
        return eval("params.node.data." + params.colDef.field + "." + property);
    } catch (e) {
        return "";
    }
}

function AX_ValueGetter(params) {
    // LT(params)
    var field = params.colDef.field;
    var type = evalCell(params, "type");
    var value = evalCell(params, "value");
    // FILL CELL CONFIGURATION PROPERTY WITH DEFAULT VALUE IF NOT EXISTS
    if (params.data !== undefined) {
        fillDefaultCellConfig(params);
    }

    if (type === "numeric") {
        if (value !== "" && value !== null && value !== undefined) {
            value = Number(value);
        } else {
            value = '';
        }
    }

    if (type === "checkbox") {
        if (value === "true" || value === true || value === 1) {
            value = true;
        } else {
            value = false;
        }
    }

    if (type === "comboBox") {
        let listOfValues = evalCell(params, "comboBoxValues");
        let resultItem = listOfValues.filter(item => {
            return item.key === value;
        })
        if (resultItem.length === 1) {
            return resultItem[0].label;
        }
    }

    var dataTypeParams = evalCell(params, "dataTypeParams");
    //Formula
    if (dataTypeParams !== undefined) {
        if (dataTypeParams.formula !== undefined && dataTypeParams.formula === true) {
            let formula = evalCell(params, "formula");
            if (formula !== '' && formula !== undefined) {
                let formulaJs = getPlainJSFormula(formula, params);
                let log_row_field_position = "[Row " + params.node.id + "][" + field + "]";
                if (formulaJs !== false) {
                    LT(log_row_field_position + " Plain formula Js: ", formulaJs)
                    try {
                        let computedValue = eval(formulaJs)
                        LT(log_row_field_position + " Computed value: ", computedValue)
                        eval("params.node.data." + field + '.value="' + computedValue + '"')
                        return computedValue
                    } catch (e) {
                        if (e instanceof SyntaxError) {
                            console.log(log_row_field_position + " Sintassi formula errata: " + e.message);
                        } else {
                            console.log(log_row_field_position + " Formula non corretta: " + e.message);
                        }

                        eval("params.node.data." + field + '.styleParams={}')
                        eval("params.node.data." + field + '.styleParams.backgroundColor="#FFA500"')
                    }
                } else {
                    eval("params.node.data." + field + '.styleParams={}')
                    eval("params.node.data." + field + '.styleParams.backgroundColor="#FFA500"')
                    console.log(log_row_field_position + " La formula non deve contenere la cella stessa");
                }
            }
        }
    }
    return value;
}

function getPlainJSFormula(formula, params) {
    let formulaJs = formula;
    var current_field = params.colDef.field;

    //verifico che nella formula non ci sia il campo corrente
    if (formula.indexOf(current_field) >= 0) {
        return false
    }

    //Determina il tipo campo dei placeholder della formula <<CAMPO>>
    let tags = formulaJs.match(/<<(.*?)\>>/gs);

    if (tags === null) {
        return formulaJs
    } else {
        tags.forEach(tag => {
            let evalReadyTag = tag
            evalReadyTag = evalReadyTag.replaceAll('<<', '')
            evalReadyTag = evalReadyTag.replaceAll('>>', '')
            nodeTag = 'params.node.data.' + evalReadyTag + '.value'
            let tag_type = eval("params.node.data." + evalReadyTag + '.type')
            if (tag_type === 'numeric' || tag_type === 'date') {
                evalReadyTag = evalReadyTag.replaceAll(evalReadyTag, 'Number(' + nodeTag + ')')
            } else {
                evalReadyTag = evalReadyTag.replaceAll(evalReadyTag, '' + nodeTag + '')
            }
            formulaJs = formulaJs.replaceAll(tag, evalReadyTag)
        });
        return formulaJs;
    }
}

function AX_ValueFormatter(params) {
    var field = params.colDef.field;
    var type = evalCell(params, "type");
    var value = params.value;

    // HACK: Nel caso di colonna raggruppamento, prendi il type direttamente dal columnDef
    if (params.node.group === true) {
        type = params.colDef.type;
    }

    if (type === "numeric") {
        if ("" + value === "NaN") {
            value = "";
        } else {
            if (value !== '' && value !== null) {
                let dataTypeParams = evalCell(params, "dataTypeParams");
                value = formatNumber(value, ',', '.', dataTypeParams.precision)
            }
        }
    }

    if (type === "date") {
        let dataTypeParams = evalCell(params, "dataTypeParams");
        let dateFormat = dataTypeParams.dateFormat
        if (dateFormat === 'DDD dd/mm/yyyy HH:MM.ss') {
            value = excelNumToCellDate(value, true);
        } else {
            value = excelNumToCellDate(value);
        }
    }
    return value;
}

function AX_filterValueGetter(params) {
    var field = params.colDef.field;
    var type = evalCell(params, "type");
    var value = evalCell(params, "value");
    if (type === "date") {
        value = excelNumToCellDate(value);
    }
    return value;
}

function AX_CellStyle(params) {
    // LT("Cell Style",params)
    var type = evalCell(params, "type");
    var isEditable = evalCell(params, 'editable');
    var textColor = 'black'
    var textAlign = 'left'
    var backgroundColor = 'inherit'
    var fontWeight = 'bold'

    // styleParams
    if (isEditable === true) {
        backgroundColor = '#fff'
        fontWeight = 'normal'
    }
    if (evalCell(params, 'styleParams') !== undefined) {
        let styleParams = evalCell(params, 'styleParams')
        if (styleParams.textColor !== undefined && styleParams.textColor !== '') {
            textColor = styleParams.textColor
        }
        if (styleParams.backgroundColor !== undefined && styleParams.backgroundColor !== '') {
            backgroundColor = styleParams.backgroundColor
        }
    }

    if (params.colDef.type === 'checkbox' || type == 'checkbox') {
        textAlign = 'center'
    }
    return {
        backgroundColor: backgroundColor,
        fontWeight: fontWeight,
        color: textColor,
        textAlign: textAlign
    };
}

function AX_CellEditorSelector(params) {
    // LT(params)
    var type = evalCell(params, "type");
    var isEditable = evalCell(params, 'editable');
    if (isEditable === true) {
        // Multiple Cell range delete
        if ("Delete" === event.code) {
            deleteCellsRange(params)
            return {}
        }

        if (type === "string") {
            return {
                component: "AxTextCellEditor"
            };
        } else if (type === "date") {
            return {
                component: "AxDateCellEditor"
            };
        } else if (type === "time") {
            return {
                component: "AxTimeCellEditor"
            };
        } else if (type === "numeric") {
            return {
                component: "AxNumericCellEditor"
            };
        } else if (type === "picklist") {
            return {
                component: 'AxPicklistCellEditor',
            };
        } else if (type === "comboBox") {
            return {
                component: 'AxPicklistCellEditor',
            };
        } else if (type === 'pickcolor') {
            return {
                component: 'AxPickcolorEditor'
            }
        }
    }
    return {
        component: "AxUneditableCellEditor"
    };
}

function AX_CellRendererSelector(params) {
    var type = evalCell(params, "type");
    if (type === 'picklist' || type === 'comboBox') {
        return {
            component: "AxPicklistRenderer"
        };
    }
    if (type === 'checkbox') {
        return {
            component: "AxCheckboxCellRenderer"
        };
    }
    if (type === 'popupdialog') {
        return {
            component: "AxOpenDialogRenderer"
        }
    }
    if (type === 'pickcolor') {
        return {
            component: "AxPickcolorRenderer"
        }
    }
}

function AX_CellValueParser(params) {
}

function AX_CellValueSetter(params) {
    var type = evalCell(params, 'type');
    var isEditable = evalCell(params, 'editable');
    var isNullable = evalCell(params, 'nullable');
    if (isEditable === true) {
        // Check not-nullable
        if (params.newValue === params.oldValue) {
            // Il valore è uguale al precedente
            return false;
        }
        // Se è not-nullable, verificare che il nuovo valore non sia empty
        if (isNullable === false) {
            if (params.newValue === '' || params.newValue === undefined || params.newValue === null) {
                return false;
            }
        } else {
            if (params.newValue === '' || params.newValue === undefined || params.newValue === null) {
                params.newValue = '';
                eval("params.node.data." + params.colDef.field + ".value= params.newValue");
                return true;
            }
        }
        if (type === "string") {
            eval("params.node.data." + params.colDef.field + ".value= params.newValue");
            return true;
        } else if (type === "checkbox") {
            if (params.newValue === true || params.newValue === "true" || params.newValue === 1) {
                params.newValue = "true"
            } else {
                params.newValue = "false"
            }
            eval("params.node.data." + params.colDef.field + ".value=params.newValue");
            return true
        } else if (type === "numeric") {
            params.newValue = Number(params.newValue).toFixed(evalCell(params, 'dataTypeParams.precision'))
            eval("params.node.data." + params.colDef.field + ".value= Number(params.newValue)");
            return true
        } else if (type === "date") {
            if (!(isNaN(unformatDate(invertFormatDate(params.newValue))))) {
                params.newValue = unformatDate(invertFormatDate(params.newValue));
                eval("params.node.data." + params.colDef.field + ".value= params.newValue");
            } else {
                if (standardFormatDate(params.newValue) != 'aN-aN-NaN') {
                    params.newValue = unformatDate(invertFormatDate(standardFormatDate(params.newValue)));
                    eval("params.node.data." + params.colDef.field + ".value= params.newValue");
                } else {
                    eval("params.node.data." + params.colDef.field + ".value=params.oldValue");
                }
            }
            return true;
        } else if (type === "picklist") {
            eval("params.node.data." + params.colDef.field + ".value= params.newValue");
            return true;
        } else {
            eval("params.node.data." + params.colDef.field + ".value= params.newValue");
            return true;
        }
    }
}

// Dynamic picklist and combobox
function getRemoteSelectOptionsValues(url) {
    let result = [];
    $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json",
        async: false,
        success: function (data) {
            result = JSON.parse(data.remoteValues)
        },
        error: function (jqXHR, exception) {
            CM('error', 'Errore! Verificare la risposta dell\'url ' + url)
            addErrorGrowl([{name: 'title', value: 'Attenzione'}, {
                name: 'message',
                value: 'Errore durante il recupero dei valori della tendina'
            }]);
        }
    });
    return result;
}

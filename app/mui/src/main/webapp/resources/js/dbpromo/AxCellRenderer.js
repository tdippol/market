//Unique btn factory
function gridActionButtonElem(typeBtn, customTitle) {
    var elem = '';
    let onHoverColor = '';
    elem = document.createElement("button");
    elem.className = 'ax-grid-btn ';
    if (typeBtn === 'trash') {
        onHoverColor = '#d83232';
        elem.innerHTML = '<i class="fas fa-trash-alt"></i>';
        elem.title = "Elimina";
    }
    if (typeBtn === 'plus') {
        onHoverColor = '#51ae53';
        elem.innerHTML = '<i class="fas fa-plus ax-grid-btn-icon"></i>';
        elem.title = "Aggiungi";
    }
    if (typeBtn === 'upload') {
        onHoverColor = '#516aae';
        elem.innerHTML = '<i class="fas fa-upload ax-grid-btn-icon"></i>';
        elem.title = "Carica file";
    }
    if (typeBtn === 'download') {
        onHoverColor = '#1cac8196';
        elem.innerHTML = '<i class="fas fa-download ax-grid-btn-icon"></i>';
        elem.title = "Scarica file";
    }
    if (typeBtn === 'publish') {
        onHoverColor = '#1cac8196';
        elem.innerHTML = '<i class="fas fa-file-export ax-grid-btn-icon"></i>';
        elem.title = "Pubblica";
    }
    if (typeBtn === 'empty') {
        onHoverColor = '#e3bb76';
        elem.innerHTML = '<i class="fas fa-eraser ax-grid-btn-icon"></i>';
        elem.title = "Svuota";
    }

    if (typeBtn === 'barcode') {
        onHoverColor = '#4e4466';
        elem.innerHTML = '<i class="fas fa-barcode ax-grid-btn-icon"></i>';
        elem.title = "Barcode";
    }
    if ( typeBtn === 'selectAllMedia'){
        onHoverColor = '#516aae';
        elem.innerHTML = '	<i class="fas fa-check-double"></i>';
        elem.title = "Seleziona tutti i media";
    }
    if ( typeBtn == 'addRowAbove'){
        onHoverColor = '#e3bb76';
        elem.innerHTML = '<i class="fas fa-arrow-alt-circle-up ax-grid-btn-icon"></i>';
        elem.title = "Aggiungi Sopra";
    }
    if (typeBtn === 'edit') {
        onHoverColor = '#f0ad4e';
        elem.innerHTML = '<i class="fas fa-edit ax-grid-btn-icon"></i>';
        elem.title = "Modifica";
    }
    elem.style.cssText = '--onHoverColor: ' + onHoverColor + ';';
    if (customTitle !== undefined && customTitle !== '') {
        elem.title = customTitle;
    }
    elem.onmouseover = function () {
        this.style.cursor = 'pointer';
    }
    return elem;
}

Pianificazione_BtnCellRenderer = function(params) {
    let addEnabled = params.data.addEnabled;
    let deleteEnabled = params.data.deleteEnabled;
    let emptyAllEnabled = params.data.emptyAllEnabled;
    let uploadEnabled = params.data.uploadEnabled;
    let barcodeEnabled = params.data.barcodeEnabled;
    let uploadLogoMessaggioEnabled = params.data.uploadLogoMessaggioEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    var cell_2 = row.insertCell(1);
    var cell_3 = row.insertCell(2);
    var cell_4 = row.insertCell(3);
    var cell_5 = row.insertCell(4);
    var cell_6 = row.insertCell(5);
    cell_1.style.width = '25px';
    cell_2.style.width = '25px';
    cell_3.style.width = '25px';
    cell_4.style.width = '25px';
    cell_5.style.width = '25px';
    cell_6.style.width = '25px';
    cell_1.style.padding = '0';
    cell_2.style.padding = '0';
    cell_3.style.padding = '0';
    cell_4.style.padding = '0';
    cell_5.style.padding = '0';
    cell_6.style.padding = '0';
    var div = document.createElement("div");
    //ADD___________________________
    var addSpan = document.createElement("span");
    if (addEnabled != undefined && addEnabled.value === "true") {
        var elem = gridActionButtonElem('plus');
        elem.onclick = function () {
            addPlanningRow([
                {name: 'tipoElemento', value: params.data.tipoElemento.value},
                {name: 'idPromoPianificazione', value: params.data.idPromoPianificazione.value},
                {name: 'descrizioneMeccanica', value: params.data.descrizioneMeccanica.value}
            ]);
        }
        addSpan.appendChild(elem);
    }
    cell_1.appendChild(addSpan);

    //UPLOAD_________________________
    var uploadSpan = document.createElement("span");
    if (uploadEnabled !== undefined && uploadEnabled.value === "true") {
        var elem = gridActionButtonElem('upload');
        elem.onclick = function () {
            setCurrentPlanningRow([{
                name: 'idPromoPianificazione',
                value: params.data.idPromoPianificazione.value
            }]);
        }
        uploadSpan.appendChild(elem);
    }
    cell_2.appendChild(uploadSpan);

    //EMPTY_______________
    var emptySpan = document.createElement("span");
    if (emptyAllEnabled !== undefined && emptyAllEnabled.value === "true") {
        var elem = gridActionButtonElem('empty');
        elem.onclick = function () {
            let itemToEmpty = [{name: 'tipoElemento', value: params.data.tipoElemento.value}];
            if (params.data.master !== undefined) {
                itemToEmpty.push({name: 'master', value: params.data.master.value});
            }
            if (params.data.tipoRiga !== undefined) {
                itemToEmpty.push({name: 'tipoRiga', value: params.data.tipoRiga.value});
            }
            if (params.data.numRaggruppamento !== undefined) {
                itemToEmpty.push({name: 'numRaggruppamento', value: params.data.numRaggruppamento.value});
            }
            if (params.data.descrizioneMeccanica !== undefined) {
                itemToEmpty.push({name: 'descrizioneMeccanica', value: params.data.descrizioneMeccanica.value});
            }
            if (params.data.masterDetails !== undefined) {
                itemToEmpty.push({name: 'masterDetails', value: params.data.masterDetails.value});
            }
            if (params.data.nomeElemento !== undefined) {
                itemToEmpty.push({name: 'nomeElemento', value: params.data.nomeElemento.value});
            }
            emptyPlanningRow(itemToEmpty);
            idRowToBeEmpty = params.data.idPromoPianificazione.value;
        }
        emptySpan.appendChild(elem);
    }
    cell_3.appendChild(emptySpan);

    //BARCODE_______________
    var barcodeSpan = document.createElement("span");
    if (barcodeEnabled != undefined && barcodeEnabled.value === "true") {
        var elem = gridActionButtonElem('barcode');
        elem.onclick = function () {
            openDialogBarcode(params.data.idPromoPianificazione.value);
        }
        barcodeSpan.appendChild(elem);
    }
    cell_5.appendChild(barcodeSpan);

    //DELETE_______________
    var cancelSpan = document.createElement("span");
    if (deleteEnabled != undefined && deleteEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            let itemToDelete = [{name: 'tipoElemento', value: params.data.tipoElemento.value}];
            if (params.data.master !== undefined) {
                itemToDelete.push({name: 'master', value: params.data.master.value});
            }
            if (params.data.tipoRiga !== undefined) {
                itemToDelete.push({name: 'tipoRiga', value: params.data.tipoRiga.value});
            }
            if (params.data.numRaggruppamento !== undefined) {
                itemToDelete.push({name: 'numRaggruppamento', value: params.data.numRaggruppamento.value});
            }
            if (params.data.descrizioneMeccanica !== undefined) {
                itemToDelete.push({name: 'descrizioneMeccanica', value: params.data.descrizioneMeccanica.value});
            }
            if (params.data.masterDetails !== undefined) {
                itemToDelete.push({name: 'masterDetails', value: params.data.masterDetails.value});
            }
            if (params.data.nomeElemento !== undefined) {
                itemToDelete.push({name: 'nomeElemento', value: params.data.nomeElemento.value});
            }
            if (params.data.minRaggruppamento !== undefined) {
                itemToDelete.push({name: 'minRaggruppamento', value: params.data.minRaggruppamento.value});
            }
            if (params.data.fratelliDelMaster !== undefined) {
                itemToDelete.push({name: 'fratelliDelMaster', value: params.data.fratelliDelMaster.value});
            }
            removePlanningRow(itemToDelete);
            idRowToBeDeleted = params.data.idPromoPianificazione.value;
        }
        cancelSpan.appendChild(elem);
    }
    cell_4.appendChild(cancelSpan);

    // UPLOAD LOGO E MESSAGGIO
    var uploadLogoMessaggioSpan = document.createElement("span");
    if (uploadLogoMessaggioEnabled !== undefined && uploadLogoMessaggioEnabled.value === "true") {
        var elem = gridActionButtonElem('upload', 'Carica Logo e Messaggi');
        elem.onclick = function () {
            handleUploadLogoMessaggio([{name: 'idPianificazione', value: params.data.idPromoPianificazione.value}]);
        }
        uploadLogoMessaggioSpan.appendChild(elem);
    }
    cell_6.appendChild(uploadLogoMessaggioSpan);
    //TODO aggiungere bottone aggiungi sopra solo se la riga corrente e' maggiore di 1
    div.appendChild(table);
    return div;
}

//PIANIFICAZIONE CONFIGURAZIONE - GRID HEADER
PianConfig_Header_BtnCellRenderer = function (params) {
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    cell_1.style.width = '25px'
    cell_1.style.padding = '0';
    var div = document.createElement("div");

    //DELETE_______________
    var cancelSpan = document.createElement("span");
    if (deleteEnabled != undefined && deleteEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            removePlanningHeader([{name: 'idHeader', value: params.data.id.value}]);
        }
        cancelSpan.appendChild(elem);
    }
    cell_1.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}

//PIANIFICAZIONE CONFIGURAZIONE - GRID TIPO ELEMENTO
PianConfig_TipoElemento_BtnCellRenderer = function (params) {
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    cell_1.style.width = '25px';
    cell_1.style.padding = '0';
    var div = document.createElement("div");

    //DELETE_______________
    var cancelSpan = document.createElement("span");
    if (deleteEnabled != undefined && deleteEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            removePlanningTipoElemento([
                {name: 'idHeader', value: params.data.idCfgConfHeader.value},
                {name: 'idTipoElemento', value: params.data.id.value},
            ]);
        }
        cancelSpan.appendChild(elem);
    }
    cell_1.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}

//PIANIFICAZIONE CONFIGURAZIONE - GRID REGOLE
PianConfig_Regole_BtnCellRenderer = function (params) {
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    cell_1.style.width = '25px';
    cell_1.style.padding = '0';
    var div = document.createElement("div");

    //DELETE_______________
    var cancelSpan = document.createElement("span");
    if (deleteEnabled != undefined && deleteEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            removePlanningRegola([
                {name: 'idHeader', value: params.data.idCfgConfHeader.value},
                {name: 'idCfgPianificazione', value: params.data.idCfgPianificazione.value},
            ]);
        }
        cancelSpan.appendChild(elem);
    }
    cell_1.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}

//CREA PROMO
CreaPromo_AddPromoRenderer = function(params) {
    var span = document.createElement("span");
    if (
        params.data.anno.value !== "" &&
        params.data.descrizione.value !== "" &&
        params.data.gruppo.value !== "" &&
        params.data.canale.value !== "" &&
        params.data.dataInizio.value !== "" &&
        params.data.dataFine.value !== "" &&
        (params.data.messaggio.value === "" || params.data.messaggio.value === undefined)
    ) {
        var elem = gridActionButtonElem('plus', 'Crea Promozione');
        elem.onclick = function () {
            rowIndex = $(this).parent().parent().parent().attr("row-id");
            PF('savePromoConfirmDialog').show();
        }
        span.appendChild(elem);
    }
    return span;
}

// Crea Piano Media
CreaPianoMedia_BtnRenderer = function (params) {
    let createEnabled = params.data.createEnabled;
    var span = document.createElement("span");
    if (createEnabled != undefined && createEnabled.value === "true") {
        var elem = gridActionButtonElem("plus", "Crea Piano Media");
        elem.onclick = function () {
            rowIndex = $(this).parent().parent().parent().attr("row-id");
            PF('savePianoMediaConfirmDialog').show();
        }
        span.appendChild(elem);
    }
    return span;
}

//MODIFICA PROMO
ModifcaPromo_DeletePromoRenderer = function (params) {
    if (params.data.isDeletable.value === 'false') {
        return '';
    }
    var span = document.createElement("span");
    var elem = gridActionButtonElem('trash');
    elem.onclick = function (event) {
        PF('rowData').jq.val(JSON.stringify(params.data));
        removePromo([{name: 'promoDescriptionToBeDeleted', value: params.data.descrizione.value}]);
    }
    span.appendChild(elem);
    return span;
}

//SCHEDA PROMO - MECCANICHE
SchedaPromoMeccaniche_DeleteMeccanicheRenderer = function(params) {
    var span = document.createElement("span");
    if(params.data.removeEnabled.value==="true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function (event) {
            event.preventDefault();
            idMeccanica = params.data.idPromozioneMeccaniche.value;
            $('.ui-confirm-dialog-message')
                .text('Confermi la cancellazione della meccanica ' + params.data.descrizioneEsteso.value + ' ?');
            PF('cancellaMeccanicaConfirmDialog').show();
        }
        span.appendChild(elem);
    }
    return span;
}

//SCHEDA PROMO - PLANOGRAM
SchedaPromoPlanogram_DeletePlanogramRenderer = function(params) {
    var span = document.createElement("span");
    if(params.data.removeEnabled.value==="true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function (event) {
            event.preventDefault();
            idPlano = params.data.idPlano.value;
            $('.ui-confirm-dialog-message')
                .text('Confermi la cancellazione del planogram ' + params.data.codicePlano.value + ' ?');
            PF('confirmRemovePlano').show();
        };
        span.appendChild(elem);
    }
    return span;
}

//SCHEDA PROMO - REPARTI
SchedaPromoReparti_DeleteRepartiRenderer = function (params) {
    var span = document.createElement("span");
    if (params.data.removeEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function (event) {
            event.preventDefault();
            idReparto = params.data.idReparto.value;
            $('.ui-confirm-dialog-message')
                .text('Confermi la cancellazione del reparto ' + params.data.descrizioneEstesa.value + ' ?');
            PF('cancellaRepartoConfirmDialog').show();
        }
        span.appendChild(elem);
    }
    return span;
}

// SCHEDA PROMO - TIPO CASSA
SchedaPromoMeccaniche_DeleteTipoCassaRenderer = function(params) {
    var span = document.createElement("span");
    if (params.data.removeEnabled !== undefined && params.data.removeEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function (ev) {
            ev.preventDefault();
            idTipoCassa = params.data.idTipoCassa.value;
            $('.ui-confirm-dialog-message')
                .text('Confermi la cancellazione del tipo cassa ' + params.data.descrizione.value + ' per la promozione selezionata ?');
            PF('cancellaTipoCassaConfirmDialog').show();
        };
        span.appendChild(elem);
    }
    return span;
}

//CONFIGURAZIONE SET
ConfigSet_DeleteRow = function (params) {
    if (params.data.removeEnabled == undefined || params.data.removeEnabled.value == "false") {
        return '';
    }
    var span = document.createElement("span");
    if (params.data.removeEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function (event) {
            event.preventDefault();
            PF('rowData').jq.val(JSON.stringify(params.data));
            $('.ui-confirm-dialog-message').text('Confermi la cancellazione della riga?');
            PF('cancellaRigaSet_confirmDialog').show();
        }
        span.appendChild(elem)
    }
    return span;
}

//SCHEDA PROMO - COMPRATORI
SchedaPromoCompratori_DeleteCompratoriRenderer = function (params) {
    var span = document.createElement("span");
    var elem = gridActionButtonElem('trash');
    elem.onclick = function (event) {
        event.preventDefault();
        idCompratore = params.data.id.value;
        removeBuyer([{name: 'idBuyer', value: params.data.id.value}, {
            name: 'descrizione',
            value: params.data.descrizione.value
        }]);
    }
    if (params.data.removeEnabled.value === 'true') {
        span.appendChild(elem);
    }
    return span;
}

AxPicklistRenderer = function (params) {
    let type = evalCell(params, 'type');
    let dataTypeParams = evalCell(params, 'dataTypeParams');
    let value = evalCell(params, 'value');
    if (dataTypeParams.multiselect !== undefined && dataTypeParams.multiselect === true) {
        //Multiselect
        let resultElement = document.createElement("span");
        let value = params.value;
        if (value !== undefined) {
            let listValues = value.split(dbPromoAppSettings.stringUtils.joinDelimiter);
            resultElement = listValues.join(', ');
        }
        return resultElement;
    } else {
        //SingleSelect
        if (type === "picklist") {
            return value;
        } else if (type === "comboBox") {
            let listOfValues = evalCell(params, "comboBoxValues");
            let resultItem = listOfValues.filter(item => {
                return item.key === value
            })
            if (resultItem.length === 1) {
                return resultItem[0].label;
            }
        }
    }
}

AxFilterRederer = function (params) {
    let gridCode = params.api.gridOptionsWrapper.gridOptions.gridCode;
    let rowData = getLeafRowData(gridCode);
    LT(rowData);
    //FILTRARE PER LA COLONNA e creare i dati da mettere nel filtro
    let type = evalCellColumnTypes(params, "type");
    let value = evalCellColumnTypes(params, "value");
    if (type === 'date') {
        return excelNumToCellDate(value);
    }
    if (type === 'comboBox') {
        return "TEST";
    }
    return value;
}

//COMPLEMENTARI DONWLOAD FILE
Complementari_downloadFileScarti = function(params) {
    var span = document.createElement("span");
    let isDownloadable = params.data.FILE_SCARTI.value;
    if (isDownloadable === 'true') {
        var elem = gridActionButtonElem('download', 'Scarica file scarti');
        elem.style.width = '40px';
        elem.onclick = function () {
            let idUpload = params.data.ID_UPLOAD.value;
            let urlDownload = contextPath + '/resources/pianificazionepromo/scarti/' + idUpload;
            ajaxDownloadFileFromPost(urlDownload);
        }
        span.appendChild(elem);
    }
    return span;
}

function ajaxDownloadFileFromPost(urlDownload) {
    $.ajax({
        type: "POST",
        url: urlDownload,
        data: null,
        xhr: function() {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 2) {
                    if (xhr.status == 200) {
                        xhr.responseType = "blob";
                    } else {
                        xhr.responseType = "text";
                    }
                }
            };
            return xhr;
        },
        success: function(blob, status, xhr) {
            // check for a filename
            var filename = "";
            var disposition = xhr.getResponseHeader('Content-Disposition');
            if (disposition && disposition.indexOf('attachment') !== -1) {
                var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                var matches = filenameRegex.exec(disposition);
                if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
            }

            if (typeof window.navigator.msSaveBlob !== 'undefined') {
                // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
                window.navigator.msSaveBlob(blob, filename);
            } else {
                var URL = window.URL || window.webkitURL;
                var downloadUrl = URL.createObjectURL(blob);
                if (filename) {
                    // use HTML5 a[download] attribute to specify filename
                    var a = document.createElement("a");
                    // safari doesn't support this yet
                    if (typeof a.download === 'undefined') {
                        window.location.href = downloadUrl;
                    } else {
                        a.href = downloadUrl;
                        a.download = filename;
                        document.body.appendChild(a);
                        a.click();
                    }
                } else {
                    window.location.href = downloadUrl;
                }
                setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
            }
        },
        error: ajaxErrorHandler
    });
}

//CUMULABILITA - GRID CUMULABILITA
Cumulabilita_BtnCellRenderer = function (params) {
    let publishEnabled = params.data.publishEnabled;
    let cancelEnabled = params.data.cancelEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    var cell_2 = row.insertCell(0);
    cell_1.style.width = '25px';
    cell_1.style.padding = '0';
    cell_2.style.width = '25px';
    cell_2.style.padding = '0';
    var div = document.createElement("div");

    //PUBLISH_______________
    var publishSpan = document.createElement("span");
    if (publishEnabled != undefined && publishEnabled.value === "true") {
        var elem = gridActionButtonElem('publish');
        elem.onclick = function () {
            publishCumulabilita([{name: 'idCumulabilita', value: params.data.id.value}]);
        }
        publishSpan.appendChild(elem);
    }
    cell_1.appendChild(publishSpan);

    //CANCEL_______________
    var cancelSpan = document.createElement("span");
    if(cancelEnabled!=undefined && cancelEnabled.value==="true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            cancelCumulabilita([{name: 'idCumulabilita', value: params.data.id.value}]);
        }
        cancelSpan.appendChild(elem);
    }
    cell_2.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}

//CUMULABILITA - GRID BUONI
Buoni_BtnCellRenderer = function (params) {
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    cell_1.style.width = '25px';
    cell_1.style.padding = '0';
    var div = document.createElement("div");

    //DELETE_______________
    var cancelSpan = document.createElement("span");
    if (deleteEnabled != undefined && deleteEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            removeBuono([
                {name: 'idCumulabilita', value: params.data.idCumulabilita.value},
                {name: 'idBuono', value: params.data.id.value},
            ]);
        }
        cancelSpan.appendChild(elem);
    }
    cell_1.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}

// Renderer con pulsante per aprire una modale
AxOpenDialogRenderer = function (params) {
    var e = document.createElement("div");
    let key = params.value && params.value.value ? params.value.value : "";
    let value = key && params.value.comboBoxValues && params.value.comboBoxValues[0] && (params.value.comboBoxValues[0].key === key)
        ? params.value.comboBoxValues[0].label
        : "";
    var btn = document.createElement("button");
    btn.className = "ax-grid-btn"
    btn.title = params.btnTitle ? params.btnTitle : "Apri dialog";
    btn.innerText = ". . .";
    btn.style.cssText = "--onHoverColor: #51ae53; margin-right: 5px; color: black !important; font-weight: bold;";
    btn.onmouseover = function () {
        this.style.cursor = "pointer";
    }
    if (params.callback) {
        btn.addEventListener('click', params.callback.bind(this, params));
    }
    e.appendChild(btn);
    e.appendChild(document.createTextNode(value));
    e.setAttribute("refKey", key);
    return e;
}

// Renderer apertura color-picker
AxPickcolorRenderer = function (params) {
    var e = document.createElement("span");
    let value = params.value.value;
    var eLeft = document.createElement("div");
    let bgColor = value ? '#' + value : '#1066A8';
    eLeft.style.cssText = "float: left; width: 20px; height: 20px; margin-top: 3px; border: 2px solid #d9dcde; "
        + "background-color: " + bgColor;
    e.appendChild(eLeft);
    var eRight = document.createElement("div");
    let rgbText = value ? value : '1066A8';
    eRight.style.cssText = "margin-left: 25px;";
    eRight.appendChild(document.createTextNode(rgbText));
    e.appendChild(eRight);
    return e;
}

// Pianificazioni Articoli Piano Media - Delete button renderer
PianificazioneMedia_DeleteBtnRenderer = function (params) {
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell = row.insertCell(0);
    cell.style.width = '25px';
    cell.style.padding = '0';
    var div = document.createElement("div");
    var cancelSpan = document.createElement("span");
    if (deleteEnabled != undefined && deleteEnabled.value === "true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            removeArticoloPianificazioneMedia([
                {name: 'id', value: params.data.id.value},
                {name: 'articolo', value: params.data.articolo.value}
            ]);
        }
        cancelSpan.appendChild(elem);
    }
    cell.appendChild(cancelSpan);
    // bottone select all media
    cell = row.insertCell(1);
    cell.style.width = '25px';
    cell.style.padding = '0';
    div = document.createElement("div");
    cancelSpan = document.createElement("span");
    elem = gridActionButtonElem('selectAllMedia');
    elem.onclick = function () {
        selectAllMediaForArticolo([
            {name: 'id', value: params.data.id.value},
            {name: 'articolo', value: params.data.articolo.value}
        ]);
    }
    cancelSpan.appendChild(elem);
    cell.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}

//Aggiungi riga sopra
MessaggiPianificazione_ButtonsRenderer=function (params) {
    var table = document.createElement("table");
    var row = table.insertRow(0);
    // creazione del bottone 'trash'
    var deleteCell = row.insertCell(0);
    deleteCell.style.width = '25px';
    deleteCell.style.padding = '0';
    var div = document.createElement("div");
    var span = document.createElement("span");
    var deleteButton = gridActionButtonElem('trash');
    deleteButton.onclick = function () {
        deleteMessaggioPianificazione([
            {name: 'id', value: params.data.id.value}
        ]);
    }
    span.appendChild(deleteButton);
    deleteCell.appendChild(span);

    // creazione del bottone 'addRowAbove'
    var insertAboveCell = row.insertCell(0);
    insertAboveCell.style.width = '25px';
    insertAboveCell.style.padding = '0';
    div = document.createElement("div");
    span = document.createElement("span");
    var insertAboveButton = gridActionButtonElem('addRowAbove');
    insertAboveButton.onclick = function () {
        insertMessageAbove([
            {name: 'id', value: params.data.id.value}
        ]);
    }
    span.appendChild(insertAboveButton);
    insertAboveCell.appendChild(span);
    div.appendChild(table);
    return div;
}

// Messaggi Pianificazione - Delete button renderer
CastellettoPianificazione_DeleteBtnRenderer = function (params) {
    var div = document.createElement("div");
    if (params.data?.sequenzaStampa && params.data.sequenzaStampa?.value > 5) {
        var table = document.createElement("table");
        var row = table.insertRow(0);
        var cell = row.insertCell(0);
        cell.style.width = '25px';
        cell.style.padding = '0';
        var deleteSpan = document.createElement("span");
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            deleteCastelletto([
                {name: 'id', value: params.data.id.value}
            ]);
        }
        deleteSpan.appendChild(elem);
        cell.appendChild(deleteSpan);
        div.appendChild(table);
    }
    return div;
}

// Inclusione Esclusione - Delete button renderer
InclusioneEsclusione_DeleteBtnRenderer = function (params) {
    var div = document.createElement("div");
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell = row.insertCell(0);
    cell.style.width = '25px';
    cell.style.padding = '0';
    var deleteSpan = document.createElement("span");
    var elem = gridActionButtonElem('trash');
    elem.onclick = function () {
        removeInclusioneEsclusione([
            {name: 'id', value: params.data.id.value}
        ]);
    }
    deleteSpan.appendChild(elem);
    cell.appendChild(deleteSpan);
    div.appendChild(table);
    return div;
}

// Totalizzatori - Tab Obiettivo
TotalizzatoriObiettivo_DeleteObiettivoRenderer = function (params) {
	var span = document.createElement("span");
	// noinspection JSUnresolvedReference
	if (params.data.removeEnabled.value === "true") {
		var elem = gridActionButtonElem('trash');
		elem.onclick = function (event) {
			event.preventDefault();
			// noinspection JSUndeclaredVariable
			idObiettivo = params.data.id.value;
			// noinspection JSUnresolvedReference
			$('.ui-confirm-dialog-message')
				.text('Confermi la cancellazione del obiettivo associato alla promozione ' + params.data.codicePromo.value + ' ?');
			// noinspection JSUnresolvedReference
			PF('deleteObiettivoConfirmDialog').show();
		}
		span.appendChild(elem);
	}
	return span;
}

// Sottoscrizioni
Sottoscrizioni_DeleteSottoscrizioneRenderer = function (params) {
	var span = document.createElement("span");
	// noinspection JSUnresolvedReference
	if (params.data.removeEnabled.value === "true") {
		var elem = gridActionButtonElem('trash');
		elem.onclick = function (event) {
			event.preventDefault();
			// noinspection JSUndeclaredVariable
			idSottoscrizione = params.data.id.value;
			// noinspection JSUnresolvedReference
			$('.ui-confirm-dialog-message')
				.text('Confermi la cancellazione della sottoscrizione con codice ' + params.data.codice.value + ' ?');
			// noinspection JSUnresolvedReference
			PF('deleteSottoscrizioneConfirmDialog').show();
		}
		span.appendChild(elem);
	}
	return span;
}

// SchedaPromo - Sottoscrizioni
SchedaPromoSottoscrizioni_DeleteSottoscrizioneRenderer = function (params) {
	var span = document.createElement("span");
	// noinspection JSUnresolvedReference
	if (params.data.removeEnabled.value === "true") {
		var elem = gridActionButtonElem('trash');
		elem.onclick = function (event) {
			event.preventDefault();
			// noinspection JSUndeclaredVariable
			idSottoscrizione = params.data.id.value;
			// noinspection JSUnresolvedReference
			$('.ui-confirm-dialog-message')
				.text('Confermi la cancellazione della sottoscrizione con codice ' + params.data.codice.value + ' ?');
			// noinspection JSUnresolvedReference
			PF('deleteShedaPromoSottoscrizioneConfirmDialog').show();
		}
		span.appendChild(elem);
	}
	return span;
}

// Generic edit button renderer:
Default_EditBtnRenderer = function(params) {
    var span = document.createElement("span");
    var elem = gridActionButtonElem('edit');
    elem.onclick = function () {
        // by default, it calls a javascript function named 'openEditDialog' with the row
        openDefaultEditDialog(params.data);
    };
    span.appendChild(elem);
    return span;
}
MacrospaziRetail_BtnRenderer = function(params) {
    let editEnabled = params.data.editEnabled;
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    var cell_2 = row.insertCell(0);
    cell_1.style.width = '25px';
    cell_1.style.padding = '0';
    cell_2.style.width = '25px';
    cell_2.style.padding = '0';
    var div = document.createElement("div");
    //EDIT_______________
    var editSpan = document.createElement("span");
    if (editEnabled != undefined && editEnabled.value === "true") {
        var elem = gridActionButtonElem('edit');
        elem.onclick = function () {
            openMacrospaziEditDialog(params.data);
        }
        editSpan.appendChild(elem);
    }
    cell_1.appendChild(editSpan);
    //DELETE_______________
    var cancelSpan = document.createElement("span");
    if(deleteEnabled!=undefined && deleteEnabled.value==="true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            openMacrospaziDeleteDialog(params.data);
        }
        cancelSpan.appendChild(elem);
    }
    cell_2.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}
// Generic edit button renderer:
EventiRetail_BtnRenderer = function(params) {
    let editEnabled = params.data.editEnabled;
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow(0);
    var cell_1 = row.insertCell(0);
    var cell_2 = row.insertCell(0);
    cell_1.style.width = '25px';
    cell_1.style.padding = '0';
    cell_2.style.width = '25px';
    cell_2.style.padding = '0';
    var div = document.createElement("div");

    //EDIT_______________
    var editSpan = document.createElement("span");
    if (editEnabled != undefined && editEnabled.value === "true") {
        var elem = gridActionButtonElem('edit');
        elem.onclick = function () {
            openEventiEditDialog(params.data);
        }
        editSpan.appendChild(elem);
    }
    cell_1.appendChild(editSpan);

    //CANCEL_______________
    var cancelSpan = document.createElement("span");
    if(deleteEnabled!=undefined && deleteEnabled.value==="true") {
        var elem = gridActionButtonElem('trash');
        elem.onclick = function () {
            openEventiDeleteDialog(params.data);
        }
        cancelSpan.appendChild(elem);
    }
    cell_2.appendChild(cancelSpan);

    div.appendChild(table);
    return div;
}

// Rifatturazione Initial Load
RifatturazioneInitialLoadActionsRenderer = function (params) {
    // noinspection JSUnresolvedReference
    let editEnabled = params.data.editEnabled;
    // noinspection JSUnresolvedReference
    let deleteEnabled = params.data.deleteEnabled;
    var table = document.createElement("table");
    var row = table.insertRow();
    var div = document.createElement("div");
    // edit button
    if (editEnabled != undefined && editEnabled.value === "true") {
        var cellEdit = row.insertCell();
        cellEdit.style.width = '25px';
        cellEdit.style.padding = '0';
        var spanEdit = document.createElement("span");
        var elemEdit = gridActionButtonElem('edit');
        elemEdit.onclick = function () {
            // noinspection JSUnresolvedReference
            openRifatturazioneInitialLoadEditDialog(params.data);
        }
        spanEdit.appendChild(elemEdit);
        cellEdit.appendChild(spanEdit);
    }
    // delete button
    if (deleteEnabled != undefined && deleteEnabled.value === "true") {
        var cellDelete = row.insertCell();
        cellDelete.style.width = '25px';
        cellDelete.style.padding = '0';
        var spanDelete = document.createElement("span");
        var elemDelete = gridActionButtonElem('trash');
        elemDelete.onclick = function () {
            // noinspection JSUnresolvedReference
            openRifatturazioneInitialLoadDeleteDialog(params.data);
        }
        spanDelete.appendChild(elemDelete);
        cellDelete.appendChild(spanDelete);
    }
    div.appendChild(table);
    return div;
}
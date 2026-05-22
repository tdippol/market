/**
 *  TEXT EDITOR
 * 
 */

var KEY_BACKSPACE = 8;
var KEY_F2 = 113;
var KEY_DELETE = 46;

function AxTextCellEditor() {
}

AxTextCellEditor.prototype.init = function (params) {
	

	var dataTypeParams	= evalCellColumnTypes(params,'dataTypeParams')

	
	this.eInput 		= document.createElement('input');
	this.eInput.type 	= 'text';
	this.eInput.classList.add('AxTextEditor');
	this.eInput.style.zIndex = 10000000;
	this.eInput.style.position = "relative";
	
	//Text max input
	this.eInput.maxLength = dataTypeParams.length;	
	//Text min input
	if(dataTypeParams.minLength!=undefined){
		this.eInput.minLength=dataTypeParams.minLength
	}
	
	//Text transformation after edit
	if(params.transform_getValue){
		this.eInput.transform_getValue = params.transform_getValue;	
	}
	
	
	this.params = params;

	var startValue;

	var keyPressBackspaceOrDelete = params.keyPress === KEY_BACKSPACE || params.keyPress === KEY_DELETE;

	if 		(keyPressBackspaceOrDelete) {startValue = '';} 
	else if (params.charPress) {	startValue = params.charPress;} 
	else {
		startValue = params.value;
		if (params.keyPress !== KEY_F2) {
			this.highlightAllOnFocus = true;
		}
	}

	if (startValue !== null && startValue !== undefined) {
		this.eInput.value = startValue;
	}
	
	
	
	var KEY_LEFT = 37;
	var KEY_UP = 38;
	var KEY_RIGHT = 39;
	var KEY_DOWN = 40;
	var KEY_PAGE_UP = 33;
	var KEY_PAGE_DOWN = 34;
	var KEY_PAGE_HOME = 36;
	var KEY_PAGE_END = 35;
	var KEY_TAB = 9;


	if(evalCellColumnTypes(params,'nullable')===false){
		this.eInput.addEventListener('input', this.inputChanged.bind(this));
	}
	
	this.eInput.addEventListener('keydown', function(event) {
		var keyCode = event.keyCode;

		var isNavigationKey = keyCode===KEY_LEFT || keyCode===KEY_RIGHT || keyCode===KEY_UP
		|| keyCode===KEY_DOWN || keyCode===KEY_PAGE_DOWN || keyCode===KEY_PAGE_UP
		|| keyCode===KEY_PAGE_HOME || keyCode===KEY_PAGE_END;

		if (isNavigationKey) {
			// this stops the grid from receiving the event and executing keyboard navigation
			event.stopPropagation();
		}
		
		
		if(keyCode===KEY_TAB){/*
			event.stopPropagation();
			params.api.stopEditing()
			var lastCell=params.api.getFocusedCell();
			params.api.setFocusedCell(lastCell.rowIndex, lastCell.column.colId, lastCell.floating)
			*/
		}
		
		
	});
	
};

AxTextCellEditor.prototype.getGui = function () {
	return this.eInput;
};

AxTextCellEditor.prototype.inputChanged = function(event) {
	 let val = event.target.value;
		 if(val === undefined || val === null || val === '') {
			 $(this.eInput).parents('.ag-cell-focus').addClass('cell-not-nullable')
		 } else {
			 $(this.eInput).parents('.ag-cell-focus').removeClass('cell-not-nullable')
		 }
  }

AxTextCellEditor.prototype.getValue = function (params) {
	
	let isValid=validateMinLength(this.eInput)

	if(!isValid){
		return this.params.value
	}else{
		
		if(this.eInput.transform_getValue=='uppercase'){
			return this.eInput.value.toUpperCase();	
		}
		
		return this.eInput.value;
		
	}
};

AxTextCellEditor.prototype.afterGuiAttached = function () {
	this.eInput.focus();
};
AxTextCellEditor.prototype.destroy = function() {
	$(this.eInput).parents('.ag-cell-focus').removeClass('cell-not-nullable')
};

function validateMinLength(input){
	
	if(input.value.length>=0 && input.value.length<input.minLength){
		addWarningGrowl([{ name: 'title', value: 'Attenzione' }, {
			name: 'message',
			value: 'Il testo inserito non può essere minore di '+input.minLength+' caratteri'
		}]);
		return false;
	}
	return true
}

/*
* 
* CHECKBOX EDITOR/RENDERER
* 
* */
function AxCheckboxCellRenderer() {}
AxCheckboxCellRenderer.prototype.init = function(params) {
  this.params = params;

  var editable=evalCellColumnTypes(params,'editable')

  this.eGui = document.createElement('input');
  this.eGui.type = 'checkbox';
  this.eGui.disabled = !editable;
  this.eGui.checked = params.value;

  this.checkedHandler = this.checkedHandler.bind(this);
  this.eGui.addEventListener('click', this.checkedHandler);
}
AxCheckboxCellRenderer.prototype.checkedHandler = function(e) {
  let checked = e.target.checked;
  let colId = this.params.column.colId;
  this.params.node.setDataValue(colId, checked);
}
AxCheckboxCellRenderer.prototype.getGui = function(params) {
  return this.eGui;
}
AxCheckboxCellRenderer.prototype.destroy = function(params) {
  this.eGui.removeEventListener('click', this.checkedHandler);
}

/*
* 
* INTEGER RENDERER
* 
* */
function AxIntegerRenderer() {
}
AxIntegerRenderer.prototype.init = function(params) {
this.eGui = document.createElement("span");
if (params.value !== "" && params.value !== undefined
	&& params.value !== null) {
this.eGui.innerHTML = formatInteger(params.value);
}
};
AxIntegerRenderer.prototype.getGui = function() {
return this.eGui;
};
/*
* 
* NUMERIC EDITOR
* 
* */
function unformatNumberDbPromo(num,precision){
	return num.toString().replace(".", ",");
}
function formatNumberDbPromo(num,precision){
	return Number(num.toString().replace(",", "."));
}

function checkNumericValidation(input){

	let maxNumber=input.maxNumber
	let minNumber=input.minNumber

	if(minNumber>=0 && minNumber>formatNumberDbPromo(input.value)){
		addWarningGrowl([{ name: 'title', value: 'Attenzione' }, {
			name: 'message',
			value: 'Il numero inserito non può essere minore di '+minNumber
		}]);
		return false
	}
	if(maxNumber>=0 && formatNumberDbPromo(input.value)>maxNumber){
		addWarningGrowl([{ name: 'title', value: 'Attenzione' }, {
			name: 'message',
			value: 'Il numero inserito non può essere maggiore di '+maxNumber
		}]);
		return false
	}
		
	return true

}

/**
 * NUMERIC EDITOR
 *
 */
function AxNumericCellEditor() {
}
AxNumericCellEditor.prototype.init = function(params) {

var dataTypeParams	= evalCellColumnTypes(params,'dataTypeParams')

var precision=dataTypeParams.precision;


this.eInput = document.createElement("input");
this.eInput.classList.add('AxTextEditor');
this.eInput.style.zIndex = 10000000;
this.eInput.style.position = "relative";

if(dataTypeParams.length!=undefined){
	this.eInput.maxNumber=dataTypeParams.length
}else{
	this.eInput.maxNumber=-1
}
if(dataTypeParams.minLength!=undefined){
	this.eInput.minNumber=dataTypeParams.minLength
}else{
	this.eInput.minNumber=-1
}
if(dataTypeParams.allowZero!=undefined && dataTypeParams.allowZero===false){
	this.eInput.disableInputZero=true
}else{
	this.eInput.disableInputZero=false
}




if (isCharIntegrer(params.charPress, dataTypeParams.allowNegative)) {
this.eInput.value = params.charPress;
} else {





if (params.value !== undefined && params.value !== null) {

	this.eInput.value = unformatNumberDbPromo(params.value,precision);
	/*
	if(dataTypeParams.precision>0){
		this.eInput.value = formatNumber(params.value);
	}else{
		this.eInput.value = formatInteger(params.value);	
	}*/
}
}



var KEY_LEFT = 37;
var KEY_UP = 38;
var KEY_RIGHT = 39;
var KEY_DOWN = 40;
var KEY_PAGE_UP = 33;
var KEY_PAGE_DOWN = 34;
var KEY_PAGE_HOME = 36;
var KEY_PAGE_END = 35;

if(evalCellColumnTypes(params,'nullable')===false){
this.eInput.addEventListener('input', this.inputChanged.bind(this));
}
this.eInput.addEventListener('keydown', function(event) {
var keyCode = event.keyCode;
var isNavigationKey = keyCode===KEY_LEFT || keyCode===KEY_RIGHT || keyCode===KEY_UP
|| keyCode===KEY_DOWN || keyCode===KEY_PAGE_DOWN || keyCode===KEY_PAGE_UP
|| keyCode===KEY_PAGE_HOME || keyCode===KEY_PAGE_END;

if (isNavigationKey) {
	// this stops the grid from receiving the event and executing keyboard navigation
	event.stopPropagation();
}
});




var that = this;
this.eInput.addEventListener("keypress", function(event) {

if (!isKeyPressedInteger(event)) {
	that.eInput.focus();
	if (event.preventDefault) {
		event.preventDefault();
	}
	
	if(precision>0){
		if(event.charCode==46 || event.charCode==44){
			if(!(that.eInput.value).includes(",")){
				that.eInput.value = that.eInput.value+',';
			}
		}
	}
	
} else {
	
	if (that.isKeyPressedNavigation(event)) {
		event.stopPropagation();
	}
}
});


var charPressIsNotANumber = !isCharIntegrer(params.charPress);


if (params.keyPress === 46) {
this.eInput.value = "0";
this.cancelBeforeStart = false;
} else {
if(params.keyPress==null || params.keyPress==13){
	this.cancelBeforeStart = false;
	//this.eInput.value = params.value;
}else{
	this.cancelBeforeStart = charPressIsNotANumber;
}
}
};
AxNumericCellEditor.prototype.inputChanged = function(event) {
let val = event.target.value;
if(val === undefined || val === null || val === '') {
  $(this.eInput).parents('.ag-cell-focus').addClass('cell-not-nullable')
} else {
  $(this.eInput).parents('.ag-cell-focus').removeClass('cell-not-nullable')
}
}
AxNumericCellEditor.prototype.isKeyPressedNavigation = function(event) {
return event.keyCode === 39 || event.keyCode === 37;
};
AxNumericCellEditor.prototype.getGui = function() {
return this.eInput;
};
AxNumericCellEditor.prototype.afterGuiAttached = function() {
this.eInput.focus();
};
AxNumericCellEditor.prototype.isCancelBeforeStart = function() {
return this.cancelBeforeStart;
};
AxNumericCellEditor.prototype.isCancelAfterEnd = function() {
	let input=this.eInput
	let disableInputZero=input.disableInputZero
	
	if(disableInputZero && formatNumberDbPromo(input.value)===0){
		addWarningGrowl([{ name: 'title', value: 'Attenzione' }, {
			name: 'message',
			value: 'Il numero inserito non può essere zero'
		}]);
		return true
	}
	

	return false;
};
AxNumericCellEditor.prototype.getValue = function() {
	//return unformatNumber(this.eInput.value);

	let isValid=checkNumericValidation(this.eInput)

	if(isValid){
		return unformatNumber(this.eInput.value);
	}else{
		return unformatNumber('')
	}

};
AxNumericCellEditor.prototype.destroy = function() {
$(this.eInput).parents('.ag-cell-focus').removeClass('cell-not-nullable')
};
AxNumericCellEditor.prototype.isPopup = function() {
return false;
};

/**
 * TIME EDITOR
 *
 */
function AxTimeCellEditor() {
}
var inputId='timepicker14'
AxTimeCellEditor.prototype.init = function(params) {

	   let dataTypeParams = evalCellColumnTypes(params,"dataTypeParams")
	   
	   


   this.eInput = document.createElement("div");
   var div = $(this.eInput);
   div.addClass("input-group bootstrap-timepicker timepicker");

   var div = $(this.eInput);
   var input = $('<input id="'+inputId+'" value="" name="timepicker13" type="text" class="" style="height:30px;width:100%">');

   div.append(input);
   //div.append('<span class="input-group-addon"><i class="fa fa-clock"></i></span>');

   var timeOptions={
	   defaultTime:params.value,
	   minuteStep: dataTypeParams.minuteStep !==undefined ? dataTypeParams.minuteStep : 1,
	   secondStep: dataTypeParams.secondStep !==undefined ? dataTypeParams.secondStep : 1,
	   minHours: dataTypeParams.minHours !==undefined ? dataTypeParams.minHours : 0,
	   maxHours: dataTypeParams.maxHours !==undefined ? dataTypeParams.maxHours : 24,
	   showInputs: false,
	   showSeconds: dataTypeParams.showSeconds !==undefined && dataTypeParams.showSeconds ? dataTypeParams.showSeconds : false,
	   disableFocus: true,
	   showMeridian: false,
	   showInputs:true
   }

	   $(function () {

		   $('#'+inputId).timepicker(timeOptions).timepicker('showWidget');

		   $('#'+inputId).focus();
		   $('#'+inputId).timepicker().on('changeTime.timepicker', function(e) {
			   input=e.time.value
		   });
	   });
	   

	   

};
AxTimeCellEditor.prototype.isKeyPressedNavigation = function(event) {
return event.keyCode === 39 || event.keyCode === 37;
};
AxTimeCellEditor.prototype.getGui = function() {
return this.eInput;
};
AxTimeCellEditor.prototype.afterGuiAttached = function() {
this.eInput.focus();
};
AxTimeCellEditor.prototype.isCancelBeforeStart = function() {
return this.cancelBeforeStart;
};
AxTimeCellEditor.prototype.isCancelAfterEnd = function() {
return false;
};
AxTimeCellEditor.prototype.getValue = function() {
   return $('#'+inputId).val()
};
AxTimeCellEditor.prototype.destroy = function() {
   $('#'+inputId).timepicker('hideWidget');
};
AxTimeCellEditor.prototype.isPopup = function() {
return false;
};

/**
*  COMBOBOX EDITOR
* 
*/
function AxComboBoxCellEditor() {
}
AxComboBoxCellEditor.prototype.init = function (params) {

	let comboBoxValues = evalCellColumnTypes(params,'comboBoxValues')
	let value = evalCellColumnTypes(params,'value')
	
	this.eInput = document.createElement("div");
	var div = $(this.eInput);

		var select = $('<select class="AxComboBoxEditor" style="height:30px"></select>');
			
			select.attr("name", "comboBox");
			
			$.each(comboBoxValues, function (i, item) {
				select.append($('<option>', { 
					value: item.key,
					text : item.label 
				}));
			});
		
			
		if(value===undefined){
			select.selectpicker('val', '');
		}else{
			select.selectpicker('val', value);
		}
		
		
			
		div.append(select);


		//Auto open combobox on enter
		if (params.keyPress === 13) {
			
		}
		
};
AxComboBoxCellEditor.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
AxComboBoxCellEditor.prototype.getGui = function() {
	return this.eInput;
};
AxComboBoxCellEditor.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
AxComboBoxCellEditor.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
AxComboBoxCellEditor.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
AxComboBoxCellEditor.prototype.getValue = function() {
	return $(this.eInput).find("select").val();
};
AxComboBoxCellEditor.prototype.destroy = function() {
};
AxComboBoxCellEditor.prototype.isPopup = function() {
	return false;
};

/**
 *  PICKLIST EDITOR
 *
 */
function AxPicklistCellEditor () {}
var globalParams=null
AxPicklistCellEditor.prototype.init = function(params) {
	
	globalParams=params
	
	let dataTypeParams = evalCellColumnTypes(globalParams,"dataTypeParams")
	
	let isMultiselect= false
	if(dataTypeParams.multiselect!==undefined && dataTypeParams.multiselect){
		isMultiselect=true
	}


	let className='AxPicklist'
	
	this.eInput = document.createElement("div");
	var div = $(this.eInput);
	div.addClass("");
	
	
	
	
	var select = $("<select style='display:none'></select>");
	
	if(isMultiselect){
		select.attr('multiple',true)	
	}
	
	
	select.attr("data-live-search", true);
	select.attr("autoclose", true);
	select.attr("name", "picklist");
	select.addClass(className);

	
	//Load Select Options _____________________
	let optionsList=getAxPicklistOptionsList(globalParams);
	
	if(optionsList===false){
		//Interrompe la costruzione della tendina e non fa editare il campo
		globalParams.stopEditing();
	}
	
	
	
	
	$.each(optionsList,function( index, optionObj ) {
		select.append('<option value="'+optionObj.key+'">'+optionObj.label+'</option>');
	});
	//__________________________________________
	
	
	
	
	div.append(select);
	
	
	
	
	
	$(function () {
		
		//Create instance of selectpicker____________________________________
		let builderOptions={
			noneSelectedText:'Seleziona un valore',
			noneResultsText:'Nessun risultato per {0}',
			selectAllText:'Seleziona tutti',
			//iconBase:'LineAwesome',
			//tickIcon:'la-check'
			//selectedTextFormat:'count > 3'
		}

		if(isMultiselect){
			builderOptions.showTick=true
			builderOptions.multipleSeparator=', '
		}

		$("."+className).selectpicker(builderOptions);
			   //Default selected Option
			let preselecetValue=evalCellColumnTypes(globalParams,"value")
			if(preselecetValue!==''){
				if(isMultiselect){
					$("."+className).selectpicker('val', preselecetValue.split(dbPromoAppSettings.stringUtils.joinDelimiter));
				}else{
					$("."+className).selectpicker('val', preselecetValue);
				}
			}
			//Auto open
			$("."+className).selectpicker('toggle');
		//___________________________________________________________________
		
		var searchablePicklist= $("."+className+" .dropdown-menu:first").detach();
		
		searchablePicklist.appendTo("body");
		searchablePicklist.css("z-index", 999999999).css("position","absolute");
		if (select.offset() != null) {
			searchablePicklist.css(select.offset());
		}
		searchablePicklist.addClass("AxPicklistOptions");
		
		if(isMultiselect){
			searchablePicklist.addClass("isMultiselect");
		}
		$("div.dropdown-menu.AxPicklistOptions>div.bs-searchbox>input").focus()

		$(".AxPicklistOptions .inner .show").css("width", 100 + "%").css("position", "relative").css("top", "0px");
	
		$('div.dropdown.bootstrap-select.AxPicklist').css('border','1px solid #29dca7').css('width','100%')
			
		
	});
	
	
	
	
	//Click out of picklist
	$("body").on("click",function() {
		var found = false;
		$(".AxPicklistOptions .inner .show").each(
			function(key, value) {
				if ($(".AxPicklistOptions .inner .show")[key].clientHeight > 0) {
					found = true;
				}
		});
		
		if (found) {
			$(".AxPicklistOptions").hide().remove();
			if (globalParams != null) {
				 globalParams.stopEditing();
				 $("."+className).selectpicker('destroy')
				 
			}
		}
	});
	
	
	
};
AxPicklistCellEditor.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
AxPicklistCellEditor.prototype.getGui = function() {
	return this.eInput;
};
AxPicklistCellEditor.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
AxPicklistCellEditor.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
AxPicklistCellEditor.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
AxPicklistCellEditor.prototype.getValue = function() {
	
	let result=$(this.eInput).find("select").val();

	let dataTypeParams = evalCellColumnTypes(globalParams,"dataTypeParams")
	if(dataTypeParams.multiselect!==undefined){
		if(dataTypeParams.multiselect===true){
			return result.join(dbPromoAppSettings.stringUtils.joinDelimiter)	
		}
	}
	return result; 
};
AxPicklistCellEditor.prototype.destroy = function() {
	$(".AxPicklistOptions").hide().remove()
	$(".AxPicklist").selectpicker('destroy')
};
AxPicklistCellEditor.prototype.isPopup = function() {
	return false;
};

//AxPicklist OPTIONS LOADER
function getAxPicklistOptionsList(params){
	
	let optionsList=[];
	
	let type = evalCellColumnTypes(params,"type");
	
	//PICKLIST #############################
	if(type==='picklist'){
		let picklistValues=[]
		let picklistService=evalCellColumnTypes(params, "picklistService")
		
		if(picklistService!==undefined){
			//Recupero dei valori da url remoto
			picklistValues= getRemoteSelectOptionsValues(picklistService.url)
			CM('info','PicklistService [Remote]')
			if(picklistValues===false){
				return false;
			}
			//IMPOSTAZIONE PROPRIETA' CELLA picklistValues
			eval("params.node.data." + params.column.colDef.field + ".picklistValues= picklistValues");
		}else {
			//Recupero dei valori da parametro della cella
			picklistValues = evalCellColumnTypes(params,"picklistValues")
			CM('info','PicklistValues [Local]')
		}
			
		
		//Trasformazione array semplici values in oggetti key:label
		$.each(picklistValues,function( index, picklistValue ) {
			let picklistValueObj={};
			picklistValueObj.key=picklistValue;
			picklistValueObj.label=picklistValue;
			optionsList.push(picklistValueObj);
		});
	
	}
	//COMBOBOX #############################
	else if(type==='comboBox'){
		
		let comboBoxService=evalCellColumnTypes(params, "comboBoxService")

		if(comboBoxService!==undefined){
			//Recupero dei valori da url remoto
			comboBoxValues= getRemoteSelectOptionsValues(comboBoxService.url)
			CM('info','comboBoxService [Remote]')
			if(comboBoxValues===false){
				return false;
			}
			//IMPOSTAZIONE PROPRIETA' CELLA comboBoxValues
			eval("params.node.data." + params.column.colDef.field + ".comboBoxValues= comboBoxValues");
		}else {
			//Recupero dei valori da parametro della cella
			comboBoxValues = evalCellColumnTypes(params,"comboBoxValues")
			CM('info','comboBoxValues [Local]')
		}
		
		optionsList = comboBoxValues;
	}
	

	//Set blank opt label 
	let blankOptionPosition=optionsList.findIndex(el => el.key === '');
	if(blankOptionPosition>0){
		let blankOptionObj=optionsList[blankOptionPosition]
		//set default blank text
		blankOptionObj.label='&nbsp;'
	}
	//Porto in prima posizione l'eventuale blank option
	/*let blankOptionPosition=optionsList.findIndex(el => el.key === '');
	if(blankOptionPosition>0){
		let blankOptionObj=optionsList[blankOptionPosition]
		optionsList.splice(blankOptionPosition, 1);
		//set default blank text
		blankOptionObj.label=' '
		optionsList.splice(0, 0, blankOptionObj);
	}*/
	
	
	return optionsList ;
}

//Ajax remote callL for Dynamic picklist and combobox
function getRemoteSelectOptionsValues(url){
  let result=[];
  
  if(url==undefined || url=='' || url==null){
	  CM('error','La proprietà dell\'obj picklistService.url/comboBoxService.url è vuota')
	  addErrorGrowl([{name: 'title', value: 'Attenzione'}, {name: 'message',value: 'Errore di connessione verso la ws'}]);
	  result=false;
  }else{
  
	  $.ajax({
		  type: "GET",
		  url: url,
		  contentType: "application/json",
		  async: false,
		  success: function (data) {
			  try {
				  result=JSON.parse(data.remoteValues)
				} catch (error) {
					//mockup data
				  result=data.remoteValues
				}

			  
		  },
		  error: function (jqXHR, exception, thrownError) {
				switch (jqXHR.status) {
					case 412:
						let response=jqXHR.responseJSON;
						addWarningGrowl([{name: 'title', value: 'Attenzione'}, {name: 'message',value: response.message}]);
						result=false;
					break;
					default:
						CM('error','Errore! Verificare la risposta dell\'url '+url)
						addErrorGrowl([{name: 'title', value: 'Attenzione'}, {name: 'message',value: 'Errore durante il recupero dei valori della tendina'}]);
						result = false;
						break;
				}
		  }
	  });

  }

	return result;
}

/**
 * Pickcolor Cell Editor
 */
function AxPickcolorEditor() {
}

AxPickcolorEditor.prototype.init = function (params) {
	this.eInput = document.createElement("input");
	this.eInput.type = "color";
	if (params.value.value) {
		this.eInput.value = '#' + params.value.value;
	} else {
		this.eInput.value = '#1066A8';
	}
	this.eInput.addEventListener('change', function (event) {
		params.stopEditing();
	});
}
AxPickcolorEditor.prototype.getGui = function () {
	return this.eInput;
};
AxPickcolorEditor.prototype.getValue = function () {
	return this.eInput.value;
};
AxPickcolorEditor.prototype.isPopup = function () {
	return true;
};

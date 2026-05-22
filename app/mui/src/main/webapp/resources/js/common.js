
function fnCall(fn, ...args){
	let func = (typeof fn =="string")?window[fn]:fn;
	if (typeof func == "function") func(...args);
	else {
	  CM("error",`Function "${fn}" not found!`)
	}
  }
  
 
 
  window.addEventListener("keydown",function (e) {
    if (e.keyCode === 114 || (e.ctrlKey && e.keyCode === 70)) { 
		if ($('.filterInputText').length ) {
			$('.filterInputText:first').focus().select();
        	e.preventDefault();
		}
    }
})


agGrid.fetchRemote = function(params){ 
	return new Promise((resolve, reject) => {
		const xhr = new XMLHttpRequest();
		xhr.open("GET", params.url);
		
		xhr.onreadystatechange=function(){
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					resolve(JSON.parse(xhr.responseText))
				} else {
					let grawlType='';
					if(xhr.status==412){
						grawlType='warning'
					}else{
						grawlType='error'
					}

					let grawMessage='';
					if(xhr.responseText!=''){
						grawMessage=JSON.parse(xhr.response).message
					}


					let err_obj={
						url:params.url,
						status:xhr.status,
						statusText:xhr.statusText,
						grawlType: grawlType,
						grawMessage: grawMessage,
					}
					
					reject(err_obj)
					
				}
			}
			
		}
	
		xhr.send();
	  });

 };

 

 function displayErrorFetchRemote(err_obj){
			let msg="Errore di caricamento \n"+err_obj.url+" \n["+err_obj.status+" - "+err_obj.statusText+"]"
			CM("error",msg)

			if(err_obj.grawMessage!=''){
				if(err_obj.grawlType=='warning'){
					addWarningGrowl([{name: 'title', value: 'Attenzione'}, {name: 'message',value: err_obj.grawMessage}]);
				}else if(err_obj.grawlType=='error'){
					addErrorGrowl([{name: 'title', value: 'Attenzione'}, {name: 'message',value: err_obj.grawMessage}]);
				}
			}else{
				addErrorGrowl([{name: 'title', value: 'Attenzione'}, {name: 'message',value: msg}]);
			}
}




var ajaxErrorHandler = function (jqXHR, exception, thrownError,event,callback_obj) {
	
	

	if(jqXHR.responseJSON==undefined){
		switch (jqXHR.status) {
			case 404:
				addErrorGrowl([{name: 'title', value: 'Errore'}, {name: 'message',value: 'Internal server error. Missing callback service'}]);      
			break;
			default:
				addErrorGrowl([{name: 'title', value: 'Errore'}, {name: 'message',value: 'Internal server error. '+jqXHR.status+' - '+jqXHR.statusText}]);      
			break;
		}
		return;
	}

	let response=jqXHR.responseJSON;
	if(response.message==undefined || response.message==''){
		response.message = jqXHR.statusText
	}

	switch (jqXHR.status) {
		case 412:

			if(response.originalRow!=undefined){
				(event.api.getRowNode(event.node.id)).setData(response.originalRow)
			}

			addWarningGrowl([{name: 'title', value: 'Attenzione'}, {name: 'message',value: response.message}]);
		break;
		
		default:
			addErrorGrowl([{name: 'title', value: 'Errore'}, {name: 'message',value: response.message}]);      
		break;
	}

	

	//----Check condition callback
	if(callback_obj!==undefined){
		if(callback_obj.condition=='always'
		|| callback_obj.condition==jqXHR.status){
			//Add response obj as first
			callback_obj.params.unshift(response)
			fnCall(callback_obj.action,callback_obj.params)
		}
	}
	
}



/*
	CONSOLE LOG HANDLER
*/

function help(){
	let help={
		"_LOG_":" ",
			"hideDebugLog()":"Disattiva log-debug",
			"showDebugLog()":"Attiva log-debug con clear dei log in console",
			"showDebugLogNoClear()":"Attiva log-debug con persistenza dei log in console",
		"_GRID_":" ",
			"getGridCodes()":"Restituisce i gridCodes presenti nella pagina",
			"getGridOptionFromGridMap(gridCode)":"GridOption della griglia",
	}
	console.table(help)
	return "";
}

function hideDebugLog(){
	sessionStorage.dbPromoLogLevel=0
	return "Debug Log => Off"
}
function showDebugLog(){
	sessionStorage.dbPromoLogLevel=1
	return "Debug Log => On"
}
function showDebugLogNoClear(){
	sessionStorage.dbPromoLogLevel=2
	return "Debug Log => On (no Clear)"
}
function getDebugLogLevel(){
	if (typeof(Storage) !== "undefined") {
		if (!sessionStorage.dbPromoLogLevel) {
			hideDebugLog()
		} 
		return sessionStorage.dbPromoLogLevel;
	} 
	return 0;
}
function getGridCodes(){
	let grids=$('.ag-theme-balham')
	
	grids.each(function (index,grid){
		if($(grid).attr('gridid')!==undefined){
			CM('info',$(grid).attr('gridid'));
		}
	});
	return "";
	
}

function getDebugLogSettings(){
	let level = getDebugLogLevel();

	//ALL LOGS OFF
	if(level==0){
		return {
			log_enabled: false,					// global log enabled for dbpromo
			debugger_enabled: false,
			max_array_preview: 0, 			// impostare -1 per la preview completa degli array nel log
			clear_console: false,				// pulisce la console prima di ogni creaGriglia
			display_custom_messages: false,	// abilita la visualizzazione dei messaggi custom
		};
	}

	//ALL LOGS ON (with clear)
	else if(level==1){
		return {
			log_enabled: true,					// global log enabled for dbpromo
			debugger_enabled: true,
			max_array_preview: 250, 			// impostare -1 per la preview completa degli array nel log
			clear_console: true,				// pulisce la console prima di ogni creaGriglia
			display_custom_messages: true,	// abilita la visualizzazione dei messaggi custom
		};
	}

	//ALL LOGS ON (without clear)
	else if(level==2){
		return {
			log_enabled: true,					// global log enabled for dbpromo
			debugger_enabled: true,
			max_array_preview: 250, 			// impostare -1 per la preview completa degli array nel log
			clear_console: false,				// pulisce la console prima di ogni creaGriglia
			display_custom_messages: true,	// abilita la visualizzazione dei messaggi custom
		};
	}

}

//RDG - Elimina le selection in tutte le griglie presenti in pagina premendo il pulsante ESC
$(document).keyup(function (e) {
	if (e.key === "Escape") {
		if (muiEnv == 'dbPromo') {
			let grids = $('.ag-theme-balham');
			grids.each(function (index, grid) {
				if ($(grid).attr('gridid') != undefined) {
					let gridCode = $(grid).attr('gridid');
					if (gridCode != undefined && getGridOptionFromGridMap(gridCode) != undefined
						&& getGridOptionFromGridMap(gridCode).api != undefined) {
						getGridOptionFromGridMap(gridCode).api.deselectAll();
					}
				}
			});
		} else {
			var gridList = $("[gridid]");
			var n_grid = gridList.length;
			for (i = 0; i < n_grid; i++) {
				if (document.querySelector("." + gridList[i].getAttribute("gridid")).gridOptions != undefined
					&& document.querySelector("." + gridList[i].getAttribute("gridid")).gridOptions.api != undefined) {
					document.querySelector("." + gridList[i].getAttribute("gridid")).gridOptions.api.deselectAll();
				}
			}
		}
	}
});

function addUrlParamContesto(muiContesto){
	if (muiContesto !== '') {
		return '?contesto=' + muiContesto;
	}
	return '';
}
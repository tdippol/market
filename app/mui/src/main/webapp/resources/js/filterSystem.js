var db = null;
var DB_NAME = "MUI";
var DB_VERSION = 31;
var DB_STORE_NAME = "filters";
(function() {
	openDb();
})();
function openDb() {
	var req = indexedDB.open(DB_NAME, DB_VERSION);
	req.onsuccess = function(evt) {
		db = req.result;
		initGlobalFilters();
	};
	req.onerror = function(evt) {
		console.error("Error opening Indexed DB:", evt.target.errorCode);
	};
	req.onupgradeneeded = function(evt) {
		if (!evt.currentTarget.result.objectStoreNames.contains(DB_STORE_NAME)) {
			var store = evt.currentTarget.result
					.createObjectStore(DB_STORE_NAME);
			store.createIndex("dimension", "dimension", {
				unique : true
			});
		}
	};
}
function getObjectStore(store_name, mode) {
	if (db !== null) {
		var tx = db.transaction(store_name, mode);
		return tx.objectStore(store_name);
	}
	return null;
}
function addFilter(key, value) {
	if (!jQuery.isEmptyObject(value.jsonData)) {
		var store = getObjectStore(DB_STORE_NAME, "readwrite");
		if (store !== null) {
			var req = store.put(value, key);
			req.onerror = function() {
				console.error("addFilter error", this.error);
			};
		}
	}
}
function refreshGlobalFilters(globalFilters) {
	if (globalFilters !== null) {
		$.each(globalFilters, function(key, dimensionFilter) {
			addFilter(dimensionFilter.DIM_code, dimensionFilter);
		});
	}
}
function createCombinedPicklist(cached_filters, caller) {
	var store = getObjectStore(DB_STORE_NAME, "readwrite");
	store.getAll().onsuccess = function(event) {
		var list_filter = {};
		$.each(event.target.result, function(index, filter) {
			list_filter[filter.DIM_code] = filter;
		});
		executeCreateCombinedPicklist(list_filter, cached_filters, caller);
	};
}
function executeCreateCombinedPicklist(list_filter, cached_filters, caller) {

	let fromCaller = (caller == undefined) ? '' : caller

	if (typeof list_filter === "undefined") {
		console.log("lista dei filtri null");
	}
	if (typeof cached_filters === "undefined") {
		console.log("lista dei fltri selezionati null");
	}
	if (typeof cached_filters == "") {
		console.log("lista dei fltri selezionati vuota");
	}
	if (typeof list_filter == "") {
		console.log("lista dei filtri vuota");
	}
	$
			.each(
					list_filter,
					function(key, filter) {
						var dimCode = fromCaller+filter.DIM_code;
						var dimColumnName = filter.DIM_columnName;
						var dimDescription = filter.DIM_description;
						var html_groupTab = $("#filter_" + dimCode);
						var globalJsonData = filter.jsonData;
						var list_ATTR = filter.list_ATTR;


						$.each(list_ATTR,function(key_att, attr) {

								var id_Dim_Attr = dimCode + "_"+ attr.ATTR_code;
								var id_Dim_Attr_remove = "clear_"+ dimCode + "_"+ attr.ATTR_code;


								if(attr.ATTR_type=='datePicker'){
									var datePicker = document.createElement("input");
										datePicker.style.border = "1px solid #ebedf2";
									var option_date = {
										autoclose : true,
										format : "dd/mm/yyyy",
										language : "it",
										orientation : "bottom right",
										templates : {
											leftArrow : '<i class="la la-angle-left"></i>',
											rightArrow : '<i class="la la-angle-right"></i>'
										},
										onSelect : function() {
											$(this).focus();
										}
									};
									$(datePicker).datepicker(option_date);
									$(datePicker).attr("id","datepicker_"+ dimCode+ "_"+ attr.ATTR_code);
									$(datePicker).attr("name",attr.ATTR_code);
									$(datePicker).addClass("filters form-control k_selectpicker DatePickerFilter DatePicker_"+ dimCode+ " DatePicker_"+ dimCode+ "_"+ attr.ATTR_code);
									$(datePicker).attr("dim-code", dimCode);
									$(datePicker).attr("dim-columnName",dimColumnName);
									$(datePicker).attr("dim-description",dimDescription);
									$(datePicker).attr("attr-code",attr.ATTR_code);
									$(datePicker).attr("attr-type",attr.ATTR_type);
									$(datePicker).attr("attr-columnName",attr.ATTR_columnName);
									$(datePicker).attr("attr-description",attr.ATTR_desc);
									$(datePicker).attr("server-name",filter.ENDPOINT_serverName);

									var div_attr = $("#" + id_Dim_Attr);
									div_attr.append(datePicker);

								}else{
									
									var select = $("<select ><option value=''></option></select>")
											.attr("id","select_"+ dimCode+ "_"+ attr.ATTR_code)
											.attr("name",attr.ATTR_code)
											.attr("multiple", true);
									select.attr("data-live-search",true);
									select.addClass("filters form-control k_selectpicker CombinedPicklist CombinedPicklist_"+ dimCode+ " CombinedPicklist_"+ dimCode+ "_"+ attr.ATTR_code);
									select.attr("dim-code", dimCode);
									select.attr("dim-columnName",dimColumnName);
									select.attr("dim-description",dimDescription);
									select.attr("attr-code",attr.ATTR_code);
									select.attr("attr-type",attr.ATTR_type);
									select.attr("attr-columnName",attr.ATTR_columnName);
									select.attr("attr-description",attr.ATTR_desc);
									select.attr("server-name",filter.ENDPOINT_serverName);
									var div_attr = $("#" + id_Dim_Attr);
									div_attr.append(select);
									select.selectpicker("refresh");
									var attrDimJsonData = array_unique(arrayColumn(globalJsonData,attr.ATTR_columnName));

								}
						});



						html_groupTab.append('<div class="col-4" style="margin-top:40px;"><a href="#" class="clear_all_dim_filter_'+ dimCode
											+ '" id="" style="color: #080899">Rimuovi tutti i filtri selezionati</a></div>');
						$("select.filters.CombinedPicklist_" + dimCode).on("hide.bs.select", function() {
									UASP(dimCode, globalJsonData);
								});
						$(".link_clear_selection_" + dimCode)
								.on(
										"click",
										function() {
											var a_clear = $(this);
											var attrCode = a_clear.attr("attr-code");
											var attrType = a_clear.attr("attr-type");
											
												if(attrType=='datePicker'){
													var datePicker = $("input.filters.DatePicker_"+ dimCode + "_" + attrCode);
													datePicker.val('')
												}else{
													
											var select = $("select.filters.CombinedPicklist_"
													+ dimCode + "_" + attrCode);
											resetSelectedOpt(select);
											UASP(dimCode, globalJsonData);

										}

										});
						$(".clear_all_dim_filter_" + dimCode)
								.on(
										"click",
										function() {
											var list_SelectDim = $("select.filters.CombinedPicklist_"
													+ dimCode);
											$.each(
															list_SelectDim,
															function(key) {
																var select = $(this);
																resetSelectedOpt(select);
															});
											UASP(dimCode, globalJsonData);

											var list_DatePickerDim = $("input.filters.DatePicker_"+ dimCode);
											$.each(
												list_DatePickerDim,
															function(key) {
																var datePicker = $(this);
																datePicker.val('')
															});


										});
						var list_SelectDim = $("select.filters.CombinedPicklist_"
								+ dimCode);
						$.each(list_SelectDim, function(key) {
							var select = $(this);
							resetSelectedOpt(select);
						});
						UASP(dimCode, globalJsonData);
						var list_SelectDim = $("select.filters.CombinedPicklist_"+ dimCode);
						$.each(list_SelectDim, function(key) {
							var select = $(this);
							var check_uasp = setDefaultValueFromCache(
									cached_filters, select,fromCaller);
							if (check_uasp == true) {
							}
							UASP(dimCode, globalJsonData);
							select.selectpicker("refresh");
						});

						var list_DatePickerDim = $("input.filters.DatePicker_"+ dimCode);
						
						$.each(list_DatePickerDim, function(key,DatePicker) {
							
							setDefaultValueFromCache_DatePicker(cached_filters, DatePicker,fromCaller);
								
						});


					});
	hideFilterSpinner();
}
function setDefaultValueFromCache_DatePicker(cached_filters, DatePicker,fromCaller) {
	if (cached_filters != "") {
		$.each(cached_filters, function(key, singolo_Filtro) {
			var dimCode = fromCaller+singolo_Filtro.Dimension_code;
			var attrCode = singolo_Filtro.Attribute_code;
			var arr_selectedValues = singolo_Filtro.selectedValues;
			var id = "datepicker_" + dimCode + "_" + attrCode;
			
			if (id == $(DatePicker).attr("id")) {
				let converted_date = excelNumToCellDate_noWeekDay(arr_selectedValues);

				let dd = getPartOfDate(converted_date, "dd/mm/yyyy","dd");
				let mm =  getPartOfDate(converted_date, "dd/mm/yyyy","mm");
				let yyyy =  getPartOfDate(converted_date,"dd/mm/yyyy", "yyyy");

				$(DatePicker).datepicker("setDate", new Date(yyyy, mm - 1, dd));
				//$(DatePicker).val(excelNumToCellDate_noWeekDay(arr_selectedValues))
				
			}
			

		});
		return true;
	} else {
		return false;
	}
}

function setDefaultValueFromCache(cached_filters, select,fromCaller) {
	if (cached_filters != "") {
		$.each(cached_filters, function(key, singolo_Filtro) {
			var dimCode = fromCaller+singolo_Filtro.Dimension_code;
			var attrCode = singolo_Filtro.Attribute_code;
			var arr_selectedValues = singolo_Filtro.selectedValues;
			var id = "select_" + dimCode + "_" + attrCode;
			if (id == select.attr("id")) {
				setSelectedOptArr(select, arr_selectedValues);
			}
		});
		return true;
	} else {
		return false;
	}
}
function UASP(dimCode, globalJsonData) {
	var new_json_data = update_json_with_filter(dimCode, globalJsonData);
	var list_SelectDim = $("select.filters.CombinedPicklist_" + dimCode);
	$.each(list_SelectDim, function(key) {
		var select = $(this);
		var selectedOpt = getCurrentSelectedOpt(select);
		emptySelectOpt(select);
		injectSelectOpt(select, new_json_data);
		setSelectedOpt(select, selectedOpt);
	});
}
function update_json_with_filter(dimCode, globalJsonData) {
	var list_SelectDim = $("select.filters.CombinedPicklist_" + dimCode);
	
	var list_item_selected = {};
	$.each(list_SelectDim, function(key) {
		var select = $(this);
		var select_id = select.attr("id");
		var selectedVal = select.selectpicker("val");
		if (selectedVal != "") {
			selectedVal = selectedVal.filter(function(v) {
				return v !== "";
			});
		}
		var attrCode = select.attr("attr-code");
		var attrColumnName = select.attr("attr-columnName");
		if (selectedVal.length > 0) {
			list_item_selected[attrColumnName] = selectedVal;
		}
	});
	var json_updated = globalJsonData;
	$.each(list_item_selected, function(attrColumnName) {
		json_updated = json_filter(json_updated, attrColumnName,
				list_item_selected[attrColumnName]);
	});
	return json_updated;
}
function injectSelectOpt(select, new_json_data) {
	var select_id = select.attr("id");
	var attrColumnName = select.attr("attr-columnName");
	var attrDimJsonData = Object.keys(new_json_data).map(function(k) {
		return new_json_data[k];
	});
	attrDimJsonData = array_unique(arrayColumn(attrDimJsonData, attrColumnName));
	$.each(attrDimJsonData, function(index, option) {
		if (option) {
			select.append($("<option></option>").attr("value", option).text(
					option));
		}
	});
	select.selectpicker("refresh");
}
function emptySelectOpt(select) {
	var select_id = select.attr("id");
	$("#" + select_id).find("option").remove();
	$("#" + select_id).selectpicker("refresh");
}
function resetSelectedOpt(select) {
	select.find("option").remove();
	select.selectpicker("refresh");
}
function setSelectedOpt(select, t) {
	var attrColumnName = select.attr("attr-columnName");
	t = t[attrColumnName];
	select.selectpicker("val", t);
	select.selectpicker("refresh");
}
function setSelectedOptArr(select, arr) {
	select.selectpicker("val", arr);
	select.selectpicker("refresh");
}
function getSelectDataAttribute(select, datafield) {
	return select.attr(datafield);
}
function getCurrentSelectedOpt(select) {
	var list_item_selected = {};
	var select_id = select.attr("id");
	var selectedVal = select.selectpicker("val");
	if (selectedVal) {
		selectedVal = selectedVal.filter(function(v) {
			return v !== "";
		});
	}
	var attrCode = select.attr("attr-code");
	var attrColumnName = select.attr("attr-columnName");
	if (selectedVal.length > 0) {
		list_item_selected[attrColumnName] = selectedVal;
	}
	return list_item_selected;
}
function json_filter(json, column, array_values) {
	var as = $(json).filter(function(i, n) {
		return ($.inArray(n[column], array_values) !== -1);
	});
	return as;
}
function arrayColumn(array, columnName) {
	return array.map(function(value, index) {
		return value[columnName];
	});
}
function array_unique(a) {
	var unique = a.filter(function(itm, i, a) {
		return i == a.indexOf(itm);
	});
	return unique;
}
function a(c) {
	alert(c);
}
function c(a) {
	console.log(a);
}
function isEmpty(obj) {
	for ( var key in obj) {
		if (obj.hasOwnProperty(key)) {
			return false;
		}
	}
	return true;
}
function applyAdminFilters() {
	var FILTER_OBJ = {};
	var list_SelectDim = $("select.filters.CombinedPicklist");
	$.each(list_SelectDim, function(key) {
		var OBJ_part = {};
		var jsonForUpdate = {};
		var listAttr = {};
		var select = $(this);
		var Dimension = getSelectDataAttribute(select, "dim-columnName");
		var Dimension_code = getSelectDataAttribute(select, "dim-code").replace('fromGestioneUtenti','');
		var Attribute = getSelectDataAttribute(select, "attr-columnName");
		var Attribute_code = getSelectDataAttribute(select, "attr-code");
		var ServerName = getSelectDataAttribute(select, "server-name");
		var arr_selectedValues = getCurrentSelectedOpt(select);
		if (!isEmpty(arr_selectedValues)) {
			OBJ_part.Dimension = Dimension;
			OBJ_part.Dimension_code = Dimension_code;
			OBJ_part.Dimension_desc = Dimension;
			OBJ_part.MDXjsonSource = "nothing";
			OBJ_part.Attribute = Attribute;
			OBJ_part.Attribute_code = Attribute_code;
			OBJ_part.Attribute_desc = Attribute;
			OBJ_part.ServerName = ServerName;
			OBJ_part.selectedValues = arr_selectedValues[Attribute];
			FILTER_OBJ[key] = OBJ_part;
		}
	});
	persistAdminFilters([ {
		name : "jsonFilterd",
		value : JSON.stringify(FILTER_OBJ)
	} ]);
}
function applyFilters() {
	
	var FILTER_OBJ = {};
	var GLOBAL_FILTER_OBJ = {};
	var FILTER_VALUE_OBJ = {};
	var list_SelectDim = $("select.filters.CombinedPicklist");
	$.each(list_SelectDim, function(key) {
		var OBJ_part = {};
		var jsonForUpdate = {};
		var listAttr = {};
		var select = $(this);
		var Dimension = getSelectDataAttribute(select, "dim-columnName");
		var Dimension_code = getSelectDataAttribute(select, "dim-code");
		var Dimension_desc = getSelectDataAttribute(select, "dim-description");
		var Attribute = getSelectDataAttribute(select, "attr-columnName");
		var Attribute_code = getSelectDataAttribute(select, "attr-code");
		var Attribute_type = getSelectDataAttribute(select, "attr-type");
		var Attribute_desc = getSelectDataAttribute(select, "attr-description");
		var ServerName = getSelectDataAttribute(select, "server-name");
		var arr_selectedValues = getCurrentSelectedOpt(select);
		OBJ_part.Dimension = Dimension;
		OBJ_part.Dimension_code = Dimension_code;
		OBJ_part.Dimension_desc = Dimension_desc;
		OBJ_part.MDXjsonSource = "nothing";
		OBJ_part.Attribute = Attribute;
		OBJ_part.Attribute_code = Attribute_code;
		OBJ_part.Attribute_desc = Attribute_desc;
		OBJ_part.Attribute_type = Attribute_type;
		OBJ_part.ServerName = ServerName;
		OBJ_part.selectedValues = arr_selectedValues[Attribute];
		if (!isEmpty(arr_selectedValues)) {
			FILTER_OBJ[key+'_dropdown'] = OBJ_part;
		}
	});
	
	var list_DatePickerDim = $("input.filters.DatePickerFilter");
	$.each(list_DatePickerDim, function(key) {
		var OBJ_part = {};
		var jsonForUpdate = {};
		var listAttr = {};
		var DatePicker = $(this);
		var Dimension = getSelectDataAttribute(DatePicker, "dim-columnName");
		var Dimension_code = getSelectDataAttribute(DatePicker, "dim-code");
		var Dimension_desc = getSelectDataAttribute(DatePicker, "dim-description");
		var Attribute = getSelectDataAttribute(DatePicker, "attr-columnName");
		var Attribute_code = getSelectDataAttribute(DatePicker, "attr-code");
		var Attribute_type = getSelectDataAttribute(DatePicker, "attr-type");
		var Attribute_desc = getSelectDataAttribute(DatePicker, "attr-description");
		var ServerName = getSelectDataAttribute(DatePicker, "server-name");
		
		var datePicker_val = DatePicker.val()
		
		OBJ_part.Dimension = Dimension;
		OBJ_part.Dimension_code = Dimension_code;
		OBJ_part.Dimension_desc = Dimension_desc;
		OBJ_part.MDXjsonSource = "nothing";
		OBJ_part.Attribute = Attribute;
		OBJ_part.Attribute_code = Attribute_code;
		OBJ_part.Attribute_desc = Attribute_desc;
		OBJ_part.Attribute_type = Attribute_type;
		OBJ_part.ServerName = ServerName;
		OBJ_part.selectedValues = [unformatDate(invertFormatDate(datePicker_val))];
		
		if (!isEmpty(datePicker_val)) {
			FILTER_OBJ[key+'_datepicker'] = OBJ_part;
		}
	});
	loadSelectedFilters([ {
		name : "jsonFilterd",
		value : JSON.stringify(FILTER_OBJ)
	} ]);
}
function toggleSubheaderAttribute(status, choseDimension) {
	var filter = "#dimFilter_" + String(choseDimension);
	if (status == "show") {
		$("#k_sub_header_filter").removeClass("d-none");
		$(filter).removeClass("d-none");
	} else {
		$("#k_sub_header_filter").addClass("d-none");
		$(filter).addClass("d-none");
	}
}
function toggleInitializeReference() {
	if ($("#updateableContent").hasClass("d-none")) {
		$("#updateableContent").removeClass("d-none");
	} else {
		$("#updateableContent").addClass("d-none");
	}
}
function resetPanelFilters(list_filter) {
	var list_SelectDim = $("select.filters.CombinedPicklist");
	$.each(list_SelectDim, function(key) {
		var select = $(this);
		resetSelectedOpt(select);
	});
	$.each(list_filter, function(key, filter) {
		var dimCode = filter.DIM_code;
		var globalJsonData = filter.jsonData;
		UASP(dimCode, globalJsonData);
	});

	var list_DatePickerDim = $("input.filters.DatePickerFilter");
	
		$.each(list_DatePickerDim,function(key) {
			var DatePicker = $(this);
			DatePicker.val('')
		});
}
function showFilterSpinner() {
	$(".filter-spinner").css({
		display : ""
	});
	$("#formFilterDialog").css({
		display : "none"
	});
}
function hideFilterSpinner() {
	$(".filter-spinner").css({
		display : "none"
	});
	$("#formFilterDialog").css({
		display : ""
	});
}
function loadPreselections(list, currentSelections) {
	//console.log(currentSelections);
	var store = getObjectStore(DB_STORE_NAME, "readwrite");
	if (store !== null) {
		store.getAll().onsuccess = function(event) {
			var list_filter = {};
			$.each(event.target.result, function(index, filter) {
				list_filter[filter.DIM_code] = filter;
			});
			executeLoadPreselections(list_filter, list, currentSelections);
		};
	}
}
function executeLoadPreselections(list_filter, list, currentSelections) {
	var preselectionList = Object.assign({}, list);
	if (list_filter !== null) {
		var filterJsonData = [];
		$.each(list_filter, function(key, filter) {
			filterJsonData[key] = filter.jsonData;
		});
		$.each(preselectionList, function(index, preselection) {
			loadPreselectionValues(preselection, filterJsonData[$(preselection)
					.attr("dim-code")], currentSelections);
			$(preselection).selectpicker("refresh");
		});
	} else {
	}
}
function loadPreselectionValues(preselection, filterJsonData, currentSelections) {
	if (filterJsonData == null) {
		console
				.error("Impossible caricare le preselections: filterJsonData is null");
		emptySelectOpt($(preselection));
		return;
	}

	var jsonCurrentSelections=[]

	try {
		jsonCurrentSelections=JSON.parse(currentSelections)
	} catch (error) {
		console.error("Impossible caricare le preselections: filterJsonData is not a json obj");
		emptySelectOpt($(preselection));
	}

	var list_item_selected = {};
	var selectedValue = null;
	$.each(jsonCurrentSelections, function(key, item) {
		if (item.Dimension_code === $(preselection).attr("dim-code")) {
			var attrColumnName = item.Attribute;
			list_item_selected[attrColumnName] = item.selectedValues;
		}
	});
	$.each(list_item_selected, function(attrColumnName) {
		filterJsonData = json_filter(filterJsonData, attrColumnName,
				list_item_selected[attrColumnName]);
	});
	emptySelectOpt($(preselection));
	injectSelectOpt($(preselection), filterJsonData);
	var visibleValues = [];
	$.each($("option", $(preselection)), function(key, item) {
		visibleValues.push($(item).attr("value"));
	});
	if (visibleValues.length > 0) {
		var sessionSelectedValue = sessionStorage.getItem($(preselection).attr(
				"id"));
		if (sessionSelectedValue != null
				&& visibleValues.includes(sessionSelectedValue)) {
			selectedValue = [ sessionSelectedValue ];
		} else {
			selectedValue = [ visibleValues[0] ];
			sessionStorage.setItem($(preselection).attr("id"), selectedValue);
		}
	}
	setSelectedOptArr($(preselection), selectedValue);
}
function onPreselectionChange(preselection) {
	var id = $(preselection).attr("id");
	var selectedValue = document.getElementById(id).value;
	sessionStorage.setItem(id, selectedValue);
	performPreSelection([ {
		name : "id",
		value : id
	}, {
		name : "selectedValue",
		value : selectedValue
	} ]);
}
















function silentActionFilter(param_request , param_currentSelections, componentId){

//			var param = JSON.parse('{ "SSPromoWeek": ["Descrizione","Anno"],"SSCompratore": ["Descrizione"]}');
			
			var param = JSON.parse(param_request);
		
			var currentSelections=JSON.parse(param_currentSelections);
		
		
			var list_DIM_code=Object.keys(param);
			
			var filterStructure=[];
			var DB_STORE_NAME = "filters";
			var store = getObjectStore(DB_STORE_NAME, "readwrite");
			store.getAll().onsuccess = function(event) {
				var list_to_filter = {};
				$.each(event.target.result, function(index, filter) {
				
						if(list_DIM_code.indexOf(filter.DIM_columnName)!=-1){
							
							list_to_filter[filter.DIM_code] = filter.jsonData;
							
													
							//utile per la ricerca ATTR_code<->ATTR_columnName
								delete filter.MDX_jsonSource;
								delete filter.ENDPOINT_serverName;
								delete filter.jsonData;
							filterStructure.push(filter);
						}

				});
				
				//console.log(filterStructure);
				
			
				//console.log("DATI DA FILTRARE",list_to_filter);
				
				//---------------------------------
				//Creo mappa con i valori selezionati
				var map_selectedValues={};
				var selectedAttr={};
				//console.log("CURRENT SELECTIONS",currentSelections);
				$.each(currentSelections,function(curSel_key,curSel_item){
					
					if( (Object.keys(list_to_filter)).indexOf(curSel_item.Dimension_code)>=0 ){
						
						if(map_selectedValues[curSel_item.Dimension_code]==undefined){
							map_selectedValues[curSel_item.Dimension_code]=[];
						}
						
						selectedAttr={
								"Attribute":curSel_item.Attribute,
								"ATTR_code":curSel_item.Attribute_code,
								"selectedValues":curSel_item.selectedValues
						};
						
						map_selectedValues[curSel_item.Dimension_code].push(selectedAttr);
						
					}
				});
				
				
				//TAGLIO DEI DATI
				var filteredData=list_to_filter;
				$.each(map_selectedValues,function(DIM_code,item){
					$.each(item,function(attribute_key,attribute_item){
						filteredData = cutAvaiableData(filteredData,DIM_code,attribute_item);
					});
					
				});
				
				//---------------------------------
				//FILTRO DEI CAMPI PER QUELLI RICHIESTI
				
				var finalResult=[];
				var obj_result={};
				$.each(param,function(DIM_columnName,attr_list){
					//console.log(DIM_code,attr_list);
					$.each(attr_list,function(attribute_key,ATTR_columnName){
						
						let DIM_code = getDimensionMeta(filterStructure,'DIM_columnName',DIM_columnName,'DIM_code');
						let ATTR_code = getAttributeMeta(filterStructure,DIM_columnName,'ATTR_columnName',ATTR_columnName,'ATTR_code');
						
						if(ATTR_code!==false){
							
							let uniqueValuesOfAttrColname=(filteredData[DIM_code]).map(function(row) { return row[ATTR_columnName]; });
							
							uniqueValuesOfAttrColname=uniqueValuesOfAttrColname.filter( function onlyUnique(value, index, self) { return self.indexOf(value) === index;} );
							
							obj_result={
								"Dim_code":DIM_code,
								"Dim_columnName":DIM_columnName,
								"Attr_code":ATTR_code,
								"Attr_columnName":ATTR_columnName,
								"avaiableValues":uniqueValuesOfAttrColname
							}
							
							
							finalResult.push(obj_result);
						}
					});
					
				});
				
				
				//-----check errori
				var check_warning=false;
				var list_warning=[];
				$.each(param,function(DIM_columnName,attr_list){
					$.each(attr_list,function(attribute_key,ATTR_columnName){
							
							var exist_result=0;
							$.each(finalResult,function(key,item_result){
								
								if(item_result.Dim_columnName==DIM_columnName && item_result.Attr_columnName==ATTR_columnName){
									exist_result=1;
								}
								
							});
							
							if(exist_result==0){
								list_warning.push(DIM_columnName+"."+ATTR_columnName);
								check_warning=true;
							}
							
					});
				});
				
					
				if(check_warning===true){
					addToast("warning","Attenzione!","Alcuni parametri impostati per l'esecuzione del processo non hanno generato risultati: "+JSON.stringify(list_warning));
					return false;
				}else{
					
					console.log("FINAL RESULT (callback here) ",JSON.stringify(finalResult));
					performSilentAction([{name:'silence', value:JSON.stringify(finalResult)},{name:'componentId', value:componentId}]);
				}
			};
	}
	
	
	function cutAvaiableData(list_to_filter,DIM_code,item){
		
		var new_Array=[];
		$.each(list_to_filter,function(key_DIM_code,arr_Data){
			
			if(key_DIM_code==DIM_code){
				new_Array=arr_Data.filter(function (row) {
					if((item.selectedValues).indexOf(row[item.Attribute])>=0){
						return row;
					}
				});
			}
			
		});
		
		list_to_filter[DIM_code]=new_Array;
		
		return list_to_filter;
		
	}
	
	
	
	
	
	
	
	function getAttributeMeta(filterStructure,DIM_columnName,searchField,searchValue,returnField){
		var return_value=false;
		
		$.each(filterStructure,function(key,item){
			if(item.DIM_columnName==DIM_columnName){
				//console.log(item.list_ATTR);
				$.each(item.list_ATTR,function(attr_key,attribute){
					if(attribute[searchField]==searchValue){
						return_value = attribute[returnField];
					}
				});
			}
		});
	
		return return_value;
	
	}
	
	
	
	function getDimensionMeta(filterStructure,searchField,searchValue,returnField){
		var return_value=false;
		$.each(filterStructure,function(key,item){
			if(item[searchField]==searchValue){
				return_value = item[returnField];
			}
		});
	
		return return_value;
	
	}
	
	
	function addToast(level,obj,message){
		
		if(level=='warning'){
			addWarningGrowl([{name: "title", value: obj}, {
						name: "message",
						value: message
					}]);
		}
		if(level=='error'){
			addErrorGrowl([{name: "title", value: obj}, {
						name: "message",
						value: message
					}]);
		}
	}
	
var devel = false;
var AxAgColumnTypes = {
	TM1DataColumnText : {
		cellEditorSelector : axCellEditorSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser,
		valueSetter : axValueSetter
	},
	TM1DataColumnNumber : {
		cellEditorSelector : axCellEditorSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser,
		valueSetter : axValueSetter
	},
	TM1DataColumnInteger : {
		cellEditorSelector : axCellEditorSelector,
		cellRendererSelector : cellRendererSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser,
		valueSetter : axValueSetter
	},
	TM1DataColumnDate : {
		cellEditorSelector : axCellEditorSelector,
		cellRendererSelector : cellRendererSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser,
		valueSetter : axValueSetter,
		filterValueGetter : AX_filterValueGetter,
	},
	TM1DataColumnCheckbox : {
		cellEditorSelector : axCellEditorSelector,
		cellRendererSelector : cellRendererSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser,
		valueSetter : axValueSetter
	},
	TotalDataColumnNumber : {
		cellEditorSelector : axCellEditorSelector,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser
	},
	TM1Element : {
		cellStyle : axCellStyle
	},
	TM1DataColumnNumberK : {
		cellEditorSelector : axCellEditorSelector,
		cellRendererSelector : cellRendererSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser,
		valueSetter : axValueSetter
	},
	TM1DataColumnPercentage : {
		cellEditorSelector : axCellEditorSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParse : axValueParser,
		valueSetter : axValueSetter
	},
	TM1DataColumnIntegerPercentage : {
		cellEditorSelector : axCellEditorSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParse : axValueParser,
		valueSetter : axValueSetter
	},
	TM1DataColumnFlatPercentage : {
		cellEditorSelector : axCellEditorSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParse : axValueParser,
		valueSetter : axValueSetter
	},
	TM1DataColumnDecimal3 : {
		cellEditorSelector : axCellEditorSelector,
		valueGetter : axValueGetter,
		cellStyle : axCellStyle,
		valueFormatter : axValueFormatter,
		valueParser : axValueParser,
		valueSetter : axValueSetter
	}
};

function AX_filterValueGetter(params){
	
	var type 	= params.colDef.type;
	var value 	= evaluate(params, "Value"); 
	
	if (type.includes("TM1DataColumnDate")) {
		value = excelNumToCellDate(value);
	}

	return value;
}





function axCellEditorSelector(params) {
	var type = params.colDef.type;
	if (type.includes("TotalDataColumnNumber")) {
		return {
			component : "numericCellEditor"
		};
	}
	var isUpd = evaluate(params, "Updateable");
	if ("Delete" === event.code) {
		prepareMultipleCellPut(params);
		multipleDeleteCells(params);
		executeMultipleCellPut(params);
	}
	if (isUpd === true) {
		if (evaluate(params, "HasPicklist") === true) {
			var data = {};
			data.row = params.data.RowKeys;
			data.grid = params.node.beans.gridOptionsWrapper.environment.eGridDiv.getAttribute('gridid');
        	data.ordinal = params.data[params.column.colId].GridColumn;
			var jsonData = JSON.stringify(data);
			var picklistValues = [];
			if (event.keyCode === 46) {
				params.api.stopEditing()
			} else {
				getPicklistValues([ {
					name : "data",
					value : jsonData
				} ]);
			}
			window.asynchPicklistValues = function(xhr, status, args) {
				var picklist = [];
				if (args.picklist != null) {
					picklist = JSON.parse(args.picklist);
					picklist.push("");
				}
				var select = $(".ag-cell-edit-input select");
				select.closest(".ag-cell-inline-editing").addClass(
						"searchablePicklist");
				$.each(picklist, function(index, value) {
					var option = $("<option></option>");
					option.attr("value", value).text(value);
					if (globalParms.value !== "" && globalParms.value != null
							&& globalParms.value === value) {
						option.prop("selected", true);
					}
					select.append(option);
				});
				select.selectpicker("refresh");
				var searchablePicklist = $(
						".searchablePicklist .dropdown-menu:first").detach();
				if (searchablePicklist != null) {
					searchablePicklist.appendTo("body");
					searchablePicklist.css("z-index", 999999).css("position",
							"absolute");
					if (select.offset() != null) {
						searchablePicklist.css(select.offset());
					}
					searchablePicklist.addClass("gridPicklist");
					$(".gridPicklist .inner .show").css("width", 100 + "%")
							.css("position", "relative").css("top", "0px");
				}
				$(".gridPicklist").click(function() {
					$(".gridPicklist").hide().remove();
				});
				$("body")
						.on(
								"click",
								function() {
									var found = false;
									$(".gridPicklist .inner .show")
											.each(
													function(key, value) {
														if ($(".gridPicklist .inner .show")[key].clientHeight > 0) {
															found = true;
														}
													});
									if (found) {
										$(".gridPicklist").hide().remove();
										if (globalParms != null) {
											globalParms.stopEditing();
										}
									}
								});
				$(".ag-cell-focus .searchablePicklist button").removeAttr(
						"title").trigger("click");
			};
			return {
				component : "picklistCellEditor",
				params : {
					values : picklistValues
				}
			};
		} else {
			if (type.includes("TM1DataColumnText")) {
				return {
					component : "agTextCellEditor"
				};
			} else {
				if (type.includes("TM1DataColumnNumber")
						|| type.includes("TM1DataColumnPercentage")
						|| type.includes("TM1DataColumnDecimal3")) {
					return {
						component : "numericCellEditor"
					};
				} else {
					if (type.includes("TM1DataColumnInteger")
							|| type.includes("TM1DataColumnIntegerPercentage")
							|| type.includes("TM1DataColumnFlatPercentage")
							) {
						return {
							component : "numericCellEditorInt"
						};
					} else {
						if (type.includes("TM1DataColumnDate")) {
							return {
								component : "dateCellEditor"
							};
						} else {
							if (type.includes("TM1DataColumnCheckbox")) {
								return {
									component : "checkboxCellEditor"
								};
							} else {
								if (type.includes("TM1DataColumnNumberK")) {
									return {
										component : "numericCellEditorK"
									};
								}
							}
						}
					}
				}
			}
		}
	}
	return {
		component : "uneditableCellEditor"
	};
}
function cellRendererSelector(params) {
	var type = params.colDef.type;
	if (type.includes("TotalDataColumnNumber")
			|| type.includes("TM1DataColumnNumber")
			|| type.includes("TM1DataColumnDecimal3")) {
		return {
			component : "numericCellRenderer"
		};
	} else {
		if (type.includes("TM1DataColumnNumberK")) {
			return {
				component : "numericCellRendererK"
			};
		} else {
			if (type.includes("TM1DataColumnInteger")) {
				return {
					component : "numericCellRendererInt"
				};
			}
		}
	}
	try {
		var isUpd = evaluate(params, "Updateable");
		if (isUpd === true) {
			if (evaluate(params, "HasPicklist") === true) {
				var data = {};
				data.row = params.data.RowKeys;
				data.grid = params.node.beans.gridOptionsWrapper.environment.eGridDiv.getAttribute('gridid');
				data.ordinal = params.data[params.column.colId].GridColumn;
				var jsonData = JSON.stringify(data);
				var picklistValues = [];
				getPicklistValues([ {
					name : "data",
					value : jsonData
				} ]);
				window.asynchPicklistValues = function(xhr, status, args) {
					var picklist = [];
					if (args.picklist != null) {
						picklist = JSON.parse(args.picklist);
						picklist.push("");
					}
					$.each(picklist, function(index, value) {
						$(".ag-cell-edit-input select").append(
								$("<option></option>").attr("value", value)
										.text(value));
					});
				};
				return {
					component : "picklistCellEditor",
					params : {
						values : picklistValues
					}
				};
			}
		}
		if (type.includes("TM1DataColumnDate")) {
			return {
				component : "dateCellRenderer"
			};
		} else {
			if (type.includes("TM1DataColumnCheckbox")) {
				return {
					component : "checkboxCellRenderer"
				};
			}
		}
	} catch (Err) {
		return {
			component : "uneditableCellEditor"
		};
	}
	return {
		component : "uneditableCellEditor"
	};
}
function axValueGetter(params) {
	var type = params.colDef.type;
	if (type.includes("TM1DataColumnText")) {
		return evaluate(params, "Value");
	} else {
		if (type.includes("TM1DataColumnNumber")
				|| type.includes("TM1DataColumnPercentage")
				|| type.includes("TM1DataColumnIntegerPercentage")
				|| type.includes("TM1DataColumnFlatPercentage")
				|| type.includes("TM1DataColumnNumberK")
				|| type.includes("TM1DataColumnDecimal3")) {
			if (evaluate(params, "Value") !== "") {
				return Number(evaluate(params, "Value"));
			}
			return null;
		} else {
			if (type.includes("TM1DataColumnInteger")) {
				if (evaluate(params, "Value") !== "") {
					return Math.round(evaluate(params, "Value"));
				}
				return null;
			} else {
				if (type.includes("TM1DataColumnCheckbox")) {
					return Number(evaluate(params, "Value"));
				} else {
					if (type.includes("TM1DataColumnDate")) {
						return evaluate(params, "Value");
					}
				}
			}
		}
	}
}
function axValueFormatter(params) {
	var type = params.colDef.type;
	if (type.includes("TM1DataColumnText")) {
		if ("" + params.value === "NaN") {
			params.value = "";
		}
		return params.value;
	} else {
		if (type.includes("TM1DataColumnNumber")
				|| type.includes("TM1DataColumnNumberK")
				|| type.includes("TotalDataColumnNumber")) {
			if (params.value === null) {
				return null;
			}
			return formatNumber(params.value);
		} else {
			if (type.includes("TM1DataColumnDecimal3")) {
				if (params.value === null) {
					return null;
				}
				return formatNumber(params.value, ",", ".", 3);
			} else {
				if (type.includes("TM1DataColumnInteger")) {
					if (params.value === null) {
						return null;
					}
					return formatInteger(params.value);
				} else {
					if (type.includes("TM1DataColumnDate")) {
						if (params.value === null) {
							return null;
						}
						return formatDate(params.value);
					} else {
						if (type.includes("TM1DataColumnCheckbox")) {
							if (params.value === null || params.value === "") {
								return 0;
							}
							return params.value;
						} else {
							if (type.includes("TM1DataColumnPercentage")) {
								if (params.value === null) {
									return null;
								}
								return formatPercentage(params.value);
							} else {
								if (type
										.includes("TM1DataColumnIntegerPercentage")) {
									if (params.value === null) {
										return null;
									}
									return formatIntegerPercentage(params.value);
								}else{
									if (type
										.includes("TM1DataColumnFlatPercentage")) {
										if (params.value === null) {
											return null;
										}
										return formatFlatPercentage(params.value);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
function axValueParser(params) {
	var type = params.colDef.type;
	if (type.includes("TM1DataColumnText")
			|| type.includes("TM1DataColumnCheckbox")) {
		return params.newValue;
	} else {
		if (type.includes("TM1DataColumnNumber")
				|| type.includes("TM1DataColumnNumberK")
				|| type.includes("TotalDataColumnNumber")
				|| type.includes("TM1DataColumnDecimal3")) {
			return unformatNumber(params.newValue);
		} else {
			if (type.includes("TM1DataColumnInteger")) {
				return unformatInteger(params.newValue);
			} else {
				if (type.includes("TM1DataColumnDate")) {
					return unformatDate(params.newValue);
				} else {
					if (type.includes("TM1DataColumnPercentage")
							|| type.includes("TM1DataColumnIntegerPercentage")
							|| type.includes("TM1DataColumnFlatPercentage")
						) {
						return unformatPercentage(params.newValue);
					}
				}
			}
		}
	}
}
function axValueSetter(params) {
	var isUpd = evaluate(params, "Updateable");
	if (isUpd === true) {
		var type = params.colDef.type;
		if (type.includes("TM1DataColumnText")) {
			if(params.newValue==undefined){
				params.newValue=""
			}
			eval("params.node.data." + params.colDef.field
					+ ".Value= params.newValue");
			return true;
		} else {
			if (type.includes("TM1DataColumnInteger")
					|| type.includes("TM1DataColumnNumber")
					|| type.includes("TM1DataColumnCheckbox")
					|| type.includes("TM1DataColumnNumberK")
					|| type.includes("TotalDataColumnNumber")
					|| type.includes("TM1DataColumnDecimal3")) {
				if (!(isNaN(params.newValue))) {
					eval("params.node.data." + params.colDef.field
							+ ".Value= Number(params.newValue)");
				} else {
					var newValue = Number(unformatNumber(params.newValue));
					if (isNaN(newValue)) {
						eval("params.node.data." + params.colDef.field
								+ ".Value= Number(params.oldValue)");
					} else {
						eval("params.node.data." + params.colDef.field
								+ ".Value= " + newValue);
					}
				}
				return true;
			} else {
				if (type.includes("TM1DataColumnDate")) {
					params.newValue = unformatDate(invertFormatDate(params.newValue));
					if (!(isNaN(params.newValue))) {
						eval("params.node.data." + params.colDef.field
								+ ".Value= params.newValue");
					} else {
						eval("params.node.data." + params.colDef.field
								+ ".Value=params.oldValue");
					}
					return true;
				} else {
					if (type.includes("TM1DataColumnPercentage")) {
						return unformatPercentage(params.newValue);
					}
				}
			}
		}
	}
}
function axCellStyle(params) {
	var type = params.colDef.type;
	if (type === "TM1Element") {
		if (params.node.allChildrenCount === null) {
			return {
				color : "gray"
			};
		} else {
			return {
				"background-color" : "inherit",
				"font-weight" : "bold"
			};
		}
		return {
			color : "gray"
		};
	}
	if (params.node.allChildrenCount === null) {
		var isUpd = false;
		try {
			isUpd = evaluate(params, "Updateable");
		} catch (Err) {
			console.log("error determining update state " + Err);
		}
		var isCons = false;
		try {
			isCons = Number(evaluate(params, "Consolidated"));
		} catch (Err) {
			console.log("error determining consolidated state " + Err);
		}
		if (isUpd === true) {
			if (isCons) {
				return {
					"background-color" : "inherit",
					"font-weight" : "bold",
					color : "black"
				};
			} else {
				return {
					"background-color" : "#fff",
					color : "black"
				};
			}
		} else {
			return {
				"background-color" : "inherit",
				"font-weight" : "bold",
				color : "Black"
			};
		}
	} else {
		return {
			"background-color" : "inherit",
			"font-weight" : "bold",
			color : "Black"
		};
	}
	return {
		color : "gray"
	};
}
function evaluate(params, value) {
	try {
		return eval("params.node.data." + params.colDef.field + "." + value);
	} catch (e) {
		devel
				&& console.log("error evaluating column " + params.colDef.field
						+ " while searching for " + value);
		return "";
	}
}
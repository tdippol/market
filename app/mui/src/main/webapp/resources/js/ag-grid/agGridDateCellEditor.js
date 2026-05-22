function DateCellEditor() {
}
DateCellEditor.prototype.init = function(params) {

	this.eInput = document.createElement("input");
	this.eInput.value = formatDate(params.value);
	var option_date = {
		rtl : KUtil.isRTL(),
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
	
	
	if(params.optionDate_startDate){
		option_date.startDate = params.optionDate_startDate	
	}
	
	
	if (params.value !== undefined && params.value !== null
			&& params.value !== 0 && params.value !== "") {
		option_date.todayHighlight = false;
	} else {
		option_date.todayHighlight = true;
	}
	$(this.eInput).datepicker(option_date);
	if (params.value !== undefined && params.value !== null
			&& params.value !== 0 && params.value !== "") {
		var dd = getPartOfDate(standardFormatDate(params.value), "dd-mm-yyyy",
				"dd");
		var mm = getPartOfDate(standardFormatDate(params.value), "dd-mm-yyyy",
				"mm");
		var yyyy = getPartOfDate(standardFormatDate(params.value),
				"dd-mm-yyyy", "yyyy");
		$(this.eInput).datepicker("setDate", new Date(yyyy, mm - 1, dd));
	}

	let globalParams=params
	$(this.eInput).datepicker().on('changeDate', function(e) {
		globalParams.stopEditing();
	})

	if (isCharNumeric(params.charPress)) {
		this.eInput.value = params.charPress;
	} else {
		if (params.value !== undefined && params.value !== null) {
			this.eInput.value = formatDate(params.value);
		}
	}
	var that = this;
	this.eInput.addEventListener("keypress", function(event) {
		if (!isKeyPressedNumeric(event)) {
			that.eInput.focus();
			if (event.preventDefault) {
				event.preventDefault();

			}
		} else {
			if (that.isKeyPressedNavigation(event)) {
				event.stopPropagation();
			}
		}
	});
	if (isCharDelete(params.keyPress)) {
		this.eInput.value = "";
		$(this.eInput).datepicker("destroy");
		$(this.eInput).hide();
	}
	var charPressIsNotANumber = params.charPress
			&& (" ".indexOf(params.charPress) < 0);
	this.cancelBeforeStart = charPressIsNotANumber;
};
DateCellEditor.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
DateCellEditor.prototype.getGui = function() {
	return this.eInput;
};
DateCellEditor.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
DateCellEditor.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
DateCellEditor.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
DateCellEditor.prototype.getValue = function() {
	return unformatNumber(this.eInput.value);
	return this.eInput.value;
};
DateCellEditor.prototype.destroy = function() {
};
DateCellEditor.prototype.isPopup = function() {
	return false;
};
function NumericCellEditor() {
}
NumericCellEditor.prototype.init = function(params) {
	this.eInput = document.createElement("input");
	if (isCharNumeric(params.charPress)) {
		this.eInput.value = params.charPress;
	} else {
		if (params.value !== undefined && params.value !== null) {
			this.eInput.value = formatNumber(params.value);
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
	var charPressIsNotANumber = !isCharIntegrer(params.charPress);
	if (params.keyPress === 46) {
		this.eInput.value = "0";
		this.cancelBeforeStart = false;
	} else {
		this.cancelBeforeStart = charPressIsNotANumber;
	}
};
NumericCellEditor.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
NumericCellEditor.prototype.getGui = function() {
	return this.eInput;
};
NumericCellEditor.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
NumericCellEditor.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
NumericCellEditor.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
NumericCellEditor.prototype.getValue = function() {
	return unformatNumber(this.eInput.value);
};
NumericCellEditor.prototype.destroy = function() {
};
NumericCellEditor.prototype.isPopup = function() {
	return false;
};
function NumericCellEditorInt() {
}
NumericCellEditorInt.prototype.init = function(params) {
	this.eInput = document.createElement("input");
	if (isCharIntegrer(params.charPress)) {
		this.eInput.value = params.charPress;
	} else {
		if (params.value !== undefined && params.value !== null) {
			this.eInput.value = formatInteger(params.value);
		}
	}
	var that = this;
	this.eInput.addEventListener("keypress", function(event) {
		if (!isKeyPressedInteger(event)) {
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
NumericCellEditorInt.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
NumericCellEditorInt.prototype.getGui = function() {
	return this.eInput;
};
NumericCellEditorInt.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
NumericCellEditorInt.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
NumericCellEditorInt.prototype.isCancelAfterEnd = function() {
	return false;
};
NumericCellEditorInt.prototype.getValue = function() {
	return unformatInteger(this.eInput.value);
};
NumericCellEditorInt.prototype.destroy = function() {
};
NumericCellEditorInt.prototype.isPopup = function() {
	return false;
};
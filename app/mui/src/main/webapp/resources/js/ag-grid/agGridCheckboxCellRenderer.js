function CheckboxCellRenderer() {
}
CheckboxCellRenderer.prototype.init = function(params) {
	if (params.value !== undefined && params.value !== null
			&& params.value !== 0) {
		this.eInput = document.createElement("i");
		this.eInput.className = "fa fa-check";
	} else {
	}
	var charPressIsNotANumber = params.charPress
			&& ("1234567890".indexOf(params.charPress) < 0);
	this.cancelBeforeStart = charPressIsNotANumber;
};
CheckboxCellRenderer.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
CheckboxCellRenderer.prototype.getGui = function() {
	return this.eInput;
};
CheckboxCellRenderer.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
CheckboxCellRenderer.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
CheckboxCellRenderer.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
CheckboxCellRenderer.prototype.getValue = function() {
	return this.eInput.value;
};
CheckboxCellRenderer.prototype.destroy = function() {
};
CheckboxCellRenderer.prototype.isPopup = function() {
	return false;
};
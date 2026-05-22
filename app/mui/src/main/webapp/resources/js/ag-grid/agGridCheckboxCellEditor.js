function CheckboxCellEditor() {
}
CheckboxCellEditor.prototype.init = function(params) {
	this.eInput = document.createElement("input");
	this.eInput.type = "checkbox";
	if (params.value !== undefined && params.value !== null
			&& params.value !== 0) {
		this.eInput.value = params.value;
		this.eInput.checked = true;
	} else {
		this.eInput.value = 0;
		this.eInput.checked = false;
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
	this.eInput.addEventListener("change", function() {
		if (that.eInput.checked) {
			that.eInput.value = 1;
		} else {
			that.eInput.value = 0;
		}
	});
	var charPressIsNotANumber = params.charPress
			&& ("1234567890".indexOf(params.charPress) < 0);
	this.cancelBeforeStart = charPressIsNotANumber;
};
CheckboxCellEditor.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
CheckboxCellEditor.prototype.getGui = function() {
	return this.eInput;
};
CheckboxCellEditor.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
CheckboxCellEditor.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
CheckboxCellEditor.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
CheckboxCellEditor.prototype.getValue = function() {
	return this.eInput.value;
};
CheckboxCellEditor.prototype.destroy = function() {
};
CheckboxCellEditor.prototype.isPopup = function() {
	return false;
};
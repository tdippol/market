function DateCellRenderer() {
}
DateCellRenderer.prototype.init = function(params) {
	
	
	this.eInput = document.createElement("span");
	if(params.showDateTime && params.showDateTime===true){
		this.eInput.innerHTML = formatDate(params.value,true);	
	}else{
		this.eInput.innerHTML = formatDate(params.value);	
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
	var charPressIsNotANumber = params.charPress
			&& ("1234567890".indexOf(params.charPress) < 0);
	this.cancelBeforeStart = charPressIsNotANumber;
};
DateCellRenderer.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
DateCellRenderer.prototype.getGui = function() {
	return this.eInput;
};
DateCellRenderer.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
DateCellRenderer.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
DateCellRenderer.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
DateCellRenderer.prototype.getValue = function() {
	return unformatNumber(this.eInput.value);
	return this.eInput.value;
};
DateCellRenderer.prototype.destroy = function() {
};
DateCellRenderer.prototype.isPopup = function() {
	return false;
};
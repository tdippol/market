function NumericCellEditorK() {}
NumericCellEditorK.prototype.init = function(params) {
    this.eInput = document.createElement("input");
    if (isCharNumeric(params.charPress)) {
        this.eInput.value = params.charPress;
    } else {
        if (params.value !== undefined && params.value !== null) {
            this.eInput.value = formatNumber(params.value / 1000);
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
NumericCellEditorK.prototype.isKeyPressedNavigation = function(event) {
    return event.keyCode === 39 || event.keyCode === 37;
};
NumericCellEditorK.prototype.getGui = function() {
    return this.eInput;
};
NumericCellEditorK.prototype.afterGuiAttached = function() {
    this.eInput.focus();
};
NumericCellEditorK.prototype.isCancelBeforeStart = function() {
    return this.cancelBeforeStart;
};
NumericCellEditorK.prototype.isCancelAfterEnd = function() {
    return false;
};
NumericCellEditorK.prototype.getValue = function() {
    return unformatNumber(this.eInput.value) * 1000;
};
NumericCellEditorK.prototype.destroy = function() {};
NumericCellEditorK.prototype.isPopup = function() {
    return false;
};
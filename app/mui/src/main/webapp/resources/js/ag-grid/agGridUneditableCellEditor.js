function UneditableCellEditor() {
}
UneditableCellEditor.prototype.init = function(params) {
	this.cancelBeforeStart = true;
};
UneditableCellEditor.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
UneditableCellEditor.prototype.getGui = function() {
	return null;
};
UneditableCellEditor.prototype.afterGuiAttached = function() {
};
UneditableCellEditor.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
UneditableCellEditor.prototype.isCancelAfterEnd = function() {
	return false;
};
UneditableCellEditor.prototype.getValue = function() {
	return null;
};
UneditableCellEditor.prototype.destroy = function() {
};
UneditableCellEditor.prototype.isPopup = function() {
	return false;
};
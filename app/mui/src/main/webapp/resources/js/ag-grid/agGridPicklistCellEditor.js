function PicklistCellEditor() {
}
var globalParms;
PicklistCellEditor.prototype.init = function(params) {
	globalParms = params;
	this.eInput = document.createElement("div");
	var div = $(this.eInput);
	div.addClass("bootstrap-select ag-cell-edit-input");
	var select = $("<select onblur='globalParms.stopEditing();' onchange='globalParms.stopEditing();'></select>");
	select.attr("data-live-search", true);
	select.attr("autoclose", true);
	select.attr("name", "picklist");
	select.addClass("k_selectpicker ag-cell-edit-input searchablePicklist");
	div.append(select);
};
PicklistCellEditor.prototype.isKeyPressedNavigation = function(event) {
	return event.keyCode === 39 || event.keyCode === 37;
};
PicklistCellEditor.prototype.getGui = function() {
	return this.eInput;
};
PicklistCellEditor.prototype.afterGuiAttached = function() {
	this.eInput.focus();
};
PicklistCellEditor.prototype.isCancelBeforeStart = function() {
	return this.cancelBeforeStart;
};
PicklistCellEditor.prototype.isCancelAfterEnd = function() {
	var value = this.getValue();
	return false;
};
PicklistCellEditor.prototype.getValue = function() {
	return $(this.eInput).find("select").val();
};
PicklistCellEditor.prototype.destroy = function() {
};
PicklistCellEditor.prototype.isPopup = function() {
	return false;
};
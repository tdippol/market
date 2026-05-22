function NumericCellRendererInt() {
}
NumericCellRendererInt.prototype.init = function(params) {
	this.eGui = document.createElement("span");
	if (params.value !== "" && params.value !== undefined
			&& params.value !== null) {
		this.eGui.innerHTML = formatInteger(params.value);
	}
};
NumericCellRendererInt.prototype.getGui = function() {
	return this.eGui;
};
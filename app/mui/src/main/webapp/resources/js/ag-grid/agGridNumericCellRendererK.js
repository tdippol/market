function NumericCellRendererK() {}
NumericCellRendererK.prototype.init = function(params) {
    this.eGui = document.createElement("span");
    if (params.value !== "" && params.value !== undefined && params.value !== null) {
        var th = " k";
        this.eGui.innerHTML = formatNumber(params.value / 1000) + th;
    }
};
NumericCellRendererK.prototype.getGui = function() {
    return this.eGui;
};
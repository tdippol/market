function formatNumber(num, d, t, c) {
    var n = num,
        c = isNaN(c = Math.abs(c)) ? 2 : c,
        d = d == undefined ? "," : d,
        t = t == undefined ? "." : t,
        s = n < 0 ? "-" : "",
        i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))),
        j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}

function formatInteger(num, d, t) {
    var n = num,
        c = 0;
    d = d == undefined ? "," : d, t = t == undefined ? "." : t, s = n < 0 ? "-" : "", i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))), j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}

function unformatInteger(num, d, t) {
    var n = num;
    var res;
    d = d == undefined ? "," : d;
    t = t == undefined ? "." : t;
    res = n.replace(t, "");
    res = res.replace(d, ".");
    return res;
}

function unformatNumber(num, d, t) {
    var n = num;
    var res;
    res = n.replace(/,/g, ".");
    var parts = res.split(".");
    if (parts[1] === undefined) {
        return res;
    } else {
        return parts.slice(0, -1).join("") + "." + parts.slice(-1);
    }
}

function formatPercentage(num) {
    if (num == null) {
        return "";
    }
    var percen = formatNumber(100 * num) + " %";
    return percen;
}

function formatIntegerPercentage(num) {
    if (num == null) {
        return "";
    }
    var percen = formatInteger(100 * num) + " %";
    return percen;
}

function formatFlatPercentage(num){
	if (num == null) {
        return "";
    }
    var percen = formatNumber(num) + " %";
    return percen;
}

function unformatPercentage(percen) {
    var unf = percen.replace("%", "");
    return unf;
}

function standardFormatDate(num) {
    if (num == null || num === "") {
        return "";
    }
    var $Date = new Date(Math.round((num - 25569) * 86400 * 1000));
    var day = ("0" + $Date.getDate()).slice(-2);
    var month = ("0" + ($Date.getMonth() + 1)).slice(-2);
    var year = ("" + $Date.getFullYear());
    var $Date_formatted = day + "-" + month + "-" + year;
    return $Date_formatted;
}


function formatDate(num,showDateTime) {
	
	
    if (num == null || num === "") {
        return "";
    }
    var $Date = new Date(Math.round((num - 25569) * 86400 * 1000));
    
    var $Date_formatted=dateToCellString($Date,showDateTime)
    
    return $Date_formatted;
}

function dateToCellString($Date,showDateTime){
	
	var day = ("0" + $Date.getDate()).slice(-2);
    var month = ("0" + ($Date.getMonth() + 1)).slice(-2);
    var year = ("" + $Date.getFullYear()).slice(-2);
    var $Date_formatted = day + "-" + month + "-" + year;
    var Nday_ofTheWeek = $Date.getDay();
    var DayOfTheWeek_IT = "";
    if (Nday_ofTheWeek == 0) {
        DayOfTheWeek_IT = "Dom";
    }
    if (Nday_ofTheWeek == 1) {
        DayOfTheWeek_IT = "Lun";
    }
    if (Nday_ofTheWeek == 2) {
        DayOfTheWeek_IT = "Mar";
    }
    if (Nday_ofTheWeek == 3) {
        DayOfTheWeek_IT = "Mer";
    }
    if (Nday_ofTheWeek == 4) {
        DayOfTheWeek_IT = "Gio";
    }
    if (Nday_ofTheWeek == 5) {
        DayOfTheWeek_IT = "Ven";
    }
    if (Nday_ofTheWeek == 6) {
        DayOfTheWeek_IT = "Sab";
    }
    
    $Date_formatted = DayOfTheWeek_IT + " " + $Date_formatted;
    
    
    if(showDateTime!==undefined && showDateTime===true){
    	let hours=($Date.getHours()<10?'0':'') 		+ $Date.getHours();
    	let minutes=($Date.getMinutes()<10?'0':'') 	+ $Date.getMinutes();
    	let seconds=($Date.getSeconds()<10?'0':'') 	+ $Date.getSeconds();
    	let time=hours+':'+minutes+':'+seconds;
    	$Date_formatted=$Date_formatted+' '+time;
    }

    return $Date_formatted;
}

function excelNumToCellDate(serial,showDateTime) {

	
    if (serial == null || serial === "") {
        return "";
    }
    
	   var utc_days  = Math.floor(serial - 25569);
	   var utc_value = utc_days * 86400;                                        
	   var date_info = new Date(utc_value * 1000);

	   var fractional_day = serial - Math.floor(serial) + 0.0000001;

	   var total_seconds = Math.floor(86400 * fractional_day);

	   var seconds = total_seconds % 60;

	   total_seconds -= seconds;

	   var hours = Math.floor(total_seconds / (60 * 60));
	   var minutes = Math.floor(total_seconds / 60) % 60;

	   var $Date_formatted=dateToCellString(new Date(date_info.getFullYear(), date_info.getMonth(), date_info.getDate(), hours, minutes, seconds),showDateTime)
	    
	   return $Date_formatted;
	   
	}


    function excelNumToCellDate_noWeekDay(serial){

        let dateString = excelNumToCellDate(serial,false)
        if(dateString){
            let small_year = '20'+dateString.substring(dateString.length - 2);
            
            return dateString.substring(4,10).replace(/-/gi, "/")+small_year;
        }else{
            return serial
        }

    }



function unformatDate(dt) {
    if (dt != "") {
        var myInt = Number(new Date(dt));
        myInt = (Math.trunc(myInt / 1000 / 86400) + 25569 + 1);
        return myInt;
    } else {
        return "";
    }
}

function invertFormatDate(dt) {
    if (dt != "") {
        var dd = dt[3] + dt[4];
        var mm = dt[0] + dt[1];
        var yyyy = dt[6] + dt[7] + dt[8] + dt[9];
        return dd + "/" + mm + "/" + yyyy;
    } else {
        return "";
    }
}

function getPartOfDate(dt, format, need) {
    var part_of_date = "";
    if (format == "dd-mm-yyyy") {
        if (need == "dd") {
            part_of_date = dt.substring(0, 2);
        }
        if (need == "mm") {
            part_of_date = dt.substring(3, 5);
        }
        if (need == "yyyy") {
            part_of_date = dt.substring(6, 10);
        }
    }
	
    if (format == "dd/mm/yyyy") {
        if (need == "dd") {
            part_of_date = dt.substring(0, 2);
        }
        if (need == "mm") {
            part_of_date = dt.substring(3, 5);
        }
        if (need == "yyyy") {
            part_of_date = dt.substring(6, 10);
        }
    }
	
    return part_of_date;
}

function axc(c) {
    console.log(c);
}

function axa(a) {
    alert(a);
}

function isCharNumeric(charStr) {
    return "1234567890,.".indexOf(charStr) >= 0;
    return !!/\d/.test(charStr);
}

function isCharIntegrer(charStr) {
    // return "1234567890".indexOf(charStr) >= 0;
    // return !!/\d/.test(charStr);
    isCharInteger(charStr, false);
}
function isCharIntegrer(charStr, allowNegative) {
    if (allowNegative !=undefined && allowNegative == true){
        return "1234567890-".indexOf(charStr) >= 0;
    } else {
        return "1234567890".indexOf(charStr) >= 0;
    }
    return !!/\d/.test(charStr);
}

function isCharSpaceBar(charStr) {
    if (charStr.charCodeAt(0) == 32) {
        return true;
    } else {
        return false;
    }
}

function isCharDelete(charStr) {
    if (charStr == 46) {
        return true;
    } else {
        return false;
    }
}

function isKeyPressedNumeric(event) {
    var charCode = getCharCodeFromEvent(event);
    var charStr = String.fromCharCode(charCode);
    return isCharNumeric(charStr);
}

function isKeyPressedInteger(event) {
    return isKeyPressedInteger(event, false);
}
function isKeyPressedInteger(event, allowNegative) {
    var charCode = getCharCodeFromEvent(event);
    var charStr = String.fromCharCode(charCode);
    return isCharIntegrer(charStr,allowNegative);
}

function getCharCodeFromEvent(event) {
    event = event || window.event;
    return (typeof event.which == "undefined") ? event.keyCode : event.which;
}

function formatCustomDate(value) {
    //console.log('[formatCustomDate] - input', d);
    var date = new Date(value);
    const dd = date.getDate();
    const mm = date.getMonth() + 1;
    const yyyy = date.getFullYear();
	var result = dd + '/' + mm + '/' + yyyy;
	console.log('[formatCustomDate] - date string', result);
    return result;
}


function formatDateDBPromo(date) {
	
	
    if (date == null || date === "") {
        return "";
    }
	
	//invert the month and day
	date=date.substring(3, 5)+"/"+date.substring(0, 2)+"/"+date.substring(6, 10);
	
    var $Date = new Date(date);
	
    var day = ("0" + $Date.getDate()).slice(-2);
	
    var month = ("0" + ($Date.getMonth() + 1)).slice(-2);
    var year = ("" + $Date.getFullYear()).slice(-2);
    var $Date_formatted = day + "-" + month + "-" + year;
    var Nday_ofTheWeek = $Date.getDay();
    var DayOfTheWeek_IT = "";
    if (Nday_ofTheWeek == 0) {
        DayOfTheWeek_IT = "Dom";
    }
    if (Nday_ofTheWeek == 1) {
        DayOfTheWeek_IT = "Lun";
    }
    if (Nday_ofTheWeek == 2) {
        DayOfTheWeek_IT = "Mar";
    }
    if (Nday_ofTheWeek == 3) {
        DayOfTheWeek_IT = "Mer";
    }
    if (Nday_ofTheWeek == 4) {
        DayOfTheWeek_IT = "Gio";
    }
    if (Nday_ofTheWeek == 5) {
        DayOfTheWeek_IT = "Ven";
    }
    if (Nday_ofTheWeek == 6) {
        DayOfTheWeek_IT = "Sab";
    }
    $Date_formatted = DayOfTheWeek_IT + " " + $Date_formatted;
    return $Date_formatted;
}

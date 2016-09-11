/*****************************************************************
****                     去除空格                     *****
*****************************************************************/
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
	
function ltrim(stringToTrim) {
	return stringToTrim.replace(/^\s+/,"");
}
		
function rtrim(stringToTrim) {
	return stringToTrim.replace(/\s+$/,"");
}

String.prototype.trim = function() {return this.replace(/^\s+|\s+$/g,"");}
String.prototype.ltrim = function() {return this.replace(/^\s+/,"");}
String.prototype.rtrim = function() {return this.replace(/\s+$/,"");}

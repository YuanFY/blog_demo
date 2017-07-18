(function(global,$) {
	global.StringUtils = {
		isNotNull:function(value){return value != null && value != undefined && value != ''}
	}; 
})(typeof window !== "undefined" ? window : this,jQuery);
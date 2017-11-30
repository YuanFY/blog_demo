(function(global,$) {
	global.StringUtils = {
		isNotNull : function (value) {
			return value != null && value != undefined && value != ''
		},
		isNull : function (value) {
			return value == null || value == undefined || value == '';
		}
	}; 
	global.su = global.StringUtils;
})(typeof window !== "undefined" ? window : this,jQuery);
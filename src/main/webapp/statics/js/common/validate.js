/**
 * 验证邮箱格式
 * @param event 邮箱对象
 * @param maxLen 限制最大长度
 * @param errorId 显示错误的id
 */
function validateEmail(event, maxLen, errorId, labelName){
	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/; 
	var value = $(event).val();
	labelName = labelName?labelName:"邮箱";
	if (value == "") {
		$("#"+errorId).html(labelName+"不能为空");
		return false;
	}
	if (!reg.test(value)) {
		$("#"+errorId).html("请输入有效的邮箱地址,格式如example@email.com");
		return false;
	}
	if (value.length > maxLen) {
		$("#"+errorId).html(labelName+"最大长度不能超过"+maxLen);
		return false;
	}
	$("#"+errorId).html("");
	return true;
}

/**
 * 验证标签内容
 * @param event 验证对象
 * @param maxLen 最大长度
 * @param labelName 输入框名称
 * @param errorId 显示错误的id
 * @param isPattern 是否需要正则验证
 * @param parttern_ 支持外部正则验证
 * @param errorMsg 支持外部正则验证后展示的错误消息
 */
function validateCommonInput(event, maxLen, labelName, errorId, isPattern, parttern_, errorMsg){
	var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	var value = $(event).val();
	if (value == "") {
		$("#"+errorId).html(labelName + "不能为空");
		return false;
	}
	if (maxLen != null && value.length > maxLen) {
		$("#"+errorId).html(labelName + "最大长度不能超过" + maxLen);
		return false;
	}
	if (isPattern && parttern_ != null) {
		if (!parttern_.test(value)) {
			$("#"+errorId).html(errorMsg);
			return false;
		}
	}else if (isPattern && !reg.test(value)) {
		$("#"+errorId).html("只能输入中文、英文字母、数字和英文下划线");
		return false;
	}
	$("#"+errorId).html("");
	return true;
}
/**
 * 设置input的class
 * @param e
 * @param validate
 */
function changeInputClass(e, validate){
    if(validate != 1){
        $(e).parent().addClass("has-error");
    }else{
        $(e).parent().removeClass("has-error");
    }
}
/**
 * 验证电话号码
 * @param event 事件对象
 * @param labelName 标签名
 * @param errorId 显示错误的id
 * @param isMobile 是否需要验证手机号，
 * @param isPhone 是否需要验证座机号
 * @param isEmpty 是否允许为空
 */
function validateTelephoneNumber(event, labelName, errorId, isMobile, isPhone, isEmpty) {
	var value = $(event).val();//不为空
    if((value == null || value == "") && !isEmpty){
        $("#" + errorId).html(labelName + "不能为空");
        return false;
    }
    if(isMobile && !validatePhone && !validateMobile(value)){
        $("#" + errorId).html("请填写正确格式的手机号码(如:18876868666)");
        return false;
    }
    if(!validateMobile && isPhone && !validatePhone(value)){
        $("#" + errorId).html("请填写正确格式的座机号码(如:400-6302001)");
        return false;
    }
    if(isMobile && isPhone && !validateMobile(value) && !validatePhone(value)){
        $("#" + errorId).html("请填写正确格式的手机号码或座机号码(如:400-6302001)");
        return false;
    }
    $("#" + errorId).html("");
    return true;
}
/**
 * @param 验证手机号
 * @returns {Boolean} 布尔值
 */
function validateMobile(val) { 
    var arr = new Array();
    arr = val.split(" ");
    if(arr.length != 1){
        return false;
    }
    var patrn = /^[0-9]*$/;
    if(val != "" && val.length != 0){
        if (patrn.exec(val)) {
            if(val.length != 11) {
                return false;
            }
        }else {
            return false;               
        }
    }
    if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(val))){
        return false;
    }
    return true; 
}
/**
 * @param 验证座机号
 * @returns {Boolean} 布尔值
 */
function validatePhone(val){
    var res1 = /^\d{3}-\d{7,8}$/
    var res2 = /^\d{4}-\d{7,8}$/
    if (!res1.test(val) && !res2.test(val)) {
        return false;
    } else {
        return true;
    }
}
/**
 * 验证标签内容
 * @param event 验证对象
 * @param maxLen 最大长度
 * @param labelName 输入框名称
 * @param errorId 显示错误的id
 * @param isPattern 是否需要正则验证
 * @param parttern_ 支持外部正则验证
 * @param errorMsg 支持外部正则验证后展示的错误消息
 */
function validateNumber(event, labelName, errorId, minValue, maxValue){
	var reg = /^\+?[1-9][0-9]*$/; 
	var value = $(event).val();
	if (value == "") {
		$("#"+errorId).html(labelName + "不能为空");
		return false;
	}
	if (!reg.test(value) || parseInt(value) > maxValue ||  parseInt(value) < minValue) {
		$("#"+errorId).html(labelName + "必须为正整数，范围："+minValue+"~" + maxValue);
		return false;
	}
	$("#"+errorId).html("");
	return true;
}

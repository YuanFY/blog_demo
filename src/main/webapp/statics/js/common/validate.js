/**
 * 验证邮箱格式
 * @param event 邮箱对象
 * @param maxLen 限制最大长度
 * @param errorId 显示错误的id
 */
function validateEmail(event, maxLen, errorId){
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-zA-Z])+/;
	var value = $(event).val();
	if (value == "") {
		$("#"+errorId).html("邮箱不能为空");
		return false;
	}
	if (!reg.test(value)) {
		$("#"+errorId).html("请输入有效的邮箱地址");
		return false;
	}
	if (value.length > maxLen) {
		$("#"+errorId).html("邮箱最大长度不能超过"+maxLen);
		return false;
	}
	$("#"+errorId).html("");
	return true;
}

/**
 * 验证标签内容
 * @param event 验证对象
 * @param maxLen 最大长度
 * @param inputName 输入框名称
 * @param errorId 显示错误的id
 * @param isPattern 是否需要正则验证
 */
function validateCommonInput(event, maxLen, inputName, errorId, isPattern){
	var reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	var value = $(event).val();
	if (value == "") {
		$("#"+errorId).html(inputName + "不能为空");
		return false;
	}
	if (value.length > maxLen) {
		$("#"+errorId).html(inputName + "最大长度不能超过" + maxLen);
		return false;
	}
	if (isPattern && !reg.test(value)) {
		$("#"+errorId).html("只能输入中文、英文字母、数字和英文下划线");
		return false;
	}
	$("#"+errorId).html("");
	return true;
}


/**
 * 根据id显示相对应url请求的内容
 * @param url  请求路径
 * @param params 查询参数
 * @param id 要显示内容的div编号
 * @author yuanfy 
 * @date
 */
var _loadPageFun;
function showContentById_common(url,params,id){  
    $('#'+id).html('');
    //防止重复点击提交ajax
	if(_loadPageFun){
		_loadPageFun.abort();
	} 
	_loadPageFun = $.ajax({
		type:'POST',
 		url:url,
 		data:params,
 		success:function(data){
 			$('#'+id).html(data);
 		}
 	 });
};
//**************************************分页**********************************************
//查询参数
var display=10;
var pageLimit=10;
//显示分页信息
function showPageInfo_common(pageId,dataTotal,pageNo){
	var pages=dataTotal%pageLimit==0?Math.floor(dataTotal/pageLimit):Math.floor(dataTotal/pageLimit)+1;
	if(pages==0){
		pages=1;
		pageNo=1;
	}
	$("#" + pageId).pagination(dataTotal, {
		current_page   : pageNo-1,
		items_per_page : pageLimit,
		num_display_entries : display,
		callback: function(page){
			//doquery_list(page+1);
		}
	});
}
//**************************************qq表情**********************************************
/**
 * 初始化qq表情组件
 * @param qqFaceObj 要显示QQ表情的div对象
 * @param textObj 接收QQ表情的文本域对象
 * @param leftObj 还剩多少个字的接收对象，可以为空
 */
function initQQFace_common(qqFaceObj, textObj, leftObj, maxLen){
	$.qqface({
        before : function(textarea, code){
            var value = textarea.val();
            var length = getLenth_common(value);
            return length < maxLen;
        }, //要在插入之前执行
        after: function(textarea, code){
            var value = textarea.val();
            var length = getLenth_common(value);
            if (leftObj != null) {
            	$('#left').html('还可以输入'+ (maxLen -length) + '个字符');
            }
            $(textObj).change();
        }, //在插入之后执行
        imgPath : 'statics/images/gif/',
        textarea : textObj,
        handle : qqFaceObj
    });
}
/**
 * 获取文本域中字符的长度
 * @param str 
 * @returns
 */
function getLenth_common(str) {
//    str = str.replace(/\[:([\s\S]+?)\]/g, 'F'); //把所有表情都变成F，一个字符
//    str = str.replace(/[\u4e00-\u9fa5]/g, 'CN'); //把所有汉字都变成CN，两个字符 
    return str.length;
}
/**
 * 替换QQ表情
 * @param str 还有QQ表情的字符串
 * @returns
 */
function replaceQQContent_common(str){
	str = str.replace(/\</g,'&lt;');
	str = str.replace(/\>/g,'&gt;');
	str = str.replace(/\n/g,'<br/>');
	str = str.replace(/\[\:([a-zA-Z0-9_\u4e00-\u9fa5]*)\]/g,'<img src="statics/images/gif/$1.gif" border="0" />');
	return str;
}

//**************************************异步提交函数**********************************************
/**
 * 异步请求公共函数
 * @param url      -- not null
 * @param params   -- default null
 * @param dataType -- default json
 * @param methodType   -- default get
 * @param isSync      是否同步  default true
 * @param successMsg  成功信息 default null
 * @param errorMsg    错误信息 default null
 * @returns
 */
function ajaxRequest_common(url, params, dataType, methodType, isSync, successMsg, errorMsg){
	$.ajax({
	    url : url,
	    data : params,
	    type : methodType == null ? "get" : methodType,
	    async : isSync == null ? true : isSync,
	    dataType: dataType == null ? "json" : dataType,
	    success : function(result){
	    	var msg = null;
	        if (result.error == 1) {
	        	msg = (successMsg != null ? successMsg : result.msg);
	        }else if (result.error == 0) {
	        	msg = (errorMsg != null ? errorMsg : result.msg);
	        } else {
	        	msg = result.msg
	        }
	        alert(msg);
	    },
	    error : {}
	}); 
}
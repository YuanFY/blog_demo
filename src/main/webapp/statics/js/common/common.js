
/**
 * 根据id显示相对应url请求的内容
 * @param url  请求路径
 * @param params 查询参数
 * @param id 要显示内容的div编号
 * @author yuanfy 
 * @date
 */
var _loadPageFun;
function showContentById(url,params,id){  
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
}

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
};

//查询参数
var display=10;
var pageLimit=10;
//显示分页信息
function showPageInfo(pageId,dataTotal,pageNo){
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

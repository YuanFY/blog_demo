<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 
   enctype 属性值：
       1、application/x-www-form-urlencoded 在发送前编码所有字符（默认）
       2、multipart/form-data 不对字符编码。 在使用包含文件上传控件的表单时，必须使用该值。
       3、text/plain  空格转换为 "+" 加号，但不对特殊字符编码。
-->
<div class="row">
    <form method="post" enctype="multipart/form-data" id="form1">
        <div><label>1、采用流的方式</label></div>
        <div class="col-sm-7" style="padding-left:0px">
            <div class="input-group">
                <input type="text" class="form-control" id="showFileInput1">
                <input type="file" style="display:none" name="txtFile" id="uploadFileInput1" accept="text/plain">
                <span class="input-group-addon" id="uploadFileButton1">
                    <span class="glyphicon glyphicon-folder-open"></span>
                    <label>浏览</label>
                </span>
            </div>
        </div>
        <div class="col-sm-5">
            <!-- 
                                    当form中存在button标签时，用ajax异步提交表单后，也面会被刷新。（感觉很诡异）
                                    原因：button 存在时会再次提交一下表单，所以页面被刷新了。（之前认为button type='submit' 时）button才有提交表单的功能。
            -->
            <a class="btn btn-default" id="submit1">上传</a>
        </div>
    </form>
</div>
<div class="row">
    <form method="post" enctype="multipart/form-data" id="form2">
        <div><label>2、采用springmvc的方式</label></div>
        <div class="col-sm-7" style="padding-left:0px">
            <div class="input-group">
                <input type="text" class="form-control" id="showFileInput2">
                <input type="file" style="display:none" name="txtFile" id="uploadFileInput2" accept="text/plain">
                <span class="input-group-addon" id="uploadFileButton2">
                    <span class="glyphicon glyphicon-folder-open"></span>
                    <label>浏览</label>
                </span>
            </div>
        </div>
        <div class="col-sm-5">
            <a class="btn btn-default" id="submit2">上传</a>
        </div>
    </form>
</div>

<div class="row">
    <textarea style="margin: 0px; height: 581px; width: 615px;" id="fileContent"></textarea>
</div>

<script type="text/javascript">
$(function (){
	$("#uploadFileButton1").click(function (){
		$("#uploadFileInput1").click();
	});
	$("#uploadFileInput1").change(function (){
		$("#showFileInput1").val($(this).val());
	});
	$("#submit1").click(function(){
		uploadFile("${pageContext.request.contextPath}/test/upload1.html", "form1");
	});
	$("#uploadFileButton2").click(function (){
        $("#uploadFileInput2").click();
    });
    $("#uploadFileInput2").change(function (){
        $("#showFileInput2").val($(this).val());
    });
	$("#submit2").click(function(){
        uploadFile("${pageContext.request.contextPath}/test/upload2.html", "form2");
    });
});
function setFile(fileId,value){
    if(value == null || value==''){
        $("#installBtn").attr("disabled", true);
    }else{
        $("#installBtn").attr("disabled", false);
    }
    if(value.lastIndexOf("\\") > 0) {
        $('#'+fileId).val(value.substring(value.lastIndexOf("\\")+1));
    }else{
        $('#'+fileId).val(value);
    }
}
function uploadFile(url, id) {
	var options  = {    
	    url: url,
        type:'post',    
        success:function(result)    
        {    
            $("#fileContent").html(result);
        },
        error:function(data){
        }
    };    
    var form = $("#"+id);  
    form.ajaxSubmit(options); 
}
</script>

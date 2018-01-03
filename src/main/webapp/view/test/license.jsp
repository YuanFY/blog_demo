<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row" style="height: 650px">
	<div><label>1、生成wci文件，<a style="cursor: pointer" id="modalBtn" class="text-info" data-toggle="modal" data-target="#licenseModal">请点击获取</a></label></div>
    
</div>
<div class="modal fade" id="licenseModal" tabindex="-1" role="dialog" style="display:none;margin: auto;top:0%;">  
    <div class='modal-dialog'>
        <div class='modal-content'>
	        <div class='modal-header'>
	            <a class="close" data-dismiss="modal" ><span aria-hidden="true">&times;</span></a><h4 class='modal-title pull-left'>订购License</h4>
	        </div>
            <div class='modal-body'>
	            <div class="form-group">
	                <label class="col-xs-3 control-label" for="">企业名称：</label>
	                <div class="col-xs-8">
	                    <div class="input-group">
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-flag"></span></span>
	                        <input id="orgName" type="text" class="form-control" placeholder="" data-name="企业名称" 
	                        data-msgTo="#orgNameError" data-toggle="valid" data-minLen="2" data-maxLen="50" >
	                    </div>
	                </div>
	                <div class="col-xs-1">
	                  <span class="text-danger inline m-l-n m-t-sm" >*</span>
	                </div>            
	            </div>
	            <div class="col-xs-offset-2"><span style="color:red; margin-left: 65px" id="orgNameError" class="error"></span></div>
	            <div class="form-group">
	                <label class="col-xs-3 control-label" for="">企业联系电话：</label>
	                <div class="col-xs-8">
	                    <div class="input-group">
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-phone-alt"></span></span>
	                        <input id="orgPhone" type="text" class="form-control" placeholder="" data-name="企业联系电话" 
	                        data-msgTo="#orgPhoneError" data-toggle="valid" data-minLen="2" data-maxLen="50" >
	                    </div>
	                </div>
	                <div class="col-xs-1">
	                  <span class="text-danger inline m-l-n m-t-sm">*</span>
	                </div>             
	            </div>
	            <div class="col-xs-offset-2"><span style="color:red; margin-left: 65px" id="orgPhoneError" class="error"></span></div>
               <!--  <div class="form-group">
	                <label class="col-xs-3 control-label" for="">客户姓名：</label>
	                <div class="col-xs-8">
	                    <div class="input-group">
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
	                        <input id="custName" type="text" class="form-control" placeholder="" data-name="客户姓名" 
	                        data-msgTo="#custNameError" data-toggle="valid" data-minLen="2" data-maxLen="50" >
	                    </div>
	                </div>
	                <div class="col-xs-1">
	                  <span class="text-danger inline m-l-n m-t-sm">*</span>
	                </div>                    
	            </div>
	            <div class="col-xs-offset-2"><span style="color:red; margin-left: 65px" id="custNameError" class="error"></span></div>
	            <div class="form-group">
	                <label class="col-xs-3 control-label" for="">客户邮箱：</label>
	                <div class="col-xs-8">
	                    <div class="input-group">
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
	                        <input id="email" type="mail" class="form-control" placeholder="" data-name="邮箱" 
	                        data-msgTo="#emailError" data-toggle="valid" data-minLen="2" data-maxLen="50" >
	                    </div>
	                </div>
	                <div class="col-xs-1">
	                  <span class="text-danger inline m-l-n m-t-sm">*</span>
	                </div>                    
	            </div>
	            <div class="col-xs-offset-2"><span style="color:red; margin-left: 65px" id="emailError" class="error"></span></div>
	            <div class="form-group">
	                <label class="col-xs-3 control-label" for="">客户电话：</label>
	                <div class="col-xs-8">
	                    <div class="input-group">
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
	                        <input id="phone" type="text" class="form-control" placeholder="" data-name="联系电话" 
	                        data-msgTo="#phoneError" data-toggle="valid" data-minLen="2" data-maxLen="50" >
	                    </div>
	                </div>
	                <div class="col-xs-1">
	                  <span class="text-danger inline m-l-n m-t-sm">*</span>
	                </div>                    
	            </div>
	            <div class="col-xs-offset-2"><span style="color:red; margin-left: 65px" id="phoneError" class="error"></span></div> -->
	            <div class="form-group">
	                <label class="col-xs-3 control-label" for="">版本：</label>
	                <div class="col-xs-8">
	                  <select id="version" class="form-control m-b">
	                    <option value="enterprise">企业版</option>
	                    <option value="diamond" selected="selected">企业增强版</option>
	                  </select> 
	                </div>
	                <div class="col-xs-1">
	                    <span class="text-danger inline m-l-n m-t-sm">*</span>
	                </div>                  
	            </div>
	            <div class="col-xs-offset-2"><span style="color:red; margin-left: 65px" class="error"></span></div>
                <div class="form-group numberDiv"  style="display: black">
	                <label class="col-xs-3 control-label" for="">个数：</label>
	                <div class="col-xs-8">
	                    <div class="input-group">
	                        <input type="text" class="form-control" placeholder="" name="number" id="number">
	                        <span class="input-group-addon">个</span>
	                    </div>
	                </div>
	                <div class="col-xs-1">
	                  <span class="text-danger inline m-l-n m-t-sm">*</span>
	                </div>
                </div>
                <div class="col-xs-offset-2"><span style="color:red; margin-left: 65px" id="numberError" class="error"></span></div>
            </div>
            <div class='modal-footer'>
                <button type="button" class="btn btn-info" id="downloadBtn">导出WCI文件</button>
                <a class="btn btn-default" data-dismiss="modal" >取消</a>
            </div>
        </div>
    </div>
</div>
<form id="downLoadForm"></form>
<script type="text/javascript">
$(function(){
	//打开摸态框之前，清空模态框中的内容
    $("#modalBtn").click(function(){
        $("input[type=text]").val("");
        $("input[type=mail]").val("");
        $(".error").html("");
        $.each($(".input-group"), function(index, element){
            $(element).removeClass('has-error');
        });
        $("#downloadBtn").attr("disabled", false);
    });
    // 1、企业名称判断
    $("#orgName").bind("input propertychange focus",function(){
    	changeInputClass(this, validateCommonInput(this, 20, "企业名称", $(this).attr("id")+"Error", true));
    });
    // 2、企业联系电话、客户电话判断
    $("#orgPhone, #phone").bind("input propertychange focus",function(){
    	changeInputClass(this, validateTelephoneNumber(this, $(this).attr("id")=="phone"?"客户电话":"企业联系电话", $(this).attr("id")+"Error", true, true, false));
    });
    // 3、判断客户姓名
    $("#custName").bind("input propertychange focus",function(){
        changeInputClass(this, validateCommonInput(this, 15, "客户姓名", $(this).attr("id")+"Error", true));
    });
    //4 判断客户邮箱
    $("#email").bind("input propertychange focus",function(){
        changeInputClass(this, validateEmail(this, 30, $(this).attr("id")+"Error", "客户邮箱"));
    });
    //5 判断个数
    $("#number").bind("input propertychange focus",function(){
    	 changeInputClass(this, validateNumber(this, "个数", $(this).attr("id")+"Error", 1, 99999));
    });
    
    //模态框  验证功能
    $("#downloadBtn").click(function(){
    	var orgNameFlag = validateCommonInput($("#orgName"), 20, "企业名称", $("#orgName").attr("id")+"Error", true)
    	changeInputClass($("#orgName"), orgNameFlag);
    	var orgPhoneFlag = validateTelephoneNumber($("#orgPhone"), "企业联系电话", $("#orgPhone").attr("id")+"Error", true, true, false)
    	changeInputClass($("#orgPhone"), orgPhoneFlag);
    	var numberFlag = validateNumber($("#number"), "个数", $("#number").attr("id")+"Error", 1, 99999)
    	changeInputClass($("#number"), numberFlag);
        if(orgNameFlag && orgPhoneFlag && numberFlag){
            sumitDownLoadForm();
        }
    });
    
    function sumitDownLoadForm(){
        $("#downloadBtn").attr("disabled", true);
        $('#downLoadForm').attr('method','post');
        $('#downLoadForm').attr('action','${pageContext.request.contextPath}/test/license/downloadWciFile.html');
        $('#downLoadForm').html('');
        $('#downLoadForm').html($('#downLoadForm').html()+'<input type="hidden" name="orgName" value="'+$.trim($('#orgName').val())+'" />');
        $('#downLoadForm').html($('#downLoadForm').html()+'<input type="hidden" name="orgPhone" value="'+$.trim($('#orgPhone').val())+'" />');
        $('#downLoadForm').html($('#downLoadForm').html()+'<input type="hidden" name="version" value="'+$.trim($('#version').val())+'" />');
        $('#downLoadForm').html($('#downLoadForm').html()+"<input type='hidden' name='number' value='"+ $.trim($('#number').val()) +"' />");
        //$('#downLoadForm').submit();
        var options  = {    
            url: '${pageContext.request.contextPath}/test/license/checkDownloadWciFile.html',
            type:'post',    
            success:function(result)    
            {    
                var type = typeof result;
                if(type == 'string' ){
                    result = JSON.parse(result)
                }else{
                    result = eval(result);
                }
                if(result.error == 1){
                    $('#downLoadForm').submit();
                    setTimeout(function(){
                        $("#downloadBtn").attr("disabled", false);
                        $('.modal-header .close').click();
                        jc.alert("系统提示","文件获取成功",'','');
                    }, 500);
                }else{
                    jf.error(result.msg, 3000);
                    $("#downloadBtn").attr("disabled", false);
                }
            }
        };    
        var form = $("#downLoadForm");  
        form.ajaxSubmit(options);  
    }
});
</script>

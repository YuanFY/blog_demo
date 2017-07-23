<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="renderer" content="webkit">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>公共聊天室</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/bootstrap/3.3.7/bootstrap.min.css?"+Math.random()/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/common.css?"+Math.random() />

<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/bootstrap/3.3.7/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/common/common.js?"+Math.random()></script>
<style type="text/css">
	.modal-dialog{
		margin-top: 2%;
	}
	#chatDiv{
		width: 100%;
		height: 350px;
		overflow:auto;
		border: 1px solid #C0C0C0;
	}
</style>
</head>

<body>
	<!-- 模态框（Modal） -->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row text-center">
					<label style="font-size: 24px">公共聊天室</label>
				</div>
				
				<div id="chatDiv">
					
				</div>
				<div class="row">
					<div class="col-sm-2">
					    <input type="hidden" name="userId" value="${user.userId}">
						<input type="text" name="userName" value="${user.userName }" class="form-control" placeholder="用户名" readonly="readonly">
					</div>
					<div class="col-sm-8">
						<input type="text" name="message" class="form-control" placeholder="请输入内容" data-toggle="tooltip" data-placement="top" title="发送内容不能为空">
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-primary" id="sendBtn_chat">发送</button>
					</div>
				</div>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
	
	<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" style="display:none;" data-backdrop="static" data-keyboard="false">  
        <div class='modal-dialog' role='document'>
            <div class='modal-content' style="margin:100px auto">
                <div class='modal-header'>
                     <a class="close" data-dismiss="modal" style="display:none"><span aria-hidden="true">&times;</span></a><h4 class='modal-title pull-left'>用户信息</h4>
                </div>
                <div class='modal-body' style="max-height: 600px;">
                    <div class="form-group">
                        <label class="col-xs-2 control-label" style="font-weight: normal;">用户名：</label>
                        <div class="col-xs-9">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-flag"></span></span>
                                <input id="userName" type="text" class="form-control" placeholder="" data-name="用户名" 
                                data-msgTo="#userNameError" data-toggle="valid" data-minLen="2" data-maxLen="20">
                            </div>
                        </div>
                        <div class="col-xs-1">
                          <span class="text-danger inline m-l-n m-t-sm" >*</span>
                        </div>            
                        <div class="col-xs-offset-3"><span style="color:red; margin-left: 40px" id="userNameError" class="error"></span></div>        
                    </div>
                </div>
                <div class='modal-footer'>
                    <button type="button" class="btn btn-info" id="saveBtn">确定</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
var userNameValidate = 0;
$(function (){
	$("input[name=message]").tooltip();
	var userName = $("input[name=userName]").val();
	if (!StringUtils.isNotNull(userName)) {
		$('#userModal').modal('show');
	} 
    $("#userName").bind("input propertychange focus",function(){
        validateUserName(this);
    });
    $("#saveBtn").click(function(){
    	if (userNameValidate == 1){
    		sendMessageToServer();
    	}
    });
    var sendBtnFlag = false;
    $("input[name=message]").bind("input propertychange focus",function(){
    	var message = $(this).val();
        if (message == null || $.trim(message) == '') {
            $(this).addClass("has-error");
        } else{
        	$(this).removeClass("has-error");
        	sendBtnFlag = true;
        }
    });
    $("#sendBtn_chat").click(function(){
    	if (!sendBtnFlag) {
	    	var message = $("input[name=message]").val();
	    	if (message == null || $.trim(message) == '') {
	    		$("input[name=message]").addClass("has-error");
	    		$("input[name=message]").focus();
	    		return;
	    	}
    	}
    	$("input[name=message]").removeClass("has-error");
    	sendMessageToServer();
    });
});
/**
 * 发送消息给服务器
 */
function sendMessageToServer() {
	$.ajax({
        url:'${pageContext.request.contextPath}/chat/startClient.html',
        dataType: 'json',
        data:{userId:$("input[name=userId]").val(),userName:$("#userName").val(), message:$("input[name=message]").val()},
        success: function(result){
            var state = result.error;
            if(state == 1){
                $('.modal-header .close').click();
                $("input[name=userId]").val(result.data.userId);
                $("input[name=userName]").val($("#userName").val());
                $("input[name=message]").val("");
            }
        }
    });
}



function changeInputDivClass(e, validate){
    if(validate != 1){
        $(e).parent().addClass("has-error");
    }else{
        $(e).parent().removeClass("has-error");
    }
}
function validateUserName(e){
    var userName = $(e).val();//不为空
    var pattern = /^[A-Za-z0-9-_\u4e00-\u9fa5]+$/; 
    if(userName == null || userName == ""){
        $("#userNameError").text("不能为空");
        userNameValidate = -1;
    }else if(!pattern.test(userName)){
        $("#userNameError").text("只能输入中文、英文字母、数字、英文下划线、英文中划线");
        userNameValidate = 0;
    }else{
        if(userName.length > 15){
            $("#userNameError").text("长度必须小于等于15");
            userNameValidate = 0;
        }else{
            $("#userNameError").text("");
            userNameValidate = 1;
        }
    }
    changeInputDivClass(e, userNameValidate);
}

</script>
</html>

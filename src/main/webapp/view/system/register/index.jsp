<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="renderer" content="webkit">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>注册-Demo</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/bootstrap/3.3.7/bootstrap.min.css?"+Math.random()/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/common.css?"+Math.random() />

<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/bootstrap/3.3.7/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/common/common.js?"+Math.random()></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/common/validate.js?"+Math.random()></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/common/gVerify.js?"+Math.random()></script>
<style type="text/css">
	.modal-dialog{
		width: 680px;
		margin-top: 8%;
	}
</style>
</head>

<body>
	<!-- 模态框（Modal） -->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-7">
						<ul class="nav nav-tabs">
			                <li class="active"><a href="#emailRegister" data-toggle="tab">邮箱注册</a></li>
			                <li><a href="#phoneRegister" data-toggle="tab">手机注册</a></li>
			            </ul>
			            <div id="emailRegister">
			            	<form id="emailRegisterForm">
							<div class="input-group  margin-top20">
					            <span class="input-group-addon">邮箱</span>
					            <input type="text" class="form-control" placeholder="请输入邮箱" id="email">
					        </div>
					        <div id="emailError" style="color:red; padding-left: 53px; "></div>
					        <div class="input-group  margin-top20">
					            <span class="input-group-addon">昵称</span>
					            <input type="text" class="form-control" placeholder="请输入昵称，至多20个" id="userName">
					        </div>
					        <div id="userNameError" style="color:red; padding-left: 53px; "></div>
					        <div class="input-group  margin-top20">
					            <span class="input-group-addon">密码</span>
					            <input type="password" class="form-control" placeholder="请输入密码，字母或特殊符号和数字结合" id="password">
					        </div>
					        <div id="passwordError" style="color:red; padding-left: 53px; "></div>
					        <div class="row" style="margin: 0px">
						        <div class="col-sm-5" style="padding-left: 0px;padding-right: 0px">
						        	<div class="input-group  margin-top20">
							            <span class="input-group-addon">验证码</span>
							            <input type="text" class="form-control" placeholder="验证码" id="verifyCode" maxlength="4">
							        </div>
						        </div>
						        <div class="col-sm-7" style="padding-top: 20px">
						        	<div id="verifyCodeImg"></div>
						        </div>
					        </div>
					        <div id="verifyCodeError" style="color:red; padding-left: 67px; "></div>
					        <div class="from-group" style="margin-top: 20px; width: 100%">
					            <button type="button" class="btn btn-primary btn-lg" style="width: 100%" id="registerBtn">注册</button>
					        </div>
					        </form>
						</div>
					</div>
					<div class="col-sm-4" style="margin-left: 50px; font-size: 15px">
						<div class="from-group margin-top20">
							<label>已经注册过?</label>
							<br>
							<a href="${pageContext.request.contextPath }/login.html"> 立即登录 → </a>
						</div>
						<div class="from-group" style="margin-top: 20px;">
							<label>使用以下账号直接登录</label>
							<br>
							<img class="img-circle" width="35" height="35" src="${pageContext.request.contextPath }/statics/images/common/qq.png">
							<img class="img-circle img-left" width="35" height="35" src="${pageContext.request.contextPath }/statics/images/common/wx.png">
							<img class="img-circle img-left" width="35" height="35" src="${pageContext.request.contextPath }/statics/images/common/wb.png">
						</div>
					</div>
				</div>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
	<script type="text/javascript">
		$(function (){
			var verifyCode = new GVerify("verifyCodeImg");

			$("#email").on("input propertychange focus", function(){
				validateEmail(this, 50, $(this).attr("id")+"Error");
			});
			$("#userName").on("input propertychange focus", function(){
				validateCommonInput(this, 50, "昵称", $(this).attr("id")+"Error", true);
			});
			$("#password").on("input propertychange focus", function(){
				validateCommonInput(this, 50, "密码", $(this).attr("id")+"Error", false);
			});
			$("#registerBtn").click(function(){
				var emailValidate = validateEmail($("#email"), 50, $("#email").attr("id")+"Error");
				var userNameValidate = validateCommonInput($("#userName"), 20, "昵称", $("#userName").attr("id")+"Error", true);
				var passwordValidate = validateCommonInput($("#password"), 20, "密码", $("#password").attr("id")+"Error", false);
				var verifyCodeValidate = verifyCode.validate($("#verifyCode").val());
				if ($("#verifyCode").val() == '') {
					$("#verifyCodeError").html("验证码不能为空");
					verifyCodeValidate = false;
				} else if (!verifyCodeValidate) {
					$("#verifyCodeError").html("验证码错误");
				}
				if (emailValidate && userNameValidate && passwordValidate && verifyCodeValidate) {
					var options  = {    
						    url: "${pageContext.request.contextPath}/",
					        type:'post',    
					        success:function(result)    
					        {    
					            
					        },
					        error:function(data){
					        }
					    };    
					    var form = $("#"+id);  
					    form.ajaxSubmit(options); 
				}
			});
		});
	</script>
</body>
</html>

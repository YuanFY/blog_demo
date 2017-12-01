<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="renderer" content="webkit">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>登录-Demo</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/bootstrap/3.3.7/bootstrap.min.css?"+Math.random()/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/common.css?"+Math.random() />
<jsp:include page="../../common/js.jsp"></jsp:include>
<style type="text/css">
	.modal-dialog{
		width: 680px;
		margin-top: 10%;
	}
</style>
</head>

<body>
	<!-- 模态框（Modal） -->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row text-center">
					<label style="font-size: 24px">登录</label>
				</div>
				<div class="row">
					<div class="col-sm-7">
						<div>
							<div class="input-group input-group-lg margin-top20">
					            <span class="input-group-addon">账号</span>
					            <input type="text" name="name" class="form-control" placeholder="请输入手机号/邮箱">
					        </div>
					        <span style="color:red" id="nameError">&nbsp;</span>
				        </div>
				        <div>
							<div class="input-group input-group-lg">
					            <span class="input-group-addon">密码</span>
					            <input type="password" name="password" class="form-control" placeholder="请输入密码">
					        </div>	
					        <span style="color:red" id="passwordError">&nbsp;</span>
				        </div>
				        <div class="from-group">
				        	<div class="col-sm-5">
				        		<input type="checkbox">
				        		<label class="label-lg">记住密码</label>
				        	</div>
				            <div class="col-sm-4 col-md-offset-3 padding-right-zero text-right" >
				        		<label class="label-lg"><a>忘记密码？</a></label>
				        	</div>
				        </div>
				        <div class="from-group" style="margin-top: 45px; width: 100%">
				            <button type="button" class="btn btn-primary btn-lg" style="width: 100%" id="loginBtn">登录</button>
				        </div>
					</div>
					<div class="col-sm-4" style="margin-left: 50px; font-size: 15px">
						<div class="from-group margin-top20">
							<label>没有账号?</label>
							<br>
							<a href="${pageContext.request.contextPath }/system/register/index.html"> 立即注册 → </a>
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
			$("input[name=name]").on("input propertychange", function(){
				if (validateCommonInput(this, null, "账号", $(this).attr("name")+"Error", false)){
					$("#"+$(this).attr("name")+"Error").html("&nbsp;");
				}
			});
			$("input[name=password]").on("input propertychange", function(){
				if (validateCommonInput(this, null, "密码", $(this).attr("name")+"Error", false)){
					$("#"+$(this).attr("name")+"Error").html("&nbsp;");
				}
			});
			$("#loginBtn").click(function(){
				var node = $("input[name=name]");
				if (validateCommonInput(node, null, "账号", $(node).attr("name")+"Error", false)){
					$("#"+$(node).attr("name")+"Error").html("&nbsp;");
				} else {
					$(node).focus();
					return;
				}
				node = $("input[name=password]");
				if (validateCommonInput(node, null, "密码", $(node).attr("name")+"Error", false)){
					$("#"+$(node).attr("name")+"Error").html("&nbsp;");
				} else {
					$(node).focus();
					return;
				}
				$.ajax({
					url: "${pageContext.request.contextPath}/system/login/login.html",
					type:'post',
					data:{name: $("input[name=name]").val(), password:$("input[name=password]").val()},
					success: function(result){
						if (result.error == 1) {
							window.location.href="${pageContext.request.contextPath}/index.html";
						} else{
							$("#passwordError").html(result.msg);
						}
					}
				});
			});
		});
	</script>
</body>
</html>

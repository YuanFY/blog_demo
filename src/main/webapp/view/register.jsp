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
				<div class="row">
					<div class="col-sm-7">
						<ul class="nav nav-tabs">
			                <li class="active"><a href="#emailRegister" data-toggle="tab">邮箱注册</a></li>
			                <li><a href="#phoneRegister" data-toggle="tab">手机注册</a></li>
			            </ul>
			            <div id="emailRegister">
							<div class="input-group  margin-top20">
					            <span class="input-group-addon">邮箱</span>
					            <input type="text" class="form-control" placeholder="请输入邮箱">
					        </div>
					        <div class="input-group  margin-top20">
					            <span class="input-group-addon">昵称</span>
					            <input type="text" class="form-control" placeholder="请输入昵称，至少3个字符，至多20个">
					        </div>	
					        <div class="input-group  margin-top20">
					            <span class="input-group-addon">密码</span>
					            <input type="text" class="form-control" placeholder="请输入密码，字母或特殊符号和数字结合">
					        </div>
					        <div class="input-group  margin-top20">
					            <span class="input-group-addon">验证码</span>
					            <input type="text" class="form-control" placeholder="请输入验证码">
					        </div>
					        <div class="from-group" style="margin-top: 20px; width: 100%">
					            <button type="button" class="btn btn-primary btn-lg" style="width: 100%">注册</button>
					        </div>
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
</body>
</html>

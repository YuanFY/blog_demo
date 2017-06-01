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
						<input type="text" class="form-control" placeholder="用户名">
					</div>
					<div class="col-sm-8">
						<input type="text" class="form-control" placeholder="请输入内容">
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-primary">发送</button>
					</div>
				</div>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</body>
</html>

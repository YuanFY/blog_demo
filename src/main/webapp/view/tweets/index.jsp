<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
<!--
.qqFace { margin-top: 4px; background: #fff; padding: 2px; border: 1px #dfe6f6 solid; }
.qqFace table td { padding: 0px; }
.qqFace table td img { cursor: pointer; border: 1px #fff solid; }
.qqFace table td img:hover { border: 1px #0066cc solid; }
-->
</style>
<!-- 引入qq表情组件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery.qqFace.js"></script>
<!-- 引入图片上传组件 -->
<!-- 引用控制层插件样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/upload/zyUpload.css" type="text/css">
<!-- <script type="text/javascript" src="http://www.lanrenzhijia.com/ajaxjs/jquery.min.js"></script> -->
<!-- 引用核心层插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/upload/core/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/upload/zyUpload.js"></script>
<!-- 引用初始化JS -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/upload/core/jq22.js"></script>

<div class="container">
    <div class="row" id="blog-container">
        <div class="col-sm-8">
            <div class="input-group">
			    <textarea id="saytext" name="saytext" class="form-control custom-control" rows="2" placeholder="今天你动弹了吗？" style="resize:none"></textarea>     
				<span class="input-group-addon btn btn-primary">动弹</span>
		    </div>
		    <div>
                <i class="emotion" title="插入表情"></i>
                <i class="insertImg" title="插入图片" id="insertImg_i"></i>
                <div id="insertImg" style="display:none"></div>
		    </div>
		    <div class="clearFloat"></div>
            <div id="tweetsContent" >
               	<div class="row">
               		<h4>大家在动弹什么？</h4>
               	</div>
                <c:forEach begin="1" end="9">
                <div class="row">
	                <div class="panel panel-default">
						<div class="panel-body">
							<div class="col-sm-2 header">
		                        <a  href="#"  title="苗哥">
		                            <img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
		                        </a>
		                    </div>
		                    <div class="col-sm-10 content">
		                        <div class="box vertical">
                					<a class="ti-uname" href="https://my.oschina.net/hardbone" title="局长" target="_blank">局长&nbsp;&nbsp;</a>
                					<span class="dark-box" >12分钟前</span>
                            	</div>
		                        <section class="blog-brief text-gradient">在html文件中，使用svg标记可以绘制图形。这个例子中主要用到path，linearGradient两个技术</section>
		                        <footer class="dark-box">
		                            <span class="glyphicon glyphicon-thumbs-up span-icon" ><value>0</value></span>
		                            <span class="glyphicon glyphicon-comment span-icon" ><value>0</value></span>
		                            <span >查看详情</span>
		                        </footer>
		                    </div>
						</div>
					</div>
                </div>
                </c:forEach>
                <div class="panel-footer" id="divPagingMonitor" style="display: none"></div>
                <div class="panel-footer"><div id="divPaging_new" class="pagination"></div></div>
            </div>
        </div>
        <div class="col-sm-4" id="">
            <ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="#">热门动弹</a></li>
			</ul>
			<div class="row">
				<c:forEach begin="1" end="9">
                <div class="row">
	                <div class="panel panel-default">
						<div class="panel-body">
							<div class="col-sm-2 header-sm">
		                        <a  href="#"  title="苗哥" style="float:left">
		                            <img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
		                        </a>
		                    </div>
		                    <div class="col-sm-10 content-sm">
		                        <div class="box vertical">
                					<a class="ti-uname" href="https://my.oschina.net/hardbone" title="局长" target="_blank">局长&nbsp;&nbsp;</a>
                					<span class="dark-box" >12分钟前</span>
                            	</div>
		                        <section class="blog-brief text-gradient">在html文件中，使用svg标记可以绘制图形。这个例子中主要用到path，linearGradient两个技术</section>
		                        <footer class="dark-box">
		                            <span class="glyphicon glyphicon-thumbs-up span-icon" ><value>0</value></span>
		                            <span class="glyphicon glyphicon-comment span-icon" ><value>0</value></span>
		                            <span >查看详情</span>
		                        </footer>
		                    </div>
						</div>
					</div>
                </div>
                </c:forEach>
			</div>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $("#footer").show();
        $('.emotion').qqFace({
            id : 'facebox', 
            assign:'saytext', 
            path:'${pageContext.request.contextPath }/statics/images/arclist/' //表情存放的路径
        });//$("#show").html(replace_em(str));
        
        $(document).click(function(e){
        	e = e || window.event;  
            var dom =  e.srcElement|| e.target;  
        	var nodeId = dom.id;
        	if (nodeId != 'insertImg_i') {
        		$("#insertImg").css("display", "none");
        		$("#insertImg").html("");
        	}
        });
        
        $(".insertImg").click(function (){
        	$("#insertImg").css("display", "");
        	initUpload("insertImg");
        });
        
        
        //showPageInfo("divPaging_new", 20, 1);
    });
    </script>
</div>
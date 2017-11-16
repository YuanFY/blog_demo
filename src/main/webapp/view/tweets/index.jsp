<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 引入qq表情组件 -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery.min.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery.qqFace.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery.qqface.min.js"></script>
<!-- 引入图片上传组件 -->
<!-- 引用控制层插件样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/upload/zyUpload.css" type="text/css">
<!-- <script type="text/javascript" src="http://www.lanrenzhijia.com/ajaxjs/jquery.min.js"></script> -->
<!-- 引用核心层插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/upload/core/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/upload/zyUpload.js"></script>
<!-- 引用初始化JS -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/upload/core/initUpload.js?"+Math.random()></script>
<div class="container">
    <div class="row" id="blog-container">
        <div class="col-sm-8">
            <div class="input-group">
			    <textarea id="tweetsContent" name="tweetsContent" class="form-control custom-control" rows="2" placeholder="今天你动弹了吗？" style="resize:none"></textarea>     
				<span class="input-group-addon btn btn-primary" id="sendTweets">动弹</span>
		    </div>
		    <div>
                <i class="emotion" title="插入表情" id="qqFace"></i>
                <i class="insertImg" title="插入图片" id="insertImg_i"></i>
                <span id="left" style="top: 5px;position: relative;color: #BEBEBE"></span>
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
        var oldLength = '';
        //文本框文字的限制
        $('#tweetsContent').on('change keydown keyup input', function(event) {
	        var textarea = $(this);
	        var value = $(this).val();
	        var length = getLenth_common(value);
	        if (length > 200) {
	            textarea.val(oldLength);
	        } else {
	            oldLength = value;
	            $('#left').html('还可以输入'+ (200 -length) + '个字符');
	        }
	    });
        
        //加载qq组件
        initQQFace_common($('#qqFace'), $('#tweetsContent'), $('#left'));
        
        //加载上传组件
        $(document).mousedown(function(e){ 
        	e = e || window.event;  
            var dom =  e.srcElement|| e.target;  
        	var nodeId = dom.id;
        	var htmlContent = $("#insertImg").html()+"";
        	
        	if ((nodeId != 'insertImg_i' && htmlContent.indexOf($(dom).html()) < 0) || $(dom).html() == "") {
        		$("#insertImg").css("display", "none");
        		$("#insertImg").html("");
        	}
        });
        
        $(".insertImg").click(function (){
        	$("#insertImg").css("display", "");
        	var htmlContent = $("#insertImg").html()+"";
        	if (htmlContent == '') {
        		initUpload("insertImg", "${pageContext.request.contextPath}/tweets/uploadImg.html","image/gif,image/jpeg,image/png");
        	}
        });
        
        //发送动弹
        $("#sendTweets").click(function (){
        	//alert(1);
        	$("#left").html(replaceQQContent_common($("#tweetsContent").val()));
        	/* $.ajax({
                url:'${pageContext.request.contextPath}/tweets/save.html',
                data:{tweetsContent:$("#tweetsContent").val()},
                dataType: 'json',
                success: function(result){
                    
                }
            }); */
        });
        showPageInfo_common("divPaging_new", 20, 1);
    });
    </script>
</div>
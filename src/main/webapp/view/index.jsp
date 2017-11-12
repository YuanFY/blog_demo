<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="renderer" content="webkit">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>Demo</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/bootstrap/3.3.7/bootstrap.min.css?"+Math.random()/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/common.css?"+Math.random() />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/jquery/jquery.pagination.css?"+Math.random() />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/statics/css/jquery/reset.css?"+Math.random() />

<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery/jquery.pagination.js?"+Math.random()></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/bootstrap/3.3.7/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/common/common.js?"+Math.random()></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/common/stringUtil.js?"+Math.random()></script>
    
</head>
<body id="body">
    <div class="header">
        <!-- 导航条 -->
        <div class="navbar navbar-fixed-top navbar-default" role="navigation" id="header-nav">
            <!-- container 左右（15px）居中显示  -->
            <div class="container" id="header-container">
                <div class="navbar-header">
                    <!-- 设置响应式导航栏按钮 -->
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#main-navbar-collapse">
                        <span class="sr-only">切换导航</span>
                        <span class="icon-bar"></span><!-- ‘汉堡按钮’ -->
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#index" id="homeTitle">Project Name</a>
                </div>
                <!-- 可折叠响应式导航栏 -->
                <div class="collapse navbar-collapse" id="main-navbar-collapse">
                    <ul class="nav navbar-nav" id="main-header-nav">
                        <li class="active"><a href="#index" data-toggle="tab">主页</span></a></li>
                        <li><a href="#blog" data-toggle="tab" >博客</a></li>
                        <li><a href="#tweets" data-toggle="tab">动弹</a></li>
                        <li><a href="#sponsor" data-toggle="tab">赞助</a></li>
                        <li><a href="#chat" data-toggle="tab">公共聊天室</a></li>
                        <li><a href="#test" data-toggle="tab">功能测试</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" id="header-right" <c:if test="${not empty user }">style="display:none"</c:if>> 
                        <li><a href="${pageContext.request.contextPath }/system/register/index.html"><span class="glyphicon glyphicon-user"></span> 注册</a></li> 
                        <li><a href="${pageContext.request.contextPath }/system/login/index.html"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li> 
                    </ul>
                    <ul class="nav navbar-nav navbar-right" <c:if test="${empty user }">style="display:none"</c:if>>
                    	<li><a>${user.name }, 您好</a></li>
                    	<li>
                    		<a style="padding-left:0px" class="dropdown-toggle" data-toggle="dropdown" id="mySpaceDropMenu">我的空间
                    			<span class="caret"></span>
                    		</a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="mySpaceDropMenu" id="mySpaceDropMenuUL">
								<li role="presentation">
						            <a role="menuitem" tabindex="-1" href="#">个人资料修改</a>
						            <a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath }/system/login/logout.html">退出</a>
						        </li>
							</ul>
                    	</li>
                    </ul>
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </div>
    </div>
    
    <!-- 主题内容 -->
    <div class="main-content tab-content" id="mainDiv">
        <div id="index" class="tab-pane active"></div>
        <div id="blog" class="tab-pane"></div>
        <div id="readBook" class="tab-pane"></div>
        <div id="tweets" class="tab-pane"></div>
        <div id="question" class="tab-pane"></div>
        <div id="sponsor" class="tab-pane"></div>
        <div id="chat" class="tab-pane"></div>
        <div id="test" class="tab-pane"></div>
    </div>
    
    <!-- 底部 -->
    <div class="footer" id="footer" >
        <ul class="nav nav-pills" style="padding-left:30%">
		  <li><a href="#">关于作者</a></li>
		  <li><a href="#">广告联系</a></li>
		  <li><a href="#">广告联系</a></li>
		  <li><a href="#">广告联系</a></li>
		  <li><a href="#">友情链接</a></li>
		</ul>
    </div>  
</body>

<script type="text/javascript">
    $(function(){
    	$("#footer").hide();
    	//大标题点击事件
    	$("#homeTitle").click(function (){
    		$.each($("#main-header-nav li"), function(index, element){
    			if (index == 0) {
    				$(this).addClass("active");
    			} else {
    			    $(this).removeClass("active");
    			}
            });
    		$.each($("#mainDiv .tab-pane"), function(index, element){
    			if (index == 0) {
    				$(this).addClass("active");
    			} else {
    			    $(this).removeClass("active");
    			}
            });
    		loadUrl("#index");
    	});
        //加载当前页面
        loadCurrentIndex();
        //主菜单点击
        $('#main-header-nav a').click(function (e) {
			$.each($(".main-content .tab-pane"), function(){
			    $(this).html("");
			});
            var scrollmem = $('body').scrollTop() || $('html').scrollTop();
            window.location.hash = this.hash;
            $('html,body').scrollTop(scrollmem);
            
            var id = window.location.hash;
            loadUrl(id);
        });
        $("#mySpaceDropMenu").click(function (){
        	return false;
        }).hover(function(){
        	$(this).attr("aria-expanded", true);
        	$(this).parent().addClass("open");
        });
    });
    
    /**
                加载当前页面
    */
    function loadCurrentIndex(){
    	var currentId = window.location.hash;//设置或获取 href 属性中在井号“#”后面的分段。
        if (currentId == null || currentId == '') {
            currentId = "#index";
        }
        $('#header-nav li').each(function (e) {
            var href = $(this).find("a").attr("href");
            if (href == currentId) {
                $(this).find("a").tab('show');
                $(this).addClass("active");
            } else {
                $(this).removeClass("active");
            }
        });
        loadUrl(currentId);
    }
    /**
	    根据id加载url
	*/
    function loadUrl(id) {
    	$("#footer").hide();
        $(".tab-content div").each(function(){
            $(this).empty();
        });
        if (id == "#index") {
            showContentById('${pageContext.request.contextPath}/home/index.html','', 'index');
        } else if (id == "#blog"){
            showContentById('${pageContext.request.contextPath}/blog/index.html','', 'blog');
        } else if (id == "#readBook"){
            showContentById('${pageContext.request.contextPath}/readBook/index.html','', 'readBook');
        } else if (id == "#tweets"){
            showContentById('${pageContext.request.contextPath}/tweets/index.html','', 'tweets');
        } else if (id == "#question"){
            showContentById('${pageContext.request.contextPath}/question/index.html','', 'question');
        } else if (id == "#chat"){
            showContentById('${pageContext.request.contextPath}/chat/index.html','', 'chat');
        } else if (id == "#test"){
            showContentById('${pageContext.request.contextPath}/test/index.html','', 'test');
        } else {
            showContentById('${pageContext.request.contextPath}/home/index.html','', 'index');
        }
    }
    /**
        shown.bs.tab事件在标签页显示时触发，但是必须在某个标签页已经显示之后。
                       分别使用 event.target 和 event.relatedTarget 来定位到激活的标签页和前一个激活的标签页。
    */
    /* $('a[data-toggle="tab"]').on('shown.bs.tab',function(e) {
    	$("#footer").hide();
        $(".tab-content div").each(function(){
            $(this).empty();
        });
        var id = $(e.target).attr('href');
        if (id == "#index") {
            showContentById('${pageContext.request.contextPath}/home/index.html','', 'index');
        } else if (id == "#blog"){
        	showContentById('${pageContext.request.contextPath}/blog/index.html','', 'blog');
        } else if (id == "#readBook"){
            showContentById('${pageContext.request.contextPath}/readBook/index.html','', 'readBook');
        } else if (id == "#tweets"){
            showContentById('${pageContext.request.contextPath}/tweets/index.html','', 'tweets');
        } else if (id == "#question"){
            showContentById('${pageContext.request.contextPath}/question/index.html','', 'question');
        } else if (id == "#chat"){
            showContentById('${pageContext.request.contextPath}/chat/index.html','', 'chat');
        }
    }); */

</script>
</html>

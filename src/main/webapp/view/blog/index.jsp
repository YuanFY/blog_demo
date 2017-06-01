<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
    <div class="row" id="blog-container">
        <div class="col-sm-9">
            <ul id="blog-nav" class="nav nav-tabs">
                <li class="active"><a href="#topsOfRecommend" data-toggle="tab">最新推荐</a></li>
                <li><a href="#topsOfToday" data-toggle="tab">今日热门</a></li>
                <li><a href="#topsOfWeek" data-toggle="tab">本周热门</a></li>
                <li><a href="#topsOfNew" data-toggle="tab">最新文章</a></li>
            </ul>
            <div id="blogContent" class="tab-content">
                <div class="tab-pane fade in active" id="topsOfRecommend">
                    <c:forEach begin="1" end="9">
                    <div class="row">
                        <div class="col-sm-2 header">
                            <a  href="#"  title="苗哥">
                                <img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
                            </a>
                        </div>
                        <div class="col-sm-10 content">
                            <h4>
                                <a>java中参数传递方式话题终结实例</a>
                            </h4>
                            <section class="blog-brief text-gradient">在html文件中，使用svg标记可以绘制图形。这个例子中主要用到path，linearGradient两个技术</section>
                            <footer class="box vertical dark-box">
                                <span>daemonkty</span>
                                <span>发布于</span>
                                <span>6小时前</span>
                                <span>阅读 111</span>
                                <span>点赞 1</span>
                            </footer>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div class="tab-pane fade in" id="topsOfToday">
                    <c:forEach begin="1" end="9">
                    <div class="row">
                        <div class="col-sm-2">
                            <a  href="#"  title="苗哥">
                                <img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
                            </a>
                        </div>
                        <div class="col-sm-10">
                            <h4>
                                <a>java中参数传递方式话题终结实例</a>
                            </h4>
                            <section class="blog-brief text-gradient">在html文件中，使用svg标记可以绘制图形。这个例子中主要用到path，linearGradient两个技术</section>
                            <footer class="box vertical blog-footer-box">
                                <span>daemonkty</span>
                                <span>发布于</span>
                                <span>6小时前</span>
                                <span>阅读 111</span>
                                <span>点赞 1</span>
                            </footer>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div class="tab-pane fade in" id="topsOfWeek">
                    <c:forEach begin="1" end="9">
                    <div class="row">
                        <div class="col-sm-2">
                            <a  href="#"  title="苗哥">
                                <img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
                            </a>
                        </div>
                        <div class="col-sm-10">
                            <h4>
                                <a>java中参数传递方式话题终结实例</a>
                            </h4>
                            <section class="blog-brief text-gradient">在html文件中，使用svg标记可以绘制图形。这个例子中主要用到path，linearGradient两个技术</section>
                            <footer class="box vertical blog-footer-box">
                                <span>daemonkty</span>
                                <span>发布于</span>
                                <span>6小时前</span>
                                <span>阅读 111</span>
                                <span>点赞 1</span>
                            </footer>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div class="tab-pane fade in " id="topsOfNew">
                    <c:forEach begin="1" end="9">
                    <div class="row">
                        <div class="col-sm-2">
                            <a  href="#"  title="苗哥">
                                <img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
                            </a>
                        </div>
                        <div class="col-sm-10">
                            <h4>
                                <a>java中参数传递方式话题终结实例</a>
                            </h4>
                            <section class="blog-brief text-gradient">在html文件中，使用svg标记可以绘制图形。这个例子中主要用到path，linearGradient两个技术</section>
                            <footer class="box vertical blog-footer-box">
                                <span>daemonkty</span>
                                <span>发布于</span>
                                <span>6小时前</span>
                                <span>阅读 111</span>
                                <span>点赞 1</span>
                            </footer>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-sm-3" id="">
            <ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="#">全部</a></li>
			  	<li><a href="#">移动开发</a></li>
			  	<li><a href="#">前端开发</a></li>
			  	<li><a href="#">人工智能</a></li>
			  	<li><a href="#">服务端开发/管理</a></li>
			  	<li><a href="#">游戏开发</a></li>
			  	<li><a href="#">编程语言</a></li>
			  	<li><a href="#">数据库</a></li>
			  	<li><a href="#">企业开发</a></li>
			  	<li><a href="#">图像/多媒体</a></li>
			  	<li><a href="#">大数据</a></li>
			  	<li><a href="#">软件工程</a></li>
			  	<li><a href="#">其他类型</a></li>
			</ul>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $("#footer").show();
    });
    </script>
</div>
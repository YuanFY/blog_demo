<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container" >
    <div class="row" id="home-container">
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-6" id="new-blog">
		            <div class="row title">
		                <div class="col-sm-10">
		                    <h4>最新文章</h4>
		                </div>
		                <div class="col-sm-2 title-more">
		                    <a href="#" >更多</a>
		                </div>
		                <div class="clearfix visible-xs"></div>
		            </div>
		            <hr/>  
		            <c:forEach begin="1" end="15">
		            <div class="row blog-title">
		                <div class="col-sm-10">
		                    <a class="news-link is-today " href="#" title="OVM虚拟化软件迭代时间调整，提高产品稳定性!" target="_blank">OVM 免费虚拟化软件迭代时间调整，提高产品稳定性!</a>
		                </div>
		                <div class="col-sm-2">
		                    <span class="box-fr news-date">02/28</span>
		                </div>
		            </div>  
		            </c:forEach>       
		        </div>
		        <div class="col-sm-6" id="new-question">
		            <div class="row title">
		                <div class="col-sm-10">
		                    <h4>最新问答</h4>
		                </div>
		                <div class="col-sm-2 title-more">
		                    <a href="#" >更多</a>
		                </div>
		                <div class="clearfix visible-xs"></div>
		            </div>
		            <hr/>  
		            <c:forEach begin="1" end="15">
		            <div class="row question-title">
		                <div class="col-sm-10">
		                    <a class="news-link is-today " href="#" title="OVM虚拟化软件迭代时间调整，提高产品稳定性!" target="_blank">OVM 免费虚拟化软件迭代时间调整，提高产品稳定性!</a>
		                </div>
		                <div class="col-sm-2">
		                    <span class="box-fr news-date">02/28</span>
		                </div>
		            </div> 
		            </c:forEach>       
		        </div>
            </div>
            <div class="row" style="display:none">
                <div class="row title">
	                <div class="col-sm-10">
	                    <h4>读后感</h4>
	                </div>
	                <div class="col-sm-2 title-more">
	                    <a href="#" >更多</a>
	                </div>
	                <div class="clearfix visible-xs"></div>
	            </div>
	            <hr/>  
	            <c:forEach begin="1" end="15">
	            <div class="row question-title">
	                <div class="col-sm-10">
	                    <a class="news-link is-today " href="#" title="OVM虚拟化软件迭代时间调整，提高产品稳定性!" target="_blank">OVM 免费虚拟化软件迭代时间调整，提高产品稳定性!</a>
	                </div>
	                <div class="col-sm-2">
	                    <span class="box-fr news-date">02/28</span>
	                </div>
	            </div> 
	            </c:forEach>   
            </div>
        </div>
        <div class="col-sm-3" id="new-tweets">
            <div class="row tweets-header">
	            <a  href="https://mos.meituan.com/enterprise-authentication?site=osc&amp;campaign=20170317sales" target="_blank" title="美团云">
	                <img  src="${pageContext.request.contextPath }/statics/images/home/tweets-header.png" width="100%">
	            </a>
                <form  role="form" class="tweets-action">
	                <div class="input-group">
					    <textarea class="form-control custom-control" rows="2" placeholder="今天你动弹了吗？" style="resize:none"></textarea>     
						<span class="input-group-addon btn btn-primary">动弹</span>
				    </div>
	            </form>
            </div>
            <div class="row tweets-content">
                <ul id="tweets-nav" class="nav nav-tabs">
	                <li class="active">
	                    <a href="#newTweets" data-toggle="tab">最新动弹</a>
	                </li>
	                <li><a href="#popularTweets" data-toggle="tab">热门动弹</a></li>
	                <li class="pull-right more" ><a href="#" >更多</a></li>
	            </ul>
	            <div id="tweetsContent" class="tab-content">
	                <div class="tab-pane fade in active" id="newTweets">
                        <c:forEach begin="1" end="9">
                        <div class="row">
                            <div class="col-sm-2">
                                <a  href="#"  title="苗哥">
                                    <img class="img-circle" width="30" height="30" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
                                </a>
                            </div>
                            <div class="col-sm-10">
                                <div class="form-group">
                                    <span class="tweet-user"><a href="#" target="_blank" title="苗哥">苗哥</a>：</span>肚子饿了，好在还有壹個面包，果断消灭掉...
                                </div>
	                            <div class="form-group time">
	                                <div class="col-sm-10">
	                                   <span>6分钟前&nbsp;<a class="commened" href="https://my.oschina.net/bairrfhoinn/tweet/12519004" target="_blank">(0评)</a>&nbsp;                </span>
	                                </div>           
	                                <div class="col-sm-2">
	                                   <span class="glyphicon glyphicon-thumbs-up" style="float:left"><value>0</value></span>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    </c:forEach>
	                </div>
	                <div class="tab-pane fade" id="popularTweets">
	                    <c:forEach begin="1" end="9">
                        <div class="row">
                           <div class="col-sm-2">
                                <a  href="#"  title="苗哥">
                                    <img class="img-circle" width="30" height="30" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">
                                </a>
                           </div>
                           <div class="col-sm-10">
                                <p class="tweet-content wrap"><span class="tweet-user"><a href="https://my.oschina.net/bairrfhoinn" target="_blank" title="苗哥">苗哥</a>：</span>肚子饿了，好在还有壹個面包，果断消灭掉...</p>
                                <div class="time">
                                    <span>6分钟前&nbsp;<a class="commened" href="https://my.oschina.net/bairrfhoinn/tweet/12519004" target="_blank">(0评)</a>&nbsp;                </span>
                                </div>
                           </div>
                        </div>
                        </c:forEach>
	                </div>
	            </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
    	$("#footer").show();
    });
	</script>
</div>

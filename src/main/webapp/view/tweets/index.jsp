<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
               	<div id="tweetsBody">
                
                </div>
                <div class="panel-footer"><div id="divPaging_new" class="pagination"></div></div>
            </div>
        </div>
        <div class="col-sm-4" id="">
            <ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="#">热门动弹</a></li>
			</ul>
			<div id="hotTweetsList"></div>
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
        	$("#insertImg").show();
        	var htmlContent = $("#insertImg").html()+"";
        	if (htmlContent == '') {
        		initUpload("insertImg", "${pageContext.request.contextPath}/tweets/uploadImg.html","image/gif,image/jpeg,image/png");
        	}
        });
        
        //发送动弹
        $("#sendTweets").click(function (){
        	if (su.isNull($("#userId").val())) {
        		jc.warn("用户尚未登录，请<a href='${pageContext.request.contextPath }/system/login/index.html'>登录</a>后再发动弹");
        		return;
        	}
        	if (su.isNull($.trim($("#tweetsContent").val()))) {
        		jc.error("内容不能为空");
        		return;
        	}
        	jc.post({
        		url : "${pageContext.request.contextPath}/tweets/save.html",
        		data : {tweetsContent : $("#tweetsContent").val()},
        		success : function(result){
					var msg = null;
			        if (result.error == 1) {
			        	$("#tweetsContent").val('');
			        	jc.info("发布成功");
			        	loadTweetsList();
			        }else if (result.error == -1){
			        	jc.warn("用户尚未登录，请<a href='${pageContext.request.contextPath }/system/login/index.html'>登录</a>后再发动弹");
			        }else if (result.error == 0) {
			        	jc.error(result.msg);
			        }
				}
        	});
        });
        showPageInfo_common("divPaging_new", parseInt("${listSize}"), 1);
        doquery_list(1);
        doquery_hotList();
    });
    function doquery_list(page){
    	jc.post({
    		url : "${pageContext.request.contextPath}/tweets/list.html",
    		data : {page:page,limit:8},
    		success : function(result){
				if (result == null || result.data.length == 0) {
					return;
				}
    			$("#tweetsBody").empty();
				for (var i = 0; i < result.data.length; i ++) {
					var $node = $('<div class="row">' +
			                '<div class="panel panel-default">' +
							'<div class="panel-body">' +
								'<div class="col-sm-2 header">' +
			                        '<a  href="#"  title="'+result.data[i].userName+'">' +
			                            '<img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">' +
			                        '</a>' +
			                    '</div>' +
			                    '<div class="col-sm-10 content">' +
			                        '<div class="box vertical">' +
	                					'<a class="ti-uname" href="#" title="'+result.data[i].userName+'" target="_blank">'+result.data[i].userName+'&nbsp;&nbsp;</a>' +
	                					'<span class="dark-box" >'+result.data[i].beforeTime+'</span>' +
	                            	'</div>' +
			                        '<section class="blog-brief text-gradient">'+replaceQQContent_common(result.data[i].tweetsContent)+'</section>' +
			                        '<footer class="dark-box">' +
			                            '<span class="glyphicon glyphicon-thumbs-up span-icon" title="点赞" onclick="liked(this)"><value>'+result.data[i].likeNum+'</value></span>' +
			                            '<span class="glyphicon glyphicon-comment span-icon"><value>'+result.data[i].commentNum+'</value></span>' +
			                            '<span >查看详情</span>' +
			                        '</footer>' +
			                    '</div>' +
							'</div>' +
						'</div>' +
	                '</div>');
					$("#tweetsBody").append($node);
				}
			}
    	});
    }
    function doquery_hotList(){
    	jc.post({
    		url : "${pageContext.request.contextPath}/tweets/hot/list.html",
    		data : {limit:9},
    		success : function(result){
				if (result == null || result.data.length == 0) {
					return;
				}
    			$("#hotTweetsList").empty();
				for (var i = 0; i < result.data.length; i ++) {
					var $node = $('<div class="row">' +
			                '<div class="panel panel-default">' +
							'<div class="panel-body">' +
								'<div class="col-sm-2 header-sm">' +
			                        '<a  href="#"  title="'+result.data[i].userName+'">' +
			                            '<img class="img-circle" src="https://static.oschina.net/uploads/user/68/136226_50.jpg?t=1402318962000">' +
			                        '</a>' +
			                    '</div>' +
			                    '<div class="col-sm-10 content-sm">' +
			                        '<div class="box vertical">' +
	                					'<a class="ti-uname" href="#" title="'+result.data[i].userName+'" target="_blank">'+result.data[i].userName+': &nbsp;&nbsp;</a>' +
	                            	'</div>' +
			                        '<section class="blog-brief text-gradient">'+replaceQQContent_common(result.data[i].tweetsContent)+'</section>' +
			                        '<footer class="dark-box">' +
			                            '<span class="glyphicon glyphicon-thumbs-up span-icon" title="点赞" onclick="liked(this)"><value>'+result.data[i].likeNum+'</value></span>' +
			                            '<span class="glyphicon glyphicon-comment span-icon"><value>'+result.data[i].commentNum+'</value></span>' +
			                            '<span >查看详情</span>' +
			                        '</footer>' +
			                    '</div>' +
							'</div>' +
						'</div>' +
	                '</div>');
					$("#hotTweetsList").append($node);
				}
			}
    	});
    }
    function liked(e) {
    	if ($(e).attr("class").indexOf("liked") > -1) {
    		$(e).removeClass("liked");
    	} else {
    		$(e).addClass("liked");
    	}
    }
    </script>
</div>

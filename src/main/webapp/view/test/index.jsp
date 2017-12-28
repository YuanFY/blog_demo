<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
    <div class="row">
        <div class="col-sm-2" style="padding-left: 0px">
            <ul class="nav nav-pills nav-stacked">
		        <li class="active"><a href="#uploadFile" role="tab" data-toggle="tab">文件上传</a></li>
		        <li><a href="#license" role="tab" data-toggle="tab">License</a></li>
		    </ul>
        </div>
        <div class="col-sm-10">
            <div class="main-content tab-content" >
		        <div id="uploadFile" class="tab-pane active">
		        <jsp:include page="uploadFile.jsp"/>
		        </div>
		        <div id="license" class="tab-pane">
                <jsp:include page="license.jsp"/>
                </div>
	        </div>
        </div>
    </div>
    <script type="text/javascript">
					
    </script>
</div>
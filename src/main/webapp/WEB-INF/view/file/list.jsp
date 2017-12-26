<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>文件管理</title>
</head>
<style>
body {
	margin: 10px;
	padding: 0px;
}

#center-model {
	width: 200px;
	height: 10px;
	position: fixed;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom: 0px;
	margin: auto;
}

pre table {
	font-size: 16px;
}

#footer {
	background: grey;
	width: 100%;
	height: 50px;
	position: fixed;
	bottom: 0;
	left: 0;
	text-align: center;
	padding: 15px;
}
</style>
<body>
<h1>Index of / file /</h1><hr>
<pre>
<table>
	<c:forEach items="${webFileList}" var="webFile">
		<tr>
		  <td><a href="${pageContext.request.contextPath}/file/download/${webFile.id}">${webFile.fileName }</a>&nbsp;&nbsp;&nbsp;</td>
		  <td><fmt:formatDate value="${webFile.uploadTime}" pattern="yyyy-MM-dd HH:mm" />&nbsp;&nbsp;&nbsp;</td>
		  <td>${webFile.fileSize div 1024 }K&nbsp;&nbsp;&nbsp;</td>
		  <td><a href="${pageContext.request.contextPath}/file/download/${webFile.id}">下载</a> <a target="_blabk" href="${pageContext.request.contextPath}/file/preview/${webFile.id}">预览</a></td>
		</tr>
	</c:forEach>
</table>
</pre>
<hr>

<div id="center-model" style="display:none">
  <img src="${pageContext.request.contextPath}/img/load.gif">
</div>

<div id="footer">
  <form id="uploadForm" method="POST" action="${pageContext.request.contextPath}/file/upload" enctype="multipart/form-data" >
    <input type="file" name="uploadFile">
    <button type="button" onclick="uploadAjax()">上传文件</button>
  </form>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
/*
 * 利用FormData使用Jquery AJAX上传文件 
 */
function uploadAjax(){
	var uploadForm = new FormData(document.getElementById("uploadForm"));
	$("body").css("background","#e6e6e6");
	document.getElementById('center-model').style.display='block';
	$.ajax({
	    url : "${pageContext.request.contextPath}/file/upload", //请求的url地址
	    dataType : "json", //返回格式为json
	    data : uploadForm, 
	    type : "POST", 
	    processData:false,
        contentType:false,
	    success:function(result){
	           if(result.code == 1){
	           	   alert(result.message);   
	           	   location.reload(true);   
	           }
	           if(result.code == 0){
  		          document.getElementById('center-model').style.display='none';
	        	  alert(result.message);
	        	  $("body").css("background","white");
	           }
	     	},
		  error : function() {
	           document.getElementById('center-model').style.display='none';
		       alert("呀，服务器出现了错误");
			   $("body").css("background","white");
		    }
		});
	}
	     
</script>

</body>
</html>
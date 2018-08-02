<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <base href="<%=basePath%>">
    
    <title>My JSP 'qq.jsp' starting page</title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="stylesheet" type="text/css" href="css/summernote.css">
    <link rel="stylesheet" type="text/css" href="css/summernote-bs3.css">
     <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/com.css">
	
   
   
  </head> --%>
  
 <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Summernote</title>

  <!-- 通过cdn添加css和js  -->
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

  <!-- 添加jq.js，注意，所有的js必须在jquery.js的后面，因为所有的js都是基于jq -->
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 

  <!-- 添加bootstrap框架的js -->
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

  <!-- 添加summernote的css和js -->
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>

  <!-- 添加中文包js -->
  <script type="text/javascript" src="js/summernote-zh-CN.js"></script>
 <script type="text/javascript" src="js/summer.js"></script>  
  <style type="text/css">
    body{
      padding: 100px; 
    }
  </style>

</head>
<body>
<h1 style="color: blue;text-align: center;">summernote</h1>
    <div id="summernote"></div>
    <button id="submit" class="btn btn-info" >提交</button>
</body>

<script type="text/javascript">
    //调用富文本编辑
   
</script>

</html>
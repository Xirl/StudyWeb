<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script src="js/jquery.json.min.js"></script>
 <script type="text/javascript" src="js/header.js"></script>  
<style type="text/css">
.other_bn button{
color: #fff;
background-color: #286090;
border-color: #204d74;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-moz-user-select: true;
	-ms-user-select: true;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}
#do_themeid{ margin-top:1%;background-color:#f0ad4e;border:2px solid transparent;border-radius: 4px;
}
</style>
  </head>
  
  <body>
  <span id="p_test"></span>
  <div id="do_themeid" style="margin-left:7%">
  <div id="gettheme" style="float:left">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
 我要提问
</button> 
  </div>
  <div style="margin-left:2%;display:none;float:left;margin-top:4px" class="other_bn"> 
   <button type="button" class="btn_type"></button>
  </div>
  
  </div>

<div class="modal fade" id="myModal"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">提问</h4>
          </div>
          <div class="modal-body">
            <p>问题描述</p>
            <textarea class="form-control"></textarea>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary" type="button">提交</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
</div>
<div style="height:200px; background-color:#f5f5f5" ></div>
  </body>
</html>

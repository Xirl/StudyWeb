<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'topic.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="zlpy/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	var ar=new Array();
	function onTe(){
		ar[2]="jk";
		ar[4]='bk';
		$.each(ar,function(i,ele){
			alert(ar.length);
		})	;
	}
	$(function(){
	
		onTe();
	});
	var id="${param.queId}";
	var html=[];
	var op=['A','B','C','D','E','F'];
		function onOptions(_i){
			$.ajax({
				async:true,
				url:'goods/topicShow.action?queId='+_i,
				type:'post',
				data:1,
				dataType:'json',
				success:function(data){
					if(data.length!=0){
						$.each(data,function(i,ele){
							var _tr=$("#div").html();
							_tr=_tr.replace("{topicName}",data[i].topicName);
							_tr=_tr.replace("typeName",data[i].typeName);
							html.push(_tr);
							if(data[i].options!=null){
								html.push('<input type="checkbox" value="'+data[i].options.optionsA+'" />'+op[0]+':'+data[i].options.optionsA+'<br>');
							}
						});
						$("#topic").html(html.join(""));
					};
				},
				error:function(request,msg,e){
					alert('错误 ：'+request.responseText);
				}
			});
		}
		$(function(){
			onOptions(id);
		});
	</script>
  </head>
  <body>
  <div id="topic">
  
  </div>
  <div id="div" style="display: none">
  		<span>(typeName){topicName}()</span><br>
  </div>
  <div style="display: none" id="op">
	  <input type="checkbox" value="{optionsA}" />A:{optionsA}<br>
	  <span>{optionsB}</span><br>
	  <span>{optionsC}</span><br>
	  <span>{optionsD}</span><br>
	  <span>{optionsE}</span><br>
	  <span>{optionsF}</span><br>
  </div>
  </body>
</html>

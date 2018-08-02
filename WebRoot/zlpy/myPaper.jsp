<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
     <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=0"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="zlpy/css/ipr.css"/>
    <link rel="stylesheet" type="text/css" href="zlpy/css/lanyon.css"/>
	<script type="text/javascript" src="zlpy/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		var html=[];
		var index=1;
		var index2;
		var userId="${sessionScope.currUser.userId }";
		var id="${param.poolId}";
		$(function(){
			html=[];
			$("#pageIndex").text("${page.pageIndex }");
			$("#countSize").text("${page.countSize }");
			$("#countIndex").text("${page.countIndex }");
			<c:forEach items="${page.list }"  var="list">
				var _tr=$("#ul").html();
				_tr=_tr.replace("{questionPaperName}","${list.questionPaperName }");
				_tr=_tr.replace("{href}","zlpy/topic.jsp?queId="+"${list.queId }");
				_tr=_tr.replace("{solu}","zlpy/updateSolu.jsp?queId="+"${list.queId }");
				html.push(_tr);
 			 </c:forEach>
 			 $("#use").html(html.join(""));
 			 if($(".ipr-h-text").val()==''){
 			  $("#pre").attr("onclick","return onQuery("+id+","+"''"+","+(index*1-1)+")");
 			  $("#nex").attr("onclick","return onQuery("+id+","+"''"+","+(index*1+1)+")");
 			 }else{
 			  $("#pre").attr("onclick","return onQuery("+id+","+ $(".ipr-h-text").val()+","+(index*1-1)+")");
 			  $("#nex").attr("onclick","return onQuery("+id+","+ $(".ipr-h-text").val()+","+(index*1+1)+")");
 			 }
 			 $(".ipr-h-btn").click(function(){
 			 	if($(".ipr-h-text").val()==""){
 			 		onQuery(id,"",1);
 			 	}else{
 			 		onQuery(id,$(".ipr-h-text").val(),1);
 			 	}
			});
			//onQuery(id,"",1);
		});
		function onQuery(_i,_questionPaperName,_indexId,_userId){
			html=[];
			if(_indexId<1){
				alert("当前已是首页");
				return false;
			}else if(_indexId>$("#countIndex").text()*1){
				alert("当前已是尾页");
				return false;
			}
			$.ajax({
				async:true,
				url:'goods/questionPaper.action?poolId='+_i+'&questionPaperName='+_questionPaperName+'&pageIndex='+_indexId+'&userId='+_userId,
				type:'post',
				data:1,
				dataType:'json',
				success:function(data){
					$("#use").html(html.join(""));
					$("#pageIndex").text(data.pageIndex);
					$("#countSize").text(data.countSize);
					$("#countIndex").text(data.countIndex);
					if(data.list && data.list.length>0){
						$.each(data.list,function(i,ele){
							var _tr=$("#ul").html();
							_tr=_tr.replace("{questionPaperName}",ele.questionPaperName);
							_tr=_tr.replace("{href}","zlpy/topic.jsp?queId="+ele.queId);
							_tr=_tr.replace("{solu}","zlpy/updateSolu.jsp?queId="+ele.queId);
							html.push(_tr);
						});
						$("#use").html(html.join(""));
						var prePage=data.pageIndex-1;
						var nexPage=data.pageIndex+1;
						 if($(".ipr-h-text").val()==''){
 			 				 $("#pre").attr("onclick","return onQuery("+id+","+"''"+","+prePage+")");
 			 				 $("#nex").attr("onclick","return onQuery("+id+","+"''"+","+nexPage+")");
 			 			}else{
 			 				 $("#pre").attr("onclick","return onQuery("+id+",'"+ $(".ipr-h-text").val()+"',"+prePage+")");
 			 				 $("#nex").attr("onclick","return onQuery("+id+",'"+ $(".ipr-h-text").val()+"',"+nexPage+")");
 						 }
					}else{
						html.push('<tr><td><font color="red">没有找到数据</font></td></tr>');
					}
					index2=_indexId;
					return false;
				},
				error:function(request,msg,e){
					alert('错误 ：'+request.responseText);
				}
			});
			return false;
		};
	</script>
  </head>
  
  <body class="body">
    <div class="header">
      <div class="logo">题卷大全</div>
      <label for="sidebar-checkbox" class="sidebar-toggle"></label>
      <div class="search">
          <input type="text" placeholder="请输入搜索内容" class="ipr-h-text"/>
          <button type="button" class="ipr-h-btn" id="find">搜索</button>
      </div>
      <input type="checkbox" id="sidebar-checkbox" class="sidebar-checkbox"/>
      <div id="sidebar" class="sidebar">
        <div class="sidebar-nav">
        <a href="page/homepage.action" class="sidebar-nav-item current">返回首页</a>
      </div>
    </div>
    
    
    <div class="ipr-index-mian" id="page">
      <div class="ipr-index-top">
        <ul id="use">
        </ul>
        <ul style="display: none" id="ul">
          <li class="breakfix"><a href="{href}">{questionPaperName}</a>
          <a href="{solu}" style="padding-left: 30px; color: red;">编辑答案</a></li>
        </ul>
      </div>
      <div  id="use2">
      	共<span style="color: red;" id="countSize">{countSize}</span>条记录&nbsp;
      	共<span style="color: red;" id="countIndex">{countIndex}</span>页 &nbsp;
      	 当前第<span style="color: red;" id="pageIndex">{pageIndex}</span>页&nbsp;&nbsp;
      	 <a id="pre" href="javascript:void(0);" style="border: 1px solid #353535;height: 35px;line-height: 35px;" onclick="alert(1);return onQuery(1,1,1);">
      	 上一页</a>
      	 <a id="nex" href="javascript:void(0);" style="border: 1px solid #353535;height: 35px;line-height: 35px;" onclick="{nextPage}">
      	 下一页</a>
      </div>
     </div>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>试卷</title>
	<meta name="description" content="Free html5 website template.">
	<meta name="keywords" content="free html5 templates">
	<meta name="author" content="Website  www.html-templates.co.uk">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	div[name="word"]{
		position: absolute;
		z-index: 99;
		width: 145px;
		height: auto;
		background-color: white;
		border: black solid 1px;
		display: none;
	}
	.click_work{
		padding-bottom: 8px;
		font-weight:lighter;
		font-size: 13px;
		cursor:pointer;/*鼠标放上变成小手*/
	}
	.click_work:hover{
		color: orange;
		background-color: gray;
	}
	.error{
		color: gray;
		cursor:pointer;/*鼠标放上变成小手*/
	}
</style>
	<link rel="stylesheet" href="zlpy/css/style.css" />
	<script type="text/javascript" src="zlpy/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="zlpy/js/jsonpAjax.js"></script>
	<script type="text/javascript">
	var count=1;
	$(function(){
		$("#add").click(function(){
			var _tr=$("#div").html();
			_tr=_tr.replace("{textId}","text"+(++count));
			_tr=_tr.replace("{wordId}","word"+count);
			_tr=_tr.replace("{selectId}","select"+count);
			_tr=_tr.replace("{numId}","num"+count);
			$("#table").append(_tr);
		});
		$("#sub").click(function(){
		if(!confirm("确定要上传该题卷吗？"))return;
			var lengths=$("#table tr").length;
			var textVal=0;
			var selectVal=0;
			var numVal=0;
			var isFlage=true;
			var numFlage=true;
			var topicAr=new Array();
			if($("#paperName").val()==""){
				alert("新的题卷名称不能为空");
				return;
			}
			if($("#pool option:selected").val()==""){
				alert("请选择要投放的题库");
				return;
			}
			for(var i=1;i<lengths+1;i++){
				numVal=$("#num"+i).val();
				selectVal=$("#select"+i+" option:selected").val();
				textVal=$("#text"+i).attr("class");
				var flage=true;
				if(textVal==""||selectVal==""){
					if(isFlage){
						if(!confirm("您还有空缺没有选择，如果继续操作，\n该题卷将被忽略，请问是否要继续？")){
							return;
						}
						isFlage=false;
					}
					flage=false;
				}
				if(numVal==""){
					if(numFlage){
						if(!confirm("有题目数量未定，如果继续操作，\n将默认为1，请问是否要继续？")){
							numFlage=false;
							return;
						}
					}
					numVal=1;
				}else if(numVal==0){
					numVal=1;
				}
				if(flage){
					topicAr[i-1]=textVal+" "+selectVal+" "+numVal+" ";
				}
			}
			location.href="topic/randomTopic.action?array="+topicAr+
			"&queName="+$("#paperName").val()+"&poolId="+$("#pool option:selected").val();
		});
	});
	
	</script>

  </head>
  
  <body>


<br />
<br />

 <body>


<br />
<br />

<div id="page">

  <div id="logo">
  
  <div id="logoleft">
  <input type="button" value="增加新题卷" id="add"/>
  </div>
  
 <div id="logoright" style="color: red; font-size: 35px">
 </div>
  <div>
  题卷名称:<input type="text" placeholder="请输入题卷名" id="paperName"/>
  投放题库：<select id="pool">
  	<option value="">--请选择--</option>
  	<c:forEach items="${pool}" var="list">
 			<option value="${list.detailsId}">${list.poolName}</option>
 		</c:forEach>
  </select>
  </div>
      </div>

  <div id="content">
  <table align="left" cellspacing="0" cellpadding="10" border="0" id="table">
	<tr>
		<td style="position: relative;">
			题卷名：<input id="text1" type="text" placeholder="请选择题卷" name="find" class="">
			<div id="word1" style="margin-left: 65px" name="word">
			</div>
			类型：<select id="select1" name="type">
			<option value="" >--请先选择题卷--</option>
			</select>
			数量：<input placeholder="" id="num1" name="num"/>
		</td>
	</tr>
</table>
<table>
<tbody style="display: none;" id="div">
<tr>
	<td style="position: relative;">
		题卷名：<input id="{textId}" type="text" placeholder="请输入题卷名" name="find">
		<div id="{wordId}" style="margin-left: 65px" name="word">
		</div>
		类型：<select id="{selectId}" name="type">
		<option value="" >--请先选择题卷--</option>
		</select>
		数量：<input placeholder="" id="{numId}" name="num"/>
		<input type="button" value="取消"/>
	</td>
</tr>
</tbody>
</table>
  <div style="clear: both;">&nbsp;</div>
<div id="footer">

    <div id="footerleft" style="text-align: center;">
<input type="button" value="上传题卷" id="sub" />
 
 </div>
 
    <div id="footerright">
    
</div>

</div>

</div>

<br />
<br />
    
</body>
</html>

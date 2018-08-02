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
	
	<link rel="stylesheet" href="zlpy/css/style.css" />
	<script type="text/javascript" src="zlpy/js/jquery-1.8.3.js"></script>
	<script src="zlpy/js/jquery_validate/jquery.form.min.js" type="text/javascript"></script>
	<script src="zlpy/js/jquery_validate/jquery.validate.min.1.11.1.js" type="text/javascript"></script>
	<script src="zlpy/js/jquery_validate/jquery.validate_zh_cn.js" type="text/javascript"></script>
	<script type="text/javascript">
		function onSub(){
			var typeId;
			var topicId;
			var soluId;
			var count=$("#form>span").length;
			var isFlag=true;
			if($("#pool option:selected").val()==""){
				alert("请选择要投放的题库");
				return false;
			}
			if($("#poolName").val()==""){
				alert("请填写题卷名");
				return false;
			} 
			poolName=$("#poolName").val();
			poolId=$("#pool option:selected").val();
			for(var i=0;i<count;i++){
				var j=$("#form>span").eq(i).attr("name");
				//编写Id
				typeId="#type"+j;
				topicId="#topic"+j;
				soluId="#solu"+j;
				if($(topicId).val()==""){
					alert("题目"+j+"内容不能为空");
					return false;
				}
				//对题目的类型选择进行判断，如果不为空，则保存选项，并查看是否有答案编辑
				if($(typeId).find("option:selected").val()==""){
					alert("题目"+j+"类型不能为空");
					return false;
				}else if($(typeId).find("option:selected").text()=="单选题"){
					var length=$(soluId+" input[type='radio']").length;
						for(var k=0;k<length;k++){
						 var val=$(soluId+" input[type='radio']").eq(k).next().val();
							if(val==""){
								alert("题目"+j+"的选项不能为空");
								return false;
							}
						}
				}else if($(typeId).find("option:selected").text()=="多选题"){
					var length=$(soluId+" input[type='checkbox']").length;
						for(var k=0;k<length;k++){
							 var check=$(soluId+" input[type='checkbox']").eq(k);
							 var val=check.next().val();
								if(val==""){
									alert("题目"+j+"的选项不能为空");
									return false;
								}
							}
				}
				if($("#score"+j).val()=="" ){
					if(isFlag){
						if(!confirm("如果分数项不填写，将默认为0，您确定要继续吗？")){
							return false;
						}
						isFlag=false;
					}
					$("#score"+j).val(0);
				}
			}
		};
		
		$(function(){
			var i=1;
			var count=1;
			$(".button").click(function(){
				count++;
				var _to=$("#div").html();
				_to=_to.replace("{topicId}","topic"+(++i));
				_to=_to.replace("{topic}","题目"+count+":");
				_to=_to.replace("{score}","score"+i);
				_to=_to.replace("{typeId}","type"+i);
				_to=_to.replace("{soluId}","solu"+i);
				_to=_to.replace("{span}",i);
				$("#form").append(_to);
				if($("#score"+(i-1)).val()!=""){
					$("#score"+i).val($("#score"+(i-1)).val());
				}
			});
			$(".add").live("click",function(){
				if($(this).prev().children('span').length>4){
					alert("已达到最大个数");
				}else{
					var nameVal=$(this).parent().attr("name");
					if($(this).attr("name")=="radioAdd"){
						$(this).prev().append('<span><input type="radio"  name="radio'+nameVal+'"/><input name="text'+nameVal+'"/><input type="button" value="取消" class="remove"/><br></span>');
					}else if($(this).attr("name")=="checkAdd"){
						$(this).prev().append('<span><input type="checkbox"  name="check'+nameVal+'"/><input name="text'+nameVal+'"/><input type="button" value="取消" class="remove"/><br></span>');
					}
				}
			});
			$(".remove").live("click",function(){
				if($(this).parent().parent().children('span').length==2){
					alert("至少保留两个选项");
				}else{
					$(this).parent().remove();
				}
			});
			$(".removeTopic").live("click",function(){
				if($(this).parent().parent().children('span').length==1){
					alert("至少保留一道题目");
				}else{
					count--;
					$(this).parent().remove();
				}
				
			});
			$(".score").live("blur",function(){
			 var patrn = /^(-)?\d+(\.\d+)?$/;
				if(patrn.exec($(this).val())==null || patrn.exec($(this).val())==""){
					$(this).val(0);
				}
			});
			$("a[name='solu']").live("click",function(){
				if($(this).text()=="编辑答案"){
					$(this).text("完成编辑");
					$(this).next().removeAttr("style");
				}else{
					$(this).text("编辑答案");
					$(this).next().attr("style","display:none;");
				}
				
			});
			//类型的改变部分
			$("select[class='top']").live("change",function(){
				var css=$(this).next().next().next().next();
				var spanName=$(this).parent().attr("name");
				if($(this).find("option:selected").text()=="单选题"){
					var _radio=$("#css>span[class='radio']").html();
					_radio=_radio.replace(/{radioName}/g,"radio"+spanName);
					_radio=_radio.replace(/{radioSpanName}/g,spanName);
					_radio=_radio.replace(/{textName}/g,"text"+spanName);
					css.html(_radio);
				}else if($(this).find("option:selected").text()=="多选题"){
					var _check=$("#css>span[class='check']").html();
					_check=_check.replace(/{checkName}/g,"check"+spanName);
					_check=_check.replace(/{checkSpanName}/g,spanName);
					_check=_check.replace(/{textName}/g,"text"+spanName);
					css.html(_check);
				}else if($(this).find("option:selected").val()!=""){
					var _text=$("#css>span[class='textrea']").html();
					_text=_text.replace(/{textName}/g,spanName);
					_text=_text.replace(/{textSpanName}/g,spanName);
					css.html(_text);
				}else{
					css.html("");
				}
			});
			//选项选中事件
			$("input[type='radio']").live("click",function(){
				var name=$(this).parent().parent().parent().attr("name");
				var html=$(this).parent().parent().children().find("input[type='radio']");
				var length=html.length;
				for(var i=0;i<length;i++){
					html.eq(i).next().attr("name","text"+name);
				}
				$(this).next().attr("name",name);
			});
			$("input[type='checkbox']").live("click",function(){
				var name=$(this).parent().parent().parent().attr("name");
				var html=$(this).parent().parent().children().find("input[type='checkbox']");
				$(this).next().attr("name",name);
				var length=html.length;
				for(var i=0;i<length;i++){
					if(html.eq(i).attr("checked")!="checked"){
						html.eq(i).next().attr("name","text"+name);
					}
				}
			});
		});
	</script>
  </head>
  
   <body>


<br />
<br />

<div id="page">
<form action="topic/insertTopic.action" method="get">
<!-- start -->
  <div id="logo">
  <div id="logoleft">
  <h1 >题卷创建</h1>
 
  </div>
  <br>
  
 <div id="logoright" style="color: red; font-size: 35px">
 	
 </div>
  
      </div>

  <div id="content">
   <span >
 		题库：<select id="pool" name="poolName">
 			<option value="">--请选择--</option>
 			<c:forEach items="${pool}" var="list">
 			<option value="${list.detailsId}">${list.poolName}</option>
 			</c:forEach>
 		</select>
 		题卷名：<input id="poolName" name="paperName"/ >
 	</span>
 	
<div id="form">
<br>
<span name="1">
  <!-- 题目模板1 -->
  题目1:
  <select id="type1" class="top" name="typeName">
	<option value="" >--题目类型--</option>
	<c:forEach items="${type}" var="list">
	<option value="${list.typeId }" >${list.typeName }</option>
	</c:forEach>
	</select>
	
	<input type="text" placeholder="题目内容"  id="topic1" name="topicName"/>
	<input placeholder="分数" style="width: 50px" id="score1" class="score" name="scoreName"/>
	<input type="button" value="取消" class="removeTopic" />
	 <div id="solu1"></div>
	</span> 
</div>

 <input type="button" value="添加题目" class="button"/>
 
  <div style="clear: both;">&nbsp;<br> <input type="submit" value="上传题卷"  onclick="return onSub()" /></div>
  </form>
  <!-- end -->
<div id="footer">

    <div id="footerleft">

 
 </div>
 
    <div id="footerright">
    
</div>


<!-- 题目模板2 -->
 <div id="div" style="display: none;">
 <span name="{span}">
  <br>
 	{topic}
  <select id="{typeId}" class="top" name="typeName">
	<option value="" >--题目类型--</option>
	<c:forEach items="${type}" var="list">
	<option value="${list.typeId }" >${list.typeName }</option>
	</c:forEach>
	</select>
	<input type="text" placeholder="题目内容" id="{topicId}" name="topicName"/>
	<input placeholder="分数" style="width: 50px" id="{score}" class="score" name="scoreName"/>
	 <input type="button" value="取消" class="removeTopic" />
	 <div id="{soluId}"></div>
	  </span>
  </div>
  <!-- 类型分叉模板 -->
  <div style="display: none;" id="css">
  <span  class="radio">
   <span name="{radioSpanName}">
  <div>
	<span><input type="radio"  name="{radioName}"/><input name="{textName}"/>
	<input type="button" value="取消" class="remove"/><br>
	</span>
	<span><input type="radio" name="{radioName}"/><input name="{textName}"/>
	<input type="button" value="取消" class="remove"/><br>
	</span>
	</div>
	<input type="button" value="添加选项" class="add" name="radioAdd"/>
  </span>
  </span>
  
  <span  class="check">
  <span name="{checkSpanName}">
  <div>
	<span><input type="checkbox"  name="{checkName}"/><input name="{textName}"/>
	<input type="button" value="取消" class="remove"/><br>
	</span>
	<span><input type="checkbox" name="{checkName}"/><input name="{textName}"/>
	<input type="button" value="取消" class="remove"/><br>
	</span>
	</div>
	<input type="button" value="添加选项" class="add" name="checkAdd"/>
	</span>
  </span>
  
  <span  class="textrea">
  <span name="{textSpanName}">
 	 <a href="javascript:void(0)" name="solu" >编辑答案</a><span style="display: none;"><br>
	 <textarea rows="3" cols="20" placeholder="编辑答案" name="{textName}"></textarea ></span>
  </span>
  </span>
  </div>
  
</div>

</div>
</div>

<br />
<br />



    
</body>
</html>

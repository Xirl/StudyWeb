<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
	<script type="text/javascript">
	//样式存有，暂不处理
		//Cufon.replace('h1'); // Works without a selector engine
		//Cufon.replace('#sub1'); // Requires a selector engine for IE 6-7, see above
		var id="${param.queId}";
		var html=[];
		var op=['A','B','C','D','E','F'];
		var tit=['一','二','三','四','五'];
		var radioId=0;
		var checkId=0;
		//整个页面的ajax显示
		function onOptions(_i){
			$.ajax({
				async:true,
				url:'goods/topicShow.action?queId='+_i,
				type:'post',
				data:1,
				dataType:'json',
				beforeSend:function(){
					$('#topic').html('<div style="color:blue;">正在加载题目。。。。</div>');
				},
				success:function(data){
				var k=0;//题目的序号
				var q=0;//题框的序号
				var divHtml=[];
					if(data.type.length!=0&&data.type!=null&&
							data.topic.length!=0&&data.topic!=null){
							$("#queId").attr("value",id);
							 //题框的排序部分
							 $('#topic').html("");
							$.each(data.type,function(i,tele){
								if(tele.typeName=="单选题"){
										radioId=tele.typeId;
									}else if(tele.typeName=="多选题"){
										checkId=tele.typeId;
									}
									divHtml.push('<div id="divType'+tele.typeId+'"></div>');
									$("#topic").append(divHtml);
									$("#divType"+tele.typeId).html('<h3>'+tit[q++]+"."+tele.typeName+'</h3>');
								}); 
							//题目的显示部分
						$.each(data.topic,function(i,ele){
							var topHtml=[];
							var _tr=$("#div").html();
							_tr=_tr.replace("{topicName}",++k+"."+ele.topic.topicName);
							topHtml.push(_tr);
							//选择题的构成部分
							//1.单选
							if(ele.topic.typeId==radioId){
								if(ele.list!=null && ele.list.length>0){
								topHtml.push('<br/>');
								topHtml.push('<span id="radio'+ele.topic.topicId+'" name="'+ele.topic.topicId+'"><span>');
								$("#divType"+ele.topic.typeId).append(topHtml);
								topHtml=[];
								$.each(ele.list,function(o,opt){
									var _op=$("#radio").html();
									_op=soluAdd(ele.solut,_op,opt,ele.topic.score);
									_op=_op.replace(/{options}/g,k);
									_op=_op.replace(/{optionIndex}/g,op[o]+":");
									_op=_op.replace(/{optionsName}/g,opt.options);
									topHtml.push(_op);
									});
								}
								$("#radio"+ele.topic.topicId).html(topHtml);
								//多选
							}else if(ele.topic.typeId==checkId){
								if(ele.list!=null && ele.list.length>0){
								topHtml.push('<br/>');
								topHtml.push('<span id="check'+ele.topic.topicId+'" name="'+ele.topic.topicId+'"><span>');
								$("#divType"+ele.topic.typeId).append(topHtml);
								topHtml=[];
								$.each(ele.list,function(o,opt){
									var _op=$("#check").html();
									_op=soluAdd(ele.solut,_op,opt,ele.topic.score);
									_op=_op.replace(/{options}/g,k);
									_op=_op.replace(/{optionIndex}/g,op[o]+":");
									_op=_op.replace(/{optionsName}/g,opt.options);
									topHtml.push(_op);
									});
									$("#check"+ele.topic.topicId).html(topHtml);
								}
							}else{
									topHtml.push('<br/>');
									var _wr=$("#write").html();
									topHtml.push(_wr);
									topHtml.push('<span id="check'+ele.topic.topicId+'" name="'+ele.topic.topicId+'"><span>');
									$("#divType"+ele.topic.typeId).append(topHtml);
									topHtml=[];
									if(ele.solut!=null && ele.solut.length>0){
										topHtml.push('<div ><textarea rows="3" cols="20" style="color:red;" name="'+ele.topic.topicId+'">'+ele.solut[0].solution+'</textarea></div>');
									}else{
										topHtml.push('<div ><textarea rows="3" cols="20" style="color:red;">该题暂无答案</textarea></div>');
									}
									$("#check"+ele.topic.topicId).html(topHtml);
							}
								
						});
					};
				},
				error:function(request,msg,e){
					alert('错误 ：'+request.responseText);
				}
			});
		}
		
			function soluAdd(_solu,_op,_opt,_score){
				//答案注入
				if(_solu!=null && _solu.length>0){
					if(_solu.length>1){
						var isFlag=false;
						for(var j=0;j<_solu.length;j++){
							if(_solu[j].solution==_opt.options){
								_op=_op.replace(/{topicId}/g,_solu[j].topicId);
								isFlag=true;
								break;
							}
						}
						if(isFlag){
							_op=_op.replace(/{optionsValue}/g,_score);
							_op=_op.replace(/{checked}/g,checked="checked");
						}else{
							_op=_op.replace(/{optionsValue}/g,0);
						}
					}else{
						if(_solu[0].solution==_opt.options){
							_op=_op.replace(/{topicId}/g,_solu[0].topicId);
							_op=_op.replace(/{optionsValue}/g,_score);
							_op=_op.replace(/{checked}/g,checked="checked");
							}else{
								_op=_op.replace(/{optionsValue}/g,0);
							}
						}
					}
					return _op;
				}
		$(function(){
			onOptions(id);
			$("input[type='checkbox']").live("click",function(){
				var chil=$(this).parent().children("input[type='checkbox']");
				var length=chil.length;
				var topicId=$(this).parent().attr("name");
				$(this).next().attr("name",topicId);
				for(var i=0;i<length;i++){
					if(chil.eq(i).attr("checked")!="checked"){
						chil.eq(i).next().removeAttr("name");
					}
				}
			});
			$("input[type='radio']").live("click",function(){
				var chil=$(this).parent().children("input[type='radio']");
				var length=chil.length;
				var topicId=$(this).parent().attr("name");
				$(this).next().attr("name",topicId);
				for(var i=0;i<length;i++){
					if(chil.eq(i).attr("checked")!="checked"){
						chil.eq(i).next().removeAttr("name");
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

  <div id="logo">
  
  <div id="logoleft">
  <h1>html5</h1>
  </div>
  
 <div id="logoright" style="color: red; font-size: 35px">
 	
 </div>
  
      </div>
     

  <div id="content">
<form action="topic/updataSolu.action" id="subForm">
<div id="topic"> 
  </div>
  <input style="display: none;"  name="queId" value="{queId}" id="queId"/>
  <input type="submit" value="完成" />
  </form>

<!-- 题目的模板 -->
  <div id="div" style="display: none">
  		<span name="topicName">{topicName}</span>
  </div>
  <!-- 选择题选项的模板 -->
  <!-- 单选 -->
  <div style="display: none" id="radio">
	  <input type="radio"  value="{optionsValue}" name="{options}" class="{topicId}" {checked}/>{optionIndex}
	  <input type="hidden" name="{topicId}" value="{optionsName}"/>
	  <span >
	  {optionsName}
	  </span>
	  <br>
  </div>
  
  <!-- 多选 -->
  <div style="display: none" id="check">
  	<input type="checkbox"  value="{optionsValue}" name="{options}" class="{topicId}" {checked}/>{optionIndex}
  	 <input type="hidden" name="{topicId}" value="{optionsName}"/>
  	<span name="{topicId}">
  	{optionsName}
  	</span><br>
  </div>
  
<!-- 填空题模板 -->
<div id="write">
	<div>
	<textarea rows="3" cols="20" style="display: none;"></textarea>
	</div>
</div>
  <div style="clear: both;">&nbsp;</div>
<div id="footer">

    <div id="footerleft">

 2015@<a href="javascript:void(0);" title="网站模板" target="_blank">做题网站</a>
 </div>
 
    <div id="footerright">
    
</div>


</div>

</div>
</div>

<br />
<br />



    
</body>
</html>

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
		//查看答案与取消答案
		function onLook(_i,_statu){
			$.ajax({
			url:'goods/lookSolu.action?queId='+_i,
			type:'post',
			dataType:'json',
			success:function(data){
				if(data!=null && data.length>0){
					if(_statu=="look"){
						$.each(data,function(i,ele){
							var divId="#divType"+ele.typeId;
							if(ele.typeId==radioId || ele.typeId==checkId){
								$(divId+" input[class!='{topicId}']").next().attr("style","color:red;");
							}else{
								$(divId+" div[name='soluText']").removeAttr("style");
							}
						});
				}else if(_statu=="unLook"){
					$.each(data,function(i,ele){
							var divId="#divType"+ele.typeId;
							if(ele.typeId==radioId || ele.typeId==checkId){
								$(divId+" input[class!='{topicId}']").next().removeAttr("style");
							}else{
								$(divId+" div[name='soluText']").attr("style","display:none;");
							}
						});
				}
				}
			},
			error:function(request,msg,e){
					alert('错误 ：'+request.responseText);
				}
			});
		}
		function onScore(_i){
			//计算选择题分数
			$.ajax({
				url:'goods/lookSolu.action?queId='+_i,
				type:'post',
				data:1,
				dataType:'json',
				success:function(data){	
					if(data!=null && data.length>0){
						var score=0;
						$.each(data,function(i,ele){
							var divId="#divType"+ele.typeId;
							if(radioId!=0&&ele.typeId==radioId){
								var radio=$(divId+" input[type='radio']");
									for(var i=0;i<radio.length;i++){
										if(radio.eq(i).val()*1>0){
												radio.eq(i).next().attr("style","color:green");
											}else{
												radio.eq(i).next().removeAttr("style");
											}
										if(radio.eq(i).attr("checked")=="checked"){
											score=score+radio.eq(i).val()*1;
											if(radio.eq(i).val()*1<=0){
												radio.eq(i).next().attr("style","color:red");
											}
										}
									}
							}else if(checkId!=0&&ele.typeId==checkId){
								var span=$(divId+">span[name='checkbox']");
									//每道多选题
									for(var i=0;i<span.length;i++){
										var check=span.eq(i).children("input[type='checkbox']");
										//每道题的选项
										var oneScore=0;
										var twoScore=0;
										var threeScore=0;
										var fourScore=1;
										for(var j=0;j<check.length;j++){
											if(check.eq(j).val()*1>0){
												check.eq(j).next().attr("style","color:green");
											}else{
												check.eq(j).next().removeAttr("style");
											}
											oneScore=oneScore+check.eq(j).val()*1;
											if(check.eq(j).attr("checked")=="checked"){
												twoScore=twoScore+check.eq(j).val()*1;
												threeScore=check.eq(j).val()*1;
												fourScore=fourScore*check.eq(j).val()*1;
												if(check.eq(j).val()*1<=0){
													check.eq(j).next().attr("style","color:red");
												}
											}
											
										}
										if(twoScore==oneScore && fourScore>0){
											score=score+threeScore;
										}
									}
									
								}
						});
						$("#logoright").html('选择题得分：<span>'+score+'</span>');
					}
				},
				error:function(request,msg,e){
					alert('错误 ：'+request.responseText);
				}
			});
		}
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
								topHtml.push('<span id="radio'+ele.topic.topicId+'" name="radio"><span>');
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
								topHtml.push('<span id="check'+ele.topic.topicId+'" name="checkbox"><span>');
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
								topHtml.push('<input type="button" value="做题" class="button"/>');
									topHtml.push('<br/>');
									var _wr=$("#write").html();
									topHtml.push(_wr);
									topHtml.push('<span id="check'+ele.topic.topicId+'" name="textarea"><span>');
									$("#divType"+ele.topic.typeId).append(topHtml);
									topHtml=[];
									if(ele.solut!=null && ele.solut.length>0){
										topHtml.push('<div style="display:none;" name="soluText"><textarea rows="3" cols="20" style="color:red;">'+ele.solut[0].solution+'</textarea></div>');
									}else{
										topHtml.push('<div style="display:none;" name="soluText"><textarea rows="3" cols="20" style="color:red;">该题暂无答案</textarea></div>');
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
						}else{
							_op=_op.replace(/{optionsValue}/g,0);
						}
					}else{
						if(_solu[0].solution==_opt.options){
							_op=_op.replace(/{topicId}/g,_solu[0].topicId);
							_op=_op.replace(/{optionsValue}/g,_score);
							}else{
								_op=_op.replace(/{optionsValue}/g,0);
							}
						}
					}
					return _op;
				}
				//下载部分
				function onDownload(_paperName){
					location.href='goods/down.action?fileName='+_paperName;
				}
				//撰写部分
				function onWrite(){
					$.ajax({
						url:'goods/download.action?queId='+id+'&fileName='+id,
						type:'post',
						dataType:'json',
						success:function(data){
							 if(data!=null && data.paperName!=null){
								onDownload(data.paperName);
							}else{
								alert("下载出错！");
							} 
						}
					});
				}
		$(function(){
			onOptions(id);
			$("#look").click(function(){
				if($(this).text()=="查看答案"){
					$(this).text("取消查看");
					onLook(id,"look");
				}else{
					$(this).text("查看答案");
					onLook(id,"unLook");
				}
			});
			$("#score").click(function(){
				onScore(id);
			});
			
			$("#paper").click(function(){
				history.go(-1);//返回当前页的上一页，不过表单里的数据全部还在
				//history.back(-1);//直接返回当前页的上一页，数据全部消息，是个新页面
			});
			$(".button").live("click",function(){
				if($(this).attr("value")=="做题"){
					$(this).attr("value","完成");
					$(this).next().next().children().removeAttr("style");
				}else{
					$(this).attr("value","做题");
					$(this).next().next().children().attr("style","display:none;");
				}
			});
			$("#topic span[name='topicName']").live('dblclick',function(){
				if($(this).nextAll().length==2){
					var type=$(this).nextAll().eq(1).attr("name");
					var children=$(this).nextAll().eq(1).children("input[type='"+type+"']");
					var length=children.length;
					for(var i=0;i<length;i++){
						if(children.eq(i).attr("value")>0){
							if(children.eq(i).next().attr("style")==null){
								children.eq(i).next().attr("style","color:red");
							}else{
								children.eq(i).next().removeAttr("style");
							}
						}
					}
				}else if($(this).nextAll().length==4){
					var children=$(this).nextAll().eq(3).children();
					if(children.attr("style")!=null){
						children.removeAttr("style");
					}else{
						children.attr("style","display:none;");
					}
				}
			});
			$("#download").click(function(){
				$.ajax({
					url:'goods/downpaper.action?queId='+id,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data!=null && data.paperName!=null){
							onDownload(data.paperName);
						}else{
							onWrite();
						}
					},
					error:function(request,msg,e){
						alert('错误 ：'+request.responseText);
					}
				});
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
      <!-- menu start -->
      <ul id="nav">
	<li class="current"><a href="javascript:void(0);" id="look">查看答案</a></li>
    
	<li><a href="javascript:void(0);" id="score">分数计算（仅选择题）</a></li>
    
	 <li><a href="javascript:void(0);"  id="paper">返回题库</a></li>
     
    <li><a href="page/homepage.action" id="homePage">返回首页</a></li>
    
     <li><a href="javascript:void(0)" id="download">下载题卷</a></li>
     
    
	<li><a href="javascript:void(0);">6</a>
    <ul>
			<li><a href="javascript:void(0);" ></a></li>
			<li><a href="javascript:void(0);" ></a>
				</ul>
    </li>
    
  <li><a href="javascript:void(0);">7</a>
		<ul>
			<li><a href="javascript:void(0);" ></a></li>
			<li><a href="javascript:void(0);"></a></li>					
		</ul>
	</li>
</ul>
<br>
<br>
       <!-- menu end -->

  <div id="content">

<div id="topic"> 
  </div>
  

<!-- 题目的模板 -->
  <div id="div" style="display: none">
  		<span name="topicName">{topicName}</span>
  </div>
  <!-- 选择题选项的模板 -->
  <!-- 单选 -->
  <div style="display: none" id="radio">
	  <input type="radio"  value="{optionsValue}" name="{options}" class="{topicId}"/>{optionIndex}<span >{optionsName}</span><br>
  </div>
  
  <!-- 多选 -->
  <div style="display: none" id="check">
  	<input type="checkbox"  value="{optionsValue}" name="{options}" class="{topicId}"/>{optionIndex}<span>{optionsName}</span><br>
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

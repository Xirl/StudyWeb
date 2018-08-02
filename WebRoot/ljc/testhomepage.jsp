<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>学习网</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../ljc/css/homepage.css" rel="stylesheet" type="text/css"/>
<link href="../ljc/css/lanyon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../ljc/js/cufon-yui.js"></script>
<script type="text/javascript" src="../ljc/js/arial.js"></script>
<script type="text/javascript" src="../ljc/js/cuf_run.js"></script>
<script type="text/javascript" src="../ljc/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	$(function(){
	if(${sessionScope.currUser==null}){
		$(".loginsuccess").hide();
		$(".toplogin").show();
	}else{
		$(".loginsuccess").show();
		$(".toplogin").hide();
	}	
		//显示课程细节
		$(".data > a").live('click',function(){
			 $.ajax({
						url:'findTitle.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'dataid='+$(this).attr('class'),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'json',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							if(data!=null){
							$(".article").empty();
							$(".vedio").empty();
							$.each( data, function(i,item){
								 $(".article").append(
									"<a class="+item.detialid+"  href='javascript:;'>" + item.title + "</a><br>"
								);
							});
							}else{
								alert("找不到1");
							}
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
					});
		});
		//显示视频
		$(".article > a").live('click',function(){
			 $.ajax({
						url:'findvedio.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'id='+$(this).attr('class'),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'json',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							if(data!=null){
							$(".vedio").empty();
							$.each( data, function(i,item){
								 $(".vedio").append(
									"<video width='700' height='500' controls>	<source src=../ljc/vedio/"+item.filename+" type='video/mp4'></video>"
								);
								$(".vedio").append(
									/* "<a class="+item.filename+" href='javascript:;'>视频下载</a>" */
									//DownLoad/downvedio.action?fileName="+item.filename+"
									"<a class="+item.filename+" href='javascript:;'>视频下载</a>"
								);
							});
							}else{
								alert("找不到1");
							}
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
					});
		});
			 //下载视频
			  $(".vedio > a").live('click',function(){
			 	if(${sessionScope.currUser.name!=null && ""!=sessionScope.currUser.name}){
			 		location.href="../DownLoad/downvedio.action?fileName="+$(this).attr('class');
			 	}else{
			 		alert("需要登录才能下载");
			 		location.href="../ljc/login.jsp";
			 	}
			}); 
				//获取科目名
				$.ajax({
						url:'getDataTypeName.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						//data:'dataName='+$(this).html(),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'json',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							if(data.datea!=null){
								$.each( data.datea, function(i,item){
								    $("#homepage").append(
								    	//class='+top+' href='+course.jsp+'
								    	"<li><a class=top href='javascript:;'>"+item.name+"</a></li>"
								    );
								    /* course(item.name);
								    $(item.name).hide(); */
						    });  
							}else{
								alert("找不到2");
							}
							//插入代码
							if(data.page!=null&&data.page.list.length>0){
								$.each(data.page.list,function(i,ele){
									$(".sidebars-nav").append('<a href="../goods/questionPaper.action?poolId='+ele.detailsId+'" class="sidebars-nav-item current">'+ele.poolName+'</a>');
								});  
							}
							
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
					});
			//当鼠标移到科目是得时候，课程显示
			$(".top").live('mouseover',function(){
				$.ajax({
						url:'select.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'dataName='+$(this).html(),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'json',//设置服务器返回的数据类型，默认是text,
						success:function(data){
						if(data!=null && data!="没有数据"){
							$(".data").empty();
								$.each( data, function(i,item){
							        $(".data").append(
							        	"<a class="+item.dataid+" href='javascript:;'>" + item.dataname + "</a>&nbsp"
							        );
						    	});
							}
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
					});
				});
			$("#login").click(function(){
				if($("#loginName").val()==null || ""==$("#loginName").val() ){
					alert("用户名不能为空");
					$("#loginName").css("border","2");
					$("#loginName").css("borderColor","red");
				}else if($("#password").val()==null || ""==$("#password").val()){
					alert("密码不能为空");
					$("#password").css("borderColor","red");
				}else{
					$.ajax({
						url:'../User/login.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'loginName='+$("#loginName").val()+'&pwd='+$("#password").val()+'&name='+$("#Username").val(),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'json',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							if(data.name!=null && ""!=data.name ){
								alert("登陆成功");
								$(".currUserName").html("欢迎用户:"+data.name);
								$(".loginsuccess").show();
								$(".toplogin").hide();
								location.href="../Data/homepage.action";
								}else{
									alert("登录失败："+data);
									$("#loginName").val("");
									$("#password").val("");
									$("#loginName").css("borderColor","red");
									$("#password").css("borderColor","red");
								}
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
					});
				}
			});
			$("#register").click(function(){
				window.open("../ljc/register.jsp", "注册",'height=500, width=520, top=150, left=450, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
			});
			$("#cancel").click(function(){
				
				$.ajax({
						url:'../User/clear.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'dataName='+$(this).html(),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'json',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							location.href="../Data/homepage.action";
						}
					});
				$(".toplogin").show();
				$(".loginsuccess").hide();
				$(" #loginName").val("");
				$("#password").val("");
			});
		});	
	</script>
<!-- CuFon ends -->
</head>
<body>
	<div class="toplogin">
   		 用户名<input id="loginName"> 密码<input type="password" id="password"> 
   		 	<input name="login" id="login" class="login"  value="登录" type="button" />
           	<input name="register" id="register" class="register" type="button" value="注册" />
        </div>
         <label for="sidebars-checkbox" class="sidebars-toggle"></label>
           <input type="checkbox" id="sidebars-checkbox" class="sidebars-checkbox"/>
      <div id="sidebars" class="sidebars">
        <div class="sidebars-nav">
       <c:if test="${sessionScope.currUser.userId!=null}">
       	<a href="../goods/questionPaper.action?statu=mySelf&userId=${sessionScope.currUser.userId}" class="sidebars-nav-item current">个人题库</a>
       </c:if>
      </div>
           	 
        </div>
   <div class="loginsuccess">
   	<c:if test="${sessionScope.currUser.name!=null }">
   		<lable class="currUserName">欢迎用户：${sessionScope.currUser.name }</lable><a href="../ljc/personalinfo.jsp">个人主页</a> &nbsp;<a href="#">修改密码</a>&nbsp; <a id="cancel" href="javascript:;" onclick="return cancel();" >注销</a>
   	</c:if>
   		<c:if test="${sessionScope.currUser.name==null }">
   		<lable class="currUserName">欢迎用户：{name}</lable><a href="../ljc/personalinfo.jsp">个人主页</a> &nbsp;<a href="#">修改密码</a>&nbsp; <a id="cancel" href="javascript:;" onclick="return cancel();" >注销</a>
   	</c:if>
    </div>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><h1><a href="index.html"><span>Name</span> Company <small>Put your best slogan here</small></a></h1></div>
      <div class="clr"></div>
      <div class="menu_nav">
        <ul id="homepage">
          <li ><a class="top" href="../Data/homepage.action">首页</a></li>
          <!-- <li><a class="top" href="course.jsp">Java</a></li>
          <li><a class="top" href="about.html">C</a></li>
          <li><a class="top" href="contact.html">Python</a></li> -->
          <li><a class="top" href="../sx/main.jsp">论坛</a></li>
        </ul>
        <div class="searchform">
            <span class="XXX"> 
           		
           </span>
        </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="data">
       		
        </div>
        <div class="article" >
          
        </div>
        <div class="vedio" >
          
        </div>
      </div>
      <div class="sidebar">
        <div class="gadget">
          <h2 class="star"><span>Sidebar</span> Menu</h2><div class="clr"></div>
          <ul class="sb_menu">
            <li><a href="javascript:;">首页</a></li>
            <li><a href="javascript:;">Java</a></li>
            <li><a href="javascript:;">C</a></li>
            <li><a href="javascript:;">Python</a></li>
            <li><a href="javascript:;">论坛</a></li>
            <li><a href="javascript:;" title="Website Templates">Web Templates</a></li>
          </ul>
        </div>
        <div class="gadget">
          <h2 class="star"><span>Sponsors</span></h2><div class="clr"></div>
          <ul class="ex_menu">
            <li><a href="#" title="Website Templates">DreamTemplate</a><br />Over 6,000+ Premium Web Templates</li>
            <li><a href="#">TemplateSOLD</a><br />Premium WordPress &amp; Joomla Themes</li>
            <li><a href="#" title="Affordable Hosting">ImHosted.com</a><br />Affordable Web Hosting Provider</li>
            <li><a href="#" title="Stock Icons">MyVectorStore</a><br />Royalty Free Stock Icons</li>
            <li><a href="#" title="Website Builder">Evrsoft</a><br />Website Builder Software &amp; Tools</li>
            <li><a href="#" title="CSS Templates">CSS Hub</a><br />Premium CSS Templates</li>
          </ul>
        </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>

  <div class="fbg">
    <div class="fbg_resize">
      <div class="col c1">
        <h2><span>About</span></h2>
        <img src="../ljc/images/white.jpg" width="56" height="56" alt="pix" />
        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo. llorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum. <a href="#">Learn more...</a></p>
      </div>
      <div class="col c2">
        <h2><span>Lorem Ipsum</span></h2>
        <ul class="sb_menu">
          <li><a href="#">consequat molestie</a></li>
          <li><a href="#">sem justo</a></li>
          <li><a href="#">semper</a></li>
          <li><a href="#">magna sed purus</a></li>
          <li><a href="#">tincidunt</a></li>
        </ul>
      </div>
      <div class="col c3">
        <h2>Contact</h2>
        <p>Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue.</p>
        <p><a href="mailto:support@yoursite.com">support@yoursite.com</a></p>
        <p>+1 (123) 444-5677<br />+1 (123) 444-5678</p>
        <p>Address: 123 TemplateAccess Rd1</p>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="footer">
    <div class="footer_resize">
      <p class="lf">© Copyright <a href="#">MyWebSite</a>. Collect from: <a href="http://www.cssmoban.com/" title="网站模板" target="_blank">网站模板</a></p>
      <ul class="fmenu">
        <li class="active"><a href="index.html">Home</a></li>
        <li><a href="support.html">Support</a></li>
        <li><a href="blog.html">Blog</a></li>
        <li><a href="about.html">About Us</a></li>
        <li><a href="contact.html">Contacts</a></li>
      </ul>
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>

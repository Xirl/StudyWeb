<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script src="/StudyWeb/jquery_validate/jquery.form.min.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate.min.1.11.1.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate_zh_cn.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			$(".pwdupdate").hide();
			$(".tips").hide();
			$(".updatepwd").click(function(){
				$(".pwdupdate").toggle();
				$(".data").toggle();
			});
			/* $(".back").click(function(){
				$(".pwdupdate").toggle();
				$(".data").toggle();
			}); */
			$(".oldpwd").focus(function(){
				$(".tips").hide();
			});
				$("#update").ajaxForm({
					url:'../User/updatepassword.action',//form提交数据的地址
					dataType:'json',
					beforeSubmit:function(){//在提交前的回调函数
						if($(".oldpwd").val()!=null && ""!=$(".oldpwd").val()){
							if($(".oldpwd").val()!=${sessionScope.currUser.password} ){
								$(".tips").show();
								return false;
							}
							if($(".password").val()!=null && ""!=$(".password").val()){
								if($(".password").val()==$(".oldpwd").val()){
									alert("新密码不能与旧密码相同");
									return false;
								}
							}
						}
						return onValidate2();
					},
					success:function(data){//执行成功的回调函数
						if(data>0!=null){
							alert("修改成功");
							location.href="../ljc/personalinfo.jsp";
						}else{
							alert("修改失败："+data);
						}
					}
				});
			});
		function onValidate2(){
			var validate=$("#update").validate({
				rules:{//验证规则
					oldpwd:{
						required:true,
					},
					password:{
						required:true,
						equalTo: ".surepwd"
					},
					suerpwd:{
						required:true,
						equalTo: ".password"
					}
				},
				messages:{//对应的提示消息
					oldpwd:{
						required:"<font color='red'>旧密码不能为空</font>",
					},
					password:{
						required:"<font color='red'>新密码不能为空</font>",
						equalTo:"<font color='red'>确认密码与新密码不一致</font>",
					},
					suerpwd:{
						required:"<font color='red'>确认密码不能为空</font>",
						equalTo:"<font color='red'>确认密码与新密码不一致</font>"
					}
				}
			});
			return validate.form();//如果true就验证成功，false验证失败
		}
	</script>
	
	<title>${sessionScope.currUser.name }</title>
	<!-- Bootstrap -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.no-icons.min.css" rel="stylesheet">
	<!-- Icons -->
	<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
	<!-- Fonts -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Alice|Open+Sans:400,300,700">
	<!-- Custom styles -->
	<link rel="stylesheet" href= "css/styles.css">
	
</head>
<body class="home">

<header id="header">
	<div id="head" class="parallax" parallax-speed="2">
		<h1 id="logo" class="text-center">
			<img class="img-circle" src="images/${sessionScope.currUser.head }" alt="">
			<span class="title">${sessionScope.currUser.name }</span>
			<span class="tagline">${sessionScope.currUser.signature }<br>
				<a href="">anthony.russel42@example.com</a></span>
		</h1>
	</div>

	<nav class="navbar navbar-default navbar-sticky">
		<div class="container-fluid">
			
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
			</div>
			
			<div class="navbar-collapse collapse">
				
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.html">Home</a></li>
					<li><a href="about.html">About</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">More Pages <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="sidebar-left.html">Left Sidebar</a></li>
							<li><a href="sidebar-right.html">Right Sidebar</a></li>
							<li><a href="single.html">Blog Post</a></li>
						</ul>
					</li>
					<li><a href="blog.html">Blog</a></li>
				</ul>
			
			</div><!--/.nav-collapse -->			
		</div>	
	</nav>
</header>

<main id="main">

	<div class="container">
		<div class="row section topspace">
			<div class="col-md-12">
				<table class="data" border="0" align="center" width="600">
				<tr>
					<td>
						<p>会员类型:${sessionScope.currUser.usertype}</p>
						<p>注册时间:${sessionScope.currUser.regtime}</p>
					</td>
					<td class="tx03">
						<ul style="list-style: none">
							<li><a class="updatepwd" href="javascript:;"><span>修改密码</span></a></li>
							<li><a href="#"><span>信箱</span></a></li>
							<li><a href="../Data/homepage.action"><span>返回首页</span></a></li>
						</ul>
					</td>
				</tr>
				</table>
		<div class="pwdupdate" align="center">
			<form id="update" action="javascript:;" method="post">
				<table border="10">
					<tr>
						<td>
							原密码：<input type="password" name="oldpwd" class="oldpwd"><font class="tips" color='red'>旧密码不正确</font><br>
						</td>
					</tr>
					<tr>
						<td>
							新密码：<input type="password" name="password" class="password"><br>
						</td>
					</tr>
					<tr>
						<td>
							确认新密码：<input type="password" name="suerpwd" class="surepwd"><br>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="submit" class="submit" value="修改">
						</td>
					</tr>
					<tr>
						<td align="center">
							<a  class="back" href="personalinfo.jsp">返回</a>
						</td>
					</tr>
				</table>
					</form>
				</div>
			</div>
		</div> <!-- / section -->
	
		<div class="row section featured topspace">
			<h2 class="section-title"><span>Services</span></h2>
			<div class="row" id=row align="center">
				<div class="col-sm-6 col-md-3" style="background-color:#505C72">
					<h3 class="text-center">我的分享</h3>
					<p align="center"><a href="../goods/topicType.action?ran=ran" >随机生产试卷</a></p>
					<p class="text-center"><a href="../goods/topicType.action" class="btn btn-action">设计试卷</a></p>
				</div>
				<div class="col-sm-6 col-md-3"  style="background-color:#77C2AE">
					<h3 class="text-center">我的论坛</h3>
					<p align="center">帮助小白解决问题，看大神如何解决问题</p>
					<p class="text-center"><a href="../sx/main.jsp" class="btn btn-action">马上进入论坛</a></p>
				</div>
				<div class="col-sm-6 col-md-3" style="background-color:#77B3D4">
					<h3 class="text-center">我的提问</h3>
					<p align="center">进入论坛，发表不懂的问题，让大神来回答</p>
					<p class="text-center"><a href="../sx/news.jsp?userid=${sessionScope.currUser.userId}" class="btn btn-action">马上进入提问</a></p>
				</div>
				<div class="col-sm-6 col-md-3" style="background-color:#194366">
					<h3 class="text-center">更改信息</h3>
					<p align="center">完善个人信息，让其他人更了解你</p>
					<p class="text-center"><a href="updateperson.jsp" class="btn btn-action">马上编辑信息</a></p>
				</div>
			</div>
		</div> <!-- / section --><div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >免费网站模板</a></div>

	</div>	<!-- /container -->

</main>



<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="js/template.js"></script>
</body>
</html>

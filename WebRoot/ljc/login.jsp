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
	
	<script type="text/javascript" src="../ljc/js/jquery-1.8.3.js"></script>
	<script src="/StudyWeb/jquery_validate/jquery.form.min.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate.min.1.11.1.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate_zh_cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$("#register").click(function(){
			window.open("register.jsp", "注册",'height=500, width=520, top=150, left=450, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
		});
		
		$(".tips").hide();
		$("#loginName").focus(function(){
			$(".tips").hide();
		});
		//使用jquery.form.js插件实现ajax的form表单提交
		$("#loginForm").ajaxForm({
			url:'../User/login.action',//form提交数据的地址
			dataType:'json',
			beforeSubmit:function(){//在提交前的回调函数
				//return onValidate1();
			/* alert(onValidate2()); */
			     var myReg = /^[a-zA-Z0-9_]{0,}$/;  
			    if (!myReg.test($("#loginName").val())) { 
			        /* $.validation.tip(false, input, "用户名不能含有中文或特殊字符");  */ 
			        $(".tips").show();
			        return false;
			    }  
				return onValidate2();
			},
			success:function(data){//执行成功的回调函数
				if(data.name!=null){
					alert("登录成功");
					location.href="../ljc/personalinfo.jsp";
				}else{
					alert("登录失败："+data);
				}
			}
		});
	});
	function onValidate2(){
		var validate=$("#loginForm").validate({
			rules:{//验证规则
				loginName:{
					required:true
				},
				pwd:{
					required:true,
					rangelength:[6,12]
				}
			},
			messages:{//对应的提示消息
				loginName:{
					required:"<font color='red'>用户名不能为空</font>",
				},
				pwd:{
					required:"<font color='red'>密码不能为空</font>",
					rangelength:"<font color='red'>密码的长度必须是{0}到{1}之间</font>"
				}
			}
		});
		return validate.form();//如果true就验证成功，false验证失败
	}
	</script>
	<title>登录</title>
	<!-- Bootstrap -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.no-icons.min.css" rel="stylesheet">
	<!-- Icons -->
	<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
	<!-- Fonts -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Alice|Open+Sans:400,300,700">
	<!-- Custom styles -->
	<link rel="stylesheet" href="../ljc/css/styles.css">
	
	<style type="text/css">

 input {
   border-style:solid;  
   border: 1px solid black;
}
 input:focus{  
        border-style:solid;  
        border-color: #03a9f4;  
        box-shadow: 0 0 15px #03a9f4;  
}  
 
.speech-bubble-left:after {
  border-right-color: #292929;
  top: 50%;
  right: 100%;
  margin-top: -15px;   
}
	</style>
  
</head>
<body class="home">

<header id="header">
	<div id="head" class="parallax" parallax-speed="2">
		<h1 id="logo" class="text-center">
			<img class="img-circle" src="../ljc/images/${sessionScope.currUser.head }" alt="">
			<span class="title">${sessionScope.currUser.name }</span>
			<span class="tagline">A mystery person<br>
				<a href="">anthony.russel42@example.com</a></span>
		</h1>
	</div>

	<nav class="navbar navbar-default navbar-sticky">
		<div class="container-fluid">
			
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
			</div>
			
			<div class="navbar-collapse collapse" align="center">
				<h2>登录</h2>
			
			</div><!--/.nav-collapse -->			
		</div>	
	</nav>
</header>

<main id="main">

	<div class="container">
		
		<div class="row section topspace">
			<div class="col-md-12" align="center">
			<form action="javascript:void(0)" id="loginForm" method="post">
				用户名：<input  id="loginName" name="loginName"><font class="tips" color='red'>用户名不能为中文或特殊字符</font>
				<br>
				密&nbsp;&nbsp;码：<input type="password" name="pwd" id="password" >
				<br>
				<br>
				<input type="submit" class="asd"  value="登录"><br> 
				没有账号？<a href="javascript:void(0);" id="register">点我注册</a><br>
			</form>
			<a href="../Data/homepage.action">返回首页</a>
			</div>
		</div> <!-- / section -->
		
		<div class="row section featured topspace">
			<h2 class="section-title"><span>Services</span></h2>
			<div class="row" id=row align="center">
			
			</div>
		</div> <!-- / section --><div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >免费网站模板</a></div>

	</div>	<!-- /container -->

</main>



<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="../ljc/js/template.js"></script>
</body>
</html>

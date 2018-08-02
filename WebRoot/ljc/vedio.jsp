<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${name }</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/homepage.css" rel="stylesheet" type="text/css" />

<!-- <script type="text/javascript" src="js/cuf_run.js"></script> -->
<!-- <script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script> -->
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	if(${sessionScope.currUser==null}){
		$(".loginsuccess").hide();
		$(".toplogin").show();
	}
	$("#cancel").click(function(){
				$.ajax({
						url:'User/clear.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'dataName='+$(this).html(),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'json',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							alert("注销成功");
						}
					});
				$(".toplogin").show();
				$(".loginsuccess").hide();
				$(" #loginName").val("");
				$("#password").val("");
			});
	</script>
<!-- CuFon ends -->
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
    <div class="toplogin">
   		 用户名<input id="loginName"> 密码<input type="password" id="password"> 
   		 	<input name="login" id="login" class="login"  value="登录" type="button" />
           	<input name="register" id="register" class="register" type="button" value="注册" />
        </div>
   <div class="loginsuccess">
   		欢迎用户:${sessionScope.currUser.name }<a href="personalinfo.jsp">个人主页</a> &nbsp;<a href="#">修改密码</a>&nbsp; <a id="cancel" href="javascript:;" onclick="return cancel();" >注销</a>
    </div>
      <div class="logo"><h1><a href="index.html"><span>Name</span> Company <small>Put your best slogan here</small></a></h1></div>
      <div class="clr"></div>
      <div class="menu_nav">
        <ul >
          <li ><a class="top" href="testhomepage.jsp">首页</a></li>
          <li><a class="top" href="blog.html">论坛</a></li>
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
          <h2><span>${filename }</span></h2><div class="clr"></div>
        
          <!-- <embed src="vedio/1.mp4" width="900" height="700" autoplay="false" autostart="false" > -->
          <video width="900" height="700" controls>
  			<source src="vedio/1.mp4" type="video/mp4">
		  </video>
        </div>
      </div>
      <div class="sidebar">
        <div class="gadget">
          <h2 class="star"><span></span></h2><div class="clr"></div>
         
        </div>
        <div class="gadget">
          <h2 class="star"><span></span></h2><div class="clr"></div>
          <ul class="ex_menu">
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
        <img src="../jsp/ljc/images/white.jpg" width="56" height="56" alt="pix" />
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

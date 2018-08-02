<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'homepage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="jq/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
					url:'Data/setdata.action',//请求的url
					type:'post',//设置请求方式（post/get)，默认是get
					data:'id=${sessionScope.currUser}',//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
					dataType:'json',//设置服务器返回的数据类型，默认是text,
					success:function(data){
						if(data!=null){
						$.each( data, function(i,item){ 
						      $(".data").append(
					        	"<a class="+_name+" href=/StudyWeb/Data/findData.action?id="+item.dataid+"&name="+item.dataname+">" + item.dataname + "</a>&nbsp"
						       );
						    }); 
						    $("."+_name+"").hide(); 
							}else{
								alert("找不到1");
							}
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
			});
		});
	</script>
	<style type="text/css" >
		#navbar .subject {
	    line-height: 35px;
	    background: url(navbar.gif) repeat-x;
	    text-align: center;
		}
		#navbar a:hover {
    	color: red;
    	text-decoration: none;
    	background: #EAF3FC;
}
	</style>
  </head>
  
  <body>
	  <div id="user" >欢迎用户:${SessionScope.currUser.name } <a href="#">修改密码</a> <a href="#">个人信息</a> <a href="#">退出登录</a></div>
	 
     	会员名<input id="loginName" name="loginName"/>密码<input id="password" type="password" name="password"> <input id="login" type="button" value="登录"> <input type="button" onclick="Register()" id="Register"  value="注册">
    <div class="subject">
    	<a href="default.aspx" class="first">首页</a>
    	<a href="list.aspx?cid=3" onmouseover="showmenu(event,3,1,false)" onmouseout="delayhidemenu()">Java</a>&nbps;&nbps;
    	<a href="list.aspx?cid=8" onmouseover="showmenu(event,8,1,false)" onmouseout="delayhidemenu()">C语言</a>
    </div>
    <div class="course">
    	
    </div>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="ljc/css/base.css">
    <link rel="stylesheet" href="ljc/css/iconfont.css">
    <link rel="stylesheet" href="ljc/css/reg.css">
    
	<script type="text/javascript" src="ljc/js/jquery-1.8.3.js"></script>
	<script src="/StudyWeb/jquery_validate/jquery.form.min.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate.min.1.11.1.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate_zh_cn.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
	/* 		$("#loginName").append('<img alt="对" src="" id="true">');
			$("#loginName").append('<img alt="错" src="" id="false">'); */
		$("#loginName").blur(function(){
			var myReg = /^[a-zA-Z0-9_]{0,}$/;  
			if(!myReg.test($("#loginName").val())){
				alert("用户名不能含有中文或特殊字符");
			    clear();
			}else {
			/* 	$("#true").hide();
			$("#false").hide(); */
			
				$.ajax({
						async:true,//设置同步(false)或异步(true)，默认是true
						url:'User/selectloginName.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'loginName='+$("#loginName").val(),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'text',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							if( $("#loginName").val()!=""){
								if(data<1 ){
								//alert("验证成功");
								//$("#loginNameMsg").html("验证成功");
								//<label id="loginNameMsg"></label>
								alert("该用户名可以使用！");
								$("#false").hide();
								$("#true").show();
								}else{
									alert("此用户名已被注册,请重新输入");
									$("#true").hide();
									$("#false").show();
									$("#loginName").val("");
									$("#loginName").focus();
									clear();
								}
							}
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
					});
				
			}
		});
			
			 $("#submit").click(function(){
			if($("#loginName").val()==null || ""==$("#loginName").val() ){
				alert("用户名不能为空");
				clear();
			}else if($("#password").val()==null || ""==$("#password").val() ){
				alert("密码不能为空");
				clear();
				$("#password").focus();
			}else if($("#surepassword").val()==null || ""==$("#surepassword").val() ){
				alert("确认密码不能为空");
				clear();
				$("#password").focus();
			}else if($("#password").val()!=$("#surepassword").val() ){
				alert("确认密码与密码不相同");
				clear();
				$("#password").focus();
			}
			else if($("#Username").val()==null || ""==$("#Username").val() ){
				alert("名称不能为空");
				clear();
				$("#Username").focus();
			}else if($("#password").val().length<6 || $("#password").val().length>12 ){
				alert("密码长度必须是6到12之间");
				clear();
			}
			else{
				$.ajax({
					async:true,//设置同步(false)或异步(true)，默认是true
						url:'User/userInsert.action',//请求的url
						type:'post',//设置请求方式（post/get)，默认是get
						data:'loginName='+$("#loginName").val()+'&password='+$("#password").val()+'&name='+$("#Username").val(),//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
						dataType:'text',//设置服务器返回的数据类型，默认是text,
						success:function(data){
							if( data>0){
								if(data>0 ){
								//alert("验证成功");
								//$("#loginNameMsg").html("验证成功");
								//<label id="loginNameMsg"></label>
								alert("注册成功");
								closewin();
								}else{
									alert("注册失败");
									$("#true").hide();
									$("#false").show();
									$("#loginName").val("");
								}
							}
						},
						error:function(request,msg,e){
							alert('错误 ：'+request.responseText);
						}
					});
				}
			}); 
			function clear(){
				$("#password").val("");
				$("#surepassword").val("")
			}
			function closewin(){
				window.opener=null;
			window.open('','_self');
			window.close();
			}
			/*
				$("#registerForm").ajaxForm({
					url:'User/userInsert.action',//form提交数据的地址
					dataType:'json',
					beforeSubmit:function(){//在提交前的回调函数
						//return onValidate1();
						alert("12344444");
						return onValidate2();
					},
					success:function(data){//执行成功的回调函数
						if(data.rows>0){
							alert("注册成功");
							location.href="#";
						}else{
							alert("注册失败："+data.errMsg);
						}
					},
					error:function(){
						alert("报错");
					}
				});
				*/
		});
		/* 
		function onValidate2(){
				var validate=$("#registerForm").validate({
					rules:{//验证规则
						loginName:{
							required:true
						},
						pwd:{
							required:true,
							rangelength:[6,12]
						},
						surepassowrd:pwd
						
					},
					messages:{//对应的提示消息
						loginName:{
							required:'<font color='red'>姓名不能为空</font>',
						},
						pwd:{
							required:'<font color='red'>密码不能为空</font>',
							rangelength:'密码的长度必须是{0}到{1}之间'
						},
						surepassowrd:{
							equalTo:'<font color='red'>确认密码与密码不一致</font>'
						}
					}
				});
				return validate.form();//如果true就验证成功，false验证失败
			}   */
	</script>
	
  </head>
  
  <body>
   <!-- 		 注册  
   <form action="User/userInsert.action" method="post">
   		<div>用户名：<input name="loginName" id="loginName"></div>
   		密码：<input name="password" id="password" ><br>
   		确认密码：<input type="password" name="surepassword" id="surepassword"><label id=1>确认密码与密码不一致</label><br>
   		名称：<input name="name" id="name"><br>
   		 性别：<input type="radio" name="sex" value="男" class="sex" >男
   			<input type="radio" name="sex" value="女" class="sex">女<br>
   		<input id="register" type="submit" id="register" value="注册">
   </form> -->
    <div id="ajax-hook"></div>
    <div class="wrap">
        <div class="wpn">
            <div class="form-data pos">
            		
                <a href=""><img src="ljc/images/logo.png" class="head-logo"></a>
                <!--<p class="tel-warn hide"><i class="icon-warn"></i></p>-->
                <form id="registerForm" action="javascript:void(0)" method="post">
                    <p class="p-input pos">
                        <input id="loginName" name="loginName" autocomplete="off" autofocus="autofocus" placeholder="请输入您的用户名">
                        <span class="tel-warn tel-err hide"><em></em><i class="icon-warn"></i></span>
                    </p>
                    <p class="p-input pos" id="pwd">
                        <!-- <input type="password" style="display: none"/> -->
                        <input type="password" name="pwd" id="password" autocomplete="off" placeholder="请输入密码">
                        <span class="tel-warn pwd-err hide"><em></em><i class="icon-warn" style="margin-left: 5px"></i></span>
                    </p>
                    <p class="p-input pos" id="confirmpwd">
                        <!-- <input type="password" style="position:absolute;top:-998px"/> -->
                        <input type="password" name="surepassword" id="surepassword" autocomplete="off" placeholder="请输入确认密码">
                        <span class="tel-warn confirmpwd-err hide"><em></em><i class="icon-warn" style="margin-left: 5px"></i></span>
                        </p>
                        <p class="p-input pos" id="name">
                        <!-- <input type="password" style="position:absolute;top:-998px"/> -->
                        <input  id="Username" name="Username" placeholder="请输入名称">
                        <span class="tel-warn confirmpwd-err hide"><em></em><i class="icon-warn" style="margin-left: 5px"></i></span>
                       <input type="submit" id="submit" value="注册">
                    </p>
                </form>
                <div class="reg_checkboxline pos">
                    <span class="z"><i class="icon-ok-sign boxcol" nullmsg="请同意!"></i></span>
                    <input type="hidden" name="agree" value="1">
                    <div class="Validform_checktip"></div>
                    <p>我已阅读并接受 <a href="#" target="_brack">《XXXX协议说明》</a></p>
                </div>
                
                <div class="bottom-info">已有账号，<a href="login.jsp">马上登录</a></div>
                <div class="third-party">
                    <a href="#" class="log-qq icon-qq-round"></a>
                    <a href="#" class="log-qq icon-weixin"></a>
                    <a href="#" class="log-qq icon-sina1"></a>
                </div>
                <p class="right">Powered by © 2018</p>
            </div>
        </div>
    </div>
     <!-- <script src="jsp/ljc/js/jquery.js"></script> --> 
    <script src="ljc/js/agree.js"></script>
     <!-- <script src="jsp/ljc/js/reg.js"></script> --> 
	<div style="text-align:center;">
</div>
  </body>

</html>

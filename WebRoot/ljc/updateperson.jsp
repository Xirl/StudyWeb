<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	<!-- <link href="../ljc/css/lyz.calendar.css" rel="stylesheet" type="text/css" /> -->
<!-- <link href="../ljc/css/lyz.calendar.css" rel="stylesheet" type="text/css" /> -->
<link href="../My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script src="http://www.jq22.com/jquery/1.4.4/jquery.min.js"></script>

<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<!-- <script src="../ljc/js/lyz.calendar.min.js" type="text/javascript"></script> -->
	<script type="text/javascript" src="../ljc/js/jquery-1.8.3.js"></script>
	<script src="/StudyWeb/jquery_validate/jquery.form.min.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate.min.1.11.1.js" type="text/javascript"></script>
	<script src="/StudyWeb/jquery_validate/jquery.validate_zh_cn.js" type="text/javascript"></script>

<style>

body {

font-size: 12px;

font-family: "微软雅黑", "宋体", "Arial Narrow";

}

</style>

<script>
    $(function () {
/*         $("#birthday").calendar({

            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"

            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200

            complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true

            readonly: true,                                       // 目标对象是否设为只读，默认：true

            upperLimit: new Date(),                               // 日期上限，默认：NaN(不限制)

            lowerLimit: new Date("1900/01/01"),                   // 日期下限，默认：NaN(不限制)

            callback: function () {                               // 点击选择日期后的回调函数
				
            }
        }); */
        $("#save").click(function(){
        	var picture=$("#path").val();
        	while(picture.indexOf("&")>0){
        		picture=picture.replace("&","%26");
        	}
        	
        	if($("#name").val()==null || ""==$("#name").val()) alert("名称不能为空");
        	$.ajax({
				url:'../User/update.action',//请求的url
				type:'post',//设置请求方式（post/get)，默认是get
				data:'name='+$("#name").val()+'&sex='+$("#sex").val()+'&birthday='+$("#birthday").val()+'&signature='+$("#signature").val()+'&head='+picture,//$("#path").val().replace("&","%26").replace("&","%26")//发送到服务器的数据，支持2种格式：一种是字符串（多参数之间用&符号分割），第二种用json格式
				dataType:'json',//设置服务器返回的数据类型，默认是text,
				success:function(data){
					if(data>0){
					 	alert("修改成功");
					 	location.href="personalinfo.jsp";
						}else{
							alert("修改失败");
						}
					},
					error:function(request,msg,e){
						alert('错误 ：'+request.responseText);
					}
			});
        }); 
        $("#upload").live('click',function(){ 
        	$("#uploadForm").ajaxForm({
        		 url: '../DownLoad/uploadhead.action',  
                 type: 'post',  
                 dataType: 'json',  
                 beforeSubmit: function () {},  
                 success: function (data) {  
                		 alert(data);
                 },
        	});
         }); 
	});
	function onValidate2(){
		var validate=$("#loginForm").validate({
			rules:{//验证规则
				head:{
					required:true
				}
			},
			messages:{//对应的提示消息
				head:{
					required:"<font color='red'>请选择上传文件</font>",
				}
			}
		});
		return validate.form();//如果true就验证成功，false验证失败
	}
</script>


	<style type="text/css">
	    table.dataintable {
	      margin-top:15px;
	      border-collapse:collapse;
	      border:1px solid #aaa;
	      width:100%;
	    }
	    table.dataintable th {
	      vertical-align:baseline;
	      padding:5px 15px 5px 6px;
	      background-color:#3F3F3F;
	      border:1px solid #3F3F3F;
	      text-align:left;
	      color:#fff;
	    }
	    table.dataintable td {
	      vertical-align:text-top;
	      padding:6px 15px 6px 6px;
	      border:1px solid #aaa;
	    }
	    table.dataintable tr:nth-child(odd) {
	      background-color:#F5F5F5;
	    }
	    table.dataintable tr:nth-child(even) {
	      background-color:#fff;
	    }
	   .space{
	  	 margin:auto;
	   	 width: 140px;
	   	 height: 140px;
	   }
	</style>
	<title>${sessionScope.currUser.name }</title>
	<!-- Bootstrap -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.no-icons.min.css" rel="stylesheet">
	<!-- Icons -->
	<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
	<!-- Fonts -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Alice|Open+Sans:400,300,700">
	<!-- Custom styles -->
	<link rel="stylesheet" href="css/styles.css">
	
	<!--[if lt IE 9]> <script src="assets/js/html5shiv.js"></script> <![endif]-->
</head>
<body class="home">

<header id="header">
	<div id="head" class="parallax" parallax-speed="2">
		<h1 id="logo" class="text-center">
		<div class="space">
		 <a class="upload" > <img class="img-circle" id="upload" onclick="onupload();"  src="images/${sessionScope.currUser.head}"></a>
		</div>	  
		</h1>
	</div>
</header>
	
	    <table class="dataintable">
    <tr>
      <th colspan="2" align="center">基本信息</th>
    </tr>
    <tr>
      <td><i>名称</i></td>
      <td><input id="name" name="name" value="${sessionScope.currUser.name }"></td>
    </tr>
    <tr>
      <td><i>头像</i></td><!-- ../DownLoad/uploadhead.action -->
      <td>
      <form id="uploadForm" action="javascript:void(0);" enctype="multipart/form-data" method="post">
	      	<input type="file" name="head" id="path">
	      	<input type="submit" name="upload" id="upload" value="头像上传">
	  </form>
      </td>
    </tr>
    <tr>
      <td><i>性别</i></td>
      <c:if test="${sessionScope.currUser.sex==null }">
      <td><input type="radio" id="sex" name="sex" value="男">男
      	  <input type="radio" id="sex" name="sex" value="女">女
      </td>
     </c:if>
     <c:if test="${sessionScope.currUser.sex=='男' }">
      <td><input type="radio" id="sex" name="sex" value="男" checked="checked">男
      	  <input type="radio" id="sex" name="sex" value="女">女
      </td>
     </c:if>
     <c:if test="${sessionScope.currUser.sex=='女' }">
      <td><input type="radio" id="sex" name="sex" value="男">男
      	  <input type="radio" id="sex" name="sex" value="女" checked="checked">女
      </td>
     </c:if>
    </tr>
    <tr>
      <td><i>生日日期</i></td>
      <td> 
      	<fmt:formatDate value="${sessionScope.currUser.birthday }" pattern="yyyy-MM-dd" var="birthday"/>
  			<%-- <input name="birthday" id="birthday" style="width:170px;padding:7px 10px;border:1px solid #ccc;margin-right:10px;" value="${birthday }"/> --%>
  			  <input id="birthday" name="birthday" class="Wdate" onfocus="WdatePicker({readOnly:true,maxDate:'%y-%M-%d'})" value="${birthday }" />
      </td>
    </tr>
    <tr>
      <td>个性签名</td>
      <td><textarea name="signature" rows="5" cols="20" id="signature" >${sessionScope.currUser.signature}</textarea></td>
    </tr>
    <tr>
      
      <td colspan="2" align="center">
      <input type="button" id="save" value="保存">
      	<a href="../Data/homepage.action">首页</a>
      	<a href="personalinfo.jsp" class="abcde">个人主页</a>
      </td>
    </tr>
    </table>

<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="js/template.js"></script>
</body>
</html>

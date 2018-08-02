<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
   <!-- <meta http-equiv="X-UA-Compatible" content="ie=edge"> --> 
    <title>Document</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/font-awesome.min.css">
     <link rel="stylesheet" href="css/font-awesome.min.css">
 
  <!-- 添加jq.js，注意，所有的js必须在jquery.js的后面，因为所有的js都是基于jq -->
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
  <script type="text/javascript" src="js/summernote-zh-CN.js"></script>
  <script type="text/javascript" src="js/summer.js" charset="utf-8"></script>
  <script>
  
  </script>
<style type="text/css">
#sumbit{
color: #fff;
background-color: #286090;
border-color: #204d74;
}

.form-group{
margin-left:5%;
}
</style>
</head>

<body>
    <ol class="breadcrumb">
       <!--  <li><a href="./news.html"><i class="fa fa-angle-left"></i><i class="fa fa-angle-left"></i> 返回</a></li> -->
    </ol>
    <div class="container-fluid">
        <div>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">填写板</a></li>
                <!--  <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">文章内容</a></li> -->
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                  <!--   <form action="getComment.action" method="POST" class="form-horizontal" role="form"> -->
                        <div class="form-group">
                            <label for="textname">
                                  标题
                            </label>
                            <lable id="textname" ></label>
                        </div>
                        <div class="form-group">
                            <label for="author">
                                 署名
                            </label>
                             <lable id="username" ></label>
                        </div>
                        <input type="hidden" value="" id="commid"/>
                      <!--   <div class="form-group">
                            <label for="email">
                                作者email
                            </label>
                            <input type="text" id="email">
                        </div>   -->
							<div class="dd"><textarea type="text" name="content" id="summernote"></textarea></div>	
                        <div class="bt">
                            <button class="sit">确定</button>
                            <button class="sib">取消</button>
                        </div>
                   <!--  </form> -->
                </div>
          <!--    <div role="tabpanel" class="tab-pane" id="profile">
                       <body>
                       <div id="summernote">请输入内容</div> 
					   <button class="btn btn-primary btn-top">发布文章</button>
                    </body>
                </div> -->
            </div>
        </div>
        <div class="imgId"> <input type="hidden" value="" /> </div>
       
    </div>
   

</body>
</html>
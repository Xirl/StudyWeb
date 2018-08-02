<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">

<head>
    
    
    <title>My</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>主页</title>
    <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
   
    <link href="css/site.css" rel="stylesheet" type="text/css">
    
    <script src="js/jquery.bootstrap.newsbox.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.flot.js"></script>
    <script src="js/jquery.flot.tooltip.min.js"></script>
    <script src="js/jquery.flot.resize.js"></script>
    <style type="text/css">
    body {
        height: 100%;
    }
    ul,
    ol {
        list-style: none;
    }

    a {
        color: #666666;
    }

    a:focus,
    a:hover {
        text-decoration: none;
    }

    .clear:after {
        display: block;
        content: '';
        clear: both;
    }

    .panel-title {
        margin-top: 20px;
        font-size: 25px;
        color: #666666;
    }

    .boxes {
        margin: 50px 0;
    }

    .box {
        display: block;
        padding: 45px 20px;
        text-align: center;
        border-radius: 10px;
    }

    .box a {
        color: #fff;
    }

    .bg-blue {
        background: #99bfce;
        display: none;
    }

    .bg-pink {
        background: #d4c4ab;
        display: none;
    }

    .bg-green {
        background: #b3ce93;
        display: none;
    }

    .bg-grey {
        background: #e4d9d9;
        display: none;
    }

    .icon {
        width: 50%;
    }

    .count {
        width: 50%;
    }

    .count p {
        font-size: 30px;
        margin-top: 20px;
    }

    .count span {
        font-size: 16px;
    }

    .box i {
        font-size: 50px;
        margin: 20px 15px;
    }

    .box:hover {
        background: #424a5d;
    }

    i {
        margin-right: 5px;
    }

    .personal {
        border: 2px solid #424a5d;
        padding: 50px 10px;
    }
    /*	.personal_img {
				border: 2px solid #424a5d;
				border-radius: 100%;

			}*/

    .personal_img img {
        height: 200px;
        width: 200px;
    }

    .welcome-word {
        padding: 55px 20px;
    }

    .welcome-word span {
        /*display: block;
				margin: 20px;*/
        font-size: 22px;
    }

    .welcome-word p {
        font-size: 28px;
    }

    .personal_info {
        padding: 40px 10px;
    }
    .comment{
    	display: none;
    }

    #lunbo {
               /* height: 250px;*/
            }

            #lunbo .tittle {
                margin-top: 10px;
            }

            #lunbo ol {
                background: #3c763d;
                opacity: 0.5;
            }

            #lunbo .item {
                padding: 25px 30px;
            }

            #lunbo .item img {
                height: 200px;
                width: 220px;
            }

            .flot-chart {
                display: block;
                height: 200px;
            }

            .flot-chart-content {
                width: 100%;
                height: 100%;
            }
    </style>
</head>

<body style="margin-top:5px;">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 main">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <p class="panel-title">
                            <i class="fa fa-home"></i> Welcome
                        </p>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-9">
                                <div class="row">
                                    <div class="col-sm-3 personal_img">
                                        <a href="#">
												<img src="img/touxiang.jpeg" alt="">
											</a>
                                    </div>
                                    <div class="col-sm-5 welcome-word">
                                        <span><p>尊敬的用户</p>
											下午好
											</span>
                                        <a href="my.html">个人信息>></a>
                                    </div>
                                    <div class="col-sm-4 personal_info">
                                        <p>艺名：<a >芒果酱</a></p>
                                        <p>邮箱：<a >983255555@163.com</a></p>
                                        <p>手机号：<a >188***9</a></p>
                                        <p>住址：<a >广州市天河区中山大道西</a></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 comment" style="margin-top:5px;">
                                <div class="panel panel-success">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><a href="comments.html">最新评论</a></h3>
                                    </div>
                                    <div class="panel-body">
                                        <a href="">
												<i class="fa fa-user"></i> 啦啦啦啦啦啦啦
											</a>
                                    </div>
                                    <div class="panel-body">
                                        <a href="">
												<i class="fa fa-user"></i> 哈哈哈哈哈哈或或或或哈
											</a>
                                    </div>
                                    <div class="panel-body">
                                        <a href="">
												<i class="fa fa-user"></i> 啦啦啦啦啦啦啦
											</a>
                                    </div>
                                    <div class="panel-body">
                                        <a href="">
												<i class="fa fa-user"></i> 红红火火恍恍惚惚
											</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row boxes">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-sm-3" style="margin-top:5px;">
                                        <div class="box bg-blue clear">
                                            <a href="products.html">
                                                <div class="icon pull-left">
                                                    <i class="fa fa-shopping-cart"></i>
                                                </div>
                                                <div class="count pull-left">
                                                    <p>我的</p>
                                                    <span>提问</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-sm-3" style="margin-top:5px;">
                                        <div class="box bg-pink clear">
                                            <a href="comments.html">
                                                <div class="icon pull-left">
                                                    <i class="fa fa-commenting"></i>
                                                </div>
                                                <div class="count pull-left">
                                                    <p>我的</p>
                                                    <span>评论</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script>
$(function() {
    $(".bg-blue").fadeIn(1000);
    $(".bg-pink").fadeIn(1000);
    $(".bg-green").fadeIn(1000);
    $(".bg-grey").fadeIn(1000);
    $(".comment").slideDown(1000);
    
    

});
</script>
</html>     
       
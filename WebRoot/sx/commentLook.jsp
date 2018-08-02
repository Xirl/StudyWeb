<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>主页</title>
    <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
    <!-- <link rel="stylesheet" type="text/css" href="css/normalize.css"> -->
    <!-- <link rel="stylesheet" type="text/css" href="css/default.css"> -->
    <!-- <link rel="stylesheet" type="text/css" href="css/style.css"> -->
    <!-- <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"> -->
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
                        
<script>
$(function() {
 
    $(".comment").slideDown(1000);
  
})
</script>
</html>
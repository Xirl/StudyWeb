<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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

    
    
                            <div class="col-md-4 news">
                                    <div class="panel panel-success">
                                        <div class="panel-heading">
                                            <span class="glyphicon glyphicon-list-alt"></span><b>News</b></div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <ul class="demo2" style="overflow-y: hidden; height: 200px;">
                                                        <li style="overflow: hidden; height: 37.217px; padding-top: 3.01808px; margin-top: 0px; padding-bottom: 3.01808px; margin-bottom: 0px;" class="news-item">企业logo设计有哪些禁忌？ <a href="news.html">管理</a></li>
                                                        <li style="" class="news-item">一个好的产品包装设计，要做到什么？<a href="news.html">管理</a></li>
                                                        <li style="" class="news-item">为什么企业一定要注重品牌logo设计？ <a href="news.html">管理</a></li>
                                                        <li style="" class="news-item">产品有个好的包装设计有多重要？<a href="news.html">管理</a></li>
                                                        <li style="overflow: hidden; height: 12.0285px; padding-top: 0.981917px; margin-top: 0px; padding-bottom: 0.981917px; margin-bottom: 0px;"
                                                            class="news-item">VI设计要注意什么问题？ <a href="news.html">管理</a></li>
                                                        <li style="display: none;" class="news-item">如此创意的包装设计，产品不畅销才怪！ <a href="news.html">管理</a></li>
                                                        <li style="display: none;" class="news-item">企业画册设计应该注意什么？ <a href="news.html">管理</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer">
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


    $(".demo2").bootstrapNews({

                    newsPerPage: 4,

                    autoplay: true,

                    pauseOnHover: true,

                    navigation: true,

                    direction: 'down',

                    newsTickerInterval: 2000,

                    onToDo: function() {

                        //console.log(this);

                    }

     })
  
})
</script>
</html>
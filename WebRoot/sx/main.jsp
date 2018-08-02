<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
	<!-- <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  
   <!--  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />  -->  
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />   
    <title>首页</title>
	<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
        <script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script> 
       <script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>  

    <link rel="stylesheet" href="css/main.css" /> 
     <link rel="stylesheet" href="css/head.css" />   
    <script src="js/jquery.json.min.js"></script> 
    
    <script type="text/javascript" src="js/test.js"></script> 
     
<style type="text/css">
.primary{
margin-right:80%;
}

</style>
  </head>
<body>
 <body>
   <span id="p_test"></span>
	<div id="do_themeid" style="margin-left:7%">
	    <div style="margin-left:2%;float:left;margin-right:1%" class="r_bn">
			<button type="button" class="btn_return">首页</button>
		</div>
		<div style="margin-left:2%;float:left;margin-right:2%" class="r_bn">
			<button type="button" class="btn_comm">我的评论</button>
		</div>
		<div style="float:left;margin-right:2%" class="rr_bn">
			<button type="button" class="btn_theme">我的提问</button>
		</div>
		<div id="gettheme" style="float:left">
			<button class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">我要提问</button>
		</div>
		<div style="float:left;margin-left:2%" class="rrr_bn">
			<button type="button" class="btn_all">全部</button>
		</div>
		<div style="margin-left:2%;display:none;float:left;margin-top:4px"
			class="other_bn">
			<button type="button" class="btn_type"></button>
			<input type="hidden" class="type_id" />
		</div>
	</div>

	<div class="modal fade" id="myModal"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">提问</h4>
				</div>
				<div class="modal-body">
					<p>问题描述</p>
				<select name="type_theme" class="myType">
    				<option value="" class="optionType">--请选择--</option>
    			</select>
					<textarea class="form-control"></textarea>
				</div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
					<div class="myBtn"><button class="btn-my" data-toggle="modal" type="button">提交</button></div>
	</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<div style="height:80px; width:100%; background-color:#f5f5f5" ></div>

     <!--子窗口引入   <div class="header_j" id="header"
		style=" right: 200px; top:2px;z-index:99999999" width="100%" height="100%">
		<iframe id="head" width="100%" height="70px" src="head.jsp"
			frameborder="0" seamless></iframe>
	</div> -->
	<div id="total">
		<div style="display:none;">
			<div id="t_dn" class="dynamic_node">
				<meta class="dn_commfir_id" content="" />
				<meta class="dn_commfir_userid" content="" />
				<meta class="dn_id" content="" />
				<meta class="dn_user_id" content="" />
				
				
				<div class="dn_left">
					<div class="dn_user_avatar">
						<a href=""> <img class="img-thumbnail" src="" /></a>
					</div>
					<div class="dn_zannum">
						<span></span> <span class="myzan"></span>
					</div>
					<div class="dn_dianzan">
					<meta class="firs_zan" content="" />
						<span href=""><span class="glyphicon glyphicon-thumbs-up"
							aria-hidden="true"></span></span>
					</div>
				</div>
				<div class="dn_right">
					<div class="dn_title">
					<a href="#"><img class="img-rounded" src="img/logoquan14567395793.png" width=40px;higth=40px;/> </a>
					<span class="themeName"></span>
						<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
						<a href=""><span class="con"></span></a>
					</div>
					<div class="answer_btn">
						<input type="button" value='写回答' class="answer" />
					</div>
					<div class="dn_right_top">
						<div class="dn_username">
							<a href=""><span class="dn_username_name"></span></a> <span
								class="dn_username_signature"></span>
						</div>


						<div class="dn_content_close1">
							<a href=""> <span class="glyphicon glyphicon-triangle-bottom"
								aria-hidden="true"></span>
							</a>
						</div>
					</div>
					<div class="dn_content">
						<a href="" class="dn_content_link"> <span
							class="dn_content_digest"></span>
						</a> <span class="dn_content_text"></span>
					</div>
					<div class="dn_action" style="display: block;">
						<div class="dn_content_close2">
							<a href=""> <span class="glyphicon glyphicon-triangle-top"
								aria-hidden="true"></span>
							</a>
						</div>
						
						<a href=""><span class="dn_action_report">举报</span></a> <a href=""><span
							class="dn_action_share">分享</span></a>
							<a class="talk_first" data-toggle="modal" data-target="#twoModal" style="display:none"><span>查看对话</span></a>
							 <a href=""><span class="dn_action_comm">103条评论</span></a> <span class="dn_action_time"></span>
					</div>
					<div class="dn_comm">
						<div class="dn_comm_items"></div>
						<div class="dn_comm_showall">
							<button id="dn_comm_showall_button" type="button"
								class="btn btn-default btn-xs">显示全部评论</button>
						</div>
						<div class="dn_comm_replay">
							<div class="dn_comm_replay_field">
								<textarea class="form-control" rows="2" placeholder="写下你的评论..."></textarea>

							</div>
							<div class="dn_comm_repley_buttoms">
								<button type="button" class="btn_comment">评论</button>
								<a href="" class="dn_comm_replay_buttoms_cancel">取消</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="t_dn_comm" class="dn_comm_item">
				<meta class="dn_comm_id" content="">
				<meta class="dn_comm_userid" content="">

				<div>
					<div class="dn_comm_item_left">
						<div>
							<a href="#"><img class="img-rounded" src="img/logo.png" /> </a>
						</div>
					</div>
					<div class="dn_comm_item_mid">
						<div class="dn_comm_item_middle_username">
							<a href="#"><span>用户名</span></a>
						</div>
					</div>

				</div>
				<div>
					<div class="dn_comm_item_mid_content">
						<div class="dn_span">
							<span></span>
						</div>
					</div>
				</div>

				<div class="dn_comm_foot">
					<div class="dn_comm_foot_left">
						<span style="display:block;">2015-09-08 12:00:09</span>
					</div>

					<div class="dn_comm_item_right">
						<div class="dn_comm_zannum">
							<span>0</span> <span class="timezan"></span>
						</div>
						<div class="dn_comm_dianzan">
						<meta class="other_zan" content=""/>
							<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
						</div>
					</div>
				</div>


				<div class="dn_comm_item_mid_action">
					<a class="reply_on"><span>回复</span></a> <a href="#"><span>举报</span></a>
					<a class="talk" data-toggle="modal" data-target="#twoModal" style="display:none">查看对话</a>
				</div>

				<div class="dn_comm_reply_field" style="display: none;">
					<textarea class="form-reply" rows="2" placeholder="写下你的回复..."
						width="auto"></textarea>
					<div class="dn_repp">
						<button type="button" class="btn_rreply">回复</button>
					</div>
				</div>
			</div>
		</div>
			<!-- 模态框 -->
		<div class="modal fade" id="twoModal"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">对话列表</h4>
				</div>
					<div class="modal-body">
						<div class="body_mid">
							<div class="Rcomment">
								<div class="Rtheme">
									<div class="Rcomment_c" style="margin-top:20px">
									<meta class="talk_id" content="" />
									<meta class="rep_id" content="" />
									<meta class="rep_user" content="" />
									<meta class="rep_commid" content="" />
										<span class="Rcomment_t"></span>
											<span class="time"></span>	
									</div>
									<div class="mid-reply">
										<span class="Rcomment_content"></span> 
										<div style="float:right;display:none" class="reply_ini"><a ><span></span></a></div>
										<div class="delete_btn" style="float:right;display:none"><a ><span>删除</span></a></div>
										<div  class="reply_in"><a><span></span></a></div>
										<div class="dn_reply_field" style="display: none;">
											<textarea class="form-reply" rows="2" placeholder="回复他..."
												width="auto"></textarea>
											<div class="dn_repp">
												<button type="button" class="btn_reply">回复</button>
											</div>
										</div>
									</div>
								</div>
	                         <div class="talk_body" style="margin-top:10px">
								<div class="body_talk">
								    <meta class="repre_id" content="" />
									<meta class="repre_user" content="" />
									<meta class="reto_user" content="" />
									<span class="toreply"></span>
									<span></span> 
									<span class="replyUser"></span>
									<div class="Rtime">
										<span class="retime"></span>
									</div>
								</div>
								<div class="Rcontent"><span class="recontent"></span></div>
								
								<div class="dn_content_close6" style="display: none;">
							<a> <span class="glyphicon glyphicon-triangle-top"
								aria-hidden="true" style="float:right"></span>
							</a>
						       </div>
								
								<div class="dn_rereply_field" style="display: none;">
											<textarea class="form-rereply" rows="2" placeholder="回复回复..."
												width="auto"></textarea>
											<div class="dn_rerepp">
												<button type="button" class="btn_rereply">回复</button>
											</div>
								</div>
							</div>
							</div>
							
						</div>
					</div>
					<div class="modal-footer">
					<meta class="tocommid" content=""/>
					<div class="last_page" style="float:left;"><a><span>上一页</span></a></div>
					<div class="next_page" style="float:left;margin-left:10px"><a ><span >下一页</span></a></div>
					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>			
	</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
		
		
		

		<div class="global">
			<div class="list_layout"></div>
			<div class="load_flag">
				<button id="load_flag_button" type="button"
					class="btn btn-default btn-s">加载更多</button>
				<div id="load_flag_info">
					<small>没有更多了 ！</small>
				</div>
			</div>
		</div>
	</div>
 <div class="row J_mainNew" id="content-New"
		style="position: fixed; right: 0px; top:125px;" width="30%" height="100%">
		<iframe id="New" width="100%" height="320px" src="roll.jsp"
			frameborder="0" data-id="index_v1.html" seamless></iframe>
	</div> 
	
	
</body>
</html>

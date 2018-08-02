

$(document).ready(function(){  	
        url_user = "http://user.com/i=";  
        url_img = "http://127.0.0.1/StudyWeb/jsp/ljc/images";  
        url_dianzan = "http://dianzan.com";  
        url_dn_detail = "http://dt.com/i=";  
          
        layout_list = $(".list_layout");  
        t_dn = $("#t_dn");  
        t_dn_comm = $("#t_dn_comm");  
          
        maxnum = 2;  // 最大加载次数  
        num = 1;  
        load_flag = $(".load_flag"); 
        tmaxnum=3;
        tnum=1;//对话列表的下标
        bnum=1;//使点击上一页的按钮只绑定一次
        dnum=1;//使点击下一页的按钮只绑定一次
       var type_id=null;
 

      
        function render_dn_list(arr_dn,arr_cn,arr_cnum,fla,countList){
            for(var i=0; i<arr_dn.length;i++){ 
            	themeNum=arr_dn.length;
                var node_dn =  t_dn.clone(); 
                if(fla==1 && i==0) layout_list.find("#t_dn").remove();//类型选项移除
                
                node_dn.find(".dn_id").attr("content",arr_dn[i].id); //动态ID  
                node_dn.find(".dn_user_id").attr("content",arr_dn[i].userid);  //用户ID  
                node_dn.find(".dn_left .dn_user_avatar a").attr("href",url_user+arr_dn[i].userid);  // 用户名链接  
               node_dn.find(".dn_left .dn_user_avatar a img").attr("src",url_img+arr_cn[i].head);  // 用户头像  
                node_dn.find(".dn_zannum span").text(arr_zan[i]==null?0:arr_zan[i]);  //  点赞数  
                //node_dn.find(".dn_dianzan a").attr("href",arr_dn[i]["avatar"]);  //  点赞链接  
                node_dn.find(".dn_title a").attr("href",url_dn_detail+arr_dn[i].tcontext+"#"+arr_dn[i].id);  //跳转动态所在页锚点  
                node_dn.find(".dn_title a span").text(arr_dn[i].tcontext);  // 所属标题  
                node_dn.find(".dn_username a").attr("src",url_user+arr_dn[i].userid); //用户名链接   
                node_dn.find(".themeName").text(arr_tuser[i]==null?"刘":arr_tuser[i].loginName); //题主用户名   
                node_dn.find(".dn_title img").attr("src",url_img+arr_tuser[i].head); //题主头像
                node_dn.find(".dn_username_name").text(arr_cuser[i]==null?"待评论":arr_cuser[i].loginName); //评论用户名  
                node_dn.find(".dn_username_signature").text("--");  //用户签名  
                node_dn.find(".dn_commfir_id").attr("content",arr_cn[i]==null?"2":arr_cn[i].id);
                node_dn.find(".dn_commfir_userid").attr("content",arr_cn[i]==null?"2":arr_cn[i].toComment);
                node_dn.find(".dn_action_comm").text(arr_cnum==null?"0条评论":arr_cnum[i]+"条评论");
                
                if(countList[i]!=null) node_dn.find(".talk_first").attr("style","display:inline-block");
                var digest = "";  
                
                var content = arr_cn[i]==null?"该主题还未评论":arr_cn[i].ccontent;  
                if(content.length > 150){  
                    digest=content.substring(0,150)+"... "; 
                    node_dn.find(".dn_content_digest").append("<a href='#'> 显示全部</a>");  
                }  
                else{  
                    digest=content;  
                }  
                node_dn.find(".dn_content_digest").html(digest);  //正文 
                node_dn.find(".dn_content_text").html(content);  // 正文  
                node_dn.find(".dn_action_time").text(arr_dn[i].tdate);  //  发布时间  
      
      
                //  添加点击摘要事件  
                node_dn.find(".dn_content_digest,.dn_comm_replay_buttoms_cancel").click(  
                    function(){  
                        var dn_right = $(this).parent().parent().parent();  
                         dn_right.find(".dn_content_text").toggle();  
                         dn_right.find(".dn_content_digest").toggle();  
                         dn_right.parent().find(".dn_content_close1").toggle();  
                         dn_right.parent().find(".dn_content_close2").toggle();  
                         //dn_right.parent().find(".dn_action").toggle();  
                        var comm= dn_right.find(".dn_comm");      
                        if(comm.css("display") == "block"){  
                            comm.css("display","none");  
                        }  
                        return false;  // !!  
                    }  
                ); 
                
                //添加写回答按钮功能
                node_dn.find(".answer").click(function(){
                	var themeid=$(this).parent().parent().parent().find(".dn_id").attr("content");
                	var tcontent=$(this).parent().parent().parent().find(".dn_title a span").text();
                	var name=$(this).parent().parent().parent().find(".dn_username_name").text(); 
                	var curruser=isLoad();
                	var to_userid=2;
        	    	if(curruser==null){
        	    		if(confirm("您还未登录，是否登录")){
        	    	 curr_userID=null;
        	    			window.location.href="../jsp/ljc/login.jsp";
        	    			return false;
        	    		}else{
                	
        	    			return false;
        	    		}
        	    	} to_userid=curruser.userId;
                	window.location.href="getComment.action?flag=5&themeid="+themeid+"&tcontext="
                	+encodeURI(encodeURI(tcontent))+"&tname="+encodeURI(encodeURI(name))+"&userId="+to_userid;
                   
           	
                });
                  //首条评论的查看对话
                node_dn.find(".talk_first").click(function(){
                	var node_dn=$(this).parent().parent().parent();
                	var comid=node_dn.find(".dn_commfir_id").attr("content");
                	$(".tocommid").attr("content",comid);
                	$.ajax({
               		 url:"getReply.action",
               		 type:"post",
               		 dataType:"json",
               		 data:{
               			 otherComid:comid,flag:'3',sign:'1'
               		 },
               		 success:function(msg){
               			 if(msg.postMap!=null){
               				 upload_reply(msg.postMap,msg.userPostMap,msg.list,1,msg.integer);
               			 }else{
               				 alert('暂无数据');
               			 }
               		 }
               	 });
                	
                });
                
                // 添加点击折叠事件  
                node_dn.find(".dn_content_close2").click(  
                    function(){  
                        var dn_right = $(this).parent().parent();  
                         dn_right.find(".dn_content_text").toggle();  
                         dn_right.find(".dn_content_digest").toggle();  
                         dn_right.parent().find(".dn_content_close1").toggle();  
                         dn_right.parent().find(".dn_content_close2").toggle();  
                         //dn_right.parent().find(".dn_action").toggle();  
                   
                        var comm= dn_right.find(".dn_comm");      
                        if(comm.css("display") == "block"){  
                            comm.css("display","none");  
                        }  
                        return false;  // !!  
                    }  
                );  
                    
                node_dn.find(".dn_comm_replay_buttoms_cancel").click(function(){
                	$(this).parent().parent().parent().toggle();
                });
                  
                  
                // 为评论、分享、举报按钮添加hover下划线效果  
                node_dn.find(".dn_action_comm,.dn_action_share,.dn_action_report").hover(  
                    function(){  
                        $(this).css("text-decoration","underline");  
                        return false;  // !!  
                    }  
                    ,  
                    function(){  
                        $(this).css("text-decoration","none");  
                        return false;  // !!  
                    }  
                );  
                  
                // 为列表项添加点赞事件  
                node_dn.find(".dn_dianzan").click(  
                    function(){  
                    //alert('zan');  
                        var dn_left = $(this).parent();  
                        var zan_num = dn_left.find(".dn_zannum span:first");  
                        var zan_num2 = dn_left.find(".dn_zannum span:last").text(); 
                        var commid=$(this).parent().parent().find(".dn_commfir_id").attr("content");
                        var themeid=$(this).parent().parent().find(".dn_id").attr("content");
                        var userid=$(this).parent().parent().find(".dn_user_id").attr("content");

                        var currzan = zan_num.text();  
                        if(zan_num2==currzan){
                        	zan_num.text(parseInt(currzan)+1); 
                        	zan_num.animate({fontSize:"1.2em"},"fast");  
                            $(this).animate({fontSize:"1.2em"},"fast");  
                           zan_num.animate({fontSize:"1em"},"fast");   
                            $(this).animate({fontSize:"1em"},"fast"); 
//                	    	var curruser=isLoad();
//                	    	if(curruser==null){
//                	    		if(confirm("您还未登录，是否登录")){
                	    	// curr_userID=null;
//                	    			window.location.href="../jsp/ljc/login.jsp";
//                	    			return false;
//                	    		}else{
                        	to_userid=3;//备选，根据点赞功能
//                	    			return false;
//                	    		}
//                	    	} 
                        	$.ajax({
                        		url:"getTags.action",
                        		type:"post",
                        		dataType:"json",data:{flag:'1',commid:commid,
                        			themeid:themeid,userid:userid,to_userid:to_userid},
                        			success:function(msg){
                        				if(msg.tagid!=0) dn_left.find(".firs_zan").attr("content",msg.tagid);
                        				
                        			}
                        	});return false;
                        }else if(currzan!=zan_num2 && currzan!=0){
                        	zan_num.text(parseInt(currzan)-1); 
                        	zan_num.animate({fontSize:"1.2em"},"fast");  
                            $(this).animate({fontSize:"1.2em"},"fast");  
                           zan_num.animate({fontSize:"1em"},"fast");   
                            $(this).animate({fontSize:"1em"},"fast");
                            var zan_id=dn_left.find(".firs_zan").attr("content"); 
                        	$.ajax({
                        		url:"getTags.action",
                        		type:"post",
                        		dataType:"json",data:{flag:'0',commid:commid,zan_id:zan_id}
                        		
                        	});return false;
                        }
                        
                         
                         return false;  // !!  
                    }  
                );  
              //加载回复列表   
               function upload_reply(commdata,userPostMap,list,flag,re_reply){
            	   modal=$(".body_mid");
            	   modal.find(".Rcomment").eq(0).siblings().remove();
        		 //  modal.find(".talk_body").eq(0).siblings().remove();
        		   
           	    
            	   for(var i=0;i<commdata.length;i++){//回复评论	  
            		   var Rcomment=$(".Rcomment").eq(0).clone();  
                	    Rcomment.find(".Rcomment_t").text(userPostMap[i].loginName);//用户名
                	    Rcomment.find(".time").text(commdata[i].replyDate);//时间
                	    Rcomment.find(".Rcomment_content").text(commdata[i].replyContent);//内容 
                	    Rcomment.find(".rep_id").attr("content",commdata[i].id);
                	    Rcomment.find(".rep_commid").attr("content",commdata[i].commentId);
                	    Rcomment.find(".rep_user").attr("content",commdata[i].replyUser);
                	    tmaxnum=list.pageCount;
                	    if(re_reply[i]!=null) Rcomment.find(".reply_ini").attr("style","display:inline-block");
                	    //绑定回复按钮
                	   
                	    Rcomment.find(".reply_ini span").text("...");
                	    Rcomment.find(".reply_in span").text("回复");
                	    //
                	    var curruser=isLoad();
                	    if(curruser.userId==userPostMap[i].userId) Rcomment.find(".delete_btn").attr("style","float:right;display:inline-block");
                	   //删除回复事件
                	    Rcomment.find(".delete_btn").click(function(){
                	    	var r=$(this).parent().parent();
                	    	var repId=r.find(".rep_id").attr("content");
                	    	$.ajax({
                	    		url:"getReply.action",
                	    		type:"post",
                	    		dataType:"json",
                	    		data:{repId:repId,flag:'8'},
                	    		success:function(msg){
                	    			if(msg.msg!=0) {alert(msg.msg);r.parent().remove();}
                	    		}
                	    	});
                	    });
                	    Rcomment.find(".reply_in").click(function(){
                         	$(this).parent().find(".dn_reply_field").toggle();	
                         });
                	    //保存回复回复的回复
                	    Rcomment.find(".btn_reply").click(function(){
                         	var Rtheme=$(this).parent().parent().parent().parent();
                         	var text=Rtheme.find(".form-reply").val();
                         	var rep_id=Rtheme.find(".rep_id").attr("content");
                         	var rep_commid=Rtheme.find(".rep_commid").attr("content");
                         	//var rep_user=Rtheme.find(".rep_user").attr("content");
                         	if(text==null||text==""){
                         		alert('请输入内容后点击回复');return false;
                         	}else{
                         		Rtheme.find(".reply_ini").attr("style","display:inline-block");
                         	}
                         	var curruser=isLoad();
                         	var toUser=4;
                         	if(curruser!=null) toUser=curruser.userId;
                         	var flag='4';
                         	$.ajax({
                         		url:"getReply.action",
                           		type:"post",
                           		dataType:"json",
                           		async:true,  
                                   data: {text:text,
                                	   rep_id:rep_id,
                                	   rep_commid:rep_commid,
                                	   ToUser:toUser,
                                   	flag:flag
                                   	   },
                                   	   success:function(data){
                                   		   if(data.msg!=0){
                                   			   alert('回复已发送');
                                   			Rtheme.find(".form-reply").val('');
                                   		   }
                                   	   }
                         		
                         	});
                         	
                         });
                	   
                	   
                        
                	  
                	    
                	    //点击点点点事件
                	    modal.append(Rcomment);
                	    
                	    Rcomment.find(".reply_ini").click(function(){
                	    	
                	    	var Rtheme=$(this).parent().parent();
                	    	var com=Rtheme.parent();
                	    	//com.find(".dn_content_close6").eq(0).hide();
                	    	var rep_id=Rtheme.find(".rep_id").attr("content");
                	    	var rep_commid=Rtheme.find(".rep_commid").attr("content");
                	    	
                	    	$.ajax({
                         		url:"getReply.action",
                           		type:"post",
                           		dataType:"json",
                           		async:true,
                           		data:{
                           			rep_id:rep_id,rep_commid:rep_commid,flag:'5'
                           		},
                	    	success:function(msg){
                	    	if(msg.msg!=null) saveReply(msg.toUser,msg.newUser,msg.newReply,com);
                	    	}
                	    	});
                	    });
                	    
                	    //加载回复回复列表
                	    function saveReply(toUser,newUser,newReply,com){
                       		  // Rcomment.find(".mid-reply").append('<div class="queryReply"><a href="" class="href_reply">...</a></div>');
                	    	com.find(".talk_body,.Rtheme").eq(0).siblings().remove();
                	    	
                       		   for(var j=0;j<newReply.length;j++){//回复回复
                       			var Rreply=modal.find(".talk_body").eq(0).clone();
                       			if(j==0) Rreply.css("margin-top","20px");
                       			if(j==newReply.length-1) Rreply.find(".dn_content_close6").attr("style","display:block");
                       			Rreply.find(".repre_id").attr("content",newReply[j].id);
                       			Rreply.find(".repre_user").attr("content",newReply[j].replyUser);
                       			Rreply.find(".reto_user").attr("content",toUser[j].userId);
                       			
                       			
                       			Rreply.find(".toreply").text(toUser[j].loginName);//回复者
                       			newUser[j]==null?Rreply.find("span").eq(1).text(":"):Rreply.find("span").eq(1).text("@");	
                       			if(newUser[j]!=null) Rreply.find(".replyUser").text(newUser[j].loginName);//被回复者
                       			Rreply.find(".retime").text(newReply[j].replyDate);//时间
                       			Rreply.find(".recontent").text(newReply[j].replyContent);//回复内容
                       			Rreply.find(".recontent").click(function(){
                       				$(this).parent().parent().find(".dn_rereply_field").toggle();
                       			});
                       			//上拉按钮
                       			Rreply.find(".dn_content_close6").click(function(){
                         		   $(this).parent().parent().find(".talk_body").toggle();
                         	   })
                       			Rreply.find(".btn_rereply").click(function(){
                       				var talk_body=$(this).parent().parent().parent();
                       				var rep_id=talk_body.find(".repre_id").attr("content");
                       				var rep_user=talk_body.find(".reto_user").attr("content");
                       				var rep_commid=talk_body.parent().find(".rep_commid").attr("content");
                       				var text=talk_body.find(".form-rereply").val();
                       				var curruser=isLoad();
                                 	var toUser=4;
                                 	if(text==""||text==null||text==undefined) return false;
                                 	if(curruser!=null) toUser=curruser.userId;
                       				var flag='4';
                                 	$.ajax({
                                 		url:"getReply.action",
                                   		type:"post",
                                   		dataType:"json",
                                   		async:true,  
                                           data: {text:text,
                                        	   rep_id:rep_id,
                                        	   rep_commid:rep_commid,
                                        	   rep_user:rep_user,ToUser:toUser,
                                           	flag:flag,sign:'0'
                                           	   },
                                           	   success:function(data){
                                           		   if(data.msg!=0){
                                           			   alert('回复已发送');
                                           			talk_body.find(".form-rereply").val('');//这里用this对象是定位不到此文本域,this已变为window对象
                                           		   }
                                           	   }
                                 	});
                       			});
                       			
                       			com.append(Rreply);
                       		   }
                       	   }
                	    }
               
            	   
                 }
               //翻页对话列表
              if(bnum<2){
            	   $(".last_page").click(function(){
              	 if(tnum>1){
              	 tnum--;
              	    var otherComid=$(".tocommid").attr("content");//取自加载对话列表时填充参数，
              	  
                  	 $.ajax({
                   		 url:"getReply.action",
                   		 type:"post",
                   		 dataType:"json",
                   		 data:{
                   			 otherComid:otherComid,flag:'3',pageIndex:tnum,sign:'1'
                   		 },
                   		 success:function(msg){
                   			 if(msg.postMap!=null){
                   				 upload_reply(msg.postMap,msg.userPostMap,msg.list,1,msg.integer);
                   			 }else{
                   				 alert('获取数据失败');
                   			 }
                   		 }
                   	 });
              	 }return false;
               });
               bnum++;
              }
            
              /* $(".href_reply").click(function(){
            	  $(this).parent().parent().parent().parent().parent().find(".talk_body").toggle(); 
            	  return false;
               });*/
             
               //下一页
               if(dnum<2){
               $(".next_page").click(function(){
          	 if(tnum<tmaxnum && tnum>=1){
          		 tnum++;
          		 var otherComid=$(".tocommid").attr("content");
               	
               	 $.ajax({
               		 url:"getReply.action",
               		 type:"post",
               		 dataType:"json",
               		 data:{
               			 otherComid:otherComid,flag:'3',pageIndex:tnum,sign:'1'
               		 },
               		 success:function(msg){
               			 if(msg.postMap!=null){
               				 upload_reply(msg.postMap,msg.userPostMap,msg.list,1,msg.integer);
               			 }else{
               				 alert('获取数据失败');
               			 }
               		 }
               	 });
          	 }return false;
           });
               dnum++;
             }
               
               
                function load_reply(text,comid,to_userid,realUser,flag,r){
                	$.ajax({
                   		url:"getReply.action",
                   		type:"post",
                   		dataType:"json",
                   		async:true,  
                           data: {text:text,
                           	comid:comid,
                           	to_userid:to_userid,
                           	realUser:realUser,
                           	flag:flag
                           	   },
                           success: function(msg){
                           if(msg.status=="200" && msg.rid!=0){    
                        	   alert('用户已收到回复');
                        	 r.val('');
                           }else{
                           	//alert(ret_code); 
                        	   return false;
                           }
                          }     
                   	});//第一条评论的回复
                }
            
              
              //添加点击评论回复事件
                node_dn.find(".btn_comment").click(function(){
                	 dn_replay=$(this).parent().parent().parent().parent().parent();
                	 var r=dn_replay.find(".form-control");
                	var text=r.val();
                	var comid=dn_replay.find(".dn_commfir_id").attr("content");//第一条评论项的id，（第一条回复）
                	var to_userid=dn_replay.find(".dn_commfir_userid").attr("content");//被评论的用户
                	var realUser;
                	if(text == "" || text == null || text == undefined) alert('请输入内容');
                	dn_replay.find(".talk_first").attr("style","display:inline-block");
        	    	var curruser=isLoad();
        	    	if(curruser==null){
        	    		if(confirm("您还未登录，是否登录")){
        	    	 curr_userID=null;
        	    			window.location.href="../jsp/ljc/login.jsp";
        	    			return false;
        	    		}else{
        	    			return false;
        	    		}
        	    	}else{
        	    		realUser=curruser.userId;
        	    	}   
                	var flag=1;
                	if(text!=null&&text!=''){
                		if(comid != "" || comid != null || comid != undefined){
                			load_reply(text,comid,to_userid,realUser,flag,r)
                			
                		}
                		
                	
//                			if(othercomid == "" || othercomid == null || othercomid == undefined){
//                				upload_reply(text,othercomid,to_userid,flag)//回复的回复
//                			}
                			
                		
                	
                	 }
                });
                
               
                
                
                
                
                
                
                
                //  加载评论，并处理评论项事件  
                function load_comm(dn_id,dn_comm_items,flag){  
                   
                    $.ajax({  
                        url:"getComment.action",  
                        type: "post",//使用get方法访问后台  
                        dataType: "json",//返回json格式的数据  
                        async:true,  
                        data: {themeid:dn_id,
                        	    flag:flag},
                       complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示  
                        success: function(msg){//msg为返回的数据，在这里做数据绑定  
                            var encoded = $.toJSON(msg);  
                            code =$.evalJSON(encoded).status;  
                            newarr_dn =$.evalJSON(encoded).newComm;
                            newzan=$.evalJSON(encoded).newUtil;
                            commList=$.evalJSON(encoded).commentUser;
                            replyCount=$.evalJSON(encoded).replyCount;
                            if(code == "200"&& newarr_dn!=null){  
                                for(var i=0;i<newarr_dn.length;i++){ 
                                    var n_comm = t_dn_comm.clone();  
                                    n_comm.find(".dn_comm_id").attr("content",newarr_dn[i].id);
                                    n_comm.find(".dn_comm_userid").attr("content",newarr_dn[i].toComment);
                                    
                                    n_comm.find(".dn_comm_item_left a").attr("href",url_user+newarr_dn[i].userid);  
                                    if(commList[i]!=null) n_comm.find(".dn_comm_item_left img").attr("src",url_img+commList[i].head);  
                                    n_comm.find(".dn_comm_item_middle_username a").attr("href",url_user+newarr_dn[i].userid);  
                                    n_comm.find(".dn_comm_item_middle_username a span").text(commList[i]==null?"李":commList[i].loginName); //用户名 
                                    n_comm.find(".dn_comm_item_mid_content span").html(newarr_dn[i].ccontent);  
                                    n_comm.find(".dn_comm_foot_left span").text(newarr_dn[i].cdate); 
                                    
                                    n_comm.find(".dn_comm_zannum span:first").text(newzan[i]==null || newzan[i].length==0 || newzan[i]==undefined?0:newzan[i].count);  
                                    n_comm.find(".dn_comm_zannum span:last").text(newzan[i]==null || newzan[i].length==0 || newzan[i]==undefined?0:newzan[i].count);  
                                    if(replyCount[i]!=null)  n_comm.find(".talk").attr("style","display:inline-block");
                                    
                                    // 为评论项添加hover效果  
                                        n_comm.hover(function(){  
                                            $(this).find(".dn_comm_item_mid_action").toggle();  
                                        });  
                                       //为回复添加文本框  
                                         n_comm.find(".reply_on").click(function(){
                                         	$(this).parent().parent().find(".dn_comm_reply_field").toggle();
                                         	
                                         });
                                         
                                         //点击回复事件
                                         n_comm.find(".btn_rreply").click(function(){
                                        	//其余回复
                                        	 var dn_repley= $(this).parent().parent().parent();
                                        	 var r=dn_repley.find(".form-reply");
                                         	var replyText=r.val();
                                         	var otherComid=dn_repley.find(".dn_comm_id").attr("content");
                                         	var to_commuserid=dn_repley.find(".dn_comm_userid").attr("content");
                                         	var realUser;
                                         	$(this).parent().parent().parent().find(".talk").attr("style","display:inline-block");
                                    if(replyText==""||replyText==null||replyText==undefined){alert('请输入内容后继续！');return false;}
                                 	    	var curruser=isLoad();
                                 	    	if(curruser==null){
                                 	    		if(confirm("您还未登录，是否登录")){
                                 	    	 curr_userID=null;
                                 	    			window.location.href="../jsp/ljc/login.jsp";
                                 	    			return false;
                                 	    		}else{
                                 	    			return false;
                                 	    		}
                                 	    	}else{
                                 	    		realUser=curruser.userId;
                                 	    	}
                                         	if(replyText != "" || replyText != null || replyText != undefined){
                                        		if(otherComid != "" || otherComid != null || otherComid != undefined){
                                        			var flag=2;			
                                        			load_reply(replyText,otherComid,to_commuserid,realUser,flag,r);	
                      
                                        		}
                                         	}
                                        });
                                         
                                       
                                         ////////////////////////////////////////////////////////////////////////////
                                         
                                        
                                        
                                         //点击查看对话列表
                                         n_comm.find(".talk").click(function(){
                                        	var no_comm=$(this).parent().parent();
                                        	var otherComid=no_comm.find(".dn_comm_id").attr("content");
                                        	$(".tocommid").attr("content",otherComid);
                                        	
                                        	 $.ajax({
                                        		 url:"getReply.action",
                                        		 type:"post",
                                        		 dataType:"json",
                                        		 data:{
                                        			 otherComid:otherComid,flag:'3',sign:'1'
                                        		 },
                                        		 success:function(msg){
                                        			 if(msg.postMap!=null){
                                        				 upload_reply(msg.postMap,msg.userPostMap,msg.list,1,msg.integer);
                                        			 }else{
                                        				 alert('暂无数据');
                                        			 }
                                        		 }
                                        	 });
                                         });
                                      
                                        
                                          
                                           
                                        //  为评论项添加点赞动画效果  
                                        function loa(){
                                        	alert("调用");
                                        }
                                         n_comm.find(".dn_comm_dianzan").click(  
                                            function(){ 
                                            	 var zan = n_comm.find(".dn_comm_dianzan"); 
                                            	var themeid=$(this).parent().parent().parent().parent().parent().parent().parent().find(".dn_id").attr("content");
                                                var userid=$(this).parent().parent().parent().find(".dn_comm_userid").attr("content");
                                            	var commid=$(this).parent().parent().parent().find(".dn_comm_id").attr("content");
                                                var zan_num = $(this).parent().find(".dn_comm_zannum span:first");
                                                var currzan = zan_num.text();
                                                var zan_num2 = $(this).parent().find(".dn_comm_zannum span").eq(1).text(); 
                                                alert(userid);
                                                if(zan_num2==currzan){
                                                	zan_num.text(parseInt(currzan)+1); 
                                                	zan_num.animate({fontSize:"1.2em"},"fast");  
                                                    $(this).animate({fontSize:"1.2em"},"fast");  
                                                    zan_num.animate({fontSize:"1em"},"fast");        
                                                    $(this).animate({fontSize:"1em"},"fast");
                                                   
                                                	$.ajax({
                                                		url:"getTags.action",
                                                		type:"post",
                                                		dataType:"json",data:{flag:'1',commid:commid,themeid:themeid,userid:userid},
                                                		success:function(msg){
                                                			
                                                			if(msg.tagid!=0) zan.find(".other_zan").attr("content",msg.tagid);
                                                		}
                                                	});return false;
                                                }else if(currzan!=zan_num2 && currzan!=0){
                                                	zan_num.text(parseInt(currzan)-1);
                                                	zan_num.animate({fontSize:"1.2em"},"fast");  
                                                    $(this).animate({fontSize:"1.2em"},"fast");  
                                                    zan_num.animate({fontSize:"1em"},"fast");        
                                                    $(this).animate({fontSize:"1em"},"fast");
                                                    var zan_id=zan.find(".other_zan").attr("content");alert(zan_id+'点赞的');
                                                	$.ajax({
                                                		url:"getTags.action",
                                                		type:"post",
                                                		dataType:"json",data:{flag:'1',commid:commid,zan_id:zan_id
                                                			},
                                                		success:function(msg){
                                                			return false;
                                                		}
                                                	});return false;
                                                }
                                                 return false;
                                            }
                                        );  
                                    dn_comm_items.append(n_comm);  
                                }      
                            }  
          
                        }  
                    });
                }
                       
               
                // 加载某一项的热门评论  
                node_dn.find(".dn_action_comm").click(  
                    function(){  
                        var dn = $(this).parent().parent().parent().parent();  
                        dn.find(".dn_comm").toggle();  
                        var dn_comm_items = dn.find(".dn_comm_items");  
                        dn_comm_items.empty();  
                        var dn_id = dn.find(".dn_id").attr("content");  
                        load_comm(dn_id,dn_comm_items,1);  
                        return false;  // 禁止页面刷新  
                    }  
                );  
                 
                // 加载某一项的全部评论  
                node_dn.find("#dn_comm_showall_button").click(  
                    function(){ 
                        var dn = $(this).parent().parent().parent().parent();  
                        var dn_comm_items = dn.find(".dn_comm_items");  
                        dn_comm_items.empty();  
                        var dn_id = dn.find(".dn_id").attr("content");  
                        load_comm(dn_id,dn_comm_items,2);  
                        return false;  
                    }  
                );  
                  
                //  
                layout_list.append(node_dn);  
            }  
        }  
          
          
        function fill_list(isfla,fla){  
        	//var type=type_id;
        	var flag;
        	var typeId;
        	//type_id==null?num=1:num=num;alert(num);
        	type_id==null?flag='10':flag='7';
        	type_id==null?typeId=null:typeId=type_id;
        	fla==1?num=num:num=1;
            $.ajax({  
                url:"getTheme.action",  
                type: "post",
                dataType: "json",
                async:false,  
                data: {opr:'1',
                	   pageIndex:num,
                	   flag:flag,typeId:typeId
                      },  
                complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示  
                success: function(msg){//msg为返回的数据，在这里做数据绑定  
                	var encoded = $.toJSON(msg);  
                    ret_code =$.evalJSON(encoded).status;
                    maxnum =$.evalJSON(encoded).theme.pageCount;
                    arr_dn =$.evalJSON(encoded).theme.list; 
                    arr_cn =$.evalJSON(encoded).comment;
                    arr_zan =$.evalJSON(encoded).zan;
                    arr_cuser=$.evalJSON(encoded).commUser;
                    arr_tuser=$.evalJSON(encoded).themeUser;
                    arr_cnum=$.evalJSON(encoded).list;
                    arr_count=$.evalJSON(encoded).countList;
                    if(ret_code == "200"){  
                        render_dn_list(arr_dn,arr_cn,arr_cnum,isfla,arr_count);  
                    }  
                    else{  
                       alert(ret_code);  
                    }  
                }  
            }); 
        }  
      
    // 为加载数据按钮注册事件    
    $(".load_flag").click(function(){ 
        if(num < maxnum){	
        	num++; 
            fill_list(0,1);//标志位1表示点击全部按钮 不会重复将num置1  
             
        }  
        else{    
            load_flag.find("#load_flag_info").css("display","block");  
            load_flag.find("#load_flag_button").css("display","none");  
        } 
    });       
    fill_list(0,0);
    //头域模块
    //问题类型模块
    var theme=$("#do_themeid");
	var other_btn=$(".other_bn");
	function loadBtn(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
                var btn=other_btn.eq(0).clone();
				btn.attr("style","margin-left:2%;display:block;float:left");
				btn.find(".btn_type").text(data[i].themeType);
				btn.find(".type_id").val(data[i].id);
				
				$(".btn_type").click(function(){	
					var type=$(this).parent().find(".type_id").val();
					type_id=type;num=1;
					//点击类型按钮弹出的类型
					$.ajax({
						url:"getTheme.action",
						type:"post",	
					dataType:"json",
					data:{
						typeId:type,flag:'7',opr:'1',pageIndex:num
					},
					success:function(msg){
						if(msg.msg=="1"){
							var encoded = $.toJSON(msg);  
		                    ret_code =$.evalJSON(encoded).status;
		                    maxnum =$.evalJSON(encoded).theme.pageCount;
		                    arr_dn =$.evalJSON(encoded).theme.list; 
		                    arr_cn =$.evalJSON(encoded).comment;
		                    arr_zan =$.evalJSON(encoded).zan; 
		                    arr_cnum=$.evalJSON(encoded).list;
		                    arr_count=$.evalJSON(encoded).countList;
		                    load_flag.find("#load_flag_info").css("display","none");  
		                    load_flag.find("#load_flag_button").css("display","block");
		                    render_dn_list(arr_dn,arr_cn,arr_cnum,1,arr_count);
		                    
						} 
	                    else{  
	                       alert(msg.msg);  
	                    }  
					}, 
					error: function (XMLHttpRequest, textStatus, errorThrown) {
			             $("#p_test").innerHTML = "there is something wrong!";
			                alert(XMLHttpRequest.status);
			              // alert(XMLHttpRequest.readyState);
			              // alert(textStatus);
			        }	
					});				
				});theme.append(btn);
			}
		}	
	}
	
	//点击我的评论
	$(".btn_comm").click(function(){
		
		var curruser=isLoad();
                                 	    	if(curruser==null){
                                 	    		if(confirm("您还未登录，是否登录")){
                                 	    	 curr_userID=null;
                                 	    			window.location.href="../jsp/ljc/login.jsp";
                                	    			return false;
                                 	    		}else{
                                 	    			return false;
                                 	    		}
                                	    	}else{
                                	    		to_commuserid=curruser.userId;
                                	    	}  
             window.location.href="comm.jsp?userId="+to_commuserid;
	});
	//点击我的提问
	$(".btn_theme").click(function(){
		
		var curruser=isLoad();
                           	    	if(curruser==null){
                            	    		if(confirm("您还未登录，是否登录")){
                                 	    	 curr_userID=null;
                                 	    			window.location.href="../jsp/ljc/login.jsp";
                                	    			return false;
                                 	    		}else{
                                 	    			return false;
                                 	    		}
                                 	    	}else{
                                	    		to_commuserid=curruser.userId;
                                	    	}   
             window.location.href="news.jsp?userId="+to_commuserid;
	});
	//点击返回主页
	$(".btn_return").click(function(){
		window.location.href="";
	});
	
	//加载全部
	$(".btn_all").click(function(){
		type_id=null;
		fill_list(1,0);
	})
	
	function loadType(){
		$.ajax({
			url:"getType.action",
			type:"post",
			dataType:"json",
			success:function(data){		
				if(data.list!=null){
					typeDate=data.list;
					loadBtn(data.list);		
				}
			},
		     error: function (XMLHttpRequest, textStatus, errorThrown) {
	            //  $("#p_test").innerHTML = "there is something wrong!";
	                alert(XMLHttpRequest.status);
	              // alert(XMLHttpRequest.readyState);
	              //  alert(textStatus);
	        }
		});
	}
	loadType();
	
	//验证用户登录
    function isLoad(){
    	var user;
    	$.ajax({
    		url:"verifyUser.action",
    		type:"post",
    		async:false,
    		dataType:"json",
    		success:function(msg){
    			if(msg.msg=="1") {
    				user=msg.user;
    				curr_userID=user.userId;
    			}
    		}
    	});return user;
    }
	
    //提问弹出层模块
    function getData(typeData){
    	for(var i=0;i<typeData.length;i++){
    		
    		var type=$(".myType").find(".optionType").eq(0).clone();
    		if(i==0) $(".myType").find(".optionType").eq(0).siblings().remove();
	    	type.attr("value",typeData[i].id);
	    	type.text("");//清空
	    	type.text(typeData[i].themeType);
	    	$(".myType").append(type);
    	}    	
    } 
    //打开提问弹出层
	    $("#myModal").on("show.bs.modal",function(){
	    	var curruser=isLoad();
	    	if(curruser==null){
	    		if(confirm("您还未登录，是否登录")){
	    	 curr_userID=null;
	    			window.location.href="../jsp/ljc/login.jsp";
	    			return false;
	    		}else{
	    			return false;
	    		}
	    	}else{
	    		curr_userID=curruser.userId;
	    	}   
	    	
	    	if(typeDate!=null){
	    		 getData(typeDate);
	    	}else{
	    		$.ajax({
	    			url:"getType.action",
	    			type:"post",
	    			dataType:"json",
	    			success:function(data){		
	    				if(data.list!=null){
	    					typeDate=data.list;
	    					loadBtn(data.list);		
	    				}
	    			}		     
	    		});

	    	}	
	    	
	    	$(".btn-my").click(function(){
	    		var textarea=$(".form-control").val();
	    		if(textarea == "" || textarea == null || textarea == undefined){
	    			alert('请输入内容'); return false;
	    		}
	    		var typeId=$(".myType").find(":selected").attr("value");
	    		if(typeId == "" || typeId == null || typeId == undefined){
	    			alert('请选择类型后提交'); return false;
	    		}
	    		if(confirm("确认提交?")){
	    			$.ajax({
	    				url:"getTheme.action",
	    				type:"post",
	    				data:{text:textarea,
	    					typeId:typeId,
	    					curr_userID:curr_userID,
	    					flag:'2'
	    					},
	    				dataType:"json",
	    				success:function(data){
	    					if(data.msg!=0){
	    						$("#myModal").modal("hide");//点击后关闭弹出层
	    						alert('问题提交成功');	
	    					}
	    				}
	    			});
	    		}else{
	    			return false;
	    		}	
	    	});
	    });
	    
	    
	 
	    
	    
	    
	
});
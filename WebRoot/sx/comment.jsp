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
 <!--  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
  <script type="text/javascript" src="js/summernote-zh-CN.js"></script>
  <script type="text/javascript" src="js/summer.js" charset="utf-8"></script> -->

<script type="text/javascript" src="sx/js/jquery-1.8.3.js"></script> 
  <script src="js/jquery.json.min.js"></script> 
  <script type="text/javascript">  		
/* $('#table1 tr').hover(function(){  
    $(this).children('td').addClass('hover')  
}, function(){  
    $(this).children('td').removeClass('hover')  
});  */
$(function(){
function load(){
$.ajax({
url:"getTheme.action",
type:"post",
dataType:"json",
data:{flag:3},
success:function(data){
               var themeList=data.themeList;
         if(themeList!=null){
         var tbody=$(".tbody").find("tr").eq(0).siblings().remove();
         for(var i=0;i<themeList.length;i++){
          tbody=$(".tbody").find("tr").eq(0).clone();
         if(i==0) $(".tbody").find("tr").eq(0).remove();
         tbody.find("td").eq(0).attr("value",themeList[i].id);
         tbody.find("td").eq(1).text(themeList[i].tcontext);
         tbody.find("td").eq(2).text(themeList[i].tdate);
         tbody.find("td").eq(3).text(data.list[i]); 
         
        tbody.find("a").click(function(){
        var themeid=$(this).parent().parent().find("input").eq(0).attr("value");
        alert(themeid)
        $.ajax({
       url:"getTheme.action",
		type:"post",
		dataType:"json",
		data:{flag:'4',themeid:themeid},
		success:function(msg){
		if(msg.msg!=0) load();
		}
        });
        
        });
         
         $(".tbody").append(tbody);
         } 
         }        
}
});


}
load();




 $("input[name='check']").attr("checked",true);
                  //复选框部分
         
         $("#checkall").change(function(){  
    if($(this).attr("checked")){  
        $("input[name='check']").attr("checked",true);  
    }else{  
        $("input[name='check']").attr("checked",false);  
    }  
   });
         
            //单选框全部选中
   $("input[name='check']").change(function(){  
    if($("input[name='check']").not("input:checked").size() <= 0){  
        $("#checkall").prop("checked",true);  
    }else{  
        $("#checkall").prop("checked",false);  
    }  
});

});



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

.mytable{
margin-left:20%;
margin-top:10%;
text-align:center;
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
                 <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">文章内容</a></li> 
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                <div class="mytable">
           <table border="1" cellspacing="0" cellpadding="0" width="600" id="table1">
			<thead class="thead">
				<tr>
					<td>
						<input type="checkbox" id="checkall" value="" checked=""/> 
					</td>
					<td width="50%">主题内容</td>
					<td>时间</td>
					<td>评论数</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody class="tbody">
				<tr>
					<td>
						<input type="checkbox" name="check" value="" checked=""/>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<a href="#">删除</a>&nbsp;
					</td>
				</tr>  
			</tbody>
		</table>
		<div><a href="">上一页</a><a href="">下一页</a></div>
                </div>
                </div>
             <div role="tabpanel" class="tab-pane" id="profile">
                       <body>
                       <div id="summernote">请输入内容</div> 
					   <button class="btn btn-primary btn-top">发布文章</button>
                    </body>
                </div> 
            </div>
        </div>
        <div class="imgId"> <input type="hidden" value="" /> </div>
       
    </div>
   

</body>
</html>
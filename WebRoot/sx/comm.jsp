<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"> 
    <title>Document</title>
    <link rel="stylesheet" href="sx/css/bootstrap.min.css">
    <link rel="stylesheet" href="sx/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="sx/css/summernote.css">
    <link rel="stylesheet" href="sx/css/summernote-bs3.css">
    <script src="sx/js/jquery-3.1.0.min.js"></script>
    <script src="sx/js/bootstrap.min.js"></script>
    <script src="sx/js/bootstrap-table.js"></script>
    <script src="sx/js/bootstrap-table.min.js"></script>
    <script src="sx/js/bootstrap-table-locale-all.min.js"></script>
    <script src="sx/js/bootstrap-table-zh-CN.min.js"></script>
    <style type="text/css">
    .my-table-tool {
        margin-bottom: 30px;
    }

    .Article {
        display: none;
    }

    #modal-text,
    #modal-text1 {
        min-height: 250px;
    }

    .btn2,
    .btn3 {
        display: none;
    }

    #modal-text {
        min-height: 300px;
    }

    a {
        cursor: pointer;
    }

    .img-display {
        display: none;
    }

    .columns {
        display: none;
    }
    #eTable{
    table-layout:fixed;
    }
    .tWidth{
width:15%;
text-overflow:ellipsis;
overflow:hidden;
white-space:nowrap;
} 
    </style>
  </head>
  
  
  <body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 main">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2>我的评论</h2>
                    </div>
                    <div class="panel-body">
                        <div class="btn-group" id="toolBar" role="group">
                            <button type="button" id="btn_add" class="btn btn-outline btn-default"><i class="glyphicon glyphicon-plus"></i></button>
                            <button type="button" id="btn_trash" class="btn btn-outline btn-default"><i class="glyphicon glyphicon-trash"></i></button>
                        </div>
                        <table id="eTable" data-height="500">
                            <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="id" ></th>
                                    <th data-field="userid" ></th>
                                    <th data-field="tcontext">问题</th>
                                    <th data-field="loginName">写作人</th>
                                    <th data-field="ccontent" data-class="tWidth">评论</th>
                                    <th data-field="cdate">评论时间</th>
                                    <th data-field="zcount">点赞数</th>
                                    <th data-field="rcount">回复数</th>
                                    <th data-field="type" class="themeType">主题类型</th>
                                    <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">操作</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">操作</h4>
                </div>
                <div class="modal-body">
                    <form action="#" method="POST" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="modal-textname" class="col-sm-2 control-label">文章名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="modal-textname" readonly="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modal-author" class="col-sm-2 control-label">时间</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="modal-author" readonly="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modal-text" class="col-sm-2 control-label">文章内容</label>
                            <div class="col-sm-10" >
                                <div class="form-control" id="modal-text" rows="3" readonly="true"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary">提交</button>
                </div> -->
            </div>
        </div>
    </div>

  
    <script type="text/javascript">
 
     $("#eTable").bootstrapTable({
        //url: "./01.json",
        search: true,
        pagination: true,
        showRefresh: true,
        showToggle: true,
        showRefresh: true,
        showColumns: true,
        pageList:[5,10,20,30],
        pageNumber: 1,
        pageSize:5,
      
        toolbar: "#toolBar"
    }); 
    $("#eTable").bootstrapTable("hideColumn", "id");
    $("#eTable").bootstrapTable("hideColumn", "userid");//隐藏列
   
     //获取url参数
    	var getUrlParam = function(name) {   
    	      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");   
    	      var r = window.location.search.substr(1).match(reg);   
    	      if (r != null) return decodeURI(r[2]); return null;   
    	}  
       // alert(window.location.href);
    	userId=getUrlParam("userId"); 
    	function load(){
    	$.ajax({
            url: "sx/getComment.action",
            type: "post",           
            dataType: "json",
            data:{pageIndex:'1',flag:'6',userId:userId},
            success: function (msg) {
                $("#eTable").bootstrapTable('load', msg.list);
            },
            error: function () {
                alert("错误");
            }
        });
    	}
            load();
   
    $("#eTable").on('click-row.bs.table', function(e, row, element) {
        $(".success").removeClass('success');
        $(element).addClass('success');
    })

    function getSelectdRow() {
        var index = $("#eTable").find('tr.selected').data('index');
        return $("#eTable").bootstrapTable('id')[index];
    }

    $("#btn_trash").click(function() {
    if(confirm('是否清除选中记录')){
    $("input:checkbox[name='btSelectItem']:checked").each(function(i) { // 遍历name=test的多选框
  var id=$("#eTable").bootstrapTable("getSelections")[i].id;  // 每一个被选中项的值
    $.ajax({
   url:"sx/getComment.action",
   type:"post",
   dataType:"json",
   data:{id:id,flag:'7'}
   
   }); load();
});
        $("#eTable").find('tr.selected').remove();
        return true;
    }else{
    return false;
    }
   
    })

  /*  $("#btn_add").click(function() {
        // $("#myModal").modal("show");
        // $('#myModal :input').val('');
        location.href = 'note.html';
    });*/

    window.operateEvents = {
        'click .edit': function(e, value, row) {
            location.href = 'note.html';
        },
        'click .remove': function(e, value, row) {
        if(confirm('是否清除选中记录')){
         var id=row.id;
         alert(id)
           $.ajax({
               url:"sx/getComment.action",
			   type:"post",
			   dataType:"json",
			   data:{id:id,flag:'7'}
			    });
			   $(this).parent().parent().remove();
			   load();
			   return true;
        }else{
         return false;
        }
       
          
       
        },
        'click .detailed': function(e, value, row) {
            var tds = $(this).parent().parent().children();
            $("#myModal").modal("show")
            $("#modal-textname").val(row.tcontext);
            $("#modal-author").val(row.cdate);
            $("#modal-text").html(row.ccontent);
        //   var tag='img';
          //  if(row.ccontent.indexOf(tag)>0){
            
           //  var html = $("#modal-text").val();
          //  $("#modal-text").val($(html).text());
          // }else{
         //  $("#modal-text").html(row.ccontent);
         //  }
            
            
            // $('#modal-textname').removeClass('alert-danger');
            // $('#modal-author').removeClass('alert-danger');
            // $('#modal-text').removeClass('alert-danger');
            $('#myModal').data({ 'tds': tds });
        }
    };

    function operateFormatter(value, row, index) {
    row.id
        return [
            '<button class="btn btn-success detailed">详情</button>',
            '&nbsp;',
            '<button class="btn btn-primary remove">删除</button>',
        ].join('')
    }

    
    
    
    

    
    </script>
</body>
</html>

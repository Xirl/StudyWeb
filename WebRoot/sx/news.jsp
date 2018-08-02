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
    #btn_add{
    display:none;
    }
    #eTable{
    border: 0px solid transparent !important;
    border-collapse:separate;
    border-spacing:0 0.5rem;
    }
    </style>
  </head>
  
  
  <body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 main">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2>我的提问</h2>
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
                                    <th data-field="themeType"></th>
                                    <th data-field="tcontext">内容</th>
                                    <th data-field="tdate">发布时间</th>
                                    <th data-field="loginName">写作人</th>
                                    <th data-field="count">评论数</th>
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
                                <input type="text" class="form-control" id="modal-textname" readonly="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modal-author" class="col-sm-2 control-label">署名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="modal-author" readonly="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modal-text" class="col-sm-2 control-label">文章内容</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="modal-text" rows="3" readonly="true"></textarea>
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
    <!-- <div class="modal fade" id="thetext">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">文章</h4>
                </div>
                <div class="modal-body">
                    <form action="#" method="POST" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="modal-text" class="col-sm-2 control-label">内容</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="modal-text1" rows="3" readonly="true"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-warning btn1" id="btn1">修改</button>
                    <button class="btn btn-info btn2" id="btn2">确认</button>
                    <button type="button" class="btn btn-default btn3" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div> -->
  
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
        queryParamsType:'limit',
        
   
      
        toolbar: "#toolBar"
    }); 
    $("#eTable").bootstrapTable("hideColumn", "id");
    $("#eTable").bootstrapTable("hideColumn", "userid");//隐藏列
    $("#eTable").bootstrapTable("hideColumn", "themeType");
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
            url: "sx/getTheme.action",
            type: "post",           
            dataType: "json",
            data:{pageIndex:'1',flag:'3',userId:userId},
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
   url:"sx/getTheme.action",
   type:"post",
   dataType:"json",
   data:{id:id,flag:'4'}
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
               url:"sx/getTheme.action",
			   type:"post",
			   dataType:"json",
			   data:{id:id,flag:'4'}
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
            $("#modal-textname").val(row.loginName);
            $("#modal-author").val(row.tdate);
            $("#modal-text").val(row.tcontext);
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
    // $("#eTable").on('load-success.bs.table', function(data) {
    //     $('.modal-footer>.btn-primary').click(function() {
    //         var mydate = new Date();
    //         var textname = $('#modal-textname').val();
    //         var author = $('#modal-author').val();
    //         var time = mydate.getFullYear() + "-" + (mydate.getMonth() + 1) + "-" + mydate.getDate();
    //         var text = $('#modal-text').val();
    //         var tds = $("#myModal").data('tds');
    //         var number = 0;
    //         if (textname == "") {
    //             $('#modal-textname').addClass('alert-danger');
    //             number = 1;
    //         } else {
    //             $('#modal-textname').removeClass('alert-danger');
    //         }
    //         if (author == "") {
    //             $('#modal-author').addClass('alert-danger');
    //             number = 1;
    //         } else {
    //             $('#modal-author').removeClass('alert-danger');
    //         }
    //         if (text == "") {
    //             $('#modal-text').addClass('alert-danger');
    //             number = 1;
    //         } else {
    //             $('#modal-text').removeClass('alert-danger');
    //         }

    //         if (number == 0) {
    //             $("#myModal").modal('hide');
    //             if (tds === undefined) {
    //                 $('#eTable').bootstrapTable('prepend', [{
    //                     textname: textname,
    //                     author: author,
    //                     time: time,
    //                     text: text
    //                 }])
    //             } else {
    //                 tds.eq(1).text(textname);
    //                 tds.eq(2).text(author);
    //                 tds.eq(4).text(time);
    //                 tds.eq(5).text(text);
    //                 $("#myModal").removeData('tds');
    //             }
    //         }
    //     });
    // });
    
    
    
    

    
    </script>
</body>
</html>

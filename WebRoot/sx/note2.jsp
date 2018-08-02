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
    <link rel="stylesheet" href="css/com.css">
    <link rel="stylesheet" type="text/css" href="css/summernote.css">
    <link rel="stylesheet" type="text/css" href="css/summernote-bs4.css">
    <script src="js/summernote.min.js"></script>
    <script src="js/summernote-zh-CN.js"></script>
    <script>
  
    $(document).ready(function() { 
    $(".btn btn-primary").click(function(){
$("#summernote").summernote("code")
    alert("ssssss");
    });
       
      $("#summernote").summernote({
            height: 300,
            minHeight: 200,
            maxHeight: 400,
            focus: true,
            lang: 'zh-CN',
            toolbar: [
    ['style', ['style']],
    ['font', ['bold', 'underline', 'clear']],
    ['fontname', ['fontname']],
    ['color', ['color']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['table', ['table']],
    ['insert', ['link', 'picture']],
    ['view', ['fullscreen', 'codeview', 'help']]
  ],
             callbacks:{
            onImageUpload: function(files) {
            alert("fff");
     var $files = $(files);
     $files.each(function() {
            var file = this;
            var data = new FormData();
             data.append("file", file);
            $.ajax({
                data : data,
                type : "POST",
                url : url,// div上的action
                cache : false,
                contentType : false,
                processData : false,
             success : function(response) {
                    
                    var json = YUNM.jsonEval(response);

                    
                    YUNM.debug(json);

                   
                    YUNM.ajaxDone(json);

                    // 状态ok时
                    if (json[YUNM.keys.statusCode] == YUNM.statusCode.ok) {
                        // 文件不为空
                        if (json[YUNM.keys.result]) {

                            // 获取后台数据保存的图片完整路径
                            var imageUrl = json[YUNM.keys.result].completeSavePath;

                            // 插入到summernote
                            $this.summernote('insertImage', imageUrl, function($image) {
                                // todo，后续可以对image对象增加新的css式样等等，这里默认
                            });
                        }
                    }
                    }
             });
            });
              }
            } 
       });
       $('#summernote').on('summernote.image.upload', function(we, files) {
  // upload image to server and create imgNode...als
  $summernote.summernote('insertNode', imgNode);
});
      
      /*  function sendFile(file) {

         var filename = false;
         try {
             filename = file['name'];
         } catch (e) {
             filename = false;
         }
         if (!filename) {
             $(".note-alarm").remove();
         }

        //以上防止在图片在编辑器内拖拽引发第二次上传导致的提示错误  
        data = new FormData();
        data.append("file", file);
        data.append("key", filename); //唯一性参数  

        $.ajax({
             data: data,
             type: "POST",
             url: "getImg.action",
             cache: false,
             contentType: false,
             processData: false,
             success: function(url) {
                if (url == '200') {
                     alert("上传失败！");
                     return;
                } else {
                    alert("上传成功！");
                 }
                  alert(url);  
                 editor.insertImage($editable, url);
             },
            error: function() {
                 alert("上传失败！");
                 return;
             }
         });
         var text = "${text}";
         $('#summernote').code(text);
         var str = $('#summernote').code();
		 alert(str);
    }  */
  });
    
    //图片上传
    </script>
</head>

<body>
    <ol class="breadcrumb">
       <!--  <li><a href="./news.html"><i class="fa fa-angle-left"></i><i class="fa fa-angle-left"></i> 返回</a></li> -->
    </ol>
    <div class="container-fluid">
        <div>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">通用信息</a></li>
                <!--  <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">文章内容</a></li> -->
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <form action="" method="POST" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="textname">
                                标题
                            </label>
                            <input type="text" id="textname" placeholder="文章标题">
                        </div>
                        <div class="form-group">
                            <label for="author">
                                署名
                            </label>
                            <input type="text" id="author">
                        </div>
                        <div class="form-group">
                            <label for="email">
                                作者email
                            </label>
                            <input type="text" id="email">
                        </div>
						
                        <div class="bt">
                            <button class="btn btn-primary">确定</button>
                            <button class="btn btn-default">重置</button>
                        </div>
                    </form>
					<body>
					<div class="dd"><textarea type="text" name="content" id="summernote"></textarea>
					</div>
						
						</body>
                </div>
          <!--    <div role="tabpanel" class="tab-pane" id="profile">
                       <body>
                       <div id="summernote">请输入内容</div> 
					   <button class="btn btn-primary btn-top">发布文章</button>
                    </body>
                </div> -->
            </div>
        </div>
    </div>
   

</body>
</html>
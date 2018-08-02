    $(document).ready(function() {
    	
    	//获取url参数
    	var getUrlParam = function(name) {   
    	      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");   
    	      var r = window.location.search.substr(1).match(reg);   
    	      if (r != null) return decodeURI(r[2]); return null;   
    	}  
       // alert(window.location.href);
    	commId=getUrlParam("commId"); 
        tcontent= getUrlParam("tcontent");
        userName=getUrlParam("userName");
        $("#username").text(userName);//题主
    	$("#textname").text(tcontent);//问题
    	//$("#commid").text(commId);//评论id
  
      
      var $summernote = $('#summernote').summernote({
        height: 200,
        minHeight: null,
        maxHeight: null,
        focus: true,
        lang: 'zh-CN', //必须先加入summernote-zh-CN.js才能使用
        placeholder: "...输入主题",
      
        //调用图片上传
        callbacks: {
            onImageUpload: function (files) {
                sendFile($summernote, files[0]);
            },
          }
    });
    //ajax上传图片
    function sendFile($summernote, file) {
        var formData = new FormData();
        formData.append("file", file);
        formData.append("commId",commId);
        $.ajax({
            url: "getImg.action",//
            data: formData,
            type: 'POST',
            dataType:"json",
            cache: false,  
            contentType: false,
            processData: false,
            success: function (data) { 
              $('#summernote').summernote('insertImage', data.path);  //直接插入路径就行，filename可选
              console.log(data);
            },
            error:function(){
              alert("上传图片失败！");
            }
        });
    }
            // Firefox和Chrome早期版本中带有前缀
    var MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver;
    //下面代码必须在var $summernote = $('#summernote').summernote()构造完才有class = note-editable，否则直接使用会报错
    //
    // 选择目标节点
    var target = document.querySelector('.note-editable'); 
    // 创建观察者对象
    var observer = new MutationObserver(function(mutations){ //观察对象的回调函数
      console.log(mutations);
      mutations.forEach(function(mutation) //forEach：遍历所有MutationRecord
      {  
        console.log(mutation);
        console.log(mutation.type);  //MutationRecord.type
        console.log(mutation.oldValue);  // 注意mutation.type是childList,则不能使用oldValue来获取值
        if(mutation.addedNodes[0]!=null){ 
          console.log(mutation.addedNodes);
           console.log(mutation.addedNodes[0]);
           console.log(mutation.addedNodes[0].src);
          if(mutation.addedNodes[0].tagName ==  "IMG")
            console.log("成功添加一张img");
       }
         if(mutation.removedNodes[0]!=null)
        {
           console.log(mutation.removedNodes);
           if(mutation.removedNodes[0].tagName ==  "IMG")
           {
              var href = location.href; //当前路径http://localhost:8080/StudyWeb/sx/qq.jsp
              //console.log(href);
              href = href.substring(0,href.lastIndexOf("/")+1); //http://localhost:8080/StudyWeb/sx/
            // console.log(href);
              var imgSrc =mutation.removedNodes[0].src;   //http://localhost:8080/StudyWeb/sx/upload/2018/06/20/59.png
             imgSrc = imgSrc.replace(href,''); //    upload/2018/06/20/448.png
              $.ajax({
                 type: "POST",
                 url: "getImg.action",  
                 data:{imgSrc:imgSrc,href:href,
                 flag:1},
                 success: function(msg){ alert(msg); } //请求成功后的回调函数
               });
            }
        }
      });  
    }); 
    // 配置观察选项:
    var config = { attributes: true, childList: true, characterData: true ,subtree:true};
    // 传入目标节点和观察选项
    observer.observe(target, config);
    
    $(".sit").click(function(){
    	
        var content = $('#summernote').summernote('code');
        if($('#summernote').summernote("isEmpty")){
        	alert('请输入评论后继续！');return false;
        }
         $.ajax({
            url: "getComment.action",
            data: {content:content,
            	commId:commId,
            	flag:4
            	},
            type: 'POST',
            success: function (msg) {
              if(msg.row!=0) {
            	  alert('写入txt文件成功！');
            	  window.location.href="main.jsp";
              }
            },
            error:function(){
              alert("提交失败！");
            }
        });
    });
    
    $(".sib").click(function(){
    	$.ajax({
    		url:"getComment.action",
    		type:"post",
    		dataType:"json",
    		data:{commId:commId,flag:'8'},
    	success:function(msg){
    		if(msg.row!=0) window.history.go(-1);
    	}
    	});
    });
});


$(function(){
	var theme=$("#do_themeid");
	var other_btn=$(".other_bn");
	function loadBtn(data){
		if(data!=null){
			for(var i=0;i<data.length;i++){
                var btn=other_btn.clone();
				btn.attr("style","margin-left:2%;display:block;float:left");
				btn.find(".btn_type").text(data[i].themeType);
				btn.find(".type_id").val(data[i].id);
				$(".btn_type").click(function(){
					
					var type=$(this).parent().find(".type_id").val();
					//点击类型按钮弹出的类型
					$.ajax({
						url:"getTheme.action",
						type:"post",	
					dataType:"json",
					data:{
						typeId:type,flag:2,opr:'1'
					},
					success:function(msg){
						alert(msg.msg);
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
	
	function loadType(){
		$.ajax({
			url:"getType.action",
			type:"post",
			dataType:"json",
			success:function(data){		
				if(data!=null) loadBtn(data.list);		
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
});
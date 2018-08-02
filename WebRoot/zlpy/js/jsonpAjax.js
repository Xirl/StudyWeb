$(function(){
	var count=0;
	//当键盘键被松开时发送Ajax获取数据
		$(document).on('keyup','input[name="find"]',function(){
			
			var keywords = $(this).val();
			var textId=$(this).attr("id");
			var divId=$(this).next().attr("id");
			var questionId=0;
			if (keywords=='') { $("#"+divId).hide(); return };
			$.ajax({
				url: 'goods/findQue.action?queName='+keywords,
				dataType: 'json',
				type:'post',
				// jsonpCallback: 'fun', //回调函数的参数名(键值)key
//				beforeSend:function(){
//					$('#word').append('<div>正在加载。。</div>');
//				},
				success:function(data){
					$("#"+divId).empty().show();
					if (data==null || data.length<1)
					{
						$("#"+divId).append('<div class="error">Not find  "' + keywords + '"</div>');
					}else{
						$.each(data, function(i,ele){
							$("#"+divId).append('<div class="click_work"><span id='+ele.queId+'>'+ele.questionPaperName+'</span></div>');
						})
					}
				},
				error:function(){
					$("#"+divId).empty().show();
					$("#"+divId).append('<div class="click_work">Fail "' + keywords + '"</div>');
				}
			})
		})
//点击搜索数据复制给搜索框
		$(document).on('click','.click_work',function(){
			var word = $(this).text();
			$(this).parent().prev().val(word);
			//$('.text').val(word);
			$(this).parent().hide();
			questionId=$(this).children().attr("id");
			$(this).parent().prev().attr("class",questionId);
			// $('#texe').trigger('click');触发搜索事件
			onType($(this).children("span").attr("id"),$(this).parent().attr("id"));
		});
		$(document).on("change","select[name='type']",function(){
			var selectId=$(this).attr("id");
			$.ajax({
				url:'goods/topicCount.action?queId='+questionId+'&typeId='+$(this).val(),
				dataType:'json',
				type:'post',
				success:function(data){
					count=data.length;
					if(data!=null && data.length>0){
						var text=$("#"+selectId).next();
						if(text.val()>count){
							text.val(count);
							alert("该类型题目只有"+count+"条");
						}
						text.attr("placeholder","共有该类型题目共"+count+"条")
					}
				}
			});
		});
		$(document).on("blur","input[name='num']",function(){
			var patrn = /^(-)?\d+(\.\d+)?$/;
			if(patrn.exec($(this).val())==null || patrn.exec($(this).val())==""){
				$(this).val(1);
			}else if($(this).val()>count){
				$(this).val(count);
			}
		});
		$(document).on("click","input[value='取消']",function(){
			$(this).parent().remove();
			
		});
	})
function onType(_val,_id){
	$.ajax({
		url:'goods/topicType.action?statu=type&queId='+_val,
		dataType: 'json',
		type:'post',
		success:function(data){
			if(data!=null && data.length>0){
				$("#"+_id).next().html('<option value="" >--请选择类型--</option>');
				$.each(data,function(i,ele){
					$("#"+_id).next().append('<option value="'+ele.typeId+'" >'+ele.typeName+'</option>');
				});
			}
		},
		error:function(request,msg,e){
			alert('错误：'+request.responseText);
		}
	});
}
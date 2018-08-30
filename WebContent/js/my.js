function navClick(index){
	//找到所有的导航条项目
	var nodes =$("li[name='navName']");
	//清空li的所有的class
	for(var i=0;i<3;i++){
		nodes.eq(i).removeClass("active");
	}
	//给当前点击的的导航项目加上class效果
	nodes.eq(index).addClass("active");
}
 
var timer;
var hideTimer;
var t=61;
function getValidateCode(){
	//防止快速点击产生的bug
	if (t!=61){
		return;
	}
	//获取验证码的按钮
	timer=setInterval("codeStart()",1000);
	//防止快速点击产生的bug
	t=60;
	//请求后台服务
	 $.ajax({
	     type: "POST",
	     url: "getLoginCode.action",
	     dataType: "text",
	     success: function(result){
	            if(result=="success"){
	            	$('#code').popover('show');
	            	hideTimer=window.setInterval("popoverHide()",3000);
	            }else{
	            	alert(result);
	            }
	        },
	     Error: function(result){
	     	alert("请求失败。" + result);
	     }
	});
}
//让提示验证码的提示框自动消失
function popoverHide(){
	$('#code').popover('hide');
	window.clearInterval(hideTimer);
}
function codeStart(){
	if(t==1){
		t=61;
		window.clearInterval(timer);
		$('#code').removeAttr("disabled"); 
		$('#code').text("获取验证码");
	}else{
		t--;
		$('#code').text("获取验证码("+t+"s)");
		$('#code').attr("disabled","disabled"); 
	}
}


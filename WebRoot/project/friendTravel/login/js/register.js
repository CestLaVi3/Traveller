// JavaScript Document
function login(){
	document.getElementById("login_mb").style.display="block";
	document.getElementById("login_main").style.display="block";
}
function loginClose(){
	document.getElementById("login_mb").style.display="none";
	document.getElementById("login_main").style.display="none";
}
function changeUserName(){
	document.getElementById("userName").focus();
	document.getElementById("userName").select();
	document.getElementById("userName").style.backgroundImage="url(images/login_username2.jpg)";
}
function changePassword(){
	document.getElementById("password").focus();
	document.getElementById("password").select();
	document.getElementById("password").style.backgroundImage="url(images/login_password2.jpg)";
}
function returnUserName(){
	document.getElementById("userName").style.backgroundImage="url(images/login_username.jpg)";
}
function returnPassword(){
	document.getElementById("password").style.backgroundImage="url(images/login_password.jpg)";
}

function changeImg(){    
	 var imgSrc = $("#code_img");          
	 imgSrc.attr("src","../../../servlet/verifyCodeServlet?temp="+Math.floor(Math.random() * ( 10000 + 1)));     
}

function register(){
	debugger;
	var username=$("#username").val();
	var password=$("#password").val();
	var password2=$("#password2").val();
	var yzm=$("#yzm").val();
	if(username.length==0||!f_check_email(username)){
		$("#usernamelab").html("用户名不符合规范");
		$("#username")[0].focus();
		$("#username")[0].select();
		return;
	}
	if(password.length>=10||password.length==0||!f_check_ZhOrNumOrLett(password)){
		$("#passwordlab").html("密码不符合规范");
		$("#password")[0].focus();
		$("#password")[0].select();
		return;
	}
	if(!password2==password){
		$("#password2lab").html("密码不一致");
		$("#password2")[0].focus();
		$("#password2")[0].select();
		return;
	}
	if(yzm.length==0){
		$("#yzmlab").html("验证码不能为空");
		$("#yzm")[0].focus();
		$("#yzm")[0].select();
		return;
	}
	var str={"email":username,"password":password,"validateCode":yzm};
	$.post("../../../loginController/register.do",{"paramter":JSON.stringify(str)},callback2,"json");
}
function callback2(ret){
	debugger;
	if(ret.state==0){
		$("#usernamelab").html("邮箱存在");
		$("#username")[0].focus();
		$("#username")[0].select();
		return;
	}
	if(ret.state==1){
		$("#yzmlab").html("验证码错误");
		$("#yzm")[0].focus();
		$("#yzm")[0].select();
		return;
	}
	if(ret.state==2){
		alert("注册成功");
		return;
	}
}
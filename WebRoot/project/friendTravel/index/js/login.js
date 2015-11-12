// JavaScript Document
var user=null;
var userId=null;
var stagtegyName=null;
var travelDescName=null;
function init(){
	$.post("../../../mainController/findMainHref.do",{},callback,"json");
	$.post("../../../scienceSpotController/findMainSpot.do",{},showScenic,"json");
	$.post("../../../activeController/findActiveType.do",{},showActive,"json");	
	$.post("../../../mainController/findSession.do",{},showUser,"json");
}
/*iframe自适应*/
function SetWinHeight(obj)
{
    var iframeMain=obj;
    if (document.getElementById)
    {
        if (iframeMain && !window.opera)
        {
            if (iframeMain.contentDocument && iframeMain.contentDocument.body.offsetHeight) 
               iframeMain.height = iframeMain.contentDocument.body.offsetHeight+50; 
            else if(iframeMain.Document && iframeMain.Document.body.scrollHeight)
               iframeMain.height = iframeMain.Document.body.scrollHeight+50;
        }
    }
}
//显示景区
function showScenic(ret)
{	
	var arrSc=ret.scienceSpot;
	var arrSp=ret.spot;
	var scenic_ul=document.getElementById("scenic_ul");
	scenic_ul.innerHTML="";
	var count=0;
	for(var i=0;i<arrSc.length;i++)
	{
		var li1=document.createElement("li");
		var a1=document.createElement("a");
		a1.href="../scenicSpot/Area.html?scenicSpot="+arrSc[i];
		a1.target="iframeMain";
		var p1=document.createElement("p");
		p1.className="P1";
		p1.innerHTML=arrSc[i].spotName;
		a1.appendChild(p1);
		li1.appendChild(a1);
  	    var ul=document.createElement("ul");
		for(var m=count;m<arrSp.length;m++)
		{
		  if(arrSc[i].spotId==arrSp[m].spotId)
		  {
			var li2=document.createElement("li");
			var a2=document.createElement("a");
		    a2.href="../scenicSpot/Spot.html?"+arrSp[m];
		    a2.target="iframeMain";
			var p2=document.createElement("p");
		    p2.className="P1";
			p2.innerHTML=arrSp[m].spotName;
		    a2.appendChild(p2);
			li2.appendChild(a2);
			ul.appendChild(li2);
		  }
		  else
			{
			  count=m;
			  m=arrSp.length;
			}
		 }
		li1.appendChild(ul);
		scenic_ul.appendChild(li1);
	 }
}
var activity_a=["../activity/photoPK.html","../activity/deliciousFood.html","../activity/presentChange/redemption.html","../activity/groupTour.html"];
//显示活动
function showActive(ret)
{
	var arr=ret.value;		
	var webActive=document.getElementById("webActive");
	var selfActive=document.getElementById("selfActive");
	for(var i=0;i<arr.length;i++)
	{	
		var li=document.createElement("li");
		var a=document.createElement("a");
		a.target="iframeMain";
		var p=document.createElement("p");		
		p.innerHTML=arr[i].typeName;
		if(arr[i].status=="0"){
			a.href=activity_a[i];
			a.appendChild(p);
			li.appendChild(a);
			webActive.appendChild(li);
		}
		else if(arr[i].status=="1"){
			a.href="../activity/selfActivity.html";
			a.appendChild(p);
			li.appendChild(a);
			selfActive.appendChild(li);
		}
	}
}

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
function fexit(){
	$.post("../../../loginController/exit.do",{},exited,"json");
	document.getElementById("index_loginRight").style.display="none";
	document.getElementById("index_headRight").style.display="block";
}
function exited(){
	
}
function loginUser(){
	nowLogin();
} 

function changeImg(){    
 	 var imgSrc = $("#code_img");          
 	 imgSrc.attr("src","../../../servlet/verifyCodeServlet?temp="+Math.floor(Math.random() * ( 10000 + 1)));     
}   

function search(){
	debugger;
	var val=$("#select").val();
	var a=$("#searcha")[0];
	if(val==0){
		stagtegyName=$("#index_searchInput").val();
		a.href="../stagtegy/strategyHome.html";
	}
	if(val==1){
		travelDescName=$("#index_searchInput").val();
		a.href="../travel/travelsHome.html";
	}
}

/*登录*/
function nowLogin(){
	var username=$("#userName").val();
	var password=$("#password").val();
	var yzm=$("#yzm").val();
	if(username.length==0||!f_check_email(username)){
		$("#message").html("用户名不符合规范");
		$("#userName")[0].focus();
		$("#userName")[0].select();
		return;
	}
	if(password.length>=10||password.length==0||!f_check_ZhOrNumOrLett(password)){
		$("#message").html("密码不符合规范");
		$("#password")[0].focus();
		$("#password")[0].select();
		return;
	}
	if(yzm.length==0){
		$("#message").html("验证码不能为空");
		$("#yzm")[0].focus();
		$("#yzm")[0].select();
		return;
	}
	var str={"loginName":username,"password":password,"validateCode":yzm};
	$.post("../../../loginController/login.do",{"paramter":JSON.stringify(str)},callback1,"json");
	
}
function showUser(ret){
	if(ret.state==1){
		user=ret.value;
		userId = user.userId;
		$("#petName").html(user.petName);
		$("#userImg").attr("src","../../../"+user.imageUrl);
		document.getElementById("index_loginRight").style.display="block";
		document.getElementById("index_headRight").style.display="none";
		loginClose();
	}
}
function callback1(ret){
	debugger;
	if(ret.state==0){
		alert(ret.state);
		$("#message").html("用户名错误");
		$("#userName")[0].focus();
		$("#userName")[0].select();
		return;
	}
	if(ret.state==1){
		$("#message").html("密码错误");
		$("#password")[0].focus();
		$("#password")[0].select();
		return;
	}
	if(ret.state==2){
		$("#message").html("验证码错误");
		$("#yzm")[0].focus();
		$("#yzm")[0].select();
		return;
	}
	if(ret.state==3){
		user=ret.value;
		$("#petName").html(user.petName);
		$("#userImg").attr("src","../../../"+user.imageUrl);
		document.getElementById("index_loginRight").style.display="block";
		document.getElementById("index_headRight").style.display="none";
		loginClose();
	}
	if(ret.state==4){
		window.location.href="manager.html";
	}
}


/*显示链接*/
function callback(ret)
{
	var fLink=ret.value;
	var index_buttomMain=document.getElementById("index_buttomMain");
	index_buttomMain.innerHTML="";
	for(var i=0;i<fLink.length;i++)
	 {
		 var a=document.createElement("a");
		 a.href=fLink[i].hrefUrl;
		 a.innerHTML=fLink[i].hrefName;
		 index_buttomMain.appendChild(a);
	 }
	
}
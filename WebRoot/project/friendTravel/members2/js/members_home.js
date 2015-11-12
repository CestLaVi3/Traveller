window.onload=membersIf;
var inf=window.top.user;
debugger;
var otherId=GetQueryString("userId");
function membersIf(){
	if(otherId==null){
		membersHome();
	}else{
		otherUserPost();
	}
}

function otherUserPost(){
	$.post("../../../userController/findUserByUserId.do",{"paramter":JSON.stringify({"userId":otherId})},otherUserInf,"json");
}
function otherUserInf(ret){
	debugger;
	inf=ret.value;
	var li=["他的主页","他的朋友","他的游记","他的攻略","他的活动","他的信息","他的收藏"];
	for(var i=0;i<7;i++){
		document.getElementsByClassName("menu_allLi")[i].getElementsByTagName("a")[0].innerHTML=li[i];
	}
	membersHome();
}
function membersHome(){
	debugger;
	
	document.getElementById("homeHeadImg").src="../../../"+inf.imageUrl;
	document.getElementById("attNum").innerHTML=inf.attentionNum;
	document.getElementById("funsNum").innerHTML=inf.funsNum;
	document.getElementById("visitNum").innerHTML=inf.vistNum;
}
//接参
function GetQueryString(name)
{debugger;
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

// JavaScript Document
document.addEventListener("click",hiddeUserInfo,true);
//显示已报名
function showUserInfo()
{
	var user=document.getElementById("userInfo");
	user.style.display="block";	                                                                 
}
//隐藏已报名
function hiddeUserInfo()
{
	var user=document.getElementById("userInfo");
	user.style.display="none";
}


// JavaScript Document
function init(){
	debugger;
	var src=GetQueryString("imgSrc");
	var img = document.getElementById("bgImg");
		img.src=src;
	commentInit(0);
}

//解析超链接传递的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)
	 return  unescape(r[2]); 
	 return null;
}

function showMyComment()
{
	var allComment=document.getElementById("allComment");
	allComment.style.display="none";
	var comment_ul=document.getElementById("comment_ul1");
	comment_ul.firstChild.style.backgroundColor="white";
	comment_ul.firstChild.style.color="#999";
	var myComment=document.getElementById("myComment");
	myComment.style.display="block";
	comment_ul.lastChild.style.backgroundColor="#e2e2e2";
	comment_ul.lastChild.style.color="#499fe8";
	var img=document.getElementById("comment_img");
	img.style.display="block";
}
function showAllComment()
{
	var allComment=document.getElementById("allComment");
	allComment.style.display="block";
	var comment_ul=document.getElementById("comment_ul1");
	comment_ul.firstChild.style.backgroundColor="#e2e2e2";
	comment_ul.firstChild.style.color="#499fe8";
	var myComment=document.getElementById("myComment");
	myComment.style.display="none";
	comment_ul.lastChild.style.backgroundColor="white";
	comment_ul.lastChild.style.color="#999";
	var img=document.getElementById("comment_img");
	img.style.display="none";
}
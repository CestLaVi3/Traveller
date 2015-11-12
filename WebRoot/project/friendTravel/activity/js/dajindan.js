// JavaScript Document
var status=0;
function shuidan(){
	var img=event.srcElement;
	if(status==0){
		img.src="../../../../upload/activity/images/present/shuidan.gif";
		status=1;
		document.getElementById("tishi").style.display="block";
		var p=document.getElementById("tishi_p");
		p.innerHTML="很遗憾，你没有中奖";
		return;
	}
	if(status==1){
		var p=document.getElementById("tishi_p");
		p.innerHTML="你的机会已用尽";
		document.getElementById("tishi").style.display="block";
	}
}
function fanhui(){
	document.getElementById("tishi").style.display="none";
}
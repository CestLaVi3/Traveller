// JavaScript Document
var time;
var x = 0;
var status = 0;

function init() {
	time = setInterval("runToRight()", 100);
	$.post("../../../activeController/findActiveType.do",{},showActivity,"json");
	$.post("../../../activeController/findTemByCollect.do",{"paramter":JSON.stringify({"count":7,"activeType":5})},showSelfActivity,"json");

}
var webActivity_url = ["photoPK.html", "deliciousFood.html","presentChange/redemption.html", "groupTour.html" ];
//显示活动
function showActivity(ret)
{
	showWebActivity(ret);
}
// 显示站内活动
function showWebActivity(ret) 
{
	var activity=ret.value;
	var webActivity_ul = document.getElementById("activity_ul");
	for ( var i = 0; i < activity.length; i++)
	 {
	   if(activity[i].status=="0")
		{
		var li = document.createElement("li");
		var a = document.createElement("a");
		a.href = webActivity_url[i];
		var img = document.createElement("img");
		img.src = "../../../"+activity[i].imageUrl;
		var div1 = document.createElement("div");
		div1.className = "intro";
		var p1 = document.createElement("p");
		p1.className = "intro_p1";
		var txt1 = document.createTextNode(activity[i].typeDesc);
		p1.appendChild(txt1);
		var p2 = document.createElement("p");
		p2.className = "intro_p2";
		var txt2 = document.createTextNode(activity[i].typeName);
		p2.appendChild(txt2);
		var div2 = document.createElement("div");
		div1.appendChild(p1);
		div1.appendChild(p2);
		div1.appendChild(div2);
		a.appendChild(img);
		a.appendChild(div1);
		li.appendChild(a);
		webActivity_ul.appendChild(li);
		}
	}
}
// 显示自助活动
function showSelfActivity(ret) {
	debugger;
	var arr=ret.value;
	var div = document.getElementById("selfActivity");
	var ul=document.createElement("ul");
	for(var i=0;i<arr.length;i++)
	{
		var li=document.createElement("li");
		var a=document.createElement("a");
		a.href="partnerTripApply.html?sctiveId="+arr[i].activeId;
		a.innerHTML=arr[i].activeName;
		li.appendChild(a);
		ul.appendChild(li);
	}
	div.appendChild(ul);
}
// 向右跑
function runToRight() {
	var image = document.getElementById("image");
	if (status == 0) {
		image.src = "../../../upload/activity/images/1.png";
		status = 1;
	} else if (status == 1) {
		image.src = "../../../upload/activity/images/2.png";
		status = 2;
	} else if (status == 2) {
		image.src = "../../../upload/activity/images/1.png";
		status = 3;
	} else {
		image.src = "../../../upload/activity/images/3.png";
		status = 0;
	}
	image.style.left = x + "px";
	x = x + 5;
	if (x == 750) {
		clearInterval(time);
		status = 0;
		time = setInterval("runToLeft()", 100);
	}
}
// 向左跑
function runToLeft() {

	var image = document.getElementById("image");
	if (status == 0) {
		image.src = "../../../upload/activity/images/4.png";
		status = 1;
	} else if (status == 1) {
		image.src = "../../../upload/activity/images/5.png";
		status = 2;
	} else if (status == 2) {
		image.src = "../../../upload/activity/images/4.png";
		status = 3;
	} else {
		image.src = "../../../upload/activity/images/6.png";
		status = 0;
	}
	image.style.left = x + "px";
	x = x - 5;
	if (x == -5) {
		clearInterval(time);
		status = 0;
		time = setInterval("runToRight()", 100);
	}
}
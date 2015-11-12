// JavaScript Document
var activeId=GetQueryString("activeId");//作品ID
var pageNum=0;  //作品当前页数
var pageNum1=0; //评论当前页数
var pageCount=6;//每页作品个数
var commentType=3; //评论类型
var number=0;
var pageNumber=0;
var ret1 = null;
function init()
{
	debugger;
	getImages();
	$.post("../../../activeController/findActiveById.do",{"paramter":JSON.stringify({"activeId":activeId})},getActiveTitle,"json");
	$.post("../../../activeController/findPhotoActive.do",{"paramter":JSON.stringify({"count":9})},getPastActive,"json");
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
//获取本次活动信息
function getActiveTitle(ret)
{
	var arr=ret.value;
	var div=document.getElementById("joinNum");
	div.innerHTML="已有"+arr.joinNum+"人参与<br/>已有"+arr.collectNum+"人关注";
	var title=document.getElementById("title");
	title.innerHTML=arr.activeName;
	var sp=document.getElementById("activeTime");
	sp.innerHTML="活动时间:"+arr.startDate+"至"+arr.endDate;
	var time=document.getElementById("status");
	if(arr.status==2)
	{
		time.innerHTML="已经关闭";
	}
	
}
//获取参赛作品
function getImages()
{
	$.post("../../../activeController/findActiveJoinByPage.do",{"paramter":JSON.stringify({"pageNum":pageNum,"pageCount":pageCount,"activeId":activeId})},showImages,"json");
}
//显示参赛作品
function showImages(ret)
{
	debugger;
	ret1=ret;
	var num=ret.count%6;
	var sumNum=document.getElementById("sumNum");
		sumNum.innerHTMl="";
	if(num==0&&ret.count>=6){
		sumNum.innerHTML=Math.floor(ret.count/6);
	}else{
		sumNum.innerHTML=Math.floor(ret.count/6)+1;
	}
	var arr=ret.value;
	
	var div=document.getElementById("bigPicture");
		div.innerHTML="";
		div.innerHTML+='<img src="../../../upload/activity/images/photoPK/photoPKDetail/2.png" class="img1"/>';
	var p=document.createElement("p");
		p.innerHTML="人气值:<span>"+arr[0].praiseNum+"</span>";
		div.appendChild(p);
	var img=document.createElement("img");
	var images=JSON.parse(arr[0].images).images;
		img.src="../../../"+images[0];
		img.className="img2";
		div.appendChild(img);
	var div1=document.createElement("div");
	var p1=document.createElement("p");
		p1.innerHTML="参赛人:<span>"+arr[0].petName+"</span>";
		div1.appendChild(p1);
	var p2=document.createElement("p");
		p2.innerHTML="作品简述:<span>"+arr[0].joinDesc+"</span>";
		div1.appendChild(p2);
		div.appendChild(div1);
	var ul=document.getElementById("photo");
		ul.innerHTML="";
	for(var i=0;i<arr.length;i++)
	{
		var li=document.createElement("li");
		var img=document.createElement("img");
		img.src="../../../"+JSON.parse(arr[i].images).images[0];
		img.onclick=showBigImg;
		li.appendChild(img);
		var p1=document.createElement("p");
		p1.innerHTML="参赛者"+"<span>"+arr[i].petName+"</span>";
		li.appendChild(p1);
		var p2=document.createElement("p");
		p2.innerHTML="作品简述"+"<span>"+arr[i].joinDesc+"</span>";
		li.appendChild(p2);
		ul.appendChild(li);
	}
}
//获取往期活动
function getPastActive(ret)
{
	var arr=ret.value;
	var div=document.getElementById("pastTitle");
	var ul=document.createElement("ul");
	for(var i=0;i<arr.length;i++)
	{
		var li=document.createElement("li");
		var img=document.createElement("img");
		img.src="../../../upload/activity/images/photoPK/photoPKDetail/images/"+(i+1)+".png";
		li.appendChild(img);
		var p=document.createElement("p");
		p.innerHTML="第"+(i+1)+"季:<a href=photoPkDetail.html?activeId="+arr[i].activeId+">"+arr[i].activeName+"</a>";
		li.appendChild(p);
		ul.appendChild(li);
	}
	div.appendChild(ul);
}}
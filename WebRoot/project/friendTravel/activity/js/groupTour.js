// JavaScript Document
var pageNum=1;
var pageCount=6;
var sumCount;
var warn_marginLeft=0;
function init()
{   
	setInterval(change,50);
	$.post("../../../scienceSpotController/findSpotByCount.do", {"paramter" : JSON.stringify({"count" : "all"})}, showAllScenic, "json");
  	$.post("../../../activeController/findTeamByCollect.do",{"paramter":JSON.stringify({"pageNum":pageNum,"pageCount":pageCount,"activeType":4,"spotId":0,"planDate":""})},showOtherTrip,"json");
	
}
// 信息滚动
function change(){
	var warn=document.getElementById("dayPrice");
	 warn_marginLeft-=5;
	 warn.style.marginLeft = warn_marginLeft + "px";
	if(warn_marginLeft == -800)
		warn_marginLeft = 600;
}
// 查看酒店详细信息
function getTourDetail()
{
	if(window.parent.user==null)
	 {
	   if(window.confirm("查看详情需要先登录，立即登录？"))
	   {
	    window.parent.login();
	   }
	   return;
	 }
	var a=event.srcElement;
	// window.parent.setId=a.parentNode.getAttribute("spotId");
	window.parent.setId=a.parentNode.getAttribute("activeId");
	window.location.href="hotelDetail.html";
}
// 显示所有城市
function showAllScenic(ret)
{
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}	
	var arr = ret.value;
	var sel = document.getElementById("spot");
	sel.className="city";
	for ( var i = 0; i < arr.length; i++) {
		 var option = document.createElement("option");
		option.value=arr[i].spotId;
		option.innerHTML = arr[i].spotName;
		sel.appendChild(option);
	}
}
// 传参
function getOtherTrip()
  {
	document.getElementById("nowPage").value=pageNum;
	var spotId=$("#spot option:selected").val();
  	$.post("../../../activeController/findTeamByCollect.do",{"paramter":JSON.stringify({"pageNum":pageNum,"pageCount":pageCount,"activeType":4,"spotId":spotId,"planDate":""})},showOtherTrip,"json");	
}
// 天天特价信息
function showOtherTrip(ret)
{
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
   var arr=ret.value;
   sumCount=(ret.count%pageCount)==0?(ret.count/pageCount):parseInt(ret.count/pageCount)+1;
   $("#sumPage").html(sumCount);
   var ul=document.getElementById("otherTrip");
   ul.innerHTML="";
   for(var i=0;i<arr.length;i++)
   {
	  var li=document.createElement("li");
	  var a=document.createElement("a");
	  a.onclick=getTourDetail;
	  a.setAttribute("activeId",arr[i].activeId);
	  a.setAttribute("spotId",arr[i].spotId);
	  var img=document.createElement("img");
	  img.src="../../../"+JSON.parse(arr[i].images).images[2];
	  a.appendChild(img);
	  var p1=document.createElement("p");
	  p1.innerHTML="【"+arr[i].spotName+"】";
	  a.appendChild(p1);
	  var p2=document.createElement("p");
	  p2.innerHTML=arr[i].voteDesc;
	  a.appendChild(p2);
	  var sDiv=document.createElement("div");
	  sDiv.className="look";
      sDiv.innerHTML="去看看";
      var sp3=document.createElement("span");
      sp3.className="sp3";
      sp3.innerHTML="趣旅价："+"<span class='price'>"+"￥"+arr[i].collectNum+"</span>";
	  a.appendChild(sp3);
      a.appendChild(sDiv);
	  li.appendChild(a);
	  ul.appendChild(li); 
   }
}
// 首页
function firstPage() {
	debugger;
	pageNum = 1;
	getOtherTrip();
}
// 上一页
function pastPage() {
	if (pageNum == 1) {
		return;
	} else {
		pageNum--;
	}
	getOtherTrip();
}
// 下一页
function nextPage() {
	debugger;
	if (pageNum == sumCount) {
		return;
	} else {
		pageNum++;
	}
	getOtherTrip();
}
// 末页
function lastPage() {
	pageNum = sumCount;
	getOtherTrip();
}
// 跳转
function redirect() {
	pageNum = parseInt(document.getElementById("nowPage").value);
	if(pageNum>sumCount)
	{
		return;
	}
	getOtherTrip();
}
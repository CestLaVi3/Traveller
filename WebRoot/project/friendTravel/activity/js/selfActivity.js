// JavaScript Document
var count=8;  //活动显示个数
var pageNum=0;
var spotId=0;
var planDate="";

function init(){
	initSuggest();     //初始化推荐结伴
	nextPage();	       //初始化其他结伴
	initScienicSpot(); //初始化目的地下拉框
	
}

//初始化推荐结伴
function initSuggest(){
	$.post("../../../activeController/findTemByCollect.do",{"paramter":JSON.stringify({"count":count,"activeType":5})},initSuggestCallBack,"json");
}
function initSuggestCallBack(ret){
	var ul = document.getElementById("selfActivityList");
		ul.innerHTML="";
	for(var i = 0; i < ret.value.length; i++){
		var li = document.createElement("li");
		var a = document.createElement("a");
		var imageUrl = $.parseJSON(ret.value[i].images);
		var img = document.createElement("img");
			img.src="../../../"+imageUrl.images[0];
			img.alt="加载失败";
			img.onclick = jumpPage;
			img.setAttribute("activeId",ret.value[i].activeId);
			a.appendChild(img);
			li.appendChild(a);
		var a1 = document.createElement("a");
			a1.onclick = jumpPage;
			a1.setAttribute("activeId",ret.value[i].activeId);
			a1.innerHTML="<span>景点</span>"+ret.value[i].spotName;
			li.appendChild(a1);
		var a2 = document.createElement("a");
			a2.setAttribute("activeId",ret.value[i].activeId);
			a2.onclick=jumpPage;
			a2.innerHTML="<span>简介</span>"+ret.value[i].activeName;
			li.appendChild(a2);
		var span2 = document.createElement("span");
			span2.className="sp3";
			span2.innerHTML="出发";
		var span3 = document.createElement("span");
			span3.className="sp3";
			span3.innerHTML=ret.value[i].planDate;
		var p = document.createElement("p");
			p.innerHTML="已有<span class='sp1'>"+ret.value[i].joinNum+"</span>人报名";
			p.appendChild(span2);
			p.appendChild(span3);
			li.appendChild(p);
			ul.appendChild(li);
	}
}

//初始化其他结伴
function initOther(){
	$.post("../../../activeController/findTeamByCollect.do",{"paramter":JSON.stringify({"activeType":5,"pageCount":count,"pageNum":pageNum,"spotId":spotId,"planDate":planDate})},initOtherCallBack,"json");	
}

function initOtherCallBack(ret){
	debugger;
	var num=ret.count%8;
	var sumNum=document.getElementById("sumNum");
		sumNum.innerHTMl="";
	if(num==0&&ret.count>=8){
		sumNum.innerHTML=Math.floor(ret.count/8);
	}else{
		sumNum.innerHTML=Math.floor(ret.count/8)+1;
	}
	var $ul=$("#otherActivity");
		$ul.html("");
	for(var i = 0; i < ret.value.length; i ++){
	var	$li=$("<li></li");
	var	$a=$("<a></a>");
	var	$img=$("<img/>");
	var imageUrl1=$.parseJSON(ret.value[i].images);
		$img.attr("src","../../../"+imageUrl1.images[1]);
		$img.attr("alt","加载失败");
		$img.attr("activeId",ret.value[i].activeId);
		$img.bind("click",jumpPage);
		$a.append($img);
		$li.append($a);
	var	$a1=$("<a></a>");
		$a1.html("<span>景点</span>"+ret.value[i].spotName);
		$a1.attr("activeId",ret.value[i].activeId);
		$a1.bind("click",jumpPage);
		$li.append($a1);
	var $a2=$("<a></a>");
		$a2.attr("activeId",ret.value[i].activeId);	
		$a2.bind("click",jumpPage);
		$a2.html("<span>简介</span>"+ret.value[i].activeName);
		$li.append($a2);
	var $p=$("<p/>");
		$p.html("已有<span class='sp1'>"+ret.value[i].joinNum+"</span>人报名<span class='sp3'>出发</span><span class='sp3'>"+ret.value[i].planDate+"</span>");
		$li.append($p);
		$ul.append($li);
	}
}

/*
活动分页开始 
*/
//下一页（初始化参赛作品）
function nextPage(){
	var page=document.getElementById("pageNum");
	var sumNum=document.getElementById("sumNum").innerHTML;
	if(pageNum>=sumNum){
		return;	
	}
	pageNum++;
	page.value=pageNum;
	initOther();
	
	
}

//上一页
function pastPage(){
	debugger;
	var page=document.getElementById("pageNum");
	var sumNum=document.getElementById("sumNum").innerHTML;
	if(pageNum<=1){
		return;	
	}
	pageNum--;
	page.value=pageNum;
	initOther();
}

//末页
function lastPage(){
	var page=document.getElementById("sumNum");
	var page1=document.getElementById("pageNum");
	pageNum=parseInt(page.innerHTML);
	initOther();
	page1.value=pageNum;
}

//首页
function firstPage(){
	pageNum=1;
	var page1=document.getElementById("pageNum");
	initOther();
	page1.value=pageNum;
}

//跳转
function redirect(){
	var page=document.getElementById("pageNum");
	var sumNum=document.getElementById("sumNum").innerHTML;
	pageN=parseInt(page.value);
	if(pageN>sumNum||pageN<1){
		return;
	}
	pageNum=pageN;
	initOther();
}

/*
活动分页结束 
*/

//搜索活动
function search(){
	debugger;
	pageNum = 0;
	var date = $('#dd').datebox('getValue');
	var arr = date.split("/");
	if(date == ""){
		planDate="";
	}else{
		planDate = arr[2]+"/"+arr[0]+"/"+arr[1];
	}
	nextPage(); 
}

function initScienicSpot(){
	$.post("../../../scienceSpotController/findMainSpot.do",{},showScenic,"json");
}
//显示景区
function showScenic(ret){
	debugger;
	var $select=$("#selectPlace");
	for(var i = 0; i < ret.scienceSpot.length; i ++){
		var $option = $("<option/>");
		if(i == 0){
			$option.html("目的地");
			$option.attr("value","0");
		}else{
			$option.html(ret.scienceSpot[i-1].spotName);
			$option.attr("value",ret.scienceSpot[i-1].spotId);	
		}
		$select.append($option);
	}
}

//选择目的地
function selectPlace(){
	spotId = $("#selectPlace option:selected").val();
}

//发布结伴
function showPub()
{
	if(window.parent.user==null){
		var c = window.confirm("打开此页需用户登录，是否跳转到登录页面！");
		if(c){
			window.parent.login();
		}else{
			return;
		}
	}else{
		window.location.href = "addGroupTour.html";
	}
}

function jumpPage(){
	var activeId = event.srcElement.getAttribute("activeId");
	if(window.parent.user==null){
		var c = window.confirm("此页登录可查看，是否跳转到登录页！");
		if(c == true){
			window.parent.login();
		}else{
			return;
		}	
	}else{
		window.location.href="partnerTripApply.html?activeId="+activeId;
		
	}
	
}

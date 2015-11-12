var arrImg;
var arrlunbo=[];
var count=0;
var index=0;
var pageNum = 1;
var pageCount = 8;// 显示条数
var nowPage;
var sumCount;// 总条数
var spotId = 0;
var fitMonth = [];
var travelDays = [];
var stagtegyType = 0;
var travelDescId = 0;
var investNum = 0;
var hot = 1;// 最热
var time = 0;// 最新
var sortCon = 0;// 排序项
var user;
var stagtegyName=window.parent.stagtegyName;
function init() {
	showMonth();
	showDays();
	$.post("../../../stagtegyController/findByMonth.do", {}, getStategy,"json");
	$.post("../../../scienceSpotController/findMainSpot.do", {}, showHotScenic,"json");
	$.post("../../../stagtegyController/findStagtegy.do", {}, showScenicType,"json");
	var obj1={"pageNUm":1};
	$.post("../../../stagtegyController/findHotStagtegy.do", {"paramter":JSON.stringify(obj1)}, showHotPeople, "json");
	var obj={"pageNUm":8};
	$.post("../../../stagtegyController/findHotStagtegy.do", {"paramter":JSON.stringify(obj)}, showHotStrategy, "json");
	getStrategy();
}
var index = 0;
var lunboTime;
// 轮播
function getStategy(ret) {
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var ul = document.getElementById("advertImg");
	ul.innerHTML = "";
	arrImg = ret.value;
	lunbo1();
	lunbo();
	beginPlay();
}
function lunbo1()
{
	for(var i in arrImg)
	{
		arrlunbo.push(i);
	}
}
// 轮播
function lunbo() {
	var ul = document.getElementById("advertImg");
	ul.innerHTML="";
	for (var i in arrImg) {
		if(arrImg[arrlunbo[count]].length<=0)
		{
			count++;
			if(count==3)
			{
				count=0;
			}
			continue;
		}
		i=arrlunbo[count++];
		var li = document.createElement("li");
		var img = document.createElement("img");
		var images = JSON.parse(arrImg[i][0].images);
		img.src = "../../../" + images.images[0];
		img.id="bigImg";
		img.onmouseover = stopPlay;
		img.onmouseout = beginPlay;
		img.alt = "这是图片";
		var p = document.createElement("p");
		p.className = "month";
		p.innerHTML = i + "月";
		li.appendChild(img);
		li.appendChild(p);
		ul.appendChild(li);
		var hotScenic = document.getElementById("hotScenic");
		hotScenic.innerHTML="";
		var arrSta = arrImg[i];
		for ( var j = 0; j < arrSta.length-1; j++) {
			var li = document.createElement("li");
			var a = document.createElement("a");
			a.href = "strategy.html?strategyId=" + arrSta[j].stagtegyId;
			var img = document.createElement("img");
			var images = JSON.parse(arrSta[j].images);
			img.src = "../../../" + images.images[0];
			img.className = "bigImg";
			img.onmouseover=function a()
			{
				stopPlay();
				var img=document.getElementById("bigImg");
				img.src=event.srcElement.src;
			}
			img.onmouseout=function b()
			{
				beginPlay();
			}
			a.appendChild(img);
			li.appendChild(a);
			var p = document.createElement("p");
			p.className = "font";
			var span1 = document.createElement("span");
			span1.className = "address";
			span1.innerHTML = arrSta[j].spotName;
			var span2 = document.createElement("span");
			span2.className = "view";
			span2.innerHTML = arrSta[j].subComment;
			p.appendChild(span1);
			p.appendChild(span2);
			li.appendChild(p);
			hotScenic.appendChild(li);
		}
		if(count==arrlunbo.length)
		{
			count=0;
		}
		return;
	}
}
// 停止轮播
function stopPlay() {
	window.clearInterval(lunboTime);
}
// 开始轮播
function beginPlay() {
	lunboTime = window.setInterval("lunbo()", 2000);
}
// 显示热门景区搜索项
function showHotScenic(ret) {
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var arrSc = ret.scienceSpot;
	var p = document.getElementById("des");
	for ( var i = 0; i < arrSc.length; i++) {
		var span = document.createElement("span");
		span.innerHTML = arrSc[i].spotName;
		span.setAttribute("spotId", arrSc[i].spotId);
		span.onclick = selScenic;
		p.appendChild(span);
	}
	var span1 = document.createElement("span");
	span1.id = "more";
	span1.onclick = showMore;
	span1.innerHTML = "更多";
	p.appendChild(span1);
	var count1 = {"count" : "all"};
	$.post("../../../scienceSpotController/findSpotByCount.do", {"paramter" : JSON.stringify(count1)}, showAllScenic, "json");
}
// 显示景区下拉框
function showMore() {
	var cityselect = document.getElementById("cityselect");
	cityselect.style.display = "block";
	var more=document.getElementById("more");
	more.style.display="none";
}
// 显示所有景区
function showAllScenic(ret) {
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var arr = ret.value;
	var p = document.getElementById("des");
	var sel = document.createElement("select");
	sel.onchange=chcScenic;
	sel.id = "cityselect";
	var option = document.createElement("option");
	option.value=0;
	option.innerHTML = "请选择";
	sel.appendChild(option);
	for ( var i = 0; i < arr.length; i++) {
		var option = document.createElement("option");
		option.value=arr[i].spotId;
		option.innerHTML = arr[i].spotName;
		sel.appendChild(option);
	}
	p.appendChild(sel);
}
// 选择热门景区
function selScenic() {
	pageNum=1;
	var span = event.srcElement;
	var searchSce = document.getElementById("search");
	searchSce.style.display="block";
	var p = document.getElementById("des");
	var sp = document.getElementById("scenic");
	if (sp != null)
	 {
		searchSce.removeChild(sp);
	 }
	if (span == p.childNodes[1]) 
	 {
		spotId =0;
		if (searchSce.childNodes.length == 1) {
		  searchSce.style.display = "none";
		}
	 }
   else {
		spotId = span.getAttribute("spotId");
		sp = document.createElement("span");
		sp.className = "condition";
		sp.id = "scenic";
		sp.setAttribute("condition","des");
		searchSce.appendChild(sp);
		sp.innerHTML = span.innerHTML+ "<span onclick='delCondition()'>x</span>";
	}
	for ( var i = 1; i < p.childNodes.length; i++) {
		p.childNodes[i].style.backgroundColor = "white";
	}
	span.style.backgroundColor = "#00b38a";
  getStrategy();
}
//下拉框选择景区
function chcScenic()
{
	pageNum=1;
	var sel=event.srcElement;
	var searchSce = document.getElementById("search");
	searchSce.style.display="block";
	var p = document.getElementById("des");
	if(sel.selectedIndex!=0)
	{
		spotId = sel[sel.selectedIndex].value;
		var sp = document.getElementById("scenic");
		if (sp!= null)
		{
			searchSce.removeChild(sp);
		}
		sp = document.createElement("span");
		sp.className = "condition";
		sp.id = "scenic";
		sp.setAttribute("condition","des");
		searchSce.appendChild(sp);
		sp.innerHTML = sel[sel.selectedIndex].innerHTML+ "<span onclick='delCondition()'>x</span>";
	    for ( var i = 1; i < p.childNodes.length; i++)
	    {
		  p.childNodes[i].style.backgroundColor = "white";
		  if(sel[sel.selectedIndex].innerHTML==p.childNodes[i].innerHTML)
		  {
			  p.childNodes[i].style.backgroundColor = "#00b38a";
		  }
	    }  
	}
   getStrategy();
}

// 显示月份
function showMonth() {
	var month = document.getElementById("month");
	for ( var i = 1; i < 7; i++) {
		var span = document.createElement("span");
		span.innerHTML = 2 * i - 1 + "-" + 2 * i + "月";
		span.setAttribute("month", i);
		span.onclick = monthClick;
		month.appendChild(span);
	}
}
// 选择出游月份
function monthClick() {
	pageNum=1;
	var month = document.getElementById("month");
	var monthS = event.srcElement;
	for ( var i = 1; i < month.childNodes.length; i++) {
		if (monthS == month.childNodes[i]) {
			month.childNodes[i].style.backgroundColor = "#00b38a";
			var p = document.getElementById("search");
			p.style.display = "block";
			fitMonth = [];
			var sp = document.getElementById("monthS");
			if (sp != null) {
					p.removeChild(sp);
				}
			if (i == 1) {
				if (p.childNodes.length == 1) {
					p.style.display = "none";
				}
			} else {
					sp = document.createElement("span");
					sp.className = "condition";
					sp.id = "monthS";
					sp.setAttribute("condition", "month");
					p.appendChild(sp);
					var m = monthS.getAttribute("month");
					fitMonth.push(2 * m - 1);
					fitMonth.push(2 * m);
				sp.innerHTML = monthS.innerHTML+ "<span onclick='delCondition()'>x</span>";
			}
		}

		else {
			month.childNodes[i].style.backgroundColor = "white";
		}
	}
	getStrategy();
}
// 显示天数
function showDays() {
	var days = document.getElementById("day");
	for ( var i = 1; i < 6; i++) {
		var span = document.createElement("span");
		span.innerHTML = 3 * i - 2 + "-" + 3 * i + "天";
		span.setAttribute("day", i);
		span.onclick = dayClick;
		days.appendChild(span);
	}
	var sp1 = document.createElement("span");
	sp1.innerHTML = "15天以上";
	sp1.setAttribute("day", 6);
	sp1.onclick = dayClick;
	days.appendChild(sp1);
}
// 选择出游天数
function dayClick() {
	pageNum=1;
	var day = document.getElementById("day");
	var dayS = event.srcElement;
	for ( var i = 1; i < day.childNodes.length; i++) {
		if (dayS == day.childNodes[i]) {
			day.childNodes[i].style.backgroundColor = "#00b38a";
			var p = document.getElementById("search");
			p.style.display = "block";
			var sp = document.getElementById("dayS");
			travelDays = [];
			if (sp != null) {
					p.removeChild(sp);
				}
			if (i == 1) {
				if (p.childNodes.length == 1) {
					p.style.display = "none";
				}
			} else {
					sp = document.createElement("span");
					sp.className = "condition";
					sp.id = "dayS";
					sp.setAttribute("condition", "day");
					p.appendChild(sp);
					var m = dayS.getAttribute("day");
					if (m == 6) {
						travelDays.push(3 * m - 2);
					} else {
						travelDays.push(3 * m - 2);
						travelDays.push(3 * m - 1);
						travelDays.push(3 * m);
					}
				sp.innerHTML = dayS.innerHTML+ "<span onclick='delCondition()'>x</span>";
			}
		} else {
			day.childNodes[i].style.backgroundColor = "white";
		}
	}
	getStrategy();
}
// 显示景区类型
function showScenicType(ret) {
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var arr = ret.value;
	var p = document.getElementById("sta");
	for ( var i = 0; i < arr.length; i++) {
		var span = document.createElement("span");
		span.innerHTML = arr[i].typeName;
		span.setAttribute("stagtegyType",arr[i].typeId);
		span.onclick = selScenicType;
		p.appendChild(span);
	}
}
// 选择景区主题
function selScenicType() {
	pageNum=1;
	var span = event.srcElement;
	var p = document.getElementById("sta");
	var searchType = document.getElementById("search");
	searchType.style.display="block";
	var sp = document.getElementById("scenicType");
	if (sp!= null)
	   {
			searchType.removeChild(sp);
		}
	if (span == p.childNodes[1]) {
		stagtegyType = 0;
		if (searchType.childNodes.length == 1) {
				searchType.style.display = "none";
			}
	}
	else {
		stagtegyType = span.getAttribute("stagtegyType");
		sp = document.createElement("span");
		sp.className = "condition";
		sp.id = "scenicType";
		sp.setAttribute("condition","sta");
		searchType.appendChild(sp);
		sp.innerHTML = span.innerHTML+ "<span onclick='delCondition()'>x</span>";
	}
	for ( var i = 1; i < p.childNodes.length; i++) {
		p.childNodes[i].style.backgroundColor = "white";
	}
	span.style.backgroundColor = "#00b38a";
  getStrategy();
}
// 取消搜索条件
function delCondition() {
	pageNum=1;
	var p = document.getElementById("search");
	var sp = event.srcElement;
	var con = sp.parentNode.getAttribute("condition");
	if (con == "month") {
		fitMonth = [];
	} else if (con == "day") {
		travelDays = [];
	} else if (con == "des") {
		spotId = 0;
	} else if (con == "sta") {
		stagtegyType = 0;
	}
	var p1 = document.getElementById(con);
	p1.childNodes[1].style.backgroundColor = "#00b38a";
	for ( var i = 2; i < p1.childNodes.length; i++) {
		p1.childNodes[i].style.backgroundColor = "white";
	}
	p.removeChild(sp.parentNode);
	if (p.childNodes.length == 1) {
		p.style.display = "none";
	}
	getStrategy();
}
// 按热门排序
function selHot() {
	pageNum=1;
	hot = 1;
	time = 0;
	document.getElementById("hot").style.backgroundColor = "#00b38a";
	document.getElementById("new").style.backgroundColor = "white";
	getStrategy();
}
// 按时间排序
function selNew() {
	pageNum=1;
	time = 1;
	hot = 0;
	document.getElementById("new").style.backgroundColor = "#00b38a";
	document.getElementById("hot").style.backgroundColor = "white";
	getStrategy();
}
// 按照搜索条件刷新攻略
function getStrategy() {
	debugger;
	document.getElementById("nowPage").value=pageNum;
	var condition = '{';
	if (spotId != 0) {
		condition += '"spotId":"' + spotId + '",';
	}
	if (fitMonth.length > 0) {
		condition += '"fitMonth":"' + fitMonth[0] + ',' + fitMonth[1] + '",';
	}
	if (travelDays.length > 0) {
		condition += '"travelDays":"' + travelDays[0];
		if (travelDays.length > 1) {
			condition += ',' + travelDays[1] + ',' + travelDays[2];
		}
		condition += '",';
	}
	if (stagtegyType != 0) {
		condition += '"stagtegyType":"' + stagtegyType + '",';
	}
	if(!(stagtegyName==null)){
		condition += '"stagtegyName":"' + stagtegyName + '",';
	}
	condition = condition.substr(0, condition.length - 1);
	if (condition.length == 0) {
		condition = '{';
	}
	condition += '}';
	condition = JSON.parse(condition);
	var obj = {
		"pageNum" : pageNum,
		"pageCount" : pageCount,
		"sort" :
		  {
			"travelDescId" : time,
			"investNum" : hot
		  },
		"condition" : condition
	};
	$.post("../../../stagtegyController/findByConditionAndSot.do", {"paramter" : JSON.stringify(obj)}, showStragety, "json");
}
// 攻略展示
function showStragety(ret) {
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	sumCount=(ret.count);
	document.getElementById("sumPage").value =Math.ceil((ret.count) / pageCount);
	var arr = ret.value;
	var ul = document.getElementById("strategy_ul");
	ul.innerHTML="";
	for ( var i = 0; i < arr.length; i++) {
		var li = document.createElement("li");
		var a = document.createElement("a");
		a.setAttribute("stagtegyId",arr[i].stagtegyId);
		a.onclick=showStaDetail;
		var img1 = document.createElement("img");
		var images = JSON.parse(arr[i].images);
		img1.src="../../../"+images.images[0];
		img1.className = "photo";
		a.appendChild(img1);
		li.appendChild(a);
		var a1 = document.createElement("a");
		a1.setAttribute("stagtegyId",arr[i].stagtegyId);
		a1.onclick=showStaDetail;
		a1.className = "title";
		a1.innerHTML = arr[i].stagtegyTitle;
		li.appendChild(a1);
		var p1 = document.createElement("p");
		var sp1 = document.createElement("span");
		sp1.className = "writer";
		sp1.innerHTML = "来自" + arr[i].petName;
		p1.appendChild(sp1);
		var sp2 = document.createElement("span");
		sp2.className = "time";
		sp2.innerHTML = arr[i].createTime;
		p1.appendChild(sp2);
		var sp3 = document.createElement("span");
		sp3.className = "writer";
		sp3.innerHTML = arr[i].travelDays;
		p1.appendChild(sp3);
		li.appendChild(p1);
		var sp4 = document.createElement("span");
		sp4.className = "detail";
		sp4.innerHTML = "指南：" + arr[i].subComment;
		li.appendChild(sp4);
		var p2 = document.createElement("p");
		p2.className = "strategy_p";
		var img2 = document.createElement("img");
		img2.src = "../../../upload/strategy/images/liulan.png";
		img2.alt = "这是图片";
		p2.appendChild(img2);
		var sp5 = document.createElement("span");
		sp5.innerHTML = arr[i].investNum;
		p2.appendChild(sp5);
		var img4 = document.createElement("img");
		img4.src = "../../../upload/strategy/images/pinglun.jpg";
		img4.alt = "这是图片";
		p2.appendChild(img4);
		var sp7 = document.createElement("span");
		sp7.innerHTML = arr[i].commentNum;
		p2.appendChild(sp7);
		li.appendChild(p2);
		ul.appendChild(li);
	}
}
// 显示本周达人
function showHotPeople(ret) {
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var arr = ret.value;
	var div = document.getElementById("daren");
	for ( var i = 0; i < arr.length; i++) {
		var a = document.createElement("a");
		a.href = "#";
		var img = document.createElement("img");
		img.src = "../../../" + arr[i].imageUrl;
		a.appendChild(img);
		var p1 = document.createElement("p");
		p1.className = "p2";
		var sp1 = document.createElement("span");
		sp1.innerHTML = arr[i].petName;
		p1.appendChild(sp1);
		var sp2 = document.createElement("span");
		sp2.innerHTML = arr[i].spotName;
		p1.appendChild(sp2);
		var sp3 = document.createElement("span");
		p1.appendChild(sp3);
		a.appendChild(p1);
		div.appendChild(a);
		var p2 = document.createElement("p");
		p2.className = "p1";
		p2.innerHTML = arr[i].stagtegyTitle;
		div.appendChild(p2);
		var p3 = document.createElement("p");
		p3.innerHTML = arr[i].subComment;
		div.appendChild(p3);
	}
}
// 显示热门攻略
function showHotStrategy(ret) {
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var ul = document.getElementById("bang_ul");
	var arr = ret.value;
	for ( var i = 0; i < arr.length; i++) {
		var li = document.createElement("li");
		var sp1 = document.createElement("span");
		sp1.className = "paixu";
		sp1.innerHTML = (i + 1)+".";
		sp1.style.color="red";
		li.appendChild(sp1);
		var a = document.createElement("a");
		a.setAttribute("stagtegyId",arr[i].stagtegyId);
		a.onclick=showStaDetail;
		a.innerHTML = arr[i].stagtegyTitle;
		li.appendChild(a);
		var sp2 = document.createElement("span");
		sp2.className = "number";
		sp2.innerHTML = arr[i].investNum + "次浏览";
		li.appendChild(sp2);
		ul.appendChild(li);
	}
}
//查看攻略详情
function showStaDetail()
{
	 user=window.parent.user;
	 var a=event.srcElement;
	if(user==null)
	{
	  var mess=window.confirm("查看攻略详情，请先登录");
	  if(mess)
	  {
	   window.parent.login();
	  }
	  return;
	}
	 else
	 {
		a.href="strategy.html?stagtegyId="+a.getAttribute("stagtegyId");
	}
}
//写攻略
function writeSta()
{
	user=window.parent.user;
	if(user==null)
	{
		var rs=window.confirm("登陆后才能写攻略，立即登录");
		if(rs)
		 {
			 window.parent.login(); 
		 }	
	}
	else
	{
		window.location.href="addStrategy.html";
	}
}
//首页
function firstPage() {
	pageNum = 1;
	getStrategy();
}
//上一页
function pastPage() {
	if (pageNum == 1) {
		return;
	} else {
		pageNum--;
	}
	getStrategy();
}
//下一页
function nextPage() {
	if (pageNum == Math.ceil(sumCount/ pageCount)) {
		return;
	} else {
		pageNum++;
	}
	getStrategy();
}
//末页
function lastPage() {
	pageNum = Math.ceil(sumCount/ pageCount);
	getStrategy();
}
//跳转
function redirect() {
	pageNum = parseInt(document.getElementById("nowPage").value);
	if(pageNum>Math.ceil(sumCount/ pageCount))
	{
		return;
	}
	getStrategy();
}
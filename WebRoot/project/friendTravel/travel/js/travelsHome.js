
var spotId="";
var travelDescName=window.parent.travelDescName;
/*
初始化页面
*/
function init(){

	 initHotSpot();
	 initTravels();
	 initImg();
}

/*
 * 岛屿介绍区开始
 */
function initImg(){
	debugger;
	var ul = document.getElementById("mainImg").childNodes;
	for(var i=0;i<ul.length;i++){
		ul[i].addEventListener("mouseover",changeImg);
		ul[i].addEventListener("mouseout",function(){
			var bigImg = document.getElementById("rightImg").firstChild;
			bigImg.src="images/bg1.jpg";
			var bigImg = document.getElementById("rightImg").lastChild;
			bigImg.innerHTML = "马尔代夫是地处亚洲印度洋上的一个群岛国家，马尔代夫位于北纬4度，东经73度。总面积9万平方公里（含领海面积），陆地面积298平方公里。平均海拔1.8米。距离印度南部约600公里，距离斯里兰卡西南部约750公里。南北长820公里，东西宽130公里。由26组自然环礁、1192个珊瑚岛组成，构成20个环礁，分布在9万平方公里的海域内，其中有人定居岛屿有200座。";
		});
	}
}
function changeImg(){
	debugger;
	var ev = event || window.event;
	if(ev.target.tagName != "IMG" && ev.target.tagName != "DIV"){
		var src = ev.target.firstChild.src;
		var p = ev.target.lastChild.innerHTML;
	}
	else{
		var src = ev.target.parentNode.firstChild.src;
		var p = ev.target.parentNode.lastChild.innerHTML;
	}
	var bigImg = document.getElementById("rightImg");
	bigImg.firstChild.src = src;
	bigImg.lastChild.innerHTML = p;
}
/*
初始化热门目的地
*/
function initHotSpot(){
	var parm=JSON.stringify({"count":6});
	jQuery.post("../../../scienceSpotController/findSpotByCount.do",{"paramter":parm},initHotSpotBody,"json");
}

function initHotSpotBody(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;	
	}
	var ul=document.getElementById("left_ul");
	for(var i = 0;i < ret.value.length; i ++ ){
		var li=document.createElement("li");
		/*
		var a=document.createElement("a");
		var img=document.createElement("img");
		var imagesUrl=JSON.parse(ret.value[i].imageUrl);
			img.src="../../../"+imagesUrl.images[0];
			img.alt="这是图片";
			img.onclick=getSpotId;
			img.setAttribute("spotId",ret.value[i].spotId);
			img.className="left_img";
		*/
		var span=document.createElement("span");
		var a1=document.createElement("a");
			a1.onclick=getSpotId;
			a1.innerHTML=ret.value[i].spotName;
			a1.setAttribute("spotId",ret.value[i].spotId);
		//a.appendChild(img);
		//li.appendChild(a);
		span.appendChild(a1);
		li.appendChild(span);
		ul.appendChild(li);
	}
}
/*
初始化热门目的地结束
*/


/*
初始化游记
*/
function getSpotId(){
	debugger;
	var ul = document.getElementById("left_ul").childNodes;
	for(var i = 0;i < ul.length; i ++ ){
		var cura = ul[i].firstChild.firstChild;
		cura.style.borderBottom = "none";
	}
	var cur = event.srcElement;
	cur.style.borderBottom = "2px solid #00b38a";
	var page=document.getElementById("pageNum").value="0";
	spotId=event.srcElement.getAttribute("spotId");	
	
	
	initTravels();
}


//下一页
function initTravels(){
	var page=document.getElementById("pageNum");
	var pageNum=parseInt(page.value);
	var p=pageNum+1;
	var sumNum=document.getElementById("sumNum").value;
	if(pageNum==sumNum){
		alert("已经是最后一页！");
		return;	
	}
	if(travelDescName==null){
	if(spotId.length==0){
		var param={"pageNum":p,"pageCount":6,"spotId":0};
	}
	
	else{
		var param={"pageNum":p,"pageCount":6,"spotId":spotId};
	}
	}
	else{
	var param={"pageNum":p,"pageCount":6,"spotId":0,"descTitle":travelDescName};
}
	jQuery.post("../../../travelDescController/findDescByPage.do",{"paramter":JSON.stringify(param)},initTravelsCallBack,"json");
	pageNum++;
	page.value=pageNum;
}
//上一页
function pastPage(){
	debugger;
	var page=document.getElementById("pageNum");
	var pageNum=parseInt(page.value);
	var sumNum=document.getElementById("sumNum").value;
	if(pageNum==1){
		alert("已经是第一页！");
		return;	
	}
	pageNum--;
	if(spotId.length==0){
		var param={"pageNum":pageNum,"pageCount":6,"spotId":0};
	}
	else{
		var param={"pageNum":pageNum,"pageCount":6,"spotId":spotId};
	}
	jQuery.post("../../../travelDescController/findDescByPage.do",{"paramter":JSON.stringify(param)},initTravelsCallBack,"json");
	page.value=pageNum;
}	
//末页
function lastPage(){
	var page=document.getElementById("sumNum");
	var page1=document.getElementById("pageNum");
	var pageNum=parseInt(page.value);
	if(spotId.length==0){
		var param={"pageNum":pageNum,"pageCount":6,"spotId":0};
	}
	else{
		var param={"pageNum":pageNum,"pageCount":6,"spotId":spotId};
	}
	jQuery.post("../../../travelDescController/findDescByPage.do",{"paramter":JSON.stringify(param)},initTravelsCallBack,"json");
	page1.value=pageNum;
}
//首页
function firstPage(){
	var pageNum=1;
	var page1=document.getElementById("pageNum");
	if(spotId.length==0){
		var param={"pageNum":pageNum,"pageCount":6,"spotId":0};
	}
	else{
		var param={"pageNum":pageNum,"pageCount":6,"spotId":spotId};
	}
	jQuery.post("../../../travelDescController/findDescByPage.do",{"paramter":JSON.stringify(param)},initTravelsCallBack,"json");
	page1.value=pageNum;
}	
//跳转
function redirect(){
	var page=document.getElementById("pageNum");
	var pageNum=parseInt(page.value);
	if(spotId.length==0){
		var param={"pageNum":pageNum,"pageCount":6,"spotId":0};
	}
	else{
		var param={"pageNum":pageNum,"pageCount":6,"spotId":spotId};
	}
	jQuery.post("../../../travelDescController/findDescByPage.do",{"paramter":JSON.stringify(param)},initTravelsCallBack,"json");
}

function initTravelsCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);	
		return;
	}
	var num=ret.count%9;
	var sumNum=document.getElementById("sumNum");
	if(num==0&&ret.count>=6){
		sumNum.value=Math.floor(ret.count/6);
	}else{
		sumNum.value=Math.floor(ret.count/6)+1;
	}
	var ul=document.getElementById("right_ul");
		ul.innerHTML="";
	for(var i = 0; i < ret.value.length ; i ++ ){
		var li = document.createElement("li");
		var imga= document.createElement("a");
			imga.onclick=linkTravel;
		var img = document.createElement("img");
		var imagesUrl=JSON.parse(ret.value[i].images);
			img.src="../../../"+imagesUrl.images[0];
			img.alt="这是图片";
			img.className="right_img";
			img.setAttribute("travelId",ret.value[i].travelDescId);
			imga.appendChild(img);
			li.appendChild(imga);
		var div=document.createElement("div");
			div.className="introduce";
		var span=document.createElement("span");
			span.innerHTML=ret.value[i].descContent;
			div.appendChild(span);
			li.appendChild(div);
		var div1=document.createElement("div");
			div1.className="detail";
		var p1=document.createElement("p");
			p1.className="right_p1";
		var a=document.createElement("a");
			a.onclick=linkTravel;
			a.setAttribute("travelId",ret.value[i].travelDescId);
			a.innerHTML=ret.value[i].descTitle;
			p1.appendChild(a);
		var p2=document.createElement("p");
			p2.className="right_p2";
		var img1=document.createElement("img");
		var img2=document.createElement("img");
		var img3=document.createElement("img");
			img1.src="images/liulan.png";
			img1.alt="这是图片";
			img2.src="images/zan.jpg";
			img2.alt="这是图片";
			img3.src="images/pinglun.jpg";
			img3.alt="这是图片";
		var span1=document.createElement("span");
		var span2=document.createElement("span");
		var span3=document.createElement("span");
			span1.innerHTML=ret.value[i].investNum;
			span2.innerHTML=ret.value[i].praiseNum;
			span3.innerHTML=ret.value[i].commentNum;
		p2.appendChild(img1);
		p2.appendChild(span1);
		p2.appendChild(img2);
		p2.appendChild(span2);
		p2.appendChild(img3);
		p2.appendChild(span3);
		div1.appendChild(p1);
		div1.appendChild(p2);
		li.appendChild(div1);
		var p3=document.createElement("p");
		var a1=document.createElement("a");
		var img4=document.createElement("img");
		var span4=document.createElement("span");
		var span5=document.createElement("span");
			a1.href="#";
			a1.className="right_a1";
			img4.src="../../../"+ret.value[i].imageUrl;
			img4.alt="这是图片";
			span4.innerHTML=ret.value[i].petName;
			span5.innerHTML=ret.value[i].createTime;
			span5.className="right_span2";
		a1.appendChild(img4);
		a1.appendChild(span4);
		p3.appendChild(a1);
		p3.appendChild(span5);
		li.appendChild(p3);	
		ul.appendChild(li);	
	}
	
}

//查看游记
function linkTravel(){
	debugger;
	var user=window.parent.userId;
	var travelId=event.srcElement.getAttribute("travelId");
	if(window.parent.user == null ){
		var c=window.confirm("您还未登录，是否跳转到登录页！");
		if(c==true){
			window.parent.login();
			return;
		}else{
			return;
		}	
	}
	window.location.href="travel.html?travelId="+travelId;	
}

//验证是否登录
function checkLogin1(){
	if(window.parent.userId==null){
		var c=window.confirm("您还未登录，是否跳转到登录页！");
		if(c==true){
			window.parent.login();
			return;
		}else{
			return;
		}	
	}
	window.location.href="../members2/members_travels.html";
}
function checkLogin2(){
	if(window.parent.userId==null){
		var c=window.confirm("您还未登录，是否跳转到登录页！");
		if(c==true){
			window.parent.login();
			return;
		}else{
			return;
		}	
	}
	window.location.href="addTravel.html";
}
/*
初始化游记结束
*/
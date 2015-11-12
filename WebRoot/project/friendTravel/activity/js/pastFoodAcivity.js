var activeId="";
var activeTypeId="";
var pageNum=0;
var pageNum1=0;
var type="hot";
var commentType=3;
function init(){
	debugger;
	activeId=GetQueryString("activeId");
	initHeader();
	nextPage();
	initRank();
	nextPage1();
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

function initHeader(){
	$.post("../../../activeController/findActiveById.do",{"paramter":JSON.stringify({"activeId":activeId})},initHeaderCallBack,"json");
}
function initHeaderCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	debugger;
	activeTypeId=ret.value.activeType;
	var div=document.getElementById("header");
	var imageUrl=$.parseJSON(ret.value.images);
	var img1 = document.createElement("img");
	var img2 = document.createElement("img");
	var img3 = document.createElement("img");
		img1.src = "../../../" + imageUrl.images[0];
		img1.alt = "加载失败";
		img2.src = "../../../" +  imageUrl.images[1];
		img2.alt = "加载失败";
		img3.src = "../../../" +  imageUrl.images[2];
		img3.alt = "加载失败";
		div.appendChild(img1);
		div.appendChild(img2);
		div.appendChild(img3);
	var div1 = document.getElementById("logo");
	var p = document.createElement("p");
	var length=ret.value.activeName.length;
		p.innerHTML=ret.value.activeName.substr(0,2)+"<br/>"+ret.value.activeName.substr(2,length);
		div1.appendChild(p);
}

function showHotAcity()
{
	type="hot";
	pageNum=0;
	nextPage();
	var ul = document.getElementById("personMakeFood");
	ul.style.display="block";
	var ulPast = document.getElementById("pastFoodAcity");
	ulPast.style.display="none";
}
//显示往期活动
function showPastAcity()
{
	type="past";
	pageNum=0;
	nextPage();
	var ul = document.getElementById("personMakeFood");
		ul.style.display="none";
	var ulPast = document.getElementById("pastFoodAcity");
		ulPast.style.display="block";
}
function pagePost(param){
	if(type=="hot"){
	$.post("../../../activeController/findActiveJoinByPage.do",{"paramter":JSON.stringify(param)},pagePostCallBack,"json");
	}
	if(type=="past"){
	$.post("../../../activeController/findActiveByPageAndType.do",{"paramter":JSON.stringify(param)},pagePostCallBack,"json");
	}
}
function pagePostCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);	
		return;
	}
	
	if(type=="hot"){
		var num=ret.count%6;
		var sumNum=document.getElementById("sumNum");
			sumNum.innerHTMl="";
		if(num==0&&ret.count>=6){
			sumNum.innerHTML=Math.floor(ret.count/6);
		}else{
			sumNum.innerHTML=Math.floor(ret.count/6)+1;
		}
		var ul = document.getElementById("personMakeFood");
			ul.innerHTML="";
		for(var i = 0; i < ret.value.length; i ++){
			var li = document.createElement("li");
			var img = document.createElement("img");
			var imageUrl=JSON.parse(ret.value[i].images);
				img.src="../../../"+imageUrl.images[0];
				img.onclick=showStep;
				img.setAttribute("foodValue",JSON.stringify(ret.value[i]));
				li.appendChild(img);
			var p = document.createElement("p");
			var span = document.createElement("span");
				span.className="sp1";
				span.innerHTML="美食";
				p.appendChild(span);
			var span1 = document.createElement("span");
				span1.innerHTML=ret.value[i].joinName;
				p.appendChild(span1);
			var span2 = document.createElement("span");
				span2.className="sp3";
				span2.innerHTML=ret.value[i].praiseNum;
				p.appendChild(span2);
			var img1 = document.createElement("img");
				img1.src="../../../upload/activity/images/deliciousFood/heart.jpg";
				img1.setAttribute("activeJoinId",ret.value[i].activeJoinId);
				img1.onclick=prise;
				p.appendChild(img1);
				li.appendChild(p);
			var p1 = document.createElement("p");
			var span3 = document.createElement("span");
				span3.className="sp1";
				span3.innerHTML="参赛者";
				p1.appendChild(span3);
			var span4 = document.createElement("span");
				span4.innerHTML=ret.value[i].petName;
				p1.appendChild(span4);
				li.appendChild(p1);
			var p2 = document.createElement("p");
			var span5 = document.createElement("span");
				span5.className="sp1";
				span5.innerHTML="美食介绍";
				p2.appendChild(span5);
			var span6 = document.createElement("span");
				span6.innerHTML=ret.value[i].joinDesc;
				p2.appendChild(span6);
				li.appendChild(p2);
				ul.appendChild(li);
		}
	}
	if(type=="past"){
		var num=ret.count%6;
		var sumNum=document.getElementById("sumNum");
			sumNum.innerHTMl="";
		if(num==0&&ret.count>=6){
			sumNum.innerHTML=Math.floor(ret.count/6);
		}else{
			sumNum.innerHTML=Math.floor(ret.count/6)+1;
		}
		var ulPast = document.getElementById("pastFoodAcity");
		ulPast.innerHTML="";
		for(var j = 0; j < ret.value.length; j ++){
			var li1 = document.createElement("li");
			var a = document.createElement("a");
				a.href ="pastFoodAcivity.html?activeId="+ret.value[j].activeId;
			var img6 = document.createElement("img");
			var imgUrl=$.parseJSON(ret.value[j].images);
				img6.src="../../../"+imgUrl.images[j];
				a.appendChild(img6);
				li1.appendChild(a);
			var a1 = document.createElement("a");
				a1.href ="pastFoodAcivity.html?activeId="+ret.value[j].activeId;
				a1.className="a1";
				a1.innerHTML=ret.value[j].activeName;
				li1.appendChild(a1);
				ulPast.appendChild(li1);		
		}
 	}
	
}

//点赞
function prise(){
	debugger;
	var activeJoinId=event.srcElement.getAttribute("activeJoinId");
	$.post("../../../activeController/praiseActiveJoin.do",{"paramter":JSON.stringify({"activeJoinId":activeJoinId})},priseCallBack,"json");
}
function priseCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
	}
	debugger;
	pageNum=0;
	nextPage();

}

//下一页（初始化参赛作品）
function nextPage(){
	debugger;
	var page=document.getElementById("pageNum");
	var sumNum=document.getElementById("sumNum").innerHTML;
	if(pageNum>=sumNum){
		return;	
	}
	pageNum++;
	page.value=pageNum;
	if(type=="hot"){
		var param={"pageNum":pageNum,"pageCount":6,"activeId":activeId};
	}
	if(type=="past"){
		var param={"pageNum":pageNum,"pageCount":6,"activeType":activeTypeId};	
	}
	pagePost(param);
	
	
}

//上一页
function pastPage(){
	
	var page=document.getElementById("pageNum");
	var sumNum=document.getElementById("sumNum").innerHTML;
	if(pageNum<=1){
		return;	
	}
	pageNum--;
	page.value=pageNum;
	if(type="hot"){
		var param={"pageNum":pageNum,"pageCount":6,"activeId":activeId};
	}
	if(type="past"){
		var param={"pageNum":pageNum,"pageCount":6,"activeType":activeTypeId};	
	}
	pagePost(param);
}

//末页
function lastPage(){
	var page=document.getElementById("sumNum");
	var page1=document.getElementById("pageNum");
	pageNum=parseInt(page.innerHTML);
	if(type=="hot"){
		var param={"pageNum":pageNum,"pageCount":6,"activeId":activeId};
	}
	if(type=="past"){
		var param={"pageNum":pageNum,"pageCount":6,"activeType":activeTypeId};	
	}
	pagePost(param);
	page1.value=pageNum;
}

//首页
function firstPage(){
	pageNum=1;
	var page1=document.getElementById("pageNum");
	if(type=="hot"){
		var param={"pageNum":pageNum,"pageCount":6,"activeId":activeId};
	}
	if(type=="past"){
		var param={"pageNum":pageNum,"pageCount":6,"activeType":activeTypeId};	
	}
	pagePost(param);
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
	pageNum = pageN;
	if(type=="hot"){
		var param={"pageNum":pageNum,"pageCount":6,"activeId":activeId};
	}
	if(type=="past"){
		var param={"pageNum":pageNum,"pageCount":6,"activeType":activeTypeId};	
	}
	pagePost(param);
}


//初始化排行榜
function initRank(){
		$.post("../../../activeController/findActiveJoinByCount.do",{"paramter":JSON.stringify({"count":10,"activeId":activeId})},initRankCallBack,"json");
}
function initRankCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;	
	}
	var div=document.getElementById("rank");
		div.innerHTML="";
	var p=document.createElement("p");
		p.innerHTML="排行榜";
		div.appendChild(p);
	var ul=document.createElement("ul");
	for(var i = 0; i < ret.value.length; i ++){
		if(i>=0&&i<3){
			var li=document.createElement("li");
			var img=document.createElement("img");
			if(i==0){
				img.src="../../../upload/activity/images/photoPK/1.png";	
			}
			if(i==1){
				img.src="../../../upload/activity/images/photoPK/2.png";	
			}
			if(i==2){
				img.src="../../../upload/activity/images/photoPK/3.png";	
			}
			li.appendChild(img);
		}else{
			var p1=document.createElement("p");
				p1.className="number";
				p1.innerHTML=i+1;
				li.appendChild(p1);
		}
		var p2=document.createElement("p");
			p2.innerHTML=ret.value[i].petName;
		var p3=document.createElement("p");
			p3.innerHTML=ret.value[i].praiseNum+"人赞";	
			li.appendChild(p2);
			li.appendChild(p3);	
			ul.appendChild(li);
	}	
	div.appendChild(ul);
}

//选择评论类型
function check(){
    var obj=event.srcElement;
	var input=document.getElementById("checked");
	var sum=document.getElementById("sumNum1");
		sum.innerHTML="7";
		input.id="";
		obj.id="checked";
		obj.checked="checked";
		commentType=obj.value;
		pageNum1=0;
		nextPage1();
}

//初始化评论
function initComment(param){
	$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
}
function initCommentCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;	
	}
	var num=ret.count%4;
	var sumNum=document.getElementById("sumNum1");
		sumNum.innerHTMl="";
	if(num==0&&ret.count>=4){
		sumNum.innerHTML=Math.floor(ret.count/4);
	}else{
		sumNum.innerHTML=Math.floor(ret.count/4)+1;
	}
	var ul=document.getElementById("allComment1");
		ul.innerHTML="";
	for(var i = 0; i < ret.comment.length; i ++ ){
		var li = document.createElement("li");
		var img = document.createElement("img");
			img.src="../../../"+ret.comment[i].imageUrl;
			img.className="answers_img";
		var p = document.createElement("p");
			p.className="userName";
			p.innerHTML=ret.comment[i].petName;
		var span = document.createElement("span");
			span.innerHTML="发表于"+ret.comment[i].createTime;
			p.appendChild(span);
		var a=document.createElement("a");
			a.innerHTML="删除评论";
			a.className="deleteComment";
			a.onclick=deleteComment;
			a.setAttribute("commentId",ret.comment[i].commentId);
			if(window.parent.user==null){
				a.style.display="none";
			}else{
			if(window.parent.user.userId==ret.comment[i].userId){
				a.style.display="block";
			}
			}
		var p1 = document.createElement("p");
			p1.className="commentDetail";
			p1.innerHTML=ret.comment[i].commentContent;
			li.appendChild(img);
			li.appendChild(p);
			li.appendChild(a);
			li.appendChild(p1);
			ul.appendChild(li);
	}
}

//下一页
function nextPage1(){
	debugger;
	var page=document.getElementById("pageNum1");
	var sumNum=document.getElementById("sumNum1").innerHTML;
	if(pageNum1>=sumNum){
		return;	
	}
	pageNum1++;
	page.value=pageNum1;
	var param={"relId":activeId,"commentType":4,"commentLevel":commentType,"pageNum":pageNum1,"pageCount":4};
	initComment(param);
		
}

//上一页
function pastPage1(){
	var page=document.getElementById("pageNum1");
	var sumNum=document.getElementById("sumNum1").innerHTML;
	if(pageNum1<=1){
		return;	
	}
	pageNum1--;
	page.value=pageNum1;
	var param={"relId":activeId,"commentType":4,"commentLevel":commentType,"pageNum":pageNum1,"pageCount":4};
	initComment(param);
}

//末页
function lastPage1(){
	var page=document.getElementById("sumNum1");
	var page1=document.getElementById("pageNum1");
	pageNum1=parseInt(page.innerHTML);
	var param={"relId":activeId,"commentType":4,"commentLevel":commentType,"pageNum":pageNum1,"pageCount":4};
	initComment(param);
	page1.value=pageNum;
}

//首页
function firstPage1(){
	pageNum1=1;
	var page1=document.getElementById("pageNum1");
	var param={"relId":activeId,"commentType":4,"commentLevel":commentType,"pageNum":pageNum1,"pageCount":4};
	initComment(param);
	page1.value=pageNum1;
}
//跳转
function redirect1(){
	var page=document.getElementById("pageNum1");
	var sumNum=document.getElementById("sumNum1").innerHTML;
	pageN=parseInt(page.value);
	if(pageN>sumNum||pageN<1){
		return;
	}
	pageNum1 = pageN;
	var param={"relId":activeId,"commentType":4,"commentLevel":commentType,"pageNum":pageNum1,"pageCount":4};
	initComment(param);
}

//删除评论
function deleteComment(){
	var commentId=event.srcElement.getAttribute("commentId");
	$.post("../../../commentController/deleteCommentById.do",{"paramter":JSON.stringify({"commentId":commentId})},deleteCommentCallBack,"json");
}
function deleteCommentCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	pageNum1=0;
	nextPage1();
}

//显示评论框
function submitComment(){
	var div=document.getElementById("saveComment_div");
		div.style.display="block";
}

//隐藏评论框
function hiddenComment(){
	var div=document.getElementById("saveComment_div");
	var content=document.getElementById("talk").value="";
		div.style.display="none";
}

//发表评论
function saveComment(){
	var arr=document.getElementsByName("comment");
	for(var i = 0; i < arr.length; i ++){
		if(arr[i].checked){
			var commentLevel=arr[i].value;
		}
	}
	var content=document.getElementById("talk").value;
	if(commentLevel==undefined){
		alert("请选择评论类型！");
		return;
	}
	$.post("../../../commentController/saveComment.do",{"paramter":JSON.stringify({"relId":activeId,"commentType":4,"commentContent":content,"commentLevel":commentLevel})},saveCommentCallBack,"json");
}
function saveCommentCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	hiddenComment();
	pageNum1=pageNum1-1;
	nextPage1();
}

//评论剩余字数
function checkLength(){
	var length=document.getElementById("talk").value.length;
	var number=parseInt(100-length);
	var span=document.getElementById("wordNumber");
		span.innerHTML=number+"/100";
	if(number<=0){
		document.getElementById("talk").value=document.getElementById("talk").value.substring(0,100);
	}
}

function showStep()
{
	debugger;
	foodValue=$.parseJSON(event.srcElement.getAttribute("foodValue"));
	window.open("makeStep.html","_blank","width=580,status=no,resizable=no,height=600,toolbar=no,menubar=no,left=400,top=5%;");
}










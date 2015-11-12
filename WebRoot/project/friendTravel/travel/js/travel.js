// JavaScript Document
var travelId="";
var spotId="";

//初始化页面
function init(){
	travelId =GetQueryString("travelId");
	initTravel();
	initComment();
	initTravels();
}


function showBigImg()
{
	var bigImg=document.getElementsByClassName("bigImg_container")[0];
	bigImg.style.display="block";
    var bigImg1=document.getElementsByClassName("bigImg")[0];
	bigImg1.style.display="block";
	
}
function closeBigImg()
{
	var bigImg=document.getElementsByClassName("bigImg_container")[0];
	bigImg.style.display="none";
    var bigImg1=document.getElementsByClassName("bigImg")[0];
	bigImg1.style.display="none";
}

//显示回复框
function replay(){
	var div1=document.getElementById("reply");
	if(div1!=null){
		div1.style.display="none";
	}
	var obj=event.srcElement;
	var div=obj.parentNode.lastChild;
		div.id="reply";
	div.style.display="block";
}

//隐藏回复框
function replayTalk(){
	var obj=event.srcElement;
	var div=obj.parentNode;
	div.style.display="none";
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



/*
初始化游记内容
*/
function initTravel(){
	debugger;
	var parm={"travelId":travelId};
	$.post("../../../travelDescController/findDescById.do",{"paramter":JSON.stringify(parm)},initTravelCallBack,"json");	
}
function initTravelCallBack(ret){
	debugger;
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
		spotId=ret.value.spotId;
	var img = document.getElementById("bgImg");
	var imagesUrl=$.parseJSON(ret.value.images);
		img.src="";
		img.src="../../../"+imagesUrl.images[0];
	var div = document.getElementById("picture");
		div.innerHTML="";
	var img1 = document.createElement("img");
		img1.src="../../../"+imagesUrl.images[0];
		img1.alt="这是图片";
		img1.className="bgImg";
		img1.id="img";
		div.appendChild(img1);
	var div3 = document.createElement("div");
		div3.className = "smallPicture";
	for(var i = 0;i < imagesUrl.images.length; i++){
		var img11 = document.createElement("img");
			img11.src = "../../../"+imagesUrl.images[i];
			if(i == 0){
				img11.style.border = "1px solid red";
				img11.id = "curImg";
			}
			img11.onmouseover = mouseOverPicture;
		div3.appendChild(img11);
	}
		div.appendChild(div3);
	var div1 =document.getElementById("header_left");
	var span=document.createElement("span");
		span.className="time";
		span.innerHTML=ret.value.createTime;
		div1.appendChild(span);
	var h2=document.createElement("h2");
		h2.innerHTML="【"+ret.value.spotName +"】" + ret.value.descTitle;
		div1.appendChild(h2);
	var div2=document.getElementById("header_right");
		div2.innerHTML="";
	var a1=document.createElement("a");
	var a2=document.createElement("a");
	var img3=document.createElement("img");
		a1.href="#";
		a1.className="header_a1";
		img3.src="../../../"+ret.value.imageUrl;
		img3.alt="这是图片";
		a1.appendChild(img3);
		a2.href="#";
		a2.className="header_a2";
		a2.innerHTML=ret.value.petName;
		div2.appendChild(a1);
		div2.appendChild(a2);
		div2.appendChild(span);
	var span2=document.getElementById("left_icon");
		span2.innerHTML="";
	var a3=document.createElement("a");
	var a4=document.createElement("a");
	var span3=document.createElement("span");
	var span4=document.createElement("span");
		a3.innerHTML="<img src='images/shoucang.jpg'>"+ret.value.collectNum;
		a3.onclick=shoucang;
		a4.innerHTML="<img src='images/zan.jpg'>"+ret.value.praiseNum;
		a4.onclick=dianzan;
		span3.innerHTML="<img src='images/pinglun.jpg'>"+ret.value.commentNum;
		span4.innerHTML="<img src='images/liulan.png'>"+ret.value.investNum;
		span2.appendChild(a3);
		span2.appendChild(a4);
		span2.appendChild(span3);
		span2.appendChild(span4);
	var span5=document.getElementById("travel_span");
		span5.innerHTML="";
		span5.innerHTML=ret.value.descContent;
		initSpot();
}
/*
初始化游记内容结束
*/



/*
初始化评论内容
*/
function initComment(){
	nextPage();
}
//下一页
function nextPage(){
	debugger;
	var page=document.getElementById("pageNum");
	var pageNum=parseInt(page.value);
	var p=pageNum+1;
	var sumNum=document.getElementById("sumNum").value;
	if(pageNum==sumNum){
		alert("已经是最后一页！");
		return;	
	}
	var param={"relId":travelId, "commentType":0, "pageNum":p,"pageCount":5};
	$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	pageNum++;
	page.value=pageNum;
}
//上一页
function pastPage(){
	var page=document.getElementById("pageNum");
	var pageNum=parseInt(page.value);
	var sumNum=document.getElementById("sumNum").value;
	if(sumNum==1){
		alert("已经是第一页！");
		return;	
	}
	pageNum--;
	var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
	$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	page.value=pageNum;
}	
//末页
function lastPage(){
	var page=document.getElementById("sumNum");
	var page1=document.getElementById("pageNum");
	var pageNum=parseInt(page.value);
	var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
	$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	page1.value=pageNum;
}
//首页
function firstPage(){
	var pageNum=1;
	var page1=document.getElementById("pageNum");
	var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
	$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	page1.value=pageNum;
}	
//跳转
function redirect(){
	var page=document.getElementById("pageNum");	
	var pageNum=page.value;
	var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
	$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
}

//初始化评论及回复内容
function initCommentCallBack(ret){
	debugger;
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	
	var num=ret.count%5;
	var sumNum=document.getElementById("sumNum");
	if(num==0&&ret.count>=5){
		sumNum.value=Math.floor(ret.count/5);
	}else{
		sumNum.value=Math.floor(ret.count/5)+1;
	}
	var p=document.getElementById("allAnswers");
		p.innerHTML="("+ret.count+")";
	var ul=document.getElementById("answers_ul");
		ul.innerHTML="";
	for(var i = 0; i < ret.comment.length; i ++){
		var li = document.createElement("li");
		var img = document.createElement("img");
			img.alt="个人头像";
			img.src="../../../"+ret.comment[i].imageUrl;
			img.className="answers_img";
			li.appendChild(img);
		var p1 = document.createElement("p");
			p1.className="userName";
			p1.innerHTML=ret.comment[i].petName;
			li.appendChild(p1);
		var p2 = document.createElement("p");
			p2.className="pubTime";
			p2.innerHTML=ret.comment[i].createTime;
			li.appendChild(p2);
		var a2 = document.createElement("a");
			a2.className="deleteComment";
			a2.innerHTML="删除评论";
			a2.onclick=deleteComment;
			a2.setAttribute("CommentId",ret.comment[i].commentId);
			li.appendChild(a2);
		if(window.parent.user.userId!=ret.comment[i].userId){
			a2.style.display="none";
		}
			
		var p3 = document.createElement("p");
			p3.className="comment";
			p3.innerHTML=ret.comment[i].commentContent;
			li.appendChild(p3);
		var ul1 = document.createElement("ul");
			ul1.innerHTML="";
		for(var j = 0; j < ret.reply.length; j ++){
			if(ret.comment[i].commentId==ret.reply[j].commentId){
			var li1 = document.createElement("li");
				li1.className="answers_div";
			var img1 = document.createElement("img");
				img1.src="../../../"+ret.reply[j].imageUrl;
				li1.appendChild(img1);
			var span = document.createElement("span");
				span.className="div_span1";
				span.innerHTML=ret.reply[j].petName+"回复了评论";
				li1.appendChild(span);
			var span1 = document.createElement("span");
				span1.className="div_time1";
				span1.innerHTML=ret.reply[j].replyTime;
				li1.appendChild(span1);
			var a = document.createElement("a");
				a.className="answers_talk";
				a.innerHTML="删除回复";
				a.setAttribute("replyId",ret.reply[j].replyId);
				a.onclick=deleteReply;
				if(window.parent.user.userId!=ret.reply[j].userId){
					a.style.display="none";
				}
				li1.appendChild(a);
			var p4 = document.createElement("p");
				p4.innerHTML=ret.reply[j].replyContent;
				li1.appendChild(p4);
				ul1.appendChild(li1);
			}
		}
			li.appendChild(ul1);
		
		var a1 = document.createElement("a");
			a1.className="answers_talk";
			a1.onclick=replay;
			a1.innerHTML="回复";
			li.appendChild(a1);
		var div1 = document.createElement("div");
			div1.className="callback";
			
			div1.id="huifu";
		var input = document.createElement("input");
			input.type="text";
			input.className="replaykuang";
			div1.appendChild(input);
		var input1 = document.createElement("input");
			input1.type="button";
			input1.className="huifu";
			input1.value="确认回复";
			input1.setAttribute("commentId",ret.comment[i].commentId);
			input1.onclick=saveReply;
			div1.appendChild(input1);
		var input2 = document.createElement("input");
			input2.className="huifu";
			input2.type="button";
			input2.value="取消回复";
			input2.onclick=replayTalk;
			div1.appendChild(input2);
			li.appendChild(div1);
			ul.appendChild(li);	
	}
}
/*
初始化评论内容结束
*/


/*
初始化景区内容开始
*/
function initSpot(){
	debugger;
	$.post("../../../spotController/findSpotById.do",{"paramter":JSON.stringify({"spotId":spotId})},initSpotCallBack,"json");
}

function initSpotCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;	
	}
	debugger;
	var div=document.getElementById("recommend_scenic");
	var div1=document.createElement("div");
		div1.className="titlediv";
	var a=document.createElement("a");
		a.href="../scenicSpot/searchProvinces.html";
	var p=document.createElement("p");
		p.className="destination_p1";
		p.innerHTML=ret.scienceSpot.spotName;
		a.appendChild(p);
		div1.appendChild(a);
		div.appendChild(div1);
	var imagesUrl=$.parseJSON(ret.scienceSpot.imageUrl);
	var img=document.createElement("img");
		img.src="../../../"+imagesUrl.images[0];
		img.alt="加载失败";
		div.appendChild(img);
	var p1=document.createElement("p");
		p1.className="recommend_p3";
		p1.innerHTML=ret.scienceSpot.locationDesc;
		div.appendChild(p1);
	var p2=document.createElement("p");
		p2.className="hot_title";
		p2.innerHTML="热门景点->";
		div.appendChild(p2);
	var div2=document.createElement("div");
		div2.className="hot_scenic";
	var ul=document.createElement("ul");
		ul.className="scenic";
	for(var i = 0; i < 2; i ++){
		var li = document.createElement("li");
		var a1 = document.createElement("a");
			a1.href="../scenicSpot/searchProvinces.html";
		var img1 = document.createElement("img");
		var images=$.parseJSON(ret.spot[i].images);
			img1.src="../../../"+images.images[0];
			a1.appendChild(img1);
			li.appendChild(a1);
		var a2 = document.createElement("a");
			a2.href="../scenicSpot/searchProvinces.html";
			a2.innerHTML=ret.spot[i].spotName;
			li.appendChild(a2);
		var p3 = document.createElement("p");
			p3.innerHTML=ret.spot[i].spotDesc;
			li.appendChild(p3);
			ul.appendChild(li);	
	}	
		div2.appendChild(ul);
	var a3 = document.createElement("a");	
		a3.href="../scenicSpot/searchProvinces.html";
		a3.innerHTML="更多热门景区";
		div2.appendChild(a3);
		div.appendChild(div2);
}
/*
初始化景区内容结束
*/


/*
初始化相关游记内容开始
*/
function initTravels(){
	$.post("../../../travelDescController/findRelationSpotById.do",{"paramter":JSON.stringify({"spotId":1,"count":6})},initTravelsCallBack,"json");
}

function initTravelsCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;	
	}
	var div=document.getElementById("travels1");
	var p=document.createElement("p");
		p.innerHTML="相关点评";
		div.appendChild(p);
	var ul=document.createElement("ul");
		ul.innerHTML="";
	for(var i = 0; i< ret.value.length; i ++){
		var li=document.createElement("li");
		var images=$.parseJSON(ret.value[i].images);
		var img=document.createElement("img");
			img.src="../../../"+images.images[0];
			img.onclick=linkTravel;
			img.setAttribute("travelId",ret.value[i].travelDescId);
			li.appendChild(img);
		var p1=document.createElement("p");
		var span=document.createElement("span");
			span.className="fontLink1";
			span.innerHTML=ret.value[i].descContent;
			p1.appendChild(span);
			li.appendChild(p1);
		var p2=document.createElement("p");
		var span1=document.createElement("span");
			span1.className="fontLink2";
			span1.innerHTML="来自："+ret.value[i].petName;
			p2.appendChild(span1);
			li.appendChild(p2);
			ul.appendChild(li);
	}
	div.appendChild(ul);
}
/*
初始化相关游记内容结束
*/


//收藏
function shoucang(){
	$.post("../../../travelDescController/collectTravelDesc.do",{"paramter":JSON.stringify({"travelDescId":travelId})},shoucangCallBack,"json");
}
function shoucangCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
		if(ret.state=="1"){
		alert(ret.value);
	}
}

//点赞
function dianzan(){
	$.post("../../../travelDescController/praiseTravelDesc.do",{"paramter":JSON.stringify({"travelDescId":travelId})},dianzanCallBack,"json");
}
function dianzanCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
		if(ret.state=="1"){
		alert(ret.value);
	}
	initTravel(travelId);	
}

//发表评论
function saveComment(){
	debugger;
	var content=document.getElementById("talk").value;
	$.post("../../../commentController/saveComment.do",{"paramter":JSON.stringify({"relId":travelId,"commentType":0,"commentContent":content})},saveCommentCallBack,"json");
}
function saveCommentCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	debugger;
	if(ret.state=="1"){
		document.getElementById("talk").value="";
		var page1=document.getElementById("pageNum");
		var pageNum=parseInt(page1.value);
		var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
		$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	}
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
	if(ret.state=="1"){
		document.getElementById("talk").value="";
		var page1=document.getElementById("pageNum");
		var pageNum=parseInt(page1.value);
		var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
		$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	}
}

//回复评论
function saveReply(){
	debugger;
	var commentId=event.srcElement.getAttribute("commentId");
	var content=document.getElementById("reply").firstChild.value;
	$.post("../../../commentController/saveReply.do",{"paramter":JSON.stringify({"commentId":commentId,"replyContent":content})},saveReplyCallBack,"json");
}
function saveReplyCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	if(ret.state=="1"){
		document.getElementById("reply").firstChild.value="";
		document.getElementById("reply").style.display="none";
		var page1=document.getElementById("pageNum");
		var pageNum=parseInt(page1.value);
		var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
		$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	}
}

//删除回复
function deleteReply(){
	var replyId=event.srcElement.getAttribute("replyId");
	$.post("../../../commentController/deleteReply.do",{"paramter":JSON.stringify({"replyId":replyId})},deleteReplyCallBack,"json");
}
function deleteReplyCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	if(ret.state=="1"){
		var page1=document.getElementById("pageNum");
		var pageNum=parseInt(page1.value);
		var param={"relId":travelId, "commentType":0, "pageNum":pageNum,"pageCount":5};
		$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	}
}

//查看游记
function linkTravel(){
	var user=window.parent.user;
	var travelId=event.srcElement.getAttribute("travelId");
	if(user == null ){
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

function mouseOverPicture(){
	var img = document.getElementById("curImg");
		img.id = "";
		img.style.border = "";
	var img1 = event.srcElement;
		img1.style.border = "1px solid red";
		img1.id = "curImg";
	var imgSrc = img1.src;
	var bgImg = document.getElementById("bgImg");
	var bigImg = document.getElementById("img");
		bgImg.src="";
		bigImg.src="";
		bgImg.src = imgSrc;
		bigImg.src = imgSrc;
	 
}



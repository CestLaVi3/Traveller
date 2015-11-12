// JavaScript Document
var stagtegyId=GetQueryString("stagtegyId");
var images;
var index=0;
var time;
var pageNum = 1;
var pageCount = 3;// 显示条数
var sumCount;// 总条数
var fitMonth=0;
var zan1=0;
//接参
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
function init()
{
	$.post("../../../stagtegyController/findById.do",{"paramter":JSON.stringify({"stagtegyId":stagtegyId})},strategyDetail,"json");
	var obj={"relId":stagtegyId,"commentType":1,"pageNum":pageNum,"pageCount":pageCount};
	var obj1={"stagtegyId":stagtegyId,"count":4};
	$.post("../../../stagtegyController/findRelationStagtegy.do",{"paramter":JSON.stringify(obj1)},showRelSta,"json");
	getComment();
}
//显示攻略内容
function strategyDetail(ret)
{
  if(ret.state=="0")
  {
	 alert(ret.value); 
	 return;
  }
 var arr=ret.value;
 fitMonth=arr.fitMonth;
 var h2=document.getElementById("h2");
 h2.innerHTML="【"+arr.spotName+"】"+arr.stagtegyTitle;
 var time=document.getElementById("time");
 time.innerHTML=arr.createTime;
 var shoucang=document.getElementById("shoucang");
 var txt=document.createTextNode("收藏");
 shoucang.setAttribute("stagtegyId",stagtegyId);
 shoucang.appendChild(txt);
 var zan=document.getElementById("zan");
 var txt=document.createTextNode("赞"+arr.praiseNum);
 zan.setAttribute("num",arr.praiseNum);
 zan.setAttribute("stagtegyId",stagtegyId);
 zan.appendChild(txt);
 var pinglun=document.getElementById("pinglun");
 var txt=document.createTextNode("评论"+arr.commentNum);
 pinglun.appendChild(txt);
 var liulan=document.getElementById("liulan");
 var txt=document.createTextNode("浏览"+arr.investNum);
 liulan.appendChild(txt);
 
 var photo=document.getElementById("photo");
 photo.src="../../../"+arr.imageUrl;
 var userName=document.getElementById("userName");
 userName.innerHTML=arr.petName;
 
 var subComment=document.getElementById("subComment");
 subComment.innerHTML="指南推荐:"+arr.subComment;
 var content=document.getElementById("content");
 content.innerHTML=arr.stagtegyContent;
 lunbo(arr);
}
//轮播
function lunbo(arr)
{
	var img=JSON.parse(arr.images);
	images=img.images;
	var picture=document.getElementById("picture");
	var img1=document.createElement("img");
	img1.src="../../../"+images[0];
	img1.id="bigImg";
	img1.className="bgImg";
	picture.appendChild(img1);
	var div=document.createElement("div");
	div.id="small";
    div.className="lunbo";
	for(var i=0;i<images.length;i++)
	{
		var img=document.createElement("img");
		img.src="../../../"+images[i];
		if(i==0)
		 {
			 img.style.border="red 1px solid";
		 }
		img.onmouseover=stopPlay;
		img.onmouseout=beginPlay;
		div.appendChild(img);
	}
	picture.appendChild(div);
    time=window.setInterval(showBigImg,2000);
	
}
//轮播大图片
function showBigImg()
{
	var img=document.getElementById("bigImg");
	img.src="../../../"+images[index++];
	var div=document.getElementById("small");
	for(var i=0;i<div.childNodes.length;i++)
	{
	  if(index==(i+1))
		{
		div.childNodes[i].style.border="red 1px solid";
		}
	  else
	    {
			div.childNodes[i].style.border="0px";
		}
	}
	if(index==images.length)
	{
		index=0;
	}
}
//开始轮播
function beginPlay()
{
	time=window.setInterval(showBigImg,2000);
}
//停止轮播
function stopPlay()
{
	var img=document.getElementById("bigImg");
	var div=document.getElementById("small");
	for(var i=0;i<div.childNodes.length;i++)
	{
		div.childNodes[i].style.border="none";
	}
	var smallImg=event.srcElement;
	smallImg.style.border="red 1px solid";
	img.src=smallImg.src;
	window.clearInterval(time);
}
//获取评论
function getComment()
{
	document.getElementById("nowPage").value=pageNum;
	var param={"relId":stagtegyId, "commentType":1, "pageNum":pageNum,"pageCount":pageCount};
	$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
}
//初始化评论及回复内容
function initCommentCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	sumCount=ret.count;
	document.getElementById("sumPage").value=Math.ceil(ret.count/pageCount);
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
//显示推荐出游月份
function showFitMonth()
{
	var tb=document.getElementById("month_table");
	tb.innerHTML="";
	var m=1;
	for(var i=0;i<3;i++)
	{
		var tr=document.createElement("tr");
		for(var j=0;j<4;j++)
		{
			var td=document.createElement("td");
			var t=fitMonth%4;
			if(t==0)
			 {
				 t=4;
			 }
			if(i==(Math.ceil(fitMonth/4)-1)&&j==t-1)
			{
				td.innerHTML=(m++)+"月<img src='../../../upload/strategy/images/jian.jpg'/>";
			}
			else
			{
			td.innerHTML=(m++)+"月";
			}
			tr.appendChild(td);
		}
		tb.appendChild(tr);
	}
}
//显示相关攻略
function showRelSta(ret)
{
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var ul = document.getElementById("comMenuUl");
	var arr = ret.value;
	var relNum = 0;
	if (arr.length > 4) 
	{
		relNum = 4;
	} 
	else
	{
		relNum = arr.length;
	}
	for ( var i = 0; i < relNum; i++) 
	{
		var li = document.createElement("li");
		var images=JSON.parse(arr[i].images);
		var a=document.createElement("a");
		a.href="strategy.html?stagtegyId="+arr[i].stagtegyId;
		var img = document.createElement("img");
        img.src="../../../"+images.images[0];
		a.appendChild(img);
		var p = document.createElement("p");
		p.innerHTML="【"+arr[i].spotName+"】"+arr[i].stagtegyTitle+"<br/>";
		var sp = document.createElement("span");
		sp.innerHTML="来自:"+arr[i].petName;
		p.appendChild(sp);
		a.appendChild(p);
		li.appendChild(a);
		var div = document.createElement("div");
		var a1 = document.createElement("a");
		a1.setAttribute("stagtegyId",arr[i].stagtegyId);
		a1.onclick=shoucang;
		a1.innerHTML="<img src='../../../upload/strategy/images/shoucang.jpg'/>收藏";
		div.appendChild(a1);
		var a2 = document.createElement("a");
		a2.setAttribute("stagtegyId",arr[i].stagtegyId);
		a2.onclick=zan;
		a2.setAttribute("num",arr[i].praiseNum);
		a2.innerHTML="<img src='../../../upload/strategy/images/praise.jpg'/>赞"+arr[i].praiseNum;
		div.appendChild(a2);
		var a3 = document.createElement("a");
		a3.setAttribute("stagtegyId",arr[i].stagtegyId);
		a3.innerHTML="<img src='../../../upload/strategy/images/pinglun.jpg'/>评论"+arr[i].commentNum;
		div.appendChild(a3);
		var a4 = document.createElement("a");
		a4.setAttribute("stagtegyId",arr[i].stagtegyId);
		a4.innerHTML="<img src='../../../upload/strategy/images/liulan.png'/>浏览"+arr[i].investNum;
		div.appendChild(a4);
		li.appendChild(div);
		ul.appendChild(li);
	} 
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
		document.getElementById("comment").value="";
		var param={"relId":stagtegyId, "commentType":1, "pageNum":pageNum,"pageCount":pageCount};
		$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	}
}

//回复评论
function saveReply(){
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
		var param={"relId":stagtegyId, "commentType":1, "pageNum":pageNum,"pageCount":pageCount};
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
		var param={"relId":stagtegyId, "commentType":1, "pageNum":pageNum,"pageCount":pageCount};
		$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	}
}

//发表评论
function comment()
{
	var content=document.getElementById("comment").value;
	var obj={"relId":stagtegyId,
             "commentType":1,
             "commentContent":content
			}
	$.post("../../../commentController/saveComment.do",{"paramter":JSON.stringify(obj)},saveCommentCallBack,"json");
}
function saveCommentCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	if(ret.state=="1"){
		document.getElementById("count").innerHTML="0/100";
		document.getElementById("comment").value="";
		var param={"relId":stagtegyId, "commentType":1, "pageNum":pageNum,"pageCount":pageCount};
		$.post("../../../commentController/findCommentById.do",{"paramter":JSON.stringify(param)},initCommentCallBack,"json");
	}
}
//收藏
function shoucang()
{
	var a=event.srcElement;
	var id=a.getAttribute("stagtegyId");
	var obj={"stagtegyId":id};
	$.post("../../../stagtegyController/collectStagtegy.do",{"paramter":JSON.stringify(obj)},callback,"json");
}
function callback(ret)
{
	alert(ret.value);
}
//赞
function zan()
{
	zan1++;
	if(zan1<2)
	{
	var a=event.srcElement;
	var id=a.getAttribute("stagtegyId");
	a.innerHTML="<img src='../../../upload/strategy/images/praise.jpg'/>赞"+(parseInt(a.getAttribute("num"))+1);
	var obj={"stagtegyId":id};
	$.post("../../../stagtegyController/praiseStagtegy.do",{"paramter":JSON.stringify(obj)},{},"json");
	}
	else
	{
		alert("你已赞过该攻略!");
	}
}
//计数
function count()
{
	var txt=document.getElementById("comment");
	var sp=document.getElementById("count");
	if(txt.value.length>=100)
	{
		sp.innerHTML="100/100";
		txt.value=txt.value.substr(1,101);
	}
	else
	 {
	 sp.innerHTML=txt.value.length+"/100";
	 }
}
//首页
function firstPage() {
	pageNum = 1;
	getComment();
}
//上一页
function pastPage() {
	if (pageNum == 1) {
		return;
	} else {
		pageNum--;
	}
	getComment();
}
//下一页
function nextPage() {
	if (pageNum == Math.ceil(sumCount / pageCount)) {
		return;
	} else {
		pageNum++;
	}
	getComment();
}
//末页
function lastPage() {
	pageNum = Math.ceil(sumCount / pageCount);
	getComment();
}
//跳转
function redirect() {
	pageNum = parseInt(document.getElementById("nowPage").value);
	if(pageNum>Math.ceil(sumCount/ pageCount))
	{
		return;
	}
	getComment();
}

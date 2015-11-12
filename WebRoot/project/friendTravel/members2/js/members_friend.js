var pageNumInp;//分页区当页INPUT显示
var pageNum=1;//当前页数
var showObj=0;//当前显示页的对象编号，选项卡切换更改他的值

var userId=window.parent.inf.userId;

$(document).ready(friendPost);



function friendPost(){
	attentionFriendsPost();
	pageNumInp=document.getElementById("pageNumInp");
	pageNumInp.value=pageNum;
	
}

/*关注部分请求*/
function attentionFriendsPost(){
	var a={
		"pageNum":pageNum,
		"pageCount":9,
		"userId":userId
	};
	$.post("../../../userController/findCurUserCollect.do",{"paramter":JSON.stringify(a)},attentionFriends,"json");	
}
/*关注好友写入*/
function attentionFriends(attF){
	pageSet(attF.count,9);
	var af=document.getElementById("attention_friends");
	af.innerHTML=null;
	for(var i=0;i<attF.value.length;i++){
	var li=document.createElement("li");
		li.className="friend_membersShow mt20 ml20";
		var headImgDiv=document.createElement("div");
			headImgDiv.className="friend_avatar fl";
			var headImgA=document.createElement("a");
				headImgA.href="members_home.html?userId="+attF.value[i].userId;
				headImgA.target="iframeMain";//
				var headImg=document.createElement("img");
					headImg.src="../../../"+attF.value[i].imageUrl;//
				headImgA.appendChild(headImg);
			headImgDiv.appendChild(headImgA);
		li.appendChild(headImgDiv);
		
		var conbody=document.createElement("div");
			conbody.className="friend_information fl ml20";
			var nameA=document.createElement("a");
				nameA.href="members_home.html?userId="+attF.value[i].userId;//
				nameA.innerHTML=attF.value[i].petName;//
				nameA.target="iframeMain";
			conbody.appendChild(nameA);
			var p1=document.createElement("p");
				p1.className="f12 clearfloat";
				var sexSpan=document.createElement("span");
					sexSpan.className="fl";
					if(attF.value[i].sex=="0"){
						sexSpan.innerHTML="男";
					}
					else{
						sexSpan.innerHTML="女";
					};//
				p1.appendChild(sexSpan);
				var oldSpan=document.createElement("span");
					oldSpan.className="fr";
					oldSpan.innerHTML=attF.value[i].location;//
				p1.appendChild(oldSpan);
			conbody.appendChild(p1);
			var p2=document.createElement("p");
				p2.className="f12 clearfloat";
				var hisAtt=document.createElement("span");
					hisAtt.className="fl fb";
					hisAtt.innerHTML="他的关注：";
				p2.appendChild(hisAtt);
				var attNum=document.createElement("span");
					attNum.className="fr c08c";
					attNum.innerHTML=attF.value[i].attentionNum;//
				p2.appendChild(attNum);
			conbody.appendChild(p2);
			var p3=document.createElement("p");
				p3.className="f12 clearfloat";
				var hisFans=document.createElement("span");
					hisFans.className="fl fb";
					hisFans.innerHTML="他的粉丝：";
				p3.appendChild(hisFans);
				var fansNum=document.createElement("span");
					fansNum.className="fr c08c";
					fansNum.innerHTML=attF.value[i].funsNum;//
				p3.appendChild(fansNum);
			conbody.appendChild(p3);
			var p4=document.createElement("p");
				p4.className="tc";
				var butImgA=document.createElement("a");
					butImgA.className="f12 fl";
					butImgA.setAttribute("userId",attF.value[i].userId);
					butImgA.onclick=unfollowPost;
					var butImg=document.createElement("img");
						butImg.src="img/user_remove.gif";//
						butImg.style.width="15px";
						butImg.style.height="15px";
					butImgA.appendChild(butImg);
					var butSpan=document.createElement("span");
						butSpan.innerHTML="取消关注";
					butImgA.appendChild(butSpan);
				p4.appendChild(butImgA);
			conbody.appendChild(p4);
		li.appendChild(conbody);	
	af.appendChild(li);	
	}
}
/*取消关注*/
function unfollowPost(){
	var unfollowNum=null;
	var theEle=$(event.target);
	if(theEle.context.nodeName=="A"){
		unfollowNum=theEle.attr("userid");
	}else{
		unfollowNum=theEle.parent().attr("userid");
	}
	var a={"userId":unfollowNum};
	$.post("../../../userController/unCollectUser.do",{"paramter":JSON.stringify(a)},attentionFriendsPost,"json");
};

/*添加关注*/
/*添加关注部分搜索内容与想后台所传参数*/
var pName="";
var sexNum=3;
function clickSearch(){
	var sexN=document.getElementById("sexNum");
	pName=document.getElementById("sName").value;
	if(sexN.childNodes[1].selected){
		sexNum=sexN.childNodes[1].value;
	}else{
		sexNum=sexN.childNodes[0].value;
	}
	addFriendsPost();
}
/*添加关注的请求*/
function addFriendsPost(){
	var a={
		"sex":"3",
		"petName":pName,
		"freezeStatus":"1",
		"pageNum":pageNum,
		"pageCount":"3"
	};
	$.post("../../../userController/findByCondition.do",{"paramter":JSON.stringify(a)},addFriends,"json");
}
/*添加关注部分书写代码*/
function addFriends(addF){
	pageSet(addF.count,3);
	var af=document.getElementById("add_friends");
	af.innerHTML=null;
	for(var i=0;i<addF.value.length;i++){
	var li=document.createElement("li");
		li.className="friend_membersShow mt20 ml20";
		var headImgDiv=document.createElement("div");
			headImgDiv.className="friend_avatar fl";
			var headImgA=document.createElement("a");
				headImgA.href="members_home.html?userId="+addF.value[i].userId;//
				var headImg=document.createElement("img");
					headImg.src="../../../"+addF.value[i].imageUrl;//
				headImgA.appendChild(headImg);
			headImgDiv.appendChild(headImgA);
		li.appendChild(headImgDiv);
		
		var conbody=document.createElement("div");
			conbody.className="friend_information fl ml20";
			var nameA=document.createElement("a");
				nameA.href="members_home.html?userId="+addF.value[i].userId;//
				nameA.innerHTML=addF.value[i].petName;//
				nameA.target="iframeMain";
			conbody.appendChild(nameA);
			var p1=document.createElement("p");
				p1.className="f12 clearfloat";
				var sexSpan=document.createElement("span");
					sexSpan.className="fl";
					if(addF.value[i].sex=="0"){
						sexSpan.innerHTML="男";
					}
					else{
						sexSpan.innerHTML="女";
					};//
				p1.appendChild(sexSpan);
				var oldSpan=document.createElement("span");
					oldSpan.className="fr";
					oldSpan.innerHTML=addF.value[i].location;//
				p1.appendChild(oldSpan);
			conbody.appendChild(p1);
			var p2=document.createElement("p");
				p2.className="f12 clearfloat";
				var hisAtt=document.createElement("span");
					hisAtt.className="fl fb";
					hisAtt.innerHTML="他的关注：";
				p2.appendChild(hisAtt);
				var attNum=document.createElement("span");
					attNum.className="fr c08c";
					attNum.innerHTML=addF.value[i].attentionNum;//
				p2.appendChild(attNum);
			conbody.appendChild(p2);
			var p3=document.createElement("p");
				p3.className="f12 clearfloat";
				var hisFans=document.createElement("span");
					hisFans.className="fl fb";
					hisFans.innerHTML="他的粉丝：";
				p3.appendChild(hisFans);
				var fansNum=document.createElement("span");
					fansNum.className="fr c08c";
					fansNum.innerHTML=addF.value[i].funsNum;//
				p3.appendChild(fansNum);
			conbody.appendChild(p3);
			var p4=document.createElement("p");
				p4.className="tc";
				p4.innerHTML=addF.value[i].signature;
			conbody.appendChild(p4);
		li.appendChild(conbody);
			
			var addButCon=document.createElement("div");
				addButCon.className="friend_information fr";
				addButCon.style.width="400px";
				var addButA=document.createElement("a");
					addButA.className="f12 fr";
					addButA.setAttribute("userId",addF.value[i].userId);
					addButA.onclick=addFriendAttPost;
					var addButImg=document.createElement("img");
						addButImg.src="img/user_remove.gif";//
						addButImg.style.width="15px";
						addButImg.style.height="15px";
					addButA.appendChild(addButImg);
					var addButSpan=document.createElement("span");
						addButSpan.innerHTML="添加关注";
					addButA.appendChild(addButSpan);
				addButCon.appendChild(addButA);
			
		li.appendChild(addButCon);
	af.appendChild(li);	
	}
}
/*添加好友按钮请求*/
function addFriendAttPost(){
	var addFriendNum=null;
	var theEle=$(event.target);
	if(theEle.context.nodeName=="A"){
		addFriendNum=theEle.attr("userid");
	}else{
		addFriendNum=theEle.parent().attr("userid");
	}
	var a={"userId":addFriendNum};
	$.post("../../../userController/collectUser.do",{"paramter":JSON.stringify(a)},addFriendPrompt,"json");
}
/*显示添加关注状态刷新已关注数据*/
function addFriendPrompt(afp){
	if(afp.state=="1"){
		attentionFriendsPost();
		alert(afp.value);
	}else{
		alert(afp.value);
	}
}


/*个人粉丝部分*/
/*个人粉丝部分请求*/
function friendFunsPost(){
	var a={
			"pageNum":pageNum,
			"pageCount":"9",
			"userId":userId
	};
	$.post("../../../userController/findCurUserFuns.do",{"paramter":JSON.stringify(a)},friendFuns,"json");
}
/*个人粉丝部分书写代码*/
function friendFuns(funs){
	pageSet(funs.count,9,2);
	var af=document.getElementById("friend_funs");
	af.innerHTML=null;
	for(var i=0;i<funs.value.length;i++){
	var li=document.createElement("li");
		li.className="friend_membersShow mt20 ml20";
		var headImgDiv=document.createElement("div");
			headImgDiv.className="friend_avatar fl";
			var headImgA=document.createElement("a");
				headImgA.href="members_home.html?userId="+funs.value[i].userId;//
				var headImg=document.createElement("img");
					headImg.src="../../../"+funs.value[i].imageUrl;//
				headImgA.appendChild(headImg);
			headImgDiv.appendChild(headImgA);
		li.appendChild(headImgDiv);
		
		var conbody=document.createElement("div");
			conbody.className="friend_information fl ml20";
			var nameA=document.createElement("a");
				nameA.href="members_home.html?userId="+funs.value[i].userId;//
				nameA.innerHTML=funs.value[i].petName;//
				nameA.target="iframeMain";
			conbody.appendChild(nameA);
			var p1=document.createElement("p");
				p1.className="f12 clearfloat";
				var sexSpan=document.createElement("span");
					sexSpan.className="fl";
					if(funs.value[i].sex=="0"){
						sexSpan.innerHTML="男";
					}
					else{
						sexSpan.innerHTML="女";
					};//
				p1.appendChild(sexSpan);
				var oldSpan=document.createElement("span");
					oldSpan.className="fr";
					oldSpan.innerHTML=funs.value[i].location;//
				p1.appendChild(oldSpan);
			conbody.appendChild(p1);
			var p2=document.createElement("p");
				p2.className="f12 clearfloat";
				var hisAtt=document.createElement("span");
					hisAtt.className="fl fb";
					hisAtt.innerHTML="他的关注：";
				p2.appendChild(hisAtt);
				var attNum=document.createElement("span");
					attNum.className="fr c08c";
					attNum.innerHTML=funs.value[i].attentionNum;//
				p2.appendChild(attNum);
			conbody.appendChild(p2);
			var p3=document.createElement("p");
				p3.className="f12 clearfloat";
				var hisFans=document.createElement("span");
					hisFans.className="fl fb";
					hisFans.innerHTML="他的粉丝：";
				p3.appendChild(hisFans);
				var fansNum=document.createElement("span");
					fansNum.className="fr c08c";
					fansNum.innerHTML=funs.value[i].funsNum;//
				p3.appendChild(fansNum);
			conbody.appendChild(p3);
			var p4=document.createElement("p");
				p4.className="tc";
				var butImgA=document.createElement("a");
					butImgA.className="f12 fl";
					butImgA.setAttribute("userId",funs.value[i].userId);//
					butImgA.onclick=addFriendAttPost;
					var butImg=document.createElement("img");
						butImg.src="img/user_remove.gif";//
						butImg.style.width="15px";
						butImg.style.height="15px";
					butImgA.appendChild(butImg);
					var butSpan=document.createElement("span");
						butSpan.innerHTML="添加关注";
					butImgA.appendChild(butSpan);
				p4.appendChild(butImgA);
			conbody.appendChild(p4);
		li.appendChild(conbody);	
	af.appendChild(li);	
	}
}

/*访客内容*/
/*访客部分请求*/
function friendVisitPost(){
	var a={
			"pageNum":pageNum,
			"pageCount":"9",
			"userId":userId
	};
	$.post("../../../userController/findCurUserBrowse.do",{"paramter":JSON.stringify(a)},friendVisit,"json");
}
/*访客书写代码*/
function friendVisit(visits){
	pageSet(visits.count,9);
	var af=document.getElementById("friend_visits");
	af.innerHTML=null;
	for(var i=0;i<visits.value.length;i++){
	var li=document.createElement("li");
		li.className="friend_membersShow mt20 ml20";
		var headImgDiv=document.createElement("div");
			headImgDiv.className="friend_avatar fl";
			var headImgA=document.createElement("a");
				headImgA.href="members_home.html?userId="+visits.value[i].userId;//
				var headImg=document.createElement("img");
					headImg.src="../../../"+visits.value[i].imageUrl;//
				headImgA.appendChild(headImg);
			headImgDiv.appendChild(headImgA);
		li.appendChild(headImgDiv);
		
		var conbody=document.createElement("div");
			conbody.className="friend_information fl ml20";
			var nameA=document.createElement("a");
				nameA.href="members_home.html?userId="+visits.value[i].userId;//
				nameA.innerHTML=visits.value[i].petName;//
				nameA.target="iframeMain";
			conbody.appendChild(nameA);
			var p1=document.createElement("p");
				p1.className="f12 clearfloat";
				var sexSpan=document.createElement("span");
					sexSpan.className="fl";
					if(visits.value[i].sex=="0"){
						sexSpan.innerHTML="男";
					}
					else{
						sexSpan.innerHTML="女";
					};//
				p1.appendChild(sexSpan);
				var oldSpan=document.createElement("span");
					oldSpan.className="fr";
					oldSpan.innerHTML=visits.value[i].location;//
				p1.appendChild(oldSpan);
			conbody.appendChild(p1);
			var p2=document.createElement("p");
				p2.className="f12 clearfloat";
				var hisAtt=document.createElement("span");
					hisAtt.className="fl fb";
					hisAtt.innerHTML="他的关注：";
				p2.appendChild(hisAtt);
				var attNum=document.createElement("span");
					attNum.className="fr c08c";
					attNum.innerHTML=visits.value[i].attentionNum;//
				p2.appendChild(attNum);
			conbody.appendChild(p2);
			var p3=document.createElement("p");
				p3.className="f12 clearfloat";
				var hisFans=document.createElement("span");
					hisFans.className="fl fb";
					hisFans.innerHTML="他的粉丝：";
				p3.appendChild(hisFans);
				var fansNum=document.createElement("span");
					fansNum.className="fr c08c";
					fansNum.innerHTML=visits.value[i].funsNum;//
				p3.appendChild(fansNum);
			conbody.appendChild(p3);
			var p4=document.createElement("p");
				p4.className="tc";
				var butImgA=document.createElement("a");
					butImgA.className="f12 fl";
					butImgA.setAttribute("userId",visits.value[i].userId);//
					butImgA.onclick=addFriendAttPost;
					var butImg=document.createElement("img");
						butImg.src="img/user_remove.gif";//
						butImg.style.width="15px";
						butImg.style.height="15px";
					butImgA.appendChild(butImg);
					var butSpan=document.createElement("span");
						butSpan.innerHTML="添加关注";
					butImgA.appendChild(butSpan);
				p4.appendChild(butImgA);
			conbody.appendChild(p4);
		li.appendChild(conbody);	
	af.appendChild(li);	
	}
}

/**选项卡切换与分页有联系
 * 切换时当前页自动为1
 * 上一页最小至1
 * 下一页最大至当前总页数
 * 跳转页面输入的书大于总页数或小于0 函数不作为。
 * 跳转页面改进的话就是不能输入大于总页数的数字，或者输入跳转，value值自动还原，禁止输入负数 字母 文字 符号等
 * */
function tabNav1(num) {
	for (var i = 0; i < 4; i++) {
		document.getElementById("cntaMenu_"+i).style.borderBottom='none';
		document.getElementById('cnta_' + i).style.display = 'none';
	}
	
	showObj=num;
	document.getElementById("pageNumInp").value=1;
	pageNum=1;
	document.getElementById('cnta_' + num).style.display= 'block';
	document.getElementById("cntaMenu_"+num).style.borderBottom='2px solid #FFCC33';
	if(num==0){
		attentionFriendsPost();
	}
	if(num==1){
		addFriendsPost();
	}
	if(num==2){
		friendFunsPost();
	}
	if(num==3){
		friendVisitPost();
	}
}
/*分页点击事件*/
function pageCount(){
	return parseInt(document.getElementById("pageSumNum").value);
}
function pageFirstClick(){
	pageNum=1;
	pageNumInp.value=pageNum;
	judgeShowObj();
}
function pageLastClick(){
	pageNum=pageCount();
	pageNumInp.value=pageNum;
	judgeShowObj();
}
function pageAddClick(){
	if(pageNum<pageCount()){
		pageNum+=1;
		pageNumInp.value=pageNum;
		judgeShowObj();
	}else{
		return false;
	}
}
function pageMinusClick(){
	if(pageNum>0){
		pageNum-=1;
		pageNumInp.value=pageNum;
		judgeShowObj();
	}else{
		return false;
	}
	
}
function skipClick(){
	pageNum=parseInt(document.getElementById("pageNumInp").value);
	if(pageNum<pageCount()&&pageNum>0){
		judgeShowObj();
	}
}
/*判断哪页处于显示状态并执行请求刷新该页面*/
function judgeShowObj(){
	if(showObj==0){
		attentionFriendsPost();
	}else if(showObj==1){
		addFriendsPost();
	}else if(showObj==2){
		friendFunsPost();
	}else if(showObj==3){
		friendVisitPost();
	}
}

function pageSet(count,num){
	var countPd=parseInt(count)%num;
	var countNum=parseInt(count)-parseInt(count)%num;
		if(countPd==0){
			document.getElementById("pageSumNum").value=countNum/num;
		}else{
			document.getElementById("pageSumNum").value=(countNum/num)+1;
		}
}

// JavaScript Document
/*var arr=new Array();
 arr=["../../../upload/activity/images/deliciousFood/cq/cq.gif",
 "../../../upload/activity/images/deliciousFood/cq/cq1.gif",
 "../../../upload/activity/images/deliciousFood/cq/cq2.gif",
 "../../../upload/activity/images/deliciousFood/cq/cq3.gif",
 "../../../upload/activity/images/deliciousFood/cq/cq4.gif",
 "../../../upload/activity/images/deliciousFood/cq/cq5.gif",
 "../../../upload/activity/images/deliciousFood/cq/cq6.gif",
 "../../../upload/activity/images/deliciousFood/cq/cq7.gif",
 ];
 var index=1;
 */
var activeId = "";
var activeTypeId = "";
var pageNum = 0;
var pageNum1 = 0;
var type = "hot";
var commentType = 3;
var foodValue = null;
function init() {
	/* setInterval(showImage,3700); */
	$.post("../../../activeController/collect.do", {}, initHeader, "json");
}

// 初始化页头
function initHeader(ret) {
	activeId = ret.value[0].activeId.toString();
	activeTypeId = ret.value[0].activeType;
	var div = document.getElementById("header");
	div.innerHTML = "";
	var p = document.createElement("p");
	p.className = "p1";
	p.innerHTML = ret.value[0].activeName;
	div.appendChild(p);
	for ( var i = 0; i < 5; i++) {
		var img = document.createElement("img");
		var imageUrl = $.parseJSON(ret.value[0].images);
		if (i == 0) {
			img.id = "img";
		}
		if (i == 1) {
			img.className = "img";
		}
		img.src = "../../../" + imageUrl.images[i];
		img.alt = "加载失败";
		div.appendChild(img);
	}
	var div1 = document.createElement("div");
	div1.className = "focus";
	var img1 = document.createElement("img");
	img1.src = "../../../upload/activity/images/deliciousFood/1.png";
	img1.alt = "加载失败";
	div1.appendChild(img1);
	var div2 = document.createElement("div");
	div2.className = "apply";
	var p1 = document.createElement("p");
	p1.innerHTML = "已报名人数";
	div2.appendChild(p1);
	var m = 0;
	for ( var j = 0; j < 6; j++) {
		var span = document.createElement("span");

		if (j < (6 - (ret.value[0].joinNum).toString().length)) {

			span.innerHTML = 0;
			div2.appendChild(span);
		} else {
			var num = (ret.value[0].joinNum).toString().substr(m++, 1);
			span.innerHTML = num;
			div2.appendChild(span);
		}

	}
	var input = document.createElement("input");
	input.type = "button";
	input.value = "立即报名";
	input.onclick = showApply;
	div2.appendChild(input);
	var input1 = document.createElement("input");
	input1.type = "button";
	input1.value = "关注此活动";
	input1.setAttribute("activeId", ret.value[0].activeId);
	input1.onclick = collectActive;
	div2.appendChild(input1);
	div1.appendChild(div2);
	div.appendChild(div1);
	var div3 = document.createElement("div");
	div3.className = "roles";
	var img2 = document.createElement("img");
	img2.src = "../../../upload/activity/images/deliciousFood/2.png";
	img2.alt = "加载失败";
	div3.appendChild(img2);
	var div4 = document.createElement("div");
	div4.innerHTML = ret.value[0].productDesc;
	div3.appendChild(div4);
	div.appendChild(div3);
	var span7 = document.getElementById("active_name");
	span7.innerHTML = ret.value[0].activeName;

	nextPage();
	initRank();
	nextPage1();
}
// 关注活动
function collectActive() {
	$.post("../../../activeController/collectActive.do", {
		"paramter" : JSON.stringify({
			"activeId" : activeId
		})
	}, collectActiveCallBack, "json");
}
function collectActiveCallBack(ret) {
	if (ret.state == "0") {
		alert(ret.value);
		return;
	} else {
		alert(ret.value);
		return;
	}
}

// 显示火热进行中
function showHotAcity() {
	debugger;
	type = "hot";
	pageNum = 0;
	nextPage();
	var ul = document.getElementById("personMakeFood");
	ul.style.display = "block";
	var ulPast = document.getElementById("pastFoodAcity");
	ulPast.style.display = "none";
}
// 显示往期活动
function showPastAcity() {
	type = "past";
	pageNum = 0;
	nextPage();
	var ul = document.getElementById("personMakeFood");
	ul.style.display = "none";
	var ulPast = document.getElementById("pastFoodAcity");
	ulPast.style.display = "block";
}
function pagePost(param) {
	debugger;
	if (type == "hot") {
		$.post("../../../activeController/findActiveJoinByPage.do", {
			"paramter" : JSON.stringify(param)
		}, pagePostCallBack, "json");
	}
	if (type == "past") {
		$.post("../../../activeController/findActiveByPageAndType.do", {
			"paramter" : JSON.stringify(param)
		}, pagePostCallBack, "json");
	}
}
function pagePostCallBack(ret) {
	if (ret.state == "0") {
		alert(ret.value);
		return;
	}
	debugger;
	if (type == "hot") {
		var num = ret.count % 6;
		var sumNum = document.getElementById("sumNum");
		sumNum.innerHTMl = "";
		if (num == 0 && ret.count >= 6) {
			sumNum.innerHTML = Math.floor(ret.count / 6);
		} else {
			sumNum.innerHTML = Math.floor(ret.count / 6) + 1;
		}
		var ul = document.getElementById("personMakeFood");
		ul.innerHTML = "";
		for ( var i = 0; i < ret.value.length; i++) {
			var li = document.createElement("li");
			var img = document.createElement("img");
			var imageUrl = JSON.parse(ret.value[i].images);
			img.src = "../../../" + imageUrl.images[0];
			img.onclick = showStep;
			img.setAttribute("foodValue", JSON.stringify(ret.value[i]));
			li.appendChild(img);
			var p = document.createElement("p");
			var span = document.createElement("span");
			span.className = "sp1";
			span.innerHTML = "美食";
			p.appendChild(span);
			var span1 = document.createElement("span");
			span1.innerHTML = ret.value[i].joinName;
			p.appendChild(span1);
			var span2 = document.createElement("span");
			span2.className = "sp3";
			span2.innerHTML = ret.value[i].praiseNum;
			p.appendChild(span2);
			var img1 = document.createElement("img");
			img1.src = "../../../upload/activity/images/deliciousFood/heart.jpg";
			img1.setAttribute("activeJoinId", ret.value[i].activeJoinId);
			img1.onclick = prise;
			p.appendChild(img1);
			li.appendChild(p);
			var p1 = document.createElement("p");
			var span3 = document.createElement("span");
			span3.className = "sp1";
			span3.innerHTML = "参赛者";
			p1.appendChild(span3);
			var span4 = document.createElement("span");
			span4.innerHTML = ret.value[i].petName;
			p1.appendChild(span4);
			li.appendChild(p1);
			var p2 = document.createElement("p");
			var span5 = document.createElement("span");
			span5.className = "sp1";
			span5.innerHTML = "美食介绍";
			p2.appendChild(span5);
			var span6 = document.createElement("span");
			span6.innerHTML = ret.value[i].joinDesc;
			p2.appendChild(span6);
			li.appendChild(p2);
			ul.appendChild(li);
		}
	}
	if (type == "past") {
		var num = ret.count % 6;
		var sumNum = document.getElementById("sumNum");
		sumNum.innerHTMl = "";
		if (num == 0 && ret.count >= 6) {
			sumNum.innerHTML = Math.floor(ret.count / 6);
		} else {
			sumNum.innerHTML = Math.floor(ret.count / 6) + 1;
		}
		var ulPast = document.getElementById("pastFoodAcity");
		ulPast.innerHTML = "";
		for ( var j = 0; j < ret.value.length; j++) {
			var li1 = document.createElement("li");
			var a = document.createElement("a");
			a.href = "pastFoodAcivity.html?activeId=" + ret.value[j].activeId;
			var img6 = document.createElement("img");
			var imgUrl = $.parseJSON(ret.value[j].images);
			img6.src = "../../../" + imgUrl.images[0];
			a.appendChild(img6);
			li1.appendChild(a);
			var a1 = document.createElement("a");
			a1.href = "pastFoodAcivity.html?activeId=" + ret.value[j].activeId;
			a1.className = "a1";
			a1.innerHTML = ret.value[j].activeName;
			li1.appendChild(a1);
			ulPast.appendChild(li1);
		}
	}

}

// 点赞
function prise() {
	var activeJoinId = event.srcElement.getAttribute("activeJoinId");
	$.post("../../../activeController/praiseActiveJoin.do", {
		"paramter" : JSON.stringify({
			"activeJoinId" : activeJoinId
		})
	}, priseCallBack, "json");
}
function priseCallBack(ret) {
	if (ret.state == "0") {
		alert(ret.value);
	}
	pageNum = 0;
	nextPage();

}

// 下一页（初始化参赛作品）
function nextPage() {
	debugger;
	var page = document.getElementById("pageNum");
	var sumNum = document.getElementById("sumNum").innerHTML;
	if (pageNum >= sumNum) {
		return;
	}
	pageNum++;
	page.value = pageNum;
	if (type == "hot") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeId" : activeId
		};
	}
	if (type == "past") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeType" : activeTypeId
		};
	}
	pagePost(param);
}

// 上一页
function pastPage() {
	debugger;
	var page = document.getElementById("pageNum");
	var sumNum = document.getElementById("sumNum").innerHTML;
	if (pageNum <= 1) {
		return;
	}
	pageNum--;
	page.value = pageNum;
	if (type == "hot") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeId" : activeId
		};
	}
	if (type == "past") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeType" : activeTypeId
		};
	}
	pagePost(param);
}

// 末页
function lastPage() {
	var page = document.getElementById("sumNum");
	var page1 = document.getElementById("pageNum");
	pageNum = parseInt(page.innerHTML);
	if (type == "hot") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeId" : activeId
		};
	}
	if (type == "past") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeType" : activeTypeId
		};
	}
	pagePost(param);
	page1.value = pageNum;
}

// 首页
function firstPage() {
	pageNum = 1;
	var page1 = document.getElementById("pageNum");
	if (type == "hot") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeId" : activeId
		};
	}
	if (type == "past") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeType" : activeTypeId
		};
	}
	pagePost(param);
	page1.value = pageNum;
}

// 跳转
function redirect() {
	var page = document.getElementById("pageNum");
	var sumNum = document.getElementById("sumNum").innerHTML;
	pageN = parseInt(page.value);
	if (pageN >= sumNum || pageN < 1) {
		return;
	}
	pageNum = pageN;
	if (type == "hot") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeId" : activeId
		};
	}
	if (type == "past") {
		var param = {
			"pageNum" : pageNum,
			"pageCount" : 6,
			"activeType" : activeTypeId
		};
	}
	pagePost(param);
}

// 初始化排行榜
function initRank() {
	$.post("../../../activeController/findActiveJoinByCount.do", {
		"paramter" : JSON.stringify({
			"count" : 10,
			"activeId" : activeId
		})
	}, initRankCallBack, "json");
}
function initRankCallBack(ret) {
	if (ret.state == "0") {
		alert(ret.value);
		return;
	}
	var div = document.getElementById("rank");
	div.innerHTML = "";
	var p = document.createElement("p");
	p.innerHTML = "排行榜";
	div.appendChild(p);
	var ul = document.createElement("ul");
	for ( var i = 0; i < ret.value.length; i++) {
		if (i >= 0 && i < 3) {
			var li = document.createElement("li");
			var img = document.createElement("img");
			if (i == 0) {
				img.src = "../../../upload/activity/images/photoPK/1.png";
			}
			if (i == 1) {
				img.src = "../../../upload/activity/images/photoPK/2.png";
			}
			if (i == 2) {
				img.src = "../../../upload/activity/images/photoPK/3.png";
			}
			li.appendChild(img);
		} else {
			var p1 = document.createElement("p");
			p1.className = "number";
			p1.innerHTML = i + 1;
			li.appendChild(p1);
		}
		var p2 = document.createElement("p");
		p2.innerHTML = ret.value[i].petName;
		var p3 = document.createElement("p");
		p3.innerHTML = ret.value[i].praiseNum + "人赞";
		li.appendChild(p2);
		li.appendChild(p3);
		ul.appendChild(li);
	}
	div.appendChild(ul);
}

// 选择评论类型
function check() {
	var obj = event.srcElement;
	var input = document.getElementById("checked");
	var sum = document.getElementById("sumNum1");
	sum.innerHTML = "7";
	input.id = "";
	obj.id = "checked";
	obj.checked = "checked";
	commentType = obj.value;
	pageNum1 = 0;
	nextPage1();
}

// 初始化评论
function initComment(param) {
	$.post("../../../commentController/findCommentById.do", {
		"paramter" : JSON.stringify(param)
	}, initCommentCallBack, "json");
}
function initCommentCallBack(ret) {
	if (ret.state == "0") {
		alert(ret.value);
		return;
	}
	var num = ret.count % 4;
	var sumNum = document.getElementById("sumNum1");
	sumNum.innerHTMl = "";
	if (num == 0 && ret.count >= 4) {
		sumNum.innerHTML = Math.floor(ret.count / 4);
	} else {
		sumNum.innerHTML = Math.floor(ret.count / 4) + 1;
	}
	var ul = document.getElementById("allComment1");
	ul.innerHTML = "";
	for ( var i = 0; i < ret.comment.length; i++) {
		var li = document.createElement("li");
		var img = document.createElement("img");
		img.src = "../../../" + ret.comment[i].imageUrl;
		img.className = "answers_img";
		var p = document.createElement("p");
		p.className = "userName";
		p.innerHTML = ret.comment[i].petName;
		var span = document.createElement("span");
		span.innerHTML = "发表于" + ret.comment[i].createTime;
		p.appendChild(span);
		var a = document.createElement("a");
		a.innerHTML = "删除评论";
		a.className = "deleteComment";
		a.onclick = deleteComment;
		a.setAttribute("commentId", ret.comment[i].commentId);
		if (window.parent.user == null) {
			a.style.display = "none";
		} else {
			if (window.parent.user.userId == ret.comment[i].userId) {
				a.style.display = "block";
			}
		}
		var p1 = document.createElement("p");
		p1.className = "commentDetail";
		p1.innerHTML = ret.comment[i].commentContent;
		li.appendChild(img);
		li.appendChild(p);
		li.appendChild(a);
		li.appendChild(p1);
		ul.appendChild(li);
	}
}

// 下一页
function nextPage1() {
	var page = document.getElementById("pageNum1");
	var sumNum = document.getElementById("sumNum1").innerHTML;
	if (pageNum1 >= sumNum) {
		return;
	}
	pageNum1++;
	page.value = pageNum1;
	var param = {
		"relId" : activeId,
		"commentType" : 4,
		"commentLevel" : commentType,
		"pageNum" : pageNum1,
		"pageCount" : 4
	};
	initComment(param);

}

// 上一页
function pastPage1() {
	var page = document.getElementById("pageNum1");
	var sumNum = document.getElementById("sumNum1").innerHTML;
	if (pageNum1 <= 1) {
		return;
	}
	pageNum1--;
	page.value = pageNum1;
	var param = {
		"relId" : activeId,
		"commentType" : 4,
		"commentLevel" : commentType,
		"pageNum" : pageNum1,
		"pageCount" : 4
	};
	initComment(param);
}

// 末页
function lastPage1() {
	var page = document.getElementById("sumNum1");
	var page1 = document.getElementById("pageNum1");
	pageNum1 = parseInt(page.innerHTML);
	var param = {
		"relId" : activeId,
		"commentType" : 4,
		"commentLevel" : commentType,
		"pageNum" : pageNum1,
		"pageCount" : 4
	};
	initComment(param);
	page1.value = pageNum;
}

// 首页
function firstPage1() {
	pageNum1 = 1;
	var page1 = document.getElementById("pageNum1");
	var param = {
		"relId" : activeId,
		"commentType" : 4,
		"commentLevel" : commentType,
		"pageNum" : pageNum1,
		"pageCount" : 4
	};
	initComment(param);
	page1.value = pageNum1;
}
// 跳转
function redirect1() {
	var page = document.getElementById("pageNum1");
	var sumNum = document.getElementById("sumNum1").innerHTML;
	pageN = parseInt(page.value);
	if (pageN >= sumNum || pageN < 1) {
		return;
	}
	pageNum1 = pageN;
	var param = {
		"relId" : activeId,
		"commentType" : 4,
		"commentLevel" : commentType,
		"pageNum" : pageNum1,
		"pageCount" : 4
	};
	initComment(param);
}

// 删除评论
function deleteComment() {
	var commentId = event.srcElement.getAttribute("commentId");
	$.post("../../../commentController/deleteCommentById.do", {
		"paramter" : JSON.stringify({
			"commentId" : commentId
		})
	}, deleteCommentCallBack, "json");
}
function deleteCommentCallBack(ret) {
	if (ret.state == "0") {
		alert(ret.value);
		return;
	}
	pageNum1 = 0;
	nextPage1();
}

// 显示评论框
function submitComment() {
	var div = document.getElementById("saveComment_div");
	div.style.display = "block";
}

// 隐藏评论框
function hiddenComment() {
	var div = document.getElementById("saveComment_div");
	var content = document.getElementById("talk").value = "";
	div.style.display = "none";
}

// 发表评论
function saveComment() {
	var arr = document.getElementsByName("comment");
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i].checked) {
			var commentLevel = arr[i].value;
		}
	}
	var content = document.getElementById("talk").value;
	if (commentLevel == undefined) {
		alert("请选择评论类型！");
		return;
	}
	$.post("../../../commentController/saveComment.do", {
		"paramter" : JSON.stringify({
			"relId" : activeId,
			"commentType" : 4,
			"commentContent" : content,
			"commentLevel" : commentLevel
		})
	}, saveCommentCallBack, "json");
}
function saveCommentCallBack(ret) {
	if (ret.state == "0") {
		alert(ret.value);
		return;
	}
	hiddenComment();
	pageNum1 = 0;
	nextPage1();
}

// 评论剩余字数
function checkLength() {
	var length = document.getElementById("talk").value.length;
	var number = parseInt(100 - length);
	var span = document.getElementById("wordNumber");
	span.innerHTML = number + "/100";
	if (number <= 0) {
		document.getElementById("talk").value = document.getElementById("talk").value
				.substring(0, 100);
	}
}

// 显示头部图片
function showImage() {
	var img = document.getElementById("img");
	img.src = arr[index++];
	if (index == 8) {
		index = 0;
	}
}
// 显示报名表
function showApply() {
	if (window.parent.user == null) {
		var c = window.confirm("登陆后可打开，是否跳转到登录页！");
		if (c == true) {
			window.parent.login();
			return;
		} else {
			return;
		}
	}
	var apply = document.getElementById("foodApply_container");
	apply.style.display = "block";
	var petName = document.getElementById("petName");
	petName.innerHTML = "";
	petName.innerHTML = window.parent.user.petName;
	var activeId1 = document.getElementById("activeId");
	activeId1.value = activeId;
	var activeType1 = document.getElementById("activeType");
	activeType1.value = activeTypeId;
}
// 隐藏报名表
function closeApply() {
	var apply = document.getElementById("foodApply_container");
	apply.style.display = "none";
}
// 显示烹饪步骤
function showStep() {
	debugger;
	foodValue = $.parseJSON(event.srcElement.getAttribute("foodValue"));
	window
			.open(
					"makeStep.html",
					"_blank",
					"width=580,status=no,resizable=no,height=600,toolbar=no,menubar=no,left=400,top=5%;");
}
/*
 * //隐藏烹饪步骤 function closeStep() { var
 * step=document.getElementById("makeFoodStep"); var
 * make=document.getElementById("makeFood"); make.style.display="none";
 * step.style.display="none"; }
 */
// 图片预览
function previewImage(file) {
	debugger;
	var MAXWIDTH = 100;
	var MAXHEIGHT = 100;
	var div = event.srcElement.parentNode.lastChild;
	div.style.display = "block";
	div.parentNode.firstChild.innerHTML = "";
	if (file.files && file.files[0]) {
		div.innerHTML = '<img id=imghead>';
		var img = div.firstChild;
		img.addEventListener("click", function() {
		}, false);
		img.style.display = "block";
		img.onload = function() {
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			img.width = rect.width;
			img.height = rect.height;
			img.style.marginLeft = rect.left + 'px';
			img.style.marginTop = rect.top + 'px';
		}
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
		}
		reader.readAsDataURL(file.files[0]);
	} else {
		var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
				img.offsetHeight);
		status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
		div.innerHTML = "<div id=divhead style='width:" + rect.width
				+ "px;height:" + rect.height + "px;margin-top:" + rect.top
				+ "px;margin-left:" + rect.left + "px;" + sFilter + src
				+ "\"'></div>";
	}
}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		top : 0,
		left : 0,
		width : width,
		height : height
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = Math.round(height / rateWidth);
		} else {
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}

	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}
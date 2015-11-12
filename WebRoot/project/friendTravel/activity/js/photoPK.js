// JavaScript Document
var index = 1;
var arrPast;
var activeId;
var message;
function init() {
	debugger;
	$.post("../../../activeController/findPhotoActive.do", {
		"paramter" : JSON.stringify({
			"count" : "ALL"
		})
	}, show, "json");
	$.post("../../../activeController/findPhotoActive.do", {
		"paramter" : JSON.stringify({
			"count" : "ALL"
		})
	}, getZhong, "json");
}
//最上面的图片初始化
function show(pic){
	var pic_value=pic.value;
	var picUl = document.getElementById("picUl");
	picUl.innerHTML="";
	var picli = document.createElement("li");
	var pic = document.createElement("img");
	var images = JSON.parse(pic_value[0].images)
	pic.src = "../../../" + images.images[1];
	picli.appendChild(pic);
	picUl.appendChild(picli);
}
//点击小图，变成大图
function showPic() {
	debugger;
	var curImg = event.srcElement;
	var content=curImg.parentNode.lastChild;
	var picUl = document.getElementById("picUl");
	picUl.innerHTML="";
	var picli = document.createElement("li");
	var pic = document.createElement("img");
	pic.src = curImg.src;
	picli.appendChild(pic);
	var mengban=document.createElement("div");
	mengban.className="imgM";
	var span=document.createElement("span");
	span.innerHTML=content.innerHTML;
	mengban.appendChild(span);
	picli.appendChild(mengban);
	picUl.appendChild(picli);

}
// 中式美食
function getZhong(ret) {
	debugger;
	var arr = ret.value;
	arrPast = arr;
	getZhongMenu();
}
function getZhongMenu() {
	debugger;
	var ul = document.getElementById("photo");
	ul.innerHTML = "";
	var m;
	if (index + 5 <= arrPast.length) {
		m = index + 5;
	} else {
		m = arrPast.length;
	}
	for ( var i = index; i < m; i++) {
		var images = JSON.parse(arrPast[i].images);
		var li = document.createElement("li");
		var a = document.createElement("a");
		var img = document.createElement("img");
		img.src = "../../../" + images.images[1];
		img.alt = "error";
		img.onmouseover = showPic;
		a.appendChild(img);
		var message = document.createElement("div");
		message.innerHTML = arrPast[i].activeName;
		var contentS=document.createElement("div");
		contentS.innerHTML=arrPast[i].voteDesc;
		contentS.className="content";
		a.appendChild(message);
		a.appendChild(contentS);
		li.appendChild(a);
		ul.appendChild(li);
	}
}
// 上一期
function pastActive() {
	if (index == 1) {
		index = 1;
	} else {
		index = index - 5;
	}
	getZhongMenu();
}
// 下一期
function nextActive() {
	if (index + 5 < arrPast.length) {
		index = index + 5;
		getZhongMenu();
	}
}

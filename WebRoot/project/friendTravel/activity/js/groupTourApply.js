// JavaScript Document
var index = 0;
var time;
var arrImg;
function init() {
	getRelTrip();
}
//详情信息
function getRelTrip() {
	var mp = new BMap.Map('map');
	var map = document.getElementById("map");
	var point = new BMap.Point(116.404, 39.915);
	mp.centerAndZoom(point, 5);
	mp.addControl(new BMap.MapTypeControl());
	var top_left_control = new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_TOP_LEFT
	});
	mp.addControl(top_left_control); 
	var div = $("#tripCommand");
	var ul = $("<ul/>");
	div.append(ul, map);
	var li = $("<li/>");
	var a = $("<a/>");
	var name = $("<h2/>")[0];
	name.innerHTML = "北京"
	name.className = "nameH";
	var name2 = $("<h2/>")[0];
	name2.innerHTML = "双人房";
	name2.className = "name2";
	li.append(name);
	li.append(name2);
	var img = $("<img/>");
	img.attr("src", "../../../upload/activity/images/photoPK/e1.jpg");
	a.append(img);
	li.append(a);
	var aDiv = $("<div/>");
	var a1 = $("<a/>");
	a1.append("<span>" + "￥" + "123" + "</span>");
	aDiv.append(a1);
	var discount = $("<p/>")[0];
	discount.innerHTML = "4.5折";
	discount.className = "discount";
	aDiv.append(discount);
	var div = $("<div/>")[0];
	div.innerHTML = "100人已购";
	aDiv.append(div);
	var dateP = $("<p/>")[0];
	dateP.innerHTML = "有效期：2016-9-1";
	aDiv.append(dateP);
	dateP.className = "dateP";
	li.append(aDiv);
	ul.append(li);
	var foodDiv=document.createElement("div");
	foodDiv.className="foodDiv";
	div.appendChild(foodDiv);
	var foodTitle=document.createElement("h2");
	foodTitle.innerHTML="健康食物";
	foodDiv.appendChild(foodTitle);
	var foodImg=document.createElement("img");
	foodImg.src="../../../upload/activity/images/photoPK/222.jpg";
	foodDiv.appendChild(foodImg);
	var content=document.createElement("p");
	content.innerHTML="豆浆是中国人的传统早餐美食，我们平时喝豆浆一般不过滤豆渣，因为感觉豆渣很有营养";
	foodDiv.appendChild(content);
	
}

var travel=null;


function init(){
	if(window.parent.parent.user==null){
		return;
	}
	initBody();
}

//初始化游记列表
function initBody(){
	debugger;
	$.post("../../../travelDescController/findDescByUser.do",{"paramter":JSON.stringify({"userId":window.parent.parent.user.userId})},initBodyCallBack,"json");
}


function initBodyCallBack(ret){
	if(ret.state=="0"){
		alert(ret.value);
	}
	var ul=document.getElementById("myTravels_travels");
		ul.innerHTML="";
	for(var i = 0; i< ret.value.length; i ++){
		var li = document.createElement("li");
			li.className="travel clearfloat";
		var div = document.createElement("div");
			div.className="fl";
		var a = document.createElement("a");
			a.className="c0";
		var img = document.createElement("img");
		var imageUrl=$.parseJSON(ret.value[i].images);
			img.className="img";
			img.src="../../../"+imageUrl.images[0];
			a.appendChild(img);
			div.appendChild(a);
			li.appendChild(div);
		var div1 = document.createElement("div");
			div1.className="fl travel_con ml20";
		var h3 = document.createElement("h3");
		var a1 = document.createElement("a");
			a1.className="travel_title";
			a1.innerHTML=ret.value[i].descTitle;
			h3.appendChild(a1);
			div1.appendChild(h3);
		var div2 = document.createElement("div");
			div2.id="word";
			div2.className="travel_word f14 mt20";
			div2.innerHTML=ret.value[i].descContent;
			div1.appendChild(div2);
		var div3 = document.createElement("div");
			div3.className="travel_date f12";
		var span = document.createElement("span");
			span.innerHTML=ret.value[i].createTime;
			div3.appendChild(span);
			div1.appendChild(div3);
		var div4 = document.createElement("div");
			div4.className="f14 mt20";
		var span1 = document.createElement("span");
		var i6 = document.createElement("i");
			i6.className="travel_i1 fl ml30";
		var span2 = document.createElement("span");
			span2.className="fl ml10";
			span2.innerHTML=ret.value[i].praiseNum;
			span1.appendChild(i6);
			span1.appendChild(span2);
			div4.appendChild(span1);
		var span3 = document.createElement("span");
		var i1 = document.createElement("i");
			i1.className="travel_i2 fl ml30";
		var span4 = document.createElement("span");
			span2.className="fl ml10";
			span2.innerHTML=ret.value[i].commentNum;
			span3.appendChild(i1);
			span3.appendChild(span4);
			div4.appendChild(span3);
		var span5 = document.createElement("span");
		var i2 = document.createElement("i");
			i2.className="travel_i3 fl ml30";
		var span6 = document.createElement("span");
			span6.className="fl ml10";
			span6.innerHTML=ret.value[i].collectNum;
			span5.appendChild(i2);
			span5.appendChild(span6);
			div4.appendChild(span5);
		var span7 = document.createElement("span");
		var i3 = document.createElement("i");
			i3.className="travel_i4 fl ml30";
			i3.setAttribute("travelId",ret.value[i].travelDescId);
			i3.onclick=deleteTravel;
			span7.appendChild(i3);
			div4.appendChild(span7);
			if(window.parent.parent.user.userId!=ret.value[i].userId){
				i3.style.display="none";
			}
			debugger;
		var span9 = document.createElement("span");
		var i4 = document.createElement("i");
			i4.className="travel_i5 fl ml30";
			i4.setAttribute("travelId",JSON.stringify(ret.value[i].travelDescId));
			i4.onclick=updateTravel;
			span9.appendChild(i4);
			div4.appendChild(span9);
			div1.appendChild(div4);
			if(window.parent.parent.user.userId!=ret.value[i].userId){
				i4.style.display="none";
			}
			li.appendChild(div1);
			ul.appendChild(li);
			
	}
}



//删除游记
function deleteTravel(){
	var c = window.confirm("是否删除游记！");
	if(c){
		var travelId=event.srcElement.getAttribute("travelId");
		$.post("../../../travelDescController/deleteTravelDesc.do",{"paramter":JSON.stringify({"travelDescId":travelId})},deleteTravelCallBack,"json");
	}else{
		return;
	}
}
function deleteTravelCallBack(ret){
	debugger;
	if(ret.state=="0"){
		alert(ret.value);
		return;
	}
	initBody();
}

//修改游记
function updateTravel(){
	debugger;
	travel=event.srcElement.getAttribute("travelId");
	window.location.href="../travel/addTravel.html?travelId="+travel;
	
	
}







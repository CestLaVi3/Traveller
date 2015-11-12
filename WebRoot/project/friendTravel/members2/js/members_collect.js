$(document).ready(descCollectPost);
function descCollectPost() {
	var a = {"userId":window.parent.inf.userId};
	$.post("../../../travelDescController/findDescByCollect.do", {"paramter": JSON.stringify(a)}, descCollect, "json");
	};
function descCollect(fcc) {
	var FCcons = document.getElementById("collect_con");
		FCcons.innerHTML=null;
	for (var i = 0; i < fcc.value.length; i++) {
		var FCcon = document.createElement("li");
		FCcon.className = "travel clearfloat mt20";
		var travelImgDiv = document.createElement("div");
			travelImgDiv.className = "fl";
		var travelImgA = document.createElement("a");
			travelImgA.href = "";
		var travelImg = document.createElement("img");
			travelImg.style.width = "510px";
			travelImg.style.height = "300px";
			travelImg.src = JSON.parse(fcc.value[i].images).images[0];
			travelImgA.appendChild(travelImg);
			travelImgDiv.appendChild(travelImgA);
		FCcon.appendChild(travelImgDiv);
		var travelText = document.createElement("div");
			travelText.className = "travel_con fl ml20";
			
		var travelTextHead = document.createElement("div");
			travelTextHead.className = "travel_date clearfloat";
			var Date = document.createElement("div");
			Date.className = "fl";
		var dateCon = document.createElement("span");
			dateCon.className = "fb f14";
			dateCon.innerHTML = fcc.value[i].petName;
			Date.appendChild(dateCon);
		var dateText = document.createElement("span");
			dateText.className = "ml20 f12";
			dateText.innerHTML = "发表于：" + "&nbsp;" + fcc.value[i].createTime;
			Date.appendChild(dateText);
		var h = document.createElement("h3");
		var travelTit = document.createElement("a");
			travelTit.className = "travel_title";
			travelTit.href = "";
			travelTit.innerHTML = fcc.value[i].descTitle;
			h.appendChild(travelTit);
			Date.appendChild(h);
			travelTextHead.appendChild(Date);
			
		var friendHeadImg = document.createElement("img");
			friendHeadImg.className = "fr";
			friendHeadImg.src = fcc.value[0].imageUrl;
			travelTextHead.appendChild(friendHeadImg);
			travelText.appendChild(travelTextHead);

		var travelTextCon = document.createElement("div");
			travelTextCon.className = "travel_word f14 mt20";
			travelTextCon.innerHTML = fcc.value[i].descContent;
			travelText.appendChild(travelTextCon);
			
		var travelTextBut = document.createElement("div");
			travelTextBut.className = "f14 mt20";
		var n = 4;
		var num = [fcc.value[i].investNum, fcc.value[i].commentNum, fcc.value[i].collectNum, fcc.value[i].praiseNum];
		var eventName = [investE, commentE, collectE, deleteE];
		for (var z = 0; z < n; z++) {
			var span = document.createElement("span");
			var ii = document.createElement("i");
			ii.className = "travel_i" + (z + 1) + " ml30 fl";
			ii.setAttribute("travelid", fcc.value[i].travelDescId);
			ii.onclick = eventName[z];
			var spanC = document.createElement("span");
			spanC.className = "fl ml10";
			spanC.innerHTML = num[z];
			span.appendChild(ii);
			span.appendChild(spanC);
			travelTextBut.appendChild(span);
		}
			travelText.appendChild(travelTextBut);
		FCcon.appendChild(travelText);
		FCcons.appendChild(FCcon);
	}
}
function investE(){
	var a=$(event.target).attr("travelid");
	var b=$("#attention_friends").next();
	$.post("../../../travelDescController/investTravelDesc",{"paramter":JSON.stringify(a)},investSet,"json");
	$(event.target).next().text(parseInt($(event.target).next().text())+1);
}
function investSet(ret){
	alert(ret.value);
}
function commentE(){
}
function collectE(){
	var a={"travelDescId":$(event.target).attr("travelid")};
	$.post("../../../travelDescController/collectTravelDesc.do",{"paramter":JSON.stringify(a)},collectSet,"json");
};
function collectSet(ret){
	if(ret.state="1"){
	$(event.target).next().text(parseInt($(event.target).next().text())+1);
	}
	else{
		alert(ret.value);
	}
};
function deleteE(){
	
};
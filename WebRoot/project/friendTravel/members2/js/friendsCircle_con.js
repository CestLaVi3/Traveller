

/*初始化*/
$(document).ready(friendsC);
function friendsC(){

	friendsCircleConPost();
	friendsCircleStagtegyConPost();
	
}
/*游记动态*/
function friendsCircleConPost(){
	var userid=window.parent.Inf.userId;
	$.post("../../../travelDescController/findDescByUserCollect.do",{"paramter":JSON.stringify({"userId":userid})},friendsCircleCon,"json");
	
}

function friendsCircleCon(fcc){
	var FCcons=document.getElementById("FC_con");
	for(var i=0;i<fcc.value.length;i++){
		var FCcon=document.createElement("li");
			FCcon.className="travel clearfloat mt20";
			
			var travelImgDiv=document.createElement("div");
				travelImgDiv.className="fl";
				var travelImgA=document.createElement("a");
					travelImgA.href="";
					var travelImg=document.createElement("img");
						travelImg.style.width="510px";
						travelImg.style.height="300px";
						travelImg.src=JSON.parse(fcc.value[i].images).images[0];
					travelImgA.appendChild(travelImg);
				travelImgDiv.appendChild(travelImgA);
			FCcon.appendChild(travelImgDiv);
			var travelText=document.createElement("div");
				travelText.className="travel_con fl ml20";
				
				var	travelTextHead=document.createElement("div");
					travelTextHead.className="travel_date clearfloat";
					
					var TitAndDate=document.createElement("div");
						TitAndDate.className="fl";
						
						var dateText=document.createElement("span");
							dateText.className="f12";
							dateText.innerHTML="发表于："+"&nbsp;"+fcc.value[i].createTime;
						TitAndDate.appendChild(dateText);
						
						var dateCon=document.createElement("span");
							dateCon.className="ml20 fb f14";
							dateCon.innerHTML=fcc.value[i].petName;
						TitAndDate.appendChild(dateCon);
						
						var h=document.createElement("h3");
							var travelTit=document.createElement("a");
								travelTit.className="travel_title";
								travelTit.href="";
								travelTit.innerHTML=fcc.value[i].descTitle;
							h.appendChild(travelTit);
						TitAndDate.appendChild(h);
					travelTextHead.appendChild(TitAndDate);
					
					var friendHeadImg=document.createElement("img");
						friendHeadImg.className="fr";
						friendHeadImg.src=fcc.value[0].imageUrl;
					travelTextHead.appendChild(friendHeadImg);
				travelText.appendChild(travelTextHead);
				
				var travelTextCon=document.createElement("div");
					travelTextCon.className="travel_word f14 mt20";
					travelTextCon.innerHTML=fcc.value[i].descContent;
				travelText.appendChild(travelTextCon);
				
				var travelTextBut=document.createElement("div");
					travelTextBut.className="f14 mt20";
					var n=4;
					var num=[fcc.value[i].investNum,fcc.value[i].commentNum,fcc.value[i].collectNum,fcc.value[i].praiseNum];
					var eventName=[investE,commentE,collectE,praiseE]
					for(var z=0;z<n;z++){
						var span=document.createElement("span");
							var ii=document.createElement("i");
							ii.className="travel_i"+(z+1)+" ml30 fl";
							ii.setAttribute("travelid",fcc.value[i].travelDescId);
							ii.onclick=eventName[z];
							var spanC=document.createElement("span");
							spanC.className="fl ml10";
							spanC.innerHTML=num[z];
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
	
	var a={
			"travelDescId":$(event.target).attr("travelid")
			};
	var b=$("#attention_friends").next();
	$.post("../../../travelDescController/collectTravelDesc.do",{"paramter":JSON.stringify(a)},collectSet,"json");
	
}
function collectSet(ret){

	if(ret.state="1"){
	$(event.target).next().text(parseInt($(event.target).next().text())+1);
	}
	else{
		alert(ret.value);
	}
};
function praiseE(){
	var a={
			"travelDescId":$(event.target).attr("travelid")
			};
	var b=$("#attention_friends").next();
	$.post("../../../travelDescController/praiseTravelDesc.do",{"paramter":JSON.stringify(a)},praiseSet,"json");
}
function praiseSet(ret){
	
	if(ret.state="1"){
	$(event.target).next().text(parseInt($(event.target).next().text())+1);
	}
	else{
		alert(ret.value);
	}
};


/*攻略动态*/
function friendsCircleStagtegyConPost(){

	var userid=window.parent.Inf.userId;
	$.post("../../../stagtegyController/findByUserCollect.do",{"paramter":JSON.stringify({"userId":userid})},friendsCircleStagtegyCon,"json");
	
}
function friendsCircleStagtegyCon(fcs){
	if(fcs.state=="0"){
		alert(fcs.value);
	}
	var FCcons=document.getElementById("FCS_con");
	document.getElementById("trend_1").style.display="none";
	for(var i=0;i<fcs.value.length;i++){
		var FCcon=document.createElement("li");
			FCcon.className="travel clearfloat mt20";
			var travelImgDiv=document.createElement("div");
				travelImgDiv.className="fl";
				var travelImgA=document.createElement("a");
					travelImgA.href="";
					var travelImg=document.createElement("img");
						travelImg.style.width="510px";
						travelImg.style.height="300px"
						travelImg.src=JSON.parse(fcs.value[i].images).images[0];
					travelImgA.appendChild(travelImg);
				travelImgDiv.appendChild(travelImgA);
			FCcon.appendChild(travelImgDiv);
			var travelText=document.createElement("div");
				travelText.className="travel_con fl ml20";
				
				var	travelTextHead=document.createElement("div");
					travelTextHead.className="travel_date clearfloat";
					
					var TitAndDate=document.createElement("div");
						TitAndDate.className="fl";
						
						var dateText=document.createElement("span");
							dateText.className="f12";
							dateText.innerHTML="发表于："+"&nbsp;"+fcs.value[i].createTime;
						TitAndDate.appendChild(dateText);
						
						var dateCon=document.createElement("span");
							dateCon.className="f12";
							dateCon.innerHTML=fcs.value[i].petName;
						TitAndDate.appendChild(dateCon);
						
						var h=document.createElement("h3");
							var travelTit=document.createElement("a");
								travelTit.className="travel_title";
								travelTit.href="";
								travelTit.innerHTML=fcs.value[i].stagtegyTitle;
							h.appendChild(travelTit);
						TitAndDate.appendChild(h);
					travelTextHead.appendChild(TitAndDate);
					
					var friendHeadImg=document.createElement("img");
						friendHeadImg.className="fr";
						friendHeadImg.src=fcs.value[i].imageUrl;
					travelTextHead.appendChild(friendHeadImg);
				travelText.appendChild(travelTextHead);
				
				var travelTextCon=document.createElement("div");
					travelTextCon.className="travel_word f14 mt20";
					travelTextCon.innerHTML=fcs.value[i].stagtegyContent;
				travelText.appendChild(travelTextCon);
				
				var travelTextBut=document.createElement("div");
					travelTextBut.className="f14 mt20";
					var n=4;
					var num=[fcs.value[i].investNum,fcs.value[i].commentNum,fcs.value[i].collectNum,fcs.value[i].praiseNum];
					
					for(var z=0;z<n;z++){
						var span=document.createElement("span");
							var ii=document.createElement("i");
							ii.className="travel_i"+(z+1)+" ml30 fl";
						
							var spanC=document.createElement("span");
							spanC.className="fl ml10";
							spanC.innerHTML=num[z];
							span.appendChild(ii);
							span.appendChild(spanC);
					travelTextBut.appendChild(span);
					}
				travelText.appendChild(travelTextBut);
			FCcon.appendChild(travelText);
		FCcons.appendChild(FCcon);
	}	
}

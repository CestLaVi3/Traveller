// JavaScript Document// JavaScript Document
var arid;
var ardata;
window.onload = function(){
	debugger;
	arid = GetQueryString("arid"); 	
	findpost();
	}
function findpost(){
	document.getElementById("allmap").innerHTML = "";
	 $.post("../../../scienceSpotController/findById.do",{"paramter":JSON.stringify({"spotId":arid})},findar,"json");
	  $.post("../../../scienceSpotController/findhumanbyspotId.do",{"paramter":JSON.stringify({"spotId":arid})},findhuman,"json");	
	 $.post("../../../scienceSpotController/findmenubyspotId.do",{"paramter":JSON.stringify({"spotId":arid})},findmenu,"json");	
	 $.post("../../../scienceSpotController/findSpecialtybyspotId.do",{"paramter":JSON.stringify({"spotId":arid})},findSpecialty,"json");	
	 $.post("../../../spotController/findSpotById.do",{"paramter":JSON.stringify({"spotId":arid})},findallar,"json");			 
	}
function findar(data){
	debugger;
	ardata = data;
	if(ardata.value.locationX.length!=0){
	 debugger;
	var map = new BMap.Map("allmap");    // 创建Map实例
    map.disableScrollWheelZoom();
	map.disableDoubleClickZoom();
	map.disableKeyboard();
	map.disableContinuousZoom();		
	map.centerAndZoom(new BMap.Point(ardata.value.locationX,ardata.value.locationY), 7);  // 初始化地图,设置中心点坐标和地图级别设置的
	var point = new BMap.Point(ardata.value.locationX,ardata.value.locationY);
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);	
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); 	
 }
    document.getElementById("spotName").innerHTML= "关于"+data.value.spotName;
	var strhot = "";
	for(var i=0;i<data.value.hotLevel;i++){
		strhot+="★";
		}
	document.getElementById("hotLevel").innerHTML = "热度丨"+strhot;
	document.getElementById("locationDesc").innerHTML=data.value.locationDesc;
	document.getElementById("custom").innerHTML=data.value.custom;
	document.getElementById("spotDesc").innerHTML=data.value.spotDesc;
	if(data.value.imageUrl.length!=0){
	var obj = JSON.parse(data.value.imageUrl); 
	var url = obj.images;
	for(var i=0;i<url.length;i++){
		var q = i+1;
		document.getElementById("zimg"+q).src = "../../../"+url[i];
		}
	}
		if(data.value.travelRoute.length!=0){
	var obj1 = JSON.parse(data.value.travelRoute);
	for(sta in obj1){
		if(obj1[sta]!=null){
			var travelRoute = document.getElementById("sub8");
			var h4 = document.createElement("h4");
			h4.innerHTML = sta;
			var p =document.createElement("p");
			p.innerHTML = obj1[sta];
			p.className = "lylx";
			travelRoute.appendChild(h4);	
			travelRoute.appendChild(p);	
			}
	}
			debugger;
		} 
}	
function findSpecialty(data){
	debugger;
	var ul2 = document.getElementById("specialty");
	for(var i=0;i<data.value.length;i++){
		var obj = JSON.parse(data.value[i].images); 
	     var url = obj.images;
		var li = document.createElement("li");
		var img = document.createElement("img");
		img.src = "../../../"+url[0];
		var h6 = document.createElement("h6");
		h6.innerHTML = data.value[i].specialtyName;
		li.appendChild(img);
		li.appendChild(h6);
		li.specialtyId=data.value[i].specialtyId;
		li.specialtyName=data.value[i].specialtyName;
		li.specialtyDesc=data.value[i].specialtyDesc;
		li.images = JSON.parse(data.value[i].images).images;
		img.specialtyId=data.value[i].specialtyId;
		img.specialtyName=data.value[i].specialtyName;
		img.specialtyDesc=data.value[i].specialtyDesc;
		img.images = JSON.parse(data.value[i].images).images;
		h6.specialtyId=data.value[i].specialtyId;
		h6.specialtyName=data.value[i].specialtyName;
		h6.specialtyDesc=data.value[i].specialtyDesc;
		h6.images = JSON.parse(data.value[i].images).images;
		li.onclick = function(){
			var obj = event.srcElement;
			xianshi1(obj);
			}
		ul2.appendChild(li);			
		}
	}	
function findmenu(data){
	debugger;

	var ul2 = document.getElementById("menu");
	for(var i=0;i<data.value.length;i++){
		var obj = JSON.parse(data.value[i].menuImages); 
	     var url = obj.menuImages;
		var li = document.createElement("li");
		var img = document.createElement("img");
		img.src = "../../../"+url[0];
		var h6 = document.createElement("h6");
		h6.innerHTML = data.value[i].menuName;
		li.appendChild(img);
		li.appendChild(h6);
		li.menuId=data.value[i].menuId;
		li.menuName=data.value[i].menuName;
		li.menuDesc=data.value[i].menuDesc;
		li.images = JSON.parse(data.value[i].menuImages).menuImages;
		img.menuId=data.value[i].menuId;
		img.menuName=data.value[i].menuName;
		img.menuDesc=data.value[i].menuDesc;
		img.images = JSON.parse(data.value[i].menuImages).menuImages;
		h6.menuId=data.value[i].menuId;
		h6.menuName=data.value[i].menuName;
		h6.menuDesc=data.value[i].menuDesc;
		h6.images = JSON.parse(data.value[i].menuImages).menuImages;
		
		li.onclick = function(){
			var obj = event.srcElement;
			xianshi2(obj);
			}

		ul2.appendChild(li);	
		}

	}	
function findhuman(data){
	debugger;
	var ul2 = document.getElementById("human");
	for(var i=0;i<data.value.length;i++){
		var obj = JSON.parse(data.value[i].images); 
	     var url = obj.images;
		var li = document.createElement("li");
		var img = document.createElement("img");
		img.src = "../../../"+url[0];
		var h6 = document.createElement("h6");
		h6.innerHTML = data.value[i].humanName;
		var h61 = document.createElement("h6");
		h61.innerHTML = data.value[i].birthday+"~"+data.value[i].dieDay;
		li.appendChild(img);
		li.appendChild(h6);
		li.appendChild(h61);
		li.humanid=data.value[i].humanId;
		li.humanName=data.value[i].humanName;
		li.birthday=data.value[i].birthday;
		li.dieDay=data.value[i].dieDay;
		li.humanDesc=data.value[i].humanDesc;
		li.images = JSON.parse(data.value[i].images).images;
		img.humanid=data.value[i].humanId;
		img.humanName=data.value[i].humanName;
		img.birthday=data.value[i].birthday;
		img.dieDay=data.value[i].dieDay;
		img.humanDesc=data.value[i].humanDesc;
		img.images = JSON.parse(data.value[i].images).images;
		h6.humanid=data.value[i].humanId;
		h6.humanName=data.value[i].humanName;
		h6.birthday=data.value[i].birthday;
		h6.dieDay=data.value[i].dieDay;
		h6.humanDesc=data.value[i].humanDesc;
		h6.images = JSON.parse(data.value[i].images).images;
		h61.humanid=data.value[i].humanId;
		h61.humanName=data.value[i].humanName;
		h61.birthday=data.value[i].birthday;
		h61.dieDay=data.value[i].dieDay;
		h61.humanDesc=data.value[i].humanDesc;
		h61.images = JSON.parse(data.value[i].images).images;		
		li.onclick = function(){
			var obj = event.srcElement;
			xianshi(obj);
			}
	  
		ul2.appendChild(li);	
		}
	}	
function findallar(data){
	debugger;
	//  <a href="Area.html" style="margin:20px 0 0 15px;float:left;color:#000000" class="myButton" >东城区</a>
	var linkdiv = document.getElementById("sub9");
	for(var i=0;i<data.spot.length;i++){
		var a = document.createElement("a");
		a.href="Spot.html?spid="+data.spot[i].sysSpotId;
		a.style = "margin:20px 0 0 15px;float:left;color:#000000";
		a.className ="myButton";
		a.innerHTML = data.spot[i].spotName;
		linkdiv.appendChild(a);
		}	
	}	
		

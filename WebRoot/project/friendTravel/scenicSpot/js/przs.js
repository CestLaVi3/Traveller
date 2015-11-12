// JavaScript Document
// JavaScript Document
var prid;
var prdata;
window.onload = function(){
	 
	prid = GetQueryString("prid");  
	findpost(); 
	}
function findpost(){
	document.getElementById("allmap").innerHTML = "";
	 $.post("../../../provinceController/findbyId.do",{"paramter":JSON.stringify({"provinceId":prid})},findpr,"json");	
	 $.post("../../../scienceSpotController/findbyProvinceId.do",{"paramter":JSON.stringify({"provinceId":prid})},findallar,"json");			
	}	
function findpr(data){
	 
	prdata = data;
	 if(prdata.value.locationX.length!=0){
	  
	var map = new BMap.Map("allmap");    // 创建Map实例
    map.disableScrollWheelZoom();
	map.disableDoubleClickZoom();
	map.disableKeyboard();
	map.disableContinuousZoom();		
	map.centerAndZoom(new BMap.Point(prdata.value.locationX,prdata.value.locationY), 5);  // 初始化地图,设置中心点坐标和地图级别设置的
	var point = new BMap.Point(prdata.value.locationX,prdata.value.locationY);
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);	
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); 	
 }
    document.getElementById("provinceName").innerHTML= "关于"+data.value.provinceName;
	var strhot = "";
	for(var i=0;i<data.value.hotLevel;i++){
		strhot+="★";
		}
	document.getElementById("hotLevel").innerHTML = "热度丨"+strhot;
	document.getElementById("locationDesc").innerHTML=data.value.locationDesc;
	document.getElementById("travelDesc").innerHTML=data.value.travelDesc;
	document.getElementById("provinceDesc").innerHTML=data.value.provinceDesc;
	document.getElementById("historyDesc").innerHTML=data.value.historyDesc;
	document.getElementById("cultureDesc").innerHTML=data.value.cultureDesc;
	var obj = JSON.parse(data.value.imageUrl); 
	var url = obj.images;
	for(var i=0;i<url.length;i++){
		var q = i+1;
		document.getElementById("zimg"+q).src = "../../../"+url[i];
		}
}
function findallar(data){
	 
	//  <a href="Area.html" style="margin:20px 0 0 15px;float:left;color:#000000" class="myButton" >东城区</a>
	var linkdiv = document.getElementById("sub7");
	for(var i=0;i<data.value.length;i++){
		var a = document.createElement("a");
		a.href="Area.html?arid="+data.value[i].spotId;
		a.style = "margin:20px 0 0 15px;float:left;color:#000000";
		a.className ="myButton";
		a.innerHTML = data.value[i].spotName;
		linkdiv.appendChild(a);
		}	
	}	
function findallar(data){
	 
	//  <a href="Area.html" style="margin:20px 0 0 15px;float:left;color:#000000" class="myButton" >东城区</a>
	var linkdiv = document.getElementById("sub7");
	for(var i=0;i<data.value.length;i++){
		var a = document.createElement("a");
		a.href="Area.html?arid="+data.value[i].spotId;
		a.style = "margin:20px 0 0 15px;float:left;color:#000000";
		a.className ="myButton";
		a.innerHTML = data.value[i].spotName;
		linkdiv.appendChild(a);
		}	
	}		

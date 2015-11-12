// JavaScript Document
// JavaScript Document
// JavaScript Document
var spid;
var spdata;
var spottype;

window.onload = function(){
	debugger;
	spid = GetQueryString("spid"); 
	 $.post("../../../spotController/findAllspotType.do",{},findtype,"json");
	findpost();
	}	
function findpost(){
	document.getElementById("allmap").innerHTML = "";
	 $.post("../../../spotController/findbyId.do",{"paramter":JSON.stringify({"sysSpotId":spid})},findsp,"json");
	}
function findtype(data){
    spottype = data.value;
	}	
function findsp(data){
	debugger;
	spdata = data;
	 if(spdata.value.locationX.length!=0){
	 debugger;
	var map = new BMap.Map("allmap");    // 创建Map实例
    map.disableScrollWheelZoom();
	map.disableDoubleClickZoom();
	map.disableKeyboard();
	map.disableContinuousZoom();		
	map.centerAndZoom(new BMap.Point(spdata.value.locationX,spdata.value.locationY), 7);  // 初始化地图,设置中心点坐标和地图级别设置的
	var point = new BMap.Point(spdata.value.locationX,spdata.value.locationY);
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);	
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); 	
	document.getElementById("zsspotname").innerHTML = "关于"+data.value.spotName;
	document.getElementById("zsmenpiao").innerHTML = data.value.ticketDesc;
	document.getElementById("zsopentime").innerHTML = data.value.openTime;
	document.getElementById("zsyouhui").innerHTML = data.value.discountDesc;
	document.getElementById("zsaddres").innerHTML = data.value.locationDesc;
	var strhot = "";
	for(var i=0;i<data.value.hotLevel;i++){
		strhot+="★";
		}
	document.getElementById("zshotlevle").innerHTML = "热度丨"+strhot;
	if(data.value.fitSeason==1){
	    document.getElementById("zsseason").innerHTML = "春";
	}
	if(data.value.fitSeason==2){
		document.getElementById("zsseason").innerHTML = "夏";
		}
	if(data.value.fitSeason==3){
		document.getElementById("zsseason").innerHTML = "秋";
		}
	if(data.value.fitSeason==4){
		document.getElementById("zsseason").innerHTML = "东";
		}
	for(var i=0;i<spottype.length;i++){
		if(spottype[i].sportTypeId==data.value.spotTypeId){
	document.getElementById("zsspottype").innerHTML = spottype[i].sportTypeName;
		}
	}
	document.getElementById("zsspotdesc").innerHTML = data.value.spotDesc;
		if(data.value.images.length!=0){
	var obj = JSON.parse(data.value.images); 
	var url = obj.images;
	for(var i=0;i<url.length;i++){
		var q = i+1;
		document.getElementById("zimg"+q).src = "../../../"+url[i];
		}
	}	
 }
   
}	

// JavaScript Document
// JavaScript Document
var spid;
var spdata;
var arid;
var type;
window.onload = function(){
	debugger;
	spid = GetQueryString("lxname");  
	type = GetQueryString("type");  
	arid = GetQueryString("spotId");
	document.getElementById("form1").value = spid;
	 $.post("../../../spotController/findAllspotType.do",{},findtype,"json");
	if(type==1){
		showedit1(0);
		var obj=self.frames[0];
        obj.drawmap(arid);		
		}else{
	findpost();
	var obj=self.frames[0];
    obj.drawmap(arid);
		}
	}
function findtype(data){
	var selec = document.getElementById("spottype");
	for(var i =0;i<data.value.length;i++){
		var option = document.createElement("option");
		option.value = data.value[i].sportTypeId;
		option.innerHTML = data.value[i].sportTypeName;
		selec.appendChild(option);
		}	
	}	
function findpost(){
	document.getElementById("allmap").innerHTML = "";
	 $.post("../../../spotController/findbyId.do",{"paramter":JSON.stringify({"sysSpotId":spid})},findsp,"json");
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
	document.getElementById("spotname").value = data.value.spotName;
	document.getElementById("zsspotname").innerHTML = "关于"+data.value.spotName;
	document.getElementById("zX").value = data.value.locationX;
	document.getElementById("zY").value = data.value.locationY;
    document.getElementById("menp").value = data.value.ticketDesc;
	document.getElementById("zsmenpiao").innerHTML = data.value.ticketDesc;
	document.getElementById("opentime").value = data.value.openTime;
	document.getElementById("zsopentime").innerHTML = data.value.openTime;
	document.getElementById("youhuizhengce").value = data.value.discountDesc;
	document.getElementById("zsyouhui").innerHTML = data.value.discountDesc;
	document.getElementById("diliweizhi").value = data.value.locationDesc;
	document.getElementById("zsaddres").innerHTML = data.value.locationDesc;
	document.getElementById("spothot").value = data.value.hotLevel;
	var strhot = "";
	for(var i=0;i<data.value.hotLevel;i++){
		strhot+="★";
		}
	document.getElementById("zshotlevle").innerHTML = "热度丨"+strhot;	
	document.getElementById("season").value = data.value.fitSeason;
	document.getElementById("zsseason").innerHTML = data.value.fitSeason;
	document.getElementById("spottype").value = data.value.spotTypeId;
	document.getElementById("zsspottype").innerHTML = data.value.spotTypeId;
	document.getElementById("zsspotdesc").innerHTML = data.value.spotDesc;
	editor1.setContent(data.value.spotDesc);
		if(data.value.images.length!=0){
	var obj = JSON.parse(data.value.images); 
	var url = obj.images;
	for(var i=0;i<url.length;i++){
		var q = i+1;
		document.getElementById("zimg"+q).src = "../../../"+url[i];
		document.getElementById("bjimg"+q).src = "../../../"+url[i];
		}
	}	
 }
   
}	
function pointlnglat(x,y){
	document.getElementById("inlocationX").value = x;
	document.getElementById("inlocationY").value = y;
	}
function save1(){
	debugger;	
	if(type!=1){
	   var spotname = document.getElementById("spotname").value;
	   var zx = 	document.getElementById("zX").value;
	   var zy =	document.getElementById("zY").value;
	   var menp = document.getElementById("menp").value;
	   var opentime = document.getElementById("opentime").value;
	   var youhuizhengce = document.getElementById("youhuizhengce").value;
		var diliweizhi =	document.getElementById("diliweizhi").value;
		var hotlevel = document.getElementById("spothot").value;
		var season = document.getElementById("season").value;
		var spottype = document.getElementById("spottype").value;
	$.post("../../../spotController/updateBasic.do",{"paramter":JSON.stringify({"discountDesc":youhuizhengce,"fitSeason":season,"hotLevel":hotlevel,"locationDesc":diliweizhi,"locationX":zx,"locationY":zy,"openTime":opentime,"spotName":spotname,"spotTypeId":spottype,"ticketDesc":menp,"sysSpotId":spid})},save11,"json");	
	}
	else if(type==1){
	   var spotname = document.getElementById("spotname").value;
	   var zx = 	document.getElementById("zX").value;
	   var zy =	document.getElementById("zY").value;
	   var menp = document.getElementById("menp").value;
	   var opentime = document.getElementById("opentime").value;
	   var youhuizhengce = document.getElementById("youhuizhengce").value;
		var diliweizhi =	document.getElementById("diliweizhi").value;
		var hotlevel = document.getElementById("spothot").value;
		var season = document.getElementById("season").value;
		var spottype = document.getElementById("spottype").value;
	$.post("../../../spotController/saveBasic.do",{"paramter":JSON.stringify({"discountDesc":youhuizhengce,"fitSeason":season,"hotLevel":hotlevel,"locationDesc":diliweizhi,"locationX":zx,"locationY":zy,"openTime":opentime,"spotName":spotname,"spotTypeId":spottype,"ticketDesc":menp,"spotId":arid})},save12,"json");	
		}
	}	
function save11(data){
	debugger;
	alert("保存成功");
	findpost(); 
	}
function save12(data){
	debugger;
	alert("保存成功1");
	window.parent.location.reload();
	}		
function save2(){
    var txt = editor1.getContentTxt();
	alert(txt);
	$.post("../../../spotController/updateSpotDesc.do",{"paramter":JSON.stringify({"sysSpotId":spid,"spotDesc":txt})},save21,"json");		
	}	
function save21(data){
	alert("保存成功");
	findpost(); 
	}

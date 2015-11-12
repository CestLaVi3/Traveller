// JavaScript Document
var prid;
var prdata;
window.onload = function(){
	debugger;
	prid = GetQueryString("lxname");  
	document.getElementById("form1").value = prid;
	findpost(); 
	}
function findpost(){
	document.getElementById("allmap").innerHTML = "";
	 $.post("../../../provinceController/findbyId.do",{"paramter":JSON.stringify({"provinceId":prid})},findpr,"json");		
	}	
function findpr(data){
	debugger;
	prdata = data;
	 if(prdata.value.locationX.length!=0){
	 debugger;

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
	document.getElementById("inprovinceName").value= data.value.provinceName;
	document.getElementById("inhotLevel").value =  data.value.hotLevel;
	document.getElementById("inlocationDesc").value=data.value.locationDesc;	
	document.getElementById("inlocationX").value = data.value.locationX;
	document.getElementById("inlocationY").value = data.value.locationY;	
	editor1.setContent(data.value.travelDesc);
	editor2.setContent(data.value.provinceDesc);
	editor3.setContent(data.value.historyDesc);
	editor4.setContent(data.value.cultureDesc);
	var obj = JSON.parse(data.value.imageUrl); 
	var url = obj.images;
	for(var i=0;i<url.length;i++){
		var q = i+1;
		document.getElementById("zimg"+q).src = "../../../"+url[i];
		document.getElementById("bjimg"+q).src = "../../../"+url[i];
		}
}	
function pointlnglat(x,y){
	document.getElementById("inlocationX").value = x;
	document.getElementById("inlocationY").value = y;
	}
function save1(){
	var provinceName = document.getElementById("inprovinceName").value;
	var hotLevel = document.getElementById("inhotLevel").value;
	var locationDesc = document.getElementById("inlocationDesc").value;	
	var locationX = document.getElementById("inlocationX").value;
	var locationY = document.getElementById("inlocationY").value;
	$.post("../../../provinceController/updateBasic.do",{"paramter":JSON.stringify({"provinceId":prid,"provinceName":provinceName,"hotLevel":hotLevel,"locationDesc":locationDesc,"locationX":locationX,"locationY":locationY})},save11,"json");			
	}	
function save11(data){
	alert("保存成功");
	findpost(); 
	}	
function save2(){
    var txt = editor1.getContentTxt();
	$.post("../../../provinceController/updatetravelDesc.do",{"paramter":JSON.stringify({"provinceId":prid,"travelDesc":txt})},save21,"json");		
	}	
function save21(data){
	alert("保存成功");
	findpost(); 
	}		
function save3(){
    var txt = editor2.getContentTxt();
	$.post("../../../provinceController/updateprovinceDesc.do",{"paramter":JSON.stringify({"provinceId":prid,"provinceDesc":txt})},save11,"json");		
	}	
function save4(){
    var txt = editor3.getContentTxt();
	$.post("../../../provinceController/updatehistoryDesc.do",{"paramter":JSON.stringify({"provinceId":prid,"historyDesc":txt})},save11,"json");		
	}	
function save5(){
    var txt = editor4.getContentTxt();
	$.post("../../../provinceController/updatecultureDesc.do",{"paramter":JSON.stringify({"provinceId":prid,"cultureDesc":txt})},save11,"json");		
	}		
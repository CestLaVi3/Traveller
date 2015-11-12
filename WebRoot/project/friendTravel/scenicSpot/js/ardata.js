// JavaScript Document
var arid;
var ardata;
var prid;
var type;
window.onload = function(){
	debugger;
	arid = GetQueryString("lxname");  
	type = GetQueryString("type");  
	prid = GetQueryString("provinceId");
	document.getElementById("form1").value = arid;
	document.getElementById("savehumanspotid").value = arid;
	document.getElementById("uphumanspotid").value = arid;
	document.getElementById("savemenuspotid").value = arid;
	document.getElementById("upmenuspotid").value = arid;
	document.getElementById("savespecialtyspotid").value = arid;
	document.getElementById("upspecialtyspotid").value = arid;
	if(type==1){
		showedit1(0);
		var obj=self.frames[0];
        obj.drawmap(prid);		
		}else{
	findpost();
	var obj=self.frames[0];
    obj.drawmap(prid);
		}
	}
function findpost(){
	document.getElementById("allmap").innerHTML = "";
	 $.post("../../../scienceSpotController/findById.do",{"paramter":JSON.stringify({"spotId":arid})},findar,"json");
	 $.post("../../../scienceSpotController/findhumanbyspotId.do",{"paramter":JSON.stringify({"spotId":arid})},findhuman,"json");	
	 $.post("../../../scienceSpotController/findmenubyspotId.do",{"paramter":JSON.stringify({"spotId":arid})},findmenu,"json");	
	 $.post("../../../scienceSpotController/findSpecialtybyspotId.do",{"paramter":JSON.stringify({"spotId":arid})},findSpecialty,"json");				
	}
function findSpecialty(data){
	debugger;
	var ul2 = document.getElementById("inspecialty");
	var ul1 = document.getElementById("specialty");
	ul1.innerHTML = "";
	ul2.innerHTML = '<li id="inspecialtyflag"><a href="#" class="myButton myButton1" style="margin:0" onClick="savespecialty();">新建</a></li> ';
	var flag = document.getElementById("inspecialtyflag");
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
		ul2.insertBefore(li,flag);	
		li.onclick = function(){
			var obj = event.srcElement;
			updatespecialty(obj);
			}
	    h6.onclick = function(){
			var obj = event.srcElement.parentNode;
			updatespecialty(obj);
			}
        img.onclick = function(){
			var obj = event.srcElement.parentNode;
			updatespecialty(obj);
			}		
		}
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
		ul1.appendChild(li);	
		}
	}	
function findmenu(data){
	debugger;
	var ul2 = document.getElementById("inmenu");
	var ul1 = document.getElementById("menu");
	ul1.innerHTML = "";
	ul2.innerHTML = '<li id="inmenuflag"><a href="#" class="myButton myButton1" style="margin:0" onClick="savemenu();">新建</a></li> ';
	var flag = document.getElementById("inmenuflag");
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
		ul2.insertBefore(li,flag);	
		li.onclick = function(){
			var obj = event.srcElement;
			updatemenu(obj);
			}
		  h6.onclick = function(){
			var obj = event.srcElement.parentNode;
			updatemenu(obj);
			}
        img.onclick = function(){
			var obj = event.srcElement.parentNode;
			updatemenu(obj);
			}			
		}
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
		ul1.appendChild(li);	
		}
	}	
function findhuman(data){
	debugger;
	var ul2 = document.getElementById("inhuman");
	var ul1 = document.getElementById("human");
	ul1.innerHTML = "";
	ul2.innerHTML = '<li id="inhumanflag"><a href="#" class="myButton myButton1" style="margin:0" onClick="savehuman();">新建</a></li> ';
	var flag = document.getElementById("inhumanflag");
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
		ul2.insertBefore(li,flag);	
		li.onclick = function(){
			var obj = event.srcElement;
			updatehuman(obj);
			}
		h6.onclick = function(){
			var obj = event.srcElement.parentNode;
			updatehuman(obj);
			}
		h61.onclick = function(){
			var obj = event.srcElement.parentNode;
			updatehuman(obj);
			}
		img.onclick = function(){
			var obj = event.srcElement.parentNode;
			updatehuman(obj);
			}			
		}
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
		ul1.appendChild(li);	
		}
	}		
function findar(data){
	debugger;
	ardata = data;
	prid = data.value.provinceId;
	document.getElementById("form2").value = arid;
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
	document.getElementById("inspotName").value= data.value.spotName;
	document.getElementById("inhotLevel").value =  data.value.hotLevel;
	document.getElementById("inlocationDesc").value=data.value.locationDesc;	
	document.getElementById("inlocationX").value = data.value.locationX;
	document.getElementById("inlocationY").value = data.value.locationY;	
	editor1.setContent(data.value.custom);
	editor2.setContent(data.value.spotDesc);
	if(data.value.imageUrl.length!=0){
	var obj = JSON.parse(data.value.imageUrl); 
	var url = obj.images;
	for(var i=0;i<url.length;i++){
		var q = i+1;
		document.getElementById("zimg"+q).src = "../../../"+url[i];
		document.getElementById("bjimg"+q).src = "../../../"+url[i];
		}
	}
	
	document.getElementById("travelRoute").innerHTML = '<h2>旅游路线</h2><div id="flagnode"></div><a href="#" class="myButton myButton1"  onClick="showedit1(7)">编辑</a>';
	if(data.value.travelRoute.length!=0){
	var obj1 = JSON.parse(data.value.travelRoute);
	for(sta in obj1){
		if(obj1[sta]!=null){
			var travelRoute = document.getElementById("travelRoute");
			var flagnode =document.getElementById("flagnode");
			var h4 = document.createElement("h4");
			h4.innerHTML = sta;
			var p =document.createElement("p");
			p.innerHTML = obj1[sta];
			p.className = "lylx";
			travelRoute.insertBefore(h4,flagnode);	
			travelRoute.insertBefore(p,flagnode);	
			document.getElementById(sta).value = obj1[sta];		
			}
	}
			debugger;
		} 
}	
function pointlnglat(x,y){
	document.getElementById("inlocationX").value = x;
	document.getElementById("inlocationY").value = y;
	}
function save1(){
	debugger;	
	if(type!=1){
	var spotName = document.getElementById("inspotName").value;
	var hotLevel = document.getElementById("inhotLevel").value;
	var locationDesc = document.getElementById("inlocationDesc").value;	
	var locationX = document.getElementById("inlocationX").value;
	var locationY = document.getElementById("inlocationY").value;
	$.post("../../../scienceSpotController/updateBasic.do",{"paramter":JSON.stringify({"provinceId":prid,"spotId":arid,"spotName":spotName,"hotLevel":hotLevel,"locationDesc":locationDesc,"locationX":locationX,"locationY":locationY})},save11,"json");	
	}
	else if(type==1){
	var spotName = document.getElementById("inspotName").value;
	var hotLevel = document.getElementById("inhotLevel").value;
	var locationDesc = document.getElementById("inlocationDesc").value;	
	var locationX = document.getElementById("inlocationX").value;
	var locationY = document.getElementById("inlocationY").value;
	$.post("../../../scienceSpotController/saveSpotBasic.do",{"paramter":JSON.stringify({"provinceId":prid,"SpotName":spotName,"HotLevel":hotLevel,"LocationDesc":locationDesc,"LocationX":locationX,"LocationY":locationY})},save12,"json");	
		}
	}	
function save11(data){
	debugger;
	alert("保存成功");
	findpost(); 
	}
function save12(data){
	debugger;
	window.parent.location.reload();
	}		
function save2(){
    var txt = editor1.getContentTxt();
	alert(txt);
	$.post("../../../scienceSpotController/updateCustombyspotId.do",{"paramter":JSON.stringify({"spotId":arid,"custom":txt})},save21,"json");		
	}	
function save21(data){
	alert("保存成功");
	findpost(); 
	}
function save3(){
    var txt = editor2.getContentTxt();
		alert(txt);
	$.post("../../../scienceSpotController/updateSpotDescbyId.do",{"paramter":JSON.stringify({"spotId":arid,"spotDesc":txt})},save31,"json");		
	}	
function save31(data){
	alert("保存成功");
	findpost(); 
	}
function save4(){
	str={
		"OneDay":document.getElementById("OneDay").value,
		"TwoDay":document.getElementById("TwoDay").value,
		"ThreeDay":document.getElementById("ThreeDay").value,
		"FourDay":document.getElementById("FourDay").value,
		"FiveDay":document.getElementById("FiveDay").value,
		"SixDay":document.getElementById("SixDay").value,
		"SevenDay":document.getElementById("SevenDay").value
	}
	$.post("../../../scienceSpotController/updateTravleRoutebyspotId.do",{"paramter":JSON.stringify({"spotId":arid,"travleRoute":str})},save41,"json");		
	}					
function save41(data){
	alert("保存成功");
	findpost(); 
	}	
function savehuman(){
	document.getElementById("edithuman1").style.display = "block";
	document.getElementById("edithuman2").style.display = "none";
	}	
function savemenu(){
	document.getElementById("edithuman3").style.display = "block";
	document.getElementById("edithuman4").style.display = "none";
	}
function savespecialty(){
	document.getElementById("edithuman5").style.display = "block";
	document.getElementById("edithuman6").style.display = "none";
	}			
function updatehuman(obj){
	debugger;
	document.getElementById("edithuman1").style.display = "none";
	document.getElementById("edithuman2").style.display = "block";
	document.getElementById("uphumanid").value = obj.humanid;
	document.getElementById("upbirthday").value = obj.birthday;
	document.getElementById("updieday").value = obj.dieDay;
	document.getElementById("uphumanname").value = obj.humanName;
	document.getElementById("uphumanDesc").value = obj.humanDesc;
	document.getElementById("himg1").src = "../../../"+obj.images[0];
	document.getElementById("himg2").src = "../../../"+obj.images[1];
	document.getElementById("himg3").src = "../../../"+obj.images[2];			
	}
function updatemenu(obj){
	debugger;
	document.getElementById("edithuman3").style.display = "none";
	document.getElementById("edithuman4").style.display = "block";
	document.getElementById("upmenuid").value = obj.menuId;
	document.getElementById("upmenuname").value = obj.menuName;
	document.getElementById("upmenuDesc").value = obj.menuDesc;
	document.getElementById("mimg1").src = "../../../"+obj.images[0];
	document.getElementById("mimg2").src = "../../../"+obj.images[1];
	document.getElementById("mimg3").src = "../../../"+obj.images[2];			
	}
function updatespecialty(obj){
	debugger;
	document.getElementById("edithuman5").style.display = "none";
	document.getElementById("edithuman6").style.display = "block";
	document.getElementById("upspecialtyid").value = obj.specialtyId;
	document.getElementById("upspecialtyname").value = obj.specialtyName;
	document.getElementById("upspecialtyDesc").value = obj.specialtyDesc;
	document.getElementById("simg1").src = "../../../"+obj.images[0];
	document.getElementById("simg2").src = "../../../"+obj.images[1];
	document.getElementById("simg3").src = "../../../"+obj.images[2];			
	}		
function drophuman(){
	debugger;
	var hid = document.getElementById("uphumanid").value;
	$.post("../../../scienceSpotController/drophumanbyhumanId.do",{"paramter":JSON.stringify({"humanId":hid})},drop1,"json");		
	}
function dropmenu(){
	debugger;
	var hid = document.getElementById("upmenuid").value;
	$.post("../../../scienceSpotController/deleteById.do",{"paramter":JSON.stringify({"menuId":hid})},drop1,"json");		
	}
function dropspecialty(){
	debugger;
	var hid = document.getElementById("upspecialtyid").value;
	$.post("../../../scienceSpotController/deleteSpecialtybyId.do",{"paramter":JSON.stringify({"specialtyId":hid})},drop1,"json");		
	}				
function drop1(data){
	debugger;
	alert("删除成功");
	window.location.reload();
	}
	
// JavaScript Document
var travel=null;

function init(){
	debugger;
	travelId =GetQueryString("travelId");
	if(travelId!=null){
		$.post("../../../travelDescController/findDescById.do",{"paramter":JSON.stringify({"travelId":travelId})},TravelCallBack,"json");
		$.post("../../../scienceSpotController/findSpotByCount.do",{"paramter":JSON.stringify({"count":"all"})},initSpot,"json");
	}else{
		$.post("../../../scienceSpotController/findSpotByCount.do",{"paramter":JSON.stringify({"count":"all"})},initSpot,"json");
	}
}

//解析超链接传递的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)
	 return  unescape(r[2]); 
	 return null;
}

function TravelCallBack(ret){
	debugger;
	travel=ret.value;
	var imageUrl=$.parseJSON(travel.images);
	for(var i = 0; i < imageUrl.images.length; i++){
		var img = document.getElementById("img"+(i+1));
			img.firstChild.src = "../../../"+imageUrl.images[i];
			img.style.display="block";
			img.firstChild.style.display="block";
	}
	var descTitle=document.getElementById("descTitle");
		descTitle.value=travel.descTitle; 
	var editor_a = UE.getEditor('editor').setContent(travel.descContent);
	var travelDescId=document.getElementById("travelDescId");
		travelDescId.value=travel.travelDescId;
	$("form").attr("action","../../../travelDescController/updateTravelDesc.do");
	$("#save").val("修改");
}

//景区填充
function initSpot(ret){
	debugger;
	if(ret.state=="0"){
		alert(ret.value);
	}
	var sel=document.getElementById("place");
	if(travel==null){
		for(var i = 0; i < ret.value.length; i ++ ){
			var op=document.createElement("option");
			if(i==0){
				op.selected="selected";
			}
				op.innerHTML=ret.value[i].spotName;
				op.value=ret.value[i].spotId;
			sel.appendChild(op);
		}
	}else{
		for(var i = 0; i < ret.value.length; i ++ ){
			var op=document.createElement("option");
				op.innerHTML=ret.value[i].spotName;
				op.value=ret.value[i].spotId;
				if(ret.value[i].spotId==travel.spotId){
					op.selected="selected";
				}
			sel.appendChild(op);
		}
	}
}



//图片预览 
function previewImage(file)  
{  
var MAXWIDTH  = 100;  
var MAXHEIGHT =  100;  
var div = event.srcElement.parentNode.lastChild;
div.style.display="block";
div.parentNode.firstChild.innerHTML="";  
if (file.files && file.files[0])  
{  
div.innerHTML = '<img id=imghead>';  
var img =div.firstChild;
img.addEventListener("click",function(){},false);
img.style.display="block";
img.onload = function(){  
var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
img.width = rect.width;  
img.height = rect.height;  
img.style.marginLeft = rect.left+'px';  
 img.style.marginTop = rect.top+'px';  
}  
 var reader = new FileReader();  
   reader.onload = function(evt){img.src = evt.target.result;}  
  reader.readAsDataURL(file.files[0]);  
  }  
 else  
 {  
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';  
    file.select();  
   var src = document.selection.createRange().text;  
    div.innerHTML = '<img id=imghead>';  
    var img = document.getElementById('imghead');  
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;  
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);  
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";  
  }  
}  
function clacImgZoomParam( maxWidth, maxHeight, width, height ){  
  var param = {top:0, left:0, width:width, height:height};  
if( width>maxWidth || height>maxHeight )  
{  
 rateWidth = width / maxWidth;  
      rateHeight = height / maxHeight;  
         
        if( rateWidth > rateHeight )  
        {  
            param.width =  maxWidth;  
           param.height = Math.round(height / rateWidth);  
        }else  
        {  
            param.width = Math.round(width / rateHeight);  
           param.height = maxHeight;  
        }  
   }  
      
    param.left = Math.round((maxWidth - param.width) / 2);  
    param.top = Math.round((maxHeight - param.height) / 2);  
    return param;  
}


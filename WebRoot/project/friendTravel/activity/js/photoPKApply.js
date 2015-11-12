
var activeId="";

function init(){
	activeId = GetQueryString("activeId");
	var input = $("#activeId");
		input.val(activeId);
	initReward();
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

function initReward(){
	$.post("../../../activeController/findActiveById.do",{"paramter":JSON.stringify({"activeId":activeId})},initRewardCallBack,"json");
}
function initRewardCallBack(ret){
	debugger;
	var div = $("#reward");
		div.html("");
	var imageUrl1 = $.parseJSON(ret.value.prizeImage);
	for(var i = 0; i < 3; i ++){
		var div1 = $("<div/>")
			div1.attr("class","img"+(i+1));
		var img1 = $("<img src=../../../"+imageUrl1.images[i]+ " alt='加载失败'/>");
		var p1 = $("<p></p>");
		var title1 = $.parseJSON(ret.value.prizeTile);
			p1.html(title1.title[i]);
			div1.append(img1);
			div1.append(p1);
			div.append(div1);
	}
	var img2 = $("<img src='../../../upload/activity/images/photoPK/reward.png' alt='加载失败' class='img'/>");
		div.append(img2);
		
		
	var div3 = $("#thing");
		div3.html(ret.value.productDesc);
}


//图片预览 
function previewImage(file)  
{  
	debugger;
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

//评论剩余字数
function checkLength(){
	var length=document.getElementById("talk").value.length;
	var number=parseInt(50-length);
	var span=document.getElementById("wordNumber");
		span.innerHTML=number+"/50";
	if(number<=0){
		document.getElementById("talk").value=document.getElementById("talk").value.substring(0,100);
	}
}














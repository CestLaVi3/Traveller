var stagtegyId=GetQueryString("stagtegyId");
var stagtegy=null;
//接参
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
function init()
{
	if(stagtegyId!=null)
	{
	 $.post("../../../stagtegyController/findById.do",{"paramter":JSON.stringify({"stagtegyId":stagtegyId})},getOneStagtegy,"json");
	 document.getElementById("title").innerHTML="修改评论";
	}
	else
	{
	document.getElementById("title").innerHTML="发表评论";
	$.post("../../../scienceSpotController/findSpotByCount.do",{"paramter":JSON.stringify({"count":"all"})},showSpot,"json");
	$.post("../../../stagtegyController/findStagtegy.do",{},showStaType,"json");
	}
}
//修改酒店填充信息
function getOneStagtegy(ret)
{
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	stagtegy=ret.value;
	$.post("../../../scienceSpotController/findSpotByCount.do",{"paramter":JSON.stringify({"count":"all"})},showSpot,"json");
	$.post("../../../stagtegyController/findStagtegy.do",{},showStaType,"json");
	$("#month option").each(function() {
        if(this.value==stagtegy.fitMonth)
		{
			this.selected=true;
		}
    });
	$("#days option").each(function() {
        if(this.value==stagtegy.travelDays)
		{
			this.selected=true;
		}
    });
	 if(stagtegy.status=="0"||stagtegy.status=="2")
		{
			$("#status option").each(function(index) {
			 if(index==1)
			 {
                $(this).attr("selected",true);
			 }
            });
		}
	 else
	  {
		 $("#status option").each(function(index) {
			 if(index==2)
			 {
				 $(this).attr("selected",true);
			 }
            }); 
	  }
	var ul=document.getElementById("images");
	for(var i=0;i<3;i++)
	{
		if(JSON.parse(stagtegy.images).images[i]!=null)
		{
		ul.childNodes[i].lastChild.firstChild.src="../../../"+JSON.parse(stagtegy.images).images[i];
		ul.childNodes[i].lastChild.firstChild.style.display="block";
		ul.childNodes[i].firstChild.style.display="none";
		}
	}
	$('form').attr('action','../../../stagtegyController/updateStagtegy.do');
	$('#stagtegyId').val(stagtegy.stagtegyId);
	var title=document.getElementById("title1");
	title.value=stagtegy.stagtegyTitle;
	var editor_a = UE.getEditor('editor').setContent(stagtegy.stagtegyContent);
	var keys=document.getElementById("keys");
	keys.value=stagtegy.keys;
	var subComment=document.getElementById("subComment");
	subComment.value=stagtegy.subComment;
}
//显示景区
function showSpot(ret)
{
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var arr=ret.value;
	var place=document.getElementById("place");
	for(var i=0;i<arr.length;i++)
	{
		var option=document.createElement("option");
		option.innerHTML=arr[i].spotName;
		option.value=arr[i].spotId;
	  if(stagtegyId!=null)
	  {
		if(arr[i].spotId==stagtegy.spotId)
		{
			option.selected=true;
		}
	  }
		place.appendChild(option);
	}
}
//显示酒店类型
function showStaType(ret)
{
	if(ret.state=="0")
	{
	 alert(ret.value); 
	 return;
	}
	var arr=ret.value;
	var type=document.getElementById("type");
	for(var i=0;i<arr.length;i++)
	{
		var option=document.createElement("option");
		option.innerHTML=arr[i].typeName;
		option.value=arr[i].typeId;
	 if(stagtegyId!=null)
	  {
	  if(arr[i].typeId==stagtegy.stagtegyType)
		{
			option.selected=true;
		}
	  }
		type.appendChild(option);
	}
}
//验证提交数据
function check()
{
	var content=document.getElementById("editor");
	var place=document.getElementById("place");
	var month=document.getElementById("month");
	var days=document.getElementById("days");
	var type=document.getElementById("type");
	var status=document.getElementById("status");
	if(place.selectedIndex==0||month.selectedIndex==0||days.selectedIndex==0||type.selectedIndex==0||status.selectedIndex==0||($("#manypic ul li:eq(0)").find("img").attr("src")==""&&$("#manypic ul li:eq(1)").find("img").attr("src")&&$("#manypic ul li:eq(2)").find("img").attr("src")))
	{
		alert("输入信息不完整");
		return false;
	}
	 return true;
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
var img =div.firstChild;
img.addEventListener("click",function(){},false);
img.style.display="block"; 
 var reader = new FileReader();  
   reader.onload = function(evt){img.src = evt.target.result;};  
  reader.readAsDataURL(file.files[0]);  
  }  
 else  
 {  
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';  
    file.select();  
   var src = document.selection.createRange().text;   
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;  
  }  
}  
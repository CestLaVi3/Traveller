// JavaScript Document
var activity=null;
var index=0;
var state=1;
function init()
{
	$.post("../../../scienceSpotController/findSpotByCount.do",{"paramter":JSON.stringify({"count":"all"})},getSpot,"json");
	var activeId=GetQueryString("activeId");
	if(activeId!=null)
    {
		$.post("../../../activeController/findTeamActiveById.do",{"paramter":JSON.stringify({"activeId":activeId})},getDetail,"json");
    }
}
//接参
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
//查看详情
function getDetail(ret)
{
	if(ret.state=="0")
	{
	  alert(ret.value);
	  return;
	}
	activity=ret.value;
	writeDetail();
}
//反填信息
function writeDetail()
{
	debugger;
 	$('.photo').find("img").attr("src","../../../"+$.parseJSON(activity.images).images[0]);
 	$('.photo').find("img").css("display","block");
 	$('.label').css("display","none");
 	$('#qq').value=activity.qqNum;
 	$('#phone').value=activity.phoneNum;
 	$('#weixin').value=activity.weixinNum;
 	$('#name').value=activity.activeName;
 	$('#desc').value=activity.productDesc;
    $('#personNum').numberspinner('setValue',activity.personNum);
    $('#travelDays').numberspinner('setValue',activity.travelDays);
    $('#startTime').datebox('setValue',activity.planDate);
    $('.right :button,:submit').css("display","none");
    getTrip(0);
}
//显示目的地
function getSpot(ret)
{
	if(ret.state)
	var sel=$("#spot");
	var arr=ret.value;
	for(var i=0;i<arr.length;i++)
	{
	  var option=$("<option/>");
	  option.val(arr[i].spotId);
	  option.html(arr[i].spotName);
	  if(activity!=null)
	  {
		 if(activity.spotId==arr[i].spotId)
		 {
		  $(option).attr("selected",true);
		 }
	  }
	  sel.append(option);
	}
}
//显示旅游天数
function getTrip(status)
{
	var arr;
	var days=parseInt($('#travelDays').numberspinner('getValue'));
	if(isNaN(days))
	{
	  alert("请输入旅行天数");
	  return;
	}
	$('.left>span').css("display","none");
	$('.img1,.img2').css("display","block");
	var ul=$('#daysUl');
	ul.html("");
	if(index+11>=days)
	 {
		arr=days;
	 }
	else
	{
		arr=index+11;
	}
	for(var i=index;i<arr;i++)
	 {
	   var li=$("<li/>");
	   li.html("第"+(i+1)+"天");
	   if(i==index)
		{
		   li.css("backgroundColor","white");
		}
	   li.attr("travelDay",i);
	   li.click(function()
			   {
		        $(this).parent().children().css("backgroundColor","#D7D7FF");
				$(this).css("backgroundColor","white");
				var ul=$('#trip');
				ul.children().css("display","none");
				ul.children("li:eq("+$(this).attr('travelDay')+")").css("display","block");
			   });
	   ul.append(li);
	 }
	if(status==0)
	{
	 writeTripDetail();
	}
	else
	{
		changeTrip();	
	}
}
//旅游行程
function writeTripDetail()
{
  var ul=$('#trip'); 
  ul.html("");
  var days=parseInt($('#travelDays').numberspinner('getValue'));
  for(var i=0;i<days;i++)
	{
	  var li=$("<li/>");
	  if(i==0)
	  {
		  li.css("display","block");
	  }
	  li.append('<div class="photo"><label for="url'+(i+1)+'" class="label">+添加图片</label><input type="file" id="url'+(i+1)+'" name="url'+(i+1)+'" onchange="previewImage(this)" style=" display:none;"><label class="lbl" for="url'+(i+1)+'"><img src=""></label></div>');
	  var txt1=$("<textarea/>");
	  txt1.attr("placeholder","住宿描述");
	  txt1.attr("name","liveDesc"+(i+1));
	  li.append("住宿描述:");
	  li.append(txt1);
	  var txt2=$("<textarea/>");
	  txt2.attr("placeholder","餐饮描述");
	  txt2.attr("name","foodDesc"+(i+1));
	  li.append("餐饮描述:");
	  li.append(txt2);
	  var sp=$("<span/>");
	  sp.html("旅游行程:");
	  sp.addClass("sp");
	  li.append(sp);
 	  var txt3=$('<textarea/>');
      txt3.attr("placeholder","餐饮描述");
	  txt3.attr("name","desc"+(i+1));
	  li.append(txt3);
	  if(activity!=null)
	   {
		  var desc=$.parseJSON(activity.activeDesc)[i];
		  li.find("img").attr("src","../../../"+desc.imageUrl);
		  li.find("img").css("display","block");
		  li.find(".label").css("display","none");
		  txt1.val(desc.liveDesc);
		  txt2.val(desc.foodDesc);
		  txt3.val(desc.desc);
	   }
	  ul.append(li);
	}
}
//翻页的行程
function changeTrip()
{
	var ul=$('#trip');
	ul.children().css("display","none");
	ul.children("li:eq("+index+")").css("display","block");
}
function checkQQ()
{
	if(!f_check_number($(event.srcElement).val()))
	{
		$(event.srcElement).css("border","red 1px solid");
		$('.qq').html("*必须为数字");
		$('.qq').attr("sta",0);
	}
	else
	{
		$(event.srcElement).css("border","0px");
		$('.qq').html("");
		$('.qq').attr("sta",1);
		 
	}
}
function checkPhone()
{
	if(!f_check_number($(event.srcElement).val()))
	{
		$(event.srcElement).css("border","red 1px solid");
		$('.phone').html("*必须为数字");
		$('.phone').attr("sta",0);
	}
	else if($(event.srcElement).val().length<11||$(event.srcElement).val().length>11)
	{
		$(event.srcElement).css("border","red 1px solid");
		$('.phone').html("*请填写11位");
		$('.phone').attr("sta",0);	
	}
	else
	{
		$(event.srcElement).css("border","0px");
		$('.phone').html("");
		$('.phone').attr("sta",1);
	}
}

//检查数据是否填写完整
function check()
{
  if(parseInt($('#travelDays').numberspinner('getValue'))!=$('#daysUl').children().length)
  {
	  $('#travelDays').numberspinner('setValue',$('#daysUl').children().length);
  }
  $(".right span").each(function b()
		  {
			  if($(this).attr("sta")=="0")
			  {
				  state=0;
				  return false;
			  }
			  state=1;
		  });
  if($(".right div").children("input,textarea").val()==""||$("#startTime").datebox('getValue')==""||$("#personNum").numberspinner('getValue')==""||$("#travelDays").numberspinner('getValue')==""||$(".right img").attr("src")==""||$(".right div select option:selected").val()=="0")
  {
	  alert("个人信息填写不要完整");
	  return false;
  }
 if($("#trip").has("li").length>0)
  {
    $("#trip li").each(function a(index){
	if($(this).children("textarea").val()==""||$(this).find("img").attr("src")=="")
	 {
		alert("第"+(index+1)+"天旅游行程填写不完整!");
		state=0;
		return false;
	 }
   });
  }
 else
  {
	 alert("请点击编辑行程，录入行程安排");
	 return false;
  }
 if(state==0)
 {
	 return false;
 }
  return true;  
}
//上一页
function past()
{
  if(index==0)
  {
	  return;
  }
  index=index-11;
  getTrip(1);
}
//下一页
function next()
{
  var days=parseInt($('#travelDays').numberspinner('getValue'));
  if(index+11>=days)
  {
	  return;
  }
  index=index+11;
  getTrip(1);
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
   reader.onload = function(evt){img.src = evt.target.result;}  
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

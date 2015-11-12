//显示正参加活动

window.onload=init;
var page=1;
var status=1;
var count=0;
var user=window.parent.user;
function init(){
	activeSpot(1);
	register();
}
function register(){
	$("#page").val("1");
	$("#btn").click(retbtn);
	$("#firstPage").click(firstPage);
	$("#endPage").click(endPage);
	$("#nextPage").click(nextPage);
	$("#previousPage").click(previousPage);
}
function retbtn(){
	var num=$("#page").val();
	if(num>parseInt(Math.ceil(count/3))){
		activeSpot(parseInt(Math.ceil(count/3)));
		$("#page").val(parseInt(Math.ceil(count/3)));
		page=parseInt(Math.ceil(count/3));
	}
	else if(num<1){
		$("#page").val("1");
		page=1;
		activeSpot(1);
	}
	else{
		activeSpot(num);
		page=num;
	}
}
function firstPage(){
	page=1;
	$("#page").val(page);
	activeSpot(page);
}
function endPage(){
	page=parseInt(Math.ceil(count/3));
	$("#page").val(page);
	activeSpot(page);
}
function previousPage(){
	page--;
	if(page==0){
		page=1;
	}
	$("#page").val(page);
	activeSpot(page);
}
function nextPage(){
	page++;
	if(page==(parseInt(Math.ceil(count/3))+1)){
		page=parseInt(Math.ceil(count/3));
	}
	$("#page").val(page);
	activeSpot(page);
}
function activeSpot(pageNum){
	var activeJoin={"userId":user.userId,"pageNum":pageNum,"pageCount":"3","status":status};
	$.post("../../../../activeController/findTeamByUserCollect.do",{"paramter":JSON.stringify(activeJoin)},showJoinActive,"json");
}
function showJoinActive(ret){
	$("#activeUl").html("");	
	count=ret.count;
	$("#allPage").html(Math.ceil(count/3));
	for(var i=0;i<ret.value.length;i++){
		var li=$("<li/>");
		var div1=$("<div/>");
		div1.addClass("intro");
		var span1=$("<span/>");
		span1.addClass("sp1");
		span1.html(ret.value[i].startDate);
		div1.append(span1);
		var span2=$("<span/>");;
		span2.html("至");
		div1.append(span2);
		var span3=$("<span/>");
		span3.addClass("sp1");
		span3.html(ret.value[i].endDate);
		div1.append(span3);
		var span4=$("<span/>");
		span4.html("活动名称:");
		div1.append(span4);
		var span5=$("<span/>");
		span5.addClass("sp2");
		span5.html(ret.value[i].activeName);
		div1.append(span5);
		li.append(div1);
		var div2=$("<div/>");
		div2.addClass("detail");
		var a1=$("<a/>");
		a1.attr("href","#");
		a1.attr("target","_top");
		var img1=$("<img/>");
		var imgu=$.parseJSON(ret.value[i].images);
		if(imgu.images.length>0){
			img1.attr("src","../../../../"+imgu.images[0]);
		}
		else{
			img1.attr("src","../../../../upload/index/images/22.jpg");
		}
		img1.attr("alt","加载失败");
		a1.append(img1);
		div2.append(a1);
		var div3=$("<div/>");
		var p1=$("<p/>");
		var span6=$("<span/>");
		span6.html("参赛者:");
		p1.append(span6);
		var a2=$("<a/>");
		a2.attr("href","#");
		var span7=$("<span/>");
		span7.html(ret.value[i].petName);
		a2.append(span7);
		p1.append(a2);
		div3.append(p1);
		var p2=$("<p/>");
		var span8=$("<span/>");
		span8.html("活动简介:");
		p2.append(span8);
		var a3=$("<a/>");
		a3.attr("href","#");
		var span9=$("<span/>");
		span9.html(ret.value[i].productDesc);
		p2.append(span9);
		div3.append(p2);
		div2.append(div3);
		li.append(div2);
		if(status=="1"){
		var a4=$("<a/>");
		a4.attr("href","#");
		a4.html("取消关注");
		a4.click(unJoinActive);
		a4.attr("activeId",ret.value[i].activeId);
		li.append(a4);
		}
		$("#activeUl").append(li);
		}
}
function unJoinActive(){
	var activeId=$(event.target).attr("activeId");
	$.post("../../../../activeController/unCollectActive.do",{"paramter":JSON.stringify({"activeId":activeId})},retJoinActive,"json");

}
function retJoinActive(ret){
	if(ret.state=="1"){
		showNowAcity(page);
	}
}
function showNowAcity()
{	status=1;
	page=1;
	$("#page").val(page);
	$("#now").css("color","red");
	$("#past").css("color","black");
	activeSpot(1);
}
//显示往期参加活动
function showPastAcity()
{	status=2;
	page=1;
	$("#page").val(page);
	$("#past").css("color","red");
	$("#now").css("color","black");
	activeSpot(1);
}
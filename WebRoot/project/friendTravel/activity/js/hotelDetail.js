$(document).ready(init);
var curSpotId = window.parent.setId;
/*
 * 初始化函数
 * */
function init(){
	$.post("../../../activeController/findTeamActiveById.do",{"paramter":JSON.stringify({"activeId":curSpotId})},hotelDetailShow,"json");
}
function hotelDetailShow(Data){
	debugger;
	var Datas = Data.value;
	var mp = new BMap.Map('hotelMap');  
	var point = new BMap.Point(121.491, 31.233);
	mp.centerAndZoom(point, 5);
	$(".hotelBasic ul li:eq(0)").html(Datas.personNum + "人已购买");
	$(".hotelBig img").attr("src","../../../"+JSON.parse(Datas.images).images[2]);
	$(".hotelLocation h4").html(Datas.spotName + "度假村");
	$(".hotelLocation p:eq(1)").html(Datas.phoneNum);
	$(".hotelLocation p:eq(0)").html(Datas.productDesc);
	$(".hotelBig h2").html(Datas.spotName + "度假村");//酒店名称
	$(".hotelPrice strong").html(Datas.collectNum);//价格
	$(".table tr:eq(0) td:eq(0)").html(Datas.productDesc);//推荐理由
	$(".table tr:eq(0) td:eq(1)").html(Datas.collectNum + "元/人");//价格
	if(Datas.status == 1){
		$(".table tr:eq(1) td:eq(0)").html("可以");//一价全包
	}else{
		$(".table tr:eq(1) td:eq(0)").html("无");//一价全包
	}
	if(Datas.travelDays > 1){
		$(".table tr:eq(1) td:eq(1)").html("有");//中文服务
	}else{
		$(".table tr:eq(1) td:eq(1)").html("无");//中文服务
	}
	$(".table tr:eq(1) td:eq(2)").html("可以");//接待儿童
	$(".table tr:eq(3) td:eq(0)").html(Datas.joinNum + "星奢华岛屿");//星级
	$(".table tr:eq(3) td:eq(1)").html("Adaaran");//价格
	$(".table tr:eq(3) td:eq(2)").html(Datas.createTime.split("-")[0] + "年");//建成/翻新
	$(".table tr:eq(5) td:eq(1)").html(Datas.phoneNum);//电话
	$(".table tr:eq(5) td:eq(0)").html(Datas.weixinNum);//官网
	$(".table tr:eq(2) td:eq(0)").html("距离马累机场26千米");//距离
	$(".table tr:eq(2) td:eq(1)").html(Datas.actualName);//交通
	$(".options li:eq(0)").css("backgroundColor","#EDB057");
	$(".options li:eq(0)").css("color","white");
	$(".options li:eq(0)").click(function(){
		$('html,body').animate({scrollTop: $(".hotelLocation").offset().top - 50},500);
		var event = $(this);
		changeLiCss(event);
	});
	$(".options li:eq(1)").click(function(){
		$('html,body').animate({scrollTop: $(".hotelDetail").offset().top - 100},1000);
		var event = $(this);
		changeLiCss(event);
	});
	$(".options li:eq(2)").click(function(){
		$('html,body').animate({scrollTop: $(".hotelFood").offset().top},1000);
		var event = $(this);
		changeLiCss(event);
	});
	var top = $('.options').position().top;
    var pos = $('.options').css("position");
	$(window).scroll(function() { //监控滚动条滚动时
         var scrolls = $(this).scrollTop(); 
         if (scrolls > top) { 
             if (window.XMLHttpRequest) { 
            	 $('.options').css({ 
                     position: "fixed",
                     top: 0  
                 }).addClass("shadow"); 
             } 
         }else { 
        	 $('.options').css({ 
                 position: pos, 
                 top: top 
             }).removeClass("shadow");
         } 
     });
}
/*
 * 清除选项栏li样式函数
 */
function changeLiCss(event){
	$(".options li").css("backgroundColor","white");
	$(".options li").css("color","black");
	event.css("backgroundColor","#EDB057");
	event.css("color","white");
}












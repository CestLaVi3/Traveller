window.onload = init;
/*轮播全局变量*/
var setId=null;
/*轮播下面的全局变量*/
var spotData = null;  //景点数据
var j = 0;//只显示四个li的限制条件
var x;//酒店主题PK第一个轮播的控制变量
var y;//酒店主题PK第二个轮播的控制变量
var set1 = null;//酒店主题PK第一个轮播的setInterval
var set2 = null;//酒店主题PK第二个轮播的setInterval
var travelContentVar = 0;
var setId=null;

function init(){
	$.post("../../../scienceSpotController/findMainSpot.do",{},sightsShow,"json");//显示创建的热门酒店展示div的动态节点的函数
	$.post("../../../stagtegyController/findStagtegy.do",{},travelTypeShow,"json");//显示旅游推荐类型的动态节点的函数
	bigLunbo();//大轮播函数
	starHotel_Carouzel();//明星酒店图标轮播
	pkMem1_Carouzel();//主题PK一轮播
	pkMem2_Carouzel();//主题PK二轮播
}
/*
 * 顶部轮播函数开始
 * */
function bigLunbo(){
	$(function(){
	    var aPage = $('#main .page a');     //分页按钮
	    var aImg = $('#main .box img');     //图像集合
	    var iSize = aImg.size();        //图像个数
	    var index = 0;      //切换索引
	    var t;
	    //分页按钮点击
	    aPage.click(function(){
	        index = $(this).index();
	        change(index)
	    });
	    //切换过程
	    function change(index){
	        aPage.removeClass('active');
	        aPage.eq(index).addClass('active');
	        aImg.stop();
	        //隐藏除了当前元素，所有图像
	        aImg.eq(index).siblings().animate({
	            opacity:0
	        },1000)
	        //显示当前图像
	        aImg.eq(index).animate({
	            opacity:1
	        },1000)
	    }
	    function autoshow() {
	        index=index+1;
	        if(index<=iSize-1){
	            change(index);
	        }else{
	            index=0;
	            change(index);
	        }
	    }
	    int=setInterval(autoshow,3000);
	    function clearInt() {
	        $('#btnLeft,#btnRight,.page a').mouseover(function() {
	            clearInterval(int);
	        })
	    }
	    function setInt() {
	        $('#btnLeft,#btnRight,.page a').mouseout(function() {
	            int=setInterval(autoshow,3000);
	        })
	    }
	    clearInt();
	    setInt();
	})
}
/*
 * 顶部轮播函数结束
 * */

/*
 * 景区展示div函数开始
*/
function sightsShow(sightData){
	debugger;
	spotData = sightData;
	showSightsTop(sightData);//显示景区栏函数
	showSightMessage(sightData,sightData.scienceSpot[0].spotId);//显示单个景区内容函数
}
/*
 * 显示景区栏函数
 */
function showSightsTop(sightData){
	for(var i = 0; i < sightData.scienceSpot.length; i++){
		var ul = $("#sightsTopUl");
		var li = $("<li>");
		ul.append(li);
		var span = $("<span>");		
		span.html(sightData.scienceSpot[i].spotName);
		span.attr("spotId",sightData.scienceSpot[i].spotId);
		span.click(changeSightMessage);
		li.append(span);
		span.css("cursor","pointer");
	}
	initTop(ul);
}
/*
 * 显示单个景区内容函数
 */
function showSightMessage(sightData,spotIdP){
	var spots = sightData.spot;
	var ul = $("#sightsDisplay");
	ul.html("");
	j=0;
	for(var i = 0; i < spots.length; i++){
		if(spots[i].spotId == spotIdP){
			if(j == 4){
				break;
			}else{
				j++;
				var a = $("<a>");
				ul.append(a);
				a.href = "#";
				var li = $("<li>");
				a.append(li);
				var divBig = $("<div>");
				li.append(divBig);
				var img = $("<img>");
				divBig.append(img);
				var imgu=JSON.parse(spots[i].images);
				img.attr("src","../../../"+imgu.images[0]);
				var span = $("<span>");
				li.append(span);
				span.html(spots[i].spotName);
			}
		}
	}
}
function changeSightMessage(){
	var curSpan = event.target;
	var ul = $("#sightsTopUl")[0];
	changeSpan(curSpan,ul);
	var spotId = curSpan.getAttribute("spotId");
	showSightMessage(spotData,spotId);
}
/*
 * 景区展示div函数结束
 */

/*
 * 旅游推荐展示div函数开始
*/
/*
 * 显示旅游推荐类型函数
 */
function travelTypeShow(travelTypeData){
	var travelTypeDatas = travelTypeData.value;
	for(var i = 1; i < 7; i++){
		var str1 = ".travelType";
		var type = str1.concat(i);
		$(type)[0].innerHTML = travelTypeDatas[i].typeName;
		travelContentVar = 0;
		$.post("../../../stagtegyController/findDescByCondition.do",{"paramter":JSON.stringify({"typeId":i})},travelTypeContentShow,"json");//显示旅游推荐类型的内容的动态节点的函数
	}
}
/*
 * 显示单个旅游推荐内容函数
 */
function travelTypeContentShow(ContentData){
	travelContentVar++;
	var ContentDatas = ContentData.value;
	var str1 = ".traSmallUl";
	var type = str1.concat(travelContentVar);
	for(var j = 0; j < ContentDatas.length; j++){
		var li = $("<li>");
		$(type).append(li);
		li.html(ContentDatas[j].spotName);
	}
	
}
function clear(){
	clearInterval(s);
	var imgself = event.srcElement;
	var listi = imgself.getAttribute("list");
	var strategys = StrategyData;
	var img = $("#showImg")[0];
	var imgu = JSON.parse(strategys[listi].images);
	img.src = "../../" + imgu.images[0];
	var p = $("#showP")[0];
	p.innerHTML = StrategyData[listi].stagtegyContent;
}
/*
 * 旅游攻略展示div函数结束
 */

/*
 * 初始化热门酒店索引函数
 */
function initTop(ul){
	$("#sightsTopUl li:eq(0) span").css("backgroundColor","#00b38a");
	$("#sightsTopUl li:eq(0) span").css("color","#ffffff");
}
/*
 * 每个模块标题栏样式改变（span & 小三角）
 * */
function changeSpan(curSpan,ul){
	var li = ul.childNodes;
	//每次改变前清除当前所有li样式
	for(var i = 0; i < li.length; i++ ){
		var span = li[i].firstChild;		//span
		span.style.backgroundColor = "white";
		span.style.color = "black";
	}
	//改变当前onmouseover对象的li样式
	curSpan.style.backgroundColor = "#00b38a";
	curSpan.style.color = "#ffffff";
}

/*
 * 明星酒店推荐li左右滑动控制函数
 */
function starHotel_Carouzel() {
    var i = 1;
    var iconUl = $("#iconUl");
    var iconUl_li = $("#iconUl li");
    $(".starHotelDis")[0].style.borderRight = "1px solid #f1f1f1";
    iconUl.width((iconUl_li.width() - 1) * iconUl_li.length);
    $(".starHotel_turnLast").hide();
    if (iconUl_li.length < 9) {
        $(".starHotel_turnNext").hide();
    }
    $(".starHotel_turnLast").click(function () {
        if (i < 3) {
            $(".starHotel_turnLast").hide();
        }
        i--;
        iconUl.animate({ marginLeft: -(iconUl_li.width()) * (i - 1) + "px" });
        $(".starHotel_turnNext").show();
    });
    $(".starHotel_turnNext").click(function () {
        if (i > iconUl_li.length - 10) {
            $(".starHotel_turnNext").hide();
        }
        iconUl.animate({ marginLeft: -(iconUl_li.width()) * i + "px" });
        i++;
        $(".starHotel_turnLast").show();
    });
}

/*
 * 酒店主题PK第一个轮播控制函数
 */
function pkMem1_Carouzel(){
	x = 1;
    var iconUl = $("#mem1_Ul");
    var iconUl_li = $("#mem1_Ul li");
    iconUl.width(iconUl_li.width() * iconUl_li.length);
    set1 = setInterval(setIn1,3000);
    iconUl.mouseover(function(){
    	clearInterval(set1);
    });
    iconUl.mouseout(function(){
    	set1 = setInterval(setIn1,3000);
    });
    $(".pkMem1_turnLast").click(function(){
    	clearInterval(set1);
    	if(x == 1){
    		x = 4;
    	}else{
    		x--;
    	}
    	iconUl.animate({marginLeft: -(iconUl_li.width()) * (x - 1) + "px"});
    	set1 = setInterval(setIn1,3000);
    });
    $(".pkMem1_turnNext").click(function(){
    	clearInterval(set1);
    	if(x == 4){
    		x = 1;
    	}else{
    		x++;
    	}
    	set1 = setInterval(setIn1,3000) ;
    	iconUl.animate({marginLeft: -(iconUl_li.width()) * (x - 1) + "px"});
    });
}

/*
 * 酒店主题PK第二个轮播控制函数
 */
function pkMem2_Carouzel(){
	y = 1;
    var iconUl = $("#mem2_Ul");
    iconUl.mouseover(function(){
    	clearInterval(set2);
    });
    iconUl.mouseout(function(){
    	set2 = setInterval(setIn2,3000);
    });
    var iconUl_li = $("#mem2_Ul li");
    iconUl.width(iconUl_li.width() * iconUl_li.length);
    setTimeout(function(){
    	set2 = setInterval(setIn2,3000);
    },1000);
    $(".pkMem2_turnLast").click(function(){
    	clearInterval(set2);
    	if(y == 1){
    		y = 4;
    	}else{
    		y--;
    	}
    	set2 = setInterval(setIn2,3000);
    	iconUl.animate({marginLeft: -(iconUl_li.width()) * (y - 1) + "px"});
    });
    $(".pkMem2_turnNext").click(function(){
    	clearInterval(set2);
    	if(y == 4){
    		y = 1;
    	}else{
    		y++;
    	}
    	set2 = setInterval(setIn2,3000);
    	iconUl.animate({marginLeft: -(iconUl_li.width()) * (y - 1) + "px"});
    });
}
function setIn1(){
	if(x == 4){
		x = 1;
	}else{
		x++;
	}
	$("#mem1_Ul").animate({marginLeft: -($("#mem1_Ul li").width()) * (x - 1) + "px"});
}
function setIn2(){
	if(y == 4){
		y = 1;
	}else{
		y++;
	}
	$("#mem2_Ul").animate({marginLeft: -($("#mem2_Ul li").width()) * (y - 1) + "px"});
}








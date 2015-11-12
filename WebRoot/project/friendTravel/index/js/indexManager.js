window.onload=init;
function quxiao(){
	document.getElementById("updateImg").style.display="none";
	document.getElementById("addImg").style.display="none";
	document.getElementById("imgmb").style.display="none";
}
function updateImg(){
	document.getElementById("updateImg").style.display="block";
	document.getElementById("imgmb").style.display="block";
}
function addImg(){
	document.getElementById("addImg").style.display="block";
	document.getElementById("imgmb").style.display="block";
}




function init(){
	getImages();
	register();
}
function register(){
	$("#deleteImages").click(deleteImages);
}
function getIamges(){
	$.post("../../../mainController/findMainImage.do",{},showImages,"json");
}
function showImages(ret){
	if(state=="1"){
		var images=ret.value;
		for(var i=0;i<images.length;i++){
			var li=$("<li/>");
			var img=$("<img/>");
			img.attr("src","../../../"+images.imageUrl);
			img.attr("alt","加载失败");
			li.append(img);
			var input=$("<input/>");
			input.attr("type","checkBox");
			input.attr("imageId",imageId);
			input.attr("name","check");
			li.append(input);
			var span=$("span");
			span.html(images.imageDesc);
			li.append(span);
			$("#imagesUl").append(li);
		}
	}
	else{
		alert(ret.value);
	}
}
function deleteImages(){
	var ids=new Array();
	var input=document.getElementsByName("check");
	for(var i=0;i<input.length;i++){
		if(input[i].ckecked){
			ids.push(input[i].imageId);
		}
	}
	$.post("../../../mainController/deleteImage.do",{"paramter":JSON.stringify(ids)},callback,"json");
}
function callback(){
	getImages();
}
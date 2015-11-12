
function init(){
	debugger;
	var ret=window.opener.foodValue;
	var div = document.getElementById("makeFood");
	var p = document.createElement("p");
	var img1 = document.createElement("img");
	var img2 = document.createElement("img");
	var img3 = document.createElement("img");
	var div1= document.createElement("div");
		p.innerHTML="烹饪步骤";
	var imageUrl=$.parseJSON(ret.images);
		img1.src="../../../"+imageUrl.images[0];
		img1.alt="加载失败";
		img2.src="../../../"+imageUrl.images[1];
		img2.alt="加载失败";
		img3.src="../../../"+imageUrl.images[2];
		img3.alt="加载失败";
		div1.innerHTML=ret.joinDesc;
		div.appendChild(p);
		div.appendChild(img1);
		div.appendChild(img2);
		div.appendChild(img3);
		div.appendChild(div1);
}




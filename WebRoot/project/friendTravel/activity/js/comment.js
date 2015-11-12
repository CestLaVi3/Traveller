// JavaScript Document
 var status=0;
 var arr=new Array();
 function commentInit(num){
	 status=num;
	 if(status==0){
		 arr=["味道","环境","服务"];
	}
	else if(status==1)
	{
	  arr=["味道","环境","服务"];
	}
	commentShow();
}
function commentShow(){
	var div=document.getElementById("myComment");
	var d2=document.createElement("div");
	var ul=document.createElement("ul");
	ul.className="myComment";
	for(var i=0;i<arr.length;i++)
	{
		var li=document.createElement("li");
		var span=document.createElement("span");
		var txt=document.createTextNode(arr[i]+":");
		span.appendChild(txt);
		li.appendChild(span);
		for(var j=0;j<5;j++){
		var img=document.createElement("img");
		if(status==0)
		 {
		img.src="../../../upload/activity/images/deliciousFood/foodComment/star.png";
		 }
		else if(status==1)
		{
		img.src="../../../../upload/activity/images/deliciousFood/foodComment/star.png";
		}
		li.appendChild(img);
	    img.onclick=changeStar;
     }
		ul.appendChild(li);
	}
	d2.appendChild(ul);
	var textarea=document.createElement("textarea");
	textarea.placeholder="请写下您的评价，对别人很有帮助哦！";
	d2.appendChild(textarea);
	var br=document.createElement("br");
	d2.appendChild(br);
	var input=document.createElement("input");
	input.type="button";
	input.value="提交";
	d2.appendChild(input);
	div.appendChild(d2);
	
}
//切换星星
function changeStar()
{
	var img=event.srcElement;
	var index;
	for(var i=1;i<6;i++)
	{
		if(img.parentNode.childNodes[i]==img)
		{
				index=i;
				break;	
		}
	}
	for(var j=1;j<=index;j++)
	{
		  if(status==0)
		   {
			img.parentNode.childNodes[j].src="../../../upload/activity/images/deliciousFood/foodComment/star1.png";	
		   }
		   else if(status==1)
		   {
		 img.parentNode.childNodes[j].src="../../../../upload/activity/images/deliciousFood/foodComment/star1.png";
		  }
	}
	for(var j=index+1;j<6;j++)
	{
			if(status==0)
		   {
			img.parentNode.childNodes[j].src="../../../upload/activity/images/deliciousFood/foodComment/star.png";	
		   }
		   else if(status==1)
		   {
		 img.parentNode.childNodes[j].src="../../../../upload/activity/images/deliciousFood/foodComment/star.png";
		  }
	}
}
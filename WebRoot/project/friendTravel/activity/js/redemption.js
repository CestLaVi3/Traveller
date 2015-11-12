// JavaScript Document
window.onload=init;
var user=window.parent.user;
function init(){
	showPersonImage();
}
function showPersonImage(){
	debugger;
	$("#personImage").attr("src","../../../../"+user.imageUrl);
	$("#userName").html(user.petName);
	$("#jinbi").html(user.userId*100);
}
function help(num){
	document.getElementById("p1").style.display="none";
	document.getElementById("p2").style.display="none";
	document.getElementById("p3").style.display="none";
	var p=document.getElementById(num);
	p.style.display="block";
}

function SetWinHeight(obj)
{
    var iframeMain=obj;
    if (document.getElementById)
    {
        if (iframeMain && !window.opera)
        {
            if (iframeMain.contentDocument && iframeMain.contentDocument.body.offsetHeight) 
               iframeMain.height = iframeMain.contentDocument.body.offsetHeight+50; 
            else if(iframeMain.Document && iframeMain.Document.body.scrollHeight)
               iframeMain.height = iframeMain.Document.body.scrollHeight+50;
        }
    }
}
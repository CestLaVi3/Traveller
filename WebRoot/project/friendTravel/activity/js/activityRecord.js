// JavaScript Document
window.onload=init;
var user=window.parent.user;
function init(){
	showPersonImage();
}
function showPersonImage(){
	$("#personImage").attr("src","../../../../"+user.imageUrl);
	$("#userName").html(user.petName);
	$("#jinbi").html(user.userId*100);
}


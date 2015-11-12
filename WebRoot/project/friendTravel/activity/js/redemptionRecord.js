window.onload=init;
var user=window.parent.user;
function init(){
	$(".p").html(user.petName);
}

window.onload=MInfInit;

function MInfInit(){
	var value=window.parent.inf;
	MySetting(value);
	if(value.userId==window.top.user.userId){
		realNameInf(value);
		userdInp(value);
	}
	
}

function MySetting(value){
	document.getElementById("nickname").innerHTML=value.petName;
	document.getElementById("site").innerHTML=value.location;//lociton 为关键字 不知道有木有影响
	document.getElementById("qqNum").innerHTML=value.qqNum;
	document.getElementById("eMail").innerHTML=value.email;
	document.getElementById("gexing").innerHTML=value.signature;
	var nan=document.getElementById("nanL").style.display;
	var nv=document.getElementById("nvL").style.display;
	var sexNum=value.sex;
	if(sexNum==0){
		nan="none";
		nv="";
	}
	else if(sexNum==1){
		nan="none";
	}
}
function realNameInf(value){
	debugger;
	document.getElementById("name").innerHTML=value.userName;
	/*document.getElementById("birthday").innerHTML=value.birthday;*/
	document.getElementById("address").innerHTML=value.address;
	document.getElementById("phoneNum").innerHTML=value.phoneNum;
	document.getElementById("idCard").innerHTML=value.identitycard;
	if(value.authenticated=="1"){
		alert("请耐心等待系统管理员验证您的身份信息");
	}else if(value.authenticated=="2"){
		alert("您已经通过审批，快去组织团游吧");
	}
	
}
function userdInp(value){
	document.getElementById("theUserId1").value=value.userId;
	document.getElementById("theUserId2").value=value.userId;
}
function AccountS(){
	var oldPwd=document.getElementById("oldPwd").innerHTML;
	var newPwd=document.getElementById("newPwd").innerHTML;
	var affPwd=document.getElementById("affPwd").innerHTML;
	if(oldPwd==ret.value.password){
		if(newPwd==affPwd){
			
		}
		else{
			alert("两次密码不一致");
		}
	}
	else{
		alert("密码输入错误");
	}
}
var objValue=[
  			[
  			{"type":"text","name":"petName"},
  			{"type":"text","name":"location"},
  			{"type":"text","name":"qqNum"},
  			{"type":"text","name":"email"},
  			{"type":"text","name":"signature"}
  			],
  			[
  			{"type":"text","name":"userName"},
  			{"type":"text","name":"address"},
  			{"type":"text","name":"phoneNum"},
  			{"type":"text","name":"identitycard"},
  			 ]
  ];
  var objv=null;

  function inputShowMode() {
  	var but=event.srcElement;
  	debugger;
  	var liId=but.parentNode.parentNode.parentNode.id;
  	var DivId="personalSet";
  	if(but.childNodes[0].nodeValue=="修改"){
  		if(liId=="cnt_1"){
  			DivId="realInf";
  			objv=objValue[1];	
  		}
  		else if(liId=="cnt_0"){
  			DivId="personalSet";
  			objv=objValue[0];	
  		}
  		var div = but.parentNode.getElementsByClassName("information_cnt");
  		xiugaiFun(but,div);		
  	}else if(but.childNodes[0].nodeValue=="保存"){
  			var form=but.parentNode.parentNode.submit();
  		if(liId=="cnt_1"){
  			DivId="realInf";
  			objv=objValue[1];
  			var div = document.getElementById(DivId).getElementsByClassName("information_cnt");
  		}
  		else if(liId=="cnt_0"){
  			DivId="personalSet";
  			objv=objValue[0];
  			var div = document.getElementById(DivId).getElementsByClassName("information_cnt");
  		}else{	
  			var div = but.parentElement.getElementsByClassName("information_cnt");
  		}
  		baocun(but,div);
  	}
  }
  function xiugaiFun(but,div,DivId){
  	var a=null;
  	for(var i=0;i<div.length;i++){
  			var inp=document.createElement("input");
  			inp.className="information_inputText";
  			inp.type="text";
  			inp.id="inp_"+i;
  			inp.name=objv[i].name;
  			inp.value=div[i].getElementsByTagName("div")[0].innerHTML;
  			inp.select();
  			inp.focus();
  			div[i].getElementsByTagName("div")[0].innerHTML=null;
  			div[i].getElementsByTagName("div")[0].appendChild(inp);
  			div[i].style.border=" 1px solid #DCDCDC";
  		}
  		if(document.getElementById("sex")){
  			document.getElementById("sex").style.border="1px solid #DCDCDC";
  			var nan=document.getElementById("nan");
  			var nanL=document.getElementById("nanL");
  			var nv=document.getElementById("nv");
  			var nvL=document.getElementById("nvL");
  			nan.style.display="";
  			nanL.style.display="";
  			nv.style.display="";
  			nvL.style.display="";	
  		}	
  		but.childNodes[0].nodeValue="保存";

  }
  function baocun(but,div,DivId){
  	
  	for(var i=0;i<div.length;i++){
  			var a=null;
  			a=document.getElementById("inp_"+i).value;
  			div[i].getElementsByTagName("div")[0].innerHTML=a;
  			div[i].style.border="none";
  		}
  		if(document.getElementById("sex")){
  			document.getElementById("sex").style.border="none";
  			var nan=document.getElementById("nan");
  			var nanL=document.getElementById("nanL");
  			var nv=document.getElementById("nv");
  			var nvL=document.getElementById("nvL");
  			nan.style.display="none";
  			nv.style.display="none";
  			if(nan.checked){
  				nanL.style.display="";
  				nvL.style.display="none";
  			}else if(nv.checked){
  				nvL.style.display="";
  				nanL.style.display="none";
  			}else{
  				nanL.style.display="";
  				nvL.style.display="none";
  			}	
  		}
  		but.childNodes[0].nodeValue="修改";
  }

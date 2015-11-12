$(document).ready(managerInit);
var indexRow=null;
var userId=null;
var petName="";
function managerInit(){
	manager();
	managerBut();
	managerSearch();
}
//初始化页面
function manager(){
	debugger;
	var a={
			url:"../../../userController/findUserByName.do",
			method:'post',
			iconCls: 'icon-edit',
			toolbar: '#tool',
			pagination:true,
			rownumbers:true,
			singleSelect: true,
			queryParams:{
				"paramter":JSON.stringify({
					'pageNum':'1',
					'pageCount':'8',
					'petName':petName
				})
			},
			loadFilter: function(data){
				if (data.state=="1"){
					var jsn={
							"total":data.count,
							"rows":dataValue(data.value)
					};
					return jsn;
				} else {
					return ;
				}
			},
			columns:[[
				{field:'jichu',title:'基本信息',colspan:6,width:'500',align:'center'},
				{field:'shenfen',title:'身份验证信息',colspan:5,width:'500',align:'center'},
				{field:'zhuangtai',title:'状态',colspan:3,width:'160',align:'center'}
				],[
				
				{field:'check',checkbox:'ture',width:'80',align:'center'},
				{field:'userId',title:'会员ID',width:'50'},
				{field:'imageUrl',title:'头像',width:'80',align:'center'},
				{field:'petName',title:'昵称',width:'80',align:'center'},
				{field:'userName',title:'姓名',width:'80',align:'center'},
				{field:'loginName',title:'账号',width:'150',align:'center'},	
				{field:'password',title:'密码',width:'150',align:'center'},
				{field:'phoneNum',title:'手机号',width:'90',align:'center'},	
				{field:'identitycard',title:'身份证号',width:'100',align:'center'},
				{field:'address',title:'通讯地址',width:'120',align:'center'},
				{field:'identityCardImage',title:'身份证图片',width:'100',align:'center'},
				{field:'freezeStatus',title:'是否冻结',width:'80',align:'center'},
				{field:'authenticated',title:'是否认证',width:'80',align:'center'}
				]],
			onClickRow:getUserAndIndex
			};		
	$('#userTab').datagrid(a);
};
/*
*修改返回的数据
* 性别
* 验证状态
* 冻结状态
* 头像标签
* 昵称链接
*/
function dataValue(value){
	debugger;
	for(var i=0;i<value.length;i++){
		value[i].freezeStatus=value[i].freezeStatus==0?"冻结":"正常";
		value[i].authenticated=value[i].authenticated==2?"已验证":"未验证";
		if(value[i].sex=="0"){
			 value[i].sex="男";
		}else if(value[i].sex=="1"){
			 value[i].sex="女";
		}else{
			 value[i].sex="保密";
		}
		value[i].imageUrl="<a href='members_home.html?userId="+value[i].userId+"'><img style='width:50px; height:50px;' src='../../../"+value[i].imageUrl+"'/></a>";
		value[i].petName="<a href='members_home.html?userId="+value[i].userId+"'>"+value[i].petName+"</a>";
		if(value[i].identityCardImage==""){
			
		}else{
			value[i].identityCardImage="<img style='width:50px; height:50px;' src='../../../"+$.parseJSON(value[i].identitycardImage).positive+"'/><img style='width:50px; height:50px;' src='../../../"+$.parseJSON(value[i].identitycardImage).opposite+"'/>";
		}
	}
	return value;
}

/*
*页面管理按钮加载
*/
function managerBut(){
	$('#add').linkbutton({iconCls:'icon-add',plain:true});
	$('#add').bind('click',addIdentification);
	$('#remove').linkbutton({iconCls:'icon-remove',plain:true});
	$('#remove').bind('click',removeIdentification);
	$('#ok').linkbutton({iconCls:'icon-ok',plain:true});
	$('#ok').bind('click',addLock);
	$('#undo').linkbutton({iconCls:'icon-undo',plain:true});
	$('#undo').bind('click',removeLock);
}
/*
*页面搜索框加载
*/
function managerSearch(){
	var searchAttr={
		searcher:function(value){ 
			petName=value;
			alert(value);
			manager();
		}, 
		prompt:'请输入搜索内容',
		width:240,
		align:'center'
	};
	$('#mSearch').searchbox(searchAttr);
};


function getUserAndIndex(index,data){
	debugger;
	indexRow=index;
	userId=data.userId;
};

//添加认证事件
function addIdentification(){
	debugger;
	$('#userTab').datagrid('updateRow',{index:0,row:{authenticated:'已认证'}});
	var a={
		"userId":userId,
		"authenticated":"2"
	};
	$.post("../../../userController/checkUser.do",{"paramter":JSON.stringify(a)},returnIdentification,"json");
	//alert($('#userTab').datagrid('getRows')[0].authenticated);
};
//删除认证事件
function removeIdentification(){
	debugger;
	$('#userTab').datagrid('updateRow',{index:0,row:{authenticated:'未认证'}});
	var a={
		"userId":userId,
		"authenticated":"0"
	};
	$.post("../../../userController/checkUser.do",{"paramter":JSON.stringify(a)},returnIdentification,"json");
};
//认证后台返回信息
function returnIdentification(ret){
	debugger;
	if(ret.state=="1"){
		alert(ret.value+",点击确认刷新页面");
	}
	else{
		alert(ret.value);
	}
};

//冻结事件
function addLock(){
	debugger;
	$('#userTab').datagrid('updateRow',{index:0,row:{freezeStatus:'冻结'}});
	var a={
		"userId":userId,
		"freezeStatus":"0"
	};
	$.post("../../../userController/freezeUser.do",{"paramter":JSON.stringify(a)},returnLock,"json");
}
//解除冻结事件
function removeLock(){
	debugger;
	$('#userTab').datagrid('updateRow',{index:0,row:{freezeStatus:'正常'}});
	var a={
		"userId":userId,
		"freezeStatus":"1"
	};
	$.post("../../../userController/freezeUser.do",{"paramter":JSON.stringify(a)},returnLock,"json");
}
//冻结后台返回信息
function returnLock(ret){
	if(ret.state=="1"){
		alert(ret.value+",点击确认刷新页面");
	}
	else{
		alert(ret.value);
	}
};


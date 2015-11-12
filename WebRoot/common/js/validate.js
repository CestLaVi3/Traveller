function trim(str){
	//删除左右两端的空格　　     
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){
	//删除左边的空格　　     
	return str.replace(/(^\s*)/g,"");
}
function rtrim(str){
	//删除右边的空格
	return str.replace(/(\s*$)/g,"");
}
/* 
功能：判断是否为日期(格式:yyyy年MM月dd日,yyyy-MM-dd,yyyy/MM/dd,yyyyMMdd) 
提示信息：未输入或输入的日期格式错误！ 
使用：f_check_date(obj) 
返回：bool 
*/ 
function f_check_date(obj)   
{   
	var date = trim(obj);  
    
    var dtype = "(yyyy-MM-dd)";   
    var format = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")"));  //日期格式  
    var year,month,day,datePat,matchArray;   
  
    if(/^(y{4})(-|\/)(M{1,2})\2(d{1,2})$/.test(format))   
        datePat = /^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/;   
    else if(/^(y{4})(年)(M{1,2})(月)(d{1,2})(日)$/.test(format))   
        datePat = /^(\d{4})年(\d{1,2})月(\d{1,2})日$/;   
    else if(format=="yyyyMMdd")   
        datePat = /^(\d{4})(\d{2})(\d{2})$/;   
    else  
    {   
        return false;   
    }   
    matchArray = date.match(datePat);   
    if(matchArray == null)    
    {   
        return false;   
    }   
    if(/^(y{4})(-|\/)(M{1,2})\2(d{1,2})$/.test(format))   
    {   
        year = matchArray[1];   
        month = matchArray[3];   
        day = matchArray[4];   
    } else  
    {   
        year = matchArray[1];   
        month = matchArray[2];   
        day = matchArray[3];   
    }   
    if (month < 1 || month > 12)   
    {                
        return false;   
    }   
    if (day < 1 || day > 31)   
    {   
        return false;   
    }        
    if ((month==4 || month==6 || month==9 || month==11) && day==31)   
    {   
        return false;   
    }        
    if (month==2)   
    {   
        var isleap=(year % 4==0 && (year % 100 !=0 || year % 400==0));   
        if (day>29)   
        {                  
            return false;   
        }   
        if ((day==29) && (!isleap))   
        {                  
            return false;   
        }   
    }   
    return true;   
}   
/* 
用途：检查输入字符串是否只由汉字、字母、数字、_、-等字符组成 
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/ 
function f_check_ZhOrNumOrLett(obj,_title){    //判断是否是汉字、字母、数字、_、-等字符组成  
    var regu = "^[-0-9a-zA-Z\u0080-\u07ff\u0800-\uffff]+$";  
    var re = new RegExp(regu);   
    if (re.test( obj )) {   
      return true;   
    }   
    return false;   
}
/* 
用途：检查输入字符串是否只由汉字、字母、数字、_、-和全角字符组成等字符组成 
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/ 
function f_check_ZhOrNumOrLettAll(obj,_title){    //判断是否是汉字、字母、数字、_、+、-、*、/、(、)和全角字符组成等字符组成  
    var regu = "^[\uFF00-\uFFFF\u4e00-\u9fa5\()(\s| )、。，\+\.\*:_0-9a-zA-Z-]*$";  
    var re = new RegExp(regu);   
    if (re.test( obj )) {   
      return true;   
    }   
    return false;   
}
/* 
用途：检查输入字符串是否只由字母、数字、_、-等字符组成 
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/
function f_check_NumOrLett(obj,_title){    //判断是否是字母、数字、_、-等字符组成  
    var regu = "^[、_0-9a-zA-Z\-]+$";  
    var re = new RegExp(regu);   
    if (re.test( obj )) {   
      return true;   
    }   
    return false;   
}
//检查字符长度，如果是汉字则占2-3个字符
/* 
用途：检查输入字符串是否只由字母、数字、_等字符组成并以字母或数字开头
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/
function f_check_StartWithLett(value,_title) {
	var myregexp = new RegExp("^[a-zA-Z0-9][_0-9a-zA-Z]*$");
	if (myregexp.test( value )) {   
      return true;
    }   
    return false; 
}
/* 
用途：检查输入字符串是否只由字母、数字、_等字符组成并以字母或数字开头
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/
function f_check_LNDL_L(value,_title) {
	var myregexp = new RegExp("^[a-zA-Z][_0-9a-zA-Z]*$");
	if (myregexp.test( value )) {   
      return true;
    }   
    return false; 
}

/* 
用途：检查输入字符串是否只由字母、数字组成并以字母开头 
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/
function f_check_NumLett(value) {
	var myregexp = new RegExp("^[0-9a-zA-Z]*$");
	if (myregexp.test( value )) {   
      return true;
    }   
    return false; 
}
function f_check_RptCode(value,_title) {
	var myregexp = new RegExp("^[0-9a-zA-Z][0-9a-zA-Z_]*$");
	if (myregexp.test( value )) {   
      return true;
    }   
    return false; 
}
function strlen(str)   
{   
    var i;   
    var len;   
       
    len = 0;   
    if(typeof(str)!='undefined'&&str.length>0)len =  str.replace(/[\u0080-\u07ff]/g,'**').replace(/[\u0800-\uffff]/g,'***').length ;
    return len;   
} 
/*判断字符长度
*_value:需要判断的字符串
*_maxLen：允许的最大长度
*_alertInfo：超长后提示信息
*return boolean 
*/
function supLen(_value,_maxLen,_alertInfo){
	if(typeof(_maxLen)=='undefined')return false;
    if(_maxLen != 0 && strlen(_value) > parseInt(_maxLen)) {   
            alert(_alertInfo);   
            return false;   
    }   
	return true;
}
/*判断字符长度
*_value:需要判断的字符串

*_maxLen：允许的最大长度

*_alertInfo：超长后提示信息
*return boolean 
*/
function supLen_alert(_value,_maxLen,_alertInfo){
	if(typeof(_maxLen)=='undefined')return false;
    if(_maxLen != 0 && strlen(_value) > parseInt(_maxLen)) {   
            return false;   
    }   
	return true;
}
/* 
* 判断是否为数字，是则返回true,否则返回false 
*/ 
function f_check_number(obj)   
{
    if (/^\d+$/.test(obj))   
    {   
       return true;   
    }    
    else    
    {   
       //f_alert(obj,"请输入数字");   
       return false;   
    }   
} 
 
/* 
用途：检查输入对象的值是否符合E-Mail格式 
输入：str 输入的字符串 
返回：如果通过验证返回true,否则返回false 
*/ 
function f_check_email(obj){     
    var myReg = /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;    
    if(myReg.test( obj )) return true;    
    return false;    
}  

/* 判断是否为邮政编码 */ 

function f_check_zipcode(obj)   
{   
    if(!f_check_number(obj))   
        return false;   
    if(obj.value.length!=6)   
    {   
        return false;   
    }   
    return true;   
} 
/* 
用途：检查输入字符串是否只由中文、字母、数字、-、@等字符组成
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/
function f_check_zh_num_sign(value, _title) {
	var myregexp = new RegExp("^[_@0-9a-zA-Z\u4e00-\u9fa5\-]+$");
	if (myregexp.test( value )) {   
      return true;
    }   
    return false; 
}
function f_alert(obj,alertInfo,_title)   
{   
    var caption=null;
    if(_title == null&&typeof(_title)=='undefined') {
        caption = "";   	
    } else {
    	caption = _title + " :";
    }
    alert(caption + alertInfo + "！");    
       // obj.focus();   
}  
function isBlank(_value,msgErr){
	if(_value==""||_value==null){
		alert(msgErr);
		return true;
	}
	return false;
}
/* 
 * 验证长度
 * strValue 需要验证的字符串
 * min 下界 
 * max 上界
 * return boolean 符合验证要求返回ture 否则返回 false

 */
function f_check_length(strValue,min,max){
  if(typeof(max)=="number" && typeof(min)=="number"){
  	return new RegExp(
      "^\\d{"+min+","+max+"}$").test(
        strValue.replace(/[\u0080-\u07ff]/g,'**').replace(/[\u0800-\uffff]/g,'***').replace(/[\s\S]/g,'0'));
  }
  return false;
}


function f_check_IP(obj,_title)    
{    
    var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/; //匹配IP地址的正则表达式   
    if(re.test( obj.value ))   
    {   
        if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;   
    }   
    return false;    
} 
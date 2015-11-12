
	
	function tabNav(num) {
		var s=0;
		var n=num;
		for (var i = 0; i < 2; i++) {
			
		var div=document.getElementById('cnt_' + i);
		
		var div1=div.getElementsByClassName("information_cnt");
		var but=div.getElementsByClassName("information_saveBut")[0];
		debugger;
			if(but.value=="保存"){
				var r=confirm("您当前页面没有保存,是否保存");
				if(r){
					baocun(but,div1);
					document.getElementById("cntMenu_"+i).style.borderBottom='none';
					div.style.display = 'none';
					s=0;
				}
				else{
					s=1;
				}
			}
			else{
				div.style.display = 'none';
			}
		}
		tabNavIf(n,s);
	}
	function tabNavIf(n,s){
		if(s==0){
		document.getElementById('cnt_' + n).style.display = 'block';
		document.getElementById("cntMenu_"+n).style.borderBottom='2px solid #ffcc33';
		}
		
	}
	
	function tabNavHome(num) {
		
		for (var i = 0; i < 7; i++) {
			document.getElementById("homeMenu_"+i).style.backgroundColor='';
		}
	
		document.getElementById("homeMenu_"+num).style.backgroundColor='#FFCC99';
	}
	function tabNavTrends(num){
		debugger;
		for (var i = 0; i <2; i++) {
			document.getElementById("trendMenu_"+i).style.borderBottom='none';
			document.getElementById("trend_"+i).style.display = 'none';
			
		}
	
		document.getElementById("trend_"+num).style.display = '';
		document.getElementById("trendMenu_"+num).style.borderBottom='2px solid #FFCC33';
	}
	

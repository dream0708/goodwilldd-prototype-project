var g_table = new Map();
var syncOptionEnum = {
        none: 1,
        clearValues: 2
};
var stringValidateEnum={
	KOREAN:1,
	ENGLISH:2,
	NUMBER:3
	
};


/**
 * @version 1.0 2012.05.02
 * 문자 제한을 한다.<br/>
 * @Example
 * 사용법 1<br/>
 * <font color="blue">stringValidate(text);</font><br/>
 * 숫자, 한글, 영어 외에 문자가 있는 경우에는 <br/><font color="red">true</font>를 리턴한다.<br/>
 * <br/>
 * @Example
 * 사용법 2<br/>
 * <font color="blue">stringValidate(text, <b>param</b>);</font><br/>
 * <br/>
 * 숫자인지 체크<br/>
 * var <b>param</b>=<br/>
 * [stringValidateEnum.NUMBER
 * ];<br/>
 * <br/>
 * 한글인지 체크
 * <br/>
 * var <b>param</b>=<br/>
 * [
 * stringValidateEnum.KOREAN
 * ];<br/><br/>
 * 영어인지 체크
 * <br/>
 * var <b>param</b>=<br/>
 * [
 * stringValidateEnum.ENGLISH
 * ];<br/><br/>
 * 아래와 같은 식으로 조합도 가능하다.<br/>
 * var <b>param</b>=<br/>
 * [stringValidateEnum.NUMBER, <br/>
 * stringValidateEnum.KOREAN, <br/>stringValidateEnum.ENGLISH
 * ];<br/><br/>
 * @Example
 * 사용법 3<br/>
 * 이메일인지 체크<br/>
 *  * <font color="blue">stringValidate(text, <b>email</b>);</font><br/>
 * 이메일이 아니면 <font color="red">false</font>를 리턴한다.<br/>
 * <br/>
 * @param string
 * @param opt
 * @returns {Boolean}
 */
function stringValidate(string, opt){
	Logs_com("=== stringValidate = "+string);
	if(typeof string != "string") return false;
	string = string.trim();
	var key="";
	if(typeof opt == "undefined"){
		key=/[^ㄱ-ㅎ|ㅏ-ㅣ|가-힝|0-9a-zA-Z]/;
		
	}else if(typeof opt=="string"){
		if(opt=="email"){
			key=new RegExp("[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+@[-!#$%&'*+/0-9=?A-Z^_a-z{|}~]+.[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+$");
			return string.search(key)>=0;
		}
	}
	else{
		var head = "[^";
		var tail = "]";
		key="[^ㄱ-ㅎ|ㅏ-ㅣ|가-힝|0-9a-zA-Z]";
		key = new RegExp(key);
		try{
			var combine;
			$(opt).each(function(){
				var result;
				if(typeof combine=="undefined"){
					result = getRegExp(this);
					if(result==false){
						combine = key;
						return false;
					}
					combine = result;	
				}else{
					result = getRegExp(this);
					if(result==false){
						combine = key;
						return false;
					}
					combine += "|"+result;
				}
			});	
			Logs_com(combine);
			key = new RegExp(head+combine+tail);
		}catch(e){
			Logs_com(e);
		}
	}
	Logs_com("key = "+key);
	return (string.search(key)>=0);
}

function getRegExp(opt){
	if(opt == stringValidateEnum.KOREAN){
		return "ㄱ-ㅎ|ㅏ-ㅣ|가-힝";
	}else
	if(opt == stringValidateEnum.ENGLISH){
		return "a-zA-Z";
	}else
	if(opt == stringValidateEnum.NUMBER){
		return "0-9";
	}else{
		return false;
	}
}

function getLanguageResource(opt){
	var lang;
	Logs_com(getContextPath()+'/web/body/languageVersion.json');

	if(opt=="new"){
		var lang;	
		Logs_com("Login make new language pack!!");
	}else{
		try{
			lang = JSON.parse($.cookie("languagePack"));	
		}catch(e){
			Logs_com("can not obtain language from cookie - "); Logs_com(e);
		}
			
	}
	try{
		var cnt = 0;
		while(lang==undefined && cnt<10){
			cnt++;
			lang = _getLanguageResource();
			if((lang!=undefined))
				$.cookie('languagePack', JSON.stringify(lang), {'expires':1, 'path':'/'});
		}	
	}catch(e){
		Logs_com("getLanguageResource - "); Logs_com(e);
	}
	
//	$.ajax({
//		type: 'get'
//		, async: false
//		, url: getContextPath()+'/web/body/languageVersion.json'		        
//		, success: function(data) {
//			Logs_com(JSON.stringify(data));
//			var version=data['version'];
//			var verInCookie=$.cookie('languageVer');
//			try{
//				lang = JSON.parse($.cookie('languagePack'));
//				lang['version'];
//				Logs_com(lang['version']);
//				Logs_com(JSON.stringify(lang));
//			}catch(e){
//				lang = undefined;
//				Logs_com("Cat not obtain cookie");
//				Logs_com(e);
//			}
//			
//			Logs_com("version="+version+", verInCookie="+verInCookie);
//			if((verInCookie!=version)||(lang==undefined)){
//				try{
//					var cnt = 0;
//					lang=undefined;
//					while(lang==undefined && cnt<10){
//						cnt++;
//						lang = _getLanguageResource();
//						if((lang!=undefined)){
//							var string = JSON.stringify(lang);
//							$.cookie('languagePack', string, {'expires':1, 'path':'/'});
//							Logs_com(($.cookie('languagePack')));
//							var ver = 0;
//							try{
//								ver = lang['version'];
//							}catch(e){
//								ver = 0;
//							}
//							Logs_com("add cookie version = "+ver);
//							$.cookie('languageVer', ver, {'expires':1, 'path':'/'});
//						}
//					}	
//				}catch(e){
//					Logs_com("getLanguageResource - "); Logs_com(e);
//				}	
//			}		
//			return lang;
//		}
//		, error: function(data, status, err) {
//			pezAlert('서버와의 통신이 실패했습니다.1 text = '+status+" erro = "+err);
//			return;
//		}
//	});
	
	return lang;
}
var languageMap;
function _getLanguageResource(){
	if(languageMap==undefined){
		Logs_com(getContextPath()+"/web/body/language");
		
		try{
			$.ajax({
				type: 'get'
				, async: false
				, url: getContextPath()+"/web/body/language"		        
				, success: function(data) {
					Logs_com("data");
					languageMap = makeLanguageResource(data);
					Logs_com("languageMap");
					Logs_com(JSON.stringify(languageMap));
					return languageMap;			
				}
				, error: function(data, status, err) {
					pezAlert('서버와의 통신이 실패했습니다.1 text = '+status+" erro = "+err);
					return;
				}
			});
			
		}catch(e){
			Logs_com("_getLanguageResource - "); Logs_com(e);
			return {};
		}
	
	}else{
		return languageMap;
	}
};



makeLanguageResource = function(data){
	Logs_com("makeLanguageResource");
	var htmlObject = $('<html></html>');
	htmlObject.html(data);
	
	Logs_com(makeLanguageResource);
	Logs_com(htmlObject.find('version').text());
	
	var retId = htmlObject.find('#languagePack').setter2();
	Logs_com("retId");
	Logs_com(retId);
	var retJson = {};
	$(retId).each(function() {
		Logs_com(this);
		var id = this.toString();
		Logs_com(id);
		var text = $(htmlObject).find('#'+id).text();
		Logs_com($(htmlObject));
		Logs_com("text = "+text);
		if((typeof text=="string")&&(text.length>0))
			retJson[id.replace(/---/g, '.')]=text;
	});
	Logs_com("result :");
	Logs_com(JSON.stringify(retJson));
	
	return retJson;
};

jQuery.fn.setter2 = function() {
	try{
		var ret = [];
		$('[id]', this).each(function() {
			var id = this.id;
			this.id = id.replace(/[.]/g, '---');
			var idName = this.id;
//			Logs_com("setter id = "+idName);
			if(idName.length>1)
				ret.push(idName);
		});
	}catch(e){
		Logs_com("setter - "); Logs_com(e);
		return [];
	}
	
	return ret;
};


/**
 * AutoSync dat로 JSON 데이타가 들어오면<br/>
 * HTML에서 JSON 데이타와 동일한 아이디를 찾아<br/>
 * 값으로 넣어 준다.<br/>
 * !!!!!! 주의 사항 !!!!!!!!!!!<br/>
 * 해당 id 하위 html의 id를 모두 초기화 시킨다.
 * @returns {Number}  -1 error<br/>
 * */
function autoSync(id, dat, option) {
	if(!($.type(dat).toString()=='object')){
		Logs_com("dat type is "+$.type(dat));
		return;
	}
	var ret = $('#'+id).setter();
	try{
		for(var i=0;i<ret.length;i++){
			Logs_com("autoSync ret="+ret[i]);
			var text = dat[ret[i]];
			Logs_com(text);
			
			switch(Number(option)){
			case syncOptionEnum.clearValues:{
				if(text==undefined){
					if($('#'+ret[i]).children().length==0){
						text="";
					}else{
						continue;	
					}
				}
				break;
			}
			
			default:
				if(text==undefined) continue;
				break;
			}
			Logs_com($('#'+id).find("#"+ret[i])[0].tagName);
			
			if(isVal($('#'+id).find("#"+ret[i])[0].tagName)){
				Logs_com('val('+text+")");
				$('#'+id).find("#"+ret[i]).val(text);
			}else if(isImg($('#'+id).find("#"+ret[i])[0].tagName)){
				Logs_com('src('+text+")");
				$('#'+id).find("#"+ret[i]).attr("src",text);
			}
			else{
				Logs_com('text('+text+")");
				$('#'+id).find("#"+ret[i]).text(text);
			}				
		}	
	}catch(e){
		Logs_com("autoSync - "); Logs_com(e);
		return;
	}
	
	return 1;
};



function isImg(name){
	Logs_com("isImg name = "+name);
	if(name=='IMG')
		return true;
	return false;
}
function isVal(name){
	Logs_com("isVal name = "+name);
	if(name=='INPUT')
		return true;
	if(name=='SELECT')
		return true;
	
	return false;
}
jQuery.fn.setter = function() {
	try{
		var ret = [];
		$('[id]', this).each(function() {
			
			var idName = this.id;
//			Logs_com("setter id = "+idName);
			if(idName.length>1)
				ret.push(idName);
		});
	}catch(e){
		Logs_com("setter - "); Logs_com(e);
		return [];
	}
	
	return ret;
};

/**
 * json 데이타에 다른 json 데이타를 더한다.
 * @param json
 * @param dat
 */
function jsonAppend(json, dat) {
//	Logs_com("jsonAppend");
	try{
		for(var key in dat){
			json[key]=dat[key];
		}
	}catch(e){
		Logs_com("setter - "); Logs_com(e);
		return ;
	}
	
//	return ret;
};



/**
 * table id를 넣는다.<br/>
 * <br/>
 * ex ) <br/>
 * var _this = this;<br/>
 *		$(document).ready(function () {&nbsp;<br/>
 *			_this.table1 = tableSync('tbl1');&nbsp;<br/>
 *		});<br/>
 * @param table id
 * @returns
 */
function tableSync(tbl){
	var table = $('#'+tbl);
	
	if(table.html()==null){
		Logs_com("tableSync Null");
//		return null;	// 잘못된 코딩일 때 에러가 나므로 인자를 넘기기로 한다.
	}
	try{
		var formatMap = new Map();
		g_table.put(tbl, formatMap);	
	}catch(e){
		Logs_com("tableSync - "); Logs_com(e);
//		return null;	// 잘못된 코딩일 때 에러가 나므로 인자를 넘기기로 한다.
	}
	
	return table;
}

/**
 * 
 * @param item 아이템 스트링이 온다.<br/>
 * @param fomatter 포멧터 함수가 넘어온다.<br/>
 * @returns {Number}  -1 error<br/>
 * ex) _this.table1.addFormatter('productprice', formatter.moneyWon);<br/>
 */
jQuery.fn.addFormatter = function(item, fomatter) {
	if(this.html()==null){
		Logs_com("addFormatter "+this.html());
		return -1;
	}
	if(!($.type(fomatter).toString()=='function')){
		Logs_com("fomatter type is "+$.type(fomatter));
		return;
//		return -1;
	}
	try{
		var formatMap = g_table.get($(this).attr('id'));
		formatMap.put(item, fomatter);
		g_table.put($(this).attr('id'), formatMap);	
		formatMap=null;	//"Make Null for memory controls"
		
	}catch(e){
		Logs_com("addFormatter - "); Logs_com(e);
		return ;
	}
};

/**
 * 넘어온 list로 table 을 만든다.<br/>
 * @param list
 * @returns {Number} -1 error
 * ex) _this.table1.tableAutoSync(datlist);
 */
jQuery.fn.tableAutoSync = function(list, option) {
	Logs_com("tableAutoSync aa");
	if(!$.type([],list) ) return -1;	// 배열이 아니면 그냥 리턴 시킨다.
	if(this.html()==null){
		Logs_com("tableAutoSync "+this.html());
		return -1;
	}
	try{
		
		var ret = this.setter();
		var container = $('<k></k>');
		var tbody2 = this.find('tbody>tr').get(-1);
		var textHtml = "";
		container.html(tbody2);
		textHtml=container.html();
		this.append(container.html());
		var tbody = $('<k></k>');
		container.html('');
		tbody.html(textHtml);
		for(var i=0;i<list.length;i++){
			container.append( sync(this, tbody, ret, list[i], option));
		}
		if(list.length==0){
			$(this).hide();
			Logs_com("Table hide");
			return 1;
		}
		
		this.find('tbody').html('');
		this.append(container.html());
		$(this).show();
		return 1;	
	}catch(e){
		Logs_com("tableAutoSync - "); Logs_com(e);
		return;
	}
};

/**
 * 넘어온 list를 기존 table 에 더한다.<br/>
 * @param list
 * @returns {Number} -1 error
 * ex) _this.table1.tableAddAutoSync(datlist);
 */
jQuery.fn.tableAddAutoSync = function(list, option) {
	
	if(!$.type([],list) ) return ;	// 배열이 아니면 그냥 리턴 시킨다.
	if(this.html()==null) return ;
	try{
		var ret = this.setter();
		var container = $('<k></k>');
		var tbody2 = this.find('tbody>tr').get(-1);
		var textHtml = "";
		container.html(tbody2);
		textHtml=container.html();
		this.append(container.html());
		var tbody = $('<k></k>');
		container.html('');
		tbody.html(textHtml);
		for(var i=0;i<list.length;i++){
			container.append( sync(this, tbody, ret, list[i], option));
		}
		this.append(container.html());
		
		return 1;	
	}catch(e){
		Logs_com("tableAddAutoSync - "); Logs_com(e);
		return;
	}
};


function sync(_this, tag, ids, dat, option) {
	if(_this.html()==null)return -1;
	if(tag.html()==null)return -1;
	if(!$.type(ids,[]))return -1;
	Logs_com("pez.util.js function sync");
	try{
		var formatMap = g_table.get($(_this).attr('id'));
		for(var i=0;i<ids.length;i++){
			var id = ids[i];
			var item = dat[id];
			
			
			switch(Number(option)){
			case syncOptionEnum.clearValues:{
				if(item==undefined){
					if($('#'+ids[i]).children().length==0){
						item="";
					}else{
						continue;	
					}
				}
				break;
			}
			
			default:
				if(item==undefined) continue;
				break;
			}
			
			
		
			var formatter=null;
			try{
				formatter  = formatMap.get(id);
			}catch(e){
				Logs_com('no formatter');
			}
			if(formatter!= null){
				item = formatter(item);
			}
			
			Logs_com($('#'+id)[0].tagName);
			Logs_com("item="+item);
			Logs_com("id = "+id);
			
			if(isVal($('#'+id)[0].tagName)){
				if($('#'+id)[0].tagName=='INPUT'){
					tag.find('#'+id).attr('value',item );
				}
				else if($('#'+id)[0].tagName=='SELECT'){
					(tag.find('#'+id+' > option')).each(function(){
						if($(this).val()==item){
							$(this).attr('selected', true);
						}else{
							$(this).attr('selected', false);
						}
					});
				}else{
					tag.find('#'+id).val(item );	
				}
				Logs_com(tag);
				Logs_com("value = "+tag.find('#'+id).val());
			}else{
				tag.find('#'+id).text(item);
			}
//			Logs_com(555);
			
		}
	}catch(e){
		Logs_com("sync - "); Logs_com(e);
		return ;
	}
	Logs_com(tag);
	return tag.html();
};


var formatter = {
	/**
	 * ex) 123456 ==> 123,456<br/>
	 * @param data
	 * @returns
	 */
	numberComma : function (data) // 숫자형 데이터에 3자리마다 콤마를 붙이는 함수
	{
		if(isNaN(data)) return data;
		var nocomma = data.replace(/,/gi,''); // 불러온 값중에서 컴마를 제거 
		var b = ''; // 값을 넣기위해서 미리 선언 
		var i = 0; // 뒤에서 부터 몇번째인지를 체크하기 위한 변수 선언 
	    
	   // 숫자를 뒤에서 부터 루프를 이용하여 불러오기 
		for (var k=(nocomma.length-1); k>=0; k--) { 
	       var a = nocomma.charAt(k); 
	 
	       if (k == 0 && a == 0) {  // 첫자리의 숫자가 0인경우 입력값을 취소 시킴 
	    	   data = ''; 
	           return data; 
	       }else { 
	           // 뒤에서 3으로 나누었을때 나머지가 0인경우에 컴마 찍기 
	           //i가 0인 경우는 제일 뒤에 있다는 것이므로 컴마를 찍으면 안됨 
	           if (i != 0 && i % 3 == 0) { 
	               b = a + "," + b ; 
	           }else { // 나머지가 0인 아닌경우 컴마없이 숫자 붙이기 
	               b = a + b; 
	           } 
	           i++; 
	       } 
	   } 
	   return b; 
	},
	
	
	/**
	 * ex) 123456 ==> 123,456 원<br/>
	 * @param data
	 * @returns
	 */
	moneyWon : function (data) 
	{
		if(isNaN(data)) return data;
	    return numberComma(data)+" 원"; 
	},
	
	
	/**
	 * 숫자를 2자리로 만듬.<br/>
	 * ex) 99 ==> 99, 1 ==> 01
	 * @param data
	 * @returns
	 */
	twoDigits : function (data) //  
	{
		if(isNaN(data)) return data;
		if(data>99) return data;
		if(data>9) return data;
		data='0'+data;
	   return data; 
	},
	
	/**
	 * 숫자를 3자리로 만듬.<br/>
	 * ex) 99 ==> 099, 1 ==> 001
	 * @param data
	 * @returns
	 */
	threeDigits : function (data) //  
	{
		if(isNaN(data)) return data;
		if(data>99) return data;
		if(data>9) return '0'+data;
	   return '00'+data; 
	},
	
	/**
	 * 숫자를 4자리로 만듬.<br/>
	 * ex) 99 ==> 0099, 1 ==> 0001
	 * @param data
	 * @returns
	 */
	fourDigits : function (data) //  
	{
		if(isNaN(data)) return data;
		if(data>999) return data;
		if(data>99) return '0'+data;
		if(data>9) return '00'+data;
	   return '000'+data; 
	},
	
	dateFormatYYYYMMDD : function (data) //  
	{
		if(!$.type(data,new Date))return data;
		var ret = "";
		try{
			ret = $.format.date(dat, 'yyyy/MM/dd');
		}catch(e){
			Logs_com("dateFormatYYYYMMDD - "); Logs_com(e);
			return data;
		}
		
		return ret; 
	},
	dateFormatYYMMDD : function (data) //  
	{
		if(!$.type(data,new Date))return data;
		
		var ret = "";
		try{
			ret = $.format.date(data, 'yy/MM/dd');
				
		}catch(e){
			Logs_com("dateFormatYYMMDD - "); Logs_com(e);
			return data;
		}
		
		return ret; 
	}
	
};



/**
 * form 아이디를 받아서 input에 있는 value를 json<br/>
 * 데이타로 리턴한다.
 * @param formId ex)document.form
 * @returns JSON 데이타
 */
function getJsonFromForm(formId){
	var ret={};
	
	try{
		$(':input', formId).each(function() {
			var name = this.name;
			if(name.length>1){
				ret[name]=this.value;
				Logs_com("k key = "+name+", value = "+this.value);
			}
		});
	}catch(e){
		Logs_com("getJsonFromForm - "); 
		Logs_com(e);
		return {};
	}
	
	return ret;
}
/**
 * @version 1.0 2012.04.17
@example
@1. 일반 얼랏 메시지 - show() 필요 없음.<br/>
	pezAlert("alert message");<br/>
	<br/>
@example
@2.콜백 함수 포함 얼랏 메시지<br/>
	pezAlert("alert message", function(){<br/>
//		callback function<br/>
//		do somethingg<br/>
	}).show();	// 반드시 show()를 호출해야 나타난다.<br/>
	<br/>
@example
@3.	alertType을 warning으로 한다.<br/>
	pezAlert("alert message", function(){<br/>
//		callback function<br/>
//		do somethingg<br/>
	}).type(alertType.WARNING)	<br/>
	.show();// 반드시 show()를 호출해야 나타난다.<br/>
	<br/>
	type 종류<br/>
	alertType.ERROR<br/>
	alertType.INFO<br/>
	alertType.SUCCESS<br/>
	alertType.WARNING<br/>
	alertType.CONFIRM<br/>
<br/>
@example
@4. alert을 객체로 생성한다.<br/>
	var popup0 = pezAlert("Are you sure?", function(){<br/>
//		ok 버튼을 눌렀을 때<br/>
//		do something<br/>
	}, function(){<br/>
//		cancel  버튼을 눌렀을 때<br/>
//		do something<br/>
	});<br/>
	popup0.type(alertType.CONFIRM)<br/>
		.lButton("오케이")<br/>
		.rButton("노");<br/>
	popup0.show();	// show()를 호출해야 나타난다.<br/>
	<br/>
//	사용 가능 함수 및 설명<br/>
	  lButton(text)			// 확인(기본) 버튼 텍스트<br/>
	  rButton(text)			// 취소 버튼 텍스트<br/>
	  title(text)			// 제목 텍스트	<br/>
	  draggable(boolean)	// 창을 드래그 할 수 있는지. default : true<br/>
	  modal(boolean)		// 배경을 클릭 할 수 없게. default : true / false 면 클릭 가능<br/>
	  resizable(boolean)	// 창 크기를 마우스로 조정 가능하게. // default true<br/>
	  noTitle(boolean)		// 타이틀을 숨긴다. default : false<br/>
	  height(number)		// 높이 지정	 default : auto<br/>
	  width(number)			// 넓 지정 default : 300<br/>
	  maxHeight(number)		// 최대 높이 지정<br/>
	  maxWidth(number)		// 최대 넓이 지정<br/>
	  minHeight(number)		// 최소 높이 지정<br/>
	  minWidth(number)		// 최소 넓이 지정<br/>
	<br/>
*/

function pezAlert(string, success, cancel){
	
	if(typeof success == 'function'){
		return new pezAlertClass(string, success, cancel);
	}else{
		return new pezAlertClass(string, null, null).type(alertType.INFO).noTitle(true).show();	
	}
}


/**
 *   ERROR<br/>
        INFO<br/>
        SUCCESS<br/>
        WARNING<br/>
        CONFIRM<br/>
     ex) new pezAlertClass("위험").type(alertType.WARNING).show();
 */
var alertType = {
        ERROR:'error',
        INFO:'info',
        SUCCESS:'success',
        WARNING:'warning',
        CONFIRM:'confirm'
};

function makeStringTag(string){
	if(typeof string !="string") return string;
	var ret=[];
	var tag=$("<p></p>");
	if(string.indexOf('\n')>=0){
		ret=string.split('\n');
		Logs_com(ret);
		var ss="";
		$(ret).each(function(){
			ss+=this+"<br/>";
		});
		tag.html(ss);
	}else{
		tag.html(string);
	}
	return tag;
}

/**
 * var popup = new pezAlertClass(메시지, ok 함수, 취소 함수).type(alertType.CONFIRM).show();<br/>
 * 또는<br/>
 * var popup = new pezAlertClass(메시지, ok 함수, 취소 함수);<br/>
 * popup.type(alertType.ERROR);<br/>
 * popup.title("제목 변경).lButton("괜찮아);<br/>
 * popup.show();<br/>
 * @param string 메시지
 * @param success 확인 메소드
 * @param cancel 취소 메소드
 */
function pezAlertClass (string, success, cancel){
	Logs_com("pezAlertClass - "+string);	
	var _pez = this;
	_pez.alertType = alertType;
	
//	_pez.loading = $("<div id='pezPopup' ><div id='pezPopup_message' style='padding-left: 48px;'></div></div>");
	_pez.loading = $("<div id='pezPopup' ><p id='pezPopup_message' style='padding-left: 48px;'></p></div>");
//	alert(string);
//	makeStringTag(string);
//	$(_pez.loading).find('#pezPopup_message').text(string);
	$(_pez.loading).find('#pezPopup_message').html(makeStringTag(string));
	var lang = getLanguageResource();
	
	
	var text_alert;
	var text_ok;
	var text_cancle;
	
	if(typeof lang=='object'){
		try{
			text_alert = lang['alert.alert'];
			text_ok = lang['alert.ok'];
			text_cancle = lang['alert.cancle'];	
		}catch(e){
			Logs_com(e);
		}
	}
		
	/**
	 * default values - start
	 */
	
	if(text_alert!=undefined)
		_pez._title = text_alert;
	else
		_pez._title = "알림";
	
	if(text_ok!=undefined)
		_pez._lBtn = text_ok;
	else
		_pez._lBtn = "확인";
	
	if(text_cancle!=undefined)
		_pez._rBtn = text_cancle;
	else
		_pez._rBtn = "취소";
	
	_pez._modal = true;
	_pez._draggable = true;
	_pez._height = 'auto';
	_pez._width = 300;
	_pez._maxHeight = false;
	_pez._maxWidth = false;
	_pez._minHeight = 150;
	_pez._minWidth  = 150;
	_pez._resizable = true;
	_pez._noTitle = false;
	/**
	 * default values - the end
	 */
	

	
	
	
//	/**
//	 * default values - start
//	 */
//	_pez._modal = true;
//	_pez._title = "알림";
//	_pez._lBtn = "확인";
//	_pez._rBtn = "취소";
//	_pez._draggable = true;
//	_pez._height = 'auto';
//	_pez._width = 300;
//	_pez._maxHeight = false;
//	_pez._maxWidth = false;
//	_pez._minHeight = 150;
//	_pez._minWidth  = 150;
//	_pez._resizable = true;
//	_pez._noTitle = false;
//	/**
//	 * default values - the end
//	 */
	
	var text_error = "에러";
	var text_information = "정보";
	var text_succeed = "성공";
	var text_warning = "경고!!";
	var text_areUsure = "확실합니까?";
	var text_yes = "네!";
	
	try{
		text_error = lang['alert.error'];
		text_information = lang['alert.information'];
		text_succeed = lang['alert.succeed'];
		text_warning = lang['alert.warning'];
		text_areUsure = lang['alert.areUsure'];
		text_yes = lang['alert.yes'];	
	}catch(e){
		Logs_com(e);
	}
	
	
	
	_pez.type=function(type){
		
		switch(type){
		case _pez.alertType.ERROR:{
			$(_pez.loading).addClass(_pez.alertType.ERROR);
			if(text_error!=undefined){
				_pez.title(text_error);
			}else{
				_pez.title("에러");	
			}
			
			break;
		}
		case _pez.alertType.INFO:{
			$(_pez.loading).addClass(_pez.alertType.INFO);
			if(text_information!=undefined){
				_pez.title(text_information);
			}else{
				_pez.title("정보");	
			}
			
			break;
		}
		case _pez.alertType.SUCCESS:{
			$(_pez.loading).addClass(_pez.alertType.SUCCESS);
			if(text_succeed!=undefined){
				_pez.title(text_succeed);
			}else{
				_pez.title("성공");	
			}
			
			break;
		}
		case _pez.alertType.WARNING:{
			$(_pez.loading).addClass(_pez.alertType.WARNING);
			if(text_warning!=undefined){
				_pez.title(text_warning);
			}else{
				_pez.title("경고!!");	
			}
			
			break;
		}
		case _pez.alertType.CONFIRM:{
			$(_pez.loading).addClass(_pez.alertType.CONFIRM);
			if(text_areUsure!=undefined && text_yes!=undefined){
				_pez.title(text_areUsure);
				_pez.lButton(text_yes);	
			}else{
				_pez.title("확실합니까?");
				_pez.lButton("네!");	
			}
			
			_pez.show = _pez.confirm;
			break;
		}
		}
		
		return _pez;
	};

	_pez.lButton = function (name){
		if(typeof name == "string"){
			_pez._lBtn = name;
		}
			
		return _pez;
	};
	
	_pez.rButton = function (name){
		if(typeof name == "string"){
			_pez._rBtn = name;
		}
		return _pez;
	};
	
	_pez.title = function (name){
		if(typeof name == "string"){
			_pez._title = name;
		}
		return _pez;
	};
	_pez.draggable = function (flag){
		if(typeof flag == "boolean"){
			_pez._draggable = flag;
		}
		return _pez;
	};
	_pez.modal = function (flag){
		if(typeof flag == "boolean"){
			_pez._modal = flag;
		}
		return _pez;
	};
	_pez.resizable = function (flag){
		if(typeof flag == "boolean"){
			_pez._resizable = flag;
		}
		return _pez;
	};
	
	_pez.noTitle = function (flag){
		if(typeof flag == "boolean"){
			_pez._noTitle = flag;
		}
		return _pez;
	};
	
	
	_pez.height = function(size){
		if(typeof size == "number"){
			_pez._height = size;
		}
		return _pez;
	};
	
	_pez.width = function(size){
		if(typeof size == "number"){
			_pez._width = size;
		}
		return _pez;
	};
	
	_pez.maxHeight = function(size){
		if(typeof size == "number"){
			_pez._maxHeight = size;
		}
		return _pez;
	};
	
	_pez.maxWidth = function(size){
		if(typeof size == "number"){
			_pez._maxWidth = size;
		}
		return _pez;
	};
	_pez.minHeight = function(size){
		if(typeof size == "number"){
			_pez._minHeight = size;
		}
		return _pez;
	};
	
	_pez.minWidth = function(size){
		if(typeof size == "number"){
			_pez._minWidth = size;
		}
		return _pez;
	};
	
	
		
	_pez.confirm = function (){
		try{
			$(_pez.loading).dialog({
				modal:_pez._modal,
				title:_pez._title,
				draggable:_pez._draggable,
				height:_pez._height,
				width:_pez._width,
				resizable:_pez._resizable,
				maxHeight:_pez._maxHeight,
				maxWidth:_pez._maxWidth,
				minHeight:_pez._minHeight,
				minWidth:_pez._minWidth,
				
				buttons: [{
					text : _pez._lBtn,
					click: function() {
						if(typeof success == 'function'){
							$( _pez.loading ).dialog( "close" );
							return success.call();
					      }
						$( _pez.loading ).dialog( "close" );
					}
				},
				{
					text:_pez._rBtn,
					click: function() {
						if(typeof cancel == 'function'){
							$( _pez.loading ).dialog( "close" );
							return cancel.call();
					      }
						$( _pez.loading ).dialog( "close" );
					}
				}]
			});
			if(!_pez._noTitle){
				$(_pez.loading).prev().removeClass('ui-dialog-titlebar');
				$(_pez.loading).prev().addClass('ui-dialog-titlebar-show');	
			}
			
		}catch(e){
			Logs_com("autoSync - "); Logs_com(e);
			return;
		}
		
	};
	
	_pez._alert = function (){
		try{
			$(_pez.loading).dialog({
				modal:_pez._modal,
				title:_pez._title,
				draggable:_pez._draggable,
				height:_pez._height,
				width:_pez._width,
				resizable:_pez._resizable,
				maxHeight:_pez._maxHeight,
				maxWidth:_pez._maxWidth,
				minHeight:_pez._minHeight,
				minWidth:_pez._minWidth,
				
				buttons: [{
					text : _pez._lBtn,
					click: function() {
						if(typeof success == 'function'){
							$( _pez.loading ).dialog( "close" );
							return success.call();
					      }
						$( _pez.loading ).dialog( "close" );
					}
				}]
			});
			if(!_pez._noTitle){
				$(_pez.loading).prev().removeClass('ui-dialog-titlebar');
				$(_pez.loading).prev().addClass('ui-dialog-titlebar-show');	
			}
		}catch(e){
			Logs_com("autoSync - "); Logs_com(e);
			return;
		}
	};
	
	_pez.show = _pez._alert;
};


function Logs_com(log){
	console.log(log);
}



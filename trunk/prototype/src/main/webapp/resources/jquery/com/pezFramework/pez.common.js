//popup window
(function($){ 		  
	$.fn.popupWindow = function(instanceSettings){
		
		return this.each(function(){
		
		$(this).click(function(){
		
		$.fn.popupWindow.defaultSettings = {
			centerBrowser:0, // center window over browser window? {1 (YES) or 0 (NO)}. overrides top and left
			centerScreen:0, // center window over entire screen? {1 (YES) or 0 (NO)}. overrides top and left
			height:500, // sets the height in pixels of the window.
			left:0, // left position when the window appears.
			location:0, // determines whether the address bar is displayed {1 (YES) or 0 (NO)}.
			menubar:0, // determines whether the menu bar is displayed {1 (YES) or 0 (NO)}.
			resizable:0, // whether the window can be resized {1 (YES) or 0 (NO)}. Can also be overloaded using resizable.
			scrollbars:0, // determines whether scrollbars appear on the window {1 (YES) or 0 (NO)}.
			status:0, // whether a status line appears at the bottom of the window {1 (YES) or 0 (NO)}.
			width:500, // sets the width in pixels of the window.
			windowName:null, // name of window set from the name attribute of the element that invokes the click
			windowURL:null, // url used for the popup
			top:0, // top position when the window appears.
			toolbar:0 // determines whether a toolbar (includes the forward and back buttons) is displayed {1 (YES) or 0 (NO)}.
		};
		
		settings = $.extend({}, $.fn.popupWindow.defaultSettings, instanceSettings || {});
		
		var windowFeatures =    'height=' + settings.height +
								',width=' + settings.width +
								',toolbar=' + settings.toolbar +
								',scrollbars=' + settings.scrollbars +
								',status=' + settings.status + 
								',resizable=' + settings.resizable +
								',location=' + settings.location +
								',menuBar=' + settings.menubar;

				settings.windowName = this.name || settings.windowName;
				settings.windowURL = this.href || settings.windowURL;
				var centeredY,centeredX;
			
				if(settings.centerBrowser){
						
					if ($.browser.msie) {//hacked together for IE browsers
						centeredY = (window.screenTop - 120) + ((((document.documentElement.clientHeight + 120)/2) - (settings.height/2)));
						centeredX = window.screenLeft + ((((document.body.offsetWidth + 20)/2) - (settings.width/2)));
					}else{
						centeredY = window.screenY + (((window.outerHeight/2) - (settings.height/2)));
						centeredX = window.screenX + (((window.outerWidth/2) - (settings.width/2)));
					}
					window.open(settings.windowURL, settings.windowName, windowFeatures+',left=' + centeredX +',top=' + centeredY).focus();
				}else if(settings.centerScreen){
					centeredY = (screen.height - settings.height)/2;
					centeredX = (screen.width - settings.width)/2;
					window.open(settings.windowURL, settings.windowName, windowFeatures+',left=' + centeredX +',top=' + centeredY).focus();
				}else{
					window.open(settings.windowURL, settings.windowName, windowFeatures+',left=' + settings.left +',top=' + settings.top).focus();	
				}
				return false;
			});
			
		});	
	};
})(jQuery);
function gotoUrl(url){ location.href = url;} // url history를 남기고 페이지 이동함.
function gotoUrlReplace(url){ location.replace(url);} // url history를 남기지 않고 페이지 이동함.
function scrollToTop(){ document.body.scrollTop = 0; } // 스크롤을 맨위로 이동시키는 함수
//콤마 붙이기
function numberComma(data) // 숫자형 데이터에 3자리마다 콤마를 붙이는 함수
{
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
}	


function Trim(strings){
	var retString = "";
	var c;
	var i;
	for(i=0; i<strings.length;i++){
		c = strings.charAt(i);
		if(c != ' '){
			retString += c;
		}
	}
	return(retString);
};


function Logs(dat){
	console.log(dat);
}



Map = function(){
	 this.map = new Object();
	};   
Map.prototype = {   
    put : function(key, value){   
        this.map[key] = value;
    },   
    get : function(key){   
        return this.map[key];
    },
    containsKey : function(key){    
     return key in this.map;
    },
    containsValue : function(value){    
     for(var prop in this.map){
      if(this.map[prop] == value) return true;
     }
     return false;
    },
    isEmpty : function(key){    
     return (this.size() == 0);
    },
    clear : function(){   
     for(var prop in this.map){
      delete this.map[prop];
     }
    },
    remove : function(key){    
     delete this.map[key];
    },
    keys : function(){   
        var keys = new Array();   
        for(var prop in this.map){   
            keys.push(prop);
        }   
        return keys;
    },
    values : function(){   
     var values = new Array();   
        for(var prop in this.map){   
         values.push(this.map[prop]);
        }   
        return values;
    },
    size : function(){
      var count = 0;
      for (var prop in this.map) {
        count++;
      }
      return count;
    }
};

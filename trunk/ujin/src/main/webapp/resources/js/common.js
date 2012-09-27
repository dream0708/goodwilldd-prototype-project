String.prototype.padLeft = function (length, character) { 
    return new Array(length - this.length + 1).join(character || '0') + this; 
};
	
$.fn.serializeObject = function(){
   var o = {};
   var a = this.serializeArray();
   var inputValue = "";
   $.each(a, function() {
	   inputValue = this.value;
	   if ( $('#' + this.name).hasClass('pezComma') )// Commify Input Data unformatting 
		   inputValue = inputValue.replace(/,/g, '');
	
       if ( $('#' + this.name).hasClass('pezDate') )// Date Input Data unformatting
     	   inputValue = inputValue.replace(/-/g, '');
       
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(inputValue || '');
       } else {
           o[this.name] = inputValue || '';
       }
   });
   return o;
};
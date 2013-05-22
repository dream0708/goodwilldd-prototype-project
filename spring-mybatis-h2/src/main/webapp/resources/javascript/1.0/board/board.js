$(function(){
	var self = this;
	$("button[name='write']").bind("click", function() {
		location.href="save.htm";
	});
	$("button[name='list']").bind("click", function() {
		location.href="list.htm";
	});
});

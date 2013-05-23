$(function(){
	var self = this;
	$("button[name='write']").bind("click", function() {
		location.href="writeForm.htm";
	});
	$("button[name='list']").bind("click", function() {
		location.href="list.htm";
	});
	$(".subject").bind("click", function() {
		location.href="read/"+$(this).find("input").val()+".htm";
	});
});

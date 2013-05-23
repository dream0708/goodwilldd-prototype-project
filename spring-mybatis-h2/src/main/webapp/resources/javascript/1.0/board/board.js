$(function(){
	var self = this;
	$(".subject").bind("click", function() {
		location.href="read/"+$(this).find("input").val()+".htm";
	});
});

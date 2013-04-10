function MainViewModel() {
	var self = this;
	self.openDialog = function() {
		$("#dialogItem").dialog("open");
	};
	self.saveCustomer = function() {
		$("#customer").ajaxSubmit({
			url: "/ujin/main/saveCustomer.htm",
			type: "post",
			syccess: function() {
				$("#dialogItem").dialog("close");
			},
			error: function() {
				$("#dialogItem").dialog("close");		
			}
		}).clearForm();
	};
};


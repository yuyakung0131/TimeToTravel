$(document).ready(function() {
	$(".account").click(function(e) {
		e.preventDefault();
		var form = $("#memberCenterForm")
		form.submit();
	})
})

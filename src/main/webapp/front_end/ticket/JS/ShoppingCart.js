$(document).ready(function() {
	$(".shoppingCartIMG").click(function(e) {
		e.preventDefault();
		var form = $("#shoppingListForm")
		form.submit();
	})
})
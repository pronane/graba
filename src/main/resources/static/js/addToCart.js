$(document).ready(function() {
	$("buttonAddToCart").on("click", function(e) {
		alert('Add to cart')
		addToCart();
	});	
});

function addToCart() {
	quantity = $("#quantity" + productId).val();
	///cart/add/{pid}/qty}
	url= contextPath + "/cart/add/" + productId + "/" + quantity;
	
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(crsfHeaderName, csrfValue);
		}
	}).done(function(reponse) {
		$("#modalTitle").text("Shopping Cart");
		$("#modalBody").text(response);
		$("#myModal").modal();
	}).fail(function() {
		$("#modalTitle").text("Shopping Cart");
		$("#modalBody").text("Error while adding product to shopping cart.");
		$("#myModal").modal();
	});
}
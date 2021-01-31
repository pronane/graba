$(document).ready(function() {
	$("#buttonAddToCart").on("click", function(e) {
		addToCart();
	});	
});


function addToCart() {
	quantity = $("#quantity" + productId).val();
	///cart/add/{pid}/qty}
	url= contextPath + "cart/add/" + productId + "/" + quantity;
	$.ajax({
		type: "POST",
		url: contextPath + "cart/add/",
		data: jQuery.param({ productId: productId, quantity : quantity}) ,
    	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(crsfHeaderName, csrfValue);
		}
	}).done(function(response) {
		if(response.includes("You must login")) {
			$("#modalTitle").text("Shopping Cart");
			$("#modalBody").text("You must login to add items to your Cart.");
			$("#myModal").modal();
			 $('#myModal').on('hidden.bs.modal', function () {
				window.location.href = "/login"
			  })
		} else {
			$("#modalTitle").text("Shopping Cart");
			$("#modalBody").text(response);
			$("#myModal").modal();
		}
	}).fail(function() {
		$("#modalTitle").text("Shopping Cart");
		$("#modalBody").text("Error while adding product to shopping cart.");
		$("#myModal").modal();
	});
}

/*function addToCart() {
	quantity = $("#quantity" + productId).val();
	///cart/add/{pid}/qty}
	url= contextPath + "cart/add/" + productId + "/" + quantity;

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
}*/
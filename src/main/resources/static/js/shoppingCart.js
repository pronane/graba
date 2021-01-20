$(document).ready(function() {
	$("buttonAddToCart").on("click", function(e) {

		$(".minusButton").on("click", function(evt) {
			evt.preventDefault();
			decreaseQuantity($(this));
		});

		$(".plusButton").on("click", function(evt) {
			evt.preventDefault();
			increaseQuantity($(this));
		});

		$(".link-remove").on("click", function(evt) {
			evt.preventDefault();
			removeFromCart($(this));
		});
		alert('Add to cart')
		updateTotal();
	});
});

function removeFromCart(link) {
	url = link.attr("href");
	
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(crsfHeaderName, csrfValue);
		}
	}).done(function(response) {
		$("#modalTitle").text("Shopping Cart");
		if(response.includes("removed")) {
			$("#myModal").on("hide.bs.modal", function(e) {
				rowNumber = link.attr("rowNumber");
				removeProduct(rowNumber);
				updateTotal();
			});
		}
		$("#modalBody").text(response);
		$("#myModal").modal();
		
	}).fail(function() {
		$("#modalTitle").text("Shopping Cart");
		$("#modalBody").text("Error while adding product to shopping cart.");
		$("#myModal").modal();
	});
}

function removeProduct(rowNumber) {
	rowId = "row" + rowNumber;
	$("#"+rowId).remove();
}

function decreaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);

	newQty = parseInt(qtyInput.val()) - 1;
	if (newQty > 0) {
		qtyInput.val(newQty);
		updateQuantity(productId,newQty);
	}
}

function increaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);

	newQty = parseInt(qtyInput.val()) + 1;
	if (newQty < 10) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
		
}
function updateQuantity(productId, quantity) {
	url= contextPath + "/cart/update/" + productId + "/" + quantity;
	
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(crsfHeaderName, csrfValue);
		}
	}).done(function(newSubtotal) {
		updateSubtotal(newSubtotal, productId);
		updateTotal();
	}).fail(function() {
		$("#modalTitle").text("Shopping Cart");
		$("#modalBody").text("Error while adding product to shopping cart.");
		$("#myModal").modal();
	});
}

function updateSubtotal(newSubtotal, productId) {
	$("#subtotal" +productId).text(newSubtotal);
}

function udpateTotal() {
	tota = 0.0;

	$(".productSubtotal").each(function(index, element) {
		total = total + parseFloat(element.innerHTML);
	});

	$("#totalAmount").text("$" + total);
}
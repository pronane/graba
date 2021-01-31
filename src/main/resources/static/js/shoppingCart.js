$(document).ready(function() {
		$(".link-remove").on("click", function(evt) {

			evt.preventDefault();
			removeFromCart($(this));
		});
//	$("buttonAddToCart").on("click", function(e) {

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
		//alert('Add to cart')
		//updateTotal();
//	});
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
				originalQuantity = $("#quantity" + productId);

			});
		}
		$("#modalBody").text(response);
		$("#myModal").modal();
		
	}).fail(function(response) {
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
		removeFromBasket(newQty);
	}
}

function increaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);
	newQty = parseInt(qtyInput.val()) + 1;
	if (newQty < 10) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	    addToBasket(newQty);
	}	
}

function updateQuantity(productId, quantity) {
	url= contextPath + "/cart/update/" + productId + "/" + quantity;
	$.ajax({
		type: "POST",
		url: contextPath + "cart/update/",
		data: jQuery.param({ productId: productId, quantity : quantity}) ,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
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

function addToBasket(newQuantity) {
		var original = $('.update span').html();
		if(!original ) {
			original = '0';
		}
		var totalCartItems = parseInt(original) + parseInt(1);
		$('.update span').html(totalCartItems);
}

function removeFromBasket(newQuantity) {
		var original = $('.update span').html();
		if(!original ) {
			original = '0';
		}
		var totalCartItems = parseInt(original) - parseInt(1);
		$('.update span').html(totalCartItems);
}

/*function updateQuantity(productId, quantity) {
	url= contextPath + "/cart/update/" + productId + "/" + quantity;
		alert("alert url is " + url);
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
}*/

function updateSubtotal(newSubtotal, productId) {
	$("#subtotal" +productId).text(newSubtotal);
}

function updateTotal() {
	total = 0.0;

	$(".productSubtotal").each(function(index, element) {
		total = total + parseFloat(element.innerHTML);
	});

	$("#totalAmount").text("$" + total);
}
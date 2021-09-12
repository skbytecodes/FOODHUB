window.onload = function setItemsTocart() {
	let itemsdiv = document.getElementById('items');

	var products = localStorage.getItem("items");
	var items = JSON.parse(products);
	for (let item of items) {

		let id = item.itemid;
		let name = item.itemname;
		let cost = item.itemcost;
		let description = item.itemdesc;
		let image = item.itemimg;
		let quantity = item.quantity;


		//DOM CREATION

		let itemdiv = document.createElement('div');
		itemdiv.setAttribute('id', 'item');
		itemdiv.setAttribute('class', 'item');

		let img = document.createElement('img');
		img.src = '' + image;
		img.setAttribute('class', 'product-image');

		let itemName = document.createElement('span');
		itemName.setAttribute('class', 'name');
		itemName.innerText = name;


		let itemCost = document.createElement('span');
		itemCost.setAttribute('class', 'cost');
		itemCost.innerText = cost;


		let itemQuantity = document.createElement('input');
		itemQuantity.innerHTML = quantity;
		itemQuantity.setAttribute('id', '' + item.itemid);
		itemQuantity.setAttribute('type', 'number');
		itemQuantity.setAttribute('value', quantity);
		itemQuantity.setAttribute('class', 'quantity');
		itemQuantity.addEventListener('change', quantityChanged);


		let remove = document.createElement('span');
		remove.setAttribute('id', '' + id);
		remove.setAttribute('class', 'remove');
		remove.innerText = "Remove";
		remove.addEventListener('click', removeButton)

		itemdiv.appendChild(img);
		itemdiv.appendChild(itemName);
		itemdiv.appendChild(itemCost);
		itemdiv.appendChild(itemQuantity);
		itemdiv.appendChild(remove);
		itemsdiv.appendChild(itemdiv);
	}
	totalPrice();
}

function totalPrice() {
	let total = 0;
	let items = document.querySelectorAll('.item');
	for (let i = 0; i < items.length; i++) {
		let costelem = items[i].querySelector('.cost');
		let quantelem = items[i].querySelector('input');
		let cost = costelem.innerText
		let quantity = quantelem.value;
		total = total + (cost * quantity)
	}

	document.querySelector('.total').innerText = total.toFixed(2);
	document.querySelector('.subtotal').innerText = total.toFixed(2);
	localStorage.setItem("amount", total);
}

function quantityChanged(event) {
	let inputelem = event.target;
	let quantity = inputelem.value
	let id = inputelem.getAttribute('id');
	if (isNaN(quantity) || quantity <= 0) {
		quantity = 1;
	}
	else {
		inputelem.setAttribute("value", quantity);
		let allitems = localStorage.getItem("items")
		let items = JSON.parse(allitems)
		let newItems = []
		for (let item of items) {
			if (item.itemid == id) {
				item.quantity = quantity
			}
			newItems.push(item)
		}
		localStorage.setItem("items", JSON.stringify(newItems))
		totalPrice();
	}
}

function removeButton(event) {
	console.log("remove")
	let buttonelem = event.target
	let buttonid = buttonelem.getAttribute("id")

	let items = []
	let allitems = localStorage.getItem("items")
	let products = JSON.parse(allitems)

	for (let item of products) {
		if (item.itemid != buttonid) {
			items.push(item)
		}
	}

	localStorage.removeItem("items")
	localStorage.setItem("items", JSON.stringify(items))
	buttonelem.parentElement.remove();
	totalPrice();
}

function openNav() {
  document.getElementById("myNav").style.width = "100%";
}

function closeNav() {
  document.getElementById("myNav").style.width = "0%";
}
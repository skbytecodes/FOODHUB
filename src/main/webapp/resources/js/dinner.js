var cartitems = [];
async function fetchProducts() {
	let json
	let config = {
		method: "GET",
		header: {
			'content-type': 'application/json',
		},
	}
	try {
		let url = "http://localhost:8080/FOODHUB/dinnerproducts";
		let response = await fetch(url, config);
		if (response.ok) {
			json = await response.json();
		} else {
			console.log("could not fetch data");
		}
	}
	catch (e) {
		console.log("exception");
	}
	return json;
}


function createItemsOnPage(data) {
	for (let product of data) {
		let productContainer = document.getElementById('products-container');

		let productdiv = document.createElement('div');
		productdiv.setAttribute('class', 'product');


		let img = document.createElement('img');
		img.src = "data:image/jpeg;base64," + product.realImage;
		img.setAttribute('class', 'product-image');


		let pname = document.createElement('p');
		pname.innerText = "" + product.pname;
		pname.style.fontWeight = 700;
		pname.setAttribute("class","pname");

	
		let pdesc = document.createElement('p');
		pdesc.innerText = "" + product.pdesc;
		pdesc.style.fontWeight = 500;
		pdesc.style.marginTop = "4px";
		pdesc.setAttribute("class","pdesc");

		let rs = document.createElement("i");
		rs.setAttribute("class","fa fa-inr");
		rs.style.display = "inline-block";
		rs.style.marginLeft = "5px";
		rs.style.marginTop = "4px";
		
		let pcost = document.createElement('span');
		pcost.innerText = "" + product.pcost;
		pcost.style.fontWeight = 500;
		pcost.style.marginLeft = "1px";
		pcost.setAttribute("class","pcost");

		let addToCart = document.createElement('button');
		addToCart.setAttribute('class', 'cartbtn');
		addToCart.setAttribute('id', 'cart-btn');
		addToCart.style.display = "block";
		addToCart.setAttribute('productid', '' + product.pid);
		addToCart.addEventListener('click', buttonListener)
		addToCart.innerText = "add to cart";
		
		
		productdiv.appendChild(img);
		productdiv.appendChild(pname);
		productdiv.appendChild(pdesc);
		productdiv.appendChild(rs);
		productdiv.appendChild(pcost);
		productdiv.appendChild(addToCart);
		productContainer.appendChild(productdiv);
		
	}
}

function buttonListener(event) {
	let button = event.target;

	let itemid = button.getAttribute('productid');
	let costElem = button.previousSibling;
	let cost = costElem.innerText.replace("Cost:", '');
	let descelem = costElem.previousSibling.previousSibling;
	let desc = descelem.innerText.replace("Desc:", '');
	let nameelem = descelem.previousSibling;
	let name = nameelem.innerText.replace("Item:", '');
	let imgelem = nameelem.previousSibling;
	let imgsrc = imgelem.getAttribute("src");

	let item = {
		itemid: itemid,
		itemname: name,
		itemcost: cost,
		itemdesc: desc,
		itemimg: imgsrc,
		quantity: 1
	}
	
	const arr = JSON.parse(localStorage.getItem("items"));
	if(arr == null){
		cartitems.push(item);
		addtoLocalStorage();
		button.disabled = "true";
		button.style.opacity = .5;
	}
	else{
		cartitems = JSON.parse(localStorage.getItem("items"));
		let obj = cartitems.find(o => o.itemid === item.itemid);
		if(obj == undefined){
			console.log("items is not in cart")
			cartitems.push(item);
		}
		addtoLocalStorage();
		button.disabled = "true";
		button.style.opacity = .5;
	}
}

function addtoLocalStorage() {
	localStorage.setItem("items", JSON.stringify(cartitems));
}


async function main() {
	let data = await fetchProducts();
	createItemsOnPage(data);

}
main();

function openNav() {
  document.getElementById("myNav").style.width = "100%";
}

function closeNav() {
  document.getElementById("myNav").style.width = "0%";
}

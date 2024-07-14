 import {router} from "../main.js"
 
 export function getProductCard(title, price, id){
	const item = document.createElement("li");
	 item.classList.add("product-list-item");
	 
	 const productTitle = document.createElement("h2");
	 productTitle.classList.add("product-list-title");
	// productTitle.textContent = title;
	 
	 let productLink = document.createElement("a");
	 productLink.textContent = title;
	 productLink.href = "";
	 
	 productLink.addEventListener("click", function(event){
		 event.preventDefault();
		 router.navigate(`/product/${id}`);
	 });
	 
	 productTitle.append(productLink);
	 
	 const productPrice = document.createElement("strong");
	 productPrice.classList.add("product-list-price");
	 productPrice.textContent = `${price} eur`;
	 
	 const addBasket = document.createElement("button");
	 addBasket.classList.add("btn");
	 addBasket.textContent = "In basket";
	 
	 item.append(productTitle, productPrice, addBasket);
	 return item;
	  
 }
import { getMainTitle } from "../components/mainTitle.js"
import { getProductCard } from "../components/productCard.js"

export function getMainPage(){
	 const page = document.createElement("div");
	 page.classList.add("page", "main-page", "container");
	 
	 const mainTitle = getMainTitle("Main page");
	 
	 const list = document.createElement("ul");
	 list.classList.add("product-list", "list-reset");
	 
	 list.append(
		 getProductCard("product1", 100, 1),
		 getProductCard("product2", 200, 2),
		 getProductCard("product3", 300, 3)
	 );
	 
	 page.append(mainTitle, list);
	 
	 return page;
 }
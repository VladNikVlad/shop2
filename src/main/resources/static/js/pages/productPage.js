import { getMainTitle } from "../components/mainTitle.js"
import { getDesc } from "../components/deskContainer.js"

export function getProductPage(id){
	 const page = document.createElement("div");
	 page.classList.add("page", "product-page", "container");
	 
	 const mainTitle = getMainTitle("Product " + id);
	 const desc = getDesc("Page in developer");
	 page.append(mainTitle, desc);
	 return page;
 }
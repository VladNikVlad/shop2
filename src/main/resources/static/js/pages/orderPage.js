import { getMainTitle } from "../components/mainTitle.js"
import { getDesc } from "../components/deskContainer.js"

export function getOrderPage(){
	 const page = document.createElement("div");
	 page.classList.add("page", "order-page", "container");
	 
	 const mainTitle = getMainTitle("order");
	 const desc = getDesc("order here");
	 
	 page.append(mainTitle, desc);
	 return page;
 }
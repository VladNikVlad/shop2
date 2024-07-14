import { getMainTitle } from "../components/mainTitle.js"
import { getDesc } from "../components/deskContainer.js"
 import { router } from "../main.js"

export function getBasketPage(){
	 const page = document.createElement("div");
	 page.classList.add("page", "basket-page", "container");
	 
	 const mainTitle = getMainTitle("Basket");
	 const desc = getDesc("Page in developer");
	 
	 let orderLink = document.createElement("a");
	 orderLink.classList.add("btn");
	 orderLink.textContent = "order";
	 orderLink.href = "";
	 
	 orderLink.addEventListener("click", function(event){
		 event.preventDefault();
		 router.navigate('/order');
	 });
	 
	 page.append(mainTitle, desc, orderLink);
	 return page;
 }
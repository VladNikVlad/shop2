import { getMainTitle } from "../components/mainTitle.js"
import { getDesc } from "../components/deskContainer.js"

export function getNotFoundPage(){
	 const page = document.createElement("div");
	 page.classList.add("page", "notFound-page", "container");
	 
	 const mainTitle = getMainTitle("Not found");
	 const desc = getDesc("Page is not found!");
	 page.append(mainTitle, desc);
	 return page;
 }
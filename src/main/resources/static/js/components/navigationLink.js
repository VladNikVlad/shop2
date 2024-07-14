 import { router } from "../main.js"

export function getNavigationLink(path, title = ""){
	 let link = document.createElement("a");
	 link.href=path;
	 link.classList.add("btn");
	 link.textContent = title;
	// link1.setAttribute("data-nacvigo", true);
	 link.addEventListener("click", function(event){
		 event.preventDefault();
		 router.navigate(path);
	 });
	 return link;
 }
 /*import { router } from "../main.js"*/
 import { getNavigationLink } from "../components/navigationLink.js"
 import { getLogo } from "../components/logo.js"
 
 //Cerate header
 export function getHeader() {
	 
	 const header = document.createElement("header");
	 header.classList.add("header");
	 
	 const logo = getLogo();
	 
	 const container = document.createElement("div");
	 container.classList.add("container", "header__container");
	 
	 const nav = document.createElement("nav");
	 nav.classList.add("navigation");
	 
	 let link1 = getNavigationLink('/', "Main page");
	 let link2 = getNavigationLink('/catalog', "Catalog");
	 let link3 = getNavigationLink('/basket', "Basket");
	 
	 nav.append(link1, link2, link3);
	 container.append(logo, nav);
	 
	 header.append(container);

	 return header;
 }
 

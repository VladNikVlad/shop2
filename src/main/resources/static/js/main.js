 import { getHeader } from "./components/header.js"
 import { getPageContainer } from "./components/pageContainer.js"
 
 const app = document.getElementById("app"); 
 
 export const router = new Navigo('/');
  	
 const header = getHeader();
 const pageContainer = getPageContainer();
 
 router.on('/', async () => {
	 pageContainer.innerHTML = "";
 	const mainPageModul = await import("./pages/mainPage.js");
    const mainPage = mainPageModul.getMainPage();
    pageContainer.append(mainPage);
 });
 
 router.on('/catalog', async () => {
	 pageContainer.innerHTML = "";
 	const cotalogPageModul = await import("./pages/catalogPage.js");
	const cotalogPage = cotalogPageModul.getCatalogPage();
	pageContainer.append(cotalogPage);
 });
 
 router.on('/product/:id', async ({data}) => {
	pageContainer.innerHTML = "";
 	const productPageModul = await import("./pages/productPage.js");
	const productPage = productPageModul.getProductPage(data.id);
 	pageContainer.append(productPage);
 });
 
 router.on('/basket', async () => {
	 pageContainer.innerHTML = "";
 	const basketPageModul = await import("./pages/basketPage.js");
	const basketPage = basketPageModul.getBasketPage();
 	pageContainer.append(basketPage);
 });
 
 router.on('/order', async () => {
	 
	 if(true){
		 router.navigate('/');
		 return;
	 }
	 
	 pageContainer.innerHTML = "";
 	const orderPageModul = await import("./pages/orderPage.js");
	const orderPage = orderPageModul.getOrderPage();
 	pageContainer.append(orderPage);
 });
 
 router.notFound('/basket', async () => {
	 pageContainer.innerHTML = "";
 	const notFoundPageModul = await import("./pages/notFound.js");
	const notFoundPage = notFoundPageModul.getNotFoundPage();
 	pageContainer.append(notFoundPage);
 });
 
 
 router.resolve();	
 
 app.append(header, pageContainer);
 
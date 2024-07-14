//пис товару
 export function getDesc(text){
	 const desk = document.createElement("p");
	 desk.classList.add("desk");
	 desk.textContent = text;
	 return desk;
 }

import logoImg from "../img/001.png"

export function getLogo(){
	const logo = document.createElement("png");
	logo.classList.add("logo");
	logo.src = logoImg;
	
	return logo;
}
document.addEventListener("DOMContentLoaded", () => {
	const request = new XMLHttpRequest();
	request.onreadystatechange = () => {
		if (request.readyState == 4 && request.status == 200) {

			const imgTags = document.getElementsByClassName("images");
			const aTags = document.getElementsByClassName("clubIds");
			
			for (let i = 0; i < 4; i++) {
				console.log(request.responseXML.getElementsByTagName("clubId")[i].childNodes[0].nodeValue);
				imgTags[i].src += request.responseXML.getElementsByTagName("image")[i].childNodes[0].nodeValue;
				aTags[i].href += request.responseXML.getElementsByTagName("clubId")[i].childNodes[0].nodeValue;
			}
		}
	};
	request.open("GET", "http://localhost:8080/LinkedClubs/GetClubs", true);
	request.send();
});
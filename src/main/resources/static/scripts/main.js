var currentMerch = null;

function resetContents()  {
	//Resets all the contents to their default view
}

function setCurrentMerch(merchID) {
	currentMerch.merchID = parseInt(merchID);
	currentMerch.sizeFK = null;
	currentMerch.quantity = 0;
	showMerchInfo(merchID);
}

function setMerchSize(sizeFK,sizeTitle) {
	currentMerch.sizeFK = sizeFK;
	document.getElementById("sizeButton").innerHTML = "Размер: "+sizeTitle;
}

function incrementCurrentMerch() {
	currentMerch.quantity++;
	document.getElementById("quantityPicked").innerHTML = currentMerch.quantity;
}

function decrementCurrentMerch() {
	let val = parseInt(document.getElementById("quantityPicked").innerHTML);
	if (val>0) {
		currentMerch.quantity--;
		document.getElementById("quantityPicked").innerHTML = currentMerch.quantity;
	}
}

function addToCart() {
	/*Проверяет, залогинился ли юзверь (запросом на сервер, или проверкой кэша): 
	 * Если да - формирует запрос на внесение текущего айтема в корзину.
	 * Если нет - то отфутболивает на страничку регистрации (да-да, придётся поебаться со Spring Security)*/
}

function sendRequest(type,url,params,target) {
	const request = new XMLHttpRequest();
	request.open(type, (url+"?"+params), true);
	request.addEventListener("readystatechange", () => {
	    if(request.readyState === 4 && request.status === 200) {
	    	if (target==null)
	    		console.log(request.responseText);
	    	else {
	    		target.innerHTML=request.responseText;
	    	}
	    }
	    
	});
	request.send();
	return request;
}

function loadContent(page) {
	$('.merchCarousel').slick({
		infinite: false,
		slidesToShow: 3,
		slidesToScroll: 3,
		dots: true,
		arrows: false
	});
	let initMerchID;
	switch (page) {
		case "Casual":
			initMerchID=1;
			break;
		default: initMerchID=1;
			break;
	}
	currentMerch = {
			merchID: initMerchID,
			sizeFK: null,
			quantity: 0
		};
	showMerchInfo(initMerchID);
}

function showMerchInfo(merchID) {
	let merchInfo = document.getElementById("merchInfo");
	const request = sendRequest("POST","/merchInfo",("merchID="+merchID),merchInfo);
	console.log(request);
}
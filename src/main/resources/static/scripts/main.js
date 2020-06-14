
function resetContents()  {
	//Resets all the contents to their default view
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

	showMerchInfo(initMerchID);
}

function showMerchInfo(merchID) {
	let merchInfo = document.getElementById("merchInfo");
	//AJAX query: send the merch ID back to server, retrieves a view of merch info with all data set
	const request = sendRequest("POST","/merchInfo",("merchID="+merchID),merchInfo);
	console.log(request);
}
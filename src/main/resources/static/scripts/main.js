var currentMerch = null;
var sizeButton = document.getElementById("sizeButton");

/*$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});*/

function submitAuth(itemId) {
	let token = $("meta[name='_csrf']").attr("content"); 
	let header = $("meta[name='_csrf_header']").attr("content");
	const xhr = new XMLHttpRequest();
	xhr.open("POST", document.getElementById(itemId).action); 
	xhr.setRequestHeader(header, token);
	const data = new FormData(document.getElementById(itemId)); 
	xhr.send(data);
}

function resetContents()  {
	//Resets all the contents to their default view
}
function rejectItem(itemId) {
	let comment = prompt("Причина отказа: ");
	let delcontents=document.getElementById("delcontents");
	const request = sendRequest("POST",
			"/delivery_reject",
			("itemId="+itemId+"&comment="+comment),
			delcontents);
}

function acceptItem(itemId) {
	let delcontents=document.getElementById("delcontents");
	const request = sendRequest("POST",
			"/delivery_accept",
			("itemId="+itemId),
			delcontents);
}

function removeOrder(itemId) {
	let delcontents=document.getElementById("ordercontents");
	const request = sendRequest("POST",
			"/order_remove",
			("orderId="+itemId),
			delcontents);
}

function rateItem(itemId, mark) {
	let delcontents=document.getElementById("ordercontents");
	const request = sendRequest("POST",
			"/order_rate",
			("orderId="+itemId+"&mark="+mark),
			delcontents);
}

function setCurrentMerch(merchID) {
	currentMerch.merchID = parseInt(merchID);
	currentMerch.sizeFK = null;
	currentMerch.quantity = 0;
	showMerchInfo(merchID);
}

function canAddToCartCheck() {
	if (currentMerch.sizeFK!=null&&currentMerch.quantity>0)
		document.getElementById("cartButton").disabled=false;
}

function canNotAddToCartCheck() {
	if (currentMerch.sizeFK==null||currentMerch.quantity==0)
		document.getElementById("cartButton").disabled=true;
}

function setMerchSize(sizeFK,sizeTitle) {
	currentMerch.sizeFK = sizeFK;
	document.getElementById("incrementButton").disabled=false;
	document.getElementById("sizeButton").innerHTML = "Размер: "+sizeTitle;
	canAddToCartCheck();
}

function incrementCurrentMerch() {
	currentMerch.quantity++;
	document.getElementById("decrementButton").disabled=false;
	document.getElementById("quantityPicked").innerHTML = currentMerch.quantity;
	canAddToCartCheck();
}

function decrementCurrentMerch() {
	let val = currentMerch.quantity;
	if (val>0) {
		currentMerch.quantity--;
		document.getElementById("quantityPicked").innerHTML = currentMerch.quantity;
	}
	else document.getElementById("decrementButton").disabled=true
	canNotAddToCartCheck();
}

function addToCart() {
	const request = sendRequest("POST","/cart_add",("id="
			+currentMerch.merchID
			+"&amount="+currentMerch.quantity
			+"&size="+currentMerch.sizeFK),document.getElementById("cartResponse"));
}

function removeItem(itemId) {
	let cartcontents=document.getElementById("cartcontents");
	const request = sendRequest("POST",
			"/cart_remove",
			("itemId="+itemId),
			cartcontents);
}

function incrementItem(itemId) {
	let cartcontents=document.getElementById("cartcontents");
	const request = sendRequest("POST",
			"/cart_increment",
			("itemId="+itemId),
			cartcontents);
/*	if (response="+") {
		console.log("Success!");
		value = document.getElementById("quantityPicked").value;
		document.getElementById("quantityPicked").value = parseInt(value)+1;
	}
	else {
		console.log("Fail!");
		document.getElementById("incrementButton").setAttribute("disabled",true);
	} */
}

function decrementItem(itemId) {
	let cartcontents=document.getElementById("cartcontents");
	const request = sendRequest("POST",
			"/cart_decrement",
			("itemId="+itemId),
			cartcontents);
	/*if (response="+") {
		console.log("Success!");
		value = document.getElementById("quantityPicked").value;
		document.getElementById("quantityPicked").value = parseInt(value)-1;
	}
	else {
		console.log("Fail!");
		document.getElementById("decrementButton").setAttribute("disabled",true);
	}*/
}

function showPassChange() {
	document.getElementById("passChange").style.display = "block";
	document.getElementById("change").style.display = "none";
}

function hidePassChange() {
	document.getElementById("passChange").style.display = "none";
	document.getElementById("change").style.display = "block";
	document.getElementsByName("password")[0].value = "";
	document.getElementsByName("confirm")[0].value = "";
}

function sendRequest(type,url,params,target) {
	const request = new XMLHttpRequest();
	//let token = $("meta[name='_csrf']").attr("content"); 
	//let header = $("meta[name='_csrf_header']").attr("content");
	if (params==null)
		params="";
	request.open(type, (url+"?"+params), true);
	request.addEventListener("readystatechange", () => {
	    if(request.readyState === 4 && request.status === 200) {
	    	if (target!=null){
	    		target.innerHTML=request.responseText;
	    	}
	    	else console.log(request.responseText);
	    }
	    
	});
//	request.setRequestHeader(header, token);
	request.send();
	return request;
}

function sortCarousel(section, sort) {
	let mainContainer = document.getElementById("mainContainer");
	let carousel = document.createElement("div");
	const request = sendRequest("POST","/carousel",("section="+section+"&sort="+sort),carousel);
	mainContainer.replaceChild(carousel,mainContainer.children[2]);
	console.log(request);
}

function loadContent(page) {
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
			quantity: 0,
		};
	//showMerchInfo(initMerchID);
}

function showMerchInfo(merchID) {
	let merchInfo = document.getElementById("merchInfo");
	const request = sendRequest("POST","/merchInfo",("merchID="+merchID),merchInfo);
	console.log(request);
}

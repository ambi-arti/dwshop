function submitForm(itemId) {
	let admincontents=document.getElementById("admincontents");
//	let token = $("meta[name='_csrf']").attr("content"); 
//	let header = $("meta[name='_csrf_header']").attr("content");
	const xhr = new XMLHttpRequest();
	xhr.open("POST", document.getElementById(itemId).action); 
//	xhr.setRequestHeader(header, token);
	xhr.onload = function(event){ 
	    admincontents.innerHTML=event.target.response;
		//console.log(event.target.response);
	}; 
	const data = new FormData(document.getElementById(itemId)); 
	xhr.send(data);
}

function remove(itemType,itemId) {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/remove_"+itemType,
			("&itemId="+itemId),
			admincontents);
}

function sendQuery() {
	submitForm("query");
}

function edit(itemId,action) {
	let admincontents=document.getElementById("admincontents");
	let itemElems = document.getElementsByClassName(itemId);
	console.log(action);
	if (action=="EDIT") {
		for (let i=0;i<itemElems.length;i++)
			itemElems[i].removeAttribute("disabled");
		document.getElementById("editLink"+itemId).setAttribute("state","SAVE");
		document.getElementById("editLink"+itemId).innerHTML = "<b>[SAVE]</b>";
		console.log("Editiing!");
	}
	else {
		submitForm(itemId);	
		for (let i=0;i<itemElems.length;i++)
			itemElems[i].setAttribute("disabled","disabled");
		document.getElementById("editLink"+itemId).setAttribute("state","EDIT");
		document.getElementById("editLink"+itemId).innerHTML = "<b>[EDIT]</b>";
		console.log("Saving!");
	}	
}

function accounts() {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/accounts",null,
			admincontents);
}

function merch() {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/merch",null,
			admincontents);
}

function merchsize() {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/merchsize",null,
			admincontents);
}

function merchprops() {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/merchproperty",null,
			admincontents);
}

function merchcolours() {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/merchcolour",null,
			admincontents);
}

function merchdiscs() {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/merchdisc",null,
			admincontents);
}

function nativeQuery() {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/native",null,
			admincontents);
}

function newItem() {
		submitForm("new");	
		console.log("Saving!");	
}
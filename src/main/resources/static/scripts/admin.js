function submitForm(itemId) {
	let admincontents=document.getElementById("admincontents");
	const xhr = new XMLHttpRequest();
	xhr.open("POST", document.getElementById(itemId).action); 
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
			("itemId="+itemId),
			admincontents);
}

function edit(itemId,action) {
	let admincontents=document.getElementById("admincontents");
	let itemElems = document.getElementsByClassName(itemId);
	console.log(action);
	if (action=="EDIT") {
		for (let i=0;i<itemElems.length;i++)
			itemElems[i].removeAttribute("disabled");
		document.getElementById("editLink"+itemId).setAttribute("state","SAVE");
		document.getElementById("editLink"+itemId).innerHTML = "[SAVE]";
		console.log("Editiing!");
	}
	else {
		submitForm(itemId);	
		for (let i=0;i<itemElems.length;i++)
			itemElems[i].setAttribute("disabled","disabled");
		document.getElementById("editLink"+itemId).setAttribute("state","EDIT");
		document.getElementById("editLink"+itemId).innerHTML = "[EDIT]";
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

function newItem() {
		submitForm("new");	
		console.log("Saving!");	
}
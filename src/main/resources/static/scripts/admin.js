function submitForm(itemId,url) {
	const xhr = new XMLHttpRequest();
	xhr.open("POST", url); 
	xhr.onload = function(event){ 
	    admincontents.innerHTML=event.target.response;
	}; 
	const data = new FormData(document.getElementById(itemId)); 
	xhr.send(data);
}

function removeAccount(itemId) {
	let admincontents=document.getElementById("admincontents");
	const request = sendRequest("POST",
			"/adminium/remove_account",
			("itemId="+itemId),
			admincontents);
}

function editAccount(itemId,action) {
	let admincontents=document.getElementById("admincontents");
	let itemElems = document.getElementsByClassName(itemId);
	console.log(action);
	if (action=="EDIT") {
		for (let i=0;i<itemElems.length;i++)
			itemElems[i].removeAttribute("disabled");
		document.getElementById("editLink").setAttribute("text","SAVE");
		console.log("Editiing!");
	}
	else {
		for (let i=0;i<itemElems.length;i++)
			itemElems[i].setAttribute("disabled","disabled");
		submitForm(itemId,"/adminium/edit_account");	
		document.getElementById("editLink").setAttribute("text","EDIT");
		console.log("Saving!");
	}	
}
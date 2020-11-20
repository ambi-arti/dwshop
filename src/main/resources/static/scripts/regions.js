function getCityList(regionId) {
	let cityList=document.getElementById("cityList");
	const request = sendRequest("POST",
		"/getCities",
		("regionId="+regionId),
		cityList);
}
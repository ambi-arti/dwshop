
function resetContents()  {
	//Resets all the contents to their default view
}

function loadContent() {
	$('.merchCarousel').slick({
		infinite: false,
		slidesToShow: 3,
		slidesToScroll: 3,
		dots: true,
		arrows: false
	});
}
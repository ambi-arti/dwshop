function initCars() {
	$('.merchCarousel').slick({
		infinite: false,
		slidesToShow: 3,
		slidesToScroll: 3,
		dots: true,
		arrows: false
	});
	document.getElementsByClassName("merchCarousel")[0].style.display="block";
}

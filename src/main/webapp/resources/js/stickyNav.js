$(document).ready(function() {
	var navY = $('#mainNav').offset().top;
	 
	var stickyNav = function(){
		var scrollY = $(window).scrollTop();
			  
		if (scrollY > navY) { 
			$('#mainNav').addClass('sticky');
			$('#container').css('padding-top','50px');
		} else {
			$('#mainNav').removeClass('sticky');
			$('#container').css('padding-top','0px');
		}
	};
 
	stickyNav();
	 
	$(window).scroll(function() {
		stickyNav();
	});
});
//for click backTop button
$(function() {
	$('#BackTop').click(function() {
		$('html,body').animate({
			scrollTop: 0
		}, 333);
	});
	$(window).scroll(function() {
		if ($(this).scrollTop() > 300) {
			$('#BackTop').fadeIn(222);
		} else {
			$('#BackTop').stop().fadeOut(222);
		}
	}).scroll();
});

//for click about us
var dd = document.querySelector('.aboutus-section.spad')

function goTo() {
	dd.scrollIntoView()
}


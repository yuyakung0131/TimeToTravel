

$(document).ready(function() {
	var starMod = $('#starMod').val();
	console.log(starMod);  //0.5
	$(function() {
		$(".star5 .star-over").css("width", (starMod * 100) + '%');

		console.log((starMod * 100) + '%');  //50%
	});

	const stars = document.querySelector('.rating');
	const star = stars.querySelectorAll('.rating-item');
	stars.onclick = e => {
		const elClass = e.target.classList;
		if (!elClass.contains('active')) {
			star.forEach(
				item => item.classList.remove('active')
			);
			console.log(e.target.getAttribute("data-rate"));
			elClass.add('active');
		}

	};




});






$(document).ready(function() {
	
	
	var starMod = $('#starMod').val();
	console.log(starMod);  //0.5
	$(function() {
		$(".star5 .star-over").css("width", (starMod * 100) + '%');

		console.log((starMod * 100) + '%');  //50%
	});



$(".itrCommentBtn").click(function(){ 
	
		let itinerary_type_id = $(".itrCommentBtn").prev().text();
		let member_id =$("#pMemId").text();
		console.log(itinerary_type_id);
		console.log(member_id);
		
		$("#itrTypeIdComment").val(itinerary_type_id);
		$("#itrMemIdComment").val(member_id);
		});

});

//評論
$("#selType li").click(function() {
	$("#search-type").val($(this).attr("value"));


});

//收藏
function changeHeart(){
$.post(`${path}/front_end/itinerarytype/collection.do`,
   {
    action:'insert_delete_itineraryCollection',
    member_id :$('#mid').val(),
    itinerary_type_id :$('#itr').val()
   },
   function(data){
    console.log(data);
    $('#heart').css('color',data);
   });
 
}

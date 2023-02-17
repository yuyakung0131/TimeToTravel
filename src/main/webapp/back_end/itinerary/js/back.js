
$(document).ready(function() {
	$("#collapse").on("click", function() {
		$("#sidebar").toggleClass("active");
		$(".bi-arrow-left-square").toggleClass("bi-arrow-right-square");
	})
})

$("tr:odd").css("background-color", "rgb(220, 250, 250)");


// $(document).ready(function(){
//     $("li").on("click",function(){
//         $(this).addClass("active2");
//     })
// })


const tabContent1 = document.getElementById("itr-2-1");
const tabContent2 = document.getElementById("itr-2-2");
const tabContent3 = document.getElementById("itr-2-3");
const tabTitle1 = document.getElementById("itr-title-1");
const tabTitle2 = document.getElementById("itr-title-2");
const tabTitle3 = document.getElementById("itr-title-3");
const ermsg = document.getElementById("ermsg")
window.onload = function doermsg() {
	if (ermsg.style.color != null) {
		tabContent1.classList.remove("show");
		tabContent1.classList.remove("active");
		tabContent2.classList.add("show");
		tabContent2.classList.add("active");
		tabTitle1.classList.remove("active");
		tabTitle1.setAttribute("aria-selected", "false");
		tabTitle2.classList.add("active");
		tabTitle2.setAttribute("aria-selected", "true");
	};
};

$(document).ready(function() {
	$('#basicModal').modal('show');
	//	$("#basicModal").css("display","");
});


	$(document).ready(
			function() {
				$("#myInput").on(
						"keyup",
						function() {
							//抓搜尋關鍵字
							let value = $(this).val().toLowerCase();
							//抓table符合的
							$("#myBody tr").filter(
									function() {
										$(this).toggle(
												$(this).text().toLowerCase()
														.indexOf(value) > -1)
									});
						});

				$(function() {
					$("#myTable").tablesorter();
				});

			});

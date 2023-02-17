
$(document).ready(function(){
    $("#collapse").on("click",function(){
        $("#sidebar").toggleClass("active");
        $(".bi-arrow-left-square").toggleClass("bi-arrow-right-square");
    })
})

$( "tr:odd" ).css( "background-color", "rgb(220, 250, 250)" );


// $(document).ready(function(){
//     $("li").on("click",function(){
//         $(this).addClass("active2");
//     })
// })


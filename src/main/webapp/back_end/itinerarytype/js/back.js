
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

 $(document).ready(function(){
            $("#myInput").on("keyup", function(){
                //抓搜尋關鍵字
                let value = $(this).val().toLowerCase();
                //抓table符合
                $("#myBody tr").filter(function(){
                    $(this).toggle($(this).text().toLocaleLowerCase().indexOf(value)>-1)
                });
            });
       });


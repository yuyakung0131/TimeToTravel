$(document).ready(function () {
	$(".doAjaxForCollection.button.button-like").parent("form").on("submit",function(e){
    		e.preventDefault();
    	})
    	
    	
        $(".doAjaxForCollection.button.button-like").on("click",function () {
			let member_id=$("#member_id").val();
			if(member_id===null||member_id===''){
				let form=$("#reservationForm");
				form.submit();
				
			}
        	$(this).toggleClass('liked');
        if ($(this).children('.collection_btn').text() === "已收藏") {
            $(this).children('.collection_btn').text('收藏');
        } else {
            $(this).children('.collection_btn').text('已收藏');
        }
        
            $.ajax({
                type: "post",
                url: "roomCollection.do",
                data: $(".doAjaxForCollection.button.button-like").parent("form").serialize(),
                dataType: "Json"
            });
        });
	
    
});

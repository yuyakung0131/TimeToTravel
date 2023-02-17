<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Time To Travel">
<meta name="keywords" content="Sona, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Time To Travel</title>
<style type="text/css">

 .top-nav .col-lg-6 .tn-left .search-bar{
    width:200px;
    height:30px;
    vertical-align: middle;
    white-space: nowrap;
    position: relative;
    border-radius: 10px;
    font-size: 18px;
    padding: 6px;
    color: #111111;
    z-index: 3;
    border: 2px solid black;
}
 .search-btn{
    position: absolute;
    right: 20px;
    bottom: 15px;
    width: 22px;
    height: 22px;
    color: #111111;
    z-index: 2;
    cursor: pointer;
    border-radius: 50%;
    border: 1px solid white;
    background-color: #ffffff;
    opacity: 0.3;
}
.fas.fa-search{
    position: absolute;
    top:7px;
    right: 7px;
    width: 22px;
    height: 22px;
    color: #111111;
    z-index: 5;
    cursor: pointer;
}

.ui-helper-hidden-accessible{
 display: none;
/*  opacity: 0; */
}
.search-btn{
width:36px;
height:32px;
cursor: pointer;
position: absolute;
top: 16px;
right: 10px;

}

.top-nav .tn-left .searchicon {
    position: absolute;
    right: 6px;
    bottom: 15px;
    width: 22px;
    height: 22px;
    color: #111111;
    z-index: 2;
    cursor: pointer;
}
#ui-id-1{
    display: none;
    width: 180px;
    max-height: 100px; 
    position: absolute;
    color: #17223b;
    padding: 0;
    margin: 0;
    background-color: #f9f9f9;
    box-shadow: 0px 2px 3px 0px #ccc;
    border-radius: 6px;
    box-sizing: border-box;
    overflow: auto;
    z-index: 10;

}
#ui-id-1::-webkit-scrollbar {
	width: 7px;
}

#ui-id-1::-webkit-scrollbar-button {
	background: transparent;
	border-radius: 4px;
}

#ui-id-1::-webkit-scrollbar-track-piece {
	background: transparent;
}

#ui-id-1::-webkit-scrollbar-thumb {
	border-radius: 4px;
	background-color: rgba(0, 0, 0, 0.4);
	border: 1px solid slategrey;
}

#ui-id-1::-webkit-scrollbar-track {
	box-shadow: transparent;
}

.ui-menu-item a{
text-decoration: none;
color:#17223b;
}
.ui-menu-item a:hover{
color:#ffad60;
}
.ui-menu-item a:hover a:focus {
	text-decoration: none;
	outline: none;
	font-weight:700px;
}

.ui-menu-item{
    list-style: none;
    display: block;
    font-size: 16px;
    color: #17223b;
    font-weight: 500;
    padding: 14px 0 12px;
    margin-right: 64px;
    position: relative;
    margin: 0 10px;
    cursor: pointer;

}

.ui-menu-item:hover{
 background-color: #f4f0e6;
color:#17223b;

}

.ui-menu-item .searchone{
	 background-color: white;
	 color:black;
	 border:0px;
}
.ui-menu-item:hover .searchone{
	 background-color: #f4f0e6;
	 border:0px;
}



.ui-menu-item a:hover :link :visited{
color:#ffad60;
background-color: #f4f0e6;
border:3px solid  black;
}


</style>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/styleforheader_footerNEW.css"
	type="text/css">
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/member/css/home_main.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
	
<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-3.3.1.min.js"></script>
			<script
		src="<%=request.getContextPath()%>/front_end/member/js/jquery-ui.min.js"></script>

</head>
<body>
<!-- header start except for toolbarFixed  -->
<header class="header-section header-normal"> </header>
<div class="top-nav">
	<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<ul class="tn-left">
					<a href="<%=request.getContextPath()%>/front_end/member/home.jsp"><img
						class="tttlogo"
						src="<%=request.getContextPath()%>/front_end/member/img/ttt-LOGO.png"
						alt="" style="cursor: pointer;"></a>
					<div>Time to Travel</div>
					<div class="wrapforsearch">
  <div class="search-icon search-switch">
  <form METHOD="post" ACTION="" class="search-model-form">
    <input class="search-bar" type="text" name="search" id="search3" placeholder="探索..." autocomplete="off">
    <button class="search-btn">
      <i class="fas fa-search"></i>
    </button>
     </form>
  </div>
</div>
				</ul>
			</div>
			<div class="col-lg-6">
				<div class="tn-right">
					<div class="top-social">
						<a
							href="<%=request.getContextPath()%>/front_end/member/login.jsp"><img
							class="shoppingCartIMG"
							src="<%=request.getContextPath()%>/front_end/member/img/shopping-cart_1.png"
							alt=""> </a>
						<asp:Label ID="lblCartCount" runat="server"
							CssClass="badge badge-warning" ForeColor="White" />${cart.size() }
						<div class="dropdown" style="cursor: pointer;">
							<a class="dropbtn "> <img class="account"
								src="<%=request.getContextPath()%>/front_end/member/img/account.png"
								alt=""></a>
							<div class="dropdown-content">
								<a
									href="<%=request.getContextPath()%>/front_end/member/regist.jsp"><i
									class="fa-solid fa-right-to-bracket"></i>註冊/登入</a>
							</div>
						</div>
						<div class="language-option">
							<img class="flag"
								src="<%=request.getContextPath()%>/front_end/member/img/taiwan.png"
								alt=""> <span>TW </span>
						</div>
						<form Method="post" action="shoppinglist.do" id="shoppingListForm">
							<input type="hidden" name="action" value="checklogin">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="menu-item">
	<div class="container">
		<div class="row">
			<div class="col-lg-10">
				<div class="nav-menu">
					<nav class="mainmenu">
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/front_end/member/home.jsp">|&nbsp;&nbsp;首頁</a></li>
							<li><a href="<%=request.getContextPath()%>/front_end/roomtype/listAllRoomFirm.jsp">|&nbsp;&nbsp;住宿</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/ticket/browseTicket.jsp">|&nbsp;&nbsp;票券</a>
								<ul class="dropdown">
									<li><a
										href="<%=request.getContextPath()%>/front_end/ticket/browseTicketByType.jsp?tkt_type_id=2">交通</a>
									<li><a
										href="<%=request.getContextPath()%>/front_end/ticket/browseTicketByType.jsp?tkt_type_id=3">主題樂園</a>
									<li><a
										href="<%=request.getContextPath()%>/front_end/ticket/browseTicketByType.jsp?tkt_type_id=1">展覽</a>
									<li><a
										href="<%=request.getContextPath()%>/front_end/ticket/browseTicketByType.jsp?tkt_type_id=4">觀光景點</a>
								</ul></li>
							<li><a href="<%=request.getContextPath()%>/front_end/itinerarytype/itinerary_list.jsp">|&nbsp;&nbsp;行程</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/article/listAllArticle.jsp">|&nbsp;&nbsp;文章</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/firm/addFirm.jsp">|&nbsp;&nbsp;廠商</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/news/news_front_getAll.jsp">|&nbsp;&nbsp;最新消息</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/qa/NewFile.jsp">|&nbsp;&nbsp;常見問題</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Header End except for toolbarFixed -->
<script type="text/javascript">

$(document).ready(function() {

$.get("<%=request.getContextPath()%>/front_end/homesearch.do?action=getSearch_For_Display",{},function(data){
	var search = []
	var source = []
	var productId = null;
	var href = null;
	for (var i = 0 ; i < data.length;i++){
		if(data[i].firm_type == 1 ){
			
			productId = data[i].product;
			href="<%=request.getContextPath()%>/front_end/ticket/ticket.do?action=getTicketDetailed_For_Display&tkt_id=" +productId;
			console.log(productId);
		} else if(data[i].firm_type == 2){
			productId = data[i].product;
			href="<%=request.getContextPath()%>/front_end/roomtype/room.do?room_firm_id="+productId+"&action=roomType_view";
			console.log(productId);
		}else if(data[i].firm_type == 3){
			productId = data[i].product;
			href="<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id="+ productId +"&action=showDetail";
			console.log(productId);
		}
		console.log(href);
		source.push({
            href: href,
			
			label: data[i].title,
		});
	}
	$("#search3").autocomplete({
		minLength:1,
	    source :source,
	    create:function(){
	    	$(this).data('ui-autocomplete')._renderItem = function(ul,item){
	    		return $('<li>').append('<a class="searchone" href="' + item.href + '"> <span style="margin-left:10px">'+ item.label + '</span></a>').appendTo(ul);};

	}
	    
	})

},"json");
});
</script>


</body>

</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itinerarytype.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
List<ItineraryTypeVO> list = itinerarytypeSvc.getAll();
List<ItineraryTypeVO> newList = new ArrayList<ItineraryTypeVO>();
for (ItineraryTypeVO vo : list) { 
	if(vo.getItinerary_type_state() == 0) {
	newList.add(vo);
	}}

pageContext.setAttribute("newList", newList);
%>

<jsp:useBean id="itineraryclassSvc" scope="page"
	class="com.itineraryclass.model.ItineraryClassService" />
<jsp:useBean id="itineraryLocSvc" scope="page"
	class="com.itineraryloc.model.ItineraryLocService" />



<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Time To Travel">
<meta name="keywords" content="Sona, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Time To Travel</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/flaticon.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<link rel="stylesheet" href="css/styleforheader_footerNEW.css"
	type="text/css">
<!-- <script src="https://kit.fontawesome.com/b8c9d2d1df.js" crossorigin="anonymous"></script> -->


<!-- wang新增開始 -->

<script
	src='<%=request.getContextPath()%>/front_end/itinerarytype/js/index.global.js'></script>
<script
	src='<%=request.getContextPath()%>/front_end/itinerarytype/js/slider.js'></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/itr-bs.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/itinerarytype/css/slider.css"
	type="text/css" />

<!-- wang新增結束 -->

</head>

<body>
	<!-- Page Preloder Begin -->
	<jsp:include page="../preLoder.jsp"></jsp:include>
	<!-- Page Preloder End -->
	<!-- Offcanvas Menu Section Begin -->
	<c:choose>
		<c:when test="${memberVO.member_id == null}">
			<!-- offcanvasMenuSection_nonmember -->
			<jsp:include page="../offcanvasMenuSection_nonmember.jsp"></jsp:include>
		</c:when>
		<c:when test="${memberVO.member_id != null}">
			<!-- offcanvasMenuSection_member -->
			<jsp:include page="../offcanvasMenuSection_member.jsp"></jsp:include>
		</c:when>
	</c:choose>
	<!-- Offcanvas Menu Section End -->
	<div class="toolbarFixed">
		<!-- header Begin -->
		<c:choose>
			<c:when test="${memberVO.member_id == null}">
				<!-- header_nonmember -->
				<jsp:include page="../header_nonmemberforhomepage.jsp"></jsp:include>
			</c:when>
			<c:when test="${memberVO.member_id != null}">
				<!-- header_member -->
				<jsp:include page="../header_memberforhomepage.jsp"></jsp:include>
			</c:when>
		</c:choose>
		<!-- Header End -->
		<!-- 行程瀏覽開始 -->
		<section class="itrList">
			<div class="container">

				<div class="row"
					style="text-align:center; background:url(<%=request.getContextPath()%>/front_end/itinerarytype/img/line3.jpg);border-radius: 6px;background-repeat: no-repeat;background-size: cover; padding:50px">


					<div class="col-md-12">

						<h1
							style="color: #FFFF; font-size: 70px; font-weight: 900; text-shadow: 0.1em 0.1em 0.2em black">
							探索台灣之美</h1>
						<form style="" action="" class="search">
							<input id="tags" class="bar" placeholder="search">

							<button id="autoSearch" type="submit">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</form>
					</div>
				</div>

				<div class="row" style="margin: 0px auto">
					<div class="col-md-2">

						<button
							style="margin-top: 20px; border: 1px solid #d09f6f;; font-weight: 800; width: 100%; background-color: #DFA974"
							class="btn btn-primary" type="button" data-toggle="collapse"
							data-target="#collapseExample" aria-expanded="false"
							aria-controls="collapseExample">預算</button>


						<div class="collapse show" id="collapseExample">
							<div class="card card-body">

								<div class="">
									<div class="slider">
										<div class="range-slider w-100">
											<p>價格篩選</p>
											<span style="white-space: nowrap;" class="rangeValues"></span>
											<input name="range" class="itrMulSearch" value="0" min="0"
												max="30000" step="500" type="range"> <input
												name="range" class="itrMulSearch" value="30000" min="0"
												max="30000" step="500" type="range">
										</div>
									</div>
								</div>

							</div>
						</div>

						<button
							style="border: 1px solid #d09f6f; font-weight: 800; width: 100%; background-color: #DFA974"
							class="btn btn-primary" type="button" data-toggle="collapse"
							data-target="#ccccc" aria-expanded="false" aria-controls="ccccc">
							類別</button>

						<div class="collapse show" id="ccccc">
							<div class="card card-body">

								<select class="itrMulSearch" id="itrClass">
									<option value="0">請選擇</option>
									<c:forEach var="itineraryClassVO"
										items="${itineraryclassSvc.all}">
										<option value="${itineraryClassVO.itinerary_class_id}">${itineraryClassVO.itinerary_class_name}</option>
									</c:forEach>

								</select>

							</div>
						</div>




						<button
							style="border: 1px solid #d09f6f; font-weight: 800; width: 100%; background-color: #DFA974"
							class="btn btn-primary" type="button" data-toggle="collapse"
							data-target="#gggg" aria-expanded="false" aria-controls="gggg">
							縣市別</button>

						<div class="collapse show" id="gggg">
							<div class="card card-body" style="padding: 10px;">
								<div class="row">
									<c:forEach var="itinerarylocVO" items="${itineraryLocSvc.all}">
										<div class="col-6" style="width: 100%; padding: 8px;">
											<input class="itrMulSearch" type="checkbox" id="scales"
												value="${itinerarylocVO.itinerary_loc_id}" name="locId">
											<label for="locId">${itinerarylocVO.itinerary_loc_name}</label>
										</div>
									</c:forEach>

								</div>

							</div>
						</div>







					</div>


					<div class="col-md-10" id="change">
						<div class="row">
							<%@ include file="page1.file"%>
							<!-- 單個行程 -->
							<c:forEach var="itineraryTypeVO" items="${newList}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

								<%-- 							<p>${newList.size()}</p> --%>
								<div class="col-md-4 co3">
									<a
										href="<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id=${itineraryTypeVO.itinerary_type_id}&action=showDetail">
										<div class="outer">
											<div class="upper">
												<img class="w-100" style=""
													src="<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id=${itineraryTypeVO.itinerary_type_id}&offset=0">
											</div>

											<div class="lower" style="padding-left: 10px;">
												<div class="row" style="margin: 10px 0px;">
													<div class="col-md-8"
														style="padding-left: 0px; padding-right: 0px; display: flex;">
														<h5
															style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden;">
															<!-- <i class="fa fa-map-marker" aria-hidden="true"></i> -->
															${itineraryTypeVO.itinerary_title}
														</h5>
													</div>

													<c:set var="avgstar"
														value="${itineraryTypeVO.itinerary_total_score / itineraryTypeVO.itinerary_total_people}">
													</c:set>

													<div class="col-md-4"
														style="margin-top: 3px; padding-right: 3px; padding-left: 0px; display: flex;">
														<i class="fa fa-star" aria-hidden="true"></i>

														<c:if test="${itineraryTypeVO.itinerary_total_score > 0}">
															<span style="font-size: 17px; margin-top: 3px;color:black"> <fmt:formatNumber
																	type="number" value="${avgstar}" maxFractionDigits="1" />

																(${itineraryTypeVO.itinerary_total_people})
															</span>
														</c:if>


														<c:if test="${itineraryTypeVO.itinerary_total_score == 0}">
															<span style="font-size: 17px; margin-top: 3px;color:black">
																0(0) </span>
														</c:if>

													</div>

												</div>
												<div class="row" style="margin: 5px 0px;">

													<span style="color: #FFFF; font-weight: 700;" class="tag">${itineraryTypeVO.itineraryClassVO.itinerary_class_name}</span>
													<!-- 													<span class="tag">環島遊</span>  -->
													<!-- 													<span class="tag">環島遊</span>  -->
													<!-- 													<span class="tag">環島遊</span> -->
												</div>
												<div class="row">
													<div class="col-md-12 listprice text-right">
														<span style="color:black">TWD</span> <span>${itineraryTypeVO.itinerary_price}</span>
														<span style="color:black">起</span>

													</div>
												</div>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>

						<!-- 分頁 -->
						<%@ include file="page2.file"%>
						<!-- 分頁 -->


					</div>

				</div>
			</div>
		</section>

		<!-- 行程瀏覽結束 -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->




	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	<!-- wang新增開始 -->
	<script
		src="<%=request.getContextPath()%>/front_end/itinerarytype/js/itr-bs.js"></script>

	<script>
	$(document).ready(function() {
		$(".itrMulSearch").on("change", function() {
			console.log("有進來1");

			var locList = [];
			var allCheckboxes = document.getElementsByName("locId");
			for (var i = 0; i < allCheckboxes.length; i++) {
				if (allCheckboxes[i].checked)
					locList.push(allCheckboxes[i].value);
			}
			let rangeall = document.getElementsByName("range");
			let r1 = parseFloat(rangeall[0].value);
			let r2 = parseFloat(rangeall[1].value);
			if (r1 > r2) {
				let tmp = r2;
				r2 = r1;
				r1 = tmp;
			}
			var e = document.getElementById("itrClass");
			var itrClass = e.value;
			console.log(locList); //[]
			console.log(r1); // num
			console.log(r2); // num
			console.log(itrClass); //num
			
			$.ajax({
				   type: "POST",
				   dataType : 'json',
				   data: {json:JSON.stringify(locList),r1:r1,r2:r2,itrClass:itrClass},
				   url: "<%=request.getContextPath()%>/itinerarytype/ItineraryTypeServletForAjax.do?action=itrMulSearch" ,
				   success: function(data) {
						var list = '<div class="row">';
		                for (var i = 0; i < data.length; i++) {
		                
		                	
						var avgCommentItr = 0;
						if(data[i].itineraryVO.itinerary_total_score !== "0"){
						var num = (data[i].itineraryVO.itinerary_total_score / data[i].itineraryVO.itinerary_total_people);
						avgCommentItr =  num.toFixed(1);
						
						}	
						console.log("zzzz");
						console.log(data[i].itineraryVO.itinerary_total_score);
						if(data[i].itineraryVO.itinerary_total_score == "0"){
							avgCommentItr = "0";
							
						}
					
						 
						 console.log(avgCommentItr);
						 
		                    var lis = '<div class="col-md-4 co3">\
								<a href="<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id=' +data[i].itineraryVO.itinerary_type_id+ '&action=showDetail">\
									<div class="outer">\
										<div class="upper">\
											<img src="<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id='+data[i].itineraryVO.itinerary_type_id+'&offset=0">\
											<div class="innertext"></div>\
										</div>\
										<div class="lower" style="padding-left: 10px;">\
											<div class="row" style="margin: 10px 0px;">\
											<div class="col-md-8" style="padding-left: 0px;padding-right:0px;display: flex;">\
												<h5 style="white-space:nowrap;text-overflow:ellipsis;overflow:hidden;">\
														<!-- <i class="fa fa-map-marker" aria-hidden="true"></i> -->'
														+ data[i].itineraryVO.itinerary_title +
													'</h5>\
												</div>\
												<div class="col-md-4" style="margin-top: 3px; padding-right: 3px;padding-left:0px; display: flex;">\
													<i class="fa fa-star" aria-hidden="true"></i> \
		                                            <span style="font-size: 17px; margin-top: 3px;color:black">' + avgCommentItr + '('+ data[i].itineraryVO.itinerary_total_people + ')</span>\
												</div>\
											</div>\
											<div class="row" style="margin: 5px 0px;">\
								            	<span class="tag" style="color:#FFFF;font-weight:700;">' + data[i].className + '</span>\
											</div>\
											<div class="row">\
												<div class="col-md-12 listprice text-right">\
													<span style="color:black">TWD</span> <span>'+data[i].itineraryVO.itinerary_price+'</span>\
													<span style="color:black">起</span>\
												</div>\
											</div>\
										</div>\
									</div>\
								</a>\
							</div>';
		                   
		                    list += lis;
		                }
		                
		                list += '</div>'
		                
		                $("#change").html(list);
		                
		            }
				});
			
		})

		
		 $.get("<%=request.getContextPath()%>/itinerarytype/ItineraryTypeServletForAjax.do?&action=ajaxAutoComplete",{},function(data) {
	    		var Tags = [];
	    		var source = [];
            for (var i = 0; i < data.length; i++) {
            
//             	Tags.push(data[i].itinerary_title);
                source.push({
                	href: "<%=request.getContextPath()%>/front_end/itinerarytype/do?itinerary_type_id="+ data[i].itinerary_type_id +"&action=showDetail",
                    label: data[i].itinerary_title,
                    icon: "<%=request.getContextPath()%>/ItineraryImgReader.do?itinerary_type_id="  +  data[i].itinerary_type_id +  "&offset=0"

															});

												}

												$("#tags")
														.autocomplete(
																{

																	minLength : 1, //使用者最少輸入多少個字才啟動autocomplete
																	source : source, //將資訊丟進Source參數裡
																	create : function() {
																		$(this)
																				.data(
																						'ui-autocomplete')._renderItem = function(
																				ul,
																				item) {
																			return $(
																					'<li>')
																					.append(
																							'<a href="' + item.href + '"><img style="width:50px;height:40px;border-radius: 2px;" class="icon" src="' + item.icon + '" /> <span style="margin-left:10px">'
																									+ item.label
																									+ '</span></a>')
																					.appendTo(
																							ul);
																		};
																	}
																})

											}, "json");

						});
	</script>
	<!-- wang新增結束 -->
</body>

</html>
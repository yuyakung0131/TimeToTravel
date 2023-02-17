<%@page import="com.itinerary.model.ItineraryService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itinerarytype.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itinerary.model.*"%>
<%@ page import="com.itinerarycollection.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

ItineraryTypeVO itineraryTypeVO = (ItineraryTypeVO)request.getAttribute("itineraryTypeVO");	

ItineraryCollectionService itineraryCollectionSvc = new ItineraryCollectionService();
ItineraryCollectionVO itineraryCollectionVO = itineraryCollectionSvc.getOneItineraryCollection(memberVO.getMember_id(), itineraryTypeVO.getItinerary_type_id());
pageContext.setAttribute("itineraryCollectionVO", itineraryCollectionVO);
%> 

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

<!-- wang新增 -->
<!-- <link rel="stylesheet" href="https://kit.fontawesome.com/34fe54718c.css" crossorigin="anonymous"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"> -->
<script src='js/index.global.js'></script>
<link rel="stylesheet" href="css/itr-bs.css" type="text/css">

<!-- wang新增 -->



</head>

<body>

		<!-- 行程瀏覽頁 -->
		<!-- 路徑 -->
		<div class="path">
			<P>行程 > 輕旅行 > 宜蘭</P>
		</div>
		<!-- 封面圖 -->
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100" src="img/line1.jpg" alt="First slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="img/line2.jpg" alt="Second slide">
				</div>
				<!-- <div class="carousel-item">
                  <img class="d-block w-100" src="..." alt="Third slide">
              </div> -->
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<!-- 日曆 -->

		<div class="container ">
			<div class="row itrtitle shadow p-3 mb-5 bg-white rounded ">

				<div class="col-md-10">
					<h1>
						<i class="fa fa-caret-right" aria-hidden="true"></i>
						${itineraryTypeVO.itinerary_title}
					</h1>
				</div>
				<div class="col-md-2">
				<form method="post"  action="<%=request.getContextPath() %>/front_end/itinerarytype/collection.do" >
				<button type="button"  onclick="changeHeart()">
					<c:if test="${itineraryCollectionVO==null}">
					<i style="font-size: 30px; color: gary;" id="heart" class="fa fa-heart" aria-hidden="true"></i>
					</c:if>
					<c:if test="${itineraryCollectionVO!=null}">
					<i style="font-size: 30px;color: red;" id="heart" class="fa fa-heart" aria-hidden="true"></i>
					</c:if>
				</button>		
						<input type="hidden" name="member_id" value="${memberVO.member_id }" id="mid">
						<input type="hidden" name="itinerary_type_id" value="${itineraryTypeVO.itinerary_type_id }" id="itr">
						<input type="hidden" name="action" value="insert_delete_itineraryCollection">
				</form>
				</div>
				<div class="col-md-12" style="margin: 10px;">
					<h1>報名表單</h1>
				</div>
				<div class="col-md-10 offset-md-1" style="margin: auto;">
					<table table border=1 rules="rows" class="table ">
						<thead>

							<tr>
								<th nowrap="nowrap">場次</th>
								<th nowrap="nowrap">行程開始日</th>
								<th nowrap="nowrap">行程結束日</th>
								<th nowrap="nowrap">已報名人數</th>
								<th nowrap="nowrap">價格</th>
								<th nowrap="nowrap">最少人數</th>
								<th nowrap="nowrap">最多人數</th>
								<th nowrap="nowrap">報名開始日</th>
								<th nowrap="nowrap">報名結束日</th>
								<th nowrap="nowrap">狀態</th>
								<th nowrap="nowrap">報名</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="itineraryVO" items="${fiteritrList}"
								varStatus="s">
								<tr>
									<td nowrap="nowrap">${s.index+1}</td>
									<td nowrap="nowrap">${itineraryVO.itinerary_start}</td>
									<td nowrap="nowrap">${itineraryVO.itinerary_end}</td>
									<td nowrap="nowrap">${itineraryVO.itinerary_now}</td>
									<td nowrap="nowrap">${itineraryVO.itinerary_price}</td>
									<td nowrap="nowrap">${itineraryVO.itinerary_min}</td>
									<td nowrap="nowrap">${itineraryVO.itinerary_max}</td>
									<td nowrap="nowrap">${itineraryVO.entered_start}</td>
									<td nowrap="nowrap">${itineraryVO.entered_end}</td>
									<c:if test="${itineraryVO.itinerary_state == 0}">
										<td>正常</td>
									</c:if>
									<c:if test="${itineraryVO.itinerary_state == 1}">
										<td>額滿</td>
									</c:if>
									<c:if test="${itineraryVO.itinerary_state == 2}">
										<td>取消</td>
									</c:if>
									<c:if test="${itineraryVO.itinerary_state == 3}">
										<td>未開放報名</td>
									</c:if>
									<td nowrap="nowrap"><a href="<%=request.getContextPath() %>/front_end/itinerarytype/order.do?itinerary_id=${itineraryVO.itinerary_id}&action=orderDetail">
											<c:if test="${itineraryVO.itinerary_now >= itineraryVO.itinerary_max}">
  												<button type="button" class="btn btn-warning btn-sm" disabled="disabled">已額滿</button>
											</c:if>
											<c:if test="${itineraryVO.itinerary_now < itineraryVO.itinerary_max}">
  												<button type="button" class="btn btn-warning btn-sm" >報名</button>
											</c:if>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>


				</div>
				<div class="col-md-8 offset-md-2">
					<div class="col-md-10 offset-md-1">
						<h1>
							<i class="fa fa-map-marker" aria-hidden="true"></i> 行程資訊
						</h1>
					</div>


					<p>${itineraryTypeVO.itinerary_info}</p>

					<div class="col-md-10 offset-md-1">



						<h1>
							<i class="fa fa-info-circle" aria-hidden="true"></i> 其他說明
						</h1>
					</div>
					<p>${itineraryTypeVO.itinerary_other}</p>





				</div>
			</div>
		</div>

		<!-- <section id="itr-content">
      <div class="container">
        <div class="row">
          <div class="col-md-10 offset-md-1">
            <p>
              Lorem ipsum, dolor sit amet consectetur adipisicing elit. Consequuntur, similique exercitationem dolor ex
              expedita ipsa at inventore sed eveniet totam facilis ab vero quaerat impedit quibusdam laboriosam
              aspernatur tempore temporibus.
            </p>
            <p>
              Lorem ipsum, dolor sit amet consectetur adipisicing elit. Consequuntur, similique exercitationem dolor ex
              expedita ipsa at inventore sed eveniet totam facilis ab vero quaerat impedit quibusdam laboriosam
              aspernatur tempore temporibus.
            </p>
            <p>
              Lorem ipsum, dolor sit amet consectetur adipisicing elit. Consequuntur, similique exercitationem dolor ex
              expedita ipsa at inventore sed eveniet totam facilis ab vero quaerat impedit quibusdam laboriosam
              aspernatur tempore temporibus.
            </p>
          </div>
        </div>
      </div> -->

<!-- 		</section> -->
		<!-- 評論 -->
		<section id="itr-comment">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<h3>行程評論內容</h3>
						<div class="row">
							<div class="col-md-2 offset-md-1 total-star">
								<p>
									<!-- 								奇怪的計算方法 -->
									<c:if test="${(itrCommentList).size() > 0}">
										<c:set var="avgStar" value="0">
										</c:set>

										<c:forEach var="itineraryCommentVO" items="${itrCommentList}"
											begin="0" end="${(itrCommentList).size()}" step="1">

											<c:set var="avgStar"
												value="${avgStar+itineraryCommentVO.itinerary_comment_star}">

											</c:set>

										</c:forEach>
										<fmt:formatNumber type="number"
											value="${avgStar/(itrCommentList).size()}"
											maxFractionDigits="1" />

									</c:if>
									<c:if test="${empty itrCommentList}">
									0
									</c:if>
								</p>
							</div>
							<div class="col-md-9">

								<c:forEach begin="1" end="${avgStar/(itrCommentList).size()}"
									step="1">
									<div class="star2">
										<span class="star star-under fa fa-star"></span> <span
											class="star star-over fa fa-star"></span>
									</div>
								</c:forEach>

								<input type="hidden"
									value="${avgStar/(itrCommentList).size() % 1}" id="starMod" />

								<%-- 								<c:if test="${avgStar/(itrCommentList).size() % 1 != 0}"> --%>
								<c:if test="${avgStar/(itrCommentList).size() % 1 > 0}">
									<div class="star5">
										<span class="star star-under fa fa-star"></span> <span
											class="star star-over fa fa-star"></span>
									</div>
								</c:if>

								<c:if test="${empty avgStar}">
									<c:forEach begin="1" end="4" step="1">
										<div class="star5">
											<span class="star star-under fa fa-star"></span> <span
												class="star star-over fa fa-star"></span>
										</div>
									</c:forEach>
								</c:if>
								<c:if test="${5 - avgStar/(itrCommentList).size() >= 1}">
									<c:forEach begin="1"
										end="${5 - avgStar/(itrCommentList).size()}" step="1">

										<div class="star3">
											<span class="star star-under fa fa-star"></span> <span
												class="star star-over fa fa-star"></span>
										</div>
									</c:forEach>
								</c:if>
								<p>
									<!-- 								奇怪的計算人數方法 -->
									<c:if test="${(itrCommentList).size() >= 0}">
									
										${(itrCommentList).size()}
								</c:if>
									則評論
								</p>
							</div>
						</div>

						<hr>
						<%-- 					<jsp:useBean id="memberSvc" scope="page" --%>
						<%-- 					class="com.member.model.MemberService" /> --%>

						<!-- 單個評論 -->
						<c:forEach var="itineraryCommentVO" items="${itrCommentList}"
							varStatus="s">
							<div class="row">
								<div class="col-md-4 text-center">
									<!-- 會員照片+id -->
									<img src="img/1593518601_373107.jpg" alt="">
									<h5>${itineraryCommentVO.memberVO.member_name}</h5>
									<%-- 									<h5>${itineraryCommentVO.member_id}</h5> --%>
								</div>

								<div class="col-md-8">
									<c:forEach begin="1"
										end="${itineraryCommentVO.itinerary_comment_star}" step="1">
										<div class="star4">
											<span class="star star-under fa fa-star"></span> <span
												class="star star-over fa fa-star"></span>
										</div>
									</c:forEach>

									<p>${itineraryCommentVO.itinerary_comment_post}</p>
									<p>
										<fmt:formatDate
											value="${itineraryCommentVO.itinerary_comment_time}"
											pattern="yyyy-MM-dd" />
									</p>

								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>

		<!-- Footer Section Begin -->
		<footer class="footer-section">
			<div class="footer-sectioncontainer">
				<div class="container">
					<div class="footer-text">
						<div class="row">
							<div class="col-lg-4">
								<div class="ft-about">
									<div class="social">
										<h6>認識 Time to Travel</h6>
										<div class="footeritem">
											<a href="./aboutus.html">關於我們</a> <a href="./contactus.html">聯繫我們</a>
											<a href="./QA.html">常見問題</a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-3 offset-lg-1">
								<div class="ft-service">
									<h6>Time to Travel 服務</h6>
									<div class="footerservice">
										<a href="./rooms.html">|&nbsp;&nbsp;住宿</a> <a
											href="./ticket.html">|&nbsp;&nbsp;票券</a> <a
											href="./itinerary.html">|&nbsp;&nbsp;行程</a> <a
											href="./article.html">|&nbsp;&nbsp;文章</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3 offset-lg-1">
								<div class="ft-newslatter">
									<h6>取得最新資訊</h6>

									<form action="#" class="fn-form">
										<input type="text" placeholder="Email">
										<button type="submit">
											<i class="fa fa-send"></i>
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="copyright-option">
						<div class="container">
							<div class="row">
								<div class="col-lg-7">
									<ul>

									</ul>
								</div>
								<div class="col-lg-5">
									<div class="co-text">
										<p>
											<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
											Copyright &copy;
											<script>
												document.write(new Date()
														.getFullYear());
											</script>
											Time To Travel All rights reserved. <br> | This is made
											with <i class="fa fa-heart" aria-hidden="true"></i> by <a
												href="#" target="_blank">Tibame CGA105G3</a>
											<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<!-- Footer Section End -->

	<!-- Search model Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">
				<i class="icon_close"></i>
			</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
	<!-- Search model end -->

	<!-- Js Plugins -->
	<script>
		const path = '<%=request.getContextPath()%>';
	</script>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	<!-- wang新增 -->
	<script src="js/itr-bs.js"></script>
</body>

</html>
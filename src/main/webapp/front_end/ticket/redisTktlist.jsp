<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="redis.clients.jedis.Jedis"%>
<%@ page import="redis.clients.jedis.JedisPool"%>
<%@ page import="redis.clients.jedis.JedisPoolConfig"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ticket.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>

<%
MemberVO member = (MemberVO) session.getAttribute("memberVO");
pageContext.setAttribute("member", member);
//
Jedis jedis = new Jedis("localhost", 6379);
String member_id = (String) request.getAttribute("member_id");
String memberFav_list;
// System.out.println(member_id);
// System.out.println(jedis.hmget("memberlist", member_id));

//
List<String> xx = jedis.hmget("memberlist", member_id);
// System.out.println(xx.get(0));

// System.out.println(xx.size());
String[] aa = null;
aa = xx.get(0).split(",");
// System.out.println(aa.length);
// System.out.println(aa[0]);
// System.out.println(aa[1]);
%>



<head>
<meta charset="BIG5">
<title>Time To Travel</title>
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/elegant-icons.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/flaticon.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/nice-select.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/magnific-popup.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/slicknav.min.css"
	type="text/css">
<!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
<!-- <link rel="stylesheet" href="css/styleforheader_footer..css" type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/ticket/CSS/styleforheader_footerNEW.css"
	type="text/css">
<script src="https://kit.fontawesome.com/b8c9d2d1df.js"
	crossorigin="anonymous"></script>
<style>
img.tttlogo {
	width: 60px;
	height: 60px;
}

.useriocn {
	border: 1px solid red;
}

.img-cart {
	display: block;
	max-width: 200px;
	height: auto;
	margin-left: auto;
	margin-right: auto;
}

table tr td {
	border: 1px solid #FFFFFF;
}

table tr th {
	background: #eee;
}

.panel-shadow {
	box-shadow: rgba(0, 0, 0, 0.3) 7px 7px 7px;
}

#lblCartCount {
	font-size: 14px;
	/*      background: #ff0000;   */
	color: red;
	padding: 0 5px;
	/*     vertical-align: top; */
}
</style>
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
				<jsp:include page="../header_nonmember.jsp"></jsp:include>
			</c:when>
			<c:when test="${memberVO.member_id != null}">
				<!-- header_member -->
				<jsp:include page="../header_member.jsp"></jsp:include>
			</c:when>
		</c:choose>
		<!-- Header End -->

		<!-- redisTklist Section Begin -->
		<link rel="stylesheet" type="text/css"
			href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
		<div class="container bootstrap snippets bootdey">
			<div class="col-md-12 col-sm-12 content">
				<div class="row">
					<div class="col-md-12">
						<ol class="breadcrumb">
							<li><a
								href="<%=request.getContextPath()%>/front_end/ticket/browseTicket.jsp">票券首頁</a></li>
							<li class="active">/會員收藏清單</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-info panel-shadow">
							<div class="panel-heading">
								<h3>
									<img class="img-circle img-thumbnail"
										src="<%=request.getContextPath()%>/front_end/member/member.do?action=getImg&member_id=${member.getMember_id().toString()}"
										width=120px height=120px>
									<%=member.getMember_name()%>
								</h3>
							</div>
							<br>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>產品名稱</th>
												<th>產品圖片</th>
												<th>產品種類</th>
												<th>產品發行公司</th>
												<th>產品描述</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<%
											if ((xx != null) && (xx.size() > 0) && (xx.get(0).trim()).length() != 0) {
											%>
											<%
											Set<Ticket> list = new HashSet();
											TicketService ticketSvc = new TicketService();
											for (int i = 0; i < aa.length; i++) {
												Ticket ticket = ticketSvc.getOneTicket(Integer.valueOf(aa[i]));
												list.add(ticket);

											}
											pageContext.setAttribute("list", list);
											jedis.close();
											%>
											<jsp:useBean id="tktimgSvc2" scope="session"
												class="com.tktimg.model.TktImgService" />
											<c:forEach var="tkt" items="${list}" begin="0"
												end="${list.size()-1}">
												<tr id="item1">
													<%-- 												<%=list.size()%> --%>
													<td><strong>${tkt.tkt_name}</strong>
														<p>${tkt.tkt_amount}張</p></td>
													<td><img
														src="<%=request.getContextPath()%>/PicServlet?tkt_img_id=${tktimgSvc2.findByTktId(tkt.getTkt_id()).get(0).tkt_img_id}"
														class="img-cart" width=300px height=250px></td>
													<td>${tkt.getTicketType().tkt_type_name }</td>
													<td>${tkt.getFirm().firm_name }</td>
													<td>${tkt.instruction}</td>
													<td>
														<form method="post" action="list.do"
															style="margin-bottom: 0px;">
															<input type="submit" value="刪除票券"> <input
																type="hidden" name="tkt_id" value="${tkt.tkt_id}">
															<input type="hidden" name="action" value="delete">
														</form>
													</td>
												</tr>
												<tr>
													<td colspan="6">&nbsp;</td>
												</tr>
											</c:forEach>

											<%
											}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<a
							href="<%=request.getContextPath()%>/front_end/member/Favlist.jsp"
							class="btn btn-success"><span
							class="glyphicon glyphicon-arrow-left"></span>&nbsp;至會員中心查看</a> <a
							href="<%=request.getContextPath()%>/front_end/ticket/browseTicket.jsp"
							class="btn btn-primary pull-right">回商品瀏覽頁面<span
							class="glyphicon glyphicon-chevron-right"></span></a> <br> <br>
						<br>
					</div>
				</div>
			</div>
		</div>
		<!-- redisTklist Section End -->
		<!-- Footer Section Begin -->
		<jsp:include page="../footer.jsp"></jsp:include>
		<!-- Footer Section End -->
	</div>
	<!-- Search model Begin -->
<%-- 	<jsp:include page="../search.jsp"></jsp:include> --%>
	<!-- Search model End -->
	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/jquery.slicknav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/ticket/JS/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/ticket/JS/main.js"></script>
</body>

</html>
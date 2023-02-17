<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.qa.model.*"%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/qa/css/bootstrap.min.css"
	type="text/css">
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/qa/css/back.css"
	type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>


	<div class="container-all">
		<!-- ���� -->
		<div class="container-left">
			<!-- �����C -->
			<jsp:include page="../sidebar.jsp"></jsp:include>
		</div>
		<!-- �k�� -->
		<div class="container-right">
			<!-- �k�W -->
			<jsp:include page="../header.jsp"></jsp:include>
			<!-- �k�U -->
			<section id="first">
				<div class="container right-down">
					<!-- �q1 -->
					<div class="row">
						<div class="col-md-12 h1">
							<h1>�`�����D�޲z</h1>
						</div>

					</div>
					<!-- =======================�H�U�϶��i�ۭq======================= -->
					<!-- �������ҡA�i�H�s�W�����A�Y���n�����i�H����R�� -->
					<div class="nav nav-tabs">
						<!-- ����1 -->

						<button class="nav-link active" data-bs-toggle="tab"
							data-bs-target="#tab-1" aria-selected="true">�`�����D��Ƭd��</button>
						<button class="nav-link" data-bs-toggle="tab"
							data-bs-target="#tab-2" aria-selected="false">�`�����D��ƺ޲z</button>

					</div>
					<!--  �������e�A�Y���n�����i�H����R�� -->
					<div class="tab-content">

						<br> <br>
						<div class="tab-pane show active" id="tab-1">

							<div>
								<div class="col-md-5">
									<a
										href="<%=request.getContextPath()%>/back_end/qa/listAllQa.jsp">�C�X</a>�Ҧ����
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-6">
									<form method="post" action="qa.do">
										<input type="text" placeholder="��J���D�s��" name="qa_id">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="�e�X">
									</form>
								</div>
								<br>

								<jsp:useBean id="qaclassSvc" scope="page"
									class="com.qaclass.model.QaclassService" />
								<div class="col-md-6">
									<form method="post" action="qa.do">
										��ܱ`�����D����:<select size=1 name="qa_class_id">
											<c:forEach var="qaclassId" items="${qaclassSvc.all}">
												<option value="${qaclassId.qa_class_id}">${qaclassId.qa_class_name}
											</c:forEach>
										</select> <input type="hidden" name="action"
											value="getType_For_Display"> <input type="submit"
											value="�e�X">
									</form>
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-6">
									<form method="post" action="qa.do">
										�`�����D���A <select name=qa_state>
											<option value="0">�U�[</option>
											<option value="1">�W�[</option>
										</select> <input type="hidden" name="action"
											value="getState_For_Display"> <input type="submit"
											value="�e�X">
									</form>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="tab-2">
							<div>
								<div class="col-md-5">
									<a
										href="<%=request.getContextPath()%>/back_end/qa/addOneQa.jsp">�s�W</a>�@���`�����D���
								</div>
							</div>
						</div>
						<!-- =======================�H�W�϶��i�ۭq======================= -->
					</div>
				</div>
			</section>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/qa/js/back.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/qa/js/bootstrap.min.js"></script>
</body>
</body>
</html>
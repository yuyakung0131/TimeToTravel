<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.qa.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.firm.model.*"%>

<%
QaVO qavo = (QaVO) request.getAttribute("qa");
%>

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
							data-bs-target="#tab-1" aria-selected="true"></button>
						<table id="table-1">
							<tr>
								<td>
									<h3>�`�����D��Ʒs�W</h3>
								</td>
								<td>
									<h4>
										<a
											href="<%=request.getContextPath()%>/back_end/qa/select_qa.jsp">�^����</a>
									</h4>
								</td>
							</tr>
						</table>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">�Эץ��H�U���~:</font>
							<c:forEach var="message" items="${errorMsgs}">
								<font style="color: red">${message}</font>
							</c:forEach>
						</c:if>

<!-- 						<h3>��ƭק�:</h3> -->
						<FORM METHOD="post" ACTION="qa.do" name="form1">
							<table>
								<tr>
									<td>�`�����D�s��:</td>
									<td><%=qavo.getQa_no()%></td>
								</tr>


								<tr>
									<td>���D��ܶ���:</td>
									<td><input type="TEXT" name="qa_show_no" size="45"
										value="<%=qavo.getQa_show_no()%>" /></td>
								</tr>
								<jsp:useBean id="qaclassSvc" scope="page"
									class="com.qaclass.model.QaclassService" />
								<tr>
									<td>�`�����D����:<font color=red><b>*</b></font></td>
									<td><select size="1" name="qaclass_id">
											<c:forEach var="qaclass" items="${qaclassSvc.all}">
												<option value="${qaclass.qa_class_id}"
													${(qavo.qa_class_id==qaclass.qa_class_id)? 'selected':' ' }>${qaclass.qa_class_name}
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>�`�����D���e:</td>
									<td><input type="TEXT" name="question" size="45"
										value="<%=qavo.getQuestion()%>" /></td>
								</tr>
								<tr>
									<td>�`�����D�ѵ�:</td>
									<td><input type="TEXT" name="answer" size="45"
										value="<%=qavo.getAnswer()%>" /></td>
								</tr>

								<tr>
									<td>�`�����D���A:</td>
									<td><input type="TEXT" name="qa_state" size="45"
										value="<%=qavo.getQa_state()%>" /></td>
								</tr>


							</table>
							<input type="hidden" name="action" value="update"><input
								type="hidden" name="qa_id" value="<%=qavo.getQa_no()%>">
							<input type="submit" value="�e�X�s�W">
						</FORM>
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

	<br>



</body>
</html>
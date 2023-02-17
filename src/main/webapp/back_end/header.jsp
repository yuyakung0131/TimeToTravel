<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

			<div class="header">
				<div class="col-md-12">
					<div class="float-right">
						<span>${sessionScope.empVO.emp_name} (員工編號:${sessionScope.empVO.emp_id})</span>
						<a href="<%=request.getContextPath()%>/front_end/emp/emp.do?action=logout" id="logout">登出<i
							class="bi bi-box-arrow-right"></i></a>
					</div>
				</div>
			</div>
package com.itineraryclass.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itineraryclass.model.ItineraryClassService;
import com.itineraryclass.model.ItineraryClassVO;
import com.itinerarytype.model.ItineraryTypeService;
import com.itinerarytype.model.ItineraryTypeVO;

public class ItineraryClassServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String itinerary_class_name = req.getParameter("itinerary_class_name");
			String itinerary_class_nameReg = "[\\u4e00-\\u9fa5]+";
			if (itinerary_class_name == null || itinerary_class_name.trim().length() == 0) {
				errorMsgs.add("行程類別: 請勿空白");
			} else if (!itinerary_class_name.trim().matches(itinerary_class_nameReg)) {
				errorMsgs.add("行程類別: 只能是中文");
			}

			ItineraryClassVO itineraryClassVO = new ItineraryClassVO();
			itineraryClassVO.setItinerary_class_name(itinerary_class_name);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("itineraryClassVO", itineraryClassVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ItineraryClassService itineraryClassSvc = new ItineraryClassService();
			itineraryClassVO = itineraryClassSvc.addItineraryClass(itinerary_class_name);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/itinerarytype/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs2 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs2);
			
				/***************************1.接收請求參數****************************************/
				Integer itinerary_class_id = Integer.valueOf(req.getParameter("itinerary_class_id"));
				
				/***************************2.開始查詢資料****************************************/
				ItineraryClassService itineraryclassSvc = new ItineraryClassService();
				ItineraryClassVO itineraryClassVO = itineraryclassSvc.getOneItineraryClass(itinerary_class_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("itineraryClassVO", itineraryClassVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/itinerarytype/updateItineraryClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer itinerary_class_id = Integer.valueOf(req.getParameter("itinerary_class_id").trim());

			String itinerary_class_name = req.getParameter("itinerary_class_name");
			String itinerary_class_nameReg = "[\\u4e00-\\u9fa5]+";
			if (itinerary_class_name == null || itinerary_class_name.trim().length() == 0) {
				errorMsgs.add("行程類別: 請勿空白");
			} else if (!itinerary_class_name.trim().matches(itinerary_class_nameReg)) {
				errorMsgs.add("行程類別: 只能是中文");
			}

			ItineraryClassVO itineraryClassVO = new ItineraryClassVO();
			itineraryClassVO.setItinerary_class_id(itinerary_class_id);
			itineraryClassVO.setItinerary_class_name(itinerary_class_name);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("itineraryClassVO", itineraryClassVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/itinerarytype/updateItineraryClass.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			ItineraryClassService itineraryclassSvc = new ItineraryClassService();
			itineraryClassVO = itineraryclassSvc.updateItineraryClass(itinerary_class_id, itinerary_class_name);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryClassVO", itineraryClassVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/itinerarytype/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}
		
	}
}

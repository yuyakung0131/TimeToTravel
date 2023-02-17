package com.itinerary.controller;

import java.awt.ItemSelectable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itinerarytype.model.ItineraryTypeService;
import com.itinerarytype.model.ItineraryTypeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonStreamParser;
import org.json.JSONObject;

public class ItineraryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getItineraryType_For_Display".equals(action)) {

			String str = req.getParameter("itinerary_type_id");
			Integer itinerary_type_id = Integer.valueOf(str);
//			if (str.equals("-1"))
//			{
//				ItineraryService itinerarySvc = new ItineraryService();
//				List<ItineraryVO> list = itinerarySvc.getAll();
//				req.setAttribute("list", list);
//				String url = "/back_end/itinerary/listAllItinerary.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//			}
//			
//			else {
			/*************************** 2.開始查詢資料 *****************************************/
			ItineraryService itinerarySvc = new ItineraryService();
			List<ItineraryVO> list = itinerarySvc.getItineraryType(itinerary_type_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/itinerary/listAllItinerary.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
//		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("itinerary_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入行程編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itinerary/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer itinerary_id = null;
			try {
				itinerary_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("行程編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itinerary/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ItineraryService itinerarySvc = new ItineraryService();
			ItineraryVO itineraryVO = itinerarySvc.getOneItinerary(itinerary_id);
			if (itineraryVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itinerary/listAllItinerary.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryVO", itineraryVO); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/itinerary/listOneItinerary.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs1", errorMsgs1);

			/*************************** 1.接收請求參數 ****************************************/
			Integer itinerary_id = Integer.valueOf(req.getParameter("itinerary_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			ItineraryService itinerarySvc = new ItineraryService();
			ItineraryVO itineraryVO = itinerarySvc.getOneItinerary(itinerary_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("itineraryVO", itineraryVO); // 資料庫取出的empVO物件,存入req

			boolean openModal = true;
			req.setAttribute("openModal", openModal);

			String url = "/back_end/itinerary/listAllItinerary.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer itinerary_id = Integer.valueOf(req.getParameter("itinerary_id").trim());

			Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id").trim());

			java.sql.Date itinerary_start = null;
			try {
				itinerary_start = java.sql.Date.valueOf(req.getParameter("itinerary_start").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入行程開始日期!");
			}
			java.sql.Date itinerary_end = null;

			try {
				itinerary_end = java.sql.Date.valueOf(req.getParameter("itinerary_end").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入行程結束日期!");
			}
			java.sql.Date entered_start = null;

			try {
				entered_start = java.sql.Date.valueOf(req.getParameter("entered_start").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入報名開始日期!");
			}
			java.sql.Date entered_end = null;
			try {
				entered_end = java.sql.Date.valueOf(req.getParameter("entered_end").trim());

			} catch (IllegalArgumentException e) {

				errorMsgs.add("請輸入報名結束日期!");
			}

			if (((entered_start).equals(itinerary_end)) || ((itinerary_end).before(itinerary_start)) ||((itinerary_start).before(entered_end)) || ((entered_end).before(entered_start)) ) {
				errorMsgs.add("請確認日期");
			}

			Integer itinerary_now = null;
			try {
				itinerary_now = Integer.valueOf(req.getParameter("itinerary_now").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請勿空白");
			}

			Integer itinerary_price = null;
			try {
				itinerary_price = Integer.valueOf(req.getParameter("itinerary_price").trim());
			} catch (NumberFormatException e) {
				itinerary_price = 0;
				errorMsgs.add("行程費用請填數字");
			}
			
			if (itinerary_price<=0) {
				errorMsgs.add("行程費用請輸入大於0的金額.");
			}

			Integer itinerary_min = null;
			try {
				itinerary_min = Integer.valueOf(req.getParameter("itinerary_min").trim());
			} catch (NumberFormatException e) {
				itinerary_min = 0;
				errorMsgs.add("行程最低人數請填數字");
			}

			Integer itinerary_max = null;
			try {
				itinerary_max = Integer.valueOf(req.getParameter("itinerary_max").trim());
			} catch (NumberFormatException e) {
				itinerary_max = 0;
				errorMsgs.add("行程最多人數請填數字");
			}

			Byte itinerary_state = Byte.valueOf(req.getParameter("itinerary_state").trim());

			ItineraryVO itineraryVO = new ItineraryVO();
			itineraryVO.setItinerary_id(itinerary_id);
			itineraryVO.setItinerary_type_id(itinerary_type_id);
			itineraryVO.setItinerary_start(itinerary_start);
			itineraryVO.setItinerary_end(itinerary_end);
			itineraryVO.setItinerary_now(itinerary_now);
			itineraryVO.setItinerary_price(itinerary_price);
			itineraryVO.setItinerary_min(itinerary_min);
			itineraryVO.setItinerary_max(itinerary_max);
			itineraryVO.setEntered_start(entered_start);
			itineraryVO.setEntered_end(entered_end);
			itineraryVO.setItinerary_state(itinerary_state);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				req.setAttribute("itineraryVO", itineraryVO); // 含有輸入格式錯誤的empVO物件,也存入req <<<如果輸錯，其他資料會保留
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itinerary/listAllItinerary.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			ItineraryService itinerarySvc = new ItineraryService();
			itineraryVO = itinerarySvc.updateItinerary(itinerary_id, itinerary_type_id, itinerary_start, itinerary_end,
					itinerary_now, itinerary_price, itinerary_min, itinerary_max, entered_start, entered_end,
					itinerary_state);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("itineraryVO", itineraryVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/itinerary/listAllItinerary.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> adderrorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("adderrorMsgs", adderrorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id").trim());
			java.sql.Date itinerary_start = null;
			try {
				itinerary_start = java.sql.Date.valueOf(req.getParameter("itinerary_start").trim());
			} catch (IllegalArgumentException e) {
				adderrorMsgs.add("請輸入行程開始日期!");
			}
			java.sql.Date itinerary_end = null;

			try {
				itinerary_end = java.sql.Date.valueOf(req.getParameter("itinerary_end").trim());
			} catch (IllegalArgumentException e) {
				adderrorMsgs.add("請輸入行程結束日期!");
			}
			java.sql.Date entered_start = null;

			try {
				entered_start = java.sql.Date.valueOf(req.getParameter("entered_start").trim());
			} catch (IllegalArgumentException e) {
				adderrorMsgs.add("請輸入報名開始日期!");
			}
			java.sql.Date entered_end = null;
			try {
				entered_end = java.sql.Date.valueOf(req.getParameter("entered_end").trim());
			} catch (IllegalArgumentException e) {
				adderrorMsgs.add("請輸入報名結束日期!");
			}

//			if((entered_start).compareTo(itinerary_end)==0) {
//				errorMsgs.add("請確認日期");
//			}

			if (((entered_start).equals(itinerary_end)) || ((itinerary_end).before(itinerary_start)) ||((itinerary_start).before(entered_end)) || ((entered_end).before(entered_start)) ) {
				adderrorMsgs.add("請確認日期");
			}
			Integer itinerary_now = null;
			try {
				itinerary_now = Integer.valueOf(req.getParameter("itinerary_now").trim());
			} catch (NumberFormatException e) {
				adderrorMsgs.add("請勿空白");
			}

			Integer itinerary_price = null;
			try {
				itinerary_price = Integer.valueOf(req.getParameter("itinerary_price").trim());
			} catch (NumberFormatException e) {
				itinerary_price = 0;
				adderrorMsgs.add("行程費用請填數字");
			}
			
			if (itinerary_price<=0) {
				adderrorMsgs.add("行程費用請輸入大於0的金額.");
			}

			Integer itinerary_min = null;
			try {
				itinerary_min = Integer.valueOf(req.getParameter("itinerary_min").trim());
			} catch (NumberFormatException e) {
				itinerary_min = 0;
				adderrorMsgs.add("行程最低人數請填數字");
			}

			Integer itinerary_max = null;
			try {
				itinerary_max = Integer.valueOf(req.getParameter("itinerary_max").trim());
			} catch (NumberFormatException e) {
				itinerary_max = 0;
				adderrorMsgs.add("行程最多人數請填數字");
			}

			Byte itinerary_state = Byte.valueOf(req.getParameter("itinerary_state").trim());

			ItineraryVO additineraryVO = new ItineraryVO();
			additineraryVO.setItinerary_type_id(itinerary_type_id);
			additineraryVO.setItinerary_start(itinerary_start);
			additineraryVO.setItinerary_end(itinerary_end);
			additineraryVO.setItinerary_now(itinerary_now);
			additineraryVO.setItinerary_price(itinerary_price);
			additineraryVO.setItinerary_min(itinerary_min);
			additineraryVO.setItinerary_max(itinerary_max);
			additineraryVO.setEntered_start(entered_start);
			additineraryVO.setEntered_end(entered_end);
			additineraryVO.setItinerary_state(itinerary_state);

			// Send the use back to the form, if there were errors
			if (!adderrorMsgs.isEmpty()) {
				req.setAttribute("additineraryVO", additineraryVO); // 含有輸入格式錯誤的empVO物件,也存入req <<<如果輸錯，其他資料會保留
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itinerary/listAllItinerary.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ItineraryService itinerarySvc = new ItineraryService();
			additineraryVO = itinerarySvc.addItinerary(itinerary_type_id, itinerary_start, itinerary_end, itinerary_now,
					itinerary_price, itinerary_min, itinerary_max, entered_start, entered_end, itinerary_state);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/itinerary/listAllItinerary.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllItinerary.jsp
			successView.forward(req, res);
		}

		
	       //ajax gson test  >>>gson.html
        
	    	if("gson".equals(action)) {

				String str = req.getParameter("itinerary_type_id");
				Integer itinerary_type_id = Integer.valueOf(str);
					/***************************2.開始查詢資料*****************************************/
					ItineraryService itinerarySvc = new ItineraryService();
					List<ItineraryVO> list = itinerarySvc.getItineraryType(itinerary_type_id);
		   //		小吳範例 		GsonEx.java
//					// List to JSON
//					jsonStr = gson.toJson(bookList);
//					System.out.println("List to JSON: " + jsonStr);
//					// JSON to List
//					System.out.println("JSON to List: ");
//					/*
//					 * TypeToken represents a generic type. TypeToken is an abstract class
//					 * but with no abstract methods, thus we don't have to override any
//					 * method in TypeToken.
//					 */
//					Type collectionType = new TypeToken<List<Book>>() {
//					}.getType();
//					List<Book> myBookList = gson.fromJson(jsonStr, collectionType);
//					for (Book book : myBookList) {
//						book.show();
//					}
					System.out.println("abc");
//					Gson gson = new Gson();
					res.setContentType("text/html;charset=utf-8");
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					String jsonStr = "";
					jsonStr = gson.toJson(list);
					PrintWriter out = res.getWriter();
					out.print(jsonStr);          
			        out.close();
					System.out.println(jsonStr);

					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//					req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
//					String url = "/back_end/itinerary/listAllItinerary.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//					successView.forward(req, res);
			}
		
		
		
		
		
		// ajax gson test >>>gson.html

		
		
		
		if ("gson2".equals(action)) {

//			String str = req.getParameter("itinerary_type_id");
			String[] json = req.getParameterValues("json");
			String price1 = req.getParameter("r1");
			String price2 = req.getParameter("r2");
			String classId = req.getParameter("itrClass");

//			List<String> items = Arrays.asList(myJsonData[0].split(","));
//			for (int i = 0; i < items.size(); i++) {
			
			String locList = json[0].replace("\"", "").replace(" ", "").replace("[", "").replace("]", "");
//				if (!("".equals(q))) {
//
//					System.out.println(q);
//					System.out.println("ggg");
//				}
//			}
////		
			if(classId.equals("0") )
			{
				classId = " ITINERARY_CLASS_ID ";
			};
			if("".equals(locList))
			{
				locList = " ITINERARY_LOC_ID ";
			};
			
			
			
			System.out.println(locList);
			System.out.println(price1);
			System.out.println(price2);
			System.out.println(classId);

			/*************************** 2.開始查詢資料 *****************************************/
			ItineraryTypeService itineraryTypeSvc = new ItineraryTypeService();
			List<ItineraryTypeVO> list = itineraryTypeSvc.getTypeByMul(locList,price1,price2,classId);
					
			res.setContentType("text/html;charset=utf-8");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonStr = "";
			jsonStr = gson.toJson(list);
			System.out.println(jsonStr);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
//				String url = "/back_end/itinerary/listAllItinerary.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
		}

//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

//				/***************************1.接收請求參數***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//		}
	}
}

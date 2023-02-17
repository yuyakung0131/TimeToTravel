package com.itinerarytype.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itinerarycollection.model.ItineraryCollectionService;
import com.itinerarycollection.model.ItineraryCollectionVO;
import com.itinerarycomment.model.ItineraryCommentVO;
import com.itineraryimg.model.ItineraryImgService;
import com.itineraryimg.model.ItineraryImgVO;
import com.itinerarylocdetail.model.ItineraryLocDetailService;
import com.itineraryorder.model.ItineraryOrderService;
import com.itineraryorder.model.ItineraryOrderVO;
import com.itinerarytype.model.ItineraryTypeService;
import com.itinerarytype.model.ItineraryTypeVO;


public class ItineraryTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getItineraryClass_For_Display".equals(action)) {
			
			String str = req.getParameter("itinerary_class_id");
			Integer itinerary_class_id = Integer.valueOf(req.getParameter("itinerary_class_id"));
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************2.開始查詢資料*****************************************/
				ItineraryTypeService itineraryTypeSvc = new ItineraryTypeService();
				List<ItineraryTypeVO> itineraryTypeVO = itineraryTypeSvc.getItineraryClass(itinerary_class_id);
				if (itineraryTypeVO.size()==0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itineraryTypeVO", itineraryTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/itinerarytype/listItineraryClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		if("getFirm_For_Display".equals(action)) {

			String str = req.getParameter("firm_id");
			Integer firm_id = Integer.valueOf(str);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		
				/***************************2.開始查詢資料*****************************************/
				ItineraryTypeService itineraryTypeSvc = new ItineraryTypeService();
				List<ItineraryTypeVO> itineraryTypeVO = itineraryTypeSvc.getFirm(firm_id);
				if (itineraryTypeVO.size()==0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itineraryTypeVO", itineraryTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/itinerarytype/listFirm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("itinerary_type_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入種類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer itinerary_type_id = null;
				try {
					itinerary_type_id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("種類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
				ItineraryTypeVO itineraryTypeVO = itinerarytypeSvc.getOneItineraryType(itinerary_type_id);
				if (itineraryTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itineraryTypeVO", itineraryTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/itinerarytype/listOneItineraryType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
			 List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer itinerary_class_id = null;
					try {
						itinerary_class_id = Integer.valueOf(req.getParameter("itinerary_class_id").trim());
					} catch (NumberFormatException e) {
						itinerary_class_id = 0;
						errorMsgs.add("類別編號請填數字");
					}
					
					Integer firm_id = null;
					try {
						firm_id = Integer.valueOf(req.getParameter("firm_id").trim());
					} catch (NumberFormatException e) {
						firm_id = 0;
						errorMsgs.add("廠商編號請填數字.");
					}
					
					String itinerary_title = req.getParameter("itinerary_title");
					String itinerary_titleReg = "^[\\u4e00-\\u9fa5_a-zA-Z0-9\s]+$";
					if (itinerary_title == null || itinerary_title.trim().length() == 0) {
						errorMsgs.add("行程標題: 請勿空白");
					} else if(!itinerary_title.trim().matches(itinerary_titleReg)) { 
						errorMsgs.add("行程標題: 只能是中、英文字母、數字");
		            }
					
					String itinerary_info = req.getParameter("itinerary_info");
					if (itinerary_info == null || itinerary_info.trim().length() == 0) {
						errorMsgs.add("行程資訊: 請勿空白");
					} 
					
					String itinerary_other = req.getParameter("itinerary_other");
					
					Integer itinerary_price = null;
					try {
						itinerary_price = Integer.valueOf(req.getParameter("itinerary_price").trim());
					} catch (NumberFormatException e) {
						itinerary_price = 0;
						errorMsgs.add("行程費用請填數字.");
					}
					
					if (itinerary_price<=0) {
						errorMsgs.add("行程費用請輸入大於0的金額.");
					}
					
					Integer itinerary_min = null;
					try {
						itinerary_min = Integer.valueOf(req.getParameter("itinerary_min").trim());
					} catch (NumberFormatException e) {
						itinerary_min = 0;
						errorMsgs.add("最少成行人數請填數字.");
					}
					
					if (itinerary_min<0) {
						errorMsgs.add("最少成行人數請填數字.");
					}
					
					Integer itinerary_max = null;
					try {
						itinerary_max = Integer.valueOf(req.getParameter("itinerary_max").trim());
					} catch (NumberFormatException e) {
						itinerary_max = 0;
						errorMsgs.add("最多可報人數請填數字.");
					}
					
					
					
					if (itinerary_max < itinerary_min) {
					    errorMsgs.add("最多可報人數不能小於最少成行人數.");
					}
					
					Byte itinerary_type_state = null;
					try {
						itinerary_type_state = Byte.valueOf(req.getParameter("itinerary_type_state").trim());
					} catch (NumberFormatException e) {
						itinerary_type_state = 0;
						errorMsgs.add("行程狀態請填數字.");
					}
					
					
					

					ItineraryTypeVO itineraryTypeVO = new ItineraryTypeVO();
					itineraryTypeVO.setItinerary_class_id(itinerary_class_id);
					itineraryTypeVO.setFirm_id(firm_id);
					itineraryTypeVO.setItinerary_title(itinerary_title);
					itineraryTypeVO.setItinerary_info(itinerary_info);
					itineraryTypeVO.setItinerary_other(itinerary_other);
					itineraryTypeVO.setItinerary_price(itinerary_price);
					itineraryTypeVO.setItinerary_min(itinerary_min);
					itineraryTypeVO.setItinerary_max(itinerary_max);
					itineraryTypeVO.setItinerary_type_state(itinerary_type_state);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("itineraryTypeVO", itineraryTypeVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					
					String[] itinerary_loc_ids = req.getParameterValues("itinerary_loc_id");
//					ItineraryLocDetailService itinerarylocSvc = new ItineraryLocDetailService();
		
					/***************************2.開始新增資料***************************************/
					ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
					
					System.out.println(itinerary_loc_ids);
//					itineraryTypeVO = itinerarytypeSvc.addItineraryType(itinerary_class_id, firm_id, itinerary_title, itinerary_info, itinerary_other, itinerary_price, itinerary_min, itinerary_max, itinerary_type_state, null, null);
					itineraryTypeVO = itinerarytypeSvc.addItineraryType(itinerary_class_id, firm_id, itinerary_title, itinerary_info, itinerary_other, itinerary_price, itinerary_min, itinerary_max, itinerary_type_state,itinerary_loc_ids);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
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
					Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id"));
					
					/***************************2.開始查詢資料****************************************/
					ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
					ItineraryTypeVO itineraryTypeVO = itinerarytypeSvc.getOneItineraryType(itinerary_type_id);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("itineraryTypeVO", itineraryTypeVO);         // 資料庫取出的empVO物件,存入req
					String url = "/back_end/itinerarytype/updateItineraryType.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
			}
			
			
			if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id").trim());
				
					Integer itinerary_class_id = null;
					try {
						itinerary_class_id = Integer.valueOf(req.getParameter("itinerary_class_id").trim());
					} catch (NumberFormatException e) {
						itinerary_class_id = 0;
						errorMsgs.add("類別編號請填數字");
					}

					Integer firm_id = null;
					try {
						firm_id = Integer.valueOf(req.getParameter("firm_id").trim());
					} catch (NumberFormatException e) {
						firm_id = 0;
						errorMsgs.add("廠商編號請填數字.");
					}

					String itinerary_title = req.getParameter("itinerary_title");
					String itinerary_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]$";
					if (itinerary_title == null || itinerary_title.trim().length() == 0) {
						errorMsgs.add("行程標題: 請勿空白");
					} else if (!!itinerary_title.trim().matches(itinerary_titleReg)){
						errorMsgs.add("行程標題: 只能是中、英文字母、數字");
					}

					String itinerary_info = req.getParameter("itinerary_info");
					if (itinerary_info == null || itinerary_info.trim().length() == 0) {
						errorMsgs.add("行程資訊: 請勿空白");
					} 

					String itinerary_other = req.getParameter("itinerary_other");
					
					Integer itinerary_price = null;
					try {
						itinerary_price = Integer.valueOf(req.getParameter("itinerary_price").trim());
					} catch (NumberFormatException e) {
						itinerary_price = 0;
						errorMsgs.add("行程費用請填數字.");
					}
					
					if (itinerary_price<=0) {
						errorMsgs.add("行程費用請輸入大於0的金額.");
					}

					Integer itinerary_min = null;
					try {
						itinerary_min = Integer.valueOf(req.getParameter("itinerary_min").trim());
					} catch (NumberFormatException e) {
						itinerary_min = 0;
						errorMsgs.add("最少成行人數請填數字.");
					}
					
					if (itinerary_min<0) {
						errorMsgs.add("最少成行人數請填數字.");
					}

					Integer itinerary_max = null;
					try {
						itinerary_max = Integer.valueOf(req.getParameter("itinerary_max").trim());
					} catch (NumberFormatException e) {
						itinerary_max = 0;
						errorMsgs.add("最多可報人數請填數字.");
					}
					
					if (itinerary_max < itinerary_min) {
					    errorMsgs.add("最多可報人數不能小於最少成行人數.");
					}

					Byte itinerary_type_state = null;
					try {
						itinerary_type_state = Byte.valueOf(req.getParameter("itinerary_type_state").trim());
					} catch (NumberFormatException e) {
						itinerary_type_state = 0;
						errorMsgs.add("行程狀態請填數字.");
					}

					Integer itinerary_total_score = Integer.valueOf(req.getParameter("itinerary_total_score").trim());
					Integer itinerary_total_people = Integer.valueOf(req.getParameter("itinerary_total_people").trim());


					ItineraryTypeVO itineraryTypeVO = new ItineraryTypeVO();
					itineraryTypeVO.setItinerary_type_id(itinerary_type_id);
					itineraryTypeVO.setItinerary_class_id(itinerary_class_id);
					itineraryTypeVO.setFirm_id(firm_id);
					itineraryTypeVO.setItinerary_title(itinerary_title);
					itineraryTypeVO.setItinerary_info(itinerary_info);
					itineraryTypeVO.setItinerary_other(itinerary_other);
					itineraryTypeVO.setItinerary_price(itinerary_price);
					itineraryTypeVO.setItinerary_min(itinerary_min);
					itineraryTypeVO.setItinerary_max(itinerary_max);
					itineraryTypeVO.setItinerary_type_state(itinerary_type_state);
					itineraryTypeVO.setItinerary_total_score(itinerary_total_score);
					itineraryTypeVO.setItinerary_total_people(itinerary_total_people);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("itineraryTypeVO", itineraryTypeVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/itinerarytype/updateItineraryType.jsp");
						failureView.forward(req, res);
						return;
					}
					
					
					/***************************2.開始修改資料*****************************************/
					ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
					itineraryTypeVO = itinerarytypeSvc.updateItineraryType(itinerary_type_id, itinerary_class_id, firm_id, itinerary_title, itinerary_info, itinerary_other, itinerary_price, itinerary_min, itinerary_max, itinerary_type_state,itinerary_total_score,itinerary_total_people);
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("itineraryTypeVO", itineraryTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/back_end/itinerarytype/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
			}
		 
			
			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
					/***************************1.接收請求參數***************************************/
					Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id"));
					
					/***************************2.開始刪除資料***************************************/
					ItineraryTypeService itinerarytypeSvc = new ItineraryTypeService();
					itinerarytypeSvc.deleteItineraryType(itinerary_type_id);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/back_end/itinerarytype/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
			}
			
			
			
			if("showDetail".equals(action)) {			
				/****1.取得參數****/
				Integer itinerary_type_id = new Integer(req.getParameter("itinerary_type_id"));
//				Integer member_id=new Integer(req.getParameter("member_id"));
//				Integer itinerary_id = new Integer(req.getParameter("itinerary_id"));
				
				/****2.查詢行程種類資料****/
				ItineraryTypeService itineraryTypeSvc = new ItineraryTypeService();
				ItineraryTypeVO itineraryTypeVO = itineraryTypeSvc.getOneItineraryType(itinerary_type_id);
				
				//
				ItineraryService itinerarySvc = new ItineraryService();
				List itineraryList = itinerarySvc.getItineraryType(itinerary_type_id);
				
				
				ItineraryCollectionService itineraryCollectionSvc=new ItineraryCollectionService();
//				ItineraryCollectionVO itineraryCollectionVO=itineraryCollectionSvc.getOneItineraryCollection(member_id, itinerary_type_id);
								
				
				req.setAttribute("itineraryTypeVO", itineraryTypeVO);
//				req.setAttribute("itineraryCollectionVO", itineraryCollectionVO);
				req.setAttribute("fiteritrList", itineraryList);


			
//				過濾後的行程列表
				List<ItineraryVO> fiteritrList = itineraryList(itinerary_type_id);
				req.setAttribute("fiteritrList", fiteritrList);
				System.out.println("aaaa");
				System.out.println(fiteritrList);
				
				/****4.查詢評論資料list****/
				List<ItineraryCommentVO> itrCommentList = itineraryTypeSvc.getCommentByType(itinerary_type_id);
				req.setAttribute("itrCommentList", itrCommentList);
				
				/****5.圖片數量****/
				
				ItineraryImgService itrImgSvcImgService = new ItineraryImgService();
				List<ItineraryImgVO> imgList = itrImgSvcImgService.getItrImgByType(itinerary_type_id);
				req.setAttribute("imgList", imgList);
				
				/****6.轉交****/
				String url = "/front_end/itinerarytype/itineraryDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
	}
				
			

	
//過濾行程 若行程狀態為0(正常)才顯示在頁面
private List<ItineraryVO> itineraryList(Integer itinerary_type_id){
	List<ItineraryVO> fiteritrList = null;
	ItineraryService itinerarySvc = new ItineraryService();
	List<ItineraryVO> itineraryList = itinerarySvc.getItineraryType(itinerary_type_id);
	fiteritrList =  itineraryList.stream().filter(f -> f.getItinerary_state()  ==  0).collect(Collectors.toList());
	return fiteritrList;
}}
			
			

		 



package com.itinerarytype.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itineraryclass.model.ItineraryClassService;
import com.itineraryclass.model.ItineraryClassVO;
import com.itinerarycollection.model.ItineraryCollectionService;
import com.itinerarycollection.model.ItineraryCollectionVO;
import com.itinerarycomment.model.ItineraryCommentVO;
import com.itineraryorder.model.ItineraryOrderService;
import com.itineraryorder.model.ItineraryOrderVO;
import com.itinerarytype.model.ItineraryTypeService;
import com.itinerarytype.model.ItineraryTypeVO;


public class ItineraryTypeServletForAjax extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("itrMulSearch".equals(action)) {

			String[] json = req.getParameterValues("json");
			String price1 = req.getParameter("r1");
			String price2 = req.getParameter("r2");
			String classId = req.getParameter("itrClass");
			String locList = json[0].replace("\"", "").replace(" ", "").replace("[", "").replace("]", "");
		
//			Integer itinerary_class_id = Integer.valueOf(classId);
			
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
			ItineraryClassService itineraryClassSvc = new ItineraryClassService();
			List<ItineraryTypeVO> list = itineraryTypeSvc.getTypeByMul(locList,price1,price2,classId);
			List<Object> returnList = new ArrayList<>();
			

			
			for (ItineraryTypeVO vo : list) { 
				ItineraryClassVO itineraryClassVO = itineraryClassSvc.getOneItineraryClass(vo.getItinerary_class_id());
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				
				if(vo.getItinerary_type_state() == 0) {
				map.put("itineraryVO", vo);
				map.put("className", itineraryClassVO.getItinerary_class_name());
				System.out.println(vo.getItinerary_type_state());
				returnList.add(map);}
			}
			
			
			
			
			res.setContentType("text/html;charset=utf-8");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonStr = "";
			jsonStr = gson.toJson(returnList);
			System.out.println(jsonStr);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();

		}
	
	
		if("ajaxAutoComplete".equals(action)) {

			/***************************2.開始查詢資料*****************************************/
			ItineraryTypeService itineraryTypeSvc = new ItineraryTypeService();
			List<ItineraryTypeVO> list = itineraryTypeSvc.getItrTitle();

			System.out.println("list");
			Gson gson = new Gson();
			res.setContentType("text/html;charset=utf-8");
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonStr = "";
			jsonStr = gson.toJson(list);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);          
	        out.close();
			System.out.println(jsonStr);

			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
//			String url = "/back_end/itinerary/listAllItinerary.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
	}

	
		if("itrCalendar".equals(action)) {

			String str = req.getParameter("itinerary_type_id");
			Integer itinerary_type_id = Integer.valueOf(str);
				/***************************2.開始查詢資料*****************************************/
				ItineraryService itinerarySvc = new ItineraryService();
				List<ItineraryVO> list = itinerarySvc.getItineraryType(itinerary_type_id);
				
//				過濾後的行程列表
				List<ItineraryVO> fiteritrList = itineraryList(itinerary_type_id);
				req.setAttribute("fiteritrList", fiteritrList);
				
				
				System.out.println("abc");
				System.out.println("fiteritrList");
				res.setContentType("text/html;charset=utf-8");
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonStr = "";
				jsonStr = gson.toJson(fiteritrList);
				PrintWriter out = res.getWriter();
				out.print(jsonStr);          
		        out.close();
				System.out.println(jsonStr);


		}
	
	
	
	
	
	
	
	
	
	
	
	}
	
	private List<ItineraryVO> itineraryList(Integer itinerary_type_id){
		List<ItineraryVO> fiteritrList = null;
		ItineraryService itinerarySvc = new ItineraryService();
		List<ItineraryVO> itineraryList = itinerarySvc.getItineraryType(itinerary_type_id);
		fiteritrList =  itineraryList.stream().filter(f -> (( f.getItinerary_max() - f.getItinerary_now() >0)  &&  (f.getItinerary_state()  ==  0))).collect(Collectors.toList());
		return fiteritrList;
	}
				
}			

	
			
			

		 



package com.itineraryimg.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itineraryimg.model.ItineraryImgService;
import com.itineraryimg.model.ItineraryImgVO;

@MultipartConfig
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 5 * 1024 * 1024)

public class ItineraryImgServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			byte[] buf = req.getPart("upfile1").getInputStream().readAllBytes();
			System.out.println(req.getPart("upfile1").getSubmittedFileName());

			if (buf.length == 0) {
				errorMsgs.add("請確認有無圖片上傳");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryimg/addItineraryImg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

//			String file_format=req.getPart("upfile1").getContentType();

			if (!(req.getPart("upfile1").getContentType().contains("image"))) {
				errorMsgs.add("請確認上傳圖片格式");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryimg/addItineraryImg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			if (req.getParameter("itinerary_type_id") == null || (req.getParameter("itinerary_type_id").trim()).length() == 0) {
				errorMsgs.add("請輸入編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryimg/addItineraryImg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer itinerary_type_id = null;

			try {
				itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id"));
			} catch (Exception e) {
				errorMsgs.add("編號格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itinerarytype/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			ItineraryImgService itineraryimgSvc = new ItineraryImgService();
			ItineraryImgVO itineraryImgVO = itineraryimgSvc.addItineraryImg(buf, itinerary_type_id);
			req.setAttribute("itineraryImgVO", itineraryImgVO);
			String url = "/back_end/itineraryimg/listAllItineraryImg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
	

		if ("getOne_For_Display".equals(action)) {

			
			Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id"));
			
			ItineraryImgService itineraryimgSvc=new ItineraryImgService();
			List<ItineraryImgVO> list=itineraryimgSvc.findByItineraryTypeId(itinerary_type_id);
			req.setAttribute("itinerary_type_id", itinerary_type_id);
			String url = "/back_end/itineraryimg/listOneItineraryimg.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer itinerary_img_id = Integer.valueOf(req.getParameter("itinerary_img_id"));
			ItineraryImgService itineraryimgSvc = new ItineraryImgService();
			ItineraryImgVO itineraryImgVO = itineraryimgSvc.getOneItineraryImg(itinerary_img_id);
			req.setAttribute("itineraryImgVO", itineraryImgVO);
			String url = "/back_end/itineraryimg/updateOneItineraryimg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			byte[] buf = req.getPart("upfile1").getInputStream().readAllBytes();
			Integer itinerary_img_id = Integer.valueOf(req.getParameter("itinerary_img_id"));
			ItineraryImgService itineraryimgSvc = new ItineraryImgService();

			if (!(buf.length == 0) && !(req.getPart("upfile1").getContentType().contains("image"))) {
				errorMsgs.add("請確認上傳圖片格式");
			}

			Integer itinerary_type_id = Integer.valueOf(req.getParameter("itinerary_type_id"));



			if (buf.length == 0) {
				buf = itineraryimgSvc.getOneItineraryImg(itinerary_img_id).getItinerary_img();
			}

			ItineraryImgVO itineraryimgVO = new ItineraryImgVO();
			itineraryimgVO.setItinerary_img_id(itinerary_img_id);
			itineraryimgVO.setItinerary_img(buf);
			itineraryimgVO.setItinerary_type_id(itinerary_type_id);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("itineraryimgVO", itineraryimgVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/itineraryimg/updateOneItineraryimg.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			itineraryimgVO = itineraryimgSvc.updateItineraryImg(itinerary_img_id, buf, itinerary_type_id);
			req.setAttribute("itineraryimgVO", itineraryimgVO);
			String url = "/back_end/itineraryimg/listAllItineraryImg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer itinerary_img_id = Integer.valueOf(req.getParameter("itinerary_img_id"));
			ItineraryImgService itineraryimgSvc = new ItineraryImgService();
			itineraryimgSvc.deleteItineraryImg(itinerary_img_id);
			String url = "/back_end/itineraryimg/listAllItineraryImg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

}
}
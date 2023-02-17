package com.news.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.news.model.NewsService;
import com.news.model.NewsVO;

//繼承 HttpServlet
@MultipartConfig // 用圖必加
public class NewsServlet extends HttpServlet {

	// 無敵的doGet 加上doPost
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	// 我們由doPost開始撰寫
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 單一查詢
		if ("getOne_For_Display".equals(action)) {
			// 來自select_pagePromotion.jsp的請求

			// 先建立一個空的errorMsgs集合，若有例外訊息就往裡面放值
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 取得select_page輸入的值
			// 先用String型態，方便錯誤判斷
			// 注意JSP不論顯示文字是甚麼，都回傳name="emp_id"的資料，所以這邊getParameter("emp_id")
			String str = req.getParameter("news_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入編號");
			}
			// 若錯誤集合有資料就forward回原網頁，並中斷程式。
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/select_pageNews.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			// 判斷是否能轉型。
			Integer news_no = null;
			try {
				news_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("請輸入編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/select_pageNews.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			NewsService newsSvc = new NewsService();
			NewsVO newsVO = newsSvc.getOneNews(news_no);
			if (newsVO == null) {
				errorMsgs.add("無此資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/select_pageNews.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("newsVO", newsVO); // 資料庫取出的newsVO物件,存入req
			String url = "/back_end/news/listOneNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnePronotion.jsp
			successView.forward(req, res);
		}

//		 取一值更新
	if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 ****************************************/
		Integer news_no = Integer.valueOf(req.getParameter("news_no"));

		/*************************** 2.開始查詢資料 ****************************************/
		NewsService newsSvc = new NewsService();
		NewsVO newsVO = newsSvc.getOneNews(news_no);

		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
		req.setAttribute("newsVO", newsVO);
		// 資料庫取出的newsVO物件,存入req
		String url = "/back_end/news/update_news_input.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_news_input.jsp
		successView.forward(req, res);
	}

	//////////////////////////////////////////////////////////////
	// 修改
	// 來自update_news_input.jsp的請求
	if ("update".equals(action)) {

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer news_no = Integer.valueOf(req.getParameter("news_no").trim());
		// 錯誤時 秀出原本的值
		String news_title = req.getParameter("news_title");
		if (news_title == null || news_title.trim().length() == 0) {
			errorMsgs.add("促銷專案名稱填寫");
		}

		String news_content = req.getParameter("news_content");
		if (news_content == null || news_content.trim().length() == 0) {
			errorMsgs.add("促銷專案名稱填寫");
		}

		// 將圖片用byte[]儲存
//		Part part = req.getPart("emp_img"); 
//		InputStream in = part.getInputStream();
//		byte[] b = new byte[in.available()];
//		in.read(b);
//		in.close();
		Part part = req.getPart("news_pic");
		InputStream in = part.getInputStream();
		byte[] b = new byte[in.available()];

		if (b.length == 0) {
			NewsService newsSvc = new NewsService();
			NewsVO newsVO = newsSvc.getOneNews(news_no);
			b = newsVO.getNews_pic();
		} else {
			in.read(b);
			in.close();
		}

		// 建立這個NewsVO物件是為了讓輸入錯誤的話，可以儲存其他正確的值

		NewsVO newsVO = new NewsVO();
		newsVO.setNews_no(news_no);
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setNews_pic(b);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的newsVO物件,也存入req
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/update_news_input.jsp");
			failureView.forward(req, res);
			return; // 程式中斷
		}

		/*************************** 2.開始修改資料 *****************************************/
		NewsService newsSvc = new NewsService();
		newsVO = newsSvc.updateNews(news_no, news_title, news_content, b);
		// 再交給DAO去新增

		/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("newsVO", newsVO); // 資料庫update成功後,正確的的newsVO物件,存入req
		String url = "/back_end/news/listAllNews.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		// 修改成功後,轉交listOnePromotion.jsp
		successView.forward(req, res);
	}

	// 新增insert=======================================================================================
	if ("insert".equals(action)) { // 來自addEmp.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// 將此集合存儲在請求範圍內，以備不時之需
		// 發送 ErrorPage 視圖。
		req.setAttribute("errorMsgs", errorMsgs);

		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

		// 最新標題
		String news_title = req.getParameter("news_title");
		String news_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,)([^%&',;=?$x22]+)]{1,50}$";
		if (news_title == null || news_title.trim().length() == 0) {
			errorMsgs.add("消息標題: 請勿空白");
		} else if (!news_title.trim().matches(news_titleReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("消息標題: 只能是中、英文字母、數字和基本標點符號 且長度必需在1到50之間");
		}

		// 最新資訊
		String news_content = req.getParameter("news_content");
		String news_contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,)([^%&',;=?$x22]+)]{1,3000}$";
		if (news_content == null || news_content.trim().length() == 0) {
			errorMsgs.add("消息內容: 請勿空白");
		} else if (!news_content.trim().matches(news_contentReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("消息內容: 只能是中、英文字母、數字和基本標點符號 且長度必需在1到3000之間");
		}

		// 將圖片用byte[]儲存
		Part part = req.getPart("news_pic");
		InputStream in = part.getInputStream();
		byte[] b = new byte[in.available()];
		in.read(b);
		in.close();

		// 建立這個NewsVO物件是為了讓輸入錯誤的話，可以儲存其他正確的值
		// 宣告變數 創立物件
		NewsVO newsVO = new NewsVO();
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setNews_pic(b);

		// 如果有錯誤，請將使用發送回表單
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的newsVO物件,也存入req 將使用者輸入錯的在顯示一次
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/addNews.jsp");
			failureView.forward(req, res);
			return;
		}

		/*************************** 2.開始新增資料 ***************************************/
		NewsService newsSvc = new NewsService();
		newsVO = newsSvc.addNews(news_title, news_content, b);

		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		String url = "/back_end/news/listAllNews.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllNews.jsp
		successView.forward(req, res);
	}

	// 刪除
	if ("delete".equals(action)) { // 來自listAllNews.jsp

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 ***************************************/
		Integer news_no = Integer.valueOf(req.getParameter("news_no"));

		/*************************** 2.開始刪除資料 ***************************************/
		NewsService newsSvc = new NewsService();
		newsSvc.deleteNews(news_no);

		/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
		String url = "/back_end/news/listAllNews.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		successView.forward(req, res);
	}

	// 單一細項前台
	if ("getOne_For_FrontDisplay".equals(action)) { // 來自select_pagePromotion.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String str = req.getParameter("news_no");
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入正確編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/news/news_front_getAll.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}

		Integer news_no = null;
		try {
			news_no = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("消息編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/news/news_front_getAll.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}

		/*************************** 2.開始查詢資料 *****************************************/
		NewsService newsSvc = new NewsService();
		NewsVO newsVO = newsSvc.getOneNews(news_no);
		if (newsVO == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/news/news_front_getAll.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}

		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("newsVO", newsVO); // 資料庫取出的newsVO物件,存入req
		String url = "/front_end/news/news_front_getOne.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePronotion.jsp
		successView.forward(req, res);
	}

	// 圖片
	if ("getImg".equals(action)) {
		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();
		String newsIDString = req.getParameter("news_no");
		NewsService newsSvc = new NewsService();
		NewsVO newsVO = newsSvc.getOneNews(Integer.parseInt(newsIDString));
//				res.setContentLength(empVO.getEmp_img().length);
//				out.write(empVO.getEmp_img());
//				out.close();

		// 成功有圖片用 b1
		byte[] b1 = newsVO.getNews_pic();
		try {
			out.write(b1);
		} catch (Exception e) {
			// 沒有圖用catch
			InputStream in = getServletContext().getResourceAsStream("/back_end/news/images/noData.jpg");
			byte[] b2 = new byte[in.available()];
			in.read(b2);
			out.write(b2);
			in.close();
		}
	}

	// 複合查詢
//		if ("listNews_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.將輸入資料轉為Map **********************************/
//			// 採用Map<String,String[]> getParameterMap()的方法
//			// 注意:an immutable java.util.Map
//			// Map<String, String[]> map = req.getParameterMap();
//			HttpSession session = req.getSession();
//			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
//
//			// 以下的 if 區塊只對第一次執行時有效
//			if (req.getParameter("whichPage") == null) {
//				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
//				session.setAttribute("map", map1);
//				map = map1;
//			}
//
//			/*************************** 2.開始複合查詢 ***************************************/
//			NewsServlet newsSvc = new NewsServlet();
//			List<NewsVO> list = newsSvc.getAll(map);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("listNews_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
//			RequestDispatcher successView = req.getRequestDispatcher("/back_end/news/listOnePromotion.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//			successView.forward(req, res);
//		}

}

}
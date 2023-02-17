package com.shoppinglist.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.member.model.MemberVO;
import com.shoppinglist.model.ShoppingList;
import com.shoppinglist.model.ShoppingListService;
import com.ticket.model.Ticket;
import com.ticket.model.TicketService;
import com.ticketpromote.model.TicketPromote;
import com.ticketpromote.model.TicketPromoteService;
import com.tktitem.model.TktItem;
import com.tktitem.model.TktItemService;
import com.tktorder.model.TktOrder;
import com.tktorder.model.TktOrderService;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

class MailService {

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●1) 登入你的Gmail的:
			// ●2) 點選【管理你的 Google 帳戶】
			// ●3) 點選左側的【安全性】

			// ●4) 完成【兩步驟驗證】的所有要求如下:
			// ●4-1) (請自行依照步驟要求操作之.....)

			// ●5) 完成【應用程式密碼】的所有要求如下:
			// ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
			// ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
			// ●5-3) 最後按【產生】密碼
			final String myGmail = "timetotravelg3@gmail.com";
			final String myGmail_password = "wkmbgxsehvwrngca";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

}

public class ShoppingListServlet extends HttpServlet {
	
	public static AllInOne domain;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
	    
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Vector<ShoppingList> cart = (Vector<ShoppingList>) session.getAttribute("cart");
		String action = req.getParameter("action");

		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		Integer member_id = member.getMember_id();

		if ("orderlist".equals(action)) {

			Integer total = (Integer) session.getAttribute("total_amount");
			TicketPromote ticketpromote = (TicketPromote) session.getAttribute("ticketpromote");
			Integer promo_id = ticketpromote.getProm_id();
			Byte tkt_order_state = Byte.valueOf((byte) 1);
			Integer total_discount = Integer.valueOf((int) (ticketpromote.getDiscount_amount() * total));
			TktOrderService tktorderSvc = new TktOrderService();
			TktOrder tktorder = tktorderSvc.addTktOrder(member_id, promo_id, total, total_discount);
			TicketService tktSvc = new TicketService();

			Integer tkt_order_id = tktorder.getTkt_order_id();
//		System.out.println(tkt_order_id);
			TktOrder tktorderDB = tktorderSvc.getOneTktOrder(tkt_order_id);
			Timestamp time = tktorderDB.getOrder_date();

//		save to DB

			DataSource ds = null;
			Context ctx;
			try {
				ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/timetotravel");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Connection con = null;

			try {

				con = ds.getConnection();

				Integer tkt_id = null;
				Integer tkt_amount = null;
				Integer tkt_price = null;
				LocalDateTime tkt_deadline = null;
				Integer time_ticket = null;
				
				con.setAutoCommit(false);
				for (int i = 0; i < cart.size(); i++) {
					System.out.println(cart.size());
					ShoppingList aList = cart.get(i);
					tkt_id = aList.getTkt_id();
					tkt_amount = aList.getTkt_amount();
					tkt_price = aList.getTicket().getTkt_price();
					time_ticket = aList.getTicket().getTkt_date() * 30 * 24 * 60 * 60;

					tkt_deadline = Timestamp.from(time.toInstant().plusSeconds(time_ticket)).toLocalDateTime();

				

//			insert into TKTitem table
					TktItemService tktItemSvc = new TktItemService();
					tktItemSvc.addTktItem(tkt_id, tkt_order_id, tkt_amount, tkt_price, tkt_deadline);

//			update Ticket Table when order is placed
					Ticket ticket = tktSvc.getOneTicket(tkt_id);
					Integer new_amount = ticket.getTkt_amount() - tkt_amount;
					tktSvc.updatebyAmount(tkt_id, new_amount);

					
//					cancel shopping cart session
					
//					session.removeAttribute("cart");

				}
				
				con.commit();
				con.setAutoCommit(true);
				con.close();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

//		show on mail for tktlist
			TktItemService tktItemSvc2 = new TktItemService();
			List<TktItem> list = tktItemSvc2.getAllOrder(tkt_order_id);
			String show_item = "";
			for (int i = 0; i < list.size(); i++) {
				show_item += list.get(i).toString() + "\n";
			}

			String to = String.valueOf(req.getParameter("input_email"));

			String subject = "訂單通知";
			String title = member.getMember_gender().equals("男") ? "先生" : "小姐";

			String ch_name = member.getMember_name() + title + "您好:";
			String messageText = "此郵件是系統自動傳送，請勿直接回覆此郵件! \n\n\n" + ch_name + "\n\n\n"
					+ " 您在 TimeToTravel 的訂單已完成訂購，以下是您的訂單明細 " + "\n\n\n" + "※ 請注意!TimeToTravel保留接受訂單與否的權利" + "\n\n\n"
					+ "總共品項共有" + list.size() + "項\n\n" + "【訂購明細】\n\n" + show_item + "\n\n" + "共" + total_discount
					+ "元\n\n";

			MailService mailService = new MailService();
			mailService.sendMail(to, subject, messageText);
			
			
			
			
//			transfer to ECpay 
			
			 // 根據表單建立收款連結 (中文編碼有問題)
	        // 使用者跳轉至綠界的交易流程網站
	        // 按照流程輸入卡號..... (中文編碼!)
	            // 測試卡號: 一般信用卡測試卡號 : 4311-9522-2222-2222 安全碼 : 222
	            // 信用卡測試有效月/年：輸入的 MM/YYYY 值請大於現在當下時間的月年，
	            // 例如在 2016/04/20 當天作測試，請設定 05/2016(含)之後的有效月年，否則回應刷卡失敗。
	            // 手機請輸入正確，因為會傳驗證碼
	        // 檢查後台: 信用卡收單 - 交易明細 - 查詢
	        domain = new AllInOne("");
	        AioCheckOutOneTime obj = new AioCheckOutOneTime();
	        // 從 view 獲得資料，依照 https://developers.ecpay.com.tw/?p=2866 獲得必要的參數
	        // MerchantTradeNo  : 必填 特店訂單編號 (不可重複，因此需要動態產生)
	        obj.setMerchantTradeNo(new String("salon" + System.currentTimeMillis()));
	        // MerchantTradeDate  : 必填 特店交易時間 yyyy/MM/dd HH:mm:ss
	        obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
	        // TotalAmount  : 必填 交易金額
	        obj.setTotalAmount(total_discount.toString());
	        // TradeDesc  : 必填 交易描述
	        obj.setTradeDesc("StoreID:"+(String) req.getSession().getAttribute("foodorder_storeId"));
	        // ItemName  : 必填 商品名稱
	        obj.setItemName("This order total price is");
	        // ReturnURL   : 必填  我用不到所以是隨便填一個英文字
	        obj.setReturnURL("a");
	        // OrderResultURL   : 選填 消費者完成付費後。重新導向的位置
	        
	        
//	        obj.setOrderResultURL("http://localhost:8081/CGA105G3_main(2)/front_end/ticket/browseTicket.jsp");
	        obj.setClientBackURL("http://localhost:8081/CGA105G3_timetotravel/front_end/member/home.jsp");
	        obj.setNeedExtraPaidInfo("N");
	        
	        session.removeAttribute("cart");

	        // 回傳form訂單 並自動將使用者導到 綠界
	        String form = domain.aioCheckOut(obj, null);
	        System.out.println(form);
	        res.setCharacterEncoding("UTF-8");
	        res.getWriter().print("<html><body>" + form + "</body></html>");
			
			
			
			
			
			
			
			
			
			

//			String url = "/frontEnd/ticket/browseTicket.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);

		}

		if ("usepromote".equals(action)) {

			Integer prom_id = Integer.valueOf(req.getParameter("prom_id"));
			TicketPromoteService promoteSvc = new TicketPromoteService();
			TicketPromote ticketpromote = promoteSvc.getOneTicketPromote(prom_id);
			session.setAttribute("ticketpromote", ticketpromote);
			String url = "/front_end/ticket/checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}

		if ("delete".equals(action)) {

			// delete只有delete session的但是db的沒有刪掉??? how to fix?
			// 去判斷db中是否有相關的欄位,若有就直接刪掉db中的data

//			show no this delete item
			ShoppingList deleteList = getShoppingList(req);
			deleteList.setMember_id(member_id);
			cart.remove(deleteList);
			session.setAttribute("cart", cart);

//			delete DB
			ShoppingListService shoppingSvc = new ShoppingListService();
			Integer tkt_id = deleteList.getTkt_id();
			ShoppingList deleteListDB = shoppingSvc.getOneShoppingList(tkt_id, member_id);
			if (deleteListDB != null) {
				shoppingSvc.deleteShoppingList(tkt_id, member_id);
			}

			String url = "/front_end/ticket/cartlist.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}

		if ("getOne_For_Update".equals(action)) {

			ShoppingList updateoneList = getShoppingList(req);// generate new shoppingList object
			updateoneList.setMember_id(member_id);
			Integer new_tkt = updateoneList.getTkt_id();
			for (int i = 0; i < cart.size(); i++) {
				ShoppingList eachList = cart.get(i);
				Integer old_tkt = eachList.getTkt_id();
				if (old_tkt.equals(new_tkt)) {
					eachList = updateoneList;
					int id = cart.indexOf(eachList);
					cart.set(id, eachList);

				}
			}
			session.setAttribute("cart", cart);
			String url = "/front_end/ticket/cartlist.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}

		if ("save".equals(action)) {
			// 把最終結果存入資料庫中(結帳的時候再存?)
			ShoppingListService shoppingSvc = new ShoppingListService();
			List<ShoppingList> list_DB = shoppingSvc.getAllbyMember(member_id);
//			System.out.println("list_DB.size()" + list_DB.size());
			Integer tkt_id = null;
			Integer tkt_amount = null;
			Integer tkt_id_DB = null;
			Integer tkt_amount_DB = null;

//			System.out.println("cart.size()" + cart.size());
			for (int i = 0; i < cart.size(); i++) {
				ShoppingList aList = cart.get(i);
				tkt_id = aList.getTkt_id();
				tkt_amount = aList.getTkt_amount();
				
				
				if (list_DB == null) { //DB no data set session data to DB
					shoppingSvc.addShoppingList(tkt_id, member_id, tkt_amount);
				} 
				
				
				else if (!(list_DB.contains(aList))) { //DB exists data but new session
					shoppingSvc.addShoppingList(tkt_id, member_id, tkt_amount);
			
				} else { //DB exists data but old session with different number
					for (int j = 0; j < list_DB.size(); j++) {
						ShoppingList aListDB = list_DB.get(j);
						tkt_id_DB=aListDB.getTkt_id();
						tkt_amount_DB=aListDB.getTkt_amount();
						
						if(aList.equals(aListDB) &&tkt_amount.equals(tkt_amount_DB)) {
							continue;
						}else if (aList.equals(aListDB)) {
							shoppingSvc.updateShoppingList(tkt_id, member_id, tkt_amount);
						}
					}

				}

			}

			session.setAttribute("cart", cart);
			String url = "/front_end/ticket/cartDB_Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}

		if ("checkout".equals(action)) {
			int total = 0;
			for (int i = 0; i < cart.size(); i++) {
				ShoppingList everyList = cart.get(i);
				Integer price = everyList.getTicket().getTkt_price();
				Integer amout = everyList.getTkt_amount();
				total += (price * amout);
			}

			// cart也傳過去
			session.setAttribute("cart", cart);

			// 把價錢傳過去
//			String total_amount=String.valueOf(total);
			session.setAttribute("total_amount", total);
			String url = "/front_end/ticket/checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}

		if ("addCart".equals(action)) {

			ShoppingList one_list = getShoppingList(req);
			one_list.setMember_id(member_id);
//			
////			DB Cart(大吳建議不要混再一起看)
//			ShoppingListService shoppinglistSvc = new ShoppingListService();
//			List<ShoppingList> list = shoppinglistSvc.getAllbyMember(member_id);
//			if(list.size()>0) {
//				cart = new Vector<ShoppingList>();
//				for (int i = 0; i < list.size(); i++) {
//					ShoppingList aItem = list.get(i);
//					cart.add(aItem);
//				}
//				
//			}
//			

//			Session Cart
			if (cart == null) {
				cart = new Vector<ShoppingList>();
				cart.add(one_list);
			} else {
				if (cart.contains(one_list)) {
					ShoppingList originalShoppingList = cart.get(cart.indexOf(one_list));
					originalShoppingList.setTkt_amount(originalShoppingList.getTkt_amount() + one_list.getTkt_amount());
				} else {
					cart.add(one_list);

				}
			}

			session.setAttribute("cart", cart);
			String url = "/front_end/ticket/cartlist.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}

		if ("checklogin".equals(action)) {

//			Vector<ShoppingList> cart_DB = new Vector<ShoppingList>();
//			ShoppingListService shoppinglistSvc = new ShoppingListService();
//			List<ShoppingList> list_DB = shoppinglistSvc.getAllbyMember(member_id);
//			
//			if(cart==null) {
//			for (int i = 0; i < list_DB.size(); i++) {
//				ShoppingList aItem = list_DB.get(i);
//				cart_DB.add(aItem);
//			}
//			session.setAttribute("cart", cart_DB);
//
//			String url = "/frontEnd/ticket/cartlist.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
//			
//			}else {
//				for (int i = 0; i < list_DB.size(); i++) {
//					ShoppingList aItem = list_DB.get(i);
//					cart.add(aItem);
//				}
//				session.setAttribute("cart", cart);
//				String url = "/frontEnd/ticket/cartlist.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher(url);
//				rd.forward(req, res);
//			}

			ShoppingListService shoppinglistSvc = new ShoppingListService();
			List<ShoppingList> list_DB = shoppinglistSvc.getAllbyMember(member_id);

			if (cart == null) {
				Vector<ShoppingList> cart_DB = new Vector<ShoppingList>();
				for (int i = 0; i < list_DB.size(); i++) {
					ShoppingList aItem = list_DB.get(i);
					cart_DB.add(aItem);
				}
				session.setAttribute("cart", cart_DB);
				String url = "/front_end/ticket/cartlist.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			} else {
				String url = "/front_end/ticket/cartlist.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);

			}
//			session.setAttribute("cart", cart);
//
//			String url = "/frontEnd/ticket/cartlist.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);

//			}else {
//				for (int i = 0; i < list_DB.size(); i++) {
//					ShoppingList aItem = list_DB.get(i);
//					cart.add(aItem);
//				}
//				session.setAttribute("cart", cart);
//				String url = "/frontEnd/ticket/cartlist.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher(url);
//				rd.forward(req, res);
//			}

		}

	}

	private ShoppingList getShoppingList(HttpServletRequest req) {

//		MemberVO member=(MemberVO)session.getAttribute("memberVO");
//		Integer member_id=member.getMember_id();

		Integer tkt_id = Integer.valueOf(req.getParameter("tkt_id"));
//		Integer member_id=Integer.valueOf(3);//先寫死
		Integer tkt_amount = Integer.valueOf(req.getParameter("tkt_amount"));

		ShoppingList one_list = new ShoppingList();
		one_list.setTkt_id(tkt_id);
//		one_list.setMember_id(member_id);
		one_list.setTkt_amount(tkt_amount);
//		one_list.setCart_fav_date(new Timestamp(System.currentTimeMillis()));

		return one_list;

	}

}

package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itinerarycollection.model.ItineraryCollectionService;
import com.itineraryorder.model.ItineraryOrderService;
import com.itineraryorder.model.ItineraryOrderVO;
import com.member.errorMethod.ErrorMethod;
import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

@MultipartConfig
public class MemberServlet extends HttpServlet {
	/* prevent 405 method */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/* request from any jsp */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get request
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/* request for first search (name and id) */
		/* just condition for select(reason: need to do different error_message) */
		if ("s_memberName".equals(action)) {
			/* get input parameter from search */
			String str = req.getParameter("s_box");
			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* judge if input text is null */
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("selectMember", "•請輸入會員姓名");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/memberBack.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/* judge if input text is number */
			if (str.chars().allMatch(Character::isDigit)) {
				errorMsgs.put("selectMember", "•請輸入中文格式");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/memberBack.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/* judge is chinese or not */
			ErrorMethod em = new ErrorMethod();
			if (!(em.isContainChinese(str))) {
				errorMsgs.put("selectMember", "•請輸入中文格式");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/memberBack.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/* message's deal (not to do), give value->(first do) */
			String member_name = null; // initial for search
			member_name = str; // search name from get parameter
			/* start to search */
			MemberService memberSvc = new MemberService();
			List<MemberVO> list = memberSvc.getOneMember(member_name);
			/* judge list is empty or not */
			if (list.isEmpty()) {
				errorMsgs.put("selectMember", "•查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/memberBack.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/* end from search prepare include jsp */
			req.setAttribute("searchByOne", list); // need to set request let next one know
			String url = "/back_end/member/listOneMember.jsp"; // path of jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		} else if ("s_memberNo".equals(action)) {
			/* get input parameter to search */
			String str = req.getParameter("s_box");
			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* judge if input text is null */
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("selectMember", "•請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/memberBack.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/* message's deal (not to do), give value->(first do) */
			Integer member_id = null; // initial for search
			/* judge member_id is value */
			try {
				member_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("selectMember", "•會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/memberBack.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			member_id = Integer.valueOf(str); // search name from get parameter
			/* start to search */
			MemberService memberSvc = new MemberService();
			List<MemberVO> list = memberSvc.getOneMember(member_id);
			/* judge list is empty or not */
			if (list.isEmpty()) {
				errorMsgs.put("selectMember", "•查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/memberBack.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/* end from search prepare include jsp */
			req.setAttribute("searchByOne", list); // need to set request let next one know
			String url = "/back_end/member/listOneMember.jsp"; // path of jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		} else if ("not_choose_member".equals(action)) {
			/* set map to put error message */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			errorMsgs.put("selectMember", "•請選擇搜尋條件");
			String url = "/back_end/member/memberBack.jsp"; // path of jsp
			RequestDispatcher failureView = req.getRequestDispatcher(url);// start to transfrom
			failureView.forward(req, res);
			return; // stop process->just for fail
		}

		/* request for second search (state) */
		if ("s_enable".equals(action)) {
			/* set enable state */
			Byte member_state = null;
			member_state = 0;
			/* start to search */
			MemberService memberSvc = new MemberService();
			List<MemberVO> list = memberSvc.getListMember(member_state);
			/* end from search prepare include jsp */
			req.setAttribute("searchByState", list); // need to set request let next one know
			String url = "/back_end/member/listForState.jsp"; // path of jsp
			System.out.println(action);
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		} else if ("s_disable".equals(action)) {
			Byte member_state = null;
			member_state = 1;
			/* start to search */
			MemberService memberSvc = new MemberService();
			List<MemberVO> list = memberSvc.getListMember(member_state);
			/* end from search prepare include jsp */
			req.setAttribute("searchByState", list); // need to set request let next one know
			String url = "/back_end/member/listForState.jsp"; // path of jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		} else if ("not_choose_state".equals(action)) {
			/* set map to put error message */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			errorMsgs.put("selectState", "•請選擇狀態");
			String url = "/back_end/member/memberBack.jsp"; // path of jsp
			RequestDispatcher failureView = req.getRequestDispatcher(url);// start to transfrom
			failureView.forward(req, res);
			return; // stop process->just for fail
		}

		/* insert register data */

		if ("insert".equals(action)) {
			/* get input parameter for username(chinese) */
			String member_name = req.getParameter("member_name").trim();
			String member_email = req.getParameter("member_registEmail").trim();
			String member_pwd = req.getParameter("member_registPwd").trim();
			String member_pwd_confirm = req.getParameter("member_pwd_confirm").trim();
			Byte member_state = 0; // state default is 0 -> mean regist sucess
			MemberDAO dao = new MemberDAO();
			MemberVO memberData = new MemberVO();
			memberData = dao.findByEmail(member_email);
			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* name judge */
			/* judge input text is null */
			ErrorMethod errorMethod = new ErrorMethod();
			if (member_name == null || (member_name.trim()).length() == 0) {
				errorMsgs.put("member_name", "•請輸入會員姓名");
			}
			/* judge if input text is number */
			else if (member_name.chars().allMatch(Character::isDigit)) {
				errorMsgs.put("member_name", "•請輸入中文格式");
			}
			/* judge is chinese or not */
			else if (!(errorMethod.isChineseStr(member_name))) {
				errorMsgs.put("member_name", "•請輸入中文格式");
			}
			/* email judge */
			String mailRegx = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (member_email == null || (member_email.trim()).length() == 0) {
				errorMsgs.put("member_email", "•請輸入電子郵件");
			} else if (!(member_email.matches(mailRegx))) {
				errorMsgs.put("member_email", "•請輸入正確電子郵件格式");
			}
			/* password judge */
			String pwdRegx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
			/* judge input text is null */
			if (member_pwd == null || (member_pwd.trim()).length() == 0) {
				errorMsgs.put("member_pwd", "•請輸入密碼");
			}
			/* judge input pwd */
			else if (!(member_pwd.matches(pwdRegx))) {
				errorMsgs.put("member_pwd", "•密碼至少要8個數字,裡面起碼要包含一個英文字母,一個數字,以及一個特殊符號");
			}
			/* judge pwd confirm */
			if (member_pwd_confirm == null || (member_pwd_confirm.trim()).length() == 0) {
				errorMsgs.put("member_pwd_confirm", "•請輸入確認密碼");
			}
			/* judge input pwd confirm */
			else if (!(member_pwd_confirm.equals(member_pwd))) {
				errorMsgs.put("member_pwd_confirm", "•密碼和確認密碼不相同");
			}

			if (!errorMsgs.isEmpty()) {
				res.setCharacterEncoding("UTF-8");
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/regist.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			if (memberData == null) {
				/* set data from new object */
				MemberVO memberVO = new MemberVO();
				memberVO.setMember_name(member_name);
				memberVO.setMember_email(member_email);
				memberVO.setMember_pwd(member_pwd);
				memberVO.setMember_state(member_state);
				/* start to insert data in db */
				MemberService memberSvc = new MemberService();
				memberSvc.addMember(member_name, member_email, member_pwd, member_state);
			} else if (member_email.equals(memberData.getMember_email())) {
				errorMsgs.put("member_email", "•此帳號已註冊過,請換另一組電子郵件");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/regist.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/* start to transform jsp */
			String to = member_email;
			String subject = "註冊成功!";
			String ch_name = member_name;
//		      String passRandom = "111";
			String messageText = "此郵件是系統自動傳送，請勿直接回覆此郵件\n\n" + ch_name + "小姐/先生 您好:\n" + "您已經註冊成功!";
			MailService mailService = new MailService();
			mailService.sendMail(to, subject, messageText);
			String url = "/front_end/member/registSucess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/* update one member's state data */

		if ("btn-enable".equals(action)) {
			/* get parameter to set memberState and memberID */
			String strMemberID = req.getParameter("member");
			Integer member_id = null;
			member_id = Integer.valueOf(strMemberID);
			Byte member_state = null;
			member_state = 0;
			/* start to change state in db */
			MemberService memberSvc = new MemberService();
			memberSvc.changeState(member_state, member_id);
			/* start to transform to jsp */
			String url = "/back_end/member/memberBack.jsp"; // path of jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);// start to transfrom
			successView.forward(req, res);
		} else if ("btn-disable".equals(action)) {
			/* get parameter to set memberState and memberID */
			String strMemberID = req.getParameter("member");
			Integer member_id = null;
			member_id = Integer.valueOf(strMemberID);
			Byte member_state = null;
			member_state = 1;
			/* start to change state in db */
			MemberService memberSvc = new MemberService();
			memberSvc.changeState(member_state, member_id);
			/* start to transform to jsp */
			String url = "/back_end/member/memberBack.jsp"; // path of jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/* login for member, get member data and also do error msg */

		if ("login".equals(action)) {
			String member_email = req.getParameter("member_loginEmail");
			String member_pwd = req.getParameter("member_pwd");

			/* start to search data in db */
			MemberVO memberVO = new MemberVO();
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.loginMember(member_email);
			/* error dispose */
			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* input is null */
			if (member_email == null || (member_email.trim()).length() == 0) {
				errorMsgs.put("memberLogin_email", "•請輸入帳號");
			}
			if (member_pwd == null || (member_pwd.trim()).length() == 0) {
				errorMsgs.put("memberLogin_pwd", "•請輸入密碼");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/* account is right but pwd is wrong */
			if (memberVO == null) {
				errorMsgs.put("memberVO", "•請確認帳號密碼,如無註冊請至註冊頁面!");
			} else if (!(member_pwd.equals(memberVO.getMember_pwd()))) {
				errorMsgs.put("memberLogin_pwd", "•密碼有誤,請確認密碼!");
			} else if (memberVO.getMember_state() == 1) {
				errorMsgs.put("memberVO", "•此帳號已經停權,請聯絡客服處理");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*
			 * start to transform jsp ->first set parameter for transfrom->judge the pwd is
			 * right
			 */
			if (member_pwd.equals(memberVO.getMember_pwd())) {
				String url = "/front_end/member/home.jsp";
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(3600);
				session.setAttribute("memberVO", memberVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		/* update for member information */
		if ("updateMemberInfo".equals(action)) {
			/* Get parameter information */
			/* member_id->for who's member */
			String strMemberID = req.getParameter("member_id").trim();
			Integer member_id = null;
			member_id = Integer.valueOf(strMemberID);
			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* member_name */
			String member_firstname = req.getParameter("member_firstname").trim();
			String member_secondname = req.getParameter("member_secondname").trim();
			String member_name = member_firstname + member_secondname;

			ErrorMethod errorMethod = new ErrorMethod();
			if (member_firstname == null || (member_firstname.trim()).length() == 0) {
				errorMsgs.put("member_firstname", "•請輸入姓");
			} else if ((member_firstname.trim()).length() != 1) {
				errorMsgs.put("member_firstname", "•請輸入一個姓氏");
			} else if (!(errorMethod.isChineseStr(member_firstname))) {
				errorMsgs.put("member_firstname", "•請輸入中文格式");
			}
			if (member_secondname == null || (member_secondname.trim()).length() == 0) {
				errorMsgs.put("member_secondname", "•請輸入名");
			} else if (!(errorMethod.isChineseStr(member_secondname))) {
				errorMsgs.put("member_secondname", "•請輸入中文格式");
			}

			/* member_nameeng */
			String member_nameeng = req.getParameter("member_nameeng").trim();
			String nameengRegx = "^[A-Z]+[a-zA-Z]+$";

			if (member_nameeng == null || member_nameeng.length() == 0) {
				member_nameeng = "";
			} else if (member_name.chars().allMatch(Character::isDigit)) {
				errorMsgs.put("member_nameeng", "•請輸入英文格式");
			} else if (!(errorMethod.isEnglishStr(member_nameeng))) {
				errorMsgs.put("member_nameeng", "•請輸入英文格式");
			} else if (!(member_nameeng.matches(nameengRegx))) {
				errorMsgs.put("member_nameeng", "•請輸入開頭為大寫的英文字母");
			}

			/* member_idcard */
			String member_idcard = req.getParameter("member_idcard").trim();
			String idcardRegx = "^[A-Za-z][12]\\d{8}$";
			if (member_idcard == null || member_idcard.length() == 0) {
				member_idcard = "";
			} else if (member_idcard.length() != 10) {
				errorMsgs.put("member_idcard", "•請輸入十碼身分證字號");
			} else if (!(member_idcard.matches(idcardRegx))) {
				errorMsgs.put("member_idcard", "•請輸入正確身分證字號格式");
			}

			/* member_gender */
			String member_gender = null;
			if (req.getParameter("member_gender") == null) {
				member_gender = req.getParameter("member_gender_show");
			} else {
				if (req.getParameter("member_gender").equals("boy")) {
					member_gender = "男";
				} else if (req.getParameter("member_gender").equals("girl")) {
					member_gender = "女";
				} else if (req.getParameter("member_gender").equals("not_choose")) {
					member_gender = "";
				}
			}
			/* member_add */
			String city = req.getParameter("selectedLabel_city");
			String area = req.getParameter("selectedLabel_area");
			String update_address = req.getParameter("update_address").trim();
			String member_add = (city + area + update_address).trim();
			if (update_address.length() == 0 || update_address == null) {
				member_add = req.getParameter("member_add_show");
			} else if (member_add == null || member_add.length() == 0) {
				member_add = req.getParameter("member_add_show");
			}

			/* member_phone */
			String member_phone = req.getParameter("member_phone").trim();
			String phoneRegx = "^09[0-9]{8}$";
			if (member_phone == null || member_phone.length() == 0) {
				member_phone = "";
			} else if (member_phone.length() != 10) {
				errorMsgs.put("member_phone", "•請輸入十碼電話號碼");
			} else if (!(member_phone.matches(phoneRegx))) {
				errorMsgs.put("member_phone", "•請輸入正確台灣手機號碼格式");
			}

			if (!errorMsgs.isEmpty()) {
				String url = "/front_end/member/memberCenter.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/* start to update the data */
			MemberService memberSvc = new MemberService();
			memberSvc.changeMemberInfo(member_name, member_nameeng, member_idcard, member_gender, member_add,
					member_phone, member_id);
			/* start to transform jsp */
			String member_email = req.getParameter("member_email");
			MemberVO memberVO = new MemberVO();
			MemberDAO dao = new MemberDAO();
			memberVO = dao.findByEmail(member_email);
			member_name = memberVO.getMember_name();
			member_firstname = member_name.substring(0, 1);
			member_secondname = member_name.substring(1);
			String url = "/front_end/member/memberCenter.jsp";
			String param = "?member_firstname=" + member_firstname + "&member_secondname=" + member_secondname;
			HttpSession session = req.getSession(false);
			session.setAttribute("memberVO", memberVO);
			RequestDispatcher successView = req.getRequestDispatcher(url + param);
			successView.forward(req, res);
		}
		/* update member img */
		/* member img upload */
		if ("updateMemberImg".equals(action)) {
			byte[] member_img = req.getPart("upfile").getInputStream().readAllBytes();
			String strMemberID = req.getParameter("member_id");
			Integer member_id = Integer.valueOf(strMemberID);
			MemberService memberSvc = new MemberService();
			memberSvc.changeMemberImg(member_img, member_id);
			/* start to transform jsp */
			String member_email = req.getParameter("member_email");
			MemberVO memberVO = new MemberVO();
			MemberDAO dao = new MemberDAO();
			memberVO = dao.findByEmail(member_email);
			String member_name = memberVO.getMember_name();
			String member_firstname = member_name.substring(0, 1);
			String member_secondname = member_name.substring(1);
			String url = "/front_end/member/memberCenter.jsp";
			String param = "?member_firstname=" + member_firstname + "&member_secondname=" + member_secondname;
			HttpSession session = req.getSession(false);
			session.setAttribute("memberVO", memberVO);
			RequestDispatcher successView = req.getRequestDispatcher(url + param);
			successView.forward(req, res);
		}
		/* member img get from database */
		if ("getImg".equals(action)) {
			res.setContentType("image/jpg");
			ServletOutputStream out = res.getOutputStream();
			String strMemberID = req.getParameter("member_id");
			Integer member_id = Integer.valueOf(strMemberID);
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = new MemberVO();
			memberVO = memberSvc.getMemberImg(member_id);

			if (memberVO.getMember_img() == null) {
				InputStream in = getServletContext().getResourceAsStream("/front_end/member/img/member.jpg");
				byte[] originalPic = new byte[in.available()];
				in.read(originalPic);
				out.write(originalPic);
				out.close();
				in.close();
			} else {
				byte[] memberPIC = memberVO.getMember_img();
				out.write(memberPIC);
				out.close();
			}
		}
		/* change member account */
		if ("updateMemberAccount".equals(action)) {
			String oldEmail = req.getParameter("member_email").trim();
			String member_email = req.getParameter("member_newEmail").trim(); // this is new email
			String oldPwd = req.getParameter("member_oldPwd").trim();
			String member_pwd = req.getParameter("member_newPwd").trim();// this is new pwd -> remember can do it more**
			String confirmPwd = req.getParameter("member_newPwd_confirm").trim();
			String strMemberID = req.getParameter("member_id");
			Integer member_id = Integer.valueOf(strMemberID);

			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* account email */
			String mailRegx = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (!(member_email == null || (member_email.trim()).length() == 0)) {
				if (!(member_email.matches(mailRegx))) {
					errorMsgs.put("member_newEmail", "•請輸入正確電子郵件格式");
				} else if (member_email.equals(oldEmail)) {
					errorMsgs.put("member_newEmail", "•和原先帳號相同,請重新填寫");
				}
			}
			MemberDAO dao = new MemberDAO();
			MemberVO memberEmailCheck = new MemberVO();
			memberEmailCheck = dao.findByEmail(member_email);
			if (memberEmailCheck != null) {
				errorMsgs.put("member_newEmail", "•此帳號已經註冊,請重新填寫");
			}

			/* account pwd */
			MemberVO memberData = new MemberVO();
			memberData = dao.findByEmail(oldEmail);
			/* account pwd errror process */
			/* first */
			if (!(oldPwd == null || (oldPwd.trim()).length() == 0)) {

				if (!(oldPwd.equals(memberData.getMember_pwd()))) {
					errorMsgs.put("member_oldPwd", "•和原密碼不相同,請重新填寫");
				}

				if ((member_pwd == null || (member_pwd.trim()).length() == 0)
						&& (confirmPwd == null || (confirmPwd.trim()).length() == 0)) {
					errorMsgs.put("member_newPwd", "•請輸入新密碼");
					errorMsgs.put("member_newPwd_confirm", "•請輸入確認密碼");
				}

				if ((confirmPwd == null || (confirmPwd.trim()).length() == 0)
						&& (!(member_pwd == null || (member_pwd.trim()).length() == 0))) {
					String pwdRegx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
					errorMsgs.put("member_newPwd_confirm", "•請輸入確認密碼");
					if(member_pwd.equals(oldPwd)) {
						errorMsgs.put("member_newPwd", "•和原密碼相同,請重新填寫");
					}
					else if (!(member_pwd.matches(pwdRegx))) {
						errorMsgs.put("member_newPwd", "•密碼至少要8個數字,裡面起碼要包含一個英文字母,一個數字,以及一個特殊符號");
					}
				}

				if ((member_pwd == null || (member_pwd.trim()).length() == 0)
						&& (!(confirmPwd == null || (confirmPwd.trim()).length() == 0))) {
					errorMsgs.put("member_newPwd", "•請輸入新密碼");
				}

				if ((!(member_pwd == null || (member_pwd.trim()).length() == 0))
						&& (!(confirmPwd == null || (confirmPwd.trim()).length() == 0))) {
					String pwdRegx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
					if (!(member_pwd.matches(pwdRegx))) {
						errorMsgs.put("member_newPwd", "•密碼至少要8個數字,裡面起碼要包含一個英文字母,一個數字,以及一個特殊符號");
					}
					if (!(confirmPwd.equals(member_pwd))) {
						errorMsgs.put("member_newPwd_confirm", "•新密碼和確認密碼不相同");
					}
				}
			}
			/* second */
			if (!(member_pwd == null || (member_pwd.trim()).length() == 0)) {
				String pwdRegx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";

				if(member_pwd.equals(oldPwd)) {
					errorMsgs.put("member_newPwd", "•和原密碼相同,請重新填寫");
				}
				else if (!(member_pwd.matches(pwdRegx))) {
					errorMsgs.put("member_newPwd", "•密碼至少要8個數字,裡面起碼要包含一個英文字母,一個數字,以及一個特殊符號");
				}

				if ((oldPwd == null || (oldPwd.trim()).length() == 0)
						&& (confirmPwd == null || (confirmPwd.trim()).length() == 0)) {
					errorMsgs.put("member_oldPwd", "•請輸入舊密碼");
					errorMsgs.put("member_newPwd_confirm", "•請輸入確認密碼");
				}

				if ((confirmPwd == null || (confirmPwd.trim()).length() == 0)
						&& (!(oldPwd == null || (oldPwd.trim()).length() == 0))) {
					errorMsgs.put("member_newPwd_confirm", "•請輸入確認密碼");
					if (!(oldPwd.equals(memberData.getMember_pwd()))) {
						errorMsgs.put("member_oldPwd", "•和原密碼不相同,請重新填寫");
					}
				}

				if ((oldPwd == null || (oldPwd.trim()).length() == 0)
						&& (!(confirmPwd == null || (confirmPwd.trim()).length() == 0))) {
					errorMsgs.put("member_oldPwd", "•請輸入舊密碼");
					if (!(confirmPwd.equals(member_pwd))) {
						errorMsgs.put("member_newPwd_confirm", "•新密碼和確認密碼不相同");
					}
				}

				if ((!(oldPwd == null || (oldPwd.trim()).length() == 0))
						&& (!(confirmPwd == null || (confirmPwd.trim()).length() == 0))) {

					if (!(oldPwd.equals(memberData.getMember_pwd()))) {
						errorMsgs.put("member_oldPwd", "•和原密碼不相同,請重新填寫");
					}
					if (!(confirmPwd.equals(member_pwd))) {
						errorMsgs.put("member_newPwd_confirm", "•新密碼和確認密碼不相同");
					}
				}

			}
			/* third */
			if (!(confirmPwd == null || (confirmPwd.trim()).length() == 0)) {

				if ((member_pwd == null || (member_pwd.trim()).length() == 0)
						&& (oldPwd == null || (oldPwd.trim()).length() == 0)) {
					errorMsgs.put("member_newPwd", "•請輸入新密碼");
					errorMsgs.put("member_oldPwd", "•請輸入舊密碼");
				}

				if ((oldPwd == null || (oldPwd.trim()).length() == 0)
						&& (!(member_pwd == null || (member_pwd.trim()).length() == 0))) {
					errorMsgs.put("member_oldPwd", "•請輸入舊密碼");
					String pwdRegx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
					if (!(member_pwd.matches(pwdRegx))) {
						errorMsgs.put("member_newPwd", "•密碼至少要8個數字,裡面起碼要包含一個英文字母,一個數字,以及一個特殊符號");
					}
					if (!(confirmPwd.equals(member_pwd))) {
						errorMsgs.put("member_newPwd_confirm", "•新密碼和確認密碼不相同");
					}
				}

				if ((member_pwd == null || (member_pwd.trim()).length() == 0)
						&& (!(oldPwd == null || (oldPwd.trim()).length() == 0))) {
					errorMsgs.put("member_newPwd", "•請輸入新密碼");
					if (!(oldPwd.equals(memberData.getMember_pwd()))) {
						errorMsgs.put("member_oldPwd", "•和原密碼不相同,請重新填寫");
					}
				}

				if ((!(member_pwd == null || (member_pwd.trim()).length() == 0))
						&& (!(oldPwd == null || (oldPwd.trim()).length() == 0))) {
					String pwdRegx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
					if(member_pwd.equals(oldPwd)) {
						errorMsgs.put("member_newPwd", "•和原密碼相同,請重新填寫");
					}
					else if (!(member_pwd.matches(pwdRegx))) {
						errorMsgs.put("member_newPwd", "•密碼至少要8個數字,裡面起碼要包含一個英文字母,一個數字,以及一個特殊符號");
					}
					if (!(oldPwd.equals(memberData.getMember_pwd()))) {
						errorMsgs.put("member_oldPwd", "•和原密碼不相同,請重新填寫");
					}
					if (!(oldPwd.equals(memberData.getMember_pwd()))) {
						errorMsgs.put("member_oldPwd", "•和原密碼不相同,請重新填寫");
					}
				}
			}
			/* error message past */
			if (!errorMsgs.isEmpty()) {
				String member_name = req.getParameter("member_name");
				String member_firstname = member_name.substring(0, 1);
				String member_secondname = member_name.substring(1);
				String param = "?member_firstname=" + member_firstname + "&member_secondname=" + member_secondname;
				String url = "/front_end/member/memberCenter_Account.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url + param);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/* start to update the data */
			if ((!(member_pwd == null || (member_pwd.trim()).length() == 0))
					&& (!(member_email == null || (member_email.trim()).length() == 0))) {
				String to = oldEmail;
				String subject = "修改帳號和密碼成功!";
				String password = member_pwd;
				String newEmail = member_email;
				String messageText = "此郵件是系統自動傳送，請勿直接回覆此郵件\n\n" + "您好:\n" + "您已經修改帳號和密碼成功!\n" + "請記住修改之帳號 : " + newEmail
						+ "\n" + "請記住修改之密碼 : " + password;
				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);
			} else if (!(member_email == null || (member_email.trim()).length() == 0)) {
				String to = oldEmail;
				String subject = "修改帳號成功!";
				String newEmail = member_email;
				String messageText = "此郵件是系統自動傳送，請勿直接回覆此郵件\n\n" + "您好:\n" + "您已經修改帳號成功!\n" + "請記住修改之帳號 : " + newEmail;
				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);
			} else if (!(member_pwd == null || (member_pwd.trim()).length() == 0)) {
				String to = oldEmail;
				String subject = "修改密碼成功!";
				String password = member_pwd;
				String messageText = "此郵件是系統自動傳送，請勿直接回覆此郵件\n\n" + "您好:\n" + "您已經修改密碼成功!\n" + "請記住修改之密碼 : " + password;
				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);
			}
			/* if null */
			if ((member_pwd == null || (member_pwd.trim()).length() == 0)
					&& (oldPwd == null || (oldPwd.trim()).length() == 0)
					&& (confirmPwd == null || (confirmPwd.trim()).length() == 0)) {
				member_pwd = memberData.getMember_pwd();
			}
			if (member_email == null || (member_email.trim()).length() == 0) {
				member_email = memberData.getMember_email();
			}
			MemberService memberSvc = new MemberService();
			memberSvc.changeMemberAccount(member_email, member_pwd, member_id);
			/* Success past */
			MemberVO memberVO = new MemberVO();
			memberVO = dao.findByEmail(member_email);
			String member_name = memberVO.getMember_name();
			String member_firstname = member_name.substring(0, 1);
			String member_secondname = member_name.substring(1);
			String url = "/front_end/member/memberCenter.jsp";
			String param = "?member_firstname=" + member_firstname + "&member_secondname=" + member_secondname
					+ "&member_newEmail=" + "&member_oldPwd=" + "&member_newPwd=" + "&member_newPwd_confirm=";
			HttpSession session = req.getSession(false);
			session.setAttribute("memberVO", memberVO);
			RequestDispatcher successView = req.getRequestDispatcher(url + param);
			successView.forward(req, res);
		}

		/* error message past */
		if ("forgivePwd".equals(action)) {
			String member_email = req.getParameter("member_Email").trim();
			String member_pwd = req.getParameter("member_newPwd").trim();
			String confirmPwd = req.getParameter("member_newPwd_confirm").trim();

			/* error porcess */
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* email error */
			MemberVO memberData = new MemberVO();
			MemberDAO dao = new MemberDAO();
			memberData = dao.findByEmail(member_email);
			if (member_email == null || (member_email.trim()).length() == 0) {
				errorMsgs.put("member_Email", "•請輸入會員帳號");
			} else if (memberData == null) {
				errorMsgs.put("member_Email", "•無會員帳號");
			}
			/* pwd error */
			String pwdRegx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
			if (member_pwd == null || (member_pwd.trim()).length() == 0) {
				errorMsgs.put("member_newPwd", "•請輸入密碼");
			} else if (!(member_pwd.matches(pwdRegx))) {
				errorMsgs.put("member_newPwd", "•密碼至少要8個數字,裡面起碼要包含一個英文字母,一個數字,以及一個特殊符號");
			}
			/* confirm pwd error */
			if (confirmPwd == null || (confirmPwd.trim()).length() == 0) {
				errorMsgs.put("member_newPwd_confirm", "•請輸入確認密碼");
			} else if (!(confirmPwd.equals(member_pwd))) {
				errorMsgs.put("member_newPwd_confirm", "•密碼和確認密碼不相同,請重新填寫");
			}
			/* error past */
			if (!errorMsgs.isEmpty()) {
				String url = "/front_end/member/forgivePwd.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/* success process */
			String to = member_email;
			String subject = "修改密碼成功!";
			String password = member_pwd;
			String messageText = "此郵件是系統自動傳送，請勿直接回覆此郵件\n\n" + "您好:\n" + "您已經修改密碼成功!\n" + "請記住修改之密碼 : " + password;
			MailService mailService = new MailService();
			mailService.sendMail(to, subject, messageText);
			MemberService memberSvc = new MemberService();
			memberSvc.forgiveMemberPwd(member_pwd, member_email);
			/* success past */
			String url = "/front_end/member/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

      	/*click member center*/
		if ("clickMemberCenter".equals(action)) {
			MemberDAO dao = new MemberDAO();
			MemberVO memberVO = new MemberVO();
			String member_email = req.getParameter("member_email");
			memberVO = dao.findByEmail(member_email);
			String member_name = memberVO.getMember_name();
			String member_firstname = member_name.substring(0, 1);
			String member_secondname = member_name.substring(1);
			String url = "/front_end/member/memberCenter.jsp";
			String param = "?member_firstname=" + member_firstname + "&member_secondname=" + member_secondname
					+ "&member_newEmail=" + "&member_oldPwd=" + "&member_newPwd=" + "&member_newPwd_confirm=";
			HttpSession session = req.getSession(false);
			session.setAttribute("memberVO", memberVO);
			RequestDispatcher successView = req.getRequestDispatcher(url + param);
			successView.forward(req, res);
		}
         /*LogOut*/
		if("logOut".equals(action)) {
			HttpSession session = req.getSession(false);
			String contextPath = req.getContextPath();
			session.invalidate();
			res.sendRedirect(contextPath+"/front_end/member/home.jsp");
		}
		
		
	}
}

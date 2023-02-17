package com.tktlist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;
import com.ticket.model.Ticket;
import com.ticket.model.TicketService;
import com.tktimg.model.TktImg;
import com.tktimg.model.TktImgService;
import com.tktlist.model.TktList;
import com.tktlist.model.TktListService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


class JedisUtil {
	// Singleton單例模式
	private static JedisPool pool = null;

	private JedisUtil() {
	}

	public static JedisPool getJedisPool() {
		// double lock
		if (pool == null) {
			synchronized(JedisUtil.class) {
				if (pool == null) {
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(24);
					config.setMaxIdle(0);
					config.setMaxWaitMillis(1000);
					pool = new JedisPool(config, "localhost", 6379);
				}
			}
		}
		return pool;
	}

	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}

}

public class TktListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		MemberVO member=(MemberVO)session.getAttribute("memberVO");
		Integer member_id=member.getMember_id();
		
		
		
		
		//用REDIS來新增???
		if("addListRedis".equals(action)) {
			
		//開Redis連線
			JedisPool pool = JedisUtil.getJedisPool();
			Jedis jedis = pool.getResource();
			
			HashMap<String, String> memberFav_list;
			String current_list;
			String member_id2=String.valueOf(member_id);
			String tkt_id= req.getParameter("tkt_id");
			
			

			
		
				memberFav_list=(HashMap<String, String>) jedis.hgetAll("memberlist");
				current_list=memberFav_list.get(member_id2);
				if(current_list==null) {
					memberFav_list.put(member_id2, tkt_id);
					jedis.hmset("memberlist", memberFav_list);
				
				}else if(current_list.trim().length()==0) {
						memberFav_list.replace(member_id2, tkt_id);
						jedis.hmset("memberlist", memberFav_list);
					
					
				}else {
				memberFav_list.put(member_id2, current_list+","+tkt_id);
				jedis.hmset("memberlist", memberFav_list);

				}


			
			
			req.setAttribute("member_id", member_id2);
			String url = "/front_end/ticket/redisTktlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
			
			
			
			
			
			//關Redis連線
//			jedis.close();
//			JedisUtil.shutdownJedisPool();
			
			
		}
		
		
		
		
		if("addList".equals(action)) {
			
			
//			into Redis DB
			JedisPool pool = JedisUtil.getJedisPool();
			Jedis jedis = pool.getResource();
			
			HashMap<String, String> memberFav_list;
			String current_list;
			String member_id2=String.valueOf(member_id);
			String tkt_id= req.getParameter("tkt_id");
			
			

			
		
				memberFav_list=(HashMap<String, String>) jedis.hgetAll("memberlist");
				current_list=memberFav_list.get(member_id2);
				if(current_list==null) {
					memberFav_list.put(member_id2, tkt_id);
					jedis.hmset("memberlist", memberFav_list);
				
				}else if(current_list.trim().length()==0) {
						memberFav_list.replace(member_id2, tkt_id);
						jedis.hmset("memberlist", memberFav_list);
					
					
				}else {
				memberFav_list.put(member_id2, current_list+","+tkt_id);
				jedis.hmset("memberlist", memberFav_list);

				}


			
			

			
			
			
			
//			into SQL DB
//			Integer member_id1=2;
			Integer tkt_id1= Integer.valueOf(req.getParameter("tkt_id"));
			
			//TktList 
			TktListService tktlistSvc=new TktListService();
			tktlistSvc.addTktList(tkt_id1, member_id);
			List<TktList> list=tktlistSvc.findByMemberID(member_id);
			
			
			req.setAttribute("member_id", member_id2);
			String url = "/front_end/ticket/redisTktlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
//			//TktImg
//			TktImgService tktimgSvc =new TktImgService();
//			List<TktImg>list_img=tktimgSvc.findByTktId(tkt_id1);
//			
//			req.setAttribute("list", list);
//			req.setAttribute("tkt_id", tkt_id1);
//			
//			String url = "/front_end/ticket/tktlist.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);

			
		}
		
		
		if("delete".equals(action)) {
			
			
			
//			into SQL DB
			Integer tkt_id1= Integer.valueOf(req.getParameter("tkt_id"));
			TktListService tktlistSvc=new TktListService();
			tktlistSvc.deleteTktList(tkt_id1, member_id);
//			List<TktList> list=tktlistSvc.findByMemberID(member_id);
//			req.setAttribute("list", list);
//			
//			String url = "/front_end/ticket/tktlist.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			
			
//			into Redis DB
			JedisPool pool = JedisUtil.getJedisPool();
			Jedis jedis = pool.getResource();
			HashMap<String, String> memberFav_list;
			String member_id2=String.valueOf(member_id);//先寫死
			String tkt_id= req.getParameter("tkt_id");
			System.out.println(tkt_id);
			List<String> xx = jedis.hmget("memberlist", member_id2);
			String[] aa = null;
			List<String>bb= new ArrayList();
			aa = xx.get(0).split(",");
			System.out.println("aa.length="+aa.length);
			for(int i=0;i<aa.length;i++) {
				String var=aa[i];
				System.out.println(var);
				if(var.equals(tkt_id)) {
					continue; 
				}else {
					System.out.println("bb="+var);
					bb.add(var);
				}
			}
			System.out.println("bb size="+bb.size());
			String new_element="";
			
			for(int i=0;i<bb.size();i++) {
				new_element+=bb.get(i)+",";
			}
			
			
			memberFav_list=(HashMap<String, String>) jedis.hgetAll("memberlist");
			memberFav_list.replace(member_id2, new_element);
			jedis.hmset("memberlist", memberFav_list);



			req.setAttribute("member_id", member_id2);
			String url = "/front_end/ticket/redisTktlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		
		
		
		if("deleteRedis".equals(action)) {
			
			JedisPool pool = JedisUtil.getJedisPool();
			Jedis jedis = pool.getResource();
			HashMap<String, String> memberFav_list;
			String member_id2=String.valueOf(member_id);//先寫死
			String tkt_id= req.getParameter("tkt_id");
			System.out.println(tkt_id);
			List<String> xx = jedis.hmget("memberlist", member_id2);
			String[] aa = null;
			List<String>bb= new ArrayList();
			aa = xx.get(0).split(",");
			System.out.println("aa.length="+aa.length);
			for(int i=0;i<aa.length;i++) {
				String var=aa[i];
				System.out.println(var);
				if(var.equals(tkt_id)) {
					continue; 
				}else {
					System.out.println("bb="+var);
					bb.add(var);
				}
			}
			System.out.println("bb size="+bb.size());
			String new_element="";
			
			for(int i=0;i<bb.size();i++) {
				new_element+=bb.get(i)+",";
			}
			
			
			memberFav_list=(HashMap<String, String>) jedis.hgetAll("memberlist");
			memberFav_list.replace(member_id2, new_element);
			jedis.hmset("memberlist", memberFav_list);



			req.setAttribute("member_id", member_id2);
			String url = "/front_end/ticket/redisTktlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
	
	
}
}


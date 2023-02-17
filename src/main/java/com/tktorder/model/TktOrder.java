package com.tktorder.model;

import java.sql.Timestamp;

public class TktOrder implements java.io.Serializable{
	
		private Integer tkt_order_id;
		private Integer member_id;
		private Timestamp order_date;
		private Integer promo_id;
		private Integer total;
		private Byte tkt_order_state;
		private Integer total_discount;
		
		public Integer getTkt_order_id() {
			return tkt_order_id;
		}
		public void setTkt_order_id(Integer tkt_order_id) {
			this.tkt_order_id = tkt_order_id;
		}
		public Integer getMember_id() {
			return member_id;
		}
		public void setMember_id(Integer member_id) {
			this.member_id = member_id;
		}
		public Timestamp getOrder_date() {
			return order_date;
		}
		public void setOrder_date(Timestamp order_date) {
			this.order_date = order_date;
		}
		public Integer getPromo_id() {
			return promo_id;
		}
		public void setPromo_id(Integer promo_id) {
			this.promo_id = promo_id;
		}
		public Integer getTotal() {
			return total;
		}
		public void setTotal(Integer total) {
			this.total = total;
		}
		public Byte getTkt_order_state() {
			return tkt_order_state;
		}
		public void setTkt_order_state(Byte tkt_order_state) {
			this.tkt_order_state = tkt_order_state;
		}
		public Integer getTotal_discount() {
			return total_discount;
		}
		
		public void setTotal_discount(Integer total_discount) {
			this.total_discount = total_discount;
		}
		
		public com.member.model.MemberVO getMember(){
			com.member.model.MemberService memberSvc = new com.member.model.MemberService();
			com.member.model.MemberVO member=memberSvc.getOneMember_Ticket(member_id);
			return member;
		}
		
		public com.ticketpromote.model.TicketPromote getTicketPromote(){
			com.ticketpromote.model.TicketPromoteService ticketpromoteSvc=new com.ticketpromote.model.TicketPromoteService();
			com.ticketpromote.model.TicketPromote ticketpromote=ticketpromoteSvc.getOneTicketPromote(promo_id);
			return ticketpromote;
		}
		
		
		
		
	}


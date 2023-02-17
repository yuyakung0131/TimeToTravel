package com.itineraryorder.model;

import java.sql.Timestamp;

public class ItineraryOrderVO implements java.io.Serializable{
	private Integer itinerary_order_id;
	private Integer member_id;
	private Integer itinerary_id;
	private Timestamp itinerary_order_time;
	private Integer itinerary_people_num;
	private Integer itinerary_ttl_price;
	private Byte itinerary_order_state;
	private Byte itinerary_refund_state;
	private String itinerary_order_memo;

	public Integer getItinerary_order_id() {
		return itinerary_order_id;
	}

	public void setItinerary_order_id(Integer itinerary_order_id) {
		this.itinerary_order_id = itinerary_order_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getItinerary_id() {
		return itinerary_id;
	}

	public void setItinerary_id(Integer itinerary_id) {
		this.itinerary_id = itinerary_id;
	}

	public Timestamp getItinerary_order_time() {
		return itinerary_order_time;
	}

	public void setItinerary_order_time(Timestamp itinerary_order_time) {
		this.itinerary_order_time = itinerary_order_time;
	}

	public Integer getItinerary_people_num() {
		return itinerary_people_num;
	}

	public void setItinerary_people_num(Integer itinerary_people_num) {
		this.itinerary_people_num = itinerary_people_num;
	}

	public Integer getItinerary_ttl_price() {
		return itinerary_ttl_price;
	}

	public void setItinerary_ttl_price(Integer itinerary_ttl_price) {
		this.itinerary_ttl_price = itinerary_ttl_price;
	}

	public byte getItinerary_order_state() {
		return itinerary_order_state;
	}

	public void setItinerary_order_state(byte itinerary_order_state) {
		this.itinerary_order_state = itinerary_order_state;
	}

	public Byte getItinerary_refund_state() {
		return itinerary_refund_state;
	}

	public void setItinerary_refund_state(byte itinerary_refund_state ) {
		this.itinerary_refund_state = itinerary_refund_state;
	}

	public String getItinerary_order_memo() {
		return itinerary_order_memo;
	}

	public void setItinerary_order_memo(String itinerary_order_memo) {
		this.itinerary_order_memo = itinerary_order_memo;
	}
	
	 public com.member.model.MemberVO getMemberVO() {
		    com.member.model.MemberService itineraryClassSvc = new com.member.model.MemberService();
		    com.member.model.MemberVO memberVO = itineraryClassSvc.getOneMemberITR(member_id);
		    return memberVO;
	    }
	 
	 public com.itinerary.model.ItineraryVO getItineraryVO() {
		 com.itinerary.model.ItineraryService itinerarySvc = new com.itinerary.model.ItineraryService();
		 com.itinerary.model.ItineraryVO itineraryVO = itinerarySvc.getOneItinerary(itinerary_id);
		 return itineraryVO;
	 }

}
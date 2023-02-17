package com.itinerarycomment.model;

import java.sql.Timestamp;

import com.member.model.MemberVO;

public class ItineraryCommentVO implements java.io.Serializable{
	private Integer itinerary_comment_id;
	private Integer itinerary_type_id;
	private Integer member_id;
	private String itinerary_comment_post;
	private Integer itinerary_comment_star;
	private Timestamp itinerary_comment_time;

	public Integer getItinerary_comment_id() {
		return itinerary_comment_id;
	}

	public void setItinerary_comment_id(Integer itinerary_comment_id) {
		this.itinerary_comment_id = itinerary_comment_id;
	}

	public Integer getItinerary_type_id() {
		return itinerary_type_id;
	}

	public void setItinerary_type_id(Integer itinerary_type_id) {
		this.itinerary_type_id = itinerary_type_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getItinerary_comment_post() {
		return itinerary_comment_post;
	}

	public void setItinerary_comment_post(String itinerary_comment_post) {
		this.itinerary_comment_post = itinerary_comment_post;
	}

	public Integer getItinerary_comment_star() {
		return itinerary_comment_star;
	}

	public void setItinerary_comment_star(Integer itinerary_comment_star) {
		this.itinerary_comment_star = itinerary_comment_star;
	}

	public Timestamp getItinerary_comment_time() {
		return itinerary_comment_time;
	}

	public void setItinerary_comment_time(Timestamp itinerary_comment_time) {
		this.itinerary_comment_time = itinerary_comment_time;
	}
	
    public com.member.model.MemberVO getMemberVO() {
    		com.member.model.MemberService memSvc = new com.member.model.MemberService();
    		com.member.model.MemberVO memberVO = memSvc.getOneMemberITR(member_id);
	    return memberVO;
    }
    
 

}
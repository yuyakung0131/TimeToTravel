package com.itinerarycomment.model;

import java.util.List;

import com.itinerarytype.model.ItineraryTypeService;



public class ItineraryCommentService {
	private ItineraryCommentDAO_interface dao;

	
	public ItineraryCommentService() {
		dao = new ItineraryCommentDAO();
	}
//	public ItineraryCommentService() {
//		dao = new ItineraryCommentJDBCDAO();
//	}
	
	
	
	
	public ItineraryCommentVO addItineraryComment(Integer itinerary_type_id,Integer member_id,String itinerary_comment_post,Integer itinerary_comment_star) {
		
		ItineraryCommentVO itineraryCommentVO = new ItineraryCommentVO();
		itineraryCommentVO.setItinerary_type_id(itinerary_type_id);
		itineraryCommentVO.setMember_id(member_id);
		itineraryCommentVO.setItinerary_comment_post(itinerary_comment_post);
		itineraryCommentVO.setItinerary_comment_star(itinerary_comment_star);
		
		dao.insert(itineraryCommentVO);
		return itineraryCommentVO;
	}
	
	public ItineraryCommentVO updateItineraryComment(Integer itinerary_comment_id,Integer itinerary_type_id,Integer member_id,String itinerary_comment_post,Integer itinerary_comment_star) {
		ItineraryCommentVO itineraryCommentVO = new ItineraryCommentVO();
		itineraryCommentVO.setItinerary_comment_id(itinerary_comment_id);
		itineraryCommentVO.setItinerary_type_id(itinerary_type_id);
		itineraryCommentVO.setMember_id(member_id);
		itineraryCommentVO.setItinerary_comment_post(itinerary_comment_post);
		itineraryCommentVO.setItinerary_comment_star(itinerary_comment_star);
		dao.update(itineraryCommentVO);
		return dao.findByPrimaryKey(itinerary_comment_id);
		
	}
	
	public List<ItineraryCommentVO> getAll() {
		return dao.getAll();
	}
	
	public ItineraryCommentVO getOneItineraryComment(Integer itinerary_comment_id) {
		return dao.findByPrimaryKey(itinerary_comment_id);
	}
	
}

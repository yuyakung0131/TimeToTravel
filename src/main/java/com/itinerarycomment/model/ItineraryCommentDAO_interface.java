package com.itinerarycomment.model;

import java.util.List;

public interface ItineraryCommentDAO_interface {
	public void insert(ItineraryCommentVO itineraryCommentVO);
	public List<ItineraryCommentVO> getAll();
	public void update(ItineraryCommentVO itineraryCommentVO);
	public ItineraryCommentVO findByPrimaryKey(Integer itinerary_Comment_id);
}

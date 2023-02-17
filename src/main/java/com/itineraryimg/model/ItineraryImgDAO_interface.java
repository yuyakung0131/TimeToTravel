package com.itineraryimg.model;

import java.util.List;

public interface ItineraryImgDAO_interface {
	public void insert(ItineraryImgVO itineraryImgVO);
	public List<ItineraryImgVO> getAll();
	public ItineraryImgVO findByPrimaryKey(Integer itinerary_img_id);
	public void update(ItineraryImgVO itineraryImgVO);
	public void delete(Integer itinerary_img_id);
	public List<ItineraryImgVO> findByItineraryTypeId(Integer itinerary_type_id);
	public List<ItineraryImgVO> getItrImgByType(Integer itinerary_type_id);
	
}

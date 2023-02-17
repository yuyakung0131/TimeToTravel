package com.itineraryloc.model;

import java.util.List;

public class ItineraryLocService {
	private ItineraryLocDAO_interface dao;

	public ItineraryLocService() {
		dao = new ItineraryLocDAO();
	}
//	public ItineraryLocService() {
//		dao = new ItineraryLocJDBCDAO();
//	}
	
	public ItineraryLocVO addItineraryLoc(String itinerary_loc_name) {
		
		ItineraryLocVO itineraryLocVO = new ItineraryLocVO();
		itineraryLocVO.setItinerary_loc_name(itinerary_loc_name);
		dao.insert(itineraryLocVO);
		return itineraryLocVO;
	}
	
	public ItineraryLocVO updateItineraryLoc(Integer itinerary_loc_id,String itinerary_loc_name ) {
		ItineraryLocVO itineraryLocVO = new ItineraryLocVO();
		itineraryLocVO.setItinerary_loc_id(itinerary_loc_id);
		itineraryLocVO.setItinerary_loc_name(itinerary_loc_name);
		dao.update(itineraryLocVO);
		return dao.findByPrimaryKey(itinerary_loc_id);
		
	}
	
	public List<ItineraryLocVO> getAll() {
		return dao.getAll();
	}
	
	public ItineraryLocVO getOneItineraryLoc(Integer itinerary_loc_id) {
		return dao.findByPrimaryKey(itinerary_loc_id);
	}
	
}

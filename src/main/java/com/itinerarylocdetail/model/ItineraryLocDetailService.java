package com.itinerarylocdetail.model;

import java.util.List;


public class ItineraryLocDetailService {
	private ItineraryLocDetailDAO_interface dao;

	public ItineraryLocDetailService() {
		dao = new ItineraryLocDetailDAO();
	}
//	public ItineraryLocDetailService() {
//		dao = new ItineraryLocDetailJDBCDAO();
//	}
	
	public ItineraryLocDetailVO addItineraryLocDetail(Integer itinerary_loc_id,Integer itinerary_type_id) {
		
		ItineraryLocDetailVO itineraryLocDetailVO = new ItineraryLocDetailVO();
		itineraryLocDetailVO.setItinerary_loc_id(itinerary_loc_id);
		itineraryLocDetailVO.setItinerary_type_id(itinerary_type_id);
		dao.insert(itineraryLocDetailVO);
		return itineraryLocDetailVO;
	}
	
	public ItineraryLocDetailVO updateItineraryLocDetail(Integer itinerary_loc_id,Integer itinerary_type_id ) {
		ItineraryLocDetailVO itineraryLocDetailVO = new ItineraryLocDetailVO();
		itineraryLocDetailVO.setItinerary_loc_id(itinerary_loc_id);
		itineraryLocDetailVO.setItinerary_type_id(itinerary_type_id);
		dao.update(itineraryLocDetailVO);
		return dao.findByPrimaryKey(itinerary_loc_id,itinerary_type_id);
		
	}
	
	public List<ItineraryLocDetailVO> getAll() {
		return dao.getAll();
	}
	
	public ItineraryLocDetailVO getOneItineraryLocDetail(Integer itinerary_loc_id,Integer itinerary_type_id ) {
		return dao.findByPrimaryKey(itinerary_loc_id,itinerary_type_id);
	}
	
}

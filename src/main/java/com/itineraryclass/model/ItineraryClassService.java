package com.itineraryclass.model;

import java.util.List;

public class ItineraryClassService {
	private ItineraryClassDAO_interface dao;

	public ItineraryClassService() {
		dao = new ItineraryClassDAO();
	}

	public ItineraryClassVO addItineraryClass(String itinerary_class_name) {

		ItineraryClassVO itineraryClassVO = new ItineraryClassVO();

		itineraryClassVO.setItinerary_class_name(itinerary_class_name);
		
		dao.insert(itineraryClassVO);

		return itineraryClassVO;
	}

	public ItineraryClassVO updateItineraryClass(Integer itinerary_class_id, String itinerary_class_name) {

		ItineraryClassVO itineraryClassVO = new ItineraryClassVO();

		itineraryClassVO.setItinerary_class_id(itinerary_class_id);
		itineraryClassVO.setItinerary_class_name(itinerary_class_name);
		
		dao.update(itineraryClassVO);

		return itineraryClassVO;
	}


	public ItineraryClassVO getOneItineraryClass(Integer itinerary_class_id) {
		return dao.findByPrimaryKey(itinerary_class_id);
	}

	public List<ItineraryClassVO> getAll() {
		return dao.getAll();
	}
}

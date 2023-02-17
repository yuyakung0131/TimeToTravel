package com.itinerary.model;

import java.sql.Date;
import java.util.List;

import com.itinerary.model.ItineraryVO;

public class ItineraryService {

	private ItineraryDAO_interface dao;

	public ItineraryService() {
		dao = new ItineraryDAO();
	}

	public ItineraryVO addItinerary(Integer itinerary_type_id, Date itinerary_start, Date itinerary_end,
			Integer itinerary_now, Integer itinerary_price, Integer itinerary_min, 
			Integer itinerary_max, Date entered_start, Date entered_end, Byte itinerary_state) {

		ItineraryVO itineraryVO = new ItineraryVO();

		itineraryVO.setItinerary_type_id(itinerary_type_id);
		itineraryVO.setItinerary_start(itinerary_start);
		itineraryVO.setItinerary_end(itinerary_end);
		itineraryVO.setItinerary_now(itinerary_now);
		itineraryVO.setItinerary_price(itinerary_price);
		itineraryVO.setItinerary_min(itinerary_min);
		itineraryVO.setItinerary_max(itinerary_max);
		itineraryVO.setEntered_start(entered_start);
		itineraryVO.setEntered_end(entered_end);
		itineraryVO.setItinerary_state(itinerary_state);
		dao.insert(itineraryVO);

		return itineraryVO;
	}

	public ItineraryVO updateItinerary(Integer itinerary_id,Integer itinerary_type_id, Date itinerary_start, Date itinerary_end,
			Integer itinerary_now, Integer itinerary_price, Integer itinerary_min, 
			Integer itinerary_max, Date entered_start, Date entered_end, Byte itinerary_state) {

		ItineraryVO itineraryVO = new ItineraryVO();
		
		itineraryVO.setItinerary_id(itinerary_id);
		itineraryVO.setItinerary_type_id(itinerary_type_id);
		itineraryVO.setItinerary_start(itinerary_start);
		itineraryVO.setItinerary_end(itinerary_end);
		itineraryVO.setItinerary_now(itinerary_now);
		itineraryVO.setItinerary_price(itinerary_price);
		itineraryVO.setItinerary_min(itinerary_min);
		itineraryVO.setItinerary_max(itinerary_max);
		itineraryVO.setEntered_start(entered_start);
		itineraryVO.setEntered_end(entered_end);
		itineraryVO.setItinerary_state(itinerary_state);
		dao.update(itineraryVO);

		return dao.findByPrimaryKey(itinerary_id);
	}
	
	public List<ItineraryVO> getAll() {
		return dao.getAll();
	}
	
	public ItineraryVO getOneItinerary(Integer itinerary_id) {
		return dao.findByPrimaryKey(itinerary_id);
	}
	
	public List<ItineraryVO> getItineraryType(Integer itinerary_type_id){
		return dao.findByItinerary_type_id(itinerary_type_id);
		
	}
	
}
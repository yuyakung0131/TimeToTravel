package com.itinerarycollection.model;

import java.util.List;

import com.itinerary.model.ItineraryVO;



public class ItineraryCollectionService {
	private ItineraryCollectionDAO_interface dao;

	public ItineraryCollectionService() {
		dao = new ItineraryCollectionDAO();
	}

	public ItineraryCollectionVO addItineraryCollection(Integer member_id, Integer itinerary_type_id) {

		ItineraryCollectionVO itineraryCollectionVO = new ItineraryCollectionVO();

		itineraryCollectionVO.setMember_id(member_id);
		itineraryCollectionVO.setItinerary_type_id(itinerary_type_id);

		
		dao.insert(itineraryCollectionVO);

		return itineraryCollectionVO;
	}

	public ItineraryCollectionVO updateItineraryCollection(Integer member_id, Integer itinerary_type_id) {

		ItineraryCollectionVO itineraryCollectionVO = new ItineraryCollectionVO();

		itineraryCollectionVO.setMember_id(member_id);
		itineraryCollectionVO.setItinerary_type_id(itinerary_type_id);
		
		dao.update(itineraryCollectionVO);

		return itineraryCollectionVO;
	}


	public ItineraryCollectionVO getCollectionByMember(Integer member_id) {
		return dao.findByPrimaryKey(member_id);
	}
	
	public ItineraryCollectionVO getOneItineraryCollection(Integer member_id,Integer itinerary_type_id) {
		return dao.getOneItineraryCollection(member_id, itinerary_type_id);
	}

	public List<ItineraryCollectionVO> getAll() {
		return dao.getAll();
	}
	
	public List<ItineraryCollectionVO> getItineraryType(Integer itinerary_type_id){
		return dao.findByItinerary_type_id(itinerary_type_id);
	}
	
	public void delete(Integer member_id, Integer itinerary_type_id) {
		dao.delete(member_id, itinerary_type_id);
	}
	
	public List<ItineraryCollectionVO> getCollectionListByMember(Integer member_id) {
		return dao.getCollectionList(member_id);
	}
}

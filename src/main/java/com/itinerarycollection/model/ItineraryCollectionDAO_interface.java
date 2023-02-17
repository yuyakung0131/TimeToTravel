package com.itinerarycollection.model;

import java.util.List;



public interface ItineraryCollectionDAO_interface {
	public List<ItineraryCollectionVO> findByItinerary_type_id(Integer itinerary_type_id);
	public void insert(ItineraryCollectionVO itineraryCollectionVO);
	public void update(ItineraryCollectionVO itineraryCollectionVO);
	public List<ItineraryCollectionVO> getAll();
	public ItineraryCollectionVO findByPrimaryKey(Integer member_id);
	public void delete(Integer member_id, Integer itinerary_type_id);
	public ItineraryCollectionVO getOneItineraryCollection(Integer member_id, Integer itinerary_type_id);
	public List<ItineraryCollectionVO> getCollectionList(Integer member_id);
}

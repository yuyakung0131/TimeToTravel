package com.itinerarylocdetail.model;

import java.util.List;

public interface ItineraryLocDetailDAO_interface {
	public void insert(ItineraryLocDetailVO itineraryLocDetailVO);
	public List<ItineraryLocDetailVO> getAll();
	public ItineraryLocDetailVO findByPrimaryKey(Integer itinerary_loc_id, Integer itinerary_type_id);
	public void update(ItineraryLocDetailVO itineraryLocDetailVO);
}

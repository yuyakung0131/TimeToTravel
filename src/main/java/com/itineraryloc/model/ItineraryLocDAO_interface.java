package com.itineraryloc.model;

import java.util.List;

public interface ItineraryLocDAO_interface {
	public void insert(ItineraryLocVO itineraryLocVO);
	public List<ItineraryLocVO> getAll();
	public ItineraryLocVO findByPrimaryKey(Integer itinerary_loc_id);
	public void update(ItineraryLocVO itineraryLocVO);
	public void delete(Integer itinerary_loc_id);
}

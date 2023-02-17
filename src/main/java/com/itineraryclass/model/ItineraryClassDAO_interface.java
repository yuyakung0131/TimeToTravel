package com.itineraryclass.model;

import java.util.List;



public interface ItineraryClassDAO_interface {
	public void insert(ItineraryClassVO itineraryClassVO);
	public void update(ItineraryClassVO itineraryClassVO);
    public ItineraryClassVO findByPrimaryKey(Integer itinerary_class_id);
	public List<ItineraryClassVO> getAll();
	
}
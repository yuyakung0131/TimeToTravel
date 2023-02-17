package com.itinerary.model;
import java.util.*;
public interface ItineraryDAO_interface {
	
	public List<ItineraryVO> findByItinerary_type_id(Integer itinerary_type_id);
    public void insert(ItineraryVO itineraryVO);
    public void update(ItineraryVO itineraryVO);
    public void delete(Integer itineraryVO);
    public ItineraryVO findByPrimaryKey(Integer itinerary_id);
    public List<ItineraryVO> getAll();

//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
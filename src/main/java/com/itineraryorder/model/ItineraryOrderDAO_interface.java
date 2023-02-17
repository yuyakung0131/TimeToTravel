package com.itineraryorder.model;

import java.sql.SQLException;
import java.util.List;


public interface ItineraryOrderDAO_interface {
	public void insert(ItineraryOrderVO  itineraryOrderVO );
	public List<ItineraryOrderVO > getAll();
	public ItineraryOrderVO findByPrimaryKey(Integer itinerary_order_id);
	public void update(ItineraryOrderVO itineraryOrderVO);
	public List<ItineraryOrderVO> findByMember_id(Integer member_id);
	public List<ItineraryOrderVO> findByItinerary_id(Integer itinerary_id);
	public ItineraryOrderVO getLatestOrder(Integer member_id, Integer itinerary_id);
	public void updateItrOrder(ItineraryOrderVO itineraryOrderVO);
//	public List<ItineraryOrderVO> getItinerary(Integer itinerary_id);
}

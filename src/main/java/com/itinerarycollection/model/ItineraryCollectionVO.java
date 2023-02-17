package com.itinerarycollection.model;

import java.sql.Timestamp;

public class ItineraryCollectionVO implements java.io.Serializable{
	private Integer member_id;
	private Integer itinerary_type_id;
	private Timestamp itinerary_fav_date;

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getItinerary_type_id() {
		return itinerary_type_id;
	}

	public void setItinerary_type_id(Integer itinerary_type_id) {
		this.itinerary_type_id = itinerary_type_id;
	}

	public Timestamp getItinerary_fav_date() {
		return itinerary_fav_date;
	}

	public void setItinerary_fav_date(Timestamp itinerary_fav_date) {
		this.itinerary_fav_date = itinerary_fav_date;
	}
	
	public com.itinerarytype.model.ItineraryTypeVO getItineraryTypeVO() {
	    com.itinerarytype.model.ItineraryTypeService itineraryTypeSvc = new com.itinerarytype.model.ItineraryTypeService();
	    com.itinerarytype.model.ItineraryTypeVO itineraryTypeVO = itineraryTypeSvc.getOneItineraryType(itinerary_type_id);
	    return itineraryTypeVO;
    }

}
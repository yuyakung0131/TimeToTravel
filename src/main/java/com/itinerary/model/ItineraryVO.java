package com.itinerary.model;

import java.sql.Date;

import com.itinerarytype.model.ItineraryTypeVO;

public class ItineraryVO implements java.io.Serializable {
	private Integer itinerary_id;
	private Integer itinerary_type_id;
	private Date itinerary_start;
	private Date itinerary_end;
	private Integer itinerary_now;
	private Integer itinerary_price;
	private Integer itinerary_min;
	private Integer itinerary_max;
	private Date entered_start;
	private Date entered_end;
	private byte itinerary_state;

	public Integer getItinerary_id() {
		return itinerary_id;
	}

	public void setItinerary_id(Integer itinerary_id) {
		this.itinerary_id = itinerary_id;
	}

	public Integer getItinerary_type_id() {
		return itinerary_type_id;
	}

	public void setItinerary_type_id(Integer itinerary_type_id) {
		this.itinerary_type_id = itinerary_type_id;
	}

	public Date getItinerary_start() {
		return itinerary_start;
	}

	public void setItinerary_start(Date itinerary_start) {
		this.itinerary_start = itinerary_start;
	}

	public Date getItinerary_end() {
		return itinerary_end;
	}

	public void setItinerary_end(Date itinerary_end) {
		this.itinerary_end = itinerary_end;
	}

	public Integer getItinerary_now() {
		return itinerary_now;
	}

	public void setItinerary_now(Integer itinerary_now) {
		this.itinerary_now = itinerary_now;
	}

	public Integer getItinerary_price() {
		return itinerary_price;
	}

	public void setItinerary_price(Integer itinerary_price) {
		this.itinerary_price = itinerary_price;
	}

	public Integer getItinerary_min() {
		return itinerary_min;
	}

	public void setItinerary_min(Integer itinerary_min) {
		this.itinerary_min = itinerary_min;
	}

	public Integer getItinerary_max() {
		return itinerary_max;
	}

	public void setItinerary_max(Integer itinerary_max) {
		this.itinerary_max = itinerary_max;
	}

	public Date getEntered_start() {
		return entered_start;
	}

	public void setEntered_start(Date entered_start) {
		this.entered_start = entered_start;
	}

	public Date getEntered_end() {
		return entered_end;
	}

	public void setEntered_end(Date entered_end) {
		this.entered_end = entered_end;
	}

	public byte getItinerary_state() {
		return itinerary_state;
	}

	public void setItinerary_state(byte itinerary_state) {
		this.itinerary_state = itinerary_state;
	}

	public com.itinerarytype.model.ItineraryTypeVO getItineraryTypeVO() {
	    com.itinerarytype.model.ItineraryTypeService itineraryTypeSvc = new com.itinerarytype.model.ItineraryTypeService();
	    com.itinerarytype.model.ItineraryTypeVO itineraryTypeVO = itineraryTypeSvc.getOneItineraryType(itinerary_type_id);
	    return itineraryTypeVO;
    }
	
}
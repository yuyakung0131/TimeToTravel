package com.itinerarytype.model;

import java.util.List;

import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;

public class ItineraryTypeVO implements java.io.Serializable{
	private Integer itinerary_type_id;
	private Integer itinerary_class_id;
	private Integer firm_id;
	private String itinerary_title;
	private String itinerary_info;
	private String itinerary_other;
	private Integer itinerary_price;
	private Integer itinerary_min;
	private Integer itinerary_max;
	private byte itinerary_type_state;
	private Integer itinerary_total_score;
	private Integer itinerary_total_people;
	

	public Integer getItinerary_type_id() {
		return itinerary_type_id;
	}

	public void setItinerary_type_id(Integer itinerary_type_id) {
		this.itinerary_type_id = itinerary_type_id;
	}

	public Integer getItinerary_class_id() {
		return itinerary_class_id;
	}

	public void setItinerary_class_id(Integer itinerary_class_id) {
		this.itinerary_class_id = itinerary_class_id;
	}

	public Integer getFirm_id() {
		return firm_id;
	}

	public void setFirm_id(Integer firm_id) {
		this.firm_id = firm_id;
	}

	public String getItinerary_title() {
		return itinerary_title;
	}

	public void setItinerary_title(String itinerary_title) {
		this.itinerary_title = itinerary_title;
	}


	public String getItinerary_info() {
		return itinerary_info;
	}

	public void setItinerary_info(String itinerary_info) {
		this.itinerary_info = itinerary_info;
	}

	public String getItinerary_other() {
		return itinerary_other;
	}

	public void setItinerary_other(String itinerary_other) {
		this.itinerary_other = itinerary_other;
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

	public byte getItinerary_type_state() {
		return itinerary_type_state;
	}

	public void setItinerary_type_state(byte itinerary_type_state) {
		this.itinerary_type_state = itinerary_type_state;
	}

	public Integer getItinerary_total_score() {
		return itinerary_total_score;
	}

	public void setItinerary_total_score(Integer itinerary_total_score) {
		this.itinerary_total_score = itinerary_total_score;
	}

	public Integer getItinerary_total_people() {
		return itinerary_total_people;
	}

	public void setItinerary_total_people(Integer itinerary_total_people) {
		this.itinerary_total_people = itinerary_total_people;
	}
	

	
	 public com.itineraryclass.model.ItineraryClassVO getItineraryClassVO() {
		    com.itineraryclass.model.ItineraryClassService itineraryClassSvc = new com.itineraryclass.model.ItineraryClassService();
		    com.itineraryclass.model.ItineraryClassVO itineraryClassVO = itineraryClassSvc.getOneItineraryClass(itinerary_class_id);
		    return itineraryClassVO;
	    }
	
	 public com.firm.model.FirmVO getFirmVO() {
		 com.firm.model.FirmService firmSvc = new com.firm.model.FirmService();
		 com.firm.model.FirmVO firmVO = firmSvc.getByID_ITR(firm_id);
		 return firmVO;
	 }
	 

}
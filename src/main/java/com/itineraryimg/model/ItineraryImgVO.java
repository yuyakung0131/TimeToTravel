package com.itineraryimg.model;

public class ItineraryImgVO implements java.io.Serializable {
	private Integer itinerary_img_id;
	private byte[] itinerary_img;
	private Integer itinerary_type_id;
	
	public Integer getItinerary_img_id() {
		return itinerary_img_id;
	}
	public void setItinerary_img_id(Integer itinerary_img_id) {
		this.itinerary_img_id = itinerary_img_id;
	}
	public byte[] getItinerary_img() {
		return itinerary_img;
	}
	public void setItinerary_img(byte[] itinerary_img) {
		this.itinerary_img = itinerary_img;
	}
	public Integer getItinerary_type_id() {
		return itinerary_type_id;
	}
	public void setItinerary_type_id(Integer itinerary_type_id) {
		this.itinerary_type_id = itinerary_type_id;
	}
	
	public com.itinerarytype.model.ItineraryTypeVO getItineraryTypeVO() {
		com.itinerarytype.model.ItineraryTypeService itineraryTypeSvc=new com.itinerarytype.model.ItineraryTypeService();
		com.itinerarytype.model.ItineraryTypeVO itineraryTypeVO=itineraryTypeSvc.getOneItineraryType(itinerary_type_id);
		
		return itineraryTypeVO;
	}
	

	
}

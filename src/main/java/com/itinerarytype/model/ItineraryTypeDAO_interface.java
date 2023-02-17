package com.itinerarytype.model;

import java.util.List;

import com.itinerarycomment.model.ItineraryCommentVO;



public interface ItineraryTypeDAO_interface {
	public void insert(ItineraryTypeVO itineraryTypeVO);
	public List<ItineraryTypeVO> getAll();
	public ItineraryTypeVO findByPrimaryKey(Integer itinerary_type_id);
	public void update(ItineraryTypeVO itineraryTypeVO);
	public void delete(Integer itinerary_type_id);
	public List<ItineraryTypeVO> findByItinerary_class_id(Integer itinerary_class_id);
	public List<ItineraryTypeVO> findByFirm_id(Integer firm_id);
	public List<ItineraryCommentVO> getCommentByType(Integer itinerary_type_id);
	public List<ItineraryTypeVO> getTypeByMula(String locList, String price1, String price2, String classId);
	public List<ItineraryTypeVO> getItrTitle();
	public void updateItrTotalComment(ItineraryTypeVO itineraryTypeVO);
	void insert(ItineraryTypeVO itineraryTypeVO, String[] itinerary_loc_ids);
}

package com.itinerarytype.model;

import java.util.List;
import java.util.Set;

import com.firm.model.FirmVO;
import com.itinerary.model.ItineraryVO;
import com.itinerarycomment.model.ItineraryCommentVO;


public class ItineraryTypeService {
	private ItineraryTypeDAO_interface dao;

	public ItineraryTypeService() {
		dao = new ItineraryTypeDAO();
	}

	public ItineraryTypeVO addItineraryType(Integer itinerary_class_id, Integer firm_id, String itinerary_title, String itinerary_info, String itinerary_other,
			Integer itinerary_price, Integer itinerary_min, Integer itinerary_max, Byte itinerary_type_state ,String[] itinerary_loc_ids) {

		ItineraryTypeVO itineraryTypeVO = new ItineraryTypeVO();

		itineraryTypeVO.setItinerary_class_id(itinerary_class_id);
		itineraryTypeVO.setFirm_id(firm_id);
		itineraryTypeVO.setItinerary_title(itinerary_title);
		itineraryTypeVO.setItinerary_info(itinerary_info);
		itineraryTypeVO.setItinerary_other(itinerary_other);
		itineraryTypeVO.setItinerary_price(itinerary_price);
		itineraryTypeVO.setItinerary_min(itinerary_min);
		itineraryTypeVO.setItinerary_max(itinerary_max);
		itineraryTypeVO.setItinerary_type_state(itinerary_type_state);
//		itineraryTypeVO.setItinerary_total_score(itinerary_total_score);
//		itineraryTypeVO.setItinerary_total_people(itinerary_total_people);
		
		dao.insert( itineraryTypeVO,itinerary_loc_ids);

		return itineraryTypeVO;
	}

	public ItineraryTypeVO updateItineraryType(Integer itinerary_type_id, Integer itinerary_class_id, Integer firm_id, String itinerary_title, String itinerary_info, String itinerary_other,
			Integer itinerary_price, Integer itinerary_min, Integer itinerary_max, Byte itinerary_type_state, Integer itinerary_total_score, Integer itinerary_total_people) {

		ItineraryTypeVO itineraryTypeVO = new ItineraryTypeVO();

		itineraryTypeVO.setItinerary_type_id(itinerary_type_id);
		itineraryTypeVO.setItinerary_class_id(itinerary_class_id);
		itineraryTypeVO.setFirm_id(firm_id);
		itineraryTypeVO.setItinerary_title(itinerary_title);
		itineraryTypeVO.setItinerary_info(itinerary_info);
		itineraryTypeVO.setItinerary_other(itinerary_other);
		itineraryTypeVO.setItinerary_price(itinerary_price);
		itineraryTypeVO.setItinerary_min(itinerary_min);
		itineraryTypeVO.setItinerary_max(itinerary_max);
		itineraryTypeVO.setItinerary_type_state(itinerary_type_state);
		itineraryTypeVO.setItinerary_total_score(itinerary_total_score);
		itineraryTypeVO.setItinerary_total_people(itinerary_total_people);
		dao.update(itineraryTypeVO);

		return itineraryTypeVO;
	}


	public ItineraryTypeVO getOneItineraryType(Integer itinerary_type_id) {
		
		return dao.findByPrimaryKey(itinerary_type_id);
	}

	public List<ItineraryTypeVO> getAll() {
		return dao.getAll();
	}
	
	public void deleteItineraryType(Integer itinerary_type_id) {

		dao.delete(itinerary_type_id);
	}
	
	public List<ItineraryTypeVO> getItineraryClass(Integer itinerary_class_id){
		return dao.findByItinerary_class_id(itinerary_class_id);
	}
	
	public List<ItineraryTypeVO> getFirm(Integer firm_id){
		return dao.findByFirm_id(firm_id);
	}
	
//	wang0126新增,用行程種類編號找評論的list
	public List<ItineraryCommentVO> getCommentByType(Integer itinerary_type_id) {
		return dao.getCommentByType(itinerary_type_id);
	}
	
	public List<ItineraryTypeVO> getTypeByMul(String locList,String price1,String price2,String classId){
		return dao.getTypeByMula(locList,price1,price2,classId);
	}
	
	public List<ItineraryTypeVO> getItrTitle() {
		return dao.getItrTitle();
	}
	public ItineraryTypeVO updateItrTotalComment(Integer itinerary_type_id,Integer itinerary_total_score) {

		ItineraryTypeVO itineraryTypeVO = new ItineraryTypeVO();

		itineraryTypeVO.setItinerary_type_id(itinerary_type_id);
		itineraryTypeVO.setItinerary_total_score(itinerary_total_score);
		dao.updateItrTotalComment(itineraryTypeVO);

		return itineraryTypeVO;
	}

}

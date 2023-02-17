package com.itineraryimg.model;

import java.util.List;




public class ItineraryImgService {
	private ItineraryImgDAO_interface dao;
	
	public ItineraryImgService() {
		dao = new ItineraryImgDAO();
	}
	
	public ItineraryImgVO addItineraryImg(byte[]itinerary_img,Integer itinerary_type_id) {

		ItineraryImgVO itineraryImgVO = new ItineraryImgVO();

		itineraryImgVO.setItinerary_type_id(itinerary_type_id);
		itineraryImgVO.setItinerary_img(itinerary_img);
			
		dao.insert(itineraryImgVO);

		return itineraryImgVO;
	}

	public ItineraryImgVO updateItineraryImg(Integer itinerary_img_id, byte[]itinerary_img, Integer itinerary_type_id ) {

		ItineraryImgVO itineraryImgVO = new ItineraryImgVO();

		itineraryImgVO.setItinerary_img(itinerary_img);
		itineraryImgVO.setItinerary_type_id(itinerary_type_id);
		itineraryImgVO.setItinerary_img_id(itinerary_img_id);
		
		dao.update(itineraryImgVO);

		return itineraryImgVO;
	}


	public ItineraryImgVO getOneItineraryImg(Integer itinerary_img_id) {
		return dao.findByPrimaryKey(itinerary_img_id);
	}
	
	public void deleteItineraryImg(Integer itinerary_img_id) {
		dao.delete(itinerary_img_id);
	}

	public List<ItineraryImgVO> getAll() {
		return dao.getAll();
	}
	
	public List<ItineraryImgVO>findByItineraryTypeId(Integer itinerary_type_id){
		return dao.findByItineraryTypeId(itinerary_type_id);
	}
	
	// 0201新增
	
	public List<ItineraryImgVO> getItrImgByType(Integer itinerary_type_id){
		return dao.getItrImgByType(itinerary_type_id);
	}
	

}

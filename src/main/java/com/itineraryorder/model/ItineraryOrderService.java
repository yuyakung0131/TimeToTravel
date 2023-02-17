package com.itineraryorder.model;

import java.util.List;

import com.itinerarytype.model.ItineraryTypeVO;



public class ItineraryOrderService {
	private ItineraryOrderDAO_interface dao;

	public ItineraryOrderService() {
		dao = new ItineraryOrderDAO();
	}

	public ItineraryOrderVO addItineraryOrder(Integer member_id, Integer itinerary_id, Integer itinerary_people_num, Integer itinerary_ttl_price, 
			Byte itinerary_order_state, Byte itinerary_refund_state, String itinerary_order_memo) {

		ItineraryOrderVO itineraryOrderVO = new ItineraryOrderVO();

		itineraryOrderVO.setMember_id(member_id);
		itineraryOrderVO.setItinerary_id(itinerary_id);
		itineraryOrderVO.setItinerary_people_num(itinerary_people_num);
		itineraryOrderVO.setItinerary_ttl_price(itinerary_ttl_price);
		itineraryOrderVO.setItinerary_order_state(itinerary_order_state);
		itineraryOrderVO.setItinerary_refund_state(itinerary_refund_state);
		itineraryOrderVO.setItinerary_order_memo(itinerary_order_memo);
		
		dao.insert(itineraryOrderVO);

		return itineraryOrderVO;
	}

	public ItineraryOrderVO updateItineraryOrder(Integer itinerary_order_id, Integer member_id, Integer itinerary_id, Integer itinerary_people_num, Integer itinerary_ttl_price, 
			Byte itinerary_order_state, Byte itinerary_refund_state, String itinerary_order_memo) {

		ItineraryOrderVO itineraryOrderVO = new ItineraryOrderVO();

		itineraryOrderVO.setItinerary_order_id(itinerary_order_id);
		itineraryOrderVO.setMember_id(member_id);
		itineraryOrderVO.setItinerary_id(itinerary_id);
		itineraryOrderVO.setItinerary_people_num(itinerary_people_num);
		itineraryOrderVO.setItinerary_ttl_price(itinerary_ttl_price);
		itineraryOrderVO.setItinerary_order_state(itinerary_order_state);
		itineraryOrderVO.setItinerary_refund_state(itinerary_refund_state);
		itineraryOrderVO.setItinerary_order_memo(itinerary_order_memo);
		dao.update(itineraryOrderVO);

		return itineraryOrderVO;
	}


	public ItineraryOrderVO getOneItineraryOrder(Integer itinerary_order_id) {
		return dao.findByPrimaryKey(itinerary_order_id);
	}

	public List<ItineraryOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<ItineraryOrderVO> getMember(Integer member_id){
		return dao.findByMember_id(member_id);
	}
	
	public List<ItineraryOrderVO> getItinerary(Integer itinerary_id){
		return dao.findByItinerary_id(itinerary_id);
	}
	
	public ItineraryOrderVO getLatestOrder(Integer member_id, Integer itinerary_id){
		return dao.getLatestOrder(member_id, itinerary_id);
	}

	public ItineraryOrderVO updateItrOrder(Integer itinerary_order_id, Byte itinerary_order_state,Byte itinerary_refund_state) {
		ItineraryOrderVO itineraryOrderVO = new ItineraryOrderVO();
		
		itineraryOrderVO.setItinerary_order_id(itinerary_order_id);
		itineraryOrderVO.setItinerary_order_state(itinerary_order_state);
		itineraryOrderVO.setItinerary_refund_state(itinerary_refund_state);
		dao.updateItrOrder(itineraryOrderVO);

		return itineraryOrderVO;
	}
	
//	public List<ItineraryOrderVO> getForItinerary(Integer itinerary_id){
//		return dao.getItinerary(itinerary_id);
//	}
	
	
}


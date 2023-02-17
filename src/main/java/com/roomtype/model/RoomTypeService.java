package com.roomtype.model;

import java.util.List;

public class RoomTypeService {
	private RoomTypeDAO_interface dao;

	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}

	public RoomTypeVO insert(Integer firm_id, String room_type_name, Integer room_type_amount, String room_type_content,
			Integer room_type_price) {
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		roomTypeVO.setFirm_id(firm_id);
		roomTypeVO.setRoom_type_name(room_type_name);
		roomTypeVO.setRoom_type_amount(room_type_amount);
		roomTypeVO.setRoom_type_content(room_type_content);
		roomTypeVO.setRoom_type_price(room_type_price);
		dao.insert(roomTypeVO);

		return roomTypeVO;
	}

	public RoomTypeVO update(Integer room_type_id, Byte room_type_state) {
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		roomTypeVO.setRoom_type_id(room_type_id);
		roomTypeVO.setRoom_type_state(room_type_state);
		dao.update(roomTypeVO);

		return roomTypeVO;
	}

	public void delete(Integer room_type_id) {
		dao.delete(room_type_id);
	}

	public RoomTypeVO getOneRoomType(Integer room_type_id) {
		return dao.findByPrimaryKey(room_type_id);
	}

	public List<RoomTypeVO> getRoomTypeByFirmID(Integer firm_id) {
		return dao.getRoomTypeByFirmID(firm_id);
	}

	public List<RoomTypeVO> getAll() {
		return dao.getAll();
	}
}

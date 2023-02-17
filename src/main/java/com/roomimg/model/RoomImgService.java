package com.roomimg.model;

import java.util.List;

public class RoomImgService {
	private RoomImg_interface dao;

	public RoomImgService() {
		dao = new RoomImgDAO();
	}

	public RoomImgVO insert(Integer room_type_id, byte[] room_img) {
		RoomImgVO roomImgVO = new RoomImgVO();
		roomImgVO.setRoom_type_id(room_type_id);
		roomImgVO.setRoom_img(room_img);
		dao.insert(roomImgVO);
		return roomImgVO;
	}

	public RoomImgVO update(Integer room_img_id, byte[] room_img) {
		RoomImgVO roomImgVO = new RoomImgVO();
		roomImgVO.setRoom_img_id(room_img_id);
		roomImgVO.setRoom_img(room_img);
		dao.update(roomImgVO);
		return roomImgVO;
	}

	public void delete(Integer room_img_id) {
		dao.delete(room_img_id);
	}

	public RoomImgVO getOneRoomImg(Integer room_img_id) {
		return dao.findPrimaryKey(room_img_id);
	}

	public RoomImgVO getImgByRoomTypeLimit1(Integer room_type_id) {
		return dao.getImgByRoomTypeLimit1(room_type_id);
	}

	public List<RoomImgVO> getImgByRoomType(Integer room_type_id) {
		return dao.getImgByRoomType(room_type_id);
	}

	public List<RoomImgVO> getAll() {
		return dao.getAll();
	}

}

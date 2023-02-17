package com.roomimg.model;

import java.util.List;

public interface RoomImg_interface {
	public void insert(RoomImgVO roomImgVO);
	
	public void delete(Integer room_img_id);

	public void update(RoomImgVO roomImgVO);
	
	public RoomImgVO findPrimaryKey(Integer room_img_id);
	
	public RoomImgVO getImgByRoomTypeLimit1(Integer room_type_id);

	public List<RoomImgVO> getAll();
	
	public List<RoomImgVO> getImgByRoomType(Integer room_type_id);
}

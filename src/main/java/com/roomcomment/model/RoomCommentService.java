package com.roomcomment.model;

import java.sql.Timestamp;
import java.util.List;

public class RoomCommentService {

	private RoomCommentDAO_interface rcdao;

public RoomCommentService() {
	rcdao = new RoomCommentDAO(); 
}

public RoomCommentVO addRoomComment(Integer room_type_id,Integer member_id,
		String room_comment_content, Integer room_comment_star) {
	
	RoomCommentVO roomCommentVO =new RoomCommentVO();
	
	roomCommentVO.setRoom_type_id(room_type_id);
	roomCommentVO.setMember_id(member_id);
	roomCommentVO.setRoom_comment_content(room_comment_content);
	roomCommentVO.setRoom_comment_star(room_comment_star);
	
	rcdao.insert(roomCommentVO);
	return roomCommentVO ;
}

//public void updateRoomComment(Integer room_comment_id,Integer room_type_id,Integer member_id,
//		String room_comment_content, Integer room_comment_star,Timestamp room_comment_time) {
//	RoomCommentVO roomCommentVO =new RoomCommentVO();
//	roomCommentVO.setRoom_comment_id(room_comment_star);
//	roomCommentVO.setRoom_type_id(room_type_id);
//	roomCommentVO.setMember_id(member_id);
//	roomCommentVO.setRoom_comment_content(room_comment_content);
//	roomCommentVO.setRoom_comment_star(room_comment_star);
//	roomCommentVO.setRoom_comment_time(room_comment_time);
//	rcdao.update(roomCommentVO);
//}


public void deleteRoomComment(Integer room_comment_id) {
	rcdao.delete(room_comment_id);
}


public RoomCommentVO getOneRoomComment(Integer room_comment_id) {
	
	return rcdao.findByPrimaryKey(room_comment_id);
		
}
public List<RoomCommentVO> getAll(){
	return rcdao.getAll();
	
}
public List<RoomCommentVO> getCommentByRoomType(Integer room_type_id){
	return rcdao.getCommemtByRoomType(room_type_id);
}
}



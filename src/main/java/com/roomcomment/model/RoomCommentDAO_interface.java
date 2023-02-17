package com.roomcomment.model;

import java.util.List;

public interface RoomCommentDAO_interface {
	public void insert(RoomCommentVO roomCommentVO);

//	public void update(RoomCommentVO roomCommentVO);

	public void delete(Integer room_comment_id);

	public RoomCommentVO findByPrimaryKey(Integer room_comment_id);

	public List<RoomCommentVO> getAll();

//找評論
	public List<RoomCommentVO> getCommemtByRoomType(Integer room_type_id);
}

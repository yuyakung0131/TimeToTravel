package com.roomcomment.model;

import java.util.List;

public class TestRoomComment {
	public static void main(String[] args) {
		RoomCommentDAO daoJDBC = new RoomCommentDAO();
		// 新增
				RoomCommentVO roomCommentVO= new RoomCommentVO();
				roomCommentVO.setRoom_type_id(1);
				roomCommentVO.setMember_id(4);
				roomCommentVO.setRoom_comment_content("只能用完美來形容");
				roomCommentVO.setRoom_comment_star(5);

				daoJDBC.insert(roomCommentVO);

				// 查詢
				List<RoomCommentVO> list = daoJDBC.getAll();
				for (RoomCommentVO aRoomComment : list) {
					System.out.print(aRoomComment.getRoom_comment_id() + ",");
					System.out.print(aRoomComment.getMember_id() + ",");
					System.out.print(aRoomComment.getRoom_comment_content() + ",");
					System.out.print(aRoomComment.getRoom_comment_star() + ",");
					System.out.print(aRoomComment.getRoom_comment_time());

					System.out.println();
				}
			}
		}

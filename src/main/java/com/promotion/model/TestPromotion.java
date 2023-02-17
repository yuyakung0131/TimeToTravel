package com.promotion.model;

import java.util.List;


public class TestPromotion {
	public static void main(String[] args) {
		PromotionDAO dao=new PromotionDAO();
		// 查詢All
				List<PromotionVO> list = dao.getAll();
				for(PromotionVO aPromotion : list) {
					System.out.print(aPromotion.getPromotion_id());
					System.out.print(aPromotion.getPromotion_name()+",");
					System.out.print("開始時間:"+aPromotion.getPromotion_startdate()+",");
					System.out.print("結束時間:"+aPromotion.getPromotion_enddate()+",");
					System.out.print(aPromotion.getPromotion_state());

					System.out.println();
			}
					// 單一查詢 (X)
//					PromotionVO promotionVO = dao.findByPrimaryKey(1);
//					System.out.print(promotionVO.getPromotion_id());
//					System.out.print(promotionVO.getPromotion_name()+",");
//					System.out.print("開始時間:"+promotionVO.getPromotion_startdate()+",");
//					System.out.print("結束時間:"+promotionVO.getPromotion_enddate()+",");
//					System.out.print(promotionVO.getPromotion_state());
//					System.out.println("---------------------");
					
					// 刪除
//					dao.delete(1);
					
					// 新增
//					PromotionVO promotionVO2 = new PromotionVO();
//					promotionVO2.setPromotion_name("吳永志促銷");
//					promotionVO2.setPromotion_startdate(java.sql.Date.valueOf("2023-04-30"));
//					promotionVO2.setPromotion_enddate(java.sql.Date.valueOf("2023-05-01"));
//					promotionVO2.setPromotion_state(new Byte("1"));
//					dao.insert(promotionVO2);
					
					// 修改
//					PromotionVO promotionVO3 = new PromotionVO();
//					promotionVO3.setPromotion_id(1);
//					promotionVO3.setPromotion_name("吳永志333");
//					promotionVO3.setPromotion_startdate(java.sql.Date.valueOf("2022-06-30"));
//					promotionVO3.setPromotion_enddate(java.sql.Date.valueOf("2022-07-01"));
//					promotionVO3.setPromotion_state(new Byte("1"));
//					dao.update(promotionVO3);
//					
				}
			}

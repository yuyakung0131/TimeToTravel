package com.promotionitem.model;

import java.util.List;
import java.util.Map;

public interface PromotionItemDAO_interface {
	public void insert(PromotionItemVO promotionItemVO);

	public void update(PromotionItemVO promotionItemVO);

	public List<PromotionItemVO> getAll();
	//育生新增房型抓促銷用
	public PromotionItemVO getProByRoomType(Integer room_type_id);
	
	public PromotionItemVO findByPrimaryKey(Integer promotion_id);

	public PromotionItemVO findByTwo(Integer promotion_id, Integer room_type_id);

}

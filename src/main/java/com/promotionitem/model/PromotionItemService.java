package com.promotionitem.model;

import java.util.List;
import java.util.Map;



public class PromotionItemService {
	private PromotionItemDAO_interface pmidao;

	public PromotionItemService() {
		pmidao = new PromotionItemDAO();
	}

	public PromotionItemVO addPromotionItem(Integer promotion_id, Integer room_type_id, Double discount_number) {
		PromotionItemVO promotionItemVO = new PromotionItemVO();

		promotionItemVO.setPromotion_id(promotion_id);
		promotionItemVO.setRoom_type_id(room_type_id);
		promotionItemVO.setDiscount_number(discount_number);
		pmidao.insert(promotionItemVO);
		return promotionItemVO;
	}

	public PromotionItemVO getOnePromotionItem(Integer promotion_id) {
		return pmidao.findByPrimaryKey(promotion_id);
	}

	//育生新增房型抓促銷用
	public PromotionItemVO getProByRoomType(Integer room_type_id) {
		return pmidao.getProByRoomType(room_type_id);
	}
	
	public PromotionItemVO updatePromotionItem(Integer promotion_id, Integer room_type_id, Double discount_number) {

		PromotionItemVO promotionItemVO = new PromotionItemVO();

		promotionItemVO.setPromotion_id(promotion_id);
		promotionItemVO.setRoom_type_id(room_type_id);
		promotionItemVO.setDiscount_number(discount_number);

		pmidao.update(promotionItemVO);

		return promotionItemVO;
	}

	public List<PromotionItemVO> getAll() {
		return pmidao.getAll();
	}

	public PromotionItemVO getByTwo(Integer promotion_id, Integer room_type_id) {
		PromotionItemVO promotionItemVO = pmidao.findByTwo(promotion_id, room_type_id);
		return promotionItemVO;
	}
public static void main(String[] args) {
	
	PromotionItemService svcItemService = new PromotionItemService();
	svcItemService.updatePromotionItem(1, 1, 2.0);

}

}

package com.promotion.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PromotionDAO_interface {
	public void insert(PromotionVO promotionVO);

	public void update(PromotionVO promotionVO);

	public void delete(Integer promotion_id);

	public PromotionVO findByPrimaryKey(Integer promotion_id);

	public List<PromotionVO> getAll();

	public List<PromotionVO> getAll(Map<String, String[]> map);



	public List<PromotionVO> getPromotionByState(Byte promotion_state);
}

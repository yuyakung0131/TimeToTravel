package com.promotion.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PromotionService {
	private PromotionDAO_interface pmDao;

	public PromotionService() {
		pmDao =new PromotionDAO();
	}
	
	//新增
	public PromotionVO addPromotion(String promotion_name,Date promotion_startdate,Date promotion_enddate,Byte promotion_state) {
		PromotionVO promotionVO = new PromotionVO();
		
		promotionVO.setPromotion_name(promotion_name);
		promotionVO.setPromotion_startdate(promotion_startdate);
		promotionVO.setPromotion_enddate(promotion_enddate);
		promotionVO.setPromotion_state(promotion_state);
		pmDao.insert(promotionVO);
		
		return promotionVO;
	}
	
//	修改 (要有主鍵)
	public PromotionVO updatePromotion(Integer promotion_id,String promotion_name,Date promotion_startdate,Date promotion_enddate,Byte promotion_state)
	{
		PromotionVO promotionVO = new PromotionVO();
		promotionVO.setPromotion_id(promotion_id);
		promotionVO.setPromotion_name(promotion_name);
		promotionVO.setPromotion_startdate(promotion_startdate);
		promotionVO.setPromotion_enddate(promotion_enddate);
		promotionVO.setPromotion_state(promotion_state);
		pmDao.update(promotionVO);
		
		return promotionVO;
	}
	public void deletePromotion(Integer promotion_id) {
		pmDao.delete(promotion_id);
	}
	
	public PromotionVO getOnePromotion(Integer promotion_id) {
		
		return pmDao.findByPrimaryKey(promotion_id);
	}
	public List<PromotionVO> getAll(){
		return pmDao.getAll();
	}

	public List<PromotionVO> getAll(Map<String, String[]> map) {
		return pmDao.getAll(map);
	}

public List<PromotionVO> getPromotionByState(Byte promotion_state){
	   return pmDao.getPromotionByState(promotion_state);
}
	

	
}


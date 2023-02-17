package com.promotionitem.model;

public class TestPromotionItem {
	public static void main(String[] args) {
		PromotionItemDAO dItemJDBCDAO = new PromotionItemDAO();
		PromotionItemVO promotionItemVO = dItemJDBCDAO.findByTwo(1, 1);
		System.out.println(promotionItemVO.toString());
	}

}

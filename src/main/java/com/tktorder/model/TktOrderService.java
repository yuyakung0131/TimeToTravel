package com.tktorder.model;

import java.util.List;

public class TktOrderService {
	 private TktOrderDAO dao;
	   
	   public TktOrderService() {
		   dao = new TktOrderDAO();
	   }
	   public TktOrder addTktOrder(Integer member_id, 
			   Integer promo_id,Integer total,Integer total_discount) {

		   TktOrder tktOrder = new TktOrder();

		   tktOrder.setMember_id(member_id);
		   tktOrder.setPromo_id(promo_id);
		   tktOrder.setTotal(total);
//		   tktOrder.setTkt_order_state(tkt_order_state);
		   tktOrder.setTotal_discount(total_discount);
		   Integer tkt_order_id= dao.insert(tktOrder);
		   tktOrder.setTkt_order_id(tkt_order_id);

			return tktOrder;
		}

		public TktOrder  updateTktOrder(Integer tkt_order_id, Integer member_id, 
				   Integer promo_id,Integer total, Byte tkt_order_state,Integer total_discount) {

			   TktOrder tktOrder = new TktOrder();
			   tktOrder.setTkt_order_id(tkt_order_id);
			   tktOrder.setMember_id(member_id);
			   tktOrder.setPromo_id(promo_id);
			   tktOrder.setTotal(total);
			   tktOrder.setTkt_order_state(tkt_order_state);
			   tktOrder.setTotal_discount(total_discount);
				dao.update(tktOrder);
				return tktOrder;
		}

//		public void deleteTktOrder(Integer tkt_order_id) {
//			dao.delete(tkt_order_id);
//		}

		public TktOrder getOneTktOrder(Integer tkt_order_id) {
			return dao.findByPrimaryKey(tkt_order_id);
		}
		
		 public List<TktOrder> findByMemberId(Integer member_id){
			return dao.findByMemberId(member_id);
		}
		 
			public List<TktOrder> selectByTktOrderState(Byte tkt_order_state){
				return dao.selectByTktOrderState(tkt_order_state);
			}

		public List<TktOrder> getAll() {
			return dao.getAll();
		}

}

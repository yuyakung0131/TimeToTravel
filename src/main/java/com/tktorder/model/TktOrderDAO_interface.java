package com.tktorder.model;

import java.util.List;


public interface TktOrderDAO_interface {
    public Integer insert(TktOrder TktOrder);
    public void update(TktOrder TktOrder);
    //public void delete(Integer tkt_order_id);
    public TktOrder findByPrimaryKey(Integer tkt_order_id);
    public List<TktOrder> findByMemberId(Integer member_id);
    public List<TktOrder> selectByTktOrderState(Byte tkt_order_state);
    public List<TktOrder> getAll();
}

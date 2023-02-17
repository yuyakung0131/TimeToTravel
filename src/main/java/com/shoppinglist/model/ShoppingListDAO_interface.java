package com.shoppinglist.model;

import java.util.List;

public interface ShoppingListDAO_interface {
	public void insert(ShoppingList shoppinglist);
//	public void insertItem(ShoppingList shoppinglist);
	public void update(ShoppingList shoppinglist);
	public ShoppingList findByPrimaryKey(Integer tkt_id,Integer member_id);
	public void delete(Integer tkt_id,Integer member_id);
	public List<ShoppingList>getAll();
	public List<ShoppingList>getAllbyMember(Integer member_id);

}

package com.service;

import java.util.List;

import com.entity.FeedBack;
import com.entity.Food;
import com.entity.Order;

public interface OrderService {

	// 获取指定用户的指定日期订单列表
	public List<Order> getOrdersByDateCodeUserId(String dateCode, int userId);

	// 获取指定用户的订单列表
	public List<Order> getOrdersByUserId(int userId);

	// 获取指定orderId的订单列表
	public List<Order> getOrdersByOrderId(String orderId);
	
	// 新增订单，因为orderId是String，所以这里返回String
	public boolean addOrder(Order order);
	
	public boolean addOrderDetail(int foodId, int count, String orderId);
	
	public int getBuyTimesByUserId(int foodId, int userId);
	
	public int getSearchTimesByUserId(String keyword, int userId);
	
	public int getMarkTimesByUserId(int foodId, int userId);
	
	public int getTasteByUserId(int userId);
	
	public List<Food> getFoodsByDayPidCid2(Integer day, Integer pid, Integer cid);
	
}

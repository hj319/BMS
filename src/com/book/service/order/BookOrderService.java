package com.book.service.order;

import java.util.List;

import com.book.entity.BookOrders;

public interface BookOrderService {

	List<BookOrders> getOrderList(String uid);

	boolean addOrder(BookOrders order);

	boolean updateOrder(String oid, int count,double curPrice);

}

package com.book.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.entity.BookOrders;

/**
 * 操作订单表的接口
 * @author asus
 *@创建时间：2020年4月10日下午4:48:29
 */
public interface BookOrdersMapper {
	
	int saveOrder(BookOrders order);
	
	List<BookOrders> getOrderList(@Param("uid") String uid);

	int updateOrder(@Param("oid") String oid,@Param("count") int count, @Param("curPrice") double curPrice);
}

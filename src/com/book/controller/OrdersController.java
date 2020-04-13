package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.BookOrders;
import com.book.service.order.BookOrderService;
import com.book.service.order.impl.BookOrderServiceImpl;

/**
 * Servlet implementation class OrdersController
 */
@WebServlet("/OrdersController")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookOrderService os=new BookOrderServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("add".equals(op))
		{
			addcart(request,response);
		}
		else if("showCart".equals(op))
		{
			showCart(request,response);
		}
		else if("update".equals(op))
		{
			updateCart(request,response);
		}
	}

	private void updateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置响应的内容格式
				response.setContentType("application/json;charset=utf-8");
				PrintWriter pw = response.getWriter();
				String oid = request.getParameter("oid");
				int count = Integer.parseInt(request.getParameter("count"));
				double price = Double.parseDouble(request.getParameter("price"));
				double curPrice = price*count;
				// 调用业务层更新订单的方法
				boolean isOk = os.updateOrder(oid,count,curPrice);
				if(isOk) {
					pw.write("{\"result\":\"true\"}");
				}else {
					pw.write("{\"result\":\"false\"}");
				}
				pw.flush();
				pw.close();
	}

	private void showCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId=(String)request.getSession().getAttribute("userId"); 
		List<BookOrders> list=os.getOrderList(userId);
		request.getSession().setAttribute("list", list);
		response.sendRedirect("user/cart.jsp");
		
	}

	private void addcart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 设置响应给客户端的格式
				response.setContentType("application/json;charset=UTF-8");
				// 获得id的值
				int id = Integer.parseInt(request.getParameter("id"));
				
				double price = Double.parseDouble(request.getParameter("price"));
				// 生成订单编号  当前时间+6位随机数
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
				String orderId = sdf.format(date);
				int rand = (int)(Math.random()*(999999-100000+1)+100000);
				orderId += rand;
				System.out.println("生成的订单id是："+orderId);
				// 获得用户的账户
				String userId = (String) request.getSession().getAttribute("showuser");
				BookOrders order = new BookOrders(orderId, id, 1, price, date, userId);
				// 调用增加订单的一个业务层方法
				boolean isOk = os.addOrder(order);
				PrintWriter pw = response.getWriter();
				if(isOk) {
					pw.write("{\"result\":\"true\"}");
				}else {
					pw.write("{\"result\":\"false\"}");
				}
				pw.flush();
				pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

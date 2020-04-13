package com.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.entity.BookCategory;
import com.book.service.category.BookCategoryService;
import com.book.service.category.impl.BookCategoryServiceImpl;

/**
 * Servlet implementation class AdminCategory
 */
@WebServlet("/AdminCategory")
public class AdminCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookCategoryService bs=new BookCategoryServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("addCategory".equals(op))
		{
			addCategory(request,response);
		}
		else if("CategoryList".equals(op))
		{
			getCategoryList(request,response);
		}
		else if("deleteCategory".equals(op))
		{
			deleteCategory(request,response);
		}
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		boolean isOk=bs.deleteCategory(id);
		if(isOk)
		{
			//删除成功
			response.sendRedirect("AdminCategory?op=CategoryList");
		}
		
	}

	private void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<BookCategory> listCa=bs.getCategory();
		session.setAttribute("listCa", listCa);
		response.sendRedirect("admin/category-mgr.jsp");
		
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String category=request.getParameter("category");
		boolean isOk=bs.addCategory(category);
		if(isOk)
		{
			//增加成功
			response.sendRedirect("AdminCategory?op=CategoryList");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.codec.digest.DigestUtils;

import com.book.entity.BookCategory;
import com.book.entity.BookInfo;
import com.book.entity.BookOrders;
import com.book.entity.BookUser;
import com.book.entity.Pager;
import com.book.service.book.BookInfoService;
import com.book.service.book.PageService;
import com.book.service.book.impl.BookInfoServiceImpl;
import com.book.service.book.impl.PageServiceImpl;
import com.book.service.category.BookCategoryService;
import com.book.service.category.impl.BookCategoryServiceImpl;
import com.book.service.order.BookOrderService;
import com.book.service.order.impl.BookOrderServiceImpl;
import com.book.service.user.BookUserService;
import com.book.service.user.impl.BookUserServiceImpl;
import com.mysql.jdbc.StringUtils;


/**
 * Servlet implementation class UserBookController
 */
@WebServlet("/UserBookController")
public class UserBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookUserService bs=new BookUserServiceImpl();
    private BookCategoryService bcs=new BookCategoryServiceImpl();
    private BookOrderService bds=new BookOrderServiceImpl();
    private BookInfoService bis=new  BookInfoServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符编码格式
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("add".equals(op))
		{
			addBookUser(request,response);
		}
		else if("login".equals(op))
		{
			login(request,response);
		}
		else if("Index".equals(op))
		{
			getBookList(request,response);
		}
		/*
		 * else if("addOrder".equals(op)) { addOrder(request,response); }
		 */
		else if("getcatebook".equals(op))
		{
			getcatebook(request,response);
		}
		else if("findByBookName".equals(op))
		{
			findByBookName(request,response);
		}
		
	}
	
	private void findByBookName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bookName=request.getParameter("bookName");
		PageService pgs=new PageServiceImpl();
		Pager pg=new Pager();
		int count=pgs.getFindByBookNameCount(bookName);
		String curPage = request.getParameter("pageIndex");
		int currpage;
		if(curPage == null || curPage.length()==0)
			currpage=1;
		else if(Integer.valueOf(curPage) < 1)
			currpage = 1;
		else
			currpage = Integer.valueOf(curPage);
		int pageSize=3;
		pg.setPageSize(pageSize);
		pg.setTotalCount(count);
		int from = (currpage-1)*pg.getPageSize();
		List<BookInfo> blist=pgs.getFindByBookNameData(from, pageSize,bookName);
		pg.setCurrpage(currpage);
		pg.setBlist(blist);
		HttpSession session = request.getSession();
		session.setAttribute("pg", pg);
		List<BookCategory> listCa=bcs.getCategory();
		session.setAttribute("listCa", listCa);
		response.sendRedirect("user/index.jsp");
		
	}

	private void getcatebook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cid=request.getParameter("id");
		List<BookInfo> list=bis.getcatebook(cid);
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		//页面跳转
		response.sendRedirect("user/category.jsp");
		
	}

	/*
	 * private void addOrder(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { List<BookOrders> list=bds.addOrder();
	 * HttpSession session = request.getSession(); session.setAttribute("list",
	 * list); //页面跳转 response.sendRedirect("user/cart.jsp");
	 * 
	 * }
	 */

	private void getBookList(HttpServletRequest request, HttpServletResponse response) throws IOException {
				PageService pgs=new PageServiceImpl();
				Pager pg=new Pager();
				int count=pgs.getCount();
				String curPage = request.getParameter("pageIndex");
				int currpage;
				if(curPage == null || curPage.length()==0)
					currpage=1;
				else if(Integer.valueOf(curPage) < 1)
					currpage = 1;
				else
					currpage = Integer.valueOf(curPage);
				int pageSize=3;
				pg.setPageSize(pageSize);
				pg.setTotalCount(count);
				int from = (currpage-1)*pg.getPageSize();
				List<BookInfo> blist=pgs.getBookData(from, pageSize);
				pg.setCurrpage(currpage);
				pg.setBlist(blist);
				HttpSession session = request.getSession();
				session.setAttribute("pg", pg);
				List<BookCategory> listCa=bcs.getCategory();
				session.setAttribute("listCa", listCa);
				response.sendRedirect("user/index.jsp");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String loginId=request.getParameter("loginId");
		String loginPsw=request.getParameter("loginPsw");
		String showuser=loginId;
		String name=(String) request.getSession().getAttribute("name");
		loginId= DigestUtils.md5Hex(loginId);
		loginPsw = DigestUtils.md5Hex(loginPsw);
		boolean isOk=bs.loginValidate(loginId, loginPsw);
		if(isOk)
		{	
			
			HttpSession session=request.getSession();
			session.setAttribute("showuser", showuser);
			session.setAttribute("name", name);
			response.sendRedirect("UserBookController?op=Index");
		}
		else {
			response.sendRedirect("user/user-login.jsp");
		}
		
	}

	private void addBookUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String loginId=request.getParameter("loginId");
		String loginPsw=request.getParameter("loginPsw");
		String reLoginPsw=request.getParameter("reLoginPsw");
		String name=request.getParameter("name");
		String code=request.getParameter("code");
		HttpSession session=request.getSession();
		session.setAttribute("name", name);
		// 对账号名和密码进行MD5加密处理
		loginId= DigestUtils.md5Hex(loginId);
		loginPsw = DigestUtils.md5Hex(loginPsw);
		//将这些数据封装到实体类中
		BookUser user=new BookUser(loginId,loginPsw,name,1);
		boolean isOk=bs.saveUser(user);
		if(isOk)
		{
			//登录
			response.sendRedirect("user/user-login.jsp");
		}
		else {
			response.sendRedirect("user/user-regist.jsp");
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

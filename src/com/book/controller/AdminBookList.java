package com.book.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.book.entity.BookCategory;
import com.book.entity.BookInfo;
import com.book.entity.Pager;
import com.book.service.book.BookInfoService;
import com.book.service.book.PageService;
import com.book.service.book.impl.BookInfoServiceImpl;
import com.book.service.book.impl.PageServiceImpl;
import com.mysql.jdbc.StringUtils;


/**
 * Servlet implementation class AdminBookList
 */
@WebServlet("/AdminBookList")
public class AdminBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookInfoService bs=new BookInfoServiceImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("getBookList".equals(op))
		{
			getBookList(request,response);
		}
		else if("addBookInfo".equals(op))
		{
			addBookInfo(request,response);
		}
		else if("findById".equals(op))
		{
			findById(request,response);
		}
		else if("updateBookInfo".equals(op))
		{
			updateBookInfo(request,response);
		}
		else if("deleteBookInfo".equals(op))
		{
			deleteBookInfo(request,response);
		}
		
	}

	private void deleteBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		boolean isOk=bs.deleteBookInfo(id);
		if(isOk)
		{
			response.sendRedirect("AdminBookList?op=getBookList");
		}
		
	}

	private void updateBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取页面的值
				int id=Integer.parseInt(request.getParameter("id"));
				String bookName=request.getParameter("bookName");
				String author=request.getParameter("author");
				int categoryId=Integer.parseInt(request.getParameter("categoryId"));
				double price=Double.parseDouble(request.getParameter("price"));
				String publisher=request.getParameter("publisher");
				String photo=request.getParameter("photo");
				System.out.println("这里的id是"+id);
				// 获得文件上传的路径
					String filePath = this.getServletContext().getRealPath("/static/photo");
					// 查看是否是
					boolean isMultipart = ServletFileUpload.isMultipartContent(request);
					if(isMultipart) {
						// 创建一个工厂对象
						FileItemFactory fac = new DiskFileItemFactory();
						// 创建一个文件上传的对象
						ServletFileUpload upload = new ServletFileUpload(fac);
						// 获得表单中的所有请求 限制10M
						upload.setFileSizeMax(10*1024*1024);
						try {
							List<FileItem> items = upload.parseRequest(request);
							// 遍历所有的请求内容
							Iterator<FileItem> it = items.iterator();
							while (it.hasNext()) {
								// 取出元素对象
								FileItem item = it.next();
								// 判断是否是普通的表单元素
								
								photo = item.getName();
									// 设置一个上传文件的对象
									File saveFile = new File(filePath, photo);
									item.write(saveFile);
								
							}
						} catch (FileUploadException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
					// 创建一个瑶保存到数据库中的实体对象
					BookInfo book = new BookInfo(id,bookName,author,categoryId,publisher,price,photo);
					// 调用业务层中保存数据的方法
					boolean isOk = bs.updateBookInfo(book);
					if(isOk) {
						response.sendRedirect("AdminBookList?op=getBookList");
					}else {
						
					}
		
	}

	private void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		BookInfo book=bs.findById(id);
		if(book!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("book", book);
			//页面跳转
			response.sendRedirect("admin/book-edit.jsp");
		}
		
	}

	private void addBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取页面的值
		String bookName=request.getParameter("bookName");
		String author=request.getParameter("author");
		int categoryId=Integer.parseInt(request.getParameter("category"));
		double price=Double.parseDouble(request.getParameter("price"));
		String publisher=request.getParameter("publisher");
		String photo=request.getParameter("photo");
		
		// 获得文件上传的路径
			String filePath = this.getServletContext().getRealPath("/static/photo");
			// 查看是否是
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart) {
				// 创建一个工厂对象
				FileItemFactory fac = new DiskFileItemFactory();
				// 创建一个文件上传的对象
				ServletFileUpload upload = new ServletFileUpload(fac);
				// 获得表单中的所有请求 限制10M
				upload.setFileSizeMax(10*1024*1024);
				try {
					List<FileItem> items = upload.parseRequest(request);
					// 遍历所有的请求内容
					Iterator<FileItem> it = items.iterator();
					while (it.hasNext()) {
						// 取出元素对象
						FileItem item = it.next();
						// 判断是否是普通的表单元素
						
						photo = item.getName();
							// 设置一个上传文件的对象
							File saveFile = new File(filePath, photo);
							item.write(saveFile);
						
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			// 创建一个瑶保存到数据库中的实体对象
			BookInfo book = new BookInfo(bookName,author,categoryId,publisher,price,photo);
			// 调用业务层中保存数据的方法
			boolean isOk = bs.addBookInfo(book);
			if(isOk) {
				response.sendRedirect("AdminBookList?op=getBookList");
			}else {
				//增加失败
				
			}
		}

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
		int pageSize=6;
		pg.setPageSize(pageSize);
		pg.setTotalCount(count);
		int from = (currpage-1)*pg.getPageSize();
		List<BookInfo> blist=pgs.getBookData(from, pageSize);
		pg.setCurrpage(currpage);
		pg.setBlist(blist);
		HttpSession session = request.getSession();
		session.setAttribute("pg", pg);
		response.sendRedirect("admin/book-mgr.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

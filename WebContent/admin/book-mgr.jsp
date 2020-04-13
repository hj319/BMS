<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>智远教育--图书网后台管理系统</h1></div>
    	<div id="info">张三，您好！&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/admin-login.jsp">登出</a></div>
    	<div class="menu">
    		<ul>
    			<li><a href="admin-home.jsp">首页</a></li>
    			<li><a href="${pageContext.request.contextPath}/AdminCategory?op=CategoryList">图书分类管理</a></li>
    			<li><a href="${pageContext.request.contextPath}/AdminBookList?op=getBookList">图书管理</a></li>
    			<li><a href="#">购书订单管理</a></li>
    		</ul>	
    	</div>
    	<div id="main">
			<div class="section-left">    	
				<h2>图书信息列表</h2>		
				<table class="table" cellspacing="0" style="font-size: 12px;">
			    	<tr>
			    		<td class="header" width="100">书名</td>
			    		<td class="header" width="60">作者</td>
			    		<td class="header" width="60">类型</td>
			    		<td class="header" width="60">售价</td>
			    		<td class="header" width="60">操作</td>
			    	</tr>
			    	<c:forEach items="${pg.blist}" var="pg">
			    		<tr>
				    		<td>${pg.bookName}</td>
				    		<td>${pg.author}</td>
				    		<td>${pg.bookcategory.category}</td>
				    		<td>￥${pg.price}</td>
				    		<td><a href="${pageContext.request.contextPath}/AdminBookList?op=deleteBookInfo&id=${pg.id}">删除</a>&nbsp;<a href="${pageContext.request.contextPath}/AdminBookList?op=findById&id=${pg.id}">编辑</a></td>
			    		</tr>
			    	</c:forEach>
			    	
			    </table>
			    <div class="paging">
    					 <span class="fr">
    					 【第${pg.currpage}页/共${pg.totalPage}页】
    					 <a href="${pageContext.request.contextPath}/AdminBookList?op=getBookList&pageIndex=1">首页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/AdminBookList?op=getBookList&pageIndex=${pg.currpage-1}">上一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/AdminBookList?op=getBookList&pageIndex=${pg.currpage+1}">下一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/AdminBookList?op=getBookList&pageIndex=${pg.totalPage}">尾页</a>&nbsp;
    					 </span>    					
    				</div>
			</div>
			<div class="section-right">
				<h2>添加图书信息</h2>
				<form action="${pageContext.request.contextPath}/AdminBookList?op=addBookInfo" method="post">
					<p>图书书名：<input type="text" name="bookName"  /></p>
					<p>图书作者：<input type="text" name="author"  /></p>
					<p>图书分类：
						<select name="category">
							<c:forEach items="${listCa}" var="c">
								<option value="${c.id}">${c.category}</option>
							</c:forEach>
						</select>
					</p>
					<p>图书售价：<input type="text" name="price"  /></p>
					<p>图书出版社：<input type="text" name="publisher"  /></p>   
					<p>图书图片：<input type="file" name="photo"  /></p>    				 				
					<p><input type="submit" value=" 保 存 "  /></p>
				</form>
			</div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
</html>

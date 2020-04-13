<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE HTML>
<html>
  <head>
    <title>智远图书网</title>
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header">
    		<div id="tool-bar">&nbsp;&nbsp; 欢迎光临智远图书网，[<a href="user-login.html">请登录</a>]&nbsp;[<a href="user-regist.html">免费注册</a>]&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/UserBookController?op=Index">首页</a>&nbsp;|&nbsp;<a href="cart.html">购物车</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;<a href="#">帮助</a></div>
    		<h1>智远图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
    	</div>
    	<form id="search-bar" action="" method="post">
    		书名：<input type="text" class="txt" name="condition" />
    		<input id="search-btn" type="submit" value=" 搜索图书 " />
    	</form>
    	<div id="main">
    		<div class="section-left">
    			<div class="box-left">
    				<div class="box-title">分类畅销榜</div>
    				<div class="box-content">
    					<c:forEach items="${listCa}" var="c">
    						<p>·<a href="${pageContext.request.contextPath}/UserBookController?op=getcatebook&id=${c.id}">${c.category}</a></p>
    					</c:forEach>				
    				</div>
    			</div>
    		</div>
    		<div class="section-right">
    			<div class="box-right">
    				<div class="box-title">您目前浏览的图书【搜索条件——分类：全部；书名：无】</div>
    				<div class="paging" style="border-bottom: 1px solid  #64A26F; color: #006666; ">
    					 <span class="fr"><a href="${pageContext.request.contextPath}/UserBookController?op=Index">首页</a>&nbsp;<a href="#">上一页</a>&nbsp;<a href="#">下一页</a>&nbsp;<a href="#">尾页</a>&nbsp;</span>
    					 共有图书1000种，分50页显示，每页显示20个商品
    				</div>
    				<c:forEach items="${list}" var="c">
    					<div class="box-item">
	    					<div class="img-box"><img src="${pageContext.request.contextPath}/static/photo/${c.photo}" /></div>
	    					<div class="info-box">
	    						<span style="font-size: 14px; "><a href="#">${c.bookName}</a></span><br />
								作者：${c.author}&nbsp;&nbsp;著<br />
								分类：${c.categoryId}<br />
								出版社：${c.publisher}<br />							
								售价：￥<span style="color: #990000;">${c.price}</span>		<br />					
	    					</div>
	    					<a href="cart.html" class="btn-buy">购&nbsp;&nbsp;买</a>    					
	    					<div class="cf"></div>
	    				</div>  
    				</c:forEach>
    				   				
    				<div class="paging">
    					 <span class="fr"><a href="#">首页</a>&nbsp;<a href="#">上一页</a>&nbsp;<a href="#">下一页</a>&nbsp;<a href="#">尾页</a>&nbsp;</span>    					
    				</div>
    			</div>
    		</div>
    		<div class="cf"></div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
</html>
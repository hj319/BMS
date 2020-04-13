<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <!DOCTYPE HTML>
<html>
  <head>
    <title>智远图书网</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header">
    		<div id="tool-bar">&nbsp;&nbsp;
    		<c:if test="${!empty bookUser }" var="res">${showUser }</c:if>
    		 欢迎光临智远图书网，[<a href="${pageContext.request.contextPath}/user/user-login.jsp">请登录</a>]&nbsp;[<a href="user-regist.html">免费注册</a>]&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/UserBookController?op=Index">首页</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/OrdersController?op=showCart&userId=${showuser}">购物车</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;<a href="#">帮助</a></div>
    		<h1>智远图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
    	</div>
    	<form id="search-bar" action="${pageContext.request.contextPath}/UserBookController?op=findByBookName" method="post">
    		书名：<input type="text" class="txt" name="bookName" />
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
						<!-- <p>·<a href="#">全部</a></p>
    					<p>·<a href="#">文学</a></p>
    					<p>·<a href="#">小说</a></p>
    					<p>·<a href="#">青春文学</a></p>
    					<p>·<a href="#">旅游</a></p>
    					<p>·<a href="#">哲学</a></p>
    					<p>·<a href="#">百科</a></p>
    					<p>·<a href="#">恐怖小说</a></p>     -->					
    				</div>
    			</div>
    		</div>
    		<div class="section-right">
    			<div class="box-right">
    				<div class="box-title">您目前浏览的图书【搜索条件——分类：全部；书名：无】</div>
    				<div class="paging" style="border-bottom: 1px solid  #64A26F; color: #006666; ">
    					 <span class="fr"><a href="#">首页</a>&nbsp;<a href="#">上一页</a>&nbsp;<a href="#">下一页</a>&nbsp;<a href="#">尾页</a>&nbsp;</span>
    					 共有图书1000种，分50页显示，每页显示20个商品
    				</div>
    				<c:forEach items="${pg.blist}" var="b">
    					<div class="box-item">
	    					<div class="img-box"><img src="${pageContext.request.contextPath}/static/photo/${b.photo}" /></div>
		    					<div class="info-box">
		    						<span style="font-size: 14px; "><a href="#">${b.bookName}</a></span><br />
									作者：${b.author}&nbsp;&nbsp;著<br />
									分类：${b.bookcategory.category}<br />
									出版社：${b.publisher}<br />							
									售价：￥<span style="color: #990000;">${b.price}</span>		<br />					
		    					</div>
		    					<a href="javascript:addcart(${b.id},${b.price})" class="btn-buy">购&nbsp;&nbsp;买</a>    					
	    					<div class="cf"></div>
    					</div>  
    				</c:forEach>
    				  
    				<%-- <div class="box-item">
    					<div class="img-box"><img src="${pageContext.request.contextPath}/static/photo/sg.jpg" /></div>
    					<div class="info-box">
    						<span style="font-size: 14px; "><a href="#">尸鬼</a></span><br />
							作者：（日）小野不由美&nbsp;&nbsp;著<br />
							分类：恐怖小说<br />
							出版社：吉林出版集团<br />							
							售价：￥<span style="color: #990000;">170.0</span>		<br />					
    					</div>
    					<a href="cart.html" class="btn-buy">购&nbsp;&nbsp;买</a>    					
    					<div class="cf"></div>
    				</div>    	
    				<div class="box-item">
    					<div class="img-box"><img src="${pageContext.request.contextPath}/static/photo/hlxj.jpg" /></div>
    					<div class="info-box">
    						<span style="font-size: 14px; "><a href="#">红楼小讲</a></span><br />
							作者：周汝昌&nbsp;&nbsp;著<br />
							分类：文学<br />
							出版社：北京出版社<br />							
							售价：￥<span style="color: #990000;">10.0</span><br />					
    					</div>
    					<a href="cart.html" class="btn-buy">购&nbsp;&nbsp;买</a>    					
    					<div class="cf"></div>
    				</div>  --%> 				
    				<div class="paging">
    					 <span class="fr">
    					 【第${pg.currpage}页/共${pg.totalPage}页】
    					 <a href="${pageContext.request.contextPath}/UserBookController?op=Index&pageIndex=1">首页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/UserBookController?op=Index&pageIndex=${pg.currpage-1}">上一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/UserBookController?op=Index&pageIndex=${pg.currpage+1}">下一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/UserBookController?op=Index&pageIndex=${pg.totalPage}">尾页</a>&nbsp;
    					 </span>    					
    				</div>
    			</div>
    		</div>
    		<div class="cf"></div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		function addcart(id,price){
			// 将数据添加到购物车中
			$.post("${pageContext.request.contextPath}/OrdersController?op=add",
					{id:id,price:price},function(res){
				if(res.result=="true"){
					alert("增加成功");
				}else{
					alert("增加失败");
				}
			});
		}
	</script>
  </body>
</html>
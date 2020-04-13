package com.book.entity;

import java.awt.print.Book;
import java.util.List;

/**
 * ��ҳ��ʵ����
 * @author asus
 *
 */
public class Pager {
	//��ǰ�ڼ�ҳ
	private int currpage;
	//ÿҳ��ʾ������
	private int pageSize;
	//����������
	private int totalCount;
	//�ܹ�����ҳ
	private int totalPage;
	private List<BookInfo> blist;
	public int getCurrpage() {
		return currpage;
	}
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount>0)
		{
			this.totalCount = totalCount;
		}
	}
	public int getTotalPage() {
		//������ҳ��
		if(totalCount%pageSize==0)
		{
			totalPage=totalCount/pageSize;
		}else {
			totalPage=totalCount/pageSize+1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<BookInfo> getBlist() {
		return blist;
	}
	public void setBlist(List<BookInfo> blist) {
		this.blist = blist;
	}
	
}

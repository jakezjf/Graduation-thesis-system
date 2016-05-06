package com.jf.common.page;

import java.util.List;

import com.fancy.cms.model.TLogBook;

/**
 * @author rc.wys
 * @date 2013-6-21 下午1:41:41
 * @DESC 列表分页
 */
public class Pagination extends SimplePage {

	/**
	 * 当前页的数据
	 */
	private List<?> list;

	public Pagination() {
	}

	/**
	 * 
	 * @DESC 构造方法
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	/**
	 * 
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param list
	 *            分页内容
	 */
	public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public Pagination(List<TLogBook> list) {
		this.list = list;
	}
	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (this.pageNo - 1) * this.pageSize;
	}

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<?> getList() {
		return this.list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}
}

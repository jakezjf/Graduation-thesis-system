package com.jf.common.page;

/**
 * 
 * @author ray.wang
 * @date 2013-1-17 下午02:24:01
 * @DESC 简单分页类
 */
public class SimplePage {

	/**
	 * 
	 * @DESC 检查页码
	 * @param pageNo
	 * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
	 */
	public static int cpn(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}

	private int DEF_COUNT = 20;

	protected int pageNo = 1;

	protected int pageSize = 20;

	protected int totalCount = 0;

	public SimplePage() {
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
	 */
	public SimplePage(int pageNo, int pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}

	/**
	 * @DESC 调整页码，使不超过最大页页码
	 */
	public void adjustPageNo() {
		if (this.pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (this.pageNo > tp) {
			this.pageNo = tp;
		}
	}

	/**
	 * 下一页页码
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return this.pageNo;
		} else {
			return this.pageNo + 1;
		}
	}

	/**
	 * 获得页码
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/**
	 * 每页几条数据
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 上一页页码
	 */
	public int getPrePage() {
		if (isFirstPage()) {
			return this.pageNo;
		} else {
			return this.pageNo - 1;
		}
	}

	/**
	 * 总共几条数据
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 总共几页
	 */
	public int getTotalPage() {
		int totalPage = this.totalCount / this.pageSize;
		if (totalPage == 0 || this.totalCount % this.pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}

	/**
	 * 是否第一页
	 */
	public boolean isFirstPage() {
		return this.pageNo <= 1;
	}

	/**
	 * 是否最后一页
	 */
	public boolean isLastPage() {
		return this.pageNo >= getTotalPage();
	}

	/**
	 * if pageNo < 1 then pageNo=1
	 * 
	 * @DESC 设置页码
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	/**
	 * if pageSize< 1 then pageSize=DEF_COUNT
	 * 
	 * @DESC 设置每页多少条记录
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = this.DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * if totalCount<0 then totalCount=0
	 * 
	 * @DESC 设置总记录数
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}
}

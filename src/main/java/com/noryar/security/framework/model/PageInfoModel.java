package com.noryar.security.framework.model;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：分页信息模型.
 * @author leon.
 *
 */
public class PageInfoModel<T> implements Serializable {

	/**
	 * 序列化参数.
	 */
	private static final long serialVersionUID = 2506551182318381293L;
	
	/**
	 * 当前页.
	 */
	private Integer currentPage;
	
	/**
	 * 总页数.
	 */
	private Integer totalPage;
	
	/**
	 * 分页容量.
	 */
	private Integer pageSize;
	
	/**
	 * 总记录数.
	 */
	private Integer totalCnt;
	
	/**
	 * 结果集记录.
	 */
	private List<T> dataList;

	/**
	 * 功能描述：当前页的获取.
	 * @return 当前页.
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * 功能描述：当前页的设定.
	 * @param currentPage 当前页.
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 功能描述：总页数的获取.
	 * @return 总页数.
	 */
	public Integer getTotalPage() {
		return totalPage;
	}

	/**
	 * 功能描述：总页数的设定.
	 * @param totalPage 总页数.
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 功能描述：分页容量的获取.
	 * @return 分页容量.
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 功能描述：分页容量的设定.
	 * @param pageSize 分页容量.
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 功能描述：总记录数的获取.
	 * @return 总记录数.
	 */
	public Integer getTotalCnt() {
		return totalCnt;
	}

	/**
	 * 功能描述：总记录数的设定.
	 * @param totalCnt 总记录数.
	 */
	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	/**
	 * 功能描述：结果集记录的获取.
	 * @return 结果集记录.
	 */
	public List<T> getDataList() {
		return dataList;
	}

	/**
	 * 功能描述：结果集记录的设定.
	 * @param dataList 结果集记录.
	 */
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}

package com.mskl.common.page;

import java.util.ArrayList;
import java.util.List;

public class QueryResult<T> {
	/** 结果数据 **/
	private List<T> resultList = new ArrayList<T>();
	/** 数据总量 **/
	private long totalCount;
	
	public List<T> getResultList() {
		return resultList;
	}
	
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}

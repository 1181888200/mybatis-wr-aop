package com.lwl.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 切换数据源路由
 * @author lwl
 * @create 2019年1月23日 下午1:11:03
 * @version 1.0
 */
public class RouteDataSource extends AbstractRoutingDataSource{

	
	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getDataSource();
	}

	
}

package com.lwl.dataSource;

import com.lwl.enums.RouteDataSourceKeyEnum;

/**
 * 用于切换数据源（可以和RouteDataSource合二为一）
 * @author lwl
 * @create 2019年1月24日 下午1:10:52
 * @version 1.0
 */
public class DatabaseContextHolder {
	
		//线程安全容器，用于高并发情况下，保证切换数据源一致
		private static ThreadLocal<String> local = new ThreadLocal<>();
		
		/**
		 * 设置数据源
		 * @param selectKey 枚举，好处就是局限性，只能输入枚举中的对象，保障输入都是有效的
		 * @author lwl
		 * @create 2019年1月23日 下午1:13:22
		 */
		public static void setDataSource(RouteDataSourceKeyEnum selectKey) {
			local.set(selectKey.name());
		}
		
		/**
		 * 获取当前数据源
		 * @return
		 * @author lwl
		 * @create 2019年1月24日 下午1:11:40
		 */
		public static String getDataSource() {
			return local.get()==null?RouteDataSourceKeyEnum.MASTER.name():local.get();
		}

		public static void clearDatabaseType() {
			local.remove();
		}
}

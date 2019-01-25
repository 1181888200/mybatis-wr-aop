package com.lwl.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.lwl.dataSource.DatabaseContextHolder;
import com.lwl.enums.RouteDataSourceKeyEnum;

/**
 * 通过注解的形式，用AOP切入到注解上，切换数据源
 * @author lwl
 * @create 2019年1月24日 下午4:37:41
 * @version 1.0
 */
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

	public static final Logger logger = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);

/**
     * 切换到从库
     *
     * @param proceedingJoinPoint
     * @param readOnlyConnection
     * @return
     * @throws Throwable
     */
	@Around("@annotation(readOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection) throws Throwable {
			try {
				logger.info("设置只读数据库");
				DatabaseContextHolder.setDataSource(RouteDataSourceKeyEnum.SLAVE);
				Object result = proceedingJoinPoint.proceed();
				return result;
			}finally {
				DatabaseContextHolder.clearDatabaseType();
	            logger.info("restore database connection");
	        }
	}

	@Override
	public int getOrder() {
		return 0;
	}
}

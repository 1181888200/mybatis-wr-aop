package com.lwl.aop2;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lwl.dataSource.DatabaseContextHolder;
import com.lwl.enums.RouteDataSourceKeyEnum;

/**
 * 切面类 将注解放在service实现类的方法前，自动设置当前数据源为注解中数据源。 切换数据源(不同方法调用不同数据源)
 * 
 * @author lwl
 * @create 2019年1月24日 下午4:00:09
 * @version 1.0
 */
@Aspect
@Order(1)
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {

	/**
	 * 切入点
	 * @author lwl
	 * @create 2019年1月24日 下午4:28:32
	 */
	@Pointcut("execution(* com.lwl.service.*.*(..))")
	public void aspect() {
		
	}

	/**
	 * 配置前置处理,使用在方法aspect()上注册的切入点，绑定数据源信息
	 */
	@Before("aspect()")
	public void before(JoinPoint point) {
		Object target = point.getTarget();
		String method = point.getSignature().getName();
		Class<?> classz = target.getClass();
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = classz.getMethod(method, parameterTypes);
			//通过判断执行方法的前缀是不是get、find、query ，如果是则从从库中查询数据源
			//此方法一般根据业务需要做相应的调整
			Pattern p=Pattern.compile("^get|^find|^query");
	        Matcher match=p.matcher(m.getName());
			if (match.find()) {
				DatabaseContextHolder.setDataSource(RouteDataSourceKeyEnum.SLAVE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 配置后置处理，清空数据源信息
	 * 
	 * @param point
	 */
	@After("aspect()")
	public void after(JoinPoint point) {
		DatabaseContextHolder.clearDatabaseType();
	}

}

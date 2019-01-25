package com.lwl.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 利用AOP将设置数据库的操作从代码中抽离,这里的粒度控制在方法级别,所以利用注解的形式标注这个方法涉及的数据库事务只读,走从库.
 * 
 * @author lwl
 * @create 2019年1月24日 下午3:08:14
 * @version 1.0
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnlyConnection {

}

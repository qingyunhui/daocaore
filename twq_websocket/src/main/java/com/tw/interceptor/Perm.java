package com.tw.interceptor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限配置
 * @author db2admin
 *
 */
@Retention(RetentionPolicy.RUNTIME)//指定该注解是在运行期进行
@Target(ElementType.METHOD)//指定该注解要在方法上使用
public @interface Perm {
	/* 权限值 */
	String privilegeValue();
}
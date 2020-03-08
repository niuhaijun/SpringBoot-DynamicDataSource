package com.niu.springbootdynamicdatasource.apiutil.annotation;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 会在拦截器那里判断是否有这个注解，如果存在把结果包装成 {code:'',data:''} 的形式
 *
 * @author LDZ
 * @date 2020-03-02 16:36
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResponseBody {

	/**
	 * 序列化可选 fastjson
	 *
	 * @return
	 * @see SerializerFeature
	 */
	SerializerFeature[] serializerFeature() default {};
}

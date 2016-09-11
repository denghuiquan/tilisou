package com.tilisou.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型工具类，<br/>用于支持公共接口泛型类型的确定
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-9 下午8:01:52
 */
public class GenericsUtils {
	/**
	 * 通过反射,获得指定类的父类的泛型参数的实际类型. 如XXServiceBean extends DaoSupport<XX>
	 * 
	 * @param clazz
	 *            clazz 需要反射的类,该类必须继承范型父类
	 * @param index
	 *            index 泛型参数所在索引,从0开始：int.
	 * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) {
		// 得到泛型父类
		Type genType = clazz.getGenericSuperclass();
		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
		// DaoSupport<XXX,Contact>就返回Buyer和Contact类型
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		
		if (index >= params.length || index < 0) {
			throw new RuntimeException("你输入的索引"
					+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如BuyerServiceBean extends DaoSupport<XXX>
	 * 
	 * @param clazz
	 *            clazz 需要反射的类,该类必须继承泛型父类
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 通过反射,获得方法返回值泛型参数的实际类型. 如: public Map<String, XXX> getNames(){}
	 * 
	 * @param method
	 *            method 方法：Method
	 * @param index index 泛型参数所在索引,从0开始：int.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getMethodGenericReturnType(Method method, int index) {
		// 得到方法返回的泛型类型
		Type returnType = method.getGenericReturnType();
		// 判断返回类型实现ParameterizedType接口没有
		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			if (index >= typeArguments.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			return (Class) typeArguments[index];
		}
		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		return Object.class;
	}
	
	/**
	 * 通过反射,获得方法返回值第一个泛型参数的实际类型. 如: public Map<String, XXX> getNames(){}
	 * 
	 * @param method
	 *            method 方法：Method
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getMethodGenericReturnType(Method method) {
		return getMethodGenericReturnType(method, 0);
	}
    
	/**
	 * 通过反射,获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String,
	 * XXX> maps, List<String> names){}
	 * 
	 * @param method 
	 *            method 方法：Method
	 * @param index index 第几个输入参数：int
	 * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
	 */
	@SuppressWarnings("rawtypes")
	public static List<Class> getMethodGenericParameterTypes(Method method,
			int index) {
		List<Class> results = new ArrayList<Class>();
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if (index >= genericParameterTypes.length || index < 0) {
			throw new RuntimeException("你输入的索引"
					+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		Type genericParameterType = genericParameterTypes[index];
		if (genericParameterType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericParameterType;
			Type[] parameterArgTypes = aType.getActualTypeArguments();
			for (Type parameterArgType : parameterArgTypes) {
				Class parameterArgClass = (Class) parameterArgType;
				results.add(parameterArgClass);
			}
			return results;
		}
		return results;
	}

	/**
	 * 通过反射,获得方法输入参数第一个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String, XXX>
	 * maps, List<String> names){}
	 * 
	 * @param method
	 *            method 方法:Method
	 * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
	 */
	@SuppressWarnings("rawtypes")
	public static List<Class> getMethodGenericParameterTypes(Method method) {
		return getMethodGenericParameterTypes(method, 0);
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, XXX> names;
	 * 
	 * @param field
	 *            field 字段：Field
	 * @param index
	 *            index 泛型参数所在索引,从0开始：int.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getFieldGenericType(Field field, int index) {
		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index >= fieldArgTypes.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			return (Class) fieldArgTypes[index];
		}
		return Object.class;
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, XXX> names;
	 * 
	 * @param field
	 *            field 字段：Field
	 * @param index index 泛型参数所在索引,从0开始:int.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getFieldGenericType(Field field) {
		return getFieldGenericType(field, 0);
	}
}

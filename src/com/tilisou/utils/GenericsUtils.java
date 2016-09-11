package com.tilisou.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ���͹����࣬<br/>����֧�ֹ����ӿڷ������͵�ȷ��
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-9 ����8:01:52
 */
public class GenericsUtils {
	/**
	 * ͨ������,���ָ����ĸ���ķ��Ͳ�����ʵ������. ��XXServiceBean extends DaoSupport<XX>
	 * 
	 * @param clazz
	 *            clazz ��Ҫ�������,�������̳з��͸���
	 * @param index
	 *            index ���Ͳ�����������,��0��ʼ��int.
	 * @return ���Ͳ�����ʵ������, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ���
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) {
		// �õ����͸���
		Type genType = clazz.getGenericSuperclass();
		// ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�ֱ�ӷ���Object.class
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// ���ر�ʾ������ʵ�����Ͳ�����Type���������,������ŵĶ��Ƕ�Ӧ���͵�Class, ��BuyerServiceBean extends
		// DaoSupport<XXX,Contact>�ͷ���Buyer��Contact����
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		
		if (index >= params.length || index < 0) {
			throw new RuntimeException("�����������"
					+ (index < 0 ? "����С��0" : "�����˲���������"));
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * ͨ������,���ָ����ĸ���ĵ�һ�����Ͳ�����ʵ������. ��BuyerServiceBean extends DaoSupport<XXX>
	 * 
	 * @param clazz
	 *            clazz ��Ҫ�������,�������̳з��͸���
	 * @return ���Ͳ�����ʵ������, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ���
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * ͨ������,��÷�������ֵ���Ͳ�����ʵ������. ��: public Map<String, XXX> getNames(){}
	 * 
	 * @param method
	 *            method ������Method
	 * @param index index ���Ͳ�����������,��0��ʼ��int.
	 * @return ���Ͳ�����ʵ������, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ���
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getMethodGenericReturnType(Method method, int index) {
		// �õ��������صķ�������
		Type returnType = method.getGenericReturnType();
		// �жϷ�������ʵ��ParameterizedType�ӿ�û��
		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			if (index >= typeArguments.length || index < 0) {
				throw new RuntimeException("�����������"
						+ (index < 0 ? "����С��0" : "�����˲���������"));
			}
			return (Class) typeArguments[index];
		}
		// ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�ֱ�ӷ���Object.class
		return Object.class;
	}
	
	/**
	 * ͨ������,��÷�������ֵ��һ�����Ͳ�����ʵ������. ��: public Map<String, XXX> getNames(){}
	 * 
	 * @param method
	 *            method ������Method
	 * @return ���Ͳ�����ʵ������, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ���
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getMethodGenericReturnType(Method method) {
		return getMethodGenericReturnType(method, 0);
	}
    
	/**
	 * ͨ������,��÷������������index��������������з��Ͳ�����ʵ������. ��: public void add(Map<String,
	 * XXX> maps, List<String> names){}
	 * 
	 * @param method 
	 *            method ������Method
	 * @param index index �ڼ������������int
	 * @return ��������ķ��Ͳ�����ʵ�����ͼ���, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ��ؿռ���
	 */
	@SuppressWarnings("rawtypes")
	public static List<Class> getMethodGenericParameterTypes(Method method,
			int index) {
		List<Class> results = new ArrayList<Class>();
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if (index >= genericParameterTypes.length || index < 0) {
			throw new RuntimeException("�����������"
					+ (index < 0 ? "����С��0" : "�����˲���������"));
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
	 * ͨ������,��÷������������һ��������������з��Ͳ�����ʵ������. ��: public void add(Map<String, XXX>
	 * maps, List<String> names){}
	 * 
	 * @param method
	 *            method ����:Method
	 * @return ��������ķ��Ͳ�����ʵ�����ͼ���, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ��ؿռ���
	 */
	@SuppressWarnings("rawtypes")
	public static List<Class> getMethodGenericParameterTypes(Method method) {
		return getMethodGenericParameterTypes(method, 0);
	}

	/**
	 * ͨ������,���Field���Ͳ�����ʵ������. ��: public Map<String, XXX> names;
	 * 
	 * @param field
	 *            field �ֶΣ�Field
	 * @param index
	 *            index ���Ͳ�����������,��0��ʼ��int.
	 * @return ���Ͳ�����ʵ������, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ���
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getFieldGenericType(Field field, int index) {
		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index >= fieldArgTypes.length || index < 0) {
				throw new RuntimeException("�����������"
						+ (index < 0 ? "����С��0" : "�����˲���������"));
			}
			return (Class) fieldArgTypes[index];
		}
		return Object.class;
	}

	/**
	 * ͨ������,���Field���Ͳ�����ʵ������. ��: public Map<String, XXX> names;
	 * 
	 * @param field
	 *            field �ֶΣ�Field
	 * @param index index ���Ͳ�����������,��0��ʼ:int.
	 * @return ���Ͳ�����ʵ������, ���û��ʵ��ParameterizedType�ӿڣ�����֧�ַ��ͣ�����ֱ�ӷ���
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Class getFieldGenericType(Field field) {
		return getFieldGenericType(field, 0);
	}
}

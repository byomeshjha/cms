package com.lms.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;


/**
 * Universal Comparator depends on the exact name of the
 * field definition in application Bean/DTO.
 * 
 * Failure to pass accurate field name will return list 
 * without sorting.
 * 
 * @author bjha
 *
 * @param <T>
 */
public class UniversalComparator<T> implements Comparator<T> {
	
	String sortName = "";

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
	/**
	 * overridden compare method for interface
	 * Comparator. The comparison primarily uses 
	 * reflection to get the declared field definition 
	 * in the associated bean definition. Based on the 
	 * sortName provided, this method tries to find the
	 * declared field with same name as sortOrder and 
	 * executes comparison on the declared fields of the
	 * 2 objects passed. 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(T o1, T o2) {
		
		try {
			Class<?> clazz = o1.getClass();
			
			//System.out.println("Class Name: " + clazz.getName());
			//Field[] fields = clazz.getDeclaredFields();
			//for(Field f:fields) {
			//	System.out.println("Field Name: " + f.getName());
			//}
			
			Field field1 = clazz.getDeclaredField(sortName);
			field1.setAccessible(true);
			Object value1 = field1.get(o1);
			Class<?> clazz2 = o2.getClass();
			Field field2 = clazz2.getDeclaredField(sortName);
			field2.setAccessible(true);
			Object value2 = field2.get(o2);
			
			if(value1 instanceof String) {
				return ((String) value1).compareToIgnoreCase((String) value2);
			}
			else if(value1 instanceof Integer) {
				return ((Integer) value1).compareTo((Integer) value2);
			}
			else if(value1 instanceof Long) {
				return ((Long) value1).compareTo((Long) value2);
			}
			else if(value1 instanceof Double) {
				return ((Double) value1).compareTo((Double) value2);
			}
			else if(value1 instanceof Float) {
				return ((Float) value1).compareTo((Float) value2);
			}
			else if(value1 instanceof BigDecimal) {
				return ((BigDecimal) value1).compareTo((BigDecimal) value2);			
			}
			else if(value1 instanceof Date) {
				return ((Date) value1).compareTo((Date) value2);			
			}
			else if(value1 instanceof Enum) {
				return ((Enum)value1).compareTo((Enum)value2);
			}
			else {
				return 0;
			}
		}
		catch(Exception e) {
			e.printStackTrace(System.err);
			return 0;
		}
	}
}

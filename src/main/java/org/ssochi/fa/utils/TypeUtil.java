package org.ssochi.fa.utils;

public class TypeUtil {
	public static Class<?> getWarpClass(Class<?> clazz) {
		if (clazz == long.class) {
			return Long.class;
		}
		if (clazz == int.class) {
			return Integer.class;
		}
		if (clazz == char.class) {
			return Character.class;
		}
		if (clazz == byte.class) {
			return Byte.class;
		}
		if (clazz == float.class) {
			return Float.class;
		}
		if (clazz == double.class) {
			return Double.class;
		}
		if (clazz == short.class) {
			return Short.class;
		}
		if (clazz == boolean.class) {
			return Boolean.class;
		}
		return clazz;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getOrDefault(Class<T> target, Object val, T defaultValue) {
		if (val == null) {
			return defaultValue;
		}
		return (T) val;
	}
}

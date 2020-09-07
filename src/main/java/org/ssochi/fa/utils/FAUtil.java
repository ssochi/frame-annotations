package org.ssochi.fa.utils;

import lombok.Cleanup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.ssochi.fa.annotations.views.View;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.FAView;
import org.ssochi.fa.core.engine.interfaces.VueFunction;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.jsoup.nodes.Document;
import org.ssochi.fa.core.vue.StringVueFunction;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

public class FAUtil {
	public static final String HTML_FRAGMENT_PATH = "htmlFragment/";
	public static final String HTML_JS_FRAGMENT_PATH = "jsFragment/";
	private static final Map<String, Element> fragmentCache = new ConcurrentHashMap<>();
	private static final Map<String, VueFunction> jsFragmentCache = new ConcurrentHashMap<>();
	private static final String regex = "\\$\\{%s\\}";

	public static FAView buildView(FAField field) {
		Annotation viewAnnotation = field.getView();
		String viewName = viewAnnotation.annotationType().getSimpleName();
		View vw = viewAnnotation.annotationType().getAnnotation(View.class);
		if (vw == null) {
			throw new FARunningTimeException("在注解%s没有找到View注解", viewName);
		}
		try {
			Constructor<? extends FAView> constructor = vw.bind().getConstructor(FAField.class);
			return constructor.newInstance(field);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FARunningTimeException("构建%s失败", viewName);
		}
	}

	public static Element readElement(Class<?> viewClass, String fileName, Map<String, String> context, String identity) {
		return readElement(viewClass, fileName, context, identity, false);
	}

	//TODO 后续优化
	public static Element readElement(Class<?> viewClass, String fileName, Map<String, String> context, String identity, boolean force) {
		String key = identity + fileName;
		if (!fragmentCache.containsKey(key) || force) {
			try {
				String fragment = readAndReplace(viewClass, HTML_FRAGMENT_PATH, fileName, context);
				Document document = Jsoup.parseBodyFragment(fragment);
				fragmentCache.put(key, document.body());
			} catch (Exception e) {
				e.printStackTrace();
				throw new FARunningTimeException("无法解析文件 = %s", fileName);
			}
		}
		return fragmentCache.get(key);
	}

	public static VueFunction readVueFunction(Class<?> viewClass, String fileName, Map<String, String> context, String identity) {
		return readVueFunction(viewClass, fileName, context, identity, false);
	}

	public static VueFunction readVueFunction(Class<?> viewClass, String fileName, Map<String, String> context, String identity, boolean force) {
		String key = identity + fileName;
		if (!jsFragmentCache.containsKey(key) || force) {
			try {
				String fragment = readAndReplace(viewClass, HTML_JS_FRAGMENT_PATH, fileName, context);
				StringVueFunction func = new StringVueFunction(fragment);
				jsFragmentCache.put(key, func);
			} catch (Exception e) {
				e.printStackTrace();
				throw new FARunningTimeException("无法解析文件 = %s", fileName);
			}
		}
		return jsFragmentCache.get(key);
	}

	public static String readAndReplace(Class<?> viewClass, String resourcePath, String path, Map<String, String> context) throws IOException {
		@Cleanup InputStream ism = viewClass.getResourceAsStream(resourcePath + path);
		@Cleanup InputStreamReader ismReader = new InputStreamReader(ism);
		@Cleanup BufferedReader reader = new BufferedReader(ismReader);
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String fragment = sb.toString();

		if (context == null || context.isEmpty()) {
			return fragment;
		}
		for (Map.Entry<String, String> entry : context.entrySet()) {
			fragment = fragment.replaceAll(String.format(regex, entry.getKey()), entry.getValue());
		}
		return fragment;
	}
}

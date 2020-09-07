package org.ssochi.fa.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.ssochi.fa.core.engine.interfaces.VueFunction;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.jsoup.nodes.Document;
import org.ssochi.fa.core.vue.StringVueFunction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FAUtil {
	public static final String HTML_FRAGMENT_PATH = "src/main/resources/htmlFragment/";
	public static final String HTML_JS_FRAGMENT_PATH = "src/main/resources/jsFragment/";
	private static final Map<String, Element> fragmentCache = new ConcurrentHashMap<>();
	private static final Map<String, VueFunction> jsFragmentCache = new ConcurrentHashMap<>();
	private static final String regex = "\\$\\{%s\\}";

	public static Element readElement(String fileName, Map<String, String> context, String identity) {
		return readElement(fileName, context, identity,false);
	}

	//TODO 后续优化
	public static Element readElement(String fileName, Map<String, String> context, String identity, boolean force) {
		String key = identity + fileName;
		if (!fragmentCache.containsKey(key) || force) {
			try {
				String fragment = readAndReplace(HTML_FRAGMENT_PATH, fileName, context);
				Document document = Jsoup.parseBodyFragment(fragment);
				fragmentCache.put(key, document.body());
			} catch (Exception e) {
				e.printStackTrace();
				throw new FARunningTimeException("无法解析文件 = %s", fileName);
			}
		}
		return fragmentCache.get(key);
	}
    public static VueFunction readVueFunction(String fileName, Map<String, String> context, String identity){
	    return readVueFunction(fileName, context, identity,false);
    }
	public static VueFunction readVueFunction(String fileName, Map<String, String> context, String identity, boolean force) {
		String key = identity + fileName;
		if (!jsFragmentCache.containsKey(key) || force) {
			try {
				String fragment = readAndReplace(HTML_JS_FRAGMENT_PATH, fileName, context);
				StringVueFunction func = new StringVueFunction(fragment);
				jsFragmentCache.put(key, func);
			} catch (Exception e) {
				e.printStackTrace();
				throw new FARunningTimeException("无法解析文件 = %s", fileName);
			}
		}
		return jsFragmentCache.get(key);
	}

	public static String readAndReplace(String resourcePath, String path, Map<String, String> context) throws IOException {
		String fragment = Files.lines(Paths.get(resourcePath + path), StandardCharsets.UTF_8).reduce("", (a, b) -> a + b);
		for (Map.Entry<String, String> entry : context.entrySet()) {
			fragment = fragment.replaceAll(String.format(regex, entry.getKey()), entry.getValue());
		}
		return fragment;
	}
}

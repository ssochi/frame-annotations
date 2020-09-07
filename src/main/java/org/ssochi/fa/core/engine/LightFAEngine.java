package org.ssochi.fa.core.engine;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.annotations.views.View;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.FAView;
import org.ssochi.fa.core.engine.interfaces.*;
import org.ssochi.fa.core.exceptions.FARunningTimeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


public class LightFAEngine implements FAEngine {
	private FAFieldGenerator fieldGenerator = new LightFAFieldGenerator();
	private HtmlRender htmlRender = new LightHtmlRender();
	private Map<Class<?>, List<FAView>> cache = new HashMap<>();

	public String generateHtmlContext(Object vm) {
		Preconditions.checkNotNull(vm);
		List<FAView> views = getCachedFields(vm);
		assignFieldValue(vm, views);
		return htmlRender.render(views);
	}

	private void assignFieldValue(Object vm, List<FAView> views) {
		for (FAView view : views) {
			String fieldName = view.getFieldName();
			try {
				Field declaredField = vm.getClass().getDeclaredField(fieldName);
				declaredField.setAccessible(true);
				Object val = declaredField.get(vm);
				view.setFieldValue(val);
			} catch (Exception e) {
				e.printStackTrace();
				throw new FARunningTimeException("在获取%s时出错", fieldName);
			}
		}
	}

	private List<FAView> getCachedFields(Object vm) {
		Class<?> vmClass = vm.getClass();
		ViewModel viewModel = vmClass.getAnnotation(ViewModel.class);
		if (viewModel == null) {
			throw new FARunningTimeException("you need annotated the class to display with ViewModel annotation");
		}

		if (cache.containsKey(vmClass)) {
			return cache.get(vmClass);
		}

		List<FAField> fields = new ArrayList<>();
		for (Field validField : getValidFields(vm)) {
			fields.add(fieldGenerator.generate(viewModel, validField));
		}
		sort(fields, viewModel.title());
		List<FAView> views = new LinkedList<>();
		for (FAField field : fields) {
			FAView view = buildView(field);
			view.check();
			views.add(view);
		}

		cache.put(vmClass, views);
		return views;
	}

	private FAView buildView(FAField field) {
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

	private void sort(List<FAField> fields, String viewModelName) {
		Map<String, FAField> dict = fields.stream().collect(Collectors.toMap((Function<FAField, String>) field -> field.getViewProperties().prev(), (Function<FAField, FAField>) field -> field));
		List<FAField> collect = fields.stream().filter(f -> f.getViewProperties().prev().isEmpty()).collect(Collectors.toList());
		if (collect.size() != 1) {
			throw new FARunningTimeException("you must and only to set on prev eq empty string ");
		}
		fields.clear();
		FAField head = collect.get(0);
		fields.add(head);
		while (dict.get(head.getFieldName()) != null) {
			head = dict.get(head.getFieldName());
			fields.add(head);
		}
	}

	private List<Field> getValidFields(Object vm) {
		Field[] fields = vm.getClass().getDeclaredFields();
		List<Field> res = new LinkedList<>();
		for (Field field : fields) {
			for (Annotation annotation : field.getAnnotations()) {
				if (annotation.annotationType().getAnnotation(View.class) != null) {
					res.add(field);
				}
			}
		}
		return res;
	}
}


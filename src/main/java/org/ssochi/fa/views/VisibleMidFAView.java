package org.ssochi.fa.views;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.jsoup.nodes.Element;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.utils.ElementFactory;
import org.ssochi.fa.utils.FAUtil;
import org.jsoup.nodes.Document;
import org.ssochi.fa.utils.TypeUtil;

import java.util.HashMap;
import java.util.Map;


public class VisibleMidFAView extends FormItemView {
	public static final String FIELD_MI_IDS = "miIds";
	public static final String FIELD_EPS = "eps";
	public static final String METHOD_HANDLE_COMMAND = "handleCommandMethod";
	public static final String METHOD_ADD_ALL_MI_IDS = "addAllMiIdsMethod";
	public static final String FILE_FRAGMENT = "VisibleMidView.fragment";
	public static final String FILE_JS_FRAGMENT = "VisibleMidView.jsFragment";

	public VisibleMidFAView(FAField field) {
		super(field);
	}

	@Override
	protected void drawViewVue(DrawableVue vue) {
		vue.addForm(getFieldName(), TypeUtil.getOrDefault(String.class, fieldValue, ""));
		vue.add(localName(FIELD_EPS), getEps());
		vue.addFunction(FAUtil.readVueFunction(FILE_JS_FRAGMENT, context(), getFieldName(), false));
	}


	private JsonArray getEps() {
		JsonArray array = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "wanqilin");
		jsonObject.addProperty("id", "1310673343");
		array.add(jsonObject);
		jsonObject = new JsonObject();
		jsonObject.addProperty("name", "leijun");
		jsonObject.addProperty("id", "233333");
		array.add(jsonObject);
		return array;
	}

	@Override
	protected void onBuildContext(Map<String, String> cxt) {
		cxt.put(FIELD_MI_IDS, majorVarRef());
		cxt.put(FIELD_EPS, localName(FIELD_EPS));
		cxt.put(METHOD_HANDLE_COMMAND, localName(METHOD_HANDLE_COMMAND));
		cxt.put(METHOD_ADD_ALL_MI_IDS, localName(METHOD_ADD_ALL_MI_IDS));
	}

	@Override
	protected void drawFormItem(ElementFactory creator, Element formItem) {
		formItem.appendChild(FAUtil.readElement(FILE_FRAGMENT, context(), getFieldName()));
	}

	@Override
	public void check() {
		if (isValidType(String.class)) {
			return;
		}
		throw inValidTypeException();
	}
}

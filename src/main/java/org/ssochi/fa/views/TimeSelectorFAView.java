package org.ssochi.fa.views;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.jsoup.nodes.Document;
import org.ssochi.fa.utils.ElementFactory;
import org.ssochi.fa.utils.FAUtil;

import static org.ssochi.fa.utils.Constants.*;

public class TimeSelectorFAView extends FormItemView {
	public static final String fragment_file = "TimeSelectorView.fragment";

	public TimeSelectorFAView(FAField field) {
		super(field);
	}

	@Override
	protected void drawViewVue(DrawableVue vue) {
		String val = fieldValue == null ? "" : (String) fieldValue;
		vue.addForm(majorVarName(), val);
	}

	@Override
	protected void onBuildContext(Map<String, String> context) {
		context.put("startTime", majorVarRef());
		super.onBuildContext(context);
	}

	@Override
	protected void drawFormItem(ElementFactory doc, Element formItem) {
		formItem.appendChild(readElement(fragment_file));
	}

	@Override
	public void check() {
		if (isValidType(String.class)) {
			return;
		}
		throw inValidTypeException();
	}
}

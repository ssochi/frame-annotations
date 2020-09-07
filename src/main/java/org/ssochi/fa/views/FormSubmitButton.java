package org.ssochi.fa.views;

import org.jsoup.nodes.Element;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.utils.ElementFactory;

public class FormSubmitButton extends FormItemView {
	public static final String fragment = "FormSubmitButton.fragment";
	public static final String jsFragment = "FormSubmitButton.jsFragment";

	public FormSubmitButton(FAField field) {
		super(field);
	}

	@Override
	protected void drawViewVue(DrawableVue vue) {
		vue.addFunction(readFunction(jsFragment));

	}

	@Override
	protected void drawFormItem(ElementFactory creator, Element formItem) {
		formItem.appendChild(readElement(fragment));
	}

	@Override
	public void check() {

	}
}

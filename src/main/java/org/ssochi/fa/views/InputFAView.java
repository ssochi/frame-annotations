package org.ssochi.fa.views;

import org.jsoup.nodes.Element;
import org.ssochi.fa.annotations.views.InputView;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.utils.ElementFactory;
import org.ssochi.fa.utils.TypeUtil;
import org.jsoup.nodes.Document;

import java.util.Optional;

import static org.ssochi.fa.utils.Constants.*;

public class InputFAView extends FormItemView {
	public InputFAView(FAField field) {
		super(field);
	}

	InputView inputView() {
		return (InputView) getView();
	}

	@Override
	protected void drawFormItem(ElementFactory creator, Element formItem) {
		Element input = creator.create(EL_INPUT);
		input.attr(V_MODEL, "");
		input.attr(V_DISABLE, inputView().disable() ? TRUE : FALSE);
		input.attr(V_MODEL, FORM_SUFFIX + getFieldName());

		if (inputView().textArea()) {
			input.attr(TYPE, "textarea");
		}

		formItem.appendChild(input);
	}


	@Override
	public void check() {
		checkSupportType(getFieldType());
	}

	@Override
	public void drawViewVue(DrawableVue vue) {
		vue.addForm(getFieldName(), Optional.ofNullable(fieldValue).orElse(inputView().defaultText()).toString());
	}

	protected void checkSupportType(Class<?> fieldType) {
		if (Number.class.isAssignableFrom(TypeUtil.getWarpClass(fieldType)) || String.class.isAssignableFrom(fieldType))
			return;
		throw new FARunningTimeException("InputView 不支持这种Field类型(%s)", fieldType.getSimpleName());
	}
}

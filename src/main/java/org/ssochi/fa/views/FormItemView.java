package org.ssochi.fa.views;

import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.ssochi.fa.annotations.PreCondition;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.FAView;
import org.ssochi.fa.core.html.HtmlItem;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.utils.ElementFactory;
import org.ssochi.fa.utils.TypeUtil;

import static org.ssochi.fa.utils.Constants.*;

/**
 * 以FormItem为父节点的View
 */
public abstract class FormItemView extends FAView {
	public FormItemView(FAField field) {
		super(field);
	}

	@Override
	final public HtmlItem drawHtmlItem(Document doc) {
		Element formItem = doc.createElement(EL_FORM_ITEM);
		formItem.attr(LABEL, getViewProperties().title());
		if (getViewProperties().visible()) {
			formItem.attr(V_IF, TRUE);
		}

		renderPreCondition(formItem);

		drawFormItem(new ElementFactory(doc), formItem);
		return new HtmlItem(formItem);
	}

	private void renderPreCondition(Element formItem) {
		PreCondition preCondition = getPreCondition();
		if (preCondition == null)
			return;
		String attr = preCondition.action().attr();
		formItem.attr(attr, FORM_SUFFIX + preCondition.previous() + preCondition.condition());
	}

	@Override
	final public void drawVue(DrawableVue vue) {
		drawViewVue(vue);
	}

	protected abstract void drawViewVue(DrawableVue vue);

	protected abstract void drawFormItem(ElementFactory creator, Element formItem);

	protected boolean isValidType(Class<?>... classes) {
		for (Class<?> aClass : classes) {
			if (aClass.isAssignableFrom(TypeUtil.getWarpClass(getFieldType()))) {
				return true;
			}
		}
		return false;
	}

	protected FARunningTimeException inValidTypeException() {
		return new FARunningTimeException("%s 不支持 类型 %s", this.getClass().getSimpleName(), getFieldType().getSimpleName());
	}

	/**
	 * 对于定义在data中的一般变量 使用这个方法生产名字
	 */
	protected String localName(String name) {
		return getFieldName() + "_" + name;
	}

	/**
	 * 对于主属性使用这个方法生产名字
	 */
	protected String majorVarRef() {
		return FORM_SUFFIX + getFieldName();
	}

	/**
	 * @return 主属性名称
	 */
	protected String majorVarName() {
		return getFieldName();
	}

	protected void putLocal(Map<String, String> context, String key) {
		context.put(key, localName(key));
	}
}


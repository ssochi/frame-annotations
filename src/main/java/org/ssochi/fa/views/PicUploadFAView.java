package org.ssochi.fa.views;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.ssochi.fa.annotations.views.PicUploadView;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.models.PicUploadModelGroup;
import org.ssochi.fa.utils.ElementFactory;
import org.ssochi.fa.utils.FAUtil;
import org.ssochi.fa.utils.GsonUtil;


import static org.ssochi.fa.utils.Constants.*;

public class PicUploadFAView extends FormItemView {
	public static final PicUploadModelGroup group = new PicUploadModelGroup();

	public PicUploadFAView(FAField field) {
		super(field);
	}

	public static final String handle_preview = "handle_preview";
	public static final String handle_remove = "handle_remove";
	public static final String file_list = "file_list";
	public static final String dialog_visible = "dialog_visible";
	public static final String dialog_image = "dialog_image";

	public static final String fragment_file = "PicUploadView.fragment";
	public static final String js_fragment_file = "PicUploadView.jsFragment";


	@Override
	protected void drawViewVue(DrawableVue vue) {
		vue.addForm(majorVarName(), GsonUtil.from(fieldValue, group));
		vue.add(localName(dialog_visible), false);
		vue.add(localName(dialog_image), EMPTY_STRING);

		vue.addFunction(readFunction(js_fragment_file));
	}

	@Override
	protected void onBuildContext(Map<String, String> context) {
		putLocal(context, handle_preview);
		putLocal(context, handle_remove);
		putLocal(context, dialog_image);
		putLocal(context, dialog_visible);
		putLocal(context, "handle_change");
		context.put(file_list, majorVarRef());
		PicUploadView view = (PicUploadView) getView();
		context.put("max_pic_num", String.valueOf(view.maxPictureCount() - 1));
		context.put("width", String.valueOf(view.width()));
		context.put("height", String.valueOf(view.height()));
	}

	@Override
	protected void drawFormItem(ElementFactory doc, Element formItem) {
		formItem.appendChild(readElement(fragment_file));
	}

	@Override
	public void check() {
		if (PicUploadModelGroup.class.isAssignableFrom(getFieldType()))
			return;
		throw new FARunningTimeException("PicUploadView 不支持这种Field类型(%s)", getFieldType().getSimpleName());
	}
}

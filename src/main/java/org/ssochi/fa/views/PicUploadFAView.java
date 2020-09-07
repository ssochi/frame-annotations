package org.ssochi.fa.views;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.models.PicUploadModelGroup;
import org.ssochi.fa.utils.FileUtil;
import org.ssochi.fa.utils.GsonUtil;
import org.jsoup.nodes.Document;


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

		vue.addFunction(FileUtil.readVueFunction(js_fragment_file,context(),getFieldName()));
	}

	public Map<String, String> context() {
		Map<String, String> context = new HashMap<>();
		putLocal(context, handle_preview);
		putLocal(context, handle_remove);
		putLocal(context, dialog_image);
		putLocal(context, dialog_visible);
		context.put(file_list, majorVarRef());
		return context;
	}

	@Override
	protected void drawFormItem(Document doc, Element formItem) {
		formItem.appendChild(FileUtil.readElement(fragment_file, context(), getFieldName()));
	}

	@Override
	public void check() {
		if (PicUploadModelGroup.class.isAssignableFrom(getFieldType()))
			return;
		throw new FARunningTimeException("PicUploadView 不支持这种Field类型(%s)", getFieldType().getSimpleName());
	}
}

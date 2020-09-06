package org.ssochi.fa.views;

import org.jsoup.nodes.Element;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.utils.TypeUtil;
import org.jsoup.nodes.Document;

import static org.ssochi.fa.utils.Constants.FORM_SUFFIX;
import static org.ssochi.fa.utils.Constants.V_MODEL;

public class SwitchFAView extends FormItemView {
    public SwitchFAView(FAField field) {
        super(field);
    }

    @Override
    protected void drawViewVue(DrawableVue vue) {
        Boolean active = fieldValue == null ? false : (Boolean) fieldValue;
        vue.addForm(getFieldName(),active);
    }

    String switchActive(){
        return FORM_SUFFIX + getFieldName();
    }

    @Override
    protected void drawFormItem(Document doc, Element formItem) {
        Element eSwitch = doc.createElement("el-switch");
        eSwitch.attr(V_MODEL, switchActive());
        eSwitch.attr("active-color","#13ce66");
        eSwitch.attr("inactive-color","#ff4949");
        formItem.appendChild(eSwitch);
    }

    @Override
    public void check() {
        Class<?> warpClass = TypeUtil.getWarpClass(getFieldType());
        if (Boolean.class.isAssignableFrom(warpClass))
            return;
        throw new FARunningTimeException("SwitchFAView 不支持这种Field类型(%s)",getFieldType().getSimpleName());
    }
}

package org.ssochi.fa.views;

import org.jsoup.nodes.Element;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.jsoup.nodes.Document;

import static org.ssochi.fa.utils.Constants.*;

public class TimeSelectorFAView extends FormItemView {
    public TimeSelectorFAView(FAField field) {
        super(field);
    }
    @Override
    protected void drawViewVue(DrawableVue vue) {
        String val = fieldValue == null ? "" : (String) fieldValue;
        vue.addForm(getFieldName(),val);
    }

    @Override
    protected void drawFormItem(Document doc, Element formItem) {
        Element div = doc.createElement(DIV);
        div.attr(CLASS,"block");

        Element picker = doc.createElement("el-date-picker");
        picker.attr(V_MODEL,getDateName());
        picker.attr(TYPE,"datetime");
        picker.attr("placeholder","选择日期时间");
        picker.attr("value-format","timestamp");

        div.appendChild(picker);
        formItem.appendChild(picker);
    }

    private String getDateName() {
        return FORM_SUFFIX + getFieldName();
    }

    @Override
    public void check() {
        if (isValidType(String.class)){
            return;
        }
        throw inValidTypeException();
    }
}

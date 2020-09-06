package org.ssochi.fa.core;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.ssochi.fa.core.html.HtmlItem;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;

/**
 * 最基础的View
 */
public abstract class FAView extends FAField{
    @Setter
    protected Object fieldValue;

    public FAView(FAField field) {
        super(field.getContractAnnotations(),field.getPreCondition(),field.getViewProperties(),field.getViewModel(),
                field.getView(),field.getFieldName(),field.getFieldType());
    }

    abstract public void check();
    abstract public HtmlItem drawHtmlItem(Document document);
    abstract public void drawVue(DrawableVue vue);
}

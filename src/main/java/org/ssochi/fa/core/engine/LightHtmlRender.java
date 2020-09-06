package org.ssochi.fa.core.engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.ssochi.fa.core.FAView;
import org.ssochi.fa.core.html.HtmlItem;
import org.ssochi.fa.core.engine.interfaces.HtmlRender;
import org.ssochi.fa.core.engine.interfaces.VueRender;
import org.ssochi.fa.core.html.FAHtml;
import org.ssochi.fa.core.vue.Vue;

import java.util.List;

import static org.ssochi.fa.utils.Constants.*;
import static org.ssochi.fa.utils.Constants.LABEL_WIDTH;

public class LightHtmlRender implements HtmlRender {
    VueRender vueRender = new LightVueRender();
    @Override
    public String render(List<FAView> views) {
        FAHtml html = new FAHtml();
        Document doc  = Jsoup.parseBodyFragment("");
        Vue vue = new Vue();
        for (FAView view : views) {
            view.drawVue(vue);
            HtmlItem htmlItem = view.drawHtmlItem(doc);
            html.appendHtmlItem(htmlItem);
        }
        return render(html,vue,doc);
    }
    private String render(FAHtml html, Vue vue, Document doc) {
        drawHead(doc);
        drawBody(html,doc);
        Element vueEle = drawVue(vue,doc);
        doc.body().appendChild(vueEle);
        return doc.toString();
    }
    private void drawHead(Document doc) {
        Element head = doc.head();
        Element cssLink = doc.createElement(LINK);
        cssLink.attr(REL,"stylesheet");
        cssLink.attr(TYPE,"text/css");
        cssLink.attr(HREF,CSS_LINK_HREF);
        head.appendChild(cssLink);

        Element vueImport = doc.createElement(SCRIPT);
        vueImport.attr(SRC,VUE_SRC);
        head.appendChild(vueImport);

        Element elementImport = doc.createElement(SCRIPT);
        elementImport.attr(SRC,ELEMENT_SRC);
        head.appendChild(elementImport);

        Element meta = doc.createElement("meta");
        meta.attr("http-equiv","Content-Type");
        meta.attr("content","text/html");
        meta.attr("charset","utf-8");
        head.appendChild(meta);


    }

    private Element drawVue(Vue vue, Document doc) {
        Element script = doc.createElement(SCRIPT);
        script.text(renderVue(vue));
        return script;
    }

    private String renderVue(Vue vue) {
        vue.add(DATA_LABEL_POSITION,"top");
        return vueRender.render(vue);
    }

    private void drawBody(FAHtml html, Document doc) {
        Element body = doc.body();
        Element div = doc.createElement(DIV);
        div.attr(ID,APP);
        Element form = drawForm(html,doc);
        div.appendChild(form);
        body.appendChild(div);
    }

    private Element drawForm(FAHtml html, Document doc) {
        Element form = doc.createElement(EL_FORM);
        form.attr(REF,FORM);
        form.attr(V_MODEL,FORM);
        form.attr(V_LABEL_POSITION, DATA_LABEL_POSITION);
        form.attr(LABEL_WIDTH,"160px");

        for (HtmlItem htmlItem : html.getHtmlItems()) {
            form.appendChild(htmlItem.getHtmlElement());
        }
        return form;
    }
}

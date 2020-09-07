package org.ssochi.fa.core.engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.ssochi.fa.annotations.ViewModel;
import org.ssochi.fa.annotations.views.FormSubmitButtonView;
import org.ssochi.fa.annotations.views.ViewProperties;
import org.ssochi.fa.core.FAField;
import org.ssochi.fa.core.FAView;
import org.ssochi.fa.core.engine.interfaces.FAFieldGenerator;
import org.ssochi.fa.core.exceptions.FARunningTimeException;
import org.ssochi.fa.core.html.HtmlItem;
import org.ssochi.fa.core.engine.interfaces.HtmlRender;
import org.ssochi.fa.core.engine.interfaces.VueRender;
import org.ssochi.fa.core.html.FAHtml;
import org.ssochi.fa.core.vue.Vue;
import org.ssochi.fa.utils.FAUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.ssochi.fa.utils.Constants.*;
import static org.ssochi.fa.utils.Constants.LABEL_WIDTH;

public class LightHtmlRender implements HtmlRender {
	VueRender vueRender = new LightVueRender();

	@ViewProperties
	@FormSubmitButtonView
	private String formSubmitButton;

	@Override
	public String render(List<FAView> views, ViewModel viewModel) {
		FAHtml html = new FAHtml();
		Document doc = Jsoup.parseBodyFragment("");
		Vue vue = new Vue();
		dealViewModel(viewModel, views);
		for (FAView view : views) {
			view.drawVue(vue);
			HtmlItem htmlItem = view.drawHtmlItem(doc);
			html.appendHtmlItem(htmlItem);
		}
		String result = render(html, vue, doc, viewModel);
		result = result.replace("&lt;","<");
		result = result.replace("&gt;",">");
		return result;
	}

	private void dealViewModel(ViewModel viewModel, List<FAView> views) {
		if (!viewModel.submitUrl().isEmpty()) {
			FAFieldGenerator generator = new LightFAFieldGenerator();
			try {
				FAField view = generator.generate(viewModel, this.getClass().getDeclaredField("formSubmitButton"));
				views.add(FAUtil.buildView(view));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				throw new FARunningTimeException("无法生成提交按钮");
			}
		}
	}

	private String render(FAHtml html, Vue vue, Document doc, ViewModel viewModel) {
		drawHead(doc, viewModel);
		drawBody(html, doc, viewModel);
		Element vueEle = drawVue(vue, doc);
		doc.body().appendChild(vueEle);
		return doc.html();
	}

	private void drawHead(Document doc, ViewModel viewModel) {
		Element head = doc.head();
		Element cssLink = doc.createElement(LINK);
		cssLink.attr(REL, "stylesheet");
		cssLink.attr(TYPE, "text/css");
		cssLink.attr(HREF, CSS_LINK_HREF);
		head.appendChild(cssLink);

		Element vueImport = doc.createElement(SCRIPT);
		vueImport.attr(SRC, VUE_SRC);
		head.appendChild(vueImport);

		Element elementImport = doc.createElement(SCRIPT);
		elementImport.attr(SRC, ELEMENT_SRC);
		head.appendChild(elementImport);

		Element meta = doc.createElement("meta");
		meta.attr("http-equiv", "Content-Type");
		meta.attr("content", "text/html");
		meta.attr("charset", "utf-8");
		head.appendChild(meta);
		Element title = doc.createElement("title");
		title.text(viewModel.title());
		head.appendChild(title);
	}

	private Element drawVue(Vue vue, Document doc) {
		Element script = doc.createElement(SCRIPT);
		script.text(renderVue(vue));
		return script;
	}

	private String renderVue(Vue vue) {
		vue.add(DATA_LABEL_POSITION, "top");
		return vueRender.render(vue);
	}

	private void drawBody(FAHtml html, Document doc, ViewModel viewModel) {
		Element body = doc.body();

		Element div = doc.createElement(DIV);
		div.attr(ID, APP);

		Element h2 = doc.createElement("h2");
		h2.attr("style", "color:#606266;");
		h2.text(viewModel.title());
		div.appendChild(h2);

		Element hr = doc.createElement("HR");
		hr.attr("style","color:#606266;");
		div.appendChild(hr);

		Element form = drawForm(html, doc);
		div.appendChild(form);

		body.appendChild(div);
	}

	private Element drawForm(FAHtml html, Document doc) {
		Element form = doc.createElement(EL_FORM);
		form.attr(REF, FORM);
		form.attr(V_MODEL, FORM);
		form.attr(V_LABEL_POSITION, DATA_LABEL_POSITION);
		form.attr(LABEL_WIDTH, "160px");

		for (HtmlItem htmlItem : html.getHtmlItems()) {
			form.appendChild(htmlItem.getHtmlElement());
		}

		return form;
	}
}

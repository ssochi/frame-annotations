package org.ssochi.fa.core.html;


import org.jsoup.nodes.Element;

public class HtmlItem {
	Element element;

	public HtmlItem(Element element) {
		this.element = element;
	}

	public Element getHtmlElement() {
		return element;
	}
}

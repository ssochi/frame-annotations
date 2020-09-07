package org.ssochi.fa.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ElementFactory {
	private Document doc;

	public ElementFactory(Document doc) {
		this.doc = doc;
	}

	public Element create(String tagName) {
		return doc.createElement(tagName);
	}
}
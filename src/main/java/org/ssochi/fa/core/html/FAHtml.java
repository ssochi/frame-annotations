package org.ssochi.fa.core.html;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class FAHtml {
    @Setter
    String title;
    @Getter
    List<HtmlItem> htmlItems = new LinkedList<>();

    public void appendHtmlItem(HtmlItem htmlItem){
        htmlItems.add(htmlItem);
    }

}

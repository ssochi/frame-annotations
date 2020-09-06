package org.ssochi.fa.core.engine.interfaces;
public interface FAEngine {
    /**
     * 通过传入的viewModel生产Html页面
     * @return html页面
     */
    public  String generateHtmlContext(Object viewModel);
}

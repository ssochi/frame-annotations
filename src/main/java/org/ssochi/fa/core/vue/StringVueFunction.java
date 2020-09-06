package org.ssochi.fa.core.vue;

import org.ssochi.fa.core.engine.interfaces.VueFunction;

public class StringVueFunction implements VueFunction {
    private String content;

    public StringVueFunction(String content) {
        this.content = content;
    }

    @Override
    public String render() {
        return content;
    }
}

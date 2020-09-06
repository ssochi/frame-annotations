package org.ssochi.fa.core.vue;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.ssochi.fa.core.engine.interfaces.DrawableVue;
import org.ssochi.fa.core.engine.interfaces.VueFunction;
import org.ssochi.fa.core.exceptions.DuplicatedVueFunctionException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Vue implements DrawableVue {
    private JsonObject data;
    private JsonObject form;
    private List<VueFunction> methods = new LinkedList<>();

    public Vue(){
        data = new JsonObject();
        form = new JsonObject();
        data.add("form",form);
    }

    @Override
    public void add(String property, JsonElement value) {
        data.add(property, value);
    }

    @Override
    public void add(String property, Number value) {
        data.addProperty(property, value);
    }

    @Override
    public void add(String property, String value) {
        data.addProperty(property, value);
    }

    @Override
    public void add(String property, Boolean value) {
        data.addProperty(property, value);
    }

    @Override
    public void add(String property, Character value) {
        data.addProperty(property, value);
    }

    @Override
    public void addForm(String property, JsonElement value) {
        form.add(property, value);
    }

    @Override
    public void addForm(String property, Number value) {
        form.addProperty(property, value);
    }

    @Override
    public void addForm(String property, String value) {
        form.addProperty(property, value);
    }

    @Override
    public void addForm(String property, Boolean value) {
        form.addProperty(property, value);
    }

    @Override
    public void addForm(String property, Character value) {
        form.addProperty(property, value);
    }

    @Override
    public String formSuffix(String fieldName) {
        return "form." + fieldName;
    }

    @Override
    public void addFunction(VueFunction func){
        methods.add(func);
    }

    public String getMethods(){
        StringBuilder sb = new StringBuilder();
        for (VueFunction func : methods) {
            sb.append(func.render()).append(',');
        }
        return sb.toString();
    }

    public JsonObject getData() {
        return data;
    }
}

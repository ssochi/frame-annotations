package org.ssochi.fa.core.engine.interfaces;

import com.google.gson.JsonElement;

import org.ssochi.fa.core.vue.VueStructureFunction;

public interface DrawableVue {
	public void add(String property, JsonElement value);

	public void add(String property, Number value);

	public void add(String property, String value);

	public void add(String property, Boolean value);

	public void add(String property, Character value);

	public void addForm(String property, JsonElement value);

	public void addForm(String property, Number value);

	public void addForm(String property, String value);

	public void addForm(String property, Boolean value);

	public void addForm(String property, Character value);

	public String formSuffix(String fieldName);

	public void addFunction(VueFunction func);
}

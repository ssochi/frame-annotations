package org.ssochi.fa.core.engine;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.ssochi.fa.core.engine.interfaces.VueRender;
import org.ssochi.fa.core.vue.Vue;

public class LightVueRender implements VueRender {
	@Override
	public String render(Vue vue) {
		StringBuilder sb = new StringBuilder();
		sb.append("var Main={");

		renderData(vue.getData(), sb);
		sb.append(",");

		renderMethod(vue, sb);

		sb.append("};");
		sb.append("var Ctor = Vue.extend(Main);new Ctor().$mount('#app');");
		return sb.toString();
	}

	private void renderMethod(Vue vue, StringBuilder sb) {
		sb.append("methods:{");
		sb.append(vue.getMethods());
		sb.append("}");
	}

	private void renderData(JsonObject data, StringBuilder sb) {
		sb.append("data(){");
		sb.append("return").append(new Gson().toJson(data));
		sb.append("}");
	}

}

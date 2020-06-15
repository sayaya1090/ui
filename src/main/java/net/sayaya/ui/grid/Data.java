package net.sayaya.ui.grid;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.Builder;

@JsType
public class Data {
	public Data put(String key, Object value) {
		Js.asPropertyMap(this).set(key, value);
		return this;
	}
	public Data delete(String key) {
		Js.asPropertyMap(this).delete(key);
		return this;
	}
	public Data attribute(Data attribute) {
	return put("_attributes", attribute);
}
	public Data children(Data... children) {
		return put("_children", children);
	}
	public <T> T get(String key, Class<T> clazz) {
		return (T) Js.asPropertyMap(this).get(key);
	}

	@JsType
	@Builder
	@SuppressWarnings("unusable-by-js")
	public static class Attribute {
		@JsProperty
		private boolean expanded;
	}
}
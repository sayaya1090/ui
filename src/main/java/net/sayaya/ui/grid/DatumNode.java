package net.sayaya.ui.grid;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@JsType
@Accessors(chain=true)
public class DatumNode {
	public DatumNode put(String key, Object value) {
		Js.asPropertyMap(this).set(key, value);
		return this;
	}
	public DatumNode delete(String key) {
		Js.asPropertyMap(this).delete(key);
		return this;
	}
	public DatumNode attribute(Attribute attribute) {
		return put("_attributes", attribute);
	}
	public DatumNode children(DatumNode... children) {
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

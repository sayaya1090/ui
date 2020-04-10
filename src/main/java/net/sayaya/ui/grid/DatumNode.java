package net.sayaya.ui.grid;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@JsType
@Setter
@Getter
@ToString
@Accessors(chain=true)
public class DatumNode {
	@JsMethod
	public DatumNode put(String key, Object value) {
		Js.asPropertyMap(this).set(key, value);
		return this;
	}
	@JsMethod
	public DatumNode delete(String key) {
		Js.asPropertyMap(this).delete(key);
		return this;
	}
	@JsMethod
	public DatumNode attribute(Attribute attribute) {
		return put("_attributes", attribute);
	}
	@JsMethod
	public DatumNode children(DatumNode... children) {
		return put("_children", children);
	}
	@JsMethod
	@SuppressWarnings("unusable-by-js")
	public <T> T get(String key, Class<T> clazz) {
		return (T) Js.asPropertyMap(this).get(key);
	}

	@JsType
	@Builder
	public static class Attribute {
		private boolean expanded;
	}
}

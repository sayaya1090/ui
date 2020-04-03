package net.sayaya.ui.grid;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
public final class Datum {
	@JsOverlay
	public Datum put(String key, Object value) {
		Js.asPropertyMap(this).set(key, value);
		return this;
	}
	@JsOverlay
	public Datum delete(String key) {
		Js.asPropertyMap(this).delete(key);
		return this;
	}
	@JsOverlay
	public <T> T get(String key, Class<T> clazz) {
		return (T) Js.asPropertyMap(this).get(key);
	}
	@JsOverlay
	public Datum setAttribute(Attribute attribute) {
		return put("_attributes", attribute);
	}
	@JsOverlay
	public Datum setChildren(Datum[] data) {
		return put("_children", data);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_= {@JsOverlay})
	@Accessors(chain=true)
	public final static class Attribute {
		@JsProperty(name="expanded")
		private boolean expanded;
		@JsProperty(name="rowspan")
		private RowSpan rowspan;

		@JsOverlay
		public static RowSpan rowspan(String column, int span) {
			RowSpan s = new RowSpan();
			Js.asPropertyMap(s).set(column, span);
			return s;
		}
		@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
		@Setter(onMethod_= {@JsOverlay})
		@Accessors(chain=true)
		public final static class RowSpan {
			private RowSpan(){}
		}
	}
}

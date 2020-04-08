package net.sayaya.ui.grid;

import jsinterop.annotations.*;
import jsinterop.base.Js;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@JsType
@Setter
@Getter
@ToString
@Accessors(chain=true)
public class Datum {
	@JsMethod
	public Datum put(String key, Object value) {
		Js.asPropertyMap(this).set(key, value);
		return this;
	}
	@JsMethod
	public Datum delete(String key) {
		Js.asPropertyMap(this).delete(key);
		return this;
	}
/*	@JsMethod
	public Datum setAttribute(DatumAttribute attribute) {
		return put("_attributes", attribute);
	}*/
	@JsMethod
	@SuppressWarnings("unusable-by-js")
	public <T> T get(String key, Class<T> clazz) {
		return (T) Js.asPropertyMap(this).get(key);
	}
}

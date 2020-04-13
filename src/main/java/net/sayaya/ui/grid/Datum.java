package net.sayaya.ui.grid;

import jsinterop.annotations.*;
import jsinterop.base.Js;

@JsType
public class Datum {
	public Datum put(String key, Object value) {
		Js.asPropertyMap(this).set(key, value);
		return this;
	}
	public Datum delete(String key) {
		Js.asPropertyMap(this).delete(key);
		return this;
	}
/*	@JsMethod
	public Datum setAttribute(DatumAttribute attribute) {
		return put("_attributes", attribute);
	}*/

	public static <T> T get(Datum datum, String key, Class<T> clazz) {
		return (T) Js.asPropertyMap(datum).get(key);
	}
}

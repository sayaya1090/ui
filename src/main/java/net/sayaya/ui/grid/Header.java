package net.sayaya.ui.grid;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_= {@JsOverlay})
@Getter(onMethod_= {@JsOverlay})
@Accessors(fluent=true)
public final class Header {
	@JsProperty(name="height")
	private Double height;
}

package net.sayaya.ui.grid;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_= {@JsOverlay})
@Accessors(fluent=true)
public final class TreeColumnOption {
	@JsProperty(name="name")
	private String id;
	@JsProperty(name="useIcon")
	private boolean useIcon;
	@JsProperty(name="useCascadingCheckbox")
	private boolean useCascadingCheckbox;
}

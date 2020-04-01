package net.sayaya.ui.grid;

import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_= {@JsOverlay})
@Accessors(fluent=true)
public final class GridBuilder {
	@JsProperty(name="el")
	private HTMLDivElement element;
	@JsProperty(name="scrollX")
	private boolean scrollX;
	@JsProperty(name="scrollY")
	private boolean scrollY;
	@JsProperty(name="columns")
	private Column[] columns;
	@JsProperty(name="treeColumnOptions")
	private TreeColumnOption treeColumnOption;
	@JsProperty(name="data")
	private Datum[] data;
	GridBuilder(){}
	@JsOverlay
	public Grid build() {
		return new Grid(this);
	}
}

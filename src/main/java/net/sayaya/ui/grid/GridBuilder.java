package net.sayaya.ui.grid;

import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import lombok.AccessLevel;
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
	@JsProperty(name="header")
	private Header header;
	@JsProperty(name="columns")
	private Column<?>[] columns;
	@JsProperty(name="columnOptions")
	private Column.ColumnOption colunmOptions;
	@Setter(AccessLevel.NONE)
	@JsProperty(name="rowHeight")
	private Object rowHeight;
	@JsProperty(name="minRowHeight")
	private Double minRowHeight;
	@JsProperty(name="minBodyHeight")
	private Double minBodyHeight;
	@JsProperty(name="treeColumnOptions")
	private TreeColumnOption treeColumnOption;
	@JsProperty(name="data")
	private Datum[] data;
	GridBuilder(){}
	@JsOverlay
	public Grid build() {
		return new Grid(this);
	}
	@JsOverlay
	public GridBuilder setRowHeight(double height) {
		rowHeight = height;
		return this;
	}
	@JsOverlay
	public GridBuilder setRowHeightAuto() {
		rowHeight = "auto";
		return this;
	}
}

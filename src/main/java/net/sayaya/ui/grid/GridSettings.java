package net.sayaya.ui.grid;

import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.LinkedList;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_= {@JsOverlay})
@Accessors(fluent=true)
public final class GridSettings {
	@JsProperty(name="el")
	private HTMLDivElement element;
	@JsProperty
	private boolean scrollX;
	@JsProperty
	private boolean scrollY;
	@JsProperty
	private boolean showDummyRows;
	@JsProperty
	private boolean heightResizable;
	@JsProperty
	private HeaderOption header;
	@JsProperty
	private Column<?>[] columns;
	@JsProperty
	private HeaderRow[] rowHeaders;
	@JsProperty
	private ColumnOption colunmOptions;
	@JsProperty
	private CopyOption copyOptions;
	@Setter(AccessLevel.NONE)
	@JsProperty
	private Object rowHeight;
	@JsProperty
	private Double minRowHeight;
	@JsProperty
	private Double minBodyHeight;
	@JsProperty
	@Setter(AccessLevel.NONE)
	private String editingEvent;
	@JsProperty
	@Setter(AccessLevel.NONE)
	private String selectionUnit;
	@JsProperty
	private Datum[] data;
	GridSettings(){}
	@JsOverlay
	public Grid element() {
		return new Grid(this);
	}
	@JsOverlay
	public GridSettings rowHeight(int height) {
		rowHeight = height + 0.0;
		return this;
	}
	@JsOverlay
	public GridSettings rowHeightAuto() {
		rowHeight = "auto";
		return this;
	}
	@JsOverlay
	public GridSettings editingEvent(@NonNull EditTrigger trigger) {
		editingEvent = trigger.name();
		return this;
	}
	@JsOverlay
	public GridSettings selectionUnit(@NonNull SelectionUnit unit) {
		selectionUnit = unit.name();
		return this;
	}
	@JsProperty
	private LinkedList<Column<?>> _columns;
	@JsOverlay
	public GridSettings column(Column<?> column) {
		if(_columns == null) _columns = new LinkedList<>();
		_columns.add(column);
		this.columns = _columns.toArray(new Column[0]);
		return this;
	}
	public enum EditTrigger {
		dbclick, click
	}
	public enum SelectionUnit {
		cell, row
	}
}

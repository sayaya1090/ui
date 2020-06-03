package net.sayaya.ui.grid;

import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.LinkedList;

import static org.jboss.elemento.Elements.div;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_= {@JsOverlay})
@Accessors(fluent=true)
public final class GridTreeSettings {
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
	private DatumNode[] data;
	@JsProperty
	private TreeColumnOption treeColumnOptions;
	GridTreeSettings(){}
	@JsOverlay
	public GridTree element() {
		return new GridTree(div(), this);
	}
	@JsOverlay
	public GridTreeSettings rowHeight(int height) {
		rowHeight = height + 0.0;
		return this;
	}
	@JsOverlay
	public GridTreeSettings rowHeightAuto() {
		rowHeight = "auto";
		return this;
	}
	@JsOverlay
	public GridTreeSettings editingEvent(@NonNull GridSettings.EditTrigger trigger) {
		editingEvent = trigger.name();
		return this;
	}
	@JsOverlay
	public GridTreeSettings selectionUnit(@NonNull GridSettings.SelectionUnit unit) {
		selectionUnit = unit.name();
		return this;
	}
	@JsProperty
	private LinkedList<Column<?>> _columns;
	@JsOverlay
	public GridTreeSettings column(Column<?> column) {
		if(_columns == null) _columns = new LinkedList<>();
		_columns.add(column);
		this.columns = _columns.toArray(new Column[0]);
		return this;
	}

	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_= {@JsOverlay})
	@Accessors(fluent=true)
	public static final class TreeColumnOption {
		private String name;
		private boolean useIcon;
		private boolean useCascadingCheckbox;
	}
}

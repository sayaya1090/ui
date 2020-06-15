package net.sayaya.ui.grid;

import elemental2.core.JsObject;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.LinkedList;

import static org.jboss.elemento.Elements.div;

public class Grid extends HTMLElementBuilder<HTMLDivElement, Grid> {
	public static GridBuilder builder() {
		return new GridBuilder();
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_ = {@JsOverlay})
	@Accessors(fluent=true)
	public final static class GridBuilder {
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
		private Data[] data;
		@JsProperty
		private TreeColumnOption treeColumnOptions;

		private GridBuilder(){}
		@JsOverlay
		public GridBuilder rowHeight(int height) {
			rowHeight = height + 0.0;
			return this;
		}
		@JsOverlay
		public GridBuilder rowHeightAuto() {
			rowHeight = "auto";
			return this;
		}
		@JsOverlay
		public GridBuilder editingEvent(@NonNull EditTrigger trigger) {
			editingEvent = trigger.name();
			return this;
		}
		@JsOverlay
		public GridBuilder selectionUnit(@NonNull SelectionUnit unit) {
			selectionUnit = unit.name();
			return this;
		}
		@JsProperty
		private LinkedList<Column<?>> _columns;
		@JsOverlay
		public GridBuilder column(Column<?> column) {
			if(_columns == null) _columns = new LinkedList<>();
			_columns.add(column);
			this.columns = _columns.toArray(new Column[0]);
			return this;
		}
		@JsOverlay
		public Grid build() {
			HtmlContentBuilder<HTMLDivElement> div = div().style("overflow: hidden;");
			Grid elem = new Grid(div, this.element(div.element()));
			return elem;
		}
	}
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private final ToastGrid toastGrid;
	private Grid(HtmlContentBuilder<HTMLDivElement> e, GridBuilder settings) {
		super(e);
		toastGrid = new ToastGrid(settings);
		_this = e;
		layout();
	}
	private void layout() {

	}
	public Grid values(Data[] data) {
		toastGrid.setData(data, null);
		return this;
	}
	public Data[] values() {
		return toastGrid.getData();
	}
	@Override
	public Grid that() {
		return this;
	}

	@JsType(isNative = true, namespace = "tui", name="Grid")
	final static class ToastGrid {
		ToastGrid(GridBuilder settings) {}
		// public native void resetData(Data[] data);
		public native void setData(Data[] data, Object callback);
		public native Data[] getData();
		public native void sort(String column, boolean asc);
		public native void unsort(String column);

		public native void appendRow();
		public native void prependRow();
		public native void removeRow();
		public native void check();
		public native void uncheck();
		public native static void applyTheme(String template, JsObject custom);
	}
}
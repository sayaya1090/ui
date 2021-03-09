package net.sayaya.ui.sheet;

import com.google.gwt.core.client.Scheduler;
import elemental2.core.JsArray;
import elemental2.core.JsNumber;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import jsinterop.base.Js;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.HTMLElementBuilder;
import net.sayaya.ui.sheet.function.AfterGetColumnHeaderRenderers;
import net.sayaya.ui.sheet.function.AfterGetRowHeaderRenderers;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.Arrays;

import static org.jboss.elemento.Elements.div;

public class Sheet extends HTMLElementBuilder<HTMLDivElement, Sheet> {
	public static SheetConfiguration builder() {
		return new SheetConfiguration();
	}
	private final Handsontable table;
	private final SheetConfiguration configuration;
	Sheet(SheetConfiguration setting) {
		this(div(), setting);
	}
	private Sheet(HtmlContentBuilder<HTMLDivElement> e, SheetConfiguration setting) {
		super(e);
		this.configuration = setting;
		table = new Handsontable(e.element(), setting);
		Js.asPropertyMap(table).set("spreadsheet", this);
	}
	public Data[] values() {
		Data[] data = configuration.data;
		if(data == null) return new Data[]{};
		else return data;
	}
	public Sheet values(Data... data) {
		configuration.data(data);
		Scheduler.get().scheduleDeferred(()->table.updateSettings(configuration));
		return that();
	}
	public Sheet append(Data data) {
		configuration.append(data);
		Scheduler.get().scheduleDeferred(()->table.updateSettings(configuration));
		return that();
	}
	public Sheet delete(String id) {
		configuration.delete(id);
		Scheduler.get().scheduleDeferred(()->table.updateSettings(configuration));
		return that();
	}
	public Sheet refresh() {
		table.render();
		return this;
	}
	public Sheet selectRow(int start) {
		table.selectRows(start);
		return this;
	}
	public Sheet selectRows(int start, int end) {
		table.selectRows(start, end);
		return this;
	}
	public Column[] columns() {
		return table.getSettings().columns;
	}
	public Handsontable table() {
		return table;
	}
	SheetConfiguration configuration() {
		return configuration;
	}
	@Override
	public Sheet that() {
		return this;
	}

	@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Handsontable")
	public final static class Handsontable {
		@JsProperty(name="spreadsheet")
		public Sheet spreadsheet;
		public Handsontable(Element element, SheetConfiguration setting) {};
		public native void render();
		public native void updateSettings(SheetConfiguration setting);
		public native SheetConfiguration getSettings();
		public native int countRows();
		public native int countCols();
		public native boolean selectColumns(int start, int end);
		public native boolean selectColumns(int start);
		public native boolean selectRows(int start, int end);
		public native boolean selectRows(int start);
		public native boolean selectCell(int row, int column);
		public native Element getCell(int row, int col, boolean topmost);
		public native void setDataAtCell(int row, int col, Object value);
		public native void alter(String action, int idex, int amount);
	}

	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_={@JsOverlay, @JsIgnore})
	@Getter(onMethod_={@JsOverlay, @JsIgnore})
	@Accessors(fluent=true)
	public final static class SheetConfiguration {
		private Data[] data;
		private String stretchH;
		private Integer width;
		private Object height;
		private Integer minRows;
		private Integer maxRows;
		private Integer fixedRowsTop;
		private Integer fixedColumnsLeft;
		private Object rowHeaders;
		private Object rowHeaderWidth;
		private boolean manualRowResize;
		private boolean manualColumnResize;
		private boolean manualRowMove;
		private boolean manualColumnMove;
		private Double viewportColumnRenderingOffset;
		private Object contextMenu;
		private boolean autoRowSize;
		private boolean autoColSize;
		private Column[] columns;
		private Object colHeaders;
		private boolean formulas;
		private String preventOverflow;
		private boolean disableVisualSelection;
		private Change beforeChange;
		private Object colWidths;
		// Events
		private AfterGetColumnHeaderRenderers afterGetColumnHeaderRenderers;
		private AfterGetRowHeaderRenderers afterGetRowHeaderRenderers;
		@JsConstructor
		public SheetConfiguration() {}
		@JsFunction
		interface HeaderRenderFn {
			String render(int n);
		}
		@JsFunction
		interface Change {
			boolean change(Object info, String source);
		}
		@JsOverlay
		public SheetConfiguration rowHeaders(boolean show) {
			this.rowHeaders = show;
			return this;
		}
		@JsOverlay
		public SheetConfiguration rowHeaders(HeaderRenderFn func) {
			this.rowHeaders = func;
			return this;
		}
		@JsOverlay
		public SheetConfiguration rowHeaderWidth(int rowHeaderWidth) {
			this.rowHeaderWidth = rowHeaderWidth + 0.0;
			return this;
		}
		@JsOverlay
		public SheetConfiguration rowHeaderWidth(int... rowHeaderWidth) {
			this.rowHeaderWidth = Arrays.stream(rowHeaderWidth).mapToDouble(i->i+0.0).toArray();
			return this;
		}
		@JsOverlay
		public SheetConfiguration columns(Column... columns) {
			this.columns = columns;
			colHeaders = (HeaderRenderFn) n->columns[n].headerRenderer().render(n).innerHTML;
			if(Arrays.stream(columns).anyMatch(c->c.width()!=null)) colWidths = Arrays.stream(columns).map(Column::width).map(JsNumber::new).toArray();
			else colWidths = null;
			return this;
		}
		@JsOverlay
		public SheetConfiguration preventOverflowX() {
			this.preventOverflow = "horizontal";
			return this;
		}
		@JsOverlay
		public SheetConfiguration heightAuto() {
			this.height = "auto";
			return this;
		}
		@JsOverlay
		public SheetConfiguration append(Data item) {
			if(data == null) data = new Data[] {};
			JsArray.asJsArray(data).push(item);
			return this;
		}
		@JsOverlay
		public SheetConfiguration delete(String id) {
			if(data == null) return this;
			JsArray.asJsArray(data).forEach((d, i, arr)->{
				if(id.equals(d.idx())) arr.splice(i, 1);
				return null;
			});
			return this;
		}
		@JsOverlay
		public Sheet build() {
			return new Sheet(this);
		}
	}
}



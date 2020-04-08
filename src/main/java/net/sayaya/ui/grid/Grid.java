package net.sayaya.ui.grid;

import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.div;

public class Grid implements IsHTMLElement<HTMLDivElement, Grid> {
	private final ToastGrid elem;
	private final HTMLDivElement div = div().element();
	public static GridSettings builder() {
		return new GridSettings();
	}
	Grid(GridSettings builder) {
		elem = new ToastGrid(builder.element(div));
	}
	public Grid update(Datum[] data) {
		elem.resetData(data);
		return this;
	}
	public Grid sort(String column, boolean b1, boolean b2) {
		elem.sort(column, b1, b2);
		return this;
	}
	public Grid unsort(String column) {
		elem.unsort(column);
		return this;
	}
	@Override
	public HTMLDivElement element() {
		return div;
	}
	@JsType(isNative = true, namespace = "tui", name="Grid")
	final static class ToastGrid {
		ToastGrid(GridSettings settings) {}
		public native void resetData(Datum[] data);
		public native void sort(String column, boolean b1, boolean b2);
		public native void unsort(String column);

		public native void appendRow();
		public native void prependRow();
		public native void removeRow();
		public native void check();
		public native void uncheck();
	}
}

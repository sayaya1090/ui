package net.sayaya.ui.grid;

import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.div;

public class Grid implements IsHTMLElement<HTMLDivElement, Grid> {
	private final ToastGrid elem;
	private final HTMLDivElement div = div().element();
	public static GridBuilder builder() {
		return new GridBuilder();
	}
	Grid(GridBuilder builder) {
		elem = new ToastGrid(builder.element(div));
	}
	public Grid update(Datum[] data) {
		elem.resetData(data);
		return this;
	}
	@Override
	public HTMLDivElement element() {
		return div;
	}
	@JsType(isNative = true, namespace = "tui", name="Grid")
	private final static class ToastGrid {
		ToastGrid(GridBuilder settings) {}
		public native void resetData(Datum[] data);
	}
}

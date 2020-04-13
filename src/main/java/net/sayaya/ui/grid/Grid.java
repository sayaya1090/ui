package net.sayaya.ui.grid;

import elemental2.core.JsObject;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import jsinterop.base.Js;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.style.Style;

import java.util.Map;

import static org.jboss.gwt.elemento.core.Elements.div;

public class Grid implements IsHTMLElement<HTMLDivElement, Grid> {
	private final ToastGrid elem;
	protected HTMLDivElement div = div().style("overflow: hidden;").element();
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
	public Datum[] data() {
		return elem.getData();
	}
	public Grid sort(String column, boolean b1, boolean b2) {
		elem.sort(column, b1, b2);
		return this;
	}
	public Grid unsort(String column) {
		elem.unsort(column);
		return this;
	}
	public static void theme(Map<String, Object> styles) {
		JsObject custom = new JsObject();
		for(String key: styles.keySet()) {
			Object value = styles.get(key);
			if(value instanceof Style) {
				Style cast = (Style)value;
				Js.asPropertyMap(custom).set(key, cast.toObject());
			} else if(value instanceof Map) {
				Map<String, Style> cast = (Map<String, Style>)value;
				JsObject custom2 = new JsObject();
				for(String key2: cast.keySet()) {
					Style value2 = cast.get(key2);
					Js.asPropertyMap(custom2).set(key2, value2.toObject());
				}
				Js.asPropertyMap(custom).set(key, custom2);
			}
		}
		ToastGrid.applyTheme("default", custom);
	}
	@Override
	public HTMLDivElement element() {
		return div;
	}
	@JsType(isNative = true, namespace = "tui", name="Grid")
	final static class ToastGrid {
		ToastGrid(GridSettings settings) {}
		public native void resetData(Datum[] data);
		public native Datum[] getData();
		public native void sort(String column, boolean b1, boolean b2);
		public native void unsort(String column);

		public native void appendRow();
		public native void prependRow();
		public native void removeRow();
		public native void check();
		public native void uncheck();
		public native static void applyTheme(String template, JsObject custom);
	}
}

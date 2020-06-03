package net.sayaya.ui.grid;

import elemental2.core.JsObject;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.*;
import jsinterop.base.Js;
import net.sayaya.ui.HTMLElementBuilder;
import net.sayaya.ui.style.Style;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.Map;

public class Grid extends HTMLElementBuilder<HTMLDivElement, Grid> {
	private final ToastGrid elem;
	protected final HtmlContentBuilder<HTMLDivElement> div;
	public static GridSettings builder() {
		return new GridSettings();
	}
	Grid(HtmlContentBuilder<HTMLDivElement> div, GridSettings builder) {
		super(div);
		this.div = div;
		elem = new ToastGrid(builder.element(div.element()));
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
	public Grid that() {
		return this;
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

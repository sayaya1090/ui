package net.sayaya.ui.grid;

import elemental2.core.JsObject;
import elemental2.dom.Element;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.*;

@JsType
@ToString
@Builder
@SuppressWarnings("unusable-by-js")
public class HeaderRow {
	@JsProperty(name="type")
	private String type;
	@JsProperty(name="header")
	private String name;
	@JsProperty(name="align")
	private String align;
	@JsProperty(name="renderer")
	private Object renderer;
	public enum HeaderRowType {
		rowNum, checkbox
	}
	public static class HeaderRowBuilder {
		public HeaderRowBuilder type(@NonNull HeaderRowType type) {
			this.type = type.name();
			return this;
		}
		public HeaderRowBuilder renderer(Column.CreateElement createElement, Column.Renderer<String> init, Column.Renderer<String> update) {
			Column.GetRender<String> getter = prop->{
				JsObject obj = new JsObject();
				Element elem = createElement.getElement();
				Js.asPropertyMap(obj).set("getElement", (Column.CreateElement)()->elem);
				Js.asPropertyMap(obj).set("mounted", (Column.InitializeProxy)elem2->init.render(elem, prop));
				Js.asPropertyMap(obj).set("render", (Column.RenderProxy<String>)props2->update.render(elem, props2));
				return obj;
			};
			this.renderer = getter;
			return this;
		}
		public HeaderRowBuilder renderer(Column.CreateElement createElement, Column.Renderer<String> init) {
			return renderer(createElement, init, init);
		}
		public HeaderRowBuilder renderer(Column.CreateElement createElement) {
			return renderer(createElement, (elem, prop)->{});
		}
	}
}

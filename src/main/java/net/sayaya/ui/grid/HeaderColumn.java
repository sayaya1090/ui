package net.sayaya.ui.grid;

import elemental2.core.JsObject;
import elemental2.dom.Element;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@JsType
@ToString
@Getter
@Builder
@SuppressWarnings("unusable-by-js")
public class HeaderColumn {
	@JsProperty(name="name")
	private String name;
	@JsProperty(name="align")
	private String align;
	@Getter(AccessLevel.NONE)
	@JsProperty(name="renderer")
	private Object renderer;

	public static HeaderColumnBuilder builder() {
		return new HeaderColumnBuilder();
	}
	public static class HeaderColumnBuilder {
		public HeaderColumnBuilder renderer(Column.CreateElement createElement, Column.Renderer<String> init, Column.Renderer<String> update) {
			Column.GetRender<String> getter = prop->{
				JsObject obj = new JsObject();
				Element elem = createElement.getElement();
				elem.classList.add("tui-grid-cell-content");
				Js.asPropertyMap(obj).set("getElement", (Column.CreateElement)()->elem);
				Js.asPropertyMap(obj).set("mounted", (Column.InitializeProxy)elem2->init.render(elem, prop));
				Js.asPropertyMap(obj).set("render", (Column.RenderProxy<String>)props2->update.render(elem, props2));
				return obj;
			};
			this.renderer = getter;
			return this;
		}
		public HeaderColumnBuilder renderer(Column.CreateElement createElement, Column.Renderer<String> init) {
			return renderer(createElement, init, init);
		}
		public HeaderColumnBuilder renderer(Column.CreateElement createElement) {
			return renderer(createElement, (elem, prop)->{});
		}
	}
}

package net.sayaya.ui.grid;

import elemental2.core.JsObject;
import elemental2.dom.Element;
import jsinterop.annotations.*;
import jsinterop.base.Js;
import lombok.*;
import lombok.experimental.Accessors;

@JsType
@ToString
@Getter
@Builder
@SuppressWarnings("unusable-by-js")
public class Column<T> {
	@JsProperty(name="name")
	private String name;
	@JsProperty(name="header")
	private String header;
	@JsProperty(name="align")
	private String align;
	@JsProperty(name="editor")
	private String editor;
	@Getter(AccessLevel.NONE)
	@JsProperty(name="width")
	private Integer width;
	@Getter(AccessLevel.NONE)
	@JsProperty(name="minWidth")
	private Integer widthMin;
	@JsProperty(name="sortable")
	private Boolean sortable;
	@Getter(AccessLevel.NONE)
	@JsProperty(name="sortingType")
	private String sortingType;
	@JsProperty
	private GridEvent.EventListener<T> onBeforeChange;
	@JsProperty
	private GridEvent.EventListener<T> onAfterChange;
	@Getter(AccessLevel.NONE)
	@JsProperty(name="renderer")
	private Object renderer;
	@SuppressWarnings("unusable-by-js")
	public static <T> ColumnBuilder<T> builder(Class<T> clazz) {
		return new ColumnBuilder<T>();
	}
	public static class ColumnBuilder<T> {
		public ColumnBuilder<T> sortAsc(Boolean isAsc) {
			if(isAsc == null) sortingType = null;
			else if(isAsc) sortingType = "asc";
			else sortingType = "desc";
			return this;
		}
		public ColumnBuilder<T> renderer(CreateElement createElement, Renderer<T> init, Renderer<T> update) {
			this.renderer = new JsObject();
			GetRender<T> getter = prop->{
				JsObject obj = new JsObject();
				Element elem = createElement.getElement();
				elem.classList.add("tui-grid-cell-content");
				Js.asPropertyMap(obj).set("getElement", (CreateElement)()->elem);
				Js.asPropertyMap(obj).set("mounted", (InitializeProxy)elem2->init.render(elem, prop));
				Js.asPropertyMap(obj).set("render", (RenderProxy<T>)props2->update.render(elem, props2));
				return obj;
			};
			Js.asPropertyMap(this.renderer).set("type", getter);
			return this;
		}
		public ColumnBuilder<T> renderer(CreateElement createElement, Renderer<T> init) {
			return renderer(createElement, init, init);
		}
		public ColumnBuilder<T> renderer2(CreateElement createElement, Renderer<T> init, Renderer<T> update) {
			GetRender<T> getter = prop->{
				JsObject obj = new JsObject();
				Element elem = createElement.getElement();
				elem.classList.add("tui-grid-cell-content");
				Js.asPropertyMap(obj).set("getElement", (CreateElement)()->elem);
				Js.asPropertyMap(obj).set("mounted", (InitializeProxy)elem2->init.render(elem, prop));
				Js.asPropertyMap(obj).set("render", (RenderProxy<T>)props2->update.render(elem, props2));
				return obj;
			};
			this.renderer = getter;
			return this;
		}
		public ColumnBuilder<T> renderer2(CreateElement createElement, Renderer<T> init) {
			return renderer2(createElement, init, init);
		}
	}
	@JsMethod
	public Boolean isSortAsc() {
		if(sortingType == null) return null;
		return "asc".equals(sortingType);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Getter(onMethod = {@JsOverlay})
	@Accessors(fluent=true)
	public final static class RendererProperty<T> {
		@JsProperty(name="grid")
		private Object grid;
		@JsProperty(name="rowKey")
		private Double rowKey;
		@JsProperty(name="columnInfo")
		private Column<T> columnInfo;
		@JsProperty(name="value")
		private T value;
		@JsOverlay
		public int rowKey() {
			return rowKey.intValue();
		}
	}
	@JsFunction
	@FunctionalInterface
	interface GetRender<T> {
		Object construct(RendererProperty<T> props);
	}
	@JsFunction
	@FunctionalInterface
	public interface CreateElement {
		Element getElement();
	}
	@JsFunction
	@FunctionalInterface
	interface InitializeProxy {
		void initialize(Element element);
	}
	@FunctionalInterface
	public interface Renderer<T> {
		void render(Element element, RendererProperty<T> props);
	}
	@JsFunction
	@FunctionalInterface
	interface RenderProxy<T> {
		void render(RendererProperty<T> props);
	}
}

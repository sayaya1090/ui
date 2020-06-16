package net.sayaya.ui.grid;

import elemental2.core.JsObject;
import elemental2.dom.Element;
import jsinterop.annotations.*;
import jsinterop.base.Js;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_ = {@JsOverlay})
@Accessors(fluent=true)
@SuppressWarnings("unusable-by-js")
public final class Column<T> {
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
	Column(){}
	@SuppressWarnings("unusable-by-js")
	@JsOverlay
	public static <T> ColumnBuilder<T> builder(Class<T> clazz) {
		return new ColumnBuilder<T>();
	}
	public static class ColumnBuilder<T> {
		private String name;
		private String header;
		private String align;
		private String editor;
		private Integer width;
		private Integer widthMin;
		private Boolean sortable;
		private String sortingType;
		private GridEvent.EventListener<T> onBeforeChange;
		private GridEvent.EventListener<T> onAfterChange;
		private Object renderer;
		ColumnBuilder() {}
		public ColumnBuilder<T> sortAsc(Boolean isAsc) {
			if(isAsc == null) sortingType = null;
			else if(isAsc) sortingType = "asc";
			else sortingType = "desc";
			return this;
		}
		public ColumnBuilder<T> renderer(CreateElement createElement, Renderer<T> init, Renderer<T> update) {
			this.renderer = new JsObject();
			GetRender<T> getter = prop-> {
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

		public ColumnBuilder<T> name(String name) {
			this.name = name;
			return this;
		}

		public ColumnBuilder<T> header(String header) {
			this.header = header;
			return this;
		}

		public ColumnBuilder<T> align(String align) {
			this.align = align;
			return this;
		}

		public ColumnBuilder<T> editor(String editor) {
			this.editor = editor;
			return this;
		}

		public ColumnBuilder<T> width(Integer width) {
			this.width = width;
			return this;
		}

		public ColumnBuilder<T> widthMin(Integer widthMin) {
			this.widthMin = widthMin;
			return this;
		}

		public ColumnBuilder<T> sortable(Boolean sortable) {
			this.sortable = sortable;
			return this;
		}

		public ColumnBuilder<T> sortingType(String sortingType) {
			this.sortingType = sortingType;
			return this;
		}

		public ColumnBuilder<T> onBeforeChange(GridEvent.EventListener<T> onBeforeChange) {
			this.onBeforeChange = onBeforeChange;
			return this;
		}

		public ColumnBuilder<T> onAfterChange(GridEvent.EventListener<T> onAfterChange) {
			this.onAfterChange = onAfterChange;
			return this;
		}

		public Column<T> build() {
			Column<T> column = new Column<>();
			return column.name(name).header(header).align(align).editor(editor)
						 .width(width).widthMin(widthMin).sortable(sortable)
						 .sortingType(sortingType)
						 .onBeforeChange(onBeforeChange)
						 .onAfterChange(onAfterChange)
						 .renderer(renderer);
		}
	}
	@JsOverlay
	public Boolean isSortAsc() {
		if(sortingType == null) return null;
		return "asc".equals(sortingType);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Getter(onMethod_ = {@JsOverlay})
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

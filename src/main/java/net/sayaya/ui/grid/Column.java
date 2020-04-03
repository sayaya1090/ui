package net.sayaya.ui.grid;

import elemental2.core.Function;
import elemental2.core.JsObject;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import jsinterop.annotations.*;
import jsinterop.base.Js;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_= {@JsOverlay})
@Getter(onMethod_= {@JsOverlay})
@Accessors(fluent=true)
public final class Column<T> {
	@JsProperty(name="name")
	private String name;
	@JsProperty(name="header")
	private String header;
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@JsProperty(name="renderer")
	private JsObject renderer;
	@JsProperty(name="editor")
	private String editor;
	@JsProperty(name="width")
	private Double width;
	@JsProperty(name="minWidth")
	private Double widthMin;
	@JsProperty(name="sortable")
	private Boolean sortable;
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@JsProperty(name="sortingType")
	private String sortingType;
	@JsOverlay
	public Column<T> renderer(CreateElement createElement, Renderer<T> init, Renderer<T> update) {
		this.renderer = new JsObject();
		GetRender<T> getter = (props)->{
			JsObject obj = new JsObject();
			Element elem = createElement.getElement();
			Js.asPropertyMap(obj).set("getElement", (CreateElement)()->elem);
			Js.asPropertyMap(obj).set("mounted", (InitializeProxy)elem2->init.render(elem, props));
			Js.asPropertyMap(obj).set("render", (RenderProxy<T>)props2->update.render(elem, props2));
			return obj;
		};
		Js.asPropertyMap(this.renderer).set("type", getter);
		return this;
	}
	@JsOverlay
	public Column<T> renderer(CreateElement createElement, Renderer<T> init) {
		return renderer(createElement, init, (e, p)->{});
	}
	@JsOverlay
	public Column<T> width(Integer width) {
		if(width == null) this.width = null;
		else this.width = width.doubleValue();
		return this;
	}
	@JsOverlay
	public Integer width() {
		if(width == null) return null;
		else return width.intValue();
	}
	@JsOverlay
	public Column<T> widthMin(Integer width) {
		if(width == null) this.widthMin = null;
		else this.widthMin = width.doubleValue();
		return this;
	}
	@JsOverlay
	public Integer widthMin() {
		if(widthMin == null) return null;
		else return widthMin.intValue();
	}
	@JsOverlay
	public Column<T> sortAsc(Boolean isAsc) {
		if(isAsc == null) sortingType = null;
		else if(isAsc) sortingType = "asc";
		else sortingType = "desc";
		return this;
	}
	@JsOverlay
	public Boolean isSortAsc() {
		if(sortingType == null) return null;
		return "asc".equals(sortingType);
	}

	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_= {@JsOverlay})
	@Accessors(fluent=true)
	public final static class ColumnOption {
		@JsProperty(name="minWidth")
		private Double minWidth;
		@JsProperty(name="frozenCount")
		private Double frozenCount;
		@JsProperty(name="frozenBorderWidth")
		private Double frozenBorderWidth;
		@JsOverlay
		public ColumnOption widthMin(Integer minWidth) {
			if(minWidth == null) this.minWidth = null;
			else this.minWidth = minWidth.doubleValue();
			return this;
		}
		@JsOverlay
		public Integer widthMin() {
			if(minWidth == null) return null;
			else return minWidth.intValue();
		}
		@JsOverlay
		public ColumnOption frozenCount(Integer frozenCount) {
			if(frozenCount == null) this.frozenCount = null;
			else this.frozenCount = frozenCount.doubleValue();
			return this;
		}
		@JsOverlay
		public Integer frozenCount() {
			if(frozenCount == null) return null;
			else return frozenCount.intValue();
		}
		@JsOverlay
		public ColumnOption frozenBorderWidth(Integer frozenBorderWidth) {
			if(frozenBorderWidth == null) this.frozenBorderWidth = null;
			else this.frozenBorderWidth = frozenBorderWidth.doubleValue();
			return this;
		}
		@JsOverlay
		public Integer frozenBorderWidth() {
			if(frozenBorderWidth == null) return null;
			else return frozenBorderWidth.intValue();
		}
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Getter(onMethod_= {@JsOverlay})
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
	interface GetRender<T> {
		Object construct(RendererProperty<T> props);
	}
	@JsFunction
	public interface CreateElement {
		Element getElement();
	}
	@JsFunction
	private interface InitializeProxy {
		void initialize(Element element);
	}
	public interface Renderer<T> {
		void render(Element element, RendererProperty<T> props);
	}
	@JsFunction
	private interface RenderProxy<T> {
		void render(RendererProperty<T> props);
	}
}

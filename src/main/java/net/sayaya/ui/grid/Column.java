package net.sayaya.ui.grid;

import elemental2.dom.Element;
import jsinterop.annotations.*;
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
	}
	@JsMethod
	public Boolean isSortAsc() {
		if(sortingType == null) return null;
		return "asc".equals(sortingType);
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

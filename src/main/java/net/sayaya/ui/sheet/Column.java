package net.sayaya.ui.sheet;

import jsinterop.annotations.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.sheet.function.AutoComplete;
import net.sayaya.ui.sheet.function.CellEditor;
import net.sayaya.ui.sheet.function.CellRenderer;
import net.sayaya.ui.sheet.function.HeaderRenderer;

import static org.jboss.elemento.Elements.label;

@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
@Setter(onMethod_={@JsOverlay, @JsIgnore})
@Getter(onMethod_={@JsOverlay, @JsIgnore})
@Accessors(fluent=true)
public final class Column {
	@JsOverlay
	@JsIgnore
	public static Column defaults() {
		Column instance = new Column();
		instance.headerRenderer(n->label().add(instance.header!=null?instance.header:instance.data).element());
		return instance;
	}
	private String data;
	private String header;
	private String type;
	@JsProperty(name="_width")
	private Integer width;
	private String format;
	private String dateFormat;
	private Object source;
	private boolean strict;
	private boolean readOnly;
	private CellRenderer renderer;
	//private Validator validator;
	private HeaderRenderer headerRenderer;
	private boolean allowInvalid;
	private boolean allowEmpty;
	private CellEditorFn editor;
	private boolean filter;
	@JsOverlay
	public Column source(String... source) {
		this.source = source;
		return this;
	}
	@JsOverlay
	public Column source(AutoComplete source) {
		this.source = source;
		return this;
	}
	@JsOverlay
	public Column renderer(CellRenderer renderer) {
		this.renderer = renderer;
		return this;
	}
	@JsOverlay
	public Column editor(CellEditorFn creator) {
		this.editor = creator;
		return this;
	}
	@JsFunction
	public interface CellEditorFn {
		CellEditor prototype(Object props);
	}
}

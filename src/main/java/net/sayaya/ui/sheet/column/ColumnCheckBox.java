package net.sayaya.ui.sheet.column;

import elemental2.core.JsRegExp;
import elemental2.core.RegExpResult;
import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPathElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.HTMLElementBuilder;
import net.sayaya.ui.event.HasValueChangeHandlers;
import net.sayaya.ui.sheet.Column;
import net.sayaya.ui.sheet.Data;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.InputType;

import java.util.function.Supplier;

import static org.jboss.elemento.Elements.*;

public final class ColumnCheckBox implements ColumnBuilder {
	private final static JsRegExp CHK_BOOLEAN = new JsRegExp("^true|false$");
	private static String normalize(String str) {
		if(str == null) return null;
		str = str.trim().toLowerCase();
		RegExpResult chkBool = CHK_BOOLEAN.exec(str);
		if(chkBool != null) return str;
		else return str;
	}
	private final String id;
	private final ColumnBuilderDefaultHelper<ColumnCheckBox> defaultHelper = new ColumnBuilderDefaultHelper<>(()->this);
	private final ColumnStyleDataChangeHelper<ColumnCheckBox> dataChangeHelper = new ColumnStyleDataChangeHelper<>(()->this);
	private final ColumnStyleColorHelper<ColumnCheckBox> colorHelper = new ColumnStyleColorHelper<>(()->this);
	private final ColumnStyleColorConditionalHelper<ColumnCheckBox> colorConditionalHelper = new ColumnStyleColorConditionalHelper<>(()->this);
	private final ColumnStyleAlignHelper<ColumnCheckBox> alignHelper = new ColumnStyleAlignHelper<>(()->this);
	ColumnCheckBox(String id) {
		this.id = id;
		alignHelper.align("center");
	}
	@Override
	public Column build() {
		Column column = defaultHelper.build().data(id).readOnly(true);
		return column.renderer((sheet, td, row, col, prop, value, ci)->{
			colorHelper.apply(td, row, prop, value);
			dataChangeHelper.apply(sheet, td, row, prop);
			colorConditionalHelper.apply(td, row, prop, value);
			alignHelper.apply(td, row, prop, value);
			Data data = sheet.spreadsheet.values()[row];
			value = normalize(value);
			CheckBox elem = CheckBox.checkBox(value!=null?Boolean.parseBoolean(value):false).style("transform: scale(0.5)");
			if(defaultHelper.readOnly()) elem.enabled(false);
			else elem.onValueChange(evt->{
				data.put(id, evt!=null?String.valueOf(evt.value()):"false");
				dataChangeHelper.apply(sheet, td, row, prop);
			});
			td.innerHTML = "";
			td.appendChild(elem.element());
			return td;
		}).headerRenderer(n->span().textContent(defaultHelper.name()).element());
	}

	public ColumnCheckBox name(String name) {return this.defaultHelper.name(name);}

	public ColumnCheckBox width(Integer width) {return this.defaultHelper.width(width);}

	public ColumnCheckBox width(Supplier<Integer> width) {return this.defaultHelper.width(width);}

	public ColumnCheckBox readOnly(Boolean readOnly) {return this.defaultHelper.readOnly(readOnly);}

	public ColumnCheckBox readOnly(Supplier<Boolean> readOnly) {return this.defaultHelper.readOnly(readOnly);}

	public ColumnCheckBox color(String color) {return this.colorHelper.color(color);}

	public ColumnCheckBox color(ColumnStyleFn<String> color) {return this.colorHelper.color(color);}

	public ColumnCheckBox colorBackground(String colorBackground) {return this.colorHelper.colorBackground(colorBackground);}

	public ColumnCheckBox colorBackground(ColumnStyleFn<String> colorBackground) {return this.colorHelper.colorBackground(colorBackground);}

	public ColumnCheckBox pattern(String pattern) {return this.colorConditionalHelper.pattern(pattern);}

	public ColumnCheckBox pattern(ColumnStyleFn<String> pattern) {return this.colorConditionalHelper.pattern(pattern);}

	public ColumnCheckBox colorConditional(String color) {return this.colorConditionalHelper.colorConditional(color);}

	public ColumnCheckBox colorConditional(ColumnStyleFn<String> color) {return this.colorConditionalHelper.colorConditional(color);}

	public ColumnCheckBox colorConditionalBackground(String colorBackground) {return this.colorConditionalHelper.colorConditionalBackground(colorBackground);}

	public ColumnCheckBox colorConditionalBackground(ColumnStyleFn<String> colorBackground) {return this.colorConditionalHelper.colorConditionalBackground(colorBackground);}

	public ColumnCheckBox align(String align) {return this.alignHelper.align(align);}

	public ColumnCheckBox align(ColumnStyleFn<String> align) {return this.alignHelper.align(align);}

	private final static class CheckBox extends HTMLElementBuilder<HTMLDivElement, CheckBox> implements HasValueChangeHandlers<Boolean> {
		public static CheckBox checkBox(boolean initialValue) {
			CheckBox elem = new CheckBox(div(), initialValue);
			elem._mdc = inject(elem.element());
			return elem;
		}
		private static native MCDCheckbox inject(Element elem) /*-{
            var mdc = $wnd.mdc.checkbox.MDCCheckbox.attachTo(elem.firstChild);
            $wnd.mdc.formField.MDCFormField.attachTo(elem);
            return mdc;
        }-*/;
		private final HTMLInputElement input = Elements.input(InputType.checkbox).css("mdc-checkbox__native-control").id().element();
		private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
		private final SVGPathElement path = (SVGPathElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "path");
		private final SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
		private final HtmlContentBuilder<HTMLDivElement> mixedmark = div().css("mdc-checkbox__mixedmark");
		private final HtmlContentBuilder<HTMLDivElement> background = div().css("mdc-checkbox__background").style("top: 0;left: 0;").add(svg).add(mixedmark);
		private final HtmlContentBuilder<HTMLDivElement> checkbox = div().css("mdc-checkbox").style("padding: 0px;").add(input).add(background)/*.add(ripple)*/;
		private final HtmlContentBuilder<HTMLLabelElement> label = label().attr("for", input.id);
		private final HtmlContentBuilder<HTMLDivElement> _this;
		private MCDCheckbox _mdc;
		private CheckBox(HtmlContentBuilder<HTMLDivElement> e, boolean initValue) {
			super(e);
			input.checked = initValue;
			_this = e;
			layout();
		}
		private void layout() {
			_this.css("mdc-form-field")
					.add(checkbox)
					.add(label);
			svg.setAttribute( "viewBox", "0 0 24 24");
			path.setAttribute("d", "M1.73,12.91 8.1,19.28 22.79,4.59");
			path.setAttribute("fill", "none");
			path.classList.add("mdc-checkbox__checkmark-path");
			svg.appendChild(path);
			svg.classList.add("mdc-checkbox__checkmark");
		}
		public CheckBox value(boolean value) {
			_mdc.checked = value;
			return that();
		}
		@Override
		public Boolean value() {
			return _mdc.checked;
		}
		public CheckBox text(String text) {
			label.textContent(text);
			return that();
		}
		public CheckBox enabled(boolean enabled) {
			_mdc.disabled = !enabled;
			return that();
		}
		@Override
		public HandlerRegistration onValueChange(ValueChangeEventListener<Boolean> listener) {
			return onValueChange(input, listener);
		}
		@Override
		public CheckBox that() {
			return this;
		}
		@JsType(isNative = true, namespace = "mdc.checkbox", name="MDCCheckbox")
		private final static class MCDCheckbox {
			@JsProperty
			private boolean checked;
			@JsProperty
			private boolean disabled;
		}
	}
}

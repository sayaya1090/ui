package net.sayaya.ui;

import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPathElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.InputType;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.label;

public class CheckBox extends HTMLElementBuilder<HTMLDivElement, CheckBox> implements HasValueChangeHandlers<Boolean> {
	public static CheckBox checkBox(boolean initialValue) {
		CheckBox elem = new CheckBox(div());
		elem._mdc = inject(elem.element());
		return elem.value(initialValue);
	}
	public static CheckBox intermediateCheckBox(boolean initialValue) {
		CheckBox elem = new CheckBox(div());
		elem._mdc = inject(elem.element());
		return elem.value(initialValue);
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
	private final HtmlContentBuilder<HTMLDivElement> background = div().css("mdc-checkbox__background").add(svg).add(mixedmark);
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-checkbox__ripple");
	private final HtmlContentBuilder<HTMLDivElement> checkbox = div().css("mdc-checkbox").add(input).add(background).add(ripple);
	private final HtmlContentBuilder<HTMLLabelElement> label = label().attr("for", input.id);
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private MCDCheckbox _mdc;
	private CheckBox(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
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
		private boolean indeterminate;
		@JsProperty
		private boolean disabled;
		@JsProperty
		private String value;
	}
}

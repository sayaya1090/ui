package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLLabelElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasValueChangeHandlers;
import net.sayaya.ui.mdc.MDCFormField;
import net.sayaya.ui.svg.SvgBuilder;
import net.sayaya.ui.svg.SvgPathBuilder;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.InputType;

import static net.sayaya.ui.svg.SvgBuilder.svg;
import static net.sayaya.ui.svg.SvgPathBuilder.path;
import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.label;

public class CheckBoxElement extends HTMLElementBuilder<HTMLDivElement, CheckBoxElement> implements HasValueChangeHandlers<Boolean> {
	public static CheckBoxElement checkBox(boolean initialValue) {
		return new CheckBoxElement(div(), initialValue);
	}
	public static CheckBoxElement intermediateCheckBox(boolean initialValue) {
		return new CheckBoxElement(div(), initialValue);
	}
	private final HTMLInputElement input = Elements.input(InputType.checkbox).css("mdc-checkbox__native-control").id().element();
	private final SvgPathBuilder path = path();
	private final SvgBuilder svg = svg();
	private final HtmlContentBuilder<HTMLDivElement> mixedmark = div();
	private final HtmlContentBuilder<HTMLDivElement> background = div();
	private final HtmlContentBuilder<HTMLDivElement> ripple = div();
	private final HtmlContentBuilder<HTMLDivElement> checkbox = div();
	private final HtmlContentBuilder<HTMLLabelElement> label = label().attr("for", input.id);
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private final MDCCheckbox _mdc;
	private CheckBoxElement(HtmlContentBuilder<HTMLDivElement> e, boolean initValue) {
		super(e);
		input.checked = initValue;
		_this = e;
		_this.css("mdc-form-field")
				.add(checkbox.css("mdc-checkbox")
						.add(input)
						.add(background.css("mdc-checkbox__background")
								.add(svg.css("mdc-checkbox__checkmark").viewBox(0, 0, 24, 24)
										.add(path.css("mdc-checkbox__checkmark-path")
												.d("M1.73,12.91 8.1,19.28 22.79,4.59")
												.fill("none")).element())
								.add(mixedmark.css("mdc-checkbox__mixedmark")))
						.add(ripple.css("mdc-checkbox__ripple")))
				.add(label);
		_mdc = new MDCCheckbox(checkbox.element());
		new MDCFormField(element());
	}
	public CheckBoxElement value(boolean value) {
		_mdc.checked = value;
		return that();
	}
	@Override
	public Boolean value() {
		return _mdc.checked;
	}
	public CheckBoxElement text(String text) {
		label.textContent(text);
		return that();
	}
	public CheckBoxElement enabled(boolean enabled) {
		_mdc.disabled = !enabled;
		return that();
	}
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Boolean> listener) {
		return onValueChange(input, listener);
	}
	@Override
	public CheckBoxElement that() {
		return this;
	}
	@JsType(isNative = true, namespace = "mdc.checkbox")
	private final static class MDCCheckbox {
		@JsProperty public boolean checked;
		@JsProperty public boolean indeterminate;
		@JsProperty public boolean disabled;
		@JsProperty public String value;
		public MDCCheckbox(Element elem){}
	}
}

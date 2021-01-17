package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasSelectionChangeHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.InputBuilder;
import org.jboss.elemento.InputType;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.input;

public class Radio<V> extends HTMLElementBuilder<HTMLDivElement, Radio<V>> implements Focusable<Radio<V>>, HasValueChangeHandlers<V>, HasSelectionChangeHandlers<V> {
	public static <V> Radio<V> radio(String group, V value) {
		Radio<V> elem = new Radio<>(div(), group, value);
		elem.css("mdc-radio");
		elem._mdc = inject(elem.element());
		return elem;
	}
	private native static MCDRadio inject(Element elem) /*-{
		return $wnd.mdc.radio.MDCRadio.attachTo(elem);
	}-*/;
	private final InputBuilder<HTMLInputElement> input = input(InputType.radio).css("mdc-radio__native-control");
	private final HtmlContentBuilder<HTMLDivElement> background = div().css("mdc-radio__background")
																	   .add(div().css("mdc-radio__outer-circle").style("box-sizing: border-box;"))
																	   .add(div().css("mdc-radio__inner-circle").style("box-sizing: border-box;"));
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-radio__ripple");
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private final V value;
	private MCDRadio _mdc;
	private Radio(HtmlContentBuilder<HTMLDivElement> e, String group, V value) {
		super(e);
		_this = e;
		this.value = value;
		input.attr("name", group);
		layout();
	}
	private void layout() {
		clear();
		_this.add(input).add(background).add(ripple);
	}
	@Override
	public Radio<V> accessKey(char key) {
		return null;
	}

	@Override
	public Radio<V> focus() {
		return null;
	}

	@Override
	public V value() {
		if("on".equals(input.element().value)) return value;
		return null;
	}

	@Override
	public HandlerRegistration onValueChange(HasValueChangeHandlers.ValueChangeEventListener<V> listener) {
		return onValueChange(input.element(), listener);
	}
	@Override
	public Radio<V> that() {
		return this;
	}

	@Override
	public V selection() {
		return value();
	}
	public Radio<V> enabled(boolean enabled) {
		_mdc.disabled = !enabled;
		return that();
	}
	@Override
	public HandlerRegistration onSelectionChange(SelectionChangeEventListener<V> listener) {
		return this.onSelectionChange(input.element(), listener);
	}
	@JsType(isNative = true, namespace = "mdc.radio", name="MDCRadio")
	private final static class MCDRadio {
		@JsProperty
		private boolean checked;
		@JsProperty
		private boolean disabled;
		@JsProperty
		private String value;
	}
}

package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLInputElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasSelectionChangeHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.InputElementBuilder;
import org.jboss.elemento.InputType;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.input;

public class RadioElement<V> extends HTMLElementBuilder<HTMLDivElement, RadioElement<V>> implements Focusable<RadioElement<V>>, HasValueChangeHandlers<V>, HasSelectionChangeHandlers<V> {
	public static <V> RadioElement<V> radio(String group, V value) {
		return new RadioElement<>(div(), group, value);
	}
	private final InputElementBuilder<HTMLInputElement> input = input(InputType.radio);
	private final V value;
	private final MDCRadio _mdc;
	private RadioElement(HTMLContainerBuilder<HTMLDivElement> e, String group, V value) {
		super(e);
		e.css("mdc-radio")
		 .add(input.attr("name", group).css("mdc-radio__native-control"))
		 .add(div().css("mdc-radio__background")
				   .add(div().css("mdc-radio__outer-circle").style("box-sizing: border-box;"))
				   .add(div().css("mdc-radio__inner-circle").style("box-sizing: border-box;")))
		 .add(div().css("mdc-radio__ripple"));
		this.value = value;
		_mdc = new MDCRadio(element());
	}
	@Override
	public RadioElement<V> accessKey(char key) {
		return null;
	}
	@Override
	public RadioElement<V> focus() {
		return null;
	}
	@Override
	public V value() {
		return value;
	}
	public boolean isSelected() {
		return _mdc.checked;
	}
	public RadioElement<V> select(boolean value) {
		_mdc.checked = value;
		return that();
	}
	@Override
	public HandlerRegistration onValueChange(HasValueChangeHandlers.ValueChangeEventListener<V> listener) {
		return onValueChange(input.element(), listener);
	}
	@Override
	public RadioElement<V> that() {
		return this;
	}
	@Override
	public V selection() {
		return value();
	}
	public RadioElement<V> enabled(boolean enabled) {
		_mdc.disabled = !enabled;
		return that();
	}
	@Override
	public HandlerRegistration onSelectionChange(SelectionChangeEventListener<V> listener) {
		return this.onSelectionChange(input.element(), listener);
	}
	@JsType(isNative = true, namespace = "mdc.radio", name="MDCRadio")
	private final static class MDCRadio {
		@JsProperty private boolean checked;
		@JsProperty private boolean disabled;
		@JsProperty private String value;
		public MDCRadio(Element element){}
	}
}

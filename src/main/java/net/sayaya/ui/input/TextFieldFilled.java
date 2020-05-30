package net.sayaya.ui.input;

import elemental2.dom.*;

import static org.jboss.elemento.Elements.*;

public abstract class TextFieldFilled<V> extends TextField<V, TextFieldFilled<V>> {
	TextFieldFilled(HTMLInputElement input) {
		super(input);
	}
	protected final HTMLLabelElement initialize() {
		return org.jboss.elemento.Elements.label().css("mdc-text-field", "mdc-text-field--filled")
				.add(span().css("mdc-text-field__input"))
				.add(input())
				.add(label())
				.add(div().css("mdc-line-ripple"))
				.element();
	}
}

package net.sayaya.ui.input;

import elemental2.dom.*;

import static org.jboss.gwt.elemento.core.Elements.div;

public abstract class TextFieldFilled<V> extends TextField<V, TextFieldFilled<V>> {
	TextFieldFilled(HTMLInputElement input) {
		super(input);
	}
	protected final HTMLDivElement initialize() {
		return div().css("mdc-text-field")
					.add(input)
					.add(div().css("mdc-line-ripple"))
					.add(label)
					.element();
	}
}

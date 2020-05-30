package net.sayaya.ui.input;

import elemental2.dom.*;

import static org.jboss.elemento.Elements.*;

public abstract class TextFieldOutlined<V> extends TextField<V, TextFieldOutlined<V>> {
	TextFieldOutlined(HTMLInputElement input) {
		super(input);
	}

	@Override
	protected final HTMLLabelElement initialize() {
		return org.jboss.elemento.Elements.label().css("mdc-text-field", "mdc-text-field--outlined")
				.add(input())
				.add(span().css("mdc-notched-outline")
						.add(span().css("mdc-notched-outline__leading"))
						.add(span().css("mdc-notched-outline__notch")
								.add(label()))
						.add(span().css("mdc-notched-outline__trailing")))
				.element();
	}
}

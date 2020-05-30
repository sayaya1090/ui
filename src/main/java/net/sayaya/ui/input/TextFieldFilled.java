package net.sayaya.ui.input;

import elemental2.dom.*;
import org.jboss.elemento.InputBuilder;

import static org.jboss.elemento.Elements.*;

public abstract class TextFieldFilled<V> extends TextField<V, TextFieldFilled<V>> {
	TextFieldFilled(InputBuilder<HTMLInputElement> builder) {
		super(builder);

	}
	protected final HTMLLabelElement initialize() {
		input().validity = new ValidityState();
		return org.jboss.elemento.Elements.label().css("mdc-text-field", "mdc-text-field--filled")
										  .add(span().css("mdc-text-field__input"))
										  .add(input())
										  .add(label())
										  .add(div().css("mdc-line-ripple"))
										  .element();
	}
}

package net.sayaya.ui.input;

import elemental2.dom.*;
import org.jboss.elemento.InputBuilder;

import static org.jboss.elemento.Elements.*;

public abstract class TextFieldOutlined<V> extends TextField<V, TextFieldOutlined<V>> {
	TextFieldOutlined(InputBuilder<HTMLInputElement> builder) {
		super(builder);
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

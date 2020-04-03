package net.sayaya.ui.input;

import org.jboss.gwt.elemento.core.InputType;

public class TextBox extends TextBoxBase<String, TextBox> {
	TextBox() {
		super(InputType.text);
	}
	@Override
	public String value() {
		return element().value;
	}
	public TextBox value(String value) {
		element().value = value;
		return this;
	}
}

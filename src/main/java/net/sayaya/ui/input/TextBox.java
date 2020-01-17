package net.sayaya.ui.input;

import org.jboss.gwt.elemento.core.InputType;

public class TextBox extends TextBoxBase<String, TextBox> {
	public TextBox() {
		super(InputType.text);
	}

	@Override
	public String getValue() {
		return element().value;
	}
}

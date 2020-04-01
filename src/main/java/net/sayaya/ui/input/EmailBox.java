package net.sayaya.ui.input;

import org.jboss.gwt.elemento.core.InputType;

public class EmailBox extends TextBoxBase<String, EmailBox> {
	EmailBox() {
		super(InputType.email);
	}
	@Override
	public String value() {
		return element().value;
	}
}

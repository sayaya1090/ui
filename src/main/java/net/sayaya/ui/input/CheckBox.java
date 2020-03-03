package net.sayaya.ui.input;

import org.jboss.gwt.elemento.core.InputType;
import static org.jboss.gwt.elemento.core.Elements.input;

public class CheckBox extends InputBase<Boolean, CheckBox> {
	CheckBox() {
		super(input(InputType.checkbox).element());
	}
	@Override
	public Boolean getValue() {
		return element().checked;
	}
}

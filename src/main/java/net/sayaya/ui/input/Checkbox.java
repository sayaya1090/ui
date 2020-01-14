package net.sayaya.ui.input;

import org.jboss.gwt.elemento.core.InputType;
import static org.jboss.gwt.elemento.core.Elements.input;

public class Checkbox extends InputBase<Checkbox> {
	public Checkbox() {
		super(input(InputType.checkbox).element());
	}
}

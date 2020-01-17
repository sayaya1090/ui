package net.sayaya.ui.input;

import net.sayaya.ui.InputBase;
import org.jboss.gwt.elemento.core.InputType;
import static org.jboss.gwt.elemento.core.Elements.input;

public class Checkbox extends InputBase<Boolean, Checkbox> {
	public Checkbox() {
		super(input(InputType.checkbox).element());
	}

	@Override
	public Boolean getValue() {
		return element().checked;
	}
}

package net.sayaya.ui.input;

import org.jboss.gwt.elemento.core.InputType;

public class NumberBox extends TextBoxBase<Double, NumberBox> {
	NumberBox() {
		super(InputType.number);
	}
	@Override
	public Double value() {
		return element().valueAsNumber;
	}
	public NumberBox value(Double value) {
		element().valueAsNumber = value;
		return this;
	}
	public NumberBox min(Double min) {
		element().setAttribute("min", min);
		return this;
	}
	public NumberBox max(Double max) {
		element().setAttribute("max", max);
		return this;
	}
}

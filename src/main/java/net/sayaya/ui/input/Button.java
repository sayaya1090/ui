package net.sayaya.ui.input;

import org.jboss.gwt.elemento.core.InputType;

import static org.jboss.gwt.elemento.core.Elements.input;

public abstract class Button<T extends Button<T>> extends InputBase<T> {
	protected Button() {
		super(input(InputType.button).element());
	}
}

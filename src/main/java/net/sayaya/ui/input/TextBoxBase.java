package net.sayaya.ui.input;

import net.sayaya.ui.InputBase;
import org.jboss.gwt.elemento.core.InputType;

import static org.jboss.gwt.elemento.core.Elements.input;

public abstract class TextBoxBase<V, W extends InputBase<V, W>> extends InputBase<V, W> {
	protected TextBoxBase(InputType type) {
		super(input(type).element());
	}
}

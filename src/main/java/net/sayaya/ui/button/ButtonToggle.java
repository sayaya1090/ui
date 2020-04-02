package net.sayaya.ui.button;

import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasValueChangeHandlers;

public class ButtonToggle extends Button<ButtonImpl> implements HasValueChangeHandlers<Boolean> {
	private boolean push = false;
	protected ButtonToggle(String... classes) {
		super(classes);
	}
	@Override
	public Boolean value() {
		return push;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeEventListener<Boolean> listener) {
		return addValueChangeHandler(this.element(), listener);
	}
}

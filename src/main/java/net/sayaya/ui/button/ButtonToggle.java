package net.sayaya.ui.button;

import net.sayaya.ui.ButtonBase;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasValueChangeHandlers;

public class ButtonToggle extends ButtonBase<ButtonImpl> implements HasValueChangeHandlers<Boolean> {
	private boolean push = false;
	@Override
	public Boolean getValue() {
		return push;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeEventListener<Boolean> listener) {
		return addValueChangeHandler(this.element(), listener);
	}
}

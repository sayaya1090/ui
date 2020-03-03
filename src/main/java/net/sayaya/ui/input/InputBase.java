package net.sayaya.ui.input;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLInputElement;
import net.sayaya.ui.event.HandlerRegistration;

public abstract class InputBase<V, W extends Input<V, W>> implements Input<V, W> {
	private final HTMLInputElement _this;
	protected InputBase(HTMLInputElement root) {
		_this = root;
	}
	@Override
	public final HTMLInputElement element() {
		return _this;
	}
	@Override
	public final HandlerRegistration addValueChangeHandler(ValueChangeEventListener<V> listener) {
		return addValueChangeHandler(_this, listener);
	}
	@Override
	public final HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(_this, listener);
	}
	public final W setEnabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return self();
	}

	@Override
	public W setAccessKey(char key) {
		_this.setAttribute("accessKey", String.valueOf(key));
		return self();
	}

	@Override
	public W setFocus() {
		_this.focus();
		return self();
	}
}

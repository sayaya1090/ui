package net.sayaya.ui.input;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.handler.Focusable;
import net.sayaya.ui.style.Style;
import org.jboss.gwt.elemento.core.IsElement;

public class InputBase<T extends InputBase<T>> implements IsElement<HTMLElement>, Focusable<T>, HasClickHandlers {
	private final HTMLElement _this;
	protected InputBase(HTMLElement root) {
		_this = root;
	}
	@Override
	public final HTMLElement element() {
		return _this;
	}
	public final T setStyle(Style style) {
		style.apply(this._this.style);
		return self();
	}
	@Override
	public final HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(_this, listener);
	}
	public final T setEnabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return self();
	}
	@Override
	public final int getTabIndex() {
		return _this.tabIndex;
	}

	@Override
	public T setAccessKey(char key) {
		_this.setAttribute("accessKey", String.valueOf(key));
		return self();
	}

	@Override
	public T setFocus() {
		_this.focus();
		return self();
	}

	@Override
	public final T setTabIndex(int index) {
		_this.tabIndex = index;
		return self();
	}
}

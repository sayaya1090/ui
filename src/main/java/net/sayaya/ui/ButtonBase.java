package net.sayaya.ui;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;

import static org.jboss.gwt.elemento.core.Elements.button;

public abstract class ButtonBase<W extends ButtonBase<W>> implements IsHTMLElement<HTMLButtonElement, W>, Focusable<W>, HasClickHandlers {
	private final HTMLButtonElement _this;
	protected ButtonBase() {
		_this = button().element();
	}
	@Override
	public final HTMLButtonElement element() {
		return _this;
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

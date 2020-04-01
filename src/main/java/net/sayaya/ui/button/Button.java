package net.sayaya.ui.button;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;

import static org.jboss.gwt.elemento.core.Elements.button;

public abstract class Button<W extends Button<W>> implements IsHTMLElement<HTMLButtonElement, W>, Focusable<W>, HasClickHandlers {
	private final HTMLButtonElement _this;
	protected Button() {
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
	public final W enabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return self();
	}
	@Override
	public W accessKey(char key) {
		_this.setAttribute("accessKey", String.valueOf(key));
		return self();
	}

	@Override
	public W focus() {
		_this.focus();
		return self();
	}
}

package net.sayaya.ui.button;

import elemental2.dom.*;
import net.sayaya.ui.Focusable;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;

import static org.jboss.gwt.elemento.core.Elements.*;

public class Button implements IsHTMLElement<HTMLButtonElement, Button>, Focusable<Button>, HasClickHandlers {
	private final HTMLDivElement ripple = div().css("mdc-button__ripple").element();
	private final HTMLElement label = span().css("mdc-button__label").element();
	private final HTMLButtonElement _this = button().css("mdc-button")
													.add(ripple)
													.add(label)
													.element();
	Button() {inject(_this);}
	private native void inject(Element elem) /*-{
		$wnd.mdc.ripple.MDCRipple.attachTo(elem);
	}-*/;
	@Override
	public final HTMLButtonElement element() {
		return _this;
	}
	@Override
	public final HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(_this, listener);
	}
	public final Button enabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return self();
	}
	@Override
	public Button accessKey(char key) {
		_this.setAttribute("accessKey", String.valueOf(key));
		return self();
	}
	@Override
	public Button focus() {
		_this.focus();
		return self();
	}
	public Button icon(HTMLElement icon) {
		while(ripple.nextSibling!=label) _this.removeChild(ripple.nextSibling);
		_this.insertBefore(icon, label);
		return this;
	}
	public Button text(String text) {
		label.innerHTML = text;
		return self();
	}
}

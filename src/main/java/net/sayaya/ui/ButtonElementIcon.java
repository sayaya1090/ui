package net.sayaya.ui;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import net.sayaya.ui.mdc.MDCRipple;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HTMLContainerBuilder;

import static org.jboss.elemento.Elements.div;

public class ButtonElementIcon extends HTMLElementBuilder<HTMLButtonElement, ButtonElementIcon> implements ButtonElement {
	private final HTMLContainerBuilder<HTMLButtonElement> _this;
	ButtonElementIcon(HTMLContainerBuilder<HTMLButtonElement> e, String icon) {
		super(e);
		_this = e.css("mdc-icon-button", "material-icons")
				.add(div().css("mdc-icon-button__ripple"))
				.add(icon);
		new MDCRipple(element()).unbounded = true;
	}
	@Override
	public final ButtonElementIcon enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonElementIcon text(String text) {
		_this.textContent(text);
		return that();
	}
	public String text() {
		return _this.element().innerHTML;
	}
	@Override
	public ButtonElementIcon that() {
		return this;
	}

	@Override
	public ButtonElementIcon accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonElementIcon focus() {
		element().focus();
		return that();
	}
	@Override
	public HandlerRegistration onClick(EventListener listener) {
		return onClick(_this.element(), listener);
	}
}

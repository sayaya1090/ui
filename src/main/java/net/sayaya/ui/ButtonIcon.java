package net.sayaya.ui;

import elemental2.dom.*;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class ButtonIcon extends HTMLElementBuilder<HTMLButtonElement, ButtonIcon> implements Button {
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	ButtonIcon(HtmlContentBuilder<HTMLButtonElement> e, String icon) {
		super(e);
		_this = e.css("mdc-icon-button", "material-icons").add(icon);
	}
	@Override
	public final ButtonIcon enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonIcon text(String text) {
		_this.textContent(text);
		return that();
	}
	public String text() {
		return _this.element().innerHTML;
	}
	@Override
	public ButtonIcon that() {
		return this;
	}

	@Override
	public ButtonIcon accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonIcon focus() {
		element().focus();
		return that();
	}
	@Override
	public HandlerRegistration onClick(EventListener listener) {
		return onClick(_this.element(), listener);
	}
}

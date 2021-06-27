package net.sayaya.ui;

import elemental2.dom.HTMLButtonElement;
import net.sayaya.ui.event.HasClickHandlers;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.button;
import static org.jboss.elemento.EventType.bind;

public interface ButtonElement extends IsElement<HTMLButtonElement>, HasClickHandlers, Focusable<ButtonElement> {
	static ButtonElementText flat() {
		ButtonElementText elem = new ButtonElementText(button());
		elem.css("mdc-button");
		bind(elem, "DOMNodeInserted", evt-> ButtonElementText.inject(elem.element()));
		return elem;
	}
	static ButtonElementText outline() {
		ButtonElementText elem = new ButtonElementText(button());
		elem.css("mdc-button", "mdc-button--outlined");
		ButtonElementText.inject(elem.element());
		return elem;
	}
	static ButtonElementText contain() {
		ButtonElementText elem = new ButtonElementText(button());
		elem.css("mdc-button", "mdc-button--unelevated");
		ButtonElementText.inject(elem.element());
		return elem;
	}
	static ButtonElementFloating floating(IconElement iconElement) {
		ButtonElementFloating elem = new ButtonElementFloating(button(), iconElement);
		elem.css("mdc-fab");
		bind(elem, "DOMNodeInserted", evt-> ButtonElementText.inject(elem.element()));
		return elem;
	}
	static ButtonElementIcon icon(String icon) {
		ButtonElementIcon elem = new ButtonElementIcon(button(), icon);
		return elem;
	}
	static ButtonElementToggle toggle() {
		ButtonElementToggle elem = new ButtonElementToggle(button());
		elem.css("mdc-button", "mdc-button--outlined", "mdc-button-toggle");
		ButtonElementToggle.inject(elem.element());
		return elem;
	}
	ButtonElement enabled(boolean enabled);
	ButtonElement text(String text);
	String text();
}

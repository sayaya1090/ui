package net.sayaya.ui;

import elemental2.dom.HTMLButtonElement;
import net.sayaya.ui.event.HasClickHandlers;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.button;
import static org.jboss.elemento.EventType.bind;

public interface Button extends IsElement<HTMLButtonElement>, HasClickHandlers, Focusable<Button> {
	static ButtonText flat() {
		ButtonText elem = new ButtonText(button());
		elem.css("mdc-button");
		bind(elem, "DOMNodeInserted", evt->ButtonText.inject(elem.element()));
		return elem;
	}
	static ButtonText outline() {
		ButtonText elem = new ButtonText(button());
		elem.css("mdc-button", "mdc-button--outlined");
		ButtonText.inject(elem.element());
		return elem;
	}
	static ButtonText contain() {
		ButtonText elem = new ButtonText(button());
		elem.css("mdc-button", "mdc-button--unelevated");
		ButtonText.inject(elem.element());
		return elem;
	}
	static ButtonFloating floating(Icon icon) {
		ButtonFloating elem = new ButtonFloating(button(), icon);
		elem.css("mdc-fab");
		bind(elem, "DOMNodeInserted", evt->ButtonText.inject(elem.element()));
		return elem;
	}
	static ButtonIcon icon(String icon) {
		ButtonIcon elem = new ButtonIcon(button(), icon);
		return elem;
	}
	Button enabled(boolean enabled);
	Button text(String text);
	String text();
}

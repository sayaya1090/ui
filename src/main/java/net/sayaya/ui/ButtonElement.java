package net.sayaya.ui;

import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import net.sayaya.ui.event.HasClickHandlers;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.*;

public interface ButtonElement extends IsElement<HTMLButtonElement>, HasClickHandlers, Focusable<ButtonElement> {
	static ButtonElementText flat() {
		return new ButtonElementText(button());
	}
	static ButtonElementText outline() {
		return new ButtonElementText(button()).css("mdc-button--outlined");
	}
	static ButtonElementTextContained contain() {
		return new ButtonElementTextContained(button()).unelevate();
	}
	static ButtonElementFloating floating(IconElement iconElement) {
		return new ButtonElementFloating(button(), iconElement);
	}
	static ButtonElementIcon icon(String icon) {
		var elem = new ButtonElementIcon(button(), icon);
		return elem;
	}
	static ButtonElementToggle toggle() {
		return new ButtonElementToggle(button());
	}
	default HtmlContentBuilder<HTMLDivElement> touchable() {
		this.element().classList.add("mdc-button--touch");
		element().appendChild(span().css("mdc-button__touch").element());
		return div().css("mdc-touch-target-wrapper").add(this);
	}
	ButtonElement enabled(boolean enabled);
	ButtonElement text(String text);
	String text();
}

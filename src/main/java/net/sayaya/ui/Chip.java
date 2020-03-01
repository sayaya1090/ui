package net.sayaya.ui;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasRemoveHandlers;
import net.sayaya.ui.style.Style;

import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.span;

public class Chip implements IsHTMLElement<HTMLElement, Chip>, HasRemoveHandlers {
	private HTMLElement thumbnails;
	private HTMLButtonElement remove;
	private final HTMLElement container = span().element();
	private final Style style = new Style();
	public Chip(String text) {
		container.innerHTML = text;
		remove = button("X").element();
		container.appendChild(remove);
		remove.addEventListener("click", evt->container.remove());
		setStyle(style.setColor("rgb(35, 47, 52)").setBackgroundColor("rgba(35, 47, 52, 0.12)"));
	}
	@Override
	public HTMLElement element() {
		return container;
	}

	@Override
	public HandlerRegistration addRemoveHandler(EventListener listener) {
		return addRemoveHandler(container, listener);
	}
}

package net.sayaya.ui;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import net.sayaya.ui.style.Style;
import org.jboss.elemento.IsElement;

public interface IsHTMLElement<E extends HTMLElement, W extends FluentInterface<W>> extends IsElement<E>, FluentInterface<W>, HasAttachHandlers, HasDetachHandlers {
	default W style(Style style) {
		style.apply(element().style);
		return self();
	}
	default W addClass(String clazz1, String... clazz) {
		element().classList.add(clazz1);
		if(clazz!=null) for(String c: clazz) element().classList.add(c);
		return self();
	}
	default HandlerRegistration addAttachHandler(EventListener listener) {
		return addAttachHandler(element(), listener);
	}
	default HandlerRegistration addDetachHandler(EventListener listener) {
		return addDetachHandler(element(), listener);
	}
}

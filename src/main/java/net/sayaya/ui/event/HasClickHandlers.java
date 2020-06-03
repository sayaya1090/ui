package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

import org.gwtproject.event.shared.HandlerRegistration;

import static org.jboss.elemento.EventType.bind;

@FunctionalInterface
public interface HasClickHandlers {
	HandlerRegistration onClick(EventListener listener);
	default HandlerRegistration onClick(EventTarget dom, EventListener listener) {
		return bind(dom, "click", listener);
	}
}
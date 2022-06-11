package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import static org.jboss.elemento.EventType.bind;

@FunctionalInterface
public interface HasDetachHandlers {
	HandlerRegistration onDetach(EventListener listener);
	default HandlerRegistration onDetach(EventTarget dom, EventListener listener) {
		return bind(dom, "DOMNodeRemoved", listener);
	}
}

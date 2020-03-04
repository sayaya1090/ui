package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

@FunctionalInterface
public interface HasDetachHandlers extends HasHandlers {
	HandlerRegistration addDetachHandler(EventListener listener);
	default HandlerRegistration addDetachHandler(EventTarget dom, EventListener listener) {
		return addHandler("DOMNodeRemoved", dom, listener);
	}
}

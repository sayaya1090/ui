package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

@FunctionalInterface
public interface HasRemoveHandlers extends HasHandlers {
	HandlerRegistration addRemoveHandler(EventListener listener);
	default HandlerRegistration addRemoveHandler(EventTarget dom, EventListener listener) {
		return addHandler("DOMNodeRemoved", dom, listener);
	}
}

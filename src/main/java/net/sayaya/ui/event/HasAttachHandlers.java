package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

@FunctionalInterface
public interface HasAttachHandlers extends HasHandlers {
	HandlerRegistration addAttachHandler(EventListener listener);
	default HandlerRegistration addAttachHandler(EventTarget dom, EventListener listener) {
		return addHandler("DOMNodeInserted", dom, listener);
	}
}

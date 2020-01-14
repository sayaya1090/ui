package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

@FunctionalInterface
public interface HasClickHandlers extends HasHandlers {
	HandlerRegistration addClickHandler(EventListener listener);
	default HandlerRegistration addClickHandler(EventTarget dom, EventListener listener) {
		return addHandler("click", dom, listener);
	}
}
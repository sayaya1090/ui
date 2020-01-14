package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

public interface HasHandlers {
	default HandlerRegistration addHandler(String event, EventTarget dom, EventListener listener) {
		dom.addEventListener(event, listener);
		return ()->dom.removeEventListener(event, listener);
	}
}

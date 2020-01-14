package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

public interface HasClickHandlers {
	void addClickHandler(EventListener listener);
	void removeClickHandler(EventListener listener);
	default void addClickHandler(EventTarget target, EventListener listener) {
		target.addEventListener("click", listener);
	}
	default void removeClickHandler(EventTarget target, EventListener listener) {
		target.removeEventListener("click", listener);
	}
}

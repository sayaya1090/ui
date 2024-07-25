package net.sayaya.ui.event;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import net.sayaya.ui.util.OnAttachEvent;
import org.gwtproject.event.shared.HandlerRegistration;

import static org.jboss.elemento.EventType.bind;

@FunctionalInterface
public interface HasAttachHandlers {
	HandlerRegistration onAttach(EventListener listener);
	default HandlerRegistration onAttach(EventTarget dom, EventListener listener) {
		var obs = OnAttachEvent.observe(dom, listener);
		return obs::disconnect;
	}
}

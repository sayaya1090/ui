package net.sayaya.ui.event;

import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;

public interface HasValueChangeHandlers<V> extends HasHandlers {
	V value();
	interface ValueChangeEventListener<V> {
		void handleEvent(ValueChangeEvent<V> evt);
	}
	final class ValueChangeEvent<V> {
		private Event event;
		private V value;
		public Event event() {
			return event;
		}
		public ValueChangeEvent<V> event(Event evt) {
			this.event = evt;
			return this;
		}
		public V value() {
			return value;
		}
		public ValueChangeEvent<V> value(V value) {
			this.value = value;
			return this;
		}
	}
	HandlerRegistration addValueChangeHandler(ValueChangeEventListener<V> listener);
	default HandlerRegistration addValueChangeHandler(EventTarget dom, ValueChangeEventListener<V> listener) {
		EventListener wrapper = evt->listener.handleEvent(new ValueChangeEvent<V>().event(evt).value(value()));
		return addHandler("change", dom, wrapper);
	}
}

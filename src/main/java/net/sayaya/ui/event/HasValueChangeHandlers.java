package net.sayaya.ui.event;

import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import lombok.Data;
import lombok.experimental.Accessors;

public interface HasValueChangeHandlers<V> extends HasHandlers {
	V getValue();
	interface ValueChangeEventListener<V> {
		void handleEvent(ValueChangeEvent<V> evt);
	}
	final class ValueChangeEvent<V> {
		private Event event;
		private V value;
		public Event getEvent() {
			return event;
		}
		public ValueChangeEvent<V> setEvent(Event evt) {
			this.event = evt;
			return this;
		}
		public V getValue() {
			return value;
		}
		public ValueChangeEvent<V> setValue(V value) {
			this.value = value;
			return this;
		}
	}
	HandlerRegistration addValueChangeHandler(ValueChangeEventListener<V> listener);
	default HandlerRegistration addValueChangeHandler(EventTarget dom, ValueChangeEventListener<V> listener) {
		EventListener wrapper = evt->listener.handleEvent(new ValueChangeEvent<V>().setEvent(evt).setValue(getValue()));
		return addHandler("change", dom, wrapper);
	}
}

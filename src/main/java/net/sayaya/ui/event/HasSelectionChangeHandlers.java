package net.sayaya.ui.event;

import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import org.gwtproject.event.shared.HandlerRegistration;

import static org.jboss.elemento.EventType.bind;

public interface HasSelectionChangeHandlers<T> {
    T selection();
    interface SelectionChangeEventListener<V> {
        void handle(SelectionChangeEvent<V> evt);
    }
    final class SelectionChangeEvent<T> {
        private Event event;
        private T selection;
        public static <T> SelectionChangeEvent<T> event(Event event, T value) {
            SelectionChangeEvent<T> evt = new SelectionChangeEvent<>();
            evt.event = event;
            evt.selection = value;
            return evt;
        }
        private SelectionChangeEvent(){}
        public Event event() {
            return event;
        }
        public SelectionChangeEvent<T> event(Event evt) {
            this.event = evt;
            return this;
        }
        public T selection() {
            return selection;
        }
        public SelectionChangeEvent<T> selection(T value) {
            this.selection = value;
            return this;
        }
    }
    HandlerRegistration onSelectionChange(SelectionChangeEventListener<T> listener);
    default HandlerRegistration onSelectionChange(EventTarget dom, SelectionChangeEventListener<T> listener) {
        EventListener wrapper = evt->listener.handle(new SelectionChangeEvent<T>().event(evt).selection(selection()));
        return bind(dom, "change", wrapper);
    }
}

package net.sayaya.ui.event;

import elemental2.dom.CustomEvent;
import elemental2.dom.Event;
import org.gwtproject.event.shared.HandlerRegistration;

import java.util.Collection;

public interface HasStateChangeHandlers<State extends Enum> {
    interface StateChangeEventListener<State extends Enum> {
        void handle(StateChangeEvent<State> evt);
    }
    final class StateChangeEvent<State extends Enum> {
        private Event event;
        private State selection;
        private static <State extends Enum> StateChangeEvent event(State value) {
            StateChangeEvent<State> evt = new StateChangeEvent<>();
            evt.event = new CustomEvent("state");
            evt.selection = value;
            return evt;
        }
        private StateChangeEvent(){}
        public Event event() {
            return event;
        }
        public State state() {
            return selection;
        }
    }
    Collection<StateChangeEventListener<State>> listeners();
    State state();
    default void fireStateChangeEvent() {
        StateChangeEvent<State> evt = StateChangeEvent.event(this.state());
        for(StateChangeEventListener<State> listener: listeners()) listener.handle(evt);
    }
    default HandlerRegistration onStateChange(StateChangeEventListener<State> listener) {
        listeners().add(listener);
        return ()->listeners().remove(listener);
    }
}

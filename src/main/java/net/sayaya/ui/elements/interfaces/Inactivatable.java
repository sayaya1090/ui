package net.sayaya.ui.elements.interfaces;

import elemental2.dom.HTMLElement;

public interface Inactivatable<E extends HTMLElement, SELF extends Inactivatable<E, SELF>> {
    default SELF disable() {
        return disable(true);
    }
    SELF disable(boolean disabled);
    default SELF enable() {
        return disable(false);
    }
    default SELF enable(boolean enabled) {
        return disable(false);
    }
}

package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;

public interface Elevatable<E extends HTMLElement, SELF extends Elevatable<E, SELF>> {
    SELF elevated(boolean elevated);
    default SELF elevated() {
        return elevated(true);
    }
}

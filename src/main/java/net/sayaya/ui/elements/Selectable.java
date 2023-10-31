package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;

public interface Selectable<E extends HTMLElement, SELF extends Selectable<E, SELF>> {
    SELF selected(boolean selected);
    default SELF selected() {
        return selected(true);
    }
}

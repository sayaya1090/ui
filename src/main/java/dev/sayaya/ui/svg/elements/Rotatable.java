package dev.sayaya.ui.svg.elements;

import dev.sayaya.ui.svg.dom.SVGElement;
import org.jboss.elemento.IsElement;

public interface Rotatable<E extends SVGElement, SELF extends Rotatable<E, SELF>> extends IsElement<E> {
    SELF that();
    default SELF rotate(int param) {
        return rotate(String.valueOf(param));
    }
    default SELF rotate(String param) {
        element().setAttribute("rotate", param);
        return that();
    }
}
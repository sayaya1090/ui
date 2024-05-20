package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGElement;
import org.jboss.elemento.IsElement;

public interface HasPosition<E extends SVGElement, SELF extends HasPosition<E, SELF>> extends IsElement<E> {
    SELF that();
    default SELF x(int param) {
        return x(String.valueOf(param));
    }
    default SELF x(String param) {
        element().setAttribute("x", param);
        return that();
    }
    default SELF y(int param) {
        return y(String.valueOf(param));
    }
    default SELF y(String param) {
        element().setAttribute("y", param);
        return that();
    }
}
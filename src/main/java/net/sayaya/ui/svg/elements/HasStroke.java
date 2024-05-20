package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGElement;
import org.jboss.elemento.IsElement;

public interface HasStroke<E extends SVGElement, SELF extends HasStroke<E, SELF>> extends IsElement<E> {
    SELF that();
    default SELF stroke(String param) {
        element().setAttribute("stroke", param);
        return that();
    }
    default SELF strokeWidth(int param) {
        return strokeWidth(String.valueOf(param));
    }
    default SELF strokeWidth(String param) {
        element().setAttribute("stroke-width", param);
        return that();
    }
}
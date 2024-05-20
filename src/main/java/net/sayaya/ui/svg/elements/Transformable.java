package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGElement;
import org.jboss.elemento.IsElement;

public interface Transformable<E extends SVGElement, SELF extends Transformable<E, SELF>> extends IsElement<E> {
    SELF that();
    default SELF transform(String param) {
        element().setAttribute("transform", param);
        return that();
    }
}
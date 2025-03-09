package dev.sayaya.ui.svg.elements;

import dev.sayaya.ui.svg.dom.SVGElement;
import org.jboss.elemento.IsElement;

public interface HasFill<E extends SVGElement, SELF extends HasFill<E, SELF>> extends IsElement<E> {
    SELF that();
    default SELF fill(String param) {
        element().setAttribute("fill", param);
        return that();
    }
}

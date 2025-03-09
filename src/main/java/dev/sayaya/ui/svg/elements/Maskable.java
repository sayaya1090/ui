package dev.sayaya.ui.svg.elements;

import dev.sayaya.ui.svg.dom.SVGElement;
import org.jboss.elemento.IsElement;

public interface Maskable<E extends SVGElement, SELF extends Maskable<E, SELF>> extends IsElement<E> {
    SELF that();
    default SELF mask(SvgMaskBuilder mask) {
        return mask(mask.id());
    }
    default SELF mask(String id) {
        element().setAttribute("mask", "url(#" + id + ")");
        return that();
    }
}
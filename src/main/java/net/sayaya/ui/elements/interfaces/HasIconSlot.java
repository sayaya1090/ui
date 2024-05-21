package net.sayaya.ui.elements.interfaces;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import net.sayaya.ui.elements.IconButtonElementBuilder;
import net.sayaya.ui.elements.IconElementBuilder;
import net.sayaya.ui.svg.elements.IsSvgElement;
import org.jboss.elemento.Container;
import org.jboss.elemento.HTMLElementBuilder;

public interface HasIconSlot<E extends HTMLElement, SELF extends HasIconSlot<E, SELF>> extends Container<E, SELF> {
    default SELF icon(String icon) {
        return icon(IconElementBuilder.icon(icon));
    }
    default SELF icon(IconElementBuilder icon) {
        return icon(icon.element());
    }
    default SELF icon(IconButtonElementBuilder<?, ?> icon) {
        return icon(icon.element());
    }
    default SELF icon(HTMLElementBuilder<HTMLImageElement> icon) {
        return icon(icon.element());
    }
    default SELF icon(IsSvgElement<?, ?> element) {
        return icon(element.element());
    }

    default SELF icon(Element icon) {
        this.add(icon);
        icon.setAttribute("slot", "icon");
        return that();
    }
}
package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
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
    default SELF icon(HTMLElement icon) {
        this.add(icon);
        icon.setAttribute("slot", "icon");
        return that();
    }
}
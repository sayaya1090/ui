package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import net.sayaya.ui.dom.MdIconElement;
import org.jboss.elemento.Container;

public interface HasIcon<E extends HTMLElement, SELF extends HasIcon<E, SELF>> extends Container<E, SELF> {
    default SELF icon(String icon) {
        return icon(IconElementBuilder.icon(icon));
    }
    default SELF icon(IconElementBuilder icon) {
        return icon(icon.element());
    }
    default SELF icon(MdIconElement icon) {
        this.add(icon);
        icon.setAttribute("slot", "icon");
        return that();
    }
}

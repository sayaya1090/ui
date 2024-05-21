package net.sayaya.ui.elements.interfaces;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import net.sayaya.ui.elements.IconButtonElementBuilder;
import net.sayaya.ui.elements.IconElementBuilder;
import net.sayaya.ui.svg.elements.IsSvgElement;
import org.jboss.elemento.Container;
import org.jboss.elemento.HTMLElementBuilder;

public interface HasEndSlot<E extends HTMLElement, SELF extends HasEndSlot<E, SELF>> extends Container<E, SELF> {
    default SELF end(IconElementBuilder icon) {
        return end(icon.element());
    }
    default SELF end(IconButtonElementBuilder<?, ?> icon) {
        return end(icon.element());
    }
    default SELF end(HTMLElementBuilder<HTMLImageElement> icon) {
        return end(icon.element());
    }
    default SELF end(IsSvgElement<?, ?> element) {
        return end(element.element());
    }
    default SELF end(Element icon) {
        this.add(icon);
        icon.setAttribute("slot", "end");
        return that();
    }
}

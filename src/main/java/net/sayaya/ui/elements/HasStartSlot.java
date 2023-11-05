package net.sayaya.ui.elements;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import net.sayaya.ui.svg.IsSvgElement;
import org.jboss.elemento.Container;
import org.jboss.elemento.HTMLElementBuilder;

public interface HasStartSlot<E extends HTMLElement, SELF extends HasStartSlot<E, SELF>> extends Container<E, SELF> {
    default SELF start(IconElementBuilder icon) {
        return start(icon.element());
    }
    default SELF start(IconButtonElementBuilder<?, ?> icon) {
        return start(icon.element());
    }
    default SELF start(HTMLElementBuilder<HTMLImageElement> icon) {
        return start(icon.element());
    }
    default SELF start(IsSvgElement<?, ?> element) {
        return start(element.element());
    }
    default SELF start(Element icon) {
        this.add(icon);
        icon.setAttribute("slot", "start");
        return that();
    }
}

package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import org.jboss.elemento.Container;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.div;

public interface HasSupportingTextSlot<E extends HTMLElement, SELF extends HasSupportingTextSlot<E, SELF>> extends Container<E, SELF> {
    default SELF supportingText(String supportingText) {
        return supportingText(div().add(supportingText));
    }
    default SELF supportingText(IsElement<? extends HTMLElement> element) {
        return supportingText(element.element());
    }
    default SELF supportingText(HTMLElement element) {
        element.setAttribute("slot", "supporting-text");
        add(element);
        return that();
    }
}

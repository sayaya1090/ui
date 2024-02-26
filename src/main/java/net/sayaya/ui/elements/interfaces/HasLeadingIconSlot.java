package net.sayaya.ui.elements.interfaces;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import net.sayaya.ui.elements.IconButtonElementBuilder;
import net.sayaya.ui.elements.IconElementBuilder;
import org.jboss.elemento.Container;
import org.jboss.elemento.HTMLElementBuilder;

public interface HasLeadingIconSlot<E extends HTMLElement, SELF extends HasLeadingIconSlot<E, SELF>> extends Container<E, SELF> {
    default SELF iconLeading(String icon) {
        return iconLeading(IconElementBuilder.icon(icon));
    }
    default SELF iconLeading(IconElementBuilder icon) {
        return iconLeading(icon.element());
    }
    default SELF iconLeading(IconButtonElementBuilder<?, ?> icon) {
        return iconLeading(icon.element());
    }
    default SELF iconLeading(HTMLElementBuilder<HTMLImageElement> icon) {
        return iconLeading(icon.element());
    }
    default SELF iconLeading(HTMLElement icon) {
        this.add(icon);
        icon.setAttribute("slot", "leading-icon");
        return that();
    }
}
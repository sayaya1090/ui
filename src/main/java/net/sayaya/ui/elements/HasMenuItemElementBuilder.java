package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import org.jboss.elemento.Container;

public interface HasMenuItemElementBuilder<E extends HTMLElement, SELF extends HasMenuItemElementBuilder<E, SELF>> extends Container<E, SELF> {
    default MenuElementBuilder.MenuItemElementBuilder<SELF> item() {
        var item = new MenuElementBuilder.MenuItemElementBuilder<>(that());
        that().add(item);
        return item;
    }
}

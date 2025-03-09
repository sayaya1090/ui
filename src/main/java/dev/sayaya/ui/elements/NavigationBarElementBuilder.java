package dev.sayaya.ui.elements;

import dev.sayaya.ui.dom.MdNavigationBarElement;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.htmlElement;

public class NavigationBarElementBuilder implements ElementAttributeMethods<MdNavigationBarElement, NavigationBarElementBuilder>, ElementClassListMethods<MdNavigationBarElement, NavigationBarElementBuilder>,
        ElementContainerMethods<MdNavigationBarElement, NavigationBarElementBuilder>, ElementEventMethods<MdNavigationBarElement, NavigationBarElementBuilder>,
        HTMLElementStyleMethods<MdNavigationBarElement, NavigationBarElementBuilder>, HTMLElementVisibilityMethods<MdNavigationBarElement, NavigationBarElementBuilder> {
    public static NavigationBarElementBuilder badge() {
        return new NavigationBarElementBuilder();
    }
    private final HTMLElementBuilder<MdNavigationBarElement> that = htmlElement("md-navigation-bar", MdNavigationBarElement.class);
    @Override
    public MdNavigationBarElement element() {
        return that.element();
    }
    @Override
    public NavigationBarElementBuilder that() {
        return this;
    }
}

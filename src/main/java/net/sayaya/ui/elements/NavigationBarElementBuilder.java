package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import net.sayaya.ui.dom.MdBadgeElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.htmlElement;

public class NavigationBarElementBuilder implements HasElement<MdBadgeElement, NavigationBarElementBuilder>, HasHTMLElement<MdBadgeElement, NavigationBarElementBuilder> {
    public static NavigationBarElementBuilder badge() {
        return new NavigationBarElementBuilder();
    }
    private final HTMLElementBuilder<MdBadgeElement> that = htmlElement("md-badge", MdBadgeElement.class);
    public NavigationBarElementBuilder anchor(String id) {
        that.element().anchor = id;
        return that();
    }
    public NavigationBarElementBuilder anchorElement(IsElement<? extends HTMLElement> elem) {
        return anchorElement(elem.element());
    }
    public NavigationBarElementBuilder anchorElement(HTMLElement anchor) {
        that.element().anchorElement = anchor;
        return that();
    }
    @Override
    public MdBadgeElement element() {
        return that.element();
    }
    @Override
    public NavigationBarElementBuilder that() {
        return this;
    }
}

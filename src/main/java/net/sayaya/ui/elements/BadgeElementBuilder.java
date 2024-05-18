package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import net.sayaya.ui.dom.MdBadgeElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.htmlElement;

public class BadgeElementBuilder implements HasElement<MdBadgeElement, BadgeElementBuilder>, HasHTMLElement<MdBadgeElement, BadgeElementBuilder> {
    public static BadgeElementBuilder badge() {
        return new BadgeElementBuilder();
    }
    private final HTMLElementBuilder<MdBadgeElement> that = htmlElement("md-badge", MdBadgeElement.class);
    public BadgeElementBuilder anchor(String id) {
        that.element().anchor = id;
        return that();
    }
    public BadgeElementBuilder anchorElement(IsElement<? extends HTMLElement> elem) {
        return anchorElement(elem.element());
    }
    public BadgeElementBuilder anchorElement(HTMLElement anchor) {
        that.element().anchorElement = anchor;
        return that();
    }
    @Override
    public MdBadgeElement element() {
        return that.element();
    }
    @Override
    public BadgeElementBuilder that() {
        return this;
    }
}

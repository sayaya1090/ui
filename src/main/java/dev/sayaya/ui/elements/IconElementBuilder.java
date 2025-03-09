package dev.sayaya.ui.elements;

import dev.sayaya.ui.dom.MdIconElement;
import dev.sayaya.ui.svg.elements.IsSvgElement;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.htmlElement;

public class IconElementBuilder implements HTMLElementStyleMethods<MdIconElement, IconElementBuilder>, HTMLElementVisibilityMethods<MdIconElement, IconElementBuilder>, ElementAttributeMethods<MdIconElement, IconElementBuilder>,
        ElementClassListMethods<MdIconElement, IconElementBuilder>, ElementEventMethods<MdIconElement, IconElementBuilder>, ElementTextMethods<MdIconElement, IconElementBuilder> {
    public static IconElementBuilder icon() {
        return new IconElementBuilder();
    }
    public static IconElementBuilder icon(String icon) {
        return new IconElementBuilder().add(icon);
    }
    public static IconElementBuilder icon(IsSvgElement<?, ?> icon) {
        return new IconElementBuilder().add(icon);
    }

    private final HTMLElementBuilder<MdIconElement> that = htmlElement("md-icon", MdIconElement.class);
    public IconElementBuilder add(String icon) {
        that.element().append(icon);
        return that();
    }
    public IconElementBuilder add(IsSvgElement<?, ?> icon) {
        element().append(icon.element());
        return that();
    }
    @Override
    public MdIconElement element() {
        return that.element();
    }
    @Override
    public IconElementBuilder that() {
        return this;
    }
}

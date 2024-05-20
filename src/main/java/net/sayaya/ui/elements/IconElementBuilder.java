package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdIconElement;
import net.sayaya.ui.svg.elements.IsSvgElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class IconElementBuilder implements HasHTMLElement<MdIconElement, IconElementBuilder>, HasElement<MdIconElement, IconElementBuilder> {
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
        that.add(icon);
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

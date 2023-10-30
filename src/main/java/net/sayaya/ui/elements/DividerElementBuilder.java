package net.sayaya.ui.elements;

import lombok.experimental.Delegate;
import net.sayaya.ui.dom.MdDividerElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HasHTMLElement;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

import static org.jboss.elemento.Elements.htmlElement;

public class DividerElementBuilder implements HasHTMLElement<MdDividerElement, DividerElementBuilder> {
    public static DividerElementBuilder divider() {
        return new DividerElementBuilder();
    }
    public static DividerElementBuilder dividerInset() {
        return new DividerElementBuilder().type(DividerType.INSET);
    }
    public static DividerElementBuilder dividerInsetStart() {
        return new DividerElementBuilder().type(DividerType.INSET_START);
    }

    @Delegate(excludes = { IsElement.class, TypedBuilder.class, HasHTMLElement.class })
    private final HTMLElementBuilder<MdDividerElement> that;
    private DividerElementBuilder() {
        that = htmlElement("md-divider", MdDividerElement.class);
    }
    private DividerElementBuilder type(DividerType type) {
        if(type==null || type == DividerType.FULL) {
            element().inset = false;
            element().insetStart = false;
        } else if(type == DividerType.INSET) {
            element().inset = true;
            element().insetStart = false;
        } else if(type == DividerType.INSET_START) {
            element().inset = false;
            element().insetStart = true;
        }
        return this;
    }
    public DividerElementBuilder separator() {
        element().role = "separator";
        return this;
    }
    @Override
    public MdDividerElement element() {
        return that.element();
    }
    @Override
    public DividerElementBuilder that() {
        return this;
    }
    private enum DividerType {
        FULL, INSET, INSET_START
    }
}

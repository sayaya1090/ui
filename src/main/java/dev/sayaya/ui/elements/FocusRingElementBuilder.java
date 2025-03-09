package dev.sayaya.ui.elements;

import dev.sayaya.ui.dom.MdFocusRingElement;
import elemental2.dom.HTMLElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HTMLElementStyleMethods;
import org.jboss.elemento.HTMLElementVisibilityMethods;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.htmlElement;

public class FocusRingElementBuilder implements HTMLElementStyleMethods<MdFocusRingElement, FocusRingElementBuilder>, HTMLElementVisibilityMethods<MdFocusRingElement, FocusRingElementBuilder> {
    public static FocusRingElementBuilder focusRing() {
        return new FocusRingElementBuilder();
    }
    private final HTMLElementBuilder<MdFocusRingElement> that = htmlElement("md-focus-ring", MdFocusRingElement.class);
    public FocusRingElementBuilder control(IsElement<? extends HTMLElement> elem) {
        return control(elem.element());
    }
    public FocusRingElementBuilder control(HTMLElement elem) {
        element().control = elem;
        return that();
    }
    public FocusRingElementBuilder inward() {
        return inward(true);
    }
    public FocusRingElementBuilder inward(boolean inward) {
        element().inward = inward;
        return that();
    }
    @Override
    public MdFocusRingElement element() {
        return that.element();
    }
    @Override
    public FocusRingElementBuilder that() {
        return this;
    }
}

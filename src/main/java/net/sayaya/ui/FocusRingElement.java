package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class FocusRingElement extends HTMLElement {
    public boolean inward;
    @JsProperty(name="for")
    public String _for;
    @JsOverlay
    public static FocusRingElementBuilder focusRing() {
        return new FocusRingElementBuilder();
    }
    public static class FocusRingElementBuilder implements HtmlContent<FocusRingElement, HtmlContentBuilder<FocusRingElement>> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<FocusRingElement> that;
        private FocusRingElementBuilder() {
            delegate = htmlElement("md-focus-ring", HTMLElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        public FocusRingElementBuilder inward() {
            element().inward = true;
            return this;
        }
        public FocusRingElementBuilder attach(Element elem) {
            element()._for = elem.id;
            return this;
        }
        @Override
        public FocusRingElement element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<FocusRingElement> that() {
            return that;
        }
    }
}
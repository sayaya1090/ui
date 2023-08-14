package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class IconElement extends HTMLElement {
    @JsOverlay
    public static IconElementBuilder icon(String icon) {
        return new IconElementBuilder(icon);
    }
    public static class IconElementBuilder implements HtmlContent<HTMLElement, HtmlContentBuilder<HTMLElement>> {
        @Delegate(excludes = IsElement.class)
        private final HtmlContentBuilder<HTMLElement> delegate;
        private IconElementBuilder(String icon) {
            delegate = htmlElement("md-icon", HTMLElement.class);
            delegate.add(icon);
        }
        @Override
        public IconElement element() {
            return Js.uncheckedCast(delegate.element());
        }
    }
}

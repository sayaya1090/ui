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
import org.jboss.elemento.TypedBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class DividerElement extends HTMLElement {
    @JsOverlay
    public static DividerElementBuilder divider() {
        return new DividerElementBuilder();
    }
    @JsOverlay
    public static DividerElementBuilder dividerInset() {
        return new DividerElementBuilder().type(DividerType.INSET);
    }
    @JsOverlay
    public static DividerElementBuilder dividerInsetStart() {
        return new DividerElementBuilder().type(DividerType.INSET_START);
    }
    public boolean inset;
    public boolean insetStart;
    public String role;

    public static class DividerElementBuilder implements HtmlContent<DividerElement, HtmlContentBuilder<DividerElement>> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<DividerElement> that;
        private DividerElementBuilder() {
            delegate = htmlElement("md-divider", HTMLElement.class);
            element().role = "separator";
            that = new HtmlContentBuilder<>(element());
        }
        public DividerElementBuilder type(DividerType type) {
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
        @Override
        public DividerElement element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<DividerElement> that() {
            return that;
        }
    }
    public enum DividerType {
        FULL, INSET, INSET_START
    }
}

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
public class CheckboxElement extends HTMLElement {
    public boolean checked;
    public boolean indeterminate;
    public String ariaLabel;
    @JsOverlay
    public static CheckboxElementBuilder checkbox() {
        return new CheckboxElementBuilder();
    }
    public static class CheckboxElementBuilder implements HtmlContent<CheckboxElement, HtmlContentBuilder<CheckboxElement>> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<CheckboxElement> that;
        private CheckboxElementBuilder() {
            delegate = htmlElement("md-checkbox", HTMLElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        public CheckboxElementBuilder state(CheckboxState state) {
            if(state==null || state == CheckboxState.UNCHECKED) {
                element().checked = false;
                element().indeterminate = false;
            } else if(state == CheckboxState.CHECKED) {
                element().checked = true;
                element().indeterminate = false;
            } else if(state == CheckboxState.INDETERMINATE) {
                element().checked = false;
                element().indeterminate = true;
            }
            return this;
        }
        public CheckboxElementBuilder ariaLabel(String label) {
            element().ariaLabel = label;
            return this;
        }
        @Override
        public CheckboxElement element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<CheckboxElement> that() {
            return that;
        }
    }
    public enum CheckboxState {
        UNCHECKED, CHECKED, INDETERMINATE
    }
}

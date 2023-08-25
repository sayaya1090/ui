package net.sayaya.ui.chip;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import net.sayaya.ui.ChipElement;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class AssistChipElement extends ChipElement {

    public String label;
    public String ariaLabel;
    public boolean disabled;
    public boolean elevated;
    public String href;

    public static class AssistChipElementBuilder extends ChipElementBuilder<AssistChipElement, AssistChipElementBuilder> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<AssistChipElement> that;
        public AssistChipElementBuilder() {
            delegate = htmlElement("md-assist-chip", HTMLElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        public AssistChipElementBuilder label(String label) {
            element().label = label;
            return this;
        }
        public AssistChipElementBuilder enabled(boolean enabled) {
            element().disabled = !enabled;
            return this;
        }
        public AssistChipElementBuilder href(String url) {
            element().href = url;
            return this;
        }
        @Override
        public AssistChipElement element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<AssistChipElement> that() {
            return that;
        }
    }
}
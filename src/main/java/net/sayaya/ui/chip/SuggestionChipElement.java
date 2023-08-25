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
public class SuggestionChipElement extends ChipElement {
    public String label;
    public String ariaLabel;
    public boolean disabled;
    public boolean elevated;
    public String href;

    public static class SuggestionChipElementBuilder extends ChipElementBuilder<SuggestionChipElement, SuggestionChipElementBuilder> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<SuggestionChipElement> that;
        public SuggestionChipElementBuilder() {
            delegate = htmlElement("md-suggestion-chip", HTMLElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        public SuggestionChipElementBuilder label(String label) {
            element().label = label;
            return this;
        }
        public SuggestionChipElementBuilder enabled(boolean enabled) {
            element().disabled = !enabled;
            return this;
        }
        public SuggestionChipElementBuilder href(String url) {
            element().href = url;
            return this;
        }
        @Override
        public SuggestionChipElement element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<SuggestionChipElement> that() {
            return that;
        }
    }
}

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
public class InputChipElement extends ChipElement {
    public String label;
    public String ariaLabel;
    public boolean disabled;
    public boolean elevated;
    public String href;

    public static class InputChipElementBuilder extends ChipElementBuilder<InputChipElement, InputChipElementBuilder> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<InputChipElement> that;
        public InputChipElementBuilder() {
            delegate = htmlElement("md-input-chip", HTMLElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        public InputChipElementBuilder label(String label) {
            element().label = label;
            return this;
        }
        public InputChipElementBuilder enabled(boolean enabled) {
            element().disabled = !enabled;
            return this;
        }
        public InputChipElementBuilder href(String url) {
            element().href = url;
            return this;
        }
        @Override
        public InputChipElement element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<InputChipElement> that() {
            return that;
        }
    }
}

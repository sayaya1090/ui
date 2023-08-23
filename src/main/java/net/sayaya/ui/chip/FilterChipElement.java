package net.sayaya.ui.chip;

import elemental2.dom.DomGlobal;
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
public class FilterChipElement extends ChipElement {
    public static class FilterChipElementBuilder extends ChipElementBuilder<FilterChipElement, FilterChipElementBuilder> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<FilterChipElement> that;
        public FilterChipElementBuilder() {
            delegate = htmlElement("md-filter-chip", HTMLElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        @Override
        public FilterChipElement element() {
            DomGlobal.console.log("1");
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<FilterChipElement> that() {
            return that;
        }
    }
}

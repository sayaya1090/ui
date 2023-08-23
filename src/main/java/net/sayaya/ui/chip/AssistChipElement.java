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
public class AssistChipElement extends ChipElement {

    public static class AssistChipElementBuilder extends ChipElementBuilder<AssistChipElement, AssistChipElementBuilder> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<AssistChipElement> that;
        public AssistChipElementBuilder() {
            delegate = htmlElement("md-assist-chip", HTMLElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        @Override
        public AssistChipElement element() {
            DomGlobal.console.log("2");
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<AssistChipElement> that() {
            return that;
        }
    }
}
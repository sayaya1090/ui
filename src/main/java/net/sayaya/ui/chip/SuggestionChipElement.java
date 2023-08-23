package net.sayaya.ui.chip;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.ChipElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class SuggestionChipElement extends ChipElement {
    @JsOverlay
    public static HtmlContentBuilder<SuggestionChipElement> suggestion() {
        return htmlElement("md-suggestion-chip", SuggestionChipElement.class);
    }
}

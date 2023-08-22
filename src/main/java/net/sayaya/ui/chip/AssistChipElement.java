package net.sayaya.ui.chip;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.ChipElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class AssistChipElement extends ChipElement {
    @JsOverlay
    public static HtmlContentBuilder<AssistChipElement> filledField() {
        return htmlElement("md-assist-chip", AssistChipElement.class);
    }
}
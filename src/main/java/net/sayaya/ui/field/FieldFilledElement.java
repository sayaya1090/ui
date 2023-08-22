package net.sayaya.ui.field;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.FieldElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class FieldFilledElement extends FieldElement {
    @JsOverlay
    public static HtmlContentBuilder<FieldFilledElement> filledField() {
        return htmlElement("md-filled-field", FieldFilledElement.class);
    }
}

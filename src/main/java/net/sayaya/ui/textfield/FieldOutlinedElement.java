package net.sayaya.ui.textfield;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.FieldElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class FieldOutlinedElement extends FieldElement {
    @JsOverlay
    public static HtmlContentBuilder<FieldOutlinedElement> outlinedTextField() {
        return htmlElement("md-outlined-text-field", FieldOutlinedElement.class);
    }
}

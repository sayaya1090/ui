package net.sayaya.ui.progress;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.ProgressElement;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class CircularElement extends ProgressElement {
    public static class CircularElementBuilder extends ProgressElementBuilder<CircularElement, CircularElementBuilder> {
        public CircularElementBuilder() {
            super(htmlElement("md-circular-progress", HTMLElement.class));
        }
    }
}
package net.sayaya.ui.progress;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.ProgressElement;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class LinearElement extends ProgressElement {
    public static class LinearElementBuilder extends ProgressElement.ProgressElementBuilder<LinearElement, LinearElementBuilder> {
        public LinearElementBuilder() {
            super(htmlElement("md-linear-progress", HTMLElement.class));
        }
    }
}
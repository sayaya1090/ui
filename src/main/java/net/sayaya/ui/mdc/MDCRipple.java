package net.sayaya.ui.mdc;

import elemental2.dom.Element;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc.ripple")
public final class MDCRipple {
    public MDCRipple(Element elem){}
    @JsProperty public boolean unbounded;
}

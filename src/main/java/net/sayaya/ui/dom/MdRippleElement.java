package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdRipple")
public class MdRippleElement extends HTMLElement {
    public boolean disabled;
    public String htmlFor;
    public HTMLElement control;

    public native void attach(HTMLElement control);
    public native void detach();
}
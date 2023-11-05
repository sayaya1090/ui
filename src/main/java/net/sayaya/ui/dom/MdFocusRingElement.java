package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdFocusRing")
public class MdFocusRingElement extends HTMLElement {
    public boolean visible;
    public boolean inward;
    public String htmlFor;
    public HTMLElement control;

    public native void attach(HTMLElement control);
    public native void detach();
}

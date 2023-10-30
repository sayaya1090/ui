package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdDivider")
public class MdDividerElement extends HTMLElement {
    public boolean inset;
    public boolean insetStart;
    public String role;
}

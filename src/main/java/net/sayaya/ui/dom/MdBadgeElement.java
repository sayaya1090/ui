package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdBadge")
public class MdBadgeElement extends HTMLElement {
    public String anchor;
    public HTMLElement anchorElement;
    public String value;
}

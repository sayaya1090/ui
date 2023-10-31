package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdListItem")
public class MdListItemElement extends HTMLElement {
    public boolean disabled;
    public String type;
    public String href;
    public String target;
}
package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdList")
public class MdListElement extends HTMLElement {
    public MdListItemElement[] items;
    public native MdListItemElement activateNextItem();
    public native MdListItemElement activatePreviousItem();
}
package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdChipSet")
public class MdChipSetElement extends HTMLElement {
    public MdChipElement[] chips;
}
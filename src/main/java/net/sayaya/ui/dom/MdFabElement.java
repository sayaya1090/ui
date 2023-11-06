package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class MdFabElement extends HTMLElement {
    public String variant;
    public String size;
    public String label;
    public boolean lowered;

    @JsType(isNative = true, namespace = "mdc", name="MdFab")
    public static class MdPlainFabElement extends MdFabElement {

    }

    @JsType(isNative = true, namespace = "mdc", name="MdBrandedFab")
    public static class MdBrandedFabElement extends MdFabElement {

    }
}

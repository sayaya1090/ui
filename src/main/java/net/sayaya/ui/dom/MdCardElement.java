package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class MdCardElement extends HTMLElement {
    @JsType(isNative = true, namespace = "mdc", name="MdElevatedCard")
    public static class MdElevatedCardElement extends MdCardElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdFilledCard")
    public static class MdFilledCardElement extends MdCardElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdOutlinedCard")
    public static class MdOutlinedCardElement extends MdCardElement {

    }
}
